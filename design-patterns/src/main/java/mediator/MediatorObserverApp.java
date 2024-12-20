package mediator;

import java.util.*;

//Observer Interface
interface Observer {
	void update(String event);
}

//Concrete Observer: A device that reacts to events
class Device implements Observer {
	 private final String name;
	
	 public Device(String name) {
	     this.name = name;
	 }
	
	 @Override
	 public void update(String event) {
	     System.out.println(name + " received event: " + event);
	 }
}

//Mediator Interface
interface Mediator {
	 void register(String eventType, Observer observer);
	 void unregister(String eventType, Observer observer);
	 void notifyObservers(String eventType, String event);
}


//Concrete Mediator (Singleton): Manages event registration and notification
/**
* EventMediator is a Singleton class that acts as the central hub for 
* communication between Observers and events. It manages the registration,
* unregistration, and notification of Observers for specific event types.
*/
class EventMediator implements Mediator {

	 // Singleton instance of EventMediator
	 private static EventMediator instance;
	
	 // A Map to store lists of Observers for each event type
	 // Key: Event type (String), Value: List of Observers registered for this event
	 private final Map<String, List<Observer>> observersMap = new HashMap<>();
	
	 /**
	  * Private constructor to prevent instantiation from outside the class.
	  * This ensures only one instance of EventMediator is created.
	  */
	 private EventMediator() {
	 }
	
	 /**
	  * Static method to provide access to the Singleton instance of EventMediator.
	  * It initializes the instance if it hasn't been created yet.
	  *
	  * @return the single instance of EventMediator
	  */
	 public static EventMediator getInstance() {
	     if (instance == null) {
	         instance = new EventMediator(); // Lazy initialization
	     }
	     return instance;
	 }
	
	 /**
	  * Registers an Observer for a specific event type.
	  * If no list exists for the given event type, it creates one.
	  *
	  * @param eventType the type of event the Observer wants to listen to
	  * @param observer  the Observer to be registered
	  */
	 @Override
	 public void register(String eventType, Observer observer) {
	     // Use computeIfAbsent to add a new list if the eventType is not already in the map
	     observersMap.computeIfAbsent(eventType, k -> new ArrayList<>()).add(observer);
	     System.out.println(observer + " registered for event: " + eventType);
	 }
	
	 /**
	  * Unregisters an Observer from a specific event type.
	  * If the Observer is not found, no action is taken.
	  *
	  * @param eventType the type of event the Observer is listening to
	  * @param observer  the Observer to be unregistered
	  */
	 @Override
	 public void unregister(String eventType, Observer observer) {
	     // Retrieve the list of observers for the given event type
	     List<Observer> observers = observersMap.get(eventType);
	     if (observers != null) {
	         // Remove the observer from the list
	         observers.remove(observer);
	         System.out.println(observer + " unregistered from event: " + eventType);
	     }
	 }

	 /**
	  * Notifies all registered Observers of a specific event type.
	  * If no Observers are registered for the event, a message is printed.
	  *
	  * @param eventType the type of event to notify Observers about
	  * @param event     the actual event message or data
	  */
	 @Override
	 public void notifyObservers(String eventType, String event) {
	     // Retrieve the list of observers for the given event type
	     List<Observer> observers = observersMap.get(eventType);
	     if (observers != null && !observers.isEmpty()) {
	         System.out.println("Mediator notifying observers of event: " + eventType);
	         // Notify each observer by calling its update() method
	         for (Observer observer : observers) {
	             observer.update(event);
	         }
	     } else {
	         // Print a message if no observers are registered for the event
	         System.out.println("No observers registered for event: " + eventType);
	     }
	 }
}

//Main Application: Client
public class MediatorObserverApp {
	 public static void main(String[] args) {
	     // Get Singleton Mediator
	     Mediator mediator = EventMediator.getInstance();
	
	     // Create Observers (Devices)
	     Observer livingRoomLight = new Device("Living Room Light");
	     Observer kitchenLight = new Device("Kitchen Light");
	     Observer bedroomFan = new Device("Bedroom Fan");
	
	     // Register Observers for events
	     mediator.register("TURN_ON", livingRoomLight);
	     mediator.register("TURN_ON", kitchenLight);
	     mediator.register("TURN_OFF", bedroomFan);
	     mediator.register("TURN_OFF", kitchenLight);
	
	     // Trigger events
	     System.out.println("\n-- Turning ON devices --");
	     mediator.notifyObservers("TURN_ON", "Devices turned ON");
	
	     System.out.println("\n-- Turning OFF devices --");
	     mediator.notifyObservers("TURN_OFF", "Devices turned OFF");
	
	     // Unregister an observer
	     System.out.println("\n-- Unregistering Kitchen Light from TURN_OFF --");
	     mediator.unregister("TURN_OFF", kitchenLight);
	
	     // Trigger events again
	     System.out.println("\n-- Turning OFF devices after unregistering --");
	     mediator.notifyObservers("TURN_OFF", "Devices turned OFF");
	 }
}