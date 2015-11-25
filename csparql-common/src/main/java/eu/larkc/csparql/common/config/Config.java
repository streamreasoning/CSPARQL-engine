/*******************************************************************************
 * Copyright 2014 DEIB -Politecnico di Milano
 *   
 * Soheila Dehghanzadeh (soheila.dehghanzadeh@insight-centre.org)
 * Shen Gao (shengao@ifi.uzh.ch)
 * Daniele Dell'Aglio (daniele.dellaglio@polimi.it)
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

package eu.larkc.csparql.common.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.BasePathLocationStrategy;
import org.apache.commons.configuration2.io.ClasspathLocationStrategy;
import org.apache.commons.configuration2.io.CombinedLocationStrategy;
import org.apache.commons.configuration2.io.FileLocationStrategy;
import org.apache.commons.configuration2.io.FileSystemLocationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	public static final Config INSTANCE = new Config();

	private final Logger logger = LoggerFactory.getLogger(Config.class);

	private Configuration config;

	private Config() {
		try {
			List<FileLocationStrategy> subs = Arrays.asList(
					new BasePathLocationStrategy(),
					new FileSystemLocationStrategy(),
					new ClasspathLocationStrategy());
			
			FileLocationStrategy strategy = new CombinedLocationStrategy(subs);
			
			FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class);
			Parameters params = new Parameters();
			
			builder.configure(params.fileBased()
					.setFileName("csparql.properties")
					.setLocationStrategy(strategy)
					);
			
			config = builder.getConfiguration();
			logger.debug("Configuration file successfully lodead");
		} catch (ConfigurationException e) {
			logger.error("Error while lading the configuration file; default config will be used", e);
			config = new BaseConfiguration();
			config.addProperty("esper.externaltime", false);
		}
	}

	public boolean isEsperUsingExternalTimestamp() {
		return config.getBoolean("esper.externaltime");
	}

	public String getExternalTraceFile() {
		if (isEsperUsingExternalTimestamp()) {
			File f = new File(config.getString("esper.externaltracefile"));
			if (f.exists() && !f.isDirectory()) {
				return config.getString("esper.externaltracefile");
			} else {
				throw new RuntimeException("using external timestamp, but no trace file found");
			}
		} else {
			throw new RuntimeException("not using external timestamp");
		}
	}
	
	public long getTimeStampTick() {
		if (isEsperUsingExternalTimestamp()) {
			File f = new File(config.getString("esper.externaltracefile"));
			if (f.exists() && !f.isDirectory()) {
				return config.getLong("esper.externaltimetick");
			} else {
				return 1000L;
			}
		} else {
			throw new RuntimeException("not using external timestamp");
		}
	}
	
	//mainly for test purposes
	public void setConfigParams(Properties properties){
		for(Entry<Object,Object> entry : properties.entrySet())
			config.setProperty(entry.getKey().toString(), entry.getValue());
	}
	
}
