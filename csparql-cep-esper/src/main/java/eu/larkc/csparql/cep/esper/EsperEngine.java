/*******************************************************************************
 * Copyright 2014 DEIB -Politecnico di Milano
 *   
 *  Marco Balduini (marco.balduini@polimi.it)
 *  Emanuele Della Valle (emanuele.dellavalle@polimi.it)
 *  Davide Barbieri
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *   
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *   
 *  Acknowledgements:
 *  
 *  This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
package eu.larkc.csparql.cep.esper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EPStatementState;
import com.espertech.esper.client.time.CurrentTimeEvent;

import eu.larkc.csparql.cep.api.CepEngine;
import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.common.config.Config;
import eu.larkc.csparql.common.utils.CsparqlUtils;

public class EsperEngine implements CepEngine {

	private EPServiceProvider epService = null;
	private Map<String, CepQuery> queries = null;
	private Collection<RdfStream> streams = null;
	private Map<String, EPStatement> statements = null;
	private final Configuration configuration = new Configuration();
	private ArrayBlockingQueue<RdfQuadruple> queue;
	private boolean enableInjecter;
	private Long currentSystemTime;
	//private Long lastSlideTime;
	//private boolean initSysteTime = false;

	public Collection<CepQuery> getAllQueries() {
		return this.queries.values();
	}

	public Collection<RdfStream> getAllRegisteredStreams() {
		return streams;
	}

	public void initialize() {

		// if using external timestamp
		configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(!Config.INSTANCE.isEsperUsingExternalTimestamp());
		// Obtain an engine instance
		this.epService = EPServiceProviderManager.getDefaultProvider(this.configuration);
		// ...and initialize it
		this.epService.initialize();

		// initialize collections
		this.queries = new HashMap<String, CepQuery>();
		this.streams = new ArrayList<RdfStream>();
		this.statements = new HashMap<String, EPStatement>();
	}

	public void setUpInjecter(int queueDimension){

		if(queueDimension == 0){
			enableInjecter = false;
		} else {

			enableInjecter = true;
			this.queue = new ArrayBlockingQueue<RdfQuadruple>(queueDimension);

			EsperInjecter esj = new EsperInjecter(queue, this);
			Thread esjThread = new Thread(esj);
			esjThread.start();
		}
	}

	public EPServiceProvider getEpService() {
		return epService;
	}

	public void setEpService(EPServiceProvider epService) {
		this.epService = epService;
	}

	public void registerStream(final RdfStream p) {
		String un = p.uniqueName();
		//this.epService.getEPAdministrator().getConfiguration().addImport(RdfQuadruple.class);
		/*
		 * there are two problems need to be fixed with using external time stamp.
		 * 1. how to get range and step here
		 * 2. how to take care the steps of mutli queries. like, there is an empty slide.  
		 */
		this.epService.getEPAdministrator().getConfiguration().addEventType(un,
				RdfQuadruple.class);
		p.addObserver(this);
		this.streams.add(p);
	}

	public void unregisterQuery(final String id) {
		this.queries.remove(id);
	}

	public RdfSnapshot registerQuery(final String query, final String id) {

		//		String tempQuery = query;
		//		tempQuery = tempQuery.replace("time", "time_batch");
		//		tempQuery = tempQuery.replace(")", " , \"FORCE_UPDATE , START_EAGER\")");
		//		tempQuery = tempQuery.substring(0, tempQuery.indexOf(")"));
		//		tempQuery = tempQuery + ") output snapshot every 3 seconds";
		//		System.out.println(tempQuery);
		final EsperQuery qry = new EsperQuery(query);
		this.queries.put(id, qry);
		final EPStatement stmt = this.epService.getEPAdministrator().createEPL(query);
		this.statements.put(id, stmt);

		final QueryListener l = new QueryListener(id);
		stmt.addListener(l);
		return l;
	}

	public void destroy() {
		this.epService.destroy();
	}

