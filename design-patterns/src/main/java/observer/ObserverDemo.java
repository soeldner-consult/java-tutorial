package observer;

import java.util.ArrayList;
import java.util.List;

// Subject
interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

interface Observer {
    // Method to update the observer
    void update(Integer data);
}

// ConcreteSubject
class Display implements Observer {
    private final String name;
    
    public Display(String name) {
        this.name = name;
    }

    @Override
    public void update(Integer data) {
        System.out.println(name + " received update: Temperature = " + data + "°C");
    }
}

class WeatherData implements Observable {
    private int temperature;
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            // uses ConcreteObserver
        	observer.update(temperature);
        }
    }

    // Custom method to update temperature
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("WeatherData: Temperature updated to " + temperature + "°C");
        notifyObservers();
    }
}


public class ObserverDemo {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        // Create observers
        Display display1 = new Display("Display 1");
        Display display2 = new Display("Display 2");

        // Register observers
        weatherData.attach(display1);
        weatherData.attach(display2);

        // Update temperature and notify observers
        weatherData.setTemperature(25);
        weatherData.setTemperature(30);

        // Remove one observer and notify again
        weatherData.detach(display1);
        weatherData.setTemperature(35);
    }
}


//When to Use the Observer Pattern
//When an object needs to notify other objects about changes in its state.
//
//In scenarios like:
//
//Event listeners in GUI applications.
//
//Real-time data feeds, like stock tickers or live scores.
//
//Messaging systems, such as RabbitMQ or Kafka.