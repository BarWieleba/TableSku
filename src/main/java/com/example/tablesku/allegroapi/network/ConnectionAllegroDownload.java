package com.example.tablesku.allegroapi.network;

import com.example.tablesku.allegroapi.entity.AuthTokenEntity;
import com.example.tablesku.allegroapi.entity.ItemsEntity;
import com.example.tablesku.network.ConnectionBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionAllegroDownload {

    private final ObjectMapper objectMapper;
    private final ConnectionBean connectionBean;
    private final String TAG = "ConnectionAllegro";


    public ConnectionAllegroDownload() {
        objectMapper = new ObjectMapper();
        connectionBean = new ConnectionBean("https://api.allegro.pl.allegrosandbox.pl");
    }

    public Map<String, Object> getLaptops(AuthTokenEntity tokenEntity) throws IOException {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", "Bearer " + tokenEntity.getAccessToken());
        hashMap.put("Accept", "application/vnd.allegro.public.v1+json");
        HashMap<String, Object> result = connectionBean.getXWwwFormUrlEncodedLogin("/offers/listing?category.id=2&phrase=laptop", hashMap);
        return result;
    }
}
