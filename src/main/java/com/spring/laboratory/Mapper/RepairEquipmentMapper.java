package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.RepairEquipment;

import java.util.List;
import java.util.Map;

public interface RepairEquipmentMapper {
    List<RepairEquipment> query();
    List<Map> querydoornumber();
    List<Map> queryequipmentname(String doorNumber);
    List<RepairEquipment> querybydoornumber(String uuid);
    List<RepairEquipment> queryfuzzy(String doorNumber);
    void insert(RepairEquipment repairEquipment);
    void updatebyuuid(RepairEquipment repairEquipment);
    void deletebyuuid(String uuid);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
