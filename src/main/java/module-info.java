module com.example.updatedpainterapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.updatedpainterapp to javafx.fxml;
    exports com.example.updatedpainterapp;
}