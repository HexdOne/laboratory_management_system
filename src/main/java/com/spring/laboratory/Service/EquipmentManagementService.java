package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface EquipmentManagementService {
    Result<EquipmentManagement> query(int pageNum);
    void getExcel (HttpServletResponse response) throws Exception;
    List<EquipmentManagement> queryall();
    Result<EquipmentManagement> querybydoornumber(String doorNumber);
    Result<EquipmentManagement> querydoornumber();
    Result<Map> queryequipmentbrand();
    Result<EquipmentManagement> queryfuzzy(int pageNum,String doorNumber);
    Result addEquipment(EquipmentManagement equipmentManagement);
    Result updatebydoornumber(EquipmentManagement equipmentManagement);
    Result updatestatus(EquipmentManagement equipmentManagement);
    Result deletebyuuid(EquipmentManagement equipmentManagement);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
    String batchImport(String fileName, MultipartFile file) throws Exception;
}
