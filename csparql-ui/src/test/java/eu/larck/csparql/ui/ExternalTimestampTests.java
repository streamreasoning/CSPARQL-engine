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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import eu.larkc.csparql.cep.api.TestGeneratorFromFile;
import eu.larkc.csparql.common.config.Config;
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
	
	@Test public void shouldCountWindowContents(){
		final String queryGetAll = "REGISTER QUERY PIPPO AS SELECT (COUNT(*) AS ?tot) FROM STREAM <http://myexample.org/stream> [RANGE 5s STEP 2s]  WHERE { ?S ?P ?O }";
	
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
		
		//TODO: check if the results are the expected ones
//		assertEquals(expected, formatter.getResults());
		
		System.out.println(formatter.getResults());
		
	}
	

}
