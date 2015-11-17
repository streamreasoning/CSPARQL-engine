/*******************************************************************************
 * Copyright 2014 DEIB -Politecnico di Milano
 *   
 * Soheila Dehghanzadeh (soheila.dehghanzadeh@insight-centre.org)
 * Shen Gao (shengao@ifi.uzh.ch)
 * Daniele Dell'Aglio (daniele.dellaglio@polimi.it)
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
