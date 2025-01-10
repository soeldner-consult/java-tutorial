package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// exercise from script
// we have to add a few words about Comparable and Generics here though

//Comparable only in class XYZ<K extends Comparable> without implementing the interface:
//
//Use Case1: Container for key-value pairs where keys need to be comparable.
//Example: Sorting or searching based on keys.
//The class implementing the Comparable interface while XYZ<K, V> without K extends Comparable:
//
//Use Case2: Comparing instances of the class based on criteria OTHER than keys.
//Example: Comparing based on values or a combination of keys and values.
//Both K extends Comparable and the class implementing the Comparable interface:
//
//Use Case3: Comparing both keys AND instances of the class.
//Example: Sorting or searching based on keys and comparing entire objects.

// <K extends Comparable<K>, V> provides type saftey for K at compile time, preventing you from using 
// non-comparable types for K.

// baseline is: if we want class Pair to provide logic for comparing instances (that might contain additional
// generic types) we implement Comparable

// something like this is also possible while it is too random in practical terms
//class ABC implements Comparable {
//@Override
//public int compareTo(Object o) {
//	// TODO Auto-generated method stub
//	return 0;
//	}
//}

class Pair<K extends Comparable<K>,V> implements Comparable<Pair<K, V>>  {
	K key;
	V value;
	
	Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
	
	
	@Override
    public int compareTo(Pair<K, V> other) {
        // Compare based on the key
		// both null..
        if (this.key == null && other.key == null) {
            return 0; // Both keys are null, treat as equal
        }
        if (this.key == null) {
            return -1; // Null is considered smaller
        }
        if (other.key == null) {
            return 1; // Non-null is greater
        }
        return this.key.compareTo(other.key);
    }
	
	@Override
    public String toString() {
        // Customize the string representation
        return "Pair{key=" + key + ", value=" + value + "}";
    }
}

// 1. generic class
class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Box contains: " + item;
    }
}

// 2. generic methods
class Utility {
    public static <T> void printItem(T item) {
        System.out.println("Item: " + item);
    }

    // methods with bounded type parameter
    public static <T extends Number> double sumNumbers(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}

// 3. demonstration of wildcards (Unbounded and Bounded)
class WildcardDemo {
    // Method with unbound wildcard (?)
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println("Item: " + item);
        }
    }

    // method with bound wildcard (Upper Bound)
    public static void printNumberList(List<? extends Number> list) {
        for (Number num : list) {
            System.out.println("Number: " + num);
        }
    }

    
    // search for additional use cases..
    // method with lower bound
    public static void addInteger(List<? super Integer> list) {
        list.add(42); // adds always Integer values
        System.out.println("Added 42 to the list.");
    }
}

//A generic class implementing Comparable
//A class can have only one natural ordering defined through compareTo().
//If multiple sorting orders are needed (e.g., by name and by grade), you must use the Comparator interface instead.
// If you omit T extends Comparable<T>, the compiler cannot guarantee that T has a compareTo method, leading to errors.
// class ComparableBox<T> implements Comparable<ComparableBox<T>> { does not work!
class ComparableBox<T extends Comparable<T>> implements Comparable<ComparableBox<T>> {

	 private T value;
	
	 // Constructor
	 public ComparableBox(T value) {
	     this.value = value;
	 }
	
	 // Getter for value
	 public T getValue() {
	     return value;
	 }
	
	 // compareTo method to define natural ordering
	 public int compareTo(ComparableBox<T> other) {
		 // here value of Type T is compared..
	     return this.value.compareTo(other.value);
	 }
	
	 @Override
	 public String toString() {
	     return "Box{" + "value=" + value + '}';
	 }
}


public class GenericsApp {
	
