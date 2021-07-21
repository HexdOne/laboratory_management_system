package com.spring.laboratory.Mapper;

import com.spring.laboratory.Entity.ApplicationClassroom;

import java.util.List;

public interface ApplicationClassroomMapper {
    List<ApplicationClassroom> query();
    List<ApplicationClassroom> querybystudentid(String studentid);
    List<ApplicationClassroom> queryfuzzy(String studentName);
    List<ApplicationClassroom> querydoornumber();
    void update(ApplicationClassroom applicationClassroom);
    void insert(ApplicationClassroom applicationClassroom);
    void deletebyuuid(String uuid);
    void batchdeleteuuid(String[] uuid);
    void deleteall();
}
