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

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class SparqlMarcoParser extends Parser {

   public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>",
         "<UP>", "SELECT", "DUPLICATEPARAM", "WHERE", "PREFIX", "ID", "ORDERBY",
         "ORDERPARAM", "INT", "FLOAT", "EXPONENT", "COMMENT", "WS", "ESC_SEQ", "STRING",
         "CHAR", "HEX_DIGIT", "UNICODE_ESC", "OCTAL_ESC", "':'", "'<'", "'>'", "'?'", "'{'",
         "'}'", "'.'", "'('", "')'", "'LIMIT'", "'OFFSET'" };
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
   public static final int FLOAT = 12;
   public static final int INT = 11;
   public static final int ORDERBY = 9;
   public static final int ID = 8;
   public static final int EOF = -1;
   public static final int DUPLICATEPARAM = 5;
   public static final int ORDERPARAM = 10;
   public static final int T__30 = 30;
   public static final int T__31 = 31;
   public static final int T__32 = 32;
   public static final int ESC_SEQ = 16;
   public static final int WS = 15;
   public static final int COMMENT = 14;
   public static final int SELECT = 4;
   public static final int STRING = 17;

   // delegates
   // delegators

   public SparqlMarcoParser(final TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   public SparqlMarcoParser(final TokenStream input, final RecognizerSharedState state) {
      super(input, state);

   }

   protected TreeAdaptor adaptor = new CommonTreeAdaptor();

   public void setTreeAdaptor(final TreeAdaptor adaptor) {
      this.adaptor = adaptor;
   }

   public TreeAdaptor getTreeAdaptor() {
      return this.adaptor;
   }

   @Override
   public String[] getTokenNames() {
      return SparqlMarcoParser.tokenNames;
   }

   @Override
   public String getGrammarFileName() {
      return "/Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g";
   }

   public static class query_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "query"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:1: query : ( select |
   // construct | ask | describe EOF );
   public final SparqlMarcoParser.query_return query() throws RecognitionException {
      final SparqlMarcoParser.query_return retval = new SparqlMarcoParser.query_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token EOF5 = null;
      SparqlMarcoParser.select_return select1 = null;

      SparqlMarcoParser.construct_return construct2 = null;

      SparqlMarcoParser.ask_return ask3 = null;

      SparqlMarcoParser.describe_return describe4 = null;

      Object EOF5_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:8: ( select |
         // construct | ask | describe EOF )
         int alt1 = 4;
         switch (this.input.LA(1)) {
            case SELECT:
            case PREFIX: {
               alt1 = 1;
            }
               break;
            case ID: {
               alt1 = 2;
            }
               break;
            case INT: {
               alt1 = 3;
            }
               break;
            case FLOAT: {
               alt1 = 4;
            }
               break;
            default:
               final NoViableAltException nvae = new NoViableAltException("", 1, 0,
                     this.input);

               throw nvae;
         }

         switch (alt1) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:10: select
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_select_in_query27);
               select1 = this.select();

               this.state._fsp--;

               this.adaptor.addChild(root_0, select1.getTree());

            }
               break;
            case 2:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:19: construct
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_construct_in_query31);
               construct2 = this.construct();

               this.state._fsp--;

               this.adaptor.addChild(root_0, construct2.getTree());

            }
               break;
            case 3:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:31: ask
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_ask_in_query35);
               ask3 = this.ask();

               this.state._fsp--;

               this.adaptor.addChild(root_0, ask3.getTree());

            }
               break;
            case 4:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:9:37: describe EOF
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_describe_in_query39);
               describe4 = this.describe();

               this.state._fsp--;

               this.adaptor.addChild(root_0, describe4.getTree());
               EOF5 = (Token) this.match(this.input, SparqlMarcoParser.EOF,
                     SparqlMarcoParser.FOLLOW_EOF_in_query41);
               EOF5_tree = this.adaptor.create(EOF5);
               this.adaptor.addChild(root_0, EOF5_tree);

            }
               break;

         }
         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "query"

   public static class select_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "select"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:1: select : ( prefix )*
   // SELECT ( DUPLICATEPARAM )? ( var )+ WHERE where ( orderBy )? ( limit )? ( offset )? ;
   public final SparqlMarcoParser.select_return select() throws RecognitionException {
      final SparqlMarcoParser.select_return retval = new SparqlMarcoParser.select_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token SELECT7 = null;
      Token DUPLICATEPARAM8 = null;
      Token WHERE10 = null;
      SparqlMarcoParser.prefix_return prefix6 = null;

      SparqlMarcoParser.var_return var9 = null;

      SparqlMarcoParser.where_return where11 = null;

      SparqlMarcoParser.orderBy_return orderBy12 = null;

      SparqlMarcoParser.limit_return limit13 = null;

      SparqlMarcoParser.offset_return offset14 = null;

      Object SELECT7_tree = null;
      Object DUPLICATEPARAM8_tree = null;
      Object WHERE10_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:9: ( ( prefix )*
         // SELECT ( DUPLICATEPARAM )? ( var )+ WHERE where ( orderBy )? ( limit )? ( offset
         // )? )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:11: ( prefix )*
         // SELECT ( DUPLICATEPARAM )? ( var )+ WHERE where ( orderBy )? ( limit )? ( offset
         // )?
         {
            root_0 = this.adaptor.nil();

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:11: ( prefix )*
            loop2: do {
               int alt2 = 2;
               final int LA2_0 = this.input.LA(1);

               if (LA2_0 == SparqlMarcoParser.PREFIX) {
                  alt2 = 1;
               }

               switch (alt2) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:11:
                     // prefix
                  {
                     this.pushFollow(SparqlMarcoParser.FOLLOW_prefix_in_select50);
                     prefix6 = this.prefix();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, prefix6.getTree());

                  }
                     break;

                  default:
                     break loop2;
               }
            } while (true);

            SELECT7 = (Token) this.match(this.input, SparqlMarcoParser.SELECT,
                  SparqlMarcoParser.FOLLOW_SELECT_in_select53);
            SELECT7_tree = this.adaptor.create(SELECT7);
            this.adaptor.addChild(root_0, SELECT7_tree);

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:26: (
            // DUPLICATEPARAM )?
            int alt3 = 2;
            final int LA3_0 = this.input.LA(1);

            if (LA3_0 == SparqlMarcoParser.DUPLICATEPARAM) {
               alt3 = 1;
            }
            switch (alt3) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:26:
                  // DUPLICATEPARAM
               {
                  DUPLICATEPARAM8 = (Token) this.match(this.input,
                        SparqlMarcoParser.DUPLICATEPARAM,
                        SparqlMarcoParser.FOLLOW_DUPLICATEPARAM_in_select55);
                  DUPLICATEPARAM8_tree = this.adaptor.create(DUPLICATEPARAM8);
                  this.adaptor.addChild(root_0, DUPLICATEPARAM8_tree);

               }
                  break;

            }

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:42: ( var )+
            int cnt4 = 0;
            loop4: do {
               int alt4 = 2;
               final int LA4_0 = this.input.LA(1);

               if (LA4_0 == 25) {
                  alt4 = 1;
               }

               switch (alt4) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:42: var
                  {
                     this.pushFollow(SparqlMarcoParser.FOLLOW_var_in_select58);
                     var9 = this.var();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, var9.getTree());

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

            WHERE10 = (Token) this.match(this.input, SparqlMarcoParser.WHERE,
                  SparqlMarcoParser.FOLLOW_WHERE_in_select61);
            WHERE10_tree = this.adaptor.create(WHERE10);
            this.adaptor.addChild(root_0, WHERE10_tree);

            this.pushFollow(SparqlMarcoParser.FOLLOW_where_in_select63);
            where11 = this.where();

            this.state._fsp--;

            this.adaptor.addChild(root_0, where11.getTree());
            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:59: ( orderBy )?
            int alt5 = 2;
            final int LA5_0 = this.input.LA(1);

            if (LA5_0 == SparqlMarcoParser.ORDERBY) {
               alt5 = 1;
            }
            switch (alt5) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:59: orderBy
               {
                  this.pushFollow(SparqlMarcoParser.FOLLOW_orderBy_in_select65);
                  orderBy12 = this.orderBy();

                  this.state._fsp--;

                  this.adaptor.addChild(root_0, orderBy12.getTree());

               }
                  break;

            }

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:68: ( limit )?
            int alt6 = 2;
            final int LA6_0 = this.input.LA(1);

            if (LA6_0 == 31) {
               alt6 = 1;
            }
            switch (alt6) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:68: limit
               {
                  this.pushFollow(SparqlMarcoParser.FOLLOW_limit_in_select68);
                  limit13 = this.limit();

                  this.state._fsp--;

                  this.adaptor.addChild(root_0, limit13.getTree());

               }
                  break;

            }

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:75: ( offset )?
            int alt7 = 2;
            final int LA7_0 = this.input.LA(1);

            if (LA7_0 == 32) {
               alt7 = 1;
            }
            switch (alt7) {
               case 1:
                  // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:11:75: offset
               {
                  this.pushFollow(SparqlMarcoParser.FOLLOW_offset_in_select71);
                  offset14 = this.offset();

                  this.state._fsp--;

                  this.adaptor.addChild(root_0, offset14.getTree());

               }
                  break;

            }

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "select"

   public static class prefix_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prefix"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:13:1: prefix : PREFIX ID ':'
   // prefixPart ;
   public final SparqlMarcoParser.prefix_return prefix() throws RecognitionException {
      final SparqlMarcoParser.prefix_return retval = new SparqlMarcoParser.prefix_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token PREFIX15 = null;
      Token ID16 = null;
      Token char_literal17 = null;
      SparqlMarcoParser.prefixPart_return prefixPart18 = null;

      Object PREFIX15_tree = null;
      Object ID16_tree = null;
      Object char_literal17_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:13:9: ( PREFIX ID ':'
         // prefixPart )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:13:11: PREFIX ID ':'
         // prefixPart
         {
            root_0 = this.adaptor.nil();

            PREFIX15 = (Token) this.match(this.input, SparqlMarcoParser.PREFIX,
                  SparqlMarcoParser.FOLLOW_PREFIX_in_prefix81);
            PREFIX15_tree = this.adaptor.create(PREFIX15);
            this.adaptor.addChild(root_0, PREFIX15_tree);

            ID16 = (Token) this.match(this.input, SparqlMarcoParser.ID,
                  SparqlMarcoParser.FOLLOW_ID_in_prefix83);
            ID16_tree = this.adaptor.create(ID16);
            this.adaptor.addChild(root_0, ID16_tree);

            char_literal17 = (Token) this.match(this.input, 22,
                  SparqlMarcoParser.FOLLOW_22_in_prefix84);
            char_literal17_tree = this.adaptor.create(char_literal17);
            this.adaptor.addChild(root_0, char_literal17_tree);

            this.pushFollow(SparqlMarcoParser.FOLLOW_prefixPart_in_prefix86);
            prefixPart18 = this.prefixPart();

            this.state._fsp--;

            this.adaptor.addChild(root_0, prefixPart18.getTree());

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "prefix"

   public static class prefixPart_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "prefixPart"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:14:1: prefixPart : '<' ID '>'
   // ;
   public final SparqlMarcoParser.prefixPart_return prefixPart() throws RecognitionException {
      final SparqlMarcoParser.prefixPart_return retval = new SparqlMarcoParser.prefixPart_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token char_literal19 = null;
      Token ID20 = null;
      Token char_literal21 = null;

      Object char_literal19_tree = null;
      Object ID20_tree = null;
      Object char_literal21_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:15:2: ( '<' ID '>' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:15:4: '<' ID '>'
         {
            root_0 = this.adaptor.nil();

            char_literal19 = (Token) this.match(this.input, 23,
                  SparqlMarcoParser.FOLLOW_23_in_prefixPart94);
            char_literal19_tree = this.adaptor.create(char_literal19);
            this.adaptor.addChild(root_0, char_literal19_tree);

            ID20 = (Token) this.match(this.input, SparqlMarcoParser.ID,
                  SparqlMarcoParser.FOLLOW_ID_in_prefixPart95);
            ID20_tree = this.adaptor.create(ID20);
            this.adaptor.addChild(root_0, ID20_tree);

            char_literal21 = (Token) this.match(this.input, 24,
                  SparqlMarcoParser.FOLLOW_24_in_prefixPart96);
            char_literal21_tree = this.adaptor.create(char_literal21);
            this.adaptor.addChild(root_0, char_literal21_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "prefixPart"

   public static class var_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "var"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:17:1: var : '?' ID ;
   public final SparqlMarcoParser.var_return var() throws RecognitionException {
      final SparqlMarcoParser.var_return retval = new SparqlMarcoParser.var_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token char_literal22 = null;
      Token ID23 = null;

      Object char_literal22_tree = null;
      Object ID23_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:17:5: ( '?' ID )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:17:7: '?' ID
         {
            root_0 = this.adaptor.nil();

            char_literal22 = (Token) this.match(this.input, 25,
                  SparqlMarcoParser.FOLLOW_25_in_var105);
            char_literal22_tree = this.adaptor.create(char_literal22);
            this.adaptor.addChild(root_0, char_literal22_tree);

            ID23 = (Token) this.match(this.input, SparqlMarcoParser.ID,
                  SparqlMarcoParser.FOLLOW_ID_in_var106);
            ID23_tree = this.adaptor.create(ID23);
            this.adaptor.addChild(root_0, ID23_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "var"

   public static class where_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "where"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:18:1: where : '{' ( whereCorp
   // )+ '}' ;
   public final SparqlMarcoParser.where_return where() throws RecognitionException {
      final SparqlMarcoParser.where_return retval = new SparqlMarcoParser.where_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token char_literal24 = null;
      Token char_literal26 = null;
      SparqlMarcoParser.whereCorp_return whereCorp25 = null;

      Object char_literal24_tree = null;
      Object char_literal26_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:19:2: ( '{' ( whereCorp
         // )+ '}' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:19:4: '{' ( whereCorp )+
         // '}'
         {
            root_0 = this.adaptor.nil();

            char_literal24 = (Token) this.match(this.input, 26,
                  SparqlMarcoParser.FOLLOW_26_in_where115);
            char_literal24_tree = this.adaptor.create(char_literal24);
            this.adaptor.addChild(root_0, char_literal24_tree);

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:19:8: ( whereCorp )+
            int cnt8 = 0;
            loop8: do {
               int alt8 = 2;
               final int LA8_0 = this.input.LA(1);

               if (LA8_0 == 25) {
                  alt8 = 1;
               }

               switch (alt8) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:19:8:
                     // whereCorp
                  {
                     this.pushFollow(SparqlMarcoParser.FOLLOW_whereCorp_in_where117);
                     whereCorp25 = this.whereCorp();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, whereCorp25.getTree());

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

            char_literal26 = (Token) this.match(this.input, 27,
                  SparqlMarcoParser.FOLLOW_27_in_where120);
            char_literal26_tree = this.adaptor.create(char_literal26);
            this.adaptor.addChild(root_0, char_literal26_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "where"

   public static class whereCorp_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "whereCorp"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:20:1: whereCorp : var
   // prefixPart ':' ID var '.' ;
   public final SparqlMarcoParser.whereCorp_return whereCorp() throws RecognitionException {
      final SparqlMarcoParser.whereCorp_return retval = new SparqlMarcoParser.whereCorp_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token char_literal29 = null;
      Token ID30 = null;
      Token char_literal32 = null;
      SparqlMarcoParser.var_return var27 = null;

      SparqlMarcoParser.prefixPart_return prefixPart28 = null;

      SparqlMarcoParser.var_return var31 = null;

      Object char_literal29_tree = null;
      Object ID30_tree = null;
      Object char_literal32_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:21:2: ( var prefixPart
         // ':' ID var '.' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:21:4: var prefixPart ':'
         // ID var '.'
         {
            root_0 = this.adaptor.nil();

            this.pushFollow(SparqlMarcoParser.FOLLOW_var_in_whereCorp128);
            var27 = this.var();

            this.state._fsp--;

            this.adaptor.addChild(root_0, var27.getTree());
            this.pushFollow(SparqlMarcoParser.FOLLOW_prefixPart_in_whereCorp130);
            prefixPart28 = this.prefixPart();

            this.state._fsp--;

            this.adaptor.addChild(root_0, prefixPart28.getTree());
            char_literal29 = (Token) this.match(this.input, 22,
                  SparqlMarcoParser.FOLLOW_22_in_whereCorp131);
            char_literal29_tree = this.adaptor.create(char_literal29);
            this.adaptor.addChild(root_0, char_literal29_tree);

            ID30 = (Token) this.match(this.input, SparqlMarcoParser.ID,
                  SparqlMarcoParser.FOLLOW_ID_in_whereCorp132);
            ID30_tree = this.adaptor.create(ID30);
            this.adaptor.addChild(root_0, ID30_tree);

            this.pushFollow(SparqlMarcoParser.FOLLOW_var_in_whereCorp134);
            var31 = this.var();

            this.state._fsp--;

            this.adaptor.addChild(root_0, var31.getTree());
            char_literal32 = (Token) this.match(this.input, 28,
                  SparqlMarcoParser.FOLLOW_28_in_whereCorp136);
            char_literal32_tree = this.adaptor.create(char_literal32);
            this.adaptor.addChild(root_0, char_literal32_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "whereCorp"

   public static class orderBy_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderBy"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:22:1: orderBy : ORDERBY (
   // orderClause )+ ;
   public final SparqlMarcoParser.orderBy_return orderBy() throws RecognitionException {
      final SparqlMarcoParser.orderBy_return retval = new SparqlMarcoParser.orderBy_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ORDERBY33 = null;
      SparqlMarcoParser.orderClause_return orderClause34 = null;

      Object ORDERBY33_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:22:9: ( ORDERBY (
         // orderClause )+ )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:22:11: ORDERBY (
         // orderClause )+
         {
            root_0 = this.adaptor.nil();

            ORDERBY33 = (Token) this.match(this.input, SparqlMarcoParser.ORDERBY,
                  SparqlMarcoParser.FOLLOW_ORDERBY_in_orderBy143);
            ORDERBY33_tree = this.adaptor.create(ORDERBY33);
            this.adaptor.addChild(root_0, ORDERBY33_tree);

            // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:22:19: ( orderClause
            // )+
            int cnt9 = 0;
            loop9: do {
               int alt9 = 2;
               final int LA9_0 = this.input.LA(1);

               if (LA9_0 == SparqlMarcoParser.ORDERPARAM || LA9_0 == 25) {
                  alt9 = 1;
               }

               switch (alt9) {
                  case 1:
                     // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:22:19:
                     // orderClause
                  {
                     this.pushFollow(SparqlMarcoParser.FOLLOW_orderClause_in_orderBy145);
                     orderClause34 = this.orderClause();

                     this.state._fsp--;

                     this.adaptor.addChild(root_0, orderClause34.getTree());

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

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "orderBy"

   public static class orderClause_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "orderClause"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:23:1: orderClause : ( stdOrder
   // | choosenOrder );
   public final SparqlMarcoParser.orderClause_return orderClause()
         throws RecognitionException {
      final SparqlMarcoParser.orderClause_return retval = new SparqlMarcoParser.orderClause_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlMarcoParser.stdOrder_return stdOrder35 = null;

      SparqlMarcoParser.choosenOrder_return choosenOrder36 = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:24:2: ( stdOrder |
         // choosenOrder )
         int alt10 = 2;
         final int LA10_0 = this.input.LA(1);

         if (LA10_0 == 25) {
            alt10 = 1;
         } else if (LA10_0 == SparqlMarcoParser.ORDERPARAM) {
            alt10 = 2;
         } else {
            final NoViableAltException nvae = new NoViableAltException("", 10, 0, this.input);

            throw nvae;
         }
         switch (alt10) {
            case 1:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:24:4: stdOrder
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_stdOrder_in_orderClause154);
               stdOrder35 = this.stdOrder();

               this.state._fsp--;

               this.adaptor.addChild(root_0, stdOrder35.getTree());

            }
               break;
            case 2:
               // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:24:15:
               // choosenOrder
            {
               root_0 = this.adaptor.nil();

               this.pushFollow(SparqlMarcoParser.FOLLOW_choosenOrder_in_orderClause158);
               choosenOrder36 = this.choosenOrder();

               this.state._fsp--;

               this.adaptor.addChild(root_0, choosenOrder36.getTree());

            }
               break;

         }
         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "orderClause"

   public static class choosenOrder_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "choosenOrder"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:25:1: choosenOrder :
   // ORDERPARAM '(' var ')' ;
   public final SparqlMarcoParser.choosenOrder_return choosenOrder()
         throws RecognitionException {
      final SparqlMarcoParser.choosenOrder_return retval = new SparqlMarcoParser.choosenOrder_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ORDERPARAM37 = null;
      Token char_literal38 = null;
      Token char_literal40 = null;
      SparqlMarcoParser.var_return var39 = null;

      Object ORDERPARAM37_tree = null;
      Object char_literal38_tree = null;
      Object char_literal40_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:25:13: ( ORDERPARAM '('
         // var ')' )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:25:15: ORDERPARAM '('
         // var ')'
         {
            root_0 = this.adaptor.nil();

            ORDERPARAM37 = (Token) this.match(this.input, SparqlMarcoParser.ORDERPARAM,
                  SparqlMarcoParser.FOLLOW_ORDERPARAM_in_choosenOrder164);
            ORDERPARAM37_tree = this.adaptor.create(ORDERPARAM37);
            this.adaptor.addChild(root_0, ORDERPARAM37_tree);

            char_literal38 = (Token) this.match(this.input, 29,
                  SparqlMarcoParser.FOLLOW_29_in_choosenOrder166);
            char_literal38_tree = this.adaptor.create(char_literal38);
            this.adaptor.addChild(root_0, char_literal38_tree);

            this.pushFollow(SparqlMarcoParser.FOLLOW_var_in_choosenOrder167);
            var39 = this.var();

            this.state._fsp--;

            this.adaptor.addChild(root_0, var39.getTree());
            char_literal40 = (Token) this.match(this.input, 30,
                  SparqlMarcoParser.FOLLOW_30_in_choosenOrder168);
            char_literal40_tree = this.adaptor.create(char_literal40);
            this.adaptor.addChild(root_0, char_literal40_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "choosenOrder"

   public static class stdOrder_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "stdOrder"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:26:1: stdOrder : var ;
   public final SparqlMarcoParser.stdOrder_return stdOrder() throws RecognitionException {
      final SparqlMarcoParser.stdOrder_return retval = new SparqlMarcoParser.stdOrder_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      SparqlMarcoParser.var_return var41 = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:27:2: ( var )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:27:4: var
         {
            root_0 = this.adaptor.nil();

            this.pushFollow(SparqlMarcoParser.FOLLOW_var_in_stdOrder176);
            var41 = this.var();

            this.state._fsp--;

            this.adaptor.addChild(root_0, var41.getTree());

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "stdOrder"

   public static class limit_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "limit"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:28:1: limit : 'LIMIT' INT ;
   public final SparqlMarcoParser.limit_return limit() throws RecognitionException {
      final SparqlMarcoParser.limit_return retval = new SparqlMarcoParser.limit_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token string_literal42 = null;
      Token INT43 = null;

      Object string_literal42_tree = null;
      Object INT43_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:28:7: ( 'LIMIT' INT )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:28:9: 'LIMIT' INT
         {
            root_0 = this.adaptor.nil();

            string_literal42 = (Token) this.match(this.input, 31,
                  SparqlMarcoParser.FOLLOW_31_in_limit183);
            string_literal42_tree = this.adaptor.create(string_literal42);
            this.adaptor.addChild(root_0, string_literal42_tree);

            INT43 = (Token) this.match(this.input, SparqlMarcoParser.INT,
                  SparqlMarcoParser.FOLLOW_INT_in_limit185);
            INT43_tree = this.adaptor.create(INT43);
            this.adaptor.addChild(root_0, INT43_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "limit"

   public static class offset_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "offset"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:29:1: offset : 'OFFSET' INT ;
   public final SparqlMarcoParser.offset_return offset() throws RecognitionException {
      final SparqlMarcoParser.offset_return retval = new SparqlMarcoParser.offset_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token string_literal44 = null;
      Token INT45 = null;

      Object string_literal44_tree = null;
      Object INT45_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:29:9: ( 'OFFSET' INT )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:29:11: 'OFFSET' INT
         {
            root_0 = this.adaptor.nil();

            string_literal44 = (Token) this.match(this.input, 32,
                  SparqlMarcoParser.FOLLOW_32_in_offset193);
            string_literal44_tree = this.adaptor.create(string_literal44);
            this.adaptor.addChild(root_0, string_literal44_tree);

            INT45 = (Token) this.match(this.input, SparqlMarcoParser.INT,
                  SparqlMarcoParser.FOLLOW_INT_in_offset195);
            INT45_tree = this.adaptor.create(INT45);
            this.adaptor.addChild(root_0, INT45_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "offset"

   public static class construct_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "construct"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:33:1: construct : ID ;
   public final SparqlMarcoParser.construct_return construct() throws RecognitionException {
      final SparqlMarcoParser.construct_return retval = new SparqlMarcoParser.construct_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token ID46 = null;

      Object ID46_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:34:2: ( ID )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:34:4: ID
         {
            root_0 = this.adaptor.nil();

            ID46 = (Token) this.match(this.input, SparqlMarcoParser.ID,
                  SparqlMarcoParser.FOLLOW_ID_in_construct207);
            ID46_tree = this.adaptor.create(ID46);
            this.adaptor.addChild(root_0, ID46_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "construct"

   public static class ask_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "ask"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:36:1: ask : INT ;
   public final SparqlMarcoParser.ask_return ask() throws RecognitionException {
      final SparqlMarcoParser.ask_return retval = new SparqlMarcoParser.ask_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token INT47 = null;

      Object INT47_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:36:5: ( INT )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:36:8: INT
         {
            root_0 = this.adaptor.nil();

            INT47 = (Token) this.match(this.input, SparqlMarcoParser.INT,
                  SparqlMarcoParser.FOLLOW_INT_in_ask216);
            INT47_tree = this.adaptor.create(INT47);
            this.adaptor.addChild(root_0, INT47_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "ask"

   public static class describe_return extends ParserRuleReturnScope {

      Object tree;

      @Override
      public Object getTree() {
         return this.tree;
      }
   };

   // $ANTLR start "describe"
   // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:38:1: describe : FLOAT ;
   public final SparqlMarcoParser.describe_return describe() throws RecognitionException {
      final SparqlMarcoParser.describe_return retval = new SparqlMarcoParser.describe_return();
      retval.start = this.input.LT(1);

      Object root_0 = null;

      Token FLOAT48 = null;

      Object FLOAT48_tree = null;

      try {
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:39:2: ( FLOAT )
         // /Users/Marco/Desktop/Tesi Davide/Antlr-doc/SparqlMarco.g:39:5: FLOAT
         {
            root_0 = this.adaptor.nil();

            FLOAT48 = (Token) this.match(this.input, SparqlMarcoParser.FLOAT,
                  SparqlMarcoParser.FOLLOW_FLOAT_in_describe227);
            FLOAT48_tree = this.adaptor.create(FLOAT48);
            this.adaptor.addChild(root_0, FLOAT48_tree);

         }

         retval.stop = this.input.LT(-1);

         retval.tree = this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

      } catch (final RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         retval.tree = this.adaptor.errorNode(this.input, retval.start, this.input.LT(-1),
               re);

      } finally {
      }
      return retval;
   }

   // $ANTLR end "describe"

   // Delegated rules

   public static final BitSet FOLLOW_select_in_query27 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_construct_in_query31 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ask_in_query35 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_describe_in_query39 = new BitSet(
         new long[] { 0x0000000000000000L });
   public static final BitSet FOLLOW_EOF_in_query41 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_prefix_in_select50 = new BitSet(
         new long[] { 0x0000000000000090L });
   public static final BitSet FOLLOW_SELECT_in_select53 = new BitSet(
         new long[] { 0x0000000002000020L });
   public static final BitSet FOLLOW_DUPLICATEPARAM_in_select55 = new BitSet(
         new long[] { 0x0000000002000020L });
   public static final BitSet FOLLOW_var_in_select58 = new BitSet(
         new long[] { 0x0000000002000060L });
   public static final BitSet FOLLOW_WHERE_in_select61 = new BitSet(
         new long[] { 0x0000000004000000L });
   public static final BitSet FOLLOW_where_in_select63 = new BitSet(
         new long[] { 0x0000000180000202L });
   public static final BitSet FOLLOW_orderBy_in_select65 = new BitSet(
         new long[] { 0x0000000180000002L });
   public static final BitSet FOLLOW_limit_in_select68 = new BitSet(
         new long[] { 0x0000000100000002L });
   public static final BitSet FOLLOW_offset_in_select71 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_PREFIX_in_prefix81 = new BitSet(
         new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_ID_in_prefix83 = new BitSet(
         new long[] { 0x0000000000400000L });
   public static final BitSet FOLLOW_22_in_prefix84 = new BitSet(
         new long[] { 0x0000000000800000L });
   public static final BitSet FOLLOW_prefixPart_in_prefix86 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_23_in_prefixPart94 = new BitSet(
         new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_ID_in_prefixPart95 = new BitSet(
         new long[] { 0x0000000001000000L });
   public static final BitSet FOLLOW_24_in_prefixPart96 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_25_in_var105 = new BitSet(
         new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_ID_in_var106 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_26_in_where115 = new BitSet(
         new long[] { 0x0000000002000020L });
   public static final BitSet FOLLOW_whereCorp_in_where117 = new BitSet(
         new long[] { 0x000000000A000020L });
   public static final BitSet FOLLOW_27_in_where120 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_whereCorp128 = new BitSet(
         new long[] { 0x0000000000800000L });
   public static final BitSet FOLLOW_prefixPart_in_whereCorp130 = new BitSet(
         new long[] { 0x0000000000400000L });
   public static final BitSet FOLLOW_22_in_whereCorp131 = new BitSet(
         new long[] { 0x0000000000000100L });
   public static final BitSet FOLLOW_ID_in_whereCorp132 = new BitSet(
         new long[] { 0x0000000002000020L });
   public static final BitSet FOLLOW_var_in_whereCorp134 = new BitSet(
         new long[] { 0x0000000010000000L });
   public static final BitSet FOLLOW_28_in_whereCorp136 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ORDERBY_in_orderBy143 = new BitSet(
         new long[] { 0x0000000002000420L });
   public static final BitSet FOLLOW_orderClause_in_orderBy145 = new BitSet(
         new long[] { 0x0000000002000422L });
   public static final BitSet FOLLOW_stdOrder_in_orderClause154 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_choosenOrder_in_orderClause158 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ORDERPARAM_in_choosenOrder164 = new BitSet(
         new long[] { 0x0000000020000000L });
   public static final BitSet FOLLOW_29_in_choosenOrder166 = new BitSet(
         new long[] { 0x0000000002000020L });
   public static final BitSet FOLLOW_var_in_choosenOrder167 = new BitSet(
         new long[] { 0x0000000040000000L });
   public static final BitSet FOLLOW_30_in_choosenOrder168 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_var_in_stdOrder176 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_31_in_limit183 = new BitSet(
         new long[] { 0x0000000000000800L });
   public static final BitSet FOLLOW_INT_in_limit185 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_32_in_offset193 = new BitSet(
         new long[] { 0x0000000000000800L });
   public static final BitSet FOLLOW_INT_in_offset195 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_ID_in_construct207 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_INT_in_ask216 = new BitSet(
         new long[] { 0x0000000000000002L });
   public static final BitSet FOLLOW_FLOAT_in_describe227 = new BitSet(
         new long[] { 0x0000000000000002L });

}
