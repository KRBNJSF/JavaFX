module com.example.canvasalg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.canvasalg to javafx.fxml;
    exports com.example.canvasalg;
}