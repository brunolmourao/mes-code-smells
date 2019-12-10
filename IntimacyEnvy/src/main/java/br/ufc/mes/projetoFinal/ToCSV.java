package br.ufc.mes.projetoFinal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToCSV {
	
	private static String projectName;
	private String header;
	private static List<String> base = new ArrayList<String>();
	
	public ToCSV(String proName, int smellType, boolean criado) {
		setProName(proName);
		if (smellType == 0) {
			header = "class_name,num_internal,num_external \n";
			if(!criado) {
				base.add(header);
			}
		}
		else if (smellType == 1) {
			header = "class_name,method_name,data_total,data_internal,data_external \n";
			if(!criado) {
				base.add(header);
			}
		}
	}
	
	public static List<String> getBase() {
		return base;
	}
	
	public void setProName(String proName) {
		projectName = proName;
	}
	
	// INTIMACY
	public static void createRow(String className, int numInternal, int numExternal) {
		String row = "";
		row = className + "," + numInternal + "," + numExternal + "\n";
        base.add(row);
	}
	
	// ENVY
	public static void createRow(String className, String methodName, int dataTotal, int dataInternal, int dataExternal) {
		String row = "";
		row = className + "," + methodName + "," + dataTotal + "," + dataInternal + "," + dataExternal + "\n";	
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
