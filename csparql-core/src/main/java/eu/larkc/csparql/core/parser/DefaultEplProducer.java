/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

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
