package observer;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.util.concurrent.TimeUnit;

public class RxWeatherObserverApp {

    // Internal Subject class: WeatherData
    static class WeatherData {
        private final BehaviorSubject<Integer> temperatureSubject; // Rx Subject for temperature

        public WeatherData(int initialTemperature) {
            // BehaviorSubject starts with an initial value and emits new values on change
            this.temperatureSubject = BehaviorSubject.createDefault(initialTemperature);
        }

        public void setTemperature(int temperature) {
            System.out.println("WeatherData: Temperature changed to " + temperature + "°C");
            temperatureSubject.onNext(temperature); // Emit the new temperature
        }

        public Observable<Integer> getTemperatureObservable() {
            return temperatureSubject; // Expose the Observable to observers
        }
    }

    // Internal Observer class: Display
    static class Display {
        private final String name;

        public Display(String name) {
            this.name = name;
        }

        public void subscribeTo(Observable<Integer> temperatureObservable) {
            // Subscribe to the temperature Observable and print updates
            temperatureObservable.subscribe(
                temp -> System.out.println(name + " received update: Temperature = " + temp + "°C"),
                throwable -> System.err.println(name + " encountered an error: " + throwable),
                () -> System.out.println(name + " stream completed.")
            );
        }
    }

    // Main method
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Observer Pattern in RxJava Style:");

        // Create WeatherData subject with initial temperature
        WeatherData weatherData = new WeatherData(25);

        // Create observers (displays)
        Display display1 = new Display("Display 1");
        Display display2 = new Display("Display 2");

        // Subscribe the displays to the WeatherData observable
        display1.subscribeTo(weatherData.getTemperatureObservable());
        display2.subscribeTo(weatherData.getTemperatureObservable());

        // Simulate temperature changes over time
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribe(t -> weatherData.setTemperature(30)); // Change temp to 30°C after 5s

        Observable.timer(8, TimeUnit.SECONDS)
                .subscribe(t -> weatherData.setTemperature(35)); // Change temp to 35°C after 8s

        // Allow program to run for 10 seconds before exiting
        Thread.sleep(10000);
        System.out.println("Program terminated.");
    }
}
