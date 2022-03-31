module com.example.tablesku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tablesku to javafx.fxml;
    exports com.example.tablesku;
}