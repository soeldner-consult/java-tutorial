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
        
        Supplier<String> supplierRef = LambdaApp::supplierMethod; // Method reference
        System.out.println("Supplier Output: " + supplierRef.get());

        // 2. Consumer: Takes a value, returns nothing
        Consumer<String> consumer = message -> System.out.println("Consumed: " + message);
        consumer.accept("Hello, Consumer!");
        
        Consumer<String> consumerRef = System.out::println; // Method reference
        consumerRef.accept("Hello, Consumer!");

        // 3. Function: Takes one input, returns a value
        Function<Integer, String> function = number -> "The number is: " + number;
        System.out.println("Function Output: " + function.apply(42));
        
        Function<Integer, String> functionRef = LambdaApp::functionMethod; // Method reference
        System.out.println("Function Output: " + functionRef.apply(42));

        // 4. Predicate: Takes one input, returns a boolean
        Predicate<Integer> predicate = number -> number > 0;
        System.out.println("Predicate Output (is positive): " + predicate.test(42));
        System.out.println("Predicate Output (is positive): " + predicate.test(-1));
        
        Predicate<Integer> predicateRef = LambdaApp::isPositive; // Method reference
        System.out.println("Predicate Output (is positive): " + predicateRef.test(42));
        System.out.println("Predicate Output (is positive): " + predicateRef.test(-1));
        
        // 5. BiFunction: Takes two inputs, returns a value
        BiFunction<String, Integer, String> biFunction = (text, number) -> text + " has length " + number;
        System.out.println("BiFunction Output: " + biFunction.apply("LambdaApp", 9));
        
        BiFunction<String, Integer, String> biFunctionRef = LambdaApp::biFunctionMethod; // Method reference
        System.out.println("BiFunction Output: " + biFunctionRef.apply("LambdaApp", 9));
        
        // 6. BinaryOperator: Takes two inputs of the same type, returns a value of the same type
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
        System.out.println("BinaryOperator Output (sum): " + binaryOperator.apply(10, 20));
        
        BinaryOperator<Integer> binaryOperatorRef = Integer::sum; // Method reference
        System.out.println("BinaryOperator Output (sum): " + binaryOperatorRef.apply(10, 20));

        
        // 7. UnaryOperator: Takes one input, returns a value of the same type
        UnaryOperator<String> unaryOperator = text -> text.toUpperCase();
        System.out.println("UnaryOperator Output: " + unaryOperator.apply("hello"));
        
        UnaryOperator<String> unaryOperatorRef = String::toUpperCase; // Method reference
        System.out.println("UnaryOperator Output: " + unaryOperatorRef.apply("hello"));
        
        // Example with a List and Consumer
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));
        
        List<String> namesRef = Arrays.asList("Alice", "Bob", "Charlie");
        namesRef.forEach(System.out::println); // Method reference
        
        // Chaining Predicates
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> isPositive = number -> number > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
        System.out.println("Is 4 even and positive? " + isEvenAndPositive.test(4));
        System.out.println("Is -2 even and positive? " + isEvenAndPositive.test(-2));
        
        Predicate<Integer> isEvenRef = LambdaApp::isEven; // Method reference
        Predicate<Integer> isPositiveRef = LambdaApp::isPositive; // Method reference
        Predicate<Integer> isEvenAndPositiveRef = isEven.and(isPositive);
        System.out.println("Is 4 even and positive? " + isEvenAndPositiveRef.test(4));
        System.out.println("Is -2 even and positive? " + isEvenAndPositiveRef.test(-2));
        
        
        // Using Function with andThen and compose
        Function<Integer, Integer> multiplyByTwo = number -> number * 2;
        Function<Integer, Integer> addFive = number -> number + 5;
        Function<Integer, Integer> combinedFunction = multiplyByTwo.andThen(addFive);
        System.out.println("Combined Function Output: " + combinedFunction.apply(10)); // (10 * 2) + 5 = 25

        Function<Integer, Integer> reverseOrderFunction = multiplyByTwo.compose(addFive);
        System.out.println("Reverse Order Function Output: " + reverseOrderFunction.apply(10)); // (10 + 5) * 2 = 30
        
        Function<Integer, Integer> multiplyByTwoRef = LambdaApp::multiplyByTwo; // Method reference
        Function<Integer, Integer> addFiveRef = LambdaApp::addFive; // Method reference
        Function<Integer, Integer> combinedFunctionRef = multiplyByTwo.andThen(addFive);
        System.out.println("Combined Function Output: " + combinedFunctionRef.apply(10)); // (10 * 2) + 5 = 25

        Function<Integer, Integer> reverseOrderFunctionRef = multiplyByTwo.compose(addFive);
        System.out.println("Reverse Order Function Output: " + reverseOrderFunctionRef.apply(10)); // (10 + 5) * 2 = 30

        TernaryOperator ternaryOperator = (x,y,z) -> { 
        	int xy = Math.max(x, y);
        	int yz = Math.max(y, z);
        	return Math.max(xy, yz);
        };
        
        System.out.println(ternaryOperator.apply(3,5,7));
        
        TernaryOperator ternaryOperatorRef = LambdaApp::findMax; // Method reference
        System.out.println(ternaryOperatorRef.apply(3, 5, 7));
        
        
    }
    
    // as in exam
    public static int findMax(int x, int y, int z) {
        int xy = Math.max(x, y);
        int yz = Math.max(y, z);
        return Math.max(xy, yz);
    }
    
    // out of curiosity
    public static int findMaxTernary(int x, int y, int z) {
        return x > y ? (x > z ? x : z) : (y > z ? y : z);
    }
    
    // Supporting methods for method references
    public static String supplierMethod() {
        return "Hello from Supplier!";
    }

    public static String functionMethod(Integer number) {
        return "The number is: " + number;
    }

    public static boolean isPositive(Integer number) {
        return number > 0;
    }

    public static String biFunctionMethod(String text, Integer number) {
        return text + " has length " + number;
    }

    public static boolean isEven(Integer number) {
        return number % 2 == 0;
    }

    public static Integer multiplyByTwo(Integer number) {
        return number * 2;
    }

    public static Integer addFive(Integer number) {
        return number + 5;
    }
    
    
}
