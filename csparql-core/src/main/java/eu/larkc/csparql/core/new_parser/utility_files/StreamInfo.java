/*******************************************************************************
 * Copyright 2014 DEIB -Politecnico di Milano
 *   
 *  Marco Balduini (marco.balduini@polimi.it)
 *  Emanuele Della Valle (emanuele.dellavalle@polimi.it)
 *  Davide Barbieri
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *   
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *   
 *  Acknowledgements:
 *  
 *  This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
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
