package com.example.tablesku;

import com.example.tablesku.entity.Computer;
import com.example.tablesku.file.FileContentReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class HelloController {

    @FXML
    private Button downloadFromTxt;

    @FXML
    private Button downloadFromXml;

    @FXML
    private Button uploadToTxt;

    @FXML
    private Button uploadToXml;

    @FXML
    private TableView computerTable;

    private enum FileType {
        TXT, XML;
    }

    private String columnNames[] = {
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

    private LinkedList<Field> fields = new LinkedList<>(Arrays.stream(Computer.class.getDeclaredFields()).collect(Collectors.toList()));

    @FXML
    void initialize() {
        downloadFromTxt.setText("Wczytaj dane z TXT");
        downloadFromTxt.setUserData(FileType.TXT);
        downloadFromXml.setText("Wczytaj dane z XML");
        downloadFromXml.setUserData(FileType.XML);
        uploadToTxt.setText("Zapisz dane do TXT");
        uploadToTxt.setUserData(FileType.TXT);
        uploadToXml.setText("Zapisz dane do XML");
        uploadToXml.setUserData(FileType.XML);

        downloadFromTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        downloadFromXml.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        uploadToTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        uploadToXml.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);

        List<TableColumn<Computer, String>> columnList = new ArrayList<>();
        int i = 0;
        for(String columnHeader : columnNames) {
            TableColumn<Computer, String> tableColumn = new TableColumn<>(columnHeader);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields.get(i).getName()));
            tableColumn.setCellFactory(TextFieldTableCell.<Computer>forTableColumn());
            columnList.add(tableColumn);
            i++;
        }
        computerTable.getColumns().addAll(columnList);

    }

    private final EventHandler<MouseEvent> chooseFile = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button handledButton = (Button) mouseEvent.getSource();
            FileType fileType = (FileType) handledButton.getUserData();
            FileChooser fileChooser = new FileChooser();
            switch (fileType) {
                case TXT: {
                    fileChooser.setTitle("Otwórz plik TXT");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    break;
                }
                case XML: {
                    fileChooser.setTitle("Otwórz plik XML");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    break;
                }
                default: {
                    fileChooser.setTitle("Otwórz plik XML");
                }
            }
            File openedFile = fileChooser.showOpenDialog(new Stage());
            FileContentReader fileContentReader = new FileContentReader(openedFile.getAbsolutePath(), ";");
            try {

                List<Computer> computers = fileContentReader.readFromFile();
                ObservableList<Computer> computerObservableList = FXCollections.observableList(computers);


                computerTable.setItems(computerObservableList);
                computerTable.setEditable(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private final EventHandler<MouseEvent> saveFile = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button handledButton = (Button) mouseEvent.getSource();
            FileType fileType = (FileType) handledButton.getUserData();
            switch (fileType) {
                case TXT: {
                    System.out.println("Zapisz plik TXT");
                    break;
                }
                case XML: {
                    System.out.println("Zapisz plik XML");
                    break;
                }
                default: {
                    System.out.println("Zapisz plik");
                    break;
                }
            }
        }
    };
}