package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Service.CaxunService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "查询", description = "实验室信息")
@RequestMapping("/text")
public class CaxunController {
    @Autowired
    private CaxunService caxunService;
    @ApiOperation(value = "查询实验室",httpMethod = "GET")
    @GetMapping(value = "/caxun")
    public Result<LabManagement> Caxun(String doornumber) throws MyException {
        return caxunService.Caxun(doornumber);
    }
}
