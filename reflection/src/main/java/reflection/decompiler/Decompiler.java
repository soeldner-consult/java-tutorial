package reflection.decompiler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class Decompiler {

	public String decompile(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder(getClassInfo(clazz))
		    .append(" {\n");
		
		// Iterate over all constructors and add them to the output string
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
		    stringBuilder.append("  ").append(getConstructorInfo(constructor)).append("\n");
		}
		
		// Iterate over all fields and add them to the output string
		for (Field field : clazz.getDeclaredFields()) {
		    stringBuilder.append("  ").append(getFieldInfo(field)).append("\n");
		}
		
		// Iterate over all methods and add them to the output string
		for (Method method : clazz.getDeclaredMethods()) {
		    stringBuilder.append("  ").append(getMethodInfo(method)).append("\n");
		}
		
		// Finalize class body
		stringBuilder.append("}");
		
		// Return aggregated representation of the class
		return stringBuilder.toString();
	}

	protected String getClassInfo(Class<?> clazz) {
	    StringBuilder stringBuilder = new StringBuilder();
	
	    // Add class modifiers
	    stringBuilder.append(Modifier.toString(clazz.getModifiers())).append(" ");
	
	    // Add class keyword and name
	    stringBuilder.append(clazz.isInterface() ? "interface " : "class ");
	    stringBuilder.append(clazz.getSimpleName()).append(" ");
	
	    // Add superclass (if not Object)
	    Class<?> superclass = clazz.getSuperclass();
	    if (superclass != null && superclass != Object.class) {
	        stringBuilder.append("extends ").append(superclass.getSimpleName()).append(" ");
	    }
	
	    // Add implemented interfaces
	    Class<?>[] interfaces = clazz.getInterfaces();
	    if (interfaces.length > 0) {
	        stringBuilder.append("implements ");
	        for (int i = 0; i < interfaces.length; i++) {
	            stringBuilder.append(interfaces[i].getSimpleName());
	            if (i < interfaces.length - 1) {
	                stringBuilder.append(", ");
	            }
	        }
	    }
	    return stringBuilder.toString();
	}

	protected String getFieldInfo(Field field) {
	    StringBuilder stringBuilder = new StringBuilder();
	
	    // Add field modifiers
	    stringBuilder.append(Modifier.toString(field.getModifiers())).append(" ");
	
	    // Add field type and name
	    stringBuilder.append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";");
	
	    return stringBuilder.toString();
	}

	protected String getConstructorInfo(Constructor<?> constructor) {
	    StringBuilder stringBuilder = new StringBuilder();
	
	    // Add constructor modifiers
	    stringBuilder.append(Modifier.toString(constructor.getModifiers())).append(" ");
	
	    // Add constructor name
	    stringBuilder.append(constructor.getDeclaringClass().getSimpleName()).append("(");
	
	    // Add parameters
	    Class<?>[] parameterTypes = constructor.getParameterTypes();
	    for (int i = 0; i < parameterTypes.length; i++) {
	        stringBuilder.append(parameterTypes[i].getSimpleName());
	        if (i < parameterTypes.length - 1) {
	            stringBuilder.append(", ");
	        }
	    }
	    stringBuilder.append(");");
	
	    return stringBuilder.toString();
	}
	
	protected String getMethodInfo(Method method) {
        StringBuilder stringBuilder = new StringBuilder();

        // Add method modifiers
        stringBuilder.append(Modifier.toString(method.getModifiers())).append(" ");

        // Add return type
        stringBuilder.append(method.getReturnType().getSimpleName()).append(" ");

        // Add method name
        stringBuilder.append(method.getName()).append("(");

        // Add parameters
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            stringBuilder.append(parameterTypes[i].getSimpleName());
            if (i < parameterTypes.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(") ");

        // Add default return value
        stringBuilder.append("{ return ").append(getDefaultValue(method.getReturnType())).append("; }");

        return stringBuilder.toString();
    }
	
	private String getDefaultValue(Class<?> returnType) {
        if (returnType == void.class) {
            return "";
        } else if (returnType == boolean.class) {
            return "false";
        } else if (returnType.isPrimitive()) {
            return "0";
        } else {
            return "null";
        }
    }
	
	public static void main(String[] args) {
	    Decompiler decompiler = new Decompiler();
	
	    // Example class to decompile
	    System.out.println(decompiler.decompile(TestClass.class));
	    
	    Class<?> [] classes = {TestClass.class};
	    
	    
	    Stream<Class<?>> methodStream = Stream.of(classes);
	    // 2021 modified
	    methodStream.peek(x->System.out.println("class: " + x.getSimpleName())).map(x->hasPublicFooInteger(x)).forEach(System.out::println);
	}
	
	// 2021 modified
	private static boolean hasPublicFooInteger(Class<?> clazz) {
		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			int modifiers = method.getModifiers();
			if (method.getName() == "foo" && Modifier.isPublic(modifiers) && method.getGenericParameterTypes().length == 1 && method.getGenericParameterTypes()[0] == Integer.class) {
				return true;
			}
		}
		return false;
	}
}
