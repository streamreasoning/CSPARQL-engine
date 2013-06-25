/*
 * @(#)TranslationException.java   1.0   05/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.core.streams.formats;

public class TranslationException extends Exception {

   public TranslationException(final String message) {
      super(message);
   }

   private String iri;
   
   public void setIri(String s)
   {
	   iri = s;
   }
   
   public String getIri()
   {
	  return iri;
   }
   
}
