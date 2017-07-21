package com.wonders.wddc.suite.csrq.job;

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

import net.sf.json.JSONObject;

import org.apache.commons.dbcp.BasicDataSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wonders.wddc.suite.csrq.entity.ReportCacheBo;
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
import com.wonders.wddc.tiles.dic.DicDataUtils;

@IocBean
public class FormTimingJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String id =dataMap.getString("id");
		refreshdata(id);
	}
	public void refreshdata(String id) {
		try {
			update(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void update(String id) throws ParseException {
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
		ReportInfoBo info = dao.fetch(ReportInfoBo.class, Cnd.where("id", "=", id));
		if (info != null) {
			info.setHeaders(dao.query(ReportHeaderBo.class, Cnd.where(
					"reportinfoid", "=", id)));
			info.setRows(dao.query(ReportRowBo.class, Cnd.where("reportinfoid",
					"=", id)));
		}
		if("1".equals(info.getState())){
			ReportResultBo result = instantReportResult(id,null);
			info.setUpdatetime(new Date());
			dao.update(info);
			dao.insert(result);
		}else{
			List<ReportCacheBo> list=dao.query(ReportCacheBo.class, Cnd.where("report_id", "=", info.getId()));
			if(list.size()>0){
				for(ReportCacheBo cache:list){
					ReportResultBo result = instantReportResult(id,cache.getId());
					info.setUpdatetime(new Date());
					dao.update(info);
					dao.insert(result);
				}
			}
		}
		
	}
	public List<Map<String, String>> refreshMultStatResult(String id,String cacheid) throws ParseException{
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
		boolean sqlflag =false;
		String param = "";
		if(!Strings.isEmpty(cacheid)){
			ReportCacheBo cache =dao.fetch(ReportCacheBo.class , cacheid);
			param = cache.getParam();
			sqlflag =true;
		}
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
					BasicDataSource dataSource = DBAdapter.getDataSource(dbinfo);
					Dao dao1 = new NutDao(dataSource);
					Sql sql =Sqls.create(multStatInfo.getSql());
					if(sqlflag&&!Strings.isEmpty(param)){
						sql =toDealSql(multStatInfo.getSql(),param); 
					}
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
									map
											.put(rsmd.getColumnLabel(i)
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
					if(re!=null&&re.size()>0){
						list.addAll(re);
					}
					DBAdapter.closeDataSource(dataSource);
					MultStatResultBo bo = new MultStatResultBo();
					bo.setId(UUID.randomUUID().toString().replaceAll("-",
							""));
					bo.setInfoid(info.getId());
					bo.setResult(re);
					bo.setCountTime(new Date());
					dao.insert(bo);
				}
				multStatInfo.setUpdatetime(new Date());
				dao.update(multStatInfo);
			}
		}
		return list;
	}
	public ReportResultBo instantReportResult(String id,String cacheid) throws ParseException{
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class);
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
		resultList = refreshMultStatResult(id,cacheid);
		// 3、将数据塞到html相应的位置
		String html = info.getHtml();
		String dic = "";
		if(!Strings.isEmpty(cacheid)){
			ReportCacheBo cache = dao.fetch(ReportCacheBo.class , cacheid);
			if("2".equals(cache.getReportstate())||"4".equals(cache.getReportstate())){
				html = cache.getHtml();
			}else{
				dic = cache.getDic();
			}
		}
		Document doc = Jsoup.parse(html);
	    
		dataproc(dao,info,resultList,dic);
		doc = ReportHtmlGenerator.GenerateFinalHtml(info, doc, resultList);
		// 4、保存结果
		result = new ReportResultBo();
		result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		result.setReportinfoid(info.getId());
		result.setResult(doc.head().html() + doc.body().html());
		result.setCacheid(cacheid);
		result.setUpdatetime(new Date());
		return result;
	} 
	
	public Sql toDealSql(String originalSql,String param) throws ParseException{
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
				JSONObject jsonObject = JSONObject.fromObject(param);
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
	 * 数据处理
	 * 	将中文字典的数据进行处理
	 * @param info
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void dataproc(Dao dao,ReportInfoBo info,List<Map<String, String>> data,String dynamicdic){
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
			if(row.getDicid() == -1&&"1".equals(row.getDiczhcn())){
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
}
