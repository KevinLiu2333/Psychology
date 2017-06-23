package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkbean;

public interface TbCwkbeanMapper extends MyMapper<TbCwkbean> {
	
	List<TbCwkbean> selectByType(Integer tradeType,Integer cwkId);
}