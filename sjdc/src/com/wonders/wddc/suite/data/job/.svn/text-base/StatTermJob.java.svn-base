package com.wonders.wddc.suite.data.job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.StatInfoBo;
import com.wonders.wddc.suite.data.entity.StatResultBo;
import com.wonders.wddc.suite.data.entity.StatTermBo;
import com.wonders.wddc.suite.data.entity.StatTermResultBo;
import com.wonders.wddc.tiles.dic.DicDataUtils;

import net.sf.json.JSONObject;

@IocBean
public class StatTermJob implements Job{
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		task();
	}
	
	@SuppressWarnings("unchecked")
	public void task(){
		//统计统计单元
		Dao dao = Mvcs.ctx().getDefaultIoc().get(Dao.class,"dao");
		List<StatInfoBo> list=dao.query(StatInfoBo.class, null);
		for(StatInfoBo info:list){
			info.init();
			DBinfoBo dBinfo = dao.fetch(DBinfoBo.class,
					Cnd.where("ID", "=", info.getDatabaseid()));
			Map<String, String> result = new HashMap<String, String>();
			Dao statdao = DBAdapter.getDao(dBinfo);
			List<String> sqlstrs = info.getSqllist();
			for (String sqlstr : sqlstrs) {
				Sql sql = Sqls.create(sqlstr);
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection connection,
							ResultSet resultset, Sql sql) throws SQLException {
						Map<String, String> result = new HashMap<String, String>();
						while (resultset.next()) {
							result.put(resultset.getString("key"),
									resultset.getString("value"));
						}
						return result;
					}
				});
				statdao.execute(sql);
				Map<String, String> sqlresult = (Map<String, String>) sql
						.getResult();
				for (String key : sqlresult.keySet()) {
					result.put(key, sqlresult.get(key));
				}
			}
			StatResultBo statResult = new StatResultBo();
			statResult.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			statResult.setStatid(info.getInfoId());
			statResult.setCountTime(new Date());
			statResult.setResult(JSONObject.fromObject(result).toString());
			dao.insert(statResult);
			info.setUpdatetime(new Date());
			dao.update(info);
		}
		//统计统计项
		List<StatTermBo> list2 = dao.query(StatTermBo.class, null);
		for(StatTermBo term:list2){
			term.init();
			StatTermResultBo result = new StatTermResultBo();
			result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			result.setTermid(term.getId());
			Map<String, Map<String, String>> re = new LinkedHashMap<String, Map<String, String>>(); //包含的统计元的结果
			for(Map<String, String> statinfo:term.getStatinfolist()){
				String statinfoid = statinfo.get("value");
				String key = statinfo.get("key");
				StatResultBo statResult = getResultByStatInfoID(statinfoid,dao);
				if(statResult!=null){
					statResult.init();
					re.put(key, statResult.getResultMap());
				}
			}
			Map<String, Object> r1 = new HashMap<String, Object>();	//原始版json
			Map<String, Object> r2 = new HashMap<String, Object>();	//键值分离版json
			List<String> axisdiclist = new ArrayList<String>();		//轴字典
			if(term.getAxisdic()==0){	//不使用轴字典
				Set<String> dicset= new LinkedHashSet<String>();
				for(String key1:re.keySet()){
					Map<String, String> retemp = re.get(key1);
					for(String key2:retemp.keySet()){
						dicset.add(key2);
					}
				}
				axisdiclist.addAll(dicset);
			}else {
				Map<String, String> dic = DicDataUtils.getInstance().getDic(term.getAxisdic());
				for(String key:dic.keySet()){
					axisdiclist.add(dic.get(key));
				}
			}
			//生成r1
			for(String key : re.keySet()){
				Map<String, String> resultMap = new LinkedHashMap<String, String>();
				Map<String, String> resulttemp = re.get(key);
				for(String dic:axisdiclist){
					resultMap.put(dic, resulttemp.get(dic)==null?"0":resulttemp.get(dic));
				}
				r1.put(key, resultMap);
			}
			//生成r2
			r2.put("axis", axisdiclist);									//轴字典
			List<String> datakey = new ArrayList<String>();					//数据项
			List<List<String>> datavalue = new ArrayList<List<String>>();	//数据值
			for(String key : re.keySet()){
				datakey.add(key);
				List<String> data = new ArrayList<String>();
				Map<String, String> resulttemp = re.get(key);
				for(String dic:axisdiclist){
					data.add(resulttemp.get(dic)==null?"0":resulttemp.get(dic));
				}
				datavalue.add(data);
			}
			r2.put("key", datakey);
			r2.put("value", datavalue);
			result.setResult1(JSONObject.fromObject(r1).toString());
			result.setResult2(JSONObject.fromObject(r2).toString());
			result.setUpdatetime(new Date());
			dao.insert(result);
			term.setUpdatetime(new Date());
			dao.update(term);
		}
	}
	public StatResultBo getResultByStatInfoID(String id ,Dao dao) {
		Criteria cri = Cnd.cri();
		cri.where().and("STAT_ID","=",id);
		cri.getOrderBy().desc("COUNT_TIME");
		List<StatResultBo> list = dao.query(StatResultBo.class, cri, dao.createPager(1, 1));
		if(list==null||list.size()==0){
			return null;
		}else {
			list.get(0).init();
			return list.get(0);
		}
	}
}
