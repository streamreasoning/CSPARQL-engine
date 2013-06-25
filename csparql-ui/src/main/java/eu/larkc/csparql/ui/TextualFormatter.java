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
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.streams.format.GenericObservable;
import eu.larkc.csparql.core.ResultFormatter;

public class TextualFormatter extends ResultFormatter {

   private final int count = 0;
   private long start = 0;

   
   public void update(final GenericObservable<RDFTable> observed, final RDFTable q) {

      // System.out.println(q.toString());

      if (this.start == 0) {
         this.start = System.nanoTime();
      }

      //for (final RDFTuple t : q.) {
//         System.out.println(q.toString());
   }
   //}
}
