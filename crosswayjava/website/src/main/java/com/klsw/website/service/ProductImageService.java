package com.klsw.website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klsw.common.mall.model.TMallProductImage;

import tk.mybatis.mapper.entity.Example;

@Service
public class ProductImageService extends _BaseService<TMallProductImage> {
	
	public String selectForStringByProductId(Integer productId) throws Exception {
		StringBuilder sb = new StringBuilder();
		TMallProductImage image = new TMallProductImage();
		image.setProductid(productId);
		
		List<TMallProductImage> imageList = select(image);
		
		if(imageList != null && imageList.size() > 0) {
			for(TMallProductImage entity : imageList) {
				sb.append(entity.getImgurl()).append(",");
			}
			
			return sb.substring(0, sb.length() - 1);
		}
		
		return sb.toString();
	}
	
	public List<TMallProductImage> selectByProductId(Integer productId) throws Exception {
		Example example = new Example(TMallProductImage.class);
		example.createCriteria().andEqualTo("productid", productId);
		return super.selectByExample(example);
	}

}
