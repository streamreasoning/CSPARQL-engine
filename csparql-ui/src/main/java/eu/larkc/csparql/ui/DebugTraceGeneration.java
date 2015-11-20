package eu.larkc.csparql.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DebugTraceGeneration {
	public static void main(String args[]) {
		File output = new File("./src/main/resources/sample_debug_input.txt");
		FileWriter fw= null;
		try {
			fw = new FileWriter(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		long i = Long.parseLong("1447758448965");
		int j = 0;
		for (j = 0; j < 10000; j++) {
			try {
				fw.write("http://myexample.org/S" + j + "\thttp://myexample.org/P" + j + "\thttp://myexample.org/O" + j + "\t" + (i+j)+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
