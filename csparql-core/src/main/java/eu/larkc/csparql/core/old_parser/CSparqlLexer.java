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

public class CSparqlLexer extends Lexer {

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
   public static final int BASE = 10;
   public static final int PN_CHARS_U = 113;
   public static final int STREAM = 6;
   public static final int COMMENT = 115;
   public static final int SELECT = 14;
   public static final int REGISTER = 4;
   public static final int OPEN_CURLY_BRACE = 42;
   public static final int CLOSE_CURLY_BRACE = 44;
   public static final int DOUBLE_POSITIVE = 91;
   public static final int BOUND = 73;
   public static final int DIVIDE = 66;
   public static final int ISIRI = 75;
   public static final int A = 53;
   public static final int ASC = 38;
   public static final int ASK = 22;
   public static final int BLANK_NODE_LABEL = 102;
   public static final int COMPUTED_EVERY = 9;
   public static final int SEMICOLON = 52;
   public static final int ISBLANK = 77;
   public static final int GROUP = 34;
   public static final int WS = 104;
   public static final int INTEGER_POSITIVE = 89;
   public static final int NAMED = 24;
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
   public static final int MIN = 81;
   public static final int CLOSE_BRACE = 19;
   public static final int MINUS = 64;
   public static final int TIME_UNIT = 105;
   public static final int TRUE = 95;
   public static final int UNION = 49;
   public static final int OPEN_SQUARE_BRACE = 25;
   public static final int ECHAR = 112;
   public static final int OPTIONAL = 47;
   public static final int ANY = 116;
   public static final int STRING_LITERAL_LONG2 = 100;
   public static final int PN_CHARS_BASE = 109;
   public static final int DECIMAL = 87;
   public static final int VAR1 = 54;
   public static final int VAR2 = 55;
   public static final int STRING_LITERAL_LONG1 = 99;
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

   public CSparqlLexer() {
      ;
   }

