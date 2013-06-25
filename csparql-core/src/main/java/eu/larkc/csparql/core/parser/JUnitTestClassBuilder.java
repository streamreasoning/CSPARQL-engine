/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.larkc.csparql.core.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Marco
 */
public class JUnitTestClassBuilder {

   // Variables
   private static final String negativePath = "src/negativeTests/";
   private static final String positivePath = "src/positiveTests/";
   private static final String headerPathTemplate = "src/templateJUnitTests/headerJUnitTemplate.txt";
   private static final String footerPathTemplate = "src/templateJUnitTests/footerJUnitTemplate.txt";
   private static final String negativePathTemplate = "src/templateJUnitTests/negativeJUnitTemplate.txt";
   private static final String positivePathTemplate = "src/templateJUnitTests/positiveJUnitTemplate.txt";
   private static final String classPathWrite = "test/querytester/JUnitTesterTest.java";

   public static void createJunitTestClass() {
      try {
         String temp;
         BufferedReader in = new BufferedReader(new FileReader(
               JUnitTestClassBuilder.headerPathTemplate));
         final BufferedWriter write = new BufferedWriter(new FileWriter(
               JUnitTestClassBuilder.classPathWrite));
         // **************** WRITE HEADER
         while ((temp = in.readLine()) != null) {
            write.append(temp);
            write.newLine();
            write.flush();
         }
         in.close();

         String[] list = null;
         String className = null;
         String fileName = null;
         File path = null;
         // **************** WRITE NEGATIVE TESTS
         write.append("//*************** NEGATIVE TESTS");
         write.newLine();
         write.flush();

         // Go throught every query file
         path = new File(JUnitTestClassBuilder.negativePath);
         list = path.list();
         for (int i = 0; i < list.length; i++) {
            fileName = list[i];

            if (fileName.contains(".DS_Store") || fileName.contains(".svn")) {
               continue;
            }

            className = list[i].replaceAll("-", "_");
            className = className.replaceAll("\\.rq", "");
            // Get template for negative test
            in = new BufferedReader(new FileReader(
                  JUnitTestClassBuilder.negativePathTemplate));
            while ((temp = in.readLine()) != null) {
               if (temp.contains("@nameFile@")) {
                  write.append(temp.replaceAll("@nameFile@", fileName));
               } else if (temp.contains("@nameClass@")) {
                  write.append(temp.replaceAll("@nameClass@", className));
               } else {
                  write.append(temp);
               }
               write.newLine();
               write.flush();
            }
            in.close();
         }
         // **************** WRITE POSITIVE TESTS
         write.append("//*************** POSITIVE TESTS");
         write.newLine();
         write.flush();

         // Go throught every query file
         path = new File(JUnitTestClassBuilder.positivePath);
         list = path.list();
         for (int i = 0; i < list.length; i++) {
            fileName = list[i];

            if (fileName.contains(".DS_Store") || fileName.contains(".svn")) {
               continue;
            }

            className = list[i].replaceAll("-", "_");
            className = className.replaceAll("\\.rq", "");
            // Get template for negative test
            in = new BufferedReader(new FileReader(
                  JUnitTestClassBuilder.positivePathTemplate));
            while ((temp = in.readLine()) != null) {
               if (temp.contains("@nameFile@")) {
                  write.append(temp.replaceAll("@nameFile@", fileName));
               } else if (temp.contains("@nameClass@")) {
                  write.append(temp.replaceAll("@nameClass@", className));
               } else {
                  write.append(temp);
               }
               write.newLine();
               write.flush();
            }
            in.close();
         }
         // **************** WRITE FOOTER
         in = new BufferedReader(new FileReader(JUnitTestClassBuilder.footerPathTemplate));
         while ((temp = in.readLine()) != null) {
            write.append(temp);
            write.newLine();
            write.flush();
         }
         write.close();
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}
