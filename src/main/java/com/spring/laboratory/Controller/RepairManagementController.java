package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.RepairManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.RepairManagementService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "维修信息", description = "维修信息")
@RequestMapping("/text/repair")
public class RepairManagementController {
    @Autowired
    private RepairManagementService repairManagementService;
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<RepairManagement> query(int pageNum) throws MyException {
        return repairManagementService.query(pageNum);
    }
    @ApiOperation(value = "查询一条数据",notes = "byuuid",httpMethod = "GET")
    @ApiParam(name = "uuid",required = true)
    @GetMapping(value = "/querybydoornumber")
    public Result<RepairManagement> querybydoornumber(String uuid) throws MyException {
        return repairManagementService.querybydoornumber(uuid);
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "doorNumber",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<RepairManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        return repairManagementService.queryfuzzy(pageNum,doorNumber);
    }
    @ApiOperation(value = "查询设备编号",notes = "查询设备编号",httpMethod = "GET")
    @GetMapping(value = "/queryequipmentid")
    public Result<Map> queryequipmentid(EquipmentManagement equipmentManagement) throws MyException {
        return repairManagementService.queryequipmentid(equipmentManagement);
    }
    @ApiOperation(value = "插入数据")
    @PostMapping(value = "/insert")
    public Result insert(RepairManagement repairManagement) throws MyException {
        return repairManagementService.insert(repairManagement);
    }
    @ApiOperation(value = "删除所有数据",notes = "慎重使用")
    @DeleteMapping(value = "/delete")
    public Result deleteall() throws MyException {
        return repairManagementService.deleteall();
    }
    @ApiOperation(value = "删除一条数据",notes = "byuuid")
    @ApiParam(name = "uuid",required = true)
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(String uuid) throws MyException {
        return repairManagementService.deletebyuuid(uuid);
    }
    @ApiOperation(value = "批量删除",notes = "传入uuid数组")
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        return repairManagementService.batchdeleteuuid(uuid);
    }
}
