package eccezioni;

import java.util.ArrayList;

public class Eccezioni {

	 public static void main(String[] args) {
        		 
		 try {
            // Trigger a checked exception
            throwCheckedException();

            
            
        // the line catch (CustomCheckedException exceptionObject) implicitly creates an exception object, 
        // but the object itself is not created in the catch block. Instead, the object is instantiated 
        // when the exception is thrown, and the catch block only references this pre-existing exception object.
        } catch (CustomCheckedException exceptionObject) {
            System.out.println("Caught checked exception: " + exceptionObject.getMessage());
            
            
        // The catch block does not create a new exception object; it simply receives a reference to the 
        // object that was thrown. The catch parameter (e in this case) allows you to interact with 
        // the exception object (e.g., to retrieve its message, stack trace, or cause).
        } 
		try {
			// Trigger an unchecked exception
            throwUncheckedException();
		}
		catch (RuntimeException exceptionObject) {
        	
	            System.out.println("Caught unchecked exception: " + exceptionObject.getMessage());
        }
        
        try {
        	int x = 1/0;
        } catch (ArithmeticException e) {
        	System.out.println(e.getMessage());
        }
        	
        // this code block will throw an OutOfMemoryError
        // while it is possible to write something like 
        // try { .. } catch (OutOfMemoryError err) { .. }
        // it is heavily discouraged. So "don't try this at home!".
        ArrayList<int[]> memoryHog = new ArrayList<>();
        while (true) {
            memoryHog.add(new int[1000000]); // Allocating large arrays in a loop
        }
        // The literal meaning of hog is "a full-grown pig". Since pigs are typically greedy eaters, 
        // the figurative meaning of "hog" is "someone / something that consumes resources in a greedy 
        // or inconsiderate manner".
        
//        long memoryLimit = getMemoryLimit();
//        System.out.println("Memory Limit for Allocation: " + memoryLimit / (1024 * 1024) + " MB");
//
//        // Perform memory-hogging operation within the limit
//        memoryHog(memoryLimit);
        
//        ArrayList<int[]> memorieHog = new ArrayList<>();
//	    while(true) {
//	    	 memorieHog.add(new int[1000000]);
//	    	 System.out.println(memorieHog.size());
//	    }
        
        
    }

    // A method that throws a custom checked exception
	// The exception object is created at the throw statement.
    public static void throwCheckedException() throws CustomCheckedException {
    	
        throw new CustomCheckedException("This is a checked exception.");
    }

    // A method that throws an unchecked exception
    public static void throwUncheckedException() {
        throw new RuntimeException("This is an unchecked exception.");
    }

    // Custom checked exception class
    static class CustomCheckedException extends Exception {
        public CustomCheckedException(String message) {
            super(message); // Calls Throwable's constructor to store the message.
        }
        // Exception-Builder pattern?
    }
    
    public static long getMemoryLimit() {
        Runtime runtime = Runtime.getRuntime();

        // Get maximum memory the JVM can use
        long maxMemory = runtime.maxMemory();

        // Define memory limit as half of the maximum memory
        return maxMemory / 2;
    }
    
