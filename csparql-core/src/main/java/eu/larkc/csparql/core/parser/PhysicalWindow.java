/*******************************************************************************
 * Copyright 2013 DEIB - Politecnico di Milano
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
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;


/**
 * It identify the phisical window with its number of triple as range.
 * 
 * @author Marco
 */
public class PhysicalWindow extends Window {

   public PhysicalWindow(final int windowRange) {
      this.windowRange = windowRange;
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer();
      sb.append("Phisical window, number of triples = ");
      sb.append(this.windowRange);
      sb.append("\n");
      return sb.toString();
   }

}
