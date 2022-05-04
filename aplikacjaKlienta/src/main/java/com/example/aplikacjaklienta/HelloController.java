package com.example.aplikacjaklienta;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class HelloController {
    @FXML
    private Label chooseManufacturer;

    @FXML
    private ComboBox comboManufacturer;

    @FXML
    private Button countManufacturer;

    @FXML
    private Label displayManufacturerCount;

    @FXML
    private Label chooseMatrixLabel;

    @FXML
    private ComboBox comboMatrixProportions;

    @FXML
    private Button listComputersWithMatrix;

    @FXML
    private TableView computerTable;

    private final String columnNames[] = {
            "Producent",
            "Przekątna ekranu",
            "Rozdzielczość",
            "Typ matrycy",
            "Opakowanie",
            "Procesor",
            "Rdzenie",
            "Taktowanie",
            "RAM",
            "Pojemność dysku",
            "Typ dysku",
            "Karta graficzna",
            "VRAM",
            "System operacyjny",
            "Napęd"
    };

    @FXML
    void initialize() {
        chooseManufacturer.setText("Wybór producenta");
        chooseManufacturer.setLabelFor(comboManufacturer);
        countManufacturer.setText("liczba laptopów producenta");
        displayManufacturerCount.setText("liczba laptopów");

        chooseMatrixLabel.setText("Wybór matrycy");
        chooseMatrixLabel.setLabelFor(listComputersWithMatrix);
        listComputersWithMatrix.setText("lista laptopów z określoną matrycą");


    }


}