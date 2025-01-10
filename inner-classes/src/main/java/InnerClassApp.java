public class InnerClassApp {
	
	private String outerField = "OuterClass Field";
	
	// --- 1. Static Nested Class ---
    // A static nested class does not need an instance of the outer class to be instantiated.
    static class StaticNestedClass {
        void display() {
            System.out.println("This is a Static Nested Class.");
        }
    }

    // --- 2. Inner (non-static) Class ---
    // A non-static inner class requires an instance of the outer class.
    class InnerClass {
        void display() {
            System.out.println("This is an Inner (Non-static) Class.");
        }
    }

    // Method to demonstrate Local and Anonymous Inner Classes
    public void demonstrateInnerClasses() {
        // --- 3. Local (Inner) Class ---
        // A class declared within a method, visible only inside the method.
        class LocalInnerClass {
            void display() {
                System.out.println("This is a Local (Inner) Class.");
            }
        }

        // Create an instance of the Local Inner Class
        LocalInnerClass localInner = new LocalInnerClass();
        localInner.display();

        // --- 4. Anonymous Inner Class ---
        // An unnamed class that implements an interface or extends a class inline.
        // choosing Runnable is just convenient here as it can handily run the @Override run() method..
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is an Anonymous Inner Class implementing Runnable.");
            }
        };
        // Call the run method of the anonymous inner class
        anonymousInner.run();
    }
    
    // First-level Inner Class
    class InertClass {
        private String innerField = "InnerClass Field";

        void display() {
            // Access the inner class member using "this"
            System.out.println("InnerClass Field: " + this.innerField);

            // Access the outer class member using <OuterClass>.this
            System.out.println("OuterClass Field: " + InnerClassApp.this.outerField);
        }

        // Second-level Nested Inner Class
        class NestedInnerClass {
            private String nestedField = "NestedInnerClass Field";

            void display() {
                // Access members of NestedInnerClass
                System.out.println("NestedInnerClass Field: " + this.nestedField);

                // Access members of the first-level InnerClass
                System.out.println("InnerClass Field: " + InertClass.this.innerField);

                // Access members of the OuterClass
                System.out.println("OuterClass Field: " + InnerClassApp.this.outerField);
            }
        }
    }
    
    public static void main(String[] args) {
        
    	System.out.println("Demonstrating Different Types of Inner Classes in Java\n");

        // 1. Static Nested Class
        InnerClassApp.StaticNestedClass staticNested = new InnerClassApp.StaticNestedClass();
        staticNested.display();

        // 2. Inner (Non-Static) Class
        // an instance of the outer (declaring) type is necessary
        InnerClassApp outerInstance = new InnerClassApp();
        // only then ..
        InnerClassApp.InnerClass innerInstance = outerInstance.new InnerClass();
        innerInstance.display();

        // 3. Local Inner Class and 4. Anonymous Inner Class
        outerInstance.demonstrateInnerClasses();
        

        // Create an instance of InnerClass
        InnerClassApp.InertClass inner = outerInstance.new InertClass();
        System.out.println("Calling display() of InnerClass:");
        inner.display();

        System.out.println();

        // Create an instance of NestedInnerClass
        InnerClassApp.InertClass.NestedInnerClass nestedInner = inner.new NestedInnerClass();
        System.out.println("Calling display() of NestedInnerClass:");
        nestedInner.display();
    }
}


