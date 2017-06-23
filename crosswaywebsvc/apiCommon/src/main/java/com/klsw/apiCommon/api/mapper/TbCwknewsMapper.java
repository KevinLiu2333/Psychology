package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwknews;

public interface TbCwknewsMapper extends MyMapper<TbCwknews> {
	/**
	 * 
	 * @param name
	 * 			用户名
	 * @return
	 * @throws Exception
	 */
	public List<TbCwknews> selectFavoriteNewsByTbCwkId(String username,String type) throws Exception;
	
	public List<TbCwknews> selectNewsOrderByPraise();
	
	public List<TbCwknews> selectNewsOrderByAddtime();
	
}