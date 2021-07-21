package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.RepairManagement;

import java.util.List;
import java.util.Map;

public interface RepairManagementMapper {
    List<RepairManagement> query();
    List<RepairManagement> querybydoornumber(String uuid);
    List<Map> queryequipmentid(EquipmentManagement equipmentManagement);
    List<RepairManagement> queryfuzzy(String doorNumber);
    void insert(RepairManagement repairManagement);
    void deletebyuuid(String uuid);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
