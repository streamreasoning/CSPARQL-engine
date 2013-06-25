/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * Class which represent a stream
 * 
 * @author Marco
 */
public class StreamInfo {

   private final String iri;
   private final Window window;

   /**
    * Default constructor, window can be an instance of a Phisical or Logical window.
    * 
    * @param iri
    * @param window
    */
   public StreamInfo(final String iri, final Window window) {
      this.iri = iri;
      this.window = window;
   }

   /**
    * @return the iri
    */
   public String getIri() {
      return this.iri;
   }

   /**
    * The window can be an instance of Physical or Logical Window.
    * 
    * @return
    */
   public Window getWindow() {
      return this.window;
   }

   /**
    * Check if the stream has a phisical window which imply a numberOfTriples != 0 and freq=0
    * , step = 0.
    * 
    * @return
    */
   public boolean hasPhisicalWindow() {
      return this.window instanceof PhysicalWindow;
   }

   @Override
   /**
    * Add a \n at the end
    */
   public String toString() {
      final StringBuffer sb = new StringBuffer();
      sb.append("Iri=");
      sb.append(this.getIri() + "\n");
      sb.append(this.window.toString());

      return sb.toString();
   }

}
