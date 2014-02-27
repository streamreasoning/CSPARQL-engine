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
 * It represent the logical window of the stream with its range and window overlap identified
 * by the step field.
 * 
 * @author Marco
 */
public class LogicalWindow extends Window {

   private final RegistrationInfo.TimeUnit rangeTimeUnit;
   private final int step; // If it's equal to freq is't tumbling.
   private final RegistrationInfo.TimeUnit stepTimeUnit;

   public LogicalWindow(final int windowRange,
         final RegistrationInfo.TimeUnit rangeTimeUnit, final int step,
         final RegistrationInfo.TimeUnit stepTimeUnit) {
      this.rangeTimeUnit = rangeTimeUnit;
      this.step = step;
      this.stepTimeUnit = stepTimeUnit;
      this.windowRange = windowRange;
   }

   /**
    * @return the rangeTimeUnit
    */
   public RegistrationInfo.TimeUnit getRangeTimeUnit() {
      return this.rangeTimeUnit;
   }

   /**
    * @return the step
    */
   public int getStep() {
      return this.step;
   }

   /**
    * @return the stepTimeUnit
    */
   public RegistrationInfo.TimeUnit getStepTimeUnit() {
      return this.stepTimeUnit;
   }

   /**
    * Check if the windows are overlapping, with step != freq.
    * 
    * @return true if the windows doesn't overlap
    */
   public boolean isTumbling() {
      return this.windowRange == this.step && this.rangeTimeUnit.equals(this.stepTimeUnit);
   }

   @Override
   public String toString() {
      final StringBuffer sb = new StringBuffer();
      sb.append("Logical window, range = ");
      sb.append(this.windowRange);
      sb.append(" rangeTimeUnit = ");
      sb.append(this.rangeTimeUnit.toString());
      if (this.isTumbling()) {
         sb.append(" Tumbling");
      } else {
         sb.append(" step = ");
         sb.append(this.step);
         sb.append(" stepTimeUnit = ");
         sb.append(this.stepTimeUnit);
      }
      sb.append("\n");
      return sb.toString();
   }

}
