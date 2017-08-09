package com.wonders.wddc.suite.data.at;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.StatInfoBo;
import com.wonders.wddc.suite.data.entity.StatResultBo;
import com.wonders.wddc.suite.data.entity.StatTermBo;

import net.sf.json.JSONObject;
/**
 * 统计单元AT
 * @author vcixp
 *
 */
@At("/suite/data/unit")
@IocBean
public class StatUnitAct {
	private Log log = Logs.get();
	@Inject
	private Dao dao;

	/**
	 * 统计单元首页
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.unit.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<StatInfoBo> list = dao.query(StatInfoBo.class, null);
		result.put("list", list);
		return result;
	}

	/**
	 * 统计单元新增修改页面
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.unit.edit")
	public Map<String, Object> toEdit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		StatInfoBo info = null;
		if (Strings.isEmpty(id)) {
			info = new StatInfoBo();
			info.setInfoId(UUID.randomUUID().toString().replaceAll("-", ""));
		} else {
			info = dao.fetch(StatInfoBo.class, Cnd.where("INFO_ID", "=", id));
			if (info != null) {
				info.init();
			}
		}
		result.put("info", info);
		return result;
	}

	/**
	 * 统计单元是否正在被使用
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public Object isUsing(String id) {
		int count = dao.count(StatTermBo.class, Cnd.where("STAT_INFOS", "like",
				"%" + id + "%"));
		if (count > 0)
			return "1";
		else
			return "0";
	}

	/**
	 * 删除统计单元
	 * 
	 * @param id
	 */
	@At
	public void delete(String id) {
		StatInfoBo info = dao
				.fetch(StatInfoBo.class, Cnd.where("INFO_ID", "=", id));
		dao.delete(info);
	}

	/**
	 * 根据id统计单个
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	@At
	public void countById(String id) {
		StatInfoBo info = dao.fetch(StatInfoBo.class, Cnd.where("INFO_ID", "=", id));
		info.init();
		DBinfoBo dBinfo = dao.fetch(DBinfoBo.class, Cnd.where("ID", "=", info.getDatabaseid()));
		//获取数据源
		Map<String, String> result = new HashMap<String, String>();
		Dao statdao = DBAdapter.getDao(dBinfo);
		List<String> sqlstrs = info.getSqllist();
		//逐条sql执行
		for (String sqlstr : sqlstrs) {
			Sql sql = Sqls.create(sqlstr);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection,
						ResultSet resultset, Sql sql) throws SQLException {
					Map<String, String> result = new HashMap<String, String>();
					while (resultset.next()) {
						result.put(resultset.getString("key"), resultset
								.getString("value"));
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
	 * 统计所有
	 */
	@At
	public void countAll(){
		List<StatInfoBo> list=dao.query(StatInfoBo.class, Cnd.where("NEED_SAVE","=","1"));
		for(StatInfoBo info:list){
			countById(info.getInfoId());
		}
	}
	/**
	 * 查看结果
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.unit.view")
	public Map<String, Object> view(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isEmpty(id)){
			StatInfoBo info = dao.fetch(StatInfoBo.class,Cnd.where("INFO_ID", "=", id));
			result.put("statinfo", info);
			Criteria cri = Cnd.cri();
			cri.where().and("STAT_ID","=",id);
			cri.getOrderBy().desc("COUNT_TIME");
			List<StatResultBo> list = dao.query(StatResultBo.class, cri, dao.createPager(1, 1));
			if(list==null||list.size()==0){
				result.put("result", null);
			}else {
				list.get(0).init();
				result.put("result", list.get(0));
			}
		}
		return result;
	}
	/**
	 * 保存统计单元
	 * @param info
	 * @return
	 */
	@At
	public View save(@Param("::info.") StatInfoBo statInfo){
		statInfo.pack();
		StatInfoBo info = dao.fetch(StatInfoBo.class,
				Cnd.where("INFO_ID", "=", statInfo.getInfoId()));
		if(!Strings.isEmpty(statInfo.getSql())){
			if(statInfo.getSql().indexOf("@")>=0){
				statInfo.setNeedsave("0");
			}else {
				statInfo.setNeedsave("1");
			}
		}
		if (info == null) {
			dao.insert(statInfo);
		} else {
			dao.update(statInfo);
		}
		return new ServerRedirectView("/suite/data/unit/toIndex");
	}
	
	
	/**
	 * 获取所有统计元的id 及名称
	 * @return
	 */
	@At
	@Ok("json")
	public Object getAllstatinfo(){
		List<StatInfoBo> list = dao.query(StatInfoBo.class, null);
		Map<String, String> result = new LinkedHashMap<String, String>(); 
		for(StatInfoBo info:list){
			result.put(info.getInfoId(), info.getTitle());
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	public Object countByParm(String id,HttpServletRequest request,HttpSession session){
		if(Strings.isEmpty(id)){
			log.error("Id is null!");
			return null;
		}
		StatInfoBo info = dao.fetch(StatInfoBo.class, Cnd.where("INFO_ID", "=", id));
		if(info==null){
			log.error("Id is invalid!");
			return null;
		}
		info.init();
		DBinfoBo dBinfo = dao.fetch(DBinfoBo.class, Cnd.where("ID", "=", info.getDatabaseid()));
		//获取数据源
		Map<String, String> result = new HashMap<String, String>();
		Dao statdao = DBAdapter.getDao(dBinfo);
		List<String> sqlstrs = info.getSqllist();
		for (String sqlstr : sqlstrs) {
			Sql sql = Sqls.create(sqlstr);
			//处理参数
			List<String> parms = getparm(sqlstr);
			for(String parm:parms){
				String value = request.getParameter(parm);
				if(Strings.isEmpty(value)&&!Strings.isEmpty(parm)){
					parm=parm.replaceFirst("P_", "");
					String[] split = parm.split("_");
					if(split.length==1){
						value = (String) session.getAttribute(parm);;
					}else {
						Object obj = session.getAttribute(split[0]);
						if(obj!=null){
							for(int i=1;i<split.length;i++){
								Mirror<?> mirror = Mirror.me(obj.getClass());
								try {
									Method method = mirror.getGetter(split[i]);
									if(i+1==split.length){
										value = method.invoke(obj).toString();
									}else {
										obj = method.invoke(obj);
										if(obj==null) break;
									}
								} catch (Exception e) {
									log.error(e);
									break;
								}
							}
						}
					}
				}
				if(Strings.isEmpty(value)){
					log.error("不能得到"+parm+"的值！");
					return null;
				}
				sql.params().set(parm, value);
			}
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection,
						ResultSet resultset, Sql sql) throws SQLException {
					Map<String, String> result = new HashMap<String, String>();
					while (resultset.next()) {
						result.put(resultset.getString("key"), resultset
								.getString("value"));
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
		return JSONObject.fromObject(result).toString();
	}
	
	
	private List<String> getparm(String sql){
		List<String> result = new ArrayList<String>();
		while(true){
			int begin=sql.indexOf("@");
			if(begin<0){
				break;
			}
			sql = sql.substring(begin+1);
			int end=sql.indexOf(" ");
			if(end<0){
				result.add(sql);
				break;
			}else {
				result.add(sql.substring(0,end));
				sql=sql.substring(end+1);
			}
		}
		return result;
	}
}
