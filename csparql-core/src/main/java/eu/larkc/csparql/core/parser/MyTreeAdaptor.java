/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 *
 * @author Marco
 */
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class MyTreeAdaptor extends CommonTreeAdaptor {

   @Override
   public Object create(final Token payload) {
      return new MyTree(payload);
   }
}
