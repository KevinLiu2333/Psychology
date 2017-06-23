package com.klsw.klswWebService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.TeacherMapper;
import com.klsw.apiCommon.api.model.Teacher;

@Service
public class MyTeacherService extends _BaseService<Teacher> {

	@Autowired
	private TeacherMapper teacherMapper;
	
	public List<Teacher> selectTeachers() {
		return teacherMapper.selectTeachers();
	}
	
	public Teacher selectTeacherInfo(Integer teacherId) {
		return teacherMapper.selectTeacherInfo(teacherId);
	}
}
