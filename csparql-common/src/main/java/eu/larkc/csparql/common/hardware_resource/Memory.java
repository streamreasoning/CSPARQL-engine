package eu.larkc.csparql.common.hardware_resource;

public class Memory {
	
	private static Runtime runtime = Runtime.getRuntime();
	private static int mb = 1024*1024;
	
	public static double getTotalMemory(){
		return runtime.totalMemory() / mb;
	}
	
	public static double getFreeMemory(){
		return runtime.freeMemory() / mb;
	}
	
	public static double getMemoryUsage(){
		return (runtime.totalMemory() - runtime.freeMemory()) / mb;
	}

}
