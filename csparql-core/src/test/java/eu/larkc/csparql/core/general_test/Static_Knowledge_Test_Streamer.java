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
