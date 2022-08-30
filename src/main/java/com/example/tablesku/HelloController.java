package com.example.tablesku;

import com.example.tablesku.allegroapi.entity.AuthTokenEntity;
import com.example.tablesku.allegroapi.entity.UserAuthEntity;
import com.example.tablesku.allegroapi.network.ConnectionAllegro;
import com.example.tablesku.entity.Computer;
import com.example.tablesku.entity.ComputerList;
import com.example.tablesku.file.ClassReader;
import com.example.tablesku.file.FileContentReader;
import com.example.tablesku.network.ConnectionHelper;
import com.example.tablesku.validators.ComputerValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
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
    private Button importFromDb;

    @FXML
    private Button exportToDb;

    @FXML
    private Button loginToAllegro;

    @FXML
    private Button downloadFromAllegro;

    @FXML
    private TableView computerTable;

    @FXML
    private Button addNewRow;

    private enum FileType {
        TXT, XML, DB;
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

    private AuthTokenEntity token;

    @FXML
    void initialize() {
        downloadFromTxt.setText("Wczytaj dane z TXT");
        downloadFromTxt.setUserData(FileType.TXT);
        downloadFromXml.setText("Wczytaj dane z XML");
        downloadFromXml.setUserData(FileType.XML);
        importFromDb.setText("Importuj dane z bazy danych");
        importFromDb.setUserData(FileType.DB);
        uploadToTxt.setText("Zapisz dane do TXT");
        uploadToTxt.setUserData(FileType.TXT);
        uploadToXml.setText("Zapisz dane do XML");
        uploadToXml.setUserData(FileType.XML);
        exportToDb.setText("Eksportuj dane do bazy danych");
        exportToDb.setUserData(FileType.DB);

        loginToAllegro.setText("Połącz z Allegro");
        downloadFromAllegro.setText("Pobierz laptopy z Allegro");
        downloadFromAllegro.setDisable(true);
        downloadFromAllegro.setTooltip(new Tooltip("Przycisk jest wyłączony\n do czasu połączenia się z Allegro"));


        downloadFromTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        downloadFromXml.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        uploadToTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        uploadToXml.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        importFromDb.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        exportToDb.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        loginToAllegro.addEventHandler(MouseEvent.MOUSE_CLICKED, loginToAllegroAction);

        ClassReader classReader = new ClassReader(Computer.class);
        classReader.readClassMethods();

        List<TableColumn<Computer, String>> columnList = new ArrayList<>();
        int i = 0;
        for(String columnHeader : columnNames) {
            TableColumn<Computer, String> tableColumn = new TableColumn<>(columnHeader);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields.get(i).getName()));
            tableColumn.setCellFactory(TextFieldTableCell.<Computer>forTableColumn());



            tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Computer, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Computer, String> t) {
                    ComputerValidator validator = new ComputerValidator(t);
                    if(validator.validate()){
                        Computer computer = t.getTableView().getItems().get(t.getTablePosition().getRow());
                        Method method = classReader.getSetMethods().get(t.getTablePosition().getColumn());
                        try {
                            method.invoke(computer, t.getNewValue());   //computer.setManufacturer(t.getNewValue());
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } else {
                        showAlert();
                        computerTable.refresh();
                    }
                }
            });
            columnList.add(tableColumn);
            i++;
        }
        computerTable.getColumns().addAll(columnList);

        addNewRow.setText("Dodaj wiersz");
        addNewRow.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                computerTable.getItems().add(new Computer());
            }
        });

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

                    File openedFile = fileChooser.showOpenDialog(new Stage());
                    if(openedFile!=null) {
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
                    break;
                }
                case XML: {
                    fileChooser.setTitle("Otwórz plik XML");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    File openedFile = fileChooser.showOpenDialog(new Stage());
                    if(openedFile != null) {
                        try {
                            JAXBContext ctx = JAXBContext.newInstance(ComputerList.class);
                            Unmarshaller unmarshaller = ctx.createUnmarshaller();

                            ComputerList computerList = (ComputerList) unmarshaller.unmarshal(openedFile);
                            ObservableList<Computer> computerObservableList = FXCollections.observableList(computerList.getComputer());
                            computerTable.setItems(computerObservableList);
                            computerTable.setEditable(true);


                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case DB: {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    try {
                        List<Computer> computers = connectionHelper.readAllComputer();
                        ObservableList<Computer> computerObservableList = FXCollections.observableList(computers);
                        System.out.println("List: " + computerObservableList);
                        computerTable.setItems(computerObservableList);
                        computerTable.setEditable(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default: {
                    fileChooser.setTitle("Otwórz plik XML");
                }
            }
        }
    };

    private final EventHandler<MouseEvent> saveFile = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button handledButton = (Button) mouseEvent.getSource();
            FileType fileType = (FileType) handledButton.getUserData();
            FileChooser fileChooser = new FileChooser();
            ObservableList<Computer> computersToSave = computerTable.getItems();
            switch (fileType) {
                case TXT: {
                    fileChooser.setTitle("Zapisz plik TXT");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    File saveFile = fileChooser.showSaveDialog(new Stage());
                    if(saveFile != null) {
                        try {
                            safeToTxtFile(saveFile, computersToSave);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case XML: {
                    fileChooser.setTitle("Zapisz plik XML");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    File saveFile = fileChooser.showSaveDialog(new Stage());
                    if(saveFile != null) {
                        try {
                            JAXBContext ctx = JAXBContext.newInstance(ComputerList.class);
                            Marshaller marshaller = ctx.createMarshaller();
                            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                            ComputerList computerList = new ComputerList(new Date(), computersToSave);
                            marshaller.marshal(computerList, saveFile);
                        } catch (JAXBException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case DB: {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    try {
                        List<Computer> computerList = connectionHelper.saveComputers(computersToSave);
                        System.out.println("List: " + computerList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                default: {
                    System.out.println("Zapisz plik");
                }
            }

        }
    };

    private final EventHandler<MouseEvent> loginToAllegroAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            token = loginToAllegroAPI();
            downloadFromAllegro.setDisable(false);
        }
    };

    private final EventHandler<MouseEvent> downloadLaptopsFromAllegro = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

        }
    };

    private void safeToTxtFile(File saveFile, ObservableList<Computer> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
        for(Computer computer : list) {
            writer.write(computer.toFile());
        }
        writer.close();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Złe dane!");
        alert.setHeaderText("Wprowadzono złe dane");
        alert.setContentText("Careful with the next step!");

        alert.showAndWait();
    }

    private AuthTokenEntity loginToAllegroAPI() {
        ConnectionAllegro connectionAllegro = new ConnectionAllegro();

        UserAuthEntity userAuthEntity = null;
        try {
           userAuthEntity = connectionAllegro.getUserAuthInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(userAuthEntity == null) {
            return null;
        }
        try{
            Runtime rt = Runtime.getRuntime();
            String url = userAuthEntity.getVerificationUriComplete();
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final AuthTokenEntity[] authTokenEntity = {null};

        UserAuthEntity finalUserAuthEntity = userAuthEntity;
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i<12) {
                    try {
                        authTokenEntity[0] = connectionAllegro.getTokens(finalUserAuthEntity.getDeviceCode());
                        if(Arrays.stream(authTokenEntity).findAny().isPresent()){
                            break;
                        }
                        Thread.sleep(5000);
                    } catch (Exception e) {
                    }
                    i++;
                }
            }
        });
        newThread.start();
        return authTokenEntity[0];
    }
}