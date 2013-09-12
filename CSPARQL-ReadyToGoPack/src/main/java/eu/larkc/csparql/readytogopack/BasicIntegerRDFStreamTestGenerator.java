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


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class BasicIntegerRDFStreamTestGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(BasicIntegerRDFStreamTestGenerator.class);	

	private int c = 1;
	private boolean keepRunning = false;

	public BasicIntegerRDFStreamTestGenerator(final String iri) {
		super(iri);
	}

	public void pleaseStop() {
		keepRunning = false; 
	}

	@Override
	public void run() {
		keepRunning = true;
		while (keepRunning) {
			Random rnd = new Random();
			int n = rnd.nextInt(10);
			final RdfQuadruple q = new RdfQuadruple(getIRI()+"/S",	getIRI()+"/P" + this.c, String.valueOf(n) + "^^http://www.w3.org/2001/XMLSchema#integer",System.currentTimeMillis());

			System.out.println(n);

			this.put(q);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.c++;
		}
	}
}
