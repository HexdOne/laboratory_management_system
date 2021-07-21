package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.RepairEquipment;
import com.spring.laboratory.Entity.Result;


import java.util.Map;

public interface RepairEquipmentService {
    Result<RepairEquipment> query(int pageNum);
    Result<Map> querydoornumber();
    Result<Map> queryequipmentname(String doorNumber);
    Result<RepairEquipment> querybydoornumber(String uuid);
    Result<RepairEquipment> queryfuzzy(int pageNum,String doorNumber);
    Result insert(RepairEquipment repairEquipment);
    Result updatebyuuid(RepairEquipment repairEquipment);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
