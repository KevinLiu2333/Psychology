package com.wonders.wddc.suite.data.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.DataFileInfoBo;
import com.wonders.wddc.suite.data.entity.StatInfoBo;
import com.wonders.wddc.suite.data.entity.StatResultBo;
import com.wonders.wddc.suite.data.entity.StatTermBo;
import com.wonders.wddc.suite.data.entity.StatTermResultBo;
import com.wonders.wddc.tiles.dic.DicDataUtils;
import com.wonders.wddc.tiles.tools.DateUtils;

import net.sf.json.JSONObject;

/**
 * 统计项At
 * @author vcixp
 *
 */
@At("/suite/data/term")
@IocBean
public class StatTermAct {
	@Inject
	private Dao dao;
	private static Log log = Logs.get();
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.term.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", dao.query(StatTermBo.class, null));
		return result;
	}
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.term.edit")
	public Map<String, Object> toEdit(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		StatTermBo term = dao.fetch(StatTermBo.class,Cnd.where("ID","=",id));
		if(term==null){
			term = new StatTermBo();
			term.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		} else {
			term.init();
		}
		List<StatInfoBo> list = dao.query(StatInfoBo.class, null);
		Map<String, String> unitmap = new LinkedHashMap<String, String>(); 
		for(StatInfoBo info:list){
			unitmap.put(info.getInfoId(), info.getTitle());
		}
		result.put("unit", JSONObject.fromObject(unitmap).toString());
		result.put("term", term);
		return result;
	}
	/**
	 * 保存统计项
	 * @param term
	 * @return
	 */
	@At
	public View save(@Param("::term.") StatTermBo term){
		term.pack();
		StatTermBo temp = dao.fetch(StatTermBo.class,Cnd.where("ID","=",term.getId()));
		if(term.getStatinfolist()==null||term.getStatinfolist().size()==0){
			term.setNeedsave("0");
		}else {
			String needsave = "1";
			for(Map<String, String> info:term.getStatinfolist()){
				StatInfoBo unit = dao.fetch(StatInfoBo.class,info.get("value"));
				if(unit!=null&&"0".equals(unit.getNeedsave())){
					needsave="0";
					break;
				}
			}
			term.setNeedsave(needsave);
		}
		if(temp==null){
			dao.insert(term);
		}else {
			dao.update(term);
		}
		return new ServerRedirectView("/suite/data/term/toIndex");
	}
	/**
	 * 删除
	 * @param id
	 */
	@At
	public void delete(String id){
		StatTermBo term=dao.fetch(StatTermBo.class,Cnd.where("ID","=",id));
		if(term!=null){
			dao.delete(term);
		}
	}
	/**
	 * 统计单个统计项
	 * @param id
	 */
	@At
	public void countById(String id){
		StatTermBo term = dao.fetch(StatTermBo.class,Cnd.where("ID","=",id));
		term.init();
		StatTermResultBo result = new StatTermResultBo();
		result.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		result.setTermid(term.getId());
		Map<String, Map<String, String>> re = new LinkedHashMap<String, Map<String, String>>(); //包含的统计元的结果
		boolean other = false;
		for(Map<String, String> statinfo:term.getStatinfolist()){
			String statinfoid = statinfo.get("value");
			String key = statinfo.get("key");
			countStatInfoByID(statinfoid);
			StatResultBo statResult = getResultByStatInfoID(statinfoid);
			if(statResult!=null){
				statResult.init();
				if(term.getAxisdic()!=0){//翻译字典
					Map<String, String> dic = DicDataUtils.getInstance().getDic(term.getAxisdic());
					Map<String, String> resultmap = statResult.getResultMap();
					Map<String, String> map = new HashMap<String, String>();
					int val = 0;//其他的值
					for(String k:resultmap.keySet()){
						if(!Strings.isEmpty(dic.get(k))){
							map.put(dic.get(k), resultmap.get(k));
						}else {
							try{
								val += Integer.parseInt(resultmap.get(k));
							}catch (Exception e) {
								log.error(e);
							}
						}
					}
					if(val!=0){
						map.put("其他", val+"");
						other = true;
					}
					re.put(key, map);
				}else{
					re.put(key, statResult.getResultMap());
				}
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
			if(other){
				axisdiclist.add(dic.get("其他"));
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
	
	/**
	 * 统计统计单元
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	private void countStatInfoByID(String id) {
		StatInfoBo info = dao
				.fetch(StatInfoBo.class, Cnd.where("INFO_ID", "=", id));
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
	/**
	 * 获取统计单元的结果
	 * @param id
	 * @return
	 */
	private StatResultBo getResultByStatInfoID(String id) {
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
	/**
	 * 统计所有统计项
	 */
	@At
	public void countAll(){
		List<StatTermBo> list = dao.query(StatTermBo.class, Cnd.where("NEED_SAVE","=","1"));
		for(StatTermBo term:list){
			countById(term.getId());
		}
	}
	/**
	 * 预览统计项结果
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.term.view")
	public Map<String, Object> view(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		StatTermBo term = dao.fetch(StatTermBo.class,Cnd.where("ID","=",id));
		if(term!=null){
			result.put("term", term);
		}
		Criteria cri = Cnd.cri();
		cri.where().and("TERM_ID","=",id);
		cri.getOrderBy().desc("UPDATE_TIME");
		List<StatTermResultBo> list = dao.query(StatTermResultBo.class, cri,dao.createPager(1, 1));
		if(list==null||list.size()==0){
			result.put("result", null);
		}else {
			result.put("result", list.get(0));
		}
		return result;
	}
	
	
	/**
	 * 获取所有统计项的id 及名称
	 * @return
	 */
	@At
	@Ok("json")
	public Object getAllstatterm(){
		List<StatTermBo> list = dao.query(StatTermBo.class, null);
		Map<String, String> result = new LinkedHashMap<String, String>(); 
		for(StatTermBo term:list){
			result.put(term.getId(), term.getName());
		}
		return result;
	}
	@At
	@Ok("json")
	public Object getAllData(){
		List<DataFileInfoBo> list = dao.query(DataFileInfoBo.class, null);
		Map<String,String> result = new LinkedHashMap<String,String>();
		for(DataFileInfoBo info:list){
			result.put(info.getId(), info.getName());
		}
		return result;
	}
	/**
	 * 获取统计项结果
	 * @param id
	 * @param type
	 * @param date 
	 * 		若date为null	获取该统计项最新的数据
	 * 		若date为某一日	获取该日最新数据（时间格式为yyyy-MM-dd） 当输入为9999-99-99时数据被立即更新，并取最新数据
	 * @return
	 */
	@At
	@Ok("json")
	public Object getTermResultById(String id,String type,String date){
		if(!Strings.isEmpty(date)&&date.equals("9999-99-99")){
			countById(id);
		}
		Criteria cri = Cnd.cri();
		cri.where().and("TERM_ID","=",id);
		if(!Strings.isEmpty(date)&&!date.equals("9999-99-99")){
			String regex="[0-9]{4}-[0-9]{2}-[0-9]{2}|";
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if(matcher.matches()){
				try {
					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					cri.where().and("UPDATE_TIME",">=",d);
					cri.where().and("UPDATE_TIME","<",DateUtils.addDays(d, 1));
				} catch (ParseException e) {
					log.error(e);
				}
			}else {
				log.info("date无效，将获取最新数据！");
			}
		}
		cri.getOrderBy().desc("UPDATE_TIME");
		List<StatTermResultBo> list = dao.query(StatTermResultBo.class, cri,dao.createPager(1, 1));
		StatTermResultBo termResult = null;
		if(list==null||list.size()==0){
			termResult = null;
		}else {
			termResult = list.get(0);
		}
		if(!Strings.isEmpty(type)&&termResult!=null){
			if(type.equals("1")){
				return termResult.getResult1();
			}
			if(type.equals("2")){
				return termResult.getResult2();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Object countByParm(String id,HttpServletRequest request,HttpSession session,String type){
		StatTermBo term = dao.fetch(StatTermBo.class,Cnd.where("ID","=",id));
		term.init();
		Map<String, Map<String, String>> re = new LinkedHashMap<String, Map<String, String>>(); //包含的统计元的结果
		for(Map<String, String> statinfo:term.getStatinfolist()){
			String statinfoid = statinfo.get("value");
			String key = statinfo.get("key");
			StatUnitAct act = Mvcs.getIoc().get(StatUnitAct.class);
			Object result = act.countByParm(statinfoid, request, session);
			if(result!=null){
				Map<String, String> resultMap = (Map<String, String>) JSONObject.fromObject(result);
				re.put(key, resultMap);
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
		if ("1".equals(type)) {
			return r1;
		}
		if("2".equals(type)){
			return r2;
		}
		return null;
	}
}
