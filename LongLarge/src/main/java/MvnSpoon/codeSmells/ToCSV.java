package MvnSpoon.codeSmells;

import java.util.ArrayList;
import java.util.List;

public class ToCSV {
	
	private String projectName;
	private String header;
	private static List<String> base = new ArrayList<String>();
	
	public ToCSV(String projectName, int smellType) {
		this.projectName = projectName;
		if (smellType == 0) {
			this.header = "class_name,method_name,code_lines";
			base.add(header);
		}
		else {
			this.header = "class_name,code_lines,fields_num,methods_num";
			base.add(header);
		}
	}
	
	public List<String> getBase() {
		return base;
	}
	
	// LONG CLASS
	public static void createRow(String className, int classLines, int fieldNum, int methodNum) {
		String row = "";
		row = className + "," + classLines + "," + fieldNum + "," + methodNum;
        base.add(row);
	}
	
	// LONG METHOD
	public static void createRow(String className, String methodName, int methodLines) {
		String row = "";
		row = className + "," + methodName + "," + Integer.toString(methodLines);	
		base.add(row);
	}
	
	public void createCSV() {
		
	}
}
