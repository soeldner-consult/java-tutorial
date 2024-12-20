package com.example;

public interface MathUtils {
	
	// here you should know that static methods are also allowed in interfaces
    // Static methods in an interface
    static int add(int a, int b) {
        return a + b;
    }

    static int subtract(int a, int b) {
        return a - b;
    }
    // Constant in the interface, use them sparingly..
    static final double PI = 3.14159;

    
    // more code that we would normally expect in an interface
    // default method with an implementation
    default double calculateCircleArea(double radius) {
        return PI * radius * radius;
    }
    
    // the usual "contract" methods in an interface, this is what we are rather used to..
    int myFunnyOperation(int a, int b);
}