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
package eu.larkc.csparql.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RDFTable implements Collection<RDFTuple> {

	private final List<String> names = new ArrayList<String>();
	private final List<RDFTuple> tuples = new ArrayList<RDFTuple>();
	private String jsonSerialization = new String();
	
	public String getJsonSerialization() {
		return jsonSerialization;
	}

	public void setJsonSerialization(String jsonSerialization) {
		this.jsonSerialization = jsonSerialization;
	}

	public boolean isGraph() {
		// TODO: fix this fake implementation
		return true;
	}

	public RDFTable(final String... fieldsNames) {

		for (final String fn : fieldsNames) {
			this.names.add(fn);
		}

	}

	public RDFTable(final List<String> fieldsNames) {

		for (final String fn : fieldsNames) {
			this.names.add(fn);
		}
	}

	public void add(final String fieldName, final String fieldDefaultValue) {
		names.add(fieldName);
		for (RDFTuple t : tuples) {
			t.addFields(fieldDefaultValue);
		}
	}

	public void add(final Iterable<RDFTuple> tuplesToAdd) {
		for (final RDFTuple tta : tuplesToAdd) {
			this.add(tta);
		}
	}

	public boolean add(final RDFTuple tupleToAdd) {
		return this.getTuples().add(tupleToAdd);
	}

	public int size() {
		return this.getTuples().size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (final String n : this.names) {
			sb = sb.append(n).append("\t");
		}

		sb.append("\n");

		for (final RDFTuple t : this.getTuples()) {
			sb = sb.append(t.toString()).append("\n");
		}

		return sb.toString();
	}

	public boolean addAll(final Collection<? extends RDFTuple> arg0) {
		return this.getTuples().addAll(arg0);
	}

	public void clear() {
		this.getTuples().clear();
	}

	public boolean contains(final Object arg0) {
		return this.getTuples().contains(arg0);
	}

	public boolean containsAll(final Collection<?> arg0) {
		return this.getTuples().containsAll(arg0);
	}

	public boolean isEmpty() {
		return this.getTuples().isEmpty();
	}

	public Iterator<RDFTuple> iterator() {
		return this.getTuples().iterator();
	}

	public boolean remove(final Object arg0) {
		return this.getTuples().remove(arg0);
	}

	public boolean removeAll(final Collection<?> arg0) {
		return this.getTuples().removeAll(arg0);
	}

	public boolean retainAll(final Collection<?> arg0) {
		return this.getTuples().retainAll(arg0);
	}

	public Object[] toArray() {
		return this.getTuples().toArray();
	}

	public <T> T[] toArray(final T[] arg0) {
		return this.getTuples().toArray(arg0);
	}

	public Collection<RDFTuple> getTuples() {
		return tuples;
	}

	public Collection<String> getNames() {
		return names;
	}
}
