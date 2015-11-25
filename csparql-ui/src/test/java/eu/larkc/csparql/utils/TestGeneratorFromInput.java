package eu.larkc.csparql.utils;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;

public class TestGeneratorFromInput extends RdfStream{
	private long[] timestamps;
	
	private final String subj = "http://example.org/s";
	private final String pred = "http://example.org/p";
	private final String obj = "http://example.org/o";
	
	public TestGeneratorFromInput(String iri, long[] timestamps){
		super(iri);
		this.timestamps = timestamps;
	}
	
	public void run(){
		int i = 0;
		for(Long timestamp : timestamps){
			String c;
			if(i<10)
				c="0"+i++;
			else
				c=""+i++;
				
			RdfQuadruple tempQ = new RdfQuadruple(subj+c, pred+c, obj+c, timestamp);
			this.put(tempQ);
		}
	}
}

