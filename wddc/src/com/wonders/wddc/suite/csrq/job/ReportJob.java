package com.wonders.wddc.suite.csrq.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
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

@IocBean
public class ReportJob implements Job {
	/**
	 * 
	 * 报表定期更新
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		task();
	}

	private void task() {
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
		List<ReportInfoBo> query = dao.query(ReportInfoBo.class, Cnd.where(
				"state", "=", "1"));
		for (ReportInfoBo info : query) {
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
			// 1、获取数据
			List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 结果列表
			List<ReportDataBo> datas = dao.query(ReportDataBo.class, Cnd.where(
					"report_info_id", "=", info.getId()));
			// 2、整理数据
			for (ReportDataBo data : datas) {
				Criteria cri = Cnd.cri();
				cri.where().and("INFO_ID", "=", data.getMtdmid());
				cri.getOrderBy().desc("COUNT_TIME");
				List<MultStatResultBo> resultlist = dao.query(
						MultStatResultBo.class, cri, dao.createPager(1, 1));
				MultStatResultBo result = (resultlist == null || resultlist
						.size() == 0) ? null : resultlist.get(0);
				List<Map<String, String>> list = result.getResult();
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
			info.setUpdatetime(new Date());
			dao.update(info);
			dao.insert(result);
		}
	}

	
}