	 // Generic method that works with Map<K, V>
    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
	
	
    public static void main(String[] args) {
        // 1. Generische Klasse
        System.out.println("=== Generic Class Example ===");
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Hello Generics!");
        System.out.println(stringBox);

        Box<Integer> intBox = new Box<>();
        intBox.setItem(123);
        System.out.println(intBox);

        // 2. Generische Methode
        System.out.println("\n=== Generic Method Example ===");
        Utility.printItem("A generic string");
        Utility.printItem(100);

        double sum = Utility.sumNumbers(10.5, 20.5);
        System.out.println("Sum of numbers: " + sum);

        // 3. Wildcards in Generics
        System.out.println("\n=== Wildcard Example ===");

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.1);
        doubleList.add(2.2);
        doubleList.add(3.3);

        System.out.println("Printing List with unbounded wildcard:");
        WildcardDemo.printList(intList);
        WildcardDemo.printList(doubleList);

        System.out.println("\nPrinting List with bounded wildcard (Numbers):");
        WildcardDemo.printNumberList(intList);
        WildcardDemo.printNumberList(doubleList);

        List<Object> objList = new ArrayList<>();
        System.out.println("\nUsing Lower Bound Wildcard to add an Integer:");
        WildcardDemo.addInteger(objList);
        System.out.println("Updated Object List: " + objList);
        
        // 4. Comparable Box
        // Create a list of Box objects containing Integers
        List<ComparableBox<Integer>> integerBoxes = new ArrayList<>();
        integerBoxes.add(new ComparableBox<>(10));
        integerBoxes.add(new ComparableBox<>(5));
        integerBoxes.add(new ComparableBox<>(20));
        integerBoxes.add(new ComparableBox<>(15));

        // Sorting the list using Comparable
        System.out.println("Before sorting:");
        for (ComparableBox<Integer> box : integerBoxes) {
            System.out.println(box);
        }

        // Sort the list
        Collections.sort(integerBoxes);
        // a few comments to this sorting:
		// Scenario	Requirement
		// Collections.sort(list)	Elements must implement Comparable.
		// Collections.sort(list, comparator)	No need for Comparable; sorting uses the provided Comparator.
		// Arrays.sort(array)	Elements must implement Comparable.
		// Arrays.sort(array, comparator)	No need for Comparable; sorting uses the provided Comparator.
		// Best Practices
		// Use Comparable when you have a single, natural ordering for objects (e.g., sorting strings alphabetically or integers numerically).
		// Use Comparator when:
		// You want to define custom sorting logic.
		// The objects being sorted do not implement Comparable.
		// You need multiple sorting strategies.
        
        System.out.println("\nAfter sorting:");
        for (ComparableBox<Integer> box : integerBoxes) {
            System.out.println(box);
        }

        // Another example with Strings
        List<ComparableBox<String>> stringBoxes = new ArrayList<>();
        stringBoxes.add(new ComparableBox<>("Banana"));
        stringBoxes.add(new ComparableBox<>("Apple"));
        stringBoxes.add(new ComparableBox<>("Cherry"));
        stringBoxes.add(new ComparableBox<>("Date"));

        System.out.println("\nBefore sorting Strings:");
        for (ComparableBox<String> box : stringBoxes) {
            System.out.println(box);
        }

        Collections.sort(stringBoxes);

        System.out.println("\nAfter sorting Strings:");
        for (ComparableBox<String> box : stringBoxes) {
            System.out.println(box);
        }
        
        // Map<K, V> where K is String and V is Integer
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("Alice", 85);
        studentScores.put("Bob", 90);
        studentScores.put("Charlie", 78);

        System.out.println("Student Scores:");
        printMap(studentScores);

        
        
		//        1 Plane<String[]> plane = new Plane<String[]>(); ①
		//        2 Plane<String>[] fleet = new Plane<String>[5]; ②
        // this would be allowed!
        // List<Plane<String>> fleet = new ArrayList<>();
        
