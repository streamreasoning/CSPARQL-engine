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
package eu.larkc.csparql.sparql.jena;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.TypeMapper;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.RDFSRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.apache.jena.vocabulary.ReasonerVocabulary;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.data_source.Datasource;
import eu.larkc.csparql.common.exceptions.ReasonerException;
import eu.larkc.csparql.common.hardware_resource.Memory;
import eu.larkc.csparql.common.utils.ReasonerChainingType;
import eu.larkc.csparql.sparql.api.SparqlEngine;
import eu.larkc.csparql.sparql.api.SparqlQuery;
import eu.larkc.csparql.sparql.jena.common.JenaReasonerWrapper;
import eu.larkc.csparql.sparql.jena.data_source.JenaDatasource;
import eu.larkc.csparql.sparql.jena.ext.timestamp;

public class JenaEngine implements SparqlEngine {

	private Datasource jds = new JenaDatasource();

	private HashMap<String, JenaReasonerWrapper> reasonerMap = new HashMap<String, JenaReasonerWrapper>();

	private boolean activateInference = false;
	//	private String inferenceRulesFileSerialization = null;
	//	private String entailmentRegimeType = null;
	//	private String tBoxFileSerialization = null;

	private Model model = null;

	Map<String, Model> graphs = new HashMap<String, Model>();

	Map<Statement,Long> timestamps = new HashMap<Statement,Long>();

	private boolean performTimestampFunction = false;

	private Logger logger = LoggerFactory.getLogger(JenaEngine.class.getName());

	public void setPerformTimestampFunctionVariable(boolean value){
		performTimestampFunction = value;
	}

	public String getEngineType(){
		return "jena";
	}

	public JenaEngine() {
		super();
		FunctionRegistry.get().put("http://larkc.eu/csparql/sparql/jena/ext#timestamp", timestamp.class) ;
		timestamp.timestamps = timestamps;
	}


	public void addStatement(final String subject, final String predicate, final String object) {
		this.addStatement(subject, predicate, object, 0);
	}

	public void addStatement(final String subject, final String predicate, final String object, final long timestamp) {

		final Statement s;

		String[] objectParts = object.split("\\^\\^");
		if(objectParts.length > 1) {

			TypeMapper tm = TypeMapper.getInstance();
			RDFDatatype d = tm.getTypeByName(objectParts[1]);
			Literal lObject = model.createTypedLiteral(objectParts[0].replaceAll("\"", ""),d);

			s = new StatementImpl(new ResourceImpl(subject), new PropertyImpl(predicate), lObject); 

		} else {

			s = new StatementImpl(new ResourceImpl(subject), new PropertyImpl(predicate), new ResourceImpl(object));    	 
		}

		if(performTimestampFunction){
			if(timestamp != 0){
				timestamps.put(s, new Long(timestamp));
			}
		}
		this.model.add(s);
	}

	public void clean() {
		// TODO implement SparqlEngine.clean
		this.model.remove(this.model);
		timestamps.clear();
	}


	public void destroy() {
		this.model.close();
		timestamps.clear();
	}


