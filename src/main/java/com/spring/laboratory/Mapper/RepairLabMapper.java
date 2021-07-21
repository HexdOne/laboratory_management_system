package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.RepairLab;

import java.util.List;
import java.util.Map;

public interface RepairLabMapper {
    List<RepairLab> query();
    List<Map> querydoornumber();
    List<RepairLab> querybydoornumber(String uuid);
    List<RepairLab> queryfuzzy(String doorNumber);
    void insert(RepairLab repairLab);
    void updatebyuuid(RepairLab repairLab);
    void deletebyuuid(String uuid);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
