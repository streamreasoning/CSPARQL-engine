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
package eu.larkc.csparql.cep.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class RDFStreamAggregationTestGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(RDFStreamAggregationTestGenerator.class);	
	
   private int c = 1;
   private int ct = 1;
   private boolean keepRunning = false;
   
   private final String defaultQuery = "REGISTER QUERY PIPPO AS SELECT ?S (\"http://ex.org/n\" as ?p) (count(?O) as ?n) FROM STREAM <http://myexample.org/stream> [RANGE TRIPLES 10] WHERE { ?S ?P ?O } GROUP BY ?S" ;

   public RDFStreamAggregationTestGenerator(final String iri) {
      super(iri);
   }
   
   public void pleaseStop() {
	   keepRunning = false;
   }

   public void run() {
	  keepRunning = true;
      while (keepRunning) {
    	 double n = Math.random()*10;
    	 
    	 for (int i=0;i<n;i++) {
         final RdfQuadruple q = new RdfQuadruple("http://myexample.org/S" + this.c,
                 "http://myexample.org/P" + this.c, "http://myexample.org/O" + this.c+i, System.nanoTime());
         this.put(q);
         //logger.info(q.toString());
         ct++;
    	 }
         
         //if(c%10==0) logger.info(ct+ " triples streamed so far");

         try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
         this.c++;
      }
   }
   
   public String getDeafaultQuery() {
	   return defaultQuery;
   }
}
