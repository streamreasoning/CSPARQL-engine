/*******************************************************************************
 * Copyright 2014 DEIB -Politecnico di Milano
 *   
 *  Marco Balduini (marco.balduini@polimi.it)
 *  Emanuele Della Valle (emanuele.dellavalle@polimi.it)
 *  Davide Barbieri
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *   
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *   
 *  Acknowledgements:
 *  
 *  This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
package eu.larkc.csparql.sparql.api;

import java.text.ParseException;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.data_source.Datasource;
import eu.larkc.csparql.common.exceptions.ReasonerException;
import eu.larkc.csparql.common.utils.ReasonerChainingType;

public interface SparqlEngine {

	void initialize();

	void destroy();
	
	void parseSparqlQuery(final SparqlQuery query) throws ParseException;

	RDFTable evaluateQuery(final SparqlQuery query);

	void addStatement(final String subject, final String predicate, final String object);

	void addStatement(String subject, String predicate, String object, long timestamp);

	void clean();
	
	void execUpdateQueryOverDatasource(String queryBody);
	
	void putStaticNamedModel(String iri, String serialization);
	
	void removeStaticNamedModel(String iri);
	
	Datasource getDataSource();
		
	String getEngineType();
	
//	void activateInference();
//		
//	void activateInference(String rulesFileSerialization, String entailmentRegimeType);
//	
//	void activateInference(String rulesFileSerialization, String entailmentRegimeType, String tBoxFileSerialization);
	
	public void setReasonerMap(Object reasonerMap);
	public void addReasonerToReasonerMap(String queryId, Object reasoner);
	void arrestInference(String queryId) throws ReasonerException ;
	void restartInference(String queryId) throws ReasonerException ;
	void updateReasoner(String queryId);
	void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType);
	void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType, String tBoxFile);

	boolean getInferenceStatus();
}
