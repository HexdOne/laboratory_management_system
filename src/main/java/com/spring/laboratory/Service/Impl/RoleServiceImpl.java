package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.Role;
import com.spring.laboratory.Mapper.RoleMapper;
import com.spring.laboratory.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> queryroleByusername(String username){
        return roleMapper.queryroleByusername(username);
    }
}
