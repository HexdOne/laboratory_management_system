package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.RepairEquipment;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.RepairEquipmentMapper;
import com.spring.laboratory.Service.RepairEquipmentService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RepairEquipmentServiceImpl implements RepairEquipmentService{
    @Autowired
    private RepairEquipmentMapper repairEquipmentMapper;
    //查询列表
    @Override
    public Result<RepairEquipment> query(int pageNum) throws MyException {
        if (repairEquipmentMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairEquipment> list = repairEquipmentMapper.query();
            PageInfo<RepairEquipment> pageInfo = new PageInfo<RepairEquipment>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询门牌号
    @Override
    public Result<Map> querydoornumber() throws MyException {
        return ResultUtils.success(repairEquipmentMapper.querydoornumber());
    }
    //查询设备名称
    @Override
    public Result<Map> queryequipmentname(String doorNumber) throws MyException {
        return ResultUtils.success(repairEquipmentMapper.queryequipmentname(doorNumber));
    }
    //查询bydoornumber
    @Override
    public Result<RepairEquipment> querybydoornumber(String uuid) throws MyException {
        if (repairEquipmentMapper.querybydoornumber(uuid).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(repairEquipmentMapper.querybydoornumber(uuid));
        }
    }
    //模糊查询
    @Override
    public Result<RepairEquipment> queryfuzzy(int pageNum,String doorNumber) throws MyException {
        if (repairEquipmentMapper.queryfuzzy(doorNumber).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,15);
            List<RepairEquipment> list = repairEquipmentMapper.queryfuzzy(doorNumber);
            PageInfo<RepairEquipment> pageInfo = new PageInfo<RepairEquipment>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //增加一条数据
    @Override
    public Result insert(RepairEquipment repairEquipment) throws MyException {
        if (repairEquipment.getDoorNumber()==null||repairEquipment.getEquipmentName()==null
                ||repairEquipment.getFaultPhenomenon()==null ||repairEquipment.getPhoneNumber()==null||repairEquipment.getStudentName()==null
                ||repairEquipment.getRepairStatus()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
//        else if (!repairEquipmentMapper.querybydoornumber(repairEquipment).isEmpty()){
//            throw new MyException(ErrorEnum.ERROR_402);
//        }
        else {
            repairEquipmentMapper.insert(repairEquipment);
            return ResultUtils.success();
        }
    }
    //通过门牌号修改数据
    @Override
    public Result updatebyuuid(RepairEquipment repairEquipment) throws MyException {
        repairEquipmentMapper.updatebyuuid(repairEquipment);
        return ResultUtils.success();
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        repairEquipmentMapper.deleteall();
        return ResultUtils.success();
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        repairEquipmentMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        repairEquipmentMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
}
