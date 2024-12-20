package generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	     return this.value.compareTo(other.value);
	 }
	
	 @Override
	 public String toString() {
	     return "Box{" + "value=" + value + '}';
	 }
}

// 5. Custom generic class to demonstrate <K, V>
class Pair<K, V> {
	 private K key;
	 private V value;
	
	 public Pair(K key, V value) {
	     this.key = key;
	     this.value = value;
	 }
	
	 public K getKey() {
	     return key;
	 }
	
	 public V getValue() {
	     return value;
	 }
	
	 @Override
	 public String toString() {
	     return key + " -> " + value;
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

        // Map<K, V> where K is Integer and V is a Pair<String, Double>
        Map<Integer, Pair<String, Double>> productMap = new HashMap<>();
        productMap.put(101, new Pair<>("Laptop", 999.99));
        productMap.put(102, new Pair<>("Phone", 499.49));
        productMap.put(103, new Pair<>("Tablet", 299.99));

        System.out.println("\nProduct Details:");
        printMap(productMap);
        
		//        1 Plane<String[]> plane = new Plane<String[]>(); ①
		//        2 Plane<String>[] fleet = new Plane<String>[5]; ②
        // this would be allowed!
        // List<Plane<String>> fleet = new ArrayList<>();
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