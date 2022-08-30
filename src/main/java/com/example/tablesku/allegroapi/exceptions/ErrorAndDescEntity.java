package com.example.tablesku.allegroapi.exceptions;

public class ErrorAndDescEntity {
    private String error;
    private String errorDescription;

    public ErrorAndDescEntity() {
    }

    public ErrorAndDescEntity(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
