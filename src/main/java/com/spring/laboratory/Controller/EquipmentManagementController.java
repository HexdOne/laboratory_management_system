package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.EquipmentManagementService;
import com.spring.laboratory.Utils.ResultUtils;
import io.swagger.annotations.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "设备管理", description = "设备管理")
@RequestMapping("/text/equipment")
public class EquipmentManagementController {
    @Autowired
    private EquipmentManagementService equipmentManagementService;

    static private String sss;
    //查询列表
    //@RequiresPermissions("admin:supervise1")
//    @RequiresRoles("admin")
    @ApiOperation(value = "查询所有数据",notes = "提交要查询的页码，返回该页的数据",httpMethod = "GET")
    @ApiParam(name = "pageNum",value = "当前页码",required = true)
    @GetMapping(value = "/query")
    public Result<EquipmentManagement> query(int pageNum) throws MyException {
        return equipmentManagementService.query(pageNum);
    }
    //通过门派号查询数据
    @ApiOperation(value = "门牌号查数据")
    @ApiParam(name = "doorNumber",value = "门牌号",required = true)
    @GetMapping(value = "/querybydoornumber")
    public Result<EquipmentManagement> querybydoornumber(String doorNumber) throws MyException {
        return equipmentManagementService.querybydoornumber(doorNumber);
    }
    @ApiOperation(value = "查询实验室门牌号")
    @GetMapping(value = "/querydoornumber")
    public Result<EquipmentManagement> querydoornumber() throws MyException {
        return equipmentManagementService.querydoornumber();
    }
    @ApiOperation(value = "查询各品牌电脑数量")
    @GetMapping(value = "/queryequipmentbrand")
    public Result<Map> queryequipmentbrand() throws MyException {
        return equipmentManagementService.queryequipmentbrand();
    }
    @ApiOperation(value = "模糊查询",notes = "输入需要查询的字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",dataType="int", paramType = "query"),
            @ApiImplicitParam(name = "doorNumber",value = "输入要查询的字段",dataType="String", paramType = "query")})
    @GetMapping(value = "/queryfuzzy")
    public Result<EquipmentManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        return equipmentManagementService.queryfuzzy(pageNum,doorNumber);
    }
    @ApiOperation(value = "插入数据")
    @PostMapping(value = "/insert")
    public Result addEquipmentManagement(EquipmentManagement equipmentManagement) throws MyException {
        return equipmentManagementService.addEquipment(equipmentManagement);
    }
    @ApiOperation(value = "修改")
    @PutMapping(value = "/updata")
    public Result updatebydoornumber(EquipmentManagement equipmentManagement) throws MyException {
        return equipmentManagementService.updatebydoornumber(equipmentManagement);
    }
    @ApiOperation(value = "修改设备状态")
    @PutMapping(value = "/updatastatus")
    public Result updatastatus(EquipmentManagement equipmentManagement) throws MyException {
        return equipmentManagementService.updatestatus(equipmentManagement);
    }
    @ApiOperation(value = "删除所有数据",notes = "慎重使用")
    @DeleteMapping(value = "/deleteall")
    public Result deleteall() throws MyException {
        return equipmentManagementService.deleteall();
    }
    @ApiOperation(value = "删除一条数据",notes = "byuuid")
    @DeleteMapping(value = "/deletebyuuid")
    public Result deletebyuuid(EquipmentManagement equipmentManagement) throws MyException {
        return equipmentManagementService.deletebyuuid(equipmentManagement);
    }
    @ApiOperation(value = "批量删除",notes = "传入uuid数组")
    @DeleteMapping(value = "/batchdeleteuuid")
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        return equipmentManagementService.batchdeleteuuid(uuid);
    }
    @ApiOperation(value = "数据导出",hidden = false)
    @RequestMapping(value = "/getExcel")
    public void getExcel (HttpServletResponse response) throws Exception {
        equipmentManagementService.getExcel(response);
    }
    @ApiOperation(value = "数据导入",hidden = false)
    @RequestMapping(value = "/import")
    public Result exImport(@RequestParam(value = "filename")MultipartFile file, HttpSession session) {

        String a;

        String fileName = file.getOriginalFilename();

        try {
            sss = equipmentManagementService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return ResultUtils.error(200,sss);
    }
}
