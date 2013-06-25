/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * @author Marco
 */
public interface SparqlProducer {

   String produceSparql(String csparqlQuery);

   String produceSparql(TreeBox csparqlTree);

   /**
    * If the node is a var / blank / itiRef, return the getText() with the addition of the
    * node's identification simbobols
    * 
    * @param tbx
    * @return
    */
   String getTextWithSymbols(TreeBox tbx);
}
