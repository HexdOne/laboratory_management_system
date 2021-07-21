package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.HygieneManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.HygieneManagementService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "卫生检查", description = "卫生检查")
@RequestMapping("/text/hygiene")
public class HygieneManagementController {
    @Autowired
    private HygieneManagementService hygieneManagementService;
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<HygieneManagement> query(int pageNum) throws MyException {
        return hygieneManagementService.query(pageNum);
    }
    @ApiOperation(value = "查询实验室门牌号",notes = "查询所有的实验室门牌号",httpMethod = "GET")
    @GetMapping(value = "/querydoornumber")
    public Result<Map> querydoornumber() throws MyException {
        return hygieneManagementService.querydoornumber();
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "doorNumber",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<HygieneManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        return hygieneManagementService.queryfuzzy(pageNum,doorNumber);
    }
    @ApiOperation(value = "插入数据")
    @PostMapping(value = "/insert")
    public Result addHygiene(HygieneManagement hygieneManagement) throws MyException {
        return hygieneManagementService.addHygiene(hygieneManagement);
    }
    @ApiOperation(value = "修改")
    @PutMapping(value = "/updata")
    public Result updatebyuuid(HygieneManagement hygieneManagement) throws MyException {
        return hygieneManagementService.updatebyuuid(hygieneManagement);
    }
    @ApiOperation(value = "删除所有数据",notes = "慎重使用")
    @DeleteMapping(value = "/deleteall")
    public Result deleteall() throws MyException {
        return hygieneManagementService.deleteall();
    }
    @ApiOperation(value = "删除一条数据",notes = "byuuid")
    @ApiParam(name = "uuid")
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(String uuid) throws MyException {
        return hygieneManagementService.deletebyuuid(uuid);
    }
    @ApiOperation(value = "批量删除",notes = "传入uuid数组")
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        return hygieneManagementService.batchdeleteuuid(uuid);
    }
}
