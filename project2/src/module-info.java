module project2 {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics,javafx.base, javafx.fxml;
}
