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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class GlueTestGenerator extends TestGenerator {

   private final List<RdfQuadruple> list;
   private final int index;

   public GlueTestGenerator(final String filename) throws IOException, ParseException {
      super("http://www.glue.org/glue");
      this.index = 0;

      System.out.println("reading file...");
      this.list = new LinkedList<RdfQuadruple>();

      final BufferedReader rd = new BufferedReader(new FileReader(filename));

      final long first = 0;

      final long linenumeber = 1;

      while (true) {
         final String ln = rd.readLine();

         if (ln == null) {
            break;
         }

         if (ln.startsWith("#") | ln.startsWith("@") | ln.equals("") | ln.startsWith(" ")) {
            continue;
         }

         String[] parts = ln.split(" ");

         parts = this.joinParts(parts);

         // 2009-07-15T13:03:46Z

         final SimpleDateFormat dfparser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

         final String cleanTimestamp = parts[3].replaceAll("\"", "").substring(0,
               parts[3].length() - 16);

         final java.util.Date dDate = dfparser.parse(cleanTimestamp);

         final Calendar cal = Calendar.getInstance();
         cal.setTime(dDate);

         // if (first == 0)
         // first = cal.getTimeInMillis();
         //
         // long timestamp = cal.getTimeInMillis() - first;
         //
         // if (timestamp < 0)
         // timestamp = 0;
         // -
         // timestamp /= Speed;

         final long timestamp = cal.getTimeInMillis();

         final RdfQuadruple q = new RdfQuadruple(parts[0], parts[1], parts[2], timestamp);

         ((LinkedList<RdfQuadruple>) this.list).addFirst(q);
      }
      System.out.println(this.list.size() + " read");

   }

   private String[] joinParts(final String[] parts) {
      final String[] newparts = new String[4];
      newparts[0] = parts[0].substring(1, parts[0].length() - 1);
      newparts[1] = parts[1].substring(1, parts[1].length() - 1);
      newparts[2] = "";

      if (parts[2].indexOf("<") == 0) {
         newparts[2] = parts[2].substring(1, parts[2].length() - 1);
      } else {
         for (int i = 2; i < parts.length - 2; i++) {
            newparts[2] += parts[i] + " ";
         }

         newparts[2] = newparts[2].trim();
      }

      newparts[3] = parts[parts.length - 2];

      return newparts;
   }

   @Override
   public void run() {

      long referenceTime = this.list.get(0).getTimestamp();

      System.out.println("referenceTime: " + referenceTime);

      long streamedTriples = 0;

      while (!this.list.isEmpty()) {

         final RdfQuadruple q = this.list.remove(0);

         final long t = q.getTimestamp();

         // long wakeTime = referenceTime + t;

         final long timeToWait = t - referenceTime;
         referenceTime = t;

         streamedTriples++;

         try {
            if (timeToWait > 0) {
               System.out.println("Streamed Triples: " + streamedTriples + " time to wait: "
                     + timeToWait);
            }
            Thread.currentThread();
            Thread.sleep(timeToWait);
         } catch (final Exception e) {
            e.printStackTrace();
         }

         this.put(q);

      }

   }
}
