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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.larkc.csparql.core.old_parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.runtime.tree.Tree;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import sun.misc.Regexp;

/**
 * @author Marco
 */
public class TreeBox extends ParseTree {

   public enum QueryLanguages {
      SPARQL, CSPARQL
   };

   private ParseTree pT;
   private TreeBox parent;
   private Label label;
   private int childIndex;

   TreeBox(final Object o) {
      super(o);
   }

   @Override
   public void setParent(final Tree t) {
      this.parent = (TreeBox) t;
   }

   @Override
   public TreeBox getParent() {
      if (this.parent == null) {
         return null;
      } else {
         return this.parent;
      }
   }

   @Override
   public void addChild(final Tree t) {
      super.addChild(t);
   }

   @Override
   public TreeBox getChild(final int i) {
      return (TreeBox) super.getChild(i);
   }

   @Override
   public int getChildIndex() {
      return this.childIndex;
   }

   @Override
   public void setChildIndex(final int value) {
      this.childIndex = value;
   }

   public ParseTree getParseTree() {
      return this.pT;
   }

   public void setParseTree(final ParseTree pT) {
      this.pT = pT;
   }

   public void setLabel(final Label l) {
      this.label = l;
   }

   public Label getLabel() {
      return this.label;
   }

  
   /**
    * Find all variables labels in the children.
    * 
    * @return A Set of type String with all the variables present in its children.
    */
   public Set<String> getVariables() {
      final Set<String> var = new HashSet();
      final List<TreeBox> nodes = this.getNodesByText("var");
      for (final TreeBox t : nodes) {
         var.add(t.getChild(0).getText());
      }
      return var;
   }

   /**
    * Print all the variables labels present in its children
    */
   public void printVariables() {
      System.out.print("Variables: ");
      final Set<String> var = this.getVariables();
      for (final String s : var) {
         System.out.print("<" + s + ">");
      }
      System.out.println();
   }

   /**
    * Find all streams labels in its children.
    * 
    * @return A Set of StreamInfo with all the streams present in its children.
    */
   public Set<StreamInfo> getStreams() {
      final Set<StreamInfo> streams = new HashSet();
      StreamInfo stream;
      final List<TreeBox> streamsStatements = this.getNodesByText("datasetClauseStream");
      List<TreeBox> tempNode;

      // Stream parameters
      String uri;
      String timeRange;
      String step;

      String timeRangeUnit;
      String stepUnit;
      int nOfTriples = -1;

      for (final TreeBox t : streamsStatements) {
         tempNode = t.getNodesByText("iriRef");
         uri = tempNode.get(0).getChild(0).getText();

         // Get the physical window parameters if present
         tempNode = t.getNodesByText("physicalWindow");

         if (!tempNode.isEmpty()) {
            nOfTriples = Integer.parseInt(tempNode.get(0).getChild(1).getText());
            // Create the physical window
            final PhysicalWindow pw = new PhysicalWindow(nOfTriples);
            stream = new StreamInfo(uri, pw);
         } else {
            tempNode = t.getNodesByText("logicalWindow");
            tempNode = tempNode.get(0).getNodesByText("timeRange");
            timeRange = tempNode.get(0).getChild(0).getText();
            // Get the timeRanngeUnit from the timeRange string, it's the last char
            String[] rangeUnit = getRangeAndUnit(timeRange);
            // Get away the timeUnit from the range
            timeRange = rangeUnit[0];
            timeRangeUnit = rangeUnit[1];
            // set step
            if (tempNode.size() == 2) {
               String[] stepClauses = getRangeAndUnit(tempNode.get(1).getChild(0).getText());
               step = stepClauses[0];
               stepUnit = stepClauses[1];
            } else {
               step = timeRange;
               stepUnit = timeRangeUnit;
            }
            // Create the Logical window of the stream
            final LogicalWindow lw = new LogicalWindow(Integer.parseInt(timeRange),
                  RegistrationInfo.getUnitOfMeasure(timeRangeUnit), Integer.parseInt(step),
                  RegistrationInfo.getUnitOfMeasure(stepUnit));
            stream = new StreamInfo(uri, lw);
         }
         streams.add(stream);
      }
      return streams;
   }

