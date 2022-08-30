package com.example.tablesku.allegroapi.entity;

public class PromotionEntity {
    private Boolean emphasized;
    private Boolean bold;
    private Boolean highlight;

    public PromotionEntity() {
    }

    public PromotionEntity(Boolean emphasized, Boolean bold, Boolean highlight) {
        this.emphasized = emphasized;
        this.bold = bold;
        this.highlight = highlight;
    }

    public Boolean getEmphasized() {
        return emphasized;
    }

    public void setEmphasized(Boolean emphasized) {
        this.emphasized = emphasized;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }
}
