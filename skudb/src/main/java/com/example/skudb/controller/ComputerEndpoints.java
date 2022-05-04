package com.example.skudb.controller;

import com.bartek.soap.ComputerResponse;
import com.bartek.soap.Request;
import com.example.skudb.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ComputerEndpoints {
    @Autowired
    private ComputerService computerService;

    private static final String NAMESPACE_URI = "http://bartek.com/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "request")
    @ResponsePayload
    public ComputerResponse getComputersByManufacturerResponse(@RequestPayload Request request) {
        ComputerResponse response = new ComputerResponse();
        response.getComputerList().addAll(computerService.getComputers(request));
        response.setCount(response.getComputerList().size());
        return response;
    }

}
