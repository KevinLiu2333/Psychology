package com.klsw.klswWebService.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.TbCwkbeanMapper;
import com.klsw.apiCommon.api.model.TbCwkbean;


@Service
public class TbCwkBeanService extends _BaseService<TbCwkbean>{
	
	@Autowired
	private TbCwkbeanMapper cwkbeanMapper;
	
	public List<TbCwkbean> selectByType(Integer tradeType,Integer cwkId) {
		return cwkbeanMapper.selectByType(tradeType,cwkId);
	}
}
