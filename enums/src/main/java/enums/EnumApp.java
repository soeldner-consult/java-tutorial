package enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Locale;
import java.util.ResourceBundle;

// Basic Enum with constants
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    
}

//Enum with fields, constructor, and overridden toString
enum Severity {
	 LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4);
	
	 private final int level;
	
	 // Constructor
	 Severity(int level) {
	     this.level = level;
	 }
	
	 // Getter for level
	 public int getLevel() {
	     return level;
	 }
	
	 // Override toString to provide custom descriptions
	 @Override
	 public String toString() {
	     switch (this) {
	         case LOW:
	             return "LOW: Low severity - Monitor.";
	         case MEDIUM:
	             return "MEDIUM: Medium severity - Take action.";
	         case HIGH:
	             return "HIGH: High severity - Immediate action required.";
	         case CRITICAL:
	             return "CRITICAL: Critical severity - Emergency!";
	         default:
	             return "Unknown severity.";
	     }
	 }
}

// Enum demonstrating advanced features: EnumSet and EnumMap
enum Status {
    TODO, IN_PROGRESS, COMPLETED, BLOCKED
}

public class EnumApp {

    public static void main(String[] args) {
        // 1. Basic Enum Example
        System.out.println("=== Basic Enum Example ===");
        Day today = Day.TUESDAY;
        System.out.println("Today is: " + today);

        // 2. Switch Statement with Enums
        System.out.println("\n=== Switch Statement Example ===");
        switch (today) {
            case MONDAY:
                System.out.println("It's Monday. Start of the work week!");
                break;
            case FRIDAY:
                System.out.println("It's Friday. The weekend is near!");
                break;
            default:
                System.out.println("It's a regular weekday: " + today);
        }

        // 3. Enum with fields, methods, and custom logic
        System.out.println("\n=== Enum with Fields and Methods ===");
        Severity severity = Severity.HIGH;
        System.out.println("Severity: " + severity);
        System.out.println("Level: " + severity.getLevel());
        System.out.println("Description: " + severity);

        // 4. Iterate over Enum values
        System.out.println("\n=== Iterating Over Enum Values ===");
        for (Severity sev : Severity.values()) {
            System.out.println("Severity Level " + sev.getLevel() + ": " + sev);
        }

        // 5. EnumSet Example
        // explain use case
        System.out.println("\n=== EnumSet Example ===");
        EnumSet<Status> statusSet = EnumSet.of(Status.TODO, Status.IN_PROGRESS);
        System.out.println("Statuses in progress: " + statusSet);

        // Add a status
        statusSet.add(Status.BLOCKED);
        System.out.println("Updated Statuses: " + statusSet);

        // 6. EnumMap Example
        System.out.println("\n=== EnumMap Example ===");
        EnumMap<Status, String> statusMap = new EnumMap<>(Status.class);
        statusMap.put(Status.TODO, "Task needs to be started");
        statusMap.put(Status.IN_PROGRESS, "Task is currently in progress");
        statusMap.put(Status.COMPLETED, "Task is done");
        statusMap.put(Status.BLOCKED, "Task is blocked");

        for (Status status : statusMap.keySet()) {
            System.out.println(status + ": " + statusMap.get(status));
        }
    }
}