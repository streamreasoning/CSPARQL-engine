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
