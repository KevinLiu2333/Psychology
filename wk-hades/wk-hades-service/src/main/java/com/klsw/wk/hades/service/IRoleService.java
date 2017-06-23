package com.klsw.wk.hades.service;

import java.util.List;
import java.util.Map;

import com.klsw.wk.hades.domain.Role;

public interface IRoleService {

	List<Role> queryListByPage(Map<String, Object> parameter);

	Role findByName(String name);
	
	int insert(Role roleEntity);
	
	Role findById(Long id);

	int update(Role roleEntity);
    
    int deleteBatchById(List<Long> roleIds);
    
    boolean addRolePerm(int id, List<Integer> ids) throws Exception ;

}