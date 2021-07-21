package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.Email;
import com.spring.laboratory.Entity.UserManagement;

import java.util.List;

public interface UserManagementMapper {
    List<UserManagement> query();
    void saveuser(UserManagement userManagement);
}
