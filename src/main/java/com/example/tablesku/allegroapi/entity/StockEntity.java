package com.example.tablesku.allegroapi.entity;

public class StockEntity {
    private String unit;
    private Integer available;

    public StockEntity() {
    }

    public StockEntity(String unit, Integer available) {
        this.unit = unit;
        this.available = available;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
