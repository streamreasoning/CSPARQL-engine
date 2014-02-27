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
