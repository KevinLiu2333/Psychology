package com.wonders.ws.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.api.entity.ApiServiceUser;
import com.wonders.ws.WsUitl;

@IocBean
public class LyCorpDao {
	@Inject
	private Dao dao;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getCropByOrganCode(String organcode,ApiServiceUser serviceUser) {
		Map<String, Object> result = null;
		String sqlstr = "select CORP_NAME,UNI_SC_ID,REG_NO,ORGAN_CODE,TAXPAYERS_CODE,ESTABLISH_DATE,ADDRESS,BUSINESS_ADDRESS"
				+ ",BUSINESS_SCOPE,PERSON_NAME,TELEPHONE,ZIP,REG_CAPITAL,CURRENCY,INDUSTRY_CODE,"
				+ "(select VALUE from DIC_FR_INDUSTRY_TYPE b where b.id=INDUSTRY_CODE) value,"
				+ "CORP_TYPE,(select value from DIC_FR_TYPE b where b.id=CORP_TYPE) VALUE_1,CHANGE_DATE from corp_info where organ_code='"
				+ organcode + "' order by CHANGE_DATE desc";
		Sql sql = Sqls.create(sqlstr);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				ResultSetMetaData data = rs.getMetaData();
				while(rs.next()){
					Map<String, Object> map=new HashMap<String, Object>();
					for(int i=0;i<data.getColumnCount();i++){
						if("ESTABLISH_DATE".equals(data.getColumnName(i+1))){
							Date establish_date=rs.getDate(data.getColumnName(i+1));
							map.put("ESTABLISH_DATE",establish_date==null?"": establish_date.toString());
						}else if("CHANGE_DATE".equals(data.getColumnName(i+1))){
							Date change_date=rs.getDate(data.getColumnName(i+1));
							map.put("CHANGE_DATE", change_date==null?"": change_date.toString());
						}else {
							map.put(data.getColumnName(i+1), rs.getObject(data.getColumnName(i+1)));
						}
					}
					return map;
				}
				return null;
			}
		});
		dao.execute(sql);
		result=(Map<String, Object>) sql.getResult();
		ApiLogWebService log=WsUitl.createlog(serviceUser, result==null?0:1);
		dao.insert(log);
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCropByCorpName(String corpname,ApiServiceUser serviceUser) {
		List<Map<String, Object>> result = null;
		String sqlstr = "select CORP_NAME,UNI_SC_ID,REG_NO,ORGAN_CODE,TAXPAYERS_CODE,ESTABLISH_DATE,ADDRESS,BUSINESS_ADDRESS"
				+ ",BUSINESS_SCOPE,PERSON_NAME,TELEPHONE,ZIP,REG_CAPITAL,CURRENCY,INDUSTRY_CODE,"
				+ "(select VALUE from DIC_FR_INDUSTRY_TYPE b where b.id=INDUSTRY_CODE) value,"
				+ "CORP_TYPE,(select value from DIC_FR_TYPE b where b.id=CORP_TYPE) VALUE_1,CHANGE_DATE from corp_info where  CORP_NAME = '"
				+ corpname + "' order by CHANGE_DATE desc";
		Sql sql = Sqls.create(sqlstr);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				ResultSetMetaData data = rs.getMetaData();
				List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
				while(rs.next()){
					Map<String, Object> map=new HashMap<String, Object>();
					
					for(int i=0;i<data.getColumnCount();i++){
						if("ESTABLISH_DATE".equals(data.getColumnName(i+1))){
							Date establish_date=rs.getDate(data.getColumnName(i+1));
							map.put("ESTABLISH_DATE", establish_date==null?"": establish_date.toString());
						}
						else if("CHANGE_DATE".equals(data.getColumnName(i+1))){
							Date change_date=rs.getDate(data.getColumnName(i+1));
							map.put("CHANGE_DATE", change_date==null?"": change_date.toString());
						}else {
							map.put(data.getColumnName(i+1), rs.getObject(data.getColumnName(i+1)));
						}
						
					}
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		result=(List<Map<String, Object>>) sql.getResult();
		ApiLogWebService log=WsUitl.createlog(serviceUser, result==null?0:1);
		dao.insert(log);
		return result;
	}
}
