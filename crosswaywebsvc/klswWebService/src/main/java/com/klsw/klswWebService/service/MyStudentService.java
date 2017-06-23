package com.klsw.klswWebService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.StudentMapper;
import com.klsw.apiCommon.api.model.Student;

@Service
public class MyStudentService extends _BaseService<Student> {
	
	@Autowired
	private StudentMapper studentMapper;

	public List<Student> selectStudents() {
		return studentMapper.selectStudents();
	}
	
	public Student selectStudentInfo(Integer studentId) {
		return studentMapper.selectStudentInfo(studentId);
	}
}
