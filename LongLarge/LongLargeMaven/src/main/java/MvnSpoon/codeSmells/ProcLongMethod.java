package MvnSpoon.codeSmells;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class ProcLongMethod extends AbstractProcessor<CtClass<?>> {
	
    public void process(CtClass<?> cls) {
    	int methodBegin = 0;
    	int methodEnd = 0;
    	int methodLines = 0;
    	
    	for (CtMethod<?> method : cls.getMethods()) {
    		if (!method.isAbstract()) {
    			methodBegin = method.getPosition().getLine();
            	methodEnd = method.getPosition().getEndLine();
            	methodLines = methodEnd - methodBegin + 1;
            	
            	ToCSV.createRow(cls.getSimpleName(), method.getSimpleName(), methodLines);
    		}          
    	}
    }
}