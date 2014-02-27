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

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class SparqlMarcoLexer extends Lexer {

   public static final int PREFIX = 7;
   public static final int WHERE = 6;
   public static final int EXPONENT = 13;
   public static final int T__29 = 29;
   public static final int T__28 = 28;
   public static final int T__27 = 27;
   public static final int T__26 = 26;
   public static final int T__25 = 25;
   public static final int T__24 = 24;
   public static final int T__23 = 23;
   public static final int T__22 = 22;
   public static final int UNICODE_ESC = 20;
   public static final int OCTAL_ESC = 21;
   public static final int CHAR = 18;
   public static final int HEX_DIGIT = 19;
   public static final int INT = 11;
   public static final int FLOAT = 12;
   public static final int ORDERBY = 9;
   public static final int ID = 8;
   public static final int EOF = -1;
   public static final int DUPLICATEPARAM = 5;
   public static final int T__30 = 30;
   public static final int ORDERPARAM = 10;
   public static final int T__31 = 31;
   public static final int T__32 = 32;
   public static final int WS = 15;
   public static final int ESC_SEQ = 16;
   public static final int COMMENT = 14;
   public static final int SELECT = 4;
   public static final int STRING = 17;

   // delegates
   // delegators

   public SparqlMarcoLexer() {
      ;
   }

   public SparqlMarcoLexer(final CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public SparqlMarcoLexer(final CharStream input, final RecognizerSharedState state) {
      super(input, state);

   }

   @Override
   public String getGrammarFileName() {
      return "/Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g";
   }

   // $ANTLR start "T__22"
   public final void mT__22() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__22;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(':');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__22"

   // $ANTLR start "T__23"
   public final void mT__23() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__23;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('<');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__23"

   // $ANTLR start "T__24"
   public final void mT__24() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__24;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('>');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__24"

   // $ANTLR start "T__25"
   public final void mT__25() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__25;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('?');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__25"

   // $ANTLR start "T__26"
   public final void mT__26() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__26;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('{');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__26"

   // $ANTLR start "T__27"
   public final void mT__27() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__27;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('}');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__27"

   // $ANTLR start "T__28"
   public final void mT__28() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__28;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('.');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__28"

   // $ANTLR start "T__29"
   public final void mT__29() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__29;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('(');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__29"

   // $ANTLR start "T__30"
   public final void mT__30() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__30;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match(')');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__30"

   // $ANTLR start "T__31"
   public final void mT__31() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__31;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("LIMIT");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__31"

   // $ANTLR start "T__32"
   public final void mT__32() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.T__32;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match("OFFSET");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "T__32"

   // $ANTLR start "PREFIX"
   public final void mPREFIX() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.PREFIX;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:42:10: ( 'PREFIX' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:42:11: 'PREFIX'
         this.match("PREFIX");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "PREFIX"

   // $ANTLR start "SELECT"
   public final void mSELECT() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.SELECT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:43:11: ( 'SELECT' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:43:12: 'SELECT'
         {
            this.match("SELECT");

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "SELECT"

   // $ANTLR start "WHERE"
   public final void mWHERE() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.WHERE;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:44:10: ( 'WHERE' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:44:11: 'WHERE'
         {
            this.match("WHERE");

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "WHERE"

   // $ANTLR start "DUPLICATEPARAM"
   public final void mDUPLICATEPARAM() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.DUPLICATEPARAM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:45:19: ( 'DISTINCT' |
         // 'REDUCED' )
         int alt1 = 2;
         final int LA1_0 = this.input.LA(1);
         if (LA1_0 == 'D') {
            alt1 = 1;
         } else if (LA1_0 == 'R') {
            alt1 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 1, 0, this.input);

            throw nvae;
         }
         switch (alt1) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:45:20:
               // 'DISTINCT'
            {
               this.match("DISTINCT");

            }
               break;
            case 2:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:45:33:
               // 'REDUCED'
            {
               this.match("REDUCED");

            }
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "DUPLICATEPARAM"

   // $ANTLR start "ORDERBY"
   public final void mORDERBY() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.ORDERBY;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:46:11: ( 'ORDERBY' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:46:12: 'ORDERBY'
         {
            this.match("ORDERBY");

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ORDERBY"

   // $ANTLR start "ORDERPARAM"
   public final void mORDERPARAM() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.ORDERPARAM;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:48:4: ( 'DESC' |
         // 'ASC' )
         int alt2 = 2;
         final int LA2_0 = this.input.LA(1);
         if (LA2_0 == 'D') {
            alt2 = 1;
         } else if (LA2_0 == 'A') {
            alt2 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 2, 0, this.input);

            throw nvae;
         }
         switch (alt2) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:48:5: 'DESC'
            {
               this.match("DESC");

            }
               break;
            case 2:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:48:12: 'ASC'
            {
               this.match("ASC");

            }
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ORDERPARAM"

   // $ANTLR start "ID"
   public final void mID() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.ID;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z' || this.input.LA(1) == '_'
               || this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:51:31: ( 'a' .. 'z' |
         // 'A' .. 'Z' | '0' .. '9' | '_' )*
         loop3: do {
            int alt3 = 2;
            final int LA3_0 = this.input.LA(1);

            if (LA3_0 >= '0' && LA3_0 <= '9' || LA3_0 >= 'A' && LA3_0 <= 'Z' || LA3_0 == '_'
                  || LA3_0 >= 'a' && LA3_0 <= 'z') {
               alt3 = 1;
            }

            switch (alt3) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:
               {
                  if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9'
                        || this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z'
                        || this.input.LA(1) == '_' || this.input.LA(1) >= 'a'
                        && this.input.LA(1) <= 'z') {
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
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "ID"

   // $ANTLR start "INT"
   public final void mINT() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.INT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:54:7: ( '0' .. '9' )+
         int cnt4 = 0;
         loop4: do {
            int alt4 = 2;
            final int LA4_0 = this.input.LA(1);

            if (LA4_0 >= '0' && LA4_0 <= '9') {
               alt4 = 1;
            }

            switch (alt4) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:54:7: '0' ..
                  // '9'
               {
                  this.matchRange('0', '9');

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
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "INT"

   // $ANTLR start "FLOAT"
   public final void mFLOAT() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.FLOAT;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:5: ( ( '0' .. '9' )+
         // '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' ..
         // '9' )+ EXPONENT )
         int alt11 = 3;
         alt11 = this.dfa11.predict(this.input);
         switch (alt11) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:9: ( '0' .. '9'
               // )+
               int cnt5 = 0;
               loop5: do {
                  int alt5 = 2;
                  final int LA5_0 = this.input.LA(1);

                  if (LA5_0 >= '0' && LA5_0 <= '9') {
                     alt5 = 1;
                  }

                  switch (alt5) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:10:
                        // '0' .. '9'
                     {
                        this.matchRange('0', '9');

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
               this.match('.');
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:25: ( '0' ..
               // '9' )*
               loop6: do {
                  int alt6 = 2;
                  final int LA6_0 = this.input.LA(1);

                  if (LA6_0 >= '0' && LA6_0 <= '9') {
                     alt6 = 1;
                  }

                  switch (alt6) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:26:
                        // '0' .. '9'
                     {
                        this.matchRange('0', '9');

                     }
                        break;

                     default:
                        break loop6;
                  }
               } while (true);
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:37: ( EXPONENT
               // )?
               int alt7 = 2;
               final int LA7_0 = this.input.LA(1);
               if (LA7_0 == 'E' || LA7_0 == 'e') {
                  alt7 = 1;
               }
               switch (alt7) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:58:37:
                     // EXPONENT
                  {
                     this.mEXPONENT();

                  }
                     break;

               }
               break;
            case 2:
               this.match('.');
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:59:13: ( '0' ..
               // '9' )+
               int cnt8 = 0;
               loop8: do {
                  int alt8 = 2;
                  final int LA8_0 = this.input.LA(1);

                  if (LA8_0 >= '0' && LA8_0 <= '9') {
                     alt8 = 1;
                  }

                  switch (alt8) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:59:14:
                        // '0' .. '9'
                     {
                        this.matchRange('0', '9');

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
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:59:25: ( EXPONENT
               // )?
               int alt9 = 2;
               final int LA9_0 = this.input.LA(1);
               if (LA9_0 == 'E' || LA9_0 == 'e') {
                  alt9 = 1;
               }
               switch (alt9) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:59:25:
                     // EXPONENT
                  {
                     this.mEXPONENT();

                  }
                     break;

               }
               break;
            case 3:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:60:9: ( '0' .. '9'
               // )+
               int cnt10 = 0;
               loop10: do {
                  int alt10 = 2;
                  final int LA10_0 = this.input.LA(1);

                  if (LA10_0 >= '0' && LA10_0 <= '9') {
                     alt10 = 1;
                  }

                  switch (alt10) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:60:10:
                        // '0' .. '9'
                     {
                        this.matchRange('0', '9');

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

   // $ANTLR end "FLOAT"

   // $ANTLR start "COMMENT"
   public final void mCOMMENT() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.COMMENT;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:64:5: ( '//' (~ ( '\\n'
         // | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
         int alt15 = 2;
         final int LA15_0 = this.input.LA(1);

         if (LA15_0 == '/') {
            final int LA15_1 = this.input.LA(2);

            if (LA15_1 == '/') {
               alt15 = 1;
            } else if (LA15_1 == '*') {
               alt15 = 2;
            } else {
               final NoViableAltException nvae = new NoViableAltException("", 15, 1,
                     this.input);

               throw nvae;
            }
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);

            throw nvae;
         }
         switch (alt15) {
            case 1:
               this.match("//");
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:64:14: (~ ( '\\n'
               // | '\\r' ) )*
               loop12: do {
                  int alt12 = 2;
                  final int LA12_0 = this.input.LA(1);

                  if (LA12_0 >= '\u0000' && LA12_0 <= '\t' || LA12_0 >= '\u000B'
                        && LA12_0 <= '\f' || LA12_0 >= '\u000E' && LA12_0 <= '\uFFFF') {
                     alt12 = 1;
                  }

                  switch (alt12) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:64:14: ~
                        // ( '\\n' | '\\r' )
                     {
                        if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '\t'
                              || this.input.LA(1) >= '\u000B' && this.input.LA(1) <= '\f'
                              || this.input.LA(1) >= '\u000E'
                              && this.input.LA(1) <= '\uFFFF') {
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
                        break loop12;
                  }
               } while (true);
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:64:28: ( '\\r' )?
               int alt13 = 2;
               final int LA13_0 = this.input.LA(1);
               if (LA13_0 == '\r') {
                  alt13 = 1;
               }
               switch (alt13) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:64:28: '\\r'
                  {
                     this.match('\r');

                  }
                     break;

               }
               this.match('\n');
               _channel = BaseRecognizer.HIDDEN;
               break;
            case 2:
               this.match("/*");
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:65:14: ( options
               // {greedy=false; } : . )*
               loop14: do {
                  int alt14 = 2;
                  final int LA14_0 = this.input.LA(1);

                  if (LA14_0 == '*') {
                     final int LA14_1 = this.input.LA(2);

                     if (LA14_1 == '/') {
                        alt14 = 2;
                     } else if (LA14_1 >= '\u0000' && LA14_1 <= '.' || LA14_1 >= '0'
                           && LA14_1 <= '\uFFFF') {
                        alt14 = 1;
                     }

                  } else if (LA14_0 >= '\u0000' && LA14_0 <= ')' || LA14_0 >= '+'
                        && LA14_0 <= '\uFFFF') {
                     alt14 = 1;
                  }

                  switch (alt14) {
                     case 1:
                        // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:65:42: .
                     {
                        this.matchAny();

                     }
                        break;

                     default:
                        break loop14;
                  }
               } while (true);
               this.match("*/");
               _channel = BaseRecognizer.HIDDEN;
               break;
            default:
               break;

         }
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "COMMENT"

   // $ANTLR start "WS"
   public final void mWS() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.WS;
         int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         if (this.input.LA(1) >= '\t' && this.input.LA(1) <= '\n'
               || this.input.LA(1) == '\r' || this.input.LA(1) == ' ') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }
         _channel = BaseRecognizer.HIDDEN;
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "WS"

   // $ANTLR start "STRING"
   public final void mSTRING() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.STRING;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('\"');
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:76:12: ( ESC_SEQ | ~
         // ( '\\\\' | '\"' ) )*
         loop16: do {
            int alt16 = 3;
            final int LA16_0 = this.input.LA(1);

            if (LA16_0 == '\\') {
               alt16 = 1;
            } else if (LA16_0 >= '\u0000' && LA16_0 <= '!' || LA16_0 >= '#' && LA16_0 <= '['
                  || LA16_0 >= ']' && LA16_0 <= '\uFFFF') {
               alt16 = 2;
            }

            switch (alt16) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:76:14:
                  // ESC_SEQ
               {
                  this.mESC_SEQ();

               }
                  break;
               case 2:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:76:24: ~ (
                  // '\\\\' | '\"' )
               {
                  if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '!'
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

               default:
                  break loop16;
            }
         } while (true);
         this.match('\"');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "STRING"

   // $ANTLR start "CHAR"
   public final void mCHAR() throws RecognitionException {
      try {
         final int _type = SparqlMarcoLexer.CHAR;
         final int _channel = BaseRecognizer.DEFAULT_TOKEN_CHANNEL;
         this.match('\'');
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:79:13: ( ESC_SEQ | ~
         // ( '\\'' | '\\\\' ) )
         int alt17 = 2;
         final int LA17_0 = this.input.LA(1);
         if (LA17_0 == '\\') {
            alt17 = 1;
         } else if (LA17_0 >= '\u0000' && LA17_0 <= '&' || LA17_0 >= '(' && LA17_0 <= '['
               || LA17_0 >= ']' && LA17_0 <= '\uFFFF') {
            alt17 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 17, 0, this.input);

            throw nvae;
         }
         switch (alt17) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:79:15: ESC_SEQ
            {
               this.mESC_SEQ();

            }
               break;
            case 2:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:79:25: ~ (
               // '\\'' | '\\\\' )
            {
               if (this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '&'
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

         }
         this.match('\'');
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
      }
   }

   // $ANTLR end "CHAR"

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
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:83:22: ( '+' | '-' )?
         int alt18 = 2;
         final int LA18_0 = this.input.LA(1);
         if (LA18_0 == '+' || LA18_0 == '-') {
            alt18 = 1;
         }
         switch (alt18) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:
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
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:83:33: ( '0' .. '9'
         // )+
         int cnt19 = 0;
         loop19: do {
            int alt19 = 2;
            final int LA19_0 = this.input.LA(1);

            if (LA19_0 >= '0' && LA19_0 <= '9') {
               alt19 = 1;
            }

            switch (alt19) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:83:34: '0'
                  // .. '9'
               {
                  this.matchRange('0', '9');

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

      } finally {
      }
   }

   // $ANTLR end "EXPONENT"

   // $ANTLR start "HEX_DIGIT"
   public final void mHEX_DIGIT() throws RecognitionException {
      try {
         if (this.input.LA(1) >= '0' && this.input.LA(1) <= '9' || this.input.LA(1) >= 'A'
               && this.input.LA(1) <= 'F' || this.input.LA(1) >= 'a'
               && this.input.LA(1) <= 'f') {
            this.input.consume();

         } else {
            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
            this.recover(mse);
            throw mse;
         }

      } finally {
      }
   }

   // $ANTLR end "HEX_DIGIT"

   // $ANTLR start "ESC_SEQ"
   public final void mESC_SEQ() throws RecognitionException {
      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:90:5: ( '\\\\' ( 'b' |
         // 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UNICODE_ESC | OCTAL_ESC )
         int alt20 = 3;
         final int LA20_0 = this.input.LA(1);

         if (LA20_0 == '\\') {
            switch (this.input.LA(2)) {
               case '\"':
               case '\'':
               case '\\':
               case 'b':
               case 'f':
               case 'n':
               case 'r':
               case 't':
                  alt20 = 1;
                  break;
               case 'u':
                  alt20 = 2;
                  break;
               case '0':
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
                  alt20 = 3;
                  break;
               default:
                  final NoViableAltException nvae = new NoViableAltException("", 20, 1,
                        this.input);

                  throw nvae;
            }

         } else {
            final NoViableAltException nvae = new NoViableAltException("", 20, 0, this.input);

            throw nvae;
         }
         switch (alt20) {
            case 1:
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
               break;
            case 2:
               this.mUNICODE_ESC();
               break;
            case 3:
               this.mOCTAL_ESC();
               break;
            default:
               break;

         }
      } finally {
      }
   }

   // $ANTLR end "ESC_SEQ"

   // $ANTLR start "OCTAL_ESC"
   public final void mOCTAL_ESC() throws RecognitionException {
      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:5: ( '\\\\' ( '0' ..
         // '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) |
         // '\\\\' ( '0' .. '7' ) )
         int alt21 = 3;
         final int LA21_0 = this.input.LA(1);

         if (LA21_0 == '\\') {
            final int LA21_1 = this.input.LA(2);

            if (LA21_1 >= '0' && LA21_1 <= '3') {
               final int LA21_2 = this.input.LA(3);

               if (LA21_2 >= '0' && LA21_2 <= '7') {
                  final int LA21_5 = this.input.LA(4);

                  if (LA21_5 >= '0' && LA21_5 <= '7') {
                     alt21 = 1;
                  } else {
                     alt21 = 2;
                  }
               } else {
                  alt21 = 3;
               }
            } else if (LA21_1 >= '4' && LA21_1 <= '7') {
               final int LA21_3 = this.input.LA(3);

               if (LA21_3 >= '0' && LA21_3 <= '7') {
                  alt21 = 2;
               } else {
                  alt21 = 3;
               }
            } else {
               final NoViableAltException nvae = new NoViableAltException("", 21, 1,
                     this.input);

               throw nvae;
            }
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 21, 0, this.input);

            throw nvae;
         }
         switch (alt21) {
            case 1:
               this.match('\\');
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:14: ( '0' ..
               // '3' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:15: '0' .. '3'
               {
                  this.matchRange('0', '3');

               }
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:25: ( '0' ..
               // '7' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:26: '0' .. '7'
               {
                  this.matchRange('0', '7');

               }
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:36: ( '0' ..
               // '7' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:97:37: '0' .. '7'
               {
                  this.matchRange('0', '7');

               }
               break;
            case 2:
               this.match('\\');
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:98:14: ( '0' ..
               // '7' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:98:15: '0' .. '7'
               {
                  this.matchRange('0', '7');

               }
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:98:25: ( '0' ..
               // '7' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:98:26: '0' .. '7'
               {
                  this.matchRange('0', '7');

               }
               break;
            case 3:
               this.match('\\');
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:99:14: ( '0' ..
               // '7' )
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:99:15: '0' .. '7'
               {
                  this.matchRange('0', '7');

               }
               break;
            default:
               break;

         }
      } finally {
      }
   }

   // $ANTLR end "OCTAL_ESC"

   // $ANTLR start "UNICODE_ESC"
   public final void mUNICODE_ESC() throws RecognitionException {
      try {
         this.match('\\');
         this.match('u');
         this.mHEX_DIGIT();
         this.mHEX_DIGIT();
         this.mHEX_DIGIT();
         this.mHEX_DIGIT();

      } finally {
      }
   }

   // $ANTLR end "UNICODE_ESC"

   @Override
   public void mTokens() throws RecognitionException {
      // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:1:8: ( T__22 | T__23 |
      // T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | PREFIX |
      // SELECT | WHERE | DUPLICATEPARAM | ORDERBY | ORDERPARAM | ID | INT | FLOAT | COMMENT
      // | WS | STRING | CHAR )
      int alt22 = 24;
      alt22 = this.dfa22.predict(this.input);
      switch (alt22) {
         case 1:
            this.mT__22();
            break;
         case 2:
            this.mT__23();
            break;
         case 3:
            this.mT__24();
            break;
         case 4:
            this.mT__25();
            break;
         case 5:
            this.mT__26();
            break;
         case 6:
            this.mT__27();
            break;
         case 7:
            this.mT__28();
            break;
         case 8:
            this.mT__29();
            break;
         case 9:
            this.mT__30();
            break;
         case 10:
            this.mT__31();
            break;
         case 11:
            this.mT__32();
            break;
         case 12:
            this.mPREFIX();
            break;
         case 13:
            this.mSELECT();
            break;
         case 14:
            this.mWHERE();
            break;
         case 15:
            this.mDUPLICATEPARAM();
            break;
         case 16:
            this.mORDERBY();
            break;
         case 17:
            this.mORDERPARAM();
            break;
         case 18:
            this.mID();
            break;
         case 19:
            this.mINT();
            break;
         case 20:
            this.mFLOAT();
            break;
         case 21:
            this.mCOMMENT();
            break;
         case 22:
            this.mWS();
            break;
         case 23:
            this.mSTRING();
            break;
         case 24:
            this.mCHAR();
            break;
         default:
            break;

      }

   }

   protected DFA11 dfa11 = new DFA11(this);
   protected DFA22 dfa22 = new DFA22(this);
   static final String DFA11_eotS = "\5\uffff";
   static final String DFA11_eofS = "\5\uffff";
   static final String DFA11_minS = "\2\56\3\uffff";
   static final String DFA11_maxS = "\1\71\1\145\3\uffff";
   static final String DFA11_acceptS = "\2\uffff\1\2\1\3\1\1";
   static final String DFA11_specialS = "\5\uffff}>";
   static final String[] DFA11_transitionS = { "\1\2\1\uffff\12\1",
         "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3", "", "", "" };

   static final short[] DFA11_eot = DFA.unpackEncodedString(SparqlMarcoLexer.DFA11_eotS);
   static final short[] DFA11_eof = DFA.unpackEncodedString(SparqlMarcoLexer.DFA11_eofS);
   static final char[] DFA11_min = DFA
         .unpackEncodedStringToUnsignedChars(SparqlMarcoLexer.DFA11_minS);
   static final char[] DFA11_max = DFA
         .unpackEncodedStringToUnsignedChars(SparqlMarcoLexer.DFA11_maxS);
   static final short[] DFA11_accept = DFA
         .unpackEncodedString(SparqlMarcoLexer.DFA11_acceptS);
   static final short[] DFA11_special = DFA
         .unpackEncodedString(SparqlMarcoLexer.DFA11_specialS);
   static final short[][] DFA11_transition;

   static {
      final int numStates = SparqlMarcoLexer.DFA11_transitionS.length;
      DFA11_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         SparqlMarcoLexer.DFA11_transition[i] = DFA
               .unpackEncodedString(SparqlMarcoLexer.DFA11_transitionS[i]);
      }
   }

   class DFA11 extends DFA {

      public DFA11(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 11;
         this.eot = SparqlMarcoLexer.DFA11_eot;
         this.eof = SparqlMarcoLexer.DFA11_eof;
         this.min = SparqlMarcoLexer.DFA11_min;
         this.max = SparqlMarcoLexer.DFA11_max;
         this.accept = SparqlMarcoLexer.DFA11_accept;
         this.special = SparqlMarcoLexer.DFA11_special;
         this.transition = SparqlMarcoLexer.DFA11_transition;
      }

      @Override
      public String getDescription() {
         return "57:1: FLOAT : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( EXPONENT )? | '.' ( '0' .. '9' )+ ( EXPONENT )? | ( '0' .. '9' )+ EXPONENT );";
      }
   }

   static final String DFA22_eotS = "\7\uffff\1\31\2\uffff\10\22\1\uffff\1\44\6\uffff\12\22\1\uffff\11"
         + "\22\1\70\7\22\1\70\1\22\1\uffff\1\101\4\22\1\106\2\22\1\uffff\1"
         + "\111\1\22\1\113\1\114\1\uffff\2\22\1\uffff\1\117\2\uffff\1\22\1"
         + "\121\1\uffff\1\121\1\uffff";
   static final String DFA22_eofS = "\122\uffff";
   static final String DFA22_minS = "\1\11\6\uffff\1\60\2\uffff\1\111\1\106\1\122\1\105\1\110\2\105\1"
         + "\123\1\uffff\1\56\6\uffff\1\115\1\106\1\104\1\105\1\114\1\105\2"
         + "\123\1\104\1\103\1\uffff\1\111\1\123\1\105\1\106\1\105\1\122\1\124"
         + "\1\103\1\125\1\60\1\124\1\105\1\122\1\111\1\103\1\105\1\111\1\60"
         + "\1\103\1\uffff\1\60\1\124\1\102\1\130\1\124\1\60\1\116\1\105\1\uffff"
         + "\1\60\1\131\2\60\1\uffff\1\103\1\104\1\uffff\1\60\2\uffff\1\124"
         + "\1\60\1\uffff\1\60\1\uffff";
   static final String DFA22_maxS = "\1\175\6\uffff\1\71\2\uffff\1\111\2\122\1\105\1\110\1\111\1\105"
         + "\1\123\1\uffff\1\145\6\uffff\1\115\1\106\1\104\1\105\1\114\1\105"
         + "\2\123\1\104\1\103\1\uffff\1\111\1\123\1\105\1\106\1\105\1\122\1"
         + "\124\1\103\1\125\1\172\1\124\1\105\1\122\1\111\1\103\1\105\1\111"
         + "\1\172\1\103\1\uffff\1\172\1\124\1\102\1\130\1\124\1\172\1\116\1"
         + "\105\1\uffff\1\172\1\131\2\172\1\uffff\1\103\1\104\1\uffff\1\172"
         + "\2\uffff\1\124\1\172\1\uffff\1\172\1\uffff";
   static final String DFA22_acceptS = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\11\10\uffff\1\22"
         + "\1\uffff\1\25\1\26\1\27\1\30\1\24\1\7\12\uffff\1\23\23\uffff\1\21"
         + "\10\uffff\1\12\4\uffff\1\16\2\uffff\1\13\1\uffff\1\14\1\15\2\uffff"
         + "\1\20\1\uffff\1\17";
   static final String DFA22_specialS = "\122\uffff}>";
   static final String[] DFA22_transitionS = {
         "\2\25\2\uffff\1\25\22\uffff\1\25\1\uffff\1\26\4\uffff\1\27\1"
               + "\10\1\11\4\uffff\1\7\1\24\12\23\1\1\1\uffff\1\2\1\uffff\1\3"
               + "\1\4\1\uffff\1\21\2\22\1\17\7\22\1\12\2\22\1\13\1\14\1\22\1"
               + "\20\1\15\3\22\1\16\3\22\4\uffff\1\22\1\uffff\32\22\1\5\1\uffff" + "\1\6",
         "", "", "", "", "", "", "\12\30", "", "", "\1\32", "\1\33\13\uffff\1\34", "\1\35",
         "\1\36", "\1\37", "\1\41\3\uffff\1\40", "\1\42", "\1\43", "",
         "\1\30\1\uffff\12\23\13\uffff\1\30\37\uffff\1\30", "", "", "", "", "", "", "\1\45",
         "\1\46", "\1\47", "\1\50", "\1\51", "\1\52", "\1\53", "\1\54", "\1\55", "\1\56",
         "", "\1\57", "\1\60", "\1\61", "\1\62", "\1\63", "\1\64", "\1\65", "\1\66",
         "\1\67", "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "\1\71", "\1\72",
         "\1\73", "\1\74", "\1\75", "\1\76", "\1\77",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "\1\100", "",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "\1\102", "\1\103", "\1\104",
         "\1\105", "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "\1\107", "\1\110",
         "", "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "\1\112",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "", "\1\115", "\1\116", "",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "", "", "\1\120",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "",
         "\12\22\7\uffff\32\22\4\uffff\1\22\1\uffff\32\22", "" };

   static final short[] DFA22_eot = DFA.unpackEncodedString(SparqlMarcoLexer.DFA22_eotS);
   static final short[] DFA22_eof = DFA.unpackEncodedString(SparqlMarcoLexer.DFA22_eofS);
   static final char[] DFA22_min = DFA
         .unpackEncodedStringToUnsignedChars(SparqlMarcoLexer.DFA22_minS);
   static final char[] DFA22_max = DFA
         .unpackEncodedStringToUnsignedChars(SparqlMarcoLexer.DFA22_maxS);
   static final short[] DFA22_accept = DFA
         .unpackEncodedString(SparqlMarcoLexer.DFA22_acceptS);
   static final short[] DFA22_special = DFA
         .unpackEncodedString(SparqlMarcoLexer.DFA22_specialS);
   static final short[][] DFA22_transition;

   static {
      final int numStates = SparqlMarcoLexer.DFA22_transitionS.length;
      DFA22_transition = new short[numStates][];
      for (int i = 0; i < numStates; i++) {
         SparqlMarcoLexer.DFA22_transition[i] = DFA
               .unpackEncodedString(SparqlMarcoLexer.DFA22_transitionS[i]);
      }
   }

   class DFA22 extends DFA {

      public DFA22(final BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 22;
         this.eot = SparqlMarcoLexer.DFA22_eot;
         this.eof = SparqlMarcoLexer.DFA22_eof;
         this.min = SparqlMarcoLexer.DFA22_min;
         this.max = SparqlMarcoLexer.DFA22_max;
         this.accept = SparqlMarcoLexer.DFA22_accept;
         this.special = SparqlMarcoLexer.DFA22_special;
         this.transition = SparqlMarcoLexer.DFA22_transition;
      }

      @Override
      public String getDescription() {
         return "1:1: Tokens : ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | PREFIX | SELECT | WHERE | DUPLICATEPARAM | ORDERBY | ORDERPARAM | ID | INT | FLOAT | COMMENT | WS | STRING | CHAR );";
      }
   }

}
