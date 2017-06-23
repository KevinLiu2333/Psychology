package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbCwkhomeworkMapper;
import com.klsw.apiCommon.api.model.TbCwkhomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: HomeworkService
 *
 * @author LiuKun
 * @Description: 上交作业有关
 * @date 2016年8月29日
 */
@Service
public class TbCwkHomeworkService extends _BaseService<TbCwkhomework> {

    @Autowired
    private TbCwkhomeworkMapper tbCwkhomeworkMapper;

    /**
     * @param @param  studentId
     * @param @return
     * @return List<TbCwkhomework>
     * @throws
     * @Description: 查询当日
     * @author LiuKun
     * @date 2016年8月26日
     */
    public List<TbCwkhomework> todayUpload(Integer studentId) {
        return tbCwkhomeworkMapper.todayUpload(studentId);
    }

    /**
     * 通过学生id查询该学生已提交过的所有作业
     * @param studentId
     * @return
     */
    public List<TbCwkhomework> studentHomework(Integer studentId) {
        return tbCwkhomeworkMapper.studentHomework(studentId);
    }

    public List<TbCwkhomework> teacherHomework(Integer teacherId) {
        return tbCwkhomeworkMapper.teacherHomework(teacherId);
    }

    public List<TbCwkhomework> uncommentHomework(Integer teacherId) {
        return tbCwkhomeworkMapper.uncommentHomework(teacherId);
    }

    public List<TbCwkhomework> commentHomework(Integer teacherId) {
        return tbCwkhomeworkMapper.commentHomework(teacherId);
    }

    public TbCwkhomework homewrokDetail(Integer homeworkId) {

        return tbCwkhomeworkMapper.homeworkDetail(homeworkId);
    }

    /**
     * 通过作业Id查询所有已批改记录
     *
     * @param homeworkId
     * @return
     */
    public List<TbCwkhomework> homeworkInfolist(Integer homeworkId) {
        return tbCwkhomeworkMapper.homeworkInfolist(homeworkId);
    }

    public Integer selectGradeCount(int homeworkId) {
        return tbCwkhomeworkMapper.selectGradeCount(homeworkId);
    }
    
    /**
     * 教师通过学生id查询该学生向他提交的所有作业
     * @param studentId
     * @param teacherId
     * @return
     */
    public List<TbCwkhomework> studentHomeworkList(Integer studentId,Integer teacherId,Integer status) {
    	return tbCwkhomeworkMapper.studentHomeworkList(studentId,teacherId,status);
    }
}



