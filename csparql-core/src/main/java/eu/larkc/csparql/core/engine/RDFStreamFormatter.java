package eu.larkc.csparql.core.engine;

import java.util.GregorianCalendar;

import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;
import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.common.streams.format.GenericObservable;
import eu.larkc.csparql.common.streams.format.GenericObserver;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;



public class RDFStreamFormatter extends RdfStream implements  GenericObserver<RDFTable> {

	public RDFStreamFormatter(String iri) {
		super(iri);
		// TODO Auto-generated constructor stub
	}

	public void update(GenericObservable<RDFTable> observed, RDFTable q) {
		
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
