package com.wonders.wddc.suite.csrq.at;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

import com.wonders.wddc.suite.csrq.entity.ReportCacheBo;
import com.wonders.wddc.suite.csrq.entity.ReportDataBo;
import com.wonders.wddc.suite.csrq.entity.ReportHeaderBo;
import com.wonders.wddc.suite.csrq.entity.ReportInfoBo;
import com.wonders.wddc.suite.csrq.entity.ReportResultBo;
import com.wonders.wddc.suite.csrq.entity.ReportRowBo;
import com.wonders.wddc.suite.csrq.generator.ReportHtmlGenerator;
import com.wonders.wddc.suite.csrq.job.FormTimingJob;
import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.entity.MultStatResultBo;
import com.wonders.wddc.tiles.dic.DicDataUtils;
import com.wonders.wddc.tiles.dic.entity.DicConfigBo;
import com.wonders.wddc.tiles.quartz.QuartzManager;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.tools.DateUtils;

import net.sf.json.JSONObject;

/**
 * 报表AT
 * 1.4
 * @author vcixp
 * 
 */
@IocBean
@At("/suite/csrq/report")
public class ReportAct {
	@Inject
	private Dao dao;
	private static Log log = Logs.get();

	/**
	 * 配置报表首页
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ReportInfoBo> list = dao.query(ReportInfoBo.class, null);
		result.put("list", list);
		return result;
	}

	/**
	 * 新增、编辑报表信息
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.edit")
	public Map<String, Object> toEdit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info == null) {
			info = new ReportInfoBo();
		} else {
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
			info.setDatas(dao.query(ReportDataBo.class, Cnd.where(
					"report_info_id", "=", id)));
		}
		result.put("isnotdic", "{\"1\":\"是\",\"0\":\"否\"}");
		result.put("info", info);
		return result;
	}
	
	/**
	 * 高级配置
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.config")
	public Map<String, Object> toConfig(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ReportInfoBo bo = dao.fetch(ReportInfoBo.class, id);
		List<ReportHeaderBo> headers = dao.query(ReportHeaderBo.class, Cnd.where("report_info_id","=",id));
		for (ReportHeaderBo header : headers) {
			DicConfigBo dic = dao.fetch(DicConfigBo.class,header.getDicid());
			if(dic!=null){
				header.setDictitle(dic.getDicName());
			}
		}
		File templatepath=new File(ReportHtmlGenerator.TEMPLATE_PATH);
		File[] templatefiles = templatepath.listFiles();
		List<String> templatelist = new ArrayList<String>();
		for(File templatefile:templatefiles){
			if(templatefile.isFile()){
				String filename=templatefile.getName();
				if(filename.endsWith(".vm")){
					templatelist.add(filename.replace(".vm", ""));
				}
			}
		}
		result.put("filelist", templatelist);
		result.put("headers", headers);
		result.put("bo", bo);
		return result;
	} 
	
	/**
	 * 保存高级配置
	 * @throws ParseException 
	 * @throws SchedulerException 
	 * 
	 */
	@At
	public View saveConfig(String iscache,String hashead,String hasrow,String id,String templetfile,
			String fixedTime,@Param("::headers") List<ReportHeaderBo> headers) throws SchedulerException, ParseException {
		fixedTime=fixedTime.trim();
		templetfile=(Strings.isEmpty(templetfile)?"report":templetfile);
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null){
			info.setIscache(iscache);
			info.setHashead(hashead);
			info.setHasrow(hasrow);
			info.setFixedTime(fixedTime);
			info.setTempletfile(templetfile);
			dao.update(info);
		}
		for(ReportHeaderBo header:headers){
			dao.update(header);
		}
		String jobName = "job_"+id;
		if(fixedTime != null&&!fixedTime.equals("")&&"1".equals(iscache)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);			
			QuartzManager.getInstance().removeJob(jobName);
			QuartzManager.getInstance().addJob(jobName,FormTimingJob.class,fixedTime,param);
		}else if("0".equals(info.getIscache())){
			QuartzManager.getInstance().removeJob(jobName);
		}
		return new ServerRedirectView("/suite/csrq/report/toIndex");
	}

	/**
	 * 保存(报表配置信息)
	 * 
	 * @return
	 */
	@At
	public View save(@Param("::info.") ReportInfoBo info) {
		if (Strings.isEmpty(info.getId())) {
			info.setId(SnCreator.getInstance().getSN("csrq_report", 5, "R"));
		}
		dao.execute(Sqls
				.create("delete from suite_report_header where report_info_id ='"
						+ info.getId() + "'"));
		dao.execute(Sqls
				.create("delete from suite_report_row where report_info_id ='"
						+ info.getId() + "'"));
		dao.execute(Sqls
				.create("delete from suite_report_data where report_info_id ='"
						+ info.getId() + "'"));
		for (ReportHeaderBo header : info.getHeaders()) {
			if (Strings.isEmpty(header.getId())) {
				header.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			if (Strings.isEmpty(header.getReportinfoid())) {
				header.setReportinfoid(info.getId());
			}
			header.setColumnname(header.getColumnname().trim());
			dao.insert(header);
		}
		for (ReportRowBo row : info.getRows()) {
			if (Strings.isEmpty(row.getId())) {
				row.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			if (Strings.isEmpty(row.getReportinfoid())) {
				row.setReportinfoid(info.getId());
			}
			row.setColumnname(row.getColumnname().trim());
			dao.insert(row);
		}
		for (ReportDataBo data : info.getDatas()) {
			if (Strings.isEmpty(data.getId())) {
				data.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			}
			if (Strings.isEmpty(data.getReportinfoid())) {
				data.setReportinfoid(info.getId());
			}
			dao.insert(data);
		}
		ReportInfoBo temp = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", info
				.getId()));
		//判断报表信息是否有动态字典、动态参数项
		boolean dic = true;
		boolean param = true;
		for(ReportDataBo bo:info.getDatas()){
			MultStatInfoBo mult = dao.fetch(MultStatInfoBo.class,bo.getMtdmid());
			if(mult.getSql().indexOf("@")>=0){
				param = false;
				break;
			}
		}
		
		for(ReportRowBo bo:info.getRows()){
			if(bo.getDicid()==-1){
				dic = false;
				break;
			}
		}
		if(dic&&param){
			info.setState("1");
		}else if(dic==false&&param==true){
			info.setState("2");
		}else if(dic==true&&param==false){
			info.setState("3");
		}else{
			info.setState("4");
		}//1：不含动态字典和参数；2：只含动态字典；3：只含动态参数；4：含动态字典和动态参数；
		//默认使用report模板
		if(Strings.isEmail(info.getTempletfile())){
			info.setTempletfile("report");
		}
		if (temp == null) {
			info.setHasrow("0");
			info.setHashead("0");
			String html = ReportHtmlGenerator.creatTable(info);
			info.setHtml(html);
			dao.insert(info);
		} else {
			String html = ReportHtmlGenerator.creatTable(info);
			info.setHtml(html);
			dao.update(info);
		}
		return new ServerRedirectView("/suite/csrq/report/toIndex");
	}

	/**
	 * 编辑html
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.edit2")
	public Map<String, Object> EditHtml(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		result.put("info", info);
		return result;
	}

	/**
	 * 重置页面
	 * 
	 * @param id
	 */
	@At
	public void resetHtml(String id,HttpServletRequest request) {
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
			String html = ReportHtmlGenerator.creatTable(info);
			info.setHtml(html);
			dao.update(info);
		}
		//refreshdata(id,request);
	}

	/**
	 * 设计html
	 * 
	 * @param infoid
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.design")
	public Map<String, Object> toDesign(String infoid) {
		Map<String, Object> result = new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=",
				infoid));
		result.put("info", info);
		return result;
	}

	/**
	 * 保存页面
	 * 
	 * @param infoid
	 * @param html
	 * @return
	 */
	@At
	@Ok("json")
	public Object savehtml(String infoid, String html,HttpServletRequest request) {
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=",
				infoid));
		if (info != null) {
			info.setHtml(html);
			//info.setHtmlState("1");
			dao.update(info);
		}
		return "SUCCESS";
	}

	/**
	 * 删除报表
	 * 
	 * @param id
	 */
	@At
	public void delete(String id) {
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			dao.delete(info);
			dao
					.execute(Sqls
							.create("delete from suite_report_header where report_info_id ='"
									+ info.getId() + "'"));
			dao.execute(Sqls
					.create("delete from suite_report_row where report_info_id ='"
							+ info.getId() + "'"));
			dao
					.execute(Sqls
							.create("delete from suite_report_data where report_info_id ='"
									+ info.getId() + "'"));
		}
	}

	/**
	 * 报表更新（将最新报表结果存储到数据库）
	 * 
	 * @param id
	 * @throws ParseException 
	 */
	@At
	public void update(String id,HttpServletRequest request) throws ParseException {
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
		}
		ReportResultBo result = instantReportResult(id,request);
		info.setUpdatetime(new Date());
		dao.update(info);
		dao.insert(result);
	}
	/**
	 * 获取统计结果信息2016-12-29  含有多个数据项
	 * @param id 报表配置id
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public MultStatResultBo refreshMultStatResult(String id,HttpServletRequest request) throws ParseException{
		MultStatResultBo result = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			info.setDatas(dao.query(ReportDataBo.class, Cnd.where(
					"report_info_id", "=", id)));
		}
		if (info.getDatas() != null && info.getDatas().size() > 0) {
			
			for (ReportDataBo data : info.getDatas()) {
				//数据项配置信息
				MultStatInfoBo multStatInfo = dao.fetch(MultStatInfoBo.class, Cnd
						.where("id", "=", data.getMtdmid()));
				if (multStatInfo != null) {
					DBinfoBo dbinfo = dao.fetch(DBinfoBo.class, Cnd.where("id",
							"=", multStatInfo.getDatabaseid()));
					 
					Dao dao1 = DBAdapter.getDao(dbinfo);
					Sql sql =toDealSql(multStatInfo.getSql(),request); 
						//Sqls.create(multStatInfo.getSql());
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
									map.put(rsmd.getColumnLabel(i).toLowerCase(), 
											rs.getString(rsmd.getColumnLabel(i)));
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
					if(re!=null&&re.size()>0){
						list.addAll(re);
					}
					multStatInfo.setUpdatetime(new Date());
					dao.update(multStatInfo);
					MultStatResultBo bo = new MultStatResultBo();
					bo.setCountTime(new Date());
					bo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
					bo.setInfoid(multStatInfo.getId());
					bo.setResult(re);
					dao.insert(bo);
				}
			}
			result = new MultStatResultBo();
			result.setId(UUID.randomUUID().toString().replaceAll("-",
					""));
			result.setInfoid(info.getId());
			result.setResult(list);
			result.setCountTime(new Date());
		}
		return result;
	}
	
	/**
	 * 获取瞬时报表结果信息2016-12-29
	 * @param id
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	public ReportResultBo instantReportResult(String id,HttpServletRequest request) throws ParseException{
		ReportResultBo result = null;
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
		}
		// 1、获取数据
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 结果列表
		resultList = refreshMultStatResult(id,request).getResult();
		// 3、将数据塞到html相应的位置
		String html = info.getHtml();
		Document doc = Jsoup.parse(html);
		dataproc(info,resultList,null);
		doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
		// 4、保存结果
		result = new ReportResultBo();
		result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		result.setReportinfoid(info.getId());
		result.setResult(doc.head().html() + doc.body().html());
		result.setUpdatetime(new Date());
		return result;
	} 
	/**
	 * 数据处理
	 * 	将中文字典的数据进行处理
	 * @param info
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void dataproc(ReportInfoBo info,List<Map<String, String>> data,String dynamicdic){
		if(info.getHeaders()==null||info.getRows()==null){
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", info.getId())));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", info.getId())));
		}
		//转换用的dic
		Map<String, Map<String, String>> middic = new HashMap<String, Map<String, String>>();
		for(ReportHeaderBo header:info.getHeaders()){
			if("1".equals(header.getDiczhcn())){
				Map<String, String> dic = DicDataUtils.getInstance().getDic(header.getDicid());
				Map<String, String> dictemp = new HashMap<String, String>();
				for(String key:dic.keySet()){
					dictemp.put(dic.get(key), key);
				}
				middic.put(header.getColumnname().toLowerCase(), dictemp);
			}
		}
		for(ReportRowBo row:info.getRows()){
			if("1".equals(row.getDiczhcn())){
				if(row.getDicid()!=-1){
					Map<String, String> dic = DicDataUtils.getInstance().getDic(row.getDicid());
					Map<String, String> dictemp = new HashMap<String, String>();
					for(String key:dic.keySet()){
						dictemp.put(dic.get(key), key);
					}
					middic.put(row.getColumnname().toLowerCase(), dictemp);
				}
			}
			if(row.getDicid() == -1){
				JSONObject dicjson = JSONObject.fromObject(dynamicdic);
				Iterator it =  dicjson.keys();
				while(it.hasNext()){
					String key = (String) it.next();
					JSONObject subdic = JSONObject.fromObject(dicjson.getString(key));
					Iterator it2 =  subdic.keys();
					Map<String, String> map = new HashMap<String, String>();
					while(it2.hasNext()){
						String key2 = (String) it2.next();
						map.put(subdic.getString(key2), key2);
					}
					middic.put(key.toLowerCase(), map);
				}
			}
		}
		if(!Strings.isEmpty(dynamicdic)){
			JSONObject dicjson = JSONObject.fromObject(dynamicdic);
			Iterator it =  dicjson.keys();
			while(it.hasNext()){
				String key = (String) it.next();
				JSONObject subdic = JSONObject.fromObject(dicjson.getString(key));
				Iterator it2 =  subdic.keys();
				Map<String, String> map = new HashMap<String, String>();
				while(it2.hasNext()){
					String key2 = (String) it2.next();
					map.put(subdic.getString(key2), key2);
				}
				middic.put(key.toLowerCase(), map);
			}
		}
		for(Map<String, String> da:data){
			for(String key:middic.keySet()){
				Map<String, String> dic = middic.get(key);
				da.put(key, dic.get(da.get(key)) == null? da.get(key):dic.get(da.get(key)));
			}
		}
	}
	
	/**
	 * 更新数据（保存统计结果）
	 * 
	 * @param id
	 */
	@At
	@Ok("json")
	public Map<String, Object> refreshdata(String id,HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> re = new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		if(info==null){
			re.put("state", "fail");
			re.put("msg", "不存在的报表！");
			return re;
		}
		if("0".equals(info.getState())){
			re.put("state", "fail");
			re.put("msg", "更新失败！报表含动态参数或动态字典");
			return re;
		}
		try {
			update(id,request);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		re.put("state", "success");
		re.put("msg", "更新成功");
		re.put("name", info.getName());
		re.put("updateTime",info.getUpdatetime());
		return re;
	}
	/**
	 * 根据报表id和日期，获取结果（非动态字典，非动态参数）
	 * @param id
	 * @param date
	 * @return
	 */
	@At
	@Ok("json")
	public Object toResult(String id, String date,HttpServletResponse response,HttpServletRequest request){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Criteria cri = Cnd.cri();
		cri.where().and("report_info_id", "=", id);
		cri.getOrderBy().desc("update_time");
		if (!Strings.isEmpty(date)) {
			String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if (matcher.matches()) {
				try {
					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					cri.where().and("update_time", ">=", d);
					cri.where()
							.and("update_time", "<", DateUtils.addDays(d, 1));
				} catch (ParseException e) {
					log.error(e);
				}
			} else {
				log.info("date无效，将获取最新数据！");
			}
		}
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		List<ReportResultBo> list = new ArrayList<ReportResultBo>();
		if("1".equals(info.getIscache())){
			list = dao.query(ReportResultBo.class, cri, dao.createPager(1, 1));
		}else{
			try {
				info.setUpdatetime(new Date());
				ReportResultBo reportResult = instantReportResult( id, request);
				list.add(reportResult);
			} catch (ParseException e) {
				log.error(e);
			}
		}
		if (list != null && list.size() > 0) {
			Map<String, Object> result = new HashMap<String, Object>();
			ReportResultBo reportResult = list.get(0);
			result.put("result", reportResult.getResult());//报表展示的html（含）
			result.put("id", reportResult.getId());
			result.put("state", "suceess");
			result.put("msg", "获取成功");
			result.put("name", info.getName());
			result.put("updateTime",info.getUpdatetime());
			return result;
		} else {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("state", "fail");
			result.put("msg", "获取失败！报表id错误或该报表无数据！");
			return result;
		}
	}
	
	
	/**
	 * 根据报表id，获取结果（非动态字典，动态参数）
	 * @param id
	 * @param date
	 * @return
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	@Ok("json")
	public Map<String, Object> toDynamicParamResult(String id,HttpServletRequest request,HttpServletResponse response) throws ParseException, SchedulerException{
		Map<String, Object> re = new HashMap<String, Object>();
		re.put("id", id);
		String result="";
		response.setHeader("Access-Control-Allow-Origin", "*");
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		ReportResultBo reportResult =null;
		//缓存
		if("1".equals(info.getIscache())){
			String param =request.getParameter("param");
			Map<String, Object> map= checkCache(id,null,param,null);
			if(!(Boolean) map.get("hascache")){
				reportResult = instantReportResult( id, request);
				reportResult.setCacheid((String) map.get("cacheid"));
				dao.insert(reportResult);
				info.setUpdatetime(new Date());
				dao.update(info);
			}else{
				reportResult = (ReportResultBo) map.get("result");
				info.setUpdatetime(new Date());
			}	
		}else{
			reportResult = instantReportResult( id, request);
		}
			
		if (reportResult != null ) {
			result =  reportResult.getResult();
		} 
		re.put("state", "success");
		re.put("msg", "成功");
		re.put("result", result);
		re.put("name", info.getName());
		re.put("updateTime", info.getUpdatetime());

		return re;
	}
	
	/**
	 * 根据动态字典获取结果（动态字典，非动态参数）
	 * @param id
	 * @param dic
	 * @param data
	 * @return
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	@Ok("json")
	public Map<String, Object> toDynamicDicResult(String id,String dic,HttpServletResponse response) throws SchedulerException, ParseException{
		Map<String, Object> re = new HashMap<String, Object>();
		re.put("id", id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		String html = null;
		if(info==null){
			re.put("state", "failed");
			re.put("msg", "报表id错误");
			re.put("result", null);
			return re;
		}else {
			html = info.getHtml();
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
		}
		// 1 ------>获取数据 并整理数据
		//如果日期为当日且若当日无数据，更新数据 并获取最新数据
		//如果是历史日期且该日无数据，返回错误信息
		//如果日期为空则去数据项下最新保存的数据
		//2 ----> 解析字典并组装html
		Document doc = ReportHtmlGenerator.generateHtmlByDic(html, dic, dao, id);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 数据结果列表
		//如果缓存
		if("1".equals(info.getIscache())){
			String dochtml = doc.head().html()+doc.body().html();
			Map<String, Object> map= checkCache(id,dic,null,dochtml);
			ReportResultBo resultbo = (ReportResultBo) map.get("result");
			//没有缓存
			if(resultbo == null){
				List<ReportDataBo> dataBos = dao.query(ReportDataBo.class, Cnd.where("report_info_id","=",id));
				for (ReportDataBo data : dataBos) {
					//拼凑条件
					MultStatInfoBo bo = dao.fetch(MultStatInfoBo.class ,data.getMtdmid());
					List<Map<String, String>> list = countByMultStatInfo(bo);
					if (list != null) {
						resultList.addAll(list);
					}
				}
				//3------>中文字典
				//4------> 将数据塞到html中相应的位置
				dataproc(info, resultList, dic);
				doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
				resultbo = new ReportResultBo();
				resultbo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				resultbo.setCacheid((String) map.get("cacheid"));
				resultbo.setReportinfoid(id);
				resultbo.setResult(doc.head().html()+doc.body().html());
				resultbo.setUpdatetime(new Date());
				dao.insert(resultbo);
				info.setUpdatetime(new Date());
				dao.update(info);
				re.put("result", doc.head().html()+doc.body().html());
			}else{
				re.put("result", resultbo.getResult());
			}
		}else{//不缓存
			List<ReportDataBo> dataBos = dao.query(ReportDataBo.class, Cnd.where("report_info_id","=",id));
			for (ReportDataBo data : dataBos) {
				//拼凑条件
				MultStatInfoBo bo = dao.fetch(MultStatInfoBo.class ,data.getMtdmid());
				List<Map<String, String>> list = countByMultStatInfo(bo);
				if (list != null) {
					resultList.addAll(list);
				}
			}
			//3------>中文字典
			//4------> 将数据塞到html中相应的位置
			dataproc(info, resultList, dic);
			doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
			info.setUpdatetime(new Date());
			re.put("result", doc.head().html()+doc.body().html());
		}
		re.put("state", "success");
		re.put("msg", "成功");
		re.put("name", info.getName());
		re.put("updateTime", info.getUpdatetime());
		return re;
	}
	
	/**
	 * 根据动态字典获取结果（动态字典，动态参数）
	 * @param id
	 * @param dic
	 * @param data
	 * @return
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	@Ok("json")
	public Map<String, Object> toAllDynamicResult(String id,String dic,HttpServletRequest request,HttpServletResponse response) throws SchedulerException, ParseException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> re =new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		re.put("id", id);
		re.put("name", info.getName());
		re.put("updateTime", new Date());
		if(info==null){
			re.put("state", "fail");
			re.put("msg", "报表id错误");
			re.put("result", null);
			return re;
		}
		info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where("reportinfoid", "=", id)));
		info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid","=", id)));
		String html = info.getHtml();
		//2 ----> 解析字典并组装html
		Document doc = ReportHtmlGenerator.generateHtmlByDic(html, dic, dao, id);
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		if("1".equals(info.getIscache())){
			
			String param = request.getParameter("param");
			String dochtml = doc.head().html()+doc.body().html();
			Map<String, Object> map = checkCache(id,dic,param,dochtml);
			
			if(!(Boolean) map.get("hascache")){
				resultList = refreshMultStatResult(id,request).getResult();
				dataproc(info, resultList, dic);
				doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
				ReportResultBo resultbo = new ReportResultBo();
				resultbo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				resultbo.setReportinfoid(id);
				resultbo.setCacheid((String) map.get("cacheid"));
				resultbo.setResult(doc.head().html()+doc.body().html());
				resultbo.setUpdatetime(new Date());
				dao.insert(resultbo);
				info.setUpdatetime(new Date());
				dao.update(info);
				re.put("result", doc.head().html()+doc.body().html());	
				
			}else{
				ReportResultBo ResultBo =(ReportResultBo) map.get("result");
				re.put("result", ResultBo.getResult());
			}
			
		}else{
			
			resultList = refreshMultStatResult(id,request).getResult();
			dataproc(info, resultList, dic);
			doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
			info.setUpdatetime(new Date());
			re.put("result", doc.head().html()+doc.body().html());
			
		}
		re.put("state", "success");
		re.put("msg", "成功");
		re.put("name", info.getName());
		re.put("updateTime", info.getUpdatetime());
		return re;
	}
	
	
	/**
	 * 查看结果(预览)
	 * 
	 * @param id
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.view")
	public Map<String, Object> viewResult(String id, String date) {
 		Map<String, Object> result = new HashMap<String, Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		result.put("info", info);
		Criteria cri = Cnd.cri();
		cri.where().and("report_info_id", "=", id);
		cri.getOrderBy().desc("update_time");
		if (!Strings.isEmpty(date)) {
			String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if (matcher.matches()) {
				try {
					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					result.put("upDateTime", date);
					cri.where().and("update_time", ">=", d);
					cri.where().and("update_time", "<", DateUtils.addDays(d, 1));
							
				} catch (ParseException e) {
					log.error(e);
				}
			} else {
				log.info("date无效，将获取最新数据！");
			}
		}
		List<ReportResultBo> list = dao.query(ReportResultBo.class, cri, dao
				.createPager(1, 1));
		if (list != null && list.size() > 0) {
			result.put("result", list.get(0));
			
		    result.put("upDateTime", new SimpleDateFormat("yyyy-MM-dd").format(list.get(0).getUpdatetime()));
			result.put("hms", new SimpleDateFormat("HH:mm:ss").format(list.get(0).getUpdatetime()));
			
		} else {
			result.put("result", null);
			
		}
		return result;
	}
	/**
	 * 导出
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.export")
	public Map<String, Object> exportReport(String id,String date,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("report_info_id", "=", id);
		cri.getOrderBy().desc("update_time");
		if (!Strings.isEmpty(date)) {
			String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if (matcher.matches()) {
				try {
					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					cri.where().and("update_time", ">=", d);
					cri.where()
							.and("update_time", "<", DateUtils.addDays(d, 1));
				} catch (ParseException e) {
					log.error(e);
				}
			} else {
				log.info("date无效，将导出最新数据！");
			}
		}
		List<ReportResultBo> list = dao.query(ReportResultBo.class, cri, dao
				.createPager(1, 1));
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		if (list != null && list.size() > 0) {
			result = export(list.get(0).getId(),request);
		} else {
			result.put("body", "当日无数据");
		}
		result.put("name", info.getName());
		result.put("updateTime", info.getUpdatetime());
		return result;
	}
	/**
	 * 导出
	 * @param resultid
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.export")
	public Map<String, Object> export(String resultid,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		ReportResultBo reportResult = dao.fetch(ReportResultBo.class,Cnd.where("id","=",resultid));
		Document doc = Jsoup.parse(reportResult.getResult());
		result.put("head", doc.head().html());
		result.put("body", doc.body().html());
		result.put("updateTime", reportResult.getUpdatetime());
		return result;
	}
	
	/**
	 * 导出报表(非动态字典，动态参数类型)
	 * @param resultid
	 * @return
	 * @throws Exception 
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.export")
	public Map<String, Object> expDynamicParamReport(String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		ReportResultBo reportResult = (ReportResultBo) toDynamicParamResult(id,request,response);
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		Document doc = Jsoup.parse(reportResult.getResult());
		result.put("head", doc.head().html());
		result.put("body", doc.body().html());
		result.put("name", info.getName());
		result.put("updateTime", new Date());
		return result;
	}
	
	/**
	 * 根据动态字典导出表
	 * @return
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.export")
	public Map<String, Object> exDynamicDicReport(String id,String dic,String date,HttpServletResponse response) throws SchedulerException, ParseException{
		Map<String, Object> result = new HashMap<String, Object>();
		String html = (String) toDynamicDicResult(id, dic,response).get("result");
		Document doc = Jsoup.parse(html);
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		result.put("name", info.getName());
		result.put("updateTime", new Date());
		result.put("head", doc.head().html());
		result.put("body", doc.body().html());

		return result;
	}
	
	
	/**
	 * 根据动态字典和动态数据导出报表
	 * @return
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.export")
	public Map<String, Object> exAllDynamicReport(String id,String dic,HttpServletRequest request,HttpServletResponse response) throws SchedulerException, ParseException{
		Map<String, Object> result = new HashMap<String, Object>();
		String html = (String) toAllDynamicResult(id,dic,request,response).get("result");
		Document doc = Jsoup.parse(html);
		ReportInfoBo info = dao.fetch(ReportInfoBo.class,id);
		result.put("head", doc.head().html());
		result.put("body", doc.body().html());
		result.put("name", info.getName());
		result.put("updateTime", new Date());
		return result;
	}
	
	
	private List<Map<String, String>> countByMultStatInfo(MultStatInfoBo info){
		DBinfoBo dbinfo = dao.fetch(DBinfoBo.class,
				Cnd.where("id", "=", info.getDatabaseid()));
		Dao dao1 = DBAdapter.getDao(dbinfo);
		Sql sql = Sqls.create(info.getSql());
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql arg2)
					throws SQLException {
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int col = rsmd.getColumnCount();
				while (rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= col; i++) {
						map.put(rsmd.getColumnLabel(i).toLowerCase(),
								rs.getString(rsmd.getColumnLabel(i)));
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
		result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		result.setInfoid(info.getId());
		result.setResult(re);
		result.setCountTime(new Date());
		dao.insert(result);
		info.setUpdatetime(new Date());
		dao.update(info);
		return result.getResult();
	}
	
	/**
	 * 处理SQL中的参数问题
	 * @param originalSql
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	public Sql toDealSql(String originalSql,HttpServletRequest request) throws ParseException{
		Sql sql = Sqls.create(originalSql);
		String[] temp = null;
		String tempSql = originalSql;
		if(originalSql != null&& originalSql !=""){
			//判断字符串中是否含有'@'符
			//将紧挨@符后的字符串保存打list中
			//将字符串对应的数据从request中取出来
			//如果以字母D开头，将其转化为Date类型
			if(-1<(originalSql.indexOf("@"))){
				temp = originalSql.split("@");
				//@符号的个数
				int count = temp.length-1;
				//处理@符号
				//修改，动参统一传一个json过来，类似 param:"{S_sex:'1'}"
				String parameter =  request.getParameter("param");
				JSONObject jsonObject = JSONObject.fromObject(parameter);
				for(int i=1;i<=count;i++){
					//获取@符以及其后面紧邻的字符串
					int tempIndex = tempSql.indexOf("@");
					tempSql = tempSql.substring(tempIndex);
					//第一个空格出现的位置
					int trimIndex = tempSql.indexOf(" ");
					//@后面紧邻的字符串
					String parameterStr = tempSql.substring(1,trimIndex);
					if(parameterStr.startsWith("D_")){
						Date dateParameter = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString(parameterStr));
						sql.params().set(parameterStr, dateParameter);
					}else {
						sql.params().set(parameterStr, jsonObject.get(parameterStr));
					}
					tempSql = tempSql.substring(trimIndex);
				}
				
			}
			
		}
		return sql;
	}
	
	/**
	 * 测试用
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.test")
	public Map<String,String> dicTest(String id){
		Map<String,String> result = new HashMap<String,String>();
		result.put("id", id);
		return result;
	}
	
	
	/**
	 * 测试用
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.param_test")
	public Map<String,String> paramTest(String id){
		Map<String,String> result = new HashMap<String,String>();
		result.put("id", id);
		return result;
	}
	/**
	 * 测试周月报用
	 * 
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.special_report_view")
	public void specialViewResult1(){
		
	}
	
	/**
	 * @return
	 * 动态字典或参数报表测试页面
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.example")
	public Map<String,Object> toDynamicView(String id,String state){
		Map<String,Object> result = new HashMap<String,Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		result.put("id", id);
		result.put("state", state);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 动态字典或参数报表测试页面预览
	 * @param dic
	 * @param param
	 * @param id
	 * @param state
	 * @return
	 * @throws ParseException 
	 */
	@At
	@Ok("jsp:wddc.suite.csrq.report.example")
	public Map<String, Object> fetDynamicView(String dic,String param,String id,String state,HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Map<String,Object> result = new HashMap<String,Object>();
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
        try{
        	if("2".equals(state)){
    			result.put("result", toDynamicDicResult(id,dic,response));
    		}else if("3".equals(state)){
    			result.put("result", toDynamicParamResult(id,request, response));
    		}else{
    			result.put("result", toAllDynamicResult(id,dic, request, response));
    		}
        }catch(Exception e){
       	Map<String, String> map =new HashMap<String, String>();
        	map.put("result", "参数错误，请填写正确格式的参数。");
        	result.put("result", map);
        }		
		result.put("state", state);
		result.put("id", id);
		result.put("dic", dic);
		result.put("param", param);
		result.put("info", info);
		return result;
	}
	
	/**
	 * 校验corn表达式
	 * @param time
	 * @return
	 */
	@At
	@Ok("json")
	public String checkTime(String time){
		boolean flag = false;
		flag = CronExpression.isValidExpression(time);
		if(flag){
			return "1" ;
		}else{
			return "0" ;
		}
	}
	/**
	 * 获取所有报表（字典）
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> getAllReport(){
		Map<String, Object> re = new HashMap<String, Object>();
		List<ReportInfoBo> list = dao.query(ReportInfoBo.class, null);
		for(ReportInfoBo bo:list){
			re.put(bo.getId(), bo.getName());
		}
		return re;
	}
	/**
	 * 判断动参动字缓存
	 * 条件是否重复
	 * @param id
	 * @param dic
	 * @param param
	 * @throws ParseException 
	 * @throws SchedulerException 
	 */
	@At
	public Map<String, Object> checkCache(String id,String dic,String param,String html) throws SchedulerException, ParseException{
		Map<String, Object> result =new HashMap<String, Object>();
		ReportInfoBo infobo = dao.fetch(ReportInfoBo.class,id);
		Criteria cri = Cnd.cri();
		cri.where().and("report_id", "=", id);
		if("2".equals(infobo.getState())||"4".equals(infobo.getState())){
			cri.where().andEquals("dic", dic);
		}
		if("3".equals(infobo.getState())||"4".equals(infobo.getState())){
			cri.where().andEquals("param", param);
		}
		cri.where().andEquals("report_state", infobo.getState());
		ReportCacheBo cache = dao.fetch(ReportCacheBo.class,cri);
		if(cache==null){
			ReportCacheBo cachebo =new ReportCacheBo();
			cachebo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			cachebo.setDic(dic);
			cachebo.setParam(param);
			cachebo.setHtml(html);
			cachebo.setReportid(id);
			cachebo.setReportstate(infobo.getState());
			dao.insert(cachebo);
			result.put("hascache", false);
			result.put("cacheid", cachebo.getId());
			return result;
		}
		Criteria cri2 = Cnd.cri();
		cri2.where().andEquals("cacheid", cache.getId());
		cri2.getOrderBy().desc("update_time");
		ReportResultBo resultbo = dao.fetch(ReportResultBo.class , cri2);
		if(resultbo==null){
			result.put("hascache", false);
		}else{
			result.put("hascache", true);
			result.put("result", resultbo);
		}
		result.put("cacheid", cache.getId());
		return result;
	}
	
}
