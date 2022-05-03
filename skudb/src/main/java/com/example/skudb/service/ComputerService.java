package com.example.skudb.service;

import com.example.skudb.entity.Computer;
import com.example.skudb.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
