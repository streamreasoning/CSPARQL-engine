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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.new_parser.utility_files;

//Importazioni manuali aggiuntive
import java.util.Set;

import eu.larkc.csparql.core.new_parser.CsparqlParser;
import eu.larkc.csparql.core.new_parser.ParseException;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
import eu.larkc.csparql.core.streams.formats.CSparqlQueryImpl;
import eu.larkc.csparql.core.streams.formats.TranslationException;

/**
 * @author Marco Regaldo
 */
public final class CSparqlTranslator extends Translator {

	public CSparqlTranslator() {
	}

	@Override
	public CSparqlQuery translate(String queryCommand) throws ParseException, TranslationException{
		
		CsparqlParser parser = CsparqlParser.createAndParse(queryCommand);

		EplProducer ep = new EplProducer(this.getEngine(), parser.getStreams());
		Set<String> epls = ep.produceEpl();
		CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), parser.getSparqlQuery(), queryCommand, parser.getStreams());
		return csq;	   

		//REGISTER QUERY abc AS PREFIX ex:     <http://ex.org#> SELECT ?vm FROM STREAM <http://ex.org/stream> [RANGE 30s STEP 15s] WHERE { { SELECT ?vm (PERCENTILE(?resp_time, 0.95) AS ?perc) WHERE { ?vm <http://ex.org#response_time> ?resp_time } GROUP BY ?vm HAVING (?perc > 0) } }


		//      try {
		//         final TreeBox tb = CSparqlTranslator.parse(queryCommand);
		//         final EplProducer1_0 ep = new EplProducer1_0(this.getEngine());
		//         final SparqlProducer1_0 sp = new SparqlProducer1_0();
		//         final Set<String> epls = ep.produceEpl(tb);
		//         final String spq = sp.produceSparql(tb);
		//         CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), spq, queryCommand);
		//         csq.setTreeBox(tb);
		//         return csq;
		//      } catch (final RecognitionException e) {
		//         throw new TranslationException("Incorrect Syntax near \"" + e.token.getText()
		//               + "\"");
		//      } catch (final PostProcessingException e) {
		//         throw new TranslationException(e.getMessage());
		//      }
	}

	//   @Override
	//   public CSparqlQuery translate(final String queryCommand) throws TranslationException {
	//
	//      try {
	//         final TreeBox tb = CSparqlTranslator.parse(queryCommand);
	//         final EplProducer1_0 ep = new EplProducer1_0(this.getEngine());
	//         final SparqlProducer1_0 sp = new SparqlProducer1_0();
	//         final Set<String> epls = ep.produceEpl(tb);
	//         final String spq = sp.produceSparql(tb);
	//         CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), spq, queryCommand);
	//         csq.setTreeBox(tb);
	//         return csq;
	//      } catch (final RecognitionException e) {
	//         throw new TranslationException("Incorrect Syntax near \"" + e.token.getText()
	//               + "\"");
	//      } catch (final PostProcessingException e) {
	//         throw new TranslationException(e.getMessage());
	//      }
	//   }
}
