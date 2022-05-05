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
import javafx.stage.Window;

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
    private ComboBox comboMatrixType;

    @FXML
    private Button listComputersWithMatrix;

    @FXML
    private Label matrixProportionsLabel;

    @FXML
    private TextField matrixProportions;

    @FXML
    private Button getCompByMatrix;
    @FXML
    private Button reset;

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

        matrixProportionsLabel.setText("Proporcje ekranu");
        getCompByMatrix.setText("Znajdź komputery po proporcjach ekranu");

        reset.setText("Reset tabeli");

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
        ComputerResponse result = soapHandler.connectAndSend(true, "","", "");
        computerTable.getItems().addAll(result.getComputerList());

        List<Object> manufacturers = result.getComputerList().stream().map(x -> x.getManufacturer()).distinct().collect(Collectors.toList());
        List<Object> matrixType = result.getComputerList().stream().filter(x -> !x.getMatrixTexture().equals("")).map(x -> x.getMatrixTexture()).distinct().collect(Collectors.toList());

        comboManufacturer.getItems().addAll(manufacturers);
        comboManufacturer.getSelectionModel().selectFirst();

        countManufacturer.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            displayManufacturerCount.setText("liczba laptopów: " + result.getComputerList().stream().filter(x -> x.getManufacturer().equals(comboManufacturer.getSelectionModel().getSelectedItem())).count());
        }));

        comboMatrixType.getItems().addAll(matrixType);
        comboMatrixType.getSelectionModel().selectFirst();

        listComputersWithMatrix.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            computerTable.getItems().clear();
            ComputerResponse computerResult = soapHandler.connectAndSend(false, "", "", (String) comboMatrixType.getSelectionModel().getSelectedItem());
            computerTable.getItems().addAll(computerResult.getComputerList());
            computerTable.refresh();
        }));

        getCompByMatrix.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {

            if(!matrixProportions.getText().isEmpty()) {
                try {
                    String[] matrixData = matrixProportions.getText().split("x");
                    if(matrixData.length != 2){
                        throw new Exception("Bad data");
                    }
                    Integer.parseInt(matrixData[0]);
                    Integer.parseInt(matrixData[1]);
                } catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Niepoprawne dane");
                    alert.setHeaderText(null);
                    alert.setContentText("Proszę wprowadzić proporcje ekranu w foramcie \"AxB\" np. \"16x9\"");

                    alert.showAndWait();
                    return;
                }

                ComputerResponse computerResponse = soapHandler.connectAndSend(false, "", matrixProportions.getText(), "");
                computerTable.getItems().clear();
                computerTable.getItems().addAll(computerResponse.getComputerList());
                computerTable.refresh();
            }
        }));


        reset.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent -> {
            computerTable.getItems().clear();
            computerTable.getItems().addAll(result.getComputerList());
            computerTable.refresh();
        }));


    }


}