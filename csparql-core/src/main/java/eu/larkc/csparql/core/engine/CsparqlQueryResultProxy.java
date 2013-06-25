/*
 * @(#)CsparqlQueryResultProxty.java   1.0   01/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.core.engine;

import eu.larkc.csparql.common.NamedObject;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.streams.format.GenericObservable;

public class CsparqlQueryResultProxy extends GenericObservable<RDFTable> implements
      NamedObject {

   private final String id;

   public String getId() {
      return this.id;
   }

   public CsparqlQueryResultProxy(final String id) {
      this.id = id;
   }

}
