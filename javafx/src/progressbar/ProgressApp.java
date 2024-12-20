package progressbar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a ProgressBar
        ProgressBar progressBar = new ProgressBar(0); // Initial progress = 0

        // Create a Slider
        Slider slider = new Slider(0, 1, 0); // Min = 0, Max = 1, Initial value = 0
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        // Bind ProgressBar's progress to Slider's value
        progressBar.progressProperty().bind(slider.valueProperty());

        // Arrange components in an HBox
        HBox hBox = new HBox(20); // Spacing of 20 pixels
        hBox.getChildren().addAll(slider, progressBar);

        // Set up the Scene
        Scene scene = new Scene(hBox, 400, 100);
        primaryStage.setTitle("Slider and ProgressBar Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}