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
      
      this.notifyObservers(quads);
   }
}
