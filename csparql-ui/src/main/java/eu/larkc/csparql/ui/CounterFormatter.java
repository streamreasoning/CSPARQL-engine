/*
 * @(#)CounterFormatter.java   1.0   01/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.ui;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.streams.format.GenericObservable;
import eu.larkc.csparql.core.ResultFormatter;

public class CounterFormatter extends ResultFormatter {

   private int count = 0;
   private long start = 0;

   
   public void update(final GenericObservable<RDFTable> observed, final RDFTable q) {

      if (this.start == 0) {
         this.start = System.nanoTime();
      }

      this.count += q.size();
      float timePast = (float) (System.nanoTime() - this.start);
      final float freq = (float) this.count / timePast * 1000000000;
      
      if (timePast>10000000000f) {
       this.start = System.nanoTime();
       this.count = 0;
       System.out.println(freq);
      }

//      if (this.count % 1000 == 0) 
//    	  System.out.println(freq + " CSPARQL Results/sec");
    	  
    	  
      
   }
}
