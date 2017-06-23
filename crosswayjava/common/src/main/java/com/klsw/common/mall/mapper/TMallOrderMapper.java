package com.klsw.common.mall.mapper;

import java.util.List;
import java.util.Map;

import com.klsw.common.mall.MyMapper;
import com.klsw.common.mall.model.TMallOrder;

public interface TMallOrderMapper extends MyMapper<TMallOrder> {
	
	public List<Map<String, Object>> selectByUserId(Integer userId) throws Exception; 
}