package com.spring.laboratory.Service;


import com.spring.laboratory.Entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getUserPermission(String username);
}
