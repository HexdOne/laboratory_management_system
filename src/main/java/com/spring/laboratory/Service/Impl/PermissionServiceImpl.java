package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.Permission;
import com.spring.laboratory.Mapper.PermissionMapper;
import com.spring.laboratory.Service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    public  List<Permission> getUserPermission(String username){
     return permissionMapper.getUserPermission(username);
    }
}
