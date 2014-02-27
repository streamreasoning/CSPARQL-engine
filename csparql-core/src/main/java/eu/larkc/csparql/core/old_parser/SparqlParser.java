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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.old_parser;

/**
 *
 * @author Marco
 */
// $ANTLR 3.2 Sep 23, 2009 12:02:23 Sparql.g 2009-12-15 16:31:19

import java.io.IOException;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugEventSocketProxy;
import org.antlr.runtime.debug.DebugParser;
import org.antlr.runtime.debug.DebugTokenStream;
import org.antlr.runtime.debug.DebugTreeAdaptor;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class SparqlParser extends DebugParser {

   public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>",
         "<UP>", "BASE", "IRI_REF", "PREFIX", "PNAME_NS", "SELECT", "DISTINCT", "REDUCED",
         "ASTERISK", "CONSTRUCT", "DESCRIBE", "ASK", "FROM", "NAMED", "WHERE", "ORDER",
         "BY", "ASC", "DESC", "LIMIT", "INTEGER", "OFFSET", "OPEN_CURLY_BRACE", "DOT",
         "CLOSE_CURLY_BRACE", "OPTIONAL", "GRAPH", "UNION", "FILTER", "OPEN_BRACE",
         "CLOSE_BRACE", "COMMA", "SEMICOLON", "A", "OPEN_SQUARE_BRACE",
         "CLOSE_SQUARE_BRACE", "VAR1", "VAR2", "OR", "AND", "EQUAL", "NOT_EQUAL", "LESS",
         "GREATER", "LESS_EQUAL", "GREATER_EQUAL", "PLUS", "MINUS", "DIVIDE", "NOT", "STR",
         "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "SAMETERM", "ISIRI", "ISURI",
         "ISBLANK", "ISLITERAL", "REGEX", "LANGTAG", "REFERENCE", "DECIMAL", "DOUBLE",
         "INTEGER_POSITIVE", "DECIMAL_POSITIVE", "DOUBLE_POSITIVE", "INTEGER_NEGATIVE",
         "DECIMAL_NEGATIVE", "DOUBLE_NEGATIVE", "TRUE", "FALSE", "STRING_LITERAL1",
         "STRING_LITERAL2", "STRING_LITERAL_LONG1", "STRING_LITERAL_LONG2", "PNAME_LN",
         "BLANK_NODE_LABEL", "EOL", "WS", "PN_PREFIX", "PN_LOCAL", "VARNAME",
         "PN_CHARS_BASE", "DIGIT", "EXPONENT", "ECHAR", "PN_CHARS_U", "PN_CHARS", "COMMENT",
         "ANY" };
   public static final int PREFIX = 6;
   public static final int EXPONENT = 89;
   public static final int CLOSE_SQUARE_BRACE = 38;
   public static final int GRAPH = 29;
   public static final int REGEX = 63;
   public static final int PNAME_LN = 80;
   public static final int CONSTRUCT = 12;
   public static final int NOT = 52;
   public static final int EOF = -1;
   public static final int VARNAME = 86;
   public static final int ISLITERAL = 62;
   public static final int GREATER = 46;
   public static final int EOL = 82;
   public static final int NOT_EQUAL = 44;
   public static final int LESS = 45;
   public static final int LANGMATCHES = 55;
   public static final int DOUBLE = 67;
   public static final int BASE = 4;
   public static final int PN_CHARS_U = 91;
   public static final int COMMENT = 93;
   public static final int OPEN_CURLY_BRACE = 25;
   public static final int SELECT = 8;
   public static final int CLOSE_CURLY_BRACE = 27;
   public static final int DOUBLE_POSITIVE = 70;
   public static final int DIVIDE = 51;
   public static final int BOUND = 57;
   public static final int ISIRI = 59;
   public static final int A = 36;
   public static final int ASC = 20;
   public static final int ASK = 14;
   public static final int BLANK_NODE_LABEL = 81;
   public static final int SEMICOLON = 35;
   public static final int ISBLANK = 61;
   public static final int WS = 83;
   public static final int NAMED = 16;
   public static final int INTEGER_POSITIVE = 68;
   public static final int OR = 41;
   public static final int STRING_LITERAL2 = 77;
   public static final int FILTER = 31;
   public static final int DESCRIBE = 13;
   public static final int STRING_LITERAL1 = 76;
   public static final int PN_CHARS = 92;
   public static final int DATATYPE = 56;
   public static final int LESS_EQUAL = 47;
   public static final int DOUBLE_NEGATIVE = 73;
   public static final int FROM = 15;
   public static final int FALSE = 75;
   public static final int DISTINCT = 9;
   public static final int LANG = 54;
   public static final int IRI_REF = 5;
   public static final int WHERE = 17;
   public static final int ORDER = 18;
   public static final int LIMIT = 22;
   public static final int AND = 42;
   public static final int ASTERISK = 11;
   public static final int ISURI = 60;
   public static final int STR = 53;
   public static final int SAMETERM = 58;
   public static final int COMMA = 34;
   public static final int OFFSET = 24;
   public static final int EQUAL = 43;
   public static final int DECIMAL_POSITIVE = 69;
   public static final int PLUS = 49;
   public static final int DIGIT = 88;
   public static final int DOT = 26;
   public static final int INTEGER = 23;
   public static final int BY = 19;
   public static final int REDUCED = 10;
   public static final int INTEGER_NEGATIVE = 71;
   public static final int PN_LOCAL = 85;
   public static final int PNAME_NS = 7;
   public static final int REFERENCE = 65;
   public static final int CLOSE_BRACE = 33;
   public static final int MINUS = 50;
   public static final int TRUE = 74;
   public static final int OPEN_SQUARE_BRACE = 37;
   public static final int UNION = 30;
   public static final int ECHAR = 90;
   public static final int OPTIONAL = 28;
   public static final int ANY = 94;
   public static final int STRING_LITERAL_LONG2 = 79;
   public static final int PN_CHARS_BASE = 87;
   public static final int DECIMAL = 66;
   public static final int VAR1 = 39;
   public static final int VAR2 = 40;
   public static final int STRING_LITERAL_LONG1 = 78;
   public static final int DECIMAL_NEGATIVE = 72;
   public static final int PN_PREFIX = 84;
   public static final int DESC = 21;
   public static final int OPEN_BRACE = 32;
   public static final int GREATER_EQUAL = 48;
   public static final int LANGTAG = 64;

   // delegates
   // delegators

   public static final String[] ruleNames = new String[] { "invalidRule", "valueLogical",
         "blankNode", "additiveExpression", "booleanLiteral", "graphGraphPattern",
         "numericLiteral", "relationalExpression", "triplesNode", "limitClause", "var",
         "brackettedExpression", "verb", "graphTerm", "expression", "limitOffsetClauses",
         "numericLiteralPositive", "solutionModifier", "describeQuery", "argList",
         "baseDecl", "triplesBlock", "sourceSelector", "objectList", "whereClause",
         "groupOrUnionGraphPattern", "prologue", "orderCondition", "iriRefOrFunction",
         "askQuery", "collection", "propertyList", "prefixedName", "graphPatternNotTriples",
         "filter", "varOrIRIref", "conditionalAndExpression", "constructTriples",
         "numericLiteralNegative", "prefixDecl", "string", "varOrTerm", "unaryExpression",
         "object", "conditionalOrExpression", "primaryExpression", "blankNodePropertyList",
         "multiplicativeExpression", "groupGraphPattern", "orderClause", "constraint",
         "graphNode", "regexExpression", "functionCall", "triplesSameSubject",
         "builtInCall", "constructQuery", "datasetClause", "numericExpression",
         "optionalGraphPattern", "query", "selectQuery", "defaultGraphClause",
         "offsetClause", "numericLiteralUnsigned", "rdfLiteral", "iriRef",
         "namedGraphClause", "constructTemplate", "propertyListNotEmpty" };

   public int ruleLevel = 0;

   public int getRuleLevel() {
      return this.ruleLevel;
   }

   public void incRuleLevel() {
      this.ruleLevel++;
   }

   public void decRuleLevel() {
      this.ruleLevel--;
   }

   public SparqlParser(final TokenStream input) {
      this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
   }

   public SparqlParser(final TokenStream input, final int port,
         final RecognizerSharedState state) {
      super(input, state);
      final DebugEventSocketProxy proxy = new DebugEventSocketProxy(this, port, this.adaptor);
      this.setDebugListener(proxy);
      this.setTokenStream(new DebugTokenStream(input, proxy));
      try {
         proxy.handshake();
      } catch (final IOException ioe) {
         this.reportError(ioe);
      }
      final TreeAdaptor adap = new CommonTreeAdaptor();
      this.setTreeAdaptor(adap);
      proxy.setTreeAdaptor(adap);
   }

   public SparqlParser(final TokenStream input, final DebugEventListener dbg) {
      super(input, dbg);

      final TreeAdaptor adap = new CommonTreeAdaptor();
      this.setTreeAdaptor(adap);

   }

   protected boolean evalPredicate(final boolean result, final String predicate) {
      this.dbg.semanticPredicate(result, predicate);
      return result;
   }

   protected DebugTreeAdaptor adaptor;

   public void setTreeAdaptor(final TreeAdaptor adaptor) {
      this.adaptor = new DebugTreeAdaptor(this.dbg, adaptor);

   }

   public TreeAdaptor getTreeAdaptor() {
      return this.adaptor;
   }

   @Override
   public String[] getTokenNames() {
      return SparqlParser.tokenNames;
   }

   @Override
   public String getGrammarFileName() {
      return "Sparql.g";
   }

   protected void mismatch(final IntStream input, final int ttype, final BitSet follow)
         throws RecognitionException {
      throw new MismatchedTokenException(ttype, input);
   }

   @Override
   protected Object recoverFromMismatchedToken(final IntStream input, final int ttype,
         final BitSet follow) throws RecognitionException {
      // must throw the exception to notify junit
      throw new MismatchedTokenException(ttype, input);
   }

   @Override
   public Object recoverFromMismatchedSet(final IntStream input,
         final RecognitionException e, final BitSet follow) throws RecognitionException {
      throw e;
   }

   public static class query_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "query"
   // Sparql.g:80:1: query : prologue ( selectQuery | constructQuery | describeQuery |
   // askQuery ) EOF ;
   public final SparqlParser.query_return query() throws RecognitionException {
      final SparqlParser.query_return retval = new SparqlParser.query_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EOF6 = null;
      SparqlParser.prologue_return prologue1 = null;

      SparqlParser.selectQuery_return selectQuery2 = null;

      SparqlParser.constructQuery_return constructQuery3 = null;

      SparqlParser.describeQuery_return describeQuery4 = null;

      SparqlParser.askQuery_return askQuery5 = null;

      Object EOF6_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "query");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(80, 1);

         try {
            // Sparql.g:81:5: ( prologue ( selectQuery | constructQuery | describeQuery |
            // askQuery ) EOF )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(81, 7);
            this.pushFollow(SparqlParser.FOLLOW_prologue_in_query40);
            prologue1 = this.prologue();
            this.state._fsp--;
            this.adaptor.addChild(root_0, prologue1.getTree());
            this.dbg.location(81, 16);
            // Sparql.g:81:16: ( selectQuery | constructQuery | describeQuery | askQuery )
            int alt1 = 4;
            try {
               this.dbg.enterSubRule(1);
               try {
                  this.dbg.enterDecision(1);

                  switch (this.input.LA(1)) {
                     case SELECT: {
                        alt1 = 1;
                     }
                        break;
                     case CONSTRUCT: {
                        alt1 = 2;
                     }
                        break;
                     case DESCRIBE: {
                        alt1 = 3;
                     }
                        break;
                     case ASK: {
                        alt1 = 4;
                     }
                        break;
                     default:
                        final NoViableAltException nvae = new NoViableAltException("", 1, 0,
                              this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                  }

               } finally {
                  this.dbg.exitDecision(1);
               }

               switch (alt1) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:81:18: selectQuery
                     {
                        this.dbg.location(81, 18);
                        this.pushFollow(SparqlParser.FOLLOW_selectQuery_in_query44);
                        selectQuery2 = this.selectQuery();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, selectQuery2.getTree());

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:81:32: constructQuery
                     {
                        this.dbg.location(81, 32);
                        this.pushFollow(SparqlParser.FOLLOW_constructQuery_in_query48);
                        constructQuery3 = this.constructQuery();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, constructQuery3.getTree());

                     }
                     break;
                  case 3:
                     this.dbg.enterAlt(3);

                     // Sparql.g:81:49: describeQuery
                     {
                        this.dbg.location(81, 49);
                        this.pushFollow(SparqlParser.FOLLOW_describeQuery_in_query52);
                        describeQuery4 = this.describeQuery();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, describeQuery4.getTree());

                     }
                     break;
                  case 4:
                     this.dbg.enterAlt(4);

                     // Sparql.g:81:65: askQuery
                     {
                        this.dbg.location(81, 65);
                        this.pushFollow(SparqlParser.FOLLOW_askQuery_in_query56);
                        askQuery5 = this.askQuery();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, askQuery5.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(1);
            }
            this.dbg.location(81, 76);
            EOF6 = (Token) this.match(this.input, SparqlParser.EOF,
                  SparqlParser.FOLLOW_EOF_in_query60);
            EOF6_tree = this.adaptor.create(EOF6);
            this.adaptor.addChild(root_0, EOF6_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(82, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "query");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "query"

   public static class prologue_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prologue"
   // Sparql.g:84:1: prologue : ( baseDecl )? ( prefixDecl )* ;
   public final SparqlParser.prologue_return prologue() throws RecognitionException {
      final SparqlParser.prologue_return retval = new SparqlParser.prologue_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.baseDecl_return baseDecl7 = null;

      SparqlParser.prefixDecl_return prefixDecl8 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prologue");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(84, 1);

         try {
            // Sparql.g:85:5: ( ( baseDecl )? ( prefixDecl )* )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(85, 7);
            // Sparql.g:85:7: ( baseDecl )?
            int alt2 = 2;
            try {
               this.dbg.enterSubRule(2);
               try {
                  this.dbg.enterDecision(2);

                  final int LA2_0 = this.input.LA(1);

                  if (LA2_0 == SparqlParser.BASE) {
                     alt2 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(2);
               }

               switch (alt2) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:85:7: baseDecl
                     {
                        this.dbg.location(85, 7);
                        this.pushFollow(SparqlParser.FOLLOW_baseDecl_in_prologue77);
                        baseDecl7 = this.baseDecl();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, baseDecl7.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(2);
            }
            this.dbg.location(85, 17);
            // Sparql.g:85:17: ( prefixDecl )*
            try {
               this.dbg.enterSubRule(3);

               loop3: do {
                  int alt3 = 2;
                  try {
                     this.dbg.enterDecision(3);

                     final int LA3_0 = this.input.LA(1);

                     if (LA3_0 == SparqlParser.PREFIX) {
                        alt3 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(3);
                  }

                  switch (alt3) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:85:17: prefixDecl
                        {
                           this.dbg.location(85, 17);
                           this.pushFollow(SparqlParser.FOLLOW_prefixDecl_in_prologue80);
                           prefixDecl8 = this.prefixDecl();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, prefixDecl8.getTree());

                        }
                        break;

                     default:
                        break loop3;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(3);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(86, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "prologue");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "prologue"

   public static class baseDecl_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "baseDecl"
   // Sparql.g:88:1: baseDecl : BASE IRI_REF ;
   public final SparqlParser.baseDecl_return baseDecl() throws RecognitionException {
      final SparqlParser.baseDecl_return retval = new SparqlParser.baseDecl_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token BASE9 = null;
      Token IRI_REF10 = null;

      Object BASE9_tree = null;
      Object IRI_REF10_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "baseDecl");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(88, 1);

         try {
            // Sparql.g:89:5: ( BASE IRI_REF )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(89, 7);
            BASE9 = (Token) this.match(this.input, SparqlParser.BASE,
                  SparqlParser.FOLLOW_BASE_in_baseDecl98);
            BASE9_tree = this.adaptor.create(BASE9);
            this.adaptor.addChild(root_0, BASE9_tree);
            this.dbg.location(89, 12);
            IRI_REF10 = (Token) this.match(this.input, SparqlParser.IRI_REF,
                  SparqlParser.FOLLOW_IRI_REF_in_baseDecl100);
            IRI_REF10_tree = this.adaptor.create(IRI_REF10);
            this.adaptor.addChild(root_0, IRI_REF10_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(90, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "baseDecl");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "baseDecl"

   public static class prefixDecl_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prefixDecl"
   // Sparql.g:92:1: prefixDecl : PREFIX PNAME_NS IRI_REF ;
   public final SparqlParser.prefixDecl_return prefixDecl() throws RecognitionException {
      final SparqlParser.prefixDecl_return retval = new SparqlParser.prefixDecl_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token PREFIX11 = null;
      Token PNAME_NS12 = null;
      Token IRI_REF13 = null;

      Object PREFIX11_tree = null;
      Object PNAME_NS12_tree = null;
      Object IRI_REF13_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prefixDecl");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(92, 1);

         try {
            // Sparql.g:93:5: ( PREFIX PNAME_NS IRI_REF )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(93, 7);
            PREFIX11 = (Token) this.match(this.input, SparqlParser.PREFIX,
                  SparqlParser.FOLLOW_PREFIX_in_prefixDecl117);
            PREFIX11_tree = this.adaptor.create(PREFIX11);
            this.adaptor.addChild(root_0, PREFIX11_tree);
            this.dbg.location(93, 14);
            PNAME_NS12 = (Token) this.match(this.input, SparqlParser.PNAME_NS,
                  SparqlParser.FOLLOW_PNAME_NS_in_prefixDecl119);
            PNAME_NS12_tree = this.adaptor.create(PNAME_NS12);
            this.adaptor.addChild(root_0, PNAME_NS12_tree);
            this.dbg.location(93, 23);
            IRI_REF13 = (Token) this.match(this.input, SparqlParser.IRI_REF,
                  SparqlParser.FOLLOW_IRI_REF_in_prefixDecl121);
            IRI_REF13_tree = this.adaptor.create(IRI_REF13);
            this.adaptor.addChild(root_0, IRI_REF13_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(94, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "prefixDecl");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "prefixDecl"

   public static class selectQuery_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "selectQuery"
   // Sparql.g:96:1: selectQuery : SELECT ( DISTINCT | REDUCED )? ( ( var )+ | ASTERISK ) (
   // datasetClause )* whereClause solutionModifier ;
   public final SparqlParser.selectQuery_return selectQuery() throws RecognitionException {
      final SparqlParser.selectQuery_return retval = new SparqlParser.selectQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SELECT14 = null;
      Token set15 = null;
      Token ASTERISK17 = null;
      SparqlParser.var_return var16 = null;

      SparqlParser.datasetClause_return datasetClause18 = null;

      SparqlParser.whereClause_return whereClause19 = null;

      SparqlParser.solutionModifier_return solutionModifier20 = null;

      Object SELECT14_tree = null;
      final Object set15_tree = null;
      Object ASTERISK17_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "selectQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(96, 1);

         try {
            // Sparql.g:97:5: ( SELECT ( DISTINCT | REDUCED )? ( ( var )+ | ASTERISK ) (
            // datasetClause )* whereClause solutionModifier )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(97, 7);
            SELECT14 = (Token) this.match(this.input, SparqlParser.SELECT,
                  SparqlParser.FOLLOW_SELECT_in_selectQuery138);
            SELECT14_tree = this.adaptor.create(SELECT14);
            this.adaptor.addChild(root_0, SELECT14_tree);
            this.dbg.location(97, 14);
            // Sparql.g:97:14: ( DISTINCT | REDUCED )?
            int alt4 = 2;
            try {
               this.dbg.enterSubRule(4);
               try {
                  this.dbg.enterDecision(4);

                  final int LA4_0 = this.input.LA(1);

                  if (LA4_0 >= SparqlParser.DISTINCT && LA4_0 <= SparqlParser.REDUCED) {
                     alt4 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(4);
               }

               switch (alt4) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:
                     {
                        this.dbg.location(97, 14);
                        set15 = this.input.LT(1);
                        if (this.input.LA(1) >= SparqlParser.DISTINCT
                              && this.input.LA(1) <= SparqlParser.REDUCED) {
                           this.input.consume();
                           this.adaptor.addChild(root_0, this.adaptor.create(set15));
                           this.state.errorRecovery = false;
                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.dbg.recognitionException(mse);
                           throw mse;
                        }

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(4);
            }
            this.dbg.location(97, 38);
            // Sparql.g:97:38: ( ( var )+ | ASTERISK )
            int alt6 = 2;
            try {
               this.dbg.enterSubRule(6);
               try {
                  this.dbg.enterDecision(6);

                  final int LA6_0 = this.input.LA(1);

                  if (LA6_0 >= SparqlParser.VAR1 && LA6_0 <= SparqlParser.VAR2) {
                     alt6 = 1;
                  } else if (LA6_0 == SparqlParser.ASTERISK) {
                     alt6 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 6, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
                  }
               } finally {
                  this.dbg.exitDecision(6);
               }

               switch (alt6) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:97:40: ( var )+
                     {
                        this.dbg.location(97, 40);
                        // Sparql.g:97:40: ( var )+
                        int cnt5 = 0;
                        try {
                           this.dbg.enterSubRule(5);

                           loop5: do {
                              int alt5 = 2;
                              try {
                                 this.dbg.enterDecision(5);

                                 final int LA5_0 = this.input.LA(1);

                                 if (LA5_0 >= SparqlParser.VAR1
                                       && LA5_0 <= SparqlParser.VAR2) {
                                    alt5 = 1;
                                 }

                              } finally {
                                 this.dbg.exitDecision(5);
                              }

                              switch (alt5) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:97:40: var
                                    {
                                       this.dbg.location(97, 40);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_var_in_selectQuery153);
                                       var16 = this.var();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, var16.getTree());

                                    }
                                    break;

                                 default:
                                    if (cnt5 >= 1) {
                                       break loop5;
                                    }
                                    final EarlyExitException eee = new EarlyExitException(5,
                                          this.input);
                                    this.dbg.recognitionException(eee);

                                    throw eee;
                              }
                              cnt5++;
                           } while (true);
                        } finally {
                           this.dbg.exitSubRule(5);
                        }

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:97:47: ASTERISK
                     {
                        this.dbg.location(97, 47);
                        ASTERISK17 = (Token) this.match(this.input, SparqlParser.ASTERISK,
                              SparqlParser.FOLLOW_ASTERISK_in_selectQuery158);
                        ASTERISK17_tree = this.adaptor.create(ASTERISK17);
                        this.adaptor.addChild(root_0, ASTERISK17_tree);

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(6);
            }
            this.dbg.location(97, 58);
            // Sparql.g:97:58: ( datasetClause )*
            try {
               this.dbg.enterSubRule(7);

               loop7: do {
                  int alt7 = 2;
                  try {
                     this.dbg.enterDecision(7);

                     final int LA7_0 = this.input.LA(1);

                     if (LA7_0 == SparqlParser.FROM) {
                        alt7 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(7);
                  }

                  switch (alt7) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:97:58: datasetClause
                        {
                           this.dbg.location(97, 58);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_datasetClause_in_selectQuery162);
                           datasetClause18 = this.datasetClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, datasetClause18.getTree());

                        }
                        break;

                     default:
                        break loop7;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(7);
            }
            this.dbg.location(97, 73);
            this.pushFollow(SparqlParser.FOLLOW_whereClause_in_selectQuery165);
            whereClause19 = this.whereClause();
            this.state._fsp--;
            this.adaptor.addChild(root_0, whereClause19.getTree());
            this.dbg.location(97, 85);
            this.pushFollow(SparqlParser.FOLLOW_solutionModifier_in_selectQuery167);
            solutionModifier20 = this.solutionModifier();
            this.state._fsp--;
            this.adaptor.addChild(root_0, solutionModifier20.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(98, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "selectQuery");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "selectQuery"

   public static class constructQuery_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructQuery"
   // Sparql.g:100:1: constructQuery : CONSTRUCT constructTemplate ( datasetClause )*
   // whereClause solutionModifier ;
   public final SparqlParser.constructQuery_return constructQuery()
         throws RecognitionException {
      final SparqlParser.constructQuery_return retval = new SparqlParser.constructQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token CONSTRUCT21 = null;
      SparqlParser.constructTemplate_return constructTemplate22 = null;

      SparqlParser.datasetClause_return datasetClause23 = null;

      SparqlParser.whereClause_return whereClause24 = null;

      SparqlParser.solutionModifier_return solutionModifier25 = null;

      Object CONSTRUCT21_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(100, 1);

         try {
            // Sparql.g:101:5: ( CONSTRUCT constructTemplate ( datasetClause )* whereClause
            // solutionModifier )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(101, 7);
            CONSTRUCT21 = (Token) this.match(this.input, SparqlParser.CONSTRUCT,
                  SparqlParser.FOLLOW_CONSTRUCT_in_constructQuery184);
            CONSTRUCT21_tree = this.adaptor.create(CONSTRUCT21);
            this.adaptor.addChild(root_0, CONSTRUCT21_tree);
            this.dbg.location(101, 17);
            this.pushFollow(SparqlParser.FOLLOW_constructTemplate_in_constructQuery186);
            constructTemplate22 = this.constructTemplate();
            this.state._fsp--;
            this.adaptor.addChild(root_0, constructTemplate22.getTree());
            this.dbg.location(101, 35);
            // Sparql.g:101:35: ( datasetClause )*
            try {
               this.dbg.enterSubRule(8);

               loop8: do {
                  int alt8 = 2;
                  try {
                     this.dbg.enterDecision(8);

                     final int LA8_0 = this.input.LA(1);

                     if (LA8_0 == SparqlParser.FROM) {
                        alt8 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(8);
                  }

                  switch (alt8) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:101:35: datasetClause
                        {
                           this.dbg.location(101, 35);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_datasetClause_in_constructQuery188);
                           datasetClause23 = this.datasetClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, datasetClause23.getTree());

                        }
                        break;

                     default:
                        break loop8;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(8);
            }
            this.dbg.location(101, 50);
            this.pushFollow(SparqlParser.FOLLOW_whereClause_in_constructQuery191);
            whereClause24 = this.whereClause();
            this.state._fsp--;
            this.adaptor.addChild(root_0, whereClause24.getTree());
            this.dbg.location(101, 62);
            this.pushFollow(SparqlParser.FOLLOW_solutionModifier_in_constructQuery193);
            solutionModifier25 = this.solutionModifier();
            this.state._fsp--;
            this.adaptor.addChild(root_0, solutionModifier25.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(102, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "constructQuery");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "constructQuery"

   public static class describeQuery_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "describeQuery"
   // Sparql.g:104:1: describeQuery : DESCRIBE ( ( varOrIRIref )+ | ASTERISK ) (
   // datasetClause )* ( whereClause )? solutionModifier ;
   public final SparqlParser.describeQuery_return describeQuery()
         throws RecognitionException {
      final SparqlParser.describeQuery_return retval = new SparqlParser.describeQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DESCRIBE26 = null;
      Token ASTERISK28 = null;
      SparqlParser.varOrIRIref_return varOrIRIref27 = null;

      SparqlParser.datasetClause_return datasetClause29 = null;

      SparqlParser.whereClause_return whereClause30 = null;

      SparqlParser.solutionModifier_return solutionModifier31 = null;

      Object DESCRIBE26_tree = null;
      Object ASTERISK28_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "describeQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(104, 1);

         try {
            // Sparql.g:105:5: ( DESCRIBE ( ( varOrIRIref )+ | ASTERISK ) ( datasetClause )*
            // ( whereClause )? solutionModifier )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(105, 7);
            DESCRIBE26 = (Token) this.match(this.input, SparqlParser.DESCRIBE,
                  SparqlParser.FOLLOW_DESCRIBE_in_describeQuery210);
            DESCRIBE26_tree = this.adaptor.create(DESCRIBE26);
            this.adaptor.addChild(root_0, DESCRIBE26_tree);
            this.dbg.location(105, 16);
            // Sparql.g:105:16: ( ( varOrIRIref )+ | ASTERISK )
            int alt10 = 2;
            try {
               this.dbg.enterSubRule(10);
               try {
                  this.dbg.enterDecision(10);

                  final int LA10_0 = this.input.LA(1);

                  if (LA10_0 == SparqlParser.IRI_REF || LA10_0 == SparqlParser.PNAME_NS
                        || LA10_0 >= SparqlParser.VAR1 && LA10_0 <= SparqlParser.VAR2
                        || LA10_0 == SparqlParser.PNAME_LN) {
                     alt10 = 1;
                  } else if (LA10_0 == SparqlParser.ASTERISK) {
                     alt10 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 10, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
                  }
               } finally {
                  this.dbg.exitDecision(10);
               }

               switch (alt10) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:105:18: ( varOrIRIref )+
                     {
                        this.dbg.location(105, 18);
                        // Sparql.g:105:18: ( varOrIRIref )+
                        int cnt9 = 0;
                        try {
                           this.dbg.enterSubRule(9);

                           loop9: do {
                              int alt9 = 2;
                              try {
                                 this.dbg.enterDecision(9);

                                 final int LA9_0 = this.input.LA(1);

                                 if (LA9_0 == SparqlParser.IRI_REF
                                       || LA9_0 == SparqlParser.PNAME_NS
                                       || LA9_0 >= SparqlParser.VAR1
                                       && LA9_0 <= SparqlParser.VAR2
                                       || LA9_0 == SparqlParser.PNAME_LN) {
                                    alt9 = 1;
                                 }

                              } finally {
                                 this.dbg.exitDecision(9);
                              }

                              switch (alt9) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:105:18: varOrIRIref
                                    {
                                       this.dbg.location(105, 18);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_varOrIRIref_in_describeQuery214);
                                       varOrIRIref27 = this.varOrIRIref();

                                       this.state._fsp--;

                                       this.adaptor
                                             .addChild(root_0, varOrIRIref27.getTree());

                                    }
                                    break;

                                 default:
                                    if (cnt9 >= 1) {
                                       break loop9;
                                    }
                                    final EarlyExitException eee = new EarlyExitException(9,
                                          this.input);
                                    this.dbg.recognitionException(eee);

                                    throw eee;
                              }
                              cnt9++;
                           } while (true);
                        } finally {
                           this.dbg.exitSubRule(9);
                        }

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:105:33: ASTERISK
                     {
                        this.dbg.location(105, 33);
                        ASTERISK28 = (Token) this.match(this.input, SparqlParser.ASTERISK,
                              SparqlParser.FOLLOW_ASTERISK_in_describeQuery219);
                        ASTERISK28_tree = this.adaptor.create(ASTERISK28);
                        this.adaptor.addChild(root_0, ASTERISK28_tree);

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(10);
            }
            this.dbg.location(105, 44);
            // Sparql.g:105:44: ( datasetClause )*
            try {
               this.dbg.enterSubRule(11);

               loop11: do {
                  int alt11 = 2;
                  try {
                     this.dbg.enterDecision(11);

                     final int LA11_0 = this.input.LA(1);

                     if (LA11_0 == SparqlParser.FROM) {
                        alt11 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(11);
                  }

                  switch (alt11) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:105:44: datasetClause
                        {
                           this.dbg.location(105, 44);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_datasetClause_in_describeQuery223);
                           datasetClause29 = this.datasetClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, datasetClause29.getTree());

                        }
                        break;

                     default:
                        break loop11;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(11);
            }
            this.dbg.location(105, 59);
            // Sparql.g:105:59: ( whereClause )?
            int alt12 = 2;
            try {
               this.dbg.enterSubRule(12);
               try {
                  this.dbg.enterDecision(12);

                  final int LA12_0 = this.input.LA(1);

                  if (LA12_0 == SparqlParser.WHERE
                        || LA12_0 == SparqlParser.OPEN_CURLY_BRACE) {
                     alt12 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(12);
               }

               switch (alt12) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:105:59: whereClause
                     {
                        this.dbg.location(105, 59);
                        this.pushFollow(SparqlParser.FOLLOW_whereClause_in_describeQuery226);
                        whereClause30 = this.whereClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, whereClause30.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(12);
            }
            this.dbg.location(105, 72);
            this.pushFollow(SparqlParser.FOLLOW_solutionModifier_in_describeQuery229);
            solutionModifier31 = this.solutionModifier();
            this.state._fsp--;
            this.adaptor.addChild(root_0, solutionModifier31.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(106, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "describeQuery");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "describeQuery"

   public static class askQuery_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "askQuery"
   // Sparql.g:108:1: askQuery : ASK ( datasetClause )* whereClause ;
   public final SparqlParser.askQuery_return askQuery() throws RecognitionException {
      final SparqlParser.askQuery_return retval = new SparqlParser.askQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ASK32 = null;
      SparqlParser.datasetClause_return datasetClause33 = null;

      SparqlParser.whereClause_return whereClause34 = null;

      Object ASK32_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "askQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(108, 1);

         try {
            // Sparql.g:109:5: ( ASK ( datasetClause )* whereClause )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(109, 7);
            ASK32 = (Token) this.match(this.input, SparqlParser.ASK,
                  SparqlParser.FOLLOW_ASK_in_askQuery246);
            ASK32_tree = this.adaptor.create(ASK32);
            this.adaptor.addChild(root_0, ASK32_tree);
            this.dbg.location(109, 11);
            // Sparql.g:109:11: ( datasetClause )*
            try {
               this.dbg.enterSubRule(13);

               loop13: do {
                  int alt13 = 2;
                  try {
                     this.dbg.enterDecision(13);

                     final int LA13_0 = this.input.LA(1);

                     if (LA13_0 == SparqlParser.FROM) {
                        alt13 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(13);
                  }

                  switch (alt13) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:109:11: datasetClause
                        {
                           this.dbg.location(109, 11);
                           this.pushFollow(SparqlParser.FOLLOW_datasetClause_in_askQuery248);
                           datasetClause33 = this.datasetClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, datasetClause33.getTree());

                        }
                        break;

                     default:
                        break loop13;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(13);
            }
            this.dbg.location(109, 26);
            this.pushFollow(SparqlParser.FOLLOW_whereClause_in_askQuery251);
            whereClause34 = this.whereClause();
            this.state._fsp--;
            this.adaptor.addChild(root_0, whereClause34.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(110, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "askQuery");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "askQuery"

   public static class datasetClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "datasetClause"
   // Sparql.g:112:1: datasetClause : FROM ( defaultGraphClause | namedGraphClause ) ;
   public final SparqlParser.datasetClause_return datasetClause()
         throws RecognitionException {
      final SparqlParser.datasetClause_return retval = new SparqlParser.datasetClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FROM35 = null;
      SparqlParser.defaultGraphClause_return defaultGraphClause36 = null;

      SparqlParser.namedGraphClause_return namedGraphClause37 = null;

      Object FROM35_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "datasetClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(112, 1);

         try {
            // Sparql.g:113:5: ( FROM ( defaultGraphClause | namedGraphClause ) )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(113, 7);
            FROM35 = (Token) this.match(this.input, SparqlParser.FROM,
                  SparqlParser.FOLLOW_FROM_in_datasetClause268);
            FROM35_tree = this.adaptor.create(FROM35);
            this.adaptor.addChild(root_0, FROM35_tree);
            this.dbg.location(113, 12);
            // Sparql.g:113:12: ( defaultGraphClause | namedGraphClause )
            int alt14 = 2;
            try {
               this.dbg.enterSubRule(14);
               try {
                  this.dbg.enterDecision(14);

                  final int LA14_0 = this.input.LA(1);

                  if (LA14_0 == SparqlParser.IRI_REF || LA14_0 == SparqlParser.PNAME_NS
                        || LA14_0 == SparqlParser.PNAME_LN) {
                     alt14 = 1;
                  } else if (LA14_0 == SparqlParser.NAMED) {
                     alt14 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 14, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
                  }
               } finally {
                  this.dbg.exitDecision(14);
               }

               switch (alt14) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:113:14: defaultGraphClause
                     {
                        this.dbg.location(113, 14);
                        this
                              .pushFollow(SparqlParser.FOLLOW_defaultGraphClause_in_datasetClause272);
                        defaultGraphClause36 = this.defaultGraphClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, defaultGraphClause36.getTree());

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:113:35: namedGraphClause
                     {
                        this.dbg.location(113, 35);
                        this
                              .pushFollow(SparqlParser.FOLLOW_namedGraphClause_in_datasetClause276);
                        namedGraphClause37 = this.namedGraphClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, namedGraphClause37.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(14);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(114, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "datasetClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "datasetClause"

   public static class defaultGraphClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "defaultGraphClause"
   // Sparql.g:116:1: defaultGraphClause : sourceSelector ;
   public final SparqlParser.defaultGraphClause_return defaultGraphClause()
         throws RecognitionException {
      final SparqlParser.defaultGraphClause_return retval = new SparqlParser.defaultGraphClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.sourceSelector_return sourceSelector38 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "defaultGraphClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(116, 1);

         try {
            // Sparql.g:117:5: ( sourceSelector )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(117, 7);
            this.pushFollow(SparqlParser.FOLLOW_sourceSelector_in_defaultGraphClause295);
            sourceSelector38 = this.sourceSelector();
            this.state._fsp--;
            this.adaptor.addChild(root_0, sourceSelector38.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(118, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "defaultGraphClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "defaultGraphClause"

   public static class namedGraphClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "namedGraphClause"
   // Sparql.g:120:1: namedGraphClause : NAMED sourceSelector ;
   public final SparqlParser.namedGraphClause_return namedGraphClause()
         throws RecognitionException {
      final SparqlParser.namedGraphClause_return retval = new SparqlParser.namedGraphClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NAMED39 = null;
      SparqlParser.sourceSelector_return sourceSelector40 = null;

      Object NAMED39_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "namedGraphClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(120, 1);

         try {
            // Sparql.g:121:5: ( NAMED sourceSelector )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(121, 7);
            NAMED39 = (Token) this.match(this.input, SparqlParser.NAMED,
                  SparqlParser.FOLLOW_NAMED_in_namedGraphClause312);
            NAMED39_tree = this.adaptor.create(NAMED39);
            this.adaptor.addChild(root_0, NAMED39_tree);
            this.dbg.location(121, 13);
            this.pushFollow(SparqlParser.FOLLOW_sourceSelector_in_namedGraphClause314);
            sourceSelector40 = this.sourceSelector();
            this.state._fsp--;
            this.adaptor.addChild(root_0, sourceSelector40.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(122, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "namedGraphClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "namedGraphClause"

   public static class sourceSelector_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "sourceSelector"
   // Sparql.g:124:1: sourceSelector : iriRef ;
   public final SparqlParser.sourceSelector_return sourceSelector()
         throws RecognitionException {
      final SparqlParser.sourceSelector_return retval = new SparqlParser.sourceSelector_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.iriRef_return iriRef41 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "sourceSelector");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(124, 1);

         try {
            // Sparql.g:125:5: ( iriRef )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(125, 7);
            this.pushFollow(SparqlParser.FOLLOW_iriRef_in_sourceSelector331);
            iriRef41 = this.iriRef();
            this.state._fsp--;
            this.adaptor.addChild(root_0, iriRef41.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(126, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "sourceSelector");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "sourceSelector"

   public static class whereClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "whereClause"
   // Sparql.g:128:1: whereClause : ( WHERE )? groupGraphPattern ;
   public final SparqlParser.whereClause_return whereClause() throws RecognitionException {
      final SparqlParser.whereClause_return retval = new SparqlParser.whereClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token WHERE42 = null;
      SparqlParser.groupGraphPattern_return groupGraphPattern43 = null;

      Object WHERE42_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "whereClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(128, 1);

         try {
            // Sparql.g:129:5: ( ( WHERE )? groupGraphPattern )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(129, 7);
            // Sparql.g:129:7: ( WHERE )?
            int alt15 = 2;
            try {
               this.dbg.enterSubRule(15);
               try {
                  this.dbg.enterDecision(15);

                  final int LA15_0 = this.input.LA(1);

                  if (LA15_0 == SparqlParser.WHERE) {
                     alt15 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(15);
               }

               switch (alt15) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:129:7: WHERE
                     {
                        this.dbg.location(129, 7);
                        WHERE42 = (Token) this.match(this.input, SparqlParser.WHERE,
                              SparqlParser.FOLLOW_WHERE_in_whereClause348);
                        WHERE42_tree = this.adaptor.create(WHERE42);
                        this.adaptor.addChild(root_0, WHERE42_tree);

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(15);
            }
            this.dbg.location(129, 14);
            this.pushFollow(SparqlParser.FOLLOW_groupGraphPattern_in_whereClause351);
            groupGraphPattern43 = this.groupGraphPattern();
            this.state._fsp--;
            this.adaptor.addChild(root_0, groupGraphPattern43.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(130, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "whereClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "whereClause"

   public static class solutionModifier_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "solutionModifier"
   // Sparql.g:132:1: solutionModifier : ( orderClause )? ( limitOffsetClauses )? ;
   public final SparqlParser.solutionModifier_return solutionModifier()
         throws RecognitionException {
      final SparqlParser.solutionModifier_return retval = new SparqlParser.solutionModifier_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.orderClause_return orderClause44 = null;

      SparqlParser.limitOffsetClauses_return limitOffsetClauses45 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "solutionModifier");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(132, 1);

         try {
            // Sparql.g:133:5: ( ( orderClause )? ( limitOffsetClauses )? )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(133, 7);
            // Sparql.g:133:7: ( orderClause )?
            int alt16 = 2;
            try {
               this.dbg.enterSubRule(16);
               try {
                  this.dbg.enterDecision(16);

                  final int LA16_0 = this.input.LA(1);

                  if (LA16_0 == SparqlParser.ORDER) {
                     alt16 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(16);
               }

               switch (alt16) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:133:7: orderClause
                     {
                        this.dbg.location(133, 7);
                        this
                              .pushFollow(SparqlParser.FOLLOW_orderClause_in_solutionModifier368);
                        orderClause44 = this.orderClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, orderClause44.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(16);
            }
            this.dbg.location(133, 20);
            // Sparql.g:133:20: ( limitOffsetClauses )?
            int alt17 = 2;
            try {
               this.dbg.enterSubRule(17);
               try {
                  this.dbg.enterDecision(17);

                  final int LA17_0 = this.input.LA(1);

                  if (LA17_0 == SparqlParser.LIMIT || LA17_0 == SparqlParser.OFFSET) {
                     alt17 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(17);
               }

               switch (alt17) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:133:20: limitOffsetClauses
                     {
                        this.dbg.location(133, 20);
                        this
                              .pushFollow(SparqlParser.FOLLOW_limitOffsetClauses_in_solutionModifier371);
                        limitOffsetClauses45 = this.limitOffsetClauses();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, limitOffsetClauses45.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(17);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(134, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "solutionModifier");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "solutionModifier"

   public static class limitOffsetClauses_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "limitOffsetClauses"
   // Sparql.g:136:1: limitOffsetClauses : ( limitClause ( offsetClause )? | offsetClause (
   // limitClause )? ) ;
   public final SparqlParser.limitOffsetClauses_return limitOffsetClauses()
         throws RecognitionException {
      final SparqlParser.limitOffsetClauses_return retval = new SparqlParser.limitOffsetClauses_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.limitClause_return limitClause46 = null;

      SparqlParser.offsetClause_return offsetClause47 = null;

      SparqlParser.offsetClause_return offsetClause48 = null;

      SparqlParser.limitClause_return limitClause49 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "limitOffsetClauses");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(136, 1);

         try {
            // Sparql.g:137:5: ( ( limitClause ( offsetClause )? | offsetClause ( limitClause
            // )? ) )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(137, 7);
            // Sparql.g:137:7: ( limitClause ( offsetClause )? | offsetClause ( limitClause
            // )? )
            int alt20 = 2;
            try {
               this.dbg.enterSubRule(20);
               try {
                  this.dbg.enterDecision(20);

                  final int LA20_0 = this.input.LA(1);

                  if (LA20_0 == SparqlParser.LIMIT) {
                     alt20 = 1;
                  } else if (LA20_0 == SparqlParser.OFFSET) {
                     alt20 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 20, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
                  }
               } finally {
                  this.dbg.exitDecision(20);
               }

               switch (alt20) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:137:9: limitClause ( offsetClause )?
                     {
                        this.dbg.location(137, 9);
                        this
                              .pushFollow(SparqlParser.FOLLOW_limitClause_in_limitOffsetClauses391);
                        limitClause46 = this.limitClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, limitClause46.getTree());
                        this.dbg.location(137, 21);
                        // Sparql.g:137:21: ( offsetClause )?
                        int alt18 = 2;
                        try {
                           this.dbg.enterSubRule(18);
                           try {
                              this.dbg.enterDecision(18);

                              final int LA18_0 = this.input.LA(1);

                              if (LA18_0 == SparqlParser.OFFSET) {
                                 alt18 = 1;
                              }
                           } finally {
                              this.dbg.exitDecision(18);
                           }

                           switch (alt18) {
                              case 1:
                                 this.dbg.enterAlt(1);

                                 // Sparql.g:137:21: offsetClause
                                 {
                                    this.dbg.location(137, 21);
                                    this
                                          .pushFollow(SparqlParser.FOLLOW_offsetClause_in_limitOffsetClauses393);
                                    offsetClause47 = this.offsetClause();

                                    this.state._fsp--;

                                    this.adaptor.addChild(root_0, offsetClause47.getTree());

                                 }
                                 break;

                           }
                        } finally {
                           this.dbg.exitSubRule(18);
                        }

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:137:37: offsetClause ( limitClause )?
                     {
                        this.dbg.location(137, 37);
                        this
                              .pushFollow(SparqlParser.FOLLOW_offsetClause_in_limitOffsetClauses398);
                        offsetClause48 = this.offsetClause();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, offsetClause48.getTree());
                        this.dbg.location(137, 50);
                        // Sparql.g:137:50: ( limitClause )?
                        int alt19 = 2;
                        try {
                           this.dbg.enterSubRule(19);
                           try {
                              this.dbg.enterDecision(19);

                              final int LA19_0 = this.input.LA(1);

                              if (LA19_0 == SparqlParser.LIMIT) {
                                 alt19 = 1;
                              }
                           } finally {
                              this.dbg.exitDecision(19);
                           }

                           switch (alt19) {
                              case 1:
                                 this.dbg.enterAlt(1);

                                 // Sparql.g:137:50: limitClause
                                 {
                                    this.dbg.location(137, 50);
                                    this
                                          .pushFollow(SparqlParser.FOLLOW_limitClause_in_limitOffsetClauses400);
                                    limitClause49 = this.limitClause();

                                    this.state._fsp--;

                                    this.adaptor.addChild(root_0, limitClause49.getTree());

                                 }
                                 break;

                           }
                        } finally {
                           this.dbg.exitSubRule(19);
                        }

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(20);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(138, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "limitOffsetClauses");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "limitOffsetClauses"

   public static class orderClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderClause"
   // Sparql.g:140:1: orderClause : ORDER BY ( orderCondition )+ ;
   public final SparqlParser.orderClause_return orderClause() throws RecognitionException {
      final SparqlParser.orderClause_return retval = new SparqlParser.orderClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ORDER50 = null;
      Token BY51 = null;
      SparqlParser.orderCondition_return orderCondition52 = null;

      Object ORDER50_tree = null;
      Object BY51_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "orderClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(140, 1);

         try {
            // Sparql.g:141:5: ( ORDER BY ( orderCondition )+ )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(141, 7);
            ORDER50 = (Token) this.match(this.input, SparqlParser.ORDER,
                  SparqlParser.FOLLOW_ORDER_in_orderClause420);
            ORDER50_tree = this.adaptor.create(ORDER50);
            this.adaptor.addChild(root_0, ORDER50_tree);
            this.dbg.location(141, 13);
            BY51 = (Token) this.match(this.input, SparqlParser.BY,
                  SparqlParser.FOLLOW_BY_in_orderClause422);
            BY51_tree = this.adaptor.create(BY51);
            this.adaptor.addChild(root_0, BY51_tree);
            this.dbg.location(141, 16);
            // Sparql.g:141:16: ( orderCondition )+
            int cnt21 = 0;
            try {
               this.dbg.enterSubRule(21);

               loop21: do {
                  int alt21 = 2;
                  try {
                     this.dbg.enterDecision(21);

                     final int LA21_0 = this.input.LA(1);

                     if (LA21_0 == SparqlParser.IRI_REF || LA21_0 == SparqlParser.PNAME_NS
                           || LA21_0 >= SparqlParser.ASC && LA21_0 <= SparqlParser.DESC
                           || LA21_0 == SparqlParser.OPEN_BRACE
                           || LA21_0 >= SparqlParser.VAR1 && LA21_0 <= SparqlParser.VAR2
                           || LA21_0 >= SparqlParser.STR && LA21_0 <= SparqlParser.REGEX
                           || LA21_0 == SparqlParser.PNAME_LN) {
                        alt21 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(21);
                  }

                  switch (alt21) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:141:16: orderCondition
                        {
                           this.dbg.location(141, 16);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_orderCondition_in_orderClause424);
                           orderCondition52 = this.orderCondition();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, orderCondition52.getTree());

                        }
                        break;

                     default:
                        if (cnt21 >= 1) {
                           break loop21;
                        }
                        final EarlyExitException eee = new EarlyExitException(21, this.input);
                        this.dbg.recognitionException(eee);

                        throw eee;
                  }
                  cnt21++;
               } while (true);
            } finally {
               this.dbg.exitSubRule(21);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(142, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "orderClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "orderClause"

   public static class orderCondition_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderCondition"
   // Sparql.g:144:1: orderCondition : ( ( ( ASC | DESC ) brackettedExpression ) | (
   // constraint | var ) );
   public final SparqlParser.orderCondition_return orderCondition()
         throws RecognitionException {
      final SparqlParser.orderCondition_return retval = new SparqlParser.orderCondition_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set53 = null;
      SparqlParser.brackettedExpression_return brackettedExpression54 = null;

      SparqlParser.constraint_return constraint55 = null;

      SparqlParser.var_return var56 = null;

      final Object set53_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "orderCondition");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(144, 1);

         try {
            // Sparql.g:145:5: ( ( ( ASC | DESC ) brackettedExpression ) | ( constraint | var
            // ) )
            int alt23 = 2;
            try {
               this.dbg.enterDecision(23);

               final int LA23_0 = this.input.LA(1);

               if (LA23_0 >= SparqlParser.ASC && LA23_0 <= SparqlParser.DESC) {
                  alt23 = 1;
               } else if (LA23_0 == SparqlParser.IRI_REF || LA23_0 == SparqlParser.PNAME_NS
                     || LA23_0 == SparqlParser.OPEN_BRACE || LA23_0 >= SparqlParser.VAR1
                     && LA23_0 <= SparqlParser.VAR2 || LA23_0 >= SparqlParser.STR
                     && LA23_0 <= SparqlParser.REGEX || LA23_0 == SparqlParser.PNAME_LN) {
                  alt23 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 23, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(23);
            }

            switch (alt23) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(145, 7);
                  // Sparql.g:145:7: ( ( ASC | DESC ) brackettedExpression )
                  this.dbg.enterAlt(1);
                  // Sparql.g:145:9: ( ASC | DESC ) brackettedExpression
                  {
                     this.dbg.location(145, 9);
                     set53 = this.input.LT(1);
                     if (this.input.LA(1) >= SparqlParser.ASC
                           && this.input.LA(1) <= SparqlParser.DESC) {
                        this.input.consume();
                        this.adaptor.addChild(root_0, this.adaptor.create(set53));
                        this.state.errorRecovery = false;
                     } else {
                        final MismatchedSetException mse = new MismatchedSetException(null,
                              this.input);
                        this.dbg.recognitionException(mse);
                        throw mse;
                     }

                     this.dbg.location(145, 24);
                     this
                           .pushFollow(SparqlParser.FOLLOW_brackettedExpression_in_orderCondition454);
                     brackettedExpression54 = this.brackettedExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, brackettedExpression54.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(146, 7);
                  // Sparql.g:146:7: ( constraint | var )
                  int alt22 = 2;
                  try {
                     this.dbg.enterSubRule(22);
                     try {
                        this.dbg.enterDecision(22);

                        final int LA22_0 = this.input.LA(1);

                        if (LA22_0 == SparqlParser.IRI_REF
                              || LA22_0 == SparqlParser.PNAME_NS
                              || LA22_0 == SparqlParser.OPEN_BRACE
                              || LA22_0 >= SparqlParser.STR && LA22_0 <= SparqlParser.REGEX
                              || LA22_0 == SparqlParser.PNAME_LN) {
                           alt22 = 1;
                        } else if (LA22_0 >= SparqlParser.VAR1
                              && LA22_0 <= SparqlParser.VAR2) {
                           alt22 = 2;
                        } else {
                           final NoViableAltException nvae = new NoViableAltException("",
                                 22, 0, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                        }
                     } finally {
                        this.dbg.exitDecision(22);
                     }

                     switch (alt22) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // Sparql.g:146:9: constraint
                           {
                              this.dbg.location(146, 9);
                              this
                                    .pushFollow(SparqlParser.FOLLOW_constraint_in_orderCondition466);
                              constraint55 = this.constraint();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, constraint55.getTree());

                           }
                           break;
                        case 2:
                           this.dbg.enterAlt(2);

                           // Sparql.g:146:22: var
                           {
                              this.dbg.location(146, 22);
                              this.pushFollow(SparqlParser.FOLLOW_var_in_orderCondition470);
                              var56 = this.var();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, var56.getTree());

                           }
                           break;

                     }
                  } finally {
                     this.dbg.exitSubRule(22);
                  }
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(147, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "orderCondition");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "orderCondition"

   public static class limitClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "limitClause"
   // Sparql.g:149:1: limitClause : LIMIT INTEGER ;
   public final SparqlParser.limitClause_return limitClause() throws RecognitionException {
      final SparqlParser.limitClause_return retval = new SparqlParser.limitClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token LIMIT57 = null;
      Token INTEGER58 = null;

      Object LIMIT57_tree = null;
      Object INTEGER58_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "limitClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(149, 1);

         try {
            // Sparql.g:150:5: ( LIMIT INTEGER )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(150, 7);
            LIMIT57 = (Token) this.match(this.input, SparqlParser.LIMIT,
                  SparqlParser.FOLLOW_LIMIT_in_limitClause489);
            LIMIT57_tree = this.adaptor.create(LIMIT57);
            this.adaptor.addChild(root_0, LIMIT57_tree);
            this.dbg.location(150, 13);
            INTEGER58 = (Token) this.match(this.input, SparqlParser.INTEGER,
                  SparqlParser.FOLLOW_INTEGER_in_limitClause491);
            INTEGER58_tree = this.adaptor.create(INTEGER58);
            this.adaptor.addChild(root_0, INTEGER58_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(151, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "limitClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "limitClause"

   public static class offsetClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "offsetClause"
   // Sparql.g:153:1: offsetClause : OFFSET INTEGER ;
   public final SparqlParser.offsetClause_return offsetClause() throws RecognitionException {
      final SparqlParser.offsetClause_return retval = new SparqlParser.offsetClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OFFSET59 = null;
      Token INTEGER60 = null;

      Object OFFSET59_tree = null;
      Object INTEGER60_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "offsetClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(153, 1);

         try {
            // Sparql.g:154:5: ( OFFSET INTEGER )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(154, 7);
            OFFSET59 = (Token) this.match(this.input, SparqlParser.OFFSET,
                  SparqlParser.FOLLOW_OFFSET_in_offsetClause508);
            OFFSET59_tree = this.adaptor.create(OFFSET59);
            this.adaptor.addChild(root_0, OFFSET59_tree);
            this.dbg.location(154, 14);
            INTEGER60 = (Token) this.match(this.input, SparqlParser.INTEGER,
                  SparqlParser.FOLLOW_INTEGER_in_offsetClause510);
            INTEGER60_tree = this.adaptor.create(INTEGER60);
            this.adaptor.addChild(root_0, INTEGER60_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(155, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "offsetClause");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "offsetClause"

   public static class groupGraphPattern_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "groupGraphPattern"
   // Sparql.g:157:1: groupGraphPattern : OPEN_CURLY_BRACE ( triplesBlock )? ( (
   // graphPatternNotTriples | filter ) ( DOT )? ( triplesBlock )? )* CLOSE_CURLY_BRACE ;
   public final SparqlParser.groupGraphPattern_return groupGraphPattern()
         throws RecognitionException {
      final SparqlParser.groupGraphPattern_return retval = new SparqlParser.groupGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_CURLY_BRACE61 = null;
      Token DOT65 = null;
      Token CLOSE_CURLY_BRACE67 = null;
      SparqlParser.triplesBlock_return triplesBlock62 = null;

      SparqlParser.graphPatternNotTriples_return graphPatternNotTriples63 = null;

      SparqlParser.filter_return filter64 = null;

      SparqlParser.triplesBlock_return triplesBlock66 = null;

      Object OPEN_CURLY_BRACE61_tree = null;
      Object DOT65_tree = null;
      Object CLOSE_CURLY_BRACE67_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "groupGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(157, 1);

         try {
            // Sparql.g:158:5: ( OPEN_CURLY_BRACE ( triplesBlock )? ( (
            // graphPatternNotTriples | filter ) ( DOT )? ( triplesBlock )? )*
            // CLOSE_CURLY_BRACE )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(158, 7);
            OPEN_CURLY_BRACE61 = (Token) this.match(this.input,
                  SparqlParser.OPEN_CURLY_BRACE,
                  SparqlParser.FOLLOW_OPEN_CURLY_BRACE_in_groupGraphPattern527);
            OPEN_CURLY_BRACE61_tree = this.adaptor.create(OPEN_CURLY_BRACE61);
            this.adaptor.addChild(root_0, OPEN_CURLY_BRACE61_tree);
            this.dbg.location(158, 24);
            // Sparql.g:158:24: ( triplesBlock )?
            int alt24 = 2;
            try {
               this.dbg.enterSubRule(24);
               try {
                  this.dbg.enterDecision(24);

                  final int LA24_0 = this.input.LA(1);

                  if (LA24_0 == SparqlParser.IRI_REF || LA24_0 == SparqlParser.PNAME_NS
                        || LA24_0 == SparqlParser.INTEGER
                        || LA24_0 == SparqlParser.OPEN_BRACE
                        || LA24_0 == SparqlParser.OPEN_SQUARE_BRACE
                        || LA24_0 >= SparqlParser.VAR1 && LA24_0 <= SparqlParser.VAR2
                        || LA24_0 >= SparqlParser.DECIMAL
                        && LA24_0 <= SparqlParser.BLANK_NODE_LABEL) {
                     alt24 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(24);
               }

               switch (alt24) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:158:24: triplesBlock
                     {
                        this.dbg.location(158, 24);
                        this
                              .pushFollow(SparqlParser.FOLLOW_triplesBlock_in_groupGraphPattern529);
                        triplesBlock62 = this.triplesBlock();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, triplesBlock62.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(24);
            }
            this.dbg.location(158, 38);
            // Sparql.g:158:38: ( ( graphPatternNotTriples | filter ) ( DOT )? ( triplesBlock
            // )? )*
            try {
               this.dbg.enterSubRule(28);

               loop28: do {
                  int alt28 = 2;
                  try {
                     this.dbg.enterDecision(28);

                     final int LA28_0 = this.input.LA(1);

                     if (LA28_0 == SparqlParser.OPEN_CURLY_BRACE
                           || LA28_0 >= SparqlParser.OPTIONAL
                           && LA28_0 <= SparqlParser.GRAPH || LA28_0 == SparqlParser.FILTER) {
                        alt28 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(28);
                  }

                  switch (alt28) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:158:40: ( graphPatternNotTriples | filter ) ( DOT )? (
                        // triplesBlock )?
                        {
                           this.dbg.location(158, 40);
                           // Sparql.g:158:40: ( graphPatternNotTriples | filter )
                           int alt25 = 2;
                           try {
                              this.dbg.enterSubRule(25);
                              try {
                                 this.dbg.enterDecision(25);

                                 final int LA25_0 = this.input.LA(1);

                                 if (LA25_0 == SparqlParser.OPEN_CURLY_BRACE
                                       || LA25_0 >= SparqlParser.OPTIONAL
                                       && LA25_0 <= SparqlParser.GRAPH) {
                                    alt25 = 1;
                                 } else if (LA25_0 == SparqlParser.FILTER) {
                                    alt25 = 2;
                                 } else {
                                    final NoViableAltException nvae = new NoViableAltException(
                                          "", 25, 0, this.input);

                                    this.dbg.recognitionException(nvae);
                                    throw nvae;
                                 }
                              } finally {
                                 this.dbg.exitDecision(25);
                              }

                              switch (alt25) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:158:42: graphPatternNotTriples
                                    {
                                       this.dbg.location(158, 42);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_graphPatternNotTriples_in_groupGraphPattern536);
                                       graphPatternNotTriples63 = this
                                             .graphPatternNotTriples();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0,
                                             graphPatternNotTriples63.getTree());

                                    }
                                    break;
                                 case 2:
                                    this.dbg.enterAlt(2);

                                    // Sparql.g:158:67: filter
                                    {
                                       this.dbg.location(158, 67);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_filter_in_groupGraphPattern540);
                                       filter64 = this.filter();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, filter64.getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(25);
                           }

                           this.dbg.location(158, 76);
                           // Sparql.g:158:76: ( DOT )?
                           int alt26 = 2;
                           try {
                              this.dbg.enterSubRule(26);
                              try {
                                 this.dbg.enterDecision(26);

                                 final int LA26_0 = this.input.LA(1);

                                 if (LA26_0 == SparqlParser.DOT) {
                                    alt26 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(26);
                              }

                              switch (alt26) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:158:76: DOT
                                    {
                                       this.dbg.location(158, 76);
                                       DOT65 = (Token) this
                                             .match(
                                                   this.input,
                                                   SparqlParser.DOT,
                                                   SparqlParser.FOLLOW_DOT_in_groupGraphPattern544);
                                       DOT65_tree = this.adaptor.create(DOT65);
                                       this.adaptor.addChild(root_0, DOT65_tree);

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(26);
                           }

                           this.dbg.location(158, 81);
                           // Sparql.g:158:81: ( triplesBlock )?
                           int alt27 = 2;
                           try {
                              this.dbg.enterSubRule(27);
                              try {
                                 this.dbg.enterDecision(27);

                                 final int LA27_0 = this.input.LA(1);

                                 if (LA27_0 == SparqlParser.IRI_REF
                                       || LA27_0 == SparqlParser.PNAME_NS
                                       || LA27_0 == SparqlParser.INTEGER
                                       || LA27_0 == SparqlParser.OPEN_BRACE
                                       || LA27_0 == SparqlParser.OPEN_SQUARE_BRACE
                                       || LA27_0 >= SparqlParser.VAR1
                                       && LA27_0 <= SparqlParser.VAR2
                                       || LA27_0 >= SparqlParser.DECIMAL
                                       && LA27_0 <= SparqlParser.BLANK_NODE_LABEL) {
                                    alt27 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(27);
                              }

                              switch (alt27) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:158:81: triplesBlock
                                    {
                                       this.dbg.location(158, 81);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_triplesBlock_in_groupGraphPattern547);
                                       triplesBlock66 = this.triplesBlock();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, triplesBlock66
                                             .getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(27);
                           }

                        }
                        break;

                     default:
                        break loop28;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(28);
            }
            this.dbg.location(158, 98);
            CLOSE_CURLY_BRACE67 = (Token) this.match(this.input,
                  SparqlParser.CLOSE_CURLY_BRACE,
                  SparqlParser.FOLLOW_CLOSE_CURLY_BRACE_in_groupGraphPattern553);
            CLOSE_CURLY_BRACE67_tree = this.adaptor.create(CLOSE_CURLY_BRACE67);
            this.adaptor.addChild(root_0, CLOSE_CURLY_BRACE67_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(159, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "groupGraphPattern");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "groupGraphPattern"

   public static class triplesBlock_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesBlock"
   // Sparql.g:161:1: triplesBlock : triplesSameSubject ( DOT ( triplesBlock )? )? ;
   public final SparqlParser.triplesBlock_return triplesBlock() throws RecognitionException {
      final SparqlParser.triplesBlock_return retval = new SparqlParser.triplesBlock_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DOT69 = null;
      SparqlParser.triplesSameSubject_return triplesSameSubject68 = null;

      SparqlParser.triplesBlock_return triplesBlock70 = null;

      Object DOT69_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesBlock");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(161, 1);

         try {
            // Sparql.g:162:5: ( triplesSameSubject ( DOT ( triplesBlock )? )? )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(162, 7);
            this.pushFollow(SparqlParser.FOLLOW_triplesSameSubject_in_triplesBlock574);
            triplesSameSubject68 = this.triplesSameSubject();
            this.state._fsp--;
            this.adaptor.addChild(root_0, triplesSameSubject68.getTree());
            this.dbg.location(162, 26);
            // Sparql.g:162:26: ( DOT ( triplesBlock )? )?
            int alt30 = 2;
            try {
               this.dbg.enterSubRule(30);
               try {
                  this.dbg.enterDecision(30);

                  final int LA30_0 = this.input.LA(1);

                  if (LA30_0 == SparqlParser.DOT) {
                     alt30 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(30);
               }

               switch (alt30) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:162:28: DOT ( triplesBlock )?
                     {
                        this.dbg.location(162, 28);
                        DOT69 = (Token) this.match(this.input, SparqlParser.DOT,
                              SparqlParser.FOLLOW_DOT_in_triplesBlock578);
                        DOT69_tree = this.adaptor.create(DOT69);
                        this.adaptor.addChild(root_0, DOT69_tree);

                        this.dbg.location(162, 32);
                        // Sparql.g:162:32: ( triplesBlock )?
                        int alt29 = 2;
                        try {
                           this.dbg.enterSubRule(29);
                           try {
                              this.dbg.enterDecision(29);

                              final int LA29_0 = this.input.LA(1);

                              if (LA29_0 == SparqlParser.IRI_REF
                                    || LA29_0 == SparqlParser.PNAME_NS
                                    || LA29_0 == SparqlParser.INTEGER
                                    || LA29_0 == SparqlParser.OPEN_BRACE
                                    || LA29_0 == SparqlParser.OPEN_SQUARE_BRACE
                                    || LA29_0 >= SparqlParser.VAR1
                                    && LA29_0 <= SparqlParser.VAR2
                                    || LA29_0 >= SparqlParser.DECIMAL
                                    && LA29_0 <= SparqlParser.BLANK_NODE_LABEL) {
                                 alt29 = 1;
                              }
                           } finally {
                              this.dbg.exitDecision(29);
                           }

                           switch (alt29) {
                              case 1:
                                 this.dbg.enterAlt(1);

                                 // Sparql.g:162:32: triplesBlock
                                 {
                                    this.dbg.location(162, 32);
                                    this
                                          .pushFollow(SparqlParser.FOLLOW_triplesBlock_in_triplesBlock580);
                                    triplesBlock70 = this.triplesBlock();

                                    this.state._fsp--;

                                    this.adaptor.addChild(root_0, triplesBlock70.getTree());

                                 }
                                 break;

                           }
                        } finally {
                           this.dbg.exitSubRule(29);
                        }

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(30);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(163, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "triplesBlock");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "triplesBlock"

   public static class graphPatternNotTriples_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphPatternNotTriples"
   // Sparql.g:165:1: graphPatternNotTriples : ( optionalGraphPattern |
   // groupOrUnionGraphPattern | graphGraphPattern );
   public final SparqlParser.graphPatternNotTriples_return graphPatternNotTriples()
         throws RecognitionException {
      final SparqlParser.graphPatternNotTriples_return retval = new SparqlParser.graphPatternNotTriples_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.optionalGraphPattern_return optionalGraphPattern71 = null;

      SparqlParser.groupOrUnionGraphPattern_return groupOrUnionGraphPattern72 = null;

      SparqlParser.graphGraphPattern_return graphGraphPattern73 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphPatternNotTriples");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(165, 1);

         try {
            // Sparql.g:166:5: ( optionalGraphPattern | groupOrUnionGraphPattern |
            // graphGraphPattern )
            int alt31 = 3;
            try {
               this.dbg.enterDecision(31);

               switch (this.input.LA(1)) {
                  case OPTIONAL:
                     alt31 = 1;
                     break;
                  case OPEN_CURLY_BRACE:
                     alt31 = 2;
                     break;
                  case GRAPH:
                     alt31 = 3;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 31, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(31);
            }

            switch (alt31) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(166, 7);
                  this
                        .pushFollow(SparqlParser.FOLLOW_optionalGraphPattern_in_graphPatternNotTriples601);
                  optionalGraphPattern71 = this.optionalGraphPattern();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, optionalGraphPattern71.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(166, 30);
                  this
                        .pushFollow(SparqlParser.FOLLOW_groupOrUnionGraphPattern_in_graphPatternNotTriples605);
                  groupOrUnionGraphPattern72 = this.groupOrUnionGraphPattern();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, groupOrUnionGraphPattern72.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(166, 57);
                  this
                        .pushFollow(SparqlParser.FOLLOW_graphGraphPattern_in_graphPatternNotTriples609);
                  graphGraphPattern73 = this.graphGraphPattern();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, graphGraphPattern73.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(167, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "graphPatternNotTriples");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "graphPatternNotTriples"

   public static class optionalGraphPattern_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "optionalGraphPattern"
   // Sparql.g:169:1: optionalGraphPattern : OPTIONAL groupGraphPattern ;
   public final SparqlParser.optionalGraphPattern_return optionalGraphPattern()
         throws RecognitionException {
      final SparqlParser.optionalGraphPattern_return retval = new SparqlParser.optionalGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPTIONAL74 = null;
      SparqlParser.groupGraphPattern_return groupGraphPattern75 = null;

      Object OPTIONAL74_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "optionalGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(169, 1);

         try {
            // Sparql.g:170:5: ( OPTIONAL groupGraphPattern )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(170, 7);
            OPTIONAL74 = (Token) this.match(this.input, SparqlParser.OPTIONAL,
                  SparqlParser.FOLLOW_OPTIONAL_in_optionalGraphPattern626);
            OPTIONAL74_tree = this.adaptor.create(OPTIONAL74);
            this.adaptor.addChild(root_0, OPTIONAL74_tree);
            this.dbg.location(170, 16);
            this
                  .pushFollow(SparqlParser.FOLLOW_groupGraphPattern_in_optionalGraphPattern628);
            groupGraphPattern75 = this.groupGraphPattern();
            this.state._fsp--;
            this.adaptor.addChild(root_0, groupGraphPattern75.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(171, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "optionalGraphPattern");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "optionalGraphPattern"

   public static class graphGraphPattern_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphGraphPattern"
   // Sparql.g:173:1: graphGraphPattern : GRAPH varOrIRIref groupGraphPattern ;
   public final SparqlParser.graphGraphPattern_return graphGraphPattern()
         throws RecognitionException {
      final SparqlParser.graphGraphPattern_return retval = new SparqlParser.graphGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token GRAPH76 = null;
      SparqlParser.varOrIRIref_return varOrIRIref77 = null;

      SparqlParser.groupGraphPattern_return groupGraphPattern78 = null;

      Object GRAPH76_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(173, 1);

         try {
            // Sparql.g:174:5: ( GRAPH varOrIRIref groupGraphPattern )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(174, 7);
            GRAPH76 = (Token) this.match(this.input, SparqlParser.GRAPH,
                  SparqlParser.FOLLOW_GRAPH_in_graphGraphPattern645);
            GRAPH76_tree = this.adaptor.create(GRAPH76);
            this.adaptor.addChild(root_0, GRAPH76_tree);
            this.dbg.location(174, 13);
            this.pushFollow(SparqlParser.FOLLOW_varOrIRIref_in_graphGraphPattern647);
            varOrIRIref77 = this.varOrIRIref();
            this.state._fsp--;
            this.adaptor.addChild(root_0, varOrIRIref77.getTree());
            this.dbg.location(174, 25);
            this.pushFollow(SparqlParser.FOLLOW_groupGraphPattern_in_graphGraphPattern649);
            groupGraphPattern78 = this.groupGraphPattern();
            this.state._fsp--;
            this.adaptor.addChild(root_0, groupGraphPattern78.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(175, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "graphGraphPattern");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "graphGraphPattern"

   public static class groupOrUnionGraphPattern_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "groupOrUnionGraphPattern"
   // Sparql.g:177:1: groupOrUnionGraphPattern : groupGraphPattern ( UNION groupGraphPattern
   // )* ;
   public final SparqlParser.groupOrUnionGraphPattern_return groupOrUnionGraphPattern()
         throws RecognitionException {
      final SparqlParser.groupOrUnionGraphPattern_return retval = new SparqlParser.groupOrUnionGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token UNION80 = null;
      SparqlParser.groupGraphPattern_return groupGraphPattern79 = null;

      SparqlParser.groupGraphPattern_return groupGraphPattern81 = null;

      Object UNION80_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "groupOrUnionGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(177, 1);

         try {
            // Sparql.g:178:5: ( groupGraphPattern ( UNION groupGraphPattern )* )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(178, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern666);
            groupGraphPattern79 = this.groupGraphPattern();
            this.state._fsp--;
            this.adaptor.addChild(root_0, groupGraphPattern79.getTree());
            this.dbg.location(178, 25);
            // Sparql.g:178:25: ( UNION groupGraphPattern )*
            try {
               this.dbg.enterSubRule(32);

               loop32: do {
                  int alt32 = 2;
                  try {
                     this.dbg.enterDecision(32);

                     final int LA32_0 = this.input.LA(1);

                     if (LA32_0 == SparqlParser.UNION) {
                        alt32 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(32);
                  }

                  switch (alt32) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:178:27: UNION groupGraphPattern
                        {
                           this.dbg.location(178, 27);
                           UNION80 = (Token) this.match(this.input, SparqlParser.UNION,
                                 SparqlParser.FOLLOW_UNION_in_groupOrUnionGraphPattern670);
                           UNION80_tree = this.adaptor.create(UNION80);
                           this.adaptor.addChild(root_0, UNION80_tree);

                           this.dbg.location(178, 33);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern672);
                           groupGraphPattern81 = this.groupGraphPattern();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, groupGraphPattern81.getTree());

                        }
                        break;

                     default:
                        break loop32;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(32);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(179, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "groupOrUnionGraphPattern");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "groupOrUnionGraphPattern"

   public static class filter_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "filter"
   // Sparql.g:181:1: filter : FILTER constraint ;
   public final SparqlParser.filter_return filter() throws RecognitionException {
      final SparqlParser.filter_return retval = new SparqlParser.filter_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FILTER82 = null;
      SparqlParser.constraint_return constraint83 = null;

      Object FILTER82_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "filter");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(181, 1);

         try {
            // Sparql.g:182:5: ( FILTER constraint )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(182, 7);
            FILTER82 = (Token) this.match(this.input, SparqlParser.FILTER,
                  SparqlParser.FOLLOW_FILTER_in_filter692);
            FILTER82_tree = this.adaptor.create(FILTER82);
            this.adaptor.addChild(root_0, FILTER82_tree);
            this.dbg.location(182, 14);
            this.pushFollow(SparqlParser.FOLLOW_constraint_in_filter694);
            constraint83 = this.constraint();
            this.state._fsp--;
            this.adaptor.addChild(root_0, constraint83.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(183, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "filter");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "filter"

   public static class constraint_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constraint"
   // Sparql.g:185:1: constraint : ( brackettedExpression | builtInCall | functionCall );
   public final SparqlParser.constraint_return constraint() throws RecognitionException {
      final SparqlParser.constraint_return retval = new SparqlParser.constraint_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.brackettedExpression_return brackettedExpression84 = null;

      SparqlParser.builtInCall_return builtInCall85 = null;

      SparqlParser.functionCall_return functionCall86 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constraint");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(185, 1);

         try {
            // Sparql.g:186:5: ( brackettedExpression | builtInCall | functionCall )
            int alt33 = 3;
            try {
               this.dbg.enterDecision(33);

               switch (this.input.LA(1)) {
                  case OPEN_BRACE:
                     alt33 = 1;
                     break;
                  case STR:
                  case LANG:
                  case LANGMATCHES:
                  case DATATYPE:
                  case BOUND:
                  case SAMETERM:
                  case ISIRI:
                  case ISURI:
                  case ISBLANK:
                  case ISLITERAL:
                  case REGEX:
                     alt33 = 2;
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN:
                     alt33 = 3;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 33, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(33);
            }

            switch (alt33) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(186, 7);
                  this.pushFollow(SparqlParser.FOLLOW_brackettedExpression_in_constraint711);
                  brackettedExpression84 = this.brackettedExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, brackettedExpression84.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(186, 30);
                  this.pushFollow(SparqlParser.FOLLOW_builtInCall_in_constraint715);
                  builtInCall85 = this.builtInCall();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, builtInCall85.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(186, 44);
                  this.pushFollow(SparqlParser.FOLLOW_functionCall_in_constraint719);
                  functionCall86 = this.functionCall();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, functionCall86.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(187, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "constraint");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "constraint"

   public static class functionCall_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "functionCall"
   // Sparql.g:189:1: functionCall : iriRef argList ;
   public final SparqlParser.functionCall_return functionCall() throws RecognitionException {
      final SparqlParser.functionCall_return retval = new SparqlParser.functionCall_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.iriRef_return iriRef87 = null;

      SparqlParser.argList_return argList88 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "functionCall");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(189, 1);

         try {
            // Sparql.g:190:5: ( iriRef argList )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(190, 7);
            this.pushFollow(SparqlParser.FOLLOW_iriRef_in_functionCall736);
            iriRef87 = this.iriRef();
            this.state._fsp--;
            this.adaptor.addChild(root_0, iriRef87.getTree());
            this.dbg.location(190, 14);
            this.pushFollow(SparqlParser.FOLLOW_argList_in_functionCall738);
            argList88 = this.argList();
            this.state._fsp--;
            this.adaptor.addChild(root_0, argList88.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(191, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "functionCall");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "functionCall"

   public static class argList_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "argList"
   // Sparql.g:193:1: argList : ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
   // expression )* CLOSE_BRACE ) ;
   public final SparqlParser.argList_return argList() throws RecognitionException {
      final SparqlParser.argList_return retval = new SparqlParser.argList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE89 = null;
      Token CLOSE_BRACE90 = null;
      Token OPEN_BRACE91 = null;
      Token COMMA93 = null;
      Token CLOSE_BRACE95 = null;
      SparqlParser.expression_return expression92 = null;

      SparqlParser.expression_return expression94 = null;

      Object OPEN_BRACE89_tree = null;
      Object CLOSE_BRACE90_tree = null;
      Object OPEN_BRACE91_tree = null;
      Object COMMA93_tree = null;
      Object CLOSE_BRACE95_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "argList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(193, 1);

         try {
            // Sparql.g:194:5: ( ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
            // expression )* CLOSE_BRACE ) )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(194, 7);
            // Sparql.g:194:7: ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
            // expression )* CLOSE_BRACE )
            int alt35 = 2;
            try {
               this.dbg.enterSubRule(35);
               try {
                  this.dbg.enterDecision(35);

                  final int LA35_0 = this.input.LA(1);

                  if (LA35_0 == SparqlParser.OPEN_BRACE) {
                     final int LA35_1 = this.input.LA(2);

                     if (LA35_1 == SparqlParser.CLOSE_BRACE) {
                        alt35 = 1;
                     } else if (LA35_1 == SparqlParser.IRI_REF
                           || LA35_1 == SparqlParser.PNAME_NS
                           || LA35_1 == SparqlParser.INTEGER
                           || LA35_1 == SparqlParser.OPEN_BRACE
                           || LA35_1 >= SparqlParser.VAR1 && LA35_1 <= SparqlParser.VAR2
                           || LA35_1 >= SparqlParser.PLUS && LA35_1 <= SparqlParser.MINUS
                           || LA35_1 >= SparqlParser.NOT && LA35_1 <= SparqlParser.REGEX
                           || LA35_1 >= SparqlParser.DECIMAL
                           && LA35_1 <= SparqlParser.PNAME_LN) {
                        alt35 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 35,
                              1, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 35, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
                  }
               } finally {
                  this.dbg.exitDecision(35);
               }

               switch (alt35) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:194:9: OPEN_BRACE CLOSE_BRACE
                     {
                        this.dbg.location(194, 9);
                        OPEN_BRACE89 = (Token) this.match(this.input,
                              SparqlParser.OPEN_BRACE,
                              SparqlParser.FOLLOW_OPEN_BRACE_in_argList757);
                        OPEN_BRACE89_tree = this.adaptor.create(OPEN_BRACE89);
                        this.adaptor.addChild(root_0, OPEN_BRACE89_tree);

                        this.dbg.location(194, 20);
                        CLOSE_BRACE90 = (Token) this.match(this.input,
                              SparqlParser.CLOSE_BRACE,
                              SparqlParser.FOLLOW_CLOSE_BRACE_in_argList759);
                        CLOSE_BRACE90_tree = this.adaptor.create(CLOSE_BRACE90);
                        this.adaptor.addChild(root_0, CLOSE_BRACE90_tree);

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:194:34: OPEN_BRACE expression ( COMMA expression )*
                     // CLOSE_BRACE
                     {
                        this.dbg.location(194, 34);
                        OPEN_BRACE91 = (Token) this.match(this.input,
                              SparqlParser.OPEN_BRACE,
                              SparqlParser.FOLLOW_OPEN_BRACE_in_argList763);
                        OPEN_BRACE91_tree = this.adaptor.create(OPEN_BRACE91);
                        this.adaptor.addChild(root_0, OPEN_BRACE91_tree);

                        this.dbg.location(194, 45);
                        this.pushFollow(SparqlParser.FOLLOW_expression_in_argList765);
                        expression92 = this.expression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, expression92.getTree());
                        this.dbg.location(194, 56);
                        // Sparql.g:194:56: ( COMMA expression )*
                        try {
                           this.dbg.enterSubRule(34);

                           loop34: do {
                              int alt34 = 2;
                              try {
                                 this.dbg.enterDecision(34);

                                 final int LA34_0 = this.input.LA(1);

                                 if (LA34_0 == SparqlParser.COMMA) {
                                    alt34 = 1;
                                 }

                              } finally {
                                 this.dbg.exitDecision(34);
                              }

                              switch (alt34) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:194:58: COMMA expression
                                    {
                                       this.dbg.location(194, 58);
                                       COMMA93 = (Token) this.match(this.input,
                                             SparqlParser.COMMA,
                                             SparqlParser.FOLLOW_COMMA_in_argList769);
                                       COMMA93_tree = this.adaptor.create(COMMA93);
                                       this.adaptor.addChild(root_0, COMMA93_tree);

                                       this.dbg.location(194, 64);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_expression_in_argList771);
                                       expression94 = this.expression();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, expression94.getTree());

                                    }
                                    break;

                                 default:
                                    break loop34;
                              }
                           } while (true);
                        } finally {
                           this.dbg.exitSubRule(34);
                        }

                        this.dbg.location(194, 78);
                        CLOSE_BRACE95 = (Token) this.match(this.input,
                              SparqlParser.CLOSE_BRACE,
                              SparqlParser.FOLLOW_CLOSE_BRACE_in_argList776);
                        CLOSE_BRACE95_tree = this.adaptor.create(CLOSE_BRACE95);
                        this.adaptor.addChild(root_0, CLOSE_BRACE95_tree);

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(35);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(195, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "argList");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "argList"

   public static class constructTemplate_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructTemplate"
   // Sparql.g:197:1: constructTemplate : OPEN_CURLY_BRACE ( constructTriples )?
   // CLOSE_CURLY_BRACE ;
   public final SparqlParser.constructTemplate_return constructTemplate()
         throws RecognitionException {
      final SparqlParser.constructTemplate_return retval = new SparqlParser.constructTemplate_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_CURLY_BRACE96 = null;
      Token CLOSE_CURLY_BRACE98 = null;
      SparqlParser.constructTriples_return constructTriples97 = null;

      Object OPEN_CURLY_BRACE96_tree = null;
      Object CLOSE_CURLY_BRACE98_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructTemplate");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(197, 1);

         try {
            // Sparql.g:198:5: ( OPEN_CURLY_BRACE ( constructTriples )? CLOSE_CURLY_BRACE )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(198, 7);
            OPEN_CURLY_BRACE96 = (Token) this.match(this.input,
                  SparqlParser.OPEN_CURLY_BRACE,
                  SparqlParser.FOLLOW_OPEN_CURLY_BRACE_in_constructTemplate795);
            OPEN_CURLY_BRACE96_tree = this.adaptor.create(OPEN_CURLY_BRACE96);
            this.adaptor.addChild(root_0, OPEN_CURLY_BRACE96_tree);
            this.dbg.location(198, 24);
            // Sparql.g:198:24: ( constructTriples )?
            int alt36 = 2;
            try {
               this.dbg.enterSubRule(36);
               try {
                  this.dbg.enterDecision(36);

                  final int LA36_0 = this.input.LA(1);

                  if (LA36_0 == SparqlParser.IRI_REF || LA36_0 == SparqlParser.PNAME_NS
                        || LA36_0 == SparqlParser.INTEGER
                        || LA36_0 == SparqlParser.OPEN_BRACE
                        || LA36_0 == SparqlParser.OPEN_SQUARE_BRACE
                        || LA36_0 >= SparqlParser.VAR1 && LA36_0 <= SparqlParser.VAR2
                        || LA36_0 >= SparqlParser.DECIMAL
                        && LA36_0 <= SparqlParser.BLANK_NODE_LABEL) {
                     alt36 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(36);
               }

               switch (alt36) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:198:24: constructTriples
                     {
                        this.dbg.location(198, 24);
                        this
                              .pushFollow(SparqlParser.FOLLOW_constructTriples_in_constructTemplate797);
                        constructTriples97 = this.constructTriples();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, constructTriples97.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(36);
            }
            this.dbg.location(198, 42);
            CLOSE_CURLY_BRACE98 = (Token) this.match(this.input,
                  SparqlParser.CLOSE_CURLY_BRACE,
                  SparqlParser.FOLLOW_CLOSE_CURLY_BRACE_in_constructTemplate800);
            CLOSE_CURLY_BRACE98_tree = this.adaptor.create(CLOSE_CURLY_BRACE98);
            this.adaptor.addChild(root_0, CLOSE_CURLY_BRACE98_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(199, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "constructTemplate");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "constructTemplate"

   public static class constructTriples_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructTriples"
   // Sparql.g:201:1: constructTriples : triplesSameSubject ( DOT ( constructTriples )? )? ;
   public final SparqlParser.constructTriples_return constructTriples()
         throws RecognitionException {
      final SparqlParser.constructTriples_return retval = new SparqlParser.constructTriples_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DOT100 = null;
      SparqlParser.triplesSameSubject_return triplesSameSubject99 = null;

      SparqlParser.constructTriples_return constructTriples101 = null;

      Object DOT100_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructTriples");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(201, 1);

         try {
            // Sparql.g:202:5: ( triplesSameSubject ( DOT ( constructTriples )? )? )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(202, 7);
            this.pushFollow(SparqlParser.FOLLOW_triplesSameSubject_in_constructTriples817);
            triplesSameSubject99 = this.triplesSameSubject();
            this.state._fsp--;
            this.adaptor.addChild(root_0, triplesSameSubject99.getTree());
            this.dbg.location(202, 26);
            // Sparql.g:202:26: ( DOT ( constructTriples )? )?
            int alt38 = 2;
            try {
               this.dbg.enterSubRule(38);
               try {
                  this.dbg.enterDecision(38);

                  final int LA38_0 = this.input.LA(1);

                  if (LA38_0 == SparqlParser.DOT) {
                     alt38 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(38);
               }

               switch (alt38) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:202:28: DOT ( constructTriples )?
                     {
                        this.dbg.location(202, 28);
                        DOT100 = (Token) this.match(this.input, SparqlParser.DOT,
                              SparqlParser.FOLLOW_DOT_in_constructTriples821);
                        DOT100_tree = this.adaptor.create(DOT100);
                        this.adaptor.addChild(root_0, DOT100_tree);

                        this.dbg.location(202, 32);
                        // Sparql.g:202:32: ( constructTriples )?
                        int alt37 = 2;
                        try {
                           this.dbg.enterSubRule(37);
                           try {
                              this.dbg.enterDecision(37);

                              final int LA37_0 = this.input.LA(1);

                              if (LA37_0 == SparqlParser.IRI_REF
                                    || LA37_0 == SparqlParser.PNAME_NS
                                    || LA37_0 == SparqlParser.INTEGER
                                    || LA37_0 == SparqlParser.OPEN_BRACE
                                    || LA37_0 == SparqlParser.OPEN_SQUARE_BRACE
                                    || LA37_0 >= SparqlParser.VAR1
                                    && LA37_0 <= SparqlParser.VAR2
                                    || LA37_0 >= SparqlParser.DECIMAL
                                    && LA37_0 <= SparqlParser.BLANK_NODE_LABEL) {
                                 alt37 = 1;
                              }
                           } finally {
                              this.dbg.exitDecision(37);
                           }

                           switch (alt37) {
                              case 1:
                                 this.dbg.enterAlt(1);

                                 // Sparql.g:202:32: constructTriples
                                 {
                                    this.dbg.location(202, 32);
                                    this
                                          .pushFollow(SparqlParser.FOLLOW_constructTriples_in_constructTriples823);
                                    constructTriples101 = this.constructTriples();

                                    this.state._fsp--;

                                    this.adaptor.addChild(root_0, constructTriples101
                                          .getTree());

                                 }
                                 break;

                           }
                        } finally {
                           this.dbg.exitSubRule(37);
                        }

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(38);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(203, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "constructTriples");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "constructTriples"

   public static class triplesSameSubject_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesSameSubject"
   // Sparql.g:205:1: triplesSameSubject : ( varOrTerm propertyListNotEmpty | triplesNode
   // propertyList );
   public final SparqlParser.triplesSameSubject_return triplesSameSubject()
         throws RecognitionException {
      final SparqlParser.triplesSameSubject_return retval = new SparqlParser.triplesSameSubject_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.varOrTerm_return varOrTerm102 = null;

      SparqlParser.propertyListNotEmpty_return propertyListNotEmpty103 = null;

      SparqlParser.triplesNode_return triplesNode104 = null;

      SparqlParser.propertyList_return propertyList105 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesSameSubject");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(205, 1);

         try {
            // Sparql.g:206:5: ( varOrTerm propertyListNotEmpty | triplesNode propertyList )
            int alt39 = 2;
            try {
               this.dbg.enterDecision(39);

               switch (this.input.LA(1)) {
                  case IRI_REF:
                  case PNAME_NS:
                  case INTEGER:
                  case VAR1:
                  case VAR2:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                  case TRUE:
                  case FALSE:
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2:
                  case PNAME_LN:
                  case BLANK_NODE_LABEL:
                     alt39 = 1;
                     break;
                  case OPEN_SQUARE_BRACE:
                     final int LA39_2 = this.input.LA(2);
                     if (LA39_2 == SparqlParser.CLOSE_SQUARE_BRACE) {
                        alt39 = 1;
                     } else if (LA39_2 == SparqlParser.IRI_REF
                           || LA39_2 == SparqlParser.PNAME_NS || LA39_2 == SparqlParser.A
                           || LA39_2 >= SparqlParser.VAR1 && LA39_2 <= SparqlParser.VAR2
                           || LA39_2 == SparqlParser.PNAME_LN) {
                        alt39 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 39,
                              2, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                     break;
                  case OPEN_BRACE:
                     final int LA39_3 = this.input.LA(2);
                     if (LA39_3 == SparqlParser.CLOSE_BRACE) {
                        alt39 = 1;
                     } else if (LA39_3 == SparqlParser.IRI_REF
                           || LA39_3 == SparqlParser.PNAME_NS
                           || LA39_3 == SparqlParser.INTEGER
                           || LA39_3 == SparqlParser.OPEN_BRACE
                           || LA39_3 == SparqlParser.OPEN_SQUARE_BRACE
                           || LA39_3 >= SparqlParser.VAR1 && LA39_3 <= SparqlParser.VAR2
                           || LA39_3 >= SparqlParser.DECIMAL
                           && LA39_3 <= SparqlParser.BLANK_NODE_LABEL) {
                        alt39 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 39,
                              3, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 39, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(39);
            }

            switch (alt39) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(206, 7);
                  this.pushFollow(SparqlParser.FOLLOW_varOrTerm_in_triplesSameSubject844);
                  varOrTerm102 = this.varOrTerm();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, varOrTerm102.getTree());
                  this.dbg.location(206, 17);
                  this
                        .pushFollow(SparqlParser.FOLLOW_propertyListNotEmpty_in_triplesSameSubject846);
                  propertyListNotEmpty103 = this.propertyListNotEmpty();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, propertyListNotEmpty103.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(206, 40);
                  this.pushFollow(SparqlParser.FOLLOW_triplesNode_in_triplesSameSubject850);
                  triplesNode104 = this.triplesNode();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, triplesNode104.getTree());
                  this.dbg.location(206, 52);
                  this.pushFollow(SparqlParser.FOLLOW_propertyList_in_triplesSameSubject852);
                  propertyList105 = this.propertyList();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, propertyList105.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(207, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "triplesSameSubject");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "triplesSameSubject"

   public static class propertyListNotEmpty_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "propertyListNotEmpty"
   // Sparql.g:209:1: propertyListNotEmpty : verb objectList ( SEMICOLON ( verb objectList )?
   // )* ;
   public final SparqlParser.propertyListNotEmpty_return propertyListNotEmpty()
         throws RecognitionException {
      final SparqlParser.propertyListNotEmpty_return retval = new SparqlParser.propertyListNotEmpty_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SEMICOLON108 = null;
      SparqlParser.verb_return verb106 = null;

      SparqlParser.objectList_return objectList107 = null;

      SparqlParser.verb_return verb109 = null;

      SparqlParser.objectList_return objectList110 = null;

      Object SEMICOLON108_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "propertyListNotEmpty");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(209, 1);

         try {
            // Sparql.g:210:5: ( verb objectList ( SEMICOLON ( verb objectList )? )* )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(210, 7);
            this.pushFollow(SparqlParser.FOLLOW_verb_in_propertyListNotEmpty869);
            verb106 = this.verb();
            this.state._fsp--;
            this.adaptor.addChild(root_0, verb106.getTree());
            this.dbg.location(210, 12);
            this.pushFollow(SparqlParser.FOLLOW_objectList_in_propertyListNotEmpty871);
            objectList107 = this.objectList();
            this.state._fsp--;
            this.adaptor.addChild(root_0, objectList107.getTree());
            this.dbg.location(210, 23);
            // Sparql.g:210:23: ( SEMICOLON ( verb objectList )? )*
            try {
               this.dbg.enterSubRule(41);

               loop41: do {
                  int alt41 = 2;
                  try {
                     this.dbg.enterDecision(41);

                     final int LA41_0 = this.input.LA(1);

                     if (LA41_0 == SparqlParser.SEMICOLON) {
                        alt41 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(41);
                  }

                  switch (alt41) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:210:25: SEMICOLON ( verb objectList )?
                        {
                           this.dbg.location(210, 25);
                           SEMICOLON108 = (Token) this.match(this.input,
                                 SparqlParser.SEMICOLON,
                                 SparqlParser.FOLLOW_SEMICOLON_in_propertyListNotEmpty875);
                           SEMICOLON108_tree = this.adaptor.create(SEMICOLON108);
                           this.adaptor.addChild(root_0, SEMICOLON108_tree);

                           this.dbg.location(210, 35);
                           // Sparql.g:210:35: ( verb objectList )?
                           int alt40 = 2;
                           try {
                              this.dbg.enterSubRule(40);
                              try {
                                 this.dbg.enterDecision(40);

                                 final int LA40_0 = this.input.LA(1);

                                 if (LA40_0 == SparqlParser.IRI_REF
                                       || LA40_0 == SparqlParser.PNAME_NS
                                       || LA40_0 == SparqlParser.A
                                       || LA40_0 >= SparqlParser.VAR1
                                       && LA40_0 <= SparqlParser.VAR2
                                       || LA40_0 == SparqlParser.PNAME_LN) {
                                    alt40 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(40);
                              }

                              switch (alt40) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // Sparql.g:210:37: verb objectList
                                    {
                                       this.dbg.location(210, 37);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_verb_in_propertyListNotEmpty879);
                                       verb109 = this.verb();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, verb109.getTree());
                                       this.dbg.location(210, 42);
                                       this
                                             .pushFollow(SparqlParser.FOLLOW_objectList_in_propertyListNotEmpty881);
                                       objectList110 = this.objectList();

                                       this.state._fsp--;

                                       this.adaptor
                                             .addChild(root_0, objectList110.getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(40);
                           }

                        }
                        break;

                     default:
                        break loop41;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(41);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(211, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "propertyListNotEmpty");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "propertyListNotEmpty"

   public static class propertyList_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "propertyList"
   // Sparql.g:213:1: propertyList : ( propertyListNotEmpty )? ;
   public final SparqlParser.propertyList_return propertyList() throws RecognitionException {
      final SparqlParser.propertyList_return retval = new SparqlParser.propertyList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.propertyListNotEmpty_return propertyListNotEmpty111 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "propertyList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(213, 1);

         try {
            // Sparql.g:214:5: ( ( propertyListNotEmpty )? )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(214, 7);
            // Sparql.g:214:7: ( propertyListNotEmpty )?
            int alt42 = 2;
            try {
               this.dbg.enterSubRule(42);
               try {
                  this.dbg.enterDecision(42);

                  final int LA42_0 = this.input.LA(1);

                  if (LA42_0 == SparqlParser.IRI_REF || LA42_0 == SparqlParser.PNAME_NS
                        || LA42_0 == SparqlParser.A || LA42_0 >= SparqlParser.VAR1
                        && LA42_0 <= SparqlParser.VAR2 || LA42_0 == SparqlParser.PNAME_LN) {
                     alt42 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(42);
               }

               switch (alt42) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:214:7: propertyListNotEmpty
                     {
                        this.dbg.location(214, 7);
                        this
                              .pushFollow(SparqlParser.FOLLOW_propertyListNotEmpty_in_propertyList904);
                        propertyListNotEmpty111 = this.propertyListNotEmpty();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, propertyListNotEmpty111.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(42);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(215, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "propertyList");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "propertyList"

   public static class objectList_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "objectList"
   // Sparql.g:217:1: objectList : object ( COMMA object )* ;
   public final SparqlParser.objectList_return objectList() throws RecognitionException {
      final SparqlParser.objectList_return retval = new SparqlParser.objectList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token COMMA113 = null;
      SparqlParser.object_return object112 = null;

      SparqlParser.object_return object114 = null;

      Object COMMA113_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "objectList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(217, 1);

         try {
            // Sparql.g:218:5: ( object ( COMMA object )* )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(218, 7);
            this.pushFollow(SparqlParser.FOLLOW_object_in_objectList922);
            object112 = this.object();
            this.state._fsp--;
            this.adaptor.addChild(root_0, object112.getTree());
            this.dbg.location(218, 14);
            // Sparql.g:218:14: ( COMMA object )*
            try {
               this.dbg.enterSubRule(43);

               loop43: do {
                  int alt43 = 2;
                  try {
                     this.dbg.enterDecision(43);

                     final int LA43_0 = this.input.LA(1);

                     if (LA43_0 == SparqlParser.COMMA) {
                        alt43 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(43);
                  }

                  switch (alt43) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:218:16: COMMA object
                        {
                           this.dbg.location(218, 16);
                           COMMA113 = (Token) this.match(this.input, SparqlParser.COMMA,
                                 SparqlParser.FOLLOW_COMMA_in_objectList926);
                           COMMA113_tree = this.adaptor.create(COMMA113);
                           this.adaptor.addChild(root_0, COMMA113_tree);

                           this.dbg.location(218, 22);
                           this.pushFollow(SparqlParser.FOLLOW_object_in_objectList928);
                           object114 = this.object();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, object114.getTree());

                        }
                        break;

                     default:
                        break loop43;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(43);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(219, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "objectList");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "objectList"

   public static class object_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "object"
   // Sparql.g:221:1: object : graphNode ;
   public final SparqlParser.object_return object() throws RecognitionException {
      final SparqlParser.object_return retval = new SparqlParser.object_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.graphNode_return graphNode115 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "object");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(221, 1);

         try {
            // Sparql.g:222:5: ( graphNode )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(222, 7);
            this.pushFollow(SparqlParser.FOLLOW_graphNode_in_object948);
            graphNode115 = this.graphNode();
            this.state._fsp--;
            this.adaptor.addChild(root_0, graphNode115.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(223, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "object");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "object"

   public static class verb_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "verb"
   // Sparql.g:225:1: verb : ( varOrIRIref | A );
   public final SparqlParser.verb_return verb() throws RecognitionException {
      final SparqlParser.verb_return retval = new SparqlParser.verb_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token A117 = null;
      SparqlParser.varOrIRIref_return varOrIRIref116 = null;

      Object A117_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "verb");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(225, 1);

         try {
            // Sparql.g:226:5: ( varOrIRIref | A )
            int alt44 = 2;
            try {
               this.dbg.enterDecision(44);

               final int LA44_0 = this.input.LA(1);

               if (LA44_0 == SparqlParser.IRI_REF || LA44_0 == SparqlParser.PNAME_NS
                     || LA44_0 >= SparqlParser.VAR1 && LA44_0 <= SparqlParser.VAR2
                     || LA44_0 == SparqlParser.PNAME_LN) {
                  alt44 = 1;
               } else if (LA44_0 == SparqlParser.A) {
                  alt44 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 44, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(44);
            }

            switch (alt44) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(226, 7);
                  this.pushFollow(SparqlParser.FOLLOW_varOrIRIref_in_verb965);
                  varOrIRIref116 = this.varOrIRIref();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, varOrIRIref116.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(227, 7);
                  A117 = (Token) this.match(this.input, SparqlParser.A,
                        SparqlParser.FOLLOW_A_in_verb973);
                  A117_tree = this.adaptor.create(A117);
                  this.adaptor.addChild(root_0, A117_tree);
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(228, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "verb");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "verb"

   public static class triplesNode_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesNode"
   // Sparql.g:230:1: triplesNode : ( collection | blankNodePropertyList );
   public final SparqlParser.triplesNode_return triplesNode() throws RecognitionException {
      final SparqlParser.triplesNode_return retval = new SparqlParser.triplesNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.collection_return collection118 = null;

      SparqlParser.blankNodePropertyList_return blankNodePropertyList119 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(230, 1);

         try {
            // Sparql.g:231:5: ( collection | blankNodePropertyList )
            int alt45 = 2;
            try {
               this.dbg.enterDecision(45);

               final int LA45_0 = this.input.LA(1);

               if (LA45_0 == SparqlParser.OPEN_BRACE) {
                  alt45 = 1;
               } else if (LA45_0 == SparqlParser.OPEN_SQUARE_BRACE) {
                  alt45 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 45, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(45);
            }

            switch (alt45) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(231, 7);
                  this.pushFollow(SparqlParser.FOLLOW_collection_in_triplesNode990);
                  collection118 = this.collection();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, collection118.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(232, 7);
                  this
                        .pushFollow(SparqlParser.FOLLOW_blankNodePropertyList_in_triplesNode998);
                  blankNodePropertyList119 = this.blankNodePropertyList();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, blankNodePropertyList119.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(233, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "triplesNode");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "triplesNode"

   public static class blankNodePropertyList_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "blankNodePropertyList"
   // Sparql.g:235:1: blankNodePropertyList : OPEN_SQUARE_BRACE propertyListNotEmpty
   // CLOSE_SQUARE_BRACE ;
   public final SparqlParser.blankNodePropertyList_return blankNodePropertyList()
         throws RecognitionException {
      final SparqlParser.blankNodePropertyList_return retval = new SparqlParser.blankNodePropertyList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_SQUARE_BRACE120 = null;
      Token CLOSE_SQUARE_BRACE122 = null;
      SparqlParser.propertyListNotEmpty_return propertyListNotEmpty121 = null;

      Object OPEN_SQUARE_BRACE120_tree = null;
      Object CLOSE_SQUARE_BRACE122_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "blankNodePropertyList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(235, 1);

         try {
            // Sparql.g:236:5: ( OPEN_SQUARE_BRACE propertyListNotEmpty CLOSE_SQUARE_BRACE )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(236, 7);
            OPEN_SQUARE_BRACE120 = (Token) this.match(this.input,
                  SparqlParser.OPEN_SQUARE_BRACE,
                  SparqlParser.FOLLOW_OPEN_SQUARE_BRACE_in_blankNodePropertyList1015);
            OPEN_SQUARE_BRACE120_tree = this.adaptor.create(OPEN_SQUARE_BRACE120);
            this.adaptor.addChild(root_0, OPEN_SQUARE_BRACE120_tree);
            this.dbg.location(236, 25);
            this
                  .pushFollow(SparqlParser.FOLLOW_propertyListNotEmpty_in_blankNodePropertyList1017);
            propertyListNotEmpty121 = this.propertyListNotEmpty();
            this.state._fsp--;
            this.adaptor.addChild(root_0, propertyListNotEmpty121.getTree());
            this.dbg.location(236, 46);
            CLOSE_SQUARE_BRACE122 = (Token) this.match(this.input,
                  SparqlParser.CLOSE_SQUARE_BRACE,
                  SparqlParser.FOLLOW_CLOSE_SQUARE_BRACE_in_blankNodePropertyList1019);
            CLOSE_SQUARE_BRACE122_tree = this.adaptor.create(CLOSE_SQUARE_BRACE122);
            this.adaptor.addChild(root_0, CLOSE_SQUARE_BRACE122_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(237, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "blankNodePropertyList");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "blankNodePropertyList"

   public static class collection_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "collection"
   // Sparql.g:239:1: collection : OPEN_BRACE ( graphNode )+ CLOSE_BRACE ;
   public final SparqlParser.collection_return collection() throws RecognitionException {
      final SparqlParser.collection_return retval = new SparqlParser.collection_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE123 = null;
      Token CLOSE_BRACE125 = null;
      SparqlParser.graphNode_return graphNode124 = null;

      Object OPEN_BRACE123_tree = null;
      Object CLOSE_BRACE125_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "collection");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(239, 1);

         try {
            // Sparql.g:240:5: ( OPEN_BRACE ( graphNode )+ CLOSE_BRACE )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(240, 7);
            OPEN_BRACE123 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                  SparqlParser.FOLLOW_OPEN_BRACE_in_collection1036);
            OPEN_BRACE123_tree = this.adaptor.create(OPEN_BRACE123);
            this.adaptor.addChild(root_0, OPEN_BRACE123_tree);
            this.dbg.location(240, 18);
            // Sparql.g:240:18: ( graphNode )+
            int cnt46 = 0;
            try {
               this.dbg.enterSubRule(46);

               loop46: do {
                  int alt46 = 2;
                  try {
                     this.dbg.enterDecision(46);

                     final int LA46_0 = this.input.LA(1);

                     if (LA46_0 == SparqlParser.IRI_REF || LA46_0 == SparqlParser.PNAME_NS
                           || LA46_0 == SparqlParser.INTEGER
                           || LA46_0 == SparqlParser.OPEN_BRACE
                           || LA46_0 == SparqlParser.OPEN_SQUARE_BRACE
                           || LA46_0 >= SparqlParser.VAR1 && LA46_0 <= SparqlParser.VAR2
                           || LA46_0 >= SparqlParser.DECIMAL
                           && LA46_0 <= SparqlParser.BLANK_NODE_LABEL) {
                        alt46 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(46);
                  }

                  switch (alt46) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:240:18: graphNode
                        {
                           this.dbg.location(240, 18);
                           this.pushFollow(SparqlParser.FOLLOW_graphNode_in_collection1038);
                           graphNode124 = this.graphNode();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, graphNode124.getTree());

                        }
                        break;

                     default:
                        if (cnt46 >= 1) {
                           break loop46;
                        }
                        final EarlyExitException eee = new EarlyExitException(46, this.input);
                        this.dbg.recognitionException(eee);

                        throw eee;
                  }
                  cnt46++;
               } while (true);
            } finally {
               this.dbg.exitSubRule(46);
            }
            this.dbg.location(240, 29);
            CLOSE_BRACE125 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                  SparqlParser.FOLLOW_CLOSE_BRACE_in_collection1041);
            CLOSE_BRACE125_tree = this.adaptor.create(CLOSE_BRACE125);
            this.adaptor.addChild(root_0, CLOSE_BRACE125_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(241, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "collection");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "collection"

   public static class graphNode_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphNode"
   // Sparql.g:243:1: graphNode : ( varOrTerm | triplesNode );
   public final SparqlParser.graphNode_return graphNode() throws RecognitionException {
      final SparqlParser.graphNode_return retval = new SparqlParser.graphNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.varOrTerm_return varOrTerm126 = null;

      SparqlParser.triplesNode_return triplesNode127 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(243, 1);

         try {
            // Sparql.g:244:5: ( varOrTerm | triplesNode )
            int alt47 = 2;
            try {
               this.dbg.enterDecision(47);

               switch (this.input.LA(1)) {
                  case IRI_REF:
                  case PNAME_NS:
                  case INTEGER:
                  case VAR1:
                  case VAR2:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                  case TRUE:
                  case FALSE:
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2:
                  case PNAME_LN:
                  case BLANK_NODE_LABEL:
                     alt47 = 1;
                     break;
                  case OPEN_SQUARE_BRACE:
                     final int LA47_2 = this.input.LA(2);
                     if (LA47_2 == SparqlParser.CLOSE_SQUARE_BRACE) {
                        alt47 = 1;
                     } else if (LA47_2 == SparqlParser.IRI_REF
                           || LA47_2 == SparqlParser.PNAME_NS || LA47_2 == SparqlParser.A
                           || LA47_2 >= SparqlParser.VAR1 && LA47_2 <= SparqlParser.VAR2
                           || LA47_2 == SparqlParser.PNAME_LN) {
                        alt47 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 47,
                              2, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                     break;
                  case OPEN_BRACE:
                     final int LA47_3 = this.input.LA(2);
                     if (LA47_3 == SparqlParser.CLOSE_BRACE) {
                        alt47 = 1;
                     } else if (LA47_3 == SparqlParser.IRI_REF
                           || LA47_3 == SparqlParser.PNAME_NS
                           || LA47_3 == SparqlParser.INTEGER
                           || LA47_3 == SparqlParser.OPEN_BRACE
                           || LA47_3 == SparqlParser.OPEN_SQUARE_BRACE
                           || LA47_3 >= SparqlParser.VAR1 && LA47_3 <= SparqlParser.VAR2
                           || LA47_3 >= SparqlParser.DECIMAL
                           && LA47_3 <= SparqlParser.BLANK_NODE_LABEL) {
                        alt47 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 47,
                              3, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 47, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(47);
            }

            switch (alt47) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(244, 7);
                  this.pushFollow(SparqlParser.FOLLOW_varOrTerm_in_graphNode1058);
                  varOrTerm126 = this.varOrTerm();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, varOrTerm126.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(244, 19);
                  this.pushFollow(SparqlParser.FOLLOW_triplesNode_in_graphNode1062);
                  triplesNode127 = this.triplesNode();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, triplesNode127.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(245, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "graphNode");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "graphNode"

   public static class varOrTerm_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "varOrTerm"
   // Sparql.g:247:1: varOrTerm : ( var | graphTerm );
   public final SparqlParser.varOrTerm_return varOrTerm() throws RecognitionException {
      final SparqlParser.varOrTerm_return retval = new SparqlParser.varOrTerm_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.var_return var128 = null;

      SparqlParser.graphTerm_return graphTerm129 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "varOrTerm");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(247, 1);

         try {
            // Sparql.g:248:5: ( var | graphTerm )
            int alt48 = 2;
            try {
               this.dbg.enterDecision(48);

               final int LA48_0 = this.input.LA(1);

               if (LA48_0 >= SparqlParser.VAR1 && LA48_0 <= SparqlParser.VAR2) {
                  alt48 = 1;
               } else if (LA48_0 == SparqlParser.IRI_REF || LA48_0 == SparqlParser.PNAME_NS
                     || LA48_0 == SparqlParser.INTEGER || LA48_0 == SparqlParser.OPEN_BRACE
                     || LA48_0 == SparqlParser.OPEN_SQUARE_BRACE
                     || LA48_0 >= SparqlParser.DECIMAL
                     && LA48_0 <= SparqlParser.BLANK_NODE_LABEL) {
                  alt48 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 48, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(48);
            }

            switch (alt48) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(248, 7);
                  this.pushFollow(SparqlParser.FOLLOW_var_in_varOrTerm1079);
                  var128 = this.var();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, var128.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(249, 7);
                  this.pushFollow(SparqlParser.FOLLOW_graphTerm_in_varOrTerm1087);
                  graphTerm129 = this.graphTerm();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, graphTerm129.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(250, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "varOrTerm");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "varOrTerm"

   public static class varOrIRIref_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "varOrIRIref"
   // Sparql.g:252:1: varOrIRIref : ( var | iriRef );
   public final SparqlParser.varOrIRIref_return varOrIRIref() throws RecognitionException {
      final SparqlParser.varOrIRIref_return retval = new SparqlParser.varOrIRIref_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.var_return var130 = null;

      SparqlParser.iriRef_return iriRef131 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "varOrIRIref");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(252, 1);

         try {
            // Sparql.g:253:5: ( var | iriRef )
            int alt49 = 2;
            try {
               this.dbg.enterDecision(49);

               final int LA49_0 = this.input.LA(1);

               if (LA49_0 >= SparqlParser.VAR1 && LA49_0 <= SparqlParser.VAR2) {
                  alt49 = 1;
               } else if (LA49_0 == SparqlParser.IRI_REF || LA49_0 == SparqlParser.PNAME_NS
                     || LA49_0 == SparqlParser.PNAME_LN) {
                  alt49 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 49, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(49);
            }

            switch (alt49) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(253, 7);
                  this.pushFollow(SparqlParser.FOLLOW_var_in_varOrIRIref1104);
                  var130 = this.var();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, var130.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(253, 13);
                  this.pushFollow(SparqlParser.FOLLOW_iriRef_in_varOrIRIref1108);
                  iriRef131 = this.iriRef();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, iriRef131.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(254, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "varOrIRIref");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "varOrIRIref"

   public static class var_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "var"
   // Sparql.g:256:1: var : ( VAR1 | VAR2 );
   public final SparqlParser.var_return var() throws RecognitionException {
      final SparqlParser.var_return retval = new SparqlParser.var_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set132 = null;

      final Object set132_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "var");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(256, 1);

         try {
            // Sparql.g:257:5: ( VAR1 | VAR2 )
            this.dbg.enterAlt(1);

            root_0 = this.adaptor.nil();
            this.dbg.location(257, 5);
            set132 = this.input.LT(1);
            if (this.input.LA(1) >= SparqlParser.VAR1
                  && this.input.LA(1) <= SparqlParser.VAR2) {
               this.input.consume();
               this.adaptor.addChild(root_0, this.adaptor.create(set132));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(259, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "var");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "var"

   public static class graphTerm_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphTerm"
   // Sparql.g:261:1: graphTerm : ( iriRef | rdfLiteral | numericLiteral | booleanLiteral |
   // blankNode | OPEN_BRACE CLOSE_BRACE );
   public final SparqlParser.graphTerm_return graphTerm() throws RecognitionException {
      final SparqlParser.graphTerm_return retval = new SparqlParser.graphTerm_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE138 = null;
      Token CLOSE_BRACE139 = null;
      SparqlParser.iriRef_return iriRef133 = null;

      SparqlParser.rdfLiteral_return rdfLiteral134 = null;

      SparqlParser.numericLiteral_return numericLiteral135 = null;

      SparqlParser.booleanLiteral_return booleanLiteral136 = null;

      SparqlParser.blankNode_return blankNode137 = null;

      Object OPEN_BRACE138_tree = null;
      Object CLOSE_BRACE139_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphTerm");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(261, 1);

         try {
            // Sparql.g:262:5: ( iriRef | rdfLiteral | numericLiteral | booleanLiteral |
            // blankNode | OPEN_BRACE CLOSE_BRACE )
            int alt50 = 6;
            try {
               this.dbg.enterDecision(50);

               switch (this.input.LA(1)) {
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN:
                     alt50 = 1;
                     break;
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2:
                     alt50 = 2;
                     break;
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                     alt50 = 3;
                     break;
                  case TRUE:
                  case FALSE:
                     alt50 = 4;
                     break;
                  case OPEN_SQUARE_BRACE:
                  case BLANK_NODE_LABEL:
                     alt50 = 5;
                     break;
                  case OPEN_BRACE:
                     alt50 = 6;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 50, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(50);
            }

            switch (alt50) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = this.adaptor.nil();
                  this.dbg.location(262, 7);
                  this.pushFollow(SparqlParser.FOLLOW_iriRef_in_graphTerm1150);
                  iriRef133 = this.iriRef();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, iriRef133.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(263, 7);
                  this.pushFollow(SparqlParser.FOLLOW_rdfLiteral_in_graphTerm1158);
                  rdfLiteral134 = this.rdfLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, rdfLiteral134.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(264, 7);
                  this.pushFollow(SparqlParser.FOLLOW_numericLiteral_in_graphTerm1166);
                  numericLiteral135 = this.numericLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, numericLiteral135.getTree());
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(265, 7);
                  this.pushFollow(SparqlParser.FOLLOW_booleanLiteral_in_graphTerm1174);
                  booleanLiteral136 = this.booleanLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, booleanLiteral136.getTree());
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(266, 7);
                  this.pushFollow(SparqlParser.FOLLOW_blankNode_in_graphTerm1182);
                  blankNode137 = this.blankNode();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, blankNode137.getTree());
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(267, 7);
                  OPEN_BRACE138 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_graphTerm1190);
                  OPEN_BRACE138_tree = (Object) this.adaptor.create(OPEN_BRACE138);
                  this.adaptor.addChild(root_0, OPEN_BRACE138_tree);
                  this.dbg.location(267, 18);
                  CLOSE_BRACE139 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_graphTerm1192);
                  CLOSE_BRACE139_tree = (Object) this.adaptor.create(CLOSE_BRACE139);
                  this.adaptor.addChild(root_0, CLOSE_BRACE139_tree);
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(268, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "graphTerm");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "graphTerm"

   public static class expression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "expression"
   // Sparql.g:270:1: expression : conditionalOrExpression ;
   public final SparqlParser.expression_return expression() throws RecognitionException {
      final SparqlParser.expression_return retval = new SparqlParser.expression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.conditionalOrExpression_return conditionalOrExpression140 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "expression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(270, 1);

         try {
            // Sparql.g:271:5: ( conditionalOrExpression )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(271, 7);
            this.pushFollow(SparqlParser.FOLLOW_conditionalOrExpression_in_expression1209);
            conditionalOrExpression140 = this.conditionalOrExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, conditionalOrExpression140.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(272, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "expression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "expression"

   public static class conditionalOrExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "conditionalOrExpression"
   // Sparql.g:274:1: conditionalOrExpression : conditionalAndExpression ( OR
   // conditionalAndExpression )* ;
   public final SparqlParser.conditionalOrExpression_return conditionalOrExpression()
         throws RecognitionException {
      final SparqlParser.conditionalOrExpression_return retval = new SparqlParser.conditionalOrExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OR142 = null;
      SparqlParser.conditionalAndExpression_return conditionalAndExpression141 = null;

      SparqlParser.conditionalAndExpression_return conditionalAndExpression143 = null;

      Object OR142_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "conditionalOrExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(274, 1);

         try {
            // Sparql.g:275:5: ( conditionalAndExpression ( OR conditionalAndExpression )* )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(275, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_conditionalAndExpression_in_conditionalOrExpression1226);
            conditionalAndExpression141 = this.conditionalAndExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, conditionalAndExpression141.getTree());
            this.dbg.location(275, 32);
            // Sparql.g:275:32: ( OR conditionalAndExpression )*
            try {
               this.dbg.enterSubRule(51);

               loop51: do {
                  int alt51 = 2;
                  try {
                     this.dbg.enterDecision(51);

                     final int LA51_0 = this.input.LA(1);

                     if (LA51_0 == SparqlParser.OR) {
                        alt51 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(51);
                  }

                  switch (alt51) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:275:34: OR conditionalAndExpression
                        {
                           this.dbg.location(275, 34);
                           OR142 = (Token) this.match(this.input, SparqlParser.OR,
                                 SparqlParser.FOLLOW_OR_in_conditionalOrExpression1230);
                           OR142_tree = (Object) this.adaptor.create(OR142);
                           this.adaptor.addChild(root_0, OR142_tree);

                           this.dbg.location(275, 37);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_conditionalAndExpression_in_conditionalOrExpression1232);
                           conditionalAndExpression143 = this.conditionalAndExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, conditionalAndExpression143
                                 .getTree());

                        }
                        break;

                     default:
                        break loop51;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(51);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(276, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "conditionalOrExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "conditionalOrExpression"

   public static class conditionalAndExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "conditionalAndExpression"
   // Sparql.g:278:1: conditionalAndExpression : valueLogical ( AND valueLogical )* ;
   public final SparqlParser.conditionalAndExpression_return conditionalAndExpression()
         throws RecognitionException {
      final SparqlParser.conditionalAndExpression_return retval = new SparqlParser.conditionalAndExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token AND145 = null;
      SparqlParser.valueLogical_return valueLogical144 = null;

      SparqlParser.valueLogical_return valueLogical146 = null;

      Object AND145_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "conditionalAndExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(278, 1);

         try {
            // Sparql.g:279:5: ( valueLogical ( AND valueLogical )* )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(279, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_valueLogical_in_conditionalAndExpression1252);
            valueLogical144 = this.valueLogical();
            this.state._fsp--;
            this.adaptor.addChild(root_0, valueLogical144.getTree());
            this.dbg.location(279, 20);
            // Sparql.g:279:20: ( AND valueLogical )*
            try {
               this.dbg.enterSubRule(52);

               loop52: do {
                  int alt52 = 2;
                  try {
                     this.dbg.enterDecision(52);

                     final int LA52_0 = this.input.LA(1);

                     if (LA52_0 == SparqlParser.AND) {
                        alt52 = 1;
                     }

                  } finally {
                     this.dbg.exitDecision(52);
                  }

                  switch (alt52) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:279:22: AND valueLogical
                        {
                           this.dbg.location(279, 22);
                           AND145 = (Token) this.match(this.input, SparqlParser.AND,
                                 SparqlParser.FOLLOW_AND_in_conditionalAndExpression1256);
                           AND145_tree = (Object) this.adaptor.create(AND145);
                           this.adaptor.addChild(root_0, AND145_tree);

                           this.dbg.location(279, 26);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_valueLogical_in_conditionalAndExpression1258);
                           valueLogical146 = this.valueLogical();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, valueLogical146.getTree());

                        }
                        break;

                     default:
                        break loop52;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(52);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(280, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "conditionalAndExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "conditionalAndExpression"

   public static class valueLogical_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "valueLogical"
   // Sparql.g:282:1: valueLogical : relationalExpression ;
   public final SparqlParser.valueLogical_return valueLogical() throws RecognitionException {
      final SparqlParser.valueLogical_return retval = new SparqlParser.valueLogical_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.relationalExpression_return relationalExpression147 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "valueLogical");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(282, 1);

         try {
            // Sparql.g:283:5: ( relationalExpression )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(283, 7);
            this.pushFollow(SparqlParser.FOLLOW_relationalExpression_in_valueLogical1278);
            relationalExpression147 = this.relationalExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, relationalExpression147.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(284, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "valueLogical");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "valueLogical"

   public static class relationalExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "relationalExpression"
   // Sparql.g:286:1: relationalExpression : numericExpression ( EQUAL numericExpression |
   // NOT_EQUAL numericExpression | LESS numericExpression | GREATER numericExpression |
   // LESS_EQUAL numericExpression | GREATER_EQUAL numericExpression )? ;
   public final SparqlParser.relationalExpression_return relationalExpression()
         throws RecognitionException {
      final SparqlParser.relationalExpression_return retval = new SparqlParser.relationalExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EQUAL149 = null;
      Token NOT_EQUAL151 = null;
      Token LESS153 = null;
      Token GREATER155 = null;
      Token LESS_EQUAL157 = null;
      Token GREATER_EQUAL159 = null;
      SparqlParser.numericExpression_return numericExpression148 = null;

      SparqlParser.numericExpression_return numericExpression150 = null;

      SparqlParser.numericExpression_return numericExpression152 = null;

      SparqlParser.numericExpression_return numericExpression154 = null;

      SparqlParser.numericExpression_return numericExpression156 = null;

      SparqlParser.numericExpression_return numericExpression158 = null;

      SparqlParser.numericExpression_return numericExpression160 = null;

      Object EQUAL149_tree = null;
      Object NOT_EQUAL151_tree = null;
      Object LESS153_tree = null;
      Object GREATER155_tree = null;
      Object LESS_EQUAL157_tree = null;
      Object GREATER_EQUAL159_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "relationalExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(286, 1);

         try {
            // Sparql.g:287:5: ( numericExpression ( EQUAL numericExpression | NOT_EQUAL
            // numericExpression | LESS numericExpression | GREATER numericExpression |
            // LESS_EQUAL numericExpression | GREATER_EQUAL numericExpression )? )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(287, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1295);
            numericExpression148 = this.numericExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, numericExpression148.getTree());
            this.dbg.location(287, 25);
            // Sparql.g:287:25: ( EQUAL numericExpression | NOT_EQUAL numericExpression |
            // LESS numericExpression | GREATER numericExpression | LESS_EQUAL
            // numericExpression | GREATER_EQUAL numericExpression )?
            int alt53 = 7;
            try {
               this.dbg.enterSubRule(53);
               try {
                  this.dbg.enterDecision(53);

                  switch (this.input.LA(1)) {
                     case EQUAL: {
                        alt53 = 1;
                     }
                        break;
                     case NOT_EQUAL: {
                        alt53 = 2;
                     }
                        break;
                     case LESS: {
                        alt53 = 3;
                     }
                        break;
                     case GREATER: {
                        alt53 = 4;
                     }
                        break;
                     case LESS_EQUAL: {
                        alt53 = 5;
                     }
                        break;
                     case GREATER_EQUAL: {
                        alt53 = 6;
                     }
                        break;
                  }

               } finally {
                  this.dbg.exitDecision(53);
               }

               switch (alt53) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:287:27: EQUAL numericExpression
                     {
                        this.dbg.location(287, 27);
                        EQUAL149 = (Token) this.match(this.input, SparqlParser.EQUAL,
                              SparqlParser.FOLLOW_EQUAL_in_relationalExpression1299);
                        EQUAL149_tree = (Object) this.adaptor.create(EQUAL149);
                        this.adaptor.addChild(root_0, EQUAL149_tree);

                        this.dbg.location(287, 33);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1301);
                        numericExpression150 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression150.getTree());

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:287:53: NOT_EQUAL numericExpression
                     {
                        this.dbg.location(287, 53);
                        NOT_EQUAL151 = (Token) this.match(this.input,
                              SparqlParser.NOT_EQUAL,
                              SparqlParser.FOLLOW_NOT_EQUAL_in_relationalExpression1305);
                        NOT_EQUAL151_tree = (Object) this.adaptor.create(NOT_EQUAL151);
                        this.adaptor.addChild(root_0, NOT_EQUAL151_tree);

                        this.dbg.location(287, 63);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1307);
                        numericExpression152 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression152.getTree());

                     }
                     break;
                  case 3:
                     this.dbg.enterAlt(3);

                     // Sparql.g:287:83: LESS numericExpression
                     {
                        this.dbg.location(287, 83);
                        LESS153 = (Token) this.match(this.input, SparqlParser.LESS,
                              SparqlParser.FOLLOW_LESS_in_relationalExpression1311);
                        LESS153_tree = (Object) this.adaptor.create(LESS153);
                        this.adaptor.addChild(root_0, LESS153_tree);

                        this.dbg.location(287, 88);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1313);
                        numericExpression154 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression154.getTree());

                     }
                     break;
                  case 4:
                     this.dbg.enterAlt(4);

                     // Sparql.g:287:108: GREATER numericExpression
                     {
                        this.dbg.location(287, 108);
                        GREATER155 = (Token) this.match(this.input, SparqlParser.GREATER,
                              SparqlParser.FOLLOW_GREATER_in_relationalExpression1317);
                        GREATER155_tree = (Object) this.adaptor.create(GREATER155);
                        this.adaptor.addChild(root_0, GREATER155_tree);

                        this.dbg.location(287, 116);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1319);
                        numericExpression156 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression156.getTree());

                     }
                     break;
                  case 5:
                     this.dbg.enterAlt(5);

                     // Sparql.g:287:136: LESS_EQUAL numericExpression
                     {
                        this.dbg.location(287, 136);
                        LESS_EQUAL157 = (Token) this.match(this.input,
                              SparqlParser.LESS_EQUAL,
                              SparqlParser.FOLLOW_LESS_EQUAL_in_relationalExpression1323);
                        LESS_EQUAL157_tree = (Object) this.adaptor.create(LESS_EQUAL157);
                        this.adaptor.addChild(root_0, LESS_EQUAL157_tree);

                        this.dbg.location(287, 147);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1325);
                        numericExpression158 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression158.getTree());

                     }
                     break;
                  case 6:
                     this.dbg.enterAlt(6);

                     // Sparql.g:287:167: GREATER_EQUAL numericExpression
                     {
                        this.dbg.location(287, 167);
                        GREATER_EQUAL159 = (Token) this.match(this.input,
                              SparqlParser.GREATER_EQUAL,
                              SparqlParser.FOLLOW_GREATER_EQUAL_in_relationalExpression1329);
                        GREATER_EQUAL159_tree = (Object) this.adaptor
                              .create(GREATER_EQUAL159);
                        this.adaptor.addChild(root_0, GREATER_EQUAL159_tree);

                        this.dbg.location(287, 181);
                        this
                              .pushFollow(SparqlParser.FOLLOW_numericExpression_in_relationalExpression1331);
                        numericExpression160 = this.numericExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, numericExpression160.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(53);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(288, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "relationalExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "relationalExpression"

   public static class numericExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "numericExpression"
   // Sparql.g:290:1: numericExpression : additiveExpression ;
   public final SparqlParser.numericExpression_return numericExpression()
         throws RecognitionException {
      final SparqlParser.numericExpression_return retval = new SparqlParser.numericExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.additiveExpression_return additiveExpression161 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(290, 1);

         try {
            // Sparql.g:291:5: ( additiveExpression )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(291, 7);
            this.pushFollow(SparqlParser.FOLLOW_additiveExpression_in_numericExpression1351);
            additiveExpression161 = this.additiveExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, additiveExpression161.getTree());
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(292, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "numericExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "numericExpression"

   public static class additiveExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "additiveExpression"
   // Sparql.g:294:1: additiveExpression : multiplicativeExpression ( PLUS
   // multiplicativeExpression | MINUS multiplicativeExpression | numericLiteralPositive |
   // numericLiteralNegative )* ;
   public final SparqlParser.additiveExpression_return additiveExpression()
         throws RecognitionException {
      final SparqlParser.additiveExpression_return retval = new SparqlParser.additiveExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token PLUS163 = null;
      Token MINUS165 = null;
      SparqlParser.multiplicativeExpression_return multiplicativeExpression162 = null;

      SparqlParser.multiplicativeExpression_return multiplicativeExpression164 = null;

      SparqlParser.multiplicativeExpression_return multiplicativeExpression166 = null;

      SparqlParser.numericLiteralPositive_return numericLiteralPositive167 = null;

      SparqlParser.numericLiteralNegative_return numericLiteralNegative168 = null;

      Object PLUS163_tree = null;
      Object MINUS165_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "additiveExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(294, 1);

         try {
            // Sparql.g:295:5: ( multiplicativeExpression ( PLUS multiplicativeExpression |
            // MINUS multiplicativeExpression | numericLiteralPositive |
            // numericLiteralNegative )* )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(295, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_multiplicativeExpression_in_additiveExpression1368);
            multiplicativeExpression162 = this.multiplicativeExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, multiplicativeExpression162.getTree());
            this.dbg.location(295, 32);
            // Sparql.g:295:32: ( PLUS multiplicativeExpression | MINUS
            // multiplicativeExpression | numericLiteralPositive | numericLiteralNegative )*
            try {
               this.dbg.enterSubRule(54);

               loop54: do {
                  int alt54 = 5;
                  try {
                     this.dbg.enterDecision(54);

                     switch (this.input.LA(1)) {
                        case PLUS: {
                           alt54 = 1;
                        }
                           break;
                        case MINUS: {
                           alt54 = 2;
                        }
                           break;
                        case INTEGER_POSITIVE:
                        case DECIMAL_POSITIVE:
                        case DOUBLE_POSITIVE: {
                           alt54 = 3;
                        }
                           break;
                        case INTEGER_NEGATIVE:
                        case DECIMAL_NEGATIVE:
                        case DOUBLE_NEGATIVE: {
                           alt54 = 4;
                        }
                           break;

                     }

                  } finally {
                     this.dbg.exitDecision(54);
                  }

                  switch (alt54) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:295:34: PLUS multiplicativeExpression
                        {
                           this.dbg.location(295, 34);
                           PLUS163 = (Token) this.match(this.input, SparqlParser.PLUS,
                                 SparqlParser.FOLLOW_PLUS_in_additiveExpression1372);
                           PLUS163_tree = (Object) this.adaptor.create(PLUS163);
                           this.adaptor.addChild(root_0, PLUS163_tree);

                           this.dbg.location(295, 39);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_multiplicativeExpression_in_additiveExpression1374);
                           multiplicativeExpression164 = this.multiplicativeExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, multiplicativeExpression164
                                 .getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // Sparql.g:295:66: MINUS multiplicativeExpression
                        {
                           this.dbg.location(295, 66);
                           MINUS165 = (Token) this.match(this.input, SparqlParser.MINUS,
                                 SparqlParser.FOLLOW_MINUS_in_additiveExpression1378);
                           MINUS165_tree = (Object) this.adaptor.create(MINUS165);
                           this.adaptor.addChild(root_0, MINUS165_tree);

                           this.dbg.location(295, 72);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_multiplicativeExpression_in_additiveExpression1380);
                           multiplicativeExpression166 = this.multiplicativeExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, multiplicativeExpression166
                                 .getTree());

                        }
                        break;
                     case 3:
                        this.dbg.enterAlt(3);

                        // Sparql.g:295:99: numericLiteralPositive
                        {
                           this.dbg.location(295, 99);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_numericLiteralPositive_in_additiveExpression1384);
                           numericLiteralPositive167 = this.numericLiteralPositive();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, numericLiteralPositive167.getTree());

                        }
                        break;
                     case 4:
                        this.dbg.enterAlt(4);

                        // Sparql.g:295:124: numericLiteralNegative
                        {
                           this.dbg.location(295, 124);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_numericLiteralNegative_in_additiveExpression1388);
                           numericLiteralNegative168 = this.numericLiteralNegative();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, numericLiteralNegative168.getTree());

                        }
                        break;

                     default:
                        break loop54;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(54);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(296, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "additiveExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "additiveExpression"

   public static class multiplicativeExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "multiplicativeExpression"
   // Sparql.g:298:1: multiplicativeExpression : unaryExpression ( ASTERISK unaryExpression |
   // DIVIDE unaryExpression )* ;
   public final SparqlParser.multiplicativeExpression_return multiplicativeExpression()
         throws RecognitionException {
      final SparqlParser.multiplicativeExpression_return retval = new SparqlParser.multiplicativeExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ASTERISK170 = null;
      Token DIVIDE172 = null;
      SparqlParser.unaryExpression_return unaryExpression169 = null;

      SparqlParser.unaryExpression_return unaryExpression171 = null;

      SparqlParser.unaryExpression_return unaryExpression173 = null;

      Object ASTERISK170_tree = null;
      Object DIVIDE172_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "multiplicativeExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(298, 1);

         try {
            // Sparql.g:299:5: ( unaryExpression ( ASTERISK unaryExpression | DIVIDE
            // unaryExpression )* )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(299, 7);
            this
                  .pushFollow(SparqlParser.FOLLOW_unaryExpression_in_multiplicativeExpression1408);
            unaryExpression169 = this.unaryExpression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, unaryExpression169.getTree());
            this.dbg.location(299, 23);
            // Sparql.g:299:23: ( ASTERISK unaryExpression | DIVIDE unaryExpression )*
            try {
               this.dbg.enterSubRule(55);

               loop55: do {
                  int alt55 = 3;
                  try {
                     this.dbg.enterDecision(55);

                     final int LA55_0 = this.input.LA(1);

                     if (LA55_0 == SparqlParser.ASTERISK) {
                        alt55 = 1;
                     } else if (LA55_0 == SparqlParser.DIVIDE) {
                        alt55 = 2;
                     }

                  } finally {
                     this.dbg.exitDecision(55);
                  }

                  switch (alt55) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // Sparql.g:299:25: ASTERISK unaryExpression
                        {
                           this.dbg.location(299, 25);
                           ASTERISK170 = (Token) this
                                 .match(
                                       this.input,
                                       SparqlParser.ASTERISK,
                                       SparqlParser.FOLLOW_ASTERISK_in_multiplicativeExpression1412);
                           ASTERISK170_tree = (Object) this.adaptor.create(ASTERISK170);
                           this.adaptor.addChild(root_0, ASTERISK170_tree);

                           this.dbg.location(299, 34);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_unaryExpression_in_multiplicativeExpression1414);
                           unaryExpression171 = this.unaryExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, unaryExpression171.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // Sparql.g:299:52: DIVIDE unaryExpression
                        {
                           this.dbg.location(299, 52);
                           DIVIDE172 = (Token) this.match(this.input, SparqlParser.DIVIDE,
                                 SparqlParser.FOLLOW_DIVIDE_in_multiplicativeExpression1418);
                           DIVIDE172_tree = (Object) this.adaptor.create(DIVIDE172);
                           this.adaptor.addChild(root_0, DIVIDE172_tree);

                           this.dbg.location(299, 59);
                           this
                                 .pushFollow(SparqlParser.FOLLOW_unaryExpression_in_multiplicativeExpression1420);
                           unaryExpression173 = this.unaryExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, unaryExpression173.getTree());

                        }
                        break;

                     default:
                        break loop55;
                  }
               } while (true);
            } finally {
               this.dbg.exitSubRule(55);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(300, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "multiplicativeExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "multiplicativeExpression"

   public static class unaryExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "unaryExpression"
   // Sparql.g:302:1: unaryExpression : ( NOT primaryExpression | PLUS primaryExpression |
   // MINUS primaryExpression | primaryExpression );
   public final SparqlParser.unaryExpression_return unaryExpression()
         throws RecognitionException {
      final SparqlParser.unaryExpression_return retval = new SparqlParser.unaryExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NOT174 = null;
      Token PLUS176 = null;
      Token MINUS178 = null;
      SparqlParser.primaryExpression_return primaryExpression175 = null;

      SparqlParser.primaryExpression_return primaryExpression177 = null;

      SparqlParser.primaryExpression_return primaryExpression179 = null;

      SparqlParser.primaryExpression_return primaryExpression180 = null;

      Object NOT174_tree = null;
      Object PLUS176_tree = null;
      Object MINUS178_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "unaryExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(302, 1);

         try {
            // Sparql.g:303:5: ( NOT primaryExpression | PLUS primaryExpression | MINUS
            // primaryExpression | primaryExpression )
            int alt56 = 4;
            try {
               this.dbg.enterDecision(56);

               switch (this.input.LA(1)) {
                  case NOT:
                     alt56 = 1;
                     break;
                  case PLUS:
                     alt56 = 2;
                     break;
                  case MINUS:
                     alt56 = 3;
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case INTEGER:
                  case OPEN_BRACE:
                  case VAR1:
                  case VAR2:
                  case STR:
                  case LANG:
                  case LANGMATCHES:
                  case DATATYPE:
                  case BOUND:
                  case SAMETERM:
                  case ISIRI:
                  case ISURI:
                  case ISBLANK:
                  case ISLITERAL:
                  case REGEX:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                  case TRUE:
                  case FALSE:
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2:
                  case PNAME_LN:
                     alt56 = 4;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 56, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(56);
            }

            switch (alt56) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(303, 7);
                  NOT174 = (Token) this.match(this.input, SparqlParser.NOT,
                        SparqlParser.FOLLOW_NOT_in_unaryExpression1440);
                  NOT174_tree = (Object) this.adaptor.create(NOT174);
                  this.adaptor.addChild(root_0, NOT174_tree);
                  this.dbg.location(303, 11);
                  this
                        .pushFollow(SparqlParser.FOLLOW_primaryExpression_in_unaryExpression1442);
                  primaryExpression175 = this.primaryExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, primaryExpression175.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(304, 7);
                  PLUS176 = (Token) this.match(this.input, SparqlParser.PLUS,
                        SparqlParser.FOLLOW_PLUS_in_unaryExpression1450);
                  PLUS176_tree = (Object) this.adaptor.create(PLUS176);
                  this.adaptor.addChild(root_0, PLUS176_tree);
                  this.dbg.location(304, 12);
                  this
                        .pushFollow(SparqlParser.FOLLOW_primaryExpression_in_unaryExpression1452);
                  primaryExpression177 = this.primaryExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, primaryExpression177.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(305, 7);
                  MINUS178 = (Token) this.match(this.input, SparqlParser.MINUS,
                        SparqlParser.FOLLOW_MINUS_in_unaryExpression1460);
                  MINUS178_tree = (Object) this.adaptor.create(MINUS178);
                  this.adaptor.addChild(root_0, MINUS178_tree);
                  this.dbg.location(305, 13);
                  this
                        .pushFollow(SparqlParser.FOLLOW_primaryExpression_in_unaryExpression1462);
                  primaryExpression179 = this.primaryExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, primaryExpression179.getTree());
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(306, 7);
                  this
                        .pushFollow(SparqlParser.FOLLOW_primaryExpression_in_unaryExpression1470);
                  primaryExpression180 = this.primaryExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, primaryExpression180.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(307, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "unaryExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "unaryExpression"

   public static class primaryExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "primaryExpression"
   // Sparql.g:309:1: primaryExpression : ( brackettedExpression | builtInCall |
   // iriRefOrFunction | rdfLiteral | numericLiteral | booleanLiteral | var );
   public final SparqlParser.primaryExpression_return primaryExpression()
         throws RecognitionException {
      final SparqlParser.primaryExpression_return retval = new SparqlParser.primaryExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.brackettedExpression_return brackettedExpression181 = null;

      SparqlParser.builtInCall_return builtInCall182 = null;

      SparqlParser.iriRefOrFunction_return iriRefOrFunction183 = null;

      SparqlParser.rdfLiteral_return rdfLiteral184 = null;

      SparqlParser.numericLiteral_return numericLiteral185 = null;

      SparqlParser.booleanLiteral_return booleanLiteral186 = null;

      SparqlParser.var_return var187 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "primaryExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(309, 1);

         try {
            // Sparql.g:310:5: ( brackettedExpression | builtInCall | iriRefOrFunction |
            // rdfLiteral | numericLiteral | booleanLiteral | var )
            int alt57 = 7;
            try {
               this.dbg.enterDecision(57);

               switch (this.input.LA(1)) {
                  case OPEN_BRACE:
                     alt57 = 1;
                     break;
                  case STR:
                  case LANG:
                  case LANGMATCHES:
                  case DATATYPE:
                  case BOUND:
                  case SAMETERM:
                  case ISIRI:
                  case ISURI:
                  case ISBLANK:
                  case ISLITERAL:
                  case REGEX:
                     alt57 = 2;
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN:
                     alt57 = 3;
                     break;
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2:
                     alt57 = 4;
                     break;
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                     alt57 = 5;
                     break;
                  case TRUE:
                  case FALSE:
                     alt57 = 6;
                     break;
                  case VAR1:
                  case VAR2:
                     alt57 = 7;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 57, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(57);
            }

            switch (alt57) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 7);
                  this
                        .pushFollow(SparqlParser.FOLLOW_brackettedExpression_in_primaryExpression1487);
                  brackettedExpression181 = this.brackettedExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, brackettedExpression181.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 30);
                  this.pushFollow(SparqlParser.FOLLOW_builtInCall_in_primaryExpression1491);
                  builtInCall182 = this.builtInCall();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, builtInCall182.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 44);
                  this
                        .pushFollow(SparqlParser.FOLLOW_iriRefOrFunction_in_primaryExpression1495);
                  iriRefOrFunction183 = this.iriRefOrFunction();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, iriRefOrFunction183.getTree());
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 63);
                  this.pushFollow(SparqlParser.FOLLOW_rdfLiteral_in_primaryExpression1499);
                  rdfLiteral184 = this.rdfLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, rdfLiteral184.getTree());
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 76);
                  this
                        .pushFollow(SparqlParser.FOLLOW_numericLiteral_in_primaryExpression1503);
                  numericLiteral185 = this.numericLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, numericLiteral185.getTree());
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 93);
                  this
                        .pushFollow(SparqlParser.FOLLOW_booleanLiteral_in_primaryExpression1507);
                  booleanLiteral186 = this.booleanLiteral();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, booleanLiteral186.getTree());
                  break;
               case 7:
                  this.dbg.enterAlt(7);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(310, 110);
                  this.pushFollow(SparqlParser.FOLLOW_var_in_primaryExpression1511);
                  var187 = this.var();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, var187.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(311, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "primaryExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "primaryExpression"

   public static class brackettedExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "brackettedExpression"
   // Sparql.g:313:1: brackettedExpression : OPEN_BRACE expression CLOSE_BRACE ;
   public final SparqlParser.brackettedExpression_return brackettedExpression()
         throws RecognitionException {
      final SparqlParser.brackettedExpression_return retval = new SparqlParser.brackettedExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE188 = null;
      Token CLOSE_BRACE190 = null;
      SparqlParser.expression_return expression189 = null;

      Object OPEN_BRACE188_tree = null;
      Object CLOSE_BRACE190_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "brackettedExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(313, 1);

         try {
            // Sparql.g:314:5: ( OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(314, 7);
            OPEN_BRACE188 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                  SparqlParser.FOLLOW_OPEN_BRACE_in_brackettedExpression1528);
            OPEN_BRACE188_tree = (Object) this.adaptor.create(OPEN_BRACE188);
            this.adaptor.addChild(root_0, OPEN_BRACE188_tree);
            this.dbg.location(314, 18);
            this.pushFollow(SparqlParser.FOLLOW_expression_in_brackettedExpression1530);
            expression189 = this.expression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, expression189.getTree());
            this.dbg.location(314, 29);
            CLOSE_BRACE190 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                  SparqlParser.FOLLOW_CLOSE_BRACE_in_brackettedExpression1532);
            CLOSE_BRACE190_tree = (Object) this.adaptor.create(CLOSE_BRACE190);
            this.adaptor.addChild(root_0, CLOSE_BRACE190_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(315, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "brackettedExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "brackettedExpression"

   public static class builtInCall_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "builtInCall"
   // Sparql.g:317:1: builtInCall : ( STR OPEN_BRACE expression CLOSE_BRACE | LANG OPEN_BRACE
   // expression CLOSE_BRACE | LANGMATCHES OPEN_BRACE expression COMMA expression CLOSE_BRACE
   // | DATATYPE OPEN_BRACE expression CLOSE_BRACE | BOUND OPEN_BRACE var CLOSE_BRACE |
   // SAMETERM OPEN_BRACE expression COMMA expression CLOSE_BRACE | ISIRI OPEN_BRACE
   // expression CLOSE_BRACE | ISURI OPEN_BRACE expression CLOSE_BRACE | ISBLANK OPEN_BRACE
   // expression CLOSE_BRACE | ISLITERAL OPEN_BRACE expression CLOSE_BRACE | regexExpression
   // );
   public final SparqlParser.builtInCall_return builtInCall() throws RecognitionException {
      final SparqlParser.builtInCall_return retval = new SparqlParser.builtInCall_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token STR191 = null;
      Token OPEN_BRACE192 = null;
      Token CLOSE_BRACE194 = null;
      Token LANG195 = null;
      Token OPEN_BRACE196 = null;
      Token CLOSE_BRACE198 = null;
      Token LANGMATCHES199 = null;
      Token OPEN_BRACE200 = null;
      Token COMMA202 = null;
      Token CLOSE_BRACE204 = null;
      Token DATATYPE205 = null;
      Token OPEN_BRACE206 = null;
      Token CLOSE_BRACE208 = null;
      Token BOUND209 = null;
      Token OPEN_BRACE210 = null;
      Token CLOSE_BRACE212 = null;
      Token SAMETERM213 = null;
      Token OPEN_BRACE214 = null;
      Token COMMA216 = null;
      Token CLOSE_BRACE218 = null;
      Token ISIRI219 = null;
      Token OPEN_BRACE220 = null;
      Token CLOSE_BRACE222 = null;
      Token ISURI223 = null;
      Token OPEN_BRACE224 = null;
      Token CLOSE_BRACE226 = null;
      Token ISBLANK227 = null;
      Token OPEN_BRACE228 = null;
      Token CLOSE_BRACE230 = null;
      Token ISLITERAL231 = null;
      Token OPEN_BRACE232 = null;
      Token CLOSE_BRACE234 = null;
      SparqlParser.expression_return expression193 = null;

      SparqlParser.expression_return expression197 = null;

      SparqlParser.expression_return expression201 = null;

      SparqlParser.expression_return expression203 = null;

      SparqlParser.expression_return expression207 = null;

      SparqlParser.var_return var211 = null;

      SparqlParser.expression_return expression215 = null;

      SparqlParser.expression_return expression217 = null;

      SparqlParser.expression_return expression221 = null;

      SparqlParser.expression_return expression225 = null;

      SparqlParser.expression_return expression229 = null;

      SparqlParser.expression_return expression233 = null;

      SparqlParser.regexExpression_return regexExpression235 = null;

      Object STR191_tree = null;
      Object OPEN_BRACE192_tree = null;
      Object CLOSE_BRACE194_tree = null;
      Object LANG195_tree = null;
      Object OPEN_BRACE196_tree = null;
      Object CLOSE_BRACE198_tree = null;
      Object LANGMATCHES199_tree = null;
      Object OPEN_BRACE200_tree = null;
      Object COMMA202_tree = null;
      Object CLOSE_BRACE204_tree = null;
      Object DATATYPE205_tree = null;
      Object OPEN_BRACE206_tree = null;
      Object CLOSE_BRACE208_tree = null;
      Object BOUND209_tree = null;
      Object OPEN_BRACE210_tree = null;
      Object CLOSE_BRACE212_tree = null;
      Object SAMETERM213_tree = null;
      Object OPEN_BRACE214_tree = null;
      Object COMMA216_tree = null;
      Object CLOSE_BRACE218_tree = null;
      Object ISIRI219_tree = null;
      Object OPEN_BRACE220_tree = null;
      Object CLOSE_BRACE222_tree = null;
      Object ISURI223_tree = null;
      Object OPEN_BRACE224_tree = null;
      Object CLOSE_BRACE226_tree = null;
      Object ISBLANK227_tree = null;
      Object OPEN_BRACE228_tree = null;
      Object CLOSE_BRACE230_tree = null;
      Object ISLITERAL231_tree = null;
      Object OPEN_BRACE232_tree = null;
      Object CLOSE_BRACE234_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "builtInCall");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(317, 1);

         try {
            // Sparql.g:318:5: ( STR OPEN_BRACE expression CLOSE_BRACE | LANG OPEN_BRACE
            // expression CLOSE_BRACE | LANGMATCHES OPEN_BRACE expression COMMA expression
            // CLOSE_BRACE | DATATYPE OPEN_BRACE expression CLOSE_BRACE | BOUND OPEN_BRACE
            // var CLOSE_BRACE | SAMETERM OPEN_BRACE expression COMMA expression CLOSE_BRACE
            // | ISIRI OPEN_BRACE expression CLOSE_BRACE | ISURI OPEN_BRACE expression
            // CLOSE_BRACE | ISBLANK OPEN_BRACE expression CLOSE_BRACE | ISLITERAL OPEN_BRACE
            // expression CLOSE_BRACE | regexExpression )
            int alt58 = 11;
            try {
               this.dbg.enterDecision(58);

               switch (this.input.LA(1)) {
                  case STR:
                     alt58 = 1;
                     break;
                  case LANG:
                     alt58 = 2;
                     break;
                  case LANGMATCHES:
                     alt58 = 3;
                     break;
                  case DATATYPE:
                     alt58 = 4;
                     break;
                  case BOUND:
                     alt58 = 5;
                     break;
                  case SAMETERM:
                     alt58 = 6;
                     break;
                  case ISIRI:
                     alt58 = 7;
                     break;
                  case ISURI:
                     alt58 = 8;
                     break;
                  case ISBLANK:
                     alt58 = 9;
                     break;
                  case ISLITERAL:
                     alt58 = 10;
                     break;
                  case REGEX:
                     alt58 = 11;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 58, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(58);
            }

            switch (alt58) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(318, 7);
                  STR191 = (Token) this.match(this.input, SparqlParser.STR,
                        SparqlParser.FOLLOW_STR_in_builtInCall1549);
                  STR191_tree = (Object) this.adaptor.create(STR191);
                  this.adaptor.addChild(root_0, STR191_tree);
                  this.dbg.location(318, 11);
                  OPEN_BRACE192 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1551);
                  OPEN_BRACE192_tree = (Object) this.adaptor.create(OPEN_BRACE192);
                  this.adaptor.addChild(root_0, OPEN_BRACE192_tree);
                  this.dbg.location(318, 22);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1553);
                  expression193 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression193.getTree());
                  this.dbg.location(318, 33);
                  CLOSE_BRACE194 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1555);
                  CLOSE_BRACE194_tree = (Object) this.adaptor.create(CLOSE_BRACE194);
                  this.adaptor.addChild(root_0, CLOSE_BRACE194_tree);
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(319, 7);
                  LANG195 = (Token) this.match(this.input, SparqlParser.LANG,
                        SparqlParser.FOLLOW_LANG_in_builtInCall1563);
                  LANG195_tree = (Object) this.adaptor.create(LANG195);
                  this.adaptor.addChild(root_0, LANG195_tree);
                  this.dbg.location(319, 12);
                  OPEN_BRACE196 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1565);
                  OPEN_BRACE196_tree = (Object) this.adaptor.create(OPEN_BRACE196);
                  this.adaptor.addChild(root_0, OPEN_BRACE196_tree);
                  this.dbg.location(319, 23);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1567);
                  expression197 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression197.getTree());
                  this.dbg.location(319, 34);
                  CLOSE_BRACE198 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1569);
                  CLOSE_BRACE198_tree = (Object) this.adaptor.create(CLOSE_BRACE198);
                  this.adaptor.addChild(root_0, CLOSE_BRACE198_tree);
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(320, 7);
                  LANGMATCHES199 = (Token) this.match(this.input, SparqlParser.LANGMATCHES,
                        SparqlParser.FOLLOW_LANGMATCHES_in_builtInCall1577);
                  LANGMATCHES199_tree = (Object) this.adaptor.create(LANGMATCHES199);
                  this.adaptor.addChild(root_0, LANGMATCHES199_tree);
                  this.dbg.location(320, 19);
                  OPEN_BRACE200 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1579);
                  OPEN_BRACE200_tree = (Object) this.adaptor.create(OPEN_BRACE200);
                  this.adaptor.addChild(root_0, OPEN_BRACE200_tree);
                  this.dbg.location(320, 30);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1581);
                  expression201 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression201.getTree());
                  this.dbg.location(320, 41);
                  COMMA202 = (Token) this.match(this.input, SparqlParser.COMMA,
                        SparqlParser.FOLLOW_COMMA_in_builtInCall1583);
                  COMMA202_tree = (Object) this.adaptor.create(COMMA202);
                  this.adaptor.addChild(root_0, COMMA202_tree);
                  this.dbg.location(320, 47);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1585);
                  expression203 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression203.getTree());
                  this.dbg.location(320, 58);
                  CLOSE_BRACE204 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1587);
                  CLOSE_BRACE204_tree = (Object) this.adaptor.create(CLOSE_BRACE204);
                  this.adaptor.addChild(root_0, CLOSE_BRACE204_tree);
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(321, 7);
                  DATATYPE205 = (Token) this.match(this.input, SparqlParser.DATATYPE,
                        SparqlParser.FOLLOW_DATATYPE_in_builtInCall1595);
                  DATATYPE205_tree = (Object) this.adaptor.create(DATATYPE205);
                  this.adaptor.addChild(root_0, DATATYPE205_tree);
                  this.dbg.location(321, 16);
                  OPEN_BRACE206 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1597);
                  OPEN_BRACE206_tree = (Object) this.adaptor.create(OPEN_BRACE206);
                  this.adaptor.addChild(root_0, OPEN_BRACE206_tree);
                  this.dbg.location(321, 27);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1599);
                  expression207 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression207.getTree());
                  this.dbg.location(321, 38);
                  CLOSE_BRACE208 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1601);
                  CLOSE_BRACE208_tree = (Object) this.adaptor.create(CLOSE_BRACE208);
                  this.adaptor.addChild(root_0, CLOSE_BRACE208_tree);
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(322, 7);
                  BOUND209 = (Token) this.match(this.input, SparqlParser.BOUND,
                        SparqlParser.FOLLOW_BOUND_in_builtInCall1609);
                  BOUND209_tree = (Object) this.adaptor.create(BOUND209);
                  this.adaptor.addChild(root_0, BOUND209_tree);
                  this.dbg.location(322, 13);
                  OPEN_BRACE210 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1611);
                  OPEN_BRACE210_tree = (Object) this.adaptor.create(OPEN_BRACE210);
                  this.adaptor.addChild(root_0, OPEN_BRACE210_tree);
                  this.dbg.location(322, 24);
                  this.pushFollow(SparqlParser.FOLLOW_var_in_builtInCall1613);
                  var211 = this.var();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, var211.getTree());
                  this.dbg.location(322, 28);
                  CLOSE_BRACE212 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1615);
                  CLOSE_BRACE212_tree = (Object) this.adaptor.create(CLOSE_BRACE212);
                  this.adaptor.addChild(root_0, CLOSE_BRACE212_tree);
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(323, 7);
                  SAMETERM213 = (Token) this.match(this.input, SparqlParser.SAMETERM,
                        SparqlParser.FOLLOW_SAMETERM_in_builtInCall1623);
                  SAMETERM213_tree = (Object) this.adaptor.create(SAMETERM213);
                  this.adaptor.addChild(root_0, SAMETERM213_tree);
                  this.dbg.location(323, 16);
                  OPEN_BRACE214 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1625);
                  OPEN_BRACE214_tree = (Object) this.adaptor.create(OPEN_BRACE214);
                  this.adaptor.addChild(root_0, OPEN_BRACE214_tree);
                  this.dbg.location(323, 27);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1627);
                  expression215 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression215.getTree());
                  this.dbg.location(323, 38);
                  COMMA216 = (Token) this.match(this.input, SparqlParser.COMMA,
                        SparqlParser.FOLLOW_COMMA_in_builtInCall1629);
                  COMMA216_tree = (Object) this.adaptor.create(COMMA216);
                  this.adaptor.addChild(root_0, COMMA216_tree);
                  this.dbg.location(323, 44);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1631);
                  expression217 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression217.getTree());
                  this.dbg.location(323, 55);
                  CLOSE_BRACE218 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1633);
                  CLOSE_BRACE218_tree = (Object) this.adaptor.create(CLOSE_BRACE218);
                  this.adaptor.addChild(root_0, CLOSE_BRACE218_tree);
                  break;
               case 7:
                  this.dbg.enterAlt(7);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(324, 7);
                  ISIRI219 = (Token) this.match(this.input, SparqlParser.ISIRI,
                        SparqlParser.FOLLOW_ISIRI_in_builtInCall1641);
                  ISIRI219_tree = (Object) this.adaptor.create(ISIRI219);
                  this.adaptor.addChild(root_0, ISIRI219_tree);
                  this.dbg.location(324, 13);
                  OPEN_BRACE220 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1643);
                  OPEN_BRACE220_tree = (Object) this.adaptor.create(OPEN_BRACE220);
                  this.adaptor.addChild(root_0, OPEN_BRACE220_tree);
                  this.dbg.location(324, 24);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1645);
                  expression221 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression221.getTree());
                  this.dbg.location(324, 35);
                  CLOSE_BRACE222 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1647);
                  CLOSE_BRACE222_tree = (Object) this.adaptor.create(CLOSE_BRACE222);
                  this.adaptor.addChild(root_0, CLOSE_BRACE222_tree);
                  break;
               case 8:
                  this.dbg.enterAlt(8);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(325, 7);
                  ISURI223 = (Token) this.match(this.input, SparqlParser.ISURI,
                        SparqlParser.FOLLOW_ISURI_in_builtInCall1655);
                  ISURI223_tree = (Object) this.adaptor.create(ISURI223);
                  this.adaptor.addChild(root_0, ISURI223_tree);
                  this.dbg.location(325, 13);
                  OPEN_BRACE224 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1657);
                  OPEN_BRACE224_tree = (Object) this.adaptor.create(OPEN_BRACE224);
                  this.adaptor.addChild(root_0, OPEN_BRACE224_tree);
                  this.dbg.location(325, 24);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1659);
                  expression225 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression225.getTree());
                  this.dbg.location(325, 35);
                  CLOSE_BRACE226 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1661);
                  CLOSE_BRACE226_tree = (Object) this.adaptor.create(CLOSE_BRACE226);
                  this.adaptor.addChild(root_0, CLOSE_BRACE226_tree);
                  break;
               case 9:
                  this.dbg.enterAlt(9);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(326, 7);
                  ISBLANK227 = (Token) this.match(this.input, SparqlParser.ISBLANK,
                        SparqlParser.FOLLOW_ISBLANK_in_builtInCall1669);
                  ISBLANK227_tree = (Object) this.adaptor.create(ISBLANK227);
                  this.adaptor.addChild(root_0, ISBLANK227_tree);
                  this.dbg.location(326, 15);
                  OPEN_BRACE228 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1671);
                  OPEN_BRACE228_tree = (Object) this.adaptor.create(OPEN_BRACE228);
                  this.adaptor.addChild(root_0, OPEN_BRACE228_tree);
                  this.dbg.location(326, 26);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1673);
                  expression229 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression229.getTree());
                  this.dbg.location(326, 37);
                  CLOSE_BRACE230 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1675);
                  CLOSE_BRACE230_tree = (Object) this.adaptor.create(CLOSE_BRACE230);
                  this.adaptor.addChild(root_0, CLOSE_BRACE230_tree);
                  break;
               case 10:
                  this.dbg.enterAlt(10);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(327, 7);
                  ISLITERAL231 = (Token) this.match(this.input, SparqlParser.ISLITERAL,
                        SparqlParser.FOLLOW_ISLITERAL_in_builtInCall1683);
                  ISLITERAL231_tree = (Object) this.adaptor.create(ISLITERAL231);
                  this.adaptor.addChild(root_0, ISLITERAL231_tree);
                  this.dbg.location(327, 17);
                  OPEN_BRACE232 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                        SparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall1685);
                  OPEN_BRACE232_tree = (Object) this.adaptor.create(OPEN_BRACE232);
                  this.adaptor.addChild(root_0, OPEN_BRACE232_tree);
                  this.dbg.location(327, 28);
                  this.pushFollow(SparqlParser.FOLLOW_expression_in_builtInCall1687);
                  expression233 = this.expression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, expression233.getTree());
                  this.dbg.location(327, 39);
                  CLOSE_BRACE234 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall1689);
                  CLOSE_BRACE234_tree = (Object) this.adaptor.create(CLOSE_BRACE234);
                  this.adaptor.addChild(root_0, CLOSE_BRACE234_tree);
                  break;
               case 11:
                  this.dbg.enterAlt(11);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(328, 7);
                  this.pushFollow(SparqlParser.FOLLOW_regexExpression_in_builtInCall1697);
                  regexExpression235 = this.regexExpression();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, regexExpression235.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(329, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "builtInCall");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "builtInCall"

   public static class regexExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "regexExpression"
   // Sparql.g:331:1: regexExpression : REGEX OPEN_BRACE expression COMMA expression ( COMMA
   // expression )? CLOSE_BRACE ;
   public final SparqlParser.regexExpression_return regexExpression()
         throws RecognitionException {
      final SparqlParser.regexExpression_return retval = new SparqlParser.regexExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token REGEX236 = null;
      Token OPEN_BRACE237 = null;
      Token COMMA239 = null;
      Token COMMA241 = null;
      Token CLOSE_BRACE243 = null;
      SparqlParser.expression_return expression238 = null;

      SparqlParser.expression_return expression240 = null;

      SparqlParser.expression_return expression242 = null;

      Object REGEX236_tree = null;
      Object OPEN_BRACE237_tree = null;
      Object COMMA239_tree = null;
      Object COMMA241_tree = null;
      Object CLOSE_BRACE243_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "regexExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(331, 1);

         try {
            // Sparql.g:332:5: ( REGEX OPEN_BRACE expression COMMA expression ( COMMA
            // expression )? CLOSE_BRACE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(332, 7);
            REGEX236 = (Token) this.match(this.input, SparqlParser.REGEX,
                  SparqlParser.FOLLOW_REGEX_in_regexExpression1714);
            REGEX236_tree = (Object) this.adaptor.create(REGEX236);
            this.adaptor.addChild(root_0, REGEX236_tree);
            this.dbg.location(332, 13);
            OPEN_BRACE237 = (Token) this.match(this.input, SparqlParser.OPEN_BRACE,
                  SparqlParser.FOLLOW_OPEN_BRACE_in_regexExpression1716);
            OPEN_BRACE237_tree = (Object) this.adaptor.create(OPEN_BRACE237);
            this.adaptor.addChild(root_0, OPEN_BRACE237_tree);
            this.dbg.location(332, 24);
            this.pushFollow(SparqlParser.FOLLOW_expression_in_regexExpression1718);
            expression238 = this.expression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, expression238.getTree());
            this.dbg.location(332, 35);
            COMMA239 = (Token) this.match(this.input, SparqlParser.COMMA,
                  SparqlParser.FOLLOW_COMMA_in_regexExpression1720);
            COMMA239_tree = (Object) this.adaptor.create(COMMA239);
            this.adaptor.addChild(root_0, COMMA239_tree);
            this.dbg.location(332, 41);
            this.pushFollow(SparqlParser.FOLLOW_expression_in_regexExpression1722);
            expression240 = this.expression();
            this.state._fsp--;
            this.adaptor.addChild(root_0, expression240.getTree());
            this.dbg.location(332, 52);
            // Sparql.g:332:52: ( COMMA expression )?
            int alt59 = 2;
            try {
               this.dbg.enterSubRule(59);
               try {
                  this.dbg.enterDecision(59);

                  final int LA59_0 = this.input.LA(1);

                  if (LA59_0 == SparqlParser.COMMA) {
                     alt59 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(59);
               }

               switch (alt59) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:332:54: COMMA expression
                     {
                        this.dbg.location(332, 54);
                        COMMA241 = (Token) this.match(this.input, SparqlParser.COMMA,
                              SparqlParser.FOLLOW_COMMA_in_regexExpression1726);
                        COMMA241_tree = (Object) this.adaptor.create(COMMA241);
                        this.adaptor.addChild(root_0, COMMA241_tree);

                        this.dbg.location(332, 60);
                        this
                              .pushFollow(SparqlParser.FOLLOW_expression_in_regexExpression1728);
                        expression242 = this.expression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, expression242.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(59);
            }
            this.dbg.location(332, 74);
            CLOSE_BRACE243 = (Token) this.match(this.input, SparqlParser.CLOSE_BRACE,
                  SparqlParser.FOLLOW_CLOSE_BRACE_in_regexExpression1733);
            CLOSE_BRACE243_tree = (Object) this.adaptor.create(CLOSE_BRACE243);
            this.adaptor.addChild(root_0, CLOSE_BRACE243_tree);
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(333, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "regexExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "regexExpression"

   public static class iriRefOrFunction_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "iriRefOrFunction"
   // Sparql.g:335:1: iriRefOrFunction : iriRef ( argList )? ;
   public final SparqlParser.iriRefOrFunction_return iriRefOrFunction()
         throws RecognitionException {
      final SparqlParser.iriRefOrFunction_return retval = new SparqlParser.iriRefOrFunction_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.iriRef_return iriRef244 = null;

      SparqlParser.argList_return argList245 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "iriRefOrFunction");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(335, 1);

         try {
            // Sparql.g:336:5: ( iriRef ( argList )? )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(336, 7);
            this.pushFollow(SparqlParser.FOLLOW_iriRef_in_iriRefOrFunction1750);
            iriRef244 = this.iriRef();
            this.state._fsp--;
            this.adaptor.addChild(root_0, iriRef244.getTree());
            this.dbg.location(336, 14);
            // Sparql.g:336:14: ( argList )?
            int alt60 = 2;
            try {
               this.dbg.enterSubRule(60);
               try {
                  this.dbg.enterDecision(60);

                  final int LA60_0 = this.input.LA(1);

                  if (LA60_0 == SparqlParser.OPEN_BRACE) {
                     alt60 = 1;
                  }
               } finally {
                  this.dbg.exitDecision(60);
               }

               switch (alt60) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:336:14: argList
                     {
                        this.dbg.location(336, 14);
                        this.pushFollow(SparqlParser.FOLLOW_argList_in_iriRefOrFunction1752);
                        argList245 = this.argList();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, argList245.getTree());

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(60);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(337, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "iriRefOrFunction");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "iriRefOrFunction"

   public static class rdfLiteral_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "rdfLiteral"
   // Sparql.g:339:1: rdfLiteral : string ( LANGTAG | ( REFERENCE iriRef ) )? ;
   public final SparqlParser.rdfLiteral_return rdfLiteral() throws RecognitionException {
      final SparqlParser.rdfLiteral_return retval = new SparqlParser.rdfLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token LANGTAG247 = null;
      Token REFERENCE248 = null;
      SparqlParser.string_return string246 = null;

      SparqlParser.iriRef_return iriRef249 = null;

      Object LANGTAG247_tree = null;
      Object REFERENCE248_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "rdfLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(339, 1);

         try {
            // Sparql.g:340:5: ( string ( LANGTAG | ( REFERENCE iriRef ) )? )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(340, 7);
            this.pushFollow(SparqlParser.FOLLOW_string_in_rdfLiteral1770);
            string246 = this.string();
            this.state._fsp--;
            this.adaptor.addChild(root_0, string246.getTree());
            this.dbg.location(340, 14);
            // Sparql.g:340:14: ( LANGTAG | ( REFERENCE iriRef ) )?
            int alt61 = 3;
            try {
               this.dbg.enterSubRule(61);
               try {
                  this.dbg.enterDecision(61);

                  final int LA61_0 = this.input.LA(1);

                  if (LA61_0 == SparqlParser.LANGTAG) {
                     alt61 = 1;
                  } else if (LA61_0 == SparqlParser.REFERENCE) {
                     alt61 = 2;
                  }
               } finally {
                  this.dbg.exitDecision(61);
               }

               switch (alt61) {
                  case 1:
                     this.dbg.enterAlt(1);

                     // Sparql.g:340:16: LANGTAG
                     {
                        this.dbg.location(340, 16);
                        LANGTAG247 = (Token) this.match(this.input, SparqlParser.LANGTAG,
                              SparqlParser.FOLLOW_LANGTAG_in_rdfLiteral1774);
                        LANGTAG247_tree = (Object) this.adaptor.create(LANGTAG247);
                        this.adaptor.addChild(root_0, LANGTAG247_tree);

                     }
                     break;
                  case 2:
                     this.dbg.enterAlt(2);

                     // Sparql.g:340:26: ( REFERENCE iriRef )
                     {
                        this.dbg.location(340, 26);
                        // Sparql.g:340:26: ( REFERENCE iriRef )
                        this.dbg.enterAlt(1);

                        // Sparql.g:340:28: REFERENCE iriRef
                        {
                           this.dbg.location(340, 28);
                           REFERENCE248 = (Token) this.match(this.input,
                                 SparqlParser.REFERENCE,
                                 SparqlParser.FOLLOW_REFERENCE_in_rdfLiteral1780);
                           REFERENCE248_tree = (Object) this.adaptor.create(REFERENCE248);
                           this.adaptor.addChild(root_0, REFERENCE248_tree);

                           this.dbg.location(340, 38);
                           this.pushFollow(SparqlParser.FOLLOW_iriRef_in_rdfLiteral1782);
                           iriRef249 = this.iriRef();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, iriRef249.getTree());

                        }

                     }
                     break;

               }
            } finally {
               this.dbg.exitSubRule(61);
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(341, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "rdfLiteral");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "rdfLiteral"

   public static class numericLiteral_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "numericLiteral"
   // Sparql.g:343:1: numericLiteral : ( numericLiteralUnsigned | numericLiteralPositive |
   // numericLiteralNegative );
   public final SparqlParser.numericLiteral_return numericLiteral()
         throws RecognitionException {
      final SparqlParser.numericLiteral_return retval = new SparqlParser.numericLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlParser.numericLiteralUnsigned_return numericLiteralUnsigned250 = null;

      SparqlParser.numericLiteralPositive_return numericLiteralPositive251 = null;

      SparqlParser.numericLiteralNegative_return numericLiteralNegative252 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(343, 1);

         try {
            // Sparql.g:344:5: ( numericLiteralUnsigned | numericLiteralPositive |
            // numericLiteralNegative )
            int alt62 = 3;
            try {
               this.dbg.enterDecision(62);

               switch (this.input.LA(1)) {
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE:
                     alt62 = 1;
                     break;
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                     alt62 = 2;
                     break;
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE:
                     alt62 = 3;
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 62, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(62);
            }

            switch (alt62) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(344, 7);
                  this
                        .pushFollow(SparqlParser.FOLLOW_numericLiteralUnsigned_in_numericLiteral1804);
                  numericLiteralUnsigned250 = this.numericLiteralUnsigned();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, numericLiteralUnsigned250.getTree());
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(344, 32);
                  this
                        .pushFollow(SparqlParser.FOLLOW_numericLiteralPositive_in_numericLiteral1808);
                  numericLiteralPositive251 = this.numericLiteralPositive();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, numericLiteralPositive251.getTree());
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(344, 57);
                  this
                        .pushFollow(SparqlParser.FOLLOW_numericLiteralNegative_in_numericLiteral1812);
                  numericLiteralNegative252 = this.numericLiteralNegative();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, numericLiteralNegative252.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(345, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "numericLiteral");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "numericLiteral"

   public static class numericLiteralUnsigned_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "numericLiteralUnsigned"
   // Sparql.g:347:1: numericLiteralUnsigned : ( INTEGER | DECIMAL | DOUBLE );
   public final SparqlParser.numericLiteralUnsigned_return numericLiteralUnsigned()
         throws RecognitionException {
      final SparqlParser.numericLiteralUnsigned_return retval = new SparqlParser.numericLiteralUnsigned_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set253 = null;

      final Object set253_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralUnsigned");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(347, 1);

         try {
            // Sparql.g:348:5: ( INTEGER | DECIMAL | DOUBLE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(348, 5);
            set253 = (Token) this.input.LT(1);
            if (this.input.LA(1) == SparqlParser.INTEGER
                  || this.input.LA(1) >= SparqlParser.DECIMAL
                  && this.input.LA(1) <= SparqlParser.DOUBLE) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set253));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(351, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "numericLiteralUnsigned");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "numericLiteralUnsigned"

   public static class numericLiteralPositive_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "numericLiteralPositive"
   // Sparql.g:353:1: numericLiteralPositive : ( INTEGER_POSITIVE | DECIMAL_POSITIVE |
   // DOUBLE_POSITIVE );
   public final SparqlParser.numericLiteralPositive_return numericLiteralPositive()
         throws RecognitionException {
      final SparqlParser.numericLiteralPositive_return retval = new SparqlParser.numericLiteralPositive_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set254 = null;

      final Object set254_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralPositive");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(353, 1);

         try {
            // Sparql.g:354:5: ( INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(354, 5);
            set254 = (Token) this.input.LT(1);
            if (this.input.LA(1) >= SparqlParser.INTEGER_POSITIVE
                  && this.input.LA(1) <= SparqlParser.DOUBLE_POSITIVE) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set254));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(357, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "numericLiteralPositive");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "numericLiteralPositive"

   public static class numericLiteralNegative_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "numericLiteralNegative"
   // Sparql.g:359:1: numericLiteralNegative : ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE |
   // DOUBLE_NEGATIVE );
   public final SparqlParser.numericLiteralNegative_return numericLiteralNegative()
         throws RecognitionException {
      final SparqlParser.numericLiteralNegative_return retval = new SparqlParser.numericLiteralNegative_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set255 = null;

      final Object set255_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralNegative");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(359, 1);

         try {
            // Sparql.g:360:5: ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(360, 5);
            set255 = (Token) this.input.LT(1);
            if (this.input.LA(1) >= SparqlParser.INTEGER_NEGATIVE
                  && this.input.LA(1) <= SparqlParser.DOUBLE_NEGATIVE) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set255));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(363, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "numericLiteralNegative");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "numericLiteralNegative"

   public static class booleanLiteral_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "booleanLiteral"
   // Sparql.g:365:1: booleanLiteral : ( TRUE | FALSE );
   public final SparqlParser.booleanLiteral_return booleanLiteral()
         throws RecognitionException {
      final SparqlParser.booleanLiteral_return retval = new SparqlParser.booleanLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set256 = null;

      final Object set256_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "booleanLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(365, 1);

         try {
            // Sparql.g:366:5: ( TRUE | FALSE )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(366, 5);
            set256 = (Token) this.input.LT(1);
            if (this.input.LA(1) >= SparqlParser.TRUE
                  && this.input.LA(1) <= SparqlParser.FALSE) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set256));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(368, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "booleanLiteral");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "booleanLiteral"

   public static class string_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "string"
   // Sparql.g:370:1: string : ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 |
   // STRING_LITERAL_LONG2 );
   public final SparqlParser.string_return string() throws RecognitionException {
      final SparqlParser.string_return retval = new SparqlParser.string_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set257 = null;

      final Object set257_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "string");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(370, 1);

         try {
            // Sparql.g:371:5: ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 |
            // STRING_LITERAL_LONG2 )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(371, 5);
            set257 = (Token) this.input.LT(1);
            if (this.input.LA(1) >= SparqlParser.STRING_LITERAL1
                  && this.input.LA(1) <= SparqlParser.STRING_LITERAL_LONG2) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set257));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(375, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "string");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "string"

   public static class iriRef_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "iriRef"
   // Sparql.g:377:1: iriRef : ( IRI_REF | prefixedName );
   public final SparqlParser.iriRef_return iriRef() throws RecognitionException {
      final SparqlParser.iriRef_return retval = new SparqlParser.iriRef_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token IRI_REF258 = null;
      SparqlParser.prefixedName_return prefixedName259 = null;

      Object IRI_REF258_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "iriRef");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(377, 1);

         try {
            // Sparql.g:378:5: ( IRI_REF | prefixedName )
            int alt63 = 2;
            try {
               this.dbg.enterDecision(63);

               final int LA63_0 = this.input.LA(1);

               if (LA63_0 == SparqlParser.IRI_REF) {
                  alt63 = 1;
               } else if (LA63_0 == SparqlParser.PNAME_NS || LA63_0 == SparqlParser.PNAME_LN) {
                  alt63 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 63, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(63);
            }

            switch (alt63) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(378, 7);
                  IRI_REF258 = (Token) this.match(this.input, SparqlParser.IRI_REF,
                        SparqlParser.FOLLOW_IRI_REF_in_iriRef1994);
                  IRI_REF258_tree = (Object) this.adaptor.create(IRI_REF258);
                  this.adaptor.addChild(root_0, IRI_REF258_tree);
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(379, 7);
                  this.pushFollow(SparqlParser.FOLLOW_prefixedName_in_iriRef2002);
                  prefixedName259 = this.prefixedName();
                  this.state._fsp--;
                  this.adaptor.addChild(root_0, prefixedName259.getTree());
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(380, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "iriRef");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "iriRef"

   public static class prefixedName_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prefixedName"
   // Sparql.g:382:1: prefixedName : ( PNAME_LN | PNAME_NS );
   public final SparqlParser.prefixedName_return prefixedName() throws RecognitionException {
      final SparqlParser.prefixedName_return retval = new SparqlParser.prefixedName_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set260 = null;

      final Object set260_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prefixedName");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(382, 1);

         try {
            // Sparql.g:383:5: ( PNAME_LN | PNAME_NS )
            this.dbg.enterAlt(1);

            root_0 = (Object) this.adaptor.nil();
            this.dbg.location(383, 5);
            set260 = (Token) this.input.LT(1);
            if (this.input.LA(1) == SparqlParser.PNAME_NS
                  || this.input.LA(1) == SparqlParser.PNAME_LN) {
               this.input.consume();
               this.adaptor.addChild(root_0, (Object) this.adaptor.create(set260));
               this.state.errorRecovery = false;
            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.dbg.recognitionException(mse);
               throw mse;
            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(385, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "prefixedName");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "prefixedName"

   public static class blankNode_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "blankNode"
   // Sparql.g:387:1: blankNode : ( BLANK_NODE_LABEL | OPEN_SQUARE_BRACE CLOSE_SQUARE_BRACE
   // );
   public final SparqlParser.blankNode_return blankNode() throws RecognitionException {
      final SparqlParser.blankNode_return retval = new SparqlParser.blankNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token BLANK_NODE_LABEL261 = null;
      Token OPEN_SQUARE_BRACE262 = null;
      Token CLOSE_SQUARE_BRACE263 = null;

      Object BLANK_NODE_LABEL261_tree = null;
      Object OPEN_SQUARE_BRACE262_tree = null;
      Object CLOSE_SQUARE_BRACE263_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "blankNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(387, 1);

         try {
            // Sparql.g:388:5: ( BLANK_NODE_LABEL | OPEN_SQUARE_BRACE CLOSE_SQUARE_BRACE )
            int alt64 = 2;
            try {
               this.dbg.enterDecision(64);

               final int LA64_0 = this.input.LA(1);

               if (LA64_0 == SparqlParser.BLANK_NODE_LABEL) {
                  alt64 = 1;
               } else if (LA64_0 == SparqlParser.OPEN_SQUARE_BRACE) {
                  alt64 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 64, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(64);
            }

            switch (alt64) {
               case 1:
                  this.dbg.enterAlt(1);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(388, 7);
                  BLANK_NODE_LABEL261 = (Token) this.match(this.input,
                        SparqlParser.BLANK_NODE_LABEL,
                        SparqlParser.FOLLOW_BLANK_NODE_LABEL_in_blankNode2044);
                  BLANK_NODE_LABEL261_tree = (Object) this.adaptor
                        .create(BLANK_NODE_LABEL261);
                  this.adaptor.addChild(root_0, BLANK_NODE_LABEL261_tree);
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  root_0 = (Object) this.adaptor.nil();
                  this.dbg.location(389, 7);
                  OPEN_SQUARE_BRACE262 = (Token) this.match(this.input,
                        SparqlParser.OPEN_SQUARE_BRACE,
                        SparqlParser.FOLLOW_OPEN_SQUARE_BRACE_in_blankNode2052);
                  OPEN_SQUARE_BRACE262_tree = (Object) this.adaptor
                        .create(OPEN_SQUARE_BRACE262);
                  this.adaptor.addChild(root_0, OPEN_SQUARE_BRACE262_tree);
                  this.dbg.location(389, 25);
                  CLOSE_SQUARE_BRACE263 = (Token) this.match(this.input,
                        SparqlParser.CLOSE_SQUARE_BRACE,
                        SparqlParser.FOLLOW_CLOSE_SQUARE_BRACE_in_blankNode2054);
                  CLOSE_SQUARE_BRACE263_tree = (Object) this.adaptor
                        .create(CLOSE_SQUARE_BRACE263);
                  this.adaptor.addChild(root_0, CLOSE_SQUARE_BRACE263_tree);
                  break;
               default:
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final Exception e) {
            throw new RecognitionException();
         } finally {
         }
         this.dbg.location(390, 5);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "blankNode");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "blankNode"

   // Delegated rules

   public static final BitSet FOLLOW_prologue_in_query40 = new BitSet(
         new long[] { 0x0000000000007100L });
   public static final BitSet FOLLOW_selectQuery_in_query44 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_constructQuery_in_query48 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_describeQuery_in_query52 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_askQuery_in_query56 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_EOF_in_query60 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_baseDecl_in_prologue77 = new BitSet(
         new long[] { 0x0000000000000042L });
   public static final BitSet FOLLOW_prefixDecl_in_prologue80 = new BitSet(
         new long[] { 0x0000000000000042L });
   public static final BitSet FOLLOW_BASE_in_baseDecl98 = new BitSet(
         new long[] { 0x0000000000000020L });
   public static final BitSet FOLLOW_IRI_REF_in_baseDecl100 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_PREFIX_in_prefixDecl117 = new BitSet(
         new long[] { 0x0000000000000080L });
   public static final BitSet FOLLOW_PNAME_NS_in_prefixDecl119 = new BitSet(
         new long[] { 0x0000000000000020L });
   public static final BitSet FOLLOW_IRI_REF_in_prefixDecl121 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_SELECT_in_selectQuery138 = new BitSet(
         new long[] { 0x0000018000000E00L });
   public static final BitSet FOLLOW_set_in_selectQuery140 = new BitSet(
         new long[] { 0x0000018000000800L });
   public static final BitSet FOLLOW_var_in_selectQuery153 = new BitSet(
         new long[] { 0x0000018002028000L });
   public static final BitSet FOLLOW_ASTERISK_in_selectQuery158 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_datasetClause_in_selectQuery162 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_whereClause_in_selectQuery165 = new BitSet(
         new long[] { 0x0000000001440000L });
   public static final BitSet FOLLOW_solutionModifier_in_selectQuery167 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_CONSTRUCT_in_constructQuery184 = new BitSet(
         new long[] { 0x0000000002000000L });
   public static final BitSet FOLLOW_constructTemplate_in_constructQuery186 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_datasetClause_in_constructQuery188 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_whereClause_in_constructQuery191 = new BitSet(
         new long[] { 0x0000000001440000L });
   public static final BitSet FOLLOW_solutionModifier_in_constructQuery193 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_DESCRIBE_in_describeQuery210 = new BitSet(new long[] {
         0x00000180000008A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_varOrIRIref_in_describeQuery214 = new BitSet(
         new long[] { 0x00000180034680A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_ASTERISK_in_describeQuery219 = new BitSet(
         new long[] { 0x0000000003468000L });
   public static final BitSet FOLLOW_datasetClause_in_describeQuery223 = new BitSet(
         new long[] { 0x0000000003468000L });
   public static final BitSet FOLLOW_whereClause_in_describeQuery226 = new BitSet(
         new long[] { 0x0000000001440000L });
   public static final BitSet FOLLOW_solutionModifier_in_describeQuery229 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ASK_in_askQuery246 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_datasetClause_in_askQuery248 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_whereClause_in_askQuery251 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_FROM_in_datasetClause268 = new BitSet(new long[] {
         0x00000180000100A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_defaultGraphClause_in_datasetClause272 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_namedGraphClause_in_datasetClause276 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_sourceSelector_in_defaultGraphClause295 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NAMED_in_namedGraphClause312 = new BitSet(new long[] {
         0x00000180000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_sourceSelector_in_namedGraphClause314 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_sourceSelector331 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_WHERE_in_whereClause348 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_whereClause351 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_orderClause_in_solutionModifier368 = new BitSet(
         new long[] { 0x0000000001400002L });
   public static final BitSet FOLLOW_limitOffsetClauses_in_solutionModifier371 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_limitClause_in_limitOffsetClauses391 = new BitSet(
         new long[] { 0x0000000001400002L });
   public static final BitSet FOLLOW_offsetClause_in_limitOffsetClauses393 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_offsetClause_in_limitOffsetClauses398 = new BitSet(
         new long[] { 0x0000000000400002L });
   public static final BitSet FOLLOW_limitClause_in_limitOffsetClauses400 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ORDER_in_orderClause420 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_BY_in_orderClause422 = new BitSet(new long[] {
         0xFFE00181003000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_orderCondition_in_orderClause424 = new BitSet(
         new long[] { 0xFFE00181003000A2L, 0x0000000000010000L });
   public static final BitSet FOLLOW_set_in_orderCondition444 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_brackettedExpression_in_orderCondition454 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_constraint_in_orderCondition466 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_orderCondition470 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LIMIT_in_limitClause489 = new BitSet(
         new long[] { 0x0000000000800000L });
   public static final BitSet FOLLOW_INTEGER_in_limitClause491 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OFFSET_in_offsetClause508 = new BitSet(
         new long[] { 0x0000000000800000L });
   public static final BitSet FOLLOW_INTEGER_in_offsetClause510 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_CURLY_BRACE_in_groupGraphPattern527 = new BitSet(
         new long[] { 0x000001A1BA8280A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_triplesBlock_in_groupGraphPattern529 = new BitSet(
         new long[] { 0x00000000BA028000L });
   public static final BitSet FOLLOW_graphPatternNotTriples_in_groupGraphPattern536 = new BitSet(
         new long[] { 0x000001A1BE8280A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_filter_in_groupGraphPattern540 = new BitSet(new long[] {
         0x000001A1BE8280A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_DOT_in_groupGraphPattern544 = new BitSet(new long[] {
         0x000001A1BA8280A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_triplesBlock_in_groupGraphPattern547 = new BitSet(
         new long[] { 0x00000000BA028000L });
   public static final BitSet FOLLOW_CLOSE_CURLY_BRACE_in_groupGraphPattern553 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesSameSubject_in_triplesBlock574 = new BitSet(
         new long[] { 0x0000000004000002L });
   public static final BitSet FOLLOW_DOT_in_triplesBlock578 = new BitSet(new long[] {
         0x000001A1008000A2L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_triplesBlock_in_triplesBlock580 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_optionalGraphPattern_in_graphPatternNotTriples601 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_groupOrUnionGraphPattern_in_graphPatternNotTriples605 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_graphGraphPattern_in_graphPatternNotTriples609 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPTIONAL_in_optionalGraphPattern626 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_optionalGraphPattern628 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GRAPH_in_graphGraphPattern645 = new BitSet(new long[] {
         0x00000180000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_varOrIRIref_in_graphGraphPattern647 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_graphGraphPattern649 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern666 = new BitSet(
         new long[] { 0x0000000040000002L });
   public static final BitSet FOLLOW_UNION_in_groupOrUnionGraphPattern670 = new BitSet(
         new long[] { 0x0000000002028000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern672 = new BitSet(
         new long[] { 0x0000000040000002L });
   public static final BitSet FOLLOW_FILTER_in_filter692 = new BitSet(new long[] {
         0xFFE00181000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_constraint_in_filter694 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_brackettedExpression_in_constraint711 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_builtInCall_in_constraint715 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_functionCall_in_constraint719 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_functionCall736 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_argList_in_functionCall738 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_argList757 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_argList759 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_argList763 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_argList765 = new BitSet(
         new long[] { 0x0000000600000000L });
   public static final BitSet FOLLOW_COMMA_in_argList769 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_argList771 = new BitSet(
         new long[] { 0x0000000600000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_argList776 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_CURLY_BRACE_in_constructTemplate795 = new BitSet(
         new long[] { 0x000001A1088000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_constructTriples_in_constructTemplate797 = new BitSet(
         new long[] { 0x0000000008000000L });
   public static final BitSet FOLLOW_CLOSE_CURLY_BRACE_in_constructTemplate800 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesSameSubject_in_constructTriples817 = new BitSet(
         new long[] { 0x0000000004000002L });
   public static final BitSet FOLLOW_DOT_in_constructTriples821 = new BitSet(new long[] {
         0x000001A1008000A2L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_constructTriples_in_constructTriples823 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrTerm_in_triplesSameSubject844 = new BitSet(
         new long[] { 0x00000190000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_triplesSameSubject846 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesNode_in_triplesSameSubject850 = new BitSet(
         new long[] { 0x00000190000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_propertyList_in_triplesSameSubject852 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_verb_in_propertyListNotEmpty869 = new BitSet(
         new long[] { 0x000001A1008000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_objectList_in_propertyListNotEmpty871 = new BitSet(
         new long[] { 0x0000000800000002L });
   public static final BitSet FOLLOW_SEMICOLON_in_propertyListNotEmpty875 = new BitSet(
         new long[] { 0x00000198000000A2L, 0x0000000000010000L });
   public static final BitSet FOLLOW_verb_in_propertyListNotEmpty879 = new BitSet(
         new long[] { 0x000001A1008000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_objectList_in_propertyListNotEmpty881 = new BitSet(
         new long[] { 0x0000000800000002L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_propertyList904 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_object_in_objectList922 = new BitSet(
         new long[] { 0x0000000400000002L });
   public static final BitSet FOLLOW_COMMA_in_objectList926 = new BitSet(new long[] {
         0x000001A1008000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_object_in_objectList928 = new BitSet(
         new long[] { 0x0000000400000002L });
   public static final BitSet FOLLOW_graphNode_in_object948 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrIRIref_in_verb965 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_A_in_verb973 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_collection_in_triplesNode990 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_blankNodePropertyList_in_triplesNode998 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_SQUARE_BRACE_in_blankNodePropertyList1015 = new BitSet(
         new long[] { 0x00000190000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_blankNodePropertyList1017 = new BitSet(
         new long[] { 0x0000004000000000L });
   public static final BitSet FOLLOW_CLOSE_SQUARE_BRACE_in_blankNodePropertyList1019 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_collection1036 = new BitSet(new long[] {
         0x000001A1008000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_graphNode_in_collection1038 = new BitSet(new long[] {
         0x000001A3008000A0L, 0x000000000003FFFCL });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_collection1041 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrTerm_in_graphNode1058 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesNode_in_graphNode1062 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_varOrTerm1079 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_graphTerm_in_varOrTerm1087 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_varOrIRIref1104 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_varOrIRIref1108 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_var0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_graphTerm1150 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_rdfLiteral_in_graphTerm1158 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteral_in_graphTerm1166 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_booleanLiteral_in_graphTerm1174 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_blankNode_in_graphTerm1182 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_graphTerm1190 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_graphTerm1192 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_conditionalOrExpression_in_expression1209 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression1226 = new BitSet(
         new long[] { 0x0000020000000002L });
   public static final BitSet FOLLOW_OR_in_conditionalOrExpression1230 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression1232 = new BitSet(
         new long[] { 0x0000020000000002L });
   public static final BitSet FOLLOW_valueLogical_in_conditionalAndExpression1252 = new BitSet(
         new long[] { 0x0000040000000002L });
   public static final BitSet FOLLOW_AND_in_conditionalAndExpression1256 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_valueLogical_in_conditionalAndExpression1258 = new BitSet(
         new long[] { 0x0000040000000002L });
   public static final BitSet FOLLOW_relationalExpression_in_valueLogical1278 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1295 = new BitSet(
         new long[] { 0x0001F80000000002L });
   public static final BitSet FOLLOW_EQUAL_in_relationalExpression1299 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1301 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NOT_EQUAL_in_relationalExpression1305 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1307 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LESS_in_relationalExpression1311 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1313 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GREATER_in_relationalExpression1317 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1319 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LESS_EQUAL_in_relationalExpression1323 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1325 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GREATER_EQUAL_in_relationalExpression1329 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1331 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_additiveExpression_in_numericExpression1351 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1368 = new BitSet(
         new long[] { 0x0006000000800002L, 0x00000000000003FCL });
   public static final BitSet FOLLOW_PLUS_in_additiveExpression1372 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1374 = new BitSet(
         new long[] { 0x0006000000800002L, 0x00000000000003FCL });
   public static final BitSet FOLLOW_MINUS_in_additiveExpression1378 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1380 = new BitSet(
         new long[] { 0x0006000000800002L, 0x00000000000003FCL });
   public static final BitSet FOLLOW_numericLiteralPositive_in_additiveExpression1384 = new BitSet(
         new long[] { 0x0006000000800002L, 0x00000000000003FCL });
   public static final BitSet FOLLOW_numericLiteralNegative_in_additiveExpression1388 = new BitSet(
         new long[] { 0x0006000000800002L, 0x00000000000003FCL });
   public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1408 = new BitSet(
         new long[] { 0x0008000000000802L });
   public static final BitSet FOLLOW_ASTERISK_in_multiplicativeExpression1412 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1414 = new BitSet(
         new long[] { 0x0008000000000802L });
   public static final BitSet FOLLOW_DIVIDE_in_multiplicativeExpression1418 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1420 = new BitSet(
         new long[] { 0x0008000000000802L });
   public static final BitSet FOLLOW_NOT_in_unaryExpression1440 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1442 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_PLUS_in_unaryExpression1450 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1452 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_MINUS_in_unaryExpression1460 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1462 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1470 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_brackettedExpression_in_primaryExpression1487 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_builtInCall_in_primaryExpression1491 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRefOrFunction_in_primaryExpression1495 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_rdfLiteral_in_primaryExpression1499 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteral_in_primaryExpression1503 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_booleanLiteral_in_primaryExpression1507 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_primaryExpression1511 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_brackettedExpression1528 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_brackettedExpression1530 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_brackettedExpression1532 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_STR_in_builtInCall1549 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1551 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1553 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1555 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LANG_in_builtInCall1563 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1565 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1567 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1569 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LANGMATCHES_in_builtInCall1577 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1579 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1581 = new BitSet(
         new long[] { 0x0000000400000000L });
   public static final BitSet FOLLOW_COMMA_in_builtInCall1583 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1585 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1587 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_DATATYPE_in_builtInCall1595 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1597 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1599 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1601 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_BOUND_in_builtInCall1609 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1611 = new BitSet(
         new long[] { 0x0000018000000000L });
   public static final BitSet FOLLOW_var_in_builtInCall1613 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1615 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_SAMETERM_in_builtInCall1623 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1625 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1627 = new BitSet(
         new long[] { 0x0000000400000000L });
   public static final BitSet FOLLOW_COMMA_in_builtInCall1629 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1631 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1633 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISIRI_in_builtInCall1641 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1643 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1645 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1647 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISURI_in_builtInCall1655 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1657 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1659 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1661 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISBLANK_in_builtInCall1669 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1671 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1673 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1675 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISLITERAL_in_builtInCall1683 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall1685 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_builtInCall1687 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall1689 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_regexExpression_in_builtInCall1697 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_REGEX_in_regexExpression1714 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_regexExpression1716 = new BitSet(
         new long[] { 0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_regexExpression1718 = new BitSet(
         new long[] { 0x0000000400000000L });
   public static final BitSet FOLLOW_COMMA_in_regexExpression1720 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_regexExpression1722 = new BitSet(
         new long[] { 0x0000000600000000L });
   public static final BitSet FOLLOW_COMMA_in_regexExpression1726 = new BitSet(new long[] {
         0xFFF60181008000A0L, 0x000000000001FFFCL });
   public static final BitSet FOLLOW_expression_in_regexExpression1728 = new BitSet(
         new long[] { 0x0000000200000000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_regexExpression1733 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_iriRefOrFunction1750 = new BitSet(
         new long[] { 0x0000000100000002L });
   public static final BitSet FOLLOW_argList_in_iriRefOrFunction1752 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_string_in_rdfLiteral1770 = new BitSet(new long[] {
         0x0000000000000002L, 0x0000000000000003L });
   public static final BitSet FOLLOW_LANGTAG_in_rdfLiteral1774 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_REFERENCE_in_rdfLiteral1780 = new BitSet(new long[] {
         0x00000180000000A0L, 0x0000000000010000L });
   public static final BitSet FOLLOW_iriRef_in_rdfLiteral1782 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralUnsigned_in_numericLiteral1804 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralPositive_in_numericLiteral1808 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralNegative_in_numericLiteral1812 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_numericLiteralUnsigned0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_numericLiteralPositive0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_numericLiteralNegative0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_booleanLiteral0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_string0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_IRI_REF_in_iriRef1994 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_prefixedName_in_iriRef2002 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_prefixedName0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_BLANK_NODE_LABEL_in_blankNode2044 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_SQUARE_BRACE_in_blankNode2052 = new BitSet(
         new long[] { 0x0000004000000000L });
   public static final BitSet FOLLOW_CLOSE_SQUARE_BRACE_in_blankNode2054 = new BitSet(
         new long[] { 0x0000000000000002L });

}
