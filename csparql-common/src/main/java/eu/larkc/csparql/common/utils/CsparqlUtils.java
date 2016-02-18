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
package eu.larkc.csparql.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class CsparqlUtils {
	
	public static String serializeRDFFile(String filePath) throws Exception{
		File f = new File(filePath);
		Model m = ModelFactory.createDefaultModel();
		try{
			m.read(FileManager.get().open(f.getAbsolutePath()), null, "RDF/XML");
		} catch(Exception e){
			try{
				m.read(FileManager.get().open(f.getAbsolutePath()), null, "TURTLE");
			} catch(Exception e1){
				try{
					m.read(FileManager.get().open(f.getAbsolutePath()), null, "N-TRIPLE");
				} catch(Exception e2){
					m.read(FileManager.get().open(f.getAbsolutePath()), null, "RDF/JSON");
				}
			}
		}
		StringWriter sw = new StringWriter();
		m.write(sw);
		return sw.toString();
	}
	
	public static String serializeJenaModel(Model model) throws Exception{
		StringWriter sw = new StringWriter();
		model.write(sw);
		return sw.toString();
	}
	
	public static String fileToString(String filePath) throws Exception {
		File f = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}


}
