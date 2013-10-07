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
