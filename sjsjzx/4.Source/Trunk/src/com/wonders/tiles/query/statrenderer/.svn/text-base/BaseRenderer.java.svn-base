package com.wonders.tiles.query.statrenderer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BaseRenderer {

	 public static final String WHERE_SQL = "whereSql";
	/**
	 * 
	 * @return String[0]where条件 如果无条件 显示为""
               String[1]字段ID
               String[2]界面显示html代码，如果为NULL，则界面显示的效果和未设置render的效果一致
	 */
	public Map genWhereClause(HttpServletRequest request);
}
