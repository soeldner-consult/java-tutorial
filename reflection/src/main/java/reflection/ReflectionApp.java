package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionApp {

    public static void main(String[] args) {
        try {
            // 1. Introspection: Inspect a class and its members
            introspectClass(SampleClass.class);

            // 2. Dynamic invocation: Call methods and set/retrieve field values
            dynamicInvocation();

            // 3. Class loading: Demonstrate dynamic class loading
            demonstrateClassLoading("SampleClass");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Introspection: Inspect a class and its members
    public static void introspectClass(Class<?> clazz) {
        System.out.println("--- Introspecting Class: " + clazz.getName() + " ---");

        // List constructors
        System.out.println("Constructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("  " + constructor);
        }

        // List fields
        System.out.println("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + " " + field.getName());
        }

        // List methods
        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getName() + " " + method.getName());
        }
    }

    // 2. Dynamic invocation: Call methods and set/retrieve field values
    public static void dynamicInvocation() throws Exception {
        System.out.println("--- Dynamic Invocation ---");

        // Create an instance of SampleClass dynamically
        Class<?> clazz = SampleClass.class;
        Constructor<?> constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();

        // Access and modify a field dynamically
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true); // Make private fields accessible
        field.set(instance, "Dynamic Name");
        System.out.println("Field 'name' dynamically set to: " + field.get(instance));

        // Invoke a method dynamically
        Method method = clazz.getDeclaredMethod("sayHello");
        method.setAccessible(true);
        method.invoke(instance);

        // Invoke a method with arguments
        Method greetMethod = clazz.getDeclaredMethod("greet", String.class);
        greetMethod.setAccessible(true);
        greetMethod.invoke(instance, "Reflection");
    }

    // 3. Class loading: Demonstrate dynamic class loading
    public static void demonstrateClassLoading(String className) {
        System.out.println("--- Demonstrating Class Loading ---");
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("Class loaded dynamically: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        }
    }
}

// A sample class for demonstration purposes
class SampleClass {
    private String name = "Default Name";

    public SampleClass() {
        System.out.println("SampleClass instantiated");
    }

    private void sayHello() {
        System.out.println("Hello from SampleClass!");
    }

    private void greet(String message) {
        System.out.println("Greeting: " + message);
    }
}
