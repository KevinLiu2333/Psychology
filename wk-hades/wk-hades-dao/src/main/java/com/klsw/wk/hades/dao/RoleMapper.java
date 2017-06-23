package com.klsw.wk.hades.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.klsw.wk.hades.domain.Role;

@Mapper
public interface RoleMapper extends BaseDao<Role, Long> {
	
	/**
	 * 查询该角色是否有权限信息
	 * @param roleId	角色id 
	 * 使用@Param注解主要是设置mapping中可以使用参数名进行取值
	 * @return
	 */
	public int findRoleResourceById(@Param(value = "roleId") int roleId);
	
	/**
	 * 删除角色的权限信息
	 * @param roleId	角色id 
	 * 使用@Param注解主要是设置mapping中可以使用参数名进行取值
	 * @return
	 */
	public int deleteRoleResource(@Param(value = "roleId") int roleId);
	
	/**
	 * 添加角色和权限映射信息
	 * @param parameter
	 * @return
	 */
	public int addRoleResource(Map<String, Object> parameter);
}
