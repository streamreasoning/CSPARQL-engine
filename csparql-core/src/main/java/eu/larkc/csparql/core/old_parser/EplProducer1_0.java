/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
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
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.old_parser;

import java.util.HashSet;
import java.util.Set;

import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.streams.formats.TranslationException;

/**
 * Allow to build one or more executable EPL queries (one for each stream) from a CSparql
 * one.
 * 
 * @author Marco
 */
public class EplProducer1_0 extends DefaultEplProducer {

   public EplProducer1_0(final CsparqlEngine engine) {
      super(engine);
   }

   /**
    * Create a set of strings with queries in EPL from CSparql query parsed into t.
    * 
    * @param t
    * @return null if the query parsed in t is not CSparql
 * @throws TranslationException 
    */
   public Set<String> produceEpl(final TreeBox t) throws TranslationException {
      if (t.isCSparql()) {
         final TreeBox dupTree = TreeBox.dupFullTreeDecorated(t);

         final Set<StreamInfo> streams = dupTree.getStreams();
         final Set<String> result = new HashSet<String>();

         for (final StreamInfo si : streams) {
            final StringBuffer s = new StringBuffer(
                  "select * from ");
            String iri = si.getIri();
            // Apply the name conversion
            
            if (iri == null)
            {
            	TranslationException e = new TranslationException("Stream IRI " + iri + " not found");
            	e.setIri(iri);
            	throw e;
            }
            
            s.append(this.convertStreamIri(iri));
            s.append('.');
            final Window w = si.getWindow();
            if (si.hasPhisicalWindow()) {
               final PhysicalWindow ph = (PhysicalWindow) w;
               s.append("win:length_batch(");
               s.append(w.getWindowRange() );
               s.append(")");
               s.append(" output snapshot every ");
               s.append(ph.windowRange);
               s.append(" events");
            } else {
               final LogicalWindow l = (LogicalWindow) w;
               final String range = TimeUtils.getSeconds(l.getWindowRange(), l
                     .getRangeTimeUnit());
               // Tumbling
               if (l.isTumbling()) {
                  s.append("win:time_batch(");
                  // add range
                  s.append(range);
                  s.append(")");
               } else {
                  s.append("win:time(");
                  // add range
                  s.append(range);
                  s.append(")");
                  s.append(" output snapshot every ");
                  // ass step
                  final String step = TimeUtils.getSeconds(l.getStep(), l.getStepTimeUnit());
                  s.append(step);
               }
            }
            // Add the query
            result.add(s.toString());
         }
         return result;
      } else {
         return null;
      }
   }
}
