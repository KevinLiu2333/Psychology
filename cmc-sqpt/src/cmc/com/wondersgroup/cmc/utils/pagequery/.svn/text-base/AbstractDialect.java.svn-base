package com.wondersgroup.cmc.utils.pagequery;

public interface AbstractDialect {
	/**
	 * 创建分页sql
	 * @param pageIndex
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public String createPagesql(int pageIndex,int pageSize,String querySql);
	

	/**
	 * 获得常量
	 * @return
	 */
	public String getConstants(String constants);
	
	/**
	 * 获得系统时间
	 * @return
	 */
	public String getSystemDate();
	
	/**
	 * 时间转字符串
	 * @param daysql
	 * @param fmt(支持格式 1: yyyy  2 yyyyMM 3 yyyy-MM 4 yyyyMMdd 5 yyyy-MM-dd 6 yyyy-MM-dd hh24:mi:ss) 默认为4
	 * @return
	 */
	public String dateTochar(String daysql,int fmttype);

	public <R> R forUpdate(String fromsql,String wheresql,Object rowid,Class<R> clazz);
}
