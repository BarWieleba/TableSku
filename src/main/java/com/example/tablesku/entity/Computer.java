package com.example.tablesku.entity;

public class Computer {
    private String manufacturer = "";
    private String screenSize = "";
    private String resolution = "";
    private String matrixTexture = "";
    private String packaging = "";
    private String processor = "";
    private String coreCount = "";
    private String processorSpeed = "";
    private String ramSize = "";
    private String driveSize = "";
    private String driveType = "";
    private String graphics = "";
    private String videoMemory = "";
    private String os = "";
    private String recorder = "";

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
}
