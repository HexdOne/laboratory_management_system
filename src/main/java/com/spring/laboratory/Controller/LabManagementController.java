package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.LabManagementService;
import io.swagger.annotations.*;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "实验室管理", description = "实验室管理")
@RequestMapping("/text/lab")
public class LabManagementController {
    @Autowired
    private LabManagementService labManagementService;
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<LabManagement> query(int pageNum) throws MyException {
        return labManagementService.query(pageNum);
    }
    @ApiOperation(value = "获取电脑数量",notes = "电脑数量及可用数量")
    @GetMapping(value = "/querycomputer")
    public Result<Map> querylist() throws MyException {
        return labManagementService.querylist();
    }
    @ApiOperation(value = "查询一条数据",notes = "根据门牌号查询",httpMethod = "GET")
    @GetMapping(value = "/querybydoornumber")
    public Result<LabManagement> querybydoornumber(LabManagement labManagement) throws MyException {
        return labManagementService.querybydoornumber(labManagement);
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "doorNumber",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<LabManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        return labManagementService.queryfuzzy(pageNum,doorNumber);
    }
    @ApiOperation(value = "插入数据")
    @PostMapping(value = "/insert")
    public Result insert(LabManagement labManagement) throws MyException {
        return labManagementService.insert(labManagement);
    }
    @ApiOperation(value = "修改")
    @PutMapping(value = "/updata")
    public Result updatebyuuid(LabManagement labManagement) throws MyException {
        return labManagementService.update(labManagement);
    }
    @ApiOperation(value = "删除一条数据",notes = "byuuid")
    @ApiParam(name = "uuid")
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(String uuid) throws MyException {
        return labManagementService.deletebyuuid(uuid);
    }
    @ApiOperation(value = "批量删除",notes = "传入uuid数组")
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        return labManagementService.batchdeleteuuid(uuid);
    }
    @ApiOperation(value = "删除所有数据",notes = "慎重使用")
    @DeleteMapping(value = "/deleteall")
    public Result deleteall() throws MyException {
        return labManagementService.deleteall();
    }
    @ApiOperation(value = "数据导出",hidden = false)
    @RequestMapping("/getExcel")
    public void getExcel (HttpServletResponse response) throws Exception {
        labManagementService.getExcel(response);
    }
}
