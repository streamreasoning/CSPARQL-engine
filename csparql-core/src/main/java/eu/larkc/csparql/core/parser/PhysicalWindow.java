/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;


/**
 * It identify the phisical window with its number of triple as range.
 * 
 * @author Marco
 */
public class PhysicalWindow extends Window {

   public PhysicalWindow(final int windowRange) {
      this.windowRange = windowRange;
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer();
      sb.append("Phisical window, number of triples = ");
      sb.append(this.windowRange);
      sb.append("\n");
      return sb.toString();
   }

}