   private String[] getRangeAndUnit(String s) {
	   char[] chars = s.toCharArray();
	   
	   for (int i = chars.length - 1; i >= 0; i--) {
		  
		   if (!Character.isDigit(chars[i]))
				  continue;
		  
		   String[] ret = new String[2];
		   
		   ret[0] = s.substring(0, i + 1);
		   ret[1] = s.substring(i + 1);
		   
		   return ret;
	   }
	   
	   return null;
   }


/**
    * A CSparql method that search for queryName used in the registration.
    * 
    * @param tbx
    * @return the String of the name for CSparql query, null if the query is Sparql and
    *         doesn't have a name.
    */
   public String getQueryName() {
      // Skip if it's a SPARQL query
      if (this.getQueryLanguage().equals(QueryLanguages.SPARQL)) {
         return null;
      }

      String result = new String();
      if (this.getText().equals("queryName")) {
         return this.getChild(0).getText();
      } else {
         if (this.getChildren() != null) {
            for (final Object o : this.getChildren()) {
               final TreeBox t = (TreeBox) o;
               result += t.getQueryName();
            }
         }
      }
      return result;
   }

   /**
    * Check if in the tree is present the rule which identify the CSparql queries.
    * 
    * @return
    */
   public boolean isCSparql() {
      final TreeBox root = this.getRoot();
      if (root.existNode("registration")) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Get the rootNode of the supertree of the current node.
    * 
    * @return <code>this</code> if the node is already the root.
    */
   public TreeBox getRoot() {
      // Get root node
      TreeBox root = this;
      while (root.getParent() != null) {
         root = root.getParent();
      }
      return root;
   }

   /**
    * Return the query language specified in enum field <code>queryLanguages</code>.
    * 
    * @return
    */
   public QueryLanguages getQueryLanguage() {
      // Get root node
      final TreeBox root = this.getRoot();

      if (root.isCSparql()) {
         return QueryLanguages.CSPARQL;
      } else {
         return QueryLanguages.SPARQL;
      }
   }

   /**
    * Create and return a RegistrationInfo object with every usefull registration info on the
    * CSparql query
    * 
    * @return
    */
   public RegistrationInfo getRegistrationInfo() {

      if (this.isCSparql()) {
         RegistrationInfo ri = null;
         RegistrationInfo.QueryType type;
         // Find timeRangeuency & unit of measure if present in the registration statement
         final List<TreeBox> registration = this.getNodesByText("registration");
         String timeRange = new String();
         String unit = new String();
         List<TreeBox> tempNode;
         // Check if there is the registration statement
         if (!registration.isEmpty()) {
            tempNode = registration.get(0).getNodesByText("timeRange");
            // Check if there is the querytimeRange rule
            if (!tempNode.isEmpty()) {
               timeRange = tempNode.get(0).getChild(0).getText();
               unit = timeRange.substring(timeRange.length() - 1);
               timeRange = timeRange.substring(0, timeRange.length() - 1);
            }
            // Check if the registration is about a Stream
            tempNode = registration.get(0).getNodesByText("stream");
            if (!tempNode.isEmpty()) {
               type = RegistrationInfo.QueryType.STREAM;
            } else {
               type = RegistrationInfo.QueryType.QUERY;
            }
            ri = new RegistrationInfo(type, this.getQueryName(),
                  Integer.parseInt(timeRange), RegistrationInfo.getUnitOfMeasure(unit));
         }
         return ri;
      } else {
         return null;
      }
   }

   /**
    * Create a complete TreeBox tree from an antlr native ParseTree
    * 
    * @param parseTree
    * @return a TreeBox wrapper for ParseTree
    */
   public static TreeBox create(final ParseTree parseTree) {
      final TreeBox t = new TreeBox(parseTree.payload);
      t.pT = parseTree;

      for (int i = 0; i < t.pT.getChildCount(); i++) {
         final TreeBox cb = TreeBox.create((ParseTree) t.pT.getChild(i));
         cb.setParent(t);
         t.addChild(cb);
      }
      return t;
   }

   /**
    * Create a complete copy of the node t and all it's children with their labels.
    * 
    * @param t
    * @return
    */
   public static TreeBox dupFullTreeDecorated(final TreeBox t) {
      final TreeBox tbx = TreeBox.create(t.getParseTree());
      TreeBox.decorate(tbx);
      return tbx;
   }

   /**
    * Add labels to each node of the tree with a deepFirst algorithm
    * 
    * @param tbx
    */
   public static void decorate(final TreeBox tbx) {
      // Call recursive decoration
      tbx.deepFirstDecoration(tbx, 0);
   }

   private int deepFirstDecoration(final TreeBox tbx, int n) {
      if (tbx.getParent() == null) {
         // Set label of the root
         final Label l = new Label(0, 0, 0);
         tbx.setLabel(l);
      }

      if (tbx.getChildren() != null && tbx.getChildren().size() > 0) {
         for (int i = 0; i < tbx.children.size(); i++) {
            n++;
            final Label l = new Label(n, tbx.getLabel().getDeepLevel() + 1, i);
            // System.out.println(l.toString());
            tbx.getChild(i).setLabel(l);
            if (tbx.getChild(i).getChildren() != null
                  && tbx.getChild(i).getChildren().size() > 0) {
               // Call recursive
               n = this.deepFirstDecoration(tbx.getChild(i), n);
            }
         }
      }
      return n;
   }

   /**
    * Check if the nodeName exist in the subtree <b>ignoring the case</b>.
    * 
    * @param nodeName
    * @return
    */
   public boolean existNode(final String nodeName) {
      boolean result = false;
      if (this.getText().equalsIgnoreCase(nodeName)) {
         return true;
      }
      if (this.children != null) {
         for (final Object o : this.children) {
            final TreeBox t = (TreeBox) o;
            result = t.existNode(nodeName);
            if (result) {
               return result;
            }
         }
      }
      return result;
   }

   /**
    * Build a list with all the nodes in the subtree with <code>getText()</code> equal to
    * <code>nodeName</code>.
    * 
    * @param nodeName
    * @return
    */
   public List<TreeBox> getNodesByText(final String nodeName) {
      final List<TreeBox> nodes = new ArrayList<TreeBox>();
      if (this.getText().equals(nodeName)) {
         nodes.add(this);
      }
      if (this.children != null) {
         for (final Object o : this.children) {
            final TreeBox t = (TreeBox) o;
            nodes.addAll(t.getNodesByText(nodeName));
         }
      }
      nodes.remove(null);
      return nodes;
   }

   /**
    * Print the current node and all its subnodes
    */
   public void printFullTree() {
      for (int n = 0; n < this.getLabel().getDeepLevel(); n++) {
         System.out.print(" ");
      }
      System.out.println("Livello" + this.getLabel().getDeepLevel() + " - ID:"
            + this.getLabel().getId() + " - Testo:" + this.getText() + " - #Figli:"
            + this.getChildCount());
      for (int i = 0; i < this.getChildCount(); i++) {
         this.getChild(i).printFullTree();
      }
   }

   /**
    * Get a set of all the leaves nodes of the tree.
    * 
    * @return
    */
   public List<TreeBox> getLeaves() {
      final List leaves = new ArrayList();
      if (this.payload instanceof Token
            && (this.children == null || this.children.isEmpty())) { // leaf node token?
         if (!this.getText().equals("<EOF>")) {
            leaves.add(this);
         }
      } else if (this.children != null && !this.children.isEmpty()) {
         for (final Object o : this.children) {
            final TreeBox t = (TreeBox) o;
            leaves.addAll(t.getLeaves());
         }
      }
      return leaves;
   }

   /**
    * Create a string from the tree nodes content representing the executable query parsed as
    * input.
    * 
    * @return
    */
   @Override
   public String toInputString() {
      final StringBuffer sb = new StringBuffer();
      // _toStringLeaves(sb);
      this.printLeaves(sb);
      return sb.toString();
   }

   /**
    * Set <code>buf</code> with the concatenation of all the leaves nodes with a blank space
    * separator. Use <code>toInputString()</code> instead of this method if you want to have
    * a string with the input parsed.
    * 
    * @param buf
    */
   @Override
   public void _toStringLeaves(final StringBuffer buf) {
      final SparqlProducer sp = new SparqlProducer1_0();
      if (this.payload instanceof Token && !this.getText().equals("<EOF>")) { // leaf node
         // token?
         buf.append(sp.getTextWithSymbols(this));
         buf.append(" ");
         return;
      }
      for (int i = 0; this.children != null && i < this.children.size(); i++) {
         final ParseTree t = (ParseTree) this.children.get(i);
         t._toStringLeaves(buf);
      }
   }

   /**
    * TEMP!!
    * 
    * @param buf
    */
   public void printLeaves(final StringBuffer buf) {
      if (this.children == null || 0 == this.children.size()) {
         final SparqlProducer sp = new SparqlProducer1_0();
         buf.append(sp.getTextWithSymbols(this));
         buf.append(" ");
         System.out.println(this.getText());
      } else {
         for (int i = 0; this.children != null && i < this.children.size(); i++) {
            final TreeBox t = (TreeBox) this.children.get(i);
            t.printLeaves(buf);
         }
      }
   }
}
