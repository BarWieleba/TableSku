module com.example.tablesku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tablesku to javafx.fxml;
    opens com.example.tablesku.entity to javafx.base;
    exports com.example.tablesku;
}