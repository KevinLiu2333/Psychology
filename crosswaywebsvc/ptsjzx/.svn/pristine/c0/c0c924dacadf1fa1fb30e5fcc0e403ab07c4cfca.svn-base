package com.wonders.admin.dao.unifiedmonitoring;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 针对报警列表操作的接口.
 * 
 * @author Gray
 *
 */
@IocBean
public interface AlarmListDao {

	/**
	 * 通过页面值,和提取数量,从数据库中提取报警列表分页.
	 * 
	 * @param pageNum
	 *            页面值.
	 * @param quantity
	 *            提取数量.
	 * @return List<List> 查询到的结果.
	 */
	@SuppressWarnings("rawtypes")
	public List<List> getPage(int pageNum, int quantity);
	
	/**
	 * 用于查询数据总量.
	 * @return Integer 数据总量.
	 */
	public Integer getPageNumber();
}
