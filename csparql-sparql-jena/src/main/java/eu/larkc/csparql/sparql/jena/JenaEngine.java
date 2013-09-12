/*
 * @(#)JenaEngine.java   1.0   02/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */

package eu.larkc.csparql.sparql.jena;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

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
import com.hp.hpl.jena.sparql.function.FunctionRegistry;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.sparql.api.SparqlEngine;
import eu.larkc.csparql.sparql.api.SparqlQuery;
import eu.larkc.csparql.sparql.jena.ext.timestamp;

public class JenaEngine implements SparqlEngine {

	private Model model = null;

	Map<String, Model> graphs = new HashMap<String, Model>();

	Map<Statement,Long> timestamps = new HashMap<Statement,Long>();

	private boolean performTimestampFunction = false;

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

		// remove ambiguous resources from the timestamps hash map
		//		for (Statement s : ambiguousResources) {
		//			timestamps.remove(s);
		//		}
		// clears the temporary list of ambiguous resources
		//		ambiguousResources.clear();

		final Query q = QueryFactory.create(query.getQueryCommand(), Syntax.syntaxARQ);

		for(String s: q.getGraphURIs())
		{
			if (!graphs.containsKey(s))
			{
				Model m = ModelFactory.createDefaultModel();
				m.read(s);
				graphs.put(s, m);
			}

			this.model.add(graphs.get(s));
		}

		final QueryExecution qexec = QueryExecutionFactory.create(q, this.model);

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

		//		System.out.println("Query Result: " + System.currentTimeMillis());

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




}