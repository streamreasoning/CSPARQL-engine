package eu.larkc.csparql.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringWriter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class CsparqlUtils {
	
	public static String serializeRDFFile(String filePath) throws Exception{
		File f = new File(filePath);
		Model m = ModelFactory.createDefaultModel();
		try{
			m.read(FileManager.get().open(f.getAbsolutePath()), null, "RDF/XML");
		} catch(Exception e){
			try{
				m.read(FileManager.get().open(f.getAbsolutePath()), null, "TURTLE");
			} catch(Exception e1){
				try{
					m.read(FileManager.get().open(f.getAbsolutePath()), null, "N-TRIPLE");
				} catch(Exception e2){
					m.read(FileManager.get().open(f.getAbsolutePath()), null, "RDF/JSON");
				}
			}
		}
		StringWriter sw = new StringWriter();
		m.write(sw);
		return sw.toString();
	}
	
	public static String serializeJenaModel(Model model) throws Exception{
		StringWriter sw = new StringWriter();
		model.write(sw);
		return sw.toString();
	}
	
	public static String fileToString(String filePath) throws Exception {
		File f = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(f));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}


}
