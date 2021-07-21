package com.spring.laboratory.Service.Impl;

import com.spring.laboratory.Entity.LabManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.CaxunMapper;
import com.spring.laboratory.Service.CaxunService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaxunImpl implements CaxunService {
    @Autowired
    private CaxunMapper caxunMapper;

    @Override
    public Result<LabManagement> Caxun(String menpai) throws MyException {
        return ResultUtils.success(caxunMapper.Caxun(menpai));
    }
}
