/*
 * @(#)EsperQuery.java   1.0   15/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: EsperQuery.java 107 2009-09-16 15:05:49Z dbarbieri $
 */
package eu.larkc.csparql.cep.esper;

import java.util.UUID;

import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.cep.api.TimeUnit;

public class EsperQuery implements CepQuery {

   private final String id;
   private final String command;

   public String getId() {
      return this.id;
   }

   public String getQueryCommand() {
      // TODO fix this fake implementation
      return this.command;
   }

   public int getRange() {
      // TODO fix this fake implementation
      return 30;
   }

   public TimeUnit getUnit() {
      // TODO fix this fake implementation
      return TimeUnit.milliseconds;
   }

   public boolean isLogical() {
      // TODO fix this fake implementation
      return true;
   }

   public boolean isPhysical() {
      // TODO fix this fake implementation
      return false;
   }

   public EsperQuery(final String command) {
      super();
      this.id = this.generateID();
      this.command = command;
      this.parseCommand();
   }

   private void parseCommand() {
      // TODO implement EsperQuery.parseCommand
   }

   private String generateID() {
      return UUID.randomUUID().toString();
   }

}
