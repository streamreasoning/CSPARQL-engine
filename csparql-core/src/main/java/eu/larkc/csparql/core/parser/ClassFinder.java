/*
 * @(#)Configuration.java   1.0   Sep 14, 2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id: Configuration.java 232 2010-04-20 09:11:07Z dbarbieri $
 */

package eu.larkc.csparql.core.parser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class looks in the specified package for all classes who implements the specified
 * interface and return its List of type Class.
 * 
 * @author Marco
 */
class ClassFinder {

   /**
    * Searches all classes in the package which implements the interface passed as parameter
    * <p>
    * 
    * @param pckgname
    *           The name of the package to be inspected
    * @param interfaceName
    *           The interface name string
    * @return The list of the classes found, <code>null</code> otherwise
    */
   public static List<Class> find(final String pckgname, final String interfaceName) {
      final List<Class> result = new ArrayList<Class>();

      String name = new String(pckgname);
      if (!name.startsWith("/")) {
         name = "/" + name;
      }
      name = name.replace('.', '/');

      // Get a File object for the package
      final URL url = ClassFinder.class.getResource(name);
      final File directory = new File(url.getFile());

      if (directory.exists()) {
         // Get the list of the files contained in the package
         final String[] files = directory.list();
         for (int i = 0; i < files.length; i++) {

            // get only .class files
            if (files[i].endsWith(".class")) {
               // removes the .class extension
               final String classname = files[i].substring(0, files[i].length() - 6);
               try {
                  // Try to create an instance of the object
                  final Class tempClass = Class.forName(pckgname + "." + classname);
                  // Get interfaces implemented by each class found
                  final Class[] interfaces = tempClass.getInterfaces();
                  for (final Class c : interfaces) {
                     if (c.getSimpleName().equals(interfaceName)) {
                        // Found a class which implements the target interface
                        result.add(tempClass);
                     }
                  }
               } catch (final ClassNotFoundException cnfex) {
                  System.err.println(cnfex);
               }
            }
         }
      }
      return result;
   }
}
