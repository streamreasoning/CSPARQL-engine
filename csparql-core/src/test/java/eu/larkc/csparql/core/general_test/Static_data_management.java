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
//import org.apache.jena.query.Query;
//import org.apache.jena.query.QueryExecution;
//import org.apache.jena.query.QueryExecutionFactory;
//import org.apache.jena.query.QueryFactory;
//import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;
//import org.apache.jena.query.Syntax;
//
//import eu.larkc.csparql.cep.api.RdfStream;
//import eu.larkc.csparql.core.engine.CsparqlEngine;
//import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
//import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;
//
//public class Static_data_management {
//
//	public static void main(String[] args) {
//
//		CsparqlEngine engine = new CsparqlEngineImpl();
//		engine.initialize();
//
//		RdfStream stream = new Static_Knowledge_Test_Streamer("http://myexample.org/stream");
//
//		String query = "REGISTER QUERY test AS " +
//				"PREFIX ex:<http://streamreasoning.org#> " +
//				"SELECT * " +
//				"FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 5s] "	+ 
//				"FROM <http://127.0.0.1/~baldo/StaticKnowledgeTest.rdf> " +
//				"WHERE { " +
//				"?w1 ex:isIn ?r1 . " +
//				"?w2 ex:isIn ?r2 . " +
//				"?r1 ex:contiguous ?r2 . " +
//				"FILTER(?w1 != ?w2 && ?r1 != ?r2) " +
//				"}";
//
//		//		String query = "REGISTER QUERY test AS " +
//		//				"PREFIX ex:<http://streamreasoning.org#> " +
//		//				"SELECT ?s ?p ?o  " +
//		//				"FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 5s] "	+ 
//		//				"WHERE { " +
//		//				"?s ?p ?o . " +
//		//				"}";
//
//		engine.registerStream(stream);
//		CsparqlQueryResultProxy c1 = null;
//
//		try {
//			c1 = engine.registerQuery(query);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		if (c1 != null) {
//			c1.addObserver(new ConsoleFormatter());
//		}
//
//		new Thread((Runnable) stream).start();
//
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		String updateQuery = "PREFIX sr:<http://streamreasoning.org#> INSERT DATA { GRAPH <http://127.0.0.1/~baldo/StaticKnowledgeTest.rdf> { sr:r3  sr:contiguous  sr:r4 } }";
//		engine.execUpdateQueryOverDatasource(updateQuery);
//
//		//		String query = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
//		//		
//		//		Query q = QueryFactory.create(query, Syntax.syntaxSPARQL_11);
//		//		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:3030/ds/query", q);
//		//		
//		//		ResultSet rs = qexec.execSelect();
//		//		
//		//		System.out.println(ResultSetFormatter.asText(rs));
//
//	}
//
//}
