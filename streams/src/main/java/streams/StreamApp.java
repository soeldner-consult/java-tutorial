package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamApp {
	
	enum Direction {
	    LEFT("links", "gauche", "left", "izquierda", "sinistra", "лево", "lewo", "sol", "يسار", "چپ"),
	    RIGHT("rechts", "droite", "right", "derecha", "destra", "право", "prawo", "sağ", "يمين", "راست"),
	    FOLLOW("folgen", "suivre", "follow", "seguir", "seguire", "следовать", "podążać", "takip et", "اتبع", "پیروی کردن"),
	    TURN_AROUND("wenden", "faire demi-tour", "turn around", "dar la vuelta", "girare", "разворот", "zawrócić", "geri dön", "استدر", "دور زدن");

	    // Fields for translations
	    private final String german;
	    private final String french;
	    private final String english;
	    private final String spanish;
	    private final String italian;
	    private final String russian;
	    private final String polish;
	    private final String turkish;
	    private final String arabic;
	    private final String persian;

	    // Constructor
	    Direction(String german, String french, String english, String spanish, String italian, String russian, 
	              String polish, String turkish, String arabic, String persian) {
	        this.german = german;
	        this.french = french;
	        this.english = english;
	        this.spanish = spanish;
	        this.italian = italian;
	        this.russian = russian;
	        this.polish = polish;
	        this.turkish = turkish;
	        this.arabic = arabic;
	        this.persian = persian;
	    }

	    // Getters
	    public String getGerman() { return german; }
	    public String getFrench() { return french; }
	    public String getEnglish() { return english; }
	    public String getSpanish() { return spanish; }
	    public String getItalian() { return italian; }
	    public String getRussian() { return russian; }
	    public String getPolish() { return polish; }
	    public String getTurkish() { return turkish; }
	    public String getArabic() { return arabic; }
	    public String getPersian() { return persian; }
	    
//	    

	    // Dynamic translation lookup
	    public String getTranslation(String languageCode) {
	        return switch (languageCode.toLowerCase()) {
	            case "de" -> german;
	            case "fr" -> french;
	            case "en" -> english;
	            case "es" -> spanish;
	            case "it" -> italian;
	            case "ru" -> russian;
	            case "pl" -> polish;
	            case "tr" -> turkish;
	            case "ar" -> arabic;
	            case "fa" -> persian;
	            default -> "Translation not available";
	        };
	    }
	    
	}
	
    public static void main(String[] args) {
        // 1. Stream from a Collection
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Edward");
        System.out.println("Names starting with 'A':");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // 2. Stream from an Array
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int sum = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
        System.out.println("Sum of even numbers: " + sum);

        // 3. Stream from a Range
        System.out.println("Squares of numbers from 1 to 5:");
        IntStream.rangeClosed(1, 5)
                .map(n -> n * n)
                .forEach(System.out::println);

        // 4. Stream from a File (simulated with Stream.of)
        Stream<String> lines = Stream.of("Line 1", "Line 2", "Line 3");
        System.out.println("File lines:");
        lines.forEach(System.out::println);

        // 5. Intermediate Operations
        System.out.println("Uppercase names sorted:");
        names.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        // 6. Terminal Operations: Collectors
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .collect(Collectors.toList());
        System.out.println("Filtered names (length > 3): " + filteredNames);

        // 7. Reduce Operation
        OptionalInt product = Arrays.stream(numbers)
                .reduce((a, b) -> a * b);
        product.ifPresent(p -> System.out.println("Product of all numbers: " + p));

        // 8. Stream from an Infinite Source
        System.out.println("First 5 Fibonacci numbers:");
        Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(5)
                .map(fib -> fib[0])
                .forEach(System.out::println);

        // 9. Grouping and Partitioning
        Map<Boolean, List<String>> partitionedNames = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() % 2 == 0));
        System.out.println("Partitioned names by even length: " + partitionedNames);

        // 10. FlatMap Example
        List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("A", "B"),
                Arrays.asList("C", "D"),
                Arrays.asList("E", "F")
        );
        System.out.println("Flattened list:");
        nestedLists.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        // 11. Distinct and Limit Example
        System.out.println("Unique numbers, limited to 3:");
        Stream.of(1, 2, 3, 2, 1, 4, 5)
                .distinct()
                .limit(3)
                .forEach(System.out::println);
        
        // 11. Leap Year Check example
        System.out.println("Leap Year Check from 1891 to 2025:");
        IntStream.rangeClosed(1891, 2025).filter(x->x%400==0 || (x%4==0 && x%100!=0)).forEach(System.out::println);
        
        // similar checks with filters
        // Check with Strings (filtering string length)
        System.out.println("Strings with length > 5:");
        Stream.of("hello", "world", "streams", "java", "programming")
                .filter(str -> str.length() > 5)
                .forEach(System.out::println);

        // Check with Doubles (values greater than a threshold)
        System.out.println("Doubles > 50.5:");
        DoubleStream.of(10.1, 55.5, 42.3, 99.9, 60.0)
                .filter(d -> d > 50.5)
                .forEach(System.out::println);

        // Check with Longs (odd numbers)
        System.out.println("Odd Longs:");
        LongStream.rangeClosed(1000, 1010)
                .filter(n -> n % 2 != 0)
                .forEach(System.out::println);

        // Check with Floats (divisible by 1.5)
        System.out.println("Floats divisible by 1.5:");
        Stream.of(1.5f, 3.0f, 4.5f, 5.0f, 6.0f)
                .filter(f -> f % 1.5f == 0)
                .forEach(System.out::println);

        // Check with Bytes (values in a range)
        System.out.println("Bytes between 10 and 20:");
        IntStream.range(0, 50)
                .mapToObj(i -> (byte) i)
                .filter(b -> b >= 10 && b <= 20)
                .forEach(System.out::println);
        
        // 12. String Lists, mapping
        final String message = "Le cheval ne mange pas de salade de concombres.";
        final String[] words = message.split(" ");
        
        Stream<String> wordStreamUpper = Stream.of(words).map(String::toUpperCase);
        Stream<String> wordStreamLower = Stream.of(words).map(String::toLowerCase);
        wordStreamLower.forEach(x->System.out.print(x + " "));
        System.out.println();
        List<String> upperList = wordStreamUpper.collect(Collectors.toList());
        System.out.println(String.join(" ", upperList));
        
        // if you want to remove the last trailing space in the lower case example it gets a little bit complicated
        IntStream.range(0, words.length)
            .forEach(i -> {
                if (i == words.length - 1) {
                    System.out.print(words[i].toLowerCase()); // No trailing space for the last element
                } else {
                    System.out.print(words[i].toLowerCase() + " ");
                }
            });
        System.out.println();
        
        
        // Stream.generate from script
        Random rnd = new Random();
        Stream.generate(rnd::nextDouble) // method reference
        .limit(10)       // generate 10 numbers
        .sorted()        // sort them
        .forEach(System.out::println);
        
        // Creating a stream with the iterate method
        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2);

        // Process the stream: limit it, map it, and print it
        evenNumbers
            .limit(10) // Limit to 10 elements
            .map(n -> "Even number: " + n) // Transform each number into a string
            .forEach(System.out::println); // Print each string
        
        Stream<Double> doubleNumbers = Stream.iterate(0.0, n -> n + 0.345);
        
        doubleNumbers.limit(30)
        .map(n -> Math.round(n * 100.00) / 100.0) // Rounds to 2 decimal place
        .filter(n-> n>2.5)
        .forEach(System.out::println);
        
        // finite Stream with iterate, with additional Predicate
        Stream.iterate(1, n -> n <= 10, n -> n + 1).forEach(System.out::println);
        
        int[] fieldWithNumbers = {-4, 6, 20, -34, 99};
        
        Arrays.stream(fieldWithNumbers)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(Integer::intValue)
        .forEach(System.out::println);
        
        // parallel streams
        String orderedResult = IntStream.rangeClosed(1, 10)
        	    .parallel() // Convert the stream to a parallel stream
        	    .mapToObj(String::valueOf) // Convert each integer to a String
        	    .sorted() // Ensure the elements are sorted in natural order
        	    .collect(Collectors.joining(", ")); // Join them into a single string

        System.out.println("Ordered result: " + orderedResult); 
       
        int sumOfSquares = IntStream.rangeClosed(1, 10000)
                .parallel() // Start with a parallel stream
                .peek(n -> System.out.println("Parallel processing: " + n + " on " + Thread.currentThread().getName()))
                .sequential() // Switch to sequential processing
                .peek(n -> System.out.println("Sequential processing: " + n + " on " + Thread.currentThread().getName()))
                .map(n -> n * n)
                .sum(); // Sum the squares
        
        List<Integer> result = IntStream.rangeClosed(9951, 9955)
        	    .parallel()
        	    .peek(n -> System.out.println("Parallel processing: " + n + " on " + Thread.currentThread().getName()))
        	    .boxed()
        	    .collect(Collectors.toList());

        System.out.println("Sum of squares: " + sumOfSquares);
        
        // enums and streams
        Direction[] directions = {
                Direction.LEFT,
                Direction.RIGHT,
                Direction.FOLLOW,
                Direction.TURN_AROUND
        };
        
        // non-modifiable list
        List<Direction> directionList = Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.TURN_AROUND, Direction.FOLLOW);
        directionList.stream().forEach(System.out::println);
        
        // Create a list of all enum values
        System.out.println("Inserting enum values directly:");
        List<Direction> allDirections = Arrays.asList(Direction.values());
        allDirections.stream().sorted().forEach(x->System.out.print(x+" "));
        System.out.println();
        
        // modifiable list
		// List<String> modifiableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
		// modifiableList.add("D"); // Works
		// System.out.println(modifiableList); // Output: [A, B, C, D]

        // outputting translated directions
        System.out.println("Übersetzte Richtungsanweisungen:");
        // for loop
        for (Direction direction : directions) {
            System.out.println(direction + ": " + direction.getTranslation("it")); // Deutsche Übersetzung
        }
        
        // with a stream
        Stream.of(directions).forEach(x-> 
        	{System.out.println(x + ": " + x.getTranslation("fa"));});
        
        
        // finally a flatMap example
        System.out.println("Flat map not flat earth..");
        List<Optional<String>> optionalList = Arrays.asList(
                Optional.of("Apple"),
                Optional.empty(),
                Optional.of("Banana"),
                Optional.empty(),
                Optional.of("Plum")
            );
        
        // in Java Streams is primarily used to flatten nested structures and transform 
        // them into a single-level stream. This is particularly useful when each 
        // element of a stream can be expanded into multiple elements
        List<String> presentValues = optionalList.stream()
            .flatMap(opt -> opt.stream()) // Flatten the non-empty optionals
            .collect(Collectors.toList());

        System.out.println(presentValues);
        
        List<Character> characters =
        		"Ansbach".chars()
        		.boxed()
        		.map(c -> (char) c.intValue())
        		.collect(Collectors.toList());
        		
        		characters
        		.parallelStream()       		
        		.findFirst()
        		.ifPresent(System.out::println);
        		
        		characters
        		.parallelStream()
        		.findAny()
        		.ifPresent(System.out::println);
        
    }

}
