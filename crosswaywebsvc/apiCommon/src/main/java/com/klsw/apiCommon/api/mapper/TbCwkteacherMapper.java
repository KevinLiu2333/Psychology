package com.klsw.apiCommon.api.mapper;

import java.util.HashMap;
import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkteacher;
import com.klsw.apiCommon.api.model.WKTeacher;

public interface TbCwkteacherMapper extends MyMapper<TbCwkteacher> {
	
	int selectId(Integer cwkId);
	
	HashMap<String, Object> selectTeacherInfo(Integer teacherId);
	
	List<WKTeacher> selectTeacherList();
}