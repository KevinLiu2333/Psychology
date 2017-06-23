package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.AuthenticationMapper;
import com.klsw.apiCommon.api.model.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthenticationService extends _BaseService<Authentication> {
	@Autowired
	private AuthenticationMapper authenticationMapper;

	public 	List<Authentication> selectAuthentication() {
		return authenticationMapper.selectAuthentication();
	}
	 
}
