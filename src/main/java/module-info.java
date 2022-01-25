module com.example.gradletry {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gradletry to javafx.fxml;
    exports com.example.gradletry;
}