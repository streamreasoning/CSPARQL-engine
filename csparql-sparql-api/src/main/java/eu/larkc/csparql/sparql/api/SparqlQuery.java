/*
 * @(#)SparqlQuery.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: SparqlQuery.java 123 2009-09-30 14:18:13Z dbarbieri $
 */

package eu.larkc.csparql.sparql.api;

import eu.larkc.csparql.common.NamedObject;

public interface SparqlQuery extends NamedObject {

   String getQueryCommand();

   boolean isGraphQuery();

   boolean isSelectQuery();

   boolean isAskQuery();
}
