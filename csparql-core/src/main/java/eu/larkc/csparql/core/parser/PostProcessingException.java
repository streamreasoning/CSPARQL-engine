/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * Exception thrown when a method <code>treeCheck</code> of a class which implements
 * <code>TreeCheckerInterface</code> fails.
 * 
 * @author Marco
 */
public class PostProcessingException extends Exception {

   /**
    * Creates a new instance of <code>PostProcessingException</code> without detail message.
    */
   public PostProcessingException() {
   }

   /**
    * Constructs an instance of <code>PostProcessingException</code> with the specified
    * detail message.
    * 
    * @param msg
    *           the detail message.
    */
   public PostProcessingException(final String msg) {
      super(msg);
   }
}
