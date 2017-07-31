package com.wondersgroup.cmc.utils.pagequery.impl;

import com.wondersgroup.cmc.utils.pagequery.AbstractDialect;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class MysqlDialect implements AbstractDialect{

	@Override
	public String createPagesql(int pageIndex, int pageSize, String querySql) {
		int _start = pageIndex * pageSize;
		int _end = pageSize;
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from  ( ").append(querySql)
				.append(") a limit ").append(_start).append(",").append(_end);
		return sql.toString();
	}

	@Override
	public String getConstants(String constants) {
		return String.format("select %s from dual", constants);
	}
	
	@Override
	public String getSystemDate() {
		return "now()";
	}

	@Override
	public String dateTochar(String daysql, int fmttype) {
		String to_char = "date_format(%s,'%s')";
		switch (fmttype) {
		case 1:
			return String.format(to_char, daysql,"%Y");
		case 2:
			return String.format(to_char, daysql,"%Y%m");
		case 3:
			return String.format(to_char, daysql,"%Y%m");
		case 4:
			return String.format(to_char, daysql,"%Y%m%d");
		case 5:
			return String.format(to_char, daysql,"%Y-%m-%d");
		case 6:
			return String.format(to_char, daysql,"%Y-%m-%d %T");
		default:
			return String.format(to_char, daysql,"%Y%m%d");
		}
	}

	@Override
	public <R> R forUpdate(String fromsql, String wheresql, Object rowid, Class<R> clazz) {
		String sql = "%s %s for update";
		return CommonJdbcDaoUtils.get(String.format(sql,fromsql,wheresql), clazz, rowid);
	}

}
