package MvnSpoon.codeSmells;

import spoon.Launcher;

public class AuxLongMethod {
	
	public static void processLM(Launcher launcher, String csvName) {
		
		ProcLongMethod plm = new ProcLongMethod();
		new ToCSV(csvName, 0);
		
		launcher.addProcessor(plm);
		System.out.println("-- Processing the project...");
		launcher.process();
		System.out.println("-- Generating CSV file...");
		ToCSV.createCSV();
		System.out.println("-- CSV CREATED!!!");
	}
}
