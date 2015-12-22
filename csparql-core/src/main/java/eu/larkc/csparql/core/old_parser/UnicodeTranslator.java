/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.larkc.csparql.core.old_parser;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marco
 */
public class UnicodeTranslator {

   public UnicodeTranslator() {
   }

   public static String replaceUnicode(final String s) {

      String regex;
      Pattern pattern;
      Matcher matcher;

      regex = "\\\\u[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]";
      pattern = Pattern.compile(regex);
      matcher = pattern.matcher(s);

      while (matcher.find()) {
         final String[] temp = matcher.group().split("\\\\u");
         final Charset csets = Charset.forName("UTF-8");

         try {
            final Integer i = Integer.parseInt(temp[1]);
            final ByteBuffer bb = ByteBuffer.wrap(new byte[] { (byte) (i >>> 24),
                  (byte) (i >> 16 & 0xff), (byte) (i >> 8 & 0xff), (byte) (i & 0xff) });
            // String c = new String(bb, 0, 4, "UTF-8");
            System.out.println(csets.encode(bb.asCharBuffer()));
            // s = s.replace(matcher.group(), ""+c);

         } catch (final Exception e) {
            e.printStackTrace();
         }

      }

      return s;
   }
}

class UnicodeFormatter {

   /**
    * convert a String to a hex representation of the String, with 4 hex chars per char of
    * the original String, broken into byte groups. e.g. "1abc \uabcd" gives
    * "0031_0061_0062_0063_0020_abcd"
    * 
    * @param s
    *           String to convert to hex equivalent
    * @return hex represenation of string, 4 hex digit chars per char.
    */
   static public String displayHexString(final String s) {
      final StringBuilder sb = new StringBuilder(s.length() * 5 - 1);
      for (int i = 0; i < s.length(); i++) {
         final char c = s.charAt(i);
         if (i != 0) {
            sb.append('_');
         }
         // encode 16 bits as four nibbles

         sb.append(UnicodeFormatter.hexChar[c >>> 12 & 0xf]);
         sb.append(UnicodeFormatter.hexChar[c >>> 8 & 0xf]);
         sb.append(UnicodeFormatter.hexChar[c >>> 4 & 0xf]);
         sb.append(UnicodeFormatter.hexChar[c & 0xf]);
      }
      return sb.toString();
   }

   /**
    * table to convert a nibble to a hex char.
    */
   static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
         'b', 'c', 'd', 'e', 'f' };

   static public String byteToHex(final byte b) {
      // Returns hex String representation of byte b
      final char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f' };
      final char[] array = { hexDigit[b >> 4 & 0x0f], hexDigit[b & 0x0f] };
      return new String(array);
   }

   static public String charToHex(final char c) {
      // Returns hex String representation of char c
      final byte hi = (byte) (c >>> 8);
      final byte lo = (byte) (c & 0xff);
      return UnicodeFormatter.byteToHex(hi) + UnicodeFormatter.byteToHex(lo);
   }

} // class
