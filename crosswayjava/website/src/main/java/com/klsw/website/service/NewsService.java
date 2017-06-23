package com.klsw.website.service; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.google.common.collect.Lists;
import com.klsw.common.mall.mapper.TMallNewsMapper;
import com.klsw.common.mall.model.TMallNews;
 
@Service
public class NewsService extends _BaseService<TMallNews> {
  @Autowired
  private TMallNewsMapper newsMapper;
	
     public List<TMallNews> selectByNews(String  title) throws Exception {
		
		List<TMallNews> newsList = Lists.newArrayList();
 		
	    newsList = newsMapper.selectByNews(title);
 	 
		return newsList;
	}
	
}
