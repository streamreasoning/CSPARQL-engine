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
