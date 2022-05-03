package com.example.tablesku.soap;

import com.example.tablesku.entity.Computer;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;
import java.util.Set;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ComputerSoap {
    @WebMethod
    List<Computer> getAllComputersByMatrixSize(String matrixSize);

    @WebMethod
    List<Computer> getAllComputersByManufacturer(String manufacturer);

    @WebMethod
    Set<String> getAllManufacturers();

    @WebMethod
    Set<String> getAllMatrixSizes();
}
