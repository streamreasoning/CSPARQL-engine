/*
 * @(#)CepQuery.java   1.0   14/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: CepQuery.java 122 2009-09-30 09:58:39Z dbarbieri $
 */

package eu.larkc.csparql.cep.api;

import eu.larkc.csparql.common.NamedObject;

public interface CepQuery extends NamedObject {

   String getQueryCommand();

   boolean isPhysical();

   boolean isLogical();

   int getRange();

   TimeUnit getUnit();

   
}
