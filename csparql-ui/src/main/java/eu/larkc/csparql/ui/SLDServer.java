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

