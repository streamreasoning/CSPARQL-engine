/*******************************************************************************
 * Copyright 2013 DEIB - Politecnico di Milano
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
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.larkc.csparql.core.parser;

import java.util.Set;
import java.util.TreeSet;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

/**
 * @author Marco
 */
public class MyTree extends CommonTree {

   private String id;
   private int position;

   public MyTree(final Token t) {
      super(t);

   }

   public Set getVar() {
      if (this == null) {
         return null;
      }
      final Set s = new TreeSet(); // Set ordinato alfabeticamente
      MyTree temp;
      final SparqlParser p = null;

      // check in itself
      if (SparqlParser.tokenNames[this.getType()].contentEquals("VAR1")
            || SparqlParser.tokenNames[this.getType()].contentEquals("VAR2")) {
         s.add(this);
      }

      // Check in childrens
      for (int i = 0; i < this.getChildCount(); i++) {
         temp = (MyTree) this.getChild(i);
         if (SparqlParser.tokenNames[temp.getType()].contentEquals("VAR1")
               || SparqlParser.tokenNames[temp.getType()].contentEquals("VAR2")) {
            s.add(temp);
         }
      }
      return s;
   }

}
