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
    	
    	ToCSV.createRow(cls.getSimpleName(), classLines, cls.getFields().size(), cls.getMethods().size());	
    }
}