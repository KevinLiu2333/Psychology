package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.Teacher;


public interface TeacherMapper extends MyMapper<Teacher> {
	
	List<Teacher> selectTeachers();
	
	Teacher selectTeacherInfo(Integer teacherId);
	
}