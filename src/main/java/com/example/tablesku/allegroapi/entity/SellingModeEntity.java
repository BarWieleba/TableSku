package com.example.tablesku.allegroapi.entity;

public class SellingModeEntity {
    private String format;
    private PriceEntity price;
    private Integer popularity;
    private String popularityRange;

    private Integer bidCount;

    public SellingModeEntity() {
    }

    public SellingModeEntity(String format, PriceEntity price, Integer popularity, String popularityRange, Integer bidCount) {
        this.format = format;
        this.price = price;
        this.popularity = popularity;
        this.popularityRange = popularityRange;
        this.bidCount = bidCount;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getPopularityRange() {
        return popularityRange;
    }

    public void setPopularityRange(String popularityRange) {
        this.popularityRange = popularityRange;
    }

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }
}
