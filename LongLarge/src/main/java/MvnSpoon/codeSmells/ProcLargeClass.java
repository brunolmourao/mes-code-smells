package MvnSpoon.codeSmells;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class ProcLargeClass extends AbstractProcessor<CtClass<?>> {
	
    public void process(CtClass<?> cls) {
    	int classBegin = 0;
    	int classEnd = 0;
    	int classLines = 0;
    	
    	classBegin = cls.getPosition().getLine();
    	classEnd = cls.getPosition().getEndLine();
    	classLines = classEnd - classBegin + 1;
    	
    	System.out.println("> CLASS NAME: " + cls.getSimpleName());
    	System.out.println("     - Class Lines: " + classLines);
    	System.out.println("     - Number of Fields: " + cls.getFields().size());
    	System.out.println("     - Number of Methods: " + cls.getMethods().size());
    }
}