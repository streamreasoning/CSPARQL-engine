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
package eu.larkc.csparql.core.old_parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class check the consistency of the tree in respect to the Sparql rule: <em>can'parent be present two blank nodes with the same label in the same query</em>.
 * 
 * @author Marco
 */
public class DoubleBlankNodeChecker implements TreeCheckerInterface {

   HashMap map = new HashMap();
   HashMap triplesBlockMap = new HashMap();

   List<TreeBox> blankNodes = new ArrayList<TreeBox>();

   /**
    * It's the implemented method that check the specific class rule against the tree.
    * 
    * @param t
    *           The node to be checked. It's the superclass of TreeBox / ParseTree
    * @throws Exception
    *            If the tree doesn'parent pass the rule check, an exception with reason text
    *            is thrown.
    */
   public void treeCheck(final TreeBox t) throws PostProcessingException {
      /*
       * System.out.println(parent.toStringTree()); setTriplesBlockTreeRoot(parent); if
       * (triplesBlockTreeRoot == null) return; //Fase 1 -> creazione albero
       * groupGraphPattern -> triplesBlock -> BlankNodes -> etichette //Fase 2 -> visita
       * albero e per ogni nodo blankNode fare la ricerca se ne ?? presente un altro con
       * stessa label ma appartenente a triplesBlock diverso //Problemi : creare etichetta
       * multipla da padre: posso fare una lista di etichette incrementale ad ogni nodo
       * aggiungendo quella dei padri. //Problema 2: Come creare albero minimale. //Call
       * recursive visit function checkBlankNodeOfTripleBlock(parent);
       */
      final String nodeLabel = t.getText();

      // Check if the current node is the label of a blankNode type
      if (t.getParent() != null && t.getParent().getText().equals("blankNode")) { // root
         // node
         // have no
         // parent!
         TreeBox youngerBlock = this.getYoungerParentBlock(t, "groupGraphPattern");
         final TreeBox olderBlock = this.getOlderParentBlock(t, "triplesBlock");
         // If the blank node is in a construct statement it has no groupGraphPattern parent
         if (youngerBlock == null) {
            youngerBlock = this.getYoungerParentBlock(t, "constructTemplate");
         }
         // Check if the label has already being mapped check if it's in the same
         // youngerBlock
         if (this.map.containsKey(t.getText())) {
            // Check if belogs to a different GroupGraphPattern
            if (!this.map.get(t.getText()).equals(youngerBlock.getLabel().getId())) {
               throw new PostProcessingException(
                     "2 Blank nodes with same label in different GroupGraphPattern");
            }

            // If it's in the same GroupGraphPattern, check if they are in the same scope

            if (this.triplesBlockMap.containsKey(t.getText())) {
               // get the mapping already present
               final int lower = (Integer) this.triplesBlockMap.get(t.getText());
               for (int i = lower; i < olderBlock.getLabel().getChildIndex(); i++) {
                  // Pass all children in beetween and stop if a breaking node is found
                  if (youngerBlock.getChild(i).getText().equals("graphPatternNotTriples")) {
                     throw new PostProcessingException(
                           "2 Blank nodes with same label in different GroupGraphPattern");
                  }
               }
            } else {
               // add the mapping blankNode - olderTreeBox
               this.triplesBlockMap.put(t.getText(), olderBlock.getLabel().getChildIndex());
            }
         } else {
            // add the new key
            this.map.put(t.getText(), youngerBlock.getLabel().getId());
            // Add mapping node - olderTriplesBlock excluding the construct statement
            if (olderBlock != null) {
               this.triplesBlockMap.put(t.getText(), olderBlock.getLabel().getChildIndex());
            }
         }
      }

      // Call recursively the function for every child node
      for (int i = 0; i < t.getChildCount(); i++) {
         this.treeCheck(t.getChild(i));
      }
   }

   /**
    * Fuction that search for the most old parent node with text nodeName of the tree tbx.
    * 
    * @param tbx
    * @param nodeName
    * @return the nearest parent node with text nodeName or null if it's not found
    */
   private TreeBox getYoungerParentBlock(final TreeBox tbx, final String nodeName) {
      TreeBox parent = tbx.getParent();

      while (parent != null) { // triplesBlock
         if (parent.getText().equals(nodeName)) {
            return parent;
         }
         parent = parent.getParent();
      }
      return parent;
   }

   private TreeBox getOlderParentBlock(final TreeBox tbx, final String nodeName) {
      TreeBox parent = tbx.getParent();
      TreeBox result = null;

      while (parent != null) { // triplesBlock
         if (parent.getText().equals(nodeName)) {
            // System.out.println("Found!"+parent.getLabel().getId());
            result = parent;
         }
         parent = parent.getParent();
      }
      return result;
   }

}
