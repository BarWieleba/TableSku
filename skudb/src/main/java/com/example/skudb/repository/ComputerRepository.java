package com.example.skudb.repository;

import com.example.skudb.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findComputersByManufacturer(String manufacturer);
}
