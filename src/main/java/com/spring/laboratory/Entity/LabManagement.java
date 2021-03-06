package com.spring.laboratory.Entity;

public class LabManagement {

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getComputerNumber() {
        return computerNumber;
    }

    public void setComputerNumber(Integer computerNumber) {
        this.computerNumber = computerNumber;
    }

    public Integer getAvailableComputer() {
        return availableComputer;
    }

    public void setAvailableComputer(Integer availableComputer) {
        this.availableComputer = availableComputer;
    }

    public String getLabManager() {
        return labManager;
    }

    public void setLabManager(String labManager) {
        this.labManager = labManager;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLabIntroduction() {
        return labIntroduction;
    }

    public void setLabIntroduction(String labIntroduction) {
        this.labIntroduction = labIntroduction;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    private String uuid;
        private String doorNumber;//?????????
        private String labName;//???????????????
        private String labType;//???????????????
        private String department;//????????????
        private int computerNumber;//????????????
        private int availableComputer;//??????????????????
        private String labManager;//?????????
        private String phoneNumber;//???????????????
        private String labIntroduction;//???????????????
        private String software;//????????????
        private String configuration;//????????????
}
