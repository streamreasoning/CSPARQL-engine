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
package eu.larkc.csparql.common;

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

      for (final String proj : this.fields) {
         sb = sb.append(proj);
         sb = sb.append("\t");
      }

      return sb.toString();

   }
   public String get(final int i) {
	   return this.fields.get(i);
    }

}
