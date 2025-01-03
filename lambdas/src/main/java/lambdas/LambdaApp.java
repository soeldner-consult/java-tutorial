package lambdas;

import java.util.function.*;
import java.util.*;

public class LambdaApp {

	@FunctionalInterface
	interface TernaryOperator {
		public int apply(int op1, int op2, int op3);
	}
	
    public static void main(String[] args) {
        
    	// 1. Supplier: No input, returns a value, .. String supplierMethod() { return "..." }
        Supplier<String> supplier = () -> "Hello from Supplier!";
        System.out.println("Supplier Output: " + supplier.get());

        // 2. Consumer: Takes a value, returns nothing
        Consumer<String> consumer = message -> System.out.println("Consumed: " + message);
        consumer.accept("Hello, Consumer!");

        // 3. Function: Takes one input, returns a value
        Function<Integer, String> function = number -> "The number is: " + number;
        System.out.println("Function Output: " + function.apply(42));

        // 4. Predicate: Takes one input, returns a boolean
        Predicate<Integer> predicate = number -> number > 0;
        System.out.println("Predicate Output (is positive): " + predicate.test(42));
        System.out.println("Predicate Output (is positive): " + predicate.test(-1));

        // 5. BiFunction: Takes two inputs, returns a value
        BiFunction<String, Integer, String> biFunction = (text, number) -> text + " has length " + number;
        System.out.println("BiFunction Output: " + biFunction.apply("LambdaApp", 9));

        // 6. BinaryOperator: Takes two inputs of the same type, returns a value of the same type
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        System.out.println("BinaryOperator Output (sum): " + binaryOperator.apply(10, 20));

        // 7. UnaryOperator: Takes one input, returns a value of the same type
        UnaryOperator<String> unaryOperator = text -> text.toUpperCase();
        System.out.println("UnaryOperator Output: " + unaryOperator.apply("hello"));

        // Example with a List and Consumer
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));

        // Chaining Predicates
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> isPositive = number -> number > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("Is 4 even and positive? " + isEvenAndPositive.test(4));
        System.out.println("Is -2 even and positive? " + isEvenAndPositive.test(-2));

        // Using Function with andThen and compose
        Function<Integer, Integer> multiplyByTwo = number -> number * 2;
        Function<Integer, Integer> addFive = number -> number + 5;
        Function<Integer, Integer> combinedFunction = multiplyByTwo.andThen(addFive);
        System.out.println("Combined Function Output: " + combinedFunction.apply(10)); // (10 * 2) + 5 = 25

        Function<Integer, Integer> reverseOrderFunction = multiplyByTwo.compose(addFive);
        System.out.println("Reverse Order Function Output: " + reverseOrderFunction.apply(10)); // (10 + 5) * 2 = 30
        
        TernaryOperator ternaryOperator = (x,y,z) -> { 
        	int xy = Math.max(x, y);
        	int yz = Math.max(y, z);
        	return Math.max(xy, yz);
        };
        
        System.out.println(ternaryOperator.apply(3,5,7));
    
    }
}