    public static void memoryHog(long memoryLimit) {
        Runtime runtime = Runtime.getRuntime();
        ArrayList<int[]> memoryHog = new ArrayList<>();
        long usedMemory;

        try {
            while (true) {
                // Calculate the used memory
                usedMemory = runtime.totalMemory() - runtime.freeMemory();

                // Stop allocating if the memory used exceeds the limit
                if (usedMemory >= memoryLimit) {
                    System.out.println("Memory limit reached. Stopping allocation.");
                    break;
                }

                // Allocate memory greedily
                memoryHog.add(new int[100000]); // Adjust size for granularity
                System.out.println("Allocated memory, used: " + usedMemory / (1024 * 1024) + " MB");
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Caught OutOfMemoryError! Allocation stopped.");
        }

        // Final memory usage
        System.out.println("Final Memory Usage:");
        System.out.println("Used Memory: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " MB");
    }
    
}


/*
 * 1. Checked Exceptions Can Lead to Boilerplate Code
In Java, checked exceptions must be either caught or declared in the throws clause.
While this enforces strict error handling, it often results in verbose code with repeated try-catch blocks that clutter the logic of your application.
In enterprise applications, this verbosity becomes even more significant when dealing with APIs or libraries that frequently throw checked exceptions (like JDBC, Hibernate, etc.).
For example, handling a SQLException directly in a JDBC application looks like this:

java
Code kopieren
try {
    Connection conn = dataSource.getConnection();
    // Perform database operations
} catch (SQLException e) {
    // Handle exception
    throw new RuntimeException("Database operation failed", e);
}
In a Spring application, this can be replaced with cleaner code because Spring wraps the SQLException into 
a runtime exception like DataAccessException, which doesn't need explicit handling.

2. Promote Cleaner Code with Focus on Business Logic
By using unchecked exceptions, Spring allows developers to focus on business logic instead of writing repetitive error-handling code.
You’re not forced to catch every possible exception. Instead, you only handle exceptions that are truly relevant to your application.
For example:

java
Code kopieren
userService.saveUser(user);
In this code, if a database error occurs, Spring will throw a runtime exception like DataAccessException. You can decide whether to handle it at this level or propagate it further, without cluttering your code with try-catch blocks.
3. Checked Exceptions Are Often Unrecoverable
Many checked exceptions, like SQLException, are not recoverable at runtime. For instance, if the database is down, retrying or recovering from the error may not be possible in most cases.
Forcing developers to handle these exceptions with checked exceptions often adds no real value, since the best they can do is log the error and rethrow it.
Spring converts such exceptions to unchecked exceptions, allowing them to propagate naturally without forcing boilerplate handling.

4. Unchecked Exceptions and Transaction Rollbacks
Spring provides transaction management in its framework. When an unchecked exception is thrown, Spring automatically rolls back the transaction (by default).
This behavior is consistent with how most application servers and frameworks handle exceptions: any runtime exception triggers a rollback.
With checked exceptions, you would need additional configuration or code to ensure proper rollback behavior. By using unchecked exceptions, Spring simplifies this process.

5. Flexibility for the Developer
With unchecked exceptions, the developer has the choice to handle exceptions where appropriate, rather than being forced by the compiler.
This aligns with the philosophy of Spring: to provide developers with flexible tools rather than enforcing strict patterns.
How Does Spring Do This?
Spring typically wraps checked exceptions in runtime exceptions using custom exception hierarchies. For example:

DataAccessException (Unchecked Exception):

Spring wraps checked exceptions like SQLException into its own runtime exception hierarchy, such as DataAccessException.
Example:

java
Code kopieren
try {
    jdbcTemplate.execute("INSERT INTO users ...");
} catch (DataAccessException e) {
    System.out.println("A database error occurred: " + e.getMessage());
}
TransactionException:

Checked exceptions related to transaction management are also wrapped in unchecked exceptions.
Benefits of Converting Checked to Unchecked Exceptions
Advantages	Description
Cleaner Code	No need for repetitive try-catch blocks, allowing code to focus on business logic.
Simplified Error Handling	Developers only handle exceptions they care about, reducing noise in the code.
Propagation Without Boilerplate	Exceptions naturally propagate to higher layers for centralized handling.
Automatic Transaction Rollback	Runtime exceptions trigger automatic rollbacks in Spring-managed transactions.
Framework Consistency	Spring ensures consistent exception handling across the application.
Drawbacks and Criticism
While the approach is convenient, some developers criticize this philosophy:

Unchecked exceptions can obscure root causes: If you're not careful, wrapping checked exceptions in unchecked exceptions can make debugging harder if the root cause (e.g., an IOException) is not logged or preserved.
Lack of enforcement: Checked exceptions force developers to explicitly handle specific scenarios, while unchecked exceptions leave it to the developer's discretion, increasing the risk of missing important error handling.
Best Practices for Spring Exception Handling
To make the most of Spring’s philosophy while maintaining robustness:

Centralize Exception Handling: Use a global exception handler, such as a @ControllerAdvice in Spring MVC or a custom ErrorHandler for REST APIs.

Example:

java
Code kopieren
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
        return new ResponseEntity<>("Database error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
Preserve Root Cause: Always ensure that the original exception (cause) is logged or preserved when wrapping exceptions.

Document Unchecked Exceptions: Even though runtime exceptions are not enforced, document them in your method signatures so that other developers know what to expect.

Example:

java
Code kopieren
**
 * Saves a user to the database.
 * @throws DataAccessException if a database error occurs.
 
public void saveUser(User user) {
    // ...
}
Conclusion
Spring's philosophy of converting checked exceptions to unchecked exceptions simplifies code by removing 
unnecessary boilerplate and focuses on enabling developers to handle exceptions flexibly and efficiently. 
However, it comes with the responsibility of designing robust error-handling mechanisms to ensure important 
issues don’t go unnoticed.
 * 
 */

