package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LoginService {
    //登录表单提交
    public UserInfo queryInfoByUsername(String username);
    //修改数据
    public void modifyInformation(UserInfo userInfo);
    //登录后修改密码
    public void changePassword(UserInfo userInfo);
    //忘记密码，密码找回
    public void retrievePassword(UserInfo userInfo);
    //是否有这个学号
    public Integer queryStudentid(String studentId);
    //是否有这个邮箱
    public  Integer queryEmail(String email);
    //是否有这个邮箱和学号（找回密码专用）
    public Integer queryStudentAndEmail(Map<String,String> map);
    //根据学号和邮箱查找用户
    public  UserInfo queryUser(Map<String,String> usermap);
    //防止用户名重复
    public Integer queryUsername(String username);
}
