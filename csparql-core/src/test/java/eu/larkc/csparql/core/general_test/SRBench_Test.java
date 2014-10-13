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
package eu.larkc.csparql.core.general_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import eu.larkc.csparql.core.new_parser.CsparqlParser;
import eu.larkc.csparql.core.new_parser.ParseException;

public class SRBench_Test {

	public static void main(String[] args) {
		String queryFolderPath = "/Users/baldo/Documents/Work/Work_Utilities/SRBench_CSPARQL_queries";

		File queryFolder = new File(queryFolderPath);
		try {

			for(File queryText : queryFolder.listFiles()){
				if(!queryText.getName().contains(".DS_Store")){
					if(queryText.getName().contains("q3"))
						System.out.println();
					System.out.println();
					System.out.println("----------------");
					System.out.println();
					System.out.println(queryText.getName());
					System.out.println("----------------");
					System.out.println();

					String s = readFile(queryText);


					if(!s.startsWith("REGISTER"))
						s = "REGISTER QUERY PercentileExample AS " + s;
					CsparqlParser parser = CsparqlParser.createAndParse(s);

					System.out.println(parser.getSparqlQuery());
//REGISTER QUERY PercentileExample AS PREFIX om-owl: <http://knoesis.wright.edu/ssw/ont/sensor-observation.owl#> PREFIX weather: <http://knoesis.wright.edu/ssw/ont/weather.owl#> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> ASK FROM STREAM <http://www.cwi.nl/SRBench/observations> [RANGE 1h STEP 10m] WHERE { ?observation om-owl:procedure ?sensor ; om-owl:observedProperty weather:WindSpeed ; om-owl:result [ om-owl:floatValue ?value ] . } GROUP BY ?sensor HAVING ( AVG(?value) >= "74"^^xsd:float )


				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static String readFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath()), "UTF8"));
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
