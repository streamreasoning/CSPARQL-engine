/*
 * @(#)Translator.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: Translator.java 232 2010-04-20 09:11:07Z dbarbieri $
 */
package eu.larkc.csparql.core.parser;

import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
import eu.larkc.csparql.core.streams.formats.CSparqlQueryImpl;

public class PipeTranslator extends Translator {

   private final CsparqlEngine engine = null;

   @Override
   public CSparqlQuery translate(final String queryCommand) {
      final String[] parts = queryCommand.split("|");
      final CSparqlQuery q = new CSparqlQueryImpl(parts[0], parts[1], queryCommand);
      return q;
   }
}
