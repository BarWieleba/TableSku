package com.example.tablesku.validators;

import com.example.tablesku.entity.Computer;
import com.example.tablesku.file.ClassReader;
import javafx.scene.control.TableColumn;

import java.util.regex.Pattern;

public class ComputerValidator {
    private TableColumn.CellEditEvent<Computer, String> t;
    private ClassReader classReader;
    public ComputerValidator(TableColumn.CellEditEvent<Computer, String> t) {
        this.t = t;
        classReader = new ClassReader(Computer.class);
    }

    public boolean validate() {

        switch (t.getTablePosition().getColumn()) {
            case 0 : {
                return validateManufaturer(t.getNewValue());
            }
            case 1 : {
                return validateScreenSize(t.getNewValue());
            }
            default: {
                return true;
            }
        }
    }

    private boolean validateManufaturer(String value) {
        Pattern pattern = Pattern.compile("^([A-Z])[A-Za-z]*\\b");
        return pattern.matcher(value).find();
    }

    private boolean validateScreenSize(String value) {
        Pattern pattern = Pattern.compile("[0-9]*\\\"\\B");
        return pattern.matcher(value).find();
    }

    private boolean validateResolution(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }

    private boolean validateMatrixTexture(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validatePackaging(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateProcessor(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateCoreCount(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateRamSize(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateDriveSize(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateDriveType(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateGraphics(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateVideoMemory(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateOs(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateRecorder(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }



}
