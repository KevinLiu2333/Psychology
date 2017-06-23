package com.klsw.website.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.klsw.common.mall.mapper.TMallAttributeValueMapper;
import com.klsw.common.mall.model.TMallAttributeValue;

@Service
public class AttributeValueService extends _BaseService<TMallAttributeValue> {
	
	@Autowired
	private TMallAttributeValueMapper attributeValueMapper;
	
	/**
	 * 查询商品属性值，通过类型分组
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map<String, String>> selectForMapGroupByTypeName(Integer productId) throws Exception {
		Map<String, Map<String, String>> resultMap = Maps.newHashMap();
		List<TMallAttributeValue> attributeList = attributeValueMapper.selectAttributeValuesByProductId(productId);
		if(attributeList != null) {
			for(TMallAttributeValue value : attributeList) {
				if(resultMap.get(value.getAttributeTypeName()) == null) resultMap.put(value.getAttributeTypeName(), new HashMap<String, String>());
				resultMap.get(value.getAttributeTypeName()).put(value.getAttributeName(), value.getAttributevalue());
			}
		}
		
		return resultMap;
	}
	
}
