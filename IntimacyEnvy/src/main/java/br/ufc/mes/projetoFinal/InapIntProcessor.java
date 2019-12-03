package br.ufc.mes.projetoFinal;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.AbstractFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class InapIntProcessor extends AbstractProcessor<CtClass<?>> {
	int classes = 0;
	int inap_int = 0;
	
	public void process(CtClass<?> element) {
		int fan_in = 0;
		int fan_out = 0;
		if(!element.getQualifiedName().contains("exception") && !element.getQualifiedName().contains("Exception")){
			if (App.called.get(element.getQualifiedName()) != null) {
				fan_in = App.called.get(element.getQualifiedName()).size();
			}
			System.out.println("Elemento: "+element.getQualifiedName());
			//String pacoteTodo = element.getPackage().toString();
			//String[] pacote = pacoteTodo.split("\\.");
			System.out.println("Fan out Elements: ");
			for(CtTypeReference a : element.getReferencedTypes()) {
				if(!a.isShadow() && !a.isPrimitive() && !a.toString().equals(element.getQualifiedName()) 
						&& a.toString().contains("org.apache") && !a.toString().contains("exception")
						&& !a.toString().contains("Exception") && !a.toString().contains("java")) {
					
					fan_out += 1;
					System.out.println(a);
				}
			}
			classes += 1;
			System.out.println("Fan out " + fan_out);
			if(App.called.get(element.getQualifiedName()) != null) {
				System.out.println("Fan in Elements: " + App.called.get(element.getQualifiedName()));
			}
			System.out.println("Fan in " + fan_in);
			
			if(fan_out > fan_in && fan_out > 0) {
				inap_int += 1;
			}
			System.out.println("///////////////////////////////");
		}

	}
}
