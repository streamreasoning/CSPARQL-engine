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
package eu.larkc.csparql.core.engine;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RDFStreamFormatter extends RdfStream implements  Observer {

	public RDFStreamFormatter(String iri) {
		super(iri);
		// TODO Auto-generated constructor stub
	}

	//	public void update(GenericObservable<RDFTable> observed, RDFTable q) {
	//		
	//		Model model = ModelFactory.createDefaultModel();
	//		
	//		int numberOfTriplesInRDFTable = 0;
	//		
	//		for (RDFTuple t : q) {
	//			numberOfTriplesInRDFTable++;
	//			String subject = t.get(0);
	//			String property = t.get(1);
	//			Resource rSubject = model.createResource(subject);
	//			Property pProperty = model.createProperty(property);
	//
	//			String object = t.get(2);
	//			// the following code does not work for blank nodes
	//			String[] objectParts = object.split("\\^\\^");
	//
	//			if (objectParts.length > 1) {
	//
	//				TypeMapper tm = TypeMapper.getInstance();
	//
	//				RDFDatatype RDFdt = tm.getTypeByName(objectParts[1]);
	//				Literal lObject = model.createTypedLiteral(objectParts[0].replaceAll("\"", ""),RDFdt);
	//
	//				//					Literal lObject = model.createLiteral(objectParts[0].replaceAll("\"", ""));
	//				rSubject.addLiteral(pProperty, lObject);
	//			} else {
	//				Resource rObject = model.createResource(object);
	//				rSubject.addProperty(pProperty, rObject);
	//			}
	//
	//		}
	//
	//		if(numberOfTriplesInRDFTable > 0){
	//
	//			StmtIterator si = model.listStatements();
	//			//publishModel.write(System.out);
	//			while (si.hasNext()){
	//				Statement s = si.next();
	//												
	//				RdfQuadruple quad = new RdfQuadruple(s.getSubject()
	//						.toString(), s.getPredicate().toString(), s.getObject()
	//						.toString(), new GregorianCalendar().getTimeInMillis());
	//				
	//				put(quad);
	//			}
	//		}
	//		
	//	}

	@Override
	public void update(Observable o, Object arg) {
		
		RDFTable q = (RDFTable) arg;
		
		Model model = ModelFactory.createDefaultModel();

		int numberOfTriplesInRDFTable = 0;

		for (RDFTuple t : q) {
			numberOfTriplesInRDFTable++;
			String subject = t.get(0);
			String property = t.get(1);
			Resource rSubject = model.createResource(subject);
			Property pProperty = model.createProperty(property);

			String object = t.get(2);
			// the following code does not work for blank nodes
			String[] objectParts = object.split("\\^\\^");

			if (objectParts.length > 1) {

				TypeMapper tm = TypeMapper.getInstance();

				RDFDatatype RDFdt = tm.getTypeByName(objectParts[1]);
				Literal lObject = model.createTypedLiteral(objectParts[0].replaceAll("\"", ""),RDFdt);

				//					Literal lObject = model.createLiteral(objectParts[0].replaceAll("\"", ""));
				rSubject.addLiteral(pProperty, lObject);
			} else {
				Resource rObject = model.createResource(object);
				rSubject.addProperty(pProperty, rObject);
			}

		}

		if(numberOfTriplesInRDFTable > 0){

			StmtIterator si = model.listStatements();
			//publishModel.write(System.out);
			while (si.hasNext()){
				Statement s = si.next();

				RdfQuadruple quad = new RdfQuadruple(s.getSubject()
						.toString(), s.getPredicate().toString(), s.getObject()
						.toString(), new GregorianCalendar().getTimeInMillis());

				put(quad);
			}
		}

	}


}
