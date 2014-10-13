package eu.larkc.csparql.sparql.jena.common;

import com.hp.hpl.jena.reasoner.Reasoner;

import eu.larkc.csparql.sparql.api.ReasonerWrapper;

public class JenaReasonerWrapper implements ReasonerWrapper{
	
	private Reasoner reasoner;
	private boolean active;
	
	public JenaReasonerWrapper() {
		super();
	}
	
	public JenaReasonerWrapper(Object reasoner, boolean active) {
		super();
		this.reasoner = (Reasoner) reasoner;
		this.active = active;
	}
	
	@Override
	public Object getReasoner() {
		return (Reasoner) reasoner;
	}
	@Override
	public void setReasoner(Object reasoner) {
		this.reasoner = (Reasoner) reasoner;
	}
	@Override
	public boolean isActive() {
		return active;
	}
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
