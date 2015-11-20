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
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import eu.larkc.csparql.cep.api.TestGeneratorFromFile;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.config.Config;
import eu.larkc.csparql.core.ResultFormatter;
import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.engine.CsparqlEngineImpl;
import eu.larkc.csparql.core.engine.CsparqlQueryResultProxy;

public final class ExternalTimestampTests {
	private CsparqlEngine engine;
	
	@BeforeClass public static void startup(){
		
	}
	
	@Before public void setup(){
		assertEquals(true, Config.INSTANCE.isEsperUsingExternalTimestamp());
		engine = new CsparqlEngineImpl();
		engine.initialize();
	}
	
	@After public void destroy(){
		engine.destroy();
	}
	
	/*
	 * Note! C-SPARQL seems bugged, so the result is not the one that should be really expected. First windows are badly computed
	 * */
	@Test public void shouldCountSlidingWindowContents(){
		String queryGetAll = "REGISTER QUERY PIPPO AS SELECT (COUNT(*) AS ?tot) FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 2s]  WHERE { ?S ?P ?O }";
		Integer[] expected = {11,25,38,	33,	33,	33,	34,	34,	34,	35,	35,	35,	35,	35,	35,	34};
		
		
		/* The real one should be the following one: 
		 * Integer[] expected = {17, 29, 34, 35, 35, 36, 36, 36, 36, 36, 37, 37, 36, 37, 37, 36, 36}; <- bug first complete window fixed
		 * Integer[] expected = {34, 35, 35, 36, 36, 36, 36, 36, 37, 37, 36, 37, 37, 36, 36}; <- incomplete windows bug fix
		 * Integer[] expected = {11,25,38,	33,	33,	33,	34,	34,	34,	35,	35,	35,	35,	35,	35,	34}; <- window starts with the beginning of the first second. with ticking parameter help 
		 */

	
		TestGeneratorFromFile tg = new TestGeneratorFromFile("http://myexample.org/stream", "src/test/resources/sample_input.txt");
		engine.registerStream(tg);
		CsparqlQueryResultProxy c1 = null;

		try {
			c1 = engine.registerQuery(queryGetAll, false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Counter formatter = new Counter();
		c1.addObserver(formatter);
		tg.read_trace();
		
		List<Integer> actual = formatter.getResults();
		System.out.println(actual);
		int i = 0;
		for(Integer a : actual){
			assertEquals(expected[i++], a);
		}
		
		System.out.println(formatter.getResults());
		
	}
	
	@Test public void shouldCountTumblingWindowContents(){
		String queryGetAll = "REGISTER QUERY PIPPO AS SELECT (COUNT(*) AS ?tot) FROM STREAM <http://myexample.org/stream> [RANGE 4s STEP 4s]  WHERE { ?S ?P ?O }";

		Integer[] expected = {28, 27, 28, 28, 28, 29, 29, 28};
		/* The real one should be the following one: 
		 * Integer[] expected = {29, 27, 28, 28, 28, 28, 29, 29, 28}; <- bug first complete window fixed
		 */

	
		TestGeneratorFromFile tg = new TestGeneratorFromFile("http://myexample.org/stream", "src/test/resources/sample_input.txt");
		engine.registerStream(tg);
		CsparqlQueryResultProxy c1 = null;

		try {
			c1 = engine.registerQuery(queryGetAll, false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Counter formatter = new Counter();
		c1.addObserver(formatter);
		tg.read_trace();
		
		List<Integer> actual = formatter.getResults();
		System.out.println(actual);
		int i = 0;
		for(Integer a : actual){
			assertEquals(expected[i++], a);
		}
		
		System.out.println(formatter.getResults());
		
	}
	
	//for manual checking purposes
	public void shouldPrint(){
		engine = new CsparqlEngineImpl();
		engine.initialize();
		
		String queryGetAll = 
				"REGISTER QUERY PIPPO AS SELECT ?O FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 2s]  WHERE { ?S ?P ?O } ORDER BY ?O";
//				"REGISTER QUERY PIPPO AS SELECT ?O FROM STREAM <http://myexample.org/stream> [RANGE 4s STEP 4s]  WHERE { ?S ?P ?O } ORDER BY ?O";
	
		TestGeneratorFromFile tg = new TestGeneratorFromFile("http://myexample.org/stream", "src/test/resources/sample_input.txt");
		engine.registerStream(tg);
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
		tg.read_trace();
		
	}
	
	public static void main(String[] args) {
		new ExternalTimestampTests().shouldPrint();
	}
	

}
