package com.example.skudb.controller;

import com.bartek.soap.GetComputersByManufacturerRequest;
import com.bartek.soap.GetComputersByManufacturerResponse;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getComputersByManufacturerRequest")
    @ResponsePayload
    public GetComputersByManufacturerResponse getComputersByManufacturerResponse(@RequestPayload GetComputersByManufacturerRequest request) {
        GetComputersByManufacturerResponse response = new GetComputersByManufacturerResponse();
        response.getComputerList().addAll(computerService.getComputersByManufaturer(request.getManufacturer()));
        return response;
    }

}
