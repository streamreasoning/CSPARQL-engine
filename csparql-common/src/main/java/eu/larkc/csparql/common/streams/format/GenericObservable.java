/*
 * @(#)MyCustomObserver.java   1.0   18/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.common.streams.format;

import java.util.ArrayList;
import java.util.List;

public class GenericObservable<T> {

   private final List<GenericObserver<T>> observers = new ArrayList<GenericObserver<T>>();

   public void addObserver(final GenericObserver<T> o) {
      if (!this.observers.contains(o)) {
         this.observers.add(o);
      }
   }

   public void notifyObservers(final T quads) {

      for (final GenericObserver<T> o : this.observers) {
         o.update(this, quads);
      }
   }
}
