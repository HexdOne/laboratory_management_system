package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LabManagementService {
    void getExcel(HttpServletResponse response) throws Exception;
    Result<LabManagement> query(int pageNum);
    Result<Map> querylist();
    Result<LabManagement> querybydoornumber(LabManagement labManagement);
    Result<LabManagement> queryfuzzy(int pageNum,String doorNumber);
    Result insert(LabManagement labManagement);
    Result update(LabManagement labManagement);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
