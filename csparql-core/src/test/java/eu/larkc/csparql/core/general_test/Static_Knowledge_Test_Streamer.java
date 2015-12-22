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
package eu.larkc.csparql.core.general_test;
/*
 * @(#)TestGenerator.java   1.0   18/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class Static_Knowledge_Test_Streamer extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(Static_Knowledge_Test_Streamer.class);	

	private boolean keepRunning = false;
	private String quadrupleIRI = "http://streamreasoning.org#";

	public Static_Knowledge_Test_Streamer(final String iri) {
		super(iri);
	}

	public void pleaseStop() {
		keepRunning = false; 
	}

	@Override
	public void run() {
		keepRunning = true;

		Random randomGenerator = new Random();
		while (keepRunning) {
						
			RdfQuadruple q = new RdfQuadruple(quadrupleIRI+"w" + randomGenerator.nextInt(1000), quadrupleIRI+"isIn", quadrupleIRI+"r1", System.currentTimeMillis());
			this.put(q);
//			System.out.println(q);

			q = new RdfQuadruple(quadrupleIRI+"w" + randomGenerator.nextInt(1000),	quadrupleIRI+"isIn", quadrupleIRI+"r2", System.currentTimeMillis());
			this.put(q);
//			System.out.println(q);

			q = new RdfQuadruple(quadrupleIRI+"w" + randomGenerator.nextInt(1000),	quadrupleIRI+"isIn", quadrupleIRI+"r3", System.currentTimeMillis());
			this.put(q);
//			System.out.println(q);

			q = new RdfQuadruple(quadrupleIRI+"w" + randomGenerator.nextInt(1000),	quadrupleIRI+"isIn", quadrupleIRI+"r4", System.currentTimeMillis());
			this.put(q);
//			System.out.println(q);

			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
