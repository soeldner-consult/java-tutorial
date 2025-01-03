package miscellaneous;

import java.util.Arrays;

public class MiscellaneousApp {

    public static void main(String[] args) {
        System.out.println("Part 1: Shallow and Deep Copies\n");
        demonstrateCopying();

        System.out.println("\nPart 2: Multithreading Examples\n");
        demonstrateMultithreading();
        
        try {
			multithreadingDemo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Part 1: Shallow and Deep Copies
    private static void demonstrateCopying() {
        // Original array (reference type)
        int[] originalArray = {1, 2, 3, 4, 5};

        // Shallow copy
        int[] shallowCopy = originalArray;

        // Deep copy
        int[] deepCopy = Arrays.copyOf(originalArray, originalArray.length);

        System.out.println("Original array:      " + Arrays.toString(originalArray));
        System.out.println("Shallow copy:        " + Arrays.toString(shallowCopy));
        System.out.println("Deep copy:           " + Arrays.toString(deepCopy));

        // Modify original array
        originalArray[0] = 99;

        System.out.println("\nAfter modifying the original array:");
        System.out.println("Original array:      " + Arrays.toString(originalArray));
        System.out.println("Shallow copy (linked): " + Arrays.toString(shallowCopy));
        System.out.println("Deep copy (independent): " + Arrays.toString(deepCopy));

        // Pitfall: Shallow copies only duplicate references, not the objects they point to
        Person[] people = {new Person("Alice"), new Person("Bob")};
        Person[] shallowPeopleCopy = people;
        Person[] deepPeopleCopy = Arrays.stream(people).map(Person::new).toArray(Person[]::new);

        System.out.println("\nBefore modifying the objects:");
        System.out.println("Original people:      " + Arrays.toString(people));
        System.out.println("Shallow copy:         " + Arrays.toString(shallowPeopleCopy));
        System.out.println("Deep copy:            " + Arrays.toString(deepPeopleCopy));

        people[0].setName("Charlie");

        System.out.println("\nAfter modifying the first person:");
        System.out.println("Original people:      " + Arrays.toString(people));
        System.out.println("Shallow copy (linked): " + Arrays.toString(shallowPeopleCopy));
        System.out.println("Deep copy (independent): " + Arrays.toString(deepPeopleCopy));
    }

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public Person(Person other) {
            this.name = other.name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Part 2: Multithreading Examples
    private static void demonstrateMultithreading() {
        // Using Thread class
        Thread thread1 = new ThreadExample();
        thread1.start();

        // Using Runnable interface
        Thread thread2 = new Thread(new RunnableExample());
        thread2.start();

        // Using Lambda for Runnable
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Lambda thread: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread3.start();
    }
    
    // Part 2 continued: Multithreading
    private static void multithreadingDemo() throws InterruptedException {
        // Thread using Runnable
        Thread runnableThread = new Thread(() -> {
            System.out.println("Runnable Thread: Running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Runnable Thread: Interrupted");
            }
        }, "RunnableThread");

        // Thread using subclass of Thread
        class CustomThread extends Thread {
            @Override
            public void run() {
                System.out.println(getName() + ": Running");
            }
        }

        Thread customThread = new CustomThread();
        customThread.setName("CustomThread");

        // Demonstrating Thread methods
        runnableThread.start();
        System.out.println("RunnableThread isAlive: " + runnableThread.isAlive());

        customThread.setDaemon(true);
        System.out.println("CustomThread isDaemon: " + customThread.isDaemon());
        customThread.start();

        // Change and get thread priority
        customThread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("CustomThread Priority: " + customThread.getPriority());

        // Thread join
        runnableThread.join();
        System.out.println("RunnableThread has finished.");

        // Check thread access
        customThread.checkAccess();
        System.out.println("CustomThread has access checked successfully.");

        // Thread name
        System.out.println("RunnableThread Name: " + runnableThread.getName());

        // Thread status after completion
        System.out.println("RunnableThread isAlive after join: " + runnableThread.isAlive());
    }
    

    // Thread Example
    static class ThreadExample extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("ThreadExample: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Runnable Example
    static class RunnableExample implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println("RunnableExample: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
