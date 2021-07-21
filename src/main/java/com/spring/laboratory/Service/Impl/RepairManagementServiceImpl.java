package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.EquipmentManagement;
import com.spring.laboratory.Entity.RepairManagement;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.RepairManagementMapper;
import com.spring.laboratory.Service.RepairManagementService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairManagementServiceImpl implements RepairManagementService{
    @Autowired
    private RepairManagementMapper repairManagementMapper;
    //查询列表
    @Override
    public Result<RepairManagement> query(int pageNum) throws MyException {
        if (repairManagementMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairManagement> list = repairManagementMapper.query();
            PageInfo<RepairManagement> pageInfo=new PageInfo<RepairManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询bydoornumber
    @Override
    public Result<RepairManagement> querybydoornumber(String uuid) throws MyException {
        if (repairManagementMapper.querybydoornumber(uuid).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(repairManagementMapper.querybydoornumber(uuid));
        }
    }
    //查询设备id
    @Override
    public Result<Map> queryequipmentid(EquipmentManagement equipmentManagement) throws MyException {
        return ResultUtils.success(repairManagementMapper.queryequipmentid(equipmentManagement));
    }
    //模糊查询
    @Override
    public Result<RepairManagement> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (repairManagementMapper.queryfuzzy(doorNumber).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairManagement> list = repairManagementMapper.queryfuzzy(doorNumber);
            PageInfo<RepairManagement> pageInfo=new PageInfo<RepairManagement>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加
    @Override
    public Result insert(RepairManagement repairManagement) throws MyException {
        if (repairManagement.getDoorNumber()==null||repairManagement.getEquipmentName()==null||repairManagement.getRepairMethod()==null
                ||repairManagement.getRepairPerson()==null||repairManagement.getRepairResult()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
        else {
            repairManagementMapper.insert(repairManagement);
            return ResultUtils.success();
        }
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        repairManagementMapper.deleteall();
        return ResultUtils.success();
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        repairManagementMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        repairManagementMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
}
