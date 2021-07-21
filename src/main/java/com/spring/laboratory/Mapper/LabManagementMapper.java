package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.LabManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LabManagementMapper {
    List<LabManagement> query();
    List<LabManagement> queryall();
    List<Map> querylist();
    List<LabManagement> querybydoornumber(LabManagement labManagement);
    List<LabManagement> queryfuzzy(String doorNumber);
    void insert(LabManagement labManagement);
    void update(LabManagement labManagement);
    void deletebyuuid(String uuid);




    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
