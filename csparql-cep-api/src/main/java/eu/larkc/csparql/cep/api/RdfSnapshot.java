/*
 * @(#)DataStream.java   1.0   15/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.cep.api;

import java.util.List;

import eu.larkc.csparql.common.NamedObject;
import eu.larkc.csparql.common.streams.format.GenericObservable;

public class RdfSnapshot extends GenericObservable<List<RdfQuadruple>> implements
      NamedObject {

   private String id = "";

   public String getId() {
      return this.id;
   }

   public RdfSnapshot(final String id) {
      this.id = id;
   }

   public void put(final List<RdfQuadruple> q) {

      this.notifyObservers(q);
   }

}
