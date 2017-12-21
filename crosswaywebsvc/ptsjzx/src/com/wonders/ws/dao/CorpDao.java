package com.wonders.ws.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.api.entity.ApiServiceUser;
import com.wonders.query.entity.XyxxCloumn;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.ws.WsUitl;
import com.wonders.ws.bean.CorpInfoBean;
import com.wonders.ws.bean.CorpLicenseBean;
import com.wonders.ws.bean.DicFrBizTypeBean;
import com.wonders.ws.bean.PunishNoteCorpEntyBean;
import com.wonders.ws.bean.PunishNoteEntyBean;

@IocBean
public class CorpDao {
	@Inject
	private Dao	dao;

	public CorpInfoBean getCropInfoBean(String organcode, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("ORGAN_CODE", "=", organcode);
		CorpInfoBean cropInfoBean = dao.fetch(CorpInfoBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return cropInfoBean;
	}

	public List<CorpInfoBean> getCropInfoBeansByCropName(String corpname, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_NAME", "like", "%" + corpname + "%");
		List<CorpInfoBean> list = dao.query(CorpInfoBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	public CorpLicenseBean getCorpLicenseBean(String organcode, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("ORGAN_CODE", "=", organcode);
		CorpLicenseBean cropInfoBean = dao.fetch(CorpLicenseBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return cropInfoBean;
	}

	public List<CorpLicenseBean> getCorpLicenseBeansByCorpName(String corpname, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("UNIT_NAME", "like", "%" + corpname + "%");
		List<CorpLicenseBean> list = dao.query(CorpLicenseBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	public List<PunishNoteEntyBean> getPunishNoteEntyBeansByOrganCode(String organcode, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("ORGAN_CODE", "=", organcode);
		List<PunishNoteEntyBean> list = dao.query(PunishNoteEntyBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	public List<PunishNoteEntyBean> getPunishNoteEntyBeansByCorpname(String corpname, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_NAME", "like", "%" + corpname + "%");
		List<PunishNoteEntyBean> list = dao.query(PunishNoteEntyBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	public List<CorpInfoBean> getCorpInfoBeansByPersonName(String personName, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("PERSON_NAME", "like", "%" + personName + "%");
		List<CorpInfoBean> list = dao.query(CorpInfoBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	// 根据注册资金
	public List<CorpInfoBean> getCorpInfoBeansByRegcapital(double regcapital, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		cri.where().and("REG_CAPITAL", "=", regcapital);
		List<CorpInfoBean> list = dao.query(CorpInfoBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	// 根据注册日期
	public List<CorpInfoBean> getCorpInfoBeansByEstablishDate(String establishdate, ApiServiceUser serviceUser) {
		Criteria cri = Cnd.cri();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date date1 = null;
		try {
			date = sdf.parse(establishdate);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			date1 = calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cri.where().and("ESTABLISH_DATE", ">=", date);
		cri.where().and("ESTABLISH_DATE", "<=", date1);
		List<CorpInfoBean> list = dao.query(CorpInfoBean.class, cri);
		ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
		dao.insert(log);
		return list;
	}

	// 根据法人名称、组织机构代码或统一社会信用代码查询法人信息
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpInfoByNameOrgancodeUniscid(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls.create("select CORP_NAME,ORGAN_CODE,CORP_TYPE,PERSON_NAME,ADDRESS,CORP_STATUS,"
				+ "UNI_SC_ID,ESTABLISH_DATE,REG_CAPITAL,BUSINESS_SCOPE,INDUSTRY_CODE from CORP_INFO " + "where CORP_NAME = '" + keyword + "' or ORGAN_CODE = '" + keyword
				+ "' or UNI_SC_ID = '" + keyword + "'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("industry_code"))) {
						result.put("industry_code", DicDataUtils.getInstance().getDicData(1049, result.get("industry_code")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 根据法人名称、四个代码查询法人登记信息
	// 网厅
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpRegInfoToWangting(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls.create("select CORP_NAME,CORP_TYPE,PERSON_NAME,ESTABLISH_DATE,REG_CAPITAL,"
				+ "BUSINESS_SCOPE,PERSON_CERT_TYPE,PERSON_CERT_CODE,INDUSTRY_CODE,ORGANIZERS from CORP_INFO " + "where CORP_NAME = '" + keyword + "' or ORGAN_CODE = '" + keyword
				+ "' or UNI_SC_ID = '" + keyword + "'" + "or TAXPAYERS_CODE= '" + keyword + "'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("industry_code"))) {
						result.put("industry_code", DicDataUtils.getInstance().getDicData(1049, result.get("industry_code")));
					}
					if (!Strings.isEmpty(result.get("corp_type"))) {
						result.put("corp_type", DicDataUtils.getInstance().getDicData(1052, result.get("corp_type")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 根据法人名称、四个代码查询法人资质信息
	// 网厅
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpLicenseToWangting(String corpname, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_NAME", "=", corpname);
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, cri);
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			Sql sql = Sqls.create("select UNIT_NAME,LICENSE_DATA,ORGAN_CODE,UNI_SC_ID,LICENSE_TYPE,BUSINESS_SCOPE" + " from CORP_LICENSE " + "where CORP_INFO_ID = '" + corpInfoId
					+ "'");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						if (!Strings.isEmpty(map.get("license_type"))) {
							map.put("license_type", DicDataUtils.getInstance().getDicData(1060, map.get("license_type")));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、四个代码查询法人处罚信息
	// 网厅
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpCfToWangting(String corpname, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, Cnd.where("CORP_NAME", "=", corpname));
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			List<PunishNoteCorpEntyBean> punishnotecorp = dao.query(PunishNoteCorpEntyBean.class, Cnd.where("CORP_INFO_ID", "=", corpInfoId));
			List<Object> list = new ArrayList<Object>();
			for (PunishNoteCorpEntyBean enty : punishnotecorp) {
				double punishEntyId = enty.getPunishentyid();
				Sql sql = Sqls.create("select PUNISH_CODE,PUNISH_UNIT,PUNISH_BASIS,PUNISH_MEASURES,PUNISH_LIMIT," + "PUNISH_DATE,FROM_DEPT_ID from punish_note_enty "
						+ "where punish_enty_id = '" + punishEntyId + "'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
						if (rs.next()) {
							Map<String, String> map = new HashMap<String, String>();
							ResultSetMetaData rsmd = rs.getMetaData();
							int col = rsmd.getColumnCount();
							for (int i = 1; i <= col; i++) {
								map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
							}
							if (!Strings.isEmpty((CharSequence) map.get("from_dept_id"))) {
								map.put("from_dept_id", DicDataUtils.getInstance().getDicData(1064, map.get("from_dept_id")));
							}
							return map;
						}
						return null;
					}
				});
				dao.execute(sql);
				Map<String, String> res = (Map<String, String>) sql.getResult();
				list.add(res);
			}
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 按关键字查询登记信息（不包括纳税人识别号）
	// 环保局
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpRegInfoToHuanbaoju(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls
				.create("select ORGAN_CODE,REG_NO,RECEIVING_ORGAN,CORP_NAME,CORP_TYPE,PERSON_NAME,PERSON_CERT_CODE,PERSON_CERT_TYPE,REPEAL_DATE,CHANGE_DATE,REG_UPD_DATE,CORP_STATUS"
						+ ",TELEPHONE,ADDRESS,AREA_CODE,ZIP,ESTABLISH_DATE,BUSINESS_ADDRESS,REG_CAPITAL,CURRENCY,BUSINESS_SCOPE,"
						+ "INDUSTRY_CODE,ORGANIZERS from CORP_INFO "
						+ "where CORP_NAME = '" + keyword + "' or ORGAN_CODE = '" + keyword + "' or UNI_SC_ID = '" + keyword + "' or REG_NO = '" + keyword + "'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("industry_code"))) {
						result.put("industry_code", DicDataUtils.getInstance().getDicData(1049, result.get("industry_code")));
					}
					if (!Strings.isEmpty(result.get("receiving_organ"))) {
						result.put("receiving_organ", DicDataUtils.getInstance().getDicData(1050, result.get("receiving_organ")));
					}
					if (!Strings.isEmpty(result.get("corp_type"))) {
						result.put("corp_type", DicDataUtils.getInstance().getDicData(1052, result.get("corp_type")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 按资质名称查询法人登记、资质信息（不包括纳税人识别号）
	// 环保局
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpLicenseToHuanbaoju(String licenseName, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		DicFrBizTypeBean frBizType = dao.fetch(DicFrBizTypeBean.class, Cnd.where("BIZDATA_TYPE_NAME", "=", licenseName));
		if (frBizType != null) {
			String nameCode = frBizType.getBiztype();
			Sql sql = Sqls.create("select t1.ORGAN_CODE,t1.REG_NO,t2.UNIQUE_CODE,t1.corp_name,t1.CORP_TYPE,t1.PERSON_NAME"
					+ ",t1.PERSON_CERT_CODE,t1.PERSON_CERT_TYPE,t1.TELEPHONE,t1.ADDRESS,t1.AREA_CODE,t1.ZIP,t1.BUSINESS_ADDRESS"
					+ ",t2.REG_ADDRESS,t1.BUSINESS_SCOPE,t1.REG_CAPITAL,t1.CURRENCY,t2.BUSINESS_SCOPE,t1.INDUSTRY_CODE,t1.ORGANIZERS"
					+ ",t2.LICENSE_TYPE,t2.ISSUE_ORGAN,t2.LICENSE_DATA,t2.START_DATE,t2.END_DATE from corp_info t1,corp_license t2 "
					+ "where t1.corp_info_id = t2.corp_info_id and t2.LICENSE_TYPE = '" + nameCode + "' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						if (!Strings.isEmpty(map.get("industry_code"))) {
							map.put("industry_code", DicDataUtils.getInstance().getDicData(1049, map.get("industry_code")));
						}
						if (!Strings.isEmpty(map.get("corp_status"))) {
							map.put("corp_status", DicDataUtils.getInstance().getDicData(1051, map.get("corp_status")));
						}
						if (!Strings.isEmpty(map.get("corp_type"))) {
							map.put("corp_type", DicDataUtils.getInstance().getDicData(1052, map.get("corp_type")));
						}
						if (!Strings.isEmpty(map.get("license_type"))) {
							map.put("license_type", DicDataUtils.getInstance().getDicData(1060, map.get("license_type")));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、社会统一信用代码、组织机构代码返回登记信息
	// 商务委
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpRegInfoToShangwuwei(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls.create("select CORP_NAME,REG_NO,UNI_SC_ID,ESTABLISH_DATE,ORGAN_CODE,TAXPAYERS_CODE,CORP_STATUS,CORP_TYPE"
				+ ",PERSON_NAME,REG_CAPITAL,TELEPHONE,BUSINESS_ADDRESS,TAX_CHGE_DATE,TAX_CHGE_CONTENT from CORP_INFO " + "where CORP_NAME = '" + keyword + "' or ORGAN_CODE = '"
				+ keyword + "' or UNI_SC_ID = '" + keyword + "' ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("corp_status"))) {
						result.put("corp_status", DicDataUtils.getInstance().getDicData(1051, result.get("corp_status")));
					}
					if (!Strings.isEmpty(result.get("corp_type"))) {
						result.put("corp_type", DicDataUtils.getInstance().getDicData(1052, result.get("corp_type")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 根据法人名称、社会统一信用代码、组织机构代码返回资质信息
	// 商务委
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpLicenseToShangwuwei(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().or("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword);
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, cri);
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			Sql sql = Sqls.create("select UNIT_NAME,LICENSE_DATA,ORGAN_CODE,UNI_SC_ID,LICENSE_TYPE,BUSINESS_SCOPE" + " from CORP_LICENSE " + "where CORP_INFO_ID = '" + corpInfoId
					+ "'");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						if (!Strings.isEmpty(map.get("license_type"))) {
							map.put("license_type", DicDataUtils.getInstance().getDicData(1060, map.get("license_type")));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、社会统一信用代码、组织机构代码返回处罚信息
	// 商务委
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpCfToShangwuwei(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().or("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword);
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, cri);
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			List<PunishNoteCorpEntyBean> punishnotecorp = dao.query(PunishNoteCorpEntyBean.class, Cnd.where("CORP_INFO_ID", "=", corpInfoId));
			List<Object> list = new ArrayList<Object>();
			for (PunishNoteCorpEntyBean enty : punishnotecorp) {
				double punishEntyId = enty.getPunishentyid();
				Sql sql = Sqls.create("select PUNISH_CODE,PUNISH_UNIT,PUNISH_BASIS,PUNISH_MEASURES,PUNISH_LIMIT," + "PUNISH_DATE,FROM_DEPT_ID from punish_note_enty "
						+ "where punish_enty_id = '" + punishEntyId + "'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
						if (rs.next()) {
							Map<String, String> map = new HashMap<String, String>();
							ResultSetMetaData rsmd = rs.getMetaData();
							int col = rsmd.getColumnCount();
							for (int i = 1; i <= col; i++) {
								map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
							}
							if (!Strings.isEmpty((CharSequence) map.get("from_dept_id"))) {
								map.put("from_dept_id", DicDataUtils.getInstance().getDicData(1064, map.get("from_dept_id")));
							}
							return map;
						}
						return null;
					}
				});
				dao.execute(sql);
				Map<String, String> res = (Map<String, String>) sql.getResult();
				list.add(res);
			}
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、社会统一信用代码、组织机构代码、营业执照注册号、纳税人识别号返回登记信息
	// 科委
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpInfoToKewei(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls.create("select CORP_NAME,ORGAN_CODE,CORP_TYPE,PERSON_NAME,ADDRESS,CORP_STATUS,UNI_SC_ID,"
				+ "ESTABLISH_DATE,REG_CAPITAL,BUSINESS_SCOPE,INDUSTRY_CODE,REG_NO,CURRENCY,REPEAL_REASON,REPEAL_DATE,"
				+ "CHANGE_DATE,TAXPAYERS_CODE,TAX_REG_DATE,TAX_CHGE_CONTENT,TAX_CHGE_DATE,TAX_REPEAL_REASON,"
				+ "TAX_REPEAL_DATE,TAX_REPEAL_ORGAN,TAX_UPD_DATE,BUSINESS_ADDRESS from CORP_INFO " + "where CORP_NAME = '" + keyword + "' or ORGAN_CODE = '" + keyword
				+ "' or UNI_SC_ID = '" + keyword + "' " + " or TAXPAYERS_CODE = '" + keyword + "' or REG_NO = '" + keyword + "' ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("corp_status"))) {
						result.put("corp_status", DicDataUtils.getInstance().getDicData(1051, result.get("corp_status")));
					}
					if (!Strings.isEmpty(result.get("corp_type"))) {
						result.put("corp_type", DicDataUtils.getInstance().getDicData(1052, result.get("corp_type")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询法人登记信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, String> getCorpInfoToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Sql sql = Sqls.create("select CORP_INFO_ID,CORP_NAME,ORGAN_CODE,CORP_TYPE,PERSON_NAME,ADDRESS,CORP_STATUS,"
				+ "ZIP,TELEPHONE,UNI_SC_ID,ESTABLISH_DATE,CURRENCY,REG_CAPITAL,BUSINESS_SCOPE,INDUSTRY_CODE,"
				+ "ORGANIZERS,FUNDING_SRC,REG_NO,REPEAL_REASON,CHANGE_ITEM,REPEAL_DATE,CHANGE_DATE,"
				+ "BRANCH_NUM,REPRESENT_NUM,REG_UPD_DATE,TAXPAYERS_CODE,TAX_CODE,TAX_REG_DATE,"
				+ "TAX_CHGE_CONTENT,TAX_CHGE_DATE,TAX_REPEAL_REASON,TAX_REPEAL_DATE,BUSINESS_ADDRESS,"
				+ "TAX_REPEAL_ORGAN,TAX_UPD_DATE,ORGANCODE_DATE,ORGCODE_CHGDATE,ORGCODE_REPEALDATE,QS_UPD_DATE" + " from CORP_INFO where CORP_NAME = '" + keyword
				+ "' or ORGAN_CODE = '" + keyword + "' or UNI_SC_ID = '" + keyword + "'  ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				if (rs.next()) {
					Map<String, String> result = new HashMap<String, String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					int col = rsmd.getColumnCount();
					for (int i = 1; i <= col; i++) {
						result.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
					if (!Strings.isEmpty(result.get("industry_code"))) {
						result.put("industry_code", DicDataUtils.getInstance().getDicData(1049, result.get("industry_code")));
					}
					if (!Strings.isEmpty(result.get("corp_type"))) {
						result.put("corp_type", DicDataUtils.getInstance().getDicData(1052, result.get("corp_type")));
					}
					return result;
				}
				return null;
			}
		});
		dao.execute(sql);
		Map<String, String> result = (Map<String, String>) sql.getResult();
		ApiLogWebService log = WsUitl.createlog(serviceUser, 1);
		dao.insert(log);
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询法人资质信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpLicenseToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword);
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, cri);
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			Sql sql = Sqls.create("select LICENSE_ID,UNI_SC_ID,UNIT_PROPERTY,LICENSE_TYPE,ORGAN_CODE,BUREAU_CODE,"
					+ "LICENSE_STATUS,LICENSE_CODE,LICENSE_DATA,START_DATE,END_DATE,PERSON_NAME,"
					+ "BUSINESS_SCOPE,UNIT_ADDRESS,ECONOMIC_NATURE,TIME_LIMIT,ETPS_TYPE,BUSINESS_ADDRESS,"
					+ "BUSINESS_METHOD,REG_ADDRESS,UNIT_PERSON,WORK_SCOPE,WORK_ADDRESS,WORK_METHOD,"
					+ "PERMISSION_SCOPE,ESTABLISH_DATE,VALIDITY_AREA,QUALI_LEVEL,BUSINESS_CATEGORY,"
					+ "MANAGE_ORGAN,QUALITY_MANAGER,BELONG_UNIT,ETPS_ADDRESS,WAREHOUSE_ADDR,REG_NO,REG_DATE," + "CDO_CATEGORY " + "from CORP_LICENSE " + "where CORP_INFO_ID = '"
					+ corpInfoId + "' order by START_DATE desc nulls last");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						if (!Strings.isEmpty(map.get("license_type"))) {
							map.put("license_type", DicDataUtils.getInstance().getDicData(1060, map.get("license_type")));
						}
						if (!Strings.isEmpty(map.get("bureau_code"))) {
							map.put("bureau_code", DicDataUtils.getInstance().getDicData(1064, map.get("bureau_code")));
						}
						if (!Strings.isEmpty(map.get("license_status"))) {
							map.put("license_status", DicDataUtils.getInstance().getDicData(1043, map.get("license_status")));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询法人处罚信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCorpCfToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, Cnd.where("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword));
		if (corpinfo != null) {
			String corpInfoId = corpinfo.getCorpinfoid();
			List<PunishNoteCorpEntyBean> punishnotecorp = dao.query(PunishNoteCorpEntyBean.class, Cnd.where("CORP_INFO_ID", "=", corpInfoId));
			String str = "select PUNISH_ENTY_ID,CORP_NAME,CORP_ADDRESS,ORGAN_CODE,ILLEGAL_EVIDENCE,"
					+ "ILLEGAL_RULE,VALIDITY,ILLEGAL_CONTEXT,ILLEGAL_ADDRESS,ILLEGAL_DATE,TELEPHONE,ZIP,"
					+ "PERSON_NAME,PERSON_TITLE,REG_NO,ENTITY_ID,TAX_CODE,TEAM_NAME,PUNISH_LIMIT,UNIT_PROPERTY,"
					+ "PUNISH_BASIS,PUNISH_UNIT,AREA_CODE,PUNISH_CODE,PUNISH_CONTENT,FROM_DEPT_ID,PUNISH_DATE,ANNOUNCE_REASON,PUNISH_MEASURES" + " from punish_note_enty "
					+ "where punish_enty_id in (";
			StringBuffer sqlstr = new StringBuffer(str);
			int i = 0 ;
			for (PunishNoteCorpEntyBean enty : punishnotecorp) {
				double punishEntyId = enty.getPunishentyid();
				sqlstr.append(punishEntyId);
				if(i<punishnotecorp.size()-1){
					sqlstr.append(",");
					i++;
				}
			}
			sqlstr.append(") order by PUNISH_DATE desc nulls last");
			Sql sql = Sqls.create(sqlstr.toString());
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						if (!Strings.isEmpty((CharSequence) map.get("from_dept_id"))) {
							map.put("from_dept_id", DicDataUtils.getInstance().getDicData(1064, map.get("from_dept_id")));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询网上大厅信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, Object> getWangtingToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, Cnd.where("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword));
		if (corpinfo != null) {
			String corpInfoName = corpinfo.getCorpname();
			Sql sql = Sqls.create("select SUID,AFFAIR_NAME,MAIN_DEPARTMENT,TYPE,APPLY_NAME,APPLICANT,CERTIFICATE_TYPE,CERTIFICATE_NO,CONTACT_NAME,CONTACT_TEL,RESULT,APPLY_TIME,"
					+ "ACCEPT_TIME,FINISH_TIME,SUBMIT_TYPE  from PT_QFB_WANGTING where APPLICANT = '" + corpInfoName + "' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询信用信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, Object> getXyxxToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, Cnd.where("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword));
		if (corpinfo != null) {
			String corpInfoName = corpinfo.getCorpname();
			Sql sql = Sqls.create("select * from V_XYPT_DATA where name = '" + corpInfoName + "' and fyzrr = '法人' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}
						for (int j = 1; j < 21; j++) {
							String dm = rs.getString("ZYDM");
							String xl = "A" + j;
							if (rs.getString(xl) != null) {
								XyxxCloumn cloumn = dao.fetch(XyxxCloumn.class, Cnd.where("ZYDM", "=", dm).and("TABLECOLUMN", "=", xl));
								map.put(xl + "_CN", cloumn.getModdelcolumn());
							}
						}
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

	// 根据法人名称、组织机构代码、社会统一信用代码查询一数一源数据填报信息
	// 行政服务中心
	@SuppressWarnings("unchecked")
	public Map<String, Object> getYsyyToXingzheng(String keyword, ApiServiceUser serviceUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfoBean corpinfo = dao.fetch(CorpInfoBean.class, Cnd.where("CORP_NAME", "=", keyword).or("ORGAN_CODE", "=", keyword).or("UNI_SC_ID", "=", keyword));
		if (corpinfo != null) {
			String corpInfoName = corpinfo.getCorpname();
			Sql sql = Sqls.create("select CORP_NAME,TB_DATA_ID,DATAMONTH,SHEET_NAME from TB_CORP where CORP_NAME = '" + corpInfoName + "' order by DATAMONTH desc ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()) {
						Map<String, String> map = new HashMap<String, String>();
						ResultSetMetaData rsmd = rs.getMetaData();
						int col = rsmd.getColumnCount();
						for (int i = 1; i <= col; i++) {
							map.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
						}

						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list = (List<Object>) sql.getResult();
			if (list.size() == 0) {
				result.put("list", null);
			} else {
				result.put("list", list);
			}
			ApiLogWebService log = WsUitl.createlog(serviceUser, list.size());
			dao.insert(log);
		}
		return result;
	}

}
