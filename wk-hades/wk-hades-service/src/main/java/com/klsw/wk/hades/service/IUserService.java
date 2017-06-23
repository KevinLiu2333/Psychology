package com.klsw.wk.hades.service;

import java.util.List;
import java.util.Map;

import com.klsw.wk.hades.domain.User;
import com.klsw.wk.hades.exception.ServiceException;

public interface IUserService {

	public List<User> queryListByPage(Map<String, Object> parameter);

	public User findByName(String accountName);
	
	public int insert(User userEntity, String password);
	
	public User findById(Long id);

	public int update(User userEntity);
	
	public int updateOnly(User userEntity, String password) throws ServiceException;
    
    public int deleteBatchById(List<Long> userIds);
    
    public List<User> listUser();
    
}