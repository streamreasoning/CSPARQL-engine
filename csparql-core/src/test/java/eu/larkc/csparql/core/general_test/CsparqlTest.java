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
///*******************************************************************************
// * Copyright 2014 DEIB -Politecnico di Milano
// *   
// *  Marco Balduini (marco.balduini@polimi.it)
// *  Emanuele Della Valle (emanuele.dellavalle@polimi.it)
// *  Davide Barbieri
// *   
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *   
// *  	http://www.apache.org/licenses/LICENSE-2.0
// *  
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// *   
// *  Acknowledgements:
// *  
// *  This work was partially supported by the European project LarKC (FP7-215535)
// ******************************************************************************/
//package eu.larkc.csparql.core.general_test;
//
//import java.text.ParseException;
//
//import org.apache.jena.rdf.model.Model;
//import org.apache.jena.rdf.model.ModelFactory;
//import org.apache.jena.rdf.model.Statement;
//import org.apache.jena.rdf.model.StmtIterator;
//import org.apache.jena.util.FileManager;
//
//import eu.larkc.csparql.cep.api.RdfQuadruple;
//import eu.larkc.csparql.cep.api.RdfStream;
//import eu.larkc.csparql.core.engine.ConsoleFormatter;
//import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
//import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;
//
//public class CsparqlTest {
//
//	public static void main(String[] args) {
//		
//		CsparqlEngineImpl engine = new CsparqlEngineImpl();
//		
//		engine.initialize();
//		
//		RdfStream stream = new RdfStream("http://ex.org/stream");
//		
//		engine.registerStream(stream);
//		
////		String queryString = "REGISTER QUERY abc AS "
////				+ "PREFIX ex:<http://ex.org> "
////				+ "SELECT ?s ?p ?o "
////				+ "FROM STREAM <http://ex.org/stream> [RANGE 30s STEP 10s] "
////				+ "WHERE { ?s ?p ?o }";
//
//		String queryString = "REGISTER STREAM abc AS "
//				+ "PREFIX ex:     <http://ex.org#> "
//				+ "CONSTRUCT { ?vm ex:violated ex:percentilerule . } "
//				+ "FROM STREAM <http://ex.org/stream> [RANGE 30s STEP 5s] "
//				+ "WHERE { "
//				+ "{ "
//				+ "SELECT ?vm (PERCENTILE(?resp_time, 0.95) AS ?perc) "
//				+ "WHERE {      ?vm <http://ex.org#response_time> ?resp_time } "
//				+ "GROUP BY ?vm "
//				+ "HAVING (?perc > 100) "
//				+ "}}";
//		
//		CsparqlQueryResultProxy c = null;
//		
//		try {
//			c = engine.registerQuery(queryString);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		c.addObserver(new ConsoleFormatter());
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String rdf_test_path = "/Users/baldo/Desktop/test.rdf";
//		Model model = ModelFactory.createDefaultModel();
//
//		model.read(FileManager.get().open(rdf_test_path),null);
//		
//		feedStream(stream, model);
//		
//		try {
//			Thread.sleep(200000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
////		Collection<CSparqlQuery> queries = engine.getAllQueries();
////		
////		for(CSparqlQuery csp : queries){
////			System.out.println(csp.getQueryCommand());
////			System.out.println(csp.getSparqlQuery().getQueryCommand());
////			System.out.println(csp.getCepQuery().getQueryCommand());
////			System.out.println(csp.getStreams());
////
////
////		}
//		
////		String queryString = "REGISTER QUERY abc AS "
////				+ "PREFIX ex:<http://ex.org> "
////				+ "SELECT ?s ?p ?o "
////				+ "FROM STREAM <http://ex.org/stream> [RANGE 30s STEP 5s] "
////				+ "WHERE { ?s ?p ?o }";
////		
////		CsparqlParser parser = new CsparqlParser(new StringReader(queryString));
////		try {
////			parser.Query();
////		} catch (eu.larkc.csparql.core.new_parser.ParseException e) {
////			e.printStackTrace();
////		}	
////		
////		System.out.println();
//		
//
//	}
//	
//	private static void feedStream(RdfStream stream, Model model){
//		
//		StmtIterator it = model.listStatements();
//		
//		while (it.hasNext()) {
//			Statement statement = (Statement) it.next();
//			stream.put(new RdfQuadruple(statement.getSubject().toString(), statement.getPredicate().toString(), statement.getObject().toString(), System.currentTimeMillis()));
//
//		}
//		
//
//	}
//
//}
