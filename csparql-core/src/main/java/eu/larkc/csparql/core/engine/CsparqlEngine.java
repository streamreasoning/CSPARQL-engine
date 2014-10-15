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
package eu.larkc.csparql.core.engine;

import java.text.ParseException;
import java.util.Collection;

import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.common.utils.ReasonerChainingType;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;

public interface CsparqlEngine {

	/**
	 */
//	CsparqlQueryResultProxy registerQuery(String command) throws ParseException;

	CsparqlQueryResultProxy registerQuery(String command, boolean activateInference) throws ParseException;

	CsparqlQueryResultProxy registerQuery(String command, boolean activateInference, String rulesFileSerialization, ReasonerChainingType chainingType) throws ParseException;

	CsparqlQueryResultProxy registerQuery(String command, boolean activateInference, String rulesFileSerialization,	ReasonerChainingType chainingType, String tBoxFileSerialization) throws ParseException;

	/**
	 */
	void initialize();

	void initialize(int queueDimension);

	void initialize(boolean performTimestampFunction);

	void initialize(int queueDimension, boolean performTimestampFunction);

	void execUpdateQueryOverDatasource(String queryBody);

	void putStaticNamedModel(String iri, String serialization);

	void removeStaticNamedModel(String iri);

	void destroy();

	/**
	 */
	RdfStream registerStream(RdfStream stream);

	/**
	 */
	Collection<CSparqlQuery> getAllQueries();

	/**
	 */
	void unregisterQuery(String id);

	/**
	 */
	void unregisterStream(String iri);

	void startQuery(final String id);

	void stopQuery(final String id);

	RdfStream getStreamByIri(String iri);

//	void activateInference();
//
//	void activateInference(String rulesFilePath, String entailmentRegimeType);
//
//	void activateInference(String rulesFilePath, String entailmentRegimeType, String tBoxFilePath);
	
	void arrestInference(String queryId);
	void restartInference(String queryId);
	
	void updateReasoner(String queryId);
	void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType);
	void updateReasoner(String queryId, String rulesFile, ReasonerChainingType chainingType, String tBoxFile);

	boolean getInferenceStatus();

}
