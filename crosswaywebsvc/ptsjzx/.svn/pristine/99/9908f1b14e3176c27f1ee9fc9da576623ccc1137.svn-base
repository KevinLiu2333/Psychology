package com.wonders.admin.dao.unifiedmonitoring;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.entity.unifiedmonitoring.ThresholdSetBean;

/**
 * 针对数据库阀值设置表操作的接口.
 * 
 * @author Gray
 * 
 */
@IocBean
public interface ThresholdSetDao {

	/**
	 * 将数据库中阀值表的所有内容提取出来,其中阀值Id被去除改为阀值名称.
	 * 
	 * @return List<List<Object>> 所有的值
	 */
	@SuppressWarnings("rawtypes")
	public List<List> getNeedAll();

	/**
	 * 将已经设置好的阀值更新进数据库.
	 * 
	 * @param tsb
	 *            已经设置好的阀值行.
	 * @return int 更新行数.
	 */
	public int update(ThresholdSetBean tsb);
}
