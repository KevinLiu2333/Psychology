package com.klsw.klswWebService.service;
 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.SuggestionMapper; 
import com.klsw.apiCommon.api.model.Suggestion; 
@Service
public class SuggestionService extends _BaseService<Suggestion> {
	@Autowired
	private SuggestionMapper suggestionMapper;
	
	public 	List<Suggestion> selectSuggestion() {
		return suggestionMapper.selectSuggestion();
	}
	 
}
