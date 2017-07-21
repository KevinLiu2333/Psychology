package com.wonders.wddc.suite.data.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
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

import com.wonders.wddc.suite.csrq.entity.ReportDataBo;
import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.entity.MultStatResultBo;
import com.wonders.wddc.suite.data.processor.SqlProcessor;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.tools.DateUtils;

/**
 * 报表统计项AT
 * @author vcixp
 *
 */
@At("/suite/data/mult")
@IocBean
public class StatMultAct {
	@Inject
	private Dao dao;
	private Log log = Logs.get();
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.mult.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", dao.query(MultStatInfoBo.class, null));
		return result;
	}
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.mult.edit")
	public Map<String, Object> toEdit(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		MultStatInfoBo info = dao.fetch(MultStatInfoBo.class, Cnd.where("id", "=", id));
		result.put("info", info);
		return result;
	}
	/**
	 * 保存
	 * @param info
	 * @return
	 */
	@At
	public View save(@Param("::info.") MultStatInfoBo info){
		if (Strings.isEmpty(info.getId())) {
			info.setId(SnCreator.getInstance().getSN("mutl", 6, "M"));
			dao.insert(info);
		} else {
			MultStatInfoBo temp = dao.fetch(MultStatInfoBo.class,info.getId());
			if(temp==null||temp.getSql()==null||!temp.getSql().equals(info.getSql())){
				for(MultStatResultBo result:dao.query(MultStatResultBo.class, Cnd.where("INFO_ID","=",info.getId()))){
					dao.delete(result);
				}
			}
			dao.update(info);
		}
		return new ServerRedirectView("/suite/data/mult/toIndex");
	}
	/**
	 * 预览
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.mult.view")
	public Map<String, Object> view(String id,String countTime){
		Map<String, Object> result = new HashMap<String, Object>();
		MultStatInfoBo info = dao.fetch(MultStatInfoBo.class,
				Cnd.where("id", "=", id));
		result.put("info", info);
		List<MultStatResultBo> list = new ArrayList<MultStatResultBo>();
		Criteria cri = Cnd.cri();
		cri.where().and("INFO_ID","=",id);
		cri.getOrderBy().desc("COUNT_TIME");
		if("".equals(countTime)||countTime == null){
			list = dao.query(MultStatResultBo.class, cri,dao.createPager(1, 1));
		}else{
			cri.where().andLike("countTime", countTime);
			list = dao.query(MultStatResultBo.class, cri,dao.createPager(1, 1));
		}
//		Criteria cri1 = Cnd.cri();
//		cri1.where().and("INFO_ID","=",id);
//		cri1.getOrderBy().desc("COUNT_TIME");
//		List<MultStatResultBo> resultList = dao.query(MultStatResultBo.class, cri1);
//		TreeSet<String> dateSet = new TreeSet<String>();
		SimpleDateFormat countDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hmsfmt = new SimpleDateFormat("HH:mm:ss");
//		for(MultStatResultBo resultBo:resultList){
//			dateSet.add(countDate.format(resultBo.getCountTime()));
//		}
		String nowDate = list.size()==0?countTime:countDate.format(list.get(0).getCountTime());
		String hms = list.size()==0?"":hmsfmt.format(list.get(0).getCountTime());
		if(list==null||list.size()==0){
			result.put("result", null);
		}else {
			result.put("result", list.get(0));
		}
		result.put("nowDate", nowDate);
		result.put("hms", hms);
//		result.put("dateSet", dateSet);
		return result;
	}
	/**
	 * 删除
	 * @param id
	 */
	@At
	public void delete(String id){
		MultStatInfoBo info = dao.fetch(MultStatInfoBo.class,
				Cnd.where("id", "=", id));
		if (info != null) {
			dao.delete(info);
			log.info(info.getName() + "被删除！");
		}
	}
	/**
	 * 单个统计
	 * @param id
	 */
	@At
	public void countById(String id){
		MultStatInfoBo info = dao.fetch(MultStatInfoBo.class,
				Cnd.where("id", "=", id));
		if (info != null) {
			countByInfo(info);
		}
	}
	/**
	 * 统计所有
	 */
	@At
	public void countAll(){
		List<MultStatInfoBo> list = dao.query(MultStatInfoBo.class, null);
		for(MultStatInfoBo info:list){
			if(info!=null){
				countByInfo(info);
			}
		}
	}
	
	
	private void countByInfo(MultStatInfoBo info){
		DBinfoBo dbinfo = dao.fetch(DBinfoBo.class,
				Cnd.where("id", "=", info.getDatabaseid()));
		BasicDataSource dataSource = DBAdapter.getDataSource(dbinfo);
		Dao dao1 = new NutDao(dataSource);
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
		DBAdapter.closeDataSource(dataSource);
	}
	/**
	 * 获取统计结果
	 * @param id
	 * 		若date为null	获取该统计项最新的数据
	 * 		若date为某一日	获取该日最新数据（时间格式为yyyy-MM-dd） 当输入为9999-99-99时数据被立即更新，并取最新数据
	 * @return
	 */
	@At
	@Ok("json")
	public Object getJsonById(String id,String date){
		if(!Strings.isEmpty(date)&&date.equals("9999-99-99")){
			MultStatInfoBo info = dao.fetch(MultStatInfoBo.class,
					Cnd.where("id", "=", id));
			if(info!=null){
				countByInfo(info);
			}
		}
		Criteria cri = Cnd.cri();
		cri.where().and("INFO_ID","=",id);
		if(!Strings.isEmpty(date)&&!date.equals("9999-99-99")){
			String regex="[0-9]{4}-[0-9]{2}-[0-9]{2}";
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher = pattern.matcher(date);
			if(matcher.matches()){
				try {
					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
					cri.where().and("COUNT_TIME",">=",d);
					cri.where().and("COUNT_TIME","<",DateUtils.addDays(d, 1));
				} catch (ParseException e) {
					log.error(e);
				}
			}else {
				log.info("date无效，将获取最新数据！");
			}
		}
		cri.getOrderBy().desc("COUNT_TIME");
		List<MultStatResultBo> list = dao.query(MultStatResultBo.class, cri,dao.createPager(1, 1));
		if(list==null||list.size()==0){
			return null;
		}else {
			MultStatResultBo result = list.get(0);
			return result.getResult();
		}
	}
	/**
	 * 获取统计项字段名
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public Object getsqlfield(String id){
		MultStatInfoBo info = dao.fetch(MultStatInfoBo.class,id);
		String sql=null;
		if(info!=null&&StringUtils.isNotBlank(info.getSql())){
			sql = info.getSql().trim();
			sql = sql.substring(7,sql.indexOf("from")).trim();
		}
		return sql;
	}
	@At
	@Ok("json")
	public Object getAllTerm(){
		Map<String, Object>  result = new HashMap<String, Object>();
		List<MultStatInfoBo> list = dao.query(MultStatInfoBo.class, null);
		for(MultStatInfoBo bo:list){
			result.put(bo.getId(), bo.getName());
		}
		return result;
	}
	@At
	@Ok("json")
	public Object isUsing(String id){
		int count=dao.count(ReportDataBo.class,Cnd.where("mtdm_id", "=", id) );
		if(count > 0){
			return '1';
		}else{
			return '0';
		}		
	}
	
	/**
	 * 测试动态参数
	 * @param id
	 * @param param
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:wddc.suite.data.mult.example")
	public Map<String, Object> toTestParam(String id,String param){
		Map<String, Object> result =new HashMap<String, Object>();
		MultStatInfoBo info =dao.fetch(MultStatInfoBo.class,id);
		if(!Strings.isEmpty(param)){
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			String originalSql =info.getSql();
			DBinfoBo dbinfo = dao.fetch(DBinfoBo.class,info.getDatabaseid());
			BasicDataSource dataSource = DBAdapter.getDataSource(dbinfo);
			try{
				Sql sql = SqlProcessor.toDealSql(originalSql, param);
				Dao dao1 = new NutDao(dataSource);
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
				list = (List<Map<String, String>>) sql.getResult();
				result.put("result", list);
			}catch(Exception e){
				Map<String, String> map =new HashMap<String, String>();
				map.put("Msg", "参数或sql错误!");
				list.add(map);
				result.put("result", list);
			}finally {
				DBAdapter.closeDataSource(dataSource);
			}
				
		}
		result.put("info", info);
		result.put("param", param);
		return result;
		
	}
}
