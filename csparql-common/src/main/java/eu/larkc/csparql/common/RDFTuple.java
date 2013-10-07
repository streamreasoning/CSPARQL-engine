/*******************************************************************************
 * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
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
 * 
 * This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
package eu.larkc.csparql.common;

/*
 * @(#)RDFTuple.java   1.0   02/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */

import java.util.ArrayList;
import java.util.List;

public class RDFTuple {

   private final List<String> fields = new ArrayList<String>();

   public void addFields(final String... values) {
      for (final String v : values) {
         this.fields.add(v);
      }
   }

   @Override
   public String toString() {

      StringBuffer sb = new StringBuffer();

      for (final String v : this.fields) {
         sb = sb.append(v);
         sb = sb.append("\t");
      }

      return sb.toString();

   }
   public String get(final int i) {
    	if (i>2) {
    		return null;
    	} 
        return this.fields.get(i);
    }

}
