package com.example.skudb.service;

import com.bartek.soap.ComputerResponse;
import com.bartek.soap.Request;
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

    public List<ComputerResponse.ComputerList> getComputers(Request request) {
        List<Computer> computers = new ArrayList<>();

        if (request.isEverything()) {
            return computerIntoComputerList(computerRepository.findAll());
        }

        if(request.getManufacturer() != null && !request.getManufacturer().isEmpty() && request.getResolution() != null && !request.getResolution().isEmpty()) {
            computers = computerRepository.findComputersByManufacturer(request.getManufacturer());
            computers = screenProportions(computers, request.getResolution());
        }
        else if(request.getManufacturer() != null && !request.getManufacturer().isEmpty()) {
            computers = computerRepository.findComputersByManufacturer(request.getManufacturer());
        }
        else if(request.getResolution() != null && !request.getResolution().isEmpty()) {
            computers = computerRepository.findAll();
            computers = screenProportions(computers, request.getResolution());
        }
        else if(request.getMatrixType() != null && !request.getMatrixType().isEmpty()) {
            computers = computerRepository.findComputersByMatrixTexture(request.getMatrixType());
        }
        return computerIntoComputerList(computers);
    }

    private List<Computer> screenProportions(List<Computer> computers, String proportions) {
        List<Computer> matchingComputers = new ArrayList<>();
        for(Computer computer : computers) {
            if(doProportionsMatch(computer, proportions)){
                matchingComputers.add(computer);
            }
        }
        return matchingComputers;
    }

    private boolean doProportionsMatch(Computer computer, String proportions) {
        if(computer.getResolution() == null || computer.getResolution().isEmpty()){
            return false;
        }
        String[] proportion = proportions.split("x");
        int widthProp = Integer.parseInt(proportion[0]);
        int heightProp = Integer.parseInt(proportion[1]);

        String[] resolution = computer.getResolution().split("x");
        int width = Integer.parseInt(resolution[0]);
        int height = Integer.parseInt(resolution[1]);
        return (width/widthProp) == (height/heightProp);
    }



    private List<ComputerResponse.ComputerList> computerIntoComputerList(List<Computer> computers){
        List<ComputerResponse.ComputerList> computerLists = new ArrayList<>();
        for (Computer computer : computers) {
            ComputerResponse.ComputerList computerList = new ComputerResponse.ComputerList();
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
