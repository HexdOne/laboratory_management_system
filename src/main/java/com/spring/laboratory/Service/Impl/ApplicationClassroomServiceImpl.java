package com.spring.laboratory.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.laboratory.Entity.ApplicationClassroom;
import com.spring.laboratory.Entity.Result;
import com.spring.laboratory.Exception.ErrorEnum;
import com.spring.laboratory.Exception.MyException;
import com.spring.laboratory.Mapper.ApplicationClassroomMapper;
import com.spring.laboratory.Service.ApplicationClassroomService;
import com.spring.laboratory.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationClassroomServiceImpl implements ApplicationClassroomService{
    @Autowired
    private ApplicationClassroomMapper applicationClassroomMapper;
    //查询列表
    @Override
    public Result<ApplicationClassroom> query(int pageNum) throws MyException {
        if (applicationClassroomMapper.query().isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,8);
            List<ApplicationClassroom> list = applicationClassroomMapper.query();
            PageInfo<ApplicationClassroom> pageInfo=new PageInfo<ApplicationClassroom>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询bydoornumber
    @Override
    public Result<ApplicationClassroom> querybystudentid(String studentid) throws MyException {
        if (applicationClassroomMapper.querybystudentid(studentid).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            return ResultUtils.success(applicationClassroomMapper.querybystudentid(studentid));
        }
    }
    //模糊查询
    @Override
    public Result<ApplicationClassroom> queryfuzzy(int pageNum,String studentName) throws MyException {
        if (applicationClassroomMapper.queryfuzzy(studentName).isEmpty()){
            throw new MyException(ErrorEnum.ERROR_300);
        }
        else {
            PageHelper.startPage(pageNum,8);
            List<ApplicationClassroom> list = applicationClassroomMapper.queryfuzzy(studentName);
            PageInfo<ApplicationClassroom> pageInfo=new PageInfo<ApplicationClassroom>(list);
            return ResultUtils.success(pageInfo);
        }
    }
    //查询实验室门牌号
    @Override
    public Result<ApplicationClassroom> querydoornumber() throws MyException {
        return ResultUtils.success(applicationClassroomMapper.querydoornumber());
    }
    //增加一条数据
    @Override
    public Result insert(ApplicationClassroom a) throws MyException {
        if (a.getStudentName()==null||a.getStudentId().toString().length()!=10||a.getUseDemand()==null||a.getUseReason()==null||a.getRemark()==null
                ||a.getApplicationStatus()==null){
            throw new MyException(ErrorEnum.ERROR_400);
        }
        applicationClassroomMapper.insert(a);
        return ResultUtils.success();
    }
    //修改bystudentid
    @Override
    public Result update(ApplicationClassroom applicationClassroom) throws MyException {
        if (applicationClassroom.getApplicationStatus()==null){
            throw new MyException(ErrorEnum.ERROR_500);
        }
        applicationClassroomMapper.update(applicationClassroom);
        return ResultUtils.success();
    }
    //删除所有数据
    @Override
    public Result deleteall() throws MyException {
        applicationClassroomMapper.deleteall();
        return ResultUtils.success();
    }
    //删除一条数据
    @Override
    public Result deletebyuuid(String uuid) throws MyException {
        applicationClassroomMapper.deletebyuuid(uuid);
        return ResultUtils.success();
    }
    //批量删除
    @Override
    public Result batchdeleteuuid(String[] uuid) throws MyException {
        applicationClassroomMapper.batchdeleteuuid(uuid);
        return ResultUtils.success();
    }
}
