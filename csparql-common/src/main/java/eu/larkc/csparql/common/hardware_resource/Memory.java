/*******************************************************************************
 * Copyright 2013 DEIB - Politecnico di Milano
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
 ******************************************************************************/
package eu.larkc.csparql.common.hardware_resource;

public class Memory {
	
	private static Runtime runtime = Runtime.getRuntime();
	private static int mb = 1024*1024;
	
	public static double getTotalMemory(){
		return runtime.totalMemory() / mb;
	}
	
	public static double getFreeMemory(){
		return runtime.freeMemory() / mb;
	}
	
	public static double getMemoryUsage(){
		return (runtime.totalMemory() - runtime.freeMemory()) / mb;
	}

}
