package com.example.skudb.service;

import com.bartek.soap.GetComputersByManufacturerResponse;
import com.example.skudb.entity.Computer;
import com.example.skudb.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> saveAllComputers(List<Computer> computerList) {
        return computerRepository.saveAll(computerList);
    }

    public List<Computer> readAll() {
        return computerRepository.findAll();
    }

    public List<GetComputersByManufacturerResponse.ComputerList> getComputersByManufaturer(String manufacturer) {
        List<Computer> computers = computerRepository.findComputersByManufacturer(manufacturer);
        return computerIntoComputerList(computerRepository.findComputersByManufacturer(manufacturer));
    }

    private List<GetComputersByManufacturerResponse.ComputerList> computerIntoComputerList(List<Computer> computers){
        List<GetComputersByManufacturerResponse.ComputerList> computerLists = new ArrayList<>();
        for (Computer computer : computers) {
            GetComputersByManufacturerResponse.ComputerList computerList = new GetComputersByManufacturerResponse.ComputerList();
            computerList.setId(computer.getId());
            computerList.setManufacturer(computer.getManufacturer());
            computerList.setScreenSize(computer.getScreenSize());
            computerList.setResolution(computer.getResolution());
            computerList.setMatrixTexture(computer.getMatrixTexture());
            computerList.setPackaging(computer.getPackaging());
            computerList.setProcessor(computer.getProcessor());
            computerList.setCoreCount(computer.getCoreCount());
            computerList.setProcessorSpeed(computer.getProcessorSpeed());
            computerList.setRamSize(computer.getRamSize());
            computerList.setDriveSize(computer.getDriveSize());
            computerList.setDriveType(computer.getDriveType());
            computerList.setGraphics(computer.getGraphics());
            computerList.setVideoMemory(computer.getVideoMemory());
            computerList.setOs(computer.getOs());
            computerList.setRecorder(computer.getRecorder());
            computerLists.add(computerList);
        }
        return computerLists;
    }
}
