package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkstudent;

public interface TbCwkstudentMapper extends MyMapper<TbCwkstudent> {
	Integer selectId(Integer cwkId);
	
	Integer selectPianoGrade(Integer cwkId);
}