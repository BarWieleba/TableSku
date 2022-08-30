package com.example.tablesku.allegroapi.entity;

public class SellerEntity {
    private Integer id;
    private String login;
    private Boolean company;
    private Boolean superSeller;

    public SellerEntity() {
    }

    public SellerEntity(Integer id, String login, Boolean company, Boolean superSeller) {
        this.id = id;
        this.login = login;
        this.company = company;
        this.superSeller = superSeller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getCompany() {
        return company;
    }

    public void setCompany(Boolean company) {
        this.company = company;
    }

    public Boolean getSuperSeller() {
        return superSeller;
    }

    public void setSuperSeller(Boolean superSeller) {
        this.superSeller = superSeller;
    }
}
