package MvnSpoon.codeSmells;

import spoon.Launcher;

public class AuxLargeClass {
	
	public static void processLC(Launcher launcher, String csvName) {
		
		ProcLargeClass plc = new ProcLargeClass();
		new ToCSV(csvName, 1);
		
		launcher.addProcessor(plc);
		System.out.println("-- Processing the project...");
		launcher.process();
		System.out.println("-- Generating CSV file...");
		ToCSV.createCSV();
		System.out.println("-- CSV CREATED!!!");
	}
}
