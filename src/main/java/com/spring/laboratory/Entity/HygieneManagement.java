package com.spring.laboratory.Entity;

import java.sql.Date;

public class HygieneManagement {

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHygienePerson() {
        return hygienePerson;
    }

    public void setHygienePerson(String hygienePerson) {
        this.hygienePerson = hygienePerson;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getHygieneResult() {
        return hygieneResult;
    }

    public void setHygieneResult(String hygieneResult) {
        this.hygieneResult = hygieneResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHygieneTime() {
        return hygieneTime;
    }

    public void setHygieneTime(String hygieneTime) {
        this.hygieneTime = hygieneTime;
    }

    private String uuid;
    private String hygienePerson;
    private String doorNumber;
    private String hygieneResult;
    private String remark;
    private String hygieneTime;

}
