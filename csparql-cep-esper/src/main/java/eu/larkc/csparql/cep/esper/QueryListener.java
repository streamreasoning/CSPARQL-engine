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
/*
 * @(#)QueryListener.java   1.0   15/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: QueryListener.java 136 2009-12-11 14:13:53Z dbarbieri $
 */
package eu.larkc.csparql.cep.esper;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfSnapshot;

class QueryListener extends RdfSnapshot implements UpdateListener {

   QueryListener(final String id) {
      super(id);
   }

   public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {

      final List<RdfQuadruple> quads = new ArrayList<RdfQuadruple>();

      if (newEvents == null)
    	  return;
      
      for (final EventBean b : newEvents) {
         // TODO: to keep if we use Observer Observable in java.util.
         // this.setChanged();
         final RdfQuadruple q = new RdfQuadruple(b.get("subject").toString(), b.get(
               "predicate").toString(), b.get("object").toString(), Long.parseLong(b.get("timestamp").toString()));

         q.setStreamName(b.get("streamName").toString());
         
         quads.add(q);
      }
      
      setChanged();
      this.notifyObservers(quads);
   }
}
