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
            case 2 : {
                return validateResolution(t.getNewValue());
            }
            case 3 : {
                return validateMatrixTexture(t.getNewValue());
            }
            case 4 : {
                return validatePackaging(t.getNewValue());
            }
            case 5 : {
                return validateProcessor(t.getNewValue());
            }
            case 6 : {
                return validateCoreCount(t.getNewValue());
            }
            case 7 : {
                return validateProcessorSpeed(t.getNewValue());
            }
            case 8 : {
                return validateRamSize(t.getNewValue());
            }
            case 9 : {
                return validateDriveSize(t.getNewValue());
            }
            case 10 : {
                return validateDriveType(t.getNewValue());
            }
            case 11 : {
                return validateGraphics(t.getNewValue());
            }
            case 12 : {
                return validateVideoMemory(t.getNewValue());
            }
            case 13 : {
                return validateOs(t.getNewValue());
            }
            case 14 : {
                return validateRecorder(t.getNewValue());
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
        Pattern pattern = Pattern.compile("^[0-9]+x[0-9]+$");
        return pattern.matcher(value).find();
    }

    private boolean validateMatrixTexture(String value) {
        Pattern pattern = Pattern.compile("^[a-z]+$");
        return pattern.matcher(value).find();
    }
    private boolean validatePackaging(String value) {
        Pattern pattern = Pattern.compile("^[a-z]+$");
        return pattern.matcher(value).find();
    }
    private boolean validateProcessor(String value) {
        Pattern pattern = Pattern.compile("^[a-z]\\w*");
        return pattern.matcher(value).find();
    }

    private boolean validateProcessorSpeed(String value) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        return pattern.matcher(value).find();
    }

    private boolean validateCoreCount(String value) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        return pattern.matcher(value).find();
    }
    private boolean validateRamSize(String value) {
        Pattern pattern = Pattern.compile("^[0-9]+\\sGB$");
        return pattern.matcher(value).find();
    }
    private boolean validateDriveSize(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateDriveType(String value) {
        Pattern pattern = Pattern.compile("\\b(HDD|SSD)\\b");
        return pattern.matcher(value).find();
    }
    private boolean validateGraphics(String value) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*");
        return pattern.matcher(value).find();
    }
    private boolean validateVideoMemory(String value) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]*$");
        return pattern.matcher(value).find();
    }
    private boolean validateOs(String value) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]*$");
        return pattern.matcher(value).find();
    }
    private boolean validateRecorder(String value) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]*$");
        return pattern.matcher(value).find();
    }



}
