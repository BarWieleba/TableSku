package com.example.tablesku.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
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

    public Computer() {
    }

    public Computer(String manufacturer, String screenSize, String resolution, String matrixTexture, String packaging, String processor, String coreCount, String processorSpeed, String ramSize, String driveSize, String driveType, String graphics, String videoMemory, String os, String recorder) {
        this.manufacturer = manufacturer;
        this.screenSize = screenSize;
        this.resolution = resolution;
        this.matrixTexture = matrixTexture;
        this.packaging = packaging;
        this.processor = processor;
        this.coreCount = coreCount;
        this.processorSpeed = processorSpeed;
        this.ramSize = ramSize;
        this.driveSize = driveSize;
        this.driveType = driveType;
        this.graphics = graphics;
        this.videoMemory = videoMemory;
        this.os = os;
        this.recorder = recorder;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMatrixTexture() {
        return matrixTexture;
    }

    public void setMatrixTexture(String matrixTexture) {
        this.matrixTexture = matrixTexture;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(String coreCount) {
        this.coreCount = coreCount;
    }

    public String getProcessorSpeed() {
        return processorSpeed;
    }

    public void setProcessorSpeed(String processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getDriveSize() {
        return driveSize;
    }

    public void setDriveSize(String driveSize) {
        this.driveSize = driveSize;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getVideoMemory() {
        return videoMemory;
    }

    public void setVideoMemory(String videoMemory) {
        this.videoMemory = videoMemory;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "manufacturer='" + manufacturer + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", resolution='" + resolution + '\'' +
                ", matrixTexture='" + matrixTexture + '\'' +
                ", packaging='" + packaging + '\'' +
                ", processor='" + processor + '\'' +
                ", coreCount='" + coreCount + '\'' +
                ", processorSpeed='" + processorSpeed + '\'' +
                ", ramSize='" + ramSize + '\'' +
                ", driveSize='" + driveSize + '\'' +
                ", driveType='" + driveType + '\'' +
                ", graphics='" + graphics + '\'' +
                ", videoMemory='" + videoMemory + '\'' +
                ", os='" + os + '\'' +
                ", recorder='" + recorder + '\'' +
                '}';
    }

    public String toFile() {
        return manufacturer + ";"
                + screenSize + ";"
                + resolution + ";"
                + matrixTexture + ";"
                + packaging + ";"
                + processor + ";"
                + coreCount + ";"
                + processorSpeed + ";"
                + ramSize + ";"
                + driveSize + ";"
                + driveType + ";"
                + graphics + ";"
                + videoMemory + ";"
                + os + ";"
                + recorder + ";\n";
    }
}
