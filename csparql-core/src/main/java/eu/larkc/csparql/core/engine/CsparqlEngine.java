/*******************************************************************************
 * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Acknowledgements:
 * 
 * This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
/*
 * @(#)CsparqlEngine.java   1.0   10/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: CsparqlEngine.java 233 2010-04-20 13:54:43Z dbarbieri $
 */
package eu.larkc.csparql.core.engine;

import java.text.ParseException;
import java.util.Collection;

import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;

public interface CsparqlEngine {

   /**
   */
   CsparqlQueryResultProxy registerQuery(String command) throws ParseException;

   /**
   */
   void initialize();
   
   void initialize(int queueDimension);
   
   void initialize(boolean performTimestampFunction);

   void initialize(int queueDimension, boolean performTimestampFunction);

   void execUpdateQueryOverDatasource(String queryBody);

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
   
   void activateInference();
   
   void setInferenceRulesFilePath(String path);
}
