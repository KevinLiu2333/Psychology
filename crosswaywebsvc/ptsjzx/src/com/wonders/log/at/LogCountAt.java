package com.wonders.log.at;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.api.entity.ApiService;
import com.wonders.echarts.entity.EchartsData;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.query.entity.CorpInfo;
import com.wonders.query.entity.CorpLicense;
import com.wonders.query.entity.PunishNoteEnty;
import com.wonders.query.entity.PunishNoteInfoEnty;
import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbContents;
import com.wonders.sjtb.entity.TbSheet;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.ws.bean.PunishNoteCorpEntyBean;
import com.wonders.zymlgl.entity.Resource;

import net.sf.json.JSONObject;

@IocBean
@At("/logcount")
public class LogCountAt {

	@Inject
	private Dao	dao;

	@At
	@Ok("jsp:jsp.log.countindex")
	public Map<String, Object> toCount() {
		return null;

	}

	@At
	@Ok("jsp:jsp.log.countone")
	public Map<String, Object> toCountOne() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("usercount", dao.count(User.class, Cnd.where("STATE", "=", "1")));
		result.put("key", dao.count(User.class, Cnd.where("ROLE_ID", "like", "%6%")));
		Sql sql = Sqls
				.create("select a.JOBID,a.OUTPUTNUM,a.END_TIME from T_ETLLOG a where not exists(select 1 from T_ETLLOG where JOBID = a.JOBID and END_TIME > a.END_TIME) order by a.JOBID desc");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					if ("T_GA_RJBXX_PT".equals(rs.getString(1))) {
						map.put("sjlx", "人口落地数据");
					} else if ("T_GA_RHFL_CJ_PT".equals(rs.getString(1))) {
						map.put("sjlx", "人户分离落地数据");
					} else {
						map.put("sjlx", "房屋落地数据");
					}
					map.put("rksl", rs.getInt(2));
					map.put("rktime", rs.getString(3).substring(0, 8));
					list.add(map);
				}
				return list;
			}
		});
		dao.execute(sql);
		result.put("rksj", sql.getResult());
		result.put(
				"frsj",
				dao.count(CorpInfo.class) + dao.count(CorpLicense.class) + dao.count(PunishNoteCorpEntyBean.class) + dao.count(PunishNoteEnty.class)
						+ dao.count(PunishNoteInfoEnty.class));
		result.put("frti", new SimpleDateFormat("yyyyMMdd").format(new Date()));
		result.put("fwdb", dao.count(DwLog.class, Cnd.where("COUNT_TYPE", "=", "8")));
		result.put("rkdb", dao.count(DwLog.class, Cnd.where("COUNT_TYPE", "=", "9")));
		result.put("frdb", dao.count(DwLog.class, Cnd.where("COUNT_TYPE", "=", "10")));
		List<DwLog> list = dao.query(DwLog.class, Cnd.where("COUNT_TYPE", "=", "8").desc("OPERATE_DATE"), dao.createPager(1, 1));
		result.put("fwdbtime", list.get(0).getOperateDate());
		list = dao.query(DwLog.class, Cnd.where("COUNT_TYPE", "=", "9").desc("OPERATE_DATE"), dao.createPager(1, 1));
		result.put("rkdbtime", list.get(0).getOperateDate());
		list = dao.query(DwLog.class, Cnd.where("COUNT_TYPE", "=", "10").desc("OPERATE_DATE"), dao.createPager(1, 1));
		result.put("frdbtime", list.get(0).getOperateDate());
		return result;

	}

	@At
	@Ok("jsp:jsp.log.counttwo")
	public Map<String, Object> toCountTwo() {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("THEME_ID", "=", "39");
		cri.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> list = dao.query(EchartsData.class, cri, dao.createPager(1, 1));
		if (list != null && list.size() > 0) {
			JSONObject json = JSONObject.fromObject(list.get(0).getDataJson());
			result.put("bqhj", json.get("bqhj"));
			result.put("lhry", json.get("lhry"));
			result.put("jwry", json.get("jwry"));
		}
		Criteria cri1 = Cnd.cri();
		cri1.where().and("THEME_ID", "=", "41");
		cri1.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> list1 = dao.query(EchartsData.class, cri1, dao.createPager(1, 1));
		String rzhz = "0";
		if (list1 != null && list1.size() > 0) {
			JSONObject json = JSONObject.fromObject(list1.get(0).getDataJson());
			for (Iterator<?> iter = json.keys(); iter.hasNext();) {
				String keytemp = (String) iter.next();
				rzhz = json.getString(keytemp);
			}
		}
		Criteria cri2 = Cnd.cri();
		cri2.where().and("THEME_ID", "=", "42");
		cri2.getOrderBy().desc("CREATE_DATE");
		List<EchartsData> list2 = dao.query(EchartsData.class, cri2, dao.createPager(1, 1));
		String rzhz1 = "0";
		if (list2 != null && list2.size() > 0) {
			JSONObject json1 = JSONObject.fromObject(list2.get(0).getDataJson());
			for (Iterator<?> iter1 = json1.keys(); iter1.hasNext();) {
				String keytemp1 = (String) iter1.next();
				rzhz1 = json1.getString(keytemp1);
			}
		}
		result.put("rhfl", Integer.parseInt(rzhz) + Integer.parseInt(rzhz1));
		result.put("yxfr", dao.count(CorpInfo.class, Cnd.where("CORP_STATUS", "=", "0001")));
		result.put("frzz", dao.count(CorpLicense.class));
		result.put("frcf", dao.count(PunishNoteEnty.class) + dao.count(PunishNoteInfoEnty.class) + dao.count(PunishNoteCorpEntyBean.class));
		result.put("rrk", dao.count(Resource.class, Cnd.where("RESOURCE_TYPE", "=", "r_rkl").and("VALIDITY", "=", "1")));
		result.put("rfr", dao.count(Resource.class, Cnd.where("RESOURCE_TYPE", "=", "r_frl").and("VALIDITY", "=", "1")));
		result.put("rjj", dao.count(Resource.class, Cnd.where("RESOURCE_TYPE", "=", "r_cyjjl").and("VALIDITY", "=", "1")));
		List<TbContents> content = dao.query(TbContents.class, null);
		int sjl = 0;
		for (TbContents con : content) {
			Sql sql = Sqls.create("select count(*) from " + con.getTableName());
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while (rs.next()) {
						return rs.getInt(1);
					}
					return 0;
				}
			});
			dao.execute(sql);
			sjl += (Integer) sql.getResult();

		}
		result.put("rsjl", sjl);
		return result;
	}

	@At
	@Ok("jsp:jsp.log.countthree")
	public Map<String, Object> toCountThree() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rkzyx", dao.count(TbColumns.class, Cnd.where("TABLE_BM", "like", "%rk")));
		result.put("frzyx", dao.count(TbColumns.class, Cnd.where("TABLE_BM", "like", "%fr")));
		result.put("jjzyx", dao.count(TbColumns.class, Cnd.where("TABLE_BM", "like", "%jjl")));
		result.put("rkzy", dao.count(TbSheet.class, Cnd.where("TABEL_BM", "like", "%rk")));
		result.put("frzy", dao.count(TbSheet.class, Cnd.where("TABEL_BM", "like", "%fr")));
		result.put("jjzy", dao.count(TbSheet.class, Cnd.where("TABEL_BM", "like", "%jjl")));
		List<TbContents> content = dao.query(TbContents.class, null);
		int rksj = 0;
		int frsj = 0;
		int jjsj = 0;
		for (TbContents con : content) {
			Sql sql = Sqls.create("select count(*) from " + con.getTableName() + " where data_year>=201606");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while (rs.next()) {
						return rs.getInt(1);
					}
					return 0;
				}
			});
			dao.execute(sql);
			if (1 == con.getOrderNo()) {
				rksj += (Integer) sql.getResult();
			} else if (2 == con.getOrderNo()) {
				frsj += (Integer) sql.getResult();
			} else {
				jjsj += (Integer) sql.getResult();
			}

		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		String Moth = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
		int mrksj = 0;
		int mfrsj = 0;
		int mjjsj = 0;
		for (TbContents con : content) {
			Sql sql = Sqls.create("select count(*) from " + con.getTableName() + " where DATA_YEAR='" + Moth + "'");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while (rs.next()) {
						return rs.getInt(1);
					}
					return 0;
				}
			});
			dao.execute(sql);
			if (1 == con.getOrderNo()) {
				mrksj += (Integer) sql.getResult();
			} else if (2 == con.getOrderNo()) {
				mfrsj += (Integer) sql.getResult();
			} else {
				mjjsj += (Integer) sql.getResult();
			}

		}
		result.put("rksj", rksj);
		result.put("frsj", frsj);
		result.put("jjsj", jjsj);
		result.put("mrksj", mrksj);
		result.put("mfrsj", mfrsj);
		result.put("mjjsj", mjjsj);
		return result;
	}

	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.log.countfour")
	public Map<String, Object> toCountFour(HttpSession session, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().andEquals("VALID", "1");
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> list = dao.query(ApiService.class, cri);
		for (ApiService service : list) {
			String calltime = "select count(1) times from API_LOG_WS where CALL_SERVICE_ID='" + service.getId() + "'";
			String callresult = "select sum(CALL_RESULT) callresult,max(CALL_DATE) from API_LOG_WS where CALL_SERVICE_ID='" + service.getId() + "'";
			Sql calltimeSql = Sqls.create(calltime);
			Sql callresultsSql = Sqls.create(callresult);
			calltimeSql.setCallback(new SqlCallback() {

				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while (rs.next()) {
						return rs.getInt("times");
					}
					return null;
				}
			});
			callresultsSql.setCallback(new SqlCallback() {

				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					while (rs.next()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("callresult", rs.getLong(1));
						map.put("last", rs.getString(2));
						return map;
					}
					return null;
				}
			});
			dao.execute(callresultsSql, calltimeSql);
			service.setCalltime((Integer) calltimeSql.getResult());
			Map<String, Object> map = (Map<String, Object>) callresultsSql.getResult();
			service.setCallresult((Long) map.get("callresult"));
			service.setSummary((String) map.get("last"));
		}
		int times = 0;
		int getresult = 0;
		for (ApiService ser : list) {
			times += ser.getCalltime();
			getresult += ser.getCallresult();
		}
		result.put("list", list);
		result.put("times", times);
		result.put("getresult", getresult);
		Sql sql = Sqls.create("select SERVICE_NAME,sum(inputnum) as suminputnum,sum(CALL_NUM) as sumcallnum,max(INPUTDATE) as lastime "
				+ "from interface_receive_log t  group by SERVICE_NAME ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				List<Map<String, Object>> apilist = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("sername", rs.getString(1));
					map.put("input", rs.getInt(2));
					map.put("intime", rs.getInt(3));
					map.put("lasttime", rs.getString(4).substring(0, 8));
					apilist.add(map);
				}
				return apilist;
			}
		});
		dao.execute(sql);
		List<Map<String, Object>> apilist = (List<Map<String, Object>>) sql.getResult();
		int alltimes = 0;
		int allresult = 0;
		for (Map<String, Object> api : apilist) {
			alltimes += (Integer) api.get("intime");
			allresult += (Integer) api.get("input");
		}
		result.put("alltimes", alltimes);
		result.put("allresult", allresult);
		result.put("apilist", apilist);
		return result;
	}

	// 人口数据落地详情
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.log.log_personDetails")
	public Map<String, Object> toPersonDetails(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Sql sql = Sqls.create("select to_char(operate_date,'yyyymmdd') as operate_date,sum(operate_size) as sum from log_bacrecman "
				+ "where operate_dept_from in ('T_GA_FWJBXX','T_GA_RHFL_CJ','T_GA_RJBXX') group by operate_date order by operate_date desc");
		sql.setPager(pager);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql sql) throws SQLException {
				List<Object> res = new ArrayList<Object>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					String date = rs.getString(1);
					map.put("date", date);
					map.put("sum", rs.getString(2));
					Sql sql1 = Sqls.create("select operate_size from log_bacrecman where operate_dept_from = 'T_GA_RJBXX' and operate_type = '备份' "
							+ "and to_char(operate_date,'yyyymmdd') = '" + date + "' ");
					sql1.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql sql1) throws SQLException {
							if (rs.next())
								return rs.getString(1);
							return null;
						}
					});
					dao.execute(sql1);
					map.put("rjbxx", sql1.getResult());
					sql1 = Sqls.create("select operate_size from log_bacrecman where operate_dept_from = 'T_GA_RHFL_CJ' and operate_type = '备份' "
							+ "and to_char(operate_date,'yyyymmdd') = '" + date + "' ");
					sql1.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql sql1) throws SQLException {
							if (rs.next())
								return rs.getString(1);
							return null;
						}
					});
					dao.execute(sql1);
					map.put("rhfl", sql1.getResult());
					sql1 = Sqls.create("select operate_size from log_bacrecman where operate_dept_from = 'T_GA_FWJBXX' and operate_type = '备份' "
							+ "and to_char(operate_date,'yyyymmdd') = '" + date + "' ");
					sql1.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection arg0, ResultSet rs, Sql sql1) throws SQLException {
							if (rs.next())
								return rs.getString(1);
							return null;
						}
					});
					dao.execute(sql1);
					map.put("fwjbxx", sql1.getResult());
					res.add(map);
				}
				return res;
			}
		});
		dao.execute(sql);
		List<Object> list = (List<Object>) sql.getResult();
		sql = Sqls.create("select count(1) from (select to_char(operate_date,'yyyymmdd') as operate_date,sum(operate_size) as sum from log_bacrecman "
				+ "where operate_dept_from in ('T_GA_FWJBXX','T_GA_RHFL_CJ','T_GA_RJBXX') group by operate_date order by operate_date desc)");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
				if (rs.next())
					return rs.getInt(1);
				return 0;
			}
		});
		dao.execute(sql);
		pager.setRecordCount((Integer) sql.getResult());
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}

	// 街镇人口分类统计
	@At
	@Ok("jsp:jsp.log.log_personCount")
	public Map<String, Object> toPersonCount() {
		return null;

	}

	// 数据表数量统计
	@SuppressWarnings({ "unchecked" })
	@At
	@Ok("jsp:jsp.log.dataContentCount")
	public Map<String, Object> toDataContentCount(final String tablename) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean queryflag = false;
		if (!Strings.isEmpty(tablename)) {
			queryflag = true;
		}
		if (queryflag) {
			try {
				//格式化十进制数字，保留两位小数
				final DecimalFormat formater = new DecimalFormat();
		        formater.setMaximumFractionDigits(2);
		        formater.setGroupingSize(0);
		        formater.setRoundingMode(RoundingMode.FLOOR);
		        
				Sql sql1 = Sqls.create("select count(1) from " + tablename + " ");
				sql1.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql sql1) throws SQLException {
						if(rs.next())
							return rs.getDouble(1);
						return null;
					}
				});
				dao.execute(sql1);
				final double countAll = (Double) sql1.getResult();
				result.put("count", countAll);
				String sqlstr = "SELECT t.COLUMN_NAME,C.COMMENTS  FROM user_tab_columns t ,user_col_comments c"
						+ " where T.COLUMN_NAME = C.COLUMN_NAME and T.TABLE_NAME = C.TABLE_NAME " + "and t.table_name = upper('" + tablename + "') ";
				Sql sql = Sqls.create(sqlstr);
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
						List<Object> listResult = new ArrayList<Object>();
						while (rs.next()) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("name", rs.getString(1));
							map.put("comments", rs.getString(2));
							Sql sql2 = Sqls.create("select count(1) from " + tablename + " where " + rs.getString(1) + " is not null");
							sql2.setCallback(new SqlCallback() {
								@Override
								public Object invoke(Connection arg0, ResultSet rs, Sql sql2) throws SQLException {
									if (rs.next())
										return rs.getDouble(1);
									return null;
								}
							});
							dao.execute(sql2);
							double count = (Double) sql2.getResult();
							map.put("count", (int) count);
							double ratio = ( count / countAll ) * 100.00 ;
							map.put("ratio", formater.format(ratio));
							listResult.add(map);
						}
						return listResult;
					}
				});
				dao.execute(sql);
				List<Object> list = (List<Object>) sql.getResult();
				result.put("list", list);
				
				result.put("flag","1");
			} catch (Exception e) {
				result.put("flag", "所查表不存在！");
				e.printStackTrace();
			}
		}
		result.put("tablename", tablename);
		return result;
	}

}
