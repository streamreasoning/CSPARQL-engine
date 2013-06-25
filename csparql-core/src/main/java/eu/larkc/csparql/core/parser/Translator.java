/*
 * @(#)Translator.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: Translator.java 242 2010-05-13 10:04:26Z dbarbieri $
 */
package eu.larkc.csparql.core.parser;

import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.streams.formats.CSparqlQuery;
import eu.larkc.csparql.core.streams.formats.TranslationException;

public abstract class Translator {

   private CsparqlEngine engine = null;

   public abstract CSparqlQuery translate(String queryCommand) throws TranslationException;

   public CsparqlEngine getEngine() {
      return this.engine;
   }

   public void setEngine(final CsparqlEngine engine) {
      this.engine = engine;
   }
}
