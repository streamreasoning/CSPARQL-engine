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
