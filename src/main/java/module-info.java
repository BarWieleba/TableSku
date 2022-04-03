module com.example.tablesku {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires jakarta.activation;


    opens com.example.tablesku to javafx.fxml;
    opens com.example.tablesku.entity to javafx.base, jakarta.xml.bind;
    exports com.example.tablesku;
}