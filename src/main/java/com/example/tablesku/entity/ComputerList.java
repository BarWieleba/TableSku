package com.example.tablesku.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComputerList {

    private Date modDate;
    private List<Computer> computer;

    public ComputerList() {
    }

    public ComputerList(Date modDate, List<Computer> computer) {
        this.modDate = modDate;
        this.computer = computer;
    }

    public List<Computer> getComputer() {
        return computer;
    }

    public void setComputer(List<Computer> computer) {
        this.computer = computer;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
