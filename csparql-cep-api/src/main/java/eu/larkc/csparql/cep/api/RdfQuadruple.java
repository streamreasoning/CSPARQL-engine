/*******************************************************************************
 * Copyright 2014 Davide Barbieri, Emanuele Della Valle, Marco Balduini
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
package eu.larkc.csparql.cep.api;

public class RdfQuadruple {

   private final String subject;
   private final String predicate;
   private final String object;
   private final long timestamp;
   private String streamName;
   
   public RdfQuadruple(final String subject, final String predicate, final String object,
         final long milliseconds) {
      this.subject = subject;
      this.predicate = predicate;
      this.object = object;
      this.timestamp = milliseconds;
   }

   
   
   public String getSubject() {
      return this.subject;
   }

   public String getPredicate() {
      return this.predicate;
   }

   public String getObject() {
      return this.object;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   @Override
	public String toString() {
		return getSubject()  + " " + getPredicate() +  " " + getObject() + " . (" + getTimestamp() + ")";
	}



public void setStreamName(String streamName) {
	this.streamName = streamName;
}



public String getStreamName() {
	return streamName;
}
}