	public RDFTable evaluateQuery(final SparqlQuery query) {

		long startTS = System.currentTimeMillis();

		final Query q = QueryFactory.create(query.getQueryCommand(), Syntax.syntaxSPARQL_11);

		for(String s: q.getGraphURIs()){
			List<RDFTuple> list = jds.getNamedModel(s);
			for(RDFTuple t : list)
				addStatement(t.get(0), t.get(1), t.get(2));
		}

		QueryExecution qexec;

		if(reasonerMap.containsKey(query.getId())){
			if(reasonerMap.get(query.getId()).isActive()){
				Reasoner reasoner = (Reasoner) reasonerMap.get(query.getId()).getReasoner();
				InfModel infmodel = ModelFactory.createInfModel(reasoner, this.model);	
				qexec = QueryExecutionFactory.create(q, infmodel);
			} else {
				qexec = QueryExecutionFactory.create(q, model);
			}
		} else {
			qexec = QueryExecutionFactory.create(q, model);
		}

		//		if(activateInference){
		//			
		//			if(inferenceRulesFileSerialization == null || inferenceRulesFileSerialization.isEmpty()){
		//				logger.debug("RDFS reasoner");
		//				System.out.println("RDFS reasoner");
		//				InfModel infmodel = ModelFactory.createRDFSModel(this.model);
		//				qexec = QueryExecutionFactory.create(q, infmodel);
		//			} else {
		//				try{
		//					//					logger.debug("Custom Reasoner. Loading inference rule from {}", inferenceRulesFilePath);
		//					Reasoner reasoner;
		//					if(reasonerMap.containsKey(query.getId())){
		//						reasoner = (Reasoner) reasonerMap.get(query.getId()).getReasoner();
		//					} else{
		//						//						Model m = ModelFactory.createDefaultModel();
		//						//						Resource configuration =  m.createResource();
		//						//						configuration.addProperty(ReasonerVocabulary.PROPruleMode, entailmentRegimeType);
		//						//						configuration.addProperty(ReasonerVocabulary.PROPruleSet, inferenceRulesFilePath);
		//						//						reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
		//
		//						reasoner = new GenericRuleReasoner(Rule.parseRules(Rule.rulesParserFromReader(new BufferedReader(new StringReader(inferenceRulesFileSerialization)))));
		//						reasoner.setParameter(ReasonerVocabulary.PROPruleMode, entailmentRegimeType);
		//						if(tBoxFileSerialization != null){
		//							//							Model tBox = ModelFactory.createDefaultModel();
		//							//							tBox = FileManager.get().loadModel("file:" + tBoxFilePath);
		//							try{
		//								reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization),null , "RDF/XML"));
		//							} catch (Exception e) {
		//								try{
		//									reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization),null, "N-TRIPLE"));
		//								} catch (Exception e1) {
		//									try{
		//										reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization),null, "TURTLE"));
		//									} catch (Exception e2) {
		//										try{
		//											reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFileSerialization),null, "RDF/JSON"));
		//										} catch (Exception e3) {
		//											logger.error(e.getMessage(), e3);
		//										}
		//									}
		//								}
		//							}
		//						}
		//						reasonerMap.put(query.getId(), new JenaReasonerWrapper(reasoner, true));
		//					}
		//					InfModel infmodel = ModelFactory.createInfModel(reasoner, this.model);
		//					//					infmodel.write(System.out);
		//					qexec = QueryExecutionFactory.create(q, infmodel);
		//				} catch(Exception e){
		//					e.printStackTrace();
		//					logger.debug("RDFS reasoner");
		//					System.out.println("RDFS reasoner");
		//					InfModel infmodel = ModelFactory.createRDFSModel(this.model);
		//					qexec = QueryExecutionFactory.create(q, infmodel);
		//				}
		//			}
		//		} else{
		//			qexec = QueryExecutionFactory.create(q, model);
		//		}

		RDFTable table = null;

		if (q.isSelectType())
		{

			final ResultSet resultSet = qexec.execSelect();

			table = new RDFTable(resultSet.getResultVars());

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ResultSetRewindable tempResultSet = ResultSetFactory.makeRewindable(resultSet);

			ResultSetFormatter.outputAsJSON(bos, tempResultSet);
			table.setJsonSerialization(bos.toString());

			tempResultSet.reset();

			for (; tempResultSet.hasNext();) {
				final RDFTuple tuple = new RDFTuple();
				QuerySolution soln = tempResultSet.nextSolution();

				for (String s : table.getNames()) {
					RDFNode n = soln.get(s);
					if (n == null)
						tuple.addFields("");
					else
						tuple.addFields(format(n));
				}
				table.add(tuple);
			}
		}
		else if (q.isAskType())
		{
			table = new RDFTable("Answer");
			final RDFTuple tuple = new RDFTuple();
			tuple.addFields("" + qexec.execAsk());
			table.add(tuple);
		}
		else if (q.isDescribeType() || q.isConstructType())
		{
			Model m = null;
			if (q.isDescribeType())
				m = qexec.execDescribe();
			else
				m = qexec.execConstruct();

			table = new RDFTable("Subject", "Predicate", "Object");

			StringWriter w = new StringWriter();
			m.write(w,"RDF/JSON");
			table.setJsonSerialization(w.toString());

			StmtIterator it = m.listStatements();
			while (it.hasNext())
			{
				final RDFTuple tuple = new RDFTuple();
				Statement stm = it.next();
				tuple.addFields(formatSubject(stm.getSubject()),formatPredicate(stm.getPredicate()), format(stm.getObject())); 
				table.add(tuple);
			}
		}

		//		jds.removeNamedModel("http://streamreasoning.org/" + query.getId() + "_" + actualTs);

		long endTS = System.currentTimeMillis();

		Object[] object = new Object[6];

		object[0] = query.getId();
		object[1] = (endTS - startTS);
		object[2] = table.size();
		object[3] = Memory.getTotalMemory();
		object[4] = Memory.getFreeMemory();
		object[5] = Memory.getMemoryUsage();

		logger.debug("Information about execution of query {} \n Execution Time : {} \n Results Number : {} \n Total Memory : {} mb \n " +
				"Free Memory : {} mb \n Memory Usage : {} mb", object);

		//		System.out.println(endTS - startTS);
		//		try {
		//		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/baldo/Desktop/queryExecutionTimeReasoning.csv", true)));
		//		    out.println(System.currentTimeMillis() + "," + (endTS - startTS));
		//		    out.close();
		//		} catch (Exception e) {
		//		}

		return table;
	}

