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

public interface GenericObserver<T> {

   void update(GenericObservable<T> observed, T q);
}
