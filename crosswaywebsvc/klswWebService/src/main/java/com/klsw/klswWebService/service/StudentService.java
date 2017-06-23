package com.klsw.klswWebService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klsw.apiCommon.api.mapper.TbCwkstudentMapper;
import com.klsw.apiCommon.api.model.TbCwkstudent;


@Service
public class StudentService extends _BaseService<TbCwkstudent> {
	@Autowired
	private TbCwkstudentMapper cwkstudentMapper;
	
	public int selectId(Integer cwkId) {
		return cwkstudentMapper.selectId(cwkId);
	}

	public int selectPianoGrade(Integer studentId) {
		return cwkstudentMapper.selectPianoGrade(studentId);
	}

	public TbCwkstudent selectByUserId(Integer cwkId){
		TbCwkstudent student = new TbCwkstudent();
		student.setCwkid(cwkId);
		
		if(cwkstudentMapper.selectOne(student) == null) {
			cwkstudentMapper.insert(student);
		}
		
		student = cwkstudentMapper.selectOne(student);
		return student;
	}
	
	public int insertByUserId(Integer userId) {
		TbCwkstudent tbCwkstudent = new TbCwkstudent();
		tbCwkstudent.setCwkid(userId);
		return cwkstudentMapper.insert(tbCwkstudent);
	}
}
