package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.RepairEquipment;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.RepairEquipmentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "报修信息", description = "设备报修信息")
@RequestMapping("/text/repairequipment")
public class RepairEquipmentController {
    @Autowired
    private RepairEquipmentService repairEquipmentService;
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<RepairEquipment> query(int pageNum) throws MyException {
        return repairEquipmentService.query(pageNum);
    }
    @ApiOperation(value = "查询实验室门牌号",notes = "查询所有的实验室门牌号",httpMethod = "GET")
    @GetMapping(value = "/querydoornumber")
    public Result<Map> querydoornumber() throws MyException {
        return repairEquipmentService.querydoornumber();
    }
    @ApiOperation(value = "查询设备名称",notes = "门牌号",httpMethod = "GET")
    @ApiParam(name = "doorNumber",value = "门牌号",required = true)
    @GetMapping(value = "/queryequipmentname")
    public Result<Map> queryequipmentname(String doorNumber) throws MyException {
        return repairEquipmentService.queryequipmentname(doorNumber);
    }
    @ApiOperation(value = "查询一条数据",notes = "byuuid",httpMethod = "GET")
    @ApiParam(name = "uuid",required = true)
    @GetMapping(value = "/querybydoornumber")
    public Result<RepairEquipment> querybydoornumber(String uuid) throws MyException {
        return repairEquipmentService.querybydoornumber(uuid);
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "doorNumber",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<RepairEquipment> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        return repairEquipmentService.queryfuzzy(pageNum,doorNumber);
    }
    @ApiOperation(value = "插入数据")
    @PostMapping(value = "/insert")
    public Result insert(RepairEquipment repairEquipment) throws MyException {
        return repairEquipmentService.insert(repairEquipment);
    }
    @ApiOperation(value = "修改")
    @PutMapping(value = "/updata")
    public Result updatebyuuid(RepairEquipment repairEquipment) throws MyException {
        return repairEquipmentService.updatebyuuid(repairEquipment);
    }
    @ApiOperation(value = "删除所有数据",notes = "慎重使用")
    @DeleteMapping(value = "/deleteall")
    public Result deleteall() throws MyException {
        return repairEquipmentService.deleteall();
    }
    @ApiOperation(value = "删除一条数据",notes = "byuuid")
    @ApiParam(name = "uuid",required = true)
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(String uuid) throws MyException {
        return repairEquipmentService.deletebyuuid(uuid);
    }
    @ApiOperation(value = "批量删除",notes = "传入uuid数组")
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        return repairEquipmentService.batchdeleteuuid(uuid);
    }
}
