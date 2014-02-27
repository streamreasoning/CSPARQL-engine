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

public class CSparqlParser extends DebugParser {

   public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>",
         "<UP>", "REGISTER", "QUERY", "STREAM", "AS", "QUERY_NAME", "COMPUTED_EVERY",
         "BASE", "IRI_REF", "PREFIX", "PNAME_NS", "SELECT", "DISTINCT", "REDUCED",
         "ASTERISK", "OPEN_BRACE", "CLOSE_BRACE", "CONSTRUCT", "DESCRIBE", "ASK", "FROM",
         "NAMED", "OPEN_SQUARE_BRACE", "RANGE", "CLOSE_SQUARE_BRACE", "TRIPLES", "INTEGER",
         "STEP", "TUMBLING", "TIME_RANGE", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC",
         "DESC", "LIMIT", "OFFSET", "OPEN_CURLY_BRACE", "DOT", "CLOSE_CURLY_BRACE",
         "EXISTS", "NOT_BY_WORDS", "OPTIONAL", "GRAPH", "UNION", "FILTER", "COMMA",
         "SEMICOLON", "A", "VAR1", "VAR2", "OR", "AND", "EQUAL", "NOT_EQUAL", "LESS",
         "GREATER", "LESS_EQUAL", "GREATER_EQUAL", "MINUS", "PLUS", "DIVIDE", "NOT",
         "TIMESTAMP", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "SAMETERM",
         "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "COUNT", "SUM", "MIN", "MAX", "AVG",
         "REGEX", "LANGTAG", "REFERENCE", "DECIMAL", "DOUBLE", "INTEGER_POSITIVE",
         "DECIMAL_POSITIVE", "DOUBLE_POSITIVE", "INTEGER_NEGATIVE", "DECIMAL_NEGATIVE",
         "DOUBLE_NEGATIVE", "TRUE", "FALSE", "STRING_LITERAL1", "STRING_LITERAL2",
         "STRING_LITERAL_LONG1", "STRING_LITERAL_LONG2", "PNAME_LN", "BLANK_NODE_LABEL",
         "EOL", "WS", "TIME_UNIT", "PN_PREFIX", "PN_LOCAL", "VARNAME", "PN_CHARS_BASE",
         "DIGIT", "EXPONENT", "ECHAR", "PN_CHARS_U", "PN_CHARS", "COMMENT", "ANY" };
   public static final int PREFIX = 12;
   public static final int EXPONENT = 111;
   public static final int CLOSE_SQUARE_BRACE = 27;
   public static final int GRAPH = 48;
   public static final int PNAME_LN = 101;
   public static final int REGEX = 84;
   public static final int CONSTRUCT = 20;
   public static final int COUNT = 79;
   public static final int NOT = 67;
   public static final int EOF = -1;
   public static final int VARNAME = 108;
   public static final int ISLITERAL = 78;
   public static final int EOL = 103;
   public static final int GREATER = 61;
   public static final int NOT_EQUAL = 59;
   public static final int LESS = 60;
   public static final int LANGMATCHES = 71;
   public static final int DOUBLE = 88;
   public static final int PN_CHARS_U = 113;
   public static final int BASE = 10;
   public static final int COMMENT = 115;
   public static final int STREAM = 6;
   public static final int OPEN_CURLY_BRACE = 42;
   public static final int REGISTER = 4;
   public static final int SELECT = 14;
   public static final int CLOSE_CURLY_BRACE = 44;
   public static final int DOUBLE_POSITIVE = 91;
   public static final int DIVIDE = 66;
   public static final int BOUND = 73;
   public static final int ISIRI = 75;
   public static final int A = 53;
   public static final int ASC = 38;
   public static final int BLANK_NODE_LABEL = 102;
   public static final int ASK = 22;
   public static final int COMPUTED_EVERY = 9;
   public static final int SEMICOLON = 52;
   public static final int ISBLANK = 77;
   public static final int GROUP = 34;
   public static final int WS = 104;
   public static final int NAMED = 24;
   public static final int INTEGER_POSITIVE = 89;
   public static final int STRING_LITERAL2 = 98;
   public static final int OR = 56;
   public static final int STRING_LITERAL1 = 97;
   public static final int DESCRIBE = 21;
   public static final int FILTER = 50;
   public static final int PN_CHARS = 114;
   public static final int QUERY = 5;
   public static final int DATATYPE = 72;
   public static final int LESS_EQUAL = 62;
   public static final int DOUBLE_NEGATIVE = 94;
   public static final int FROM = 23;
   public static final int FALSE = 96;
   public static final int DISTINCT = 15;
   public static final int TIMESTAMP = 68;
   public static final int LANG = 70;
   public static final int WHERE = 33;
   public static final int IRI_REF = 11;
   public static final int ORDER = 37;
   public static final int LIMIT = 40;
   public static final int MAX = 82;
   public static final int STEP = 30;
   public static final int AND = 57;
   public static final int SUM = 80;
   public static final int ASTERISK = 17;
   public static final int ISURI = 76;
   public static final int AS = 7;
   public static final int STR = 69;
   public static final int SAMETERM = 74;
   public static final int COMMA = 51;
   public static final int OFFSET = 41;
   public static final int AVG = 83;
   public static final int EQUAL = 58;
   public static final int DECIMAL_POSITIVE = 90;
   public static final int TRIPLES = 28;
   public static final int PLUS = 65;
   public static final int DIGIT = 110;
   public static final int QUERY_NAME = 8;
   public static final int EXISTS = 45;
   public static final int DOT = 43;
   public static final int INTEGER = 29;
   public static final int BY = 35;
   public static final int REDUCED = 16;
   public static final int INTEGER_NEGATIVE = 92;
   public static final int PN_LOCAL = 107;
   public static final int PNAME_NS = 13;
   public static final int TUMBLING = 31;
   public static final int RANGE = 26;
   public static final int REFERENCE = 86;
   public static final int HAVING = 36;
   public static final int CLOSE_BRACE = 19;
   public static final int MIN = 81;
   public static final int MINUS = 64;
   public static final int TIME_UNIT = 105;
   public static final int TRUE = 95;
   public static final int OPEN_SQUARE_BRACE = 25;
   public static final int UNION = 49;
   public static final int ECHAR = 112;
   public static final int OPTIONAL = 47;
   public static final int ANY = 116;
   public static final int PN_CHARS_BASE = 109;
   public static final int STRING_LITERAL_LONG2 = 100;
   public static final int DECIMAL = 87;
   public static final int VAR1 = 54;
   public static final int STRING_LITERAL_LONG1 = 99;
   public static final int VAR2 = 55;
   public static final int DECIMAL_NEGATIVE = 93;
   public static final int PN_PREFIX = 106;
   public static final int NOT_BY_WORDS = 46;
   public static final int TIME_RANGE = 32;
   public static final int DESC = 39;
   public static final int OPEN_BRACE = 18;
   public static final int GREATER_EQUAL = 63;
   public static final int LANGTAG = 85;

   // delegates
   // delegators

   public static final String[] ruleNames = new String[] { "invalidRule", "queryName",
         "primaryExpression", "rdfLiteral", "numericLiteralUnsigned", "prefixDecl",
         "blankNode", "varOrTerm", "optionalGraphPattern", "conditionalAndExpression",
         "numericExpression", "existsFunc", "valueLogical", "string", "filter", "existElt",
         "askQuery", "triplesSameSubject", "constructQuery", "range", "propertyList",
         "whereClause", "namedGraphClause", "physicalWindow", "window", "varOrIRIref",
         "verb", "selectQuery", "prefixedName", "registration", "countAggregateExpression",
         "having", "iriRef", "groupGraphPattern", "object", "sourceSelector",
         "notExistsFunc", "offsetClause", "subquery", "solutionModifier", "numericLiteral",
         "limitClause", "triplesNode", "multiplicativeExpression", "argList",
         "windowOverlap", "builtInCall", "orderCondition", "sumAggregateExpression",
         "nonExistElt", "constraint", "minAggregateExpression", "additiveExpression",
         "maxAggregateExpression", "var", "groupBy", "newVarFromExpression", "prologue",
         "unaryExpression", "describeQuery", "graphGraphPattern", "queryWithReg",
         "brackettedExpression", "queryRange", "collection", "relationalExpression",
         "query", "triplesBlock", "defaultGraphClause", "constructTriples", "timeRange",
         "numericLiteralNegative", "propertyListNotEmpty", "numericLiteralPositive",
         "regexExpression", "timestampCall", "aggregate", "expression", "baseDecl",
         "conditionalOrExpression", "datasetClause", "logicalWindow", "booleanLiteral",
         "functionCall", "renamedVar", "datasetClauseStream", "blankNodePropertyList",
         "graphPatternNotTriples", "groupOrUnionGraphPattern", "datasetClauseStd",
         "avgAggregateExpression", "orderClause", "limitOffsetClauses", "objectList",
         "constructTemplate", "graphTerm", "iriRefOrFunction", "graphNode" };

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

   public CSparqlParser(final TokenStream input) {
      this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
   }

   public CSparqlParser(final TokenStream input, final int port,
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

   public CSparqlParser(final TokenStream input, final DebugEventListener dbg) {
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
      return CSparqlParser.tokenNames;
   }

   @Override
   public String getGrammarFileName() {
      return "CSparql.g";
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

   public static class queryWithReg_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "queryWithReg"
   // CSparql.g:50:1: queryWithReg : ( registration )? query EOF ;
   public final CSparqlParser.queryWithReg_return queryWithReg() throws RecognitionException {
      final CSparqlParser.queryWithReg_return retval = new CSparqlParser.queryWithReg_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EOF3 = null;
      CSparqlParser.registration_return registration1 = null;

      CSparqlParser.query_return query2 = null;

      Object EOF3_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "queryWithReg");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(50, 1);

         try {
            // CSparql.g:51:2: ( ( registration )? query EOF )
            this.dbg.enterAlt(1);

            // CSparql.g:51:4: ( registration )? query EOF
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(51, 4);
               // CSparql.g:51:4: ( registration )?
               int alt1 = 2;
               try {
                  this.dbg.enterSubRule(1);
                  try {
                     this.dbg.enterDecision(1);

                     final int LA1_0 = this.input.LA(1);

                     if (LA1_0 == CSparqlParser.REGISTER) {
                        alt1 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(1);
                  }

                  switch (alt1) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:51:4: registration
                        {
                           this.dbg.location(51, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_registration_in_queryWithReg65);
                           registration1 = this.registration();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, registration1.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(1);
               }

               this.dbg.location(51, 18);
               this.pushFollow(CSparqlParser.FOLLOW_query_in_queryWithReg68);
               query2 = this.query();

               this.state._fsp--;

               this.adaptor.addChild(root_0, query2.getTree());
               this.dbg.location(51, 24);
               EOF3 = (Token) this.match(this.input, CSparqlParser.EOF,
                     CSparqlParser.FOLLOW_EOF_in_queryWithReg70);
               EOF3_tree = this.adaptor.create(EOF3);
               this.adaptor.addChild(root_0, EOF3_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(52, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "queryWithReg");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "queryWithReg"

   public static class registration_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "registration"
   // CSparql.g:54:1: registration : REGISTER ( QUERY | STREAM ) queryName ( queryRange )? AS
   // ;
   public final CSparqlParser.registration_return registration() throws RecognitionException {
      final CSparqlParser.registration_return retval = new CSparqlParser.registration_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token REGISTER4 = null;
      Token set5 = null;
      Token AS8 = null;
      CSparqlParser.queryName_return queryName6 = null;

      CSparqlParser.queryRange_return queryRange7 = null;

      Object REGISTER4_tree = null;
      final Object set5_tree = null;
      Object AS8_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "registration");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(54, 1);

         try {
            // CSparql.g:55:2: ( REGISTER ( QUERY | STREAM ) queryName ( queryRange )? AS )
            this.dbg.enterAlt(1);

            // CSparql.g:55:4: REGISTER ( QUERY | STREAM ) queryName ( queryRange )? AS
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(55, 4);
               REGISTER4 = (Token) this.match(this.input, CSparqlParser.REGISTER,
                     CSparqlParser.FOLLOW_REGISTER_in_registration82);
               REGISTER4_tree = this.adaptor.create(REGISTER4);
               this.adaptor.addChild(root_0, REGISTER4_tree);

               this.dbg.location(55, 13);
               set5 = this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.QUERY
                     && this.input.LA(1) <= CSparqlParser.STREAM) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, this.adaptor.create(set5));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

               this.dbg.location(55, 28);
               this.pushFollow(CSparqlParser.FOLLOW_queryName_in_registration90);
               queryName6 = this.queryName();

               this.state._fsp--;

               this.adaptor.addChild(root_0, queryName6.getTree());
               this.dbg.location(55, 38);
               // CSparql.g:55:38: ( queryRange )?
               int alt2 = 2;
               try {
                  this.dbg.enterSubRule(2);
                  try {
                     this.dbg.enterDecision(2);

                     final int LA2_0 = this.input.LA(1);

                     if (LA2_0 == CSparqlParser.COMPUTED_EVERY) {
                        alt2 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(2);
                  }

                  switch (alt2) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:55:38: queryRange
                        {
                           this.dbg.location(55, 38);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_queryRange_in_registration92);
                           queryRange7 = this.queryRange();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, queryRange7.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(2);
               }

               this.dbg.location(55, 50);
               AS8 = (Token) this.match(this.input, CSparqlParser.AS,
                     CSparqlParser.FOLLOW_AS_in_registration95);
               AS8_tree = this.adaptor.create(AS8);
               this.adaptor.addChild(root_0, AS8_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(56, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "registration");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "registration"

   public static class query_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "query"
   // CSparql.g:57:1: query : prologue ( selectQuery | constructQuery | describeQuery |
   // askQuery ) ;
   public final CSparqlParser.query_return query() throws RecognitionException {
      final CSparqlParser.query_return retval = new CSparqlParser.query_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.prologue_return prologue9 = null;

      CSparqlParser.selectQuery_return selectQuery10 = null;

      CSparqlParser.constructQuery_return constructQuery11 = null;

      CSparqlParser.describeQuery_return describeQuery12 = null;

      CSparqlParser.askQuery_return askQuery13 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "query");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(57, 1);

         try {
            // CSparql.g:58:5: ( prologue ( selectQuery | constructQuery | describeQuery |
            // askQuery ) )
            this.dbg.enterAlt(1);

            // CSparql.g:58:7: prologue ( selectQuery | constructQuery | describeQuery |
            // askQuery )
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(58, 7);
               this.pushFollow(CSparqlParser.FOLLOW_prologue_in_query108);
               prologue9 = this.prologue();

               this.state._fsp--;

               this.adaptor.addChild(root_0, prologue9.getTree());
               this.dbg.location(58, 16);
               // CSparql.g:58:16: ( selectQuery | constructQuery | describeQuery | askQuery
               // )
               int alt3 = 4;
               try {
                  this.dbg.enterSubRule(3);
                  try {
                     this.dbg.enterDecision(3);

                     switch (this.input.LA(1)) {
                        case SELECT: {
                           alt3 = 1;
                        }
                           break;
                        case CONSTRUCT: {
                           alt3 = 2;
                        }
                           break;
                        case DESCRIBE: {
                           alt3 = 3;
                        }
                           break;
                        case ASK: {
                           alt3 = 4;
                        }
                           break;
                        default:
                           final NoViableAltException nvae = new NoViableAltException("", 3,
                                 0, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                     }

                  } finally {
                     this.dbg.exitDecision(3);
                  }

                  switch (alt3) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:58:18: selectQuery
                        {
                           this.dbg.location(58, 18);
                           this.pushFollow(CSparqlParser.FOLLOW_selectQuery_in_query112);
                           selectQuery10 = this.selectQuery();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, selectQuery10.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:58:32: constructQuery
                        {
                           this.dbg.location(58, 32);
                           this.pushFollow(CSparqlParser.FOLLOW_constructQuery_in_query116);
                           constructQuery11 = this.constructQuery();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, constructQuery11.getTree());

                        }
                        break;
                     case 3:
                        this.dbg.enterAlt(3);

                        // CSparql.g:58:49: describeQuery
                        {
                           this.dbg.location(58, 49);
                           this.pushFollow(CSparqlParser.FOLLOW_describeQuery_in_query120);
                           describeQuery12 = this.describeQuery();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, describeQuery12.getTree());

                        }
                        break;
                     case 4:
                        this.dbg.enterAlt(4);

                        // CSparql.g:58:65: askQuery
                        {
                           this.dbg.location(58, 65);
                           this.pushFollow(CSparqlParser.FOLLOW_askQuery_in_query124);
                           askQuery13 = this.askQuery();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, askQuery13.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(3);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(59, 5);

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

   public static class queryName_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "queryName"
   // CSparql.g:60:1: queryName : QUERY_NAME ;
   public final CSparqlParser.queryName_return queryName() throws RecognitionException {
      final CSparqlParser.queryName_return retval = new CSparqlParser.queryName_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token QUERY_NAME14 = null;

      Object QUERY_NAME14_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "queryName");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(60, 1);

         try {
            // CSparql.g:61:2: ( QUERY_NAME )
            this.dbg.enterAlt(1);

            // CSparql.g:61:4: QUERY_NAME
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(61, 4);
               QUERY_NAME14 = (Token) this.match(this.input, CSparqlParser.QUERY_NAME,
                     CSparqlParser.FOLLOW_QUERY_NAME_in_queryName139);
               QUERY_NAME14_tree = this.adaptor.create(QUERY_NAME14);
               this.adaptor.addChild(root_0, QUERY_NAME14_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(62, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "queryName");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "queryName"

   public static class queryRange_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "queryRange"
   // CSparql.g:63:1: queryRange : COMPUTED_EVERY timeRange ;
   public final CSparqlParser.queryRange_return queryRange() throws RecognitionException {
      final CSparqlParser.queryRange_return retval = new CSparqlParser.queryRange_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token COMPUTED_EVERY15 = null;
      CSparqlParser.timeRange_return timeRange16 = null;

      Object COMPUTED_EVERY15_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "queryRange");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(63, 1);

         try {
            // CSparql.g:64:2: ( COMPUTED_EVERY timeRange )
            this.dbg.enterAlt(1);

            // CSparql.g:64:4: COMPUTED_EVERY timeRange
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(64, 4);
               COMPUTED_EVERY15 = (Token) this.match(this.input,
                     CSparqlParser.COMPUTED_EVERY,
                     CSparqlParser.FOLLOW_COMPUTED_EVERY_in_queryRange149);
               COMPUTED_EVERY15_tree = this.adaptor.create(COMPUTED_EVERY15);
               this.adaptor.addChild(root_0, COMPUTED_EVERY15_tree);

               this.dbg.location(64, 19);
               this.pushFollow(CSparqlParser.FOLLOW_timeRange_in_queryRange151);
               timeRange16 = this.timeRange();

               this.state._fsp--;

               this.adaptor.addChild(root_0, timeRange16.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(65, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "queryRange");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "queryRange"

   public static class prologue_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prologue"
   // CSparql.g:67:1: prologue : ( baseDecl )? ( prefixDecl )* ;
   public final CSparqlParser.prologue_return prologue() throws RecognitionException {
      final CSparqlParser.prologue_return retval = new CSparqlParser.prologue_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.baseDecl_return baseDecl17 = null;

      CSparqlParser.prefixDecl_return prefixDecl18 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prologue");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(67, 1);

         try {
            // CSparql.g:68:5: ( ( baseDecl )? ( prefixDecl )* )
            this.dbg.enterAlt(1);

            // CSparql.g:68:7: ( baseDecl )? ( prefixDecl )*
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(68, 7);
               // CSparql.g:68:7: ( baseDecl )?
               int alt4 = 2;
               try {
                  this.dbg.enterSubRule(4);
                  try {
                     this.dbg.enterDecision(4);

                     final int LA4_0 = this.input.LA(1);

                     if (LA4_0 == CSparqlParser.BASE) {
                        alt4 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(4);
                  }

                  switch (alt4) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:68:7: baseDecl
                        {
                           this.dbg.location(68, 7);
                           this.pushFollow(CSparqlParser.FOLLOW_baseDecl_in_prologue170);
                           baseDecl17 = this.baseDecl();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, baseDecl17.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(4);
               }

               this.dbg.location(68, 17);
               // CSparql.g:68:17: ( prefixDecl )*
               try {
                  this.dbg.enterSubRule(5);

                  loop5: do {
                     int alt5 = 2;
                     try {
                        this.dbg.enterDecision(5);

                        final int LA5_0 = this.input.LA(1);

                        if (LA5_0 == CSparqlParser.PREFIX) {
                           alt5 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(5);
                     }

                     switch (alt5) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:68:17: prefixDecl
                           {
                              this.dbg.location(68, 17);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_prefixDecl_in_prologue173);
                              prefixDecl18 = this.prefixDecl();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, prefixDecl18.getTree());

                           }
                           break;

                        default:
                           break loop5;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(5);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(69, 5);

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
   // CSparql.g:71:1: baseDecl : BASE IRI_REF ;
   public final CSparqlParser.baseDecl_return baseDecl() throws RecognitionException {
      final CSparqlParser.baseDecl_return retval = new CSparqlParser.baseDecl_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token BASE19 = null;
      Token IRI_REF20 = null;

      Object BASE19_tree = null;
      Object IRI_REF20_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "baseDecl");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(71, 1);

         try {
            // CSparql.g:72:5: ( BASE IRI_REF )
            this.dbg.enterAlt(1);

            // CSparql.g:72:7: BASE IRI_REF
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(72, 7);
               BASE19 = (Token) this.match(this.input, CSparqlParser.BASE,
                     CSparqlParser.FOLLOW_BASE_in_baseDecl191);
               BASE19_tree = this.adaptor.create(BASE19);
               this.adaptor.addChild(root_0, BASE19_tree);

               this.dbg.location(72, 12);
               IRI_REF20 = (Token) this.match(this.input, CSparqlParser.IRI_REF,
                     CSparqlParser.FOLLOW_IRI_REF_in_baseDecl193);
               IRI_REF20_tree = this.adaptor.create(IRI_REF20);
               this.adaptor.addChild(root_0, IRI_REF20_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(73, 5);

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
   // CSparql.g:75:1: prefixDecl : PREFIX PNAME_NS IRI_REF ;
   public final CSparqlParser.prefixDecl_return prefixDecl() throws RecognitionException {
      final CSparqlParser.prefixDecl_return retval = new CSparqlParser.prefixDecl_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token PREFIX21 = null;
      Token PNAME_NS22 = null;
      Token IRI_REF23 = null;

      Object PREFIX21_tree = null;
      Object PNAME_NS22_tree = null;
      Object IRI_REF23_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prefixDecl");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(75, 1);

         try {
            // CSparql.g:76:5: ( PREFIX PNAME_NS IRI_REF )
            this.dbg.enterAlt(1);

            // CSparql.g:76:7: PREFIX PNAME_NS IRI_REF
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(76, 7);
               PREFIX21 = (Token) this.match(this.input, CSparqlParser.PREFIX,
                     CSparqlParser.FOLLOW_PREFIX_in_prefixDecl210);
               PREFIX21_tree = this.adaptor.create(PREFIX21);
               this.adaptor.addChild(root_0, PREFIX21_tree);

               this.dbg.location(76, 14);
               PNAME_NS22 = (Token) this.match(this.input, CSparqlParser.PNAME_NS,
                     CSparqlParser.FOLLOW_PNAME_NS_in_prefixDecl212);
               PNAME_NS22_tree = this.adaptor.create(PNAME_NS22);
               this.adaptor.addChild(root_0, PNAME_NS22_tree);

               this.dbg.location(76, 23);
               IRI_REF23 = (Token) this.match(this.input, CSparqlParser.IRI_REF,
                     CSparqlParser.FOLLOW_IRI_REF_in_prefixDecl214);
               IRI_REF23_tree = this.adaptor.create(IRI_REF23);
               this.adaptor.addChild(root_0, IRI_REF23_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(77, 5);

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
   // CSparql.g:79:1: selectQuery : SELECT ( DISTINCT | REDUCED )? ( ( var | renamedVar |
   // newVarFromExpression )+ | ASTERISK ) ( datasetClause )* whereClause solutionModifier ;
   public final CSparqlParser.selectQuery_return selectQuery() throws RecognitionException {
      final CSparqlParser.selectQuery_return retval = new CSparqlParser.selectQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SELECT24 = null;
      Token set25 = null;
      Token ASTERISK29 = null;
      CSparqlParser.var_return var26 = null;

      CSparqlParser.renamedVar_return renamedVar27 = null;

      CSparqlParser.newVarFromExpression_return newVarFromExpression28 = null;

      CSparqlParser.datasetClause_return datasetClause30 = null;

      CSparqlParser.whereClause_return whereClause31 = null;

      CSparqlParser.solutionModifier_return solutionModifier32 = null;

      Object SELECT24_tree = null;
      final Object set25_tree = null;
      Object ASTERISK29_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "selectQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(79, 1);

         try {
            // CSparql.g:80:5: ( SELECT ( DISTINCT | REDUCED )? ( ( var | renamedVar |
            // newVarFromExpression )+ | ASTERISK ) ( datasetClause )* whereClause
            // solutionModifier )
            this.dbg.enterAlt(1);

            // CSparql.g:80:7: SELECT ( DISTINCT | REDUCED )? ( ( var | renamedVar |
            // newVarFromExpression )+ | ASTERISK ) ( datasetClause )* whereClause
            // solutionModifier
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(80, 7);
               SELECT24 = (Token) this.match(this.input, CSparqlParser.SELECT,
                     CSparqlParser.FOLLOW_SELECT_in_selectQuery232);
               SELECT24_tree = this.adaptor.create(SELECT24);
               this.adaptor.addChild(root_0, SELECT24_tree);

               this.dbg.location(80, 14);
               // CSparql.g:80:14: ( DISTINCT | REDUCED )?
               int alt6 = 2;
               try {
                  this.dbg.enterSubRule(6);
                  try {
                     this.dbg.enterDecision(6);

                     final int LA6_0 = this.input.LA(1);

                     if (LA6_0 >= CSparqlParser.DISTINCT && LA6_0 <= CSparqlParser.REDUCED) {
                        alt6 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(6);
                  }

                  switch (alt6) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:
                        {
                           this.dbg.location(80, 14);
                           set25 = this.input.LT(1);
                           if (this.input.LA(1) >= CSparqlParser.DISTINCT
                                 && this.input.LA(1) <= CSparqlParser.REDUCED) {
                              this.input.consume();
                              this.adaptor.addChild(root_0, this.adaptor.create(set25));
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
                  this.dbg.exitSubRule(6);
               }

               this.dbg.location(80, 38);
               // CSparql.g:80:38: ( ( var | renamedVar | newVarFromExpression )+ | ASTERISK
               // )
               int alt8 = 2;
               try {
                  this.dbg.enterSubRule(8);
                  try {
                     this.dbg.enterDecision(8);

                     final int LA8_0 = this.input.LA(1);

                     if (LA8_0 == CSparqlParser.OPEN_BRACE || LA8_0 >= CSparqlParser.VAR1
                           && LA8_0 <= CSparqlParser.VAR2) {
                        alt8 = 1;
                     } else if (LA8_0 == CSparqlParser.ASTERISK) {
                        alt8 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 8, 0,
                              this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(8);
                  }

                  switch (alt8) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:80:40: ( var | renamedVar | newVarFromExpression )+
                        {
                           this.dbg.location(80, 40);
                           // CSparql.g:80:40: ( var | renamedVar | newVarFromExpression )+
                           int cnt7 = 0;
                           try {
                              this.dbg.enterSubRule(7);

                              loop7: do {
                                 int alt7 = 4;
                                 try {
                                    this.dbg.enterDecision(7);

                                    final int LA7_0 = this.input.LA(1);

                                    if (LA7_0 >= CSparqlParser.VAR1
                                          && LA7_0 <= CSparqlParser.VAR2) {
                                       alt7 = 1;
                                    } else if (LA7_0 == CSparqlParser.OPEN_BRACE) {
                                       final int LA7_3 = this.input.LA(2);

                                       if (LA7_3 == CSparqlParser.IRI_REF
                                             || LA7_3 == CSparqlParser.PNAME_NS
                                             || LA7_3 == CSparqlParser.OPEN_BRACE
                                             || LA7_3 == CSparqlParser.INTEGER
                                             || LA7_3 >= CSparqlParser.EXISTS
                                             && LA7_3 <= CSparqlParser.NOT_BY_WORDS
                                             || LA7_3 >= CSparqlParser.MINUS
                                             && LA7_3 <= CSparqlParser.PLUS
                                             || LA7_3 >= CSparqlParser.NOT
                                             && LA7_3 <= CSparqlParser.REGEX
                                             || LA7_3 >= CSparqlParser.DECIMAL
                                             && LA7_3 <= CSparqlParser.PNAME_LN) {
                                          alt7 = 3;
                                       } else if (LA7_3 >= CSparqlParser.VAR1
                                             && LA7_3 <= CSparqlParser.VAR2) {
                                          final int LA7_5 = this.input.LA(3);

                                          if (LA7_5 == CSparqlParser.AS) {
                                             final int LA7_6 = this.input.LA(4);

                                             if (LA7_6 >= CSparqlParser.VAR1
                                                   && LA7_6 <= CSparqlParser.VAR2) {
                                                final int LA7_7 = this.input.LA(5);

                                                if (LA7_7 == CSparqlParser.CLOSE_BRACE) {
                                                   alt7 = 2;
                                                }

                                             }

                                          } else if (LA7_5 == CSparqlParser.ASTERISK
                                                || LA7_5 >= CSparqlParser.OR
                                                && LA7_5 <= CSparqlParser.DIVIDE
                                                || LA7_5 >= CSparqlParser.INTEGER_POSITIVE
                                                && LA7_5 <= CSparqlParser.DOUBLE_NEGATIVE) {
                                             alt7 = 3;
                                          }

                                       }

                                    }

                                 } finally {
                                    this.dbg.exitDecision(7);
                                 }

                                 switch (alt7) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:80:41: var
                                       {
                                          this.dbg.location(80, 41);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_var_in_selectQuery248);
                                          var26 = this.var();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, var26.getTree());

                                       }
                                       break;
                                    case 2:
                                       this.dbg.enterAlt(2);

                                       // CSparql.g:80:47: renamedVar
                                       {
                                          this.dbg.location(80, 47);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_renamedVar_in_selectQuery252);
                                          renamedVar27 = this.renamedVar();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, renamedVar27
                                                .getTree());

                                       }
                                       break;
                                    case 3:
                                       this.dbg.enterAlt(3);

                                       // CSparql.g:80:60: newVarFromExpression
                                       {
                                          this.dbg.location(80, 60);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_newVarFromExpression_in_selectQuery256);
                                          newVarFromExpression28 = this
                                                .newVarFromExpression();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0,
                                                newVarFromExpression28.getTree());

                                       }
                                       break;

                                    default:
                                       if (cnt7 >= 1) {
                                          break loop7;
                                       }
                                       final EarlyExitException eee = new EarlyExitException(
                                             7, this.input);
                                       this.dbg.recognitionException(eee);

                                       throw eee;
                                 }
                                 cnt7++;
                              } while (true);
                           } finally {
                              this.dbg.exitSubRule(7);
                           }

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:80:86: ASTERISK
                        {
                           this.dbg.location(80, 86);
                           ASTERISK29 = (Token) this.match(this.input,
                                 CSparqlParser.ASTERISK,
                                 CSparqlParser.FOLLOW_ASTERISK_in_selectQuery263);
                           ASTERISK29_tree = this.adaptor.create(ASTERISK29);
                           this.adaptor.addChild(root_0, ASTERISK29_tree);

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(8);
               }

               this.dbg.location(80, 97);
               // CSparql.g:80:97: ( datasetClause )*
               try {
                  this.dbg.enterSubRule(9);

                  loop9: do {
                     int alt9 = 2;
                     try {
                        this.dbg.enterDecision(9);

                        final int LA9_0 = this.input.LA(1);

                        if (LA9_0 == CSparqlParser.FROM) {
                           alt9 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(9);
                     }

                     switch (alt9) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:80:97: datasetClause
                           {
                              this.dbg.location(80, 97);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_datasetClause_in_selectQuery267);
                              datasetClause30 = this.datasetClause();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, datasetClause30.getTree());

                           }
                           break;

                        default:
                           break loop9;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(9);
               }

               this.dbg.location(80, 112);
               this.pushFollow(CSparqlParser.FOLLOW_whereClause_in_selectQuery270);
               whereClause31 = this.whereClause();

               this.state._fsp--;

               this.adaptor.addChild(root_0, whereClause31.getTree());
               this.dbg.location(80, 124);
               this.pushFollow(CSparqlParser.FOLLOW_solutionModifier_in_selectQuery272);
               solutionModifier32 = this.solutionModifier();

               this.state._fsp--;

               this.adaptor.addChild(root_0, solutionModifier32.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(81, 5);

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

   public static class renamedVar_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "renamedVar"
   // CSparql.g:82:1: renamedVar : OPEN_BRACE var AS var CLOSE_BRACE ;
   public final CSparqlParser.renamedVar_return renamedVar() throws RecognitionException {
      final CSparqlParser.renamedVar_return retval = new CSparqlParser.renamedVar_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE33 = null;
      Token AS35 = null;
      Token CLOSE_BRACE37 = null;
      CSparqlParser.var_return var34 = null;

      CSparqlParser.var_return var36 = null;

      Object OPEN_BRACE33_tree = null;
      Object AS35_tree = null;
      Object CLOSE_BRACE37_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "renamedVar");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(82, 1);

         try {
            // CSparql.g:83:2: ( OPEN_BRACE var AS var CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:83:5: OPEN_BRACE var AS var CLOSE_BRACE
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(83, 5);
               OPEN_BRACE33 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_renamedVar287);
               OPEN_BRACE33_tree = this.adaptor.create(OPEN_BRACE33);
               this.adaptor.addChild(root_0, OPEN_BRACE33_tree);

               this.dbg.location(83, 16);
               this.pushFollow(CSparqlParser.FOLLOW_var_in_renamedVar289);
               var34 = this.var();

               this.state._fsp--;

               this.adaptor.addChild(root_0, var34.getTree());
               this.dbg.location(83, 20);
               AS35 = (Token) this.match(this.input, CSparqlParser.AS,
                     CSparqlParser.FOLLOW_AS_in_renamedVar291);
               AS35_tree = this.adaptor.create(AS35);
               this.adaptor.addChild(root_0, AS35_tree);

               this.dbg.location(83, 23);
               this.pushFollow(CSparqlParser.FOLLOW_var_in_renamedVar293);
               var36 = this.var();

               this.state._fsp--;

               this.adaptor.addChild(root_0, var36.getTree());
               this.dbg.location(83, 27);
               CLOSE_BRACE37 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_renamedVar295);
               CLOSE_BRACE37_tree = this.adaptor.create(CLOSE_BRACE37);
               this.adaptor.addChild(root_0, CLOSE_BRACE37_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(84, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "renamedVar");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "renamedVar"

   public static class newVarFromExpression_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "newVarFromExpression"
   // CSparql.g:85:1: newVarFromExpression : OPEN_BRACE expression AS var CLOSE_BRACE ;
   public final CSparqlParser.newVarFromExpression_return newVarFromExpression()
         throws RecognitionException {
      final CSparqlParser.newVarFromExpression_return retval = new CSparqlParser.newVarFromExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE38 = null;
      Token AS40 = null;
      Token CLOSE_BRACE42 = null;
      CSparqlParser.expression_return expression39 = null;

      CSparqlParser.var_return var41 = null;

      Object OPEN_BRACE38_tree = null;
      Object AS40_tree = null;
      Object CLOSE_BRACE42_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "newVarFromExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(85, 1);

         try {
            // CSparql.g:86:2: ( OPEN_BRACE expression AS var CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:86:4: OPEN_BRACE expression AS var CLOSE_BRACE
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(86, 4);
               OPEN_BRACE38 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_newVarFromExpression307);
               OPEN_BRACE38_tree = this.adaptor.create(OPEN_BRACE38);
               this.adaptor.addChild(root_0, OPEN_BRACE38_tree);

               this.dbg.location(86, 15);
               this.pushFollow(CSparqlParser.FOLLOW_expression_in_newVarFromExpression309);
               expression39 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression39.getTree());
               this.dbg.location(86, 26);
               AS40 = (Token) this.match(this.input, CSparqlParser.AS,
                     CSparqlParser.FOLLOW_AS_in_newVarFromExpression311);
               AS40_tree = this.adaptor.create(AS40);
               this.adaptor.addChild(root_0, AS40_tree);

               this.dbg.location(86, 29);
               this.pushFollow(CSparqlParser.FOLLOW_var_in_newVarFromExpression313);
               var41 = this.var();

               this.state._fsp--;

               this.adaptor.addChild(root_0, var41.getTree());
               this.dbg.location(86, 33);
               CLOSE_BRACE42 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_newVarFromExpression315);
               CLOSE_BRACE42_tree = this.adaptor.create(CLOSE_BRACE42);
               this.adaptor.addChild(root_0, CLOSE_BRACE42_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(87, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "newVarFromExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "newVarFromExpression"

   public static class constructQuery_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructQuery"
   // CSparql.g:89:1: constructQuery : CONSTRUCT constructTemplate ( datasetClause )*
   // whereClause solutionModifier ;
   public final CSparqlParser.constructQuery_return constructQuery()
         throws RecognitionException {
      final CSparqlParser.constructQuery_return retval = new CSparqlParser.constructQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token CONSTRUCT43 = null;
      CSparqlParser.constructTemplate_return constructTemplate44 = null;

      CSparqlParser.datasetClause_return datasetClause45 = null;

      CSparqlParser.whereClause_return whereClause46 = null;

      CSparqlParser.solutionModifier_return solutionModifier47 = null;

      Object CONSTRUCT43_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(89, 1);

         try {
            // CSparql.g:90:5: ( CONSTRUCT constructTemplate ( datasetClause )* whereClause
            // solutionModifier )
            this.dbg.enterAlt(1);

            // CSparql.g:90:7: CONSTRUCT constructTemplate ( datasetClause )* whereClause
            // solutionModifier
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(90, 7);
               CONSTRUCT43 = (Token) this.match(this.input, CSparqlParser.CONSTRUCT,
                     CSparqlParser.FOLLOW_CONSTRUCT_in_constructQuery330);
               CONSTRUCT43_tree = this.adaptor.create(CONSTRUCT43);
               this.adaptor.addChild(root_0, CONSTRUCT43_tree);

               this.dbg.location(90, 17);
               this.pushFollow(CSparqlParser.FOLLOW_constructTemplate_in_constructQuery332);
               constructTemplate44 = this.constructTemplate();

               this.state._fsp--;

               this.adaptor.addChild(root_0, constructTemplate44.getTree());
               this.dbg.location(90, 35);
               // CSparql.g:90:35: ( datasetClause )*
               try {
                  this.dbg.enterSubRule(10);

                  loop10: do {
                     int alt10 = 2;
                     try {
                        this.dbg.enterDecision(10);

                        final int LA10_0 = this.input.LA(1);

                        if (LA10_0 == CSparqlParser.FROM) {
                           alt10 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(10);
                     }

                     switch (alt10) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:90:35: datasetClause
                           {
                              this.dbg.location(90, 35);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_datasetClause_in_constructQuery334);
                              datasetClause45 = this.datasetClause();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, datasetClause45.getTree());

                           }
                           break;

                        default:
                           break loop10;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(10);
               }

               this.dbg.location(90, 50);
               this.pushFollow(CSparqlParser.FOLLOW_whereClause_in_constructQuery337);
               whereClause46 = this.whereClause();

               this.state._fsp--;

               this.adaptor.addChild(root_0, whereClause46.getTree());
               this.dbg.location(90, 62);
               this.pushFollow(CSparqlParser.FOLLOW_solutionModifier_in_constructQuery339);
               solutionModifier47 = this.solutionModifier();

               this.state._fsp--;

               this.adaptor.addChild(root_0, solutionModifier47.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(91, 5);

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
   // CSparql.g:93:1: describeQuery : DESCRIBE ( ( varOrIRIref )+ | ASTERISK ) (
   // datasetClause )* ( whereClause )? solutionModifier ;
   public final CSparqlParser.describeQuery_return describeQuery()
         throws RecognitionException {
      final CSparqlParser.describeQuery_return retval = new CSparqlParser.describeQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DESCRIBE48 = null;
      Token ASTERISK50 = null;
      CSparqlParser.varOrIRIref_return varOrIRIref49 = null;

      CSparqlParser.datasetClause_return datasetClause51 = null;

      CSparqlParser.whereClause_return whereClause52 = null;

      CSparqlParser.solutionModifier_return solutionModifier53 = null;

      Object DESCRIBE48_tree = null;
      Object ASTERISK50_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "describeQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(93, 1);

         try {
            // CSparql.g:94:5: ( DESCRIBE ( ( varOrIRIref )+ | ASTERISK ) ( datasetClause )*
            // ( whereClause )? solutionModifier )
            this.dbg.enterAlt(1);

            // CSparql.g:94:7: DESCRIBE ( ( varOrIRIref )+ | ASTERISK ) ( datasetClause )* (
            // whereClause )? solutionModifier
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(94, 7);
               DESCRIBE48 = (Token) this.match(this.input, CSparqlParser.DESCRIBE,
                     CSparqlParser.FOLLOW_DESCRIBE_in_describeQuery356);
               DESCRIBE48_tree = this.adaptor.create(DESCRIBE48);
               this.adaptor.addChild(root_0, DESCRIBE48_tree);

               this.dbg.location(94, 16);
               // CSparql.g:94:16: ( ( varOrIRIref )+ | ASTERISK )
               int alt12 = 2;
               try {
                  this.dbg.enterSubRule(12);
                  try {
                     this.dbg.enterDecision(12);

                     final int LA12_0 = this.input.LA(1);

                     if (LA12_0 == CSparqlParser.IRI_REF || LA12_0 == CSparqlParser.PNAME_NS
                           || LA12_0 >= CSparqlParser.VAR1 && LA12_0 <= CSparqlParser.VAR2
                           || LA12_0 == CSparqlParser.PNAME_LN) {
                        alt12 = 1;
                     } else if (LA12_0 == CSparqlParser.ASTERISK) {
                        alt12 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 12,
                              0, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(12);
                  }

                  switch (alt12) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:94:18: ( varOrIRIref )+
                        {
                           this.dbg.location(94, 18);
                           // CSparql.g:94:18: ( varOrIRIref )+
                           int cnt11 = 0;
                           try {
                              this.dbg.enterSubRule(11);

                              loop11: do {
                                 int alt11 = 2;
                                 try {
                                    this.dbg.enterDecision(11);

                                    final int LA11_0 = this.input.LA(1);

                                    if (LA11_0 == CSparqlParser.IRI_REF
                                          || LA11_0 == CSparqlParser.PNAME_NS
                                          || LA11_0 >= CSparqlParser.VAR1
                                          && LA11_0 <= CSparqlParser.VAR2
                                          || LA11_0 == CSparqlParser.PNAME_LN) {
                                       alt11 = 1;
                                    }

                                 } finally {
                                    this.dbg.exitDecision(11);
                                 }

                                 switch (alt11) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:94:18: varOrIRIref
                                       {
                                          this.dbg.location(94, 18);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_varOrIRIref_in_describeQuery360);
                                          varOrIRIref49 = this.varOrIRIref();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, varOrIRIref49
                                                .getTree());

                                       }
                                       break;

                                    default:
                                       if (cnt11 >= 1) {
                                          break loop11;
                                       }
                                       final EarlyExitException eee = new EarlyExitException(
                                             11, this.input);
                                       this.dbg.recognitionException(eee);

                                       throw eee;
                                 }
                                 cnt11++;
                              } while (true);
                           } finally {
                              this.dbg.exitSubRule(11);
                           }

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:94:33: ASTERISK
                        {
                           this.dbg.location(94, 33);
                           ASTERISK50 = (Token) this.match(this.input,
                                 CSparqlParser.ASTERISK,
                                 CSparqlParser.FOLLOW_ASTERISK_in_describeQuery365);
                           ASTERISK50_tree = this.adaptor.create(ASTERISK50);
                           this.adaptor.addChild(root_0, ASTERISK50_tree);

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(12);
               }

               this.dbg.location(94, 44);
               // CSparql.g:94:44: ( datasetClause )*
               try {
                  this.dbg.enterSubRule(13);

                  loop13: do {
                     int alt13 = 2;
                     try {
                        this.dbg.enterDecision(13);

                        final int LA13_0 = this.input.LA(1);

                        if (LA13_0 == CSparqlParser.FROM) {
                           alt13 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(13);
                     }

                     switch (alt13) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:94:44: datasetClause
                           {
                              this.dbg.location(94, 44);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_datasetClause_in_describeQuery369);
                              datasetClause51 = this.datasetClause();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, datasetClause51.getTree());

                           }
                           break;

                        default:
                           break loop13;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(13);
               }

               this.dbg.location(94, 59);
               // CSparql.g:94:59: ( whereClause )?
               int alt14 = 2;
               try {
                  this.dbg.enterSubRule(14);
                  try {
                     this.dbg.enterDecision(14);

                     final int LA14_0 = this.input.LA(1);

                     if (LA14_0 == CSparqlParser.WHERE
                           || LA14_0 == CSparqlParser.OPEN_CURLY_BRACE) {
                        alt14 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(14);
                  }

                  switch (alt14) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:94:59: whereClause
                        {
                           this.dbg.location(94, 59);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_whereClause_in_describeQuery372);
                           whereClause52 = this.whereClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, whereClause52.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(14);
               }

               this.dbg.location(94, 72);
               this.pushFollow(CSparqlParser.FOLLOW_solutionModifier_in_describeQuery375);
               solutionModifier53 = this.solutionModifier();

               this.state._fsp--;

               this.adaptor.addChild(root_0, solutionModifier53.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(95, 5);

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
   // CSparql.g:97:1: askQuery : ASK ( datasetClause )* whereClause ;
   public final CSparqlParser.askQuery_return askQuery() throws RecognitionException {
      final CSparqlParser.askQuery_return retval = new CSparqlParser.askQuery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ASK54 = null;
      CSparqlParser.datasetClause_return datasetClause55 = null;

      CSparqlParser.whereClause_return whereClause56 = null;

      Object ASK54_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "askQuery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(97, 1);

         try {
            // CSparql.g:98:5: ( ASK ( datasetClause )* whereClause )
            this.dbg.enterAlt(1);

            // CSparql.g:98:7: ASK ( datasetClause )* whereClause
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(98, 7);
               ASK54 = (Token) this.match(this.input, CSparqlParser.ASK,
                     CSparqlParser.FOLLOW_ASK_in_askQuery392);
               ASK54_tree = this.adaptor.create(ASK54);
               this.adaptor.addChild(root_0, ASK54_tree);

               this.dbg.location(98, 11);
               // CSparql.g:98:11: ( datasetClause )*
               try {
                  this.dbg.enterSubRule(15);

                  loop15: do {
                     int alt15 = 2;
                     try {
                        this.dbg.enterDecision(15);

                        final int LA15_0 = this.input.LA(1);

                        if (LA15_0 == CSparqlParser.FROM) {
                           alt15 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(15);
                     }

                     switch (alt15) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:98:11: datasetClause
                           {
                              this.dbg.location(98, 11);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_datasetClause_in_askQuery394);
                              datasetClause55 = this.datasetClause();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, datasetClause55.getTree());

                           }
                           break;

                        default:
                           break loop15;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(15);
               }

               this.dbg.location(98, 26);
               this.pushFollow(CSparqlParser.FOLLOW_whereClause_in_askQuery397);
               whereClause56 = this.whereClause();

               this.state._fsp--;

               this.adaptor.addChild(root_0, whereClause56.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(99, 5);

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
   // CSparql.g:101:1: datasetClause : ( datasetClauseStd | datasetClauseStream );
   public final CSparqlParser.datasetClause_return datasetClause()
         throws RecognitionException {
      final CSparqlParser.datasetClause_return retval = new CSparqlParser.datasetClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.datasetClauseStd_return datasetClauseStd57 = null;

      CSparqlParser.datasetClauseStream_return datasetClauseStream58 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "datasetClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(101, 1);

         try {
            // CSparql.g:102:5: ( datasetClauseStd | datasetClauseStream )
            int alt16 = 2;
            try {
               this.dbg.enterDecision(16);

               final int LA16_0 = this.input.LA(1);

               if (LA16_0 == CSparqlParser.FROM) {
                  switch (this.input.LA(2)) {
                     case IRI_REF:
                     case PNAME_NS:
                     case PNAME_LN: {
                        alt16 = 1;
                     }
                        break;
                     case NAMED: {
                        final int LA16_3 = this.input.LA(3);

                        if (LA16_3 == CSparqlParser.IRI_REF
                              || LA16_3 == CSparqlParser.PNAME_NS
                              || LA16_3 == CSparqlParser.PNAME_LN) {
                           alt16 = 1;
                        } else if (LA16_3 == CSparqlParser.STREAM) {
                           alt16 = 2;
                        } else {
                           final NoViableAltException nvae = new NoViableAltException("",
                                 16, 3, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                        }
                     }
                        break;
                     case STREAM: {
                        alt16 = 2;
                     }
                        break;
                     default:
                        final NoViableAltException nvae = new NoViableAltException("", 16,
                              1, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                  }

               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 16, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(16);
            }

            switch (alt16) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:102:7: datasetClauseStd
                  {
                     root_0 = this.adaptor.nil();

                     this.dbg.location(102, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_datasetClauseStd_in_datasetClause415);
                     datasetClauseStd57 = this.datasetClauseStd();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, datasetClauseStd57.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:103:7: datasetClauseStream
                  {
                     root_0 = this.adaptor.nil();

                     this.dbg.location(103, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_datasetClauseStream_in_datasetClause423);
                     datasetClauseStream58 = this.datasetClauseStream();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, datasetClauseStream58.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(104, 5);

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

   public static class datasetClauseStd_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "datasetClauseStd"
   // CSparql.g:106:1: datasetClauseStd : FROM ( defaultGraphClause | namedGraphClause ) ;
   public final CSparqlParser.datasetClauseStd_return datasetClauseStd()
         throws RecognitionException {
      final CSparqlParser.datasetClauseStd_return retval = new CSparqlParser.datasetClauseStd_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FROM59 = null;
      CSparqlParser.defaultGraphClause_return defaultGraphClause60 = null;

      CSparqlParser.namedGraphClause_return namedGraphClause61 = null;

      Object FROM59_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "datasetClauseStd");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(106, 1);

         try {
            // CSparql.g:107:2: ( FROM ( defaultGraphClause | namedGraphClause ) )
            this.dbg.enterAlt(1);

            // CSparql.g:107:4: FROM ( defaultGraphClause | namedGraphClause )
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(107, 4);
               FROM59 = (Token) this.match(this.input, CSparqlParser.FROM,
                     CSparqlParser.FOLLOW_FROM_in_datasetClauseStd437);
               FROM59_tree = this.adaptor.create(FROM59);
               this.adaptor.addChild(root_0, FROM59_tree);

               this.dbg.location(107, 9);
               // CSparql.g:107:9: ( defaultGraphClause | namedGraphClause )
               int alt17 = 2;
               try {
                  this.dbg.enterSubRule(17);
                  try {
                     this.dbg.enterDecision(17);

                     final int LA17_0 = this.input.LA(1);

                     if (LA17_0 == CSparqlParser.IRI_REF || LA17_0 == CSparqlParser.PNAME_NS
                           || LA17_0 == CSparqlParser.PNAME_LN) {
                        alt17 = 1;
                     } else if (LA17_0 == CSparqlParser.NAMED) {
                        alt17 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 17,
                              0, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(17);
                  }

                  switch (alt17) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:107:11: defaultGraphClause
                        {
                           this.dbg.location(107, 11);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_defaultGraphClause_in_datasetClauseStd441);
                           defaultGraphClause60 = this.defaultGraphClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, defaultGraphClause60.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:107:32: namedGraphClause
                        {
                           this.dbg.location(107, 32);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_namedGraphClause_in_datasetClauseStd445);
                           namedGraphClause61 = this.namedGraphClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, namedGraphClause61.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(17);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(108, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "datasetClauseStd");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "datasetClauseStd"

   public static class datasetClauseStream_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "datasetClauseStream"
   // CSparql.g:110:1: datasetClauseStream : FROM ( NAMED )? STREAM ( defaultGraphClause |
   // namedGraphClause ) range ;
   public final CSparqlParser.datasetClauseStream_return datasetClauseStream()
         throws RecognitionException {
      final CSparqlParser.datasetClauseStream_return retval = new CSparqlParser.datasetClauseStream_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FROM62 = null;
      Token NAMED63 = null;
      Token STREAM64 = null;
      CSparqlParser.defaultGraphClause_return defaultGraphClause65 = null;

      CSparqlParser.namedGraphClause_return namedGraphClause66 = null;

      CSparqlParser.range_return range67 = null;

      Object FROM62_tree = null;
      Object NAMED63_tree = null;
      Object STREAM64_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "datasetClauseStream");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(110, 1);

         try {
            // CSparql.g:111:2: ( FROM ( NAMED )? STREAM ( defaultGraphClause |
            // namedGraphClause ) range )
            this.dbg.enterAlt(1);

            // CSparql.g:111:4: FROM ( NAMED )? STREAM ( defaultGraphClause |
            // namedGraphClause ) range
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(111, 4);
               FROM62 = (Token) this.match(this.input, CSparqlParser.FROM,
                     CSparqlParser.FOLLOW_FROM_in_datasetClauseStream459);
               FROM62_tree = this.adaptor.create(FROM62);
               this.adaptor.addChild(root_0, FROM62_tree);

               this.dbg.location(111, 9);
               // CSparql.g:111:9: ( NAMED )?
               int alt18 = 2;
               try {
                  this.dbg.enterSubRule(18);
                  try {
                     this.dbg.enterDecision(18);

                     final int LA18_0 = this.input.LA(1);

                     if (LA18_0 == CSparqlParser.NAMED) {
                        alt18 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(18);
                  }

                  switch (alt18) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:111:9: NAMED
                        {
                           this.dbg.location(111, 9);
                           NAMED63 = (Token) this.match(this.input, CSparqlParser.NAMED,
                                 CSparqlParser.FOLLOW_NAMED_in_datasetClauseStream461);
                           NAMED63_tree = this.adaptor.create(NAMED63);
                           this.adaptor.addChild(root_0, NAMED63_tree);

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(18);
               }

               this.dbg.location(111, 16);
               STREAM64 = (Token) this.match(this.input, CSparqlParser.STREAM,
                     CSparqlParser.FOLLOW_STREAM_in_datasetClauseStream464);
               STREAM64_tree = this.adaptor.create(STREAM64);
               this.adaptor.addChild(root_0, STREAM64_tree);

               this.dbg.location(111, 23);
               // CSparql.g:111:23: ( defaultGraphClause | namedGraphClause )
               int alt19 = 2;
               try {
                  this.dbg.enterSubRule(19);
                  try {
                     this.dbg.enterDecision(19);

                     final int LA19_0 = this.input.LA(1);

                     if (LA19_0 == CSparqlParser.IRI_REF || LA19_0 == CSparqlParser.PNAME_NS
                           || LA19_0 == CSparqlParser.PNAME_LN) {
                        alt19 = 1;
                     } else if (LA19_0 == CSparqlParser.NAMED) {
                        alt19 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 19,
                              0, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(19);
                  }

                  switch (alt19) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:111:25: defaultGraphClause
                        {
                           this.dbg.location(111, 25);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_defaultGraphClause_in_datasetClauseStream468);
                           defaultGraphClause65 = this.defaultGraphClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, defaultGraphClause65.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:111:46: namedGraphClause
                        {
                           this.dbg.location(111, 46);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_namedGraphClause_in_datasetClauseStream472);
                           namedGraphClause66 = this.namedGraphClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, namedGraphClause66.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(19);
               }

               this.dbg.location(111, 65);
               this.pushFollow(CSparqlParser.FOLLOW_range_in_datasetClauseStream476);
               range67 = this.range();

               this.state._fsp--;

               this.adaptor.addChild(root_0, range67.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(112, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "datasetClauseStream");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "datasetClauseStream"

   public static class range_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "range"
   // CSparql.g:114:1: range : OPEN_SQUARE_BRACE RANGE window CLOSE_SQUARE_BRACE ;
   public final CSparqlParser.range_return range() throws RecognitionException {
      final CSparqlParser.range_return retval = new CSparqlParser.range_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_SQUARE_BRACE68 = null;
      Token RANGE69 = null;
      Token CLOSE_SQUARE_BRACE71 = null;
      CSparqlParser.window_return window70 = null;

      Object OPEN_SQUARE_BRACE68_tree = null;
      Object RANGE69_tree = null;
      Object CLOSE_SQUARE_BRACE71_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "range");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(114, 1);

         try {
            // CSparql.g:115:2: ( OPEN_SQUARE_BRACE RANGE window CLOSE_SQUARE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:115:4: OPEN_SQUARE_BRACE RANGE window CLOSE_SQUARE_BRACE
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(115, 4);
               OPEN_SQUARE_BRACE68 = (Token) this.match(this.input,
                     CSparqlParser.OPEN_SQUARE_BRACE,
                     CSparqlParser.FOLLOW_OPEN_SQUARE_BRACE_in_range488);
               OPEN_SQUARE_BRACE68_tree = this.adaptor.create(OPEN_SQUARE_BRACE68);
               this.adaptor.addChild(root_0, OPEN_SQUARE_BRACE68_tree);

               this.dbg.location(115, 22);
               RANGE69 = (Token) this.match(this.input, CSparqlParser.RANGE,
                     CSparqlParser.FOLLOW_RANGE_in_range490);
               RANGE69_tree = this.adaptor.create(RANGE69);
               this.adaptor.addChild(root_0, RANGE69_tree);

               this.dbg.location(115, 28);
               this.pushFollow(CSparqlParser.FOLLOW_window_in_range492);
               window70 = this.window();

               this.state._fsp--;

               this.adaptor.addChild(root_0, window70.getTree());
               this.dbg.location(115, 35);
               CLOSE_SQUARE_BRACE71 = (Token) this.match(this.input,
                     CSparqlParser.CLOSE_SQUARE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_SQUARE_BRACE_in_range494);
               CLOSE_SQUARE_BRACE71_tree = this.adaptor.create(CLOSE_SQUARE_BRACE71);
               this.adaptor.addChild(root_0, CLOSE_SQUARE_BRACE71_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(116, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "range");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "range"

   public static class window_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "window"
   // CSparql.g:118:1: window : ( physicalWindow | logicalWindow );
   public final CSparqlParser.window_return window() throws RecognitionException {
      final CSparqlParser.window_return retval = new CSparqlParser.window_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.physicalWindow_return physicalWindow72 = null;

      CSparqlParser.logicalWindow_return logicalWindow73 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "window");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(118, 1);

         try {
            // CSparql.g:119:2: ( physicalWindow | logicalWindow )
            int alt20 = 2;
            try {
               this.dbg.enterDecision(20);

               final int LA20_0 = this.input.LA(1);

               if (LA20_0 == CSparqlParser.TRIPLES) {
                  alt20 = 1;
               } else if (LA20_0 == CSparqlParser.TIME_RANGE) {
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

                  // CSparql.g:119:4: physicalWindow
                  {
                     root_0 = this.adaptor.nil();

                     this.dbg.location(119, 4);
                     this.pushFollow(CSparqlParser.FOLLOW_physicalWindow_in_window506);
                     physicalWindow72 = this.physicalWindow();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, physicalWindow72.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:120:4: logicalWindow
                  {
                     root_0 = this.adaptor.nil();

                     this.dbg.location(120, 4);
                     this.pushFollow(CSparqlParser.FOLLOW_logicalWindow_in_window511);
                     logicalWindow73 = this.logicalWindow();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, logicalWindow73.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(121, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "window");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "window"

   public static class logicalWindow_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "logicalWindow"
   // CSparql.g:123:1: logicalWindow : timeRange windowOverlap ;
   public final CSparqlParser.logicalWindow_return logicalWindow()
         throws RecognitionException {
      final CSparqlParser.logicalWindow_return retval = new CSparqlParser.logicalWindow_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.timeRange_return timeRange74 = null;

      CSparqlParser.windowOverlap_return windowOverlap75 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "logicalWindow");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(123, 1);

         try {
            // CSparql.g:124:2: ( timeRange windowOverlap )
            this.dbg.enterAlt(1);

            // CSparql.g:124:4: timeRange windowOverlap
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(124, 4);
               this.pushFollow(CSparqlParser.FOLLOW_timeRange_in_logicalWindow523);
               timeRange74 = this.timeRange();

               this.state._fsp--;

               this.adaptor.addChild(root_0, timeRange74.getTree());
               this.dbg.location(124, 14);
               this.pushFollow(CSparqlParser.FOLLOW_windowOverlap_in_logicalWindow525);
               windowOverlap75 = this.windowOverlap();

               this.state._fsp--;

               this.adaptor.addChild(root_0, windowOverlap75.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(125, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "logicalWindow");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "logicalWindow"

   public static class physicalWindow_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "physicalWindow"
   // CSparql.g:127:1: physicalWindow : TRIPLES INTEGER ;
   public final CSparqlParser.physicalWindow_return physicalWindow()
         throws RecognitionException {
      final CSparqlParser.physicalWindow_return retval = new CSparqlParser.physicalWindow_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token TRIPLES76 = null;
      Token INTEGER77 = null;

      Object TRIPLES76_tree = null;
      Object INTEGER77_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "physicalWindow");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(127, 1);

         try {
            // CSparql.g:128:2: ( TRIPLES INTEGER )
            this.dbg.enterAlt(1);

            // CSparql.g:128:4: TRIPLES INTEGER
            {
               root_0 = this.adaptor.nil();

               this.dbg.location(128, 4);
               TRIPLES76 = (Token) this.match(this.input, CSparqlParser.TRIPLES,
                     CSparqlParser.FOLLOW_TRIPLES_in_physicalWindow536);
               TRIPLES76_tree = (Object) this.adaptor.create(TRIPLES76);
               this.adaptor.addChild(root_0, TRIPLES76_tree);

               this.dbg.location(128, 12);
               INTEGER77 = (Token) this.match(this.input, CSparqlParser.INTEGER,
                     CSparqlParser.FOLLOW_INTEGER_in_physicalWindow538);
               INTEGER77_tree = (Object) this.adaptor.create(INTEGER77);
               this.adaptor.addChild(root_0, INTEGER77_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(129, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "physicalWindow");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "physicalWindow"

   public static class windowOverlap_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "windowOverlap"
   // CSparql.g:131:1: windowOverlap : ( STEP timeRange | TUMBLING );
   public final CSparqlParser.windowOverlap_return windowOverlap()
         throws RecognitionException {
      final CSparqlParser.windowOverlap_return retval = new CSparqlParser.windowOverlap_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token STEP78 = null;
      Token TUMBLING80 = null;
      CSparqlParser.timeRange_return timeRange79 = null;

      Object STEP78_tree = null;
      Object TUMBLING80_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "windowOverlap");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(131, 1);

         try {
            // CSparql.g:132:2: ( STEP timeRange | TUMBLING )
            int alt21 = 2;
            try {
               this.dbg.enterDecision(21);

               final int LA21_0 = this.input.LA(1);

               if (LA21_0 == CSparqlParser.STEP) {
                  alt21 = 1;
               } else if (LA21_0 == CSparqlParser.TUMBLING) {
                  alt21 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 21, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(21);
            }

            switch (alt21) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:132:4: STEP timeRange
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(132, 4);
                     STEP78 = (Token) this.match(this.input, CSparqlParser.STEP,
                           CSparqlParser.FOLLOW_STEP_in_windowOverlap549);
                     STEP78_tree = (Object) this.adaptor.create(STEP78);
                     this.adaptor.addChild(root_0, STEP78_tree);

                     this.dbg.location(132, 9);
                     this.pushFollow(CSparqlParser.FOLLOW_timeRange_in_windowOverlap551);
                     timeRange79 = this.timeRange();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, timeRange79.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:133:4: TUMBLING
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(133, 4);
                     TUMBLING80 = (Token) this.match(this.input, CSparqlParser.TUMBLING,
                           CSparqlParser.FOLLOW_TUMBLING_in_windowOverlap556);
                     TUMBLING80_tree = (Object) this.adaptor.create(TUMBLING80);
                     this.adaptor.addChild(root_0, TUMBLING80_tree);

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(134, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "windowOverlap");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "windowOverlap"

   public static class timeRange_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "timeRange"
   // CSparql.g:135:1: timeRange : TIME_RANGE ;
   public final CSparqlParser.timeRange_return timeRange() throws RecognitionException {
      final CSparqlParser.timeRange_return retval = new CSparqlParser.timeRange_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token TIME_RANGE81 = null;

      Object TIME_RANGE81_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "timeRange");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(135, 1);

         try {
            // CSparql.g:136:2: ( TIME_RANGE )
            this.dbg.enterAlt(1);

            // CSparql.g:136:4: TIME_RANGE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(136, 4);
               TIME_RANGE81 = (Token) this.match(this.input, CSparqlParser.TIME_RANGE,
                     CSparqlParser.FOLLOW_TIME_RANGE_in_timeRange566);
               TIME_RANGE81_tree = (Object) this.adaptor.create(TIME_RANGE81);
               this.adaptor.addChild(root_0, TIME_RANGE81_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(137, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "timeRange");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "timeRange"

   public static class defaultGraphClause_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "defaultGraphClause"
   // CSparql.g:139:1: defaultGraphClause : sourceSelector ;
   public final CSparqlParser.defaultGraphClause_return defaultGraphClause()
         throws RecognitionException {
      final CSparqlParser.defaultGraphClause_return retval = new CSparqlParser.defaultGraphClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.sourceSelector_return sourceSelector82 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "defaultGraphClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(139, 1);

         try {
            // CSparql.g:140:5: ( sourceSelector )
            this.dbg.enterAlt(1);

            // CSparql.g:140:7: sourceSelector
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(140, 7);
               this.pushFollow(CSparqlParser.FOLLOW_sourceSelector_in_defaultGraphClause583);
               sourceSelector82 = this.sourceSelector();

               this.state._fsp--;

               this.adaptor.addChild(root_0, sourceSelector82.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(141, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "namedGraphClause"
   // CSparql.g:143:1: namedGraphClause : NAMED sourceSelector ;
   public final CSparqlParser.namedGraphClause_return namedGraphClause()
         throws RecognitionException {
      final CSparqlParser.namedGraphClause_return retval = new CSparqlParser.namedGraphClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NAMED83 = null;
      CSparqlParser.sourceSelector_return sourceSelector84 = null;

      Object NAMED83_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "namedGraphClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(143, 1);

         try {
            // CSparql.g:144:5: ( NAMED sourceSelector )
            this.dbg.enterAlt(1);

            // CSparql.g:144:7: NAMED sourceSelector
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(144, 7);
               NAMED83 = (Token) this.match(this.input, CSparqlParser.NAMED,
                     CSparqlParser.FOLLOW_NAMED_in_namedGraphClause600);
               NAMED83_tree = (Object) this.adaptor.create(NAMED83);
               this.adaptor.addChild(root_0, NAMED83_tree);

               this.dbg.location(144, 13);
               this.pushFollow(CSparqlParser.FOLLOW_sourceSelector_in_namedGraphClause602);
               sourceSelector84 = this.sourceSelector();

               this.state._fsp--;

               this.adaptor.addChild(root_0, sourceSelector84.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(145, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "sourceSelector"
   // CSparql.g:147:1: sourceSelector : iriRef ;
   public final CSparqlParser.sourceSelector_return sourceSelector()
         throws RecognitionException {
      final CSparqlParser.sourceSelector_return retval = new CSparqlParser.sourceSelector_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.iriRef_return iriRef85 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "sourceSelector");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(147, 1);

         try {
            // CSparql.g:148:5: ( iriRef )
            this.dbg.enterAlt(1);

            // CSparql.g:148:7: iriRef
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(148, 7);
               this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_sourceSelector619);
               iriRef85 = this.iriRef();

               this.state._fsp--;

               this.adaptor.addChild(root_0, iriRef85.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(149, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "whereClause"
   // CSparql.g:151:1: whereClause : ( WHERE )? groupGraphPattern ;
   public final CSparqlParser.whereClause_return whereClause() throws RecognitionException {
      final CSparqlParser.whereClause_return retval = new CSparqlParser.whereClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token WHERE86 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern87 = null;

      Object WHERE86_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "whereClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(151, 1);

         try {
            // CSparql.g:152:5: ( ( WHERE )? groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:152:7: ( WHERE )? groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(152, 7);
               // CSparql.g:152:7: ( WHERE )?
               int alt22 = 2;
               try {
                  this.dbg.enterSubRule(22);
                  try {
                     this.dbg.enterDecision(22);

                     final int LA22_0 = this.input.LA(1);

                     if (LA22_0 == CSparqlParser.WHERE) {
                        alt22 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(22);
                  }

                  switch (alt22) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:152:7: WHERE
                        {
                           this.dbg.location(152, 7);
                           WHERE86 = (Token) this.match(this.input, CSparqlParser.WHERE,
                                 CSparqlParser.FOLLOW_WHERE_in_whereClause636);
                           WHERE86_tree = (Object) this.adaptor.create(WHERE86);
                           this.adaptor.addChild(root_0, WHERE86_tree);

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(22);
               }

               this.dbg.location(152, 14);
               this.pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_whereClause639);
               groupGraphPattern87 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern87.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(153, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "solutionModifier"
   // CSparql.g:155:1: solutionModifier : ( groupBy )? ( orderClause )? ( limitOffsetClauses
   // )? ;
   public final CSparqlParser.solutionModifier_return solutionModifier()
         throws RecognitionException {
      final CSparqlParser.solutionModifier_return retval = new CSparqlParser.solutionModifier_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.groupBy_return groupBy88 = null;

      CSparqlParser.orderClause_return orderClause89 = null;

      CSparqlParser.limitOffsetClauses_return limitOffsetClauses90 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "solutionModifier");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(155, 1);

         try {
            // CSparql.g:156:5: ( ( groupBy )? ( orderClause )? ( limitOffsetClauses )? )
            this.dbg.enterAlt(1);

            // CSparql.g:156:7: ( groupBy )? ( orderClause )? ( limitOffsetClauses )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(156, 7);
               // CSparql.g:156:7: ( groupBy )?
               int alt23 = 2;
               try {
                  this.dbg.enterSubRule(23);
                  try {
                     this.dbg.enterDecision(23);

                     final int LA23_0 = this.input.LA(1);

                     if (LA23_0 == CSparqlParser.GROUP) {
                        alt23 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(23);
                  }

                  switch (alt23) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:156:7: groupBy
                        {
                           this.dbg.location(156, 7);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_groupBy_in_solutionModifier657);
                           groupBy88 = this.groupBy();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, groupBy88.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(23);
               }

               this.dbg.location(156, 16);
               // CSparql.g:156:16: ( orderClause )?
               int alt24 = 2;
               try {
                  this.dbg.enterSubRule(24);
                  try {
                     this.dbg.enterDecision(24);

                     final int LA24_0 = this.input.LA(1);

                     if (LA24_0 == CSparqlParser.ORDER) {
                        alt24 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(24);
                  }

                  switch (alt24) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:156:16: orderClause
                        {
                           this.dbg.location(156, 16);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_orderClause_in_solutionModifier660);
                           orderClause89 = this.orderClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, orderClause89.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(24);
               }

               this.dbg.location(156, 29);
               // CSparql.g:156:29: ( limitOffsetClauses )?
               int alt25 = 2;
               try {
                  this.dbg.enterSubRule(25);
                  try {
                     this.dbg.enterDecision(25);

                     final int LA25_0 = this.input.LA(1);

                     if (LA25_0 >= CSparqlParser.LIMIT && LA25_0 <= CSparqlParser.OFFSET) {
                        alt25 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(25);
                  }

                  switch (alt25) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:156:29: limitOffsetClauses
                        {
                           this.dbg.location(156, 29);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_limitOffsetClauses_in_solutionModifier663);
                           limitOffsetClauses90 = this.limitOffsetClauses();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, limitOffsetClauses90.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(25);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(157, 5);

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

   public static class groupBy_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "groupBy"
   // CSparql.g:159:1: groupBy : GROUP BY ( var )+ ( having )? ;
   public final CSparqlParser.groupBy_return groupBy() throws RecognitionException {
      final CSparqlParser.groupBy_return retval = new CSparqlParser.groupBy_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token GROUP91 = null;
      Token BY92 = null;
      CSparqlParser.var_return var93 = null;

      CSparqlParser.having_return having94 = null;

      Object GROUP91_tree = null;
      Object BY92_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "groupBy");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(159, 1);

         try {
            // CSparql.g:160:2: ( GROUP BY ( var )+ ( having )? )
            this.dbg.enterAlt(1);

            // CSparql.g:160:4: GROUP BY ( var )+ ( having )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(160, 4);
               GROUP91 = (Token) this.match(this.input, CSparqlParser.GROUP,
                     CSparqlParser.FOLLOW_GROUP_in_groupBy684);
               GROUP91_tree = (Object) this.adaptor.create(GROUP91);
               this.adaptor.addChild(root_0, GROUP91_tree);

               this.dbg.location(160, 10);
               BY92 = (Token) this.match(this.input, CSparqlParser.BY,
                     CSparqlParser.FOLLOW_BY_in_groupBy686);
               BY92_tree = (Object) this.adaptor.create(BY92);
               this.adaptor.addChild(root_0, BY92_tree);

               this.dbg.location(160, 13);
               // CSparql.g:160:13: ( var )+
               int cnt26 = 0;
               try {
                  this.dbg.enterSubRule(26);

                  loop26: do {
                     int alt26 = 2;
                     try {
                        this.dbg.enterDecision(26);

                        final int LA26_0 = this.input.LA(1);

                        if (LA26_0 >= CSparqlParser.VAR1 && LA26_0 <= CSparqlParser.VAR2) {
                           alt26 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(26);
                     }

                     switch (alt26) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:160:13: var
                           {
                              this.dbg.location(160, 13);
                              this.pushFollow(CSparqlParser.FOLLOW_var_in_groupBy688);
                              var93 = this.var();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, var93.getTree());

                           }
                           break;

                        default:
                           if (cnt26 >= 1) {
                              break loop26;
                           }
                           final EarlyExitException eee = new EarlyExitException(26,
                                 this.input);
                           this.dbg.recognitionException(eee);

                           throw eee;
                     }
                     cnt26++;
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(26);
               }

               this.dbg.location(160, 18);
               // CSparql.g:160:18: ( having )?
               int alt27 = 2;
               try {
                  this.dbg.enterSubRule(27);
                  try {
                     this.dbg.enterDecision(27);

                     final int LA27_0 = this.input.LA(1);

                     if (LA27_0 == CSparqlParser.HAVING) {
                        alt27 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(27);
                  }

                  switch (alt27) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:160:18: having
                        {
                           this.dbg.location(160, 18);
                           this.pushFollow(CSparqlParser.FOLLOW_having_in_groupBy691);
                           having94 = this.having();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, having94.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(27);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(161, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "groupBy");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "groupBy"

   public static class having_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "having"
   // CSparql.g:163:1: having : HAVING brackettedExpression ;
   public final CSparqlParser.having_return having() throws RecognitionException {
      final CSparqlParser.having_return retval = new CSparqlParser.having_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token HAVING95 = null;
      CSparqlParser.brackettedExpression_return brackettedExpression96 = null;

      Object HAVING95_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "having");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(163, 1);

         try {
            // CSparql.g:164:2: ( HAVING brackettedExpression )
            this.dbg.enterAlt(1);

            // CSparql.g:164:4: HAVING brackettedExpression
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(164, 4);
               HAVING95 = (Token) this.match(this.input, CSparqlParser.HAVING,
                     CSparqlParser.FOLLOW_HAVING_in_having704);
               HAVING95_tree = (Object) this.adaptor.create(HAVING95);
               this.adaptor.addChild(root_0, HAVING95_tree);

               this.dbg.location(164, 11);
               this.pushFollow(CSparqlParser.FOLLOW_brackettedExpression_in_having706);
               brackettedExpression96 = this.brackettedExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, brackettedExpression96.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(165, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "having");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "having"

   public static class limitOffsetClauses_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "limitOffsetClauses"
   // CSparql.g:167:1: limitOffsetClauses : ( limitClause ( offsetClause )? | offsetClause (
   // limitClause )? ) ;
   public final CSparqlParser.limitOffsetClauses_return limitOffsetClauses()
         throws RecognitionException {
      final CSparqlParser.limitOffsetClauses_return retval = new CSparqlParser.limitOffsetClauses_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.limitClause_return limitClause97 = null;

      CSparqlParser.offsetClause_return offsetClause98 = null;

      CSparqlParser.offsetClause_return offsetClause99 = null;

      CSparqlParser.limitClause_return limitClause100 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "limitOffsetClauses");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(167, 1);

         try {
            // CSparql.g:168:5: ( ( limitClause ( offsetClause )? | offsetClause (
            // limitClause )? ) )
            this.dbg.enterAlt(1);

            // CSparql.g:168:7: ( limitClause ( offsetClause )? | offsetClause ( limitClause
            // )? )
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(168, 7);
               // CSparql.g:168:7: ( limitClause ( offsetClause )? | offsetClause (
               // limitClause )? )
               int alt30 = 2;
               try {
                  this.dbg.enterSubRule(30);
                  try {
                     this.dbg.enterDecision(30);

                     final int LA30_0 = this.input.LA(1);

                     if (LA30_0 == CSparqlParser.LIMIT) {
                        alt30 = 1;
                     } else if (LA30_0 == CSparqlParser.OFFSET) {
                        alt30 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 30,
                              0, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(30);
                  }

                  switch (alt30) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:168:9: limitClause ( offsetClause )?
                        {
                           this.dbg.location(168, 9);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_limitClause_in_limitOffsetClauses723);
                           limitClause97 = this.limitClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, limitClause97.getTree());
                           this.dbg.location(168, 21);
                           // CSparql.g:168:21: ( offsetClause )?
                           int alt28 = 2;
                           try {
                              this.dbg.enterSubRule(28);
                              try {
                                 this.dbg.enterDecision(28);

                                 final int LA28_0 = this.input.LA(1);

                                 if (LA28_0 == CSparqlParser.OFFSET) {
                                    alt28 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(28);
                              }

                              switch (alt28) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // CSparql.g:168:21: offsetClause
                                    {
                                       this.dbg.location(168, 21);
                                       this
                                             .pushFollow(CSparqlParser.FOLLOW_offsetClause_in_limitOffsetClauses725);
                                       offsetClause98 = this.offsetClause();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, offsetClause98
                                             .getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(28);
                           }

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:168:37: offsetClause ( limitClause )?
                        {
                           this.dbg.location(168, 37);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_offsetClause_in_limitOffsetClauses730);
                           offsetClause99 = this.offsetClause();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, offsetClause99.getTree());
                           this.dbg.location(168, 50);
                           // CSparql.g:168:50: ( limitClause )?
                           int alt29 = 2;
                           try {
                              this.dbg.enterSubRule(29);
                              try {
                                 this.dbg.enterDecision(29);

                                 final int LA29_0 = this.input.LA(1);

                                 if (LA29_0 == CSparqlParser.LIMIT) {
                                    alt29 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(29);
                              }

                              switch (alt29) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // CSparql.g:168:50: limitClause
                                    {
                                       this.dbg.location(168, 50);
                                       this
                                             .pushFollow(CSparqlParser.FOLLOW_limitClause_in_limitOffsetClauses732);
                                       limitClause100 = this.limitClause();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, limitClause100
                                             .getTree());

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

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(169, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderClause"
   // CSparql.g:171:1: orderClause : ORDER BY ( orderCondition )+ ;
   public final CSparqlParser.orderClause_return orderClause() throws RecognitionException {
      final CSparqlParser.orderClause_return retval = new CSparqlParser.orderClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ORDER101 = null;
      Token BY102 = null;
      CSparqlParser.orderCondition_return orderCondition103 = null;

      Object ORDER101_tree = null;
      Object BY102_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "orderClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(171, 1);

         try {
            // CSparql.g:172:5: ( ORDER BY ( orderCondition )+ )
            this.dbg.enterAlt(1);

            // CSparql.g:172:7: ORDER BY ( orderCondition )+
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(172, 7);
               ORDER101 = (Token) this.match(this.input, CSparqlParser.ORDER,
                     CSparqlParser.FOLLOW_ORDER_in_orderClause752);
               ORDER101_tree = (Object) this.adaptor.create(ORDER101);
               this.adaptor.addChild(root_0, ORDER101_tree);

               this.dbg.location(172, 13);
               BY102 = (Token) this.match(this.input, CSparqlParser.BY,
                     CSparqlParser.FOLLOW_BY_in_orderClause754);
               BY102_tree = (Object) this.adaptor.create(BY102);
               this.adaptor.addChild(root_0, BY102_tree);

               this.dbg.location(172, 16);
               // CSparql.g:172:16: ( orderCondition )+
               int cnt31 = 0;
               try {
                  this.dbg.enterSubRule(31);

                  loop31: do {
                     int alt31 = 2;
                     try {
                        this.dbg.enterDecision(31);

                        final int LA31_0 = this.input.LA(1);

                        if (LA31_0 == CSparqlParser.IRI_REF
                              || LA31_0 == CSparqlParser.PNAME_NS
                              || LA31_0 == CSparqlParser.OPEN_BRACE
                              || LA31_0 >= CSparqlParser.ASC && LA31_0 <= CSparqlParser.DESC
                              || LA31_0 >= CSparqlParser.EXISTS
                              && LA31_0 <= CSparqlParser.NOT_BY_WORDS
                              || LA31_0 >= CSparqlParser.VAR1
                              && LA31_0 <= CSparqlParser.VAR2 || LA31_0 >= CSparqlParser.STR
                              && LA31_0 <= CSparqlParser.ISLITERAL
                              || LA31_0 == CSparqlParser.REGEX
                              || LA31_0 == CSparqlParser.PNAME_LN) {
                           alt31 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(31);
                     }

                     switch (alt31) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:172:16: orderCondition
                           {
                              this.dbg.location(172, 16);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_orderCondition_in_orderClause756);
                              orderCondition103 = this.orderCondition();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, orderCondition103.getTree());

                           }
                           break;

                        default:
                           if (cnt31 >= 1) {
                              break loop31;
                           }
                           final EarlyExitException eee = new EarlyExitException(31,
                                 this.input);
                           this.dbg.recognitionException(eee);

                           throw eee;
                     }
                     cnt31++;
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(31);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(173, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderCondition"
   // CSparql.g:175:1: orderCondition : ( ( ( ASC | DESC ) brackettedExpression ) | (
   // constraint | var ) );
   public final CSparqlParser.orderCondition_return orderCondition()
         throws RecognitionException {
      final CSparqlParser.orderCondition_return retval = new CSparqlParser.orderCondition_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set104 = null;
      CSparqlParser.brackettedExpression_return brackettedExpression105 = null;

      CSparqlParser.constraint_return constraint106 = null;

      CSparqlParser.var_return var107 = null;

      final Object set104_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "orderCondition");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(175, 1);

         try {
            // CSparql.g:176:5: ( ( ( ASC | DESC ) brackettedExpression ) | ( constraint |
            // var ) )
            int alt33 = 2;
            try {
               this.dbg.enterDecision(33);

               final int LA33_0 = this.input.LA(1);

               if (LA33_0 >= CSparqlParser.ASC && LA33_0 <= CSparqlParser.DESC) {
                  alt33 = 1;
               } else if (LA33_0 == CSparqlParser.IRI_REF
                     || LA33_0 == CSparqlParser.PNAME_NS
                     || LA33_0 == CSparqlParser.OPEN_BRACE || LA33_0 >= CSparqlParser.EXISTS
                     && LA33_0 <= CSparqlParser.NOT_BY_WORDS || LA33_0 >= CSparqlParser.VAR1
                     && LA33_0 <= CSparqlParser.VAR2 || LA33_0 >= CSparqlParser.STR
                     && LA33_0 <= CSparqlParser.ISLITERAL || LA33_0 == CSparqlParser.REGEX
                     || LA33_0 == CSparqlParser.PNAME_LN) {
                  alt33 = 2;
               } else {
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

                  // CSparql.g:176:7: ( ( ASC | DESC ) brackettedExpression )
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(176, 7);
                     // CSparql.g:176:7: ( ( ASC | DESC ) brackettedExpression )
                     this.dbg.enterAlt(1);

                     // CSparql.g:176:9: ( ASC | DESC ) brackettedExpression
                     {
                        this.dbg.location(176, 9);
                        set104 = (Token) this.input.LT(1);
                        if (this.input.LA(1) >= CSparqlParser.ASC
                              && this.input.LA(1) <= CSparqlParser.DESC) {
                           this.input.consume();
                           this.adaptor.addChild(root_0, (Object) this.adaptor
                                 .create(set104));
                           this.state.errorRecovery = false;
                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.dbg.recognitionException(mse);
                           throw mse;
                        }

                        this.dbg.location(176, 24);
                        this
                              .pushFollow(CSparqlParser.FOLLOW_brackettedExpression_in_orderCondition786);
                        brackettedExpression105 = this.brackettedExpression();

                        this.state._fsp--;

                        this.adaptor.addChild(root_0, brackettedExpression105.getTree());

                     }

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:177:7: ( constraint | var )
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(177, 7);
                     // CSparql.g:177:7: ( constraint | var )
                     int alt32 = 2;
                     try {
                        this.dbg.enterSubRule(32);
                        try {
                           this.dbg.enterDecision(32);

                           final int LA32_0 = this.input.LA(1);

                           if (LA32_0 == CSparqlParser.IRI_REF
                                 || LA32_0 == CSparqlParser.PNAME_NS
                                 || LA32_0 == CSparqlParser.OPEN_BRACE
                                 || LA32_0 >= CSparqlParser.EXISTS
                                 && LA32_0 <= CSparqlParser.NOT_BY_WORDS
                                 || LA32_0 >= CSparqlParser.STR
                                 && LA32_0 <= CSparqlParser.ISLITERAL
                                 || LA32_0 == CSparqlParser.REGEX
                                 || LA32_0 == CSparqlParser.PNAME_LN) {
                              alt32 = 1;
                           } else if (LA32_0 >= CSparqlParser.VAR1
                                 && LA32_0 <= CSparqlParser.VAR2) {
                              alt32 = 2;
                           } else {
                              final NoViableAltException nvae = new NoViableAltException("",
                                    32, 0, this.input);

                              this.dbg.recognitionException(nvae);
                              throw nvae;
                           }
                        } finally {
                           this.dbg.exitDecision(32);
                        }

                        switch (alt32) {
                           case 1:
                              this.dbg.enterAlt(1);

                              // CSparql.g:177:9: constraint
                              {
                                 this.dbg.location(177, 9);
                                 this
                                       .pushFollow(CSparqlParser.FOLLOW_constraint_in_orderCondition798);
                                 constraint106 = this.constraint();

                                 this.state._fsp--;

                                 this.adaptor.addChild(root_0, constraint106.getTree());

                              }
                              break;
                           case 2:
                              this.dbg.enterAlt(2);

                              // CSparql.g:177:22: var
                              {
                                 this.dbg.location(177, 22);
                                 this
                                       .pushFollow(CSparqlParser.FOLLOW_var_in_orderCondition802);
                                 var107 = this.var();

                                 this.state._fsp--;

                                 this.adaptor.addChild(root_0, var107.getTree());

                              }
                              break;

                        }
                     } finally {
                        this.dbg.exitSubRule(32);
                     }

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(178, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "limitClause"
   // CSparql.g:180:1: limitClause : LIMIT INTEGER ;
   public final CSparqlParser.limitClause_return limitClause() throws RecognitionException {
      final CSparqlParser.limitClause_return retval = new CSparqlParser.limitClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token LIMIT108 = null;
      Token INTEGER109 = null;

      Object LIMIT108_tree = null;
      Object INTEGER109_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "limitClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(180, 1);

         try {
            // CSparql.g:181:5: ( LIMIT INTEGER )
            this.dbg.enterAlt(1);

            // CSparql.g:181:7: LIMIT INTEGER
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(181, 7);
               LIMIT108 = (Token) this.match(this.input, CSparqlParser.LIMIT,
                     CSparqlParser.FOLLOW_LIMIT_in_limitClause821);
               LIMIT108_tree = (Object) this.adaptor.create(LIMIT108);
               this.adaptor.addChild(root_0, LIMIT108_tree);

               this.dbg.location(181, 13);
               INTEGER109 = (Token) this.match(this.input, CSparqlParser.INTEGER,
                     CSparqlParser.FOLLOW_INTEGER_in_limitClause823);
               INTEGER109_tree = (Object) this.adaptor.create(INTEGER109);
               this.adaptor.addChild(root_0, INTEGER109_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(182, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "offsetClause"
   // CSparql.g:184:1: offsetClause : OFFSET INTEGER ;
   public final CSparqlParser.offsetClause_return offsetClause() throws RecognitionException {
      final CSparqlParser.offsetClause_return retval = new CSparqlParser.offsetClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OFFSET110 = null;
      Token INTEGER111 = null;

      Object OFFSET110_tree = null;
      Object INTEGER111_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "offsetClause");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(184, 1);

         try {
            // CSparql.g:185:5: ( OFFSET INTEGER )
            this.dbg.enterAlt(1);

            // CSparql.g:185:7: OFFSET INTEGER
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(185, 7);
               OFFSET110 = (Token) this.match(this.input, CSparqlParser.OFFSET,
                     CSparqlParser.FOLLOW_OFFSET_in_offsetClause840);
               OFFSET110_tree = (Object) this.adaptor.create(OFFSET110);
               this.adaptor.addChild(root_0, OFFSET110_tree);

               this.dbg.location(185, 14);
               INTEGER111 = (Token) this.match(this.input, CSparqlParser.INTEGER,
                     CSparqlParser.FOLLOW_INTEGER_in_offsetClause842);
               INTEGER111_tree = (Object) this.adaptor.create(INTEGER111);
               this.adaptor.addChild(root_0, INTEGER111_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(186, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "groupGraphPattern"
   // CSparql.g:188:1: groupGraphPattern : OPEN_CURLY_BRACE ( triplesBlock )? ( (
   // graphPatternNotTriples | filter | subquery ) ( DOT )? ( triplesBlock )? )*
   // CLOSE_CURLY_BRACE ;
   public final CSparqlParser.groupGraphPattern_return groupGraphPattern()
         throws RecognitionException {
      final CSparqlParser.groupGraphPattern_return retval = new CSparqlParser.groupGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_CURLY_BRACE112 = null;
      Token DOT117 = null;
      Token CLOSE_CURLY_BRACE119 = null;
      CSparqlParser.triplesBlock_return triplesBlock113 = null;

      CSparqlParser.graphPatternNotTriples_return graphPatternNotTriples114 = null;

      CSparqlParser.filter_return filter115 = null;

      CSparqlParser.subquery_return subquery116 = null;

      CSparqlParser.triplesBlock_return triplesBlock118 = null;

      Object OPEN_CURLY_BRACE112_tree = null;
      Object DOT117_tree = null;
      Object CLOSE_CURLY_BRACE119_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "groupGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(188, 1);

         try {
            // CSparql.g:189:5: ( OPEN_CURLY_BRACE ( triplesBlock )? ( (
            // graphPatternNotTriples | filter | subquery ) ( DOT )? ( triplesBlock )? )*
            // CLOSE_CURLY_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:189:7: OPEN_CURLY_BRACE ( triplesBlock )? ( ( graphPatternNotTriples
            // | filter | subquery ) ( DOT )? ( triplesBlock )? )* CLOSE_CURLY_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(189, 7);
               OPEN_CURLY_BRACE112 = (Token) this.match(this.input,
                     CSparqlParser.OPEN_CURLY_BRACE,
                     CSparqlParser.FOLLOW_OPEN_CURLY_BRACE_in_groupGraphPattern860);
               OPEN_CURLY_BRACE112_tree = (Object) this.adaptor.create(OPEN_CURLY_BRACE112);
               this.adaptor.addChild(root_0, OPEN_CURLY_BRACE112_tree);

               this.dbg.location(189, 24);
               // CSparql.g:189:24: ( triplesBlock )?
               int alt34 = 2;
               try {
                  this.dbg.enterSubRule(34);
                  try {
                     this.dbg.enterDecision(34);

                     final int LA34_0 = this.input.LA(1);

                     if (LA34_0 == CSparqlParser.IRI_REF || LA34_0 == CSparqlParser.PNAME_NS
                           || LA34_0 == CSparqlParser.OPEN_BRACE
                           || LA34_0 == CSparqlParser.OPEN_SQUARE_BRACE
                           || LA34_0 == CSparqlParser.INTEGER
                           || LA34_0 >= CSparqlParser.VAR1 && LA34_0 <= CSparqlParser.VAR2
                           || LA34_0 >= CSparqlParser.DECIMAL
                           && LA34_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                        alt34 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(34);
                  }

                  switch (alt34) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:189:24: triplesBlock
                        {
                           this.dbg.location(189, 24);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_triplesBlock_in_groupGraphPattern862);
                           triplesBlock113 = this.triplesBlock();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, triplesBlock113.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(34);
               }

               this.dbg.location(189, 38);
               // CSparql.g:189:38: ( ( graphPatternNotTriples | filter | subquery ) ( DOT )?
               // ( triplesBlock )? )*
               try {
                  this.dbg.enterSubRule(38);

                  loop38: do {
                     int alt38 = 2;
                     try {
                        this.dbg.enterDecision(38);

                        final int LA38_0 = this.input.LA(1);

                        if (LA38_0 == CSparqlParser.OPEN_CURLY_BRACE
                              || LA38_0 >= CSparqlParser.EXISTS
                              && LA38_0 <= CSparqlParser.GRAPH
                              || LA38_0 == CSparqlParser.FILTER) {
                           alt38 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(38);
                     }

                     switch (alt38) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:189:40: ( graphPatternNotTriples | filter | subquery
                           // ) ( DOT )? ( triplesBlock )?
                           {
                              this.dbg.location(189, 40);
                              // CSparql.g:189:40: ( graphPatternNotTriples | filter |
                              // subquery )
                              int alt35 = 3;
                              try {
                                 this.dbg.enterSubRule(35);
                                 try {
                                    this.dbg.enterDecision(35);

                                    switch (this.input.LA(1)) {
                                       case EXISTS:
                                       case NOT_BY_WORDS:
                                       case OPTIONAL:
                                       case GRAPH: {
                                          alt35 = 1;
                                       }
                                          break;
                                       case OPEN_CURLY_BRACE: {
                                          final int LA35_2 = this.input.LA(2);

                                          if (LA35_2 == CSparqlParser.SELECT) {
                                             alt35 = 3;
                                          } else if (LA35_2 == CSparqlParser.IRI_REF
                                                || LA35_2 == CSparqlParser.PNAME_NS
                                                || LA35_2 == CSparqlParser.OPEN_BRACE
                                                || LA35_2 == CSparqlParser.OPEN_SQUARE_BRACE
                                                || LA35_2 == CSparqlParser.INTEGER
                                                || LA35_2 == CSparqlParser.OPEN_CURLY_BRACE
                                                || LA35_2 >= CSparqlParser.CLOSE_CURLY_BRACE
                                                && LA35_2 <= CSparqlParser.GRAPH
                                                || LA35_2 == CSparqlParser.FILTER
                                                || LA35_2 >= CSparqlParser.VAR1
                                                && LA35_2 <= CSparqlParser.VAR2
                                                || LA35_2 >= CSparqlParser.DECIMAL
                                                && LA35_2 <= CSparqlParser.BLANK_NODE_LABEL) {
                                             alt35 = 1;
                                          } else {
                                             final NoViableAltException nvae = new NoViableAltException(
                                                   "", 35, 2, this.input);

                                             this.dbg.recognitionException(nvae);
                                             throw nvae;
                                          }
                                       }
                                          break;
                                       case FILTER: {
                                          alt35 = 2;
                                       }
                                          break;
                                       default:
                                          final NoViableAltException nvae = new NoViableAltException(
                                                "", 35, 0, this.input);

                                          this.dbg.recognitionException(nvae);
                                          throw nvae;
                                    }

                                 } finally {
                                    this.dbg.exitDecision(35);
                                 }

                                 switch (alt35) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:189:42: graphPatternNotTriples
                                       {
                                          this.dbg.location(189, 42);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_graphPatternNotTriples_in_groupGraphPattern869);
                                          graphPatternNotTriples114 = this
                                                .graphPatternNotTriples();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0,
                                                graphPatternNotTriples114.getTree());

                                       }
                                       break;
                                    case 2:
                                       this.dbg.enterAlt(2);

                                       // CSparql.g:189:67: filter
                                       {
                                          this.dbg.location(189, 67);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_filter_in_groupGraphPattern873);
                                          filter115 = this.filter();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, filter115.getTree());

                                       }
                                       break;
                                    case 3:
                                       this.dbg.enterAlt(3);

                                       // CSparql.g:189:76: subquery
                                       {
                                          this.dbg.location(189, 76);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_subquery_in_groupGraphPattern877);
                                          subquery116 = this.subquery();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, subquery116
                                                .getTree());

                                       }
                                       break;

                                 }
                              } finally {
                                 this.dbg.exitSubRule(35);
                              }

                              this.dbg.location(189, 87);
                              // CSparql.g:189:87: ( DOT )?
                              int alt36 = 2;
                              try {
                                 this.dbg.enterSubRule(36);
                                 try {
                                    this.dbg.enterDecision(36);

                                    final int LA36_0 = this.input.LA(1);

                                    if (LA36_0 == CSparqlParser.DOT) {
                                       alt36 = 1;
                                    }
                                 } finally {
                                    this.dbg.exitDecision(36);
                                 }

                                 switch (alt36) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:189:87: DOT
                                       {
                                          this.dbg.location(189, 87);
                                          DOT117 = (Token) this
                                                .match(
                                                      this.input,
                                                      CSparqlParser.DOT,
                                                      CSparqlParser.FOLLOW_DOT_in_groupGraphPattern881);
                                          DOT117_tree = (Object) this.adaptor.create(DOT117);
                                          this.adaptor.addChild(root_0, DOT117_tree);

                                       }
                                       break;

                                 }
                              } finally {
                                 this.dbg.exitSubRule(36);
                              }

                              this.dbg.location(189, 92);
                              // CSparql.g:189:92: ( triplesBlock )?
                              int alt37 = 2;
                              try {
                                 this.dbg.enterSubRule(37);
                                 try {
                                    this.dbg.enterDecision(37);

                                    final int LA37_0 = this.input.LA(1);

                                    if (LA37_0 == CSparqlParser.IRI_REF
                                          || LA37_0 == CSparqlParser.PNAME_NS
                                          || LA37_0 == CSparqlParser.OPEN_BRACE
                                          || LA37_0 == CSparqlParser.OPEN_SQUARE_BRACE
                                          || LA37_0 == CSparqlParser.INTEGER
                                          || LA37_0 >= CSparqlParser.VAR1
                                          && LA37_0 <= CSparqlParser.VAR2
                                          || LA37_0 >= CSparqlParser.DECIMAL
                                          && LA37_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                                       alt37 = 1;
                                    }
                                 } finally {
                                    this.dbg.exitDecision(37);
                                 }

                                 switch (alt37) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:189:92: triplesBlock
                                       {
                                          this.dbg.location(189, 92);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_triplesBlock_in_groupGraphPattern884);
                                          triplesBlock118 = this.triplesBlock();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, triplesBlock118
                                                .getTree());

                                       }
                                       break;

                                 }
                              } finally {
                                 this.dbg.exitSubRule(37);
                              }

                           }
                           break;

                        default:
                           break loop38;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(38);
               }

               this.dbg.location(189, 109);
               CLOSE_CURLY_BRACE119 = (Token) this.match(this.input,
                     CSparqlParser.CLOSE_CURLY_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_CURLY_BRACE_in_groupGraphPattern890);
               CLOSE_CURLY_BRACE119_tree = (Object) this.adaptor
                     .create(CLOSE_CURLY_BRACE119);
               this.adaptor.addChild(root_0, CLOSE_CURLY_BRACE119_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(190, 5);

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

   public static class subquery_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "subquery"
   // CSparql.g:192:1: subquery : OPEN_CURLY_BRACE selectQuery CLOSE_CURLY_BRACE ;
   public final CSparqlParser.subquery_return subquery() throws RecognitionException {
      final CSparqlParser.subquery_return retval = new CSparqlParser.subquery_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_CURLY_BRACE120 = null;
      Token CLOSE_CURLY_BRACE122 = null;
      CSparqlParser.selectQuery_return selectQuery121 = null;

      Object OPEN_CURLY_BRACE120_tree = null;
      Object CLOSE_CURLY_BRACE122_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "subquery");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(192, 1);

         try {
            // CSparql.g:193:2: ( OPEN_CURLY_BRACE selectQuery CLOSE_CURLY_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:193:4: OPEN_CURLY_BRACE selectQuery CLOSE_CURLY_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(193, 4);
               OPEN_CURLY_BRACE120 = (Token) this.match(this.input,
                     CSparqlParser.OPEN_CURLY_BRACE,
                     CSparqlParser.FOLLOW_OPEN_CURLY_BRACE_in_subquery905);
               OPEN_CURLY_BRACE120_tree = (Object) this.adaptor.create(OPEN_CURLY_BRACE120);
               this.adaptor.addChild(root_0, OPEN_CURLY_BRACE120_tree);

               this.dbg.location(193, 21);
               this.pushFollow(CSparqlParser.FOLLOW_selectQuery_in_subquery907);
               selectQuery121 = this.selectQuery();

               this.state._fsp--;

               this.adaptor.addChild(root_0, selectQuery121.getTree());
               this.dbg.location(193, 33);
               CLOSE_CURLY_BRACE122 = (Token) this.match(this.input,
                     CSparqlParser.CLOSE_CURLY_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_CURLY_BRACE_in_subquery909);
               CLOSE_CURLY_BRACE122_tree = (Object) this.adaptor
                     .create(CLOSE_CURLY_BRACE122);
               this.adaptor.addChild(root_0, CLOSE_CURLY_BRACE122_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(194, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "subquery");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "subquery"

   public static class triplesBlock_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesBlock"
   // CSparql.g:196:1: triplesBlock : triplesSameSubject ( DOT ( triplesBlock )? )? ;
   public final CSparqlParser.triplesBlock_return triplesBlock() throws RecognitionException {
      final CSparqlParser.triplesBlock_return retval = new CSparqlParser.triplesBlock_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DOT124 = null;
      CSparqlParser.triplesSameSubject_return triplesSameSubject123 = null;

      CSparqlParser.triplesBlock_return triplesBlock125 = null;

      Object DOT124_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesBlock");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(196, 1);

         try {
            // CSparql.g:197:5: ( triplesSameSubject ( DOT ( triplesBlock )? )? )
            this.dbg.enterAlt(1);

            // CSparql.g:197:7: triplesSameSubject ( DOT ( triplesBlock )? )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(197, 7);
               this.pushFollow(CSparqlParser.FOLLOW_triplesSameSubject_in_triplesBlock924);
               triplesSameSubject123 = this.triplesSameSubject();

               this.state._fsp--;

               this.adaptor.addChild(root_0, triplesSameSubject123.getTree());
               this.dbg.location(197, 26);
               // CSparql.g:197:26: ( DOT ( triplesBlock )? )?
               int alt40 = 2;
               try {
                  this.dbg.enterSubRule(40);
                  try {
                     this.dbg.enterDecision(40);

                     final int LA40_0 = this.input.LA(1);

                     if (LA40_0 == CSparqlParser.DOT) {
                        alt40 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(40);
                  }

                  switch (alt40) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:197:28: DOT ( triplesBlock )?
                        {
                           this.dbg.location(197, 28);
                           DOT124 = (Token) this.match(this.input, CSparqlParser.DOT,
                                 CSparqlParser.FOLLOW_DOT_in_triplesBlock928);
                           DOT124_tree = (Object) this.adaptor.create(DOT124);
                           this.adaptor.addChild(root_0, DOT124_tree);

                           this.dbg.location(197, 32);
                           // CSparql.g:197:32: ( triplesBlock )?
                           int alt39 = 2;
                           try {
                              this.dbg.enterSubRule(39);
                              try {
                                 this.dbg.enterDecision(39);

                                 final int LA39_0 = this.input.LA(1);

                                 if (LA39_0 == CSparqlParser.IRI_REF
                                       || LA39_0 == CSparqlParser.PNAME_NS
                                       || LA39_0 == CSparqlParser.OPEN_BRACE
                                       || LA39_0 == CSparqlParser.OPEN_SQUARE_BRACE
                                       || LA39_0 == CSparqlParser.INTEGER
                                       || LA39_0 >= CSparqlParser.VAR1
                                       && LA39_0 <= CSparqlParser.VAR2
                                       || LA39_0 >= CSparqlParser.DECIMAL
                                       && LA39_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                                    alt39 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(39);
                              }

                              switch (alt39) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // CSparql.g:197:32: triplesBlock
                                    {
                                       this.dbg.location(197, 32);
                                       this
                                             .pushFollow(CSparqlParser.FOLLOW_triplesBlock_in_triplesBlock930);
                                       triplesBlock125 = this.triplesBlock();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, triplesBlock125
                                             .getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(39);
                           }

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(40);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(198, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphPatternNotTriples"
   // CSparql.g:200:1: graphPatternNotTriples : ( optionalGraphPattern |
   // groupOrUnionGraphPattern | graphGraphPattern | existElt | nonExistElt );
   public final CSparqlParser.graphPatternNotTriples_return graphPatternNotTriples()
         throws RecognitionException {
      final CSparqlParser.graphPatternNotTriples_return retval = new CSparqlParser.graphPatternNotTriples_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.optionalGraphPattern_return optionalGraphPattern126 = null;

      CSparqlParser.groupOrUnionGraphPattern_return groupOrUnionGraphPattern127 = null;

      CSparqlParser.graphGraphPattern_return graphGraphPattern128 = null;

      CSparqlParser.existElt_return existElt129 = null;

      CSparqlParser.nonExistElt_return nonExistElt130 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphPatternNotTriples");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(200, 1);

         try {
            // CSparql.g:201:5: ( optionalGraphPattern | groupOrUnionGraphPattern |
            // graphGraphPattern | existElt | nonExistElt )
            int alt41 = 5;
            try {
               this.dbg.enterDecision(41);

               switch (this.input.LA(1)) {
                  case OPTIONAL: {
                     alt41 = 1;
                  }
                     break;
                  case OPEN_CURLY_BRACE: {
                     alt41 = 2;
                  }
                     break;
                  case GRAPH: {
                     alt41 = 3;
                  }
                     break;
                  case EXISTS: {
                     alt41 = 4;
                  }
                     break;
                  case NOT_BY_WORDS: {
                     alt41 = 5;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 41, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(41);
            }

            switch (alt41) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:201:7: optionalGraphPattern
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(201, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_optionalGraphPattern_in_graphPatternNotTriples952);
                     optionalGraphPattern126 = this.optionalGraphPattern();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, optionalGraphPattern126.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:201:30: groupOrUnionGraphPattern
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(201, 30);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_groupOrUnionGraphPattern_in_graphPatternNotTriples956);
                     groupOrUnionGraphPattern127 = this.groupOrUnionGraphPattern();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, groupOrUnionGraphPattern127.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:201:57: graphGraphPattern
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(201, 57);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_graphGraphPattern_in_graphPatternNotTriples960);
                     graphGraphPattern128 = this.graphGraphPattern();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, graphGraphPattern128.getTree());

                  }
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  // CSparql.g:201:77: existElt
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(201, 77);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_existElt_in_graphPatternNotTriples964);
                     existElt129 = this.existElt();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, existElt129.getTree());

                  }
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  // CSparql.g:201:88: nonExistElt
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(201, 88);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_nonExistElt_in_graphPatternNotTriples968);
                     nonExistElt130 = this.nonExistElt();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, nonExistElt130.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(202, 5);

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

   public static class existElt_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "existElt"
   // CSparql.g:204:1: existElt : EXISTS groupGraphPattern ;
   public final CSparqlParser.existElt_return existElt() throws RecognitionException {
      final CSparqlParser.existElt_return retval = new CSparqlParser.existElt_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EXISTS131 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern132 = null;

      Object EXISTS131_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "existElt");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(204, 1);

         try {
            // CSparql.g:205:2: ( EXISTS groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:205:4: EXISTS groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(205, 4);
               EXISTS131 = (Token) this.match(this.input, CSparqlParser.EXISTS,
                     CSparqlParser.FOLLOW_EXISTS_in_existElt983);
               EXISTS131_tree = (Object) this.adaptor.create(EXISTS131);
               this.adaptor.addChild(root_0, EXISTS131_tree);

               this.dbg.location(205, 11);
               this.pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_existElt985);
               groupGraphPattern132 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern132.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(206, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "existElt");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "existElt"

   public static class nonExistElt_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "nonExistElt"
   // CSparql.g:208:1: nonExistElt : NOT_BY_WORDS EXISTS groupGraphPattern ;
   public final CSparqlParser.nonExistElt_return nonExistElt() throws RecognitionException {
      final CSparqlParser.nonExistElt_return retval = new CSparqlParser.nonExistElt_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NOT_BY_WORDS133 = null;
      Token EXISTS134 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern135 = null;

      Object NOT_BY_WORDS133_tree = null;
      Object EXISTS134_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "nonExistElt");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(208, 1);

         try {
            // CSparql.g:209:2: ( NOT_BY_WORDS EXISTS groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:209:4: NOT_BY_WORDS EXISTS groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(209, 4);
               NOT_BY_WORDS133 = (Token) this.match(this.input, CSparqlParser.NOT_BY_WORDS,
                     CSparqlParser.FOLLOW_NOT_BY_WORDS_in_nonExistElt997);
               NOT_BY_WORDS133_tree = (Object) this.adaptor.create(NOT_BY_WORDS133);
               this.adaptor.addChild(root_0, NOT_BY_WORDS133_tree);

               this.dbg.location(209, 17);
               EXISTS134 = (Token) this.match(this.input, CSparqlParser.EXISTS,
                     CSparqlParser.FOLLOW_EXISTS_in_nonExistElt999);
               EXISTS134_tree = (Object) this.adaptor.create(EXISTS134);
               this.adaptor.addChild(root_0, EXISTS134_tree);

               this.dbg.location(209, 24);
               this.pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_nonExistElt1001);
               groupGraphPattern135 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern135.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(210, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "nonExistElt");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "nonExistElt"

   public static class optionalGraphPattern_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "optionalGraphPattern"
   // CSparql.g:212:1: optionalGraphPattern : OPTIONAL groupGraphPattern ;
   public final CSparqlParser.optionalGraphPattern_return optionalGraphPattern()
         throws RecognitionException {
      final CSparqlParser.optionalGraphPattern_return retval = new CSparqlParser.optionalGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPTIONAL136 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern137 = null;

      Object OPTIONAL136_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "optionalGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(212, 1);

         try {
            // CSparql.g:213:5: ( OPTIONAL groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:213:7: OPTIONAL groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(213, 7);
               OPTIONAL136 = (Token) this.match(this.input, CSparqlParser.OPTIONAL,
                     CSparqlParser.FOLLOW_OPTIONAL_in_optionalGraphPattern1016);
               OPTIONAL136_tree = (Object) this.adaptor.create(OPTIONAL136);
               this.adaptor.addChild(root_0, OPTIONAL136_tree);

               this.dbg.location(213, 16);
               this
                     .pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_optionalGraphPattern1018);
               groupGraphPattern137 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern137.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(214, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphGraphPattern"
   // CSparql.g:216:1: graphGraphPattern : GRAPH varOrIRIref groupGraphPattern ;
   public final CSparqlParser.graphGraphPattern_return graphGraphPattern()
         throws RecognitionException {
      final CSparqlParser.graphGraphPattern_return retval = new CSparqlParser.graphGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token GRAPH138 = null;
      CSparqlParser.varOrIRIref_return varOrIRIref139 = null;

      CSparqlParser.groupGraphPattern_return groupGraphPattern140 = null;

      Object GRAPH138_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(216, 1);

         try {
            // CSparql.g:217:5: ( GRAPH varOrIRIref groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:217:7: GRAPH varOrIRIref groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(217, 7);
               GRAPH138 = (Token) this.match(this.input, CSparqlParser.GRAPH,
                     CSparqlParser.FOLLOW_GRAPH_in_graphGraphPattern1035);
               GRAPH138_tree = (Object) this.adaptor.create(GRAPH138);
               this.adaptor.addChild(root_0, GRAPH138_tree);

               this.dbg.location(217, 13);
               this.pushFollow(CSparqlParser.FOLLOW_varOrIRIref_in_graphGraphPattern1037);
               varOrIRIref139 = this.varOrIRIref();

               this.state._fsp--;

               this.adaptor.addChild(root_0, varOrIRIref139.getTree());
               this.dbg.location(217, 25);
               this
                     .pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_graphGraphPattern1039);
               groupGraphPattern140 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern140.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(218, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "groupOrUnionGraphPattern"
   // CSparql.g:220:1: groupOrUnionGraphPattern : groupGraphPattern ( UNION groupGraphPattern
   // )* ;
   public final CSparqlParser.groupOrUnionGraphPattern_return groupOrUnionGraphPattern()
         throws RecognitionException {
      final CSparqlParser.groupOrUnionGraphPattern_return retval = new CSparqlParser.groupOrUnionGraphPattern_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token UNION142 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern141 = null;

      CSparqlParser.groupGraphPattern_return groupGraphPattern143 = null;

      Object UNION142_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "groupOrUnionGraphPattern");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(220, 1);

         try {
            // CSparql.g:221:5: ( groupGraphPattern ( UNION groupGraphPattern )* )
            this.dbg.enterAlt(1);

            // CSparql.g:221:7: groupGraphPattern ( UNION groupGraphPattern )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(221, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern1060);
               groupGraphPattern141 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern141.getTree());
               this.dbg.location(221, 25);
               // CSparql.g:221:25: ( UNION groupGraphPattern )*
               try {
                  this.dbg.enterSubRule(42);

                  loop42: do {
                     int alt42 = 2;
                     try {
                        this.dbg.enterDecision(42);

                        final int LA42_0 = this.input.LA(1);

                        if (LA42_0 == CSparqlParser.UNION) {
                           alt42 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(42);
                     }

                     switch (alt42) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:221:27: UNION groupGraphPattern
                           {
                              this.dbg.location(221, 27);
                              UNION142 = (Token) this
                                    .match(
                                          this.input,
                                          CSparqlParser.UNION,
                                          CSparqlParser.FOLLOW_UNION_in_groupOrUnionGraphPattern1064);
                              UNION142_tree = (Object) this.adaptor.create(UNION142);
                              this.adaptor.addChild(root_0, UNION142_tree);

                              this.dbg.location(221, 33);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern1066);
                              groupGraphPattern143 = this.groupGraphPattern();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, groupGraphPattern143.getTree());

                           }
                           break;

                        default:
                           break loop42;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(42);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(222, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "filter"
   // CSparql.g:224:1: filter : FILTER constraint ;
   public final CSparqlParser.filter_return filter() throws RecognitionException {
      final CSparqlParser.filter_return retval = new CSparqlParser.filter_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FILTER144 = null;
      CSparqlParser.constraint_return constraint145 = null;

      Object FILTER144_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "filter");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(224, 1);

         try {
            // CSparql.g:225:5: ( FILTER constraint )
            this.dbg.enterAlt(1);

            // CSparql.g:225:7: FILTER constraint
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(225, 7);
               FILTER144 = (Token) this.match(this.input, CSparqlParser.FILTER,
                     CSparqlParser.FOLLOW_FILTER_in_filter1086);
               FILTER144_tree = (Object) this.adaptor.create(FILTER144);
               this.adaptor.addChild(root_0, FILTER144_tree);

               this.dbg.location(225, 14);
               this.pushFollow(CSparqlParser.FOLLOW_constraint_in_filter1088);
               constraint145 = this.constraint();

               this.state._fsp--;

               this.adaptor.addChild(root_0, constraint145.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(226, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constraint"
   // CSparql.g:228:1: constraint : ( brackettedExpression | builtInCall | functionCall );
   public final CSparqlParser.constraint_return constraint() throws RecognitionException {
      final CSparqlParser.constraint_return retval = new CSparqlParser.constraint_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.brackettedExpression_return brackettedExpression146 = null;

      CSparqlParser.builtInCall_return builtInCall147 = null;

      CSparqlParser.functionCall_return functionCall148 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constraint");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(228, 1);

         try {
            // CSparql.g:229:5: ( brackettedExpression | builtInCall | functionCall )
            int alt43 = 3;
            try {
               this.dbg.enterDecision(43);

               switch (this.input.LA(1)) {
                  case OPEN_BRACE: {
                     alt43 = 1;
                  }
                     break;
                  case EXISTS:
                  case NOT_BY_WORDS:
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
                  case REGEX: {
                     alt43 = 2;
                  }
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN: {
                     alt43 = 3;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 43, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(43);
            }

            switch (alt43) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:229:7: brackettedExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(229, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_brackettedExpression_in_constraint1105);
                     brackettedExpression146 = this.brackettedExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, brackettedExpression146.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:229:30: builtInCall
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(229, 30);
                     this.pushFollow(CSparqlParser.FOLLOW_builtInCall_in_constraint1109);
                     builtInCall147 = this.builtInCall();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, builtInCall147.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:229:44: functionCall
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(229, 44);
                     this.pushFollow(CSparqlParser.FOLLOW_functionCall_in_constraint1113);
                     functionCall148 = this.functionCall();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, functionCall148.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(230, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "functionCall"
   // CSparql.g:232:1: functionCall : iriRef argList ;
   public final CSparqlParser.functionCall_return functionCall() throws RecognitionException {
      final CSparqlParser.functionCall_return retval = new CSparqlParser.functionCall_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.iriRef_return iriRef149 = null;

      CSparqlParser.argList_return argList150 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "functionCall");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(232, 1);

         try {
            // CSparql.g:233:5: ( iriRef argList )
            this.dbg.enterAlt(1);

            // CSparql.g:233:7: iriRef argList
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(233, 7);
               this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_functionCall1131);
               iriRef149 = this.iriRef();

               this.state._fsp--;

               this.adaptor.addChild(root_0, iriRef149.getTree());
               this.dbg.location(233, 14);
               this.pushFollow(CSparqlParser.FOLLOW_argList_in_functionCall1133);
               argList150 = this.argList();

               this.state._fsp--;

               this.adaptor.addChild(root_0, argList150.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(234, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "argList"
   // CSparql.g:236:1: argList : ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
   // expression )* CLOSE_BRACE ) ;
   public final CSparqlParser.argList_return argList() throws RecognitionException {
      final CSparqlParser.argList_return retval = new CSparqlParser.argList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE151 = null;
      Token CLOSE_BRACE152 = null;
      Token OPEN_BRACE153 = null;
      Token COMMA155 = null;
      Token CLOSE_BRACE157 = null;
      CSparqlParser.expression_return expression154 = null;

      CSparqlParser.expression_return expression156 = null;

      Object OPEN_BRACE151_tree = null;
      Object CLOSE_BRACE152_tree = null;
      Object OPEN_BRACE153_tree = null;
      Object COMMA155_tree = null;
      Object CLOSE_BRACE157_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "argList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(236, 1);

         try {
            // CSparql.g:237:5: ( ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
            // expression )* CLOSE_BRACE ) )
            this.dbg.enterAlt(1);

            // CSparql.g:237:7: ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
            // expression )* CLOSE_BRACE )
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(237, 7);
               // CSparql.g:237:7: ( OPEN_BRACE CLOSE_BRACE | OPEN_BRACE expression ( COMMA
               // expression )* CLOSE_BRACE )
               int alt45 = 2;
               try {
                  this.dbg.enterSubRule(45);
                  try {
                     this.dbg.enterDecision(45);

                     final int LA45_0 = this.input.LA(1);

                     if (LA45_0 == CSparqlParser.OPEN_BRACE) {
                        final int LA45_1 = this.input.LA(2);

                        if (LA45_1 == CSparqlParser.CLOSE_BRACE) {
                           alt45 = 1;
                        } else if (LA45_1 == CSparqlParser.IRI_REF
                              || LA45_1 == CSparqlParser.PNAME_NS
                              || LA45_1 == CSparqlParser.OPEN_BRACE
                              || LA45_1 == CSparqlParser.INTEGER
                              || LA45_1 >= CSparqlParser.EXISTS
                              && LA45_1 <= CSparqlParser.NOT_BY_WORDS
                              || LA45_1 >= CSparqlParser.VAR1
                              && LA45_1 <= CSparqlParser.VAR2
                              || LA45_1 >= CSparqlParser.MINUS
                              && LA45_1 <= CSparqlParser.PLUS || LA45_1 >= CSparqlParser.NOT
                              && LA45_1 <= CSparqlParser.REGEX
                              || LA45_1 >= CSparqlParser.DECIMAL
                              && LA45_1 <= CSparqlParser.PNAME_LN) {
                           alt45 = 2;
                        } else {
                           final NoViableAltException nvae = new NoViableAltException("",
                                 45, 1, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                        }
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 45,
                              0, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  } finally {
                     this.dbg.exitDecision(45);
                  }

                  switch (alt45) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:237:9: OPEN_BRACE CLOSE_BRACE
                        {
                           this.dbg.location(237, 9);
                           OPEN_BRACE151 = (Token) this.match(this.input,
                                 CSparqlParser.OPEN_BRACE,
                                 CSparqlParser.FOLLOW_OPEN_BRACE_in_argList1152);
                           OPEN_BRACE151_tree = (Object) this.adaptor.create(OPEN_BRACE151);
                           this.adaptor.addChild(root_0, OPEN_BRACE151_tree);

                           this.dbg.location(237, 20);
                           CLOSE_BRACE152 = (Token) this.match(this.input,
                                 CSparqlParser.CLOSE_BRACE,
                                 CSparqlParser.FOLLOW_CLOSE_BRACE_in_argList1154);
                           CLOSE_BRACE152_tree = (Object) this.adaptor
                                 .create(CLOSE_BRACE152);
                           this.adaptor.addChild(root_0, CLOSE_BRACE152_tree);

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:237:34: OPEN_BRACE expression ( COMMA expression )*
                        // CLOSE_BRACE
                        {
                           this.dbg.location(237, 34);
                           OPEN_BRACE153 = (Token) this.match(this.input,
                                 CSparqlParser.OPEN_BRACE,
                                 CSparqlParser.FOLLOW_OPEN_BRACE_in_argList1158);
                           OPEN_BRACE153_tree = (Object) this.adaptor.create(OPEN_BRACE153);
                           this.adaptor.addChild(root_0, OPEN_BRACE153_tree);

                           this.dbg.location(237, 45);
                           this.pushFollow(CSparqlParser.FOLLOW_expression_in_argList1160);
                           expression154 = this.expression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, expression154.getTree());
                           this.dbg.location(237, 56);
                           // CSparql.g:237:56: ( COMMA expression )*
                           try {
                              this.dbg.enterSubRule(44);

                              loop44: do {
                                 int alt44 = 2;
                                 try {
                                    this.dbg.enterDecision(44);

                                    final int LA44_0 = this.input.LA(1);

                                    if (LA44_0 == CSparqlParser.COMMA) {
                                       alt44 = 1;
                                    }

                                 } finally {
                                    this.dbg.exitDecision(44);
                                 }

                                 switch (alt44) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:237:58: COMMA expression
                                       {
                                          this.dbg.location(237, 58);
                                          COMMA155 = (Token) this.match(this.input,
                                                CSparqlParser.COMMA,
                                                CSparqlParser.FOLLOW_COMMA_in_argList1164);
                                          COMMA155_tree = (Object) this.adaptor
                                                .create(COMMA155);
                                          this.adaptor.addChild(root_0, COMMA155_tree);

                                          this.dbg.location(237, 64);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_expression_in_argList1166);
                                          expression156 = this.expression();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, expression156
                                                .getTree());

                                       }
                                       break;

                                    default:
                                       break loop44;
                                 }
                              } while (true);
                           } finally {
                              this.dbg.exitSubRule(44);
                           }

                           this.dbg.location(237, 78);
                           CLOSE_BRACE157 = (Token) this.match(this.input,
                                 CSparqlParser.CLOSE_BRACE,
                                 CSparqlParser.FOLLOW_CLOSE_BRACE_in_argList1171);
                           CLOSE_BRACE157_tree = (Object) this.adaptor
                                 .create(CLOSE_BRACE157);
                           this.adaptor.addChild(root_0, CLOSE_BRACE157_tree);

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(45);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(238, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructTemplate"
   // CSparql.g:240:1: constructTemplate : OPEN_CURLY_BRACE ( constructTriples )?
   // CLOSE_CURLY_BRACE ;
   public final CSparqlParser.constructTemplate_return constructTemplate()
         throws RecognitionException {
      final CSparqlParser.constructTemplate_return retval = new CSparqlParser.constructTemplate_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_CURLY_BRACE158 = null;
      Token CLOSE_CURLY_BRACE160 = null;
      CSparqlParser.constructTriples_return constructTriples159 = null;

      Object OPEN_CURLY_BRACE158_tree = null;
      Object CLOSE_CURLY_BRACE160_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructTemplate");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(240, 1);

         try {
            // CSparql.g:241:5: ( OPEN_CURLY_BRACE ( constructTriples )? CLOSE_CURLY_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:241:7: OPEN_CURLY_BRACE ( constructTriples )? CLOSE_CURLY_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(241, 7);
               OPEN_CURLY_BRACE158 = (Token) this.match(this.input,
                     CSparqlParser.OPEN_CURLY_BRACE,
                     CSparqlParser.FOLLOW_OPEN_CURLY_BRACE_in_constructTemplate1190);
               OPEN_CURLY_BRACE158_tree = (Object) this.adaptor.create(OPEN_CURLY_BRACE158);
               this.adaptor.addChild(root_0, OPEN_CURLY_BRACE158_tree);

               this.dbg.location(241, 24);
               // CSparql.g:241:24: ( constructTriples )?
               int alt46 = 2;
               try {
                  this.dbg.enterSubRule(46);
                  try {
                     this.dbg.enterDecision(46);

                     final int LA46_0 = this.input.LA(1);

                     if (LA46_0 == CSparqlParser.IRI_REF || LA46_0 == CSparqlParser.PNAME_NS
                           || LA46_0 == CSparqlParser.OPEN_BRACE
                           || LA46_0 == CSparqlParser.OPEN_SQUARE_BRACE
                           || LA46_0 == CSparqlParser.INTEGER
                           || LA46_0 >= CSparqlParser.VAR1 && LA46_0 <= CSparqlParser.VAR2
                           || LA46_0 >= CSparqlParser.DECIMAL
                           && LA46_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                        alt46 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(46);
                  }

                  switch (alt46) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:241:24: constructTriples
                        {
                           this.dbg.location(241, 24);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_constructTriples_in_constructTemplate1192);
                           constructTriples159 = this.constructTriples();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, constructTriples159.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(46);
               }

               this.dbg.location(241, 42);
               CLOSE_CURLY_BRACE160 = (Token) this.match(this.input,
                     CSparqlParser.CLOSE_CURLY_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_CURLY_BRACE_in_constructTemplate1195);
               CLOSE_CURLY_BRACE160_tree = (Object) this.adaptor
                     .create(CLOSE_CURLY_BRACE160);
               this.adaptor.addChild(root_0, CLOSE_CURLY_BRACE160_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(242, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "constructTriples"
   // CSparql.g:244:1: constructTriples : triplesSameSubject ( DOT ( constructTriples )? )? ;
   public final CSparqlParser.constructTriples_return constructTriples()
         throws RecognitionException {
      final CSparqlParser.constructTriples_return retval = new CSparqlParser.constructTriples_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token DOT162 = null;
      CSparqlParser.triplesSameSubject_return triplesSameSubject161 = null;

      CSparqlParser.constructTriples_return constructTriples163 = null;

      Object DOT162_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "constructTriples");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(244, 1);

         try {
            // CSparql.g:245:5: ( triplesSameSubject ( DOT ( constructTriples )? )? )
            this.dbg.enterAlt(1);

            // CSparql.g:245:7: triplesSameSubject ( DOT ( constructTriples )? )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(245, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_triplesSameSubject_in_constructTriples1212);
               triplesSameSubject161 = this.triplesSameSubject();

               this.state._fsp--;

               this.adaptor.addChild(root_0, triplesSameSubject161.getTree());
               this.dbg.location(245, 26);
               // CSparql.g:245:26: ( DOT ( constructTriples )? )?
               int alt48 = 2;
               try {
                  this.dbg.enterSubRule(48);
                  try {
                     this.dbg.enterDecision(48);

                     final int LA48_0 = this.input.LA(1);

                     if (LA48_0 == CSparqlParser.DOT) {
                        alt48 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(48);
                  }

                  switch (alt48) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:245:28: DOT ( constructTriples )?
                        {
                           this.dbg.location(245, 28);
                           DOT162 = (Token) this.match(this.input, CSparqlParser.DOT,
                                 CSparqlParser.FOLLOW_DOT_in_constructTriples1216);
                           DOT162_tree = (Object) this.adaptor.create(DOT162);
                           this.adaptor.addChild(root_0, DOT162_tree);

                           this.dbg.location(245, 32);
                           // CSparql.g:245:32: ( constructTriples )?
                           int alt47 = 2;
                           try {
                              this.dbg.enterSubRule(47);
                              try {
                                 this.dbg.enterDecision(47);

                                 final int LA47_0 = this.input.LA(1);

                                 if (LA47_0 == CSparqlParser.IRI_REF
                                       || LA47_0 == CSparqlParser.PNAME_NS
                                       || LA47_0 == CSparqlParser.OPEN_BRACE
                                       || LA47_0 == CSparqlParser.OPEN_SQUARE_BRACE
                                       || LA47_0 == CSparqlParser.INTEGER
                                       || LA47_0 >= CSparqlParser.VAR1
                                       && LA47_0 <= CSparqlParser.VAR2
                                       || LA47_0 >= CSparqlParser.DECIMAL
                                       && LA47_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                                    alt47 = 1;
                                 }
                              } finally {
                                 this.dbg.exitDecision(47);
                              }

                              switch (alt47) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // CSparql.g:245:32: constructTriples
                                    {
                                       this.dbg.location(245, 32);
                                       this
                                             .pushFollow(CSparqlParser.FOLLOW_constructTriples_in_constructTriples1218);
                                       constructTriples163 = this.constructTriples();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, constructTriples163
                                             .getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(47);
                           }

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(48);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(246, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesSameSubject"
   // CSparql.g:248:1: triplesSameSubject : ( varOrTerm propertyListNotEmpty | triplesNode
   // propertyList );
   public final CSparqlParser.triplesSameSubject_return triplesSameSubject()
         throws RecognitionException {
      final CSparqlParser.triplesSameSubject_return retval = new CSparqlParser.triplesSameSubject_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.varOrTerm_return varOrTerm164 = null;

      CSparqlParser.propertyListNotEmpty_return propertyListNotEmpty165 = null;

      CSparqlParser.triplesNode_return triplesNode166 = null;

      CSparqlParser.propertyList_return propertyList167 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesSameSubject");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(248, 1);

         try {
            // CSparql.g:249:5: ( varOrTerm propertyListNotEmpty | triplesNode propertyList )
            int alt49 = 2;
            try {
               this.dbg.enterDecision(49);

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
                  case BLANK_NODE_LABEL: {
                     alt49 = 1;
                  }
                     break;
                  case OPEN_SQUARE_BRACE: {
                     final int LA49_2 = this.input.LA(2);

                     if (LA49_2 == CSparqlParser.CLOSE_SQUARE_BRACE) {
                        alt49 = 1;
                     } else if (LA49_2 == CSparqlParser.IRI_REF
                           || LA49_2 == CSparqlParser.PNAME_NS || LA49_2 >= CSparqlParser.A
                           && LA49_2 <= CSparqlParser.VAR2
                           || LA49_2 == CSparqlParser.PNAME_LN) {
                        alt49 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 49,
                              2, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  }
                     break;
                  case OPEN_BRACE: {
                     final int LA49_3 = this.input.LA(2);

                     if (LA49_3 == CSparqlParser.CLOSE_BRACE) {
                        alt49 = 1;
                     } else if (LA49_3 == CSparqlParser.IRI_REF
                           || LA49_3 == CSparqlParser.PNAME_NS
                           || LA49_3 == CSparqlParser.OPEN_BRACE
                           || LA49_3 == CSparqlParser.OPEN_SQUARE_BRACE
                           || LA49_3 == CSparqlParser.INTEGER
                           || LA49_3 >= CSparqlParser.VAR1 && LA49_3 <= CSparqlParser.VAR2
                           || LA49_3 >= CSparqlParser.DECIMAL
                           && LA49_3 <= CSparqlParser.BLANK_NODE_LABEL) {
                        alt49 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 49,
                              3, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  }
                     break;
                  default:
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

                  // CSparql.g:249:7: varOrTerm propertyListNotEmpty
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(249, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_varOrTerm_in_triplesSameSubject1239);
                     varOrTerm164 = this.varOrTerm();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, varOrTerm164.getTree());
                     this.dbg.location(249, 17);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_propertyListNotEmpty_in_triplesSameSubject1241);
                     propertyListNotEmpty165 = this.propertyListNotEmpty();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, propertyListNotEmpty165.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:249:40: triplesNode propertyList
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(249, 40);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_triplesNode_in_triplesSameSubject1245);
                     triplesNode166 = this.triplesNode();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, triplesNode166.getTree());
                     this.dbg.location(249, 52);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_propertyList_in_triplesSameSubject1247);
                     propertyList167 = this.propertyList();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, propertyList167.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(250, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "propertyListNotEmpty"
   // CSparql.g:252:1: propertyListNotEmpty : verb objectList ( SEMICOLON ( verb objectList
   // )? )* ;
   public final CSparqlParser.propertyListNotEmpty_return propertyListNotEmpty()
         throws RecognitionException {
      final CSparqlParser.propertyListNotEmpty_return retval = new CSparqlParser.propertyListNotEmpty_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SEMICOLON170 = null;
      CSparqlParser.verb_return verb168 = null;

      CSparqlParser.objectList_return objectList169 = null;

      CSparqlParser.verb_return verb171 = null;

      CSparqlParser.objectList_return objectList172 = null;

      Object SEMICOLON170_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "propertyListNotEmpty");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(252, 1);

         try {
            // CSparql.g:253:5: ( verb objectList ( SEMICOLON ( verb objectList )? )* )
            this.dbg.enterAlt(1);

            // CSparql.g:253:7: verb objectList ( SEMICOLON ( verb objectList )? )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(253, 7);
               this.pushFollow(CSparqlParser.FOLLOW_verb_in_propertyListNotEmpty1264);
               verb168 = this.verb();

               this.state._fsp--;

               this.adaptor.addChild(root_0, verb168.getTree());
               this.dbg.location(253, 12);
               this.pushFollow(CSparqlParser.FOLLOW_objectList_in_propertyListNotEmpty1266);
               objectList169 = this.objectList();

               this.state._fsp--;

               this.adaptor.addChild(root_0, objectList169.getTree());
               this.dbg.location(253, 23);
               // CSparql.g:253:23: ( SEMICOLON ( verb objectList )? )*
               try {
                  this.dbg.enterSubRule(51);

                  loop51: do {
                     int alt51 = 2;
                     try {
                        this.dbg.enterDecision(51);

                        final int LA51_0 = this.input.LA(1);

                        if (LA51_0 == CSparqlParser.SEMICOLON) {
                           alt51 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(51);
                     }

                     switch (alt51) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:253:25: SEMICOLON ( verb objectList )?
                           {
                              this.dbg.location(253, 25);
                              SEMICOLON170 = (Token) this
                                    .match(
                                          this.input,
                                          CSparqlParser.SEMICOLON,
                                          CSparqlParser.FOLLOW_SEMICOLON_in_propertyListNotEmpty1270);
                              SEMICOLON170_tree = (Object) this.adaptor.create(SEMICOLON170);
                              this.adaptor.addChild(root_0, SEMICOLON170_tree);

                              this.dbg.location(253, 35);
                              // CSparql.g:253:35: ( verb objectList )?
                              int alt50 = 2;
                              try {
                                 this.dbg.enterSubRule(50);
                                 try {
                                    this.dbg.enterDecision(50);

                                    final int LA50_0 = this.input.LA(1);

                                    if (LA50_0 == CSparqlParser.IRI_REF
                                          || LA50_0 == CSparqlParser.PNAME_NS
                                          || LA50_0 >= CSparqlParser.A
                                          && LA50_0 <= CSparqlParser.VAR2
                                          || LA50_0 == CSparqlParser.PNAME_LN) {
                                       alt50 = 1;
                                    }
                                 } finally {
                                    this.dbg.exitDecision(50);
                                 }

                                 switch (alt50) {
                                    case 1:
                                       this.dbg.enterAlt(1);

                                       // CSparql.g:253:37: verb objectList
                                       {
                                          this.dbg.location(253, 37);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_verb_in_propertyListNotEmpty1274);
                                          verb171 = this.verb();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, verb171.getTree());
                                          this.dbg.location(253, 42);
                                          this
                                                .pushFollow(CSparqlParser.FOLLOW_objectList_in_propertyListNotEmpty1276);
                                          objectList172 = this.objectList();

                                          this.state._fsp--;

                                          this.adaptor.addChild(root_0, objectList172
                                                .getTree());

                                       }
                                       break;

                                 }
                              } finally {
                                 this.dbg.exitSubRule(50);
                              }

                           }
                           break;

                        default:
                           break loop51;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(51);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(254, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "propertyList"
   // CSparql.g:256:1: propertyList : ( propertyListNotEmpty )? ;
   public final CSparqlParser.propertyList_return propertyList() throws RecognitionException {
      final CSparqlParser.propertyList_return retval = new CSparqlParser.propertyList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.propertyListNotEmpty_return propertyListNotEmpty173 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "propertyList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(256, 1);

         try {
            // CSparql.g:257:5: ( ( propertyListNotEmpty )? )
            this.dbg.enterAlt(1);

            // CSparql.g:257:7: ( propertyListNotEmpty )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(257, 7);
               // CSparql.g:257:7: ( propertyListNotEmpty )?
               int alt52 = 2;
               try {
                  this.dbg.enterSubRule(52);
                  try {
                     this.dbg.enterDecision(52);

                     final int LA52_0 = this.input.LA(1);

                     if (LA52_0 == CSparqlParser.IRI_REF || LA52_0 == CSparqlParser.PNAME_NS
                           || LA52_0 >= CSparqlParser.A && LA52_0 <= CSparqlParser.VAR2
                           || LA52_0 == CSparqlParser.PNAME_LN) {
                        alt52 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(52);
                  }

                  switch (alt52) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:257:7: propertyListNotEmpty
                        {
                           this.dbg.location(257, 7);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_propertyListNotEmpty_in_propertyList1299);
                           propertyListNotEmpty173 = this.propertyListNotEmpty();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, propertyListNotEmpty173.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(52);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(258, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "objectList"
   // CSparql.g:260:1: objectList : object ( COMMA object )* ;
   public final CSparqlParser.objectList_return objectList() throws RecognitionException {
      final CSparqlParser.objectList_return retval = new CSparqlParser.objectList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token COMMA175 = null;
      CSparqlParser.object_return object174 = null;

      CSparqlParser.object_return object176 = null;

      Object COMMA175_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "objectList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(260, 1);

         try {
            // CSparql.g:261:5: ( object ( COMMA object )* )
            this.dbg.enterAlt(1);

            // CSparql.g:261:7: object ( COMMA object )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(261, 7);
               this.pushFollow(CSparqlParser.FOLLOW_object_in_objectList1317);
               object174 = this.object();

               this.state._fsp--;

               this.adaptor.addChild(root_0, object174.getTree());
               this.dbg.location(261, 14);
               // CSparql.g:261:14: ( COMMA object )*
               try {
                  this.dbg.enterSubRule(53);

                  loop53: do {
                     int alt53 = 2;
                     try {
                        this.dbg.enterDecision(53);

                        final int LA53_0 = this.input.LA(1);

                        if (LA53_0 == CSparqlParser.COMMA) {
                           alt53 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(53);
                     }

                     switch (alt53) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:261:16: COMMA object
                           {
                              this.dbg.location(261, 16);
                              COMMA175 = (Token) this.match(this.input, CSparqlParser.COMMA,
                                    CSparqlParser.FOLLOW_COMMA_in_objectList1321);
                              COMMA175_tree = (Object) this.adaptor.create(COMMA175);
                              this.adaptor.addChild(root_0, COMMA175_tree);

                              this.dbg.location(261, 22);
                              this.pushFollow(CSparqlParser.FOLLOW_object_in_objectList1323);
                              object176 = this.object();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, object176.getTree());

                           }
                           break;

                        default:
                           break loop53;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(53);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(262, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "object"
   // CSparql.g:264:1: object : graphNode ;
   public final CSparqlParser.object_return object() throws RecognitionException {
      final CSparqlParser.object_return retval = new CSparqlParser.object_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.graphNode_return graphNode177 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "object");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(264, 1);

         try {
            // CSparql.g:265:5: ( graphNode )
            this.dbg.enterAlt(1);

            // CSparql.g:265:7: graphNode
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(265, 7);
               this.pushFollow(CSparqlParser.FOLLOW_graphNode_in_object1343);
               graphNode177 = this.graphNode();

               this.state._fsp--;

               this.adaptor.addChild(root_0, graphNode177.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(266, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "verb"
   // CSparql.g:268:1: verb : ( varOrIRIref | A );
   public final CSparqlParser.verb_return verb() throws RecognitionException {
      final CSparqlParser.verb_return retval = new CSparqlParser.verb_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token A179 = null;
      CSparqlParser.varOrIRIref_return varOrIRIref178 = null;

      Object A179_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "verb");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(268, 1);

         try {
            // CSparql.g:269:5: ( varOrIRIref | A )
            int alt54 = 2;
            try {
               this.dbg.enterDecision(54);

               final int LA54_0 = this.input.LA(1);

               if (LA54_0 == CSparqlParser.IRI_REF || LA54_0 == CSparqlParser.PNAME_NS
                     || LA54_0 >= CSparqlParser.VAR1 && LA54_0 <= CSparqlParser.VAR2
                     || LA54_0 == CSparqlParser.PNAME_LN) {
                  alt54 = 1;
               } else if (LA54_0 == CSparqlParser.A) {
                  alt54 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 54, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(54);
            }

            switch (alt54) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:269:7: varOrIRIref
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(269, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_varOrIRIref_in_verb1360);
                     varOrIRIref178 = this.varOrIRIref();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, varOrIRIref178.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:270:7: A
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(270, 7);
                     A179 = (Token) this.match(this.input, CSparqlParser.A,
                           CSparqlParser.FOLLOW_A_in_verb1368);
                     A179_tree = (Object) this.adaptor.create(A179);
                     this.adaptor.addChild(root_0, A179_tree);

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(271, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "triplesNode"
   // CSparql.g:273:1: triplesNode : ( collection | blankNodePropertyList );
   public final CSparqlParser.triplesNode_return triplesNode() throws RecognitionException {
      final CSparqlParser.triplesNode_return retval = new CSparqlParser.triplesNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.collection_return collection180 = null;

      CSparqlParser.blankNodePropertyList_return blankNodePropertyList181 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "triplesNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(273, 1);

         try {
            // CSparql.g:274:5: ( collection | blankNodePropertyList )
            int alt55 = 2;
            try {
               this.dbg.enterDecision(55);

               final int LA55_0 = this.input.LA(1);

               if (LA55_0 == CSparqlParser.OPEN_BRACE) {
                  alt55 = 1;
               } else if (LA55_0 == CSparqlParser.OPEN_SQUARE_BRACE) {
                  alt55 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 55, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(55);
            }

            switch (alt55) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:274:7: collection
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(274, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_collection_in_triplesNode1385);
                     collection180 = this.collection();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, collection180.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:275:7: blankNodePropertyList
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(275, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_blankNodePropertyList_in_triplesNode1393);
                     blankNodePropertyList181 = this.blankNodePropertyList();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, blankNodePropertyList181.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(276, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "blankNodePropertyList"
   // CSparql.g:278:1: blankNodePropertyList : OPEN_SQUARE_BRACE propertyListNotEmpty
   // CLOSE_SQUARE_BRACE ;
   public final CSparqlParser.blankNodePropertyList_return blankNodePropertyList()
         throws RecognitionException {
      final CSparqlParser.blankNodePropertyList_return retval = new CSparqlParser.blankNodePropertyList_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_SQUARE_BRACE182 = null;
      Token CLOSE_SQUARE_BRACE184 = null;
      CSparqlParser.propertyListNotEmpty_return propertyListNotEmpty183 = null;

      Object OPEN_SQUARE_BRACE182_tree = null;
      Object CLOSE_SQUARE_BRACE184_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "blankNodePropertyList");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(278, 1);

         try {
            // CSparql.g:279:5: ( OPEN_SQUARE_BRACE propertyListNotEmpty CLOSE_SQUARE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:279:7: OPEN_SQUARE_BRACE propertyListNotEmpty CLOSE_SQUARE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(279, 7);
               OPEN_SQUARE_BRACE182 = (Token) this.match(this.input,
                     CSparqlParser.OPEN_SQUARE_BRACE,
                     CSparqlParser.FOLLOW_OPEN_SQUARE_BRACE_in_blankNodePropertyList1410);
               OPEN_SQUARE_BRACE182_tree = (Object) this.adaptor
                     .create(OPEN_SQUARE_BRACE182);
               this.adaptor.addChild(root_0, OPEN_SQUARE_BRACE182_tree);

               this.dbg.location(279, 25);
               this
                     .pushFollow(CSparqlParser.FOLLOW_propertyListNotEmpty_in_blankNodePropertyList1412);
               propertyListNotEmpty183 = this.propertyListNotEmpty();

               this.state._fsp--;

               this.adaptor.addChild(root_0, propertyListNotEmpty183.getTree());
               this.dbg.location(279, 46);
               CLOSE_SQUARE_BRACE184 = (Token) this.match(this.input,
                     CSparqlParser.CLOSE_SQUARE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_SQUARE_BRACE_in_blankNodePropertyList1414);
               CLOSE_SQUARE_BRACE184_tree = (Object) this.adaptor
                     .create(CLOSE_SQUARE_BRACE184);
               this.adaptor.addChild(root_0, CLOSE_SQUARE_BRACE184_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(280, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "collection"
   // CSparql.g:282:1: collection : OPEN_BRACE ( graphNode )+ CLOSE_BRACE ;
   public final CSparqlParser.collection_return collection() throws RecognitionException {
      final CSparqlParser.collection_return retval = new CSparqlParser.collection_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE185 = null;
      Token CLOSE_BRACE187 = null;
      CSparqlParser.graphNode_return graphNode186 = null;

      Object OPEN_BRACE185_tree = null;
      Object CLOSE_BRACE187_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "collection");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(282, 1);

         try {
            // CSparql.g:283:5: ( OPEN_BRACE ( graphNode )+ CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:283:7: OPEN_BRACE ( graphNode )+ CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(283, 7);
               OPEN_BRACE185 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_collection1431);
               OPEN_BRACE185_tree = (Object) this.adaptor.create(OPEN_BRACE185);
               this.adaptor.addChild(root_0, OPEN_BRACE185_tree);

               this.dbg.location(283, 18);
               // CSparql.g:283:18: ( graphNode )+
               int cnt56 = 0;
               try {
                  this.dbg.enterSubRule(56);

                  loop56: do {
                     int alt56 = 2;
                     try {
                        this.dbg.enterDecision(56);

                        final int LA56_0 = this.input.LA(1);

                        if (LA56_0 == CSparqlParser.IRI_REF
                              || LA56_0 == CSparqlParser.PNAME_NS
                              || LA56_0 == CSparqlParser.OPEN_BRACE
                              || LA56_0 == CSparqlParser.OPEN_SQUARE_BRACE
                              || LA56_0 == CSparqlParser.INTEGER
                              || LA56_0 >= CSparqlParser.VAR1
                              && LA56_0 <= CSparqlParser.VAR2
                              || LA56_0 >= CSparqlParser.DECIMAL
                              && LA56_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                           alt56 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(56);
                     }

                     switch (alt56) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:283:18: graphNode
                           {
                              this.dbg.location(283, 18);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_graphNode_in_collection1433);
                              graphNode186 = this.graphNode();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, graphNode186.getTree());

                           }
                           break;

                        default:
                           if (cnt56 >= 1) {
                              break loop56;
                           }
                           final EarlyExitException eee = new EarlyExitException(56,
                                 this.input);
                           this.dbg.recognitionException(eee);

                           throw eee;
                     }
                     cnt56++;
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(56);
               }

               this.dbg.location(283, 29);
               CLOSE_BRACE187 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_collection1436);
               CLOSE_BRACE187_tree = (Object) this.adaptor.create(CLOSE_BRACE187);
               this.adaptor.addChild(root_0, CLOSE_BRACE187_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(284, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphNode"
   // CSparql.g:286:1: graphNode : ( varOrTerm | triplesNode );
   public final CSparqlParser.graphNode_return graphNode() throws RecognitionException {
      final CSparqlParser.graphNode_return retval = new CSparqlParser.graphNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.varOrTerm_return varOrTerm188 = null;

      CSparqlParser.triplesNode_return triplesNode189 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(286, 1);

         try {
            // CSparql.g:287:5: ( varOrTerm | triplesNode )
            int alt57 = 2;
            try {
               this.dbg.enterDecision(57);

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
                  case BLANK_NODE_LABEL: {
                     alt57 = 1;
                  }
                     break;
                  case OPEN_SQUARE_BRACE: {
                     final int LA57_2 = this.input.LA(2);

                     if (LA57_2 == CSparqlParser.CLOSE_SQUARE_BRACE) {
                        alt57 = 1;
                     } else if (LA57_2 == CSparqlParser.IRI_REF
                           || LA57_2 == CSparqlParser.PNAME_NS || LA57_2 >= CSparqlParser.A
                           && LA57_2 <= CSparqlParser.VAR2
                           || LA57_2 == CSparqlParser.PNAME_LN) {
                        alt57 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 57,
                              2, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  }
                     break;
                  case OPEN_BRACE: {
                     final int LA57_3 = this.input.LA(2);

                     if (LA57_3 == CSparqlParser.CLOSE_BRACE) {
                        alt57 = 1;
                     } else if (LA57_3 == CSparqlParser.IRI_REF
                           || LA57_3 == CSparqlParser.PNAME_NS
                           || LA57_3 == CSparqlParser.OPEN_BRACE
                           || LA57_3 == CSparqlParser.OPEN_SQUARE_BRACE
                           || LA57_3 == CSparqlParser.INTEGER
                           || LA57_3 >= CSparqlParser.VAR1 && LA57_3 <= CSparqlParser.VAR2
                           || LA57_3 >= CSparqlParser.DECIMAL
                           && LA57_3 <= CSparqlParser.BLANK_NODE_LABEL) {
                        alt57 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 57,
                              3, this.input);

                        this.dbg.recognitionException(nvae);
                        throw nvae;
                     }
                  }
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

                  // CSparql.g:287:7: varOrTerm
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(287, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_varOrTerm_in_graphNode1453);
                     varOrTerm188 = this.varOrTerm();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, varOrTerm188.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:287:19: triplesNode
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(287, 19);
                     this.pushFollow(CSparqlParser.FOLLOW_triplesNode_in_graphNode1457);
                     triplesNode189 = this.triplesNode();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, triplesNode189.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(288, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "varOrTerm"
   // CSparql.g:290:1: varOrTerm : ( var | graphTerm );
   public final CSparqlParser.varOrTerm_return varOrTerm() throws RecognitionException {
      final CSparqlParser.varOrTerm_return retval = new CSparqlParser.varOrTerm_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.var_return var190 = null;

      CSparqlParser.graphTerm_return graphTerm191 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "varOrTerm");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(290, 1);

         try {
            // CSparql.g:291:5: ( var | graphTerm )
            int alt58 = 2;
            try {
               this.dbg.enterDecision(58);

               final int LA58_0 = this.input.LA(1);

               if (LA58_0 >= CSparqlParser.VAR1 && LA58_0 <= CSparqlParser.VAR2) {
                  alt58 = 1;
               } else if (LA58_0 == CSparqlParser.IRI_REF
                     || LA58_0 == CSparqlParser.PNAME_NS
                     || LA58_0 == CSparqlParser.OPEN_BRACE
                     || LA58_0 == CSparqlParser.OPEN_SQUARE_BRACE
                     || LA58_0 == CSparqlParser.INTEGER || LA58_0 >= CSparqlParser.DECIMAL
                     && LA58_0 <= CSparqlParser.BLANK_NODE_LABEL) {
                  alt58 = 2;
               } else {
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

                  // CSparql.g:291:7: var
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(291, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_var_in_varOrTerm1474);
                     var190 = this.var();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, var190.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:292:7: graphTerm
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(292, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_graphTerm_in_varOrTerm1482);
                     graphTerm191 = this.graphTerm();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, graphTerm191.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(293, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "varOrIRIref"
   // CSparql.g:295:1: varOrIRIref : ( var | iriRef );
   public final CSparqlParser.varOrIRIref_return varOrIRIref() throws RecognitionException {
      final CSparqlParser.varOrIRIref_return retval = new CSparqlParser.varOrIRIref_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.var_return var192 = null;

      CSparqlParser.iriRef_return iriRef193 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "varOrIRIref");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(295, 1);

         try {
            // CSparql.g:296:5: ( var | iriRef )
            int alt59 = 2;
            try {
               this.dbg.enterDecision(59);

               final int LA59_0 = this.input.LA(1);

               if (LA59_0 >= CSparqlParser.VAR1 && LA59_0 <= CSparqlParser.VAR2) {
                  alt59 = 1;
               } else if (LA59_0 == CSparqlParser.IRI_REF
                     || LA59_0 == CSparqlParser.PNAME_NS || LA59_0 == CSparqlParser.PNAME_LN) {
                  alt59 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 59, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(59);
            }

            switch (alt59) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:296:7: var
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(296, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_var_in_varOrIRIref1499);
                     var192 = this.var();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, var192.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:296:13: iriRef
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(296, 13);
                     this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_varOrIRIref1503);
                     iriRef193 = this.iriRef();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, iriRef193.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(297, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "var"
   // CSparql.g:299:1: var : ( VAR1 | VAR2 );
   public final CSparqlParser.var_return var() throws RecognitionException {
      final CSparqlParser.var_return retval = new CSparqlParser.var_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set194 = null;

      final Object set194_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "var");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(299, 1);

         try {
            // CSparql.g:300:5: ( VAR1 | VAR2 )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(300, 5);
               set194 = (Token) this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.VAR1
                     && this.input.LA(1) <= CSparqlParser.VAR2) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set194));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(302, 5);

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

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "graphTerm"
   // CSparql.g:304:1: graphTerm : ( iriRef | rdfLiteral | numericLiteral | booleanLiteral |
   // blankNode | OPEN_BRACE CLOSE_BRACE );
   public final CSparqlParser.graphTerm_return graphTerm() throws RecognitionException {
      final CSparqlParser.graphTerm_return retval = new CSparqlParser.graphTerm_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE200 = null;
      Token CLOSE_BRACE201 = null;
      CSparqlParser.iriRef_return iriRef195 = null;

      CSparqlParser.rdfLiteral_return rdfLiteral196 = null;

      CSparqlParser.numericLiteral_return numericLiteral197 = null;

      CSparqlParser.booleanLiteral_return booleanLiteral198 = null;

      CSparqlParser.blankNode_return blankNode199 = null;

      Object OPEN_BRACE200_tree = null;
      Object CLOSE_BRACE201_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "graphTerm");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(304, 1);

         try {
            // CSparql.g:305:5: ( iriRef | rdfLiteral | numericLiteral | booleanLiteral |
            // blankNode | OPEN_BRACE CLOSE_BRACE )
            int alt60 = 6;
            try {
               this.dbg.enterDecision(60);

               switch (this.input.LA(1)) {
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN: {
                     alt60 = 1;
                  }
                     break;
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2: {
                     alt60 = 2;
                  }
                     break;
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE: {
                     alt60 = 3;
                  }
                     break;
                  case TRUE:
                  case FALSE: {
                     alt60 = 4;
                  }
                     break;
                  case OPEN_SQUARE_BRACE:
                  case BLANK_NODE_LABEL: {
                     alt60 = 5;
                  }
                     break;
                  case OPEN_BRACE: {
                     alt60 = 6;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 60, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(60);
            }

            switch (alt60) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:305:7: iriRef
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(305, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_graphTerm1545);
                     iriRef195 = this.iriRef();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, iriRef195.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:306:7: rdfLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(306, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_rdfLiteral_in_graphTerm1553);
                     rdfLiteral196 = this.rdfLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, rdfLiteral196.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:307:7: numericLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(307, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_numericLiteral_in_graphTerm1561);
                     numericLiteral197 = this.numericLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, numericLiteral197.getTree());

                  }
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  // CSparql.g:308:7: booleanLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(308, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_booleanLiteral_in_graphTerm1569);
                     booleanLiteral198 = this.booleanLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, booleanLiteral198.getTree());

                  }
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  // CSparql.g:309:7: blankNode
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(309, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_blankNode_in_graphTerm1577);
                     blankNode199 = this.blankNode();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, blankNode199.getTree());

                  }
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  // CSparql.g:310:7: OPEN_BRACE CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(310, 7);
                     OPEN_BRACE200 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_graphTerm1585);
                     OPEN_BRACE200_tree = (Object) this.adaptor.create(OPEN_BRACE200);
                     this.adaptor.addChild(root_0, OPEN_BRACE200_tree);

                     this.dbg.location(310, 18);
                     CLOSE_BRACE201 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_graphTerm1587);
                     CLOSE_BRACE201_tree = (Object) this.adaptor.create(CLOSE_BRACE201);
                     this.adaptor.addChild(root_0, CLOSE_BRACE201_tree);

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(311, 5);

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
   // CSparql.g:313:1: expression : conditionalOrExpression ;
   public final CSparqlParser.expression_return expression() throws RecognitionException {
      final CSparqlParser.expression_return retval = new CSparqlParser.expression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.conditionalOrExpression_return conditionalOrExpression202 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "expression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(313, 1);

         try {
            // CSparql.g:314:5: ( conditionalOrExpression )
            this.dbg.enterAlt(1);

            // CSparql.g:314:7: conditionalOrExpression
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(314, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_conditionalOrExpression_in_expression1604);
               conditionalOrExpression202 = this.conditionalOrExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, conditionalOrExpression202.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(315, 5);

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
   // CSparql.g:317:1: conditionalOrExpression : conditionalAndExpression ( OR
   // conditionalAndExpression )* ;
   public final CSparqlParser.conditionalOrExpression_return conditionalOrExpression()
         throws RecognitionException {
      final CSparqlParser.conditionalOrExpression_return retval = new CSparqlParser.conditionalOrExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OR204 = null;
      CSparqlParser.conditionalAndExpression_return conditionalAndExpression203 = null;

      CSparqlParser.conditionalAndExpression_return conditionalAndExpression205 = null;

      Object OR204_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "conditionalOrExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(317, 1);

         try {
            // CSparql.g:318:5: ( conditionalAndExpression ( OR conditionalAndExpression )* )
            this.dbg.enterAlt(1);

            // CSparql.g:318:7: conditionalAndExpression ( OR conditionalAndExpression )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(318, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_conditionalAndExpression_in_conditionalOrExpression1621);
               conditionalAndExpression203 = this.conditionalAndExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, conditionalAndExpression203.getTree());
               this.dbg.location(318, 32);
               // CSparql.g:318:32: ( OR conditionalAndExpression )*
               try {
                  this.dbg.enterSubRule(61);

                  loop61: do {
                     int alt61 = 2;
                     try {
                        this.dbg.enterDecision(61);

                        final int LA61_0 = this.input.LA(1);

                        if (LA61_0 == CSparqlParser.OR) {
                           alt61 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(61);
                     }

                     switch (alt61) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:318:34: OR conditionalAndExpression
                           {
                              this.dbg.location(318, 34);
                              OR204 = (Token) this.match(this.input, CSparqlParser.OR,
                                    CSparqlParser.FOLLOW_OR_in_conditionalOrExpression1625);
                              OR204_tree = (Object) this.adaptor.create(OR204);
                              this.adaptor.addChild(root_0, OR204_tree);

                              this.dbg.location(318, 37);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_conditionalAndExpression_in_conditionalOrExpression1627);
                              conditionalAndExpression205 = this.conditionalAndExpression();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, conditionalAndExpression205
                                    .getTree());

                           }
                           break;

                        default:
                           break loop61;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(61);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(319, 5);

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
   // CSparql.g:321:1: conditionalAndExpression : valueLogical ( AND valueLogical )* ;
   public final CSparqlParser.conditionalAndExpression_return conditionalAndExpression()
         throws RecognitionException {
      final CSparqlParser.conditionalAndExpression_return retval = new CSparqlParser.conditionalAndExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token AND207 = null;
      CSparqlParser.valueLogical_return valueLogical206 = null;

      CSparqlParser.valueLogical_return valueLogical208 = null;

      Object AND207_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "conditionalAndExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(321, 1);

         try {
            // CSparql.g:322:5: ( valueLogical ( AND valueLogical )* )
            this.dbg.enterAlt(1);

            // CSparql.g:322:7: valueLogical ( AND valueLogical )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(322, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_valueLogical_in_conditionalAndExpression1647);
               valueLogical206 = this.valueLogical();

               this.state._fsp--;

               this.adaptor.addChild(root_0, valueLogical206.getTree());
               this.dbg.location(322, 20);
               // CSparql.g:322:20: ( AND valueLogical )*
               try {
                  this.dbg.enterSubRule(62);

                  loop62: do {
                     int alt62 = 2;
                     try {
                        this.dbg.enterDecision(62);

                        final int LA62_0 = this.input.LA(1);

                        if (LA62_0 == CSparqlParser.AND) {
                           alt62 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(62);
                     }

                     switch (alt62) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:322:22: AND valueLogical
                           {
                              this.dbg.location(322, 22);
                              AND207 = (Token) this
                                    .match(
                                          this.input,
                                          CSparqlParser.AND,
                                          CSparqlParser.FOLLOW_AND_in_conditionalAndExpression1651);
                              AND207_tree = (Object) this.adaptor.create(AND207);
                              this.adaptor.addChild(root_0, AND207_tree);

                              this.dbg.location(322, 26);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_valueLogical_in_conditionalAndExpression1653);
                              valueLogical208 = this.valueLogical();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, valueLogical208.getTree());

                           }
                           break;

                        default:
                           break loop62;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(62);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(323, 5);

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
   // CSparql.g:325:1: valueLogical : relationalExpression ;
   public final CSparqlParser.valueLogical_return valueLogical() throws RecognitionException {
      final CSparqlParser.valueLogical_return retval = new CSparqlParser.valueLogical_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.relationalExpression_return relationalExpression209 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "valueLogical");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(325, 1);

         try {
            // CSparql.g:326:5: ( relationalExpression )
            this.dbg.enterAlt(1);

            // CSparql.g:326:7: relationalExpression
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(326, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_relationalExpression_in_valueLogical1673);
               relationalExpression209 = this.relationalExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, relationalExpression209.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(327, 5);

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
   // CSparql.g:329:1: relationalExpression : numericExpression ( EQUAL numericExpression |
   // NOT_EQUAL numericExpression | LESS numericExpression | GREATER numericExpression |
   // LESS_EQUAL numericExpression | GREATER_EQUAL numericExpression )? ;
   public final CSparqlParser.relationalExpression_return relationalExpression()
         throws RecognitionException {
      final CSparqlParser.relationalExpression_return retval = new CSparqlParser.relationalExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EQUAL211 = null;
      Token NOT_EQUAL213 = null;
      Token LESS215 = null;
      Token GREATER217 = null;
      Token LESS_EQUAL219 = null;
      Token GREATER_EQUAL221 = null;
      CSparqlParser.numericExpression_return numericExpression210 = null;

      CSparqlParser.numericExpression_return numericExpression212 = null;

      CSparqlParser.numericExpression_return numericExpression214 = null;

      CSparqlParser.numericExpression_return numericExpression216 = null;

      CSparqlParser.numericExpression_return numericExpression218 = null;

      CSparqlParser.numericExpression_return numericExpression220 = null;

      CSparqlParser.numericExpression_return numericExpression222 = null;

      Object EQUAL211_tree = null;
      Object NOT_EQUAL213_tree = null;
      Object LESS215_tree = null;
      Object GREATER217_tree = null;
      Object LESS_EQUAL219_tree = null;
      Object GREATER_EQUAL221_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "relationalExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(329, 1);

         try {
            // CSparql.g:330:5: ( numericExpression ( EQUAL numericExpression | NOT_EQUAL
            // numericExpression | LESS numericExpression | GREATER numericExpression |
            // LESS_EQUAL numericExpression | GREATER_EQUAL numericExpression )? )
            this.dbg.enterAlt(1);

            // CSparql.g:330:7: numericExpression ( EQUAL numericExpression | NOT_EQUAL
            // numericExpression | LESS numericExpression | GREATER numericExpression |
            // LESS_EQUAL numericExpression | GREATER_EQUAL numericExpression )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(330, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1690);
               numericExpression210 = this.numericExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, numericExpression210.getTree());
               this.dbg.location(330, 25);
               // CSparql.g:330:25: ( EQUAL numericExpression | NOT_EQUAL numericExpression |
               // LESS numericExpression | GREATER numericExpression | LESS_EQUAL
               // numericExpression | GREATER_EQUAL numericExpression )?
               int alt63 = 7;
               try {
                  this.dbg.enterSubRule(63);
                  try {
                     this.dbg.enterDecision(63);

                     switch (this.input.LA(1)) {
                        case EQUAL: {
                           alt63 = 1;
                        }
                           break;
                        case NOT_EQUAL: {
                           alt63 = 2;
                        }
                           break;
                        case LESS: {
                           alt63 = 3;
                        }
                           break;
                        case GREATER: {
                           alt63 = 4;
                        }
                           break;
                        case LESS_EQUAL: {
                           alt63 = 5;
                        }
                           break;
                        case GREATER_EQUAL: {
                           alt63 = 6;
                        }
                           break;
                     }

                  } finally {
                     this.dbg.exitDecision(63);
                  }

                  switch (alt63) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:330:27: EQUAL numericExpression
                        {
                           this.dbg.location(330, 27);
                           EQUAL211 = (Token) this.match(this.input, CSparqlParser.EQUAL,
                                 CSparqlParser.FOLLOW_EQUAL_in_relationalExpression1694);
                           EQUAL211_tree = (Object) this.adaptor.create(EQUAL211);
                           this.adaptor.addChild(root_0, EQUAL211_tree);

                           this.dbg.location(330, 33);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1696);
                           numericExpression212 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression212.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:330:53: NOT_EQUAL numericExpression
                        {
                           this.dbg.location(330, 53);
                           NOT_EQUAL213 = (Token) this.match(this.input,
                                 CSparqlParser.NOT_EQUAL,
                                 CSparqlParser.FOLLOW_NOT_EQUAL_in_relationalExpression1700);
                           NOT_EQUAL213_tree = (Object) this.adaptor.create(NOT_EQUAL213);
                           this.adaptor.addChild(root_0, NOT_EQUAL213_tree);

                           this.dbg.location(330, 63);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1702);
                           numericExpression214 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression214.getTree());

                        }
                        break;
                     case 3:
                        this.dbg.enterAlt(3);

                        // CSparql.g:330:83: LESS numericExpression
                        {
                           this.dbg.location(330, 83);
                           LESS215 = (Token) this.match(this.input, CSparqlParser.LESS,
                                 CSparqlParser.FOLLOW_LESS_in_relationalExpression1706);
                           LESS215_tree = (Object) this.adaptor.create(LESS215);
                           this.adaptor.addChild(root_0, LESS215_tree);

                           this.dbg.location(330, 88);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1708);
                           numericExpression216 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression216.getTree());

                        }
                        break;
                     case 4:
                        this.dbg.enterAlt(4);

                        // CSparql.g:330:108: GREATER numericExpression
                        {
                           this.dbg.location(330, 108);
                           GREATER217 = (Token) this.match(this.input,
                                 CSparqlParser.GREATER,
                                 CSparqlParser.FOLLOW_GREATER_in_relationalExpression1712);
                           GREATER217_tree = (Object) this.adaptor.create(GREATER217);
                           this.adaptor.addChild(root_0, GREATER217_tree);

                           this.dbg.location(330, 116);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1714);
                           numericExpression218 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression218.getTree());

                        }
                        break;
                     case 5:
                        this.dbg.enterAlt(5);

                        // CSparql.g:330:136: LESS_EQUAL numericExpression
                        {
                           this.dbg.location(330, 136);
                           LESS_EQUAL219 = (Token) this
                                 .match(
                                       this.input,
                                       CSparqlParser.LESS_EQUAL,
                                       CSparqlParser.FOLLOW_LESS_EQUAL_in_relationalExpression1718);
                           LESS_EQUAL219_tree = (Object) this.adaptor.create(LESS_EQUAL219);
                           this.adaptor.addChild(root_0, LESS_EQUAL219_tree);

                           this.dbg.location(330, 147);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1720);
                           numericExpression220 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression220.getTree());

                        }
                        break;
                     case 6:
                        this.dbg.enterAlt(6);

                        // CSparql.g:330:167: GREATER_EQUAL numericExpression
                        {
                           this.dbg.location(330, 167);
                           GREATER_EQUAL221 = (Token) this
                                 .match(
                                       this.input,
                                       CSparqlParser.GREATER_EQUAL,
                                       CSparqlParser.FOLLOW_GREATER_EQUAL_in_relationalExpression1724);
                           GREATER_EQUAL221_tree = (Object) this.adaptor
                                 .create(GREATER_EQUAL221);
                           this.adaptor.addChild(root_0, GREATER_EQUAL221_tree);

                           this.dbg.location(330, 181);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_numericExpression_in_relationalExpression1726);
                           numericExpression222 = this.numericExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, numericExpression222.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(63);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(331, 5);

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
   // CSparql.g:333:1: numericExpression : additiveExpression ;
   public final CSparqlParser.numericExpression_return numericExpression()
         throws RecognitionException {
      final CSparqlParser.numericExpression_return retval = new CSparqlParser.numericExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.additiveExpression_return additiveExpression223 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(333, 1);

         try {
            // CSparql.g:334:5: ( additiveExpression )
            this.dbg.enterAlt(1);

            // CSparql.g:334:7: additiveExpression
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(334, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_additiveExpression_in_numericExpression1746);
               additiveExpression223 = this.additiveExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, additiveExpression223.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(335, 5);

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
   // CSparql.g:337:1: additiveExpression : multiplicativeExpression ( ( MINUS | PLUS )
   // multiplicativeExpression | numericLiteralPositive | numericLiteralNegative )* ;
   public final CSparqlParser.additiveExpression_return additiveExpression()
         throws RecognitionException {
      final CSparqlParser.additiveExpression_return retval = new CSparqlParser.additiveExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set225 = null;
      CSparqlParser.multiplicativeExpression_return multiplicativeExpression224 = null;

      CSparqlParser.multiplicativeExpression_return multiplicativeExpression226 = null;

      CSparqlParser.numericLiteralPositive_return numericLiteralPositive227 = null;

      CSparqlParser.numericLiteralNegative_return numericLiteralNegative228 = null;

      final Object set225_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "additiveExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(337, 1);

         try {
            // CSparql.g:338:5: ( multiplicativeExpression ( ( MINUS | PLUS )
            // multiplicativeExpression | numericLiteralPositive | numericLiteralNegative )*
            // )
            this.dbg.enterAlt(1);

            // CSparql.g:338:7: multiplicativeExpression ( ( MINUS | PLUS )
            // multiplicativeExpression | numericLiteralPositive | numericLiteralNegative )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(338, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_multiplicativeExpression_in_additiveExpression1764);
               multiplicativeExpression224 = this.multiplicativeExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, multiplicativeExpression224.getTree());
               this.dbg.location(338, 32);
               // CSparql.g:338:32: ( ( MINUS | PLUS ) multiplicativeExpression |
               // numericLiteralPositive | numericLiteralNegative )*
               try {
                  this.dbg.enterSubRule(64);

                  loop64: do {
                     int alt64 = 4;
                     try {
                        this.dbg.enterDecision(64);

                        switch (this.input.LA(1)) {
                           case MINUS:
                           case PLUS: {
                              alt64 = 1;
                           }
                              break;
                           case INTEGER_POSITIVE:
                           case DECIMAL_POSITIVE:
                           case DOUBLE_POSITIVE: {
                              alt64 = 2;
                           }
                              break;
                           case INTEGER_NEGATIVE:
                           case DECIMAL_NEGATIVE:
                           case DOUBLE_NEGATIVE: {
                              alt64 = 3;
                           }
                              break;

                        }

                     } finally {
                        this.dbg.exitDecision(64);
                     }

                     switch (alt64) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:338:35: ( MINUS | PLUS ) multiplicativeExpression
                           {
                              this.dbg.location(338, 35);
                              set225 = (Token) this.input.LT(1);
                              if (this.input.LA(1) >= CSparqlParser.MINUS
                                    && this.input.LA(1) <= CSparqlParser.PLUS) {
                                 this.input.consume();
                                 this.adaptor.addChild(root_0, (Object) this.adaptor
                                       .create(set225));
                                 this.state.errorRecovery = false;
                              } else {
                                 final MismatchedSetException mse = new MismatchedSetException(
                                       null, this.input);
                                 this.dbg.recognitionException(mse);
                                 throw mse;
                              }

                              this.dbg.location(338, 51);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_multiplicativeExpression_in_additiveExpression1778);
                              multiplicativeExpression226 = this.multiplicativeExpression();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, multiplicativeExpression226
                                    .getTree());

                           }
                           break;
                        case 2:
                           this.dbg.enterAlt(2);

                           // CSparql.g:339:14: numericLiteralPositive
                           {
                              this.dbg.location(339, 14);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_numericLiteralPositive_in_additiveExpression1795);
                              numericLiteralPositive227 = this.numericLiteralPositive();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, numericLiteralPositive227
                                    .getTree());

                           }
                           break;
                        case 3:
                           this.dbg.enterAlt(3);

                           // CSparql.g:340:14: numericLiteralNegative
                           {
                              this.dbg.location(340, 14);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_numericLiteralNegative_in_additiveExpression1811);
                              numericLiteralNegative228 = this.numericLiteralNegative();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, numericLiteralNegative228
                                    .getTree());

                           }
                           break;

                        default:
                           break loop64;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(64);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(342, 5);

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
   // CSparql.g:344:1: multiplicativeExpression : unaryExpression ( ( ASTERISK | DIVIDE )
   // unaryExpression )* ;
   public final CSparqlParser.multiplicativeExpression_return multiplicativeExpression()
         throws RecognitionException {
      final CSparqlParser.multiplicativeExpression_return retval = new CSparqlParser.multiplicativeExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set230 = null;
      CSparqlParser.unaryExpression_return unaryExpression229 = null;

      CSparqlParser.unaryExpression_return unaryExpression231 = null;

      final Object set230_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "multiplicativeExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(344, 1);

         try {
            // CSparql.g:345:5: ( unaryExpression ( ( ASTERISK | DIVIDE ) unaryExpression )*
            // )
            this.dbg.enterAlt(1);

            // CSparql.g:345:7: unaryExpression ( ( ASTERISK | DIVIDE ) unaryExpression )*
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(345, 7);
               this
                     .pushFollow(CSparqlParser.FOLLOW_unaryExpression_in_multiplicativeExpression1844);
               unaryExpression229 = this.unaryExpression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, unaryExpression229.getTree());
               this.dbg.location(345, 23);
               // CSparql.g:345:23: ( ( ASTERISK | DIVIDE ) unaryExpression )*
               try {
                  this.dbg.enterSubRule(65);

                  loop65: do {
                     int alt65 = 2;
                     try {
                        this.dbg.enterDecision(65);

                        final int LA65_0 = this.input.LA(1);

                        if (LA65_0 == CSparqlParser.ASTERISK
                              || LA65_0 == CSparqlParser.DIVIDE) {
                           alt65 = 1;
                        }

                     } finally {
                        this.dbg.exitDecision(65);
                     }

                     switch (alt65) {
                        case 1:
                           this.dbg.enterAlt(1);

                           // CSparql.g:345:25: ( ASTERISK | DIVIDE ) unaryExpression
                           {
                              this.dbg.location(345, 25);
                              set230 = (Token) this.input.LT(1);
                              if (this.input.LA(1) == CSparqlParser.ASTERISK
                                    || this.input.LA(1) == CSparqlParser.DIVIDE) {
                                 this.input.consume();
                                 this.adaptor.addChild(root_0, (Object) this.adaptor
                                       .create(set230));
                                 this.state.errorRecovery = false;
                              } else {
                                 final MismatchedSetException mse = new MismatchedSetException(
                                       null, this.input);
                                 this.dbg.recognitionException(mse);
                                 throw mse;
                              }

                              this.dbg.location(345, 45);
                              this
                                    .pushFollow(CSparqlParser.FOLLOW_unaryExpression_in_multiplicativeExpression1856);
                              unaryExpression231 = this.unaryExpression();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, unaryExpression231.getTree());

                           }
                           break;

                        default:
                           break loop65;
                     }
                  } while (true);
               } finally {
                  this.dbg.exitSubRule(65);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(346, 5);

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
   // CSparql.g:348:1: unaryExpression : ( NOT primaryExpression | PLUS primaryExpression |
   // MINUS primaryExpression | primaryExpression );
   public final CSparqlParser.unaryExpression_return unaryExpression()
         throws RecognitionException {
      final CSparqlParser.unaryExpression_return retval = new CSparqlParser.unaryExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NOT232 = null;
      Token PLUS234 = null;
      Token MINUS236 = null;
      CSparqlParser.primaryExpression_return primaryExpression233 = null;

      CSparqlParser.primaryExpression_return primaryExpression235 = null;

      CSparqlParser.primaryExpression_return primaryExpression237 = null;

      CSparqlParser.primaryExpression_return primaryExpression238 = null;

      Object NOT232_tree = null;
      Object PLUS234_tree = null;
      Object MINUS236_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "unaryExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(348, 1);

         try {
            // CSparql.g:349:5: ( NOT primaryExpression | PLUS primaryExpression | MINUS
            // primaryExpression | primaryExpression )
            int alt66 = 4;
            try {
               this.dbg.enterDecision(66);

               switch (this.input.LA(1)) {
                  case NOT: {
                     alt66 = 1;
                  }
                     break;
                  case PLUS: {
                     alt66 = 2;
                  }
                     break;
                  case MINUS: {
                     alt66 = 3;
                  }
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case OPEN_BRACE:
                  case INTEGER:
                  case EXISTS:
                  case NOT_BY_WORDS:
                  case VAR1:
                  case VAR2:
                  case TIMESTAMP:
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
                  case COUNT:
                  case SUM:
                  case MIN:
                  case MAX:
                  case AVG:
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
                  case PNAME_LN: {
                     alt66 = 4;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 66, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(66);
            }

            switch (alt66) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:349:7: NOT primaryExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(349, 7);
                     NOT232 = (Token) this.match(this.input, CSparqlParser.NOT,
                           CSparqlParser.FOLLOW_NOT_in_unaryExpression1876);
                     NOT232_tree = (Object) this.adaptor.create(NOT232);
                     this.adaptor.addChild(root_0, NOT232_tree);

                     this.dbg.location(349, 11);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_primaryExpression_in_unaryExpression1878);
                     primaryExpression233 = this.primaryExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, primaryExpression233.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:350:7: PLUS primaryExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(350, 7);
                     PLUS234 = (Token) this.match(this.input, CSparqlParser.PLUS,
                           CSparqlParser.FOLLOW_PLUS_in_unaryExpression1886);
                     PLUS234_tree = (Object) this.adaptor.create(PLUS234);
                     this.adaptor.addChild(root_0, PLUS234_tree);

                     this.dbg.location(350, 12);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_primaryExpression_in_unaryExpression1888);
                     primaryExpression235 = this.primaryExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, primaryExpression235.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:351:7: MINUS primaryExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(351, 7);
                     MINUS236 = (Token) this.match(this.input, CSparqlParser.MINUS,
                           CSparqlParser.FOLLOW_MINUS_in_unaryExpression1896);
                     MINUS236_tree = (Object) this.adaptor.create(MINUS236);
                     this.adaptor.addChild(root_0, MINUS236_tree);

                     this.dbg.location(351, 13);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_primaryExpression_in_unaryExpression1898);
                     primaryExpression237 = this.primaryExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, primaryExpression237.getTree());

                  }
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  // CSparql.g:352:7: primaryExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(352, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_primaryExpression_in_unaryExpression1906);
                     primaryExpression238 = this.primaryExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, primaryExpression238.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(353, 5);

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
   // CSparql.g:355:1: primaryExpression : ( brackettedExpression | builtInCall |
   // iriRefOrFunction | rdfLiteral | numericLiteral | booleanLiteral | var | timestampCall |
   // aggregate );
   public final CSparqlParser.primaryExpression_return primaryExpression()
         throws RecognitionException {
      final CSparqlParser.primaryExpression_return retval = new CSparqlParser.primaryExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.brackettedExpression_return brackettedExpression239 = null;

      CSparqlParser.builtInCall_return builtInCall240 = null;

      CSparqlParser.iriRefOrFunction_return iriRefOrFunction241 = null;

      CSparqlParser.rdfLiteral_return rdfLiteral242 = null;

      CSparqlParser.numericLiteral_return numericLiteral243 = null;

      CSparqlParser.booleanLiteral_return booleanLiteral244 = null;

      CSparqlParser.var_return var245 = null;

      CSparqlParser.timestampCall_return timestampCall246 = null;

      CSparqlParser.aggregate_return aggregate247 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "primaryExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(355, 1);

         try {
            // CSparql.g:356:5: ( brackettedExpression | builtInCall | iriRefOrFunction |
            // rdfLiteral | numericLiteral | booleanLiteral | var | timestampCall | aggregate
            // )
            int alt67 = 9;
            try {
               this.dbg.enterDecision(67);

               switch (this.input.LA(1)) {
                  case OPEN_BRACE: {
                     alt67 = 1;
                  }
                     break;
                  case EXISTS:
                  case NOT_BY_WORDS:
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
                  case REGEX: {
                     alt67 = 2;
                  }
                     break;
                  case IRI_REF:
                  case PNAME_NS:
                  case PNAME_LN: {
                     alt67 = 3;
                  }
                     break;
                  case STRING_LITERAL1:
                  case STRING_LITERAL2:
                  case STRING_LITERAL_LONG1:
                  case STRING_LITERAL_LONG2: {
                     alt67 = 4;
                  }
                     break;
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE:
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE:
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE: {
                     alt67 = 5;
                  }
                     break;
                  case TRUE:
                  case FALSE: {
                     alt67 = 6;
                  }
                     break;
                  case VAR1:
                  case VAR2: {
                     alt67 = 7;
                  }
                     break;
                  case TIMESTAMP: {
                     alt67 = 8;
                  }
                     break;
                  case COUNT:
                  case SUM:
                  case MIN:
                  case MAX:
                  case AVG: {
                     alt67 = 9;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 67, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(67);
            }

            switch (alt67) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:356:7: brackettedExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(356, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_brackettedExpression_in_primaryExpression1923);
                     brackettedExpression239 = this.brackettedExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, brackettedExpression239.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:357:7: builtInCall
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(357, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_builtInCall_in_primaryExpression1932);
                     builtInCall240 = this.builtInCall();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, builtInCall240.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:358:7: iriRefOrFunction
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(358, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_iriRefOrFunction_in_primaryExpression1941);
                     iriRefOrFunction241 = this.iriRefOrFunction();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, iriRefOrFunction241.getTree());

                  }
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  // CSparql.g:359:7: rdfLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(359, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_rdfLiteral_in_primaryExpression1950);
                     rdfLiteral242 = this.rdfLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, rdfLiteral242.getTree());

                  }
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  // CSparql.g:360:7: numericLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(360, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_numericLiteral_in_primaryExpression1959);
                     numericLiteral243 = this.numericLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, numericLiteral243.getTree());

                  }
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  // CSparql.g:361:7: booleanLiteral
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(361, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_booleanLiteral_in_primaryExpression1968);
                     booleanLiteral244 = this.booleanLiteral();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, booleanLiteral244.getTree());

                  }
                  break;
               case 7:
                  this.dbg.enterAlt(7);

                  // CSparql.g:362:7: var
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(362, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_var_in_primaryExpression1977);
                     var245 = this.var();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, var245.getTree());

                  }
                  break;
               case 8:
                  this.dbg.enterAlt(8);

                  // CSparql.g:363:7: timestampCall
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(363, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_timestampCall_in_primaryExpression1986);
                     timestampCall246 = this.timestampCall();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, timestampCall246.getTree());

                  }
                  break;
               case 9:
                  this.dbg.enterAlt(9);

                  // CSparql.g:364:7: aggregate
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(364, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_aggregate_in_primaryExpression1994);
                     aggregate247 = this.aggregate();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, aggregate247.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(365, 5);

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

   public static class timestampCall_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "timestampCall"
   // CSparql.g:367:1: timestampCall : TIMESTAMP OPEN_BRACE var ( iriRef |
   // graphPatternNotTriples )? CLOSE_BRACE ;
   public final CSparqlParser.timestampCall_return timestampCall()
         throws RecognitionException {
      final CSparqlParser.timestampCall_return retval = new CSparqlParser.timestampCall_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token TIMESTAMP248 = null;
      Token OPEN_BRACE249 = null;
      Token CLOSE_BRACE253 = null;
      CSparqlParser.var_return var250 = null;

      CSparqlParser.iriRef_return iriRef251 = null;

      CSparqlParser.graphPatternNotTriples_return graphPatternNotTriples252 = null;

      Object TIMESTAMP248_tree = null;
      Object OPEN_BRACE249_tree = null;
      Object CLOSE_BRACE253_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "timestampCall");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(367, 1);

         try {
            // CSparql.g:368:2: ( TIMESTAMP OPEN_BRACE var ( iriRef | graphPatternNotTriples
            // )? CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:368:4: TIMESTAMP OPEN_BRACE var ( iriRef | graphPatternNotTriples )?
            // CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(368, 4);
               TIMESTAMP248 = (Token) this.match(this.input, CSparqlParser.TIMESTAMP,
                     CSparqlParser.FOLLOW_TIMESTAMP_in_timestampCall2013);
               TIMESTAMP248_tree = (Object) this.adaptor.create(TIMESTAMP248);
               this.adaptor.addChild(root_0, TIMESTAMP248_tree);

               this.dbg.location(368, 14);
               OPEN_BRACE249 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_timestampCall2015);
               OPEN_BRACE249_tree = (Object) this.adaptor.create(OPEN_BRACE249);
               this.adaptor.addChild(root_0, OPEN_BRACE249_tree);

               this.dbg.location(368, 25);
               this.pushFollow(CSparqlParser.FOLLOW_var_in_timestampCall2017);
               var250 = this.var();

               this.state._fsp--;

               this.adaptor.addChild(root_0, var250.getTree());
               this.dbg.location(368, 29);
               // CSparql.g:368:29: ( iriRef | graphPatternNotTriples )?
               int alt68 = 3;
               try {
                  this.dbg.enterSubRule(68);
                  try {
                     this.dbg.enterDecision(68);

                     final int LA68_0 = this.input.LA(1);

                     if (LA68_0 == CSparqlParser.IRI_REF || LA68_0 == CSparqlParser.PNAME_NS
                           || LA68_0 == CSparqlParser.PNAME_LN) {
                        alt68 = 1;
                     } else if (LA68_0 == CSparqlParser.OPEN_CURLY_BRACE
                           || LA68_0 >= CSparqlParser.EXISTS
                           && LA68_0 <= CSparqlParser.GRAPH) {
                        alt68 = 2;
                     }
                  } finally {
                     this.dbg.exitDecision(68);
                  }

                  switch (alt68) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:368:30: iriRef
                        {
                           this.dbg.location(368, 30);
                           this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_timestampCall2020);
                           iriRef251 = this.iriRef();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, iriRef251.getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:368:39: graphPatternNotTriples
                        {
                           this.dbg.location(368, 39);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_graphPatternNotTriples_in_timestampCall2024);
                           graphPatternNotTriples252 = this.graphPatternNotTriples();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, graphPatternNotTriples252.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(68);
               }

               this.dbg.location(368, 64);
               CLOSE_BRACE253 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_timestampCall2028);
               CLOSE_BRACE253_tree = (Object) this.adaptor.create(CLOSE_BRACE253);
               this.adaptor.addChild(root_0, CLOSE_BRACE253_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(369, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "timestampCall");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "timestampCall"

   public static class brackettedExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "brackettedExpression"
   // CSparql.g:371:1: brackettedExpression : OPEN_BRACE expression CLOSE_BRACE ;
   public final CSparqlParser.brackettedExpression_return brackettedExpression()
         throws RecognitionException {
      final CSparqlParser.brackettedExpression_return retval = new CSparqlParser.brackettedExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token OPEN_BRACE254 = null;
      Token CLOSE_BRACE256 = null;
      CSparqlParser.expression_return expression255 = null;

      Object OPEN_BRACE254_tree = null;
      Object CLOSE_BRACE256_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "brackettedExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(371, 1);

         try {
            // CSparql.g:372:5: ( OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:372:7: OPEN_BRACE expression CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(372, 7);
               OPEN_BRACE254 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_brackettedExpression2043);
               OPEN_BRACE254_tree = (Object) this.adaptor.create(OPEN_BRACE254);
               this.adaptor.addChild(root_0, OPEN_BRACE254_tree);

               this.dbg.location(372, 18);
               this.pushFollow(CSparqlParser.FOLLOW_expression_in_brackettedExpression2045);
               expression255 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression255.getTree());
               this.dbg.location(372, 29);
               CLOSE_BRACE256 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_brackettedExpression2047);
               CLOSE_BRACE256_tree = (Object) this.adaptor.create(CLOSE_BRACE256);
               this.adaptor.addChild(root_0, CLOSE_BRACE256_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(373, 5);

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
   // CSparql.g:375:1: builtInCall : ( STR OPEN_BRACE expression CLOSE_BRACE | LANG
   // OPEN_BRACE expression CLOSE_BRACE | LANGMATCHES OPEN_BRACE expression COMMA expression
   // CLOSE_BRACE | DATATYPE OPEN_BRACE expression CLOSE_BRACE | BOUND OPEN_BRACE var
   // CLOSE_BRACE | SAMETERM OPEN_BRACE expression COMMA expression CLOSE_BRACE | ISIRI
   // OPEN_BRACE expression CLOSE_BRACE | ISURI OPEN_BRACE expression CLOSE_BRACE | ISBLANK
   // OPEN_BRACE expression CLOSE_BRACE | ISLITERAL OPEN_BRACE expression CLOSE_BRACE |
   // regexExpression | existsFunc | notExistsFunc );
   public final CSparqlParser.builtInCall_return builtInCall() throws RecognitionException {
      final CSparqlParser.builtInCall_return retval = new CSparqlParser.builtInCall_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token STR257 = null;
      Token OPEN_BRACE258 = null;
      Token CLOSE_BRACE260 = null;
      Token LANG261 = null;
      Token OPEN_BRACE262 = null;
      Token CLOSE_BRACE264 = null;
      Token LANGMATCHES265 = null;
      Token OPEN_BRACE266 = null;
      Token COMMA268 = null;
      Token CLOSE_BRACE270 = null;
      Token DATATYPE271 = null;
      Token OPEN_BRACE272 = null;
      Token CLOSE_BRACE274 = null;
      Token BOUND275 = null;
      Token OPEN_BRACE276 = null;
      Token CLOSE_BRACE278 = null;
      Token SAMETERM279 = null;
      Token OPEN_BRACE280 = null;
      Token COMMA282 = null;
      Token CLOSE_BRACE284 = null;
      Token ISIRI285 = null;
      Token OPEN_BRACE286 = null;
      Token CLOSE_BRACE288 = null;
      Token ISURI289 = null;
      Token OPEN_BRACE290 = null;
      Token CLOSE_BRACE292 = null;
      Token ISBLANK293 = null;
      Token OPEN_BRACE294 = null;
      Token CLOSE_BRACE296 = null;
      Token ISLITERAL297 = null;
      Token OPEN_BRACE298 = null;
      Token CLOSE_BRACE300 = null;
      CSparqlParser.expression_return expression259 = null;

      CSparqlParser.expression_return expression263 = null;

      CSparqlParser.expression_return expression267 = null;

      CSparqlParser.expression_return expression269 = null;

      CSparqlParser.expression_return expression273 = null;

      CSparqlParser.var_return var277 = null;

      CSparqlParser.expression_return expression281 = null;

      CSparqlParser.expression_return expression283 = null;

      CSparqlParser.expression_return expression287 = null;

      CSparqlParser.expression_return expression291 = null;

      CSparqlParser.expression_return expression295 = null;

      CSparqlParser.expression_return expression299 = null;

      CSparqlParser.regexExpression_return regexExpression301 = null;

      CSparqlParser.existsFunc_return existsFunc302 = null;

      CSparqlParser.notExistsFunc_return notExistsFunc303 = null;

      Object STR257_tree = null;
      Object OPEN_BRACE258_tree = null;
      Object CLOSE_BRACE260_tree = null;
      Object LANG261_tree = null;
      Object OPEN_BRACE262_tree = null;
      Object CLOSE_BRACE264_tree = null;
      Object LANGMATCHES265_tree = null;
      Object OPEN_BRACE266_tree = null;
      Object COMMA268_tree = null;
      Object CLOSE_BRACE270_tree = null;
      Object DATATYPE271_tree = null;
      Object OPEN_BRACE272_tree = null;
      Object CLOSE_BRACE274_tree = null;
      Object BOUND275_tree = null;
      Object OPEN_BRACE276_tree = null;
      Object CLOSE_BRACE278_tree = null;
      Object SAMETERM279_tree = null;
      Object OPEN_BRACE280_tree = null;
      Object COMMA282_tree = null;
      Object CLOSE_BRACE284_tree = null;
      Object ISIRI285_tree = null;
      Object OPEN_BRACE286_tree = null;
      Object CLOSE_BRACE288_tree = null;
      Object ISURI289_tree = null;
      Object OPEN_BRACE290_tree = null;
      Object CLOSE_BRACE292_tree = null;
      Object ISBLANK293_tree = null;
      Object OPEN_BRACE294_tree = null;
      Object CLOSE_BRACE296_tree = null;
      Object ISLITERAL297_tree = null;
      Object OPEN_BRACE298_tree = null;
      Object CLOSE_BRACE300_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "builtInCall");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(375, 1);

         try {
            // CSparql.g:376:5: ( STR OPEN_BRACE expression CLOSE_BRACE | LANG OPEN_BRACE
            // expression CLOSE_BRACE | LANGMATCHES OPEN_BRACE expression COMMA expression
            // CLOSE_BRACE | DATATYPE OPEN_BRACE expression CLOSE_BRACE | BOUND OPEN_BRACE
            // var CLOSE_BRACE | SAMETERM OPEN_BRACE expression COMMA expression CLOSE_BRACE
            // | ISIRI OPEN_BRACE expression CLOSE_BRACE | ISURI OPEN_BRACE expression
            // CLOSE_BRACE | ISBLANK OPEN_BRACE expression CLOSE_BRACE | ISLITERAL OPEN_BRACE
            // expression CLOSE_BRACE | regexExpression | existsFunc | notExistsFunc )
            int alt69 = 13;
            try {
               this.dbg.enterDecision(69);

               switch (this.input.LA(1)) {
                  case STR: {
                     alt69 = 1;
                  }
                     break;
                  case LANG: {
                     alt69 = 2;
                  }
                     break;
                  case LANGMATCHES: {
                     alt69 = 3;
                  }
                     break;
                  case DATATYPE: {
                     alt69 = 4;
                  }
                     break;
                  case BOUND: {
                     alt69 = 5;
                  }
                     break;
                  case SAMETERM: {
                     alt69 = 6;
                  }
                     break;
                  case ISIRI: {
                     alt69 = 7;
                  }
                     break;
                  case ISURI: {
                     alt69 = 8;
                  }
                     break;
                  case ISBLANK: {
                     alt69 = 9;
                  }
                     break;
                  case ISLITERAL: {
                     alt69 = 10;
                  }
                     break;
                  case REGEX: {
                     alt69 = 11;
                  }
                     break;
                  case EXISTS: {
                     alt69 = 12;
                  }
                     break;
                  case NOT_BY_WORDS: {
                     alt69 = 13;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 69, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(69);
            }

            switch (alt69) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:376:7: STR OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(376, 7);
                     STR257 = (Token) this.match(this.input, CSparqlParser.STR,
                           CSparqlParser.FOLLOW_STR_in_builtInCall2065);
                     STR257_tree = (Object) this.adaptor.create(STR257);
                     this.adaptor.addChild(root_0, STR257_tree);

                     this.dbg.location(376, 11);
                     OPEN_BRACE258 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2067);
                     OPEN_BRACE258_tree = (Object) this.adaptor.create(OPEN_BRACE258);
                     this.adaptor.addChild(root_0, OPEN_BRACE258_tree);

                     this.dbg.location(376, 22);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2069);
                     expression259 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression259.getTree());
                     this.dbg.location(376, 33);
                     CLOSE_BRACE260 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2071);
                     CLOSE_BRACE260_tree = (Object) this.adaptor.create(CLOSE_BRACE260);
                     this.adaptor.addChild(root_0, CLOSE_BRACE260_tree);

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:377:7: LANG OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(377, 7);
                     LANG261 = (Token) this.match(this.input, CSparqlParser.LANG,
                           CSparqlParser.FOLLOW_LANG_in_builtInCall2079);
                     LANG261_tree = (Object) this.adaptor.create(LANG261);
                     this.adaptor.addChild(root_0, LANG261_tree);

                     this.dbg.location(377, 12);
                     OPEN_BRACE262 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2081);
                     OPEN_BRACE262_tree = (Object) this.adaptor.create(OPEN_BRACE262);
                     this.adaptor.addChild(root_0, OPEN_BRACE262_tree);

                     this.dbg.location(377, 23);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2083);
                     expression263 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression263.getTree());
                     this.dbg.location(377, 34);
                     CLOSE_BRACE264 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2085);
                     CLOSE_BRACE264_tree = (Object) this.adaptor.create(CLOSE_BRACE264);
                     this.adaptor.addChild(root_0, CLOSE_BRACE264_tree);

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:378:7: LANGMATCHES OPEN_BRACE expression COMMA expression
                  // CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(378, 7);
                     LANGMATCHES265 = (Token) this.match(this.input,
                           CSparqlParser.LANGMATCHES,
                           CSparqlParser.FOLLOW_LANGMATCHES_in_builtInCall2093);
                     LANGMATCHES265_tree = (Object) this.adaptor.create(LANGMATCHES265);
                     this.adaptor.addChild(root_0, LANGMATCHES265_tree);

                     this.dbg.location(378, 19);
                     OPEN_BRACE266 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2095);
                     OPEN_BRACE266_tree = (Object) this.adaptor.create(OPEN_BRACE266);
                     this.adaptor.addChild(root_0, OPEN_BRACE266_tree);

                     this.dbg.location(378, 30);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2097);
                     expression267 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression267.getTree());
                     this.dbg.location(378, 41);
                     COMMA268 = (Token) this.match(this.input, CSparqlParser.COMMA,
                           CSparqlParser.FOLLOW_COMMA_in_builtInCall2099);
                     COMMA268_tree = (Object) this.adaptor.create(COMMA268);
                     this.adaptor.addChild(root_0, COMMA268_tree);

                     this.dbg.location(378, 47);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2101);
                     expression269 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression269.getTree());
                     this.dbg.location(378, 58);
                     CLOSE_BRACE270 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2103);
                     CLOSE_BRACE270_tree = (Object) this.adaptor.create(CLOSE_BRACE270);
                     this.adaptor.addChild(root_0, CLOSE_BRACE270_tree);

                  }
                  break;
               case 4:
                  this.dbg.enterAlt(4);

                  // CSparql.g:379:7: DATATYPE OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(379, 7);
                     DATATYPE271 = (Token) this.match(this.input, CSparqlParser.DATATYPE,
                           CSparqlParser.FOLLOW_DATATYPE_in_builtInCall2111);
                     DATATYPE271_tree = (Object) this.adaptor.create(DATATYPE271);
                     this.adaptor.addChild(root_0, DATATYPE271_tree);

                     this.dbg.location(379, 16);
                     OPEN_BRACE272 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2113);
                     OPEN_BRACE272_tree = (Object) this.adaptor.create(OPEN_BRACE272);
                     this.adaptor.addChild(root_0, OPEN_BRACE272_tree);

                     this.dbg.location(379, 27);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2115);
                     expression273 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression273.getTree());
                     this.dbg.location(379, 38);
                     CLOSE_BRACE274 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2117);
                     CLOSE_BRACE274_tree = (Object) this.adaptor.create(CLOSE_BRACE274);
                     this.adaptor.addChild(root_0, CLOSE_BRACE274_tree);

                  }
                  break;
               case 5:
                  this.dbg.enterAlt(5);

                  // CSparql.g:380:7: BOUND OPEN_BRACE var CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(380, 7);
                     BOUND275 = (Token) this.match(this.input, CSparqlParser.BOUND,
                           CSparqlParser.FOLLOW_BOUND_in_builtInCall2125);
                     BOUND275_tree = (Object) this.adaptor.create(BOUND275);
                     this.adaptor.addChild(root_0, BOUND275_tree);

                     this.dbg.location(380, 13);
                     OPEN_BRACE276 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2127);
                     OPEN_BRACE276_tree = (Object) this.adaptor.create(OPEN_BRACE276);
                     this.adaptor.addChild(root_0, OPEN_BRACE276_tree);

                     this.dbg.location(380, 24);
                     this.pushFollow(CSparqlParser.FOLLOW_var_in_builtInCall2129);
                     var277 = this.var();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, var277.getTree());
                     this.dbg.location(380, 28);
                     CLOSE_BRACE278 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2131);
                     CLOSE_BRACE278_tree = (Object) this.adaptor.create(CLOSE_BRACE278);
                     this.adaptor.addChild(root_0, CLOSE_BRACE278_tree);

                  }
                  break;
               case 6:
                  this.dbg.enterAlt(6);

                  // CSparql.g:381:7: SAMETERM OPEN_BRACE expression COMMA expression
                  // CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(381, 7);
                     SAMETERM279 = (Token) this.match(this.input, CSparqlParser.SAMETERM,
                           CSparqlParser.FOLLOW_SAMETERM_in_builtInCall2139);
                     SAMETERM279_tree = (Object) this.adaptor.create(SAMETERM279);
                     this.adaptor.addChild(root_0, SAMETERM279_tree);

                     this.dbg.location(381, 16);
                     OPEN_BRACE280 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2141);
                     OPEN_BRACE280_tree = (Object) this.adaptor.create(OPEN_BRACE280);
                     this.adaptor.addChild(root_0, OPEN_BRACE280_tree);

                     this.dbg.location(381, 27);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2143);
                     expression281 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression281.getTree());
                     this.dbg.location(381, 38);
                     COMMA282 = (Token) this.match(this.input, CSparqlParser.COMMA,
                           CSparqlParser.FOLLOW_COMMA_in_builtInCall2145);
                     COMMA282_tree = (Object) this.adaptor.create(COMMA282);
                     this.adaptor.addChild(root_0, COMMA282_tree);

                     this.dbg.location(381, 44);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2147);
                     expression283 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression283.getTree());
                     this.dbg.location(381, 55);
                     CLOSE_BRACE284 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2149);
                     CLOSE_BRACE284_tree = (Object) this.adaptor.create(CLOSE_BRACE284);
                     this.adaptor.addChild(root_0, CLOSE_BRACE284_tree);

                  }
                  break;
               case 7:
                  this.dbg.enterAlt(7);

                  // CSparql.g:382:7: ISIRI OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(382, 7);
                     ISIRI285 = (Token) this.match(this.input, CSparqlParser.ISIRI,
                           CSparqlParser.FOLLOW_ISIRI_in_builtInCall2157);
                     ISIRI285_tree = (Object) this.adaptor.create(ISIRI285);
                     this.adaptor.addChild(root_0, ISIRI285_tree);

                     this.dbg.location(382, 13);
                     OPEN_BRACE286 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2159);
                     OPEN_BRACE286_tree = (Object) this.adaptor.create(OPEN_BRACE286);
                     this.adaptor.addChild(root_0, OPEN_BRACE286_tree);

                     this.dbg.location(382, 24);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2161);
                     expression287 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression287.getTree());
                     this.dbg.location(382, 35);
                     CLOSE_BRACE288 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2163);
                     CLOSE_BRACE288_tree = (Object) this.adaptor.create(CLOSE_BRACE288);
                     this.adaptor.addChild(root_0, CLOSE_BRACE288_tree);

                  }
                  break;
               case 8:
                  this.dbg.enterAlt(8);

                  // CSparql.g:383:7: ISURI OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(383, 7);
                     ISURI289 = (Token) this.match(this.input, CSparqlParser.ISURI,
                           CSparqlParser.FOLLOW_ISURI_in_builtInCall2171);
                     ISURI289_tree = (Object) this.adaptor.create(ISURI289);
                     this.adaptor.addChild(root_0, ISURI289_tree);

                     this.dbg.location(383, 13);
                     OPEN_BRACE290 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2173);
                     OPEN_BRACE290_tree = (Object) this.adaptor.create(OPEN_BRACE290);
                     this.adaptor.addChild(root_0, OPEN_BRACE290_tree);

                     this.dbg.location(383, 24);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2175);
                     expression291 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression291.getTree());
                     this.dbg.location(383, 35);
                     CLOSE_BRACE292 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2177);
                     CLOSE_BRACE292_tree = (Object) this.adaptor.create(CLOSE_BRACE292);
                     this.adaptor.addChild(root_0, CLOSE_BRACE292_tree);

                  }
                  break;
               case 9:
                  this.dbg.enterAlt(9);

                  // CSparql.g:384:7: ISBLANK OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(384, 7);
                     ISBLANK293 = (Token) this.match(this.input, CSparqlParser.ISBLANK,
                           CSparqlParser.FOLLOW_ISBLANK_in_builtInCall2185);
                     ISBLANK293_tree = (Object) this.adaptor.create(ISBLANK293);
                     this.adaptor.addChild(root_0, ISBLANK293_tree);

                     this.dbg.location(384, 15);
                     OPEN_BRACE294 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2187);
                     OPEN_BRACE294_tree = (Object) this.adaptor.create(OPEN_BRACE294);
                     this.adaptor.addChild(root_0, OPEN_BRACE294_tree);

                     this.dbg.location(384, 26);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2189);
                     expression295 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression295.getTree());
                     this.dbg.location(384, 37);
                     CLOSE_BRACE296 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2191);
                     CLOSE_BRACE296_tree = (Object) this.adaptor.create(CLOSE_BRACE296);
                     this.adaptor.addChild(root_0, CLOSE_BRACE296_tree);

                  }
                  break;
               case 10:
                  this.dbg.enterAlt(10);

                  // CSparql.g:385:7: ISLITERAL OPEN_BRACE expression CLOSE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(385, 7);
                     ISLITERAL297 = (Token) this.match(this.input, CSparqlParser.ISLITERAL,
                           CSparqlParser.FOLLOW_ISLITERAL_in_builtInCall2199);
                     ISLITERAL297_tree = (Object) this.adaptor.create(ISLITERAL297);
                     this.adaptor.addChild(root_0, ISLITERAL297_tree);

                     this.dbg.location(385, 17);
                     OPEN_BRACE298 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_BRACE,
                           CSparqlParser.FOLLOW_OPEN_BRACE_in_builtInCall2201);
                     OPEN_BRACE298_tree = (Object) this.adaptor.create(OPEN_BRACE298);
                     this.adaptor.addChild(root_0, OPEN_BRACE298_tree);

                     this.dbg.location(385, 28);
                     this.pushFollow(CSparqlParser.FOLLOW_expression_in_builtInCall2203);
                     expression299 = this.expression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, expression299.getTree());
                     this.dbg.location(385, 39);
                     CLOSE_BRACE300 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_BRACE_in_builtInCall2205);
                     CLOSE_BRACE300_tree = (Object) this.adaptor.create(CLOSE_BRACE300);
                     this.adaptor.addChild(root_0, CLOSE_BRACE300_tree);

                  }
                  break;
               case 11:
                  this.dbg.enterAlt(11);

                  // CSparql.g:386:7: regexExpression
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(386, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_regexExpression_in_builtInCall2213);
                     regexExpression301 = this.regexExpression();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, regexExpression301.getTree());

                  }
                  break;
               case 12:
                  this.dbg.enterAlt(12);

                  // CSparql.g:387:7: existsFunc
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(387, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_existsFunc_in_builtInCall2221);
                     existsFunc302 = this.existsFunc();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, existsFunc302.getTree());

                  }
                  break;
               case 13:
                  this.dbg.enterAlt(13);

                  // CSparql.g:388:7: notExistsFunc
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(388, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_notExistsFunc_in_builtInCall2230);
                     notExistsFunc303 = this.notExistsFunc();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, notExistsFunc303.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(389, 5);

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

   public static class existsFunc_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "existsFunc"
   // CSparql.g:390:1: existsFunc : EXISTS groupGraphPattern ;
   public final CSparqlParser.existsFunc_return existsFunc() throws RecognitionException {
      final CSparqlParser.existsFunc_return retval = new CSparqlParser.existsFunc_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EXISTS304 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern305 = null;

      Object EXISTS304_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "existsFunc");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(390, 1);

         try {
            // CSparql.g:391:2: ( EXISTS groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:391:4: EXISTS groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(391, 4);
               EXISTS304 = (Token) this.match(this.input, CSparqlParser.EXISTS,
                     CSparqlParser.FOLLOW_EXISTS_in_existsFunc2245);
               EXISTS304_tree = (Object) this.adaptor.create(EXISTS304);
               this.adaptor.addChild(root_0, EXISTS304_tree);

               this.dbg.location(391, 11);
               this.pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_existsFunc2247);
               groupGraphPattern305 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern305.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(392, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "existsFunc");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "existsFunc"

   public static class notExistsFunc_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "notExistsFunc"
   // CSparql.g:394:1: notExistsFunc : NOT_BY_WORDS EXISTS groupGraphPattern ;
   public final CSparqlParser.notExistsFunc_return notExistsFunc()
         throws RecognitionException {
      final CSparqlParser.notExistsFunc_return retval = new CSparqlParser.notExistsFunc_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token NOT_BY_WORDS306 = null;
      Token EXISTS307 = null;
      CSparqlParser.groupGraphPattern_return groupGraphPattern308 = null;

      Object NOT_BY_WORDS306_tree = null;
      Object EXISTS307_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "notExistsFunc");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(394, 1);

         try {
            // CSparql.g:395:2: ( NOT_BY_WORDS EXISTS groupGraphPattern )
            this.dbg.enterAlt(1);

            // CSparql.g:395:4: NOT_BY_WORDS EXISTS groupGraphPattern
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(395, 4);
               NOT_BY_WORDS306 = (Token) this.match(this.input, CSparqlParser.NOT_BY_WORDS,
                     CSparqlParser.FOLLOW_NOT_BY_WORDS_in_notExistsFunc2259);
               NOT_BY_WORDS306_tree = (Object) this.adaptor.create(NOT_BY_WORDS306);
               this.adaptor.addChild(root_0, NOT_BY_WORDS306_tree);

               this.dbg.location(395, 17);
               EXISTS307 = (Token) this.match(this.input, CSparqlParser.EXISTS,
                     CSparqlParser.FOLLOW_EXISTS_in_notExistsFunc2261);
               EXISTS307_tree = (Object) this.adaptor.create(EXISTS307);
               this.adaptor.addChild(root_0, EXISTS307_tree);

               this.dbg.location(395, 24);
               this.pushFollow(CSparqlParser.FOLLOW_groupGraphPattern_in_notExistsFunc2263);
               groupGraphPattern308 = this.groupGraphPattern();

               this.state._fsp--;

               this.adaptor.addChild(root_0, groupGraphPattern308.getTree());

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(396, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "notExistsFunc");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "notExistsFunc"

   public static class aggregate_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "aggregate"
   // CSparql.g:398:1: aggregate : ( countAggregateExpression | sumAggregateExpression |
   // minAggregateExpression | maxAggregateExpression | avgAggregateExpression ) ;
   public final CSparqlParser.aggregate_return aggregate() throws RecognitionException {
      final CSparqlParser.aggregate_return retval = new CSparqlParser.aggregate_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.countAggregateExpression_return countAggregateExpression309 = null;

      CSparqlParser.sumAggregateExpression_return sumAggregateExpression310 = null;

      CSparqlParser.minAggregateExpression_return minAggregateExpression311 = null;

      CSparqlParser.maxAggregateExpression_return maxAggregateExpression312 = null;

      CSparqlParser.avgAggregateExpression_return avgAggregateExpression313 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "aggregate");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(398, 1);

         try {
            // CSparql.g:399:2: ( ( countAggregateExpression | sumAggregateExpression |
            // minAggregateExpression | maxAggregateExpression | avgAggregateExpression ) )
            this.dbg.enterAlt(1);

            // CSparql.g:400:2: ( countAggregateExpression | sumAggregateExpression |
            // minAggregateExpression | maxAggregateExpression | avgAggregateExpression )
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(400, 2);
               // CSparql.g:400:2: ( countAggregateExpression | sumAggregateExpression |
               // minAggregateExpression | maxAggregateExpression | avgAggregateExpression )
               int alt70 = 5;
               try {
                  this.dbg.enterSubRule(70);
                  try {
                     this.dbg.enterDecision(70);

                     switch (this.input.LA(1)) {
                        case COUNT: {
                           alt70 = 1;
                        }
                           break;
                        case SUM: {
                           alt70 = 2;
                        }
                           break;
                        case MIN: {
                           alt70 = 3;
                        }
                           break;
                        case MAX: {
                           alt70 = 4;
                        }
                           break;
                        case AVG: {
                           alt70 = 5;
                        }
                           break;
                        default:
                           final NoViableAltException nvae = new NoViableAltException("",
                                 70, 0, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                     }

                  } finally {
                     this.dbg.exitDecision(70);
                  }

                  switch (alt70) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:400:4: countAggregateExpression
                        {
                           this.dbg.location(400, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_countAggregateExpression_in_aggregate2279);
                           countAggregateExpression309 = this.countAggregateExpression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, countAggregateExpression309
                                 .getTree());

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:401:4: sumAggregateExpression
                        {
                           this.dbg.location(401, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_sumAggregateExpression_in_aggregate2285);
                           sumAggregateExpression310 = this.sumAggregateExpression();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, sumAggregateExpression310.getTree());

                        }
                        break;
                     case 3:
                        this.dbg.enterAlt(3);

                        // CSparql.g:402:4: minAggregateExpression
                        {
                           this.dbg.location(402, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_minAggregateExpression_in_aggregate2291);
                           minAggregateExpression311 = this.minAggregateExpression();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, minAggregateExpression311.getTree());

                        }
                        break;
                     case 4:
                        this.dbg.enterAlt(4);

                        // CSparql.g:403:4: maxAggregateExpression
                        {
                           this.dbg.location(403, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_maxAggregateExpression_in_aggregate2297);
                           maxAggregateExpression312 = this.maxAggregateExpression();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, maxAggregateExpression312.getTree());

                        }
                        break;
                     case 5:
                        this.dbg.enterAlt(5);

                        // CSparql.g:404:4: avgAggregateExpression
                        {
                           this.dbg.location(404, 4);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_avgAggregateExpression_in_aggregate2302);
                           avgAggregateExpression313 = this.avgAggregateExpression();

                           this.state._fsp--;

                           this.adaptor
                                 .addChild(root_0, avgAggregateExpression313.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(70);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(406, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "aggregate");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "aggregate"

   public static class countAggregateExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "countAggregateExpression"
   // CSparql.g:408:1: countAggregateExpression : COUNT OPEN_BRACE ( ASTERISK | var |
   // DISTINCT ( ASTERISK | var ) ) CLOSE_BRACE ;
   public final CSparqlParser.countAggregateExpression_return countAggregateExpression()
         throws RecognitionException {
      final CSparqlParser.countAggregateExpression_return retval = new CSparqlParser.countAggregateExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token COUNT314 = null;
      Token OPEN_BRACE315 = null;
      Token ASTERISK316 = null;
      Token DISTINCT318 = null;
      Token ASTERISK319 = null;
      Token CLOSE_BRACE321 = null;
      CSparqlParser.var_return var317 = null;

      CSparqlParser.var_return var320 = null;

      Object COUNT314_tree = null;
      Object OPEN_BRACE315_tree = null;
      Object ASTERISK316_tree = null;
      Object DISTINCT318_tree = null;
      Object ASTERISK319_tree = null;
      Object CLOSE_BRACE321_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "countAggregateExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(408, 1);

         try {
            // CSparql.g:409:2: ( COUNT OPEN_BRACE ( ASTERISK | var | DISTINCT ( ASTERISK |
            // var ) ) CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:409:4: COUNT OPEN_BRACE ( ASTERISK | var | DISTINCT ( ASTERISK | var
            // ) ) CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(409, 4);
               COUNT314 = (Token) this.match(this.input, CSparqlParser.COUNT,
                     CSparqlParser.FOLLOW_COUNT_in_countAggregateExpression2318);
               COUNT314_tree = (Object) this.adaptor.create(COUNT314);
               this.adaptor.addChild(root_0, COUNT314_tree);

               this.dbg.location(409, 10);
               OPEN_BRACE315 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_countAggregateExpression2320);
               OPEN_BRACE315_tree = (Object) this.adaptor.create(OPEN_BRACE315);
               this.adaptor.addChild(root_0, OPEN_BRACE315_tree);

               this.dbg.location(409, 21);
               // CSparql.g:409:21: ( ASTERISK | var | DISTINCT ( ASTERISK | var ) )
               int alt72 = 3;
               try {
                  this.dbg.enterSubRule(72);
                  try {
                     this.dbg.enterDecision(72);

                     switch (this.input.LA(1)) {
                        case ASTERISK: {
                           alt72 = 1;
                        }
                           break;
                        case VAR1:
                        case VAR2: {
                           alt72 = 2;
                        }
                           break;
                        case DISTINCT: {
                           alt72 = 3;
                        }
                           break;
                        default:
                           final NoViableAltException nvae = new NoViableAltException("",
                                 72, 0, this.input);

                           this.dbg.recognitionException(nvae);
                           throw nvae;
                     }

                  } finally {
                     this.dbg.exitDecision(72);
                  }

                  switch (alt72) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:409:23: ASTERISK
                        {
                           this.dbg.location(409, 23);
                           ASTERISK316 = (Token) this
                                 .match(
                                       this.input,
                                       CSparqlParser.ASTERISK,
                                       CSparqlParser.FOLLOW_ASTERISK_in_countAggregateExpression2324);
                           ASTERISK316_tree = (Object) this.adaptor.create(ASTERISK316);
                           this.adaptor.addChild(root_0, ASTERISK316_tree);

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:409:34: var
                        {
                           this.dbg.location(409, 34);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_var_in_countAggregateExpression2328);
                           var317 = this.var();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, var317.getTree());

                        }
                        break;
                     case 3:
                        this.dbg.enterAlt(3);

                        // CSparql.g:409:40: DISTINCT ( ASTERISK | var )
                        {
                           this.dbg.location(409, 40);
                           DISTINCT318 = (Token) this
                                 .match(
                                       this.input,
                                       CSparqlParser.DISTINCT,
                                       CSparqlParser.FOLLOW_DISTINCT_in_countAggregateExpression2332);
                           DISTINCT318_tree = (Object) this.adaptor.create(DISTINCT318);
                           this.adaptor.addChild(root_0, DISTINCT318_tree);

                           this.dbg.location(409, 49);
                           // CSparql.g:409:49: ( ASTERISK | var )
                           int alt71 = 2;
                           try {
                              this.dbg.enterSubRule(71);
                              try {
                                 this.dbg.enterDecision(71);

                                 final int LA71_0 = this.input.LA(1);

                                 if (LA71_0 == CSparqlParser.ASTERISK) {
                                    alt71 = 1;
                                 } else if (LA71_0 >= CSparqlParser.VAR1
                                       && LA71_0 <= CSparqlParser.VAR2) {
                                    alt71 = 2;
                                 } else {
                                    final NoViableAltException nvae = new NoViableAltException(
                                          "", 71, 0, this.input);

                                    this.dbg.recognitionException(nvae);
                                    throw nvae;
                                 }
                              } finally {
                                 this.dbg.exitDecision(71);
                              }

                              switch (alt71) {
                                 case 1:
                                    this.dbg.enterAlt(1);

                                    // CSparql.g:409:51: ASTERISK
                                    {
                                       this.dbg.location(409, 51);
                                       ASTERISK319 = (Token) this
                                             .match(
                                                   this.input,
                                                   CSparqlParser.ASTERISK,
                                                   CSparqlParser.FOLLOW_ASTERISK_in_countAggregateExpression2336);
                                       ASTERISK319_tree = (Object) this.adaptor
                                             .create(ASTERISK319);
                                       this.adaptor.addChild(root_0, ASTERISK319_tree);

                                    }
                                    break;
                                 case 2:
                                    this.dbg.enterAlt(2);

                                    // CSparql.g:409:62: var
                                    {
                                       this.dbg.location(409, 62);
                                       this
                                             .pushFollow(CSparqlParser.FOLLOW_var_in_countAggregateExpression2340);
                                       var320 = this.var();

                                       this.state._fsp--;

                                       this.adaptor.addChild(root_0, var320.getTree());

                                    }
                                    break;

                              }
                           } finally {
                              this.dbg.exitSubRule(71);
                           }

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(72);
               }

               this.dbg.location(409, 70);
               CLOSE_BRACE321 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_countAggregateExpression2346);
               CLOSE_BRACE321_tree = (Object) this.adaptor.create(CLOSE_BRACE321);
               this.adaptor.addChild(root_0, CLOSE_BRACE321_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(410, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "countAggregateExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "countAggregateExpression"

   public static class sumAggregateExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "sumAggregateExpression"
   // CSparql.g:412:1: sumAggregateExpression : SUM OPEN_BRACE expression CLOSE_BRACE ;
   public final CSparqlParser.sumAggregateExpression_return sumAggregateExpression()
         throws RecognitionException {
      final CSparqlParser.sumAggregateExpression_return retval = new CSparqlParser.sumAggregateExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SUM322 = null;
      Token OPEN_BRACE323 = null;
      Token CLOSE_BRACE325 = null;
      CSparqlParser.expression_return expression324 = null;

      Object SUM322_tree = null;
      Object OPEN_BRACE323_tree = null;
      Object CLOSE_BRACE325_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "sumAggregateExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(412, 1);

         try {
            // CSparql.g:413:2: ( SUM OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:413:4: SUM OPEN_BRACE expression CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(413, 4);
               SUM322 = (Token) this.match(this.input, CSparqlParser.SUM,
                     CSparqlParser.FOLLOW_SUM_in_sumAggregateExpression2359);
               SUM322_tree = (Object) this.adaptor.create(SUM322);
               this.adaptor.addChild(root_0, SUM322_tree);

               this.dbg.location(413, 8);
               OPEN_BRACE323 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_sumAggregateExpression2361);
               OPEN_BRACE323_tree = (Object) this.adaptor.create(OPEN_BRACE323);
               this.adaptor.addChild(root_0, OPEN_BRACE323_tree);

               this.dbg.location(413, 19);
               this
                     .pushFollow(CSparqlParser.FOLLOW_expression_in_sumAggregateExpression2363);
               expression324 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression324.getTree());
               this.dbg.location(413, 30);
               CLOSE_BRACE325 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_sumAggregateExpression2365);
               CLOSE_BRACE325_tree = (Object) this.adaptor.create(CLOSE_BRACE325);
               this.adaptor.addChild(root_0, CLOSE_BRACE325_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(414, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "sumAggregateExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "sumAggregateExpression"

   public static class minAggregateExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "minAggregateExpression"
   // CSparql.g:416:1: minAggregateExpression : MIN OPEN_BRACE expression CLOSE_BRACE ;
   public final CSparqlParser.minAggregateExpression_return minAggregateExpression()
         throws RecognitionException {
      final CSparqlParser.minAggregateExpression_return retval = new CSparqlParser.minAggregateExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token MIN326 = null;
      Token OPEN_BRACE327 = null;
      Token CLOSE_BRACE329 = null;
      CSparqlParser.expression_return expression328 = null;

      Object MIN326_tree = null;
      Object OPEN_BRACE327_tree = null;
      Object CLOSE_BRACE329_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "minAggregateExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(416, 1);

         try {
            // CSparql.g:417:2: ( MIN OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:417:4: MIN OPEN_BRACE expression CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(417, 4);
               MIN326 = (Token) this.match(this.input, CSparqlParser.MIN,
                     CSparqlParser.FOLLOW_MIN_in_minAggregateExpression2377);
               MIN326_tree = (Object) this.adaptor.create(MIN326);
               this.adaptor.addChild(root_0, MIN326_tree);

               this.dbg.location(417, 8);
               OPEN_BRACE327 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_minAggregateExpression2379);
               OPEN_BRACE327_tree = (Object) this.adaptor.create(OPEN_BRACE327);
               this.adaptor.addChild(root_0, OPEN_BRACE327_tree);

               this.dbg.location(417, 19);
               this
                     .pushFollow(CSparqlParser.FOLLOW_expression_in_minAggregateExpression2381);
               expression328 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression328.getTree());
               this.dbg.location(417, 30);
               CLOSE_BRACE329 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_minAggregateExpression2383);
               CLOSE_BRACE329_tree = (Object) this.adaptor.create(CLOSE_BRACE329);
               this.adaptor.addChild(root_0, CLOSE_BRACE329_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(418, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "minAggregateExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "minAggregateExpression"

   public static class maxAggregateExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "maxAggregateExpression"
   // CSparql.g:420:1: maxAggregateExpression : MAX OPEN_BRACE expression CLOSE_BRACE ;
   public final CSparqlParser.maxAggregateExpression_return maxAggregateExpression()
         throws RecognitionException {
      final CSparqlParser.maxAggregateExpression_return retval = new CSparqlParser.maxAggregateExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token MAX330 = null;
      Token OPEN_BRACE331 = null;
      Token CLOSE_BRACE333 = null;
      CSparqlParser.expression_return expression332 = null;

      Object MAX330_tree = null;
      Object OPEN_BRACE331_tree = null;
      Object CLOSE_BRACE333_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "maxAggregateExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(420, 1);

         try {
            // CSparql.g:421:2: ( MAX OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:421:4: MAX OPEN_BRACE expression CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(421, 4);
               MAX330 = (Token) this.match(this.input, CSparqlParser.MAX,
                     CSparqlParser.FOLLOW_MAX_in_maxAggregateExpression2396);
               MAX330_tree = (Object) this.adaptor.create(MAX330);
               this.adaptor.addChild(root_0, MAX330_tree);

               this.dbg.location(421, 8);
               OPEN_BRACE331 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_maxAggregateExpression2398);
               OPEN_BRACE331_tree = (Object) this.adaptor.create(OPEN_BRACE331);
               this.adaptor.addChild(root_0, OPEN_BRACE331_tree);

               this.dbg.location(421, 19);
               this
                     .pushFollow(CSparqlParser.FOLLOW_expression_in_maxAggregateExpression2400);
               expression332 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression332.getTree());
               this.dbg.location(421, 30);
               CLOSE_BRACE333 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_maxAggregateExpression2402);
               CLOSE_BRACE333_tree = (Object) this.adaptor.create(CLOSE_BRACE333);
               this.adaptor.addChild(root_0, CLOSE_BRACE333_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(422, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "maxAggregateExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "maxAggregateExpression"

   public static class avgAggregateExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "avgAggregateExpression"
   // CSparql.g:424:1: avgAggregateExpression : AVG OPEN_BRACE expression CLOSE_BRACE ;
   public final CSparqlParser.avgAggregateExpression_return avgAggregateExpression()
         throws RecognitionException {
      final CSparqlParser.avgAggregateExpression_return retval = new CSparqlParser.avgAggregateExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token AVG334 = null;
      Token OPEN_BRACE335 = null;
      Token CLOSE_BRACE337 = null;
      CSparqlParser.expression_return expression336 = null;

      Object AVG334_tree = null;
      Object OPEN_BRACE335_tree = null;
      Object CLOSE_BRACE337_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "avgAggregateExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(424, 1);

         try {
            // CSparql.g:425:2: ( AVG OPEN_BRACE expression CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:425:4: AVG OPEN_BRACE expression CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(425, 4);
               AVG334 = (Token) this.match(this.input, CSparqlParser.AVG,
                     CSparqlParser.FOLLOW_AVG_in_avgAggregateExpression2414);
               AVG334_tree = (Object) this.adaptor.create(AVG334);
               this.adaptor.addChild(root_0, AVG334_tree);

               this.dbg.location(425, 8);
               OPEN_BRACE335 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_avgAggregateExpression2416);
               OPEN_BRACE335_tree = (Object) this.adaptor.create(OPEN_BRACE335);
               this.adaptor.addChild(root_0, OPEN_BRACE335_tree);

               this.dbg.location(425, 19);
               this
                     .pushFollow(CSparqlParser.FOLLOW_expression_in_avgAggregateExpression2418);
               expression336 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression336.getTree());
               this.dbg.location(425, 30);
               CLOSE_BRACE337 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_avgAggregateExpression2420);
               CLOSE_BRACE337_tree = (Object) this.adaptor.create(CLOSE_BRACE337);
               this.adaptor.addChild(root_0, CLOSE_BRACE337_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(426, 2);

      } finally {
         this.dbg.exitRule(this.getGrammarFileName(), "avgAggregateExpression");
         this.decRuleLevel();
         if (this.getRuleLevel() == 0) {
            this.dbg.terminate();
         }
      }

      return retval;
   }

   // $ANTLR end "avgAggregateExpression"

   public static class regexExpression_return extends ParserRuleReturnScope {

      Object tree;

      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "regexExpression"
   // CSparql.g:428:1: regexExpression : REGEX OPEN_BRACE expression COMMA expression ( COMMA
   // expression )? CLOSE_BRACE ;
   public final CSparqlParser.regexExpression_return regexExpression()
         throws RecognitionException {
      final CSparqlParser.regexExpression_return retval = new CSparqlParser.regexExpression_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token REGEX338 = null;
      Token OPEN_BRACE339 = null;
      Token COMMA341 = null;
      Token COMMA343 = null;
      Token CLOSE_BRACE345 = null;
      CSparqlParser.expression_return expression340 = null;

      CSparqlParser.expression_return expression342 = null;

      CSparqlParser.expression_return expression344 = null;

      Object REGEX338_tree = null;
      Object OPEN_BRACE339_tree = null;
      Object COMMA341_tree = null;
      Object COMMA343_tree = null;
      Object CLOSE_BRACE345_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "regexExpression");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(428, 1);

         try {
            // CSparql.g:429:5: ( REGEX OPEN_BRACE expression COMMA expression ( COMMA
            // expression )? CLOSE_BRACE )
            this.dbg.enterAlt(1);

            // CSparql.g:429:7: REGEX OPEN_BRACE expression COMMA expression ( COMMA
            // expression )? CLOSE_BRACE
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(429, 7);
               REGEX338 = (Token) this.match(this.input, CSparqlParser.REGEX,
                     CSparqlParser.FOLLOW_REGEX_in_regexExpression2435);
               REGEX338_tree = (Object) this.adaptor.create(REGEX338);
               this.adaptor.addChild(root_0, REGEX338_tree);

               this.dbg.location(429, 13);
               OPEN_BRACE339 = (Token) this.match(this.input, CSparqlParser.OPEN_BRACE,
                     CSparqlParser.FOLLOW_OPEN_BRACE_in_regexExpression2437);
               OPEN_BRACE339_tree = (Object) this.adaptor.create(OPEN_BRACE339);
               this.adaptor.addChild(root_0, OPEN_BRACE339_tree);

               this.dbg.location(429, 24);
               this.pushFollow(CSparqlParser.FOLLOW_expression_in_regexExpression2439);
               expression340 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression340.getTree());
               this.dbg.location(429, 35);
               COMMA341 = (Token) this.match(this.input, CSparqlParser.COMMA,
                     CSparqlParser.FOLLOW_COMMA_in_regexExpression2441);
               COMMA341_tree = (Object) this.adaptor.create(COMMA341);
               this.adaptor.addChild(root_0, COMMA341_tree);

               this.dbg.location(429, 41);
               this.pushFollow(CSparqlParser.FOLLOW_expression_in_regexExpression2443);
               expression342 = this.expression();

               this.state._fsp--;

               this.adaptor.addChild(root_0, expression342.getTree());
               this.dbg.location(429, 52);
               // CSparql.g:429:52: ( COMMA expression )?
               int alt73 = 2;
               try {
                  this.dbg.enterSubRule(73);
                  try {
                     this.dbg.enterDecision(73);

                     final int LA73_0 = this.input.LA(1);

                     if (LA73_0 == CSparqlParser.COMMA) {
                        alt73 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(73);
                  }

                  switch (alt73) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:429:54: COMMA expression
                        {
                           this.dbg.location(429, 54);
                           COMMA343 = (Token) this.match(this.input, CSparqlParser.COMMA,
                                 CSparqlParser.FOLLOW_COMMA_in_regexExpression2447);
                           COMMA343_tree = (Object) this.adaptor.create(COMMA343);
                           this.adaptor.addChild(root_0, COMMA343_tree);

                           this.dbg.location(429, 60);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_expression_in_regexExpression2449);
                           expression344 = this.expression();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, expression344.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(73);
               }

               this.dbg.location(429, 74);
               CLOSE_BRACE345 = (Token) this.match(this.input, CSparqlParser.CLOSE_BRACE,
                     CSparqlParser.FOLLOW_CLOSE_BRACE_in_regexExpression2454);
               CLOSE_BRACE345_tree = (Object) this.adaptor.create(CLOSE_BRACE345);
               this.adaptor.addChild(root_0, CLOSE_BRACE345_tree);

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(430, 5);

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
   // CSparql.g:432:1: iriRefOrFunction : iriRef ( argList )? ;
   public final CSparqlParser.iriRefOrFunction_return iriRefOrFunction()
         throws RecognitionException {
      final CSparqlParser.iriRefOrFunction_return retval = new CSparqlParser.iriRefOrFunction_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.iriRef_return iriRef346 = null;

      CSparqlParser.argList_return argList347 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "iriRefOrFunction");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(432, 1);

         try {
            // CSparql.g:433:5: ( iriRef ( argList )? )
            this.dbg.enterAlt(1);

            // CSparql.g:433:7: iriRef ( argList )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(433, 7);
               this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_iriRefOrFunction2471);
               iriRef346 = this.iriRef();

               this.state._fsp--;

               this.adaptor.addChild(root_0, iriRef346.getTree());
               this.dbg.location(433, 14);
               // CSparql.g:433:14: ( argList )?
               int alt74 = 2;
               try {
                  this.dbg.enterSubRule(74);
                  try {
                     this.dbg.enterDecision(74);

                     final int LA74_0 = this.input.LA(1);

                     if (LA74_0 == CSparqlParser.OPEN_BRACE) {
                        alt74 = 1;
                     }
                  } finally {
                     this.dbg.exitDecision(74);
                  }

                  switch (alt74) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:433:14: argList
                        {
                           this.dbg.location(433, 14);
                           this
                                 .pushFollow(CSparqlParser.FOLLOW_argList_in_iriRefOrFunction2473);
                           argList347 = this.argList();

                           this.state._fsp--;

                           this.adaptor.addChild(root_0, argList347.getTree());

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(74);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(434, 5);

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
   // CSparql.g:436:1: rdfLiteral : string ( LANGTAG | ( REFERENCE iriRef ) )? ;
   public final CSparqlParser.rdfLiteral_return rdfLiteral() throws RecognitionException {
      final CSparqlParser.rdfLiteral_return retval = new CSparqlParser.rdfLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token LANGTAG349 = null;
      Token REFERENCE350 = null;
      CSparqlParser.string_return string348 = null;

      CSparqlParser.iriRef_return iriRef351 = null;

      Object LANGTAG349_tree = null;
      Object REFERENCE350_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "rdfLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(436, 1);

         try {
            // CSparql.g:437:5: ( string ( LANGTAG | ( REFERENCE iriRef ) )? )
            this.dbg.enterAlt(1);

            // CSparql.g:437:7: string ( LANGTAG | ( REFERENCE iriRef ) )?
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(437, 7);
               this.pushFollow(CSparqlParser.FOLLOW_string_in_rdfLiteral2491);
               string348 = this.string();

               this.state._fsp--;

               this.adaptor.addChild(root_0, string348.getTree());
               this.dbg.location(437, 14);
               // CSparql.g:437:14: ( LANGTAG | ( REFERENCE iriRef ) )?
               int alt75 = 3;
               try {
                  this.dbg.enterSubRule(75);
                  try {
                     this.dbg.enterDecision(75);

                     final int LA75_0 = this.input.LA(1);

                     if (LA75_0 == CSparqlParser.LANGTAG) {
                        alt75 = 1;
                     } else if (LA75_0 == CSparqlParser.REFERENCE) {
                        alt75 = 2;
                     }
                  } finally {
                     this.dbg.exitDecision(75);
                  }

                  switch (alt75) {
                     case 1:
                        this.dbg.enterAlt(1);

                        // CSparql.g:437:16: LANGTAG
                        {
                           this.dbg.location(437, 16);
                           LANGTAG349 = (Token) this.match(this.input,
                                 CSparqlParser.LANGTAG,
                                 CSparqlParser.FOLLOW_LANGTAG_in_rdfLiteral2495);
                           LANGTAG349_tree = (Object) this.adaptor.create(LANGTAG349);
                           this.adaptor.addChild(root_0, LANGTAG349_tree);

                        }
                        break;
                     case 2:
                        this.dbg.enterAlt(2);

                        // CSparql.g:437:26: ( REFERENCE iriRef )
                        {
                           this.dbg.location(437, 26);
                           // CSparql.g:437:26: ( REFERENCE iriRef )
                           this.dbg.enterAlt(1);

                           // CSparql.g:437:28: REFERENCE iriRef
                           {
                              this.dbg.location(437, 28);
                              REFERENCE350 = (Token) this.match(this.input,
                                    CSparqlParser.REFERENCE,
                                    CSparqlParser.FOLLOW_REFERENCE_in_rdfLiteral2501);
                              REFERENCE350_tree = (Object) this.adaptor.create(REFERENCE350);
                              this.adaptor.addChild(root_0, REFERENCE350_tree);

                              this.dbg.location(437, 38);
                              this.pushFollow(CSparqlParser.FOLLOW_iriRef_in_rdfLiteral2503);
                              iriRef351 = this.iriRef();

                              this.state._fsp--;

                              this.adaptor.addChild(root_0, iriRef351.getTree());

                           }

                        }
                        break;

                  }
               } finally {
                  this.dbg.exitSubRule(75);
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(438, 5);

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
   // CSparql.g:440:1: numericLiteral : ( numericLiteralUnsigned | numericLiteralPositive |
   // numericLiteralNegative );
   public final CSparqlParser.numericLiteral_return numericLiteral()
         throws RecognitionException {
      final CSparqlParser.numericLiteral_return retval = new CSparqlParser.numericLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      CSparqlParser.numericLiteralUnsigned_return numericLiteralUnsigned352 = null;

      CSparqlParser.numericLiteralPositive_return numericLiteralPositive353 = null;

      CSparqlParser.numericLiteralNegative_return numericLiteralNegative354 = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(440, 1);

         try {
            // CSparql.g:441:5: ( numericLiteralUnsigned | numericLiteralPositive |
            // numericLiteralNegative )
            int alt76 = 3;
            try {
               this.dbg.enterDecision(76);

               switch (this.input.LA(1)) {
                  case INTEGER:
                  case DECIMAL:
                  case DOUBLE: {
                     alt76 = 1;
                  }
                     break;
                  case INTEGER_POSITIVE:
                  case DECIMAL_POSITIVE:
                  case DOUBLE_POSITIVE: {
                     alt76 = 2;
                  }
                     break;
                  case INTEGER_NEGATIVE:
                  case DECIMAL_NEGATIVE:
                  case DOUBLE_NEGATIVE: {
                     alt76 = 3;
                  }
                     break;
                  default:
                     final NoViableAltException nvae = new NoViableAltException("", 76, 0,
                           this.input);

                     this.dbg.recognitionException(nvae);
                     throw nvae;
               }

            } finally {
               this.dbg.exitDecision(76);
            }

            switch (alt76) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:441:7: numericLiteralUnsigned
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(441, 7);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_numericLiteralUnsigned_in_numericLiteral2525);
                     numericLiteralUnsigned352 = this.numericLiteralUnsigned();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, numericLiteralUnsigned352.getTree());

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:441:32: numericLiteralPositive
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(441, 32);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_numericLiteralPositive_in_numericLiteral2529);
                     numericLiteralPositive353 = this.numericLiteralPositive();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, numericLiteralPositive353.getTree());

                  }
                  break;
               case 3:
                  this.dbg.enterAlt(3);

                  // CSparql.g:441:57: numericLiteralNegative
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(441, 57);
                     this
                           .pushFollow(CSparqlParser.FOLLOW_numericLiteralNegative_in_numericLiteral2533);
                     numericLiteralNegative354 = this.numericLiteralNegative();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, numericLiteralNegative354.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(442, 5);

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
   // CSparql.g:444:1: numericLiteralUnsigned : ( INTEGER | DECIMAL | DOUBLE );
   public final CSparqlParser.numericLiteralUnsigned_return numericLiteralUnsigned()
         throws RecognitionException {
      final CSparqlParser.numericLiteralUnsigned_return retval = new CSparqlParser.numericLiteralUnsigned_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set355 = null;

      final Object set355_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralUnsigned");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(444, 1);

         try {
            // CSparql.g:445:5: ( INTEGER | DECIMAL | DOUBLE )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(445, 5);
               set355 = (Token) this.input.LT(1);
               if (this.input.LA(1) == CSparqlParser.INTEGER
                     || this.input.LA(1) >= CSparqlParser.DECIMAL
                     && this.input.LA(1) <= CSparqlParser.DOUBLE) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set355));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(448, 5);

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
   // CSparql.g:450:1: numericLiteralPositive : ( INTEGER_POSITIVE | DECIMAL_POSITIVE |
   // DOUBLE_POSITIVE );
   public final CSparqlParser.numericLiteralPositive_return numericLiteralPositive()
         throws RecognitionException {
      final CSparqlParser.numericLiteralPositive_return retval = new CSparqlParser.numericLiteralPositive_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set356 = null;

      final Object set356_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralPositive");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(450, 1);

         try {
            // CSparql.g:451:5: ( INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(451, 5);
               set356 = (Token) this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.INTEGER_POSITIVE
                     && this.input.LA(1) <= CSparqlParser.DOUBLE_POSITIVE) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set356));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(454, 5);

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
   // CSparql.g:456:1: numericLiteralNegative : ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE |
   // DOUBLE_NEGATIVE );
   public final CSparqlParser.numericLiteralNegative_return numericLiteralNegative()
         throws RecognitionException {
      final CSparqlParser.numericLiteralNegative_return retval = new CSparqlParser.numericLiteralNegative_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set357 = null;

      final Object set357_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "numericLiteralNegative");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(456, 1);

         try {
            // CSparql.g:457:5: ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(457, 5);
               set357 = (Token) this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.INTEGER_NEGATIVE
                     && this.input.LA(1) <= CSparqlParser.DOUBLE_NEGATIVE) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set357));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(460, 5);

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
   // CSparql.g:462:1: booleanLiteral : ( TRUE | FALSE );
   public final CSparqlParser.booleanLiteral_return booleanLiteral()
         throws RecognitionException {
      final CSparqlParser.booleanLiteral_return retval = new CSparqlParser.booleanLiteral_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set358 = null;

      final Object set358_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "booleanLiteral");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(462, 1);

         try {
            // CSparql.g:463:5: ( TRUE | FALSE )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(463, 5);
               set358 = (Token) this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.TRUE
                     && this.input.LA(1) <= CSparqlParser.FALSE) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set358));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(465, 5);

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
   // CSparql.g:467:1: string : ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 |
   // STRING_LITERAL_LONG2 );
   public final CSparqlParser.string_return string() throws RecognitionException {
      final CSparqlParser.string_return retval = new CSparqlParser.string_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set359 = null;

      final Object set359_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "string");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(467, 1);

         try {
            // CSparql.g:468:5: ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 |
            // STRING_LITERAL_LONG2 )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(468, 5);
               set359 = (Token) this.input.LT(1);
               if (this.input.LA(1) >= CSparqlParser.STRING_LITERAL1
                     && this.input.LA(1) <= CSparqlParser.STRING_LITERAL_LONG2) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set359));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(472, 5);

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
   // CSparql.g:474:1: iriRef : ( IRI_REF | prefixedName );
   public final CSparqlParser.iriRef_return iriRef() throws RecognitionException {
      final CSparqlParser.iriRef_return retval = new CSparqlParser.iriRef_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token IRI_REF360 = null;
      CSparqlParser.prefixedName_return prefixedName361 = null;

      Object IRI_REF360_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "iriRef");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(474, 1);

         try {
            // CSparql.g:475:5: ( IRI_REF | prefixedName )
            int alt77 = 2;
            try {
               this.dbg.enterDecision(77);

               final int LA77_0 = this.input.LA(1);

               if (LA77_0 == CSparqlParser.IRI_REF) {
                  alt77 = 1;
               } else if (LA77_0 == CSparqlParser.PNAME_NS
                     || LA77_0 == CSparqlParser.PNAME_LN) {
                  alt77 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 77, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(77);
            }

            switch (alt77) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:475:7: IRI_REF
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(475, 7);
                     IRI_REF360 = (Token) this.match(this.input, CSparqlParser.IRI_REF,
                           CSparqlParser.FOLLOW_IRI_REF_in_iriRef2715);
                     IRI_REF360_tree = (Object) this.adaptor.create(IRI_REF360);
                     this.adaptor.addChild(root_0, IRI_REF360_tree);

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:476:7: prefixedName
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(476, 7);
                     this.pushFollow(CSparqlParser.FOLLOW_prefixedName_in_iriRef2723);
                     prefixedName361 = this.prefixedName();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, prefixedName361.getTree());

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(477, 5);

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
   // CSparql.g:479:1: prefixedName : ( PNAME_LN | PNAME_NS );
   public final CSparqlParser.prefixedName_return prefixedName() throws RecognitionException {
      final CSparqlParser.prefixedName_return retval = new CSparqlParser.prefixedName_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token set362 = null;

      final Object set362_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "prefixedName");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(479, 1);

         try {
            // CSparql.g:480:5: ( PNAME_LN | PNAME_NS )
            this.dbg.enterAlt(1);

            // CSparql.g:
            {
               root_0 = (Object) this.adaptor.nil();

               this.dbg.location(480, 5);
               set362 = (Token) this.input.LT(1);
               if (this.input.LA(1) == CSparqlParser.PNAME_NS
                     || this.input.LA(1) == CSparqlParser.PNAME_LN) {
                  this.input.consume();
                  this.adaptor.addChild(root_0, (Object) this.adaptor.create(set362));
                  this.state.errorRecovery = false;
               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.dbg.recognitionException(mse);
                  throw mse;
               }

            }

            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(482, 5);

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
   // CSparql.g:484:1: blankNode : ( BLANK_NODE_LABEL | OPEN_SQUARE_BRACE CLOSE_SQUARE_BRACE
   // );
   public final CSparqlParser.blankNode_return blankNode() throws RecognitionException {
      final CSparqlParser.blankNode_return retval = new CSparqlParser.blankNode_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token BLANK_NODE_LABEL363 = null;
      Token OPEN_SQUARE_BRACE364 = null;
      Token CLOSE_SQUARE_BRACE365 = null;

      Object BLANK_NODE_LABEL363_tree = null;
      Object OPEN_SQUARE_BRACE364_tree = null;
      Object CLOSE_SQUARE_BRACE365_tree = null;

      try {
         this.dbg.enterRule(this.getGrammarFileName(), "blankNode");
         if (this.getRuleLevel() == 0) {
            this.dbg.commence();
         }
         this.incRuleLevel();
         this.dbg.location(484, 1);

         try {
            // CSparql.g:485:5: ( BLANK_NODE_LABEL | OPEN_SQUARE_BRACE CLOSE_SQUARE_BRACE )
            int alt78 = 2;
            try {
               this.dbg.enterDecision(78);

               final int LA78_0 = this.input.LA(1);

               if (LA78_0 == CSparqlParser.BLANK_NODE_LABEL) {
                  alt78 = 1;
               } else if (LA78_0 == CSparqlParser.OPEN_SQUARE_BRACE) {
                  alt78 = 2;
               } else {
                  final NoViableAltException nvae = new NoViableAltException("", 78, 0,
                        this.input);

                  this.dbg.recognitionException(nvae);
                  throw nvae;
               }
            } finally {
               this.dbg.exitDecision(78);
            }

            switch (alt78) {
               case 1:
                  this.dbg.enterAlt(1);

                  // CSparql.g:485:7: BLANK_NODE_LABEL
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(485, 7);
                     BLANK_NODE_LABEL363 = (Token) this.match(this.input,
                           CSparqlParser.BLANK_NODE_LABEL,
                           CSparqlParser.FOLLOW_BLANK_NODE_LABEL_in_blankNode2765);
                     BLANK_NODE_LABEL363_tree = (Object) this.adaptor
                           .create(BLANK_NODE_LABEL363);
                     this.adaptor.addChild(root_0, BLANK_NODE_LABEL363_tree);

                  }
                  break;
               case 2:
                  this.dbg.enterAlt(2);

                  // CSparql.g:486:7: OPEN_SQUARE_BRACE CLOSE_SQUARE_BRACE
                  {
                     root_0 = (Object) this.adaptor.nil();

                     this.dbg.location(486, 7);
                     OPEN_SQUARE_BRACE364 = (Token) this.match(this.input,
                           CSparqlParser.OPEN_SQUARE_BRACE,
                           CSparqlParser.FOLLOW_OPEN_SQUARE_BRACE_in_blankNode2773);
                     OPEN_SQUARE_BRACE364_tree = (Object) this.adaptor
                           .create(OPEN_SQUARE_BRACE364);
                     this.adaptor.addChild(root_0, OPEN_SQUARE_BRACE364_tree);

                     this.dbg.location(486, 25);
                     CLOSE_SQUARE_BRACE365 = (Token) this.match(this.input,
                           CSparqlParser.CLOSE_SQUARE_BRACE,
                           CSparqlParser.FOLLOW_CLOSE_SQUARE_BRACE_in_blankNode2775);
                     CLOSE_SQUARE_BRACE365_tree = (Object) this.adaptor
                           .create(CLOSE_SQUARE_BRACE365);
                     this.adaptor.addChild(root_0, CLOSE_SQUARE_BRACE365_tree);

                  }
                  break;

            }
            retval.stop = this.input.LT(-1);

            retval.tree = (Object) this.adaptor.rulePostProcessing(root_0);
            this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

         }

         catch (final RecognitionException e) {
            throw e;
         } finally {
         }
         this.dbg.location(487, 5);

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

   public static final BitSet FOLLOW_registration_in_queryWithReg65 = new BitSet(
         new long[] { 0x0000000000705400L });
   public static final BitSet FOLLOW_query_in_queryWithReg68 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_EOF_in_queryWithReg70 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_REGISTER_in_registration82 = new BitSet(
         new long[] { 0x0000000000000060L });
   public static final BitSet FOLLOW_set_in_registration84 = new BitSet(
         new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_queryName_in_registration90 = new BitSet(
         new long[] { 0x0000000000000280L });
   public static final BitSet FOLLOW_queryRange_in_registration92 = new BitSet(
         new long[] { 0x0000000000000080L });
   public static final BitSet FOLLOW_AS_in_registration95 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_prologue_in_query108 = new BitSet(
         new long[] { 0x0000000000705400L });
   public static final BitSet FOLLOW_selectQuery_in_query112 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_constructQuery_in_query116 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_describeQuery_in_query120 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_askQuery_in_query124 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_QUERY_NAME_in_queryName139 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_COMPUTED_EVERY_in_queryRange149 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_timeRange_in_queryRange151 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_baseDecl_in_prologue170 = new BitSet(
         new long[] { 0x0000000000001002L });
   public static final BitSet FOLLOW_prefixDecl_in_prologue173 = new BitSet(
         new long[] { 0x0000000000001002L });
   public static final BitSet FOLLOW_BASE_in_baseDecl191 = new BitSet(
         new long[] { 0x0000000000000800L });
   public static final BitSet FOLLOW_IRI_REF_in_baseDecl193 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_PREFIX_in_prefixDecl210 = new BitSet(
         new long[] { 0x0000000000002000L });
   public static final BitSet FOLLOW_PNAME_NS_in_prefixDecl212 = new BitSet(
         new long[] { 0x0000000000000800L });
   public static final BitSet FOLLOW_IRI_REF_in_prefixDecl214 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_SELECT_in_selectQuery232 = new BitSet(
         new long[] { 0x00C0000000078000L });
   public static final BitSet FOLLOW_set_in_selectQuery234 = new BitSet(
         new long[] { 0x00C0000000060000L });
   public static final BitSet FOLLOW_var_in_selectQuery248 = new BitSet(
         new long[] { 0x00C0040200840000L });
   public static final BitSet FOLLOW_renamedVar_in_selectQuery252 = new BitSet(
         new long[] { 0x00C0040200840000L });
   public static final BitSet FOLLOW_newVarFromExpression_in_selectQuery256 = new BitSet(
         new long[] { 0x00C0040200840000L });
   public static final BitSet FOLLOW_ASTERISK_in_selectQuery263 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_datasetClause_in_selectQuery267 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_whereClause_in_selectQuery270 = new BitSet(
         new long[] { 0x0000032400000000L });
   public static final BitSet FOLLOW_solutionModifier_in_selectQuery272 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_renamedVar287 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_renamedVar289 = new BitSet(
         new long[] { 0x0000000000000080L });
   public static final BitSet FOLLOW_AS_in_renamedVar291 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_renamedVar293 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_renamedVar295 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_newVarFromExpression307 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_newVarFromExpression309 = new BitSet(
         new long[] { 0x0000000000000080L });
   public static final BitSet FOLLOW_AS_in_newVarFromExpression311 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_newVarFromExpression313 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_newVarFromExpression315 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_CONSTRUCT_in_constructQuery330 = new BitSet(
         new long[] { 0x0000040000000000L });
   public static final BitSet FOLLOW_constructTemplate_in_constructQuery332 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_datasetClause_in_constructQuery334 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_whereClause_in_constructQuery337 = new BitSet(
         new long[] { 0x0000032400000000L });
   public static final BitSet FOLLOW_solutionModifier_in_constructQuery339 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_DESCRIBE_in_describeQuery356 = new BitSet(new long[] {
         0x00C0000000022800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_varOrIRIref_in_describeQuery360 = new BitSet(
         new long[] { 0x00C0072600802800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_ASTERISK_in_describeQuery365 = new BitSet(
         new long[] { 0x0000072600800000L });
   public static final BitSet FOLLOW_datasetClause_in_describeQuery369 = new BitSet(
         new long[] { 0x0000072600800000L });
   public static final BitSet FOLLOW_whereClause_in_describeQuery372 = new BitSet(
         new long[] { 0x0000032400000000L });
   public static final BitSet FOLLOW_solutionModifier_in_describeQuery375 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ASK_in_askQuery392 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_datasetClause_in_askQuery394 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_whereClause_in_askQuery397 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_datasetClauseStd_in_datasetClause415 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_datasetClauseStream_in_datasetClause423 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_FROM_in_datasetClauseStd437 = new BitSet(new long[] {
         0x0000000001002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_defaultGraphClause_in_datasetClauseStd441 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_namedGraphClause_in_datasetClauseStd445 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_FROM_in_datasetClauseStream459 = new BitSet(
         new long[] { 0x0000000001000040L });
   public static final BitSet FOLLOW_NAMED_in_datasetClauseStream461 = new BitSet(
         new long[] { 0x0000000000000040L });
   public static final BitSet FOLLOW_STREAM_in_datasetClauseStream464 = new BitSet(
         new long[] { 0x0000000001002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_defaultGraphClause_in_datasetClauseStream468 = new BitSet(
         new long[] { 0x0000000002000000L });
   public static final BitSet FOLLOW_namedGraphClause_in_datasetClauseStream472 = new BitSet(
         new long[] { 0x0000000002000000L });
   public static final BitSet FOLLOW_range_in_datasetClauseStream476 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_SQUARE_BRACE_in_range488 = new BitSet(
         new long[] { 0x0000000004000000L });
   public static final BitSet FOLLOW_RANGE_in_range490 = new BitSet(
         new long[] { 0x0000000110000000L });
   public static final BitSet FOLLOW_window_in_range492 = new BitSet(
         new long[] { 0x0000000008000000L });
   public static final BitSet FOLLOW_CLOSE_SQUARE_BRACE_in_range494 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_physicalWindow_in_window506 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_logicalWindow_in_window511 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_timeRange_in_logicalWindow523 = new BitSet(
         new long[] { 0x00000000C0000000L });
   public static final BitSet FOLLOW_windowOverlap_in_logicalWindow525 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_TRIPLES_in_physicalWindow536 = new BitSet(
         new long[] { 0x0000000020000000L });
   public static final BitSet FOLLOW_INTEGER_in_physicalWindow538 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_STEP_in_windowOverlap549 = new BitSet(
         new long[] { 0x0000000100000000L });
   public static final BitSet FOLLOW_timeRange_in_windowOverlap551 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_TUMBLING_in_windowOverlap556 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_TIME_RANGE_in_timeRange566 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_sourceSelector_in_defaultGraphClause583 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NAMED_in_namedGraphClause600 = new BitSet(new long[] {
         0x0000000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_sourceSelector_in_namedGraphClause602 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_sourceSelector619 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_WHERE_in_whereClause636 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_whereClause639 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_groupBy_in_solutionModifier657 = new BitSet(
         new long[] { 0x0000032000000002L });
   public static final BitSet FOLLOW_orderClause_in_solutionModifier660 = new BitSet(
         new long[] { 0x0000030000000002L });
   public static final BitSet FOLLOW_limitOffsetClauses_in_solutionModifier663 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GROUP_in_groupBy684 = new BitSet(
         new long[] { 0x0000000800000000L });
   public static final BitSet FOLLOW_BY_in_groupBy686 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_groupBy688 = new BitSet(
         new long[] { 0x00C0001000000002L });
   public static final BitSet FOLLOW_having_in_groupBy691 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_HAVING_in_having704 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_brackettedExpression_in_having706 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_limitClause_in_limitOffsetClauses723 = new BitSet(
         new long[] { 0x0000030000000002L });
   public static final BitSet FOLLOW_offsetClause_in_limitOffsetClauses725 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_offsetClause_in_limitOffsetClauses730 = new BitSet(
         new long[] { 0x0000010000000002L });
   public static final BitSet FOLLOW_limitClause_in_limitOffsetClauses732 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ORDER_in_orderClause752 = new BitSet(
         new long[] { 0x0000000800000000L });
   public static final BitSet FOLLOW_BY_in_orderClause754 = new BitSet(new long[] {
         0x00C060C000042800L, 0x0000002000107FE0L });
   public static final BitSet FOLLOW_orderCondition_in_orderClause756 = new BitSet(
         new long[] { 0x00C060C000042802L, 0x0000002000107FE0L });
   public static final BitSet FOLLOW_set_in_orderCondition776 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_brackettedExpression_in_orderCondition786 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_constraint_in_orderCondition798 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_orderCondition802 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LIMIT_in_limitClause821 = new BitSet(
         new long[] { 0x0000000020000000L });
   public static final BitSet FOLLOW_INTEGER_in_limitClause823 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OFFSET_in_offsetClause840 = new BitSet(
         new long[] { 0x0000000020000000L });
   public static final BitSet FOLLOW_INTEGER_in_offsetClause842 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_CURLY_BRACE_in_groupGraphPattern860 = new BitSet(
         new long[] { 0x00C5F40222842800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_triplesBlock_in_groupGraphPattern862 = new BitSet(
         new long[] { 0x0005F40200800000L });
   public static final BitSet FOLLOW_graphPatternNotTriples_in_groupGraphPattern869 = new BitSet(
         new long[] { 0x00C5FC0222842800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_filter_in_groupGraphPattern873 = new BitSet(new long[] {
         0x00C5FC0222842800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_subquery_in_groupGraphPattern877 = new BitSet(
         new long[] { 0x00C5FC0222842800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_DOT_in_groupGraphPattern881 = new BitSet(new long[] {
         0x00C5F40222842800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_triplesBlock_in_groupGraphPattern884 = new BitSet(
         new long[] { 0x0005F40200800000L });
   public static final BitSet FOLLOW_CLOSE_CURLY_BRACE_in_groupGraphPattern890 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_CURLY_BRACE_in_subquery905 = new BitSet(
         new long[] { 0x0000000000004000L });
   public static final BitSet FOLLOW_selectQuery_in_subquery907 = new BitSet(
         new long[] { 0x0000100000000000L });
   public static final BitSet FOLLOW_CLOSE_CURLY_BRACE_in_subquery909 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesSameSubject_in_triplesBlock924 = new BitSet(
         new long[] { 0x0000080000000002L });
   public static final BitSet FOLLOW_DOT_in_triplesBlock928 = new BitSet(new long[] {
         0x00C0000022042802L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_triplesBlock_in_triplesBlock930 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_optionalGraphPattern_in_graphPatternNotTriples952 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_groupOrUnionGraphPattern_in_graphPatternNotTriples956 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_graphGraphPattern_in_graphPatternNotTriples960 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_existElt_in_graphPatternNotTriples964 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_nonExistElt_in_graphPatternNotTriples968 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_EXISTS_in_existElt983 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_existElt985 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NOT_BY_WORDS_in_nonExistElt997 = new BitSet(
         new long[] { 0x0000200000000000L });
   public static final BitSet FOLLOW_EXISTS_in_nonExistElt999 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_nonExistElt1001 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPTIONAL_in_optionalGraphPattern1016 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_optionalGraphPattern1018 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GRAPH_in_graphGraphPattern1035 = new BitSet(new long[] {
         0x00C0000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_varOrIRIref_in_graphGraphPattern1037 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_graphGraphPattern1039 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern1060 = new BitSet(
         new long[] { 0x0002000000000002L });
   public static final BitSet FOLLOW_UNION_in_groupOrUnionGraphPattern1064 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_groupOrUnionGraphPattern1066 = new BitSet(
         new long[] { 0x0002000000000002L });
   public static final BitSet FOLLOW_FILTER_in_filter1086 = new BitSet(new long[] {
         0x0000600000042800L, 0x0000002000107FE0L });
   public static final BitSet FOLLOW_constraint_in_filter1088 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_brackettedExpression_in_constraint1105 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_builtInCall_in_constraint1109 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_functionCall_in_constraint1113 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_functionCall1131 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_argList_in_functionCall1133 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_argList1152 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_argList1154 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_argList1158 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_argList1160 = new BitSet(
         new long[] { 0x0008000000080000L });
   public static final BitSet FOLLOW_COMMA_in_argList1164 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_argList1166 = new BitSet(
         new long[] { 0x0008000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_argList1171 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_CURLY_BRACE_in_constructTemplate1190 = new BitSet(
         new long[] { 0x00C0100022042800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_constructTriples_in_constructTemplate1192 = new BitSet(
         new long[] { 0x0000100000000000L });
   public static final BitSet FOLLOW_CLOSE_CURLY_BRACE_in_constructTemplate1195 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesSameSubject_in_constructTriples1212 = new BitSet(
         new long[] { 0x0000080000000002L });
   public static final BitSet FOLLOW_DOT_in_constructTriples1216 = new BitSet(new long[] {
         0x00C0000022042802L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_constructTriples_in_constructTriples1218 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrTerm_in_triplesSameSubject1239 = new BitSet(
         new long[] { 0x00E0000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_triplesSameSubject1241 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesNode_in_triplesSameSubject1245 = new BitSet(
         new long[] { 0x00E0000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_propertyList_in_triplesSameSubject1247 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_verb_in_propertyListNotEmpty1264 = new BitSet(
         new long[] { 0x00C0000022042800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_objectList_in_propertyListNotEmpty1266 = new BitSet(
         new long[] { 0x0010000000000002L });
   public static final BitSet FOLLOW_SEMICOLON_in_propertyListNotEmpty1270 = new BitSet(
         new long[] { 0x00F0000000002802L, 0x0000002000000000L });
   public static final BitSet FOLLOW_verb_in_propertyListNotEmpty1274 = new BitSet(
         new long[] { 0x00C0000022042800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_objectList_in_propertyListNotEmpty1276 = new BitSet(
         new long[] { 0x0010000000000002L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_propertyList1299 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_object_in_objectList1317 = new BitSet(
         new long[] { 0x0008000000000002L });
   public static final BitSet FOLLOW_COMMA_in_objectList1321 = new BitSet(new long[] {
         0x00C0000022042800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_object_in_objectList1323 = new BitSet(
         new long[] { 0x0008000000000002L });
   public static final BitSet FOLLOW_graphNode_in_object1343 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrIRIref_in_verb1360 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_A_in_verb1368 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_collection_in_triplesNode1385 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_blankNodePropertyList_in_triplesNode1393 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_SQUARE_BRACE_in_blankNodePropertyList1410 = new BitSet(
         new long[] { 0x00E0000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_propertyListNotEmpty_in_blankNodePropertyList1412 = new BitSet(
         new long[] { 0x0000000008000000L });
   public static final BitSet FOLLOW_CLOSE_SQUARE_BRACE_in_blankNodePropertyList1414 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_collection1431 = new BitSet(new long[] {
         0x00C0000022042800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_graphNode_in_collection1433 = new BitSet(new long[] {
         0x00C00000220C2800L, 0x0000007FFF800000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_collection1436 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_varOrTerm_in_graphNode1453 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_triplesNode_in_graphNode1457 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_varOrTerm1474 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_graphTerm_in_varOrTerm1482 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_varOrIRIref1499 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_varOrIRIref1503 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_var0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_graphTerm1545 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_rdfLiteral_in_graphTerm1553 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteral_in_graphTerm1561 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_booleanLiteral_in_graphTerm1569 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_blankNode_in_graphTerm1577 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_graphTerm1585 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_graphTerm1587 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_conditionalOrExpression_in_expression1604 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression1621 = new BitSet(
         new long[] { 0x0100000000000002L });
   public static final BitSet FOLLOW_OR_in_conditionalOrExpression1625 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression1627 = new BitSet(
         new long[] { 0x0100000000000002L });
   public static final BitSet FOLLOW_valueLogical_in_conditionalAndExpression1647 = new BitSet(
         new long[] { 0x0200000000000002L });
   public static final BitSet FOLLOW_AND_in_conditionalAndExpression1651 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_valueLogical_in_conditionalAndExpression1653 = new BitSet(
         new long[] { 0x0200000000000002L });
   public static final BitSet FOLLOW_relationalExpression_in_valueLogical1673 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1690 = new BitSet(
         new long[] { 0xFC00000000000002L });
   public static final BitSet FOLLOW_EQUAL_in_relationalExpression1694 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1696 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NOT_EQUAL_in_relationalExpression1700 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1702 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LESS_in_relationalExpression1706 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1708 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GREATER_in_relationalExpression1712 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1714 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LESS_EQUAL_in_relationalExpression1718 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1720 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_GREATER_EQUAL_in_relationalExpression1724 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_numericExpression_in_relationalExpression1726 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_additiveExpression_in_numericExpression1746 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1764 = new BitSet(
         new long[] { 0x0000000020000002L, 0x000000007F800003L });
   public static final BitSet FOLLOW_set_in_additiveExpression1769 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression1778 = new BitSet(
         new long[] { 0x0000000020000002L, 0x000000007F800003L });
   public static final BitSet FOLLOW_numericLiteralPositive_in_additiveExpression1795 = new BitSet(
         new long[] { 0x0000000020000002L, 0x000000007F800003L });
   public static final BitSet FOLLOW_numericLiteralNegative_in_additiveExpression1811 = new BitSet(
         new long[] { 0x0000000020000002L, 0x000000007F800003L });
   public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1844 = new BitSet(
         new long[] { 0x0000000000020002L, 0x0000000000000004L });
   public static final BitSet FOLLOW_set_in_multiplicativeExpression1848 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression1856 = new BitSet(
         new long[] { 0x0000000000020002L, 0x0000000000000004L });
   public static final BitSet FOLLOW_NOT_in_unaryExpression1876 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1878 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_PLUS_in_unaryExpression1886 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1888 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_MINUS_in_unaryExpression1896 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1898 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_primaryExpression_in_unaryExpression1906 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_brackettedExpression_in_primaryExpression1923 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_builtInCall_in_primaryExpression1932 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRefOrFunction_in_primaryExpression1941 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_rdfLiteral_in_primaryExpression1950 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteral_in_primaryExpression1959 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_booleanLiteral_in_primaryExpression1968 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_primaryExpression1977 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_timestampCall_in_primaryExpression1986 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_aggregate_in_primaryExpression1994 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_TIMESTAMP_in_timestampCall2013 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_timestampCall2015 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_timestampCall2017 = new BitSet(new long[] {
         0x0001E40200882800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_iriRef_in_timestampCall2020 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_graphPatternNotTriples_in_timestampCall2024 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_timestampCall2028 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_brackettedExpression2043 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_brackettedExpression2045 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_brackettedExpression2047 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_STR_in_builtInCall2065 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2067 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2069 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2071 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LANG_in_builtInCall2079 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2081 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2083 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2085 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_LANGMATCHES_in_builtInCall2093 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2095 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2097 = new BitSet(
         new long[] { 0x0008000000000000L });
   public static final BitSet FOLLOW_COMMA_in_builtInCall2099 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2101 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2103 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_DATATYPE_in_builtInCall2111 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2113 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2115 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2117 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_BOUND_in_builtInCall2125 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2127 = new BitSet(
         new long[] { 0x00C0000000000000L });
   public static final BitSet FOLLOW_var_in_builtInCall2129 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2131 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_SAMETERM_in_builtInCall2139 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2141 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2143 = new BitSet(
         new long[] { 0x0008000000000000L });
   public static final BitSet FOLLOW_COMMA_in_builtInCall2145 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2147 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2149 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISIRI_in_builtInCall2157 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2159 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2161 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2163 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISURI_in_builtInCall2171 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2173 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2175 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2177 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISBLANK_in_builtInCall2185 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2187 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2189 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2191 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ISLITERAL_in_builtInCall2199 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_builtInCall2201 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_builtInCall2203 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_builtInCall2205 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_regexExpression_in_builtInCall2213 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_existsFunc_in_builtInCall2221 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_notExistsFunc_in_builtInCall2230 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_EXISTS_in_existsFunc2245 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_existsFunc2247 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_NOT_BY_WORDS_in_notExistsFunc2259 = new BitSet(
         new long[] { 0x0000200000000000L });
   public static final BitSet FOLLOW_EXISTS_in_notExistsFunc2261 = new BitSet(
         new long[] { 0x0000040200800000L });
   public static final BitSet FOLLOW_groupGraphPattern_in_notExistsFunc2263 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_countAggregateExpression_in_aggregate2279 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_sumAggregateExpression_in_aggregate2285 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_minAggregateExpression_in_aggregate2291 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_maxAggregateExpression_in_aggregate2297 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_avgAggregateExpression_in_aggregate2302 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_COUNT_in_countAggregateExpression2318 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_countAggregateExpression2320 = new BitSet(
         new long[] { 0x00C0000000028000L });
   public static final BitSet FOLLOW_ASTERISK_in_countAggregateExpression2324 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_var_in_countAggregateExpression2328 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_DISTINCT_in_countAggregateExpression2332 = new BitSet(
         new long[] { 0x00C0000000020000L });
   public static final BitSet FOLLOW_ASTERISK_in_countAggregateExpression2336 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_var_in_countAggregateExpression2340 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_countAggregateExpression2346 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_SUM_in_sumAggregateExpression2359 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_sumAggregateExpression2361 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_sumAggregateExpression2363 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_sumAggregateExpression2365 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_MIN_in_minAggregateExpression2377 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_minAggregateExpression2379 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_minAggregateExpression2381 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_minAggregateExpression2383 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_MAX_in_maxAggregateExpression2396 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_maxAggregateExpression2398 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_maxAggregateExpression2400 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_maxAggregateExpression2402 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_AVG_in_avgAggregateExpression2414 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_avgAggregateExpression2416 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_avgAggregateExpression2418 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_avgAggregateExpression2420 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_REGEX_in_regexExpression2435 = new BitSet(
         new long[] { 0x0000000000040000L });
   public static final BitSet FOLLOW_OPEN_BRACE_in_regexExpression2437 = new BitSet(
         new long[] { 0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_regexExpression2439 = new BitSet(
         new long[] { 0x0008000000000000L });
   public static final BitSet FOLLOW_COMMA_in_regexExpression2441 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_regexExpression2443 = new BitSet(
         new long[] { 0x0008000000080000L });
   public static final BitSet FOLLOW_COMMA_in_regexExpression2447 = new BitSet(new long[] {
         0x00C0600020042800L, 0x0000003FFF9FFFFBL });
   public static final BitSet FOLLOW_expression_in_regexExpression2449 = new BitSet(
         new long[] { 0x0000000000080000L });
   public static final BitSet FOLLOW_CLOSE_BRACE_in_regexExpression2454 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_iriRef_in_iriRefOrFunction2471 = new BitSet(
         new long[] { 0x0000000000040002L });
   public static final BitSet FOLLOW_argList_in_iriRefOrFunction2473 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_string_in_rdfLiteral2491 = new BitSet(new long[] {
         0x0000000000000002L, 0x0000000000600000L });
   public static final BitSet FOLLOW_LANGTAG_in_rdfLiteral2495 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_REFERENCE_in_rdfLiteral2501 = new BitSet(new long[] {
         0x0000000000002800L, 0x0000002000000000L });
   public static final BitSet FOLLOW_iriRef_in_rdfLiteral2503 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralUnsigned_in_numericLiteral2525 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralPositive_in_numericLiteral2529 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_numericLiteralNegative_in_numericLiteral2533 = new BitSet(
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
   public static final BitSet FOLLOW_IRI_REF_in_iriRef2715 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_prefixedName_in_iriRef2723 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_set_in_prefixedName0 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_BLANK_NODE_LABEL_in_blankNode2765 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_OPEN_SQUARE_BRACE_in_blankNode2773 = new BitSet(
         new long[] { 0x0000000008000000L });
   public static final BitSet FOLLOW_CLOSE_SQUARE_BRACE_in_blankNode2775 = new BitSet(
         new long[] { 0x0000000000000002L });

}
