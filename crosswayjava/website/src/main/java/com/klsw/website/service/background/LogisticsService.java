package com.klsw.website.service.background;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.common.mall.mapper.TMallLogisticsMapper;
import com.klsw.common.mall.model.TMallLogistics;
import com.klsw.website.service._BaseService; 
@Service
public class LogisticsService  extends _BaseService<TMallLogistics> {

	@Autowired
	private TMallLogisticsMapper tMallLogisticsMapper;
}
