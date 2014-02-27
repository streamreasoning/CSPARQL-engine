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
package eu.larkc.csparql.sparql.sesame;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.larkc.csparql.sparql.api.SparqlQuery;

public class SesameQuery implements SparqlQuery {

   private final String id;
   private String command;

   
   public String getId() {
      // TODO implement SparqlQuery.getIdentifier
      return this.id;
   }

   
   public String getQueryCommand() {
      // TODO implement SparqlQuery.getQueryCommand
      return this.command;
   }

   public SesameQuery(final String cmd) {
      this();
      this.command = cmd;
   }

   public SesameQuery() {
      this.id = this.generateID();
   }

   private String generateID() {
      return UUID.randomUUID().toString();
   }

   
   public boolean isAskQuery() {
	   List<String> keywords = new ArrayList<String>(); 
	   keywords.add("ask");

	   for(String s: keywords)
		   if (command.toLowerCase().contains(s.toLowerCase()))
			   return true;
	   
	   return true;
   }

   
   public boolean isGraphQuery() {
	   
	   List<String> keywords = new ArrayList<String>(); 
	   keywords.add("construct");
	   keywords.add("describe");

	   for(String s: keywords)
		   if (command.toLowerCase().contains(s.toLowerCase()))
			   return true;
	   
	   return true;
   }

   
   public boolean isSelectQuery() {
	   List<String> keywords = new ArrayList<String>(); 
	   keywords.add("select");

	   for(String s: keywords)
		   if (command.toLowerCase().contains(s.toLowerCase()))
			   return true;
	   
	   return true;
   }
}
