package com.wondersgroup.cmc.utils.pagequery;

import org.hibernate.dialect.Dialect;

import com.wondersgroup.cmc.utils.pagequery.impl.MysqlDialect;
import com.wondersgroup.cmc.utils.pagequery.impl.OracleDialect;
import com.wondersgroup.cmc.utils.pagequery.impl.SqlserverDialect;
import com.wondersgroup.wssip.commons.dao.CommonJdbcDaoUtils;

public class DialectFactory {
	protected static AbstractDialect abstractDialect;
	public static AbstractDialect getDialectInstance(String dialect){
		if(dialect.contains("MYSQL")) {
			abstractDialect = new MysqlDialect();
		} else if(dialect.contains("SQLSERVER")) {
			abstractDialect = new SqlserverDialect();
		} else {
			abstractDialect = new OracleDialect();
		}
		return abstractDialect;
	}
	
	public static AbstractDialect getDialectInstance(Dialect dialect){
		String dialectname = dialect.getClass().getSimpleName().toUpperCase();
		return getDialectInstance(dialectname);
	}
	
	public static AbstractDialect getDefaultDialectInstance(){
		if(abstractDialect == null){
			return getDialectInstance(CommonJdbcDaoUtils.getDialect());
		}
		return abstractDialect;
	}
}
