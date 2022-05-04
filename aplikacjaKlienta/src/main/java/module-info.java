module com.example.aplikacjaklienta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aplikacjaklienta to javafx.fxml;
    exports com.example.aplikacjaklienta;
}