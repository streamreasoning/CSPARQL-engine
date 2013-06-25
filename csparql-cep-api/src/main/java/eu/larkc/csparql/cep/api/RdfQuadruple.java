/*
 * @(#)Quadruple.java   1.0   10/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.cep.api;

public class RdfQuadruple {

   private final String subject;
   private final String predicate;
   private final String object;
   private final long timestamp;
   private String streamName;
   
   public RdfQuadruple(final String subject, final String predicate, final String object,
         final long milliseconds) {
      this.subject = subject;
      this.predicate = predicate;
      this.object = object;
      this.timestamp = milliseconds;
   }

   
   
   public String getSubject() {
      return this.subject;
   }

   public String getPredicate() {
      return this.predicate;
   }

   public String getObject() {
      return this.object;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
	public String toString() {
		return getSubject()  + " " + getPredicate() +  " " + getObject() + " . (" + getTimestamp() + ")";
	}



public void setStreamName(String streamName) {
	this.streamName = streamName;
}



public String getStreamName() {
	return streamName;
}
}
