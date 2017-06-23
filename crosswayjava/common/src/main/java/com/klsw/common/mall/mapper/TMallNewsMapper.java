package com.klsw.common.mall.mapper;

import java.util.List;

import com.klsw.common.mall.MyMapper;
import com.klsw.common.mall.model.TMallNews;

public interface TMallNewsMapper extends MyMapper<TMallNews> {
	
	public List<TMallNews> selectByNews(String title) throws Exception; 
}