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
