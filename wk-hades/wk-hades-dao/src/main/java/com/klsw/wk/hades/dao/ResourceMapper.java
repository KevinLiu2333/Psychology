package com.klsw.wk.hades.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.klsw.wk.hades.domain.Resource;

@Mapper
public interface ResourceMapper extends BaseDao<Resource, Long> {
	
	/**
	 * 自定义方法
	 * 获取用户ID对应的资源信息
	 * @param userId
	 * @return
	 */
	List<Resource> findResourcesByUserId(@Param(value = "userId") int userId);
	
	/**
	 * 查询权限树集合
	 * @param parameter 参数中必须包含roleId,其他参数可参考mapping文件
	 * @return
	 */
	List<Resource> queryResourceList(Map<String, Object> parameter);
}
