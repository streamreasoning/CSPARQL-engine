package eu.larkc.csparql.sparql.api;

public interface ReasonerWrapper {
	
	Object getReasoner();
	void setReasoner(Object reasoner);
	boolean isActive();
	void setActive(boolean active);
	
}