   public CSparqlLexer(final CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public CSparqlLexer(final CharStream input, final RecognizerSharedState state) {
      super(input, state);

   }

   @Override
   public String getGrammarFileName() {
      return "CSparql.g";
   }

   // $ANTLR start "WS"
   public final void mWS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.WS;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:494:5: ( ( ' ' | '\\t' | EOL )+ )
         // CSparql.g:494:7: ( ' ' | '\\t' | EOL )+
         {
            // CSparql.g:494:7: ( ' ' | '\\t' | EOL )+
            int cnt1 = 0;
            loop1: do {
               int alt1 = 2;
               final int LA1_0 = this.input.LA(1);

               if (LA1_0 >= '\t' && LA1_0 <= '\n' || LA1_0 == '\r' || LA1_0 == ' ') {
                  alt1 = 1;
               }

               switch (alt1) {
                  case 1:
                     // CSparql.g:
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

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "WS"

   // $ANTLR start "AS"
   public final void mAS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.AS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:497:2: ( ( 'A' | 'a' ) ( 'S' | 's' ) )
         // CSparql.g:497:4: ( 'A' | 'a' ) ( 'S' | 's' )
         {
            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "AS"

   // $ANTLR start "TIME_RANGE"
   public final void mTIME_RANGE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.TIME_RANGE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:501:2: ( INTEGER TIME_UNIT )
         // CSparql.g:501:4: INTEGER TIME_UNIT
         {
            this.mINTEGER();
            this.mTIME_UNIT();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TIME_RANGE"

   // $ANTLR start "TIME_UNIT"
   public final void mTIME_UNIT() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.TIME_UNIT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:505:2: ( 'ms' | 'h' | 's' | 'm' | 'd' )
         int alt2 = 5;
         switch (this.input.LA(1)) {
            case 'm': {
               final int LA2_1 = this.input.LA(2);

               if (LA2_1 == 's') {
                  alt2 = 1;
               } else {
                  alt2 = 4;
               }
            }
               break;
            case 'h': {
               alt2 = 2;
            }
               break;
            case 's': {
               alt2 = 3;
            }
               break;
            case 'd': {
               alt2 = 5;
            }
               break;
            default:
               final NoViableAltException nvae = new NoViableAltException("", 2, 0,
                     this.input);

               throw nvae;
         }

         switch (alt2) {
            case 1:
               // CSparql.g:505:4: 'ms'
            {
               this.match("ms");

            }
               break;
            case 2:
               // CSparql.g:506:4: 'h'
            {
               this.match('h');

            }
               break;
            case 3:
               // CSparql.g:507:4: 's'
            {
               this.match('s');

            }
               break;
            case 4:
               // CSparql.g:508:4: 'm'
            {
               this.match('m');

            }
               break;
            case 5:
               // CSparql.g:509:4: 'd'
            {
               this.match('d');

            }
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TIME_UNIT"

   // $ANTLR start "STREAM"
   public final void mSTREAM() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STREAM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:512:2: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A'
         // | 'a' ) ( 'M' | 'm' ) )
         // CSparql.g:512:4: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'A' |
         // 'a' ) ( 'M' | 'm' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STREAM"

   // $ANTLR start "RANGE"
   public final void mRANGE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.RANGE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:516:2: ( ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E'
         // | 'e' ) )
         // CSparql.g:516:4: ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'E' |
         // 'e' )
         {
            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "RANGE"

   // $ANTLR start "STEP"
   public final void mSTEP() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STEP;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:520:2: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' ) )
         // CSparql.g:520:4: ( 'S' | 's' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'P' | 'p' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STEP"

   // $ANTLR start "TRIPLES"
   public final void mTRIPLES() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.TRIPLES;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:524:2: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'P' | 'p' ) ( 'L'
         // | 'l' ) ( 'E' | 'e' ) ( 'S' | 's' ) )
         // CSparql.g:524:4: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'I' | 'i' ) ( 'P' | 'p' ) ( 'L' |
         // 'l' ) ( 'E' | 'e' ) ( 'S' | 's' )
         {
            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TRIPLES"

   // $ANTLR start "TUMBLING"
   public final void mTUMBLING() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.TUMBLING;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:528:2: ( ( 'T' | 't' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'B' | 'b' ) ( 'L'
         // | 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
         // CSparql.g:528:4: ( 'T' | 't' ) ( 'U' | 'u' ) ( 'M' | 'm' ) ( 'B' | 'b' ) ( 'L' |
         // 'l' ) ( 'I' | 'i' ) ( 'N' | 'n' ) ( 'G' | 'g' )
         {
            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TUMBLING"

   // $ANTLR start "REGISTER"
   public final void mREGISTER() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.REGISTER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:532:2: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'I' | 'i' ) ( 'S'
         // | 's' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) )
         // CSparql.g:532:5: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'I' | 'i' ) ( 'S' |
         // 's' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' )
         {
            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "REGISTER"

   // $ANTLR start "QUERY"
   public final void mQUERY() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.QUERY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:536:2: ( ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'Y'
         // | 'y' ) )
         // CSparql.g:536:4: ( 'Q' | 'q' ) ( 'U' | 'u' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'Y' |
         // 'y' )
         {
            if (this.input.LA(1) == 'Q' || this.input.LA(1) == 'q') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "QUERY"

   // $ANTLR start "TIMESTAMP"
   public final void mTIMESTAMP() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.TIMESTAMP;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:540:2: ( ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'S'
         // | 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'P' | 'p' ) )
         // CSparql.g:540:5: ( 'T' | 't' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'S' |
         // 's' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'P' | 'p' )
         {
            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "TIMESTAMP"

   // $ANTLR start "EXISTS"
   public final void mEXISTS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.EXISTS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:544:2: ( ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T'
         // | 't' ) ( 'S' | 's' ) )
         // CSparql.g:544:4: ( 'E' | 'e' ) ( 'X' | 'x' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' |
         // 't' ) ( 'S' | 's' )
         {
            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "EXISTS"

   // $ANTLR start "NOT_BY_WORDS"
   public final void mNOT_BY_WORDS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.NOT_BY_WORDS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:548:2: ( ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' ) )
         // CSparql.g:548:4: ( 'N' | 'n' ) ( 'O' | 'o' ) ( 'T' | 't' )
         {
            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NOT_BY_WORDS"

   // $ANTLR start "COUNT"
   public final void mCOUNT() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.COUNT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:552:2: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'T'
         // | 't' ) )
         // CSparql.g:552:4: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'T' |
         // 't' )
         {
            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COUNT"

   // $ANTLR start "SUM"
   public final void mSUM() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.SUM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:556:2: ( ( 'S' | 's' ) ( 'U' | 'u' ) ( 'M' | 'm' ) )
         // CSparql.g:556:4: ( 'S' | 's' ) ( 'U' | 'u' ) ( 'M' | 'm' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SUM"

   // $ANTLR start "MIN"
   public final void mMIN() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.MIN;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:560:2: ( ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' ) )
         // CSparql.g:560:4: ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'N' | 'n' )
         {
            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "MIN"

   // $ANTLR start "MAX"
   public final void mMAX() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.MAX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:564:2: ( ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'X' | 'x' ) )
         // CSparql.g:564:4: ( 'M' | 'm' ) ( 'A' | 'a' ) ( 'X' | 'x' )
         {
            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "MAX"

   // $ANTLR start "AVG"
   public final void mAVG() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.AVG;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:568:2: ( ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'G' | 'g' ) )
         // CSparql.g:568:4: ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'G' | 'g' )
         {
            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'V' || this.input.LA(1) == 'v') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "AVG"

   // $ANTLR start "GROUP"
   public final void mGROUP() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.GROUP;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:572:2: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P'
         // | 'p' ) )
         // CSparql.g:572:4: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'P' |
         // 'p' )
         {
            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GROUP"

   // $ANTLR start "HAVING"
   public final void mHAVING() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.HAVING;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:576:2: ( ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N'
         // | 'n' ) ( 'G' | 'g' ) )
         // CSparql.g:576:4: ( 'H' | 'h' ) ( 'A' | 'a' ) ( 'V' | 'v' ) ( 'I' | 'i' ) ( 'N' |
         // 'n' ) ( 'G' | 'g' )
         {
            if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'V' || this.input.LA(1) == 'v') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "HAVING"

   // $ANTLR start "PNAME_NS"
   public final void mPNAME_NS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.PNAME_NS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken p = null;

         // CSparql.g:580:5: ( (p= PN_PREFIX )? ':' )
         // CSparql.g:580:7: (p= PN_PREFIX )? ':'
         {
            // CSparql.g:580:8: (p= PN_PREFIX )?
            int alt3 = 2;
            final int LA3_0 = this.input.LA(1);

            if (LA3_0 >= 'A' && LA3_0 <= 'Z' || LA3_0 >= 'a' && LA3_0 <= 'z'
                  || LA3_0 >= '\u00C0' && LA3_0 <= '\u00D6' || LA3_0 >= '\u00D8'
                  && LA3_0 <= '\u00F6' || LA3_0 >= '\u00F8' && LA3_0 <= '\u02FF'
                  || LA3_0 >= '\u0370' && LA3_0 <= '\u037D' || LA3_0 >= '\u037F'
                  && LA3_0 <= '\u1FFF' || LA3_0 >= '\u200C' && LA3_0 <= '\u200D'
                  || LA3_0 >= '\u2070' && LA3_0 <= '\u218F' || LA3_0 >= '\u2C00'
                  && LA3_0 <= '\u2FEF' || LA3_0 >= '\u3001' && LA3_0 <= '\uD7FF'
                  || LA3_0 >= '\uF900' && LA3_0 <= '\uFDCF' || LA3_0 >= '\uFDF0'
                  && LA3_0 <= '\uFFFD') {
               alt3 = 1;
            }
            switch (alt3) {
               case 1:
                  // CSparql.g:580:8: p= PN_PREFIX
               {
                  final int pStart776 = this.getCharIndex();
                  this.mPN_PREFIX();
                  p = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE,
                        Token.DEFAULT_CHANNEL, pStart776, this.getCharIndex() - 1);

               }
                  break;

            }

            this.match(':');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PNAME_NS"

   // $ANTLR start "PNAME_LN"
   public final void mPNAME_LN() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.PNAME_LN;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:584:5: ( PNAME_NS PN_LOCAL )
         // CSparql.g:584:7: PNAME_NS PN_LOCAL
         {
            this.mPNAME_NS();
            this.mPN_LOCAL();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PNAME_LN"

   // $ANTLR start "BASE"
   public final void mBASE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.BASE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:588:5: ( ( 'B' | 'b' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
         // CSparql.g:588:7: ( 'B' | 'b' ) ( 'A' | 'a' ) ( 'S' | 's' ) ( 'E' | 'e' )
         {
            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.PREFIX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:592:5: ( ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'I'
         // | 'i' ) ( 'X' | 'x' ) )
         // CSparql.g:592:7: ( 'P' | 'p' ) ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'F' | 'f' ) ( 'I' |
         // 'i' ) ( 'X' | 'x' )
         {
            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.SELECT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:596:5: ( ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C'
         // | 'c' ) ( 'T' | 't' ) )
         // CSparql.g:596:7: ( 'S' | 's' ) ( 'E' | 'e' ) ( 'L' | 'l' ) ( 'E' | 'e' ) ( 'C' |
         // 'c' ) ( 'T' | 't' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.DISTINCT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:600:5: ( ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I'
         // | 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
         // CSparql.g:600:7: ( 'D' | 'd' ) ( 'I' | 'i' ) ( 'S' | 's' ) ( 'T' | 't' ) ( 'I' |
         // 'i' ) ( 'N' | 'n' ) ( 'C' | 'c' ) ( 'T' | 't' )
         {
            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.REDUCED;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:604:5: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'C'
         // | 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' ) )
         // CSparql.g:604:7: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'D' | 'd' ) ( 'U' | 'u' ) ( 'C' |
         // 'c' ) ( 'E' | 'e' ) ( 'D' | 'd' )
         {
            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.CONSTRUCT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:608:5: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'T'
         // | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'C' | 'c' ) ( 'T' | 't' ) )
         // CSparql.g:608:7: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'N' | 'n' ) ( 'S' | 's' ) ( 'T' |
         // 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'C' | 'c' ) ( 'T' | 't' )
         {
            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.DESCRIBE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:612:5: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'R'
         // | 'r' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'E' | 'e' ) )
         // CSparql.g:612:7: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) ( 'R' |
         // 'r' ) ( 'I' | 'i' ) ( 'B' | 'b' ) ( 'E' | 'e' )
         {
            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ASK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:616:5: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'K' | 'k' ) )
         // CSparql.g:616:7: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'K' | 'k' )
         {
            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'K' || this.input.LA(1) == 'k') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.FROM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:620:5: ( ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' ) )
         // CSparql.g:620:7: ( 'F' | 'f' ) ( 'R' | 'r' ) ( 'O' | 'o' ) ( 'M' | 'm' )
         {
            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.NAMED;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:624:5: ( ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'D'
         // | 'd' ) )
         // CSparql.g:624:7: ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'D' |
         // 'd' )
         {
            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.WHERE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:628:5: ( ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E'
         // | 'e' ) )
         // CSparql.g:628:7: ( 'W' | 'w' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'E' |
         // 'e' )
         {
            if (this.input.LA(1) == 'W' || this.input.LA(1) == 'w') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ORDER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:632:5: ( ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R'
         // | 'r' ) )
         // CSparql.g:632:7: ( 'O' | 'o' ) ( 'R' | 'r' ) ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'R' |
         // 'r' )
         {
            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.BY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:636:5: ( ( 'B' | 'b' ) ( 'Y' | 'y' ) )
         // CSparql.g:636:7: ( 'B' | 'b' ) ( 'Y' | 'y' )
         {
            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ASC;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:640:5: ( ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
         // CSparql.g:640:7: ( 'A' | 'a' ) ( 'S' | 's' ) ( 'C' | 'c' )
         {
            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.DESC;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:644:5: ( ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' ) )
         // CSparql.g:644:7: ( 'D' | 'd' ) ( 'E' | 'e' ) ( 'S' | 's' ) ( 'C' | 'c' )
         {
            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.LIMIT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:648:5: ( ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T'
         // | 't' ) )
         // CSparql.g:648:7: ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'M' | 'm' ) ( 'I' | 'i' ) ( 'T' |
         // 't' )
         {
            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.OFFSET;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:652:5: ( ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E'
         // | 'e' ) ( 'T' | 't' ) )
         // CSparql.g:652:7: ( 'O' | 'o' ) ( 'F' | 'f' ) ( 'F' | 'f' ) ( 'S' | 's' ) ( 'E' |
         // 'e' ) ( 'T' | 't' )
         {
            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.OPTIONAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:656:5: ( ( 'O' | 'o' ) ( 'P' | 'p' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O'
         // | 'o' ) ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
         // CSparql.g:656:7: ( 'O' | 'o' ) ( 'P' | 'p' ) ( 'T' | 't' ) ( 'I' | 'i' ) ( 'O' |
         // 'o' ) ( 'N' | 'n' ) ( 'A' | 'a' ) ( 'L' | 'l' )
         {
            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.GRAPH;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:660:5: ( ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'H'
         // | 'h' ) )
         // CSparql.g:660:7: ( 'G' | 'g' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'P' | 'p' ) ( 'H' |
         // 'h' )
         {
            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.UNION;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:664:5: ( ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N'
         // | 'n' ) )
         // CSparql.g:664:7: ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'I' | 'i' ) ( 'O' | 'o' ) ( 'N' |
         // 'n' )
         {
            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.FILTER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:668:5: ( ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'E'
         // | 'e' ) ( 'R' | 'r' ) )
         // CSparql.g:668:7: ( 'F' | 'f' ) ( 'I' | 'i' ) ( 'L' | 'l' ) ( 'T' | 't' ) ( 'E' |
         // 'e' ) ( 'R' | 'r' )
         {
            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.A;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:672:5: ( 'a' )
         // CSparql.g:672:7: 'a'
         {
            this.match('a');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "A"

   // $ANTLR start "STR"
   public final void mSTR() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STR;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:676:5: ( ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' ) )
         // CSparql.g:676:7: ( 'S' | 's' ) ( 'T' | 't' ) ( 'R' | 'r' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.LANG;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:680:5: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) )
         // CSparql.g:680:7: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' )
         {
            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.LANGMATCHES;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:684:5: ( ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'M'
         // | 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ( 'E' | 'e' ) (
         // 'S' | 's' ) )
         // CSparql.g:684:7: ( 'L' | 'l' ) ( 'A' | 'a' ) ( 'N' | 'n' ) ( 'G' | 'g' ) ( 'M' |
         // 'm' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'C' | 'c' ) ( 'H' | 'h' ) ( 'E' | 'e' ) ( 'S'
         // | 's' )
         {
            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'H' || this.input.LA(1) == 'h') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.DATATYPE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:688:5: ( ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'T'
         // | 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' ) )
         // CSparql.g:688:7: ( 'D' | 'd' ) ( 'A' | 'a' ) ( 'T' | 't' ) ( 'A' | 'a' ) ( 'T' |
         // 't' ) ( 'Y' | 'y' ) ( 'P' | 'p' ) ( 'E' | 'e' )
         {
            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.BOUND;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:692:5: ( ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D'
         // | 'd' ) )
         // CSparql.g:692:7: ( 'B' | 'b' ) ( 'O' | 'o' ) ( 'U' | 'u' ) ( 'N' | 'n' ) ( 'D' |
         // 'd' )
         {
            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.SAMETERM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:696:5: ( ( 'S' | 's' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T'
         // | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' ) )
         // CSparql.g:696:7: ( 'S' | 's' ) ( 'A' | 'a' ) ( 'M' | 'm' ) ( 'E' | 'e' ) ( 'T' |
         // 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'M' | 'm' )
         {
            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ISIRI;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:700:5: ( ( 'I' | 'i' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'I'
         // | 'i' ) )
         // CSparql.g:700:7: ( 'I' | 'i' ) ( 'S' | 's' ) ( 'I' | 'i' ) ( 'R' | 'r' ) ( 'I' |
         // 'i' )
         {
            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ISURI;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:704:5: ( ( 'I' | 'i' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'I'
         // | 'i' ) )
         // CSparql.g:704:7: ( 'I' | 'i' ) ( 'S' | 's' ) ( 'U' | 'u' ) ( 'R' | 'r' ) ( 'I' |
         // 'i' )
         {
            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ISBLANK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:708:5: ( ( 'I' | 'i' ) ( 'S' | 's' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'A'
         // | 'a' ) ( 'N' | 'n' ) ( 'K' | 'k' ) )
         // CSparql.g:708:7: ( 'I' | 'i' ) ( 'S' | 's' ) ( 'B' | 'b' ) ( 'L' | 'l' ) ( 'A' |
         // 'a' ) ( 'N' | 'n' ) ( 'K' | 'k' )
         {
            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'B' || this.input.LA(1) == 'b') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'N' || this.input.LA(1) == 'n') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'K' || this.input.LA(1) == 'k') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.ISLITERAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:712:5: ( ( 'I' | 'i' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'T'
         // | 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' ) )
         // CSparql.g:712:7: ( 'I' | 'i' ) ( 'S' | 's' ) ( 'L' | 'l' ) ( 'I' | 'i' ) ( 'T' |
         // 't' ) ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'A' | 'a' ) ( 'L' | 'l' )
         {
            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'I' || this.input.LA(1) == 'i') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.REGEX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:716:5: ( ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'E' | 'e' ) ( 'X'
         // | 'x' ) )
         // CSparql.g:716:7: ( 'R' | 'r' ) ( 'E' | 'e' ) ( 'G' | 'g' ) ( 'E' | 'e' ) ( 'X' |
         // 'x' )
         {
            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'G' || this.input.LA(1) == 'g') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'X' || this.input.LA(1) == 'x') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.TRUE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:720:5: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'E' | 'e' ) )
         // CSparql.g:720:7: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'E' | 'e' )
         {
            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.FALSE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:724:5: ( ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E'
         // | 'e' ) )
         // CSparql.g:724:7: ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' |
         // 'e' )
         {
            if (this.input.LA(1) == 'F' || this.input.LA(1) == 'f') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'A' || this.input.LA(1) == 'a') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'L' || this.input.LA(1) == 'l') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'S' || this.input.LA(1) == 's') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

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
         final int _type = CSparqlLexer.IRI_REF;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:728:5: ( LESS ( options {greedy=false; } : ~ ( LESS | GREATER | '\"' |
         // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' ..
         // '\ ' ) ) )* GREATER )
         // CSparql.g:728:7: LESS ( options {greedy=false; } : ~ ( LESS | GREATER | '\"' |
         // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' ..
         // '\ ' ) ) )* GREATER
         {
            this.mLESS();
            // CSparql.g:728:12: ( options {greedy=false; } : ~ ( LESS | GREATER | '\"' |
            // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000'
            // .. '\ ' ) ) )*
            loop4: do {
               int alt4 = 2;
               final int LA4_0 = this.input.LA(1);

               if (LA4_0 == '!' || LA4_0 >= '#' && LA4_0 <= ';' || LA4_0 == '='
                     || LA4_0 >= '?' && LA4_0 <= '[' || LA4_0 == ']' || LA4_0 == '_'
                     || LA4_0 >= 'a' && LA4_0 <= 'z' || LA4_0 >= '~' && LA4_0 <= '\uFFFF') {
                  alt4 = 1;
               } else if (LA4_0 == '>') {
                  alt4 = 2;
               }

               switch (alt4) {
                  case 1:
                     // CSparql.g:728:40: ~ ( LESS | GREATER | '\"' | OPEN_CURLY_BRACE |
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
                     break loop4;
               }
            } while (true);

            this.mGREATER();
            this.setText(this.getText().substring(1, this.getText().length() - 1));

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "IRI_REF"

   // $ANTLR start "BLANK_NODE_LABEL"
   public final void mBLANK_NODE_LABEL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.BLANK_NODE_LABEL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken t = null;

         // CSparql.g:732:5: ( '_:' t= PN_LOCAL )
         // CSparql.g:732:7: '_:' t= PN_LOCAL
         {
            this.match("_:");

            final int tStart2471 = this.getCharIndex();
            this.mPN_LOCAL();
            t = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
                  tStart2471, this.getCharIndex() - 1);
            this.setText((t != null ? t.getText() : null));

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "BLANK_NODE_LABEL"

   // $ANTLR start "VAR1"
   public final void mVAR1() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.VAR1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken v = null;

         // CSparql.g:736:5: ( '?' v= VARNAME )
         // CSparql.g:736:7: '?' v= VARNAME
         {
            this.match('?');
            final int vStart2495 = this.getCharIndex();
            this.mVARNAME();
            v = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
                  vStart2495, this.getCharIndex() - 1);
            this.setText((v != null ? v.getText() : null));

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "VAR1"

   // $ANTLR start "VAR2"
   public final void mVAR2() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.VAR2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         CommonToken v = null;

         // CSparql.g:740:5: ( '$' v= VARNAME )
         // CSparql.g:740:7: '$' v= VARNAME
         {
            this.match('$');
            final int vStart2519 = this.getCharIndex();
            this.mVARNAME();
            v = new CommonToken(this.input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL,
                  vStart2519, this.getCharIndex() - 1);
            this.setText((v != null ? v.getText() : null));

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "VAR2"

   // $ANTLR start "LANGTAG"
   public final void mLANGTAG() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.LANGTAG;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:744:5: ( '@' ( PN_CHARS_BASE )+ ( MINUS ( PN_CHARS_BASE DIGIT )+ )* )
         // CSparql.g:744:7: '@' ( PN_CHARS_BASE )+ ( MINUS ( PN_CHARS_BASE DIGIT )+ )*
         {
            this.match('@');
            // CSparql.g:744:11: ( PN_CHARS_BASE )+
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
                     // CSparql.g:744:11: PN_CHARS_BASE
                  {
                     this.mPN_CHARS_BASE();

                  }
                     break;

                  default:
                     if (cnt5 >= 1) {
                        break loop5;
                     }
                     final EarlyExitException eee = new EarlyExitException(5, this.input);
                     throw eee;
               }
               cnt5++;
            } while (true);

            // CSparql.g:744:26: ( MINUS ( PN_CHARS_BASE DIGIT )+ )*
            loop7: do {
               int alt7 = 2;
               final int LA7_0 = this.input.LA(1);

               if (LA7_0 == '-') {
                  alt7 = 1;
               }

               switch (alt7) {
                  case 1:
                     // CSparql.g:744:27: MINUS ( PN_CHARS_BASE DIGIT )+
                  {
                     this.mMINUS();
                     // CSparql.g:744:33: ( PN_CHARS_BASE DIGIT )+
                     int cnt6 = 0;
                     loop6: do {
                        int alt6 = 2;
                        final int LA6_0 = this.input.LA(1);

                        if (LA6_0 >= 'A' && LA6_0 <= 'Z' || LA6_0 >= 'a' && LA6_0 <= 'z'
                              || LA6_0 >= '\u00C0' && LA6_0 <= '\u00D6' || LA6_0 >= '\u00D8'
                              && LA6_0 <= '\u00F6' || LA6_0 >= '\u00F8' && LA6_0 <= '\u02FF'
                              || LA6_0 >= '\u0370' && LA6_0 <= '\u037D' || LA6_0 >= '\u037F'
                              && LA6_0 <= '\u1FFF' || LA6_0 >= '\u200C' && LA6_0 <= '\u200D'
                              || LA6_0 >= '\u2070' && LA6_0 <= '\u218F' || LA6_0 >= '\u2C00'
                              && LA6_0 <= '\u2FEF' || LA6_0 >= '\u3001' && LA6_0 <= '\uD7FF'
                              || LA6_0 >= '\uF900' && LA6_0 <= '\uFDCF' || LA6_0 >= '\uFDF0'
                              && LA6_0 <= '\uFFFD') {
                           alt6 = 1;
                        }

                        switch (alt6) {
                           case 1:
                              // CSparql.g:744:34: PN_CHARS_BASE DIGIT
                           {
                              this.mPN_CHARS_BASE();
                              this.mDIGIT();

                           }
                              break;

                           default:
                              if (cnt6 >= 1) {
                                 break loop6;
                              }
                              final EarlyExitException eee = new EarlyExitException(6,
                                    this.input);
                              throw eee;
                        }
                        cnt6++;
                     } while (true);

                  }
                     break;

                  default:
                     break loop7;
               }
            } while (true);

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LANGTAG"

   // $ANTLR start "INTEGER"
   public final void mINTEGER() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.INTEGER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:748:5: ( ( DIGIT )+ )
         // CSparql.g:748:7: ( DIGIT )+
         {
            // CSparql.g:748:7: ( DIGIT )+
            int cnt8 = 0;
            loop8: do {
               int alt8 = 2;
               final int LA8_0 = this.input.LA(1);

               if (LA8_0 >= '0' && LA8_0 <= '9') {
                  alt8 = 1;
               }

               switch (alt8) {
                  case 1:
                     // CSparql.g:748:7: DIGIT
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

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER"

   // $ANTLR start "DECIMAL"
   public final void mDECIMAL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DECIMAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:752:5: ( ( DIGIT )+ DOT ( DIGIT )* | DOT ( DIGIT )+ )
         int alt12 = 2;
         final int LA12_0 = this.input.LA(1);

         if (LA12_0 >= '0' && LA12_0 <= '9') {
            alt12 = 1;
         } else if (LA12_0 == '.') {
            alt12 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 12, 0, this.input);

            throw nvae;
         }
         switch (alt12) {
            case 1:
               // CSparql.g:752:7: ( DIGIT )+ DOT ( DIGIT )*
            {
               // CSparql.g:752:7: ( DIGIT )+
               int cnt9 = 0;
               loop9: do {
                  int alt9 = 2;
                  final int LA9_0 = this.input.LA(1);

                  if (LA9_0 >= '0' && LA9_0 <= '9') {
                     alt9 = 1;
                  }

                  switch (alt9) {
                     case 1:
                        // CSparql.g:752:7: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt9 >= 1) {
                           break loop9;
                        }
                        final EarlyExitException eee = new EarlyExitException(9, this.input);
                        throw eee;
                  }
                  cnt9++;
               } while (true);

               this.mDOT();
               // CSparql.g:752:18: ( DIGIT )*
               loop10: do {
                  int alt10 = 2;
                  final int LA10_0 = this.input.LA(1);

                  if (LA10_0 >= '0' && LA10_0 <= '9') {
                     alt10 = 1;
                  }

                  switch (alt10) {
                     case 1:
                        // CSparql.g:752:18: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        break loop10;
                  }
               } while (true);

            }
               break;
            case 2:
               // CSparql.g:753:7: DOT ( DIGIT )+
            {
               this.mDOT();
               // CSparql.g:753:11: ( DIGIT )+
               int cnt11 = 0;
               loop11: do {
                  int alt11 = 2;
                  final int LA11_0 = this.input.LA(1);

                  if (LA11_0 >= '0' && LA11_0 <= '9') {
                     alt11 = 1;
                  }

                  switch (alt11) {
                     case 1:
                        // CSparql.g:753:11: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt11 >= 1) {
                           break loop11;
                        }
                        final EarlyExitException eee = new EarlyExitException(11, this.input);
                        throw eee;
                  }
                  cnt11++;
               } while (true);

            }
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
         final int _type = CSparqlLexer.DOUBLE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:757:5: ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT |
         // ( DIGIT )+ EXPONENT )
         int alt17 = 3;
         alt17 = this.dfa17.predict(this.input);
         switch (alt17) {
            case 1:
               // CSparql.g:757:7: ( DIGIT )+ DOT ( DIGIT )* EXPONENT
            {
               // CSparql.g:757:7: ( DIGIT )+
               int cnt13 = 0;
               loop13: do {
                  int alt13 = 2;
                  final int LA13_0 = this.input.LA(1);

                  if (LA13_0 >= '0' && LA13_0 <= '9') {
                     alt13 = 1;
                  }

                  switch (alt13) {
                     case 1:
                        this.mDIGIT();
                        break;

                     default:
                        if (cnt13 >= 1) {
                           break loop13;
                        }
                        final EarlyExitException eee = new EarlyExitException(13, this.input);
                        throw eee;
                  }
                  cnt13++;
               } while (true);

               this.mDOT();
               // CSparql.g:757:18: ( DIGIT )*
               loop14: do {
                  int alt14 = 2;
                  final int LA14_0 = this.input.LA(1);

                  if (LA14_0 >= '0' && LA14_0 <= '9') {
                     alt14 = 1;
                  }

                  switch (alt14) {
                     case 1:
                        this.mDIGIT();
                        break;

                     default:
                        break loop14;
                  }
               } while (true);

               this.mEXPONENT();

            }
               break;
            case 2:
               this.mDOT();
               // CSparql.g:758:11: ( DIGIT )+
               int cnt15 = 0;
               loop15: do {
                  int alt15 = 2;
                  final int LA15_0 = this.input.LA(1);

                  if (LA15_0 >= '0' && LA15_0 <= '9') {
                     alt15 = 1;
                  }

                  switch (alt15) {
                     case 1:
                        // CSparql.g:758:11: DIGIT
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
            case 3:
               // CSparql.g:759:7: ( DIGIT )+
               int cnt16 = 0;
               loop16: do {
                  int alt16 = 2;
                  final int LA16_0 = this.input.LA(1);

                  if (LA16_0 >= '0' && LA16_0 <= '9') {
                     alt16 = 1;
                  }

                  switch (alt16) {
                     case 1:
                        // CSparql.g:759:7: DIGIT
                     {
                        this.mDIGIT();

                     }
                        break;

                     default:
                        if (cnt16 >= 1) {
                           break loop16;
                        }
                        final EarlyExitException eee = new EarlyExitException(16, this.input);
                        throw eee;
                  }
                  cnt16++;
               } while (true);
               this.mEXPONENT();
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         //
      }
   }

   // $ANTLR end "DOUBLE"

   // $ANTLR start "INTEGER_POSITIVE"
   public final void mINTEGER_POSITIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.INTEGER_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:763:5: ( PLUS INTEGER )
         // CSparql.g:763:7: PLUS INTEGER
         {
            this.mPLUS();
            this.mINTEGER();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER_POSITIVE"

   // $ANTLR start "DECIMAL_POSITIVE"
   public final void mDECIMAL_POSITIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DECIMAL_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:767:5: ( PLUS DECIMAL )
         // CSparql.g:767:7: PLUS DECIMAL
         {
            this.mPLUS();
            this.mDECIMAL();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DECIMAL_POSITIVE"

   // $ANTLR start "DOUBLE_POSITIVE"
   public final void mDOUBLE_POSITIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DOUBLE_POSITIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:771:5: ( PLUS DOUBLE )
         // CSparql.g:771:7: PLUS DOUBLE
         {
            this.mPLUS();
            this.mDOUBLE();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOUBLE_POSITIVE"

   // $ANTLR start "INTEGER_NEGATIVE"
   public final void mINTEGER_NEGATIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.INTEGER_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:775:5: ( MINUS INTEGER )
         // CSparql.g:775:7: MINUS INTEGER
         {
            this.mMINUS();
            this.mINTEGER();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INTEGER_NEGATIVE"

   // $ANTLR start "DECIMAL_NEGATIVE"
   public final void mDECIMAL_NEGATIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DECIMAL_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:779:5: ( MINUS DECIMAL )
         // CSparql.g:779:7: MINUS DECIMAL
         {
            this.mMINUS();
            this.mDECIMAL();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DECIMAL_NEGATIVE"

   // $ANTLR start "DOUBLE_NEGATIVE"
   public final void mDOUBLE_NEGATIVE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DOUBLE_NEGATIVE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:783:5: ( MINUS DOUBLE )
         // CSparql.g:783:7: MINUS DOUBLE
         {
            this.mMINUS();
            this.mDOUBLE();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOUBLE_NEGATIVE"

   // $ANTLR start "EXPONENT"
   public final void mEXPONENT() throws RecognitionException {
      try {
         // CSparql.g:788:5: ( ( 'e' | 'E' ) ( PLUS | MINUS )? ( DIGIT )+ )
         // CSparql.g:788:7: ( 'e' | 'E' ) ( PLUS | MINUS )? ( DIGIT )+
         {
            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            // CSparql.g:788:17: ( PLUS | MINUS )?
            int alt18 = 2;
            final int LA18_0 = this.input.LA(1);

            if (LA18_0 == '+' || LA18_0 == '-') {
               alt18 = 1;
            }
            switch (alt18) {
               case 1:
                  // CSparql.g:
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

            // CSparql.g:788:31: ( DIGIT )+
            int cnt19 = 0;
            loop19: do {
               int alt19 = 2;
               final int LA19_0 = this.input.LA(1);

               if (LA19_0 >= '0' && LA19_0 <= '9') {
                  alt19 = 1;
               }

               switch (alt19) {
                  case 1:
                     // CSparql.g:788:31: DIGIT
                  {
                     this.mDIGIT();

                  }
                     break;

                  default:
                     if (cnt19 >= 1) {
                        break loop19;
                     }
                     final EarlyExitException eee = new EarlyExitException(19, this.input);
                     throw eee;
               }
               cnt19++;
            } while (true);

         }

      } finally {
      }
   }

   // $ANTLR end "EXPONENT"

   // $ANTLR start "STRING_LITERAL1"
   public final void mSTRING_LITERAL1() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STRING_LITERAL1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:792:5: ( '\\'' ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' |
         // '\ ' | '\ ' ) | ECHAR )* '\\'' )
         // CSparql.g:792:7: '\\'' ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' |
         // '\ ' | '\ ' ) | ECHAR )* '\\''
         {
            this.match('\'');
            // CSparql.g:792:12: ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' | '\
            // ' | '\ ' ) | ECHAR )*
            loop20: do {
               int alt20 = 3;
               final int LA20_0 = this.input.LA(1);

               if (LA20_0 >= '\u0000' && LA20_0 <= '\t' || LA20_0 >= '\u000B'
                     && LA20_0 <= '\f' || LA20_0 >= '\u000E' && LA20_0 <= '&'
                     || LA20_0 >= '(' && LA20_0 <= '[' || LA20_0 >= ']'
                     && LA20_0 <= '\uFFFF') {
                  alt20 = 1;
               } else if (LA20_0 == '\\') {
                  alt20 = 2;
               } else if (LA20_0 == '\'') {
                  alt20 = 3;
               }

               switch (alt20) {
                  case 1:
                     // CSparql.g:792:40: ~ ( '\\u0027' | '\\u005C' | '\ ' | '\ ' )
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
                     // CSparql.g:792:87: ECHAR
                  {
                     this.mECHAR();

                  }
                     break;

                  default:
                     break loop20;
               }
            } while (true);

            this.match('\'');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL1"

   // $ANTLR start "STRING_LITERAL2"
   public final void mSTRING_LITERAL2() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STRING_LITERAL2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:796:5: ( '\"' ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' |
         // '\ ' | '\ ' ) | ECHAR )* '\"' )
         // CSparql.g:796:7: '\"' ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\
         // ' | '\ ' ) | ECHAR )* '\"'
         {
            this.match('\"');
            // CSparql.g:796:12: ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\
            // ' | '\ ' ) | ECHAR )*
            loop21: do {
               int alt21 = 3;
               final int LA21_0 = this.input.LA(1);

               if (LA21_0 >= '\u0000' && LA21_0 <= '\t' || LA21_0 >= '\u000B'
                     && LA21_0 <= '\f' || LA21_0 >= '\u000E' && LA21_0 <= '!'
                     || LA21_0 >= '#' && LA21_0 <= '[' || LA21_0 >= ']'
                     && LA21_0 <= '\uFFFF') {
                  alt21 = 1;
               } else if (LA21_0 == '\\') {
                  alt21 = 2;
               } else if (LA21_0 == '\"') {
                  alt21 = 3;
               }

               switch (alt21) {
                  case 1:
                     // CSparql.g:796:40: ~ ( '\\u0022' | '\\u005C' | '\ ' | '\ ' )
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
                     // CSparql.g:796:87: ECHAR
                  {
                     this.mECHAR();

                  }
                     break;

                  default:
                     break loop21;
               }
            } while (true);

            this.match('\"');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL2"

   // $ANTLR start "STRING_LITERAL_LONG1"
   public final void mSTRING_LITERAL_LONG1() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STRING_LITERAL_LONG1;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:800:5: ( '\\'\\'\\'' ( options {greedy=false; } : ( '\\'' | '\\'\\'' )?
         // (~ ( '\\'' | '\\\\' ) | ECHAR ) )* '\\'\\'\\'' )
         // CSparql.g:800:9: '\\'\\'\\'' ( options {greedy=false; } : ( '\\'' | '\\'\\'' )?
         // (~ ( '\\'' | '\\\\' ) | ECHAR ) )* '\\'\\'\\''
         {
            this.match("'''");

            // CSparql.g:800:18: ( options {greedy=false; } : ( '\\'' | '\\'\\'' )? (~ (
            // '\\'' | '\\\\' ) | ECHAR ) )*
            loop24: do {
               int alt24 = 2;
               final int LA24_0 = this.input.LA(1);

               if (LA24_0 == '\'') {
                  final int LA24_1 = this.input.LA(2);

                  if (LA24_1 == '\'') {
                     final int LA24_3 = this.input.LA(3);

                     if (LA24_3 == '\'') {
                        alt24 = 2;
                     } else if (LA24_3 >= '\u0000' && LA24_3 <= '&' || LA24_3 >= '('
                           && LA24_3 <= '\uFFFF') {
                        alt24 = 1;
                     }

                  } else if (LA24_1 >= '\u0000' && LA24_1 <= '&' || LA24_1 >= '('
                        && LA24_1 <= '\uFFFF') {
                     alt24 = 1;
                  }

               } else if (LA24_0 >= '\u0000' && LA24_0 <= '&' || LA24_0 >= '('
                     && LA24_0 <= '\uFFFF') {
                  alt24 = 1;
               }

               switch (alt24) {
                  case 1:
                     // CSparql.g:800:46: ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR
                     // )
                  {
                     // CSparql.g:800:46: ( '\\'' | '\\'\\'' )?
                     int alt22 = 3;
                     final int LA22_0 = this.input.LA(1);

                     if (LA22_0 == '\'') {
                        final int LA22_1 = this.input.LA(2);

                        if (LA22_1 == '\'') {
                           alt22 = 2;
                        } else if (LA22_1 >= '\u0000' && LA22_1 <= '&' || LA22_1 >= '('
                              && LA22_1 <= '\uFFFF') {
                           alt22 = 1;
                        }
                     }
                     switch (alt22) {
                        case 1:
                           // CSparql.g:800:48: '\\''
                        {
                           this.match('\'');

                        }
                           break;
                        case 2:
                           // CSparql.g:800:55: '\\'\\''
                        {
                           this.match("''");

                        }
                           break;

                     }

                     // CSparql.g:800:65: (~ ( '\\'' | '\\\\' ) | ECHAR )
                     int alt23 = 2;
                     final int LA23_0 = this.input.LA(1);

                     if (LA23_0 >= '\u0000' && LA23_0 <= '&' || LA23_0 >= '('
                           && LA23_0 <= '[' || LA23_0 >= ']' && LA23_0 <= '\uFFFF') {
                        alt23 = 1;
                     } else if (LA23_0 == '\\') {
                        alt23 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 23,
                              0, this.input);

                        throw nvae;
                     }
                     switch (alt23) {
                        case 1:
                           // CSparql.g:800:67: ~ ( '\\'' | '\\\\' )
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
                           // CSparql.g:800:82: ECHAR
                        {
                           this.mECHAR();

                        }
                           break;

                     }

                  }
                     break;

                  default:
                     break loop24;
               }
            } while (true);

            this.match("'''");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL_LONG1"

   // $ANTLR start "STRING_LITERAL_LONG2"
   public final void mSTRING_LITERAL_LONG2() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.STRING_LITERAL_LONG2;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:804:5: ( '\"\"\"' ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ (
         // '\"' | '\\\\' ) | ECHAR ) )* '\"\"\"' )
         // CSparql.g:804:9: '\"\"\"' ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ (
         // '\"' | '\\\\' ) | ECHAR ) )* '\"\"\"'
         {
            this.match("\"\"\"");

            // CSparql.g:804:15: ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ ( '\"' |
            // '\\\\' ) | ECHAR ) )*
            loop27: do {
               int alt27 = 2;
               final int LA27_0 = this.input.LA(1);

               if (LA27_0 == '\"') {
                  final int LA27_1 = this.input.LA(2);

                  if (LA27_1 == '\"') {
                     final int LA27_3 = this.input.LA(3);

                     if (LA27_3 == '\"') {
                        alt27 = 2;
                     } else if (LA27_3 >= '\u0000' && LA27_3 <= '!' || LA27_3 >= '#'
                           && LA27_3 <= '\uFFFF') {
                        alt27 = 1;
                     }

                  } else if (LA27_1 >= '\u0000' && LA27_1 <= '!' || LA27_1 >= '#'
                        && LA27_1 <= '\uFFFF') {
                     alt27 = 1;
                  }

               } else if (LA27_0 >= '\u0000' && LA27_0 <= '!' || LA27_0 >= '#'
                     && LA27_0 <= '\uFFFF') {
                  alt27 = 1;
               }

               switch (alt27) {
                  case 1:
                     // CSparql.g:804:43: ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR )
                  {
                     // CSparql.g:804:43: ( '\"' | '\"\"' )?
                     int alt25 = 3;
                     final int LA25_0 = this.input.LA(1);

                     if (LA25_0 == '\"') {
                        final int LA25_1 = this.input.LA(2);

                        if (LA25_1 == '\"') {
                           alt25 = 2;
                        } else if (LA25_1 >= '\u0000' && LA25_1 <= '!' || LA25_1 >= '#'
                              && LA25_1 <= '\uFFFF') {
                           alt25 = 1;
                        }
                     }
                     switch (alt25) {
                        case 1:
                           // CSparql.g:804:45: '\"'
                        {
                           this.match('\"');

                        }
                           break;
                        case 2:
                           // CSparql.g:804:51: '\"\"'
                        {
                           this.match("\"\"");

                        }
                           break;

                     }

                     // CSparql.g:804:59: (~ ( '\"' | '\\\\' ) | ECHAR )
                     int alt26 = 2;
                     final int LA26_0 = this.input.LA(1);

                     if (LA26_0 >= '\u0000' && LA26_0 <= '!' || LA26_0 >= '#'
                           && LA26_0 <= '[' || LA26_0 >= ']' && LA26_0 <= '\uFFFF') {
                        alt26 = 1;
                     } else if (LA26_0 == '\\') {
                        alt26 = 2;
                     } else {
                        final NoViableAltException nvae = new NoViableAltException("", 26,
                              0, this.input);

                        throw nvae;
                     }
                     switch (alt26) {
                        case 1:
                           // CSparql.g:804:61: ~ ( '\"' | '\\\\' )
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
                           // CSparql.g:804:75: ECHAR
                        {
                           this.mECHAR();

                        }
                           break;

                     }

                  }
                     break;

                  default:
                     break loop27;
               }
            } while (true);

            this.match("\"\"\"");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING_LITERAL_LONG2"

   // $ANTLR start "ECHAR"
   public final void mECHAR() throws RecognitionException {
      try {
         // CSparql.g:809:5: ( '\\\\' ( 't' | 'b' | 'n' | 'r' | 'f' | '\\\\' | '\"' | '\\'' )
         // )
         // CSparql.g:809:7: '\\\\' ( 't' | 'b' | 'n' | 'r' | 'f' | '\\\\' | '\"' | '\\'' )
         {
            this.match('\\');
            if (this.input.LA(1) == '\"' || this.input.LA(1) == '\''
                  || this.input.LA(1) == '\\' || this.input.LA(1) == 'b'
                  || this.input.LA(1) == 'f' || this.input.LA(1) == 'n'
                  || this.input.LA(1) == 'r' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

      } finally {
      }
   }

   // $ANTLR end "ECHAR"

   // $ANTLR start "PN_CHARS_U"
   public final void mPN_CHARS_U() throws RecognitionException {
      try {
         // CSparql.g:814:5: ( PN_CHARS_BASE | '_' )
         // CSparql.g:
         {
            if (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                  || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
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
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS_U"

   // $ANTLR start "VARNAME"
   public final void mVARNAME() throws RecognitionException {
      try {
         // CSparql.g:819:5: ( ( PN_CHARS_U | DIGIT ) ( PN_CHARS_U | DIGIT | '\\u00B7' |
         // '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )* )
         // CSparql.g:819:7: ( PN_CHARS_U | DIGIT ) ( PN_CHARS_U | DIGIT | '\\u00B7' |
         // '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )*
         {
            if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                  || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                  || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
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
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            // CSparql.g:819:30: ( PN_CHARS_U | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' |
            // '\\u203F' .. '\\u2040' )*
            loop28: do {
               int alt28 = 2;
               final int LA28_0 = this.input.LA(1);

               if (LA28_0 >= '0' && LA28_0 <= '9' || LA28_0 >= 'A' && LA28_0 <= 'Z'
                     || LA28_0 == '_' || LA28_0 >= 'a' && LA28_0 <= 'z'
                     || LA28_0 == '\u00B7' || LA28_0 >= '\u00C0' && LA28_0 <= '\u00D6'
                     || LA28_0 >= '\u00D8' && LA28_0 <= '\u00F6' || LA28_0 >= '\u00F8'
                     && LA28_0 <= '\u037D' || LA28_0 >= '\u037F' && LA28_0 <= '\u1FFF'
                     || LA28_0 >= '\u200C' && LA28_0 <= '\u200D' || LA28_0 >= '\u203F'
                     && LA28_0 <= '\u2040' || LA28_0 >= '\u2070' && LA28_0 <= '\u218F'
                     || LA28_0 >= '\u2C00' && LA28_0 <= '\u2FEF' || LA28_0 >= '\u3001'
                     && LA28_0 <= '\uD7FF' || LA28_0 >= '\uF900' && LA28_0 <= '\uFDCF'
                     || LA28_0 >= '\uFDF0' && LA28_0 <= '\uFFFD') {
                  alt28 = 1;
               }

               switch (alt28) {
                  case 1:
                     // CSparql.g:
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
                     break loop28;
               }
            } while (true);

         }

      } finally {
      }
   }

   // $ANTLR end "VARNAME"

   // $ANTLR start "PN_CHARS"
   public final void mPN_CHARS() throws RecognitionException {
      try {
         // CSparql.g:824:5: ( PN_CHARS_U | MINUS | DIGIT | '\\u00B7' | '\\u0300' ..
         // '\\u036F' | '\\u203F' .. '\\u2040' )
         // CSparql.g:
         {
            if (this.input.LA(1) == '-' || this.input.LA(1) >= '0'
                  && this.input.LA(1) <= '9' || this.input.LA(1) >= 'A'
                  && this.input.LA(1) <= 'Z' || this.input.LA(1) == '_'
                  || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z'
                  || this.input.LA(1) == '\u00B7' || this.input.LA(1) >= '\u00C0'
                  && this.input.LA(1) <= '\u00D6' || this.input.LA(1) >= '\u00D8'
                  && this.input.LA(1) <= '\u00F6' || this.input.LA(1) >= '\u00F8'
                  && this.input.LA(1) <= '\u037D' || this.input.LA(1) >= '\u037F'
                  && this.input.LA(1) <= '\u1FFF' || this.input.LA(1) >= '\u200C'
                  && this.input.LA(1) <= '\u200D' || this.input.LA(1) >= '\u203F'
                  && this.input.LA(1) <= '\u2040' || this.input.LA(1) >= '\u2070'
                  && this.input.LA(1) <= '\u218F' || this.input.LA(1) >= '\u2C00'
                  && this.input.LA(1) <= '\u2FEF' || this.input.LA(1) >= '\u3001'
                  && this.input.LA(1) <= '\uD7FF' || this.input.LA(1) >= '\uF900'
                  && this.input.LA(1) <= '\uFDCF' || this.input.LA(1) >= '\uFDF0'
                  && this.input.LA(1) <= '\uFFFD') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS"

   // $ANTLR start "PN_PREFIX"
   public final void mPN_PREFIX() throws RecognitionException {
      try {
         // CSparql.g:834:5: ( PN_CHARS_BASE ( ( PN_CHARS | DOT )* PN_CHARS )? )
         // CSparql.g:834:7: PN_CHARS_BASE ( ( PN_CHARS | DOT )* PN_CHARS )?
         {
            this.mPN_CHARS_BASE();
            // CSparql.g:834:21: ( ( PN_CHARS | DOT )* PN_CHARS )?
            int alt30 = 2;
            final int LA30_0 = this.input.LA(1);

            if (LA30_0 >= '-' && LA30_0 <= '.' || LA30_0 >= '0' && LA30_0 <= '9'
                  || LA30_0 >= 'A' && LA30_0 <= 'Z' || LA30_0 == '_' || LA30_0 >= 'a'
                  && LA30_0 <= 'z' || LA30_0 == '\u00B7' || LA30_0 >= '\u00C0'
                  && LA30_0 <= '\u00D6' || LA30_0 >= '\u00D8' && LA30_0 <= '\u00F6'
                  || LA30_0 >= '\u00F8' && LA30_0 <= '\u037D' || LA30_0 >= '\u037F'
                  && LA30_0 <= '\u1FFF' || LA30_0 >= '\u200C' && LA30_0 <= '\u200D'
                  || LA30_0 >= '\u203F' && LA30_0 <= '\u2040' || LA30_0 >= '\u2070'
                  && LA30_0 <= '\u218F' || LA30_0 >= '\u2C00' && LA30_0 <= '\u2FEF'
                  || LA30_0 >= '\u3001' && LA30_0 <= '\uD7FF' || LA30_0 >= '\uF900'
                  && LA30_0 <= '\uFDCF' || LA30_0 >= '\uFDF0' && LA30_0 <= '\uFFFD') {
               alt30 = 1;
            }
            switch (alt30) {
               case 1:
                  // CSparql.g:834:22: ( PN_CHARS | DOT )* PN_CHARS
               {
                  // CSparql.g:834:22: ( PN_CHARS | DOT )*
                  loop29: do {
                     int alt29 = 2;
                     final int LA29_0 = this.input.LA(1);

                     if (LA29_0 == '-' || LA29_0 >= '0' && LA29_0 <= '9' || LA29_0 >= 'A'
                           && LA29_0 <= 'Z' || LA29_0 == '_' || LA29_0 >= 'a'
                           && LA29_0 <= 'z' || LA29_0 == '\u00B7' || LA29_0 >= '\u00C0'
                           && LA29_0 <= '\u00D6' || LA29_0 >= '\u00D8' && LA29_0 <= '\u00F6'
                           || LA29_0 >= '\u00F8' && LA29_0 <= '\u037D' || LA29_0 >= '\u037F'
                           && LA29_0 <= '\u1FFF' || LA29_0 >= '\u200C' && LA29_0 <= '\u200D'
                           || LA29_0 >= '\u203F' && LA29_0 <= '\u2040' || LA29_0 >= '\u2070'
                           && LA29_0 <= '\u218F' || LA29_0 >= '\u2C00' && LA29_0 <= '\u2FEF'
                           || LA29_0 >= '\u3001' && LA29_0 <= '\uD7FF' || LA29_0 >= '\uF900'
                           && LA29_0 <= '\uFDCF' || LA29_0 >= '\uFDF0' && LA29_0 <= '\uFFFD') {
                        final int LA29_1 = this.input.LA(2);

                        if (LA29_1 >= '-' && LA29_1 <= '.' || LA29_1 >= '0' && LA29_1 <= '9'
                              || LA29_1 >= 'A' && LA29_1 <= 'Z' || LA29_1 == '_'
                              || LA29_1 >= 'a' && LA29_1 <= 'z' || LA29_1 == '\u00B7'
                              || LA29_1 >= '\u00C0' && LA29_1 <= '\u00D6'
                              || LA29_1 >= '\u00D8' && LA29_1 <= '\u00F6'
                              || LA29_1 >= '\u00F8' && LA29_1 <= '\u037D'
                              || LA29_1 >= '\u037F' && LA29_1 <= '\u1FFF'
                              || LA29_1 >= '\u200C' && LA29_1 <= '\u200D'
                              || LA29_1 >= '\u203F' && LA29_1 <= '\u2040'
                              || LA29_1 >= '\u2070' && LA29_1 <= '\u218F'
                              || LA29_1 >= '\u2C00' && LA29_1 <= '\u2FEF'
                              || LA29_1 >= '\u3001' && LA29_1 <= '\uD7FF'
                              || LA29_1 >= '\uF900' && LA29_1 <= '\uFDCF'
                              || LA29_1 >= '\uFDF0' && LA29_1 <= '\uFFFD') {
                           alt29 = 1;
                        }

                     } else if (LA29_0 == '.') {
                        alt29 = 1;
                     }

                     switch (alt29) {
                        case 1:
                           // CSparql.g:
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
                           break loop29;
                     }
                  } while (true);

                  this.mPN_CHARS();

               }
                  break;

            }

         }

      } finally {
      }
   }

   // $ANTLR end "PN_PREFIX"

   // $ANTLR start "PN_LOCAL"
   public final void mPN_LOCAL() throws RecognitionException {
      try {
         // CSparql.g:839:5: ( ( PN_CHARS_U | DIGIT ) ( ( PN_CHARS | DOT )* PN_CHARS )? )
         // CSparql.g:839:7: ( PN_CHARS_U | DIGIT ) ( ( PN_CHARS | DOT )* PN_CHARS )?
         {
            if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                  || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                  || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
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
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            // CSparql.g:839:30: ( ( PN_CHARS | DOT )* PN_CHARS )?
            int alt32 = 2;
            final int LA32_0 = this.input.LA(1);

            if (LA32_0 >= '-' && LA32_0 <= '.' || LA32_0 >= '0' && LA32_0 <= '9'
                  || LA32_0 >= 'A' && LA32_0 <= 'Z' || LA32_0 == '_' || LA32_0 >= 'a'
                  && LA32_0 <= 'z' || LA32_0 == '\u00B7' || LA32_0 >= '\u00C0'
                  && LA32_0 <= '\u00D6' || LA32_0 >= '\u00D8' && LA32_0 <= '\u00F6'
                  || LA32_0 >= '\u00F8' && LA32_0 <= '\u037D' || LA32_0 >= '\u037F'
                  && LA32_0 <= '\u1FFF' || LA32_0 >= '\u200C' && LA32_0 <= '\u200D'
                  || LA32_0 >= '\u203F' && LA32_0 <= '\u2040' || LA32_0 >= '\u2070'
                  && LA32_0 <= '\u218F' || LA32_0 >= '\u2C00' && LA32_0 <= '\u2FEF'
                  || LA32_0 >= '\u3001' && LA32_0 <= '\uD7FF' || LA32_0 >= '\uF900'
                  && LA32_0 <= '\uFDCF' || LA32_0 >= '\uFDF0' && LA32_0 <= '\uFFFD') {
               alt32 = 1;
            }
            switch (alt32) {
               case 1:
                  // CSparql.g:839:31: ( PN_CHARS | DOT )* PN_CHARS
               {
                  // CSparql.g:839:31: ( PN_CHARS | DOT )*
                  loop31: do {
                     int alt31 = 2;
                     final int LA31_0 = this.input.LA(1);

                     if (LA31_0 == '-' || LA31_0 >= '0' && LA31_0 <= '9' || LA31_0 >= 'A'
                           && LA31_0 <= 'Z' || LA31_0 == '_' || LA31_0 >= 'a'
                           && LA31_0 <= 'z' || LA31_0 == '\u00B7' || LA31_0 >= '\u00C0'
                           && LA31_0 <= '\u00D6' || LA31_0 >= '\u00D8' && LA31_0 <= '\u00F6'
                           || LA31_0 >= '\u00F8' && LA31_0 <= '\u037D' || LA31_0 >= '\u037F'
                           && LA31_0 <= '\u1FFF' || LA31_0 >= '\u200C' && LA31_0 <= '\u200D'
                           || LA31_0 >= '\u203F' && LA31_0 <= '\u2040' || LA31_0 >= '\u2070'
                           && LA31_0 <= '\u218F' || LA31_0 >= '\u2C00' && LA31_0 <= '\u2FEF'
                           || LA31_0 >= '\u3001' && LA31_0 <= '\uD7FF' || LA31_0 >= '\uF900'
                           && LA31_0 <= '\uFDCF' || LA31_0 >= '\uFDF0' && LA31_0 <= '\uFFFD') {
                        final int LA31_1 = this.input.LA(2);

                        if (LA31_1 >= '-' && LA31_1 <= '.' || LA31_1 >= '0' && LA31_1 <= '9'
                              || LA31_1 >= 'A' && LA31_1 <= 'Z' || LA31_1 == '_'
                              || LA31_1 >= 'a' && LA31_1 <= 'z' || LA31_1 == '\u00B7'
                              || LA31_1 >= '\u00C0' && LA31_1 <= '\u00D6'
                              || LA31_1 >= '\u00D8' && LA31_1 <= '\u00F6'
                              || LA31_1 >= '\u00F8' && LA31_1 <= '\u037D'
                              || LA31_1 >= '\u037F' && LA31_1 <= '\u1FFF'
                              || LA31_1 >= '\u200C' && LA31_1 <= '\u200D'
                              || LA31_1 >= '\u203F' && LA31_1 <= '\u2040'
                              || LA31_1 >= '\u2070' && LA31_1 <= '\u218F'
                              || LA31_1 >= '\u2C00' && LA31_1 <= '\u2FEF'
                              || LA31_1 >= '\u3001' && LA31_1 <= '\uD7FF'
                              || LA31_1 >= '\uF900' && LA31_1 <= '\uFDCF'
                              || LA31_1 >= '\uFDF0' && LA31_1 <= '\uFFFD') {
                           alt31 = 1;
                        }

                     } else if (LA31_0 == '.') {
                        alt31 = 1;
                     }

                     switch (alt31) {
                        case 1:
                           // CSparql.g:
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
                           break loop31;
                     }
                  } while (true);

                  this.mPN_CHARS();

               }
                  break;

            }

         }

      } finally {
      }
   }

   // $ANTLR end "PN_LOCAL"

   // $ANTLR start "PN_CHARS_BASE"
   public final void mPN_CHARS_BASE() throws RecognitionException {
      try {
         // CSparql.g:844:5: ( 'A' .. 'Z' | 'a' .. 'z' | '\\u00C0' .. '\\u00D6' | '\\u00D8'
         // .. '\\u00F6' | '\\u00F8' .. '\\u02FF' | '\\u0370' .. '\\u037D' | '\\u037F' ..
         // '\\u1FFF' | '\\u200C' .. '\\u200D' | '\\u2070' .. '\\u218F' | '\\u2C00' ..
         // '\\u2FEF' | '\\u3001' .. '\\uD7FF' | '\\uF900' .. '\\uFDCF' | '\\uFDF0' ..
         // '\\uFFFD' )
         // CSparql.g:
         {
            if (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
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
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

      } finally {
      }
   }

   // $ANTLR end "PN_CHARS_BASE"

   // $ANTLR start "DIGIT"
   public final void mDIGIT() throws RecognitionException {
      try {
         // CSparql.g:861:5: ( '0' .. '9' )
         // CSparql.g:861:7: '0' .. '9'
         {
            this.matchRange('0', '9');

         }

      } finally {
      }
   }

   // $ANTLR end "DIGIT"

   // $ANTLR start "COMMENT"
   public final void mCOMMENT() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.COMMENT;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:865:5: ( '#' ( options {greedy=false; } : . )* EOL )
         // CSparql.g:865:7: '#' ( options {greedy=false; } : . )* EOL
         {
            this.match('#');
            // CSparql.g:865:11: ( options {greedy=false; } : . )*
            loop33: do {
               int alt33 = 2;
               final int LA33_0 = this.input.LA(1);

               if (LA33_0 == '\n' || LA33_0 == '\r') {
                  alt33 = 2;
               } else if (LA33_0 >= '\u0000' && LA33_0 <= '\t' || LA33_0 >= '\u000B'
                     && LA33_0 <= '\f' || LA33_0 >= '\u000E' && LA33_0 <= '\uFFFF') {
                  alt33 = 1;
               }

               switch (alt33) {
                  case 1:
                     // CSparql.g:865:38: .
                  {
                     this.matchAny();

                  }
                     break;

                  default:
                     break loop33;
               }
            } while (true);

            this.mEOL();
            _channel = BaseRecognizer.HIDDEN;

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMMENT"

   // $ANTLR start "EOL"
   public final void mEOL() throws RecognitionException {
      try {
         // CSparql.g:870:5: ( '\\n' | '\\r' )
         // CSparql.g:
         {
            if (this.input.LA(1) == '\n' || this.input.LA(1) == '\r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

      } finally {
      }
   }

   // $ANTLR end "EOL"

   // $ANTLR start "REFERENCE"
   public final void mREFERENCE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.REFERENCE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:874:5: ( '^^' )
         // CSparql.g:874:7: '^^'
         {
            this.match("^^");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "REFERENCE"

   // $ANTLR start "LESS_EQUAL"
   public final void mLESS_EQUAL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.LESS_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:878:5: ( '<=' )
         // CSparql.g:878:7: '<='
         {
            this.match("<=");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LESS_EQUAL"

   // $ANTLR start "GREATER_EQUAL"
   public final void mGREATER_EQUAL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.GREATER_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:882:5: ( '>=' )
         // CSparql.g:882:7: '>='
         {
            this.match(">=");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GREATER_EQUAL"

   // $ANTLR start "NOT_EQUAL"
   public final void mNOT_EQUAL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.NOT_EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:886:5: ( '!=' )
         // CSparql.g:886:7: '!='
         {
            this.match("!=");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NOT_EQUAL"

   // $ANTLR start "AND"
   public final void mAND() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.AND;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:890:5: ( '&&' )
         // CSparql.g:890:7: '&&'
         {
            this.match("&&");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "AND"

   // $ANTLR start "OR"
   public final void mOR() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.OR;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:894:5: ( '||' )
         // CSparql.g:894:7: '||'
         {
            this.match("||");

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OR"

   // $ANTLR start "OPEN_BRACE"
   public final void mOPEN_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.OPEN_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:898:5: ( '(' )
         // CSparql.g:898:7: '('
         {
            this.match('(');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_BRACE"

   // $ANTLR start "CLOSE_BRACE"
   public final void mCLOSE_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.CLOSE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:902:5: ( ')' )
         // CSparql.g:902:7: ')'
         {
            this.match(')');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_BRACE"

   // $ANTLR start "OPEN_CURLY_BRACE"
   public final void mOPEN_CURLY_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.OPEN_CURLY_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:906:5: ( '{' )
         // CSparql.g:906:7: '{'
         {
            this.match('{');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_CURLY_BRACE"

   // $ANTLR start "CLOSE_CURLY_BRACE"
   public final void mCLOSE_CURLY_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.CLOSE_CURLY_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:910:5: ( '}' )
         // CSparql.g:910:7: '}'
         {
            this.match('}');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_CURLY_BRACE"

   // $ANTLR start "OPEN_SQUARE_BRACE"
   public final void mOPEN_SQUARE_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.OPEN_SQUARE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:914:5: ( '[' )
         // CSparql.g:914:7: '['
         {
            this.match('[');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "OPEN_SQUARE_BRACE"

   // $ANTLR start "CLOSE_SQUARE_BRACE"
   public final void mCLOSE_SQUARE_BRACE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.CLOSE_SQUARE_BRACE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:918:5: ( ']' )
         // CSparql.g:918:7: ']'
         {
            this.match(']');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CLOSE_SQUARE_BRACE"

   // $ANTLR start "SEMICOLON"
   public final void mSEMICOLON() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.SEMICOLON;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:922:5: ( ';' )
         // CSparql.g:922:7: ';'
         {
            this.match(';');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SEMICOLON"

   // $ANTLR start "DOT"
   public final void mDOT() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DOT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:926:5: ( '.' )
         // CSparql.g:926:7: '.'
         {
            this.match('.');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DOT"

   // $ANTLR start "PLUS"
   public final void mPLUS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.PLUS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:930:5: ( '+' )
         // CSparql.g:930:7: '+'
         {
            this.match('+');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PLUS"

   // $ANTLR start "MINUS"
   public final void mMINUS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.MINUS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:934:5: ( '-' )
         // CSparql.g:934:7: '-'
         {
            this.match('-');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "MINUS"

   // $ANTLR start "ASTERISK"
   public final void mASTERISK() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.ASTERISK;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:938:5: ( '*' )
         // CSparql.g:938:7: '*'
         {
            this.match('*');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ASTERISK"

   // $ANTLR start "COMMA"
   public final void mCOMMA() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.COMMA;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:942:5: ( ',' )
         // CSparql.g:942:7: ','
         {
            this.match(',');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMMA"

   // $ANTLR start "NOT"
   public final void mNOT() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.NOT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:946:5: ( '!' )
         // CSparql.g:946:7: '!'
         {
            this.match('!');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "NOT"

   // $ANTLR start "DIVIDE"
   public final void mDIVIDE() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.DIVIDE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:950:5: ( '/' )
         // CSparql.g:950:7: '/'
         {
            this.match('/');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DIVIDE"

   // $ANTLR start "EQUAL"
   public final void mEQUAL() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.EQUAL;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:954:5: ( '=' )
         // CSparql.g:954:7: '='
         {
            this.match('=');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "EQUAL"

   // $ANTLR start "LESS"
   public final void mLESS() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.LESS;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:958:5: ( '<' )
         // CSparql.g:958:7: '<'
         {
            this.match('<');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "LESS"

   // $ANTLR start "GREATER"
   public final void mGREATER() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.GREATER;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:962:5: ( '>' )
         // CSparql.g:962:7: '>'
         {
            this.match('>');

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "GREATER"

   // $ANTLR start "ANY"
   public final void mANY() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.ANY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:965:5: ( . )
         // CSparql.g:965:7: .
         {
            this.matchAny();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ANY"

   // $ANTLR start "QUERY_NAME"
   public final void mQUERY_NAME() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.QUERY_NAME;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:969:2: ( VARNAME )
         // CSparql.g:969:4: VARNAME
         {
            this.mVARNAME();

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "QUERY_NAME"

   // $ANTLR start "COMPUTED_EVERY"
   public final void mCOMPUTED_EVERY() throws RecognitionException {
      try {
         final int _type = CSparqlLexer.COMPUTED_EVERY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // CSparql.g:973:2: ( ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'U'
         // | 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'D' | 'd' ) WS ( 'E' | 'e' ) ( 'V' | 'v' )
         // ( 'E' | 'e' ) ( 'R' | 'r' ) ( 'Y' | 'y' ) )
         // CSparql.g:973:4: ( 'C' | 'c' ) ( 'O' | 'o' ) ( 'M' | 'm' ) ( 'P' | 'p' ) ( 'U' |
         // 'u' ) ( 'T' | 't' ) ( 'E' | 'e' ) ( 'D' | 'd' ) WS ( 'E' | 'e' ) ( 'V' | 'v' ) (
         // 'E' | 'e' ) ( 'R' | 'r' ) ( 'Y' | 'y' )
         {
            if (this.input.LA(1) == 'C' || this.input.LA(1) == 'c') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'O' || this.input.LA(1) == 'o') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'M' || this.input.LA(1) == 'm') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'P' || this.input.LA(1) == 'p') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'U' || this.input.LA(1) == 'u') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'T' || this.input.LA(1) == 't') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'D' || this.input.LA(1) == 'd') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            this.mWS();
            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'V' || this.input.LA(1) == 'v') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'E' || this.input.LA(1) == 'e') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'R' || this.input.LA(1) == 'r') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

            if (this.input.LA(1) == 'Y' || this.input.LA(1) == 'y') {
               this.input.consume();

            } else {
               final MismatchedSetException mse = new MismatchedSetException(null,
                     this.input);
               this.recover(mse);
               throw mse;
            }

         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMPUTED_EVERY"

   @Override
   public void mTokens() throws RecognitionException {
      // CSparql.g:1:8: ( WS | AS | TIME_RANGE | TIME_UNIT | STREAM | RANGE | STEP | TRIPLES
      // | TUMBLING | REGISTER | QUERY | TIMESTAMP | EXISTS | NOT_BY_WORDS | COUNT | SUM |
      // MIN | MAX | AVG | GROUP | HAVING | PNAME_NS | PNAME_LN | BASE | PREFIX | SELECT |
      // DISTINCT | REDUCED | CONSTRUCT | DESCRIBE | ASK | FROM | NAMED | WHERE | ORDER | BY
      // | ASC | DESC | LIMIT | OFFSET | OPTIONAL | GRAPH | UNION | FILTER | A | STR | LANG |
      // LANGMATCHES | DATATYPE | BOUND | SAMETERM | ISIRI | ISURI | ISBLANK | ISLITERAL |
      // REGEX | TRUE | FALSE | IRI_REF | BLANK_NODE_LABEL | VAR1 | VAR2 | LANGTAG | INTEGER
      // | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE |
      // INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 |
      // STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE
      // | LESS_EQUAL | GREATER_EQUAL | NOT_EQUAL | AND | OR | OPEN_BRACE | CLOSE_BRACE |
      // OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | OPEN_SQUARE_BRACE | CLOSE_SQUARE_BRACE |
      // SEMICOLON | DOT | PLUS | MINUS | ASTERISK | COMMA | NOT | DIVIDE | EQUAL | LESS |
      // GREATER | ANY | QUERY_NAME | COMPUTED_EVERY )
      int alt34 = 103;
      alt34 = this.dfa34.predict(this.input);
      switch (alt34) {
         case 1:
            // CSparql.g:1:10: WS
         {
            this.mWS();

         }
            break;
         case 2:
            // CSparql.g:1:13: AS
         {
            this.mAS();

         }
            break;
         case 3:
            // CSparql.g:1:16: TIME_RANGE
         {
            this.mTIME_RANGE();

         }
            break;
         case 4:
            // CSparql.g:1:27: TIME_UNIT
         {
            this.mTIME_UNIT();

         }
            break;
         case 5:
            // CSparql.g:1:37: STREAM
         {
            this.mSTREAM();

         }
            break;
         case 6:
            // CSparql.g:1:44: RANGE
         {
            this.mRANGE();

         }
            break;
         case 7:
            // CSparql.g:1:50: STEP
         {
            this.mSTEP();

         }
            break;
         case 8:
            // CSparql.g:1:55: TRIPLES
         {
            this.mTRIPLES();

         }
            break;
         case 9:
            // CSparql.g:1:63: TUMBLING
         {
            this.mTUMBLING();

         }
            break;
         case 10:
            // CSparql.g:1:72: REGISTER
         {
            this.mREGISTER();

         }
            break;
         case 11:
            // CSparql.g:1:81: QUERY
         {
            this.mQUERY();

         }
            break;
         case 12:
            // CSparql.g:1:87: TIMESTAMP
         {
            this.mTIMESTAMP();

         }
            break;
         case 13:
            // CSparql.g:1:97: EXISTS
         {
            this.mEXISTS();

         }
            break;
         case 14:
            // CSparql.g:1:104: NOT_BY_WORDS
         {
            this.mNOT_BY_WORDS();

         }
            break;
         case 15:
            // CSparql.g:1:117: COUNT
         {
            this.mCOUNT();

         }
            break;
         case 16:
            // CSparql.g:1:123: SUM
         {
            this.mSUM();

         }
            break;
         case 17:
            // CSparql.g:1:127: MIN
         {
            this.mMIN();

         }
            break;
         case 18:
            // CSparql.g:1:131: MAX
         {
            this.mMAX();

         }
            break;
         case 19:
            // CSparql.g:1:135: AVG
         {
            this.mAVG();

         }
            break;
         case 20:
            // CSparql.g:1:139: GROUP
         {
            this.mGROUP();

         }
            break;
         case 21:
            // CSparql.g:1:145: HAVING
         {
            this.mHAVING();

         }
            break;
         case 22:
            // CSparql.g:1:152: PNAME_NS
         {
            this.mPNAME_NS();

         }
            break;
         case 23:
            // CSparql.g:1:161: PNAME_LN
         {
            this.mPNAME_LN();

         }
            break;
         case 24:
            // CSparql.g:1:170: BASE
         {
            this.mBASE();

         }
            break;
         case 25:
            // CSparql.g:1:175: PREFIX
         {
            this.mPREFIX();

         }
            break;
         case 26:
            // CSparql.g:1:182: SELECT
         {
            this.mSELECT();

         }
            break;
         case 27:
            // CSparql.g:1:189: DISTINCT
         {
            this.mDISTINCT();

         }
            break;
         case 28:
            // CSparql.g:1:198: REDUCED
         {
            this.mREDUCED();

         }
            break;
         case 29:
            // CSparql.g:1:206: CONSTRUCT
         {
            this.mCONSTRUCT();

         }
            break;
         case 30:
            // CSparql.g:1:216: DESCRIBE
         {
            this.mDESCRIBE();

         }
            break;
         case 31:
            // CSparql.g:1:225: ASK
         {
            this.mASK();

         }
            break;
         case 32:
            // CSparql.g:1:229: FROM
         {
            this.mFROM();

         }
            break;
         case 33:
            // CSparql.g:1:234: NAMED
         {
            this.mNAMED();

         }
            break;
         case 34:
            // CSparql.g:1:240: WHERE
         {
            this.mWHERE();

         }
            break;
         case 35:
            // CSparql.g:1:246: ORDER
         {
            this.mORDER();

         }
            break;
         case 36:
            // CSparql.g:1:252: BY
         {
            this.mBY();

         }
            break;
         case 37:
            // CSparql.g:1:255: ASC
         {
            this.mASC();

         }
            break;
         case 38:
            // CSparql.g:1:259: DESC
         {
            this.mDESC();

         }
            break;
         case 39:
            // CSparql.g:1:264: LIMIT
         {
            this.mLIMIT();

         }
            break;
         case 40:
            // CSparql.g:1:270: OFFSET
         {
            this.mOFFSET();

         }
            break;
         case 41:
            // CSparql.g:1:277: OPTIONAL
         {
            this.mOPTIONAL();

         }
            break;
         case 42:
            // CSparql.g:1:286: GRAPH
         {
            this.mGRAPH();

         }
            break;
         case 43:
            // CSparql.g:1:292: UNION
         {
            this.mUNION();

         }
            break;
         case 44:
            // CSparql.g:1:298: FILTER
         {
            this.mFILTER();

         }
            break;
         case 45:
            // CSparql.g:1:305: A
         {
            this.mA();

         }
            break;
         case 46:
            // CSparql.g:1:307: STR
         {
            this.mSTR();

         }
            break;
         case 47:
            // CSparql.g:1:311: LANG
         {
            this.mLANG();

         }
            break;
         case 48:
            // CSparql.g:1:316: LANGMATCHES
         {
            this.mLANGMATCHES();

         }
            break;
         case 49:
            // CSparql.g:1:328: DATATYPE
         {
            this.mDATATYPE();

         }
            break;
         case 50:
            // CSparql.g:1:337: BOUND
         {
            this.mBOUND();

         }
            break;
         case 51:
            // CSparql.g:1:343: SAMETERM
         {
            this.mSAMETERM();

         }
            break;
         case 52:
            // CSparql.g:1:352: ISIRI
         {
            this.mISIRI();

         }
            break;
         case 53:
            // CSparql.g:1:358: ISURI
         {
            this.mISURI();

         }
            break;
         case 54:
            // CSparql.g:1:364: ISBLANK
         {
            this.mISBLANK();

         }
            break;
         case 55:
            // CSparql.g:1:372: ISLITERAL
         {
            this.mISLITERAL();

         }
            break;
         case 56:
            // CSparql.g:1:382: REGEX
         {
            this.mREGEX();

         }
            break;
         case 57:
            // CSparql.g:1:388: TRUE
         {
            this.mTRUE();

         }
            break;
         case 58:
            // CSparql.g:1:393: FALSE
         {
            this.mFALSE();

         }
            break;
         case 59:
            // CSparql.g:1:399: IRI_REF
         {
            this.mIRI_REF();

         }
            break;
         case 60:
            // CSparql.g:1:407: BLANK_NODE_LABEL
         {
            this.mBLANK_NODE_LABEL();

         }
            break;
         case 61:
            // CSparql.g:1:424: VAR1
         {
            this.mVAR1();

         }
            break;
         case 62:
            // CSparql.g:1:429: VAR2
         {
            this.mVAR2();

         }
            break;
         case 63:
            // CSparql.g:1:434: LANGTAG
         {
            this.mLANGTAG();

         }
            break;
         case 64:
            // CSparql.g:1:442: INTEGER
         {
            this.mINTEGER();

         }
            break;
         case 65:
            // CSparql.g:1:450: DECIMAL
         {
            this.mDECIMAL();

         }
            break;
         case 66:
            // CSparql.g:1:458: DOUBLE
         {
            this.mDOUBLE();

         }
            break;
         case 67:
            // CSparql.g:1:465: INTEGER_POSITIVE
         {
            this.mINTEGER_POSITIVE();

         }
            break;
         case 68:
            // CSparql.g:1:482: DECIMAL_POSITIVE
         {
            this.mDECIMAL_POSITIVE();

         }
            break;
         case 69:
            // CSparql.g:1:499: DOUBLE_POSITIVE
         {
            this.mDOUBLE_POSITIVE();

         }
            break;
         case 70:
            // CSparql.g:1:515: INTEGER_NEGATIVE
         {
            this.mINTEGER_NEGATIVE();

         }
            break;
         case 71:
            // CSparql.g:1:532: DECIMAL_NEGATIVE
         {
            this.mDECIMAL_NEGATIVE();

         }
            break;
         case 72:
            // CSparql.g:1:549: DOUBLE_NEGATIVE
         {
            this.mDOUBLE_NEGATIVE();

         }
            break;
         case 73:
            // CSparql.g:1:565: STRING_LITERAL1
         {
            this.mSTRING_LITERAL1();

         }
            break;
         case 74:
            // CSparql.g:1:581: STRING_LITERAL2
         {
            this.mSTRING_LITERAL2();

         }
            break;
         case 75:
            // CSparql.g:1:597: STRING_LITERAL_LONG1
         {
            this.mSTRING_LITERAL_LONG1();

         }
            break;
         case 76:
            // CSparql.g:1:618: STRING_LITERAL_LONG2
         {
            this.mSTRING_LITERAL_LONG2();

         }
            break;
         case 77:
            // CSparql.g:1:639: COMMENT
         {
            this.mCOMMENT();

         }
            break;
         case 78:
            // CSparql.g:1:647: REFERENCE
         {
            this.mREFERENCE();

         }
            break;
         case 79:
            // CSparql.g:1:657: LESS_EQUAL
         {
            this.mLESS_EQUAL();

         }
            break;
         case 80:
            // CSparql.g:1:668: GREATER_EQUAL
         {
            this.mGREATER_EQUAL();

         }
            break;
         case 81:
            // CSparql.g:1:682: NOT_EQUAL
         {
            this.mNOT_EQUAL();

         }
            break;
         case 82:
            // CSparql.g:1:692: AND
         {
            this.mAND();

         }
            break;
         case 83:
            // CSparql.g:1:696: OR
         {
            this.mOR();

         }
            break;
         case 84:
            // CSparql.g:1:699: OPEN_BRACE
         {
            this.mOPEN_BRACE();

         }
            break;
         case 85:
            // CSparql.g:1:710: CLOSE_BRACE
         {
            this.mCLOSE_BRACE();

         }
            break;
         case 86:
            // CSparql.g:1:722: OPEN_CURLY_BRACE
         {
            this.mOPEN_CURLY_BRACE();

         }
            break;
         case 87:
            // CSparql.g:1:739: CLOSE_CURLY_BRACE
         {
            this.mCLOSE_CURLY_BRACE();

         }
            break;
         case 88:
            // CSparql.g:1:757: OPEN_SQUARE_BRACE
         {
            this.mOPEN_SQUARE_BRACE();

         }
            break;
         case 89:
            // CSparql.g:1:775: CLOSE_SQUARE_BRACE
         {
            this.mCLOSE_SQUARE_BRACE();

         }
            break;
         case 90:
            // CSparql.g:1:794: SEMICOLON
         {
            this.mSEMICOLON();

         }
            break;
         case 91:
            // CSparql.g:1:804: DOT
         {
            this.mDOT();

         }
            break;
         case 92:
            // CSparql.g:1:808: PLUS
         {
            this.mPLUS();

         }
            break;
         case 93:
            // CSparql.g:1:813: MINUS
         {
            this.mMINUS();

         }
            break;
         case 94:
            // CSparql.g:1:819: ASTERISK
         {
            this.mASTERISK();

         }
            break;
         case 95:
            // CSparql.g:1:828: COMMA
         {
            this.mCOMMA();

         }
            break;
         case 96:
            // CSparql.g:1:834: NOT
         {
            this.mNOT();

         }
            break;
         case 97:
            // CSparql.g:1:838: DIVIDE
         {
            this.mDIVIDE();

         }
            break;
         case 98:
            // CSparql.g:1:845: EQUAL
         {
            this.mEQUAL();

         }
            break;
         case 99:
            // CSparql.g:1:851: LESS
         {
            this.mLESS();

         }
            break;
         case 100:
            // CSparql.g:1:856: GREATER
         {
            this.mGREATER();

         }
            break;
         case 101:
            // CSparql.g:1:864: ANY
         {
            this.mANY();

         }
            break;
         case 102:
            // CSparql.g:1:868: QUERY_NAME
         {
            this.mQUERY_NAME();

         }
            break;
         case 103:
            // CSparql.g:1:879: COMPUTED_EVERY
         {
            this.mCOMPUTED_EVERY();

         }
            break;

      }

   }

   protected DFA17 dfa17 = new DFA17(this);
   protected DFA34 dfa34 = new DFA34(this);
   static final String DFA17_eotS = "\5\uffff";
   static final String DFA17_eofS = "\5\uffff";
   static final String DFA17_minS = "\2\56\3\uffff";
   static final String DFA17_maxS = "\1\71\1\145\3\uffff";
   static final String DFA17_acceptS = "\2\uffff\1\2\1\3\1\1";
   static final String DFA17_specialS = "\5\uffff}>";
   static final String[] DFA17_transitionS = { "\1\2\1\uffff\12\1",
         "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3", "", "", "" };

   static final short[] DFA17_eot = DFA.unpackEncodedString(CSparqlLexer.DFA17_eotS);
   static final short[] DFA17_eof = DFA.unpackEncodedString(CSparqlLexer.DFA17_eofS);
   static final char[] DFA17_min = DFA
         .unpackEncodedStringToUnsignedChars(CSparqlLexer.DFA17_minS);
   static final char[] DFA17_max = DFA
         .unpackEncodedStringToUnsignedChars(CSparqlLexer.DFA17_maxS);
   static final short[] DFA17_accept = DFA.unpackEncodedString(CSparqlLexer.DFA17_acceptS);
   static final short[] DFA17_special = DFA.unpackEncodedString(CSparqlLexer.DFA17_specialS);
   static final short[][] DFA17_transition;

   static {
      final int numStates = CSparqlLexer.DFA17_transitionS.length;
      DFA17_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         CSparqlLexer.DFA17_transition[i] = DFA
               .unpackEncodedString(CSparqlLexer.DFA17_transitionS[i]);
      }
   }

   class DFA17 extends DFA {

      public DFA17(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 17;
         this.eot = CSparqlLexer.DFA17_eot;
         this.eof = CSparqlLexer.DFA17_eof;
         this.min = CSparqlLexer.DFA17_min;
         this.max = CSparqlLexer.DFA17_max;
         this.accept = CSparqlLexer.DFA17_accept;
         this.special = CSparqlLexer.DFA17_special;
         this.transition = CSparqlLexer.DFA17_transition;
      }

      @Override
      public String getDescription() {
         return "756:1: DOUBLE : ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT | ( DIGIT )+ EXPONENT );";
      }
   }

   static final String DFA34_eotS = "\2\uffff\1\76\1\102\4\116\13\71\1\145\12\71\1\164\4\71\1\172\1\174"
         + "\1\177\4\71\1\u0089\1\u008b\2\71\15\uffff\1\u009b\2\112\3\uffff"
         + "\1\145\1\uffff\4\u009e\1\102\1\112\1\u00a1\1\uffff\1\116\2\112\1"
         + "\uffff\24\112\1\u00bf\1\112\2\uffff\14\112\1\u00d0\7\uffff\1\u00a1"
         + "\1\uffff\1\u00d1\2\uffff\1\u00d5\1\uffff\1\u0083\1\uffff\1\u0085"
         + "\24\uffff\1\u00db\1\u00dc\1\uffff\1\u00dd\1\u009e\2\uffff\1\u009f"
         + "\1\uffff\1\u00a1\1\u00de\1\u00df\1\112\1\u00e2\1\112\1\u00e4\16"
         + "\112\1\u00f4\7\112\1\uffff\20\112\2\uffff\1\u010c\1\uffff\1\u010c"
         + "\1\uffff\1\u010e\1\uffff\1\u010e\7\uffff\2\112\1\uffff\1\u0112\1"
         + "\uffff\3\112\1\u0117\6\112\1\u011e\4\112\1\uffff\6\112\1\u0129\2"
         + "\112\1\u012c\7\112\1\u0135\5\112\1\uffff\1\u010c\1\uffff\1\u010e"
         + "\2\112\1\uffff\4\112\1\uffff\1\112\1\u0142\1\112\1\u0144\2\112\1"
         + "\uffff\2\112\1\u0149\1\112\1\u014b\1\u014c\2\112\1\u014f\1\u0150"
         + "\1\uffff\1\u0151\1\112\1\uffff\1\112\1\u0154\1\u0155\1\u0156\2\112"
         + "\1\u0159\1\112\1\uffff\1\u015b\1\u015c\1\u015d\2\112\1\u0160\1\u0161"
         + "\1\u0162\4\112\1\uffff\1\112\1\uffff\4\112\1\uffff\1\u016c\2\uffff"
         + "\2\112\3\uffff\1\u016f\1\u0170\3\uffff\1\u0171\1\112\1\uffff\1\112"
         + "\3\uffff\2\112\3\uffff\5\112\1\u017b\1\u017c\2\112\1\uffff\2\112"
         + "\3\uffff\2\112\1\u0183\1\112\1\u0185\1\u0186\1\u0187\1\u0188\1\u0189"
         + "\2\uffff\1\u018a\3\112\1\u018e\1\112\1\uffff\1\112\6\uffff\1\u0191"
         + "\1\u0192\2\uffff\1\112\1\u0194\2\uffff\1\112\1\uffff\1\u0196\1\uffff";
   static final String DFA34_eofS = "\u0197\uffff";
   static final String DFA34_minS = "\1\0\1\uffff\1\55\1\56\17\55\1\60\12\55\1\41\3\60\1\101\1\60\2\56"
         + "\3\0\1\136\2\75\1\46\1\174\15\uffff\3\55\1\uffff\2\55\1\60\1\uffff"
         + "\4\60\1\56\1\53\1\60\1\uffff\3\55\1\uffff\26\55\2\uffff\14\55\1"
         + "\41\7\uffff\1\60\1\uffff\1\56\1\60\1\uffff\1\56\1\60\1\47\1\uffff"
         + "\1\42\24\uffff\2\55\1\uffff\1\55\1\60\2\uffff\1\60\1\uffff\1\60"
         + "\34\55\1\uffff\20\55\2\uffff\1\60\1\uffff\1\60\1\uffff\1\60\1\uffff"
         + "\1\60\7\uffff\2\55\1\uffff\1\55\1\uffff\17\55\1\uffff\27\55\1\uffff"
         + "\1\60\1\uffff\1\60\2\55\1\uffff\4\55\1\uffff\6\55\1\uffff\12\55"
         + "\1\uffff\2\55\1\uffff\10\55\1\uffff\14\55\1\uffff\1\55\1\uffff\4"
         + "\55\1\uffff\1\55\2\uffff\2\55\3\uffff\2\55\3\uffff\2\55\1\uffff"
         + "\1\55\3\uffff\2\55\3\uffff\11\55\1\uffff\2\55\3\uffff\11\55\2\uffff"
         + "\3\55\1\11\2\55\1\uffff\1\55\6\uffff\2\55\2\uffff\2\55\2\uffff\1"
         + "\55\1\uffff\1\55\1\uffff";
   static final String DFA34_maxS = "\1\uffff\1\uffff\34\ufffd\1\uffff\4\ufffd\3\71\3\uffff\1\136\2\75"
         + "\1\46\1\174\15\uffff\3\ufffd\1\uffff\3\ufffd\1\uffff\5\ufffd\1\71"
         + "\1\145\1\uffff\3\ufffd\1\uffff\26\ufffd\2\uffff\14\ufffd\1\uffff"
         + "\7\uffff\1\145\1\uffff\1\145\1\71\1\uffff\1\145\1\71\1\47\1\uffff"
         + "\1\42\24\uffff\2\ufffd\1\uffff\2\ufffd\2\uffff\1\ufffd\1\uffff\1"
         + "\145\34\ufffd\1\uffff\20\ufffd\2\uffff\1\145\1\uffff\1\145\1\uffff"
         + "\1\145\1\uffff\1\145\7\uffff\2\ufffd\1\uffff\1\ufffd\1\uffff\17"
         + "\ufffd\1\uffff\27\ufffd\1\uffff\1\145\1\uffff\1\145\2\ufffd\1\uffff"
         + "\4\ufffd\1\uffff\6\ufffd\1\uffff\12\ufffd\1\uffff\2\ufffd\1\uffff"
         + "\10\ufffd\1\uffff\14\ufffd\1\uffff\1\ufffd\1\uffff\4\ufffd\1\uffff"
         + "\1\ufffd\2\uffff\2\ufffd\3\uffff\2\ufffd\3\uffff\2\ufffd\1\uffff"
         + "\1\ufffd\3\uffff\2\ufffd\3\uffff\11\ufffd\1\uffff\2\ufffd\3\uffff"
         + "\11\ufffd\2\uffff\6\ufffd\1\uffff\1\ufffd\6\uffff\2\ufffd\2\uffff"
         + "\2\ufffd\2\uffff\1\ufffd\1\uffff\1\ufffd\1\uffff";
   static final String DFA34_acceptS = "\1\uffff\1\1\54\uffff\1\124\1\125\1\126\1\127\1\130\1\131\1\132"
         + "\1\136\1\137\1\141\1\142\1\145\1\1\3\uffff\1\55\3\uffff\1\100\7"
         + "\uffff\1\146\3\uffff\1\4\26\uffff\1\26\1\27\15\uffff\1\143\1\73"
         + "\1\74\1\75\1\76\1\77\1\133\1\uffff\1\134\2\uffff\1\135\3\uffff\1"
         + "\111\1\uffff\1\112\1\115\1\116\1\120\1\144\1\121\1\140\1\122\1\123"
         + "\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\136\1\137\1\141\1\142"
         + "\2\uffff\1\2\2\uffff\1\3\1\102\1\uffff\1\101\35\uffff\1\44\20\uffff"
         + "\1\117\1\103\1\uffff\1\105\1\uffff\1\106\1\uffff\1\110\1\uffff\1"
         + "\113\1\114\1\37\1\45\1\23\1\21\1\22\2\uffff\1\56\1\uffff\1\20\17"
         + "\uffff\1\16\27\uffff\1\104\1\uffff\1\107\3\uffff\1\7\4\uffff\1\46"
         + "\6\uffff\1\71\12\uffff\1\30\2\uffff\1\40\10\uffff\1\57\14\uffff"
         + "\1\6\1\uffff\1\70\4\uffff\1\13\1\uffff\1\41\1\17\2\uffff\1\24\1"
         + "\52\1\62\2\uffff\1\72\1\42\1\43\2\uffff\1\47\1\uffff\1\53\1\64\1"
         + "\65\2\uffff\1\25\1\5\1\32\11\uffff\1\15\2\uffff\1\31\1\54\1\50\11"
         + "\uffff\1\34\1\10\6\uffff\1\66\1\uffff\1\63\1\33\1\36\1\61\1\12\1"
         + "\11\2\uffff\1\147\1\51\2\uffff\1\14\1\35\1\uffff\1\67\1\uffff\1" + "\60";
   static final String DFA34_specialS = "\1\3\45\uffff\1\2\1\0\1\1\u016e\uffff}>";
   static final String[] DFA34_transitionS = {
         "\11\71\2\1\2\71\1\1\22\71\1\1\1\53\1\47\1\50\1\41\1\71\1\54"
               + "\1\46\1\56\1\57\1\65\1\44\1\66\1\45\1\43\1\67\12\3\1\23\1\64"
               + "\1\36\1\70\1\52\1\40\1\42\1\34\1\22\1\16\1\25\1\14\1\26\1\20"
               + "\1\21\1\33\2\35\1\31\1\17\1\15\1\30\1\24\1\13\1\11\1\10\1\12"
               + "\1\32\1\35\1\27\3\35\1\62\1\71\1\63\1\51\1\37\1\71\1\2\1\22"
               + "\1\16\1\7\1\14\1\26\1\20\1\5\1\33\2\35\1\31\1\4\1\15\1\30\1"
               + "\24\1\13\1\11\1\6\1\12\1\32\1\35\1\27\3\35\1\60\1\55\1\61\102"
               + "\71\27\35\1\71\37\35\1\71\u0208\35\160\71\16\35\1\71\u1c81\35"
               + "\14\71\2\35\142\71\u0120\35\u0a70\71\u03f0\35\21\71\ua7ff\35"
               + "\u2100\71\u04d0\35\40\71\u020e\35\2\71",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\73\2\75\1\74"
               + "\4\75\4\uffff\1\75\1\uffff\22\75\1\73\2\75\1\74\4\75\74\uffff"
               + "\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff"
               + "\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70"
               + "\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff"
               + "\u020e\75",
         "\1\111\1\uffff\12\107\7\uffff\4\112\1\110\25\112\4\uffff\1"
               + "\112\1\uffff\3\112\1\106\1\110\2\112\1\104\4\112\1\103\5\112"
               + "\1\105\7\112\74\uffff\1\112\10\uffff\27\112\1\uffff\37\112\1"
               + "\uffff\u0286\112\1\uffff\u1c81\112\14\uffff\2\112\61\uffff\2"
               + "\112\57\uffff\u0120\112\u0a70\uffff\u03f0\112\21\uffff\ua7ff"
               + "\112\u2100\uffff\u04d0\112\40\uffff\u020e\112",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\115\7\75\1\114\21"
               + "\75\4\uffff\1\75\1\uffff\1\115\7\75\1\114\11\75\1\113\7\75\74"
               + "\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1"
               + "\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75"
               + "\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75"
               + "\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\117\31\75\4\uffff"
               + "\1\75\1\uffff\1\117\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\123\3\75\1\122\16"
               + "\75\1\120\1\121\5\75\4\uffff\1\75\1\uffff\1\123\3\75\1\122\16"
               + "\75\1\120\1\121\5\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37"
               + "\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\126\3\75\1\125\3"
               + "\75\1\124\21\75\4\uffff\1\75\1\uffff\1\126\3\75\1\125\3\75\1"
               + "\124\21\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff"
               + "\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff"
               + "\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff"
               + "\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\123\3\75\1\122\16"
               + "\75\1\120\1\121\5\75\4\uffff\1\75\1\uffff\1\123\3\75\1\122\16"
               + "\75\1\120\1\121\5\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37"
               + "\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\127\3\75\1\130\25"
               + "\75\4\uffff\1\75\1\uffff\1\127\3\75\1\130\25\75\74\uffff\1\75"
               + "\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81"
               + "\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff"
               + "\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e" + "\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\133\10\75\1"
               + "\131\2\75\1\132\5\75\4\uffff\1\75\1\uffff\10\75\1\133\10\75"
               + "\1\131\2\75\1\132\5\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\134\5\75\4"
               + "\uffff\1\75\1\uffff\24\75\1\134\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\27\75\1\135\2\75\4"
               + "\uffff\1\75\1\uffff\27\75\1\135\2\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\137\15\75\1\136\13"
               + "\75\4\uffff\1\75\1\uffff\1\137\15\75\1\136\13\75\74\uffff\1"
               + "\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81"
               + "\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff"
               + "\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e" + "\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\16\75\1\140\13\75\4"
               + "\uffff\1\75\1\uffff\16\75\1\140\13\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\115\7\75\1\114\21"
               + "\75\4\uffff\1\75\1\uffff\1\115\7\75\1\114\21\75\74\uffff\1\75"
               + "\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81"
               + "\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff"
               + "\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e" + "\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\141\10\75\4"
               + "\uffff\1\75\1\uffff\21\75\1\141\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\117\31\75\4\uffff"
               + "\1\75\1\uffff\1\117\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\142\15\75\1\144\11"
               + "\75\1\143\1\75\4\uffff\1\75\1\uffff\1\142\15\75\1\144\11\75"
               + "\1\143\1\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff"
               + "\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff"
               + "\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff"
               + "\u04d0\75\40\uffff\u020e\75",
         "\12\146\7\uffff\32\146\4\uffff\1\146\1\uffff\32\146\105\uffff"
               + "\27\146\1\uffff\37\146\1\uffff\u0208\146\160\uffff\16\146\1"
               + "\uffff\u1c81\146\14\uffff\2\146\142\uffff\u0120\146\u0a70\uffff"
               + "\u03f0\146\21\uffff\ua7ff\146\u2100\uffff\u04d0\146\40\uffff"
               + "\u020e\146",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\147\10\75\4"
               + "\uffff\1\75\1\uffff\21\75\1\147\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\126\3\75\1\125\3"
               + "\75\1\124\21\75\4\uffff\1\75\1\uffff\1\126\3\75\1\125\3\75\1"
               + "\124\21\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff"
               + "\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff"
               + "\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff"
               + "\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\152\7\75\1\151\10"
               + "\75\1\150\10\75\4\uffff\1\75\1\uffff\1\152\7\75\1\151\10\75"
               + "\1\150\10\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff"
               + "\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff"
               + "\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff"
               + "\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\7\75\1\153\22\75\4"
               + "\uffff\1\75\1\uffff\7\75\1\153\22\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\5\75\1\155\11\75\1"
               + "\156\1\75\1\154\10\75\4\uffff\1\75\1\uffff\5\75\1\155\11\75"
               + "\1\156\1\75\1\154\10\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\160\7\75\1\157\21"
               + "\75\4\uffff\1\75\1\uffff\1\160\7\75\1\157\21\75\74\uffff\1\75"
               + "\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81"
               + "\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff"
               + "\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e" + "\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\161\14\75\4"
               + "\uffff\1\75\1\uffff\15\75\1\161\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\162\7\75\4"
               + "\uffff\1\75\1\uffff\22\75\1\162\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\73\2\75\1\74"
               + "\4\75\4\uffff\1\75\1\uffff\22\75\1\73\2\75\1\74\4\75\74\uffff"
               + "\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff"
               + "\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70"
               + "\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff"
               + "\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\165\1\uffff\31\165\1\uffff\1\163\36\165\1\uffff\1\165\1"
               + "\uffff\1\165\1\uffff\32\165\3\uffff\uff82\165",
         "\12\112\1\166\6\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74"
               + "\uffff\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112"
               + "\1\uffff\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120"
               + "\112\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "\12\167\7\uffff\32\167\4\uffff\1\167\1\uffff\32\167\105\uffff"
               + "\27\167\1\uffff\37\167\1\uffff\u0208\167\160\uffff\16\167\1"
               + "\uffff\u1c81\167\14\uffff\2\167\142\uffff\u0120\167\u0a70\uffff"
               + "\u03f0\167\21\uffff\ua7ff\167\u2100\uffff\u04d0\167\40\uffff"
               + "\u020e\167",
         "\12\170\7\uffff\32\170\4\uffff\1\170\1\uffff\32\170\105\uffff"
               + "\27\170\1\uffff\37\170\1\uffff\u0208\170\160\uffff\16\170\1"
               + "\uffff\u1c81\170\14\uffff\2\170\142\uffff\u0120\170\u0a70\uffff"
               + "\u03f0\170\21\uffff\ua7ff\170\u2100\uffff\u04d0\170\40\uffff"
               + "\u020e\170",
         "\32\171\6\uffff\32\171\105\uffff\27\171\1\uffff\37\171\1\uffff"
               + "\u0208\171\160\uffff\16\171\1\uffff\u1c81\171\14\uffff\2\171"
               + "\142\uffff\u0120\171\u0a70\uffff\u03f0\171\21\uffff\ua7ff\171"
               + "\u2100\uffff\u04d0\171\40\uffff\u020e\171",
         "\12\173",
         "\1\176\1\uffff\12\175",
         "\1\u0081\1\uffff\12\u0080",
         "\12\u0083\1\uffff\2\u0083\1\uffff\31\u0083\1\u0082\uffd8\u0083",
         "\12\u0085\1\uffff\2\u0085\1\uffff\24\u0085\1\u0084\uffdd\u0085",
         "\0\u0086",
         "\1\u0087",
         "\1\u0088",
         "\1\u008a",
         "\1\u008c",
         "\1\u008d",
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
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u009a\7\75\1"
               + "\u0099\17\75\4\uffff\1\75\1\uffff\2\75\1\u009a\7\75\1\u0099"
               + "\17\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286"
               + "\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\6\75\1\u009c\23\75"
               + "\4\uffff\1\75\1\uffff\6\75\1\u009c\23\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\77\1\101\6\uffff\32\77\4\uffff\1\77"
               + "\1\uffff\32\77\74\uffff\1\77\10\uffff\27\77\1\uffff\37\77\1"
               + "\uffff\u0286\77\1\uffff\u1c81\77\14\uffff\2\77\61\uffff\2\77"
               + "\57\uffff\u0120\77\u0a70\uffff\u03f0\77\21\uffff\ua7ff\77\u2100"
               + "\uffff\u04d0\77\40\uffff\u020e\77",
         "\1\77\1\100\1\uffff\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff"
               + "\32\77\74\uffff\1\77\10\uffff\27\77\1\uffff\37\77\1\uffff\u0286"
               + "\77\1\uffff\u1c81\77\14\uffff\2\77\61\uffff\2\77\57\uffff\u0120"
               + "\77\u0a70\uffff\u03f0\77\21\uffff\ua7ff\77\u2100\uffff\u04d0"
               + "\77\40\uffff\u020e\77",
         "\12\146\7\uffff\32\146\4\uffff\1\146\1\uffff\32\146\105\uffff"
               + "\27\146\1\uffff\37\146\1\uffff\u0208\146\160\uffff\16\146\1"
               + "\uffff\u1c81\146\14\uffff\2\146\142\uffff\u0120\146\u0a70\uffff"
               + "\u03f0\146\21\uffff\ua7ff\146\u2100\uffff\u04d0\146\40\uffff"
               + "\u020e\146",
         "",
         "\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22\112\1\u009d"
               + "\7\112\74\uffff\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff"
               + "\u0286\112\1\uffff\u1c81\112\14\uffff\2\112\61\uffff\2\112\57"
               + "\uffff\u0120\112\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100"
               + "\uffff\u04d0\112\40\uffff\u020e\112",
         "\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74\uffff"
               + "\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112\1\uffff"
               + "\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120\112"
               + "\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74\uffff"
               + "\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112\1\uffff"
               + "\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120\112"
               + "\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74\uffff"
               + "\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112\1\uffff"
               + "\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120\112"
               + "\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "\1\111\1\uffff\12\107\7\uffff\4\112\1\110\25\112\4\uffff\1"
               + "\112\1\uffff\3\112\1\106\1\110\2\112\1\104\4\112\1\103\5\112"
               + "\1\105\7\112\74\uffff\1\112\10\uffff\27\112\1\uffff\37\112\1"
               + "\uffff\u0286\112\1\uffff\u1c81\112\14\uffff\2\112\61\uffff\2"
               + "\112\57\uffff\u0120\112\u0a70\uffff\u03f0\112\21\uffff\ua7ff"
               + "\112\u2100\uffff\u04d0\112\40\uffff\u020e\112",
         "\1\u009f\1\uffff\1\u009f\2\uffff\12\u00a0",
         "\12\u00a2\13\uffff\1\u009f\37\uffff\1\u009f",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u00a3\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u00a3\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\27\75\1\u00a4\2\75"
               + "\4\uffff\1\75\1\uffff\27\75\1\u00a4\2\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\25\75\1\u00a5\4\75"
               + "\4\uffff\1\75\1\uffff\25\75\1\u00a5\4\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00a7\14\75"
               + "\1\u00a6\10\75\4\uffff\1\75\1\uffff\4\75\1\u00a7\14\75\1\u00a6"
               + "\10\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286"
               + "\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00a8\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00a8\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u00a9\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u00a9\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00aa\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00aa\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u00ab\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u00ab\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u00ac\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u00ac\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u00ad\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u00ad\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u00ae\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u00ae\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u00b0\2\75\1"
               + "\u00af\23\75\4\uffff\1\75\1\uffff\3\75\1\u00b0\2\75\1\u00af"
               + "\23\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286"
               + "\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u00b1\13\75"
               + "\1\u00b2\5\75\4\uffff\1\75\1\uffff\10\75\1\u00b1\13\75\1\u00b2"
               + "\5\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286"
               + "\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00b3\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00b3\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00b4\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00b4\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00b5\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00b5\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u00b6\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u00b6\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u00b7\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u00b7\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00b8\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00b8\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00bb\1\u00ba"
               + "\6\75\1\u00b9\5\75\4\uffff\1\75\1\uffff\14\75\1\u00bb\1\u00ba"
               + "\6\75\1\u00b9\5\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75"
               + "\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2"
               + "\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75"
               + "\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u00bd\15\75\1\u00bc"
               + "\13\75\4\uffff\1\75\1\uffff\1\u00bd\15\75\1\u00bc\13\75\74\uffff"
               + "\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff"
               + "\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70"
               + "\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff"
               + "\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u00be\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u00be\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\u00c0\5\75"
               + "\4\uffff\1\75\1\uffff\24\75\1\u00c0\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00c1\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00c1\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\16\75\1\u00c2\13\75"
               + "\4\uffff\1\75\1\uffff\16\75\1\u00c2\13\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u00c3\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u00c3\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u00c4\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u00c4\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00c5\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00c5\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u00c6\26\75"
               + "\4\uffff\1\75\1\uffff\3\75\1\u00c6\26\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\5\75\1\u00c7\24\75"
               + "\4\uffff\1\75\1\uffff\5\75\1\u00c7\24\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u00c8\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u00c8\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00c9\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00c9\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u00ca\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u00ca\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u00cb\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u00cb\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\75\1\u00ce\6\75\1"
               + "\u00cc\2\75\1\u00cf\10\75\1\u00cd\5\75\4\uffff\1\75\1\uffff"
               + "\1\75\1\u00ce\6\75\1\u00cc\2\75\1\u00cf\10\75\1\u00cd\5\75\74"
               + "\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75\1"
               + "\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120\75"
               + "\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0\75"
               + "\40\uffff\u020e\75",
         "\1\165\1\uffff\31\165\1\uffff\37\165\1\uffff\1\165\1\uffff"
               + "\1\165\1\uffff\32\165\3\uffff\uff82\165",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "\12\173\13\uffff\1\u009f\37\uffff\1\u009f",
         "",
         "\1\u00d2\1\uffff\12\175\13\uffff\1\u00d3\37\uffff\1\u00d3",
         "\12\u00d4",
         "",
         "\1\u00d6\1\uffff\12\u0080\13\uffff\1\u00d7\37\uffff\1\u00d7",
         "\12\u00d8",
         "\1\u00d9",
         "",
         "\1\u00da",
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
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74\uffff"
               + "\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112\1\uffff"
               + "\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120\112"
               + "\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "",
         "",
         "\12\u00a0\7\uffff\32\112\4\uffff\1\112\1\uffff\32\112\74\uffff"
               + "\1\112\10\uffff\27\112\1\uffff\37\112\1\uffff\u0286\112\1\uffff"
               + "\u1c81\112\14\uffff\2\112\61\uffff\2\112\57\uffff\u0120\112"
               + "\u0a70\uffff\u03f0\112\21\uffff\ua7ff\112\u2100\uffff\u04d0"
               + "\112\40\uffff\u020e\112",
         "",
         "\12\u00a2\13\uffff\1\u009f\37\uffff\1\u009f",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u00e0\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u00e0\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00e1\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00e1\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u00e3\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u00e3\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00e5\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00e5\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00e6\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00e6\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u00e7\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u00e7\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u00e8\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u00e8\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u00e9\31\75\4\uffff"
               + "\1\75\1\uffff\1\u00e9\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\6\75\1\u00ea\23\75"
               + "\4\uffff\1\75\1\uffff\6\75\1\u00ea\23\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00ec\3\75\1"
               + "\u00eb\21\75\4\uffff\1\75\1\uffff\4\75\1\u00ec\3\75\1\u00eb"
               + "\21\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286"
               + "\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\u00ed\5\75"
               + "\4\uffff\1\75\1\uffff\24\75\1\u00ed\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u00ee\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u00ee\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00ef\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00ef\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\75\1\u00f0\30\75"
               + "\4\uffff\1\75\1\uffff\1\75\1\u00f0\30\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00f1\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00f1\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u00f2\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u00f2\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u00f3\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u00f3\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00f5\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00f5\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u00f6\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u00f6\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u00f7\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u00f7\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u00f8\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u00f8\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\u00f9\5\75"
               + "\4\uffff\1\75\1\uffff\24\75\1\u00f9\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u00fa\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u00fa\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u00fb\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u00fb\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u00fc\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u00fc\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\5\75\1\u00fd\24\75"
               + "\4\uffff\1\75\1\uffff\5\75\1\u00fd\24\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u00fe\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u00fe\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u00ff\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u00ff\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u0100\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u0100\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0101\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0101\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0102\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0102\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u0103\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u0103\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0104\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0104\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0105\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0105\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\6\75\1\u0106\23\75"
               + "\4\uffff\1\75\1\uffff\6\75\1\u0106\23\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\16\75\1\u0107\13\75"
               + "\4\uffff\1\75\1\uffff\16\75\1\u0107\13\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0108\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0108\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0109\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0109\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u010a\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u010a\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u010b\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u010b\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\12\u010d\13\uffff\1\u00d3\37\uffff\1\u00d3",
         "",
         "\12\u00d4\13\uffff\1\u00d3\37\uffff\1\u00d3",
         "",
         "\12\u010f\13\uffff\1\u00d7\37\uffff\1\u00d7",
         "",
         "\12\u00d8\13\uffff\1\u00d7\37\uffff\1\u00d7",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u0110\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u0110\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u0111\31\75\4\uffff"
               + "\1\75\1\uffff\1\u0111\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u0113\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u0113\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0114\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0114\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0115\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0115\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0116\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0116\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0118\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0118\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0119\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0119\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u011a\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u011a\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\27\75\1\u011b\2\75"
               + "\4\uffff\1\75\1\uffff\27\75\1\u011b\2\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u011c\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u011c\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u011d\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u011d\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u011f\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u011f\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u0120\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u0120\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\30\75\1\u0121\1\75"
               + "\4\uffff\1\75\1\uffff\30\75\1\u0121\1\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0122\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0122\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u0123\26\75"
               + "\4\uffff\1\75\1\uffff\3\75\1\u0123\26\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0124\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0124\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0125\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0125\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\u0126\5\75"
               + "\4\uffff\1\75\1\uffff\24\75\1\u0126\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u0127\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u0127\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\7\75\1\u0128\22\75"
               + "\4\uffff\1\75\1\uffff\7\75\1\u0128\22\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u012a\26\75"
               + "\4\uffff\1\75\1\uffff\3\75\1\u012a\26\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u012b\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u012b\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u012d\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u012d\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u012e\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u012e\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u012f\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u012f\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0130\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0130\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0131\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0131\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\16\75\1\u0132\13\75"
               + "\4\uffff\1\75\1\uffff\16\75\1\u0132\13\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0133\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0133\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u0134\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u0134\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u0136\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u0136\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0137\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0137\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0138\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0138\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u0139\31\75\4\uffff"
               + "\1\75\1\uffff\1\u0139\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u013a\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u013a\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\12\u010d\13\uffff\1\u00d3\37\uffff\1\u00d3",
         "",
         "\12\u010f\13\uffff\1\u00d7\37\uffff\1\u00d7",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\6\75\1\u013b\23\75"
               + "\4\uffff\1\75\1\uffff\6\75\1\u013b\23\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u013c\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u013c\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u013d\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u013d\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u013e\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u013e\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u013f\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u013f\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0140\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0140\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\30\75\1\u0141\1\75"
               + "\4\uffff\1\75\1\uffff\30\75\1\u0141\1\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0143\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0143\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0145\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0145\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0146\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0146\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\10\75\1\u0147\21\75"
               + "\4\uffff\1\75\1\uffff\10\75\1\u0147\21\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0148\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0148\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u014a\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u014a\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u014d\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u014d\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u014e\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u014e\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\27\75\1\u0152\2\75"
               + "\4\uffff\1\75\1\uffff\27\75\1\u0152\2\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0153\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0153\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0157\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0157\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u0158\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u0158\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u015a\31\75\4\uffff"
               + "\1\75\1\uffff\1\u015a\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u015e\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u015e\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u015f\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u015f\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0163\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0163\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u0164\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u0164\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\75\1\u0165\30\75"
               + "\4\uffff\1\75\1\uffff\1\75\1\u0165\30\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u0166\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u0166\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0167\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0167\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u0168\26\75"
               + "\4\uffff\1\75\1\uffff\3\75\1\u0168\26\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u0169\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u0169\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\15\75\1\u016a\14\75"
               + "\4\uffff\1\75\1\uffff\15\75\1\u016a\14\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u016b\31\75\4\uffff"
               + "\1\75\1\uffff\1\u016b\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\24\75\1\u016d\5\75"
               + "\4\uffff\1\75\1\uffff\24\75\1\u016d\5\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u016e\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u016e\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u0172\31\75\4\uffff"
               + "\1\75\1\uffff\1\u0172\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0173\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0173\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\12\75\1\u0174\17\75"
               + "\4\uffff\1\75\1\uffff\12\75\1\u0174\17\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u0175\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u0175\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u0176\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u0176\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u0177\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u0177\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0178\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0178\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0179\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0179\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\21\75\1\u017a\10\75"
               + "\4\uffff\1\75\1\uffff\21\75\1\u017a\10\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\6\75\1\u017d\23\75"
               + "\4\uffff\1\75\1\uffff\6\75\1\u017d\23\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\14\75\1\u017e\15\75"
               + "\4\uffff\1\75\1\uffff\14\75\1\u017e\15\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u017f\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u017f\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\3\75\1\u0180\26\75"
               + "\4\uffff\1\75\1\uffff\3\75\1\u0180\26\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u0181\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u0181\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\2\75\1\u0182\27\75"
               + "\4\uffff\1\75\1\uffff\2\75\1\u0182\27\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\1\u0184\31\75\4\uffff"
               + "\1\75\1\uffff\1\u0184\31\75\74\uffff\1\75\10\uffff\27\75\1\uffff"
               + "\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff"
               + "\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff"
               + "\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\17\75\1\u018b\12\75"
               + "\4\uffff\1\75\1\uffff\17\75\1\u018b\12\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\23\75\1\u018c\6\75"
               + "\4\uffff\1\75\1\uffff\23\75\1\u018c\6\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\2\u018d\2\uffff\1\u018d\22\uffff\1\u018d\14\uffff\1\77\1\100"
               + "\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75\1\uffff\32\75"
               + "\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1\uffff\u0286\75"
               + "\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75\57\uffff\u0120"
               + "\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100\uffff\u04d0"
               + "\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\7\75\1\u018f\22\75"
               + "\4\uffff\1\75\1\uffff\7\75\1\u018f\22\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\13\75\1\u0190\16\75"
               + "\4\uffff\1\75\1\uffff\13\75\1\u0190\16\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "",
         "",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\4\75\1\u0193\25\75"
               + "\4\uffff\1\75\1\uffff\4\75\1\u0193\25\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\22\75\1\u0195\7\75"
               + "\4\uffff\1\75\1\uffff\22\75\1\u0195\7\75\74\uffff\1\75\10\uffff"
               + "\27\75\1\uffff\37\75\1\uffff\u0286\75\1\uffff\u1c81\75\14\uffff"
               + "\2\75\61\uffff\2\75\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21"
               + "\uffff\ua7ff\75\u2100\uffff\u04d0\75\40\uffff\u020e\75",
         "",
         "\1\77\1\100\1\uffff\12\75\1\101\6\uffff\32\75\4\uffff\1\75"
               + "\1\uffff\32\75\74\uffff\1\75\10\uffff\27\75\1\uffff\37\75\1"
               + "\uffff\u0286\75\1\uffff\u1c81\75\14\uffff\2\75\61\uffff\2\75"
               + "\57\uffff\u0120\75\u0a70\uffff\u03f0\75\21\uffff\ua7ff\75\u2100"
               + "\uffff\u04d0\75\40\uffff\u020e\75", "" };

   static final short[] DFA34_eot = DFA.unpackEncodedString(CSparqlLexer.DFA34_eotS);
   static final short[] DFA34_eof = DFA.unpackEncodedString(CSparqlLexer.DFA34_eofS);
   static final char[] DFA34_min = DFA
         .unpackEncodedStringToUnsignedChars(CSparqlLexer.DFA34_minS);
   static final char[] DFA34_max = DFA
         .unpackEncodedStringToUnsignedChars(CSparqlLexer.DFA34_maxS);
   static final short[] DFA34_accept = DFA.unpackEncodedString(CSparqlLexer.DFA34_acceptS);
   static final short[] DFA34_special = DFA.unpackEncodedString(CSparqlLexer.DFA34_specialS);
   static final short[][] DFA34_transition;

   static {
      final int numStates = CSparqlLexer.DFA34_transitionS.length;
      DFA34_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         CSparqlLexer.DFA34_transition[i] = DFA
               .unpackEncodedString(CSparqlLexer.DFA34_transitionS[i]);
      }
   }

   class DFA34 extends DFA {

      public DFA34(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 34;
         this.eot = CSparqlLexer.DFA34_eot;
         this.eof = CSparqlLexer.DFA34_eof;
         this.min = CSparqlLexer.DFA34_min;
         this.max = CSparqlLexer.DFA34_max;
         this.accept = CSparqlLexer.DFA34_accept;
         this.special = CSparqlLexer.DFA34_special;
         this.transition = CSparqlLexer.DFA34_transition;
      }

      @Override
      public String getDescription() {
         return "1:1: Tokens : ( WS | AS | TIME_RANGE | TIME_UNIT | STREAM | RANGE | STEP | TRIPLES | TUMBLING | REGISTER | QUERY | TIMESTAMP | EXISTS | NOT_BY_WORDS | COUNT | SUM | MIN | MAX | AVG | GROUP | HAVING | PNAME_NS | PNAME_LN | BASE | PREFIX | SELECT | DISTINCT | REDUCED | CONSTRUCT | DESCRIBE | ASK | FROM | NAMED | WHERE | ORDER | BY | ASC | DESC | LIMIT | OFFSET | OPTIONAL | GRAPH | UNION | FILTER | A | STR | LANG | LANGMATCHES | DATATYPE | BOUND | SAMETERM | ISIRI | ISURI | ISBLANK | ISLITERAL | REGEX | TRUE | FALSE | IRI_REF | BLANK_NODE_LABEL | VAR1 | VAR2 | LANGTAG | INTEGER | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE | LESS_EQUAL | GREATER_EQUAL | NOT_EQUAL | AND | OR | OPEN_BRACE | CLOSE_BRACE | OPEN_CURLY_BRACE | CLOSE_CURLY_BRACE | OPEN_SQUARE_BRACE | CLOSE_SQUARE_BRACE | SEMICOLON | DOT | PLUS | MINUS | ASTERISK | COMMA | NOT | DIVIDE | EQUAL | LESS | GREATER | ANY | QUERY_NAME | COMPUTED_EVERY );";
      }

      @Override
      public int specialStateTransition(int s, final IntStream _input)
            throws NoViableAltException {
         final IntStream input = _input;
         final int _s = s;
         switch (s) {
            case 0:
               final int LA34_39 = input.LA(1);

               s = -1;
               if (LA34_39 == '\"') {
                  s = 132;
               }

               else if (LA34_39 >= '\u0000' && LA34_39 <= '\t' || LA34_39 >= '\u000B'
                     && LA34_39 <= '\f' || LA34_39 >= '\u000E' && LA34_39 <= '!'
                     || LA34_39 >= '#' && LA34_39 <= '\uFFFF') {
                  s = 133;
               } else {
                  s = 57;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 1:
               final int LA34_40 = input.LA(1);

               s = -1;
               if (LA34_40 >= '\u0000' && LA34_40 <= '\uFFFF') {
                  s = 134;
               } else {
                  s = 57;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 2:
               final int LA34_38 = input.LA(1);

               s = -1;
               if (LA34_38 == '\'') {
                  s = 130;
               }

               else if (LA34_38 >= '\u0000' && LA34_38 <= '\t' || LA34_38 >= '\u000B'
                     && LA34_38 <= '\f' || LA34_38 >= '\u000E' && LA34_38 <= '&'
                     || LA34_38 >= '(' && LA34_38 <= '\uFFFF') {
                  s = 131;
               } else {
                  s = 57;
               }

               if (s >= 0) {
                  return s;
               }
               break;
            case 3:
               final int LA34_0 = input.LA(1);

               s = -1;
               if (LA34_0 >= '\t' && LA34_0 <= '\n' || LA34_0 == '\r' || LA34_0 == ' ') {
                  s = 1;
               }

               else if (LA34_0 == 'a') {
                  s = 2;
               }

               else if (LA34_0 >= '0' && LA34_0 <= '9') {
                  s = 3;
               }

               else if (LA34_0 == 'm') {
                  s = 4;
               }

               else if (LA34_0 == 'h') {
                  s = 5;
               }

               else if (LA34_0 == 's') {
                  s = 6;
               }

               else if (LA34_0 == 'd') {
                  s = 7;
               }

               else if (LA34_0 == 'S') {
                  s = 8;
               }

               else if (LA34_0 == 'R' || LA34_0 == 'r') {
                  s = 9;
               }

               else if (LA34_0 == 'T' || LA34_0 == 't') {
                  s = 10;
               }

               else if (LA34_0 == 'Q' || LA34_0 == 'q') {
                  s = 11;
               }

               else if (LA34_0 == 'E' || LA34_0 == 'e') {
                  s = 12;
               }

               else if (LA34_0 == 'N' || LA34_0 == 'n') {
                  s = 13;
               }

               else if (LA34_0 == 'C' || LA34_0 == 'c') {
                  s = 14;
               }

               else if (LA34_0 == 'M') {
                  s = 15;
               }

               else if (LA34_0 == 'G' || LA34_0 == 'g') {
                  s = 16;
               }

               else if (LA34_0 == 'H') {
                  s = 17;
               }

               else if (LA34_0 == 'B' || LA34_0 == 'b') {
                  s = 18;
               }

               else if (LA34_0 == ':') {
                  s = 19;
               }

               else if (LA34_0 == 'P' || LA34_0 == 'p') {
                  s = 20;
               }

               else if (LA34_0 == 'D') {
                  s = 21;
               }

               else if (LA34_0 == 'F' || LA34_0 == 'f') {
                  s = 22;
               }

               else if (LA34_0 == 'W' || LA34_0 == 'w') {
                  s = 23;
               }

               else if (LA34_0 == 'O' || LA34_0 == 'o') {
                  s = 24;
               }

               else if (LA34_0 == 'L' || LA34_0 == 'l') {
                  s = 25;
               }

               else if (LA34_0 == 'U' || LA34_0 == 'u') {
                  s = 26;
               }

               else if (LA34_0 == 'I' || LA34_0 == 'i') {
                  s = 27;
               }

               else if (LA34_0 == 'A') {
                  s = 28;
               }

               else if (LA34_0 >= 'J' && LA34_0 <= 'K' || LA34_0 == 'V' || LA34_0 >= 'X'
                     && LA34_0 <= 'Z' || LA34_0 >= 'j' && LA34_0 <= 'k' || LA34_0 == 'v'
                     || LA34_0 >= 'x' && LA34_0 <= 'z' || LA34_0 >= '\u00C0'
                     && LA34_0 <= '\u00D6' || LA34_0 >= '\u00D8' && LA34_0 <= '\u00F6'
                     || LA34_0 >= '\u00F8' && LA34_0 <= '\u02FF' || LA34_0 >= '\u0370'
                     && LA34_0 <= '\u037D' || LA34_0 >= '\u037F' && LA34_0 <= '\u1FFF'
                     || LA34_0 >= '\u200C' && LA34_0 <= '\u200D' || LA34_0 >= '\u2070'
                     && LA34_0 <= '\u218F' || LA34_0 >= '\u2C00' && LA34_0 <= '\u2FEF'
                     || LA34_0 >= '\u3001' && LA34_0 <= '\uD7FF' || LA34_0 >= '\uF900'
                     && LA34_0 <= '\uFDCF' || LA34_0 >= '\uFDF0' && LA34_0 <= '\uFFFD') {
                  s = 29;
               }

               else if (LA34_0 == '<') {
                  s = 30;
               }

               else if (LA34_0 == '_') {
                  s = 31;
               }

               else if (LA34_0 == '?') {
                  s = 32;
               }

               else if (LA34_0 == '$') {
                  s = 33;
               }

               else if (LA34_0 == '@') {
                  s = 34;
               }

               else if (LA34_0 == '.') {
                  s = 35;
               }

               else if (LA34_0 == '+') {
                  s = 36;
               }

               else if (LA34_0 == '-') {
                  s = 37;
               }

               else if (LA34_0 == '\'') {
                  s = 38;
               }

               else if (LA34_0 == '\"') {
                  s = 39;
               }

               else if (LA34_0 == '#') {
                  s = 40;
               }

               else if (LA34_0 == '^') {
                  s = 41;
               }

               else if (LA34_0 == '>') {
                  s = 42;
               }

               else if (LA34_0 == '!') {
                  s = 43;
               }

               else if (LA34_0 == '&') {
                  s = 44;
               }

               else if (LA34_0 == '|') {
                  s = 45;
               }

               else if (LA34_0 == '(') {
                  s = 46;
               }

               else if (LA34_0 == ')') {
                  s = 47;
               }

               else if (LA34_0 == '{') {
                  s = 48;
               }

               else if (LA34_0 == '}') {
                  s = 49;
               }

               else if (LA34_0 == '[') {
                  s = 50;
               }

               else if (LA34_0 == ']') {
                  s = 51;
               }

               else if (LA34_0 == ';') {
                  s = 52;
               }

               else if (LA34_0 == '*') {
                  s = 53;
               }

               else if (LA34_0 == ',') {
                  s = 54;
               }

               else if (LA34_0 == '/') {
                  s = 55;
               }

               else if (LA34_0 == '=') {
                  s = 56;
               }

               else if (LA34_0 >= '\u0000' && LA34_0 <= '\b' || LA34_0 >= '\u000B'
                     && LA34_0 <= '\f' || LA34_0 >= '\u000E' && LA34_0 <= '\u001F'
                     || LA34_0 == '%' || LA34_0 == '\\' || LA34_0 == '`' || LA34_0 >= '~'
                     && LA34_0 <= '\u00BF' || LA34_0 == '\u00D7' || LA34_0 == '\u00F7'
                     || LA34_0 >= '\u0300' && LA34_0 <= '\u036F' || LA34_0 == '\u037E'
                     || LA34_0 >= '\u2000' && LA34_0 <= '\u200B' || LA34_0 >= '\u200E'
                     && LA34_0 <= '\u206F' || LA34_0 >= '\u2190' && LA34_0 <= '\u2BFF'
                     || LA34_0 >= '\u2FF0' && LA34_0 <= '\u3000' || LA34_0 >= '\uD800'
                     && LA34_0 <= '\uF8FF' || LA34_0 >= '\uFDD0' && LA34_0 <= '\uFDEF'
                     || LA34_0 >= '\uFFFE' && LA34_0 <= '\uFFFF') {
                  s = 57;
               }

               if (s >= 0) {
                  return s;
               }
               break;
         }
         final NoViableAltException nvae = new NoViableAltException(this.getDescription(),
               34, _s, input);
         this.error(nvae);
         throw nvae;
      }
   }

}
