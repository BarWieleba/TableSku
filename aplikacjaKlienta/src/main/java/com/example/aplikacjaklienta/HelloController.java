package com.example.aplikacjaklienta;

import com.bartek.soap.ComputerResponse;
import com.example.aplikacjaklienta.file.ClassReader;
import com.example.aplikacjaklienta.soap.SoapHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    private TableView<ComputerResponse.ComputerList> computerTable;

    private SoapHandler soapHandler;

    private final String columnNames[] = {
            "Id",
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

    private LinkedList<Field> fields = new LinkedList<>(Arrays.stream(ComputerResponse.ComputerList.class.getDeclaredFields()).collect(Collectors.toList()));
    @FXML
    void initialize() {
        soapHandler = new SoapHandler();
        chooseManufacturer.setText("Wybór producenta");
        chooseManufacturer.setLabelFor(comboManufacturer);
        countManufacturer.setText("liczba laptopów producenta");
        displayManufacturerCount.setText("liczba laptopów");

        chooseMatrixLabel.setText("Wybór matrycy");
        chooseMatrixLabel.setLabelFor(listComputersWithMatrix);
        listComputersWithMatrix.setText("lista laptopów z określoną matrycą");

        ClassReader classReader = new ClassReader(ComputerResponse.ComputerList.class);
        classReader.readClassMethods();

        List<TableColumn<ComputerResponse.ComputerList, String>> columnList = new ArrayList<>();
        int i = 0;
        for(String columnHeader : columnNames) {
            TableColumn<ComputerResponse.ComputerList, String> tableColumn = new TableColumn<>(columnHeader);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields.get(i).getName()));
            tableColumn.setCellFactory(TextFieldTableCell.<ComputerResponse.ComputerList>forTableColumn());
            columnList.add(tableColumn);
            i++;
        }
        columnList.get(0).setVisible(false);
        computerTable.getColumns().addAll(columnList);
        ComputerResponse result = soapHandler.connectAndSend(true, "","");
        computerTable.getItems().addAll(result.getComputerList());

        List<Object> manufacturers = result.getComputerList().stream().map(x -> x.getManufacturer()).distinct().collect(Collectors.toList());

        comboManufacturer.getItems().addAll(manufacturers);
        comboManufacturer.getSelectionModel().selectFirst();




        listComputersWithMatrix.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {

        }));


    }


}