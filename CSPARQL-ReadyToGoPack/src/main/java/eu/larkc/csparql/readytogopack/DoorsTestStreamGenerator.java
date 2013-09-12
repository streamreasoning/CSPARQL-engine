package eu.larkc.csparql.readytogopack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class DoorsTestStreamGenerator extends RdfStream implements Runnable {

	/** The logger. */
	protected final Logger logger = LoggerFactory
			.getLogger(BasicRDFStreamTestGenerator.class);	

	public DoorsTestStreamGenerator(final String iri) {
		super(iri);
	}

	@Override
	public void run() {

		try {

			Thread.sleep(700);
			
			Thread.sleep(1000);
			
			RdfQuadruple q = new RdfQuadruple(getIRI() + "/d1", getIRI()+"/status", getIRI() + "/opened", System.currentTimeMillis());
			this.put(q);

//			System.out.println(q.toString());
			
			q = new RdfQuadruple(getIRI() + "/d1", getIRI()+"/status", getIRI() + "/closed", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());
			
			q = new RdfQuadruple(getIRI() + "/d2", getIRI()+"/status", getIRI() + "/closed", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());

			Thread.sleep(1000);

			q = new RdfQuadruple(getIRI() + "/d2", getIRI()+"/status", getIRI() + "/opened", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());
			
			Thread.sleep(4000);

			q = new RdfQuadruple(getIRI() + "/d1", getIRI()+"/status", getIRI() + "/opened", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());
			
			q = new RdfQuadruple(getIRI() + "/d1", getIRI()+"/status", getIRI() + "/closed", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());
			
			Thread.sleep(1000);
			
			q = new RdfQuadruple(getIRI() + "/d1", getIRI()+"/status", getIRI() + "/opened", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());

			Thread.sleep(2000);

			q = new RdfQuadruple(getIRI() + "/d2", getIRI()+"/status", getIRI() + "/closed", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());

			Thread.sleep(2000);

			q = new RdfQuadruple(getIRI() + "/d3", getIRI()+"/status", getIRI() + "/closed", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());
			
			Thread.sleep(2000);

			q = new RdfQuadruple(getIRI() + "/d3", getIRI()+"/status", getIRI() + "/opened", System.currentTimeMillis());
			this.put(q);
			
//			System.out.println(q.toString());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
