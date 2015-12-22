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

import java.util.List;

/**
 * The class allow to obtain a Sparql executable query from a Tree representing a CSparql
 * v1.0 query.
 * 
 * @author Marco
 */
public class SparqlProducer1_0 extends DefaultSparqlProducer {

   /**
    * METHOD NOT IMPLEMENTE YET!!! Method that parse csparqlQuery and from the resulting tree
    * call produceSparql().
    * 
    * @param csparqlQuery
    * @return
    */
   public String produceSparql(final String csparqlQuery) {
      // ******** TO BE IMPLEMENTED
      final String s = new String("Method not implemented yet!");
      return s;
   }

   /**
    * Create a string that represent an executable Sparql query from <code>t</code> which
    * represent a CSparql query. The param t is not modified by the method.
    * 
    * @param t
    * @return null if the query is not CSparql
    */
   public String produceSparql(final TreeBox t) {
      if (t.isCSparql()) {
         // Create a copy of the tree
         final TreeBox tbx = TreeBox.dupFullTreeDecorated(t);

         List<TreeBox> searchedNodes;
         TreeBox nodeToRemove;

         // Delete registration node
         searchedNodes = tbx.getNodesByText("registration");
         nodeToRemove = searchedNodes.get(0);
         searchedNodes.get(0).getParent().deleteChild(
               nodeToRemove.getLabel().getChildIndex());

         // Cut stream|query and range
         searchedNodes = tbx.getNodesByText("datasetClauseStream");
         

         
         for (TreeBox tb : searchedNodes) {
        	 TreeBox father = tb.getParent();
        	 if (father != null) {
                 int n = 0;
                 while (n < father.getChildCount()) {
                    if (father.getChild(n).getText().equals("datasetClauseStream")) {
                       father.deleteChild(n); // Index refresh on delete so don't increment n
                       
//                       System.out.println("FROM STREAM deleted");
                    } else {
                       n++;
                    }
                 }
         }
         
         
          
         
//             TreeBox father = null;
//         if (!searchedNodes.isEmpty()) {
//        	
//            father = searchedNodes.get(0).getParent();
////            StringBuffer buf = new StringBuffer();
////            father.printLeaves(buf);
////            System.out.println(buf);
//         }
//         if (father != null) {
//            int n = 0;
//            while (n < father.getChildCount()) {
//               if (father.getChild(n).getText().equals("datasetClauseStream")) {
//                  father.deleteChild(n); // Index refresh on delete so don't increment n
//                  
////                  System.out.println("FROM STREAM deleted");
//               } else {
//                  n++;
//               }
//            }
         }

         final List<TreeBox> leaves = tbx.getLeaves();
         // Leggo lista e creo la stringa aggiungendo i caratteri di convenzione per
         // var/blankNodes/iriRef
         final StringBuffer sb = new StringBuffer();
         for (final TreeBox tempNode : leaves) {
            sb.append(this.getTextWithSymbols(tempNode));
         }
//         System.out.println("****"+sb+"****");
         // restituisco stringa
         return sb.toString();
      } else {

         System.out.println("****"+t.toInputString()+"****");
         return t.toInputString();
      }
   }

}
