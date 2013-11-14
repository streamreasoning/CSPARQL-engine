/*******************************************************************************
 * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
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
 * 
 * This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
package eu.larkc.csparql.core.engine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.CepEngine;
import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.cep.esper.EsperEngine;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.core.Configuration;
import eu.larkc.csparql.core.parser.StreamInfo;
import eu.larkc.csparql.core.parser.Translator;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
import eu.larkc.csparql.core.streams.formats.TranslationException;
import eu.larkc.csparql.sparql.api.SparqlEngine;
import eu.larkc.csparql.sparql.jena.JenaEngine;

public class CsparqlEngineImpl implements Observer, CsparqlEngine {

	private Configuration configuration = null;
	private Collection<CSparqlQuery> queries = null;
	private Map<String, RdfStream> streams = null;
	private Map<CSparqlQuery, RdfSnapshot> snapshots = null;
	private Map<CSparqlQuery, CsparqlQueryResultProxy> results = null;
	private CepEngine cepEngine = null;
	private SparqlEngine sparqlEngine = null;
	private Reasoner reasoner = null;

	protected final Logger logger = LoggerFactory
			.getLogger(CsparqlEngineImpl.class);	

	public Collection<CSparqlQuery> getAllQueries() {
		return this.queries;
	}

	public void initialize() {

		this.configuration = Configuration.getCurrentConfiguration();
		this.queries = new ArrayList<CSparqlQuery>();
		this.streams = new HashMap<String, RdfStream>();
		this.snapshots = new HashMap<CSparqlQuery, RdfSnapshot>();
		this.results = new HashMap<CSparqlQuery, CsparqlQueryResultProxy>();
		this.sparqlEngine = this.configuration.createSparqlEngine();
		this.cepEngine = this.configuration.createCepEngine();
		this.reasoner = this.configuration.createReasoner();
		this.cepEngine.initialize();
		this.sparqlEngine.initialize();
		this.setPerformTimestampFunctionVariable(false);
		this.setUpInjecter(0);

	}
	
	public void initialize(int queueDimension) {
		this.configuration = Configuration.getCurrentConfiguration();
		this.queries = new ArrayList<CSparqlQuery>();
		this.streams = new HashMap<String, RdfStream>();
		this.snapshots = new HashMap<CSparqlQuery, RdfSnapshot>();
		this.results = new HashMap<CSparqlQuery, CsparqlQueryResultProxy>();
		this.sparqlEngine = this.configuration.createSparqlEngine();
		this.cepEngine = this.configuration.createCepEngine();
		this.reasoner = this.configuration.createReasoner();
		this.cepEngine.initialize();
		this.sparqlEngine.initialize();	
		this.setPerformTimestampFunctionVariable(false);
		this.setUpInjecter(queueDimension);
	}

	public void initialize(boolean performTimestampFunction) {
		this.configuration = Configuration.getCurrentConfiguration();
		this.queries = new ArrayList<CSparqlQuery>();
		this.streams = new HashMap<String, RdfStream>();
		this.snapshots = new HashMap<CSparqlQuery, RdfSnapshot>();
		this.results = new HashMap<CSparqlQuery, CsparqlQueryResultProxy>();
		this.sparqlEngine = this.configuration.createSparqlEngine();
		this.cepEngine = this.configuration.createCepEngine();
		this.reasoner = this.configuration.createReasoner();
		this.cepEngine.initialize();
		this.sparqlEngine.initialize();	
		this.setPerformTimestampFunctionVariable(performTimestampFunction);
		this.setUpInjecter(0);
	}

	public void initialize(int queueDimension, boolean performTimestampFunction) {
		this.configuration = Configuration.getCurrentConfiguration();
		this.queries = new ArrayList<CSparqlQuery>();
		this.streams = new HashMap<String, RdfStream>();
		this.snapshots = new HashMap<CSparqlQuery, RdfSnapshot>();
		this.results = new HashMap<CSparqlQuery, CsparqlQueryResultProxy>();
		this.sparqlEngine = this.configuration.createSparqlEngine();
		this.cepEngine = this.configuration.createCepEngine();
		this.reasoner = this.configuration.createReasoner();
		this.cepEngine.initialize();
		this.sparqlEngine.initialize();	
		this.setPerformTimestampFunctionVariable(performTimestampFunction);
		this.setUpInjecter(queueDimension);
	}

	public void setPerformTimestampFunctionVariable(boolean value){
		if(sparqlEngine.getEngineType().equals("jena")){
			JenaEngine je = (JenaEngine) sparqlEngine;
			je.setPerformTimestampFunctionVariable(value);
		}
	}
	
	public void setUpInjecter(int queueDimension){
		if(cepEngine.getCepEngineType().equals("esper")){
			EsperEngine ee = (EsperEngine) cepEngine;
			ee.setUpInjecter(queueDimension);
		}
	}
	
	@Override
	public void activateInference() {
		sparqlEngine.activateInference();		
	}
	
	@Override
	public void setInferenceRulesFilePath(String path){
		sparqlEngine.setInferenceRulesFilePath(path);
	}
	
	@Override
	public void execUpdateQueryOverDatasource(String queryBody){
		JenaEngine je = (JenaEngine) sparqlEngine;
		je.execUpdateQueryOverDatasource(queryBody);
	}

	private CSparqlQuery getQueryByID(final String id) {
		for (final CSparqlQuery q : this.queries) {
			if (q.getId().equalsIgnoreCase(id)) {
				return q;
			}
		}

		return null;
	}

	public RdfStream registerStream(final RdfStream s) {
		this.streams.put(s.getIRI(), s);
		this.cepEngine.registerStream(s);
		return s;
	}

	public void unregisterDataProvider(final RdfStream provider) {
		this.streams.remove(provider);
	}

	private void unregisterAllQueries() {

		for (final CSparqlQuery q : this.queries) {
			this.unregisterQuery(q.getId());
		}
	}

	public void startQuery(final String id) {
		this.cepEngine.startQuery(id);
	}

	public void stopQuery(final String id) {



		this.cepEngine.stopQuery(id);
	}

	private void unregisterQuery(final CSparqlQuery q) {

		this.stopQuery(q.getId());

		if (q != null) {
			this.queries.remove(q);
		}
	}

	public void unregisterQuery(final String id) {
		final CSparqlQuery q = this.getQueryByID(id);
		this.unregisterQuery(q);
	}

	public void unregisterStream(final String iri) {

		final RdfStream r = this.getStreamByIri(iri);

		if (r == null) {
			return;
		}

		this.streams.remove(iri);
	}

	public CsparqlQueryResultProxy registerQuery(final String command) throws ParseException {

		final Translator t = Configuration.getCurrentConfiguration().createTranslator(this);

		CSparqlQuery query = null;

		try {
			query = t.translate(command);
		} catch (final TranslationException e) {
			throw new ParseException(e.getMessage(), 0);
		}

		final RdfSnapshot s = this.cepEngine.registerQuery(query.getCepQuery()
				.getQueryCommand(), query.getId());

		final CsparqlQueryResultProxy result = new CsparqlQueryResultProxy(query.getId());

		this.queries.add(query);
		this.snapshots.put(query, s);
		this.results.put(query, result);
		      
//		      System.out.println();
//		      System.out.println("<CSPARQL----------------------------------->");
//		      System.out.println(System.currentTimeMillis());
//		      System.out.println(query.getCepQuery().getQueryCommand());
//		      System.out.println(query.getSparqlQuery().getQueryCommand());
//		      System.out.println("<CSPARQL----------------------------------->");
//		      System.out.println();
		
		s.addObserver(this);
		
		return result;
	}

	public void destroy() {

		this.unregisterAllQueries();
		this.cepEngine.destroy();
	}


	// Snapshot received
//	public void update(final GenericObservable<List<RdfQuadruple>> observed,
//			final List<RdfQuadruple> quads) {
//
//		long starttime = System.nanoTime();
//
//		final RdfSnapshot r = (RdfSnapshot) observed;
//
//		final CSparqlQuery csparqlquery = this.getQueryByID(r.getId());
//
//		final RdfSnapshot augmentedSnapshot = this.reasoner.augment(r);
//
//		this.snapshots.put(csparqlquery, augmentedSnapshot);
//
//		this.sparqlEngine.clean();
//
//		long count = 0;
//
//		for (final RdfQuadruple q : quads) {
//			if (isStreamUsedInQuery(csparqlquery, q.getStreamName()))
//			{
//				this.sparqlEngine.addStatement(q.getSubject(), q.getPredicate(), q.getObject(), q.getTimestamp());
//				count++;
//			}
//		}
//
//		if (count == 0)
//			return;
//
//		final RDFTable result = this.sparqlEngine.evaluateQuery(csparqlquery.getSparqlQuery());
//
//		timestamp(result, csparqlquery);
//
//		logger.info("results obtained in "+ (System.nanoTime()-starttime) + " nanoseconds");
//
//		this.notifySubscribers(csparqlquery, result);
//
//
//	}

	private void timestamp(RDFTable r, CSparqlQuery q) {
		if (q.getQueryCommand().toLowerCase().contains("register stream"))
			r.add("timestamp", "0");
		//TODO: da aggiungere il campo on the fly

	}

	private boolean isStreamUsedInQuery(CSparqlQuery csparqlquery, String streamName) {
		for (StreamInfo si : csparqlquery.getStreams()) {
			if (si.getIri().equalsIgnoreCase(streamName))
				return true;
		}

		return false;
	}


	private void notifySubscribers(final CSparqlQuery csparqlquery, final RDFTable result) {

		final CsparqlQueryResultProxy res = this.results.get(csparqlquery);
		
		res.notify(result);
	}

	public RdfStream getStreamByIri(final String iri) {

		if (this.streams.containsKey(iri)) {
			return this.streams.get(iri);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		long starttime = System.nanoTime();

		final RdfSnapshot r = (RdfSnapshot) o;
		List<RdfQuadruple> quads = (List<RdfQuadruple>) arg;

		final CSparqlQuery csparqlquery = this.getQueryByID(r.getId());

		final RdfSnapshot augmentedSnapshot = this.reasoner.augment(r);

		this.snapshots.put(csparqlquery, augmentedSnapshot);

		this.sparqlEngine.clean();

		long count = 0;

		for (final RdfQuadruple q : quads) {
			if (isStreamUsedInQuery(csparqlquery, q.getStreamName()))
			{
				this.sparqlEngine.addStatement(q.getSubject(), q.getPredicate(), q.getObject(), q.getTimestamp());
				count++;
			}
		}

		if (count == 0)
			return;

		final RDFTable result = this.sparqlEngine.evaluateQuery(csparqlquery.getSparqlQuery());

		timestamp(result, csparqlquery);

		logger.info("results obtained in "+ (System.nanoTime()-starttime) + " nanoseconds");

		this.notifySubscribers(csparqlquery, result);
		
	}

}
