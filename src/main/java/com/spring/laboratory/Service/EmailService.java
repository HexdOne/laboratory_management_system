package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.Email;

import java.util.List;

public interface EmailService {
    String  queryEmail(Email email);
    void saveEmail(Email email);
    String querynumber(Email email);
    void deleteall(Email email);
}
