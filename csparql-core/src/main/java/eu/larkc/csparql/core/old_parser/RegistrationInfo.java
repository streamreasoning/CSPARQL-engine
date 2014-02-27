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
 * Class which contains all the infos of the query registration: the type (query / stream),
 * the name and the computation timeRange with it's unit of measure.
 * 
 * @author Marco
 */
public class RegistrationInfo {

   public enum TimeUnit {
      D, H, M, S, MS
   };

   public enum QueryType {
      QUERY, STREAM
   };

   private final QueryType type;
   private final TimeUnit timeUnit; // null if there was no timeRange in the registration
                                    // statement
   private final String name;
   private final int timeRange; // -1 if it was not present in the registration statement

   /**
    * @return the type
    */
   public QueryType getQueryType() {
      return this.type;
   }

   /**
    * @return the timeUnit
    */
   public TimeUnit getUnitOfMeasure() {
      return this.timeUnit;
   }

   /**
    * @return the name
    */
   public String getName() {
      return this.name;
   }

   /**
    * @return the timeRange
    */
   public int getTimeRange() {
      return this.timeRange;
   }

   /**
    * Constructor which initialize all the fields.
    * 
    * @param type
    * @param name_uri
    * @param timeRange
    * @param timeUnit
    */
   public RegistrationInfo(final QueryType type, final String name, final int timeRange,
         final TimeUnit unitOfMeasure) {
      this.type = type;
      this.name = name;
      this.timeRange = timeRange;
      this.timeUnit = unitOfMeasure;
   }

   /**
    * Transform a string into its equivalent timeUnit.
    * 
    * @param timeUnit
    * @return null if the string doesn't match, the correct TimeUnit otherwise.
    */
   public static TimeUnit getUnitOfMeasure(final String unitOfMeasure) {
      TimeUnit result = null;
      if (unitOfMeasure.equalsIgnoreCase("ms")) {
         result = TimeUnit.MS;
      }
      if (unitOfMeasure.equalsIgnoreCase("s")) {
         result = TimeUnit.S;
      }
      if (unitOfMeasure.equalsIgnoreCase("m")) {
         result = TimeUnit.M;
      }
      if (unitOfMeasure.equalsIgnoreCase("h")) {
         result = TimeUnit.H;
      }
      if (unitOfMeasure.equalsIgnoreCase("d")) {
         result = TimeUnit.D;
      }
      return result;
   }

   @Override
   public String toString() {
      final String s = "Nome=" + this.getName() + " Type=" + this.getQueryType().name()
            + "\n" + "Freq=" + this.getTimeRange() + " Unit="
            + this.getUnitOfMeasure().name();
      return s;
   }
}
