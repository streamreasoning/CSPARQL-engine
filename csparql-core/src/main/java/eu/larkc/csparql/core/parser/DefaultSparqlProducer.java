/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * @author Marco
 */
public abstract class DefaultSparqlProducer implements SparqlProducer {

   public static String separator = " ";

   /**
    * Default implementation. var = ?var , blankNode = _:blankNode , iriRef = \u003c iriRef
    * \u003E
    * 
    * @param tbx
    * @return getText() with the addition of the node's identification simbobols
    */
   public String getTextWithSymbols(final TreeBox tbx) {
      final StringBuffer text = new StringBuffer();
      if (tbx.getParent() != null) {
         if (tbx.getParent().getText().equals("var")) {
            text.append("?");
            text.append(tbx.getText());
         }
         if (tbx.getParent().getText().equals("blankNode")) {
            if (tbx.getText().equals("[")) {
               text.append(tbx.getText());
            } else if (tbx.getText().equals("]")) {
               text.append(tbx.getText());
            } else { // It's a normal blank node
               text.append("_:");
               text.append(tbx.getText());
            }
         }
         if (tbx.getParent().getText().equals("iriRef")) {
            text.append("<");
            text.append(tbx.getText());
            text.append(">");
         }
         // Check if it's the iri of a prefix
         if (tbx.getParent().getText().equals("prefixDecl") && tbx.getChildIndex() == 2) {
            text.append("<");
            text.append(tbx.getText());
            text.append(">");
         }
      }
      if (text.length() == 0) {
         text.append(tbx.getText());
      }

      // Add the default separator
      text.append(DefaultSparqlProducer.separator);

      return text.toString();
   }
}
