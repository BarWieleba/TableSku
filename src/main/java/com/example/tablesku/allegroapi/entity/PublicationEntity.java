package com.example.tablesku.allegroapi.entity;

import java.util.Date;

public class PublicationEntity {
    private Date endingAt;

    public PublicationEntity() {
    }

    public PublicationEntity(Date endingAt) {
        this.endingAt = endingAt;
    }

    public Date getEndingAt() {
        return endingAt;
    }

    public void setEndingAt(Date endingAt) {
        this.endingAt = endingAt;
    }
}
