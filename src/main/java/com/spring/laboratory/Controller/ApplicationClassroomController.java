package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.ApplicationClassroom;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.ApplicationClassroomService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "教室申请", description = "学生申请使用教室")
@RequestMapping("/text/application")
public class ApplicationClassroomController {
    private Logger logger = LogManager.getLogger(ApplicationClassroomController.class);
    @Autowired
    private ApplicationClassroomService applicationClassroomService;
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<ApplicationClassroom> query(int pageNum) throws MyException {
        logger.info(pageNum+"   "+"开始查询");
        return applicationClassroomService.query(pageNum);
    }
    @ApiOperation(value = "查询一条数据",notes = "输入学号查询",httpMethod = "GET")
    @ApiParam(name = "studentid",value = "学号")
    @GetMapping(value = "/querybystudentid")
    public Result<ApplicationClassroom> querybystudentid(String studentid) throws MyException {
        return applicationClassroomService.querybystudentid(studentid);
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "studentName",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<ApplicationClassroom> queryfuzzy(int pageNum,String studentName) throws MyException {
        return applicationClassroomService.queryfuzzy(pageNum,studentName);
    }
    @ApiOperation(value = "查询实验室门牌号",notes = "查询所有的实验室门牌号",httpMethod = "GET")
    @GetMapping(value = "/querydoornumber")
    public Result<ApplicationClassroom> querydoornumber() throws MyException {
        return applicationClassroomService.querydoornumber();
    }
    @ApiOperation(value = "插入数据",notes = "添加",httpMethod = "POST")
//    @ApiImplicitParam(name = "applicationClassroom",value = "实体类",paramType = "body",required = true)
    @PostMapping(value = "/insert")
    public Result insert(ApplicationClassroom applicationClassroom) throws MyException {
        return applicationClassroomService.insert(applicationClassroom);
    }
    @ApiOperation(value = "修改",notes = "分配教室",httpMethod = "PUT")
    @PutMapping(value = "/updata")
    public Result updata(@ApiParam(name = "applicationClassroom",required = true) @RequestBody ApplicationClassroom applicationClassroom) throws MyException {
        return applicationClassroomService.update(applicationClassroom);
    }
    @ApiOperation(value = "删除所有数据",notes = "删除所有数据",httpMethod = "DELETE")
    @DeleteMapping(value = "/deleteall")
    public Result deleteall() throws MyException {
        return applicationClassroomService.deleteall();
    }
    @ApiOperation(value = "删除数据byuuid",notes = "uuid删除数据",httpMethod = "DELETE")
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(String uuid) throws MyException {
        return applicationClassroomService.deletebyuuid(uuid);
    }
    @ApiOperation(value = "批量删除",notes = "传入一组uuid",httpMethod = "DELETE")
    @ApiImplicitParam(name = "uuid",value = "id数组",paramType = "query",required = true)
    //@ApiParam(name = "uuid",value = "id数组",required = true)
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(@RequestParam String[] uuid) throws MyException {
        return applicationClassroomService.batchdeleteuuid(uuid);
    }
}
