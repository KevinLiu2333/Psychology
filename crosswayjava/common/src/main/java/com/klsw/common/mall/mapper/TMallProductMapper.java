package com.klsw.common.mall.mapper;

import java.util.List;

import com.klsw.common.mall.MyMapper; 
import com.klsw.common.mall.model.TMallProduct;

public interface TMallProductMapper extends MyMapper<TMallProduct> {
	
	public List<TMallProduct> selectByProduct(String name) throws Exception; 
}