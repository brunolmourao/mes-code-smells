package MvnSpoon.codeSmells;

import java.util.Scanner;

import spoon.Launcher;
import spoon.MavenLauncher;

public class SpoonMain {
	public static void main(String[] args) {
		String commonsPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\commons-collections";
		MavenLauncher commons = new MavenLauncher(commonsPath, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
		commons.buildModel();
		
		String xstreamPath = "C:\\Users\\Eve\\Documents\\Eve\\Computaçao\\MANEVO\\xstream";
		Launcher xstream = new Launcher();
		xstream.addInputResource(xstreamPath);
		xstream.buildModel();
		
		System.out.println("### SMELLY CODE");
		System.out.println("-- 0 To Long Method");
		System.out.println("-- 1 To Large Class");
		
		Scanner input = new Scanner(System.in);
		int smell = input.nextInt();
		input.close();
		
		if (smell == 0) {
			System.out.println("### LONG METHOD SMELL:");
			System.out.println(" ");
			
			System.out.println(">> COMMONS-COLLECTIONS: ");
			AuxLongMethod.processLM(commons, "commonLM");
			
			System.out.println(" ");
			
			System.out.println(">> XSTREAM: ");
			AuxLongMethod.processLM(xstream, "xstreamLM");
		}
		
		else if (smell == 1) {
			System.out.println("### LARGE CLASS SMELL:");
			
			System.out.println(" ");
			
			System.out.println(">> COMMONS-COLLECTIONS: ");
			AuxLargeClass.processLC(commons, "commonLC");
			
			System.out.println(" ");
			
			System.out.println(">> XSTREAM: ");
			AuxLargeClass.processLC(xstream, "xstreamLC");
		}
		
		else {
			System.out.println("Invalid Input...");
		}
	}
}
