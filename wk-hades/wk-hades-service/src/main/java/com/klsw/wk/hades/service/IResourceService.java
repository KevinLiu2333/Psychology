package com.klsw.wk.hades.service;

import java.util.List;
import java.util.Map;

import com.klsw.wk.hades.domain.Resource;

public interface IResourceService {

	/**
	 * 自定义方法
	 * 获取用户ID对应的资源信息
	 * @param userId
	 * @return
	 */
	public List<Resource> findResourcesByUserId(int userId);

	public List<Resource> queryListByPage(Map<String, Object> parameter);
	
	public Resource findByName(String name);
	
	public int insert(Resource resourceEntity);
	
	public Resource findById(Long id);

	public int update(Resource resourceEntity);
    
    public int deleteBatchById(List<Long> roleIds);
    
    public List<Resource> queryResourceList(Map<String, Object> parameter);
}
