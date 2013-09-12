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


import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class LBSMARDFStreamTestGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(LBSMARDFStreamTestGenerator.class);	

	private int c = 1;
	private int ct = 1;
	private boolean keepRunning = false;

	public LBSMARDFStreamTestGenerator(final String iri) {
		super(iri);
	}

	public void pleaseStop() {
		keepRunning = false;
	}

	@Override
	public void run() {



		keepRunning = true;
		
		while (keepRunning) {
			final RdfQuadruple q = new RdfQuadruple(super.getIRI()+"/user" + this.c,
					"http://myexample.org/likes", "http://myexample.org/O" + this.c, System.currentTimeMillis());

			this.put(q);
			//          logger.info(q.toString());
			System.out.println(q.toString());
			ct++;

			double n = Math.random()*5;

			for (int i=0;i<n;i++) {
				final RdfQuadruple q1 = new RdfQuadruple(super.getIRI()+"/user" + this.c+i,
						"http://myexample.org/likes", "http://myexample.org/O" + this.c, System.currentTimeMillis());
				this.put(q1);
				//         logger.info(q1.toString());
				System.out.println(q1.toString());
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ct++;
			}

			if(c%10==0) logger.info(ct+ " triples streamed so far");


			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.c++;
		}
	}

	public static String dumpRelatedStaticKnowledge(int maxUser) {

		Model m = ModelFactory.createDefaultModel(); 
		for (int j=0;j<maxUser;j++) {
			for (int i=0;i<5;i++) {      
				m.add(new ResourceImpl("http://myexample.org/user" + j+i), new PropertyImpl("http://myexample.org/follows"), new ResourceImpl("http://myexample.org/user" + j));
			}
		}
		StringWriter sw = new StringWriter(); 
		m.write(sw, "RDF/XML");
		return sw.toString();
	}

	public static void main(String[] args) {
		System.out.println(dumpRelatedStaticKnowledge(10));
	}
}
