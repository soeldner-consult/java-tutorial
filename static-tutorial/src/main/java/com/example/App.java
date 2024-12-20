package com.example;

/**
 * Hello Ansbach!
 *
 */
public class App implements MathUtils
{
	
	// the final modifier in Java makes a variable unchangeable after its initialization.
	// the static modifier makes a variable a class variable, 
	// this means the variable belongs to the class rather than to any specific instance of the class.
	public final static double PI = 3.1415;
	public static double PI_2 = 3.1415;
	
    public static void main( String[] args )
    {
    	System.out.println(PI);
        System.out.println(App.PI);
        
        // PI = 3.4567; not possible because "final"!
        
        System.out.println(PI_2);
        App.PI_2 = 3.14159265;
        System.out.println(PI_2);
//        
        // normal instantiated object
        User myUser = new User();
        myUser.setForename("Max");
        myUser.setSurname("Mustermann");
        System.out.println(myUser.getSurname());
//        
//        // back to static examples
        System.out.println(User.DEFAULT_USER);
//        
//        Exceptions: 
//        	- checked/unchecked
//        	- print stacktrace
        
//        
        Singleton singleton1 = Singleton.getInstance();
        singleton1.displayMessage();

        // Verifying that only one instance exists
        Singleton singleton2 = Singleton.getInstance();
        if (singleton1 == singleton2) {
            System.out.println("Both references point to the same Singleton instance -> We are so unique ;-)");
        } else {
            System.out.println("Different instances exist (this is not supposed to happen).");
        }
//        
//        // Using static methods in the interface
        int sum = MathUtils.add(10, 5);
        int difference = MathUtils.subtract(10, 5);

        // Accessing constants directly from the interface
        double pi = MathUtils.PI;
        
        // MathUtils.PI = 0.01; not possible

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("PI: " + pi);
        
        // Creating an instance of the implementing class
        App app = new App();
//
        // Using the default method
        double area = app.calculateCircleArea(5.0);
        System.out.println("Circle Area: " + area);
        
        System.out.println(app.myFunnyOperation(3, 2));
    }

	@Override
	public int myFunnyOperation(int a, int b) {
		return a*b;
	}
}
