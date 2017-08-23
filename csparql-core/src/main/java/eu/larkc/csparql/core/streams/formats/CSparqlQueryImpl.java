/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
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
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
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
   private String name = null;

   public CSparqlQueryImpl(final String cepQuery, final String sparqlQuery,
         final String cSparqlQuery, Collection<StreamInfo> streams, String name) {
      this.id = this.generateID();
      this.command = cSparqlQuery;
      this.sparqlQuery = Configuration.getCurrentConfiguration().createSparqlQuery(sparqlQuery);
      this.cepQuery = Configuration.getCurrentConfiguration().createCepQuery(cepQuery);
      this.streams = streams;
      this.name = name;
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
   
   public String getName(){
	   return name;
   }
}
