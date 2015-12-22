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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.old_parser;

import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.streams.formats.TranslationException;

/**
 * Implement EplProducer interface adding an utility method for converting the uri streams
 * names into EPL accettable names.
 * 
 * @author Marco
 */
public abstract class DefaultEplProducer implements EplProducer {

	CsparqlEngine engine;

	   public DefaultEplProducer(final CsparqlEngine engine) {
	      this.engine = engine;
	   }
   /**
    * Default method to convert a stream uri into an identification name correct for EPL.
    * 
    * @param uri
    * @return
    */
   public String convertStreamIri(final String uri) throws TranslationException {
      // final String patternStr = "([^a-zA-Z0-9])";
      // // Compile regular expression
      // final Pattern pattern = Pattern.compile(patternStr);
      // final Matcher matcher = pattern.matcher(uri);
      //
      // // Replace all occurrences of pattern in input
      // final StringBuffer buf = new StringBuffer();
      //
      // while (matcher.find()) {
      // // Set the replacement string
      // final String replaceStr = "_";
      //
      // // Insert replacement
      // matcher.appendReplacement(buf, replaceStr);
      // }
      // matcher.appendTail(buf);
      //
      // // Get result
      // final String result = buf.toString();
      //
      // return result;

      RdfStream s = this.engine.getStreamByIri(uri);
      if (s == null)
      {
    	  throw new TranslationException("IRI " + uri  + " not found.");
      }
      
      return s.uniqueName();
   }
}
