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
package eu.larkc.csparql.core.engine;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.TypeMapper;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

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
