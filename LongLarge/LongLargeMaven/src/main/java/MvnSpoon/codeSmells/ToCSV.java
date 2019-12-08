package MvnSpoon.codeSmells;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToCSV {
	
	private static String projectName;
	private String header;
	private static List<String> base = new ArrayList<String>();
	
	public ToCSV(String proName, int smellType) {
		setProName(proName);
		if (smellType == 0) {
			header = "class_name,method_name,code_lines \n";
			base.add(header);
		}
		else if (smellType == 1) {
			header = "class_name,code_lines,fields_num,methods_num \n";
			base.add(header);
		}
	}
	
	public static List<String> getBase() {
		return base;
	}
	
	public void setProName(String proName) {
		projectName = proName;
	}
	
	// LARGE CLASS
	public static void createRow(String className, int classLines, int fieldNum, int methodNum) {
		String row = "";
		row = className + "," + classLines + "," + fieldNum + "," + methodNum + "\n";
        base.add(row);
	}
	
	// LONG METHOD
	public static void createRow(String className, String methodName, int methodLines) {
		String row = "";
		row = className + "," + methodName + "," + Integer.toString(methodLines) + "\n";	
		base.add(row);
	}
	
	public static void createCSV() {
		String fileName = projectName + ".csv";
		
		try {
			FileWriter wry = new FileWriter(fileName, true);
			for (String row: base) {
				wry.write(row.toString());
			}
			wry.close();
			base.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}