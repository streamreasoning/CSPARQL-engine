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

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that allow to check if all the prefix used are declared and if there are not prefix
 * with same name. Prefix with same iri but different names are allowed as well as prefix not
 * used.
 * 
 * @author Marco
 */
public class PrefixChecker implements TreeCheckerInterface {

   /**
    * Check if all the prefix used in the query are declared and if there are not prefix with
    * same name.
    * 
    * @param t
    * @throws PostProcessingException
    */
   public void treeCheck(final TreeBox t) throws PostProcessingException {
      // Get all the prefix
      final List<TreeBox> prefixList = t.getNodesByText("prefixDecl");
      // Map use id as key and iri as value
      final HashMap prefixMap = new HashMap();
      // Build up the map and check if the same id is declared twice
      for (final TreeBox tbx : prefixList) {
         if (prefixMap.containsKey(tbx.getChild(1).getText())) {
            // There is yet a prefix with this name
            throw new PostProcessingException("Prefix " + tbx.getChild(1).getText()
                  + "already declared twice in the query");
         } else {
            // Add the prefix id as key and iri as value
            prefixMap.put(tbx.getChild(1).getText(), tbx.getChild(2).getText());
         }
      }

      // Now check everytime a prefix is used in the query if it's present in the prefixMap
      final List<TreeBox> prefixedNames = t.getNodesByText("prefixedName");

      for (final TreeBox tbx : prefixedNames) {
         final Pattern pattern = Pattern.compile(".*:");
         final Matcher matcher = pattern.matcher(tbx.getChild(0).getText());
         if (matcher.find()) {
            // Check if the prefix has been declared before
            if (!prefixMap.containsKey(matcher.group())) {
               // Prefix used without declaration
               throw new PostProcessingException("Prefix  used without declaration");
            }
         }
      }
   }

}
