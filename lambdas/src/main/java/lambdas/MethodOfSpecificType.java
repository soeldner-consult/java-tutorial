package lambdas;

import java.util.function.BiFunction;

public class MethodOfSpecificType {
	public static void main(String[] args) {
        
		// Using lambda
        BiFunction<String, String, Boolean> lambdaExample = (str, prefix) -> str.startsWith(prefix);

        // Using method reference
        BiFunction<String, String, Boolean> methodReferenceExample = String::startsWith;

        System.out.println(lambdaExample.apply("Hello, world!", "Hello")); // Output: true
        System.out.println(methodReferenceExample.apply("Hello, world!", "Hello")); // Output: true
    }
}
