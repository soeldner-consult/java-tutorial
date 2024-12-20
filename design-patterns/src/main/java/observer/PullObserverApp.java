//package observer;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//// Subject: WeatherData
//class WeatherData {
//    private int temperature;
//
//    public int getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(int temperature) {
//        this.temperature = temperature;
//    }
//}
//
//// Observer: Display
//class Display {
//    private final String name;
//    private final WeatherData weatherData;
//
//    public Display(String name, WeatherData weatherData) {
//        this.name = name;
//        this.weatherData = weatherData;
//    }
//
//    public void checkForUpdates() {
//        int currentTemperature = weatherData.getTemperature();
//        System.out.println(name + " pulled update: Temperature = " + currentTemperature + "°C");
//    }
//}
//
//// Main Class
//public class PullObserverApp {
//    public static void main(String[] args) {
//        System.out.println("Observer Pattern with Pure Pull-Based Notifications:");
//
//        // Subject
//        WeatherData weatherData = new WeatherData();
//        weatherData.setTemperature(25);
//
//        // Observers
//        Display display1 = new Display("Display 1", weatherData);
//        Display display2 = new Display("Display 2", weatherData);
//
//        // Simulate polling using a Timer
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                display1.checkForUpdates();
//                display2.checkForUpdates();
//            }
//        }, 0, 2000); // Polling every 2 seconds
//
//        // Simulate data changes
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                weatherData.setTemperature(30);
//                System.out.println("WeatherData: Temperature changed to 30°C");
//            }
//        }, 5000); // Change after 5 seconds
//
//        // Stop the program after 10 seconds
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("Stopping polling...");
//                timer.cancel();
//            }
//        }, 10000);
//    }
//}