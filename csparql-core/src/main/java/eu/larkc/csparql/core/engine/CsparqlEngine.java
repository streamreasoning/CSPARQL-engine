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
