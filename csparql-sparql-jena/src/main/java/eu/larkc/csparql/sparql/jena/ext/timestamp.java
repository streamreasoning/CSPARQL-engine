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
package eu.larkc.csparql.sparql.jena.ext;

import java.util.Map;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;
import com.hp.hpl.jena.rdf.model.impl.StatementImpl;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.function.FunctionBase3;


public class timestamp extends FunctionBase3 {

	static int i = 0;
	public static Map<Statement,Long> timestamps;

	//	@Override
	//	public NodeValue exec(NodeValue v) {
	//		String key = v.asString();
	//		if (timestamps.containsKey(key)) {
	//			NodeValue ts=NodeValue.makeInteger(timestamps.get(key));
	//			
	//			return ts;
	//		} else {
	//			return NodeValue.makeBoolean(false);
	//		}
	//	}

	@Override
	public NodeValue exec(NodeValue arg0, NodeValue arg1, NodeValue arg2) {

		Statement key;

		if(arg2.isLiteral()){
			String[] objectParts = arg2.asString().split("\\^\\^");
			TypeMapper tm = TypeMapper.getInstance();
			//			RDFDatatype d = tm.getTypeByName(objectParts[1]);
			RDFDatatype d = null;
			if (objectParts.length > 1) {
				d = tm.getTypeByName(objectParts[1]);
			} else {
				d = XSDDatatype.XSDstring;
			}
			Model model = ModelFactory.createDefaultModel();
			Literal lObject = model.createTypedLiteral(objectParts[0].replaceAll("\"", ""),d);
			key = new StatementImpl(new ResourceImpl(arg0.asString()), new PropertyImpl(arg1.asString()), lObject); 

			lObject = null;
			model = null;

		} else {
			key = new StatementImpl(new ResourceImpl(arg0.asString()), new PropertyImpl(arg1.asString()), new ResourceImpl(arg2.asString())); 

		}

		if (timestamps.containsKey(key)) {
			NodeValue ts=NodeValue.makeInteger(timestamps.get(key));

			return ts;
		} else {
			return NodeValue.makeBoolean(false);
		}
	}

}