	private String format(RDFNode n) {
		if (n.isLiteral())
			return "\"" + n.asLiteral().getLexicalForm() + "\"^^" + n.asLiteral().getDatatypeURI(); 
		else
			return n.toString();
	}

	private String formatPredicate(Property predicate) {
		return predicate.toString();
	}

	private String formatSubject(Resource subject) {
		return subject.toString();
	}

	public void initialize() {
		this.model = ModelFactory.createDefaultModel();
	}

	private List<RDFTuple> modelToTupleList(Model m){
		List<RDFTuple> list = new ArrayList<RDFTuple>();
		StmtIterator it = m.listStatements();
		while (it.hasNext())
		{
			final RDFTuple tuple = new RDFTuple();
			Statement stm = it.next();
			tuple.addFields(formatSubject(stm.getSubject()),formatPredicate(stm.getPredicate()), format(stm.getObject())); 
			list.add(tuple);
		}
		return list;
	}

	@Override
	public RDFTable evaluateGeneralQueryOverDatasource(String queryBody){
		return jds.evaluateGeneralQuery(queryBody);
	}

	@Override
	public void execUpdateQueryOverDatasource(String queryBody){
		jds.execUpdateQuery(queryBody);
	}


	@Override
	public void putStaticNamedModel(String iri, String modelReference) {
		Model m = ModelFactory.createDefaultModel();
		try{
			m.read(iri);
		} catch(Exception e){
			StringReader sr = new StringReader(modelReference);
			try{
				m.read(sr, null, "RDF/XML");
			} catch(Exception e1){
				try{
					sr = new StringReader(modelReference);
					m.read(sr, null, "TURTLE");
				} catch(Exception e2){
					try{
						sr = new StringReader(modelReference);
						m.read(sr, null, "N-TRIPLE");
					} catch(Exception e3){
						sr = new StringReader(modelReference);
						m.read(sr, null, "RDF/JSON");
					}
				}
			}
			sr.close();
		}
		jds.putNamedModel(iri, modelToTupleList(m));
	}

	@Override
	public void removeStaticNamedModel(String iri) {
		jds.removeNamedModel(iri);
	}

	@Override
	public Datasource getDataSource() {
		return jds;
	}

