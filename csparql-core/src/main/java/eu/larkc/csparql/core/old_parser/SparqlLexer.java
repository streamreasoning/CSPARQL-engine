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
// $ANTLR 3.2 Sep 23, 2009 12:02:23 Sparql.g 2009-12-15 16:31:21

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

public class SparqlLexer extends Lexer {

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
   public static final int PN_CHARS_U = 91;
   public static final int BASE = 4;
   public static final int COMMENT = 93;
   public static final int SELECT = 8;
   public static final int OPEN_CURLY_BRACE = 25;
   public static final int CLOSE_CURLY_BRACE = 27;
   public static final int DOUBLE_POSITIVE = 70;
   public static final int BOUND = 57;
   public static final int DIVIDE = 51;
   public static final int ISIRI = 59;
   public static final int A = 36;
   public static final int ASC = 20;
   public static final int BLANK_NODE_LABEL = 81;
   public static final int ASK = 14;
   public static final int SEMICOLON = 35;
   public static final int ISBLANK = 61;
   public static final int WS = 83;
   public static final int INTEGER_POSITIVE = 68;
   public static final int NAMED = 16;
   public static final int STRING_LITERAL2 = 77;
   public static final int OR = 41;
   public static final int STRING_LITERAL1 = 76;
   public static final int DESCRIBE = 13;
   public static final int FILTER = 31;
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

   public SparqlLexer() {
      ;
   }

