/*
 * @(#)CepEngine.java   1.0   14/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: CepEngine.java 128 2009-11-20 14:25:45Z dbarbieri $
 */

package eu.larkc.csparql.cep.api;

import java.util.Collection;

import eu.larkc.csparql.common.streams.format.GenericObserver;

public interface CepEngine extends GenericObserver<RdfQuadruple> {

   /**
   */
   void registerStream(RdfStream stream);

   /**
    */
   void unregisterStream(RdfStream stream);

   /**
   */
   void initialize();
   
   /**
   */
   void unregisterQuery(String id);

   /**
   */
   Collection<RdfStream> getAllRegisteredStreams();

   void destroy();

   /**
   */
   Collection<CepQuery> getAllQueries();

   RdfSnapshot registerQuery(String query, String id);

   void startQuery(String id);

   void stopQuery(String id);
   
   String getCepEngineType();
}
