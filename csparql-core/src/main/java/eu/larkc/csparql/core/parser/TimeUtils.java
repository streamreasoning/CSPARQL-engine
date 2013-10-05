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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

/**
 * Allow to manipulate time according to RegistrationInfo.TimeUnit
 * 
 * @author Marco
 */
public class TimeUtils {

   /**
    * Transform time into its equivalent amount of seconds according to timeUnit.
    * 
    * @param time
    * @param timeUnit
    * @return
    */
   public static String getSeconds(final int time, final RegistrationInfo.TimeUnit timeUnit) {
      String seconds = new String();
      switch (timeUnit) {
         case MS: // millisecond
            seconds = time + " msec";
            break;
         case S: // second
            seconds = time + " seconds";
            break;
         case M: // minute
            seconds = time * 60 + " seconds";
            break;
         case H: // hour
            seconds = time * 3600 + " seconds";
            break;
         case D: // day
            seconds = time * 86400 + " seconds";
            break;
         default: // Never happen
            seconds = "-1";
      }
      return seconds;
   }

}
