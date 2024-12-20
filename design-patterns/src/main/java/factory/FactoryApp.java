package factory;

// Produkt-Interface
interface Shape {
	void draw();
}

class Circle implements Shape {
	 public void draw() {
	     System.out.println("Drawing a Circle");
	 }
}

class Rectangle implements Shape {
	 public void draw() {
	     System.out.println("Drawing a Rectangle");
	 }
}

//Factory-Klasse zur Erstellung der Objekte
class ShapeFactory {
	 public static Shape getShape(String shapeType) {
	     if (shapeType == null) return null;
	     switch (shapeType.toLowerCase()) {
	         case "circle":
	             return new Circle();
	         case "rectangle":
	             return new Rectangle();
	         default:
	             throw new IllegalArgumentException("Unknown shape type");
	     }
	 }
}

public class FactoryApp {
	 public static void main(String[] args) {
	     Shape circle = ShapeFactory.getShape("circle");
	     circle.draw();  // Ausgabe: Drawing a Circle
	
	     Shape rectangle = ShapeFactory.getShape("rectangle");
	     rectangle.draw();  // Ausgabe: Drawing a Rectangle
	     
	     Shape blub = ShapeFactory.getShape("blub");
	     blub.draw();
	 }
}

// for more complex cases abstract classes are more suitable