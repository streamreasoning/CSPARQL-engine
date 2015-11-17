package eu.larck.csparql.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.core.ResultFormatter;

public class Counter extends ResultFormatter {
	private List<Integer> counts; 
	
	public Counter() {
		counts = new ArrayList<Integer>();	
	}
	
	public List<Integer> getResults(){
		return counts;
	}

	@Override
	public void update(Observable o, Object arg) {
		RDFTuple t = ((RDFTable)arg).getTuples().iterator().next();
		counts.add(Integer.parseInt(t.get(0).split("\"")[1]));
	}
}
