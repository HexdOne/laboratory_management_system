package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.RepairLab;
import com.spring.laboratory.Entity.Result;

import java.util.List;
import java.util.Map;

public interface RepairLabService {
    Result<RepairLab> query(int pageNum);
    Result<Map> querydoornumber();
    Result<RepairLab> querybydoornumber(String uuid);
    Result<RepairLab> queryfuzzy(int pageNum,String doorNumber);
    Result insert(RepairLab repairLab);
    Result updatebyuuid(RepairLab repairLab);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
