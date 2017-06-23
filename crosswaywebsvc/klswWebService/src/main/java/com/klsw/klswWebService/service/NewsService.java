package com.klsw.klswWebService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.TbCwknewsMapper;
import com.klsw.apiCommon.api.model.TbCwknews;

@Service
public class NewsService extends _BaseService<TbCwknews>{
	
	@Autowired
	private TbCwknewsMapper tbCwknewsMapper;
	/**
	 * 跟据用户名和用户类型筛选收藏新闻
	 * 
	 * @param name
	 * 			用户名
	 * @param type
	 * 			用户类型
	 * @return
	 * @throws Exception
	 */
	public List<TbCwknews> selectFavoriteNewsByTbCwkId(String name,String type) throws Exception{
		return tbCwknewsMapper.selectFavoriteNewsByTbCwkId(name,type);
	}
	/**
	 * 筛选所有的新闻，包括新闻发布者的名字
	 * 
	 */
	public List<TbCwknews> selectNewsOrderByAddtime(){
		return tbCwknewsMapper.selectNewsOrderByAddtime();
		
	}
	public List<TbCwknews> selectNewsOrderByPraise(){
		return tbCwknewsMapper.selectNewsOrderByPraise();
		
	}
}








