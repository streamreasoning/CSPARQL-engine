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
import java.util.Properties;

import eu.larkc.csparql.cep.api.RDFStreamAggregationTestGenerator;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.cep.api.TestGenerator;
import eu.larkc.csparql.cep.api.TestGeneratorFromFile;
import eu.larkc.csparql.common.config.Config;
import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;

public final class ApplicationExternalTimeStamp {

	/**
	 * @param args
	 */

	public static void main(final String[] args) {

		initialConfig();
		// final String queryGetAll = "REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://www.glue.com/stream> [RANGE 5s STEP 1s] WHERE { ?S ?P ?O }";
		// query with servie clause
		final String queryGetAll =  "REGISTER QUERY PIPPO AS SELECT * FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 2s]  WHERE { ?S ?P ?O SERVICE <http://localhost:3030/test/sparql> {?S ?P2 ?O2}}";
		//final String queryGetAll = "REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 10] WHERE { ?S ?P ?O }";

		final String queryGetEverythingFromBothStream = "REGISTER QUERY PIPPO AS SELECT ?S ?P ?O FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 1] WHERE { ?S ?P ?O }";

		final String queryAnonymousNodes = "REGISTER QUERY PIPPO AS CONSTRUCT {                        [] <http://ex.org/by> ?s  ;  <http://ex.org/count> ?n . } FROM STREAM <http://www.larkc.eu/defaultRDFInputStream> [RANGE TRIPLES 10]                        WHERE {                                { SELECT ?s ?p (count(?o) as ?n)                                  WHERE { ?s ?p ?o }                                  GROUP BY ?s }                              }";

		final String queryNoCount = "REGISTER QUERY PIPPO AS " + "SELECT ?p " + " FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 1] "
				+ " FROM <http://dbpedia.org/resource/Castello_Sforzesco> " + "WHERE { ?s ?p ?o }";

		final String queryCount = "REGISTER QUERY PIPPO AS " + "SELECT ?t (count(?t) AS ?conto)"
				+ " FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 30] WHERE { ?s <http://rdfs.org/sioc/ns#topic> ?t } " + "GROUP BY ?t ";

		final String querySimpleCount = "REGISTER QUERY PIPPO AS " + "SELECT ?s (COUNT(?s) AS ?conto) FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] WHERE { ?s ?p ?o } GROUP BY ?s";

		final String queryGetKB = "REGISTER QUERY PIPPO AS " + "SELECT ?s ?p ?o FROM <http://rdfs.org/sioc/ns>\n FROM STREAM <http://www.glue.com/stream> [RANGE TRIPLES 1] WHERE { ?s ?p ?o }";

		final String queryGetAll2 = "REGISTER QUERY PIPPO AS " + "CONSTRUCT { <http://www.streams.org/s> <http://www.streams.org/s> ?n }"
				+ " FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 2] " + " WHERE {" + "  { SELECT (count(?o) as ?n) " + "  { ?s ?p ?o }" + "   GROUP BY ?p } " + "} ";

		final CsparqlEngine engine = new CsparqlEngineImpl();
		engine.initialize();

		if (Config.INSTANCE.isEsperUsingExternalTimestamp()) {
			// example input file in src/main/resources/sample_input.txt
			TestGeneratorFromFile tg = new TestGeneratorFromFile("http://myexample.org/stream", args[0]);
			engine.registerStream(tg);
			CsparqlQueryResultProxy c1 = null;

			try {
				c1 = engine.registerQuery(queryGetAll, false);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c1.addObserver(new TextualFormatter());
			tg.read_trace();
		}

	}
	public static void initialConfig(){
		Properties prop = new Properties();
		prop.put("esper.externaltime.enabled", true);
		Config.INSTANCE.setConfigParams(prop);
	}
	private ApplicationExternalTimeStamp() {
		// hidden constructor
	}

}
