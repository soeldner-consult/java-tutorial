package com.example;

public class Singleton {

	// Static variable to hold the single instance of the class
	// the class becomes a static "field" of the class!
    private static Singleton instance;

    // Private constructor to prevent instantiation from outside the class
    private Singleton() {
        System.out.println("Singleton Instance Created");
    }

    // Lazy init
    //  Public static method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) { // Check if the instance is null
        	// only one thread can execute the synchronized block or method at a time for a given object or class. 
        	// This helps prevent race conditions and ensures thread safety when multiple threads access shared resources.
        	
        	// thread 1,2,3,4,5
            synchronized (Singleton.class) { // Thread-safe lazy initialization, lock the class object
            	// If there’s no second if (instance == null) check inside the block, these 
            	// threads will reinitialize the singleton 
            	// because they don’t check whether the instance was already created by the first thread.
            	if (instance == null) { // Double-checked locking - 
            		// needed when several threats were able to pass the first null check
                    instance = new Singleton();
                } else { // Singleton was already created

                }
            }
        }
        return instance;
    }
    
    // Eager initialization
    // private static final Singleton instance = new Singleton();
    

    // Example method to demonstrate Singleton behavior
    public void displayMessage() {
        System.out.println("Hello from Singleton!");
    }
}