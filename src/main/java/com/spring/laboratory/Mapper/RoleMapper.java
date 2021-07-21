package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.Role;

import java.util.List;

public interface RoleMapper {

List<Role> queryroleByusername(String username);
}