//	public void update(final GenericObservable<RdfQuadruple> observed, final RdfQuadruple q) {
//		RdfStream s = (RdfStream) observed;
//		q.setStreamName(s.getIRI());
//
//		if(!enableInjecter){
//			this.epService.getEPRuntime().sendEvent(q);
//		} else {
//			synchronized(queue){
//				try{
//					queue.add(q);
//				} catch(IllegalStateException e){
//					System.out.println("Queue Full");
//				}
//			}
//		}
//	}

	public void startQuery(final String id) {
		final EPStatement s = this.getStatementById(id);

		if (s != null) {
			s.start();
		}
	}

	public void stopQuery(final String id) {
		final EPStatement s = this.getStatementById(id);

		if (s != null) {
			EPStatementState state = s.getState();
			if (state.compareTo(EPStatementState.STOPPED) != 0)
				s.stop();
		}
	}

	private EPStatement getStatementById(final String id) {
		if (this.statements.containsKey(id)) {
			return this.statements.get(id);
		}

		return null;
	}

	public void unregisterStream(final RdfStream stream) {
		this.streams.remove(stream);
	}

	public String getCepEngineType() {
		return "esper";
	}

	@Override
	public void update(Observable o, Object arg) {
		
		RdfStream s = (RdfStream) o;
		RdfQuadruple q = (RdfQuadruple) arg;
		q.setStreamName(s.getIRI());

		if(!enableInjecter){
			this.currentSystemTime = this.setCurrentTimeAndSentEvent(q);
		} else {
			synchronized(queue){
				try{
					queue.add(q);
				} catch(IllegalStateException e){
					System.out.println("Queue Full");
				}
			}
		}
		q = null;
		System.gc();
	}
	
	@Override
	public Long getCurrentTime() {
		return this.currentSystemTime;
	}
	
//	@Override
//	public Long getLastSlideTime() {
//		return this.lastSlideTime;
//	}
	
//	public Long setInitialTime(Long inputTime) {
		//long slide = CacheAcquaMN.INSTANCE.getSlideLength() * 1000L;
//		long slide = 1000; 
//		if (!this.initSysteTime) {
			// using the slide that before current input time as initial time
//			Long tempTime = inputTime / slide * slide;
//			this.currentSystemTime = tempTime;
//			this.lastSlideTime = tempTime;
//			CurrentTimeEvent timeEvent = new CurrentTimeEvent(inputTime);
//			this.epService.getEPRuntime().sendEvent(timeEvent);
//			this.initSysteTime = true;
//		}
//		return this.currentSystemTime;
//	}
	
//	public Long setInitialTime(Long inputTime) {
//		if (!this.initSysteTime) {
//			CurrentTimeEvent timeEvent = new CurrentTimeEvent(inputTime);
//			this.epService.getEPRuntime().sendEvent(timeEvent);
//			this.initSysteTime = true;
//		}
//		return this.currentSystemTime;
//	}
	
	@Override
	public Long setCurrentTimeAndSentEvent(RdfQuadruple q) {
		long inputTime = q.getTimestamp();
		this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(inputTime));
		this.currentSystemTime = inputTime;
		this.epService.getEPRuntime().sendEvent(q);
		return this.currentSystemTime;
	}

//	@Override
//	public Long setCurrentTimeAndSentEvent(RdfQuadruple q) {
//		long inputTime = q.getTimestamp();
//		//long slide = 1000; //CacheAcquaMN.INSTANCE.getSlideLength() * 1000L;
//		
//		setInitialTime(inputTime);
//
//		while (inputTime >= this.lastSlideTime + slide) {
//			this.lastSlideTime += slide;
//			this.currentSystemTime = this.lastSlideTime;
//			if (inputTime == this.lastSlideTime) {
//				// in case of empty slide (no data arrived during slide), the slide still needs to be triggered.
//				// logger.debug(" in setTimeStamp equal input {},  new Time {}",inputTime,
//				// this.currentSystemTime);
//				// slide include (lastSlide  currentTime]
//				this.epService.getEPRuntime().sendEvent(q);
//				this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(inputTime));
//				return this.currentSystemTime;
//			}
//			// inputTime > this.lastSlideTime + slide
//			CurrentTimeEvent timeEvent = new CurrentTimeEvent(this.currentSystemTime);
//			this.epService.getEPRuntime().sendEvent(timeEvent);
//		}
//		if (inputTime > this.currentSystemTime) {
//			// logger.debug(" in setTimeStamp last if input {}, new Time {}",inputTime,
//			// this.currentSystemTime);
//			this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(inputTime));
//			this.currentSystemTime = inputTime;
//		}
//		this.epService.getEPRuntime().sendEvent(q);
//		return this.currentSystemTime;
//
//	}
}
