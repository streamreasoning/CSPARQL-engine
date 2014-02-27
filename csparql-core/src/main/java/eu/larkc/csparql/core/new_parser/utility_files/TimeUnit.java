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
package eu.larkc.csparql.core.new_parser.utility_files;

public enum TimeUnit {
	
	D, H, M, S, MS;
	
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

}
