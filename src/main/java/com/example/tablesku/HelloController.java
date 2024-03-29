package com.example.tablesku;

import com.example.tablesku.allegroapi.entity.*;
import com.example.tablesku.allegroapi.exceptions.ErrorAndDescEntity;
import com.example.tablesku.allegroapi.network.ConnectionAllegro;
import com.example.tablesku.allegroapi.network.ConnectionAllegroDownload;
import com.example.tablesku.dictionary.ConnectionDictionary;
import com.example.tablesku.entity.Computer;
import com.example.tablesku.entity.ComputerList;
import com.example.tablesku.file.ClassReader;
import com.example.tablesku.file.FileContentReader;
import com.example.tablesku.network.ConnectionHelper;
import com.example.tablesku.validators.ComputerValidator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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
    private Button downloadFromJson;

    @FXML
    private Button uploadToJson;

    @FXML
    private TableView computerTable;

    @FXML
    private Button addNewRow;

    private enum FileType {
        TXT, XML, DB, JSON;
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

    private ItemsWrapperEntity itemsEntities;

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

        downloadFromJson.setText("Wczytaj dane z JSON");
        downloadFromJson.setUserData(FileType.JSON);
        uploadToJson.setText("Zapisz dane do JSON");
        uploadToJson.setUserData(FileType.JSON);


        downloadFromTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        downloadFromXml.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        uploadToTxt.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        uploadToXml.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        importFromDb.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        exportToDb.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);
        loginToAllegro.addEventHandler(MouseEvent.MOUSE_CLICKED, loginToAllegroAction);
        downloadFromAllegro.addEventHandler(MouseEvent.MOUSE_CLICKED, downloadLaptopsFromAllegro);
        downloadFromJson.addEventHandler(MouseEvent.MOUSE_CLICKED, chooseFile);
        uploadToJson.addEventHandler(MouseEvent.MOUSE_CLICKED, saveFile);

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
                case JSON: {
                    fileChooser.setTitle("Otwórz plik JSON");
                    FileChooser.ExtensionFilter extensionFilterJson = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
                    fileChooser.getExtensionFilters().add(extensionFilterJson);

                    File openedFile = fileChooser.showOpenDialog(new Stage());
                    if(openedFile!=null) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            List<Computer> computers = new ObjectMapper().readValue(openedFile.getAbsoluteFile(), new TypeReference<List<Computer>>(){});
                            ObservableList<Computer> computerObservableList = FXCollections.observableList(computers);
                            computerTable.setItems(computerObservableList);
                            computerTable.setEditable(true);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
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
                            saveToTxtFile(saveFile, computersToSave);
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

                case JSON: {
                    fileChooser.setTitle("Zapisz plik JSON");
                    FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
                    fileChooser.getExtensionFilters().add(extensionFilter);
                    File saveFile = fileChooser.showSaveDialog(new Stage());
                    if(saveFile != null) {
                        try {
                            saveToJSONFile(saveFile, computersToSave);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
            try {
                token = loginToAllegroAPI();
            } catch (Exception e){
                e.printStackTrace();
            }
            downloadFromAllegro.setDisable(false);

        }
    };

    private final EventHandler<MouseEvent> downloadLaptopsFromAllegro = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            itemsEntities = downloadLaptopsFromAllegroAPI();
            List<Computer> computers = new ArrayList<>();
            for(ItemEntity item : itemsEntities.getItems().getRegular()){
                String[] name = item.getName().split(" ");
                ClassReader classReader = new ClassReader(Computer.class);
                classReader.readClassMethods();
                List<Method> methods = classReader.getSetMethods();
                Computer computer = new Computer();
                for(int i = 0; i< name.length; i++) {
                    try {
                        methods.get(i).invoke(computer, name[i]);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                computers.add(computer);
            }
            computerTable.getItems().addAll(computers);
        }
    };

    private void saveToTxtFile(File saveFile, ObservableList<Computer> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
        for(Computer computer : list) {
            writer.write(computer.toFile());
        }
        writer.close();
    }

    private void saveToJSONFile(File saveFile, ObservableList<Computer> list) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(saveFile, list);
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Złe dane!");
        alert.setHeaderText("Wprowadzono złe dane");
        alert.setContentText("Careful with the next step!");

        alert.showAndWait();
    }

    private AuthTokenEntity loginToAllegroAPI() throws Exception{
        ConnectionAllegro connectionAllegro = new ConnectionAllegro();

        Map<String, Object> resultOfAuthorization = connectionAllegro.getUserAuthInfo();

        int responseCodeFromAuth = (int)resultOfAuthorization.get(ConnectionDictionary.RESPONSE_CODE);

        UserAuthEntity userAuthEntity = null;
        ErrorAndDescEntity errorAndDescEntity = null;


        if(responseCodeFromAuth == 200){
            userAuthEntity = new ObjectMapper().readValue(String.valueOf(resultOfAuthorization.get(ConnectionDictionary.JSON_RESULT)), UserAuthEntity.class);
            openBrowser(userAuthEntity);
        } else {
            errorAndDescEntity = new ObjectMapper().readValue(String.valueOf(resultOfAuthorization.get(ConnectionDictionary.JSON_RESULT)), ErrorAndDescEntity.class);
        }



        Map<String, Object> resultOfTokens = null;
        AuthTokenEntity authTokenEntity = null;
        ErrorAndDescEntity errorAndDescEntityToken = null;
        int i = 0;
        while(i<12) {
            try{
                resultOfTokens = connectionAllegro.getTokens(userAuthEntity.getDeviceCode());
            } catch (Exception e) {
                System.out.println("Problem: " + e.getMessage());

            }


            if(resultOfTokens !=null && (int)resultOfTokens.get(ConnectionDictionary.RESPONSE_CODE) == 200){
                authTokenEntity = new ObjectMapper().readValue(String.valueOf(resultOfTokens.get(ConnectionDictionary.JSON_RESULT)), AuthTokenEntity.class);
                System.out.println("Successfully connected!");
                break;
            } else if(resultOfTokens !=null && (int)resultOfTokens.get(ConnectionDictionary.RESPONSE_CODE) == 400) {
                errorAndDescEntityToken = new ObjectMapper().readValue(String.valueOf(resultOfTokens.get(ConnectionDictionary.JSON_RESULT)), ErrorAndDescEntity.class);
                switch (errorAndDescEntityToken.getError()) {
                    case "authorization_pending": {
                        System.out.println("Auth pending");
                        break;
                    }
                    case "slow_down" : {
                        System.out.println("Request come too often");
                        break;
                    }
                    case "access_denied" : {
                        System.out.println("Access denied");
                        return null;
                    }
                    case "Invalid device code" : {
                        System.out.println("Invalid device code");
                        return null;
                    }
                    default: {
                        return null;
                    }
                }

            }
            Thread.sleep(5000);
            i++;
        }


        return authTokenEntity;
    }

    private ItemsWrapperEntity downloadLaptopsFromAllegroAPI() {
        ConnectionAllegroDownload connectionAllegroDownload = new ConnectionAllegroDownload();
        try {
            Map<String, Object> laptopsFromAllegroAPI = connectionAllegroDownload.getLaptops(token);
            if((int)laptopsFromAllegroAPI.get(ConnectionDictionary.RESPONSE_CODE) == 200) {
                ItemsWrapperEntity itemsEntities = new ObjectMapper().readValue(String.valueOf(laptopsFromAllegroAPI.get(ConnectionDictionary.JSON_RESULT)), ItemsWrapperEntity.class);
                return itemsEntities;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void openBrowser(UserAuthEntity userAuthEntity) throws IOException{
        Runtime rt = Runtime.getRuntime();
        String url = userAuthEntity.getVerificationUriComplete();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
    }
}