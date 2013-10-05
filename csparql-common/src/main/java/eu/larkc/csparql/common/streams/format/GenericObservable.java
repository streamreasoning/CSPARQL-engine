/*******************************************************************************
 * Copyright 2013 DEIB - Politecnico di Milano
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
   
   public void removeObserver(final GenericObserver<T> o) {
	      if (this.observers.contains(o)) {
	         this.observers.remove(o);
	      }
	   }

   public void notifyObservers(final T quads) {

      for (final GenericObserver<T> o : this.observers) {
         o.update(this, quads);
      }
   }
}
