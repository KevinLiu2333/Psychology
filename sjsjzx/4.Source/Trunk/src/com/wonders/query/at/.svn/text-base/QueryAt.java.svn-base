package com.wonders.query.at;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.pzgl.entity.DwLog;
import com.wonders.query.entity.CorpGQ;
import com.wonders.query.entity.CorpInfo;
import com.wonders.query.entity.CorpLHRH;
import com.wonders.query.entity.CorpLicense;
import com.wonders.query.entity.CorpXJR;
import com.wonders.query.entity.PersonInfo;
import com.wonders.query.entity.PunishNoteEnty;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/query")
@IocBean
public class QueryAt {
	@Inject
	private Dao dao;
	@Inject
	private Dao dao1;
	
	
	@At
	@Ok("jsp:jsp.query.person_list")
	public Map<String, Object> toPersonList(HttpServletRequest request,String countflag,
			HttpSession session, String nl,String nl2, String xb, String zjhm,String xm,
			String csny1,String csny2,String mz, String syrklb, String hjjd,String newquery) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		if(Strings.isEmpty(countflag)){
			log.setOperateType("查询人口列表");
			log.setCountType("1");
		}else {
			log.setOperateType("统计人口总数");
		}
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		String condition = "where 1=1 ";
		Criteria cri = Cnd.cri();
		boolean queryflag = false; // 无条件时不查询
		if (!Strings.isEmpty(xm)){
			cri.where().and("XM","like",xm+"%");
			condition += " and xm like '" + xm+"%'";
			queryflag = true;
		}
		if(!Strings.isEmpty(csny1)){
			cri.where().and("CSRQ",">=",csny1.replaceAll("-", "")+"00");
			condition += " and CSRQ >='" + csny1.replaceAll("-", "")+"00'";
			queryflag = true;
			try {
				result.put("csny1", new SimpleDateFormat("yyyyMMdd").parse(csny1.replaceAll("-", "")+"01"));
			} catch (ParseException e) {
				result.put("csny1",null);
			}
		}
		if(!Strings.isEmpty(csny2)){
			cri.where().and("CSRQ","<=",csny2.replaceAll("-", "")+"99");
			condition += " and CSRQ <='" + csny2.replaceAll("-", "")+"99'";
			queryflag = true;
			try {
				result.put("csny2", new SimpleDateFormat("yyyyMMdd").parse(csny2.replaceAll("-", "")+"01"));
			} catch (ParseException e) {
				result.put("csny2",null);
			}
		}
		if (!Strings.isEmpty(mz)) {
			String mztemp="";
			String[] mzs = mz.split(",");
			for(int i = 0 ;i< mzs.length ; i++){
				mztemp+="'"+mzs[i]+"'";
				if(i!=mzs.length-1){
					mztemp+=",";
				}
			}
			cri.where().andIn("MZDM", mzs);
			condition += " and MZDM in (" + mztemp+")";
			queryflag = true;
		}
		if (!Strings.isEmpty(xb)) {
			cri.where().and("XBDM", "=", xb);
			condition += " and XBDM='" + xb+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(zjhm)) {
			cri.where().and("ZJHM", "=", zjhm.trim());
			condition += " and ZJHM='" + zjhm.trim()+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(nl)) {
			int nowday = Integer.parseInt((new SimpleDateFormat("yyyyMMdd"))
					.format(new Date()));
			cri.where().and("CSRQ", "<=",
					"" + (nowday - Integer.parseInt(nl) * 10000));
			condition += " and CSRQ<='"
					+ (nowday - Integer.parseInt(nl) * 10000)+"'";
			queryflag = true;
		}
		if(!Strings.isEmpty(nl2)){
			int nowday = Integer.parseInt((new SimpleDateFormat("yyyyMMdd"))
					.format(new Date()));
			cri.where().and("CSRQ", ">",
					"" + (nowday - (Integer.parseInt(nl2)+1) * 10000));
			condition += " and CSRQ>'"
					+ (nowday - (Integer.parseInt(nl2) +1)* 10000)+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(syrklb)) {
			cri.where().and("SYRKLBDM", "=", syrklb);
			condition += " and SYRKLBDM='" + syrklb+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(hjjd)) {
			cri.where().and("HJDJDDM","=",hjjd);
			condition += " and HJDJDDM = '" + hjjd+"'";
			queryflag = true;
		}
		log.setQueryCondition(condition);
		log.setQueryTable("T_GA_RJBXX");
		List list =null;
		//List<PersonInfo> list = null;
		if (queryflag) {
			if(Strings.isEmpty(countflag)){
				Sql sql =Sqls.create("select rid,ZJHM,XM,CSRQ,xbdm,hjdjdhz from T_GA_RJBXX "
							+condition+" ");
				sql.setPager(pager);
				sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
						while (rs.next()) {
							Map<String, Object> person = new HashMap<String, Object>();
							person.put("xbdm", rs.getString("xbdm"));
							person.put("hjdjdhz", rs.getString("hjdjdhz"));
							person.put("rid", rs.getString("rid"));
							String name = rs.getString("XM");
							if(!Strings.isEmpty(name)){
								if (name.length() == 2) {
									person.put("xm",name.charAt(0) + "*");
								}else if (name.length() == 1) {
									person.put("xm","*");
								}else if (name.length() == 3) {
									person.put("xm",name.charAt(0)+"**");
								}else {
									person.put("xm",name.charAt(0)
											+ Strings.dup('*', name.length() - 2)
											+ name.charAt(name.length() - 1));
								}
								String csrq = rs.getString("CSRQ");
								if(!Strings.isEmpty(csrq))
									person.put("csrq",csrq.substring(0, 4) + "-"
										+ csrq.substring(4, 6));
							}
							String zjhm1 = rs.getString("ZJHM");
							if(!Strings.isEmpty(zjhm1)){
								if (zjhm1.length() == 18) {
									person.put("zjhm",zjhm1.substring(0, 6)
										+ Strings.dup("*", zjhm1.length() - 7)
										+ zjhm1.substring(zjhm1.length() - 1,
												zjhm1.length()));
								} else if (zjhm1.length() > 6) {
									person.put("zjhm",zjhm1.substring(0, 3)
										+ Strings.dup("*", zjhm1.length() - 6)
										+ zjhm1.substring(zjhm1.length() - 3,
												zjhm1.length()));
								} else {
									person.put("zjhm",Strings.dup('*', zjhm1.length() / 2)
										+ zjhm1.substring(zjhm1.length() / 2,
												zjhm1.length()));
								}
							}
							list.add(person);
						}
						return list;
					}
				});
				dao1.execute(sql);
				list =(List) sql.getResult();
				//list = dao1.query(PersonInfo.class, cri, pager);
				pager.setRecordCount(dao1.count(PersonInfo.class, cri));
				log.setQueryCount(list.size() + "");
			}	else {
				int countresult=dao1.count(PersonInfo.class, cri);
				log.setQueryCount("1");
				result.put("countresult", countresult);
			}
			if(!Strings.isEmpty(newquery)&&newquery.equals("1")){
				dao.insert(log);
			}
		} else {
			pager.setRecordCount(0);
			log.setQueryCount("0");
		}
		result.put("nl2", nl2);
		result.put("xm", xm);
		result.put("nl", nl);
		result.put("xb", xb);
		result.put("zjhm", zjhm);
		result.put("mz", mz);
		result.put("syrklb", syrklb);
		result.put("hjjd", hjjd);
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.special_list")
	public Map<String, Object> toSpecialList(HttpServletRequest request,
			String table,String Name) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if(table!=null && table.equals("xjr"))
		{
			if (!Strings.isEmpty(Name)){
				cri.where().and("linkman","like","%"+Name+"%");
			}
			List<CorpXJR>xjr=dao.query(CorpXJR.class, cri,pager);
			result.put("xjr", xjr);
			pager.setRecordCount(dao.count(CorpXJR.class,cri));
			result.put("pager", pager);
		}
		if(table!=null && table.equals("lhrh"))
		{
			if (!Strings.isEmpty(Name)){
				cri.where().and("principal","like","%"+Name+"%");
			}
			List<CorpLHRH>lhrh=dao.query(CorpLHRH.class, cri,pager);
			result.put("lhrh", lhrh);
			pager.setRecordCount(dao.count(CorpLHRH.class,cri));
			result.put("pager", pager);
		}
		
		if(table!=null && table.equals("gq"))
		{
			if (!Strings.isEmpty(Name)){
				cri.where().and("corporaTion","like","%"+Name+"%");
			}
			List<CorpGQ>gq=dao.query(CorpGQ.class, cri,pager);
			result.put("gq", gq);
			pager.setRecordCount(dao.count(CorpGQ.class,cri));
			result.put("pager", pager);
		}
		
		result.put("table", table);
		result.put("Name", Name);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.special_view")
	public Map<String, Object> specialView( String address,String table,
			HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		//Criteria cri = Cnd.cri();
		//String filePath = URLDecoder.decode(URLDecoder.decode(address,"ISO8859-1"), "utf-8");
		address = new String(address.getBytes("ISO8859-1"),"UTF-8");
		/*if(table!=null && table.equals("xjr"))
		{
			CorpXJR xjr=dao.fetch(CorpXJR.class, Cnd.where("BUSINESS_NAME","=",address));
			result.put("xjr", xjr);
		}
		if(table!=null && table.equals("lhrh"))
		{
			CorpLHRH lhrh=dao.fetch(CorpLHRH.class, Cnd.where("APPLICATION_UNITS","=",address));
			result.put("lhrh", lhrh);
		}
		
		if(table!=null && table.equals("gq"))
		{
			CorpGQ gq=dao.fetch(CorpGQ.class, Cnd.where("COMPANY_NAME","=",address));
			result.put("gq", gq);
		}*/
		CorpInfo co=dao.fetch(CorpInfo.class,Cnd.where("ORGAN_CODE","=",address));
		result.put("table", table);
		result.put("corp", co);
		return result;
	}
	
	/*
	 * 测试
	 * */
	@At
	@Ok("json")
	public void ceshi(){
		Criteria cri = Cnd.cri();
		Criteria cri1 = Cnd.cri();
		/*List<CorpXJR>xjr=dao.query(CorpXJR.class, cri);
		for (int i=0;i<xjr.size();i++)
		{
			CorpInfo co=dao.fetch(CorpInfo.class,Cnd.where("CORP_NAME","=",xjr.get(i).getBusinessName()));
			if(co!=null)
			{
				CorpXJR xjr1=dao.fetch(CorpXJR.class,Cnd.where("BUSINESS_NAME","=", co.getCorpname()));
				System.out.println("11="+xjr1.getOrganCode());
				if(xjr1.getOrganCode()==null || xjr1.getOrganCode()=="null")
				{
					String sql = "update CORP_XJR set ORGAN_CODE='"+co.getOrgancode()+"' where BUSINESS_NAME='"+co.getCorpname()+"'";
					Sql dropTableSql = Sqls.create(sql);
					dao.execute(dropTableSql);
				}
			}
		}
		
		List<CorpLHRH>lhrh=dao.query(CorpLHRH.class, cri);
		for (int i=0;i<lhrh.size();i++)
		{
			CorpInfo co=dao.fetch(CorpInfo.class,Cnd.where("CORP_NAME","=",lhrh.get(i).getApplicationUnits()));
			if(co!=null)
			{
				CorpLHRH lhrh1=dao.fetch(CorpLHRH.class,Cnd.where("APPLICATION_UNITS","=", co.getCorpname()));
				System.out.println("11="+lhrh1.getOrganCode());
				if(lhrh1.getOrganCode()==null || lhrh1.getOrganCode()=="null")
				{
					String sql = "update CORP_LHRH set ORGAN_CODE='"+co.getOrgancode()+"' where APPLICATION_UNITS='"+co.getCorpname()+"'";
					Sql dropTableSql = Sqls.create(sql);
					dao.execute(dropTableSql);
				}
			}
	
		}*/
		
		List<CorpGQ>gq=dao.query(CorpGQ.class, cri);
		for (int i=0;i<gq.size();i++)
		{
			CorpInfo co=dao.fetch(CorpInfo.class,Cnd.where("CORP_NAME","=",gq.get(i).getCompanyName()));
			if(co!=null)
			{
				CorpGQ gq1=dao.fetch(CorpGQ.class,Cnd.where("COMPANY_NAME","=", co.getCorpname()));
				System.out.println("11="+gq1.getOrganCode());
				if(gq1.getOrganCode()==null || gq1.getOrganCode()=="null")
				{
					String sql = "update CORP_GQ set ORGAN_CODE='"+co.getOrgancode()+"' where COMPANY_NAME='"+co.getCorpname()+"'";
					Sql dropTableSql = Sqls.create(sql);
					dao.execute(dropTableSql);
				}
			}
	
		}
		}
	
	@At
	@Ok("json")
	public Object getfrlx(String frlx){
		Sql sql = Sqls.create("select ID,VALUE from DIC_FR_TYPE"
				+ " where id like '"+frlx+"%' and id <> '"+frlx+"0000'" );
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id",rs.getString("ID"));
					map.put("value", rs.getString("VALUE"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) sql.getResult();
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.person_view")
	public Map<String, Object> personView( String rid,
			HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		PersonInfo personInfo = dao.fetch(PersonInfo.class,
				Cnd.where("RID", "=", rid));
		String csrq = personInfo.getCsrq();
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询人口库详细信息");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("RID=" + rid);
		log.setQueryTable("T_GA_RJBXX");
		log.setQueryCount(personInfo == null ? "0" : "1");
		dao.insert(log);
		if (csrq.length() == 8) {
			personInfo.setCsrq(csrq.substring(0, 4) + "年"
					+ csrq.substring(4, 6) + "月" + csrq.substring(6, 8) + "日");
		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		result.put("department",
				DicDataUtils.getInstance().getDicData(1003, user.getDept()));
		result.put("userName", user.getLogonName());
		result.put("person", personInfo);
		return result;
	}

	@At
	@Ok("jsp:jsp.query.corpinfo_list")
	public Map<String, Object> toCorpInfoList(HttpServletRequest request,
			HttpSession session, String dwmc, String frzt,String frlx2,
			String frlx, String zczj1, String zczj2, Date zcrq1, Date zcrq2,
			String cylb1,String cylb2,String dmlx, String dmh) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人登记信息列表");
		log.setCountType("3");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryTable("CORP_INFO");
		String condition = "where 1=1 ";
		Criteria cri = Cnd.cri();
		boolean queryflag = false;
		if(!Strings.isEmpty(cylb1)){
			queryflag = true;
			if(!Strings.isEmpty(cylb2)){
				cri.where().and("INDUSTRY_CODE","like",cylb2+"%");
				condition +=" and INDUSTRY_CODE like '%"+cylb2+"%'"; 
			}
			else {
				Sql sql =Sqls.create("select ID from DIC_FR_INDUSTRY_TYPE where INDUSTRY_TYPE ='"+cylb1+"'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						List<String> result = new ArrayList<String>();
						while(rs.next()){
							result.add(rs.getString(1));
						}
						return result;
					}
				});
				dao.execute(sql);
				@SuppressWarnings("unchecked")
				List<String> list=(List<String>) sql.getResult();
				SqlExpressionGroup exps = null;
				if(list!=null&&list.size()>0){
					exps = Cnd.exps("INDUSTRY_CODE","like",list.get(0)+"%");
					condition=condition+" and (INDUSTRY_CODE like '%"+list.get(0)+"%'";
					for(int i=1;i<list.size();i++){
						exps.or("INDUSTRY_CODE","like",list.get(i)+"%");
						condition=condition+" or INDUSTRY_CODE like '%"+list.get(i)+"%'";
					}
					condition=condition+")";
				}else {
					exps = Cnd.exps("1","=","1");
				}
				cri.where().and(exps);
			}
		}
		if (!Strings.isEmpty(dwmc)) {
			cri.where().and("CORP_NAME", "like", "%" + dwmc + "%");
			condition += " and CORP_NAME like '%" + dwmc + "%'";
			queryflag = true;
		}
		if(!Strings.isEmpty(dmh)&&!Strings.isEmpty(dmlx)){
			cri.where().and(dmlx,"=",dmh);
			condition += " and "+dmlx+" = '"+dmh+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frzt)) {
			cri.where().and("CORP_STATUS", "=", frzt);
			condition += " and CORP_STATUS = '" + frzt + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frlx)) {
			if(!Strings.isEmpty(frlx2)){
				cri.where().and("CORP_TYPE", "=", frlx2);
				condition += " and CORP_TYPE = '" + frlx2 + "'";
			}else {
				cri.where().and("CORP_TYPE", "like", frlx + "%");
				condition += " and CORP_TYPE like '" + frlx + "%'";
			}
			queryflag = true;
		}
		if (!Strings.isEmpty(zczj1)) {
			cri.where().and("REG_CAPITAL", ">=", zczj1);
			condition += " and REG_CAPITAL >= '" + zczj1 + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(zczj2)) {
			cri.where().and("REG_CAPITAL", "<", zczj2);
			condition += " and REG_CAPITAL <= '" + zczj2 + "'";
			queryflag = true;
		}
		if (zcrq1 != null) {
			cri.where().and("ESTABLISH_DATE", ">", zcrq1);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd')>= '" +new SimpleDateFormat("yyyyMMdd").format(zcrq1)  + "'";
			queryflag = true;
		}
		if (zcrq2 != null) {
			cri.where().and("ESTABLISH_DATE", "<=", zcrq2);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd') <= '" + new SimpleDateFormat("yyyyMMdd").format(zcrq2)  + "'";
			queryflag = true;
		}
		List list = null;
		log.setQueryCondition(condition);
		cri.getOrderBy().desc("ESTABLISH_DATE");
		if (queryflag) {
			Sql sql =Sqls.create("select CORP_INFO_ID,CORP_NAME,ORGAN_CODE,PERSON_NAME,CORP_STATUS,ESTABLISH_DATE from corp_info "+condition+" order by ESTABLISH_DATE desc nulls last");
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					while (rs.next()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("corpinfoid", rs.getObject("CORP_INFO_ID"));
						map.put("corpname", rs.getString("CORP_NAME"));
						map.put("organcode", rs.getString("ORGAN_CODE"));
						map.put("personname", rs.getString("PERSON_NAME"));
						map.put("corpstatus", rs.getString("CORP_STATUS"));
						map.put("establishdate", rs.getDate("ESTABLISH_DATE"));
						list.add(map);
					}
					return list;
				}
			});
			dao.execute(sql);
			//list = dao.query(CorpInfo.class, cri, pager);
			list = (List) sql.getResult();
			pager.setRecordCount(dao.count(CorpInfo.class, cri));
			log.setQueryCount(list.size() + "");
			dao.insert(log);
		}
		result.put("dmh", dmh);
		result.put("list", list);
		result.put("cylb1", cylb1);
		result.put("cylb2", cylb2);
		result.put("dwmc", dwmc);
		result.put("dmlx", dmlx);
		result.put("frzt", frzt);
		result.put("frlx", frlx);
		result.put("frlx2", frlx2);
		result.put("zczj1", zczj1);
		result.put("zczj2", zczj2);
		result.put("zcrq1", zcrq1);
		result.put("zcrq2", zcrq2);
		result.put("pager", pager);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.corpzt_list")
	public Map<String, Object> toCorpZTList(HttpServletRequest request,
			HttpSession session, String dwmc, String frzt,String frlx2,
			String cylb1,String cylb2,String dmlx, String dmh) {
				return null;
	}
	
	@At
	@Ok("jsp:jsp.query.corp_view")
	public Map<String, Object> viewCorpInfo(String corpinfoid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_INFO_ID","=",corpinfoid);
		CorpInfo corp = dao.fetch(CorpInfo.class,cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人登记信息详情");
		log.setCountType("4");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("CORP_INFO_ID=" + corpinfoid);
		log.setQueryTable("CORP_INFO");
		log.setQueryCount(corp == null ? "0" : "1");
		dao.insert(log);
		Sql sql = Sqls.create("select DEPTID from TB_CORP where ( (SHTYXYDM is not null and SHTYXYDM ='"+corp.getUniscid()+"')"
				+ " or ( ZZJGDM is not null and ZZJGDM ='"+corp.getOrgancode()+"' ) "
				+ " or ( YYZZZCH is not null and YYZZZCH = '"+corp.getRegno()+"') "
				+ " or ( NSRSBH is not null and NSRSBH ='"+corp.getTaxpayerscode()+"')"
				+ " or ( CORP_NAME is not null and CORP_NAME ='"+corp.getCorpname()+"')) group by DEPTID"); 
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet resultset, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while(resultset.next()){
					result.add(resultset.getString("DEPTID"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<String> deptlist =(List<String>) sql.getResult();
		result.put("deptlist", deptlist);
		result.put("corp", corp);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corplicense_list")
	public Map<String, Object> toCorpLicenseList(String corpinfoid,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_INFO_ID","=",corpinfoid);
		cri.getOrderBy().desc("START_DATE");
		List<CorpLicense> corpLicenses=dao.query(CorpLicense.class, cri ,pager);
		for(CorpLicense corpLicense:corpLicenses){
			String licensetype=corpLicense.getLicensetype();
			licensetype=DicDataUtils.getInstance().getDic(1060).get(licensetype);
			
				corpLicense.setLicensetype(licensetype);
		}
		pager.setRecordCount(dao.count(CorpLicense.class, cri));
		result.put("corpLicenses", corpLicenses);
		result.put("corpinfoid", corpinfoid);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corplicense_view")
	public Map<String, Object> viewCorpLicense(String licenseid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("LICENSE_ID","=",licenseid);
		CorpLicense license=dao.fetch(CorpLicense.class , cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人资质信息详细信息");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("LICENSE_ID=" + licenseid);
		log.setQueryTable("CORP_LICENSE");
		log.setQueryCount(license == null ? "0" : "1");
		dao.insert(log);
		result.put("license", license);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.punishnoteenty_list")
	public Map<String, Object> toPunishNoteEntyList(String corpinfoid,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Sql sql = Sqls.create("select PUNISH_ENTY_ID from PUNISH_NOTE_CORP_ENTY where CORP_INFO_ID = '"+corpinfoid+"'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while (rs.next()) {
					result.add(rs.getString("PUNISH_ENTY_ID"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<String> punishids = (List<String>) sql.getResult();
		
		List<PunishNoteEnty> punishNoteEnties = null;
		if(punishids!=null&&punishids.size()>0){
			Criteria cri = Cnd.cri();
			cri.where().and("PUNISH_ENTY_ID","in", punishids);
			punishNoteEnties = dao.query(PunishNoteEnty.class, cri,pager);
			pager.setRecordCount(dao.count(PunishNoteEnty.class, cri));
		}
		result.put("punishNoteEnties", punishNoteEnties);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.punishnoteenties_view")
	public Map<String, Object> viewPunishNoteEnties(String punishentyid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("PUNISH_ENTY_ID","=",punishentyid);
		PunishNoteEnty punishNoteEnty = dao.fetch(PunishNoteEnty.class,cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人处罚信息详细信息");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("PUNISH_ENTY_ID=" + punishentyid);
		log.setQueryTable("PUNISH_NOTE_ENTY");
		log.setQueryCount(punishNoteEnty == null ? "0" : "1");
		dao.insert(log);
		result.put("punishNoteEnty", punishNoteEnty);
		return result;
	}


	/**
	 * 行业专题列表.
	 */
	@At
	@Ok("jsp:jsp.query.industry_list")
	public Object industryTopicsList(){
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}
}
