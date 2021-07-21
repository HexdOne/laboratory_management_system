package com.spring.laboratory.Controller;

import com.spring.laboratory.Entity.UserManagement;
import com.spring.laboratory.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/text/user")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;
    //查询列表
//    @GetMapping(value = "/query")
//    public List<UserManagement> query(){
//        return userManagementService.query();
//    }
}
