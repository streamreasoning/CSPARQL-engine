package eu.larkc.csparql.common.data_source;

import java.util.List;

import eu.larkc.csparql.common.RDFTuple;

public interface Datasource {
	
	void putNamedModel(String namedModelURI, List<RDFTuple> modelContent);
	
	List<RDFTuple> getNamedModel(String namedModelURI);
	
	void removeNamedModel(String namedModelURI);
	
	void execUpdateQuery(String queryBody);
	
	List<RDFTuple> evaluateGeneralQuery(String queryBody);
	
	boolean containsNamedModel(String namedModelURI);


}
