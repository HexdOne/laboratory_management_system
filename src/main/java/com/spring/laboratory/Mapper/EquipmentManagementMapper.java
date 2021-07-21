package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.EquipmentManagement;

import java.util.List;
import java.util.Map;

public interface EquipmentManagementMapper {
    List<EquipmentManagement> query();
    List<EquipmentManagement> queryall();
    void  updatebyEquipmentId(EquipmentManagement equipmentManagement);
    int  querybyid(String equipmentId);
    List<EquipmentManagement> querybydoornumber(String doorNumber);
    List<EquipmentManagement> querydoornumber();
    List<Map> queryequipmentbrand();
    List<EquipmentManagement> queryfuzzy(String doorNumber);
    void insertEquipment(EquipmentManagement equipmentManagement);
    void updatebydoornumber(EquipmentManagement equipmentManagement);
    void updatestatus(EquipmentManagement equipmentManagement);
    void deletebyuuid(EquipmentManagement equipmentManagement);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
