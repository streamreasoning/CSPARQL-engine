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
 * @(#)RdfStream.java   1.0   15/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.cep.api;

import eu.larkc.csparql.common.streams.format.GenericObservable;

public class RdfStream extends GenericObservable<RdfQuadruple> {

	private long lastUpdated = 0;

	private String iri = "";

	public String getIRI() {
		return this.iri;
	}

	public String uniqueName() {
		long hashCode = this.iri.hashCode();
		hashCode = hashCode + Integer.MAX_VALUE + 1000;
		return "STREAM" + String.valueOf(hashCode);
	}

	public RdfStream(final String iri) {
		this.iri = iri;
	}

	public void put(final RdfQuadruple q) {

		lastUpdated = System.nanoTime();
		this.notifyObservers(q);
	}

	private long getLastUpdated() {
		return lastUpdated;
	}

}

