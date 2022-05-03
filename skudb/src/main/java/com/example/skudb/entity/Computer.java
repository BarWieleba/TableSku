package com.example.skudb.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String manufacturer = "";   //0
    private String screenSize = "";     //1
    private String resolution = "";     //2
    private String matrixTexture = "";  //3
    private String packaging = "";      //4
    private String processor = "";      //5
    private String coreCount = "";      //6
    private String processorSpeed = ""; //7
    private String ramSize = "";        //8
    private String driveSize = "";      //9
    private String driveType = "";      //10
    private String graphics = "";       //11
    private String videoMemory = "";    //12
    private String os = "";             //13
    private String recorder = "";       //14
}
