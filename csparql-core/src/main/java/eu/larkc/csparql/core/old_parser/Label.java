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

/**
 * Used to decorate the TreeBox
 * 
 * @author Marco
 */
public class Label {

   private int id;
   private int deepLevel;
   private int childIndex;

   Label() {
   }

   /**
    * Set the id with a deepLevel(from top to down)'.'childIndex(from left to right) notation
    * 
    * @param deepLevel
    * @param childIndex
    */
   Label(final int id, final int deepLevel, final int childIndex) {
      this.deepLevel = deepLevel;
      this.childIndex = childIndex;
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public int getDeepLevel() {
      return this.deepLevel;
   }

   public int getChildIndex() {
      return this.childIndex;
   }

   @Override
   public String toString() {
      return new String("ID:" + Integer.toString(this.id) + " deepLevel:"
            + Integer.toString(this.deepLevel) + " childIndex:"
            + Integer.toString(this.childIndex));
   }
}
