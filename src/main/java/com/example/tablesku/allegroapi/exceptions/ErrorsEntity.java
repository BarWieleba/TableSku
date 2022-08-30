package com.example.tablesku.allegroapi.exceptions;

import java.util.List;

public class ErrorsEntity {
    private List<ErrorEntity> errors;

    public ErrorsEntity() {
    }

    public ErrorsEntity(List<ErrorEntity> errors) {
        this.errors = errors;
    }

    public List<ErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorEntity> errors) {
        this.errors = errors;
    }
}
