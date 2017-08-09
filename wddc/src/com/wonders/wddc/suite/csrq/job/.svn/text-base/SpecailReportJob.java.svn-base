package com.wonders.wddc.suite.csrq.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.wddc.suite.csrq.entity.ReportDataBo;
import com.wonders.wddc.suite.csrq.entity.ReportHeaderBo;
import com.wonders.wddc.suite.csrq.entity.ReportInfoBo;
import com.wonders.wddc.suite.csrq.entity.ReportResultBo;
import com.wonders.wddc.suite.csrq.entity.ReportRowBo;
import com.wonders.wddc.suite.csrq.generator.ReportHtmlGenerator;
import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.entity.MultStatResultBo;
import com.wonders.wddc.tiles.tools.DateUtils;

/**
 * 月报、周报、年报定时器
 * @author vcixp
 *
 */
@IocBean
public class SpecailReportJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
		Criteria cri = Cnd.cri();
		cri.where().and("state", "=", "1");
		List<ReportInfoBo> query = dao.query(ReportInfoBo.class, cri);
		for (ReportInfoBo info : query) {
			boolean needrun = false;
			if (!Strings.isEmpty(info.getReportType())) {// 如果是月报、周报或年报。
				Calendar now = Calendar.getInstance();
				now.setTime(new Date());
				if ("1".equals(info.getReportType())
						&& now.get(Calendar.DAY_OF_WEEK) == (info.getDays() % 8 == 0 ? 1
								: info.getDays() % 8)) {
					needrun = true;
				}
				if ("2".equals(info.getReportType())
						&& now.get(Calendar.DAY_OF_MONTH) == (info.getDays() % 30 == 0 ? 1
								: info.getDays() % 30)) {
					needrun = true;
				}
				if ("3".equals(info.getReportType())
						&& now.get(Calendar.DAY_OF_YEAR) == (info.getDays() % 365 == 0 ? 1
								: info.getDays() % 365)) {
					needrun = true;
				}
			}
			if (!needrun) {//如果不是
				continue;
			}
			String id = info.getId();
			if (info != null) {
				info.setDatas(dao.query(ReportDataBo.class, Cnd.where(
						"report_info_id", "=", id)));
			}
			if (info.getDatas() != null && info.getDatas().size() > 0) {

				for (ReportDataBo data : info.getDatas()) {
					MultStatInfoBo multStatInfo = dao.fetch(
							MultStatInfoBo.class, Cnd.where("id", "=", data
									.getMtdmid()));
					if (multStatInfo != null) {
						DBinfoBo dbinfo = dao.fetch(DBinfoBo.class, Cnd.where(
								"id", "=", multStatInfo.getDatabaseid()));
						Dao dao1 = DBAdapter.getDao(dbinfo);
						Sql sql = Sqls.create(multStatInfo.getSql());
						sql.setCallback(new SqlCallback() {
							@Override
							public Object invoke(Connection conn, ResultSet rs,
									Sql arg2) throws SQLException {
								List<Map<String, String>> result = new ArrayList<Map<String, String>>();
								ResultSetMetaData rsmd = rs.getMetaData();
								int col = rsmd.getColumnCount();
								while (rs.next()) {
									Map<String, String> map = new HashMap<String, String>();
									for (int i = 1; i <= col; i++) {
										map.put(rsmd.getColumnLabel(i)
												.toLowerCase(), rs
												.getString(rsmd
														.getColumnLabel(i)));
									}
									result.add(map);
								}
								return result;
							}
						});
						dao1.execute(sql);
						@SuppressWarnings("unchecked")
						List<Map<String, String>> re = (List<Map<String, String>>) sql
								.getResult();
						MultStatResultBo result = new MultStatResultBo();
						result.setId(UUID.randomUUID().toString().replaceAll(
								"-", ""));
						result.setInfoid(info.getId());
						result.setResult(re);
						result.setCountTime(new Date());
						dao.insert(result);
					}
				}

			}
			if (info != null) {
				info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
						"reportinfoid", "=", id)));
				info.setRows(dao.query(ReportRowBo.class, Cnd.where(
						"reportinfoid", "=", id)));
			}
			specialreport(dao, info);
		}
	}
	
	// 周报月报，年报
	public void specialreport(Dao dao, ReportInfoBo info) {
		// 1、获取数据
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 结果列表
		List<ReportDataBo> datas = dao.query(ReportDataBo.class, Cnd.where(
				"report_info_id", "=", info.getId()));
		// 2、整理数据
		for (ReportDataBo data : datas) {
			List<Map<String, String>> list = handledata(dao, data, info
					.getReportType());
			if (list != null) {
				resultList.addAll(list);
			}
		}
		// 3、将数据塞到html相应的位置
		String html = info.getHtml();
		Document doc = Jsoup.parse(html);
		doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
		// 4、保存结果
		ReportResultBo result = new ReportResultBo();
		result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		result.setReportinfoid(info.getId());
		result.setResult(doc.head().html() + doc.body().html());
		result.setUpdatetime(new Date());
		result.setType(info.getReportType());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if("1".equals(info.getReportType())){
			result.setVersion(cal.get(Calendar.YEAR)+"--"+cal.get(Calendar.WEEK_OF_YEAR));
		}
		if("2".equals(info.getReportType())){
			result.setVersion(cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH));
		}
		if("3".equals(info.getReportType())){
			result.setVersion(cal.get(Calendar.YEAR)+"");
		}
		dao.insert(result);
	}

	// 处理数据
	public List<Map<String, String>> handledata(Dao dao, ReportDataBo data,
			String type) {
		Calendar t1 = Calendar.getInstance();
		Calendar t2 = Calendar.getInstance();
		t1.setTime(new Date());
		t1.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.DAY_OF_MONTH),  
                0, 0, 0);
		t2.set(t1.get(Calendar.YEAR), t1.get(Calendar.MONTH), t1.get(Calendar.DAY_OF_MONTH),  
                23, 59, 59);
		Criteria cri = Cnd.cri();
		cri.where().and("INFO_ID", "=", data.getMtdmid());
		cri.where().and("COUNT_TIME",">=" ,t1.getTime());
		cri.where().and("COUNT_TIME","<=" ,t2.getTime());
		cri.getOrderBy().desc("COUNT_TIME");
		List<MultStatResultBo> resultlist1 = dao.query(MultStatResultBo.class,
				cri, dao.createPager(1, 1));
		MultStatResultBo result1 = (resultlist1 == null || resultlist1.size() == 0) ? null
				: resultlist1.get(0);
		cri = Cnd.cri();
		cri.where().and("INFO_ID", "=", data.getMtdmid());
		if("1".equals(type)){
			cri.where().and("COUNT_TIME",">=" ,DateUtils.addDays(t1.getTime(), -7));
			cri.where().and("COUNT_TIME","<=" ,DateUtils.addDays(t2.getTime(), -7));
		}
		if("2".equals(type)){
			cri.where().and("COUNT_TIME",">=" ,DateUtils.addMonths(t1.getTime(), -1));
			cri.where().and("COUNT_TIME","<=" ,DateUtils.addMonths(t2.getTime(), -1));
		}
		if("3".equals(type)){
			cri.where().and("COUNT_TIME",">=" ,DateUtils.addYears(t1.getTime(), -1));
			cri.where().and("COUNT_TIME","<=" ,DateUtils.addYears(t2.getTime(), -1));
		}
		cri.getOrderBy().desc("COUNT_TIME");
		List<MultStatResultBo> resultlist2 = dao.query(MultStatResultBo.class,
				cri, dao.createPager(1, 1));
		MultStatResultBo result2 = (resultlist2 == null || resultlist2.size() == 0) ? null
				: resultlist2.get(0);
		List<Map<String, String>> list1 = result1.getResult();
		List<Map<String, String>> list2 = result2.getResult();
		if(list1==null||list2==null){
			return new ArrayList<Map<String, String>>();
		}else {//增量处理
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			for(Map<String, String> map1:list1){
				boolean flag = true;
				for(int i=0;i<list2.size();i++){
					Map<String, String> map2 = list2.get(i);
					if(map1.keySet().size()!=map2.keySet().size()){	//键值对数不同
						continue;
					}
					boolean f = true;				//查看是否是同一项数据
					for(String key : map1.keySet()){
						if("value".equalsIgnoreCase(key)){
							continue;
						}
						if(Strings.isEmpty(map1.get(key))||!map1.get(key).equalsIgnoreCase(map2.get(key))){
							f=false;
							break;
						}
					}
					if(f){
						flag = false;
						String value1 = map1.get("value");
						String value2 = map2.get("value");
						try{
							String value = (Integer.parseInt(value1)-Integer.parseInt(value2))+"";
							map1.put("value", value);
							list.add(map1);
						}catch (Exception e) {
							map1.put("value", "0");
							list.add(map1);
						}
						list2.remove(i);
						break;
					}else {
						continue;
					}
				}
				if(flag){
					list.add(map1);
				}
			}
			return list;
		}
	}
}
