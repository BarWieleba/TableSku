package com.example.tablesku.allegroapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsWrapperEntity {
    private ItemsEntity items;

    public ItemsWrapperEntity() {
    }

    public ItemsWrapperEntity(ItemsEntity items) {
        this.items = items;
    }

    public ItemsEntity getItems() {
        return items;
    }

    public void setItems(ItemsEntity items) {
        this.items = items;
    }
}
