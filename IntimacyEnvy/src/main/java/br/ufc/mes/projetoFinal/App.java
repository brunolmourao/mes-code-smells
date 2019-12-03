package br.ufc.mes.projetoFinal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.SpoonAPI;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtImport;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class App 
{
	
	public static HashMap<String, Set<String>> caller = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> called = new HashMap<String, Set<String>>();

	private static void addCall(String caller, String called) {
		if (!App.caller.containsKey(caller)) {
			App.caller.put(caller, new HashSet<String>());
		}

		App.caller.get(caller).add(called);

		if (!App.called.containsKey(called)) {
			App.called.put(called, new HashSet<String>());
		}

		App.called.get(called).add(caller);

	}
	
    public static void main( String[] args )
    {
    	//String path = "C:\\Users\\Usuário\\git\\bank-sys\\src\\main";
    	String path = "C:\\Users\\Usuário\\Desktop\\commons-collections-master\\commons-collections-master\\src\\main";
        SpoonAPI api = new Launcher();
        api.addInputResource(path);
        api.buildModel();
        //api.addProcessor("br.ufc.mes.projetoFinal.FanOutClassProcessor");
        //api.addProcessor("br.ufc.mes.projetoFinal.FanInClassProcessor");
        api.addProcessor("br.ufc.mes.projetoFinal.InapIntProcessor");
        
        Collection<CtType<?>> types = api.getModel().getAllTypes();
		TypeFilter<CtInvocation<?>> invocationFilter = new TypeFilter<CtInvocation<?>>(CtInvocation.class);
		
		Set<String> typeSet = new HashSet<String>();
		for (CtType<?> type : types) {
			if (!type.isShadow() && !type.isPrimitive() && !type.getQualifiedName().contains("exception") 
					&& !type.getQualifiedName().contains("Exception")) {
				typeSet.add(type.getQualifiedName());
			}
		}
		for (CtType<?> type : types) {
			if (!type.isShadow() && !type.isPrimitive() && !type.getQualifiedName().contains("exception") 
					&& !type.getQualifiedName().contains("Exception")) {
				String caller = type.getQualifiedName();
				for (CtInvocation<?> invocation : type.getElements(invocationFilter)) {
					String called = invocation.getExecutable().getDeclaringType().getQualifiedName();
					if (typeSet.contains(called)) {
						App.addCall(caller, called);
					}
				}
				
			}
		}
		
        api.process();
        
    }
}
