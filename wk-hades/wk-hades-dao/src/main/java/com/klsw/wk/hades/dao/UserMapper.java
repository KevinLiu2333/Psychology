package com.klsw.wk.hades.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klsw.wk.hades.domain.User;

@Mapper
public interface UserMapper extends BaseDao<User, Long> {
	
	/**
	 * 添加用户和角色对应关系
	 * @param userEntity
	 * @return
	 */
	public int insertUserRole(User userEntity);
	
	/**
	 * 更新用户和角色对应关系
	 * @param userEntity
	 * @return
	 */
	public int updateUserRole(User userEntity);
	
	/**
	 * 删除用户和角色对应关系
	 * @param userIds
	 * @return
	 */
	public int deleteBatchUserRole(List<Long> userIds);
	
	/**
	 * 添加用户个人资料信息
	 * @param userEntity
	 * @return
	 */
	public int insertUserInfo(User userEntity);
	
	/**
	 * 更新用户个人资料信息
	 * @param userEntity
	 * @return
	 */
	public int updateUserInfo(User userEntity);
	
	/**
	 * 查询所有可用用户
	 * @return
	 */
	public List<User>  queryAllUser();
}
