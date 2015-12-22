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
package eu.larkc.csparql.ui;

import java.text.ParseException;

import eu.larkc.csparql.cep.api.TestGenerator;
import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;

public final class EDVApplication {

   /**
    * @param args
    */

   public static void main(final String[] args) {

	   //final String queryGetAll = "REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://www.glue.com/stream> [RANGE 5s STEP 1s] WHERE { ?S ?P ?O }";
	   final String queryGetAll = "REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] WHERE { ?S ?P ?O }";
	  
	   final String queryNoCount = "REGISTER QUERY PIPPO AS "
				+ "SELECT ?s ?t " + " FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 30] WHERE { ?s <http://rdfs.org/sioc/ns#topic> ?t }";

	  final String queryCount = "REGISTER QUERY PIPPO AS "
			+ "SELECT ?t (count(?t) AS ?conto)" + " FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 30] WHERE { ?s <http://rdfs.org/sioc/ns#topic> ?t } "
			+ "GROUP BY ?t ";
	  
	  final String queryCountLogicalWindow = "REGISTER QUERY PIPPO AS "
			+ "SELECT ?t (count(?t) AS ?conto)" + " FROM STREAM <http://www.glue.com/stream> [RANGE 1m STEP 1s] WHERE { ?s <http://rdfs.org/sioc/ns#topic> ?t } "
			+ "GROUP BY ?t ";
	  
	  final String querySimpleCount = "REGISTER QUERY PIPPO AS "
			+ "SELECT ?s (COUNT(?s) AS ?conto) FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] WHERE { ?s ?p ?o } GROUP BY ?s";
	  
	  final String queryGetKB = "REGISTER QUERY PIPPO AS "
			+ "SELECT ?s ?p ?o FROM <http://rdfs.org/sioc/ns>\n FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] WHERE { ?s ?p ?o }";
	  
      final CsparqlEngine engine = new CsparqlEngineImpl();
      engine.initialize();

      final TestGenerator tg = new TestGenerator("http://myexample.org/stream");
//      final GlueStreamGenerator tg = new GlueStreamGenerator();
      
      engine.registerStream(tg);
      //engine.registerStream(tg2);
      final Thread t = new Thread(tg);
      t.start();
      //final Thread t2 = new Thread(tg2);
      //t2.start();
      
      CsparqlQueryResultProxy c1 = null;
      final CsparqlQueryResultProxy c2 = null;

      try {
         c1 = engine
     	.registerQuery("REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 100] WHERE { ?S ?P ?O . ?S ?P ?Y . FILTER (?O = ?Y) }", false);
//        .registerQuery("REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 1000] WHERE { ?S ?P ?O }");
//         .registerQuery(queryCountLogicalWindow);
      } catch (final ParseException ex) {
         System.out.println("errore di parsing: " + ex.getMessage());
      }
      if (c1 != null) {
//         c1.addObserver(new TextualFormatter());
         c1.addObserver(new CounterFormatter());
      }
   }

   private EDVApplication() {
      // hidden constructor
   }

}
