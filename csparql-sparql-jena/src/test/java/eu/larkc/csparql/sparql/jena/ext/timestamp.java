package eu.larkc.csparql.sparql.jena.ext;

import java.util.Map;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.TypeMapper;
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
			RDFDatatype d = tm.getTypeByName(objectParts[1]);
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
