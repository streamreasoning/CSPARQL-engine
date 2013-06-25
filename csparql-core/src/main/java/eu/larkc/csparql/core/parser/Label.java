/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * Used to decorate the TreeBox
 * 
 * @author Marco
 */
public class Label {

   private int id;
   private int deepLevel;
   private int childIndex;

   Label() {
   }

   /**
    * Set the id with a deepLevel(from top to down)'.'childIndex(from left to right) notation
    * 
    * @param deepLevel
    * @param childIndex
    */
   Label(final int id, final int deepLevel, final int childIndex) {
      this.deepLevel = deepLevel;
      this.childIndex = childIndex;
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public int getDeepLevel() {
      return this.deepLevel;
   }

   public int getChildIndex() {
      return this.childIndex;
   }

   @Override
   public String toString() {
      return new String("ID:" + Integer.toString(this.id) + " deepLevel:"
            + Integer.toString(this.deepLevel) + " childIndex:"
            + Integer.toString(this.childIndex));
   }
}
