package com.klsw.website.service.background;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.klsw.common.mall.mapper.TMallNewsMapper;
import com.klsw.common.mall.model.TMallNews;
import com.klsw.website.service._BaseService;
 

public class Bg_NewsService extends _BaseService<TMallNews> {
	@Autowired
	private TMallNewsMapper newsMapper;
	
	public List<TMallNews> selectByNews(String title) {
		
		List<TMallNews> newsList = Lists.newArrayList();
		try {
			newsList = newsMapper.selectByNews(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newsList;
		
	}
}
