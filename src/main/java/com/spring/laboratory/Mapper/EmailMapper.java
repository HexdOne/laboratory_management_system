package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.Email;

import java.util.List;

public interface EmailMapper {
    String  queryEmail(Email email);
    void saveEmail(Email email);
    String querynumber(Email email);
    void deleteall(Email email);
}
