package com.example.skudb.controller;

import com.example.skudb.entity.Computer;
import com.example.skudb.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @PostMapping("/getAllComputers")
    public List<Computer> getAllComputers() {
        return computerService.readAll();
    }

    @PostMapping("/saveAllComputers")
    public List<Computer> saveAllComputers(@RequestBody List<Computer> computers) {
        return computerService.saveAllComputers(computers);
    }
}
