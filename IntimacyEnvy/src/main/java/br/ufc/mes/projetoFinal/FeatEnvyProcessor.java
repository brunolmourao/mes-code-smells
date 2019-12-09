package br.ufc.mes.projetoFinal;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class FeatEnvyProcessor extends AbstractProcessor<CtClass<?>> {
	
	TypeFilter<CtInvocation<?>> invocationFilter = new TypeFilter<CtInvocation<?>>(CtInvocation.class);
	TypeFilter<CtFieldAccess<?>> fieldAccessFilter = new TypeFilter<CtFieldAccess<?>>(CtFieldAccess.class);
	
	public void process(CtClass<?> element) {
		if(App.classMethods.get(element.getQualifiedName()) != null) {
			for (CtMethod<?> method : element.getAllMethods()) {
				int dataTotal = 0, dataInternal = 0, dataExternal = 0;
				if(App.classMethods.get(element.getQualifiedName()).contains(method.getSignature())) {
					for (CtInvocation<?> invocation : method.getElements(invocationFilter)) {
						//System.out.println("Invocation: " + invocation.getExecutable().getSignature());
						dataTotal += 1;
						if(App.classInvocations.get(element.getQualifiedName()) != null) {
							if(App.classInvocations.get(element.getQualifiedName()).contains(invocation.getExecutable().getSignature())) {
								dataExternal += 1;
							} else {
								dataInternal +=1;
							}
						}
					}
					for (CtFieldAccess<?> fieldAccess : method.getElements(fieldAccessFilter)) {
						//System.out.println("Field Access: " + fieldAccess.getVariable().getQualifiedName());
						dataTotal += 1;
						if(App.classAccessFields.get(element.getQualifiedName()) != null) {
							if(App.classAccessFields.get(element.getQualifiedName()).contains(fieldAccess.getVariable().getQualifiedName())) {
								dataExternal += 1;
							} else {
								dataInternal +=1;
							}
						}
					}
					if(dataInternal < dataExternal && dataExternal > 0 && dataTotal > 1) {
						System.out.println("Class: " + element.getQualifiedName());
						System.out.println("Method: " + method.getSignature());
						System.out.println("Total: " + dataTotal);
						System.out.println("Interno: " + dataInternal);
						System.out.println("Externo: " + dataExternal);
					}
				}
			}
		}
	}
}
