/*******************************************************************************
 * Copyright 2014 Davide Barbieri, Emanuele Della Valle, Marco Balduini
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
 * 
 * This work was partially supported by the European project LarKC (FP7-215535)
 ******************************************************************************/
package eu.larkc.csparql.cep.api;

import java.util.List;
import java.util.Observable;

import eu.larkc.csparql.common.NamedObject;

public class RdfSnapshot extends Observable implements
NamedObject {

	private String id = "";

	public String getId() {
		return this.id;
	}

	public RdfSnapshot(final String id) {
		this.id = id;
	}

	public void put(final List<RdfQuadruple> q) {

		setChanged();
		this.notifyObservers(q);
	}

}
