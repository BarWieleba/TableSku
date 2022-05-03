package com.example.tablesku.network;

import com.example.tablesku.entity.Computer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {
    private final ObjectMapper objectMapper;
    private final ConnectionBean connectionBean;
    private final String TAG = "ConnectionHelper";
    public ConnectionHelper(){
        objectMapper = new ObjectMapper();
        connectionBean = new ConnectionBean("http://localhost:8080/api");
    }

    public List<Computer> saveComputers (List<Computer> computers) throws IOException {
        String json = connectionBean.postObjects(computers, "/saveAllComputers");
        if(json!=null && !json.isEmpty()){
            List<Computer> computerList = objectMapper.readValue(json, new TypeReference<List<Computer>>(){});
            return  computerList;
        }
        return new ArrayList<>();
    }

    public List<Computer> readAllComputer() throws IOException {
        String json = connectionBean.postGet("/getAllComputers");
        if(json!=null && !json.isEmpty()){
            List<Computer> computerList = objectMapper.readValue(json, new TypeReference<List<Computer>>(){});
            return  computerList;
        }
        return new ArrayList<>();
    }


}
