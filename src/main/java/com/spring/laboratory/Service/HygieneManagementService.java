package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.HygieneManagement;
import com.spring.laboratory.Entity.Result;

import java.util.List;
import java.util.Map;

public interface HygieneManagementService {
    Result<HygieneManagement> query(int pageNum);
    Result<Map> querydoornumber();
    Result<HygieneManagement> queryfuzzy(int pageNum,String doorNumber);
    Result addHygiene(HygieneManagement hygieneManagement);
    Result updatebyuuid(HygieneManagement hygieneManagement);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
