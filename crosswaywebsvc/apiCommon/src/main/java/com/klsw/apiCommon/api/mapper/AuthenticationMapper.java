package com.klsw.apiCommon.api.mapper;

import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.Authentication; 

public interface AuthenticationMapper extends MyMapper<Authentication> {
	
	List<Authentication> selectAuthentication();
	 
	
}