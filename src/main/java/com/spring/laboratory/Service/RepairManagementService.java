package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.RepairManagement;
import com.spring.laboratory.Entity.Result;

import java.util.List;
import java.util.Map;

public interface RepairManagementService {
    Result<RepairManagement> query(int pageNum);
    Result<RepairManagement> querybydoornumber(String uuid);
    Result<Map> queryequipmentid(EquipmentManagement equipmentManagement);
    Result<RepairManagement> queryfuzzy(int pageNum,String doorNumber);
    Result insert(RepairManagement repairManagement);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
