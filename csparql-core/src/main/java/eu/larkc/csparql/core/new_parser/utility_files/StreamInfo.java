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

package eu.larkc.csparql.core.new_parser.utility_files;

/**
 * Class which represent a stream
 * 
 * @author Marco
 */
public class StreamInfo {

	private String iri;
	private Window window;

	/**
	 * Default constructor, window can be an instance of a Phisical or Logical window.
	 * 
	 * @param iri
	 * @param window
	 */
	public StreamInfo(final String iri, final Window window) {
		this.iri = iri;
		this.window = window;
	}

	/**
	 * @return the iri
	 */
	public String getIri() {
		return this.iri;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	/**
	 * The window can be an instance of Physical or Logical Window.
	 * 
	 * @return
	 */
	public Window getWindow() {
		return this.window;
	}

	/**
	 * Check if the stream has a phisical window which imply a numberOfTriples != 0 and freq=0
	 * , step = 0.
	 * 
	 * @return
	 */
	public boolean hasPhisicalWindow() {
		return this.window instanceof PhysicalWindow;
	}

	@Override
	/**
	 * Add a \n at the end
	 */
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("Iri=");
		sb.append(this.getIri() + "\n");
		sb.append(this.window.toString());

		return sb.toString();
	}

}
