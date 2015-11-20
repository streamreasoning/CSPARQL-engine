/*******************************************************************************
 * Copyright 2015 DEIB -Politecnico di Milano
 *   
 *  Soheila Dehghanzadeh (soheila.dehghanzadeh@insight-centre.org)
 *  Shen Gao (shengao@ifi.uzh.ch)
 *  Daniele Dell'Aglio (daniele.dellaglio@polimi.it)
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

package eu.larck.csparql.ui;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.config.Config;
import eu.larkc.csparql.core.ResultFormatter;
import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;

@RunWith(Parameterized.class)
public class ExternalTimestampTests {
	private CsparqlEngine engine;
	private TestGeneratorFromInput streamGenerator;
	
	private long[] input;
	private int width, slide;
	private int[] expected;
	
	public ExternalTimestampTests(long[] input, int width, int slide, int[] expected){
		this.input = input;
		this.width = width;
		this.slide = slide;
		this.expected = expected;
	}
	
	/*
	 * PROBLEMS:
	 * In tumbling case, the first element is lost
	 * 
	 * The contemporaneity at the edge of the windows is badly managed
	 * [6:34:46 PM] Daniele Dell'Aglio: first event at x000 is added
	 * [6:34:50 PM] Daniele Dell'Aglio: the evaluation is performed
	 * [6:35:01 PM] Daniele Dell'Aglio: then, the second event at x000 does not trigger anything
	 * [6:35:18 PM] Daniele Dell'Aglio: and then is discarded at the next (tumbling) window
	 */
	
	 @Parameterized.Parameters
	    public static Iterable<?> data() {
	        return Arrays.asList(
	                new Object[][]{
                        {
                        	new long[]{1000, 1340, 2000, 2020, 3000, 3001}, 
                        	1, 
                        	1, 
                        	new int[]{2, 2}
                        },
                        {
                        	new long[]{600, 1000, 1340, 2000, 2020, 3000, 3001}, 
                        	1, 
                        	1, 
                        	new int[]{1, 2, 2}
                        },
                        {
                        	new long[]{600, 1000, 1340, 1340, 2000, 2020, 3000, 3001}, 
                        	1, 
                        	1, 
                        	new int[]{1, 3, 2}
                        },
                        {
                        	new long[]{600, 1000, 1340, 1340, 2000, 2020, 3000, 3001}, 
                        	1, 
                        	1, 
                        	new int[]{1, 3, 2}
                        },
                        {
                        	new long[]{600, 1000, 1340, 1340, 2000, 2000, 2020, 3000, 3001}, 
                        	1, 
                        	1, 
                        	new int[]{1, 4, 2}
                        },
	                }
	        );
	    }

	@BeforeClass public static void startup(){
		
	}
	
	
	@Before public void setup(){
		assertEquals(true, Config.INSTANCE.isEsperUsingExternalTimestamp());
		engine = new CsparqlEngineImpl();
		engine.initialize();
		streamGenerator = new TestGeneratorFromInput("http://myexample.org/stream", input);
	}
	
	@After public void destroy(){
		//FIXME: concurrent exception
//		engine.destroy();
	}
	
	@Test public void shouldCountSlidingWindowContents(){
		String queryGetAll = "REGISTER QUERY PIPPO AS SELECT (COUNT(*) AS ?tot) FROM STREAM <http://myexample.org/stream> [RANGE "+width+"s STEP "+slide+"s]  WHERE { ?S ?P ?O }";
		System.out.println(queryGetAll);

		engine.registerStream(streamGenerator);
		CsparqlQueryResultProxy c1 = null;

		try {
			c1 = engine.registerQuery(queryGetAll, false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Counter formatter = new Counter();
		c1.addObserver(formatter);
		streamGenerator.run();
		
		List<Integer> actual = formatter.getResults();
//		System.out.println(actual);
		int i = 0;
		for(int a : actual){
			assertEquals(expected[i++], a);
		}
		
//		System.out.println(formatter.getResults());
		
	}
	
	//for manual checking purposes
	public static void shouldPrint(){
		CsparqlEngine engine = new CsparqlEngineImpl();
		engine.initialize();
		
		TestGeneratorFromInput streamGenerator = new TestGeneratorFromInput("http://myexample.org/stream", new long[]{600, 1000, 1000, 1340, 2000, 2020, 3000, 3001});
		
		String queryGetAll = 
				"REGISTER QUERY PIPPO AS SELECT ?S FROM STREAM <http://myexample.org/stream> "
				+ "[RANGE 1s STEP 1s]  "
				+ "WHERE { ?S ?P ?O }";
//				"REGISTER QUERY PIPPO AS SELECT ?O FROM STREAM <http://myexample.org/stream> [RANGE 4s STEP 4s]  WHERE { ?S ?P ?O } ORDER BY ?O";
	
//		TestGeneratorFromFile tg = new TestGeneratorFromFile("http://myexample.org/stream", "src/test/resources/sample_input.txt");
		engine.registerStream(streamGenerator);
		CsparqlQueryResultProxy c1 = null;

		try {
			c1 = engine.registerQuery(queryGetAll, false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c1.addObserver(new ResultFormatter() {
			
			@Override
			public void update(Observable o, Object arg) {
				for(Iterator<RDFTuple> it = ((RDFTable)arg).getTuples().iterator();it.hasNext();){
					RDFTuple tuple = it.next();
					System.out.println(tuple.get(0));
				}
				System.out.println();
			}
		});
		streamGenerator.run();
		
	}
	
	public static void main(String[] args) {
		shouldPrint();
	}
}
