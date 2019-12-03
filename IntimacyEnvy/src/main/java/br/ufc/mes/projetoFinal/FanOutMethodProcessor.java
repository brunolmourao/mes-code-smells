package br.ufc.mes.projetoFinal;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

public class FanOutMethodProcessor extends AbstractProcessor<CtMethod<?>> {
	
	public void process(CtMethod<?> element) {
		// TODO Auto-generated method stub
		//int fan_out = 0
		//for() {
		//	
		//}
		int num_tipos = element.getReferencedTypes().size();
		System.out.println("Elemento: "+ element.getSimpleName());
		System.out.println("Depende de: "+ element.getReferencedTypes());
		System.out.println("Fan out " + num_tipos);
		System.out.println("///////////////////////////////");
	}

}
