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

public class TestGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(TestGenerator.class);	
	
   private int c = 1;
   private boolean keepRunning = false;

   public TestGenerator(final String iri) {
      super(iri);
   }
   
   public void pleaseStop() {
	   keepRunning = false;
   }

   public void run() {
	  keepRunning = true;
      while (keepRunning) {
    	  
    	 long start = System.nanoTime();
//         final RdfQuadruple q = new RdfQuadruple("http://myexample.org/S" + this.c,
//               "http://myexample.org/P" + this.c, "http://myexample.org/O" + this.c, this.c);
         final RdfQuadruple q = new RdfQuadruple("http://myexample.org/S" + this.c,
                 "http://myexample.org/P" + this.c, "http://myexample.org/O" + this.c, System.currentTimeMillis());//.nanoTime());
         //System.out.println("http://myexample.org/S" + this.c+"\thttp://myexample.org/P" + this.c+ "\thttp://myexample.org/O" + this.c+ "\t"+System.currentTimeMillis());
         long end = System.nanoTime();
         long duration = end-start;
//         if(c%10==0) logger.info(c+ " triples streamed so far");
         if ((duration)>1000000) logger.info(((float) c)/((float) duration)*1000000 + " triples/second streamed so far");
         
         try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.put(q);
//         try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
         this.c++;
      }
   }
}
