package MvnSpoon.codeSmells;

import spoon.Launcher;
import spoon.SpoonAPI;

public class SpoonMain {
	public static void main(String[] args) {
		String commonsPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\commons-collections";
		SpoonAPI commons = new Launcher();
		commons.addInputResource(commonsPath);
		commons.buildModel();
		ProcLongMethod plm = new ProcLongMethod();
		String xstreamPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\xstream";
		SpoonAPI xstream = new Launcher();
		xstream.addInputResource(xstreamPath);
		xstream.buildModel();
		
		System.out.println("### LONG METHOD SMELL:");
		System.out.println(" ");
		System.out.println(">> COMMONS-COLLECTIONS: ");
		
		commons.addProcessor(plm);
		commons.process();
		
		System.out.println(" ");
		System.out.println(">> XSTREAM: ");
		
		xstream.addProcessor(new ProcLongMethod());
		xstream.process();
		
		System.out.println(" ");
		
		System.out.println("### LARGE CLASS SMELL:");
		System.out.println(" ");
		System.out.println(">> COMMONS-COLLECTIONS: ");
		
		commons.addProcessor(new ProcLargeClass());
		commons.process();
		
		System.out.println(" ");
		System.out.println(">> XSTREAM: ");
		
		xstream.addProcessor(new ProcLargeClass());
		xstream.process();
	}
}
