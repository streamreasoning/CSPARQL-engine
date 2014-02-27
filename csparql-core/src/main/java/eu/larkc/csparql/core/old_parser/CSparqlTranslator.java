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
///*******************************************************************************
// * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
// * 
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * 
// *   http://www.apache.org/licenses/LICENSE-2.0
// * 
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// * 
// * Acknowledgements:
// * 
// * This work was partially supported by the European project LarKC (FP7-215535)
// ******************************************************************************/
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package eu.larkc.csparql.core.old_parser;
//
////Importazioni manuali aggiuntive
//import java.io.StringReader;
//import java.util.List;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.antlr.runtime.ANTLRStringStream;
//import org.antlr.runtime.RecognitionException;
//import org.antlr.runtime.debug.ParseTreeBuilder;
//import org.antlr.runtime.tree.ParseTree;
//
//import eu.larkc.csparql.core.new_parser.CsparqlParser;
//import eu.larkc.csparql.core.new_parser.ParseException;
//import eu.larkc.csparql.core.new_parser.utility_files.EplProducer;
//import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
//import eu.larkc.csparql.core.streams.formats.CSparqlQueryImpl;
//import eu.larkc.csparql.core.streams.formats.TranslationException;
//
///**
// * @author Marco Regaldo
// */
//public final class CSparqlTranslator extends Translator {
//
//   private static String language = "CSparql";
//
//   public CSparqlTranslator() {
//   }
//
//   private static TreeBox parse(String query) throws org.antlr.runtime.RecognitionException,
//         PostProcessingException {
//      if (CSparqlTranslator.language.contentEquals("CSparql")) {
//
//         // Preprocess, query decode '\\u' HEX HEX HEX HEX | '\\u' HEX HEX HEX HEX HEX
//         // HEX HEX HEX -> unicode equivalent
//         query = CSparqlTranslator.preprocessQuery(query);
//
//         // Lexer
//         final org.antlr.runtime.CharStream input = new ANTLRStringStream(query);
//         final CSparqlLexer lexer = new CSparqlLexer(input);
//         // Token
//         final org.antlr.runtime.CommonTokenStream tokens = new org.antlr.runtime.CommonTokenStream(
//               lexer);
//         // create a debug event listener that builds parse trees
//         final ParseTreeBuilder builder = new ParseTreeBuilder("queryWithReg");
//         // Parser
//         final CSparqlParser parser = new CSparqlParser(tokens, builder);
//
//         // *************** Eseguo parsing dalla regola di ingresso della grammatica
//         final CSparqlParser.queryWithReg_return result = parser.queryWithReg();
//
//         // MyTree t = (MyTree)result.getTree();// pull out the tree (AST) and cast it
//         final ParseTree debug = builder.getTree();
//
//         // ********** TREEBOX
//         final TreeBox tb = TreeBox.create(debug);
//         TreeBox.decorate(tb);
//
//         // Postprocess the tree for correctness
//         CSparqlTranslator.postProcessing(tb);
//
//         return tb;
//      } else {
//         // Preprocess, query decode '\\u' HEX HEX HEX HEX | '\\u' HEX HEX HEX HEX HEX
//         // HEX HEX HEX -> unicode equivalent
//         query = CSparqlTranslator.preprocessQuery(query);
//
//         // Lexer
//         final org.antlr.runtime.CharStream input = new ANTLRStringStream(query);
//         final SparqlLexer lexer = new SparqlLexer(input);
//
//         // Token
//         final org.antlr.runtime.CommonTokenStream tokens = new org.antlr.runtime.CommonTokenStream(
//               lexer);
//         // create a debug event listener that builds parse trees
//         final ParseTreeBuilder builder = new ParseTreeBuilder("query");
//         // Parser
//         final SparqlParser parser = new SparqlParser(tokens, builder);
//         // *************** Eseguo parsing dalla regola di ingresso della grammatica
//         final SparqlParser.query_return result = parser.query();
//         // MyTree t = (MyTree)result.getTree();// pull out the tree (AST) and cast it
//         final ParseTree debug = builder.getTree();
//         // ********** TREEBOX
//         final TreeBox tb = TreeBox.create(debug);
//         TreeBox.decorate(tb);
//
//         // Postprocess the tree for correctness
//         CSparqlTranslator.postProcessing(tb);
//
//         return tb;
//      }
//   }
//
//   /**
//    * Evaluate the query and change the unicode sequences according to CSPARQL specification
//    * into their character equivalent.
//    * 
//    * @param query
//    * @return the query with unicode sequences tranformed into their equivalent chars
//    */
//   private static String preprocessQuery(String query) {
//      // Match '\\u' HEX HEX HEX HEX - see
//      // http://www.w3.org/TR/rdf-sparql-query/#codepointEscape
//      final Pattern compiledRegex1 = Pattern.compile(
//            "\\\\u([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])", Pattern.MULTILINE);
//      // Match '\\U' HEX HEX HEX HEX HEX HEX HEX HEX -
//      // http://www.w3.org/TR/rdf-sparql-query/#codepointEscape
//      final Pattern compiledRegex2 = Pattern
//            .compile(
//                  "\\\\U([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])([\\da-fA-F])",
//                  Pattern.MULTILINE);
//
//      String unicode = new String();
//      StringBuffer queryOk = new StringBuffer();
//
//      // Matcher object that will search the subject string using compiledRegex1
//      Matcher regexMatcher = compiledRegex1.matcher(query);
//      while (regexMatcher.find()) {
//         // Mark the 4 numbers as Hex type for Integer.decode()
//         unicode = "0x" + regexMatcher.group(0).substring(2);
//         try {
//            final char[] c = Character.toChars(Integer.decode(unicode).intValue());
//            for (final char literal : c) {
//               unicode = "" + literal;
//            }
//            regexMatcher.appendReplacement(queryOk, unicode);
//         } catch (final NumberFormatException nfe) {
//            // The sequence is not a correct unicode, should never happen
//            nfe.toString();
//         }
//      }
//      regexMatcher.appendTail(queryOk);
//      // Temp result after first pattern matching
//      query = queryOk.toString();
//
//      // Matcher object that will search the subject string using compiledRegex2
//      queryOk = new StringBuffer();
//      regexMatcher = compiledRegex2.matcher(query);
//      while (regexMatcher.find()) {
//         // Mark the 8 numbers as Hex type for Integer.decode()
//         unicode = "0x" + regexMatcher.group(0).substring(2);
//         final char[] c = Character.toChars(Integer.decode(unicode).intValue());
//         for (final char literal : c) {
//            unicode = "" + literal;
//         }
//         regexMatcher.appendReplacement(queryOk, unicode);
//      }
//      regexMatcher.appendTail(queryOk);
//
//      // Final result
//      return queryOk.toString();
//   }
//
//   /**
//    * Run the method <code>treeCheck(TreeBox tbx)</code> of all the classes in the package
//    * which implements <code>TreeCheckerInterface</code>. The classes are found through
//    * reflection.
//    * 
//    * @param tbx
//    * @throws Exception
//    *            , in case <code>tbx</code> don't pass at least one of the checks.
//    */
//   private static void postProcessing(final TreeBox tbx) throws PostProcessingException {
//      // Post Validate the tree with all classes which implements TreeCheckerInterface
//      final List<Class> classList = ClassFinder.find(CSparqlTranslator.class.getPackage()
//            .getName(), "TreeCheckerInterface");
//      for (final Class c : classList) {
//         try {
//            // Make an instance of the class
//            final TreeCheckerInterface i = (TreeCheckerInterface) c.newInstance();
//            // Call his method
//            i.treeCheck(tbx);
//         } catch (final InstantiationException ie) {
//            ie.toString();
//         } catch (final IllegalAccessException iae) {
//            iae.toString();
//         }
//      }
//   }
//
//   @Override
//   public CSparqlQuery translate(String queryCommand) throws ParseException, TranslationException{
//	   
//	   CsparqlParser parser = new CsparqlParser(new StringReader(queryCommand));
//	   parser.Query();
//	   
//	   EplProducer ep = new EplProducer(this.getEngine(), parser.getStreams());
//	   Set<String> epls = ep.produceEpl();
//	   CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), parser.getSparqlQuery(), queryCommand);
//	   return csq;
//
////      try {
////         final TreeBox tb = CSparqlTranslator.parse(queryCommand);
////         final EplProducer1_0 ep = new EplProducer1_0(this.getEngine());
////         final SparqlProducer1_0 sp = new SparqlProducer1_0();
////         final Set<String> epls = ep.produceEpl(tb);
////         final String spq = sp.produceSparql(tb);
////         CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), spq, queryCommand);
////         csq.setTreeBox(tb);
////         return csq;
////      } catch (final RecognitionException e) {
////         throw new TranslationException("Incorrect Syntax near \"" + e.token.getText()
////               + "\"");
////      } catch (final PostProcessingException e) {
////         throw new TranslationException(e.getMessage());
////      }
//   }
//   
////   @Override
////   public CSparqlQuery translate(final String queryCommand) throws TranslationException {
////
////      try {
////         final TreeBox tb = CSparqlTranslator.parse(queryCommand);
////         final EplProducer1_0 ep = new EplProducer1_0(this.getEngine());
////         final SparqlProducer1_0 sp = new SparqlProducer1_0();
////         final Set<String> epls = ep.produceEpl(tb);
////         final String spq = sp.produceSparql(tb);
////         CSparqlQuery csq = new CSparqlQueryImpl(epls.toArray()[0].toString(), spq, queryCommand);
////         csq.setTreeBox(tb);
////         return csq;
////      } catch (final RecognitionException e) {
////         throw new TranslationException("Incorrect Syntax near \"" + e.token.getText()
////               + "\"");
////      } catch (final PostProcessingException e) {
////         throw new TranslationException(e.getMessage());
////      }
////   }
//}
