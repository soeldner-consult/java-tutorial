package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Init method: Perform initialization tasks.");
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Start method: Setting up the JavaFX stage.");

            primaryStage.setTitle("JavaFX in STS IDE");
            Label label = new Label("Hello, JavaFX!");
            StackPane root = new StackPane(label);
            Scene scene = new Scene(root, 400, 200);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop method: Cleaning up resources before exit.");
    }

    public static void main(String[] args) {
        System.out.println("Main method: Launching JavaFX application.");
        launch(args);
    }
}