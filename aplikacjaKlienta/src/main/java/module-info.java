module com.example.aplikacjaklienta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires jakarta.xml.bind;
    requires jakarta.xml.soap;


    opens com.example.aplikacjaklienta to javafx.fxml, jakarta.xml.soap;
    opens  com.bartek.soap to jakarta.xml.bind, javafx.fxml, javafx.base;
    exports com.example.aplikacjaklienta;
}