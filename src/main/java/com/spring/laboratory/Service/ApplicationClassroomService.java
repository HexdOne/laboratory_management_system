package com.spring.laboratory.Service;

import com.spring.laboratory.Entity.ApplicationClassroom;
import com.spring.laboratory.Entity.Result;

import java.util.List;
import java.util.Map;

public interface ApplicationClassroomService {
    Result<ApplicationClassroom> query(int pageNum);
    Result<ApplicationClassroom> querybystudentid(String studentid);
    Result<ApplicationClassroom> queryfuzzy(int pageNum,String studentName);
    Result<ApplicationClassroom> querydoornumber();
    Result insert(ApplicationClassroom applicationClassroom);
    Result update(ApplicationClassroom applicationClassroom);
    Result deletebyuuid(String uuid);
    Result batchdeleteuuid(String[] uuid);
    Result deleteall();
}
