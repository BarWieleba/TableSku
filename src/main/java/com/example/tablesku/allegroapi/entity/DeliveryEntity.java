package com.example.tablesku.allegroapi.entity;

public class DeliveryEntity {
    private Boolean availableForFree;
    private PriceEntity lowestPrice;

    public DeliveryEntity() {
    }

    public DeliveryEntity(Boolean availableForFree, PriceEntity lowestPrice) {
        this.availableForFree = availableForFree;
        this.lowestPrice = lowestPrice;
    }

    public Boolean getAvailableForFree() {
        return availableForFree;
    }

    public void setAvailableForFree(Boolean availableForFree) {
        this.availableForFree = availableForFree;
    }

    public PriceEntity getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(PriceEntity lowestPrice) {
        this.lowestPrice = lowestPrice;
    }
}