        // get greatest Element
        System.out.println("Greatest Element in different arrays:");
        String[] stringArr = {"bla", "bli", "blub"};
        Integer[] intArr = {1,5,10,3};
        Double[] doubleArr = {1.1,5.1,3.1};
        System.out.println(getLargestElement(stringArr));
        System.out.println(getLargestElement(intArr));
        System.out.println(getLargestElement(doubleArr));
        
        System.out.println("Count Number Occurrence");
        Object[] myArr = {"bla", "bli", "blub", 5, 10.5};
        System.out.println(countNumber(myArr));
        
        Pair<String, Integer> pair1 = new Pair<>("Apple", 10);
        Pair<String, Integer> pair2 = new Pair<>("Banana", 20);
        System.out.println("Compare pair 1 and pair 2:");
        System.out.println(pair1.compareTo(pair2)); // Output depends on the key comparison
       
        Pair<String, Integer>[] pairs = new Pair[] {
                new Pair<>("Banana", 20),
                new Pair<>("Apple", 10),
                new Pair<>("Cherry", 15),
                new Pair<>("Date", 8)
            };

            // Find the smallest Pair based on the key using compareTo
            Pair<String, Integer> smallestPair = findSmallestPair(pairs);

            // Print the result
            System.out.println("Smallest Pair: " + smallestPair);
            
            System.out.println("Generic sequential search with Comparable ..");
            Integer[] numbers = {10, 20, 30, 40, 50};
            String[] words = {"apple", "banana", "cherry", "date"};

            int numberIndex = sequentialSearch(numbers, 30);
            System.out.println("Index of 30: " + numberIndex);

            int wordIndex = sequentialSearch(words, "cherry");
            System.out.println("Index of 'cherry': " + wordIndex);
    }
    
    public static <E extends Comparable<E>> int getLargestElement(E[] arr) {
        int maxIndex = 0;
        
        // this mechanism is a bit unintuitive and thus begs an explanation:
        // ... compareTo(..)<0: The object on which compareTo is called is less than the object being compared to.
        // 0: equal
        // >0: greater than, maxIndex changes.
        for(int index = 1; index <= arr.length -1; index++) {
            if (arr[index].compareTo(arr[maxIndex]) > 0) {
                maxIndex = index;
            }
        }
        return maxIndex;
    }
    
    public static int countNumber(Object[] arr) {
    	
    	int numberOccurrences = 0;
    	for (Object e:arr) {
    		if (e instanceof Number) {
    			numberOccurrences++;
    		}
    	}
    	return numberOccurrences;
    }
    
    // Method to find the smallest Pair based on the key
    public static <K extends Comparable<K>, V> Pair<K, V> findSmallestPair(Pair<K, V>[] pairs) {
        if (pairs == null || pairs.length == 0) {
            return null; // Handle empty or null array
        }

        Pair<K, V> smallest = pairs[0];
        for (Pair<K, V> pair : pairs) {
            if (pair.compareTo(smallest) < 0) {
                smallest = pair;
            }
        }
        return smallest;
    }
    
    // compareTo(bla) == 0: checking for equality
    public static <E extends Comparable<E>> int sequentialSearch(final E[] data, final E toFind) {
        for (int i = 0; i < data.length; ++i) {
            E value = data[i];
            if (value.compareTo(toFind) == 0) { // Use compareTo for equality check
                return i; // Return the index if the element matches
            }
        }
        return -1; // Return -1 if the element is not found
    }
}

// Where are generics used?
//Collections: Type-safe storage and retrieval of objects (List<T>, Map<K,V>).
//Utility Methods: Reusable, type-safe methods and algorithms.
//Dependency Injection: Type-safe injection of dependencies (Spring).
//Stream API: Functional programming with streams.
//Custom Data Structures: Flexible and reusable implementations of stacks, queues, etc.
//Serialization: JSON/XML parsing and REST APIs.
//Frameworks and Libraries: Widely adopted in Spring, Hibernate, JUnit, etc.
// compile-time safety can be very beneficial, especially when running longer complicated tasks 