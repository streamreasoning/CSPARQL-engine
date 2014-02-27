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
