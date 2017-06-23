package com.klsw.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.common.mall.mapper.TMallOrderAddressMapper; 
import com.klsw.common.mall.model.TMallOrderAddress; 

@Service
public class TMallOrderAddressService extends _BaseService<TMallOrderAddress> {

	@Autowired
	private TMallOrderAddressMapper tMallOrderAddressMapper;
}
