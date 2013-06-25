/*
 * @(#)SesameQuery.java   1.0   25/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
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
