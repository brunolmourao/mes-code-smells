package br.ufc.mes.projetoFinal;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class FanInClassProcessor extends AbstractProcessor<CtClass<?>> {
	
	public void process(CtClass<?> element) {
		int fanIn = 0;
		if (App.called.get(element.getQualifiedName()) != null) {
			fanIn = App.called.get(element.getQualifiedName()).size();
			System.out.println("FanIn: " + element.getQualifiedName() + " = " + fanIn);
			System.out.println("FanIn Elements: " + App.called.get(element.getQualifiedName()));
		}
	}

}
