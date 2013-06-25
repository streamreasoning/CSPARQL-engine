/*
 * @(#)CSparqlQuery.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: CSparqlQuery.java 119 2009-09-30 09:55:20Z dbarbieri $
 */
package eu.larkc.csparql.core.streams.formats;

import java.util.Collection;

import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.common.NamedObject;
import eu.larkc.csparql.core.parser.StreamInfo;
import eu.larkc.csparql.core.parser.TreeBox;
import eu.larkc.csparql.sparql.api.SparqlQuery;

public interface CSparqlQuery extends NamedObject {

   String getQueryCommand();

   CepQuery getCepQuery();

   SparqlQuery getSparqlQuery();

   void setTreeBox(TreeBox tb);

   TreeBox getTreeBox();
   
   Collection<StreamInfo> getStreams();
}