   public SparqlLexer(final CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public SparqlLexer(final CharStream input, final RecognizerSharedState state) {
      super(input, state);

   }

   @Override
   public String getGrammarFileName() {
      return "Sparql.g";
   }

   // $ANTLR start "WS"
   public final void mWS() throws RecognitionException {
      try {
         final int _type = SparqlLexer.WS;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // Sparql.g:397:7: ( ' ' | '\\t' | EOL )+
         int cnt1 = 0;
         loop1: do {
            int alt1 = 2;
            final int LA1_0 = this.input.LA(1);

            if (LA1_0 >= '\t' && LA1_0 <= '\n' || LA1_0 == '\r' || LA1_0 == ' ') {
               alt1 = 1;
            }

            switch (alt1) {
               case 1:
                  // Sparql.g:
               {
                  if (this.input.LA(1) >= '\t' && this.input.LA(1) <= '\n'
                        || this.input.LA(1) == '\r' || this.input.LA(1) == ' ') {
                     this.input.consume();

                  } else {
                     final MismatchedSetException mse = new MismatchedSetException(null,
                           this.input);
                     this.recover(mse);
                     throw mse;
                  }

               }
                  break;

               default:
                  if (cnt1 >= 1) {
                     break loop1;
                  }
                  final EarlyExitException eee = new EarlyExitException(1, this.input);
                  throw eee;
            }
            cnt1++;
         } while (true);
         _channel = BaseRecognizer.HIDDEN;
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "WS"

   // $ANTLR start "PNAME_NS"
   public final void mPNAME_NS() throws RecognitionException {
      try {
         final int _type = SparqlLexer.PNAME_NS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken p = null;

         // Sparql.g:401:8: (p= PN_PREFIX )?
         int alt2 = 2;
         final int LA2_0 = this.input.LA(1);
         if (LA2_0 >= 'A' && LA2_0 <= 'Z' || LA2_0 >= 'a' && LA2_0 <= 'z'
               || LA2_0 >= '\u00C0' && LA2_0 <= '\u00D6' || LA2_0 >= '\u00D8'
               && LA2_0 <= '\u00F6' || LA2_0 >= '\u00F8' && LA2_0 <= '\u02FF'
               || LA2_0 >= '\u0370' && LA2_0 <= '\u037D' || LA2_0 >= '\u037F'
               && LA2_0 <= '\u1FFF' || LA2_0 >= '\u200C' && LA2_0 <= '\u200D'
               || LA2_0 >= '\u2070' && LA2_0 <= '\u218F' || LA2_0 >= '\u2C00'
               && LA2_0 <= '\u2FEF' || LA2_0 >= '\u3001' && LA2_0 <= '\uD7FF'
               || LA2_0 >= '\uF900' && LA2_0 <= '\uFDCF' || LA2_0 >= '\uFDF0'
               && LA2_0 <= '\uFFFD') {
            alt2 = 1;
         }
         switch (alt2) {
            case 1:
               // Sparql.g:401:8: p= PN_PREFIX
            {
               final int pStart48 = this.getCharIndex();
               this.mPN_PREFIX();
               p = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE,
                     Token.DEFAULT_CHANNEL, pStart48, this.getCharIndex() - 1);

            }
               break;

         }
         this.match(':');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PNAME_NS"

   // $ANTLR start "PNAME_LN"
   public final void mPNAME_LN() throws RecognitionException {
      try {
         final int _type = SparqlLexer.PNAME_LN;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mPNAME_NS();
         this.mPN_LOCAL();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PNAME_LN"

   // $ANTLR start "BASE"
   public final void mBASE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.BASE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "BASE"

   // $ANTLR start "PREFIX"
   public final void mPREFIX() throws RecognitionException {
      try {
         final int _type = SparqlLexer.PREFIX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PREFIX"

   // $ANTLR start "SELECT"
   public final void mSELECT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.SELECT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SELECT"

   // $ANTLR start "DISTINCT"
   public final void mDISTINCT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DISTINCT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DISTINCT"

   // $ANTLR start "REDUCED"
   public final void mREDUCED() throws RecognitionException {
      try {
         final int _type = SparqlLexer.REDUCED;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "REDUCED"

   // $ANTLR start "CONSTRUCT"
   public final void mCONSTRUCT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.CONSTRUCT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CONSTRUCT"

   // $ANTLR start "DESCRIBE"
   public final void mDESCRIBE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DESCRIBE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DESCRIBE"

   // $ANTLR start "ASK"
   public final void mASK() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ASK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'K' || this.input.LA(1) == 'k') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ASK"

   // $ANTLR start "FROM"
   public final void mFROM() throws RecognitionException {
      try {
         final int _type = SparqlLexer.FROM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "FROM"

   // $ANTLR start "NAMED"
   public final void mNAMED() throws RecognitionException {
      try {
         final int _type = SparqlLexer.NAMED;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NAMED"

   // $ANTLR start "WHERE"
   public final void mWHERE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.WHERE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'W' || this.input.LA(1) == 'w') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "WHERE"

   // $ANTLR start "ORDER"
   public final void mORDER() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ORDER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ORDER"

   // $ANTLR start "BY"
   public final void mBY() throws RecognitionException {
      try {
         final int _type = SparqlLexer.BY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "BY"

   // $ANTLR start "ASC"
   public final void mASC() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ASC;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ASC"

   // $ANTLR start "DESC"
   public final void mDESC() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DESC;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DESC"

   // $ANTLR start "LIMIT"
   public final void mLIMIT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LIMIT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LIMIT"

   // $ANTLR start "OFFSET"
   public final void mOFFSET() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OFFSET;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OFFSET"

   // $ANTLR start "OPTIONAL"
   public final void mOPTIONAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OPTIONAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPTIONAL"

   // $ANTLR start "GRAPH"
   public final void mGRAPH() throws RecognitionException {
      try {
         final int _type = SparqlLexer.GRAPH;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GRAPH"

   // $ANTLR start "UNION"
   public final void mUNION() throws RecognitionException {
      try {
         final int _type = SparqlLexer.UNION;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "UNION"

   // $ANTLR start "FILTER"
   public final void mFILTER() throws RecognitionException {
      try {
         final int _type = SparqlLexer.FILTER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "FILTER"

   // $ANTLR start "A"
   public final void mA() throws RecognitionException {
      try {
         final int _type = SparqlLexer.A;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('a');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "A"

   // $ANTLR start "STR"
   public final void mSTR() throws RecognitionException {
      try {
         final int _type = SparqlLexer.STR;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STR"

   // $ANTLR start "LANG"
   public final void mLANG() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LANG;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LANG"

   // $ANTLR start "LANGMATCHES"
   public final void mLANGMATCHES() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LANGMATCHES;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LANGMATCHES"

   // $ANTLR start "DATATYPE"
   public final void mDATATYPE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DATATYPE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DATATYPE"

   // $ANTLR start "BOUND"
   public final void mBOUND() throws RecognitionException {
      try {
         final int _type = SparqlLexer.BOUND;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "BOUND"

   // $ANTLR start "SAMETERM"
   public final void mSAMETERM() throws RecognitionException {
      try {
         final int _type = SparqlLexer.SAMETERM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SAMETERM"

   // $ANTLR start "ISIRI"
   public final void mISIRI() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ISIRI;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ISIRI"

   // $ANTLR start "ISURI"
   public final void mISURI() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ISURI;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ISURI"

   // $ANTLR start "ISBLANK"
   public final void mISBLANK() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ISBLANK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'K' || this.input.LA(1) == 'k') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ISBLANK"

   // $ANTLR start "ISLITERAL"
   public final void mISLITERAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ISLITERAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ISLITERAL"

   // $ANTLR start "REGEX"
   public final void mREGEX() throws RecognitionException {
      try {
         final int _type = SparqlLexer.REGEX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "REGEX"

   // $ANTLR start "TRUE"
   public final void mTRUE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.TRUE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TRUE"

   // $ANTLR start "FALSE"
   public final void mFALSE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.FALSE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "FALSE"

   // $ANTLR start "IRI_REF"
   public final void mIRI_REF() throws RecognitionException {
      try {
         final int _type = SparqlLexer.IRI_REF;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mLESS();
         // Sparql.g:549:12: ( options {greedy=false; } : ~ ( LESS | GREATER | '\"' |
         // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000'
         // .. '\ ' ) ) )*
         loop3: do {
            int alt3 = 2;
            final int LA3_0 = this.input.LA(1);

            if (LA3_0 == '!' || LA3_0 >= '#' && LA3_0 <= ';' || LA3_0 == '=' || LA3_0 >= '?'
                  && LA3_0 <= '[' || LA3_0 == ']' || LA3_0 == '_' || LA3_0 >= 'a'
                  && LA3_0 <= 'z' || LA3_0 >= '~' && LA3_0 <= '\uFFFF') {
               alt3 = 1;
            } else if (LA3_0 == '>') {
               alt3 = 2;
            }

            switch (alt3) {
               case 1:
                  // Sparql.g:549:40: ~ ( LESS | GREATER | '\"' | OPEN_CURLY_BRACE |
                  // CLOSE_CURLY_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' .. '\ ' )
                  // )
               {
                  if (this.input.LA(1) == '!' || this.input.LA(1) >= '#'
                        && this.input.LA(1) <= ';' || this.input.LA(1) == '='
                        || this.input.LA(1) >= '?' && this.input.LA(1) <= '['
                        || this.input.LA(1) == ']' || this.input.LA(1) == '_'
                        || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z'
                        || this.input.LA(1) >= '~' && this.input.LA(1) <= '\uFFFF') {
                     this.input.consume();

                  } else {
                     final MismatchedSetException mse = new MismatchedSetException(null,
                           this.input);
                     this.recover(mse);
                     throw mse;
                  }

               }
                  break;

               default:
                  break loop3;
            }
         } while (true);
         this.mGREATER();
         this.setText(this.getText().substring(1, this.getText().length() - 1));
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "IRI_REF"

   // $ANTLR start "BLANK_NODE_LABEL"
   public final void mBLANK_NODE_LABEL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.BLANK_NODE_LABEL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken t = null;

         this.match("_:");
         final int tStart1743 = this.getCharIndex();
         this.mPN_LOCAL();
         t = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
               tStart1743, this.getCharIndex() - 1);
         this.setText((t != null ? t.getText() : null));
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "BLANK_NODE_LABEL"

   // $ANTLR start "VAR1"
   public final void mVAR1() throws RecognitionException {
      try {
         final int _type = SparqlLexer.VAR1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken v = null;

         this.match('?');
         final int vStart1767 = this.getCharIndex();
         this.mVARNAME();
         v = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
               vStart1767, this.getCharIndex() - 1);
         this.setText((v != null ? v.getText() : null));
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "VAR1"

   // $ANTLR start "VAR2"
   public final void mVAR2() throws RecognitionException {
      try {
         final int _type = SparqlLexer.VAR2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken v = null;

         this.match('$');
         final int vStart1791 = this.getCharIndex();
         this.mVARNAME();
         v = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
               vStart1791, this.getCharIndex() - 1);
         this.setText((v != null ? v.getText() : null));
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "VAR2"

   // $ANTLR start "LANGTAG"
   public final void mLANGTAG() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LANGTAG;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('@');
         // Sparql.g:565:11: ( PN_CHARS_BASE )+
         int cnt4 = 0;
         loop4: do {
            int alt4 = 2;
            final int LA4_0 = this.input.LA(1);

            if (LA4_0 >= 'A' && LA4_0 <= 'Z' || LA4_0 >= 'a' && LA4_0 <= 'z'
                  || LA4_0 >= '\u00C0' && LA4_0 <= '\u00D6' || LA4_0 >= '\u00D8'
                  && LA4_0 <= '\u00F6' || LA4_0 >= '\u00F8' && LA4_0 <= '\u02FF'
                  || LA4_0 >= '\u0370' && LA4_0 <= '\u037D' || LA4_0 >= '\u037F'
                  && LA4_0 <= '\u1FFF' || LA4_0 >= '\u200C' && LA4_0 <= '\u200D'
                  || LA4_0 >= '\u2070' && LA4_0 <= '\u218F' || LA4_0 >= '\u2C00'
                  && LA4_0 <= '\u2FEF' || LA4_0 >= '\u3001' && LA4_0 <= '\uD7FF'
                  || LA4_0 >= '\uF900' && LA4_0 <= '\uFDCF' || LA4_0 >= '\uFDF0'
                  && LA4_0 <= '\uFFFD') {
               alt4 = 1;
            }

            switch (alt4) {
               case 1:
                  // Sparql.g:565:11: PN_CHARS_BASE
               {
                  this.mPN_CHARS_BASE();

               }
                  break;

               default:
                  if (cnt4 >= 1) {
                     break loop4;
                  }
                  final EarlyExitException eee = new EarlyExitException(4, this.input);
                  throw eee;
            }
            cnt4++;
         } while (true);
         // Sparql.g:565:26: ( MINUS ( PN_CHARS_BASE DIGIT )+ )*
         loop6: do {
            int alt6 = 2;
            final int LA6_0 = this.input.LA(1);

            if (LA6_0 == '-') {
               alt6 = 1;
            }

            switch (alt6) {
               case 1:
                  // Sparql.g:565:27: MINUS ( PN_CHARS_BASE DIGIT )+
               {
                  this.mMINUS();
                  // Sparql.g:565:33: ( PN_CHARS_BASE DIGIT )+
                  int cnt5 = 0;
                  loop5: do {
                     int alt5 = 2;
                     final int LA5_0 = this.input.LA(1);

                     if (LA5_0 >= 'A' && LA5_0 <= 'Z' || LA5_0 >= 'a' && LA5_0 <= 'z'
                           || LA5_0 >= '\u00C0' && LA5_0 <= '\u00D6' || LA5_0 >= '\u00D8'
                           && LA5_0 <= '\u00F6' || LA5_0 >= '\u00F8' && LA5_0 <= '\u02FF'
                           || LA5_0 >= '\u0370' && LA5_0 <= '\u037D' || LA5_0 >= '\u037F'
                           && LA5_0 <= '\u1FFF' || LA5_0 >= '\u200C' && LA5_0 <= '\u200D'
                           || LA5_0 >= '\u2070' && LA5_0 <= '\u218F' || LA5_0 >= '\u2C00'
                           && LA5_0 <= '\u2FEF' || LA5_0 >= '\u3001' && LA5_0 <= '\uD7FF'
                           || LA5_0 >= '\uF900' && LA5_0 <= '\uFDCF' || LA5_0 >= '\uFDF0'
                           && LA5_0 <= '\uFFFD') {
                        alt5 = 1;
                     }

                     switch (alt5) {
                        case 1:
                           // Sparql.g:565:34: PN_CHARS_BASE DIGIT
                        {
                           this.mPN_CHARS_BASE();
                           this.mDIGIT();

                        }
                           break;

                        default:
                           if (cnt5 >= 1) {
                              break loop5;
                           }
                           final EarlyExitException eee = new EarlyExitException(5,
                                 this.input);
                           throw eee;
                     }
                     cnt5++;
                  } while (true);

               }
                  break;

               default:
                  break loop6;
            }
         } while (true);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LANGTAG"

   // $ANTLR start "INTEGER"
   public final void mINTEGER() throws RecognitionException {
      try {
         final int _type = SparqlLexer.INTEGER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // Sparql.g:569:7: ( DIGIT )+
         int cnt7 = 0;
         loop7: do {
            int alt7 = 2;
            final int LA7_0 = this.input.LA(1);

            if (LA7_0 >= '0' && LA7_0 <= '9') {
               alt7 = 1;
            }

            switch (alt7) {
               case 1:
                  // Sparql.g:569:7: DIGIT
               {
                  this.mDIGIT();

               }
                  break;

               default:
                  if (cnt7 >= 1) {
                     break loop7;
                  }
                  final EarlyExitException eee = new EarlyExitException(7, this.input);
                  throw eee;
            }
            cnt7++;
         } while (true);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER"

   // $ANTLR start "DECIMAL"
   public final void mDECIMAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DECIMAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // Sparql.g:573:5: ( ( DIGIT )+ DOT ( DIGIT )* | DOT ( DIGIT )+ )
         int alt11 = 2;
         final int LA11_0 = this.input.LA(1);

         if (LA11_0 >= '0' && LA11_0 <= '9') {
            alt11 = 1;
         } else if (LA11_0 == '.') {
            alt11 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 11, 0, this.input);

            throw nvae;
         }
         switch (alt11) {
            case 1:
               // Sparql.g:573:7: ( DIGIT )+
               int cnt8 = 0;
               loop8: do {
                  int alt8 = 2;
                  final int LA8_0 = this.input.LA(1);

                  if (LA8_0 >= '0' && LA8_0 <= '9') {
                     alt8 = 1;
                  }

                  switch (alt8) {
                     case 1:
                        // Sparql.g:573:7: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt8 >= 1) {
                           break loop8;
                        }
                        final EarlyExitException eee = new EarlyExitException(8, this.input);
                        throw eee;
                  }
                  cnt8++;
               } while (true);
               this.mDOT();
               // Sparql.g:573:18: ( DIGIT )*
               loop9: do {
                  int alt9 = 2;
                  final int LA9_0 = this.input.LA(1);

                  if (LA9_0 >= '0' && LA9_0 <= '9') {
                     alt9 = 1;
                  }

                  switch (alt9) {
                     case 1:
                        // Sparql.g:573:18: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        break loop9;
                  }
               } while (true);
               break;
            case 2:
               this.mDOT();
               // Sparql.g:574:11: ( DIGIT )+
               int cnt10 = 0;
               loop10: do {
                  int alt10 = 2;
                  final int LA10_0 = this.input.LA(1);

                  if (LA10_0 >= '0' && LA10_0 <= '9') {
                     alt10 = 1;
                  }

                  switch (alt10) {
                     case 1:
                        // Sparql.g:574:11: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt10 >= 1) {
                           break loop10;
                        }
                        final EarlyExitException eee = new EarlyExitException(10, this.input);
                        throw eee;
                  }
                  cnt10++;
               } while (true);
               break;
            default:
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DECIMAL"

   // $ANTLR start "DOUBLE"
   public final void mDOUBLE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DOUBLE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // Sparql.g:578:5: ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT |
         // ( DIGIT )+ EXPONENT )
         int alt16 = 3;
         alt16 = this.dfa16.predict(this.input);
         switch (alt16) {
            case 1:
               // Sparql.g:578:7: ( DIGIT )+
               int cnt12 = 0;
               loop12: do {
                  int alt12 = 2;
                  final int LA12_0 = this.input.LA(1);

                  if (LA12_0 >= '0' && LA12_0 <= '9') {
                     alt12 = 1;
                  }

                  switch (alt12) {
                     case 1:
                        // Sparql.g:578:7: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt12 >= 1) {
                           break loop12;
                        }
                        final EarlyExitException eee = new EarlyExitException(12, this.input);
                        throw eee;
                  }
                  cnt12++;
               } while (true);
               this.mDOT();
               // Sparql.g:578:18: ( DIGIT )*
               loop13: do {
                  int alt13 = 2;
                  final int LA13_0 = this.input.LA(1);

                  if (LA13_0 >= '0' && LA13_0 <= '9') {
                     alt13 = 1;
                  }

                  switch (alt13) {
                     case 1:
                        // Sparql.g:578:18: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        break loop13;
                  }
               } while (true);
               this.mEXPONENT();
               break;
            case 2:
               this.mDOT();
               // Sparql.g:579:11: ( DIGIT )+
               int cnt14 = 0;
               loop14: do {
                  int alt14 = 2;
                  final int LA14_0 = this.input.LA(1);

                  if (LA14_0 >= '0' && LA14_0 <= '9') {
                     alt14 = 1;
                  }

                  switch (alt14) {
                     case 1:
                        // Sparql.g:579:11: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt14 >= 1) {
                           break loop14;
                        }
                        final EarlyExitException eee = new EarlyExitException(14, this.input);
                        throw eee;
                  }
                  cnt14++;
               } while (true);
               this.mEXPONENT();
               break;
            case 3:
               // Sparql.g:580:7: ( DIGIT )+
               int cnt15 = 0;
               loop15: do {
                  int alt15 = 2;
                  final int LA15_0 = this.input.LA(1);

                  if (LA15_0 >= '0' && LA15_0 <= '9') {
                     alt15 = 1;
                  }

                  switch (alt15) {
                     case 1:
                        // Sparql.g:580:7: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt15 >= 1) {
                           break loop15;
                        }
                        final EarlyExitException eee = new EarlyExitException(15, this.input);
                        throw eee;
                  }
                  cnt15++;
               } while (true);
               this.mEXPONENT();
               break;
            default:
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOUBLE"

   // $ANTLR start "INTEGER_POSITIVE"
   public final void mINTEGER_POSITIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.INTEGER_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mPLUS();
         this.mINTEGER();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER_POSITIVE"

   // $ANTLR start "DECIMAL_POSITIVE"
   public final void mDECIMAL_POSITIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DECIMAL_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mPLUS();
         this.mDECIMAL();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DECIMAL_POSITIVE"

   // $ANTLR start "DOUBLE_POSITIVE"
   public final void mDOUBLE_POSITIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DOUBLE_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mPLUS();
         this.mDOUBLE();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOUBLE_POSITIVE"

   // $ANTLR start "INTEGER_NEGATIVE"
   public final void mINTEGER_NEGATIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.INTEGER_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mMINUS();
         this.mINTEGER();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER_NEGATIVE"

   // $ANTLR start "DECIMAL_NEGATIVE"
   public final void mDECIMAL_NEGATIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DECIMAL_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mMINUS();
         this.mDECIMAL();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DECIMAL_NEGATIVE"

   // $ANTLR start "DOUBLE_NEGATIVE"
   public final void mDOUBLE_NEGATIVE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DOUBLE_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.mMINUS();
         this.mDOUBLE();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOUBLE_NEGATIVE"

   // $ANTLR start "EXPONENT"
   public final void mEXPONENT() throws RecognitionException {
      try {
         if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         // Sparql.g:609:17: ( PLUS | MINUS )?
         int alt17 = 2;
         final int LA17_0 = this.input.LA(1);
         if (LA17_0 == '+' || LA17_0 == '-') {
            alt17 = 1;
         }
         switch (alt17) {
            case 1:
               // Sparql.g:
            {
               if (this.input.LA(1) == '+' || this.input.LA(1) == '-') {
                  this.input.consume();

               } else {
                  final MismatchedSetException mse = new MismatchedSetException(null,
                        this.input);
                  this.recover(mse);
                  throw mse;
               }

            }
               break;

         }
         // Sparql.g:609:31: ( DIGIT )+
         int cnt18 = 0;
         loop18: do {
            int alt18 = 2;
            final int LA18_0 = this.input.LA(1);

            if (LA18_0 >= '0' && LA18_0 <= '9') {
               alt18 = 1;
            }

            switch (alt18) {
               case 1:
                  // Sparql.g:609:31: DIGIT
               {
                  this.mDIGIT();

               }
                  break;

               default:
                  if (cnt18 >= 1) {
                     break loop18;
                  }
                  final EarlyExitException eee = new EarlyExitException(18, this.input);
                  throw eee;
            }
            cnt18++;
         } while (true);

      } finally {
      }
   }

   // $ANTLR end "EXPONENT"

   // $ANTLR start "STRING_LITERAL1"
   public final void mSTRING_LITERAL1() throws RecognitionException {
      try {
         final int _type = SparqlLexer.STRING_LITERAL1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('\'');
         // Sparql.g:613:12: ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' | '\ '
         // | '\ ' ) | ECHAR )*
         loop19: do {
            int alt19 = 3;
            final int LA19_0 = this.input.LA(1);

            if (LA19_0 >= '\u0000' && LA19_0 <= '\t' || LA19_0 >= '\u000B' && LA19_0 <= '\f'
                  || LA19_0 >= '\u000E' && LA19_0 <= '&' || LA19_0 >= '(' && LA19_0 <= '['
                  || LA19_0 >= ']' && LA19_0 <= '\uFFFF') {
               alt19 = 1;
            } else if (LA19_0 == '\\') {
               alt19 = 2;
            } else if (LA19_0 == '\'') {
               alt19 = 3;
            }

            switch (alt19) {
               case 1:
                  // Sparql.g:613:40: ~ ( '\\u0027' | '\\u005C' | '\ ' | '\ ' )
               {
                  if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '\t'
                        || this.input.LA(1) >= '\u000B' && this.input.LA(1) <= '\f'
                        || this.input.LA(1) >= '\u000E' && this.input.LA(1) <= '&'
                        || this.input.LA(1) >= '(' && this.input.LA(1) <= '['
                        || this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF') {
                     this.input.consume();

                  } else {
                     final MismatchedSetException mse = new MismatchedSetException(null,
                           this.input);
                     this.recover(mse);
                     throw mse;
                  }

               }
                  break;
               case 2:
                  // Sparql.g:613:87: ECHAR
               {
                  this.mECHAR();

               }
                  break;

               default:
                  break loop19;
            }
         } while (true);
         this.match('\'');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL1"

   // $ANTLR start "STRING_LITERAL2"
   public final void mSTRING_LITERAL2() throws RecognitionException {
      try {
         final int _type = SparqlLexer.STRING_LITERAL2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('\"');
         // Sparql.g:617:12: ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\ '
         // | '\ ' ) | ECHAR )*
         loop20: do {
            int alt20 = 3;
            final int LA20_0 = this.input.LA(1);

            if (LA20_0 >= '\u0000' && LA20_0 <= '\t' || LA20_0 >= '\u000B' && LA20_0 <= '\f'
                  || LA20_0 >= '\u000E' && LA20_0 <= '!' || LA20_0 >= '#' && LA20_0 <= '['
                  || LA20_0 >= ']' && LA20_0 <= '\uFFFF') {
               alt20 = 1;
            } else if (LA20_0 == '\\') {
               alt20 = 2;
            } else if (LA20_0 == '\"') {
               alt20 = 3;
            }

            switch (alt20) {
               case 1:
                  // Sparql.g:617:40: ~ ( '\\u0022' | '\\u005C' | '\ ' | '\ ' )
               {
                  if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '\t'
                        || this.input.LA(1) >= '\u000B' && this.input.LA(1) <= '\f'
                        || this.input.LA(1) >= '\u000E' && this.input.LA(1) <= '!'
                        || this.input.LA(1) >= '#' && this.input.LA(1) <= '['
                        || this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF') {
                     this.input.consume();

                  } else {
                     final MismatchedSetException mse = new MismatchedSetException(null,
                           this.input);
                     this.recover(mse);
                     throw mse;
                  }

               }
                  break;
               case 2:
                  // Sparql.g:617:87: ECHAR
               {
                  this.mECHAR();

               }
                  break;

               default:
                  break loop20;
            }
         } while (true);
         this.match('\"');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL2"

   // $ANTLR start "STRING_LITERAL_LONG1"
   public final void mSTRING_LITERAL_LONG1() throws RecognitionException {
      try {
         final int _type = SparqlLexer.STRING_LITERAL_LONG1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("'''");
         // Sparql.g:621:18: ( options {greedy=false; } : ( '\\'' | '\\'\\'' )? (~ ( '\\''
         // | '\\\\' ) | ECHAR ) )*
         loop23: do {
            int alt23 = 2;
            final int LA23_0 = this.input.LA(1);

            if (LA23_0 == '\'') {
               final int LA23_1 = this.input.LA(2);

               if (LA23_1 == '\'') {
                  final int LA23_3 = this.input.LA(3);

                  if (LA23_3 == '\'') {
                     alt23 = 2;
                  } else if (LA23_3 >= '\u0000' && LA23_3 <= '&' || LA23_3 >= '('
                        && LA23_3 <= '\uFFFF') {
                     alt23 = 1;
                  }

               } else if (LA23_1 >= '\u0000' && LA23_1 <= '&' || LA23_1 >= '('
                     && LA23_1 <= '\uFFFF') {
                  alt23 = 1;
               }

            } else if (LA23_0 >= '\u0000' && LA23_0 <= '&' || LA23_0 >= '('
                  && LA23_0 <= '\uFFFF') {
               alt23 = 1;
            }

            switch (alt23) {
               case 1:
                  // Sparql.g:621:46: ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR
                  // )
               {
                  // Sparql.g:621:46: ( '\\'' | '\\'\\'' )?
                  int alt21 = 3;
                  final int LA21_0 = this.input.LA(1);

                  if (LA21_0 == '\'') {
                     final int LA21_1 = this.input.LA(2);

                     if (LA21_1 == '\'') {
                        alt21 = 2;
                     } else if (LA21_1 >= '\u0000' && LA21_1 <= '&' || LA21_1 >= '('
                           && LA21_1 <= '\uFFFF') {
                        alt21 = 1;
                     }
                  }
                  switch (alt21) {
                     case 1:
                        // Sparql.g:621:48: '\\''
                     {
                        this.match('\'');

                     }
                        break;
                     case 2:
                        // Sparql.g:621:55: '\\'\\''
                     {
                        this.match("''");

                     }
                        break;

                  }

                  // Sparql.g:621:65: (~ ( '\\'' | '\\\\' ) | ECHAR )
                  int alt22 = 2;
                  final int LA22_0 = this.input.LA(1);

                  if (LA22_0 >= '\u0000' && LA22_0 <= '&' || LA22_0 >= '(' && LA22_0 <= '['
                        || LA22_0 >= ']' && LA22_0 <= '\uFFFF') {
                     alt22 = 1;
                  } else if (LA22_0 == '\\') {
                     alt22 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 22, 0,
                           this.input);

                     throw nvae;
                  }
                  switch (alt22) {
                     case 1:
                        // Sparql.g:621:67: ~ ( '\\'' | '\\\\' )
                     {
                        if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '&'
                              || this.input.LA(1) >= '(' && this.input.LA(1) <= '['
                              || this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF') {
                           this.input.consume();

                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.recover(mse);
                           throw mse;
                        }

                     }
                        break;
                     case 2:
                        // Sparql.g:621:82: ECHAR
                     {
                        this.mECHAR();

                     }
                        break;

                  }

               }
                  break;

               default:
                  break loop23;
            }
         } while (true);
         this.match("'''");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL_LONG1"

   // $ANTLR start "STRING_LITERAL_LONG2"
   public final void mSTRING_LITERAL_LONG2() throws RecognitionException {
      try {
         final int _type = SparqlLexer.STRING_LITERAL_LONG2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("\"\"\"");
         // Sparql.g:625:15: ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ ( '\"' |
         // '\\\\' ) | ECHAR ) )*
         loop26: do {
            int alt26 = 2;
            final int LA26_0 = this.input.LA(1);

            if (LA26_0 == '\"') {
               final int LA26_1 = this.input.LA(2);

               if (LA26_1 == '\"') {
                  final int LA26_3 = this.input.LA(3);

                  if (LA26_3 == '\"') {
                     alt26 = 2;
                  } else if (LA26_3 >= '\u0000' && LA26_3 <= '!' || LA26_3 >= '#'
                        && LA26_3 <= '\uFFFF') {
                     alt26 = 1;
                  }

               } else if (LA26_1 >= '\u0000' && LA26_1 <= '!' || LA26_1 >= '#'
                     && LA26_1 <= '\uFFFF') {
                  alt26 = 1;
               }

            } else if (LA26_0 >= '\u0000' && LA26_0 <= '!' || LA26_0 >= '#'
                  && LA26_0 <= '\uFFFF') {
               alt26 = 1;
            }

            switch (alt26) {
               case 1:
                  // Sparql.g:625:43: ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR )
               {
                  // Sparql.g:625:43: ( '\"' | '\"\"' )?
                  int alt24 = 3;
                  final int LA24_0 = this.input.LA(1);

                  if (LA24_0 == '\"') {
                     final int LA24_1 = this.input.LA(2);

                     if (LA24_1 == '\"') {
                        alt24 = 2;
                     } else if (LA24_1 >= '\u0000' && LA24_1 <= '!' || LA24_1 >= '#'
                           && LA24_1 <= '\uFFFF') {
                        alt24 = 1;
                     }
                  }
                  switch (alt24) {
                     case 1:
                        // Sparql.g:625:45: '\"'
                     {
                        this.match('\"');

                     }
                        break;
                     case 2:
                        // Sparql.g:625:51: '\"\"'
                     {
                        this.match("\"\"");

                     }
                        break;

                  }

                  // Sparql.g:625:59: (~ ( '\"' | '\\\\' ) | ECHAR )
                  int alt25 = 2;
                  final int LA25_0 = this.input.LA(1);

                  if (LA25_0 >= '\u0000' && LA25_0 <= '!' || LA25_0 >= '#' && LA25_0 <= '['
                        || LA25_0 >= ']' && LA25_0 <= '\uFFFF') {
                     alt25 = 1;
                  } else if (LA25_0 == '\\') {
                     alt25 = 2;
                  } else {
                     final NoViableAltException nvae = new NoViableAltException("", 25, 0,
                           this.input);

                     throw nvae;
                  }
                  switch (alt25) {
                     case 1:
                        // Sparql.g:625:61: ~ ( '\"' | '\\\\' )
                     {
                        if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '!'
                              || this.input.LA(1) >= '#' && this.input.LA(1) <= '['
                              || this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF') {
                           this.input.consume();

                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.recover(mse);
                           throw mse;
                        }

                     }
                        break;
                     case 2:
                        // Sparql.g:625:75: ECHAR
                     {
                        this.mECHAR();

                     }
                        break;

                  }

               }
                  break;

               default:
                  break loop26;
            }
         } while (true);
         this.match("\"\"\"");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL_LONG2"

   // $ANTLR start "ECHAR"
   public final void mECHAR() throws RecognitionException {
      try {
         this.match('\\');
         if (this.input.LA(1) == '\"' || this.input.LA(1) == '\''
               || this.input.LA(1) == '\\' || this.input.LA(1) == 'b'
               || this.input.LA(1) == 'f' || this.input.LA(1) == 'n'
               || this.input.LA(1) == 'r' || this.input.LA(1) == 't') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "ECHAR"

   // $ANTLR start "PN_CHARS_U"
   public final void mPN_CHARS_U() throws RecognitionException {
      try {
         if (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z' || this.input.LA(1) == '_'
               || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z'
               || this.input.LA(1) >= '\u00C0' && this.input.LA(1) <= '\u00D6'
               || this.input.LA(1) >= '\u00D8' && this.input.LA(1) <= '\u00F6'
               || this.input.LA(1) >= '\u00F8' && this.input.LA(1) <= '\u02FF'
               || this.input.LA(1) >= '\u0370' && this.input.LA(1) <= '\u037D'
               || this.input.LA(1) >= '\u037F' && this.input.LA(1) <= '\u1FFF'
               || this.input.LA(1) >= '\u200C' && this.input.LA(1) <= '\u200D'
               || this.input.LA(1) >= '\u2070' && this.input.LA(1) <= '\u218F'
               || this.input.LA(1) >= '\u2C00' && this.input.LA(1) <= '\u2FEF'
               || this.input.LA(1) >= '\u3001' && this.input.LA(1) <= '\uD7FF'
               || this.input.LA(1) >= '\uF900' && this.input.LA(1) <= '\uFDCF'
               || this.input.LA(1) >= '\uFDF0' && this.input.LA(1) <= '\uFFFD') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS_U"

   // $ANTLR start "VARNAME"
   public final void mVARNAME() throws RecognitionException {
      try {
         if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9' || this.input.LA(1) >= 'A'
               && this.input.LA(1) <= 'Z' || this.input.LA(1) == '_'
               || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z'
               || this.input.LA(1) >= '\u00C0' && this.input.LA(1) <= '\u00D6'
               || this.input.LA(1) >= '\u00D8' && this.input.LA(1) <= '\u00F6'
               || this.input.LA(1) >= '\u00F8' && this.input.LA(1) <= '\u02FF'
               || this.input.LA(1) >= '\u0370' && this.input.LA(1) <= '\u037D'
               || this.input.LA(1) >= '\u037F' && this.input.LA(1) <= '\u1FFF'
               || this.input.LA(1) >= '\u200C' && this.input.LA(1) <= '\u200D'
               || this.input.LA(1) >= '\u2070' && this.input.LA(1) <= '\u218F'
               || this.input.LA(1) >= '\u2C00' && this.input.LA(1) <= '\u2FEF'
               || this.input.LA(1) >= '\u3001' && this.input.LA(1) <= '\uD7FF'
               || this.input.LA(1) >= '\uF900' && this.input.LA(1) <= '\uFDCF'
               || this.input.LA(1) >= '\uFDF0' && this.input.LA(1) <= '\uFFFD') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         // Sparql.g:640:30: ( PN_CHARS_U | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' |
         // '\\u203F' .. '\\u2040' )*
         loop27: do {
            int alt27 = 2;
            final int LA27_0 = this.input.LA(1);

            if (LA27_0 >= '0' && LA27_0 <= '9' || LA27_0 >= 'A' && LA27_0 <= 'Z'
                  || LA27_0 == '_' || LA27_0 >= 'a' && LA27_0 <= 'z' || LA27_0 == '\u00B7'
                  || LA27_0 >= '\u00C0' && LA27_0 <= '\u00D6' || LA27_0 >= '\u00D8'
                  && LA27_0 <= '\u00F6' || LA27_0 >= '\u00F8' && LA27_0 <= '\u037D'
                  || LA27_0 >= '\u037F' && LA27_0 <= '\u1FFF' || LA27_0 >= '\u200C'
                  && LA27_0 <= '\u200D' || LA27_0 >= '\u203F' && LA27_0 <= '\u2040'
                  || LA27_0 >= '\u2070' && LA27_0 <= '\u218F' || LA27_0 >= '\u2C00'
                  && LA27_0 <= '\u2FEF' || LA27_0 >= '\u3001' && LA27_0 <= '\uD7FF'
                  || LA27_0 >= '\uF900' && LA27_0 <= '\uFDCF' || LA27_0 >= '\uFDF0'
                  && LA27_0 <= '\uFFFD') {
               alt27 = 1;
            }

            switch (alt27) {
               case 1:
                  // Sparql.g:
               {
                  if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                        || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                        || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
                        && this.input.LA(1) <= 'z' || this.input.LA(1) == '\u00B7'
                        || this.input.LA(1) >= '\u00C0' && this.input.LA(1) <= '\u00D6'
                        || this.input.LA(1) >= '\u00D8' && this.input.LA(1) <= '\u00F6'
                        || this.input.LA(1) >= '\u00F8' && this.input.LA(1) <= '\u037D'
                        || this.input.LA(1) >= '\u037F' && this.input.LA(1) <= '\u1FFF'
                        || this.input.LA(1) >= '\u200C' && this.input.LA(1) <= '\u200D'
                        || this.input.LA(1) >= '\u203F' && this.input.LA(1) <= '\u2040'
                        || this.input.LA(1) >= '\u2070' && this.input.LA(1) <= '\u218F'
                        || this.input.LA(1) >= '\u2C00' && this.input.LA(1) <= '\u2FEF'
                        || this.input.LA(1) >= '\u3001' && this.input.LA(1) <= '\uD7FF'
                        || this.input.LA(1) >= '\uF900' && this.input.LA(1) <= '\uFDCF'
                        || this.input.LA(1) >= '\uFDF0' && this.input.LA(1) <= '\uFFFD') {
                     this.input.consume();

                  } else {
                     final MismatchedSetException mse = new MismatchedSetException(null,
                           this.input);
                     this.recover(mse);
                     throw mse;
                  }

               }
                  break;

               default:
                  break loop27;
            }
         } while (true);

      } finally {
      }
   }

   // $ANTLR end "VARNAME"

   // $ANTLR start "PN_CHARS"
   public final void mPN_CHARS() throws RecognitionException {
      try {
         if (this.input.LA(1) == '-' || this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
               || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
               || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
               && this.input.LA(1) <= 'z' || this.input.LA(1) == '\u00B7'
               || this.input.LA(1) >= '\u00C0' && this.input.LA(1) <= '\u00D6'
               || this.input.LA(1) >= '\u00D8' && this.input.LA(1) <= '\u00F6'
               || this.input.LA(1) >= '\u00F8' && this.input.LA(1) <= '\u037D'
               || this.input.LA(1) >= '\u037F' && this.input.LA(1) <= '\u1FFF'
               || this.input.LA(1) >= '\u200C' && this.input.LA(1) <= '\u200D'
               || this.input.LA(1) >= '\u203F' && this.input.LA(1) <= '\u2040'
               || this.input.LA(1) >= '\u2070' && this.input.LA(1) <= '\u218F'
               || this.input.LA(1) >= '\u2C00' && this.input.LA(1) <= '\u2FEF'
               || this.input.LA(1) >= '\u3001' && this.input.LA(1) <= '\uD7FF'
               || this.input.LA(1) >= '\uF900' && this.input.LA(1) <= '\uFDCF'
               || this.input.LA(1) >= '\uFDF0' && this.input.LA(1) <= '\uFFFD') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS"

   // $ANTLR start "PN_PREFIX"
   public final void mPN_PREFIX() throws RecognitionException {
      try {
         this.mPN_CHARS_BASE();
         // Sparql.g:655:21: ( ( PN_CHARS | DOT )* PN_CHARS )?
         int alt29 = 2;
         final int LA29_0 = this.input.LA(1);
         if (LA29_0 >= '-' && LA29_0 <= '.' || LA29_0 >= '0' && LA29_0 <= '9'
               || LA29_0 >= 'A' && LA29_0 <= 'Z' || LA29_0 == '_' || LA29_0 >= 'a'
               && LA29_0 <= 'z' || LA29_0 == '\u00B7' || LA29_0 >= '\u00C0'
               && LA29_0 <= '\u00D6' || LA29_0 >= '\u00D8' && LA29_0 <= '\u00F6'
               || LA29_0 >= '\u00F8' && LA29_0 <= '\u037D' || LA29_0 >= '\u037F'
               && LA29_0 <= '\u1FFF' || LA29_0 >= '\u200C' && LA29_0 <= '\u200D'
               || LA29_0 >= '\u203F' && LA29_0 <= '\u2040' || LA29_0 >= '\u2070'
               && LA29_0 <= '\u218F' || LA29_0 >= '\u2C00' && LA29_0 <= '\u2FEF'
               || LA29_0 >= '\u3001' && LA29_0 <= '\uD7FF' || LA29_0 >= '\uF900'
               && LA29_0 <= '\uFDCF' || LA29_0 >= '\uFDF0' && LA29_0 <= '\uFFFD') {
            alt29 = 1;
         }
         switch (alt29) {
            case 1:
               // Sparql.g:655:22: ( PN_CHARS | DOT )* PN_CHARS
            {
               // Sparql.g:655:22: ( PN_CHARS | DOT )*
               loop28: do {
                  int alt28 = 2;
                  final int LA28_0 = this.input.LA(1);

                  if (LA28_0 == '-' || LA28_0 >= '0' && LA28_0 <= '9' || LA28_0 >= 'A'
                        && LA28_0 <= 'Z' || LA28_0 == '_' || LA28_0 >= 'a' && LA28_0 <= 'z'
                        || LA28_0 == '\u00B7' || LA28_0 >= '\u00C0' && LA28_0 <= '\u00D6'
                        || LA28_0 >= '\u00D8' && LA28_0 <= '\u00F6' || LA28_0 >= '\u00F8'
                        && LA28_0 <= '\u037D' || LA28_0 >= '\u037F' && LA28_0 <= '\u1FFF'
                        || LA28_0 >= '\u200C' && LA28_0 <= '\u200D' || LA28_0 >= '\u203F'
                        && LA28_0 <= '\u2040' || LA28_0 >= '\u2070' && LA28_0 <= '\u218F'
                        || LA28_0 >= '\u2C00' && LA28_0 <= '\u2FEF' || LA28_0 >= '\u3001'
                        && LA28_0 <= '\uD7FF' || LA28_0 >= '\uF900' && LA28_0 <= '\uFDCF'
                        || LA28_0 >= '\uFDF0' && LA28_0 <= '\uFFFD') {
                     final int LA28_1 = this.input.LA(2);

                     if (LA28_1 >= '-' && LA28_1 <= '.' || LA28_1 >= '0' && LA28_1 <= '9'
                           || LA28_1 >= 'A' && LA28_1 <= 'Z' || LA28_1 == '_'
                           || LA28_1 >= 'a' && LA28_1 <= 'z' || LA28_1 == '\u00B7'
                           || LA28_1 >= '\u00C0' && LA28_1 <= '\u00D6' || LA28_1 >= '\u00D8'
                           && LA28_1 <= '\u00F6' || LA28_1 >= '\u00F8' && LA28_1 <= '\u037D'
                           || LA28_1 >= '\u037F' && LA28_1 <= '\u1FFF' || LA28_1 >= '\u200C'
                           && LA28_1 <= '\u200D' || LA28_1 >= '\u203F' && LA28_1 <= '\u2040'
                           || LA28_1 >= '\u2070' && LA28_1 <= '\u218F' || LA28_1 >= '\u2C00'
                           && LA28_1 <= '\u2FEF' || LA28_1 >= '\u3001' && LA28_1 <= '\uD7FF'
                           || LA28_1 >= '\uF900' && LA28_1 <= '\uFDCF' || LA28_1 >= '\uFDF0'
                           && LA28_1 <= '\uFFFD') {
                        alt28 = 1;
                     }

                  } else if (LA28_0 == '.') {
                     alt28 = 1;
                  }

                  switch (alt28) {
                     case 1:
                        // Sparql.g:
                     {
                        if (this.input.LA(1) >= '-' && this.input.LA(1) <= '.'
                              || this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                              || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                              || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
                              && this.input.LA(1) <= 'z' || this.input.LA(1) == '\u00B7'
                              || this.input.LA(1) >= '\u00C0'
                              && this.input.LA(1) <= '\u00D6'
                              || this.input.LA(1) >= '\u00D8'
                              && this.input.LA(1) <= '\u00F6'
                              || this.input.LA(1) >= '\u00F8'
                              && this.input.LA(1) <= '\u037D'
                              || this.input.LA(1) >= '\u037F'
                              && this.input.LA(1) <= '\u1FFF'
                              || this.input.LA(1) >= '\u200C'
                              && this.input.LA(1) <= '\u200D'
                              || this.input.LA(1) >= '\u203F'
                              && this.input.LA(1) <= '\u2040'
                              || this.input.LA(1) >= '\u2070'
                              && this.input.LA(1) <= '\u218F'
                              || this.input.LA(1) >= '\u2C00'
                              && this.input.LA(1) <= '\u2FEF'
                              || this.input.LA(1) >= '\u3001'
                              && this.input.LA(1) <= '\uD7FF'
                              || this.input.LA(1) >= '\uF900'
                              && this.input.LA(1) <= '\uFDCF'
                              || this.input.LA(1) >= '\uFDF0'
                              && this.input.LA(1) <= '\uFFFD') {
                           this.input.consume();

                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.recover(mse);
                           throw mse;
                        }

                     }
                        break;

                     default:
                        break loop28;
                  }
               } while (true);

               this.mPN_CHARS();

            }
               break;

         }

      } finally {
      }
   }

   // $ANTLR end "PN_PREFIX"

   // $ANTLR start "PN_LOCAL"
   public final void mPN_LOCAL() throws RecognitionException {
      try {
         if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9' || this.input.LA(1) >= 'A'
               && this.input.LA(1) <= 'Z' || this.input.LA(1) == '_'
               || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z'
               || this.input.LA(1) >= '\u00C0' && this.input.LA(1) <= '\u00D6'
               || this.input.LA(1) >= '\u00D8' && this.input.LA(1) <= '\u00F6'
               || this.input.LA(1) >= '\u00F8' && this.input.LA(1) <= '\u02FF'
               || this.input.LA(1) >= '\u0370' && this.input.LA(1) <= '\u037D'
               || this.input.LA(1) >= '\u037F' && this.input.LA(1) <= '\u1FFF'
               || this.input.LA(1) >= '\u200C' && this.input.LA(1) <= '\u200D'
               || this.input.LA(1) >= '\u2070' && this.input.LA(1) <= '\u218F'
               || this.input.LA(1) >= '\u2C00' && this.input.LA(1) <= '\u2FEF'
               || this.input.LA(1) >= '\u3001' && this.input.LA(1) <= '\uD7FF'
               || this.input.LA(1) >= '\uF900' && this.input.LA(1) <= '\uFDCF'
               || this.input.LA(1) >= '\uFDF0' && this.input.LA(1) <= '\uFFFD') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         // Sparql.g:660:30: ( ( PN_CHARS | DOT )* PN_CHARS )?
         int alt31 = 2;
         final int LA31_0 = this.input.LA(1);
         if (LA31_0 >= '-' && LA31_0 <= '.' || LA31_0 >= '0' && LA31_0 <= '9'
               || LA31_0 >= 'A' && LA31_0 <= 'Z' || LA31_0 == '_' || LA31_0 >= 'a'
               && LA31_0 <= 'z' || LA31_0 == '\u00B7' || LA31_0 >= '\u00C0'
               && LA31_0 <= '\u00D6' || LA31_0 >= '\u00D8' && LA31_0 <= '\u00F6'
               || LA31_0 >= '\u00F8' && LA31_0 <= '\u037D' || LA31_0 >= '\u037F'
               && LA31_0 <= '\u1FFF' || LA31_0 >= '\u200C' && LA31_0 <= '\u200D'
               || LA31_0 >= '\u203F' && LA31_0 <= '\u2040' || LA31_0 >= '\u2070'
               && LA31_0 <= '\u218F' || LA31_0 >= '\u2C00' && LA31_0 <= '\u2FEF'
               || LA31_0 >= '\u3001' && LA31_0 <= '\uD7FF' || LA31_0 >= '\uF900'
               && LA31_0 <= '\uFDCF' || LA31_0 >= '\uFDF0' && LA31_0 <= '\uFFFD') {
            alt31 = 1;
         }
         switch (alt31) {
            case 1:
               // Sparql.g:660:31: ( PN_CHARS | DOT )* PN_CHARS
            {
               // Sparql.g:660:31: ( PN_CHARS | DOT )*
               loop30: do {
                  int alt30 = 2;
                  final int LA30_0 = this.input.LA(1);

                  if (LA30_0 == '-' || LA30_0 >= '0' && LA30_0 <= '9' || LA30_0 >= 'A'
                        && LA30_0 <= 'Z' || LA30_0 == '_' || LA30_0 >= 'a' && LA30_0 <= 'z'
                        || LA30_0 == '\u00B7' || LA30_0 >= '\u00C0' && LA30_0 <= '\u00D6'
                        || LA30_0 >= '\u00D8' && LA30_0 <= '\u00F6' || LA30_0 >= '\u00F8'
                        && LA30_0 <= '\u037D' || LA30_0 >= '\u037F' && LA30_0 <= '\u1FFF'
                        || LA30_0 >= '\u200C' && LA30_0 <= '\u200D' || LA30_0 >= '\u203F'
                        && LA30_0 <= '\u2040' || LA30_0 >= '\u2070' && LA30_0 <= '\u218F'
                        || LA30_0 >= '\u2C00' && LA30_0 <= '\u2FEF' || LA30_0 >= '\u3001'
                        && LA30_0 <= '\uD7FF' || LA30_0 >= '\uF900' && LA30_0 <= '\uFDCF'
                        || LA30_0 >= '\uFDF0' && LA30_0 <= '\uFFFD') {
                     final int LA30_1 = this.input.LA(2);

                     if (LA30_1 >= '-' && LA30_1 <= '.' || LA30_1 >= '0' && LA30_1 <= '9'
                           || LA30_1 >= 'A' && LA30_1 <= 'Z' || LA30_1 == '_'
                           || LA30_1 >= 'a' && LA30_1 <= 'z' || LA30_1 == '\u00B7'
                           || LA30_1 >= '\u00C0' && LA30_1 <= '\u00D6' || LA30_1 >= '\u00D8'
                           && LA30_1 <= '\u00F6' || LA30_1 >= '\u00F8' && LA30_1 <= '\u037D'
                           || LA30_1 >= '\u037F' && LA30_1 <= '\u1FFF' || LA30_1 >= '\u200C'
                           && LA30_1 <= '\u200D' || LA30_1 >= '\u203F' && LA30_1 <= '\u2040'
                           || LA30_1 >= '\u2070' && LA30_1 <= '\u218F' || LA30_1 >= '\u2C00'
                           && LA30_1 <= '\u2FEF' || LA30_1 >= '\u3001' && LA30_1 <= '\uD7FF'
                           || LA30_1 >= '\uF900' && LA30_1 <= '\uFDCF' || LA30_1 >= '\uFDF0'
                           && LA30_1 <= '\uFFFD') {
                        alt30 = 1;
                     }

                  } else if (LA30_0 == '.') {
                     alt30 = 1;
                  }

                  switch (alt30) {
                     case 1:
                        // Sparql.g:
                     {
                        if (this.input.LA(1) >= '-' && this.input.LA(1) <= '.'
                              || this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                              || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                              || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
                              && this.input.LA(1) <= 'z' || this.input.LA(1) == '\u00B7'
                              || this.input.LA(1) >= '\u00C0'
                              && this.input.LA(1) <= '\u00D6'
                              || this.input.LA(1) >= '\u00D8'
                              && this.input.LA(1) <= '\u00F6'
                              || this.input.LA(1) >= '\u00F8'
                              && this.input.LA(1) <= '\u037D'
                              || this.input.LA(1) >= '\u037F'
                              && this.input.LA(1) <= '\u1FFF'
                              || this.input.LA(1) >= '\u200C'
                              && this.input.LA(1) <= '\u200D'
                              || this.input.LA(1) >= '\u203F'
                              && this.input.LA(1) <= '\u2040'
                              || this.input.LA(1) >= '\u2070'
                              && this.input.LA(1) <= '\u218F'
                              || this.input.LA(1) >= '\u2C00'
                              && this.input.LA(1) <= '\u2FEF'
                              || this.input.LA(1) >= '\u3001'
                              && this.input.LA(1) <= '\uD7FF'
                              || this.input.LA(1) >= '\uF900'
                              && this.input.LA(1) <= '\uFDCF'
                              || this.input.LA(1) >= '\uFDF0'
                              && this.input.LA(1) <= '\uFFFD') {
                           this.input.consume();

                        } else {
                           final MismatchedSetException mse = new MismatchedSetException(
                                 null, this.input);
                           this.recover(mse);
                           throw mse;
                        }

                     }
                        break;

                     default:
                        break loop30;
                  }
               } while (true);

               this.mPN_CHARS();

            }
               break;

         }

      } finally {
      }
   }

   // $ANTLR end "PN_LOCAL"

   // $ANTLR start "PN_CHARS_BASE"
   public final void mPN_CHARS_BASE() throws RecognitionException {
      try {
         if (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z' || this.input.LA(1) >= 'a'
               && this.input.LA(1) <= 'z' || this.input.LA(1) >= '\u00C0'
               && this.input.LA(1) <= '\u00D6' || this.input.LA(1) >= '\u00D8'
               && this.input.LA(1) <= '\u00F6' || this.input.LA(1) >= '\u00F8'
               && this.input.LA(1) <= '\u02FF' || this.input.LA(1) >= '\u0370'
               && this.input.LA(1) <= '\u037D' || this.input.LA(1) >= '\u037F'
               && this.input.LA(1) <= '\u1FFF' || this.input.LA(1) >= '\u200C'
               && this.input.LA(1) <= '\u200D' || this.input.LA(1) >= '\u2070'
               && this.input.LA(1) <= '\u218F' || this.input.LA(1) >= '\u2C00'
               && this.input.LA(1) <= '\u2FEF' || this.input.LA(1) >= '\u3001'
               && this.input.LA(1) <= '\uD7FF' || this.input.LA(1) >= '\uF900'
               && this.input.LA(1) <= '\uFDCF' || this.input.LA(1) >= '\uFDF0'
               && this.input.LA(1) <= '\uFFFD') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS_BASE"

   // $ANTLR start "DIGIT"
   public final void mDIGIT() throws RecognitionException {
      try {
         this.matchRange('0', '9');

      } finally {
      }
   }

   // $ANTLR end "DIGIT"

   // $ANTLR start "COMMENT"
   public final void mCOMMENT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.COMMENT;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('#');
         // Sparql.g:686:11: ( options {greedy=false; } : . )*
         loop32: do {
            int alt32 = 2;
            final int LA32_0 = this.input.LA(1);

            if (LA32_0 == '\n' || LA32_0 == '\r') {
               alt32 = 2;
            } else if (LA32_0 >= '\u0000' && LA32_0 <= '\t' || LA32_0 >= '\u000B'
                  && LA32_0 <= '\f' || LA32_0 >= '\u000E' && LA32_0 <= '\uFFFF') {
               alt32 = 1;
            }

            switch (alt32) {
               case 1:
                  // Sparql.g:686:38: .
               {
                  this.matchAny();

               }
                  break;

               default:
                  break loop32;
            }
         } while (true);
         this.mEOL();
         _channel = BaseRecognizer.HIDDEN;
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMMENT"

   // $ANTLR start "EOL"
   public final void mEOL() throws RecognitionException {
      try {
         if (this.input.LA(1) == '\n' || this.input.LA(1) == '\r') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "EOL"

   // $ANTLR start "REFERENCE"
   public final void mREFERENCE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.REFERENCE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("^^");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "REFERENCE"

   // $ANTLR start "LESS_EQUAL"
   public final void mLESS_EQUAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LESS_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("<=");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LESS_EQUAL"

   // $ANTLR start "GREATER_EQUAL"
   public final void mGREATER_EQUAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.GREATER_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(">=");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GREATER_EQUAL"

   // $ANTLR start "NOT_EQUAL"
   public final void mNOT_EQUAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.NOT_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("!=");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NOT_EQUAL"

   // $ANTLR start "AND"
   public final void mAND() throws RecognitionException {
      try {
         final int _type = SparqlLexer.AND;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("&&");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "AND"

   // $ANTLR start "OR"
   public final void mOR() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OR;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("||");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OR"

   // $ANTLR start "OPEN_BRACE"
   public final void mOPEN_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OPEN_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('(');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_BRACE"

   // $ANTLR start "CLOSE_BRACE"
   public final void mCLOSE_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.CLOSE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(')');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_BRACE"

   // $ANTLR start "OPEN_CURLY_BRACE"
   public final void mOPEN_CURLY_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OPEN_CURLY_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('{');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_CURLY_BRACE"

   // $ANTLR start "CLOSE_CURLY_BRACE"
   public final void mCLOSE_CURLY_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.CLOSE_CURLY_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('}');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_CURLY_BRACE"

   // $ANTLR start "OPEN_SQUARE_BRACE"
   public final void mOPEN_SQUARE_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.OPEN_SQUARE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('[');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_SQUARE_BRACE"

   // $ANTLR start "CLOSE_SQUARE_BRACE"
   public final void mCLOSE_SQUARE_BRACE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.CLOSE_SQUARE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(']');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_SQUARE_BRACE"

   // $ANTLR start "SEMICOLON"
   public final void mSEMICOLON() throws RecognitionException {
      try {
         final int _type = SparqlLexer.SEMICOLON;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(';');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SEMICOLON"

   // $ANTLR start "DOT"
   public final void mDOT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DOT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('.');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOT"

   // $ANTLR start "PLUS"
   public final void mPLUS() throws RecognitionException {
      try {
         final int _type = SparqlLexer.PLUS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('+');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PLUS"

   // $ANTLR start "MINUS"
   public final void mMINUS() throws RecognitionException {
      try {
         final int _type = SparqlLexer.MINUS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('-');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "MINUS"

   // $ANTLR start "ASTERISK"
   public final void mASTERISK() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ASTERISK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('*');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ASTERISK"

   // $ANTLR start "COMMA"
   public final void mCOMMA() throws RecognitionException {
      try {
         final int _type = SparqlLexer.COMMA;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(',');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMMA"

   // $ANTLR start "NOT"
   public final void mNOT() throws RecognitionException {
      try {
         final int _type = SparqlLexer.NOT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('!');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NOT"

   // $ANTLR start "DIVIDE"
   public final void mDIVIDE() throws RecognitionException {
      try {
         final int _type = SparqlLexer.DIVIDE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('/');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DIVIDE"

   // $ANTLR start "EQUAL"
   public final void mEQUAL() throws RecognitionException {
      try {
         final int _type = SparqlLexer.EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('=');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "EQUAL"

   // $ANTLR start "LESS"
   public final void mLESS() throws RecognitionException {
      try {
         final int _type = SparqlLexer.LESS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('<');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LESS"

   // $ANTLR start "GREATER"
   public final void mGREATER() throws RecognitionException {
      try {
         final int _type = SparqlLexer.GREATER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('>');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GREATER"

   // $ANTLR start "ANY"
   public final void mANY() throws RecognitionException {
      try {
         final int _type = SparqlLexer.ANY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.matchAny();
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ANY"

   @Override
   public void mTokens() throws RecognitionException {
      // Sparql.g:1:8: ( WS | PNAME_NS | PNAME_LN | BASE | PREFIX | SELECT | DISTINCT |
      // REDUCED | CONSTRUCT | DESCRIBE | ASK | FROM | NAMED | WHERE | ORDER | BY | ASC |
      // DESC | LIMIT | OFFSET | OPTIONAL | GRAPH | UNION | FILTER | A | STR | LANG |
      // LANGMATCHES | DATATYPE | BOUND | SAMETERM | ISIRI | ISURI | ISBLANK | ISLITERAL |
      // REGEX | TRUE | FALSE | IRI_REF | BLANK_NODE_LABEL | VAR1 | VAR2 | LANGTAG | INTEGER
      // | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE |
      // INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 |
      // STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE
      // | LESS_EQUAL | GREATER_EQUAL | NOT_EQUAL | AND | OR | OPEN_BRACE | CLOSE_BRACE |
      // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | OPEN_SQUARE_BRACE | CLOSE_SQUARE_BRACE |
      // SEMICOLON | DOT | PLUS | MINUS | ASTERISK | COMMA | NOT | DIVIDE | EQUAL | LESS |
      // GREATER | ANY )
      int alt33 = 81;
      alt33 = this.dfa33.predict(this.input);
      switch (alt33) {
         case 1:
            this.mWS();
            break;
         case 2:
            this.mPNAME_NS();
            break;
         case 3:
            this.mPNAME_LN();
            break;
         case 4:
            this.mBASE();
            break;
         case 5:
            this.mPREFIX();
            break;
         case 6:
            this.mSELECT();
            break;
         case 7:
            this.mDISTINCT();
            break;
         case 8:
            this.mREDUCED();
            break;
         case 9:
            this.mCONSTRUCT();
            break;
         case 10:
            this.mDESCRIBE();
            break;
         case 11:
            this.mASK();
            break;
         case 12:
            this.mFROM();
            break;
         case 13:
            this.mNAMED();
            break;
         case 14:
            this.mWHERE();
            break;
         case 15:
            this.mORDER();
            break;
         case 16:
            this.mBY();
            break;
         case 17:
            this.mASC();
            break;
         case 18:
            this.mDESC();
            break;
         case 19:
            this.mLIMIT();
            break;
         case 20:
            this.mOFFSET();
            break;
         case 21:
            this.mOPTIONAL();
            break;
         case 22:
            this.mGRAPH();
            break;
         case 23:
            this.mUNION();
            break;
         case 24:
            this.mFILTER();
            break;
         case 25:
            this.mA();
            break;
         case 26:
            this.mSTR();
            break;
         case 27:
            this.mLANG();
            break;
         case 28:
            this.mLANGMATCHES();
            break;
         case 29:
            this.mDATATYPE();
            break;
         case 30:
            this.mBOUND();
            break;
         case 31:
            this.mSAMETERM();
            break;
         case 32:
            this.mISIRI();
            break;
         case 33:
            this.mISURI();
            break;
         case 34:
            this.mISBLANK();
            break;
         case 35:
            this.mISLITERAL();
            break;
         case 36:
            this.mREGEX();
            break;
         case 37:
            this.mTRUE();
            break;
         case 38:
            this.mFALSE();
            break;
         case 39:
            this.mIRI_REF();
            break;
         case 40:
            this.mBLANK_NODE_LABEL();
            break;
         case 41:
            this.mVAR1();
            break;
         case 42:
            this.mVAR2();
            break;
         case 43:
            this.mLANGTAG();
            break;
         case 44:
            this.mINTEGER();
            break;
         case 45:
            this.mDECIMAL();
            break;
         case 46:
            this.mDOUBLE();
            break;
         case 47:
            this.mINTEGER_POSITIVE();
            break;
         case 48:
            this.mDECIMAL_POSITIVE();
            break;
         case 49:
            this.mDOUBLE_POSITIVE();
            break;
         case 50:
            this.mINTEGER_NEGATIVE();
            break;
         case 51:
            this.mDECIMAL_NEGATIVE();
            break;
         case 52:
            this.mDOUBLE_NEGATIVE();
            break;
         case 53:
            this.mSTRING_LITERAL1();
            break;
         case 54:
            this.mSTRING_LITERAL2();
            break;
         case 55:
            this.mSTRING_LITERAL_LONG1();
            break;
         case 56:
            this.mSTRING_LITERAL_LONG2();
            break;
         case 57:
            this.mCOMMENT();
            break;
         case 58:
            this.mREFERENCE();
            break;
         case 59:
            this.mLESS_EQUAL();
            break;
         case 60:
            this.mGREATER_EQUAL();
            break;
         case 61:
            this.mNOT_EQUAL();
            break;
         case 62:
            this.mAND();
            break;
         case 63:
            this.mOR();
            break;
         case 64:
            this.mOPEN_BRACE();
            break;
         case 65:
            this.mCLOSE_BRACE();
            break;
         case 66:
            this.mOPEN_CURLY_BRACE();
            break;
         case 67:
            this.mCLOSE_CURLY_BRACE();
            break;
         case 68:
            this.mOPEN_SQUARE_BRACE();
            break;
         case 69:
            this.mCLOSE_SQUARE_BRACE();
            break;
         case 70:
            this.mSEMICOLON();
            break;
         case 71:
            this.mDOT();
            break;
         case 72:
            this.mPLUS();
            break;
         case 73:
            this.mMINUS();
            break;
         case 74:
            this.mASTERISK();
            break;
         case 75:
            this.mCOMMA();
            break;
         case 76:
            this.mNOT();
            break;
         case 77:
            this.mDIVIDE();
            break;
         case 78:
            this.mEQUAL();
            break;
         case 79:
            this.mLESS();
            break;
         case 80:
            this.mGREATER();
            break;
         case 81:
            this.mANY();
            break;
         default:
            break;

      }

   }

   protected DFA16 dfa16 = new DFA16(this);
   protected DFA33 dfa33 = new DFA33(this);
   static final String DFA16_eotS = "\5\uffff";
   static final String DFA16_eofS = "\5\uffff";
   static final String DFA16_minS = "\2\56\3\uffff";
   static final String DFA16_maxS = "\1\71\1\145\3\uffff";
   static final String DFA16_acceptS = "\2\uffff\1\2\1\1\1\3";
   static final String DFA16_specialS = "\5\uffff}>";
   static final String[] DFA16_transitionS = { "\1\2\1\uffff\12\1",
         "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4", "", "", "" };

   static final short[] DFA16_eot = DFA.unpackEncodedString(SparqlLexer.DFA16_eotS);
   static final short[] DFA16_eof = DFA.unpackEncodedString(SparqlLexer.DFA16_eofS);
   static final char[] DFA16_min = DFA
         .unpackEncodedStringToUnsignedChars(SparqlLexer.DFA16_minS);
   static final char[] DFA16_max = DFA
         .unpackEncodedStringToUnsignedChars(SparqlLexer.DFA16_maxS);
   static final short[] DFA16_accept = DFA.unpackEncodedString(SparqlLexer.DFA16_acceptS);
   static final short[] DFA16_special = DFA.unpackEncodedString(SparqlLexer.DFA16_specialS);
   static final short[][] DFA16_transition;

   static {
      final int numStates = SparqlLexer.DFA16_transitionS.length;
      DFA16_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         SparqlLexer.DFA16_transition[i] = DFA
               .unpackEncodedString(SparqlLexer.DFA16_transitionS[i]);
      }
   }

   class DFA16 extends DFA {

      public DFA16(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 16;
         this.eot = SparqlLexer.DFA16_eot;
         this.eof = SparqlLexer.DFA16_eof;
         this.min = SparqlLexer.DFA16_min;
         this.max = SparqlLexer.DFA16_max;
         this.accept = SparqlLexer.DFA16_accept;
         this.special = SparqlLexer.DFA16_special;
         this.transition = SparqlLexer.DFA16_transition;
      }

      @Override
      public String getDescription() {
         return "577:1: DOUBLE : ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT | ( DIGIT )+ EXPONENT );";
      }
   }

   static final String DFA33_eotS = "\2\uffff\1\61\1\71\5\61\1\105\13\61\1\125\4\61\1\133\1\137\1\141"
         + "\1\144\4\61\1\156\1\160\2\61\16\uffff\1\177\3\uffff\1\71\33\uffff"
         + "\1\u009e\7\uffff\1\u009f\1\133\2\uffff\1\u009f\1\uffff\1\u00a1\2"
         + "\uffff\1\u00a5\1\uffff\1\150\1\uffff\1\152\31\uffff\1\u00af\7\uffff"
         + "\1\u00b7\1\u00b8\23\uffff\1\u009f\1\uffff\1\u00ca\1\uffff\1\u00ca"
         + "\2\uffff\2\u00cc\2\uffff\1\u00ce\6\uffff\1\u00d5\6\uffff\1\u00da"
         + "\10\uffff\1\u00e4\6\uffff\1\u00eb\1\uffff\1\u00ca\1\uffff\1\u00cc"
         + "\1\uffff\1\u00ec\10\uffff\1\u00f4\3\uffff\1\u00f7\1\u00f8\1\u00f9"
         + "\1\u00fa\2\uffff\1\u00fd\2\uffff\1\u00ff\1\u0100\1\u0101\1\u0102"
         + "\4\uffff\1\u0105\1\u0106\7\uffff\1\u010d\4\uffff\1\u010e\17\uffff"
         + "\1\u0117\5\uffff\1\u011b\1\uffff\1\u011d\1\u011e\1\u011f\1\u0120"
         + "\2\uffff\1\u0122\7\uffff\1\u0125\2\uffff\1\u0127\3\uffff\1\u0129" + "\1\uffff";
   static final String DFA33_eofS = "\u012a\uffff";
   static final String DFA33_minS = "\1\0\1\uffff\1\55\1\60\21\55\1\41\1\72\2\60\1\101\1\56\1\60\2\56"
         + "\3\0\1\136\2\75\1\46\1\174\15\uffff\5\55\1\60\2\uffff\12\55\1\uffff"
         + "\16\55\1\41\7\uffff\1\60\1\56\2\uffff\1\60\1\uffff\1\56\1\60\1\uffff"
         + "\1\56\1\60\1\47\1\uffff\1\42\24\uffff\1\55\1\uffff\36\55\2\uffff"
         + "\1\60\1\uffff\1\60\1\uffff\1\60\2\uffff\2\60\2\uffff\4\55\1\uffff"
         + "\7\55\2\uffff\21\55\1\uffff\1\60\1\uffff\1\60\1\uffff\6\55\1\uffff"
         + "\4\55\1\uffff\11\55\1\uffff\6\55\2\uffff\7\55\1\uffff\2\55\4\uffff"
         + "\2\55\1\uffff\1\55\4\uffff\2\55\2\uffff\6\55\2\uffff\10\55\1\uffff"
         + "\3\55\1\uffff\1\55\4\uffff\1\55\1\uffff\2\55\1\uffff\1\55\1\uffff"
         + "\1\55\1\uffff";
   static final String DFA33_maxS = "\1\uffff\1\uffff\23\ufffd\1\uffff\1\72\3\ufffd\1\145\3\71\3\uffff"
         + "\1\136\2\75\1\46\1\174\15\uffff\6\ufffd\2\uffff\12\ufffd\1\uffff"
         + "\16\ufffd\1\uffff\7\uffff\2\145\2\uffff\1\145\1\uffff\1\145\1\71"
         + "\1\uffff\1\145\1\71\1\47\1\uffff\1\42\24\uffff\1\ufffd\1\uffff\36"
         + "\ufffd\2\uffff\1\145\1\uffff\1\145\1\uffff\1\145\2\uffff\2\145\2"
         + "\uffff\4\ufffd\1\uffff\7\ufffd\2\uffff\21\ufffd\1\uffff\1\145\1"
         + "\uffff\1\145\1\uffff\6\ufffd\1\uffff\4\ufffd\1\uffff\11\ufffd\1"
         + "\uffff\6\ufffd\2\uffff\7\ufffd\1\uffff\2\ufffd\4\uffff\2\ufffd\1"
         + "\uffff\1\ufffd\4\uffff\2\ufffd\2\uffff\6\ufffd\2\uffff\10\ufffd"
         + "\1\uffff\3\ufffd\1\uffff\1\ufffd\4\uffff\1\ufffd\1\uffff\2\ufffd"
         + "\1\uffff\1\ufffd\1\uffff\1\ufffd\1\uffff";
   static final String DFA33_acceptS = "\1\uffff\1\1\44\uffff\1\100\1\101\1\102\1\103\1\104\1\105\1\106"
         + "\1\112\1\113\1\115\1\116\1\121\1\1\6\uffff\1\2\1\3\12\uffff\1\31"
         + "\17\uffff\1\117\1\47\1\50\1\51\1\52\1\53\1\54\2\uffff\1\56\1\107"
         + "\1\uffff\1\110\2\uffff\1\111\3\uffff\1\65\1\uffff\1\66\1\71\1\72"
         + "\1\74\1\120\1\75\1\114\1\76\1\77\1\100\1\101\1\102\1\103\1\104\1"
         + "\105\1\106\1\112\1\113\1\115\1\116\1\uffff\1\20\36\uffff\1\73\1"
         + "\55\1\uffff\1\57\1\uffff\1\61\1\uffff\1\62\1\64\2\uffff\1\67\1\70"
         + "\4\uffff\1\32\7\uffff\1\13\1\21\21\uffff\1\60\1\uffff\1\63\1\uffff"
         + "\1\4\6\uffff\1\22\4\uffff\1\14\11\uffff\1\33\6\uffff\1\45\1\36\7"
         + "\uffff\1\44\2\uffff\1\46\1\15\1\16\1\17\2\uffff\1\23\1\uffff\1\26"
         + "\1\27\1\40\1\41\2\uffff\1\5\1\6\6\uffff\1\30\1\24\10\uffff\1\10"
         + "\3\uffff\1\42\1\uffff\1\37\1\7\1\12\1\35\1\uffff\1\25\2\uffff\1"
         + "\11\1\uffff\1\43\1\uffff\1\34";
   static final String DFA33_specialS = "\1\1\35\uffff\1\2\1\0\1\3\u0109\uffff}>";
   static final String[] DFA33_transitionS = {
         "\11\61\2\1\2\61\1\1\22\61\1\1\1\43\1\37\1\40\1\30\1\61\1\44"
               + "\1\36\1\46\1\47\1\55\1\34\1\56\1\35\1\33\1\57\12\32\1\3\1\54"
               + "\1\25\1\60\1\42\1\27\1\31\1\22\1\2\1\10\1\6\1\24\1\12\1\17\1"
               + "\24\1\21\2\24\1\16\1\24\1\13\1\15\1\4\1\24\1\7\1\5\1\23\1\20"
               + "\1\24\1\14\3\24\1\52\1\61\1\53\1\41\1\26\1\61\1\11\1\2\1\10"
               + "\1\6\1\24\1\12\1\17\1\24\1\21\2\24\1\16\1\24\1\13\1\15\1\4\1"
               + "\24\1\7\1\5\1\23\1\20\1\24\1\14\3\24\1\50\1\45\1\51\102\61\27"
               + "\24\1\61\37\24\1\61\u0208\24\160\61\16\24\1\61\u1c81\24\14\61"
               + "\2\24\142\61\u0120\24\u0a70\61\u03f0\24\21\61\ua7ff\24\u2100"
               + "\61\u04d0\24\40\61\u020e\24\2\61",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\63\15\66\1\65\11\66"
               + "\1\64\1\66\4\uffff\1\66\1\uffff\1\63\15\66\1\65\11\66\1\64\1"
               + "\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286"
               + "\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72\105\uffff\27"
               + "\72\1\uffff\37\72\1\uffff\u0208\72\160\uffff\16\72\1\uffff\u1c81"
               + "\72\14\uffff\2\72\142\uffff\u0120\72\u0a70\uffff\u03f0\72\21"
               + "\uffff\ua7ff\72\u2100\uffff\u04d0\72\40\uffff\u020e\72",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\73\10\66\4\uffff"
               + "\1\66\1\uffff\21\66\1\73\10\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\76\3\66\1\74\16\66"
               + "\1\75\6\66\4\uffff\1\66\1\uffff\1\76\3\66\1\74\16\66\1\75\6"
               + "\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286"
               + "\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\101\3\66\1\100\3\66"
               + "\1\77\21\66\4\uffff\1\66\1\uffff\1\101\3\66\1\100\3\66\1\77"
               + "\21\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286"
               + "\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\102\25\66\4\uffff"
               + "\1\66\1\uffff\4\66\1\102\25\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\16\66\1\103\13\66\4\uffff"
               + "\1\66\1\uffff\16\66\1\103\13\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\104\7\66\4\uffff"
               + "\1\66\1\uffff\22\66\1\104\7\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\110\7\66\1\107\10\66"
               + "\1\106\10\66\4\uffff\1\66\1\uffff\1\110\7\66\1\107\10\66\1\106"
               + "\10\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286"
               + "\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\111\31\66\4\uffff\1"
               + "\66\1\uffff\1\111\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\7\66\1\112\22\66\4\uffff"
               + "\1\66\1\uffff\7\66\1\112\22\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\5\66\1\114\11\66\1\115"
               + "\1\66\1\113\10\66\4\uffff\1\66\1\uffff\5\66\1\114\11\66\1\115"
               + "\1\66\1\113\10\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66"
               + "\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2"
               + "\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66"
               + "\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\117\7\66\1\116\21\66"
               + "\4\uffff\1\66\1\uffff\1\117\7\66\1\116\21\66\74\uffff\1\66\10"
               + "\uffff\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66"
               + "\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0"
               + "\66\21\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\120\10\66\4\uffff"
               + "\1\66\1\uffff\21\66\1\120\10\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\121\14\66\4\uffff"
               + "\1\66\1\uffff\15\66\1\121\14\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\122\7\66\4\uffff"
               + "\1\66\1\uffff\22\66\1\122\7\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\104\7\66\4\uffff"
               + "\1\66\1\uffff\22\66\1\104\7\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\123\10\66\4\uffff"
               + "\1\66\1\uffff\21\66\1\123\10\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\126\1\uffff\31\126\1\uffff\1\124\36\126\1\uffff\1\126\1"
               + "\uffff\1\126\1\uffff\32\126\3\uffff\uff82\126",
         "\1\127",
         "\12\130\7\uffff\32\130\4\uffff\1\130\1\uffff\32\130\105\uffff"
               + "\27\130\1\uffff\37\130\1\uffff\u0208\130\160\uffff\16\130\1"
               + "\uffff\u1c81\130\14\uffff\2\130\142\uffff\u0120\130\u0a70\uffff"
               + "\u03f0\130\21\uffff\ua7ff\130\u2100\uffff\u04d0\130\40\uffff"
               + "\u020e\130",
         "\12\131\7\uffff\32\131\4\uffff\1\131\1\uffff\32\131\105\uffff"
               + "\27\131\1\uffff\37\131\1\uffff\u0208\131\160\uffff\16\131\1"
               + "\uffff\u1c81\131\14\uffff\2\131\142\uffff\u0120\131\u0a70\uffff"
               + "\u03f0\131\21\uffff\ua7ff\131\u2100\uffff\u04d0\131\40\uffff"
               + "\u020e\131",
         "\32\132\6\uffff\32\132\105\uffff\27\132\1\uffff\37\132\1\uffff"
               + "\u0208\132\160\uffff\16\132\1\uffff\u1c81\132\14\uffff\2\132"
               + "\142\uffff\u0120\132\u0a70\uffff\u03f0\132\21\uffff\ua7ff\132"
               + "\u2100\uffff\u04d0\132\40\uffff\u020e\132",
         "\1\134\1\uffff\12\135\13\uffff\1\136\37\uffff\1\136",
         "\12\140",
         "\1\143\1\uffff\12\142",
         "\1\146\1\uffff\12\145",
         "\12\150\1\uffff\2\150\1\uffff\31\150\1\147\uffd8\150",
         "\12\152\1\uffff\2\152\1\uffff\24\152\1\151\uffdd\152",
         "\0\153",
         "\1\154",
         "\1\155",
         "\1\157",
         "\1\161",
         "\1\162",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\176\7\66\4\uffff"
               + "\1\66\1\uffff\22\66\1\176\7\66\74\uffff\1\66\10\uffff\27\66"
               + "\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2"
               + "\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\24\66\1\u0080\5\66\4"
               + "\uffff\1\66\1\uffff\24\66\1\u0080\5\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff"
               + "\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286"
               + "\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\12\72\7\uffff\32\72\4\uffff\1\72\1\uffff\32\72\105\uffff\27"
               + "\72\1\uffff\37\72\1\uffff\u0208\72\160\uffff\16\72\1\uffff\u1c81"
               + "\72\14\uffff\2\72\142\uffff\u0120\72\u0a70\uffff\u03f0\72\21"
               + "\uffff\ua7ff\72\u2100\uffff\u04d0\72\40\uffff\u020e\72",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0081\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0081\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u0082\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u0082\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u0083\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u0083\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u0084\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u0084\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u0085\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u0085\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u0086\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u0086\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u0087\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u0087\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\3\66\1\u0088\2\66\1\u0089"
               + "\23\66\4\uffff\1\66\1\uffff\3\66\1\u0088\2\66\1\u0089\23\66"
               + "\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286\66"
               + "\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u008a\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u008a\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u008c\7\66\1\u008b"
               + "\17\66\4\uffff\1\66\1\uffff\2\66\1\u008c\7\66\1\u008b\17\66"
               + "\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286\66"
               + "\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120"
               + "\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0"
               + "\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\16\66\1\u008d\13\66\4"
               + "\uffff\1\66\1\uffff\16\66\1\u008d\13\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u008e\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u008e\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u008f\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u008f\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u0090\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u0090\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0091\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0091\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\3\66\1\u0092\26\66\4"
               + "\uffff\1\66\1\uffff\3\66\1\u0092\26\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\5\66\1\u0093\24\66\4"
               + "\uffff\1\66\1\uffff\5\66\1\u0093\24\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u0094\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u0094\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u0095\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u0095\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u0096\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u0096\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u0097\31\66\4\uffff"
               + "\1\66\1\uffff\1\u0097\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u0098\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u0098\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\66\1\u009b\6\66\1\u0099"
               + "\2\66\1\u009c\10\66\1\u009a\5\66\4\uffff\1\66\1\uffff\1\66\1"
               + "\u009b\6\66\1\u0099\2\66\1\u009c\10\66\1\u009a\5\66\74\uffff"
               + "\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff"
               + "\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70"
               + "\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff"
               + "\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\24\66\1\u009d\5\66\4"
               + "\uffff\1\66\1\uffff\24\66\1\u009d\5\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\126\1\uffff\31\126\1\uffff\37\126\1\uffff\1\126\1\uffff"
               + "\1\126\1\uffff\32\126\3\uffff\uff82\126",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "\12\u00a0\13\uffff\1\136\37\uffff\1\136",
         "\1\134\1\uffff\12\135\13\uffff\1\136\37\uffff\1\136",
         "",
         "",
         "\12\140\13\uffff\1\136\37\uffff\1\136",
         "",
         "\1\u00a2\1\uffff\12\142\13\uffff\1\u00a3\37\uffff\1\u00a3",
         "\12\u00a4",
         "",
         "\1\u00a7\1\uffff\12\145\13\uffff\1\u00a6\37\uffff\1\u00a6",
         "\12\u00a8",
         "\1\u00a9",
         "",
         "\1\u00aa",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00ab\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00ab\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u00ac\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u00ac\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\5\66\1\u00ad\24\66\4"
               + "\uffff\1\66\1\uffff\5\66\1\u00ad\24\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00ae\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00ae\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00b0\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00b0\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00b1\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00b1\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u00b2\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u00b2\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u00b3\31\66\4\uffff"
               + "\1\66\1\uffff\1\u00b3\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\24\66\1\u00b4\5\66\4"
               + "\uffff\1\66\1\uffff\24\66\1\u00b4\5\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00b5\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00b5\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u00b6\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u00b6\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u00b9\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u00b9\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00ba\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00ba\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u00bb\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u00bb\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00bc\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00bc\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00bd\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00bd\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00be\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00be\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u00bf\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u00bf\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00c0\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00c0\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00c1\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00c1\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\6\66\1\u00c2\23\66\4"
               + "\uffff\1\66\1\uffff\6\66\1\u00c2\23\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\17\66\1\u00c3\12\66\4"
               + "\uffff\1\66\1\uffff\17\66\1\u00c3\12\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\16\66\1\u00c4\13\66\4"
               + "\uffff\1\66\1\uffff\16\66\1\u00c4\13\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00c5\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00c5\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00c6\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00c6\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u00c7\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u00c7\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00c8\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00c8\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00c9\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00c9\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "\12\u00a0\13\uffff\1\136\37\uffff\1\136",
         "",
         "\12\u00cb\13\uffff\1\u00a3\37\uffff\1\u00a3",
         "",
         "\12\u00a4\13\uffff\1\u00a3\37\uffff\1\u00a3",
         "",
         "",
         "\12\u00cd\13\uffff\1\u00a6\37\uffff\1\u00a6",
         "\12\u00a8\13\uffff\1\u00a6\37\uffff\1\u00a6",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\3\66\1\u00cf\26\66\4"
               + "\uffff\1\66\1\uffff\3\66\1\u00cf\26\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00d0\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00d0\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u00d1\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u00d1\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00d2\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00d2\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00d3\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00d3\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00d4\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00d4\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00d6\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00d6\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u00d7\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u00d7\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\27\66\1\u00d8\2\66\4"
               + "\uffff\1\66\1\uffff\27\66\1\u00d8\2\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00d9\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00d9\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00db\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00db\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00dc\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00dc\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\3\66\1\u00dd\26\66\4"
               + "\uffff\1\66\1\uffff\3\66\1\u00dd\26\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00de\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00de\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00df\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00df\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00e0\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00e0\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\16\66\1\u00e1\13\66\4"
               + "\uffff\1\66\1\uffff\16\66\1\u00e1\13\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00e2\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00e2\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u00e3\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u00e3\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\7\66\1\u00e5\22\66\4"
               + "\uffff\1\66\1\uffff\7\66\1\u00e5\22\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u00e6\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u00e6\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00e7\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00e7\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00e8\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00e8\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u00e9\31\66\4\uffff"
               + "\1\66\1\uffff\1\u00e9\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00ea\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00ea\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "",
         "\12\u00cb\13\uffff\1\u00a3\37\uffff\1\u00a3",
         "",
         "\12\u00cd\13\uffff\1\u00a6\37\uffff\1\u00a6",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\27\66\1\u00ed\2\66\4"
               + "\uffff\1\66\1\uffff\27\66\1\u00ed\2\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00ee\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00ee\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00ef\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00ef\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u00f0\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u00f0\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\10\66\1\u00f1\21\66\4"
               + "\uffff\1\66\1\uffff\10\66\1\u00f1\21\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\30\66\1\u00f2\1\66\4"
               + "\uffff\1\66\1\uffff\30\66\1\u00f2\1\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u00f3\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u00f3\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00f5\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00f5\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u00f6\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u00f6\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u00fb\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u00fb\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u00fc\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u00fc\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u00fe\31\66\4\uffff"
               + "\1\66\1\uffff\1\u00fe\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\15\66\1\u0103\14\66\4"
               + "\uffff\1\66\1\uffff\15\66\1\u0103\14\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0104\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0104\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u0107\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u0107\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u0108\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u0108\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\66\1\u0109\30\66\4"
               + "\uffff\1\66\1\uffff\1\66\1\u0109\30\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\17\66\1\u010a\12\66\4"
               + "\uffff\1\66\1\uffff\17\66\1\u010a\12\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\3\66\1\u010b\26\66\4"
               + "\uffff\1\66\1\uffff\3\66\1\u010b\26\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\24\66\1\u010c\5\66\4"
               + "\uffff\1\66\1\uffff\24\66\1\u010c\5\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u010f\31\66\4\uffff"
               + "\1\66\1\uffff\1\u010f\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u0110\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u0110\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\12\66\1\u0111\17\66\4"
               + "\uffff\1\66\1\uffff\12\66\1\u0111\17\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\21\66\1\u0112\10\66\4"
               + "\uffff\1\66\1\uffff\21\66\1\u0112\10\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\14\66\1\u0113\15\66\4"
               + "\uffff\1\66\1\uffff\14\66\1\u0113\15\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u0114\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u0114\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0115\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0115\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0116\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0116\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u0118\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u0118\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u0119\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u0119\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\2\66\1\u011a\27\66\4"
               + "\uffff\1\66\1\uffff\2\66\1\u011a\27\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\1\u011c\31\66\4\uffff"
               + "\1\66\1\uffff\1\u011c\31\66\74\uffff\1\66\10\uffff\27\66\1\uffff"
               + "\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff"
               + "\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff"
               + "\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\23\66\1\u0121\6\66\4"
               + "\uffff\1\66\1\uffff\23\66\1\u0121\6\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\7\66\1\u0123\22\66\4"
               + "\uffff\1\66\1\uffff\7\66\1\u0123\22\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\13\66\1\u0124\16\66\4"
               + "\uffff\1\66\1\uffff\13\66\1\u0124\16\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "",
         "",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\4\66\1\u0126\25\66\4"
               + "\uffff\1\66\1\uffff\4\66\1\u0126\25\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\22\66\1\u0128\7\66\4"
               + "\uffff\1\66\1\uffff\22\66\1\u0128\7\66\74\uffff\1\66\10\uffff"
               + "\27\66\1\uffff\37\66\1\uffff\u0286\66\1\uffff\u1c81\66\14\uffff"
               + "\2\66\61\uffff\2\66\57\uffff\u0120\66\u0a70\uffff\u03f0\66\21"
               + "\uffff\ua7ff\66\u2100\uffff\u04d0\66\40\uffff\u020e\66",
         "",
         "\1\66\1\67\1\uffff\12\66\1\70\6\uffff\32\66\4\uffff\1\66\1"
               + "\uffff\32\66\74\uffff\1\66\10\uffff\27\66\1\uffff\37\66\1\uffff"
               + "\u0286\66\1\uffff\u1c81\66\14\uffff\2\66\61\uffff\2\66\57\uffff"
               + "\u0120\66\u0a70\uffff\u03f0\66\21\uffff\ua7ff\66\u2100\uffff"
               + "\u04d0\66\40\uffff\u020e\66", "" };

   static final short[] DFA33_eot = DFA.unpackEncodedString(SparqlLexer.DFA33_eotS);
   static final short[] DFA33_eof = DFA.unpackEncodedString(SparqlLexer.DFA33_eofS);
   static final char[] DFA33_min = DFA
         .unpackEncodedStringToUnsignedChars(SparqlLexer.DFA33_minS);
   static final char[] DFA33_max = DFA
         .unpackEncodedStringToUnsignedChars(SparqlLexer.DFA33_maxS);
   static final short[] DFA33_accept = DFA.unpackEncodedString(SparqlLexer.DFA33_acceptS);
   static final short[] DFA33_special = DFA.unpackEncodedString(SparqlLexer.DFA33_specialS);
   static final short[][] DFA33_transition;

   static {
      final int numStates = SparqlLexer.DFA33_transitionS.length;
      DFA33_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         SparqlLexer.DFA33_transition[i] = DFA
               .unpackEncodedString(SparqlLexer.DFA33_transitionS[i]);
      }
   }

   class DFA33 extends DFA {

      public DFA33(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 33;
         this.eot = SparqlLexer.DFA33_eot;
         this.eof = SparqlLexer.DFA33_eof;
         this.min = SparqlLexer.DFA33_min;
         this.max = SparqlLexer.DFA33_max;
         this.accept = SparqlLexer.DFA33_accept;
         this.special = SparqlLexer.DFA33_special;
         this.transition = SparqlLexer.DFA33_transition;
      }

      @Override
      public String getDescription() {
         return "1:1: Tokens : ( WS | PNAME_NS | PNAME_LN | BASE | PREFIX | SELECT | DISTINCT | REDUCED | CONSTRUCT | DESCRIBE | ASK | FROM | NAMED | WHERE | ORDER | BY | ASC | DESC | LIMIT | OFFSET | OPTIONAL | GRAPH | UNION | FILTER | A | STR | LANG | LANGMATCHES | DATATYPE | BOUND | SAMETERM | ISIRI | ISURI | ISBLANK | ISLITERAL | REGEX | TRUE | FALSE | IRI_REF | BLANK_NODE_LABEL | VAR1 | VAR2 | LANGTAG | INTEGER | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE | LESS_EQUAL | GREATER_EQUAL | NOT_EQUAL | AND | OR | OPEN_BRACE | CLOSE_BRACE | OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | OPEN_SQUARE_BRACE | CLOSE_SQUARE_BRACE | SEMICOLON | DOT | PLUS | MINUS | ASTERISK | COMMA | NOT | DIVIDE | EQUAL | LESS | GREATER | ANY );";
      }

      @Override
      public int specialStateTransition(int s, final IntStream _input)
            throws NoViableAltException {
         final IntStream input = _input;
         final int _s = s;
         switch (s) {
            case 0:
               final int LA33_31 = input.LA(1);

               s = -1;
               if (LA33_31 == '\"') {
                  s = 105;
               } else if (LA33_31 >= '\u0000' && LA33_31 <= '\t' || LA33_31 >= '\u000B'
                     && LA33_31 <= '\f' || LA33_31 >= '\u000E' && LA33_31 <= '!'
                     || LA33_31 >= '#' && LA33_31 <= '\uFFFF') {
                  s = 106;
               } else {
                  s = 49;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 1:
               final int LA33_0 = input.LA(1);

               s = -1;
               if (LA33_0 >= '\t' && LA33_0 <= '\n' || LA33_0 == '\r' || LA33_0 == ' ') {
                  s = 1;
               }

               else if (LA33_0 == 'B' || LA33_0 == 'b') {
                  s = 2;
               }

               else if (LA33_0 == ':') {
                  s = 3;
               }

               else if (LA33_0 == 'P' || LA33_0 == 'p') {
                  s = 4;
               }

               else if (LA33_0 == 'S' || LA33_0 == 's') {
                  s = 5;
               }

               else if (LA33_0 == 'D' || LA33_0 == 'd') {
                  s = 6;
               }

               else if (LA33_0 == 'R' || LA33_0 == 'r') {
                  s = 7;
               }

               else if (LA33_0 == 'C' || LA33_0 == 'c') {
                  s = 8;
               }

               else if (LA33_0 == 'a') {
                  s = 9;
               }

               else if (LA33_0 == 'F' || LA33_0 == 'f') {
                  s = 10;
               }

               else if (LA33_0 == 'N' || LA33_0 == 'n') {
                  s = 11;
               }

               else if (LA33_0 == 'W' || LA33_0 == 'w') {
                  s = 12;
               }

               else if (LA33_0 == 'O' || LA33_0 == 'o') {
                  s = 13;
               }

               else if (LA33_0 == 'L' || LA33_0 == 'l') {
                  s = 14;
               }

               else if (LA33_0 == 'G' || LA33_0 == 'g') {
                  s = 15;
               }

               else if (LA33_0 == 'U' || LA33_0 == 'u') {
                  s = 16;
               }

               else if (LA33_0 == 'I' || LA33_0 == 'i') {
                  s = 17;
               }

               else if (LA33_0 == 'A') {
                  s = 18;
               }

               else if (LA33_0 == 'T' || LA33_0 == 't') {
                  s = 19;
               }

               else if (LA33_0 == 'E' || LA33_0 == 'H' || LA33_0 >= 'J' && LA33_0 <= 'K'
                     || LA33_0 == 'M' || LA33_0 == 'Q' || LA33_0 == 'V' || LA33_0 >= 'X'
                     && LA33_0 <= 'Z' || LA33_0 == 'e' || LA33_0 == 'h' || LA33_0 >= 'j'
                     && LA33_0 <= 'k' || LA33_0 == 'm' || LA33_0 == 'q' || LA33_0 == 'v'
                     || LA33_0 >= 'x' && LA33_0 <= 'z' || LA33_0 >= '\u00C0'
                     && LA33_0 <= '\u00D6' || LA33_0 >= '\u00D8' && LA33_0 <= '\u00F6'
                     || LA33_0 >= '\u00F8' && LA33_0 <= '\u02FF' || LA33_0 >= '\u0370'
                     && LA33_0 <= '\u037D' || LA33_0 >= '\u037F' && LA33_0 <= '\u1FFF'
                     || LA33_0 >= '\u200C' && LA33_0 <= '\u200D' || LA33_0 >= '\u2070'
                     && LA33_0 <= '\u218F' || LA33_0 >= '\u2C00' && LA33_0 <= '\u2FEF'
                     || LA33_0 >= '\u3001' && LA33_0 <= '\uD7FF' || LA33_0 >= '\uF900'
                     && LA33_0 <= '\uFDCF' || LA33_0 >= '\uFDF0' && LA33_0 <= '\uFFFD') {
                  s = 20;
               }

               else if (LA33_0 == '<') {
                  s = 21;
               }

               else if (LA33_0 == '_') {
                  s = 22;
               }

               else if (LA33_0 == '?') {
                  s = 23;
               }

               else if (LA33_0 == '$') {
                  s = 24;
               }

               else if (LA33_0 == '@') {
                  s = 25;
               }

               else if (LA33_0 >= '0' && LA33_0 <= '9') {
                  s = 26;
               }

               else if (LA33_0 == '.') {
                  s = 27;
               }

               else if (LA33_0 == '+') {
                  s = 28;
               }

               else if (LA33_0 == '-') {
                  s = 29;
               }

               else if (LA33_0 == '\'') {
                  s = 30;
               }

               else if (LA33_0 == '\"') {
                  s = 31;
               }

               else if (LA33_0 == '#') {
                  s = 32;
               }

               else if (LA33_0 == '^') {
                  s = 33;
               }

               else if (LA33_0 == '>') {
                  s = 34;
               }

               else if (LA33_0 == '!') {
                  s = 35;
               }

               else if (LA33_0 == '&') {
                  s = 36;
               }

               else if (LA33_0 == '|') {
                  s = 37;
               }

               else if (LA33_0 == '(') {
                  s = 38;
               }

               else if (LA33_0 == ')') {
                  s = 39;
               }

               else if (LA33_0 == '{') {
                  s = 40;
               }

               else if (LA33_0 == '}') {
                  s = 41;
               }

               else if (LA33_0 == '[') {
                  s = 42;
               }

               else if (LA33_0 == ']') {
                  s = 43;
               }

               else if (LA33_0 == ';') {
                  s = 44;
               }

               else if (LA33_0 == '*') {
                  s = 45;
               }

               else if (LA33_0 == ',') {
                  s = 46;
               }

               else if (LA33_0 == '/') {
                  s = 47;
               }

               else if (LA33_0 == '=') {
                  s = 48;
               }

               else if (LA33_0 >= '\u0000' && LA33_0 <= '\b' || LA33_0 >= '\u000B'
                     && LA33_0 <= '\f' || LA33_0 >= '\u000E' && LA33_0 <= '\u001F'
                     || LA33_0 == '%' || LA33_0 == '\\' || LA33_0 == '`' || LA33_0 >= '~'
                     && LA33_0 <= '\u00BF' || LA33_0 == '\u00D7' || LA33_0 == '\u00F7'
                     || LA33_0 >= '\u0300' && LA33_0 <= '\u036F' || LA33_0 == '\u037E'
                     || LA33_0 >= '\u2000' && LA33_0 <= '\u200B' || LA33_0 >= '\u200E'
                     && LA33_0 <= '\u206F' || LA33_0 >= '\u2190' && LA33_0 <= '\u2BFF'
                     || LA33_0 >= '\u2FF0' && LA33_0 <= '\u3000' || LA33_0 >= '\uD800'
                     && LA33_0 <= '\uF8FF' || LA33_0 >= '\uFDD0' && LA33_0 <= '\uFDEF'
                     || LA33_0 >= '\uFFFE' && LA33_0 <= '\uFFFF') {
                  s = 49;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 2:
               final int LA33_30 = input.LA(1);

               s = -1;
               if (LA33_30 == '\'') {
                  s = 103;
               }

               else if (LA33_30 >= '\u0000' && LA33_30 <= '\t' || LA33_30 >= '\u000B'
                     && LA33_30 <= '\f' || LA33_30 >= '\u000E' && LA33_30 <= '&'
                     || LA33_30 >= '(' && LA33_30 <= '\uFFFF') {
                  s = 104;
               } else {
                  s = 49;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 3:
               final int LA33_32 = input.LA(1);

               s = -1;
               if (LA33_32 >= '\u0000' && LA33_32 <= '\uFFFF') {
                  s = 107;
               } else {
                  s = 49;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            default:
               break;
         }
         final NoViableAltException nvae = new NoViableAltException(this.getDescription(),
               33, _s, input);
         this.error(nvae);
         throw nvae;
      }
   }

}
