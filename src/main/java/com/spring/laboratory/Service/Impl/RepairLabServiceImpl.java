package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.RepairLab;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.RepairLabMapper;
import com.spring.laboratory.Service.RepairLabService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairLabServiceImpl implements RepairLabService{
    @Autowired
    private RepairLabMapper repairLabMapper;
    //查询列表
    @Override
    public Result<RepairLab> query(int pageNum) throws MyException {
        if (repairLabMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairLab> list = repairLabMapper.query();
            PageInfo<RepairLab> pageInfo = new PageInfo<RepairLab>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询门牌号
    @Override
    public Result<Map> querydoornumber() throws MyException {
        return ResultUtils.success(repairLabMapper.querydoornumber());
    }
    //查询bydoornumber
    @Override
    public Result<RepairLab> querybydoornumber(String uuid) throws MyException {
        if (repairLabMapper.querybydoornumber(uuid).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(repairLabMapper.querybydoornumber(uuid));
        }
    }
    //模糊查询
    @Override
    public Result<RepairLab> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (repairLabMapper.queryfuzzy(doorNumber).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairLab> list = repairLabMapper.query();
            PageInfo<RepairLab> pageInfo = new PageInfo<RepairLab>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加一条数据
    @Override
    public Result insert(RepairLab repairLab) throws MyException {
        if (repairLab.getDoorNumber()==null||repairLab.getFaultPhenomenon()==null||repairLab.getPhoneNumber()==null
                ||repairLab.getRepairItem()==null||repairLab.getRepairStatus()==null||repairLab.getStudentName()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
        else {
            repairLabMapper.insert(repairLab);
            return ResultUtils.success();
        }
    }
    //通过门牌号修改数据
    @Override
    public Result updatebyuuid(RepairLab repairLab) throws MyException {
        if (repairLab.getDoorNumber()==null||repairLab.getFaultPhenomenon()==null||repairLab.getPhoneNumber()==null
                ||repairLab.getRepairItem()==null||repairLab.getRepairStatus()==null||repairLab.getStudentName()==null){
            throw new MyException(ErrorEnum.ERROR_500);
        }
        else {
            repairLabMapper.updatebyuuid(repairLab);
            return ResultUtils.success();
        }
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        repairLabMapper.deleteall();
        return ResultUtils.success();
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        repairLabMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        repairLabMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
}
