package chainer;

public class MethodChainingApp {
    private String name;
    private int age;
    private String city;
    private String profession;

    // Setter for 'name'
    public MethodChainingApp setName(String name) {
        this.name = name;
        return this; // Return the current object for chaining, that's already the trick.
    }

    // Setter for 'age'
    public MethodChainingApp setAge(int age) {
        this.age = age;
        return this;
    }

    // Setter for 'city'
    public MethodChainingApp setCity(String city) {
        this.city = city;
        return this;
    }

    // Setter for 'profession'
    public MethodChainingApp setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    // Display method to print the object's state
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Profession: " + profession);
    }

    public static void main(String[] args) {
        // Demonstrate method chaining
        MethodChainingApp person = new MethodChainingApp()
                .setName("Maxi Meier")
                .setAge(25)
                .setCity("Ansbach")
                .setProfession("Java Developer");

        // Display the final state
        person.display();
    }
}

//Use Cases for Method Chaining
//Method chaining is particularly useful in scenarios where you need to configure and build complex objects, such as configuration settings, builders, or data processing. Here are a few examples of where method chaining shines:
//
//Database Query Builders: Creating SQL queries with chained methods for SELECT, WHERE, and JOIN clauses.
//GUI Frameworks: Configuring graphical user interfaces with methods for layout, styling, and event handling.
//Fluent Interfaces: Designing APIs that provide a natural and expressive language for developers.
//Stream Processing: Chaining operations on streams, as seen in Java’s Stream API.
