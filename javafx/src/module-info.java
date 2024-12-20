module javafx {
    requires javafx.controls;
    requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;

    exports trafficlights; // Export your package
    exports login;
    exports listview;
    exports progressbar;
	
	opens application to javafx.graphics, javafx.fxml;
	opens listview to javafx.fxml;
}
