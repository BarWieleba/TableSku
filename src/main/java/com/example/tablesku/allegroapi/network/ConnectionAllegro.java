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
import java.util.Map;

public class ConnectionAllegro {
    private final ObjectMapper objectMapper;
    private final ConnectionBean connectionBean;
    private final String TAG = "ConnectionAllegro";
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

    public Map<String, Object> getUserAuthInfo() throws IOException{
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", ini.get("auth_data", "Authorization"));
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        HashMap<String, Object> result = connectionBean.postXWwwFormUrlEncodedLogin("/device?client_id=" + ini.get("auth_data", "ClientId"), hashMap);
        return result;
    }

    public Map<String, Object> getTokens(String deviceCode) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", ini.get("auth_data", "Authorization"));
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        HashMap<String, Object> result = connectionBean.postXWwwFormUrlEncodedLogin("/token?grant_type=urn:ietf:params:oauth:grant-type:device_code&device_code=" + deviceCode, hashMap);
        return result;
    }
}
