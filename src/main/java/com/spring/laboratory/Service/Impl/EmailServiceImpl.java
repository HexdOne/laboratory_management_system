package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.Email;
import com.spring.laboratory.Mapper.EmailMapper;
import com.spring.laboratory.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailMapper emailMapper;

    @Override
    public  String queryEmail(Email email){return  emailMapper.queryEmail(email);}

    @Override
    public void saveEmail(Email email){
        emailMapper.saveEmail(email);
    }

    @Override
    public String querynumber(Email email){
        return emailMapper.querynumber(email);
    }

    @Override
    public void deleteall(Email email){
       emailMapper.deleteall(email);
    }
}

