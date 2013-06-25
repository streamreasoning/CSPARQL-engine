/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

import java.util.Set;

import eu.larkc.csparql.core.streams.formats.TranslationException;

/**
 * Allow to obtain an EPL query for each stream of a Csparql query.
 * 
 * @author Marco
 */
public interface EplProducer {

   Set<String> produceEpl(TreeBox csparqlTree) throws TranslationException;

   String convertStreamIri(String streamIri) throws TranslationException;

}
