package trafficlights;

enum TrafficLightColor {
    GREEN, YELLOW, YELLOW_RED, RED
}

// TrafficLight class (reuse your original logic)
class TrafficLight {
	
	// current state is represented by the enum above!
    private TrafficLightColor color;
    private static TrafficLight instance;
    
    // initializer: instance variables can be initialized independent of a particular constructor
    {
    	this.color = TrafficLightColor.GREEN;
    }
    
    private TrafficLight() {
         // Start with RED
    }
    
    public static TrafficLight getTrafficLight() {
    	if (instance == null) { // Check if the instance is null
        	// only one thread can execute the synchronized block or method at a time for a given object or class. 
        	// This helps prevent race conditions and ensures thread safety when multiple threads access shared resources.
        	
        	// thread 1,2,3,4,5
            synchronized (TrafficLight.class) { // Thread-safe lazy initialization, lock the class object
            	// If there’s no second if (instance == null) check inside the block, these 
            	// threads will reinitialize the singleton 
            	// because they don’t check whether the instance was already created by the first thread.
            	if (instance == null) { // Double-checked locking - 
            		// needed when several threats were able to pass the first null check
                    instance = new TrafficLight();
                } else { // Singleton was already created

                }
            }
        }
        return instance; 
    }

    public TrafficLightColor getColor() {
        return color;
    }
    
    // the actual values are changed here, the GUI is feeded with these underlying values
    public void switchColor() {
        switch (color) {
            // current value is RED
        	case RED:
            	// reassignment of color enum value!
                color = TrafficLightColor.YELLOW_RED;
                break;
            case YELLOW_RED:
                color = TrafficLightColor.GREEN;
                break;
            case GREEN:
                color = TrafficLightColor.YELLOW;
                break;
            case YELLOW:
                color = TrafficLightColor.RED;
                break;
        }
    }
}