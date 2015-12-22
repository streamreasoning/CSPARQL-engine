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
package eu.larkc.csparql.core.new_parser.utility_files;

public class TextUtilities {


	public static String queryTextEnhancer(String queryText){

		queryText = queryText.replace("\n", " ");

		String tempQuery;
		String firstPart;
		String secondPart;

		if(!queryText.contains("DESCRIBE ")){

			int bracketIndex = -1;
			int index = -1;
			try{

				tempQuery = queryText.substring(0, queryText.indexOf("{"));

				index = tempQuery.indexOf(" SELECT ");
				if(index >= 0 ){
					bracketIndex = queryText.indexOf("{");

					if(bracketIndex >=0 ){
						tempQuery = queryText.substring(index, bracketIndex);

						if(!tempQuery.contains(" WHERE ")){

							firstPart = queryText.substring(0, queryText.indexOf("{"));
							secondPart = queryText.substring(queryText.indexOf("{"));

							queryText = firstPart.trim() + " WHERE " + secondPart.trim();

						}
					}
				} else {

					index = queryText.indexOf(" CONSTRUCT ");
					if(index >= 0 ){

						bracketIndex = queryText.indexOf("}");

						tempQuery = queryText.substring(bracketIndex, queryText.length());

						index = tempQuery.indexOf("{");

						if(index >=0 ){

							tempQuery = tempQuery.substring(0, index + 1);
							if(!tempQuery.contains(" WHERE ")){

								firstPart = queryText.substring(0, index+ bracketIndex);
								secondPart = queryText.substring(index + bracketIndex);

								queryText = firstPart.trim() + " WHERE " + secondPart.trim();

							}
						}
					} else {
						index = queryText.indexOf(" ASK ");
						if(index >= 0 ){
							bracketIndex = queryText.indexOf("{");

							if(bracketIndex >=0 ){

								tempQuery = queryText.substring(index, bracketIndex);
								if(!tempQuery.contains(" WHERE ")){

									firstPart = queryText.substring(0, queryText.indexOf("{"));
									secondPart = queryText.substring(queryText.indexOf("{"));

									queryText = firstPart.trim() + " WHERE " + secondPart.trim();

								}
							}
						}
					}
				}

				queryText = queryText.replace("\n", " ");
				queryText = queryText.replace("}", "} ");
				queryText = queryText.replace("ASK", "ASK ");
				queryText = queryText.replace("TUMBLING", "TUMBLING ");
				queryText = queryText.replace("WHERE", "WHERE ");
				queryText = queryText.replace(" RANGE", "RANGE");
				queryText = queryText.trim().replaceAll(" +", " ");
				if(queryText.contains("ASK ")){
					String before = queryText.substring(0, queryText.indexOf("ASK ") + 4);
					String after = queryText.substring(queryText.indexOf("ASK ") + 4, queryText.length());
					queryText = before + " " + after;
				}
				//				queryText = queryText.trim().replace("{ {", "{{");

			}catch(Exception e){
				e.printStackTrace();
			}
		}

		return queryText;
	}

	public static String streamNameEnhancer(String streamIri){

		if(streamIri.startsWith("<") && streamIri.endsWith(">")){
			streamIri = streamIri.replace("<", "");
			streamIri = streamIri.replace(">", "");
		}

		return streamIri;

	}

}
