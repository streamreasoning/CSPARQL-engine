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

import eu.larkc.csparql.cep.api.CepEngine;
import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;
import eu.larkc.csparql.cep.api.RdfStream;

public class EsperEngine implements CepEngine {

	private EPServiceProvider epService = null;
	private Map<String, CepQuery> queries = null;
	private Collection<RdfStream> streams = null;
	private Map<String, EPStatement> statements = null;
	private final Configuration configuration = new Configuration();
	private ArrayBlockingQueue<RdfQuadruple> queue;
	private boolean enableInjecter;

	public Collection<CepQuery> getAllQueries() {
		return this.queries.values();
	}

	public Collection<RdfStream> getAllRegisteredStreams() {
		return streams;
	}

	public void initialize() {

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
		this.epService.getEPAdministrator().getConfiguration().addImport(RdfQuadruple.class);
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
			this.epService.getEPRuntime().sendEvent(q);
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
}
