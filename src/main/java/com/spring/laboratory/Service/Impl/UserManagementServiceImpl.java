package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.Email;
import com.spring.laboratory.Entity.UserManagement;
import com.spring.laboratory.Mapper.UserManagementMapper;
import com.spring.laboratory.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService{

    @Autowired
    private UserManagementMapper userManagementMapper;
    //查询列表
    @Override
    public List<UserManagement> query(){
        return userManagementMapper.query();
    }
    @Override
    public void saveuser(UserManagement userManagement){
        userManagementMapper.saveuser(userManagement);
    }
}
