package com.example.tablesku.allegroapi.entity;

import java.util.List;

public class ItemsEntity {
    List<ItemEntity> promoted;
    List<ItemEntity> regular;

    public ItemsEntity() {
    }

    public ItemsEntity(List<ItemEntity> promoted, List<ItemEntity> regular) {
        this.promoted = promoted;
        this.regular = regular;
    }

    public List<ItemEntity> getPromoted() {
        return promoted;
    }

    public void setPromoted(List<ItemEntity> promoted) {
        this.promoted = promoted;
    }

    public List<ItemEntity> getRegular() {
        return regular;
    }

    public void setRegular(List<ItemEntity> regular) {
        this.regular = regular;
    }
}
