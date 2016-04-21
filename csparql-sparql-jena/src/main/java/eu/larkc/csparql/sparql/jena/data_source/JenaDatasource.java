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
package eu.larkc.csparql.sparql.jena.data_source;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.TypeMapper;
import org.apache.jena.query.*;
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
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

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