	//	@Override
	//	public void activateInference() {
	//		this.activateInference = true;		
	//	}
	//
	//	@Override
	//	public void activateInference(String rulesFileSerialization, String entailmentRegimeType) {
	//		this.activateInference = true;		
	//		this.inferenceRulesFileSerialization = rulesFileSerialization;
	//		this.entailmentRegimeType = entailmentRegimeType.toLowerCase();
	//	}
	//
	//	@Override
	//	public void activateInference(String rulesFileSerialization,	String entailmentRegimeType, String tBoxFileSerialization) {
	//		this.activateInference = true;		
	//		this.inferenceRulesFileSerialization = rulesFileSerialization;
	//		this.entailmentRegimeType = entailmentRegimeType.toLowerCase();
	//		this.tBoxFileSerialization = tBoxFileSerialization;
	//	}


	@SuppressWarnings("unchecked")
	@Override
	public void setReasonerMap(Object reasonerMap) {
		this.reasonerMap = (HashMap<String, JenaReasonerWrapper>) reasonerMap;
	}

	@Override
	public void addReasonerToReasonerMap(String queryId, Object reasoner) {
		reasonerMap.put(queryId, new JenaReasonerWrapper(reasoner, true));
	}

	@Override
	public void arrestInference(String queryId) throws ReasonerException { 
		JenaReasonerWrapper jrw = this.reasonerMap.get(queryId);
		if(jrw == null)
			throw new ReasonerException("No reasoner for the specified query. Please add new reasoner using the updateReasoner method");
		else {
			jrw.setActive(false);
			this.reasonerMap.put(queryId, jrw);
		}
	}

	@Override
	public void restartInference(String queryId) throws ReasonerException {
		JenaReasonerWrapper jrw = this.reasonerMap.get(queryId);
		if(jrw == null)
			throw new ReasonerException("No reasoner for the specified query. Please add new reasoner using the updateReasoner method");
		else {
			jrw.setActive(true);
			this.reasonerMap.put(queryId, jrw);
		}
	}

	@Override
	public void updateReasoner(String queryId) {
		Resource config = ModelFactory.createDefaultModel()
				.createResource()
				.addProperty(ReasonerVocabulary.PROPsetRDFSLevel, "simple");
		Reasoner reasoner = RDFSRuleReasonerFactory.theInstance().create(config);		
		addReasonerToReasonerMap(queryId, reasoner);

	}

	@Override
	public void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType) {
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(Rule.rulesParserFromReader(new BufferedReader(new StringReader(rulesFile)))));
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
		addReasonerToReasonerMap(queryId, reasoner);
	}

	@Override
	public void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType, String tBoxFile) {
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(Rule.rulesParserFromReader(new BufferedReader(new StringReader(rulesFile)))));
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
		try{
			reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFile),null , "RDF/XML"));
		} catch (Exception e) {
			try{
				reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFile),null, "N-TRIPLE"));
			} catch (Exception e1) {
				try{
					reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFile),null, "TURTLE"));
				} catch (Exception e2) {
					try{
						reasoner = reasoner.bindSchema(ModelFactory.createDefaultModel().read(new StringReader(tBoxFile),null, "RDF/JSON"));
					} catch (Exception e3) {
						logger.error(e.getMessage(), e3);
					}
				}
			}
		}
		addReasonerToReasonerMap(queryId, reasoner);
	}

	@Override
	public boolean getInferenceStatus() {
		return this.activateInference;
	}

	@Override
	public void parseSparqlQuery(SparqlQuery query) throws ParseException {
		Query spQuery = QueryFactory.create(query.getQueryCommand(), Syntax.syntaxSPARQL_11);
		for(String s: spQuery.getGraphURIs()){
			if(!jds.containsNamedModel(s))
				throw new ParseException("The model in the FROM clause is missing in the internal dataset, please put the static model in the dataset using putStaticNamedModel(String iri, String location) method of the engine.", 0);
		}

	}
}
