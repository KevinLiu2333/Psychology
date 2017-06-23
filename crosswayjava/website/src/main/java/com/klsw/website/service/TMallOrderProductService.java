package com.klsw.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.common.mall.mapper.TMallOrderProductMapper;
import com.klsw.common.mall.model.TMallOrderProduct;

@Service
public class TMallOrderProductService extends _BaseService<TMallOrderProduct> {
	@Autowired
	private TMallOrderProductMapper tMallOrderProductMapper;
	
}
