package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.Suggestion; 

public interface SuggestionMapper extends MyMapper<Suggestion> {
	
	List<Suggestion> selectSuggestion();
	 
	
}