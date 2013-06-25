package eu.larkc.csparql.core.parser;

/**
 * The Interface describe the method needed to post validate a syntactic tree resulted from a
 * query parsing.
 * 
 * @author Marco
 */
public interface TreeCheckerInterface {

   /**
    * The method validate the tree according to a semantic rule of the language. If the tree
    * doesn't pass the check an exception will be thrown with the reason as text.
    * 
    * @param t
    *           Tree to be validated
    * @throws Exception
    *            Generic exception that contains the text which explain the reason of the
    *            fail check.
    */
   public void treeCheck(TreeBox t) throws PostProcessingException;
}
