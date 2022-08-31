module com.example.tablesku {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires com.fasterxml.jackson.databind;
    requires jakarta.xml.ws;
    requires jakarta.xml.soap;
    requires ini4j;
    requires java.xml.bind;


    opens com.example.tablesku to javafx.fxml, jakarta.xml.bind, org.glassfish.jaxb.runtime, org.glassfish.external.management.api, org.glassfish.ha.api;
    opens com.example.tablesku.entity to javafx.base, jakarta.xml.bind, com.fasterxml.jackson.databind;
    opens com.example.tablesku.allegroapi.entity to com.fasterxml.jackson.databind;
    exports com.example.tablesku;
}