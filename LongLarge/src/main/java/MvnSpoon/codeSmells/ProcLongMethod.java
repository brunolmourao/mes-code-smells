package MvnSpoon.codeSmells;

import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class ProcLongMethod extends AbstractProcessor<CtClass<?>> {
	List<String> rows = new ArrayList<String>();
	
    public void process(CtClass<?> cls) {
    	int methodBegin = 0;
    	int methodEnd = 0;
    	int methodLines = 0;
    	String line = "";
    	
    	System.out.println("> CLASS NAME: " + cls.getSimpleName());
    	for (CtMethod<?> method : cls.getMethods()) {
    		methodBegin = method.getPosition().getLine();
        	methodEnd = method.getPosition().getEndLine();
        	methodLines = methodEnd - methodBegin + 1;
        		
            System.out.println("     - Method Name: " + method.getSimpleName());
            System.out.println("     - Method Lines: " + methodLines);
            System.out.println(" ");
            
            line.concat(cls.getSimpleName());
            line.concat(",");
            line.concat(method.getSimpleName());
            line.concat(",");
            line.concat(Integer.toString(methodLines));
            line.concat("\n");
            rows.add(line);
    	}
    }
}