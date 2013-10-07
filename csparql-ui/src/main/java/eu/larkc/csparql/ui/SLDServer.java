/*******************************************************************************
 * Copyright 2013 Davide Barbieri, Emanuele Della Valle, Marco Balduini
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
package eu.larkc.csparql.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.larkc.csparql.core.engine.CsparqlEngine;
import eu.larkc.csparql.core.engine.CsparqlEngineImpl;


public class SLDServer {
	
	protected static final Logger logger = LoggerFactory
	.getLogger(SLDServer.class);

protected static CsparqlEngine engine;

private static boolean  inizialized = false;


public static CsparqlEngine getInstance() {
if (!inizialized ) {
	engine = new CsparqlEngineImpl();
	logger.info("SLD Server Singleton Initialized");
	engine.initialize();
	inizialized = true;
}
logger.info("SLD Server Singleton Retuned");
return engine;
}

}

