/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
package eu.larkc.csparql.cep.esper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class EsperEngine implements CepEngine {

	private EPServiceProvider epService = null;
	private Map<String, CepQuery> queries = null;
	private Collection<RdfStream> streams = null;
	private Map<String, EPStatement> statements = null;
	private final Configuration configuration = new Configuration();
	private ArrayBlockingQueue<RdfQuadruple> queue;
	private boolean enableInjecter;
	private Long currentSystemTime;
	private Long currentSystemLastTickTime;
	// private Long lastSlideTime;
	private boolean isSysTimeInit = false;
	private long timeStampTick = 1000L;
	private List<RdfQuadruple> quadAtBoundary = new ArrayList<RdfQuadruple>();

	protected final Logger logger = LoggerFactory.getLogger(EsperEngine.class);

	public Collection<CepQuery> getAllQueries() {
		return this.queries.values();
	}

	public Collection<RdfStream> getAllRegisteredStreams() {
		return streams;
	}

	public void initialize() {

		// if using external timestamp
		if (Config.INSTANCE.isEsperUsingExternalTimestamp()) {
			this.configuration.getEngineDefaults().getThreading().setInternalTimerEnabled(!Config.INSTANCE.isEsperUsingExternalTimestamp());
			timeStampTick = Config.INSTANCE.getTimeStampTick();
		}

		// Obtain an engine instance
		this.epService = EPServiceProviderManager.getDefaultProvider(this.configuration);
		// ...and initialize it
		this.epService.initialize();

		// initialize collections
		this.queries = new HashMap<String, CepQuery>();
		this.streams = new ArrayList<RdfStream>();
		this.statements = new HashMap<String, EPStatement>();
	}

	public void setUpInjecter(int queueDimension) {

		if (queueDimension == 0) {
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
		// this.epService.getEPAdministrator().getConfiguration().addImport(RdfQuadruple.class);
		/*
		 * there are two problems need to be fixed with using external time stamp. 1. how to get range and step here 2. how to take care the steps of mutli queries. like, there is an empty slide.
		 */
		this.epService.getEPAdministrator().getConfiguration().addEventType(un, RdfQuadruple.class);
		p.addObserver(this);
		this.streams.add(p);
	}

	public void unregisterQuery(final String id) {
		this.queries.remove(id);
	}

	public RdfSnapshot registerQuery(final String query, final String id) {

		// String tempQuery = query;
		// tempQuery = tempQuery.replace("time", "time_batch");
		// tempQuery = tempQuery.replace(")",
		// " , \"FORCE_UPDATE , START_EAGER\")");
		// tempQuery = tempQuery.substring(0, tempQuery.indexOf(")"));
		// tempQuery = tempQuery + ") output snapshot every 3 seconds";
		// System.out.println(tempQuery);
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

	// public void update(final GenericObservable<RdfQuadruple> observed, final
	// RdfQuadruple q) {
	// RdfStream s = (RdfStream) observed;
	// q.setStreamName(s.getIRI());
	//
	// if(!enableInjecter){
	// this.epService.getEPRuntime().sendEvent(q);
	// } else {
	// synchronized(queue){
	// try{
	// queue.add(q);
	// } catch(IllegalStateException e){
	// System.out.println("Queue Full");
	// }
	// }
	// }
	// }

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

		if (!enableInjecter) {
			this.currentSystemTime = this.setCurrentTimeAndSentEvent(q);
		} else {
			synchronized (queue) {
				try {
					queue.add(q);
				} catch (IllegalStateException e) {
					System.out.println("Queue Full");
				}
			}
		}
		q = null;
		// System.gc();
	}

	@Override
	public Long getCurrentTime() {
		return this.currentSystemTime;
	}

	@Override
	public Long setCurrentTimeAndSentEvent(RdfQuadruple q) {
		long inputTime = q.getTimestamp();
		if (!isSysTimeInit) {
			// Using the beginning of the current tick as the the window boundary
			long initTime = inputTime / this.timeStampTick * this.timeStampTick;
			this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(initTime));
			isSysTimeInit = true;
			this.currentSystemTime = initTime;
			this.currentSystemLastTickTime = initTime;
			this.currentSystemTime = inputTime;
			// the order of the initial event is very important
			// it must be sending the event first and then the timestamp
			// this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(initTime+1));
			this.epService.getEPRuntime().sendEvent(q);
			this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(inputTime));
			return this.currentSystemTime;
		}
		while (inputTime > (this.currentSystemLastTickTime + this.timeStampTick)) {
			if (this.quadAtBoundary.size() > 0) {
				long boundayTime = this.quadAtBoundary.get(0).getTimestamp();
				for (RdfQuadruple tempQ : this.quadAtBoundary) {
					this.epService.getEPRuntime().sendEvent(tempQ);
				}
				this.quadAtBoundary = new ArrayList<RdfQuadruple>();
				this.currentSystemTime = boundayTime;
				this.currentSystemLastTickTime += this.timeStampTick;
				this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(this.currentSystemTime));
				this.currentSystemTime += 1;
				this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(this.currentSystemTime));

			} else {
				this.currentSystemLastTickTime += this.timeStampTick;
				this.currentSystemTime = this.currentSystemLastTickTime + 1;
				this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(this.currentSystemTime));
			}
		}
		if (inputTime == (this.currentSystemLastTickTime + this.timeStampTick)) {
			this.quadAtBoundary.add(q);
		} else if (inputTime < (this.currentSystemLastTickTime + this.timeStampTick)) {
			if (this.quadAtBoundary.size() > 0) {
				long boundayTime = this.quadAtBoundary.get(0).getTimestamp();
				if (inputTime < boundayTime)
					throw new RuntimeException("unordered stream");
			}
			if (this.currentSystemTime != inputTime) {
				this.currentSystemTime = inputTime;
				this.epService.getEPRuntime().sendEvent(new CurrentTimeEvent(inputTime));
			}
			this.epService.getEPRuntime().sendEvent(q);
		}
		return this.currentSystemTime;
	}
}
