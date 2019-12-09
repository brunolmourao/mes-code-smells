package br.ufc.mes.projetoFinal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.MavenLauncher.SOURCE_TYPE;
import spoon.SpoonAPI;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtImport;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.FieldAccessFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class App 
{
	public static HashMap<String, Set<String>> classInvocations = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> classMethods = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> classAccessFields = new HashMap<String, Set<String>>();
	public static HashMap<String, Set<String>> classFields = new HashMap<String, Set<String>>();

	private static void addInvocation(String invocation, String type) {
		if (!App.classInvocations.containsKey(type)) {
			App.classInvocations.put(type, new HashSet<String>());
		}

		App.classInvocations.get(type).add(invocation);

	}
	
	private static void addMethod(String method, String type) {
		if (!App.classMethods.containsKey(type)) {
			App.classMethods.put(type, new HashSet<String>());
		}

		App.classMethods.get(type).add(method);

	}
	
	private static void addAccessField(String invocation, String type) {
		if (!App.classAccessFields.containsKey(type)) {
			App.classAccessFields.put(type, new HashSet<String>());
		}

		App.classAccessFields.get(type).add(invocation);

	}
	
	private static void addField(String field, String type) {
		if (!App.classFields.containsKey(type)) {
			App.classFields.put(type, new HashSet<String>());
		}

		App.classFields.get(type).add(field);

	}
	
    public static void main( String[] args )
    {
    	//String path = "C:\\Users\\Bruno\\Documents\\bank-sys\\src\\main";
    	String path = "C:\\Users\\Usuário\\Desktop\\commons-collections-master\\commons-collections-master\\src\\main";
    	//String path = "C:\\Users\\Usuário\\Desktop\\xstream-master\\xstream-master\\";
    	SpoonAPI api = new Launcher();
        //MavenLauncher api = new MavenLauncher(path, MavenLauncher.SOURCE_TYPE.APP_SOURCE);
        api.addInputResource(path);
        api.buildModel();
        api.addProcessor("br.ufc.mes.projetoFinal.FeatEnvyProcessor");
        
        Collection<CtType<?>> types = api.getModel().getAllTypes();
		TypeFilter<CtInvocation<?>> invocationFilter = new TypeFilter<CtInvocation<?>>(CtInvocation.class);
		TypeFilter<CtFieldAccess<?>> fieldAccessFilter = new TypeFilter<CtFieldAccess<?>>(CtFieldAccess.class);
		
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
				
				String typeName = type.getQualifiedName();
				for (CtInvocation<?> invocation : type.getElements(invocationFilter)) {
					String invocationDeclarer = invocation.getExecutable().getDeclaringType().getQualifiedName();
					if (typeSet.contains(invocationDeclarer)) {
						addInvocation(invocation.getExecutable().getSignature(), typeName);
						//System.out.println("Invocation: " + invocation.getExecutable().getSignature());
					}
				}
				
				for (CtMethod<?> method : type.getAllMethods()) {
					String methodDeclarer = method.getDeclaringType().getQualifiedName();
					if (typeSet.contains(methodDeclarer)) {
						addMethod(method.getSignature(), typeName);
						//System.out.println("Method:" + method.getSignature());
					}
				}
				
				for (CtFieldAccess<?> fieldAccess : type.getElements(fieldAccessFilter)) {
					String fieldAccessDeclarer = fieldAccess.getVariable().getDeclaringType().getQualifiedName();
					//System.out.println("Tipo Access: " + typeName);
					//System.out.println("Tipo Field Access: " + fieldAccessDeclarer);
					//System.out.println("Field Access: " + fieldAccess.toString());
					if (typeSet.contains(fieldAccessDeclarer)) {
						addAccessField(fieldAccess.getVariable().getQualifiedName(), typeName);
						//System.out.println("Field Access: " + fieldAccess.toString());
					}
				}
				
				
				for (CtFieldReference<?> field : type.getAllFields()) {
					String fieldDeclarer = field.getDeclaringType().getQualifiedName();
					//System.out.println("Tipo: " + fieldDeclarer);
					//System.out.println("Field: " + field.getSimpleName());
					if (typeSet.contains(fieldDeclarer)) {
						addField(field.getQualifiedName(), typeName);
						//System.out.println("Field: " + field.getQualifiedName());
						
					}
				}
				
				
				
				try {
					classInvocations.get(typeName).removeAll((Collection<String>) classMethods.get(typeName));
				} catch(NullPointerException e) {

				}
				
				try {
					classAccessFields.get(typeName).removeAll((Collection<String>) classFields.get(typeName));
				} catch(NullPointerException e) {
					
				}
				
				int totalExterno = 0;
				if(classInvocations.get(type.getQualifiedName()) != null && !classInvocations.get(type.getQualifiedName()).isEmpty()) {
					System.out.println("Invocations em " + type.getQualifiedName() + ": " + classInvocations.get(type.getQualifiedName()));
					totalExterno += classInvocations.get(type.getQualifiedName()).size();
				}
				if(classAccessFields.get(type.getQualifiedName()) != null && !classAccessFields.get(type.getQualifiedName()).isEmpty()) {
					System.out.println("Field Access em " + type.getQualifiedName() + ": " + classAccessFields.get(type.getQualifiedName()));
					totalExterno += classAccessFields.get(type.getQualifiedName()).size();
				}
				if(totalExterno > 0) {
					System.out.println("Total em " + type.getQualifiedName() + ": " + totalExterno);
					System.out.println("///////////////////////////////////////////");
				}
			}	
			
		}
		
        api.process();
        
    }
}
