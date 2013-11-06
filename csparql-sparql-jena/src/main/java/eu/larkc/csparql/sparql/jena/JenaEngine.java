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
package eu.larkc.csparql.sparql.jena;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.query.ResultSetRewindable;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import com.hp.hpl.jena.sparql.function.FunctionRegistry;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.hardware_resource.Memory;
import eu.larkc.csparql.sparql.api.SparqlEngine;
import eu.larkc.csparql.sparql.api.SparqlQuery;
import eu.larkc.csparql.sparql.jena.data_source.JenaDatasource;
import eu.larkc.csparql.sparql.jena.ext.timestamp;

public class JenaEngine implements SparqlEngine {

	private JenaDatasource jds = new JenaDatasource();

	private Map<String, Reasoner> reasonerMap = new HashMap<String, Reasoner>();

	private boolean activateInference = false;
	private String inferenceRulesFilePath = null;

	private Model model = null;

	Map<String, Model> graphs = new HashMap<String, Model>();

	Map<Statement,Long> timestamps = new HashMap<Statement,Long>();

	private boolean performTimestampFunction = false;

	private Logger logger = LoggerFactory.getLogger(JenaEngine.class.getName());

	//	List<Statement> ambiguousResources = new LinkedList<Statement>();

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
				//			System.out.println(s + " , " + timestamp);
				//			if (timestamps.containsKey(s)) {
				////				ambiguousResources.add(s);
				//				timestamps.remove(s);
				//				timestamps.put(s, new Long(timestamp));
				//			} else {
				//				timestamps.put(s, new Long(timestamp));
				//			}

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

		// remove ambiguous resources from the timestamps hash map
		//		for (Statement s : ambiguousResources) {
		//			timestamps.remove(s);
		//		}
		// clears the temporary list of ambiguous resources
		//		ambiguousResources.clear();


		final Query q = QueryFactory.create(query.getQueryCommand(), Syntax.syntaxARQ);

		//		long actualTs = System.currentTimeMillis();
		//
		//change URI in something better
		//		jds.putNamedModel("http://streamreasoning.org/" + query.getId() + "_" + actualTs, modelToTupleList(model));
		//		q.addNamedGraphURI("http://streamreasoning.org/" + query.getId() + "_" + actualTs);

		for(String s: q.getGraphURIs()){
			if(!jds.containsNamedModel(s)){
				Model m = ModelFactory.createDefaultModel();
				m.read(s);
				jds.putNamedModel(s, modelToTupleList(m));
				this.model.add(m);
			} else {
				List<RDFTuple> list = jds.getNamedModel(s);
				for(RDFTuple t : list)
					addStatement(t.get(0), t.get(1), t.get(2));

			}


		}


		//		for(String s: q.getGraphURIs())
		//		{
		//			//			if (!graphs.containsKey(s))
		//			//			{
		//			//				Model m = ModelFactory.createDefaultModel();
		//			//				m.read(s);
		//			//				graphs.put(s, m);
		//			//			}
		//
		//			//			m.read(s);
		//			//			graphs.put(s, m);
		//
		//			//			Model m = ModelFactory.createDefaultModel();
		//			//			String q2Str = "CONSTRUCT {?s ?p ?o} FROM <" + s + "> WHERE {?s ?p ?o}";
		//			//			Query q2 = QueryFactory.create(q2Str, Syntax.syntaxSPARQL_11);
		//			//			QueryExecution qexec2 = QueryExecutionFactory.sparqlService("http://localhost:3030/ds/query", q2);
		//			//			m.add(qexec2.execConstruct());
		//			//			while(rs.hasNext()){
		//			//				QuerySolution qs = rs.next();
		//			//				StatementImpl sImpl = null;
		//			//				if(qs.get("o").isResource())
		//			//					sImpl = new StatementImpl(new ResourceImpl(qs.get("s").toString()), new PropertyImpl(qs.get("p").toString()), new ResourceImpl(qs.get("o").toString()));
		//			//				else if(qs.get("o").isLiteral())
		//			//					sImpl = new StatementImpl(new ResourceImpl(qs.get("s").toString()), new PropertyImpl(qs.get("p").toString()), m.createTypedLiteral(qs.getLiteral("o").getLexicalForm(), qs.getLiteral("o").getDatatype()));
		//			//				m.add(sImpl);
		//			//			}
		//			//			m.read(s);
		//
		//			Model m = ModelFactory.createDefaultModel();
		//			m.read(s);
		//			graphs.put(s, m);
		//
		//			this.model.add(graphs.get(s));
		//		}

		//		String sq = "PREFIX ex:<http://streamreasoning.org#> " +
		//				"SELECT ?s ?p ?o  " +
		//				"FROM NAMED <" + "http://streamreasoning.org/" + query.getId() + "_" + actualTs + ">" +
		//				"WHERE { " +
		//				"GRAPH ?g { ?s ?p ?o . }" +
		//				"}";

		//		String sq = "PREFIX ex:<http://streamreasoning.org#> " +
		//				"SELECT * " +
		//				"FROM NAMED <" + "http://streamreasoning.org/" + query.getId() + "_" + actualTs + ">" +
		//				"FROM <http://127.0.0.1/~baldo/StaticKnowledgeTest.rdf> " +
		//				"WHERE { " +
		//				"GRAPH ?g {?w1 ex:isIn ?r1 . " +
		//				"?w2 ex:isIn ?r2 . " +
		//				"?r1 ex:contiguous ?r2 . " +
		//				"FILTER(?w1 != ?w2 && ?r1 != ?r2) " +
		//				"} " +
		//				"}";
		//
		//
		//		Query q1 = QueryFactory.create(sq, Syntax.syntaxARQ);

		QueryExecution qexec;
		if(activateInference){
			if(inferenceRulesFilePath == null || inferenceRulesFilePath.isEmpty()){
				logger.debug("RDFS reasoner");
				System.out.println("RDFS reasoner");
				InfModel infmodel = ModelFactory.createRDFSModel(this.model);
				qexec = QueryExecutionFactory.create(q, infmodel);
			} else {
				try{
					logger.debug("Custom Reasoner. Loading inference rule from {}", inferenceRulesFilePath);
					System.out.println("Custom Reasoner. Loading inference rule from " + inferenceRulesFilePath);
					Reasoner reasoner;
					if(reasonerMap.containsKey(query.getId())){
						reasoner = reasonerMap.get(query.getId());
					} else{
						Model m = ModelFactory.createDefaultModel();
						Resource configuration =  m.createResource();
						configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
						configuration.addProperty(ReasonerVocabulary.PROPruleSet, inferenceRulesFilePath);
						reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
						reasonerMap.put(query.getId(), reasoner);
					}
					InfModel infmodel = ModelFactory.createInfModel(reasoner, this.model);
					qexec = QueryExecutionFactory.create(q, infmodel);
				} catch(Exception e){
					e.printStackTrace();
					logger.debug("RDFS reasoner");
					System.out.println("RDFS reasoner");
					InfModel infmodel = ModelFactory.createRDFSModel(this.model);
					qexec = QueryExecutionFactory.create(q, infmodel);
				}
			}
		} else{
			qexec = QueryExecutionFactory.create(q, model);
		}

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

			table = new RDFTable("Subject", "Predicate", "Object", "Timestamp");

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
	public void execUpdateQueryOverDatasource(String queryBody){
		jds.execUpdateQuery(queryBody);
	}

	@Override
	public void activateInference() {
		this.activateInference = true;		
	}

	@Override
	public void setInferenceRulesFilePath(String path) {
		inferenceRulesFilePath = path;		
	}


}
