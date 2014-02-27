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
package eu.larkc.csparql.core.streams.formats;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.core.Configuration;
import eu.larkc.csparql.core.new_parser.utility_files.StreamInfo;
import eu.larkc.csparql.sparql.api.SparqlQuery;

public class CSparqlQueryImpl implements CSparqlQuery {

   private String id = null;
   private String command = null;
   private CepQuery cepQuery = null;
   private SparqlQuery sparqlQuery = null;
   private Collection<StreamInfo> streams;

   public CSparqlQueryImpl(final String cepQuery, final String sparqlQuery,
         final String cSparqlQuery, Collection<StreamInfo> streams) {
      this.id = this.generateID();
      this.command = cSparqlQuery;
      this.sparqlQuery = Configuration.getCurrentConfiguration().createSparqlQuery(
            sparqlQuery);
      this.cepQuery = Configuration.getCurrentConfiguration().createCepQuery(cepQuery);
      this.streams = streams;
   }

   private String generateID() {
      return UUID.randomUUID().toString();
   }

   public CepQuery getCepQuery() {
      return this.cepQuery;
   }

   public String getQueryCommand() {
      return this.command;
   }

   public SparqlQuery getSparqlQuery() {
      return this.sparqlQuery;
   }

   public String getId() {
      return this.id;
   }
   
   @Override
   public Collection<StreamInfo> getStreams() {
		return streams;
	}
}
