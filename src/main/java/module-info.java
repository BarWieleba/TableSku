module com.example.tablesku {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires com.fasterxml.jackson.databind;
    requires jakarta.xml.ws;
    requires jakarta.xml.soap;


    opens com.example.tablesku to javafx.fxml;
    opens com.example.tablesku.entity to javafx.base, jakarta.xml.bind, com.fasterxml.jackson.databind;
    opens com.example.tablesku.soap to jakarta.xml.ws, jakarta.xml.soap;
    exports com.example.tablesku;
}