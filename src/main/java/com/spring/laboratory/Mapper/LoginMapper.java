package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.UserInfo;

import java.util.Map;

public interface LoginMapper {

    public UserInfo queryInfoByUsername(String username);

    public void register(UserInfo userInfo);

    public  void saveRole(Map<String, Object> saveParams);

    public void modifyInformation(UserInfo userInfo);

    public void changePassword(UserInfo userInfo);

    public void retrievePassword(UserInfo userInfo);
    //是否有这个学号
    public Integer queryStudentid(String studentId);
    //是否有这个邮箱
    public  Integer queryEmail(String email);
    //是否有这个邮箱和学号(找回密码专用
    public Integer queryStudentAndEmail(Map<String,String> map);
    //根据学号和邮箱查找用户
    public  UserInfo queryUser(Map<String,String> usermap);
    //防止用户名重复
    public Integer queryUsername(String username);
}
