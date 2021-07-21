package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.Email;
import com.spring.laboratory.Entity.UserManagement;

import java.util.List;

public interface UserManagementService {
    List<UserManagement> query();
    void saveuser(UserManagement userManagement);
}
