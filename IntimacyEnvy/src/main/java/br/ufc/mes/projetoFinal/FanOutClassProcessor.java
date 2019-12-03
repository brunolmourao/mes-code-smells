package br.ufc.mes.projetoFinal;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtTypeReference;

public class FanOutClassProcessor extends AbstractProcessor<CtClass<?>> {
	
	public void process(CtClass<?> element) {
		// TODO Auto-generated method stub
		int fan_out = 0;
		if(!element.getQualifiedName().contains("exception") && !element.getQualifiedName().contains("Exception")){
			System.out.println("Elemento: "+element.getQualifiedName());
			System.out.println("Depende de: ");
			String pacoteTodo = element.getPackage().toString();
			String[] pacote = pacoteTodo.split("\\.");
			System.out.println("Pacote: "+ pacote[0]);
			for(CtTypeReference a : element.getReferencedTypes()) {
				if(!a.isShadow() && !a.isPrimitive() && !a.toString().equals(element.getQualifiedName()) 
						&& a.toString().contains(pacote[0]) && !a.toString().contains("exception")
						&& !a.toString().contains("Exception") && !a.toString().contains("java")) {
					fan_out += 1;
					System.out.println(a.toString());
					
				}
			}
			System.out.println("Fan out " + fan_out);
			System.out.println("///////////////////////////////");
		}
	}

}
