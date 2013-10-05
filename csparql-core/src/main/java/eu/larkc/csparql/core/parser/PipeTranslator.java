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
