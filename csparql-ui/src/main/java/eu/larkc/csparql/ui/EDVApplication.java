package eu.larkc.csparql.ui;

/*
 * @(#)Application.java   1.0   Oct 02, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: Application.java 243 2010-05-13 14:26:54Z dbarbieri $
 */

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
     	.registerQuery("REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 100] WHERE { ?S ?P ?O . ?S ?P ?Y . FILTER (?O = ?Y) }");
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