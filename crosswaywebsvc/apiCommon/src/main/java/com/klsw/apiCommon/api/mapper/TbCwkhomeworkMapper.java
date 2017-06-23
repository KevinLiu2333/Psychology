package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkhomework;

import java.util.List;

public interface TbCwkhomeworkMapper extends MyMapper<TbCwkhomework> {

    List<TbCwkhomework> todayUpload(Integer studentId);

    /**
     * 通过学生id查询该学生已提交过的所有作业
     * @param studentId
     * @return
     */
    List<TbCwkhomework> studentHomework(Integer studentId);

    List<TbCwkhomework> teacherHomework(Integer teacherId);

    List<TbCwkhomework> commentHomework(Integer teacherId);

    List<TbCwkhomework> uncommentHomework(Integer teacherId);

    TbCwkhomework homeworkDetail(Integer homeworkId);

    List<TbCwkhomework> homeworkInfolist(Integer homeworkId);

    Integer selectGradeCount(Integer homeworkId);
    
    /**
     * 教师通过学生id查询该学生向他提交的所有作业
     * @param studentId
     * @param teacherId
     * @return
     */
    List<TbCwkhomework> studentHomeworkList(Integer studentId,Integer teacherId,Integer status);
}