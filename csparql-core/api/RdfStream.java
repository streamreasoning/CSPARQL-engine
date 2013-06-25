/*
 * @(#)RdfStream.java   1.0   15/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.cep.api;

import eu.larkc.csparql.common.streams.format.GenericObservable;

public class RdfStream extends GenericObservable<RdfQuadruple> {

   private long lastUpdated = 0;
	
   private String iri = "";

   public String getIRI() {
      return this.iri;
   }

   public String uniqueName() {
	  long hashCode = this.iri.hashCode();
	  hashCode = hashCode + Integer.MAX_VALUE + 1000;
      return "STREAM" + String.valueOf(hashCode);
   }

   public RdfStream(final String iri) {
      this.iri = iri;
   }

   public void put(final RdfQuadruple q) {
	   
	  lastUpdated = System.nanoTime();
      this.notifyObservers(q);
   }

private long getLastUpdated() {
	return lastUpdated;
}

}

