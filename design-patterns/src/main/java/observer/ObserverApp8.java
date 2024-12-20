package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable; // Deprecated in Java 9
import java.util.Observer;

public class ObserverApp8 {

    public static void main(String[] args) {
        System.out.println("Observer Pattern Prior to Java 9:");
        observerPatternPreJava9();
    }

    // ----------- Observer Pattern Pre-Java 9 (Deprecated) ----------------
    private static void observerPatternPreJava9() {
        // Observable (Subject)
        class WeatherData extends Observable {
            private int temperature;

            public void setTemperature(int temperature) {
                this.temperature = temperature;
                setChanged(); // Mark as changed
                notifyObservers(temperature); // Push data to Observers
            }
        }

        // Observer
        class Display implements Observer {
            private String name;

            public Display(String name) {
                this.name = name;
            }

            @Override
            public void update(Observable o, Object arg) {
                System.out.println(name + " received update: Temperature = " + arg + "°C");
            }
        }

        // Usage
        WeatherData weatherData = new WeatherData();
        Display display1 = new Display("Display 1");
        Display display2 = new Display("Display 2");

        weatherData.addObserver(display1);
        weatherData.addObserver(display2);

        weatherData.setTemperature(25); // Notify Observers
        weatherData.setTemperature(30); // Notify Observers
    }

}
