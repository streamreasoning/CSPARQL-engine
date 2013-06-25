package eu.larkc.csparql.common;

/*
 * @(#)RDFTuple.java   1.0   02/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */

import java.util.ArrayList;
import java.util.List;

public class RDFTuple {

   private final List<String> fields = new ArrayList<String>();

   public void addFields(final String... values) {
      for (final String v : values) {
         this.fields.add(v);
      }
   }

   @Override
   public String toString() {

      StringBuffer sb = new StringBuffer();

      for (final String v : this.fields) {
         sb = sb.append(v);
         sb = sb.append("\t");
      }

      return sb.toString();

   }
   public String get(final int i) {
    	if (i>2) {
    		return null;
    	} 
        return this.fields.get(i);
    }

}
