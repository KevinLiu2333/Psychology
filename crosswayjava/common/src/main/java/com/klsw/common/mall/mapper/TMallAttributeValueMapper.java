package com.klsw.common.mall.mapper;

import java.util.List;

import com.klsw.common.mall.MyMapper;
import com.klsw.common.mall.model.TMallAttributeValue;

public interface TMallAttributeValueMapper extends MyMapper<TMallAttributeValue> {
	
	public List<TMallAttributeValue> selectAttributeValuesByProductId(Integer productId) throws Exception;
}