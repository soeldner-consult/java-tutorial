package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FurtherStreams {
	public static void main(String[] args) {
		
		// 1
		System.out.println("Exercise 1");
		int[] intArr = Stream.of(2, -4, 10, 45, 99, 199, -5)
		.mapToInt(x->Math.abs(x))
		.sorted()
		.toArray();
		
		System.out.println(intArr);
		
		int[] anotherIntArr = IntStream.of(2, -4, 10, 45, 99, 199, -5)
	            .map(Math::abs) // Convert to IntStream and apply Math.abs
	            .sorted()           // Sort the IntStream
	            .toArray();
	    
		System.out.println(anotherIntArr);
		
		// it always depends what you want to have
		// in the exam we should know that a normal stream consists of objects
		// while there are also streams of primitives
		// I would expect primitive streams to perform faster
		List<Integer> intList = Stream.of(2, -4, 10, 45, 99, 199, -5)
	            .map(Math::abs) // Convert to IntStream and apply Math.abs
	            .sorted()           // Sort the IntStream
	            .collect(Collectors.toList());
		
		// 2
		System.out.println("Exercise 2");
		Arrays.asList(Locale.getAvailableLocales())
			.stream()
			.map(x -> x.getDisplayCountry())
			.filter(country -> !country.isEmpty())
		    .distinct()
		    .sorted(Comparator.reverseOrder())
		    .forEach(System.out::println);
		
		// 3
		System.out.println("Exercise 3");
		
		// new int[]{0, 1} is already an object in Java. Specifically, it is an instance 
		// of the int[] type, which is an array type in Java. 
		// In Java, arrays are objects, even if they hold primitive types like int.
		
		// when you stream an array of primitive types 
		// (like int[]) in Java, autoboxing occurs if you convert the array 
		// into a Stream<Integer> instead of using the specialized IntStream. 
		// This is because Stream works with objects, while IntStream is designed
		// for primitive int.
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
				.limit(15)
				.map(x->x[0])
				.forEach(System.out::println);
				//.forEach(x->System.out.println("{" + x[0] + "," + x[1] + "}"));
		
	}
}
