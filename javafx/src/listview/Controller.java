package listview;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private ListView<String> listView; // ListView reference from FXML

    @FXML
    private Label label; // Label reference from FXML

    public void initialize() {
        // Populate the ListView with sample items
        listView.setItems(FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
        ));

        // Add a listener to update the label when a ListView item is selected
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                label.setText("Selected: " + newValue);
            }
        });
    }
}