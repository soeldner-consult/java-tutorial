package lambdas;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class InstanceMethodReferenceExample {
	
	public static void main(String[] args) {
		String string = "Method references are tricky!";
		Supplier<Integer> lengthOfString = string::length;
		System.out.println(lengthOfString.get());
		
		// however here we have "String::length" as we're dealing with a "stream" of instances in streams
		// we're referencing an instance method
		Stream.of(string).map(String::length).forEach(System.out::println);
	}
	
}
