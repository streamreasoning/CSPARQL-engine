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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.new_parser.utility_files;

/**
 * It represent the logical window of the stream with its range and window overlap identified
 * by the step field.
 * 
 * @author Marco
 */
public class LogicalWindow extends Window {

	private TimeIntervalDescription rangeDescription;
	private TimeIntervalDescription stepDescription;
	private boolean isTumbling;
	
	public LogicalWindow() {
		super();
	}

	public LogicalWindow(TimeIntervalDescription rangeDescription, TimeIntervalDescription stepDescription, boolean isTumbling) {
		this.rangeDescription = rangeDescription;
		this.stepDescription = stepDescription;
		this.isTumbling = isTumbling;
	}

	public TimeIntervalDescription getRangeDescription() {
		return rangeDescription;
	}

	public void setRangeDescription(TimeIntervalDescription rangeDescription) {
		this.rangeDescription = rangeDescription;
	}

	public TimeIntervalDescription getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(TimeIntervalDescription stepDescription) {
		this.stepDescription = stepDescription;
	}

	public boolean isTumbling() {
		return isTumbling;
	}

	public void setTumbling(boolean isTumbling) {
		this.isTumbling = isTumbling;
	}

}
