package com.example.tablesku.allegroapi.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAuthEntity {
    private String deviceCode;
    private Integer expiresIn;
    private String userCode;
    private Integer interval;
    private String verificationUri;
    private String verificationUriComplete;

    public UserAuthEntity() {
    }

    public UserAuthEntity(String deviceCode, Integer expiresIn, String userCode, Integer interval, String verificationUri, String verificationUriComplete) {
        this.deviceCode = deviceCode;
        this.expiresIn = expiresIn;
        this.userCode = userCode;
        this.interval = interval;
        this.verificationUri = verificationUri;
        this.verificationUriComplete = verificationUriComplete;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getVerificationUri() {
        return verificationUri;
    }

    public void setVerificationUri(String verificationUri) {
        this.verificationUri = verificationUri;
    }

    public String getVerificationUriComplete() {
        return verificationUriComplete;
    }

    public void setVerificationUriComplete(String verificationUriComplete) {
        this.verificationUriComplete = verificationUriComplete;
    }
}
