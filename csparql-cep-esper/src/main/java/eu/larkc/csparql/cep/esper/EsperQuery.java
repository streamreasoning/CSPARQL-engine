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
