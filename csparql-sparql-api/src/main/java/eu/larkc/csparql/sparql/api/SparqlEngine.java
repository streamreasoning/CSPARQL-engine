/*
 * @(#)SparqlEngine.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: SparqlEngine.java 127 2009-10-05 15:53:00Z dbarbieri $
 */
package eu.larkc.csparql.sparql.api;

import eu.larkc.csparql.common.RDFTable;

public interface SparqlEngine {

	void initialize();

	void destroy();

	RDFTable evaluateQuery(final SparqlQuery query);

	void addStatement(final String subject, final String predicate, final String object);

	void addStatement(String subject, String predicate, String object, long timestamp);

	void clean();
	
	void execUpdateQueryOverDatasource(String queryBody);
	
	String getEngineType();
	
	void activateInference();
	
	void setInferenceRulesFilePath(String path);

}
