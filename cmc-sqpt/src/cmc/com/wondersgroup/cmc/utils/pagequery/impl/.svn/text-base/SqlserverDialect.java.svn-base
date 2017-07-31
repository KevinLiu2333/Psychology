package com.wondersgroup.cmc.utils.pagequery.impl;

import com.wondersgroup.cmc.utils.pagequery.AbstractDialect;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class SqlserverDialect implements AbstractDialect{

	@Override
	public String createPagesql(int pageIndex, int pageSize, String querySql) {
		int _start = pageIndex * pageSize + 1;
		int _end = pageSize * ( pageIndex + 1 );
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select * from(select row_number()over(order by __tc__)__rn__,* from(")
			.append(" select top "+_end+" __tc__=1, ")
			.append(querySql.trim().substring(6))
		    .append(") t) tt where tt.__rn__ >= " + _start);
		return sql.toString();
	}

	@Override
	public String getConstants(String constants) {
		return String.format("select %s", constants);
	}
	
	@Override
	public String getSystemDate() {
		return "getdate()";
	}

	@Override
	public String dateTochar(String daysql, int fmttype) {
		String to_char = "convert(varchar(%d),%s,%d)";
		switch (fmttype) {
		case 1:
			return String.format(to_char, 4l , daysql , 112l);
		case 2:
			return String.format(to_char, 6l , daysql , 112l);
		case 3:
			return String.format(to_char, 7l , daysql , 23l);
		case 4:
			return String.format(to_char, 8l , daysql , 112l);
		case 5:
			return String.format(to_char, 10l , daysql , 23l);
		case 6:
			return String.format(to_char, 19l , daysql , 20l);
		default:
			return String.format(to_char, 8l , daysql , 112l);
		}
	}

	@Override
	public <R> R forUpdate(String fromsql, String wheresql, Object rowid,Class<R> clazz) {
		String sql = "%s with(rowlock,xlock) %s";
		return CommonJdbcDaoUtils.get(String.format(sql,fromsql,wheresql), clazz, rowid);
	}
}
