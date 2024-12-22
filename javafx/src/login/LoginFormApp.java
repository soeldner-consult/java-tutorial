package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginFormApp extends Application {
	
	private TextField userTextField;
    private PasswordField pwField;
    private Label actionTarget;
	
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Form");

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Labels and input fields
        Label userLabel = new Label("Username:");
        grid.add(userLabel, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);

        pwField = new PasswordField();
        grid.add(pwField, 1, 2);

        // Button to submit the login
        Button loginButton = new Button("Login");
        HBox hboxButton = new HBox(10);
        hboxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButton.getChildren().add(loginButton);
        grid.add(hboxButton, 1, 3);

        // Action for the login button
        actionTarget = new Label();
        grid.add(actionTarget, 1, 4);

//        loginButton.setOnAction(e -> {
//            String username = userTextField.getText();
//            String password = pwField.getText();
//
//            // Simple login logic for demonstration
//            if (username.equals("admin") && password.equals("1234")) {
//                actionTarget.setText("Login successful!");
//            } else {
//                actionTarget.setText("Login failed! Try again.");
//            }
//        });
        
//        loginButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//        	public void handle(ActionEvent actionEvent) {
//                String username = userTextField.getText();
//                String password = pwField.getText();
//
//                // Simple login logic for demonstration
//                if (username.equals("admin") && password.equals("1234")) {
//                    actionTarget.setText("Login successful!");
//                } else {
//                    actionTarget.setText("Login failed! Try again.");
//                }
//            }
//        });

	// using inner class
        loginButton.setOnAction(new LoginButtonHandler());
	    
        // Scene and stage setup
        Scene scene = new Scene(grid, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    // Inner Class for Event Handling
    private class LoginButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            String username = userTextField.getText();
            String password = pwField.getText();

            // Simple login logic for demonstration
            if (username.equals("admin") && password.equals("1234")) {
                actionTarget.setText("Login successful!");
            } else {
                actionTarget.setText("Login failed! Try again.");
            }
        }
    }

    
    
}
