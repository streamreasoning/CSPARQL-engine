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
package eu.larkc.csparql.sparql.jena.data_source;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.DatasetFactory;
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
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateRequest;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.data_source.Datasource;

public class JenaDatasource implements Datasource{

	private Dataset dataSource = DatasetFactory.createMem() ;

	@Override
	public void putNamedModel(String namedModelURI, List<RDFTuple> modelContent) {
		Model m = ModelFactory.createDefaultModel();

		for(RDFTuple t : modelContent){
			addStatement(t.get(0), t.get(1), t.get(2), m);
		}

		dataSource.addNamedModel(namedModelURI, m);
		
	}

	@Override
	public List<RDFTuple> getNamedModel(String namedModelURI) {
		Model m = dataSource.getNamedModel(namedModelURI);
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
	public void execUpdateQuery(String queryBody){

		UpdateRequest updateRequest = UpdateFactory.create(queryBody, Syntax.syntaxSPARQL_11);
		UpdateAction.execute(updateRequest, dataSource);
	}
	
	@Override
	public boolean containsNamedModel(String namedModelURI) {
		if(dataSource.containsNamedModel(namedModelURI))
			return true;
		return false;
	}
	
	@Override
	public RDFTable evaluateGeneralQuery(String queryBody) {
		// TODO this code is taken from JenaEngine class, some refactoring is required
		Query query = QueryFactory.create(queryBody, Syntax.syntaxSPARQL_11);
		QueryExecution qexec = QueryExecutionFactory.create(query, dataSource);
		RDFTable table = null;
		if (query.isAskType()){
			table = new RDFTable("Answer");
			table.setGraph(false);
			final RDFTuple tuple = new RDFTuple();
			tuple.addFields("" + qexec.execAsk());
			table.add(tuple);
		} else if (query.isConstructType() || query.isDescribeType()){
			Model m = null;
			if (query.isDescribeType())
				m = qexec.execDescribe();
			else
				m = qexec.execConstruct();

			table = new RDFTable("Subject", "Predicate", "Object");
			table.setGraph(true);

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
		} else {
			final ResultSet resultSet = qexec.execSelect();

			table = new RDFTable(resultSet.getResultVars());
			table.setGraph(false);

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
		return table;
	}
	
	@Override
	public void removeNamedModel(String namedModelURI) {
		dataSource.removeNamedModel(namedModelURI);
		
	}
	
	public Dataset getDatasource(){
		return dataSource;
	}

	private void addStatement(final String subject, final String predicate, final String object, Model model) {

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

		model.add(s);
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
}
