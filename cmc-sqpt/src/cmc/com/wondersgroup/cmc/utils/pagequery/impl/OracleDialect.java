package com.wondersgroup.cmc.utils.pagequery.impl;

import com.wondersgroup.cmc.utils.pagequery.AbstractDialect;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class OracleDialect implements AbstractDialect{

	@Override
	public String createPagesql(int pageIndex, int pageSize, String querySql) {
		int _start = pageIndex * pageSize + 1;
		int _end = pageSize * ( pageIndex + 1 );
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select temp2.* from (")
			.append(" select rownum row_num, temp1.* from ( ")
			.append(querySql)
		    .append(") temp1 where rownum <= " +  _end)	
		    .append(") temp2 where temp2.row_num >= " + _start);
		return sql.toString();
	}

	@Override
	public String getConstants(String constants) {
		return String.format("select %s from dual", constants);
	}
	
	@Override
	public String getSystemDate() {
		return "sysdate";
	}

	@Override
	public String dateTochar(String daysql, int fmttype) {
		String to_char = "to_char(%s,'%s')";
		switch (fmttype) {
		case 1:
			return String.format(to_char, daysql,"yyyy");
		case 2:
			return String.format(to_char, daysql,"yyyyMM");
		case 3:
			return String.format(to_char, daysql,"yyyy-MM");
		case 4:
			return String.format(to_char, daysql,"yyyyMMdd");
		case 5:
			return String.format(to_char, daysql,"yyyy-MM-dd");
		case 6:
			return String.format(to_char, daysql,"yyyy-MM-dd hh24:mi:ss");
		default:
			return String.format(to_char, daysql,"yyyyMMdd");
		}
	}

	@Override
	public <R> R forUpdate(String fromsql, String wheresql, Object rowid, Class<R> clazz) {
		String sql = "%s %s for update";
		return CommonJdbcDaoUtils.get(String.format(sql,fromsql,wheresql), clazz, rowid);
	}

}
