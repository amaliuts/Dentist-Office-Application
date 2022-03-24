module com.example.homeassignment_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.homeassignment_fx to javafx.fxml;
    exports com.example.homeassignment_fx;
}