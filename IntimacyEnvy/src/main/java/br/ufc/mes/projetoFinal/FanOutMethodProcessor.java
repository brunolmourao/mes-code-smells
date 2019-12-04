package br.ufc.mes.projetoFinal;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

public class FanOutMethodProcessor extends AbstractProcessor<CtMethod<?>> {
	
	public void process(CtMethod<?> element) {
		// TODO Auto-generated method stub
		//int fan_out = 0
		//for() {
		//	
		//}
		int fan_out = 0;
		System.out.println("Elemento: "+ element.getSimpleName());
		System.out.println("Fan out Elements: ");
		for(CtTypeReference a : element.getReferencedTypes()) {
			if(!a.isShadow() && !a.isPrimitive() && !a.toString().equals(element.getSimpleName()) 
					&& a.toString().contains("") && !a.toString().contains("exception")
					&& !a.toString().contains("Exception") && !a.toString().contains("java")) {
				fan_out += 1;
				System.out.println(a.toString());
				
			}
		}
		System.out.println("Elemento: "+ element.getSimpleName());
		System.out.println("Fan out " + fan_out);
		System.out.println("///////////////////////////////");
	}

}
