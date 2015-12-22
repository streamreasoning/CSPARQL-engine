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
package eu.larkc.csparql.common.config;

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
			config.addProperty("esper.externaltime.enabled", false);
			config.addProperty("esper.externaltime.tick", 0);
		}
	}

	public boolean isEsperUsingExternalTimestamp() {
		return config.getBoolean("esper.externaltime.enabled");
	}

	public long getTimeStampTick() {
		return config.getLong("esper.externaltime.tick");
	}
	
	//mainly for test purposes
	public void setConfigParams(Properties properties){
		for(Entry<Object,Object> entry : properties.entrySet())
			config.setProperty(entry.getKey().toString(), entry.getValue());
	}
	
}
