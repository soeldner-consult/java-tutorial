package miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//Article Class:
//
//Implements Cloneable and overrides the clone method to perform a shallow copy.
//The clone method simply calls super.clone().
//Order Class:
//
//Contains a List<Article>.
//Implements Cloneable and overrides the clone method to perform a deep copy.
//The clone method creates a new Order object and iterates through the list of articles, cloning each article and adding the cloned article to the new list.
//Example Usage:
//
//Demonstrates creating an Article and performing a shallow copy.
//Demonstrates creating an Order with a list of articles and performing a deep copy.
//Modifies the original article to show that the deep copied order remains unaffected.

class Article implements Cloneable {
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

class Order implements Cloneable {
    private List<Article> articles;

    public Order() {
        this.articles = new ArrayList<>();
    }

    public Order(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(articles, order.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articles);
    }

    @Override
    protected Order clone() throws CloneNotSupportedException {
        Order clonedOrder = (Order) super.clone();
        clonedOrder.articles = new ArrayList<>();
        for (Article article : this.articles) {
            clonedOrder.articles.add((Article) article.clone());
        }
        return clonedOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "articles=" + articles +
                '}';
    }
}

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
        
    // finally object cloning, shallow/deep copy..
    try {
		cloningDemo();
	} catch (CloneNotSupportedException e) {
		e.printStackTrace();
	}
        
    }
    // now shallow/deep copies with objects
    // class java.lang.CloneNotSupportedException extends java.lang.Exception
    // so checked exception, so throws declaration here and try/catch block
    // in the method become obligatory
    private static void cloningDemo() throws CloneNotSupportedException {
    	// Create an Article
        Article article1 = new Article("Title1", "Content1");
        Article article2 = new Article("Title2", "Content2");

        // Shallow copy of Article
        Article shallowCopiedArticle = (Article) article1.clone();
        System.out.println("Shallow Copied Article: " + shallowCopiedArticle);

        // Create an Order with a list of Articles
        Order order = new Order(Arrays.asList(article1, article2));

        // Deep copy of Order
        Order deepCopiedOrder = order.clone();
        System.out.println("Deep Copied Order: " + deepCopiedOrder);

        // Modify the original article to see the effect on the copied order
        article1.setTitle("Modified Title1");
        article1.setContent("Modified Content1");

        System.out.println("Original Order after modification: " + order);
        System.out.println("Deep Copied Order after modification: " + deepCopiedOrder);

    }
    
    
    // Part 1: Shallow and Deep Copies
    private static void demonstrateCopying() {
        // Original array (reference type)
        int[] originalArray = {1, 2, 3, 4, 5};

        // Shallow copy of array
        int[] shallowCopy = originalArray;

        // Deep copy of arrays
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
        
    	MiscellaneousApp mApp = new MiscellaneousApp();
    	
    	// Using Thread class
    	Thread thread1 = new ThreadExample();
        thread1.start();

        // Using Runnable interface
        Thread thread2 = new Thread(mApp.new RunnableExample());
        thread2.start();

        // Using Lambda for Runnable -> look at this again for the exam
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
        // Thread using Runnable with a "()" lambda expression
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

    // RunnableExample class which implements the Runnable interface
    class RunnableExample implements Runnable {
    	
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
