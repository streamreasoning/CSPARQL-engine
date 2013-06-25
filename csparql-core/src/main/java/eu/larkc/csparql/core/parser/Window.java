/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * Generalization of a stream window.
 * 
 * @author Marco
 */
public abstract class Window {

   protected int windowRange;

   public int getWindowRange() {
      return this.windowRange;
   }
}
