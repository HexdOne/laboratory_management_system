package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.HygieneManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.HygieneManagementMapper;
import com.spring.laboratory.Service.HygieneManagementService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HygieneManagementServiceImpl implements HygieneManagementService{
    @Autowired
    private HygieneManagementMapper hygieneManagementMapper;
    //查询列表
    @Override
    public Result<HygieneManagement> query(int pageNum) throws MyException {
        if (hygieneManagementMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<HygieneManagement> list = hygieneManagementMapper.query();
            PageInfo<HygieneManagement> pageInfo = new PageInfo<HygieneManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询bydoornumber
    @Override
    public Result<Map> querydoornumber() throws MyException {
        if (hygieneManagementMapper.querydoornumber().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(hygieneManagementMapper.querydoornumber());
        }
    }
    //模糊查询
    @Override
    public Result<HygieneManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (hygieneManagementMapper.queryfuzzy(doorNumber).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<HygieneManagement> list = hygieneManagementMapper.queryfuzzy(doorNumber);
            PageInfo<HygieneManagement> pageInfo = new PageInfo<HygieneManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加一条数据
    @Override
    public Result addHygiene(HygieneManagement hygieneManagement) throws MyException {
        if (hygieneManagement.getDoorNumber()==null||hygieneManagement.getHygienePerson()==null||hygieneManagement.getHygieneResult()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
        else {
            hygieneManagementMapper.insertHygiene(hygieneManagement);
            return ResultUtils.success();
        }
    }
    //通过门牌号修改数据
    @Override
    public Result updatebyuuid(HygieneManagement hygieneManagement) throws MyException {
        if (hygieneManagement.getDoorNumber()==null||hygieneManagement.getHygienePerson()==null||hygieneManagement.getHygieneResult()==null){
            throw new MyException(ErrorEnum.ERROR_500);
        }
        else {
            hygieneManagementMapper.updatebyuuid(hygieneManagement);
            return ResultUtils.success();
        }
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        hygieneManagementMapper.deleteall();
        return ResultUtils.success();
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        hygieneManagementMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        hygieneManagementMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
}
