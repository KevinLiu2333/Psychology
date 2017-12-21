package com.wonders.ddts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.tiles.authority.entity.Org;
@IocBean
public class GetDept {
	
	@Inject
	private Dao dao;
	
	/**
	 * 获取部门名
	 * @param code部门代码  
	 * @return
	 */
	public String getDeptName(String code){
		String orgid="";
		if(!Strings.isEmpty(code)){
			Org org =dao.fetch(Org.class,Cnd.where("ORG_CODE", "=", code));
            orgid=org.getOrgId();
		}
		if(!Strings.isEmpty(orgid)){
			Sql sql =Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where ORGAN_ID ='"+orgid+"'");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
						throws SQLException {
					if(rs.next()){
						return rs.getString("ST_DEPT_CODE");
					}
					return null;
				}
			});
			dao.execute(sql);
			String deptid = (String) sql.getResult();
			if(!Strings.isEmpty(deptid)){
				return deptid;
			}else {
				int counttime=20;//防止死循环
				boolean flag = false;//是否查到主部门organid
				String orgcode = orgid;
				while(counttime>0){
					sql = Sqls.create("select ORG_ID,ORG_PARENT_ID "
							+ "from UA_ORG where ORG_ID=(select ORG_PARENT_ID from UA_ORG where ORG_ID='"+orgcode+"')");
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
								throws SQLException {
							Map<String, String> result = new HashMap<String, String>();
							if(rs.next()){
								result.put("ORG_ID", rs.getString("ORG_ID"));
								result.put("ORG_PARENT_ID", rs.getString("ORG_PARENT_ID"));
								return result;
							}
							return null;
						}
					});
					dao.execute(sql);
					@SuppressWarnings("unchecked")
					Map<String, String> result = (Map<String, String>) sql.getResult();
					if(result==null){
						break;
					}else if(result.get("ORG_PARENT_ID").equals("1")){
						orgid=result.get("ORG_ID");
						flag=true;
						break;
					}else {
						orgcode=result.get("ORG_ID");
					}
					counttime--;
				}
				if(flag){
					sql =Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where ORGAN_ID ='"+orgid+"'");
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
								throws SQLException {
							if(rs.next()){
								return rs.getString("ST_DEPT_CODE");
							}
							return null;
						}
					});
					dao.execute(sql);
					deptid = (String) sql.getResult();
					return deptid;
				}
			}
			
		}
		return null;
		
	}
}
