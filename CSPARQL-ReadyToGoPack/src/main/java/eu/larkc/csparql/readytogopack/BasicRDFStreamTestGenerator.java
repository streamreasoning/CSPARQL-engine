package eu.larkc.csparql.readytogopack;
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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class BasicRDFStreamTestGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(BasicRDFStreamTestGenerator.class);	

	private int c = 1;
	private boolean keepRunning = false;

	public BasicRDFStreamTestGenerator(final String iri) {
		super(iri);
	}

	public void pleaseStop() {
		keepRunning = false; 
	}

	@Override
	public void run() {
		keepRunning = true;
		while (keepRunning) {
			final RdfQuadruple q = new RdfQuadruple(getIRI()+"/S" + this.c,
					getIRI()+"/P" + this.c, getIRI()+"/O" + this.c, 
					System.currentTimeMillis());

			if(c%10==0) logger.info(c+ " triples streamed so far");

			this.put(q);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.c++;
		}
	}
}
