package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.HygieneManagement;

import java.util.List;
import java.util.Map;

public interface HygieneManagementMapper {
    List<HygieneManagement> query();
    List<Map> querydoornumber();
    List<HygieneManagement> queryfuzzy(String doorNumber);
    void insertHygiene(HygieneManagement hygieneManagement);
    void updatebyuuid(HygieneManagement hygieneManagement);
    void deletebyuuid(String uuid);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
