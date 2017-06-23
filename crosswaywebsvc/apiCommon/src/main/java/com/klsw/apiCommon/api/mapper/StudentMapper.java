package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.Student;


public interface StudentMapper extends MyMapper<Student> {
	
	List<Student> selectStudents();
	
	Student selectStudentInfo(Integer studentId);
	
}