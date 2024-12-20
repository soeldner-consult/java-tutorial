package trafficlights;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TrafficLightApp extends Application {
    private TrafficLight trafficLight = TrafficLight.getTrafficLight(); // TrafficLight instance
    private Canvas canvas; // Canvas to draw the traffic light

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Traffic Light Application");

        // Create the canvas to draw the traffic light
        canvas = new Canvas(150, 400);

        // Draw initial traffic light state
        draw(canvas.getGraphicsContext2D());

        // Button to switch the traffic light
        Button btnSwitch = new Button();
        btnSwitch.setText("Umschalten");
        btnSwitch.setOnAction(e -> {
            trafficLight.switchColor(); // Switch traffic light color
            draw(canvas.getGraphicsContext2D()); // Redraw traffic light
        });

        // Layout
        VBox layout = new VBox(10, canvas, btnSwitch);
        layout.setStyle("-fx-alignment: center; -fx-padding: 10;");

        BorderPane root = new BorderPane();
        root.setCenter(layout);

        // Set the scene
        Scene scene = new Scene(root, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to draw the traffic light
    private void draw(GraphicsContext gc) {
        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw the traffic light body (rectangle)
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(50, 50, 50, 300);

        // Draw circles (red, yellow, green)
        gc.setFill(trafficLight.getColor() == TrafficLightColor.RED || trafficLight.getColor() == TrafficLightColor.YELLOW_RED ? Color.RED : Color.LIGHTGRAY );
        gc.fillOval(60, 60, 30, 30); // Top circle - Red

        gc.setFill(trafficLight.getColor() == TrafficLightColor.YELLOW || trafficLight.getColor() == TrafficLightColor.YELLOW_RED ? Color.YELLOW : Color.LIGHTGRAY);
        gc.fillOval(60, 160, 30, 30); // Middle circle - Yellow

        gc.setFill(trafficLight.getColor() == TrafficLightColor.GREEN ? Color.GREEN : Color.LIGHTGRAY);
        gc.fillOval(60, 260, 30, 30); // Bottom circle - Green
    }

    public static void main(String[] args) {
        launch(args);
    }
}