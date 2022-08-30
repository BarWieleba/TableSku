package com.example.tablesku.allegroapi.network;

import com.example.tablesku.allegroapi.entity.AuthTokenEntity;
import com.example.tablesku.allegroapi.entity.UserAuthEntity;
import com.example.tablesku.entity.Computer;
import com.example.tablesku.network.ConnectionBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ini4j.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConnectionAllegro {
    private final ObjectMapper objectMapper;
    private final ConnectionBean connectionBean;
    private final String TAG = "ConnectionHelper";
    private Wini ini;


    public ConnectionAllegro(){
        objectMapper = new ObjectMapper();
        connectionBean = new ConnectionBean("https://allegro.pl.allegrosandbox.pl/auth/oauth");
        try {
            ini = new Wini(new File("src/main/resources/config.ini"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public UserAuthEntity getUserAuthInfo() throws IOException{
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", ini.get("auth_data", "Authorization"));
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        String json = connectionBean.postXWwwFormUrlEncodedLogin("/device?client_id=" + ini.get("auth_data", "ClientId"), hashMap);
        UserAuthEntity userAuthEntity = objectMapper.readValue(json, UserAuthEntity.class);
        return userAuthEntity;
    }

    public AuthTokenEntity getTokens(String deviceCode) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", ini.get("auth_data", "Authorization"));
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        String json = connectionBean.postXWwwFormUrlEncodedLogin("/token?grant_type=urn:ietf:params:oauth:grant-type:device_code&device_code=" + deviceCode, hashMap);

        AuthTokenEntity authTokenEntity = objectMapper.readValue(json, AuthTokenEntity.class);
        return authTokenEntity;
    }
}
