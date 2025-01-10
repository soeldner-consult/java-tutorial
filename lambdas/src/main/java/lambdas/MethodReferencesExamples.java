package lambdas;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class MyClass {
	
	String stringberg;
	
	MyClass() {}
	MyClass(String stringberg) {
		this.stringberg = stringberg;
	}
}

public class MethodReferencesExamples {
    
    public static <T> T mergeThings(T a, T b, BinaryOperator<T> merger) {
        return merger.apply(a, b);
    }
    
    public static <T> T mergeThings2(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }
    
    public static String appendStrings(String a, String b) {
        return a + b;
    }
    
    public String appendStrings2(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        
        MethodReferencesExamples myApp = new MethodReferencesExamples();

        // Calling the method mergeThings with a lambda expression
        System.out.println(MethodReferencesExamples.
            mergeThings("Hello ", "Ansbach!", (a, b) -> a + b));
        
        System.out.println(MethodReferencesExamples.
                mergeThings2("Hello ", "Ansbach!", (a, b) -> a + b));
        
        // Reference to a static method
        System.out.println(MethodReferencesExamples.
            mergeThings("Hello ", "Ansbach!", MethodReferencesExamples::appendStrings));

        // Reference to an instance method of a particular object        
        System.out.println(MethodReferencesExamples.
            mergeThings("Hello ", "Ansbach!", myApp::appendStrings2));
        
        // Reference to an instance method of an arbitrary object of a
        // particular type
        // The String.concat(String) method is an instance method of the String 
        // class that concatenates the current string (this) with another string 
        // provided as a parameter.
        System.out.println(MethodReferencesExamples.
            mergeThings("Hello ", "Ansbach!", String::concat));
        
        
        
        // using the ::new (constructor reference) syntax requires a functional interface
        // on the left-hand side. This is because a method reference 
        // (including a constructor reference) needs a target type, and in Java, 
        // functional interfaces provide that target type.
        Supplier<MyClass> mySupply = MyClass::new;

        // MyClass myclazz = MyClass::new; doesn't work!
        
        // If MyClass has both a no-argument constructor and a constructor that 
        // takes a String, how does the compiler decide which one you mean?
        // Without a functional interface, the reference is ambiguous.
        Function<String, MyClass> stringSupply = MyClass::new;
        // Creating an instance of MyClass
        MyClass instance = stringSupply.apply("Hello, world!");
        // @Override toString in MyClass for sensible output
        System.out.println(instance.toString());
        
        List<String> inputs = List.of("um", "dos", "três", "quatro");
        List<MyClass> instances = inputs.stream()
                                        .map(MyClass::new)
                                        .collect(Collectors.toList());
        // do something with instances.. or maybe not.
        
        Runnable sayHelloThread = () -> {
    	System.out.println("Hello, World from a thread!");
    	};
    	
    	Thread newThread = new Thread(sayHelloThread);
    	
    	newThread.start();
    	
    	try {
			newThread.join(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}