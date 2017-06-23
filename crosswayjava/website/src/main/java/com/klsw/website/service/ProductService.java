package com.klsw.website.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/*import com.klsw.common.mall.mapper.TMallProductDescMapper;*/
import com.klsw.common.mall.mapper.TMallProductMapper;
import com.klsw.common.mall.model.TMallProduct;

import tk.mybatis.mapper.entity.Example;

@Service
public class ProductService extends _BaseService<TMallProduct> {
	
/*	@Autowired
	private TMallProductDescMapper descMapper;*/
	
/*	@Autowired
	private AttributeValueService attributeValueService;*/
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private TMallProductMapper productMapper;
	
	public TMallProduct selectProductDetailBySerial(String serial) throws Exception {
		
		TMallProduct product = new TMallProduct();
		product.setSerial(serial);
		product = super.selectOne(product);
		if(product != null) {
			/** 商品描述 */
			/*TMallProductDesc desc = new TMallProductDesc();
			desc.setProductid(product.getId());
			desc = descMapper.selectOne(desc);
			if(desc != null) product.setDesc(desc.getDescription());*/
			
			/** 商品属性 */
			/*product.setAttributeValues(attributeValueService.selectForMapGroupByTypeName(product.getId()));*/
			
			/** 商品图片 */
			product.setImageList(productImageService.selectByProductId(product.getId()));
		}
		
		return product;
	}
	
	/**
	 * 查询状态正常的商品
	 * @return
	 * @throws Exception
	 */
	public List<TMallProduct> selectCanSaleProductList() throws Exception {
		Example example = new Example(TMallProduct.class);
		example.createCriteria().andEqualTo("status", 1);
		return super.selectByExample(example);
	}
	
	/**
	 * 查询同系列商品
	 * @param seriesId
	 * @return
	 * @throws Exception
	 */
	public List<TMallProduct> selectSameSeriesProduct(Integer seriesId) throws Exception {
		Example example = new Example(TMallProduct.class);
		example.createCriteria().andEqualTo("seriesid", seriesId);
		return super.selectByExample(example);
	}
	
	public List<TMallProduct> selectBySerialList(List<String> serialList) throws Exception {
		Example example = new Example(TMallProduct.class);
		example.createCriteria().andIn("serial", serialList);
		return super.selectByExample(example);
	}
	
	public Map<String, TMallProduct> selectForMapBySerialList(List<String> serialList) throws Exception {
		
		Map<String, TMallProduct> resultMap = Maps.newHashMap();
		
		Example example = new Example(TMallProduct.class);
		example.createCriteria().andIn("serial", serialList);
		List<TMallProduct> productList = super.selectByExample(example);
		if(productList != null) {
			for(TMallProduct product : productList) {
				resultMap.put(product.getSerial(), product);
			}
		}	
		return resultMap;
	}
	
	public TMallProduct selectBySerial(String serial) throws Exception {
		TMallProduct product = new TMallProduct();
		product.setSerial(serial);
		return super.selectOne(product);
		
	}
	
	public List<TMallProduct> selectBySerialId(int serialId) throws Exception{
		
		TMallProduct product = new TMallProduct();
		product.setSeriesid(serialId);
		return super.select(product);
	}
	 
	//模糊查询
    public List<TMallProduct> selectByProduct(String  name) throws Exception {
		
		List<TMallProduct> productList = Lists.newArrayList();
		
		productList = productMapper.selectByProduct(name);
	 
		return productList;
	}
}
