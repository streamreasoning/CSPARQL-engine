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
package eu.larkc.csparql.core.engine;

import java.io.BufferedReader;
import java.io.StringReader;
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

import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.vocabulary.ReasonerVocabulary;

import eu.larkc.csparql.cep.api.CepEngine;
import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.cep.esper.EsperEngine;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.exceptions.ReasonerException;
import eu.larkc.csparql.common.utils.ReasonerChainingType;
import eu.larkc.csparql.core.Configuration;
import eu.larkc.csparql.core.new_parser.utility_files.StreamInfo;
import eu.larkc.csparql.core.new_parser.utility_files.Translator;
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

	protected final Logger logger = LoggerFactory.getLogger(CsparqlEngineImpl.class);

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

	public void setPerformTimestampFunctionVariable(boolean value) {
		if (sparqlEngine.getEngineType().equals("jena")) {
			JenaEngine je = (JenaEngine) sparqlEngine;
			je.setPerformTimestampFunctionVariable(value);
		}
	}

	public void setUpInjecter(int queueDimension) {
		if (cepEngine.getCepEngineType().equals("esper")) {
			EsperEngine ee = (EsperEngine) cepEngine;
			ee.setUpInjecter(queueDimension);
		}
	}

	// @Override
	// public void activateInference() {
	// sparqlEngine.activateInference();
	// }
	//
	// @Override
	// public void activateInference(String rulesFile, String
	// entailmentRegimeType) {
	// sparqlEngine.activateInference(rulesFile, entailmentRegimeType);
	// }
	//
	// @Override
	// public void activateInference(String rulesFile, String
	// entailmentRegimeType, String tBoxFile) {
	// sparqlEngine.activateInference(rulesFile, entailmentRegimeType,
	// tBoxFile);
	// }

	@Override
	public boolean getInferenceStatus() {
		return sparqlEngine.getInferenceStatus();
	}

	@Override
	public void arrestInference(String queryId) {
		try {
			sparqlEngine.arrestInference(queryId);
		} catch (ReasonerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void restartInference(String queryId) {
		try {
			sparqlEngine.restartInference(queryId);
		} catch (ReasonerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void updateReasoner(String queryId) {
		sparqlEngine.updateReasoner(queryId);
	}

	@Override
	public void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType) {
		sparqlEngine.updateReasoner(queryId, rulesFile, chainingType);
	}

	@Override
	public void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType, String tBoxFile) {
		sparqlEngine.updateReasoner(queryId, rulesFile, chainingType, tBoxFile);
	}

	@Override
	public void execUpdateQueryOverDatasource(String queryBody) {
		sparqlEngine.execUpdateQueryOverDatasource(queryBody);
	}

	@Override
	public RDFTable evaluateGeneralQueryOverDatasource(String queryBody) {
		return sparqlEngine.evaluateGeneralQueryOverDatasource(queryBody);
	}

	@Override
	public void putStaticNamedModel(String iri, String serialization) {
		sparqlEngine.putStaticNamedModel(iri, serialization);
	}

	@Override
	public void removeStaticNamedModel(String iri) {
		sparqlEngine.removeStaticNamedModel(iri);
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

	@Override
	public CsparqlQueryResultProxy registerQuery(String command, boolean activateInference) throws ParseException {

		final Translator t = Configuration.getCurrentConfiguration().createTranslator(this);

		CSparqlQuery query = null;

		// Split continuous part from static part
		try {
			query = t.translate(command);
		} catch (final TranslationException e) {
			throw new ParseException(e.getMessage(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("CEP query: {}", query.getCepQuery().getQueryCommand());
		logger.debug("SPARQL query: {}", query.getSparqlQuery().getQueryCommand().replace("\n", "").replace("\r", ""));

		// Parse sparql(static) query
		sparqlEngine.parseSparqlQuery(query.getSparqlQuery());

		final RdfSnapshot s = this.cepEngine.registerQuery(query.getCepQuery().getQueryCommand(), query.getId());

		final CsparqlQueryResultProxy result = new CsparqlQueryResultProxy(query.getId());
		result.setSparqlQueryId(query.getSparqlQuery().getId());
		result.setCepQueryId(query.getCepQuery().getId());

		this.queries.add(query);
		this.snapshots.put(query, s);
		this.results.put(query, result);

		s.addObserver(this);

		if (activateInference) {
			logger.debug("RDFS reasoner");
			Resource config = ModelFactory.createDefaultModel().createResource().addProperty(ReasonerVocabulary.PROPsetRDFSLevel, "simple");
			org.apache.jena.reasoner.Reasoner reasoner = RDFSRuleReasonerFactory.theInstance().create(config);
			sparqlEngine.addReasonerToReasonerMap(query.getSparqlQuery().getId(), reasoner);
		}

		return result;
	}

	@Override
	public CsparqlQueryResultProxy registerQuery(String command, boolean activateInference, String rulesFileSerialization, ReasonerChainingType chainingType) throws ParseException {

		final Translator t = Configuration.getCurrentConfiguration().createTranslator(this);

		CSparqlQuery query = null;

		// Split continuous part from static part
		try {
			query = t.translate(command);
		} catch (final TranslationException e) {
			throw new ParseException(e.getMessage(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("CEP query: {}", query.getCepQuery().getQueryCommand());
		logger.debug("SPARQL query: {}", query.getSparqlQuery().getQueryCommand().replace("\n", "").replace("\r", ""));

		// Parse sparql(static) query
		sparqlEngine.parseSparqlQuery(query.getSparqlQuery());

		final RdfSnapshot s = this.cepEngine.registerQuery(query.getCepQuery().getQueryCommand(), query.getId());

		final CsparqlQueryResultProxy result = new CsparqlQueryResultProxy(query.getId());
		result.setSparqlQueryId(query.getSparqlQuery().getId());
		result.setCepQueryId(query.getCepQuery().getId());

		this.queries.add(query);
		this.snapshots.put(query, s);
		this.results.put(query, result);

		s.addObserver(this);

		if (activateInference) {
			logger.debug("Generic Rule Engine");
			org.apache.jena.reasoner.Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(Rule.rulesParserFromReader(new BufferedReader(new StringReader(rulesFileSerialization)))));
			switch (chainingType) {
			case BACKWARD:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "backward");
				break;
			case FORWARD:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "forward");
				break;
			case HYBRID:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "hybrid");
				break;
			default:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "forward");
				break;
			}
			sparqlEngine.addReasonerToReasonerMap(query.getSparqlQuery().getId(), reasoner);
		}

		return result;

	}

	@Override
	public CsparqlQueryResultProxy registerQuery(String command, boolean activateInference, String rulesFileSerialization, ReasonerChainingType chainingType, String tBoxFileSerialization)
			throws ParseException {
		final Translator t = Configuration.getCurrentConfiguration().createTranslator(this);

		CSparqlQuery query = null;

		// Split continuous part from static part
		try {
			query = t.translate(command);
		} catch (final TranslationException e) {
			throw new ParseException(e.getMessage(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.debug("CEP query: {}", query.getCepQuery().getQueryCommand());
		logger.debug("SPARQL query: {}", query.getSparqlQuery().getQueryCommand().replace("\n", "").replace("\r", ""));

		// Parse sparql(static) query
		sparqlEngine.parseSparqlQuery(query.getSparqlQuery());

		final RdfSnapshot s = this.cepEngine.registerQuery(query.getCepQuery().getQueryCommand(), query.getId());

		final CsparqlQueryResultProxy result = new CsparqlQueryResultProxy(query.getId());
		result.setSparqlQueryId(query.getSparqlQuery().getId());
		result.setCepQueryId(query.getCepQuery().getId());

		this.queries.add(query);
		this.snapshots.put(query, s);
		this.results.put(query, result);

		s.addObserver(this);

		if (activateInference) {
			logger.debug("Generic Rule Engine");
			org.apache.jena.reasoner.Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(Rule.rulesParserFromReader(new BufferedReader(new StringReader(rulesFileSerialization)))));
			switch (chainingType) {
			case BACKWARD:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "backward");
				break;
			case FORWARD:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "forward");
				break;
			case HYBRID:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "hybrid");
				break;
			default:
				reasoner.setParameter(ReasonerVocabulary.PROPruleMode, "forward");
				break;
			}
			try {
				reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization), null, "RDF/XML"));
			} catch (Exception e) {
				try {
					reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization), null, "N-TRIPLE"));
				} catch (Exception e1) {
					try {
						reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization), null, "TURTLE"));
					} catch (Exception e2) {
						try {
							reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization), null, "RDF/JSON"));
						} catch (Exception e3) {
							logger.error(e.getMessage(), e3);
						}
					}
				}
			}
			sparqlEngine.addReasonerToReasonerMap(query.getSparqlQuery().getId(), reasoner);
		}

		return result;
	}

	public void destroy() {

		this.unregisterAllQueries();
		this.cepEngine.destroy();
	}

	// Snapshot received
	// public void update(final GenericObservable<List<RdfQuadruple>> observed,
	// final List<RdfQuadruple> quads) {
	//
	// long starttime = System.nanoTime();
	//
	// final RdfSnapshot r = (RdfSnapshot) observed;
	//
	// final CSparqlQuery csparqlquery = this.getQueryByID(r.getId());
	//
	// final RdfSnapshot augmentedSnapshot = this.reasoner.augment(r);
	//
	// this.snapshots.put(csparqlquery, augmentedSnapshot);
	//
	// this.sparqlEngine.clean();
	//
	// long count = 0;
	//
	// for (final RdfQuadruple q : quads) {
	// if (isStreamUsedInQuery(csparqlquery, q.getStreamName()))
	// {
	// this.sparqlEngine.addStatement(q.getSubject(), q.getPredicate(),
	// q.getObject(), q.getTimestamp());
	// count++;
	// }
	// }
	//
	// if (count == 0)
	// return;
	//
	// final RDFTable result =
	// this.sparqlEngine.evaluateQuery(csparqlquery.getSparqlQuery());
	//
	// timestamp(result, csparqlquery);
	//
	// logger.info("results obtained in "+ (System.nanoTime()-starttime) +
	// " nanoseconds");
	//
	// this.notifySubscribers(csparqlquery, result);
	//
	//
	// }

	// private void timestamp(RDFTable r, CSparqlQuery q) {
	// if (q.getQueryCommand().toLowerCase().contains("register stream"))
	// r.add("timestamp", "0");
	// //TODO: da aggiungere il campo on the fly
	//
	// }

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
		
		final RdfSnapshot r = (RdfSnapshot) o;
		List<RdfQuadruple> quads = (List<RdfQuadruple>) arg;

		logger.debug("current time: {}", this.cepEngine.getCurrentTime());
//		for (final RdfQuadruple q : quads) {
//			//logger.debug(q.getSubject() + "\t" + q.getPredicate() + "\t" + q.getObject() + "\t" + (q.getTimestamp()));
//			logger.debug(q.getTimestamp()+"");
//		}
		
		final CSparqlQuery csparqlquery = this.getQueryByID(r.getId());

		final RdfSnapshot augmentedSnapshot = this.reasoner.augment(r);

		this.snapshots.put(csparqlquery, augmentedSnapshot);

		this.sparqlEngine.clean();

		long count = 0;

		for (final RdfQuadruple q : quads) {
			if (isStreamUsedInQuery(csparqlquery, q.getStreamName())) {
				this.sparqlEngine.addStatement(q.getSubject(), q.getPredicate(), q.getObject(), q.getTimestamp());
				count++;
			}
		}

		if (count == 0)
			return;

		final RDFTable result = this.sparqlEngine.evaluateQuery(csparqlquery.getSparqlQuery());

		// timestamp(result, csparqlquery);

		// logger.info("results obtained in "+ (System.nanoTime()-starttime) +
		// " nanoseconds");

		this.notifySubscribers(csparqlquery, result);

	}
}
