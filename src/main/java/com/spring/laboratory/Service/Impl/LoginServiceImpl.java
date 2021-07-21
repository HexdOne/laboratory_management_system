package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.UserInfo;
import com.spring.laboratory.Mapper.LoginMapper;
import com.spring.laboratory.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserInfo queryInfoByUsername(String username) {
        System.out.println("service");
        return loginMapper.queryInfoByUsername(username);
    }
    @Override
    public void modifyInformation(UserInfo userInfo){
        System.out.println("进入到修改的service");
        loginMapper.modifyInformation(userInfo);
    }
    @Override
    public void changePassword(UserInfo userInfo){
        loginMapper.changePassword(userInfo);
    }
    @Override
    public void retrievePassword(UserInfo userInfo){
        System.out.println("进入到service修改密码");
        loginMapper.retrievePassword(userInfo);
    }
    //是否有这个学号
    @Override
    public Integer queryStudentid(String studentId){
        return loginMapper.queryStudentid(studentId);
    }
    //是否有这个邮箱
    @Override
    public  Integer queryEmail(String email){
        return  loginMapper.queryEmail(email);
    }
    @Override
    //是否有这个邮箱和学号（找回密码专用）
    public Integer queryStudentAndEmail(Map<String,String> map){
        return loginMapper.queryStudentAndEmail(map);
    }
    @Override
    //根据学号和邮箱查找用户
    public  UserInfo queryUser(Map<String,String> usermap){
    return loginMapper.queryUser(usermap);
    }
    @Override
    //防止用户名重复
    public Integer queryUsername(String username){
    return loginMapper.queryUsername(username);
    }

}
