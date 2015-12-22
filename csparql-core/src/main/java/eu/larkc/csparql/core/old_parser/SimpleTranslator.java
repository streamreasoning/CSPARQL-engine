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
// * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
// * 
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *   http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * 
// * Acknowledgements:
// * 
// * This work was partially supported by the European project LarKC (FP7-215535)
// ******************************************************************************/
//package eu.larkc.csparql.core.old_parser;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import eu.larkc.csparql.cep.api.RdfStream;
//import eu.larkc.csparql.core.engine.CsparqlEngine;
//import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
//import eu.larkc.csparql.core.streams.formats.CSparqlQueryImpl;
//import eu.larkc.csparql.core.streams.formats.TranslationException;
//
//public class SimpleTranslator extends Translator {
//
//   /*
//    * ESEMPIO DI QUERY CSPARQL SELECT DISTINCT ?district ?passages FROM STREAM
//    * <http://streams.org/citytollgates.trdf> [RANGE 30m STEP 5m] WHERE { ?tollgate
//    * t:registers ?car . ?tollgate c:placedIn ?street . ?district c:contains ?street . }
//    * ESEMPIO SPARQL SELECT ?givenName WHERE { ?y vcard:Family "Smith" . ?y vcard:Given
//    * ?givenName . }
//    */
//
//   // messo static per test
//   @Override
//   public CSparqlQuery translate(final String queryCommand) throws TranslationException {
//      // queryCommand ??? una query CSparql
//
//      // final String cepQuery =
//      // "select subject, predicate, object, timestamp from RdfQuadruple.win:length(1000) output snapshot every 2 seconds";
//      // final String sparqlQuery = "SELECT ?S ?P ?O WHERE { ?S ?P ?O }";
//
//      String selectPart = "";
//      String wherePart = "";
//      String size = "";
//      String win = "";
//      String freq = "";
//      String rate = "";
//      final String iri = "http://myexample.org/stream";
//
//      // CHECK SELECT
//      Pattern p = Pattern.compile("SELECT (.*)FROM");
//      Matcher m = p.matcher(queryCommand);
//      if (m.find()) {
//         selectPart = m.group(1);
//      }
//
//      // CHECK FROM
//      p = Pattern.compile("\\[RANGE (.*)STEP (.*)\\]");
//      m.reset();
//      m = p.matcher(queryCommand);
//      if (m.find()) {
//         size = m.group(1);
//         freq = m.group(2);
//         p = Pattern.compile("^\\d* $");
//         m.reset();
//         m = p.matcher(size);
//         if (m.find()) {
//            // Finestra in eventi
//            win = "win:length(" + size.trim() + ")";
//            rate = freq + " events";
//         } else {
//            win = "win:time(" + size.trim() + ")";
//         }
//      }
//
//      // Check WHERE
//      p = Pattern.compile("WHERE \\{ (.*)\\}");
//      m.reset();
//      m = p.matcher(queryCommand);
//      if (m.find()) {
//         wherePart = m.group(1);
//      }
//
//      final CsparqlEngine engine = this.getEngine();
//
//      final RdfStream stream = engine.getStreamByIri(iri);
//
//      if (stream == null) {
//         throw new TranslationException("non c'e' lo stream " + iri);
//      }
//
//      // Costruisco le 2 query
//      final String cepQuery = "select subject, predicate, object, timestamp from "
//            + stream.uniqueName() + "." + win + " output snapshot every " + rate;
//      final String sparqlQuery = "SELECT " + selectPart + "WHERE{" + wherePart + "}";
//
//      // Output test
//      System.out.println("CEPQUERY: " + cepQuery);
//      System.out.println("SPARQL: " + sparqlQuery);
//
//      final CSparqlQueryImpl q = new CSparqlQueryImpl(cepQuery, sparqlQuery, queryCommand);
//
//      return q;
//   }
//}
