package com.example.tablesku.allegroapi.entity;

import java.util.List;

public class ItemEntity {
    private String id;
    private String name;

    private VendorEntity vendor;
    private SellerEntity seller;
    private PromotionEntity promotion;
    private DeliveryEntity delivery;
    private List<ImageEntity> images;
    private SellingModeEntity sellingMode;
    private StockEntity stock;
    private CategoryEntity category;

    private PublicationEntity publication;

    public ItemEntity() {
    }

    public ItemEntity(String id, String name, VendorEntity vendor, SellerEntity seller, PromotionEntity promotion, DeliveryEntity delivery, List<ImageEntity> images, SellingModeEntity sellingMode, StockEntity stock, CategoryEntity category, PublicationEntity publication) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.seller = seller;
        this.promotion = promotion;
        this.delivery = delivery;
        this.images = images;
        this.sellingMode = sellingMode;
        this.stock = stock;
        this.category = category;
        this.publication = publication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerEntity seller) {
        this.seller = seller;
    }

    public PromotionEntity getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionEntity promotion) {
        this.promotion = promotion;
    }

    public DeliveryEntity getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryEntity delivery) {
        this.delivery = delivery;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public SellingModeEntity getSellingMode() {
        return sellingMode;
    }

    public void setSellingMode(SellingModeEntity sellingMode) {
        this.sellingMode = sellingMode;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public PublicationEntity getPublication() {
        return publication;
    }

    public void setPublication(PublicationEntity publication) {
        this.publication = publication;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public void setVendor(VendorEntity vendor) {
        this.vendor = vendor;
    }
}
