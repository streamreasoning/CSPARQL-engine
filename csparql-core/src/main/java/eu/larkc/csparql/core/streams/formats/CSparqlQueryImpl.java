/*
 * @(#)CSparqlQueryImpl.java   1.0   24/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.core.streams.formats;

import java.util.Collection;
import java.util.UUID;

import eu.larkc.csparql.cep.api.CepQuery;
import eu.larkc.csparql.core.Configuration;
import eu.larkc.csparql.core.parser.StreamInfo;
import eu.larkc.csparql.core.parser.TreeBox;
import eu.larkc.csparql.sparql.api.SparqlQuery;

public class CSparqlQueryImpl implements CSparqlQuery {

   private String id = null;
   private String command = null;
   private CepQuery cepQuery = null;
   private SparqlQuery sparqlQuery = null;
   private TreeBox treeBox = null;

   public CSparqlQueryImpl(final String cepQuery, final String sparqlQuery,
         final String cSparqlQuery) {
      this.id = this.generateID();
      this.command = cSparqlQuery;
      this.sparqlQuery = Configuration.getCurrentConfiguration().createSparqlQuery(
            sparqlQuery);
      this.cepQuery = Configuration.getCurrentConfiguration().createCepQuery(cepQuery);
   }

   private String generateID() {
      return UUID.randomUUID().toString();
   }

   public CepQuery getCepQuery() {
      return this.cepQuery;
   }

   public String getQueryCommand() {
      return this.command;
   }

   public SparqlQuery getSparqlQuery() {
      return this.sparqlQuery;
   }

   public String getId() {
      return this.id;
   }

   public TreeBox getTreeBox()
   {
	   return treeBox;
   }
   
	public void setTreeBox(TreeBox tb) {
		treeBox = tb;
	}

	public Collection<StreamInfo> getStreams() {
		return treeBox.getStreams();
	}
}