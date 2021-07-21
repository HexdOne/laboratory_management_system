package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.Permission;

import java.util.List;

public interface PermissionMapper {

    List<Permission> getUserPermission(String username);
}
