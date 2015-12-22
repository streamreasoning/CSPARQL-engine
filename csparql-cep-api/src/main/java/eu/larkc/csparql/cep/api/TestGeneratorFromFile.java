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
package eu.larkc.csparql.cep.api;

import java.io.BufferedReader;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGeneratorFromFile extends RdfStream {
	protected final Logger logger = LoggerFactory.getLogger(TestGeneratorFromFile.class);
	protected String inputFilePath;
	private boolean keepRunning = false;

	public TestGeneratorFromFile(final String iri, final String inputFilePath) {
		super(iri);
		this.inputFilePath = inputFilePath;
	}

	public void read_trace() {
		try {
			keepRunning = true;
			BufferedReader streamFile = new BufferedReader(new FileReader(inputFilePath));
			String line;
			String[] strElem;
			long cunrrentStamp;
			while ((line = streamFile.readLine()) != null) {
				strElem = line.split("\t");
				cunrrentStamp = Long.parseLong(strElem[3]);
				RdfQuadruple tempQ = new RdfQuadruple(strElem[0], strElem[1], strElem[2], cunrrentStamp);
				this.put(tempQ);
			}
			streamFile.close();
		} catch (Exception e) {
			System.out.println("ERROR! stream file cannot be read!: " + inputFilePath);
			e.printStackTrace();
		}
	}
}
