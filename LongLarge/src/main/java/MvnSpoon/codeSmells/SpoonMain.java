package MvnSpoon.codeSmells;

import spoon.MavenLauncher;

public class SpoonMain {
	public static void main(String[] args) {
		String commonsPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\commons-collections";
		MavenLauncher commons = new MavenLauncher(commonsPath, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
		commons.buildModel();
		
		String xstreamPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\xstream";
		MavenLauncher xstream = new MavenLauncher(xstreamPath, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
		xstream.buildModel();
		
		//#######################################//
		
		System.out.println("### LONG METHOD SMELL:");
		
		ProcLongMethod plm = new ProcLongMethod();
		
		System.out.println(" ");
		
		System.out.println(">> COMMONS-COLLECTIONS: ");
		ToCSV commonCSVLM = new ToCSV("commonLM", 0);
		commons.addProcessor(plm);
		System.out.println("-- Processing the project...");
		commons.process();
		System.out.println("-- Generating CSV file...");
		commonCSVLM.createCSV();
		System.out.println("-- CSV CREATED!!!");
		
		System.out.println(" ");
		
		//#######################################//		
		
		System.out.println(">> XSTREAM: ");
		ToCSV xstreamCSVLM = new ToCSV("xstreamLM", 0);
		xstream.addProcessor(plm);
		System.out.println("-- Processing the project...");
		xstream.process();
		System.out.println("-- Generating CSV file...");
		xstreamCSVLM.createCSV();
		System.out.println("-- CSV CREATED!!!");
		
		System.out.println(" ");
		
		//#######################################//
		
		System.out.println("### LARGE CLASS SMELL:");
		
		ProcLargeClass plc = new ProcLargeClass();
		
		System.out.println(" ");
		
		System.out.println(">> COMMONS-COLLECTIONS: ");
		ToCSV commonCSVLC = new ToCSV("commonLC", 1);
		commons.addProcessor(plc);
		System.out.println("-- Processing the project...");
		commons.process();
		System.out.println("-- Generating CSV file...");
		commonCSVLC.createCSV();
		System.out.println("-- CSV CREATED!!!");
		
		System.out.println(" ");
		
		//#######################################//
		
		System.out.println(">> XSTREAM: ");
		ToCSV xstreamCSVLC = new ToCSV("xstreamLC", 1);
		xstream.addProcessor(plc);
		System.out.println("-- Processing the project...");
		xstream.process();
		System.out.println("-- Generating CSV file...");
		xstreamCSVLC.createCSV();
		System.out.println("-- CSV CREATED!!!");
	}
}
