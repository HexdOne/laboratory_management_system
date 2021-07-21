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
        private String doorNumber;//门牌号
        private String labName;//实验室名称
        private String labType;//实验室类型
        private String department;//所属部门
        private int computerNumber;//电脑台数
        private int availableComputer;//电脑可用台数
        private String labManager;//负责人
        private String phoneNumber;//负责人电话
        private String labIntroduction;//实验室介绍
        private String software;//常用软件
        private String configuration;//常用配置
}
