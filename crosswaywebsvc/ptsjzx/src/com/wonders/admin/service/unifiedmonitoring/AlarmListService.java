package com.wonders.admin.service.unifiedmonitoring;

import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.util.ResultRules;

/**
 * 报警列表的业务层接口.
 * 
 * @author Gray
 *
 */
@IocBean
public interface AlarmListService {
	
	//创建使用常量
	//跳转
	public static final int GOTO_PAGE = 1;
	//上一页
	public static final int PREVIOUS_PAGE = 0;
	//下一页
	public static final int NEXT_PAGE = -1;
	//第一页
	public static final int FIRST_PAGE = -2;
	//最后一页
	public static final int LAST_PAVE = -3;
	
	/**
	 * 通过给定的页面数值,从数据库中提取报警列表分页.
	 * 
	 * @param pageNum
	 *            页面数值.
	 * @return Result 规定的规则.
	 */
	public ResultRules getAlarmListByPage(Integer pageNum);
}
