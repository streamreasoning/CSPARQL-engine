/*
 * @(#)FakeObserver.java   1.0   18/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.ui;

import java.util.Observable;
import java.util.Observer;

public class FakeObserver implements Observer {

   
   public void update(final Observable o, final Object arg) {
      // TODO implement Observer.update
      System.out.println(arg);
   }

}
