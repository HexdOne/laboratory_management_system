package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> queryroleByusername(String username);
}
