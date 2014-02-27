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
package eu.larkc.csparql.core.old_parser;

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
