package com.wonders.wddc.tiles.dic.at;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
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

import com.wonders.wddc.tiles.dic.DicDataUtils;
import com.wonders.wddc.tiles.dic.entity.Dic;
import com.wonders.wddc.tiles.dic.entity.DicConfigBo;
import com.wonders.wddc.tiles.dic.entity.DicResourceBo;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 字典AT
 * @author vcixp
 *
 */
@IocBean
@At("/kernel/dic")
public class DicAct {
	@Inject
	private Dao dao;
	private Log logs = Logs.get();
	@At
	@Ok("jsp:wddc.tiles.dic.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<DicConfigBo> list = dao.query(DicConfigBo.class, null);
		result.put("list", list);
		return result;
	}
	@At
	@Ok("jsp:wddc.tiles.dic.edit")
	public Map<String, Object> edit(String dicid){
		Map<String, Object> result = new HashMap<String, Object>();
		DicConfigBo dicConfig =null;
		if(!Strings.isEmpty(dicid)){
			dicConfig = dao.fetch(DicConfigBo.class,Cnd.where("DIC_ID","=",dicid));
		}
		if(dicConfig!=null){
			if(dicConfig.getDicType()==1){
				if(dicConfig.getTableName().equals("tiles_dic_resource")){
					Criteria cri = Cnd.cri();
					cri.where().and("CATALOG","=",dicid);
					cri.getOrderBy().asc("ORDER_NUM");
					dicConfig.setResources(dao.query(DicResourceBo.class, cri));
				}else {
					dicConfig.setDicType(3);
				}
			}
		}
		result.put("dic", dicConfig);
		return result;
	}
	@At
	public View save(@Param("::dic.") DicConfigBo dic){
		if(dic.getDicId()==null){	//新建
			dic.setDicId(Integer.parseInt(SnCreator.getInstance().getSN("dic")));
			beforesave(dic);
			dao.insert(dic);
		}else {						//更新
			dao.execute(Sqls.create("delete from tiles_dic_resource where catalog='"+dic.getDicId()+"'"));
			beforesave(dic);
			dao.update(dic);
		}
		loadDic(dic);
		return new ServerRedirectView("/kernel/dic/toIndex");
	}
	@At
	public void delele(String id){
		dao.execute(Sqls.create("delete from tiles_dic_config where DIC_ID ="+id));
		dao.execute(Sqls.create("delete from tiles_dic_resource where catalog ='"+id+"'"));
		DicDataUtils.getInstance().deleteDic(Integer.parseInt(id));
		logs.info("删除字典-->"+id);
	}
	
	private void beforesave(DicConfigBo dic){
		if(dic.getDicType()==1){
			dic.setTableName("tiles_dic_resource");
			dic.setItemKeyColumn("DIC_KEY");
			dic.setItemNameColumn("DIC_VALUE");
			dic.setAppendSql(" where CATALOG='"+dic.getDicId()+"' order by ORDER_NUM asc");
			if(dic.getResources()!=null&&dic.getResources().size()>0){
				int i=0;
				for(DicResourceBo resource:dic.getResources()){
					i++;
					resource.setOrdernum(i);
					resource.setCatalog(dic.getDicId()+"");
					resource.setItemid(UUID.randomUUID().toString().replaceAll("-", ""));
					dao.insert(resource);
				}
			}
		}
		if(dic.getDicType()==2){
			dic.setTableName("");
			dic.setItemKeyColumn("");
			dic.setItemNameColumn("");
			dic.setAppendSql("");
		}
		if(dic.getDicType()==3){
			dic.setDicType(1);
		}
	}
	
	/**
	 * 根据字典项配置，取得对应的字典对应关系, 并放入字典工具类中
	 * 
	 * @param config
	 */
	private void loadDic(DicConfigBo config) {
		if (config.getDicType() == null) {
			this.logs.error("字典配置信息不完整。dic_id = " + config.getDicId());
			return;
		}

		Map<String, String> map = null;
		int dicType = config.getDicType().intValue();

		switch (dicType) {
		case 1:
			map = buildDicType1(config);
			break;
		case 2:
			map = buildDicType2(config);
			break;
//		case 3:
//			map = buildDicType3(config);
//			break;
		default:
			logs.error("字典类型错误。 dic_id = " + config.getDicId());
			return;
		}

		if (logs.isDebugEnabled()) {
			logs.debug("加载了字典：" + config.getDicId() + "，记录数：" + map.size());
		}

		DicDataUtils.getInstance().addDic(config.getDicId(), map);
	}

	/**
	 * 取得类型2的字典
	 * 
	 * @param config
	 * @return
	 */
	private Map<String, String> buildDicType2(DicConfigBo config) {
		if (Strings.isEmpty(config.getValueList())) {
			logs.error("字典配置信息不完整。dic_id = " + config.getDicId());
		}

		StringTokenizer token = new StringTokenizer(config.getValueList(), "|");
		Map<String, String> dicMap = new LinkedHashMap<String, String>();
		
		while (token.hasMoreTokens()) {
			String entry = token.nextToken();
			int pos = entry.indexOf("=");
			if ((pos <= 0) || (pos >= entry.length() - 1))
				continue;
			String key = entry.substring(0, pos);
			String name = entry.substring(pos + "=".length());

			dicMap.put(key, name);
		}
		return dicMap;
	}

	/**
	 * 取得类型1的字典
	 * 
	 * @param config
	 * @return
	 */
	private Map<String, String> buildDicType1(DicConfigBo config) {
		Sql sql = Sqls.create("select $key as akey, $value as avalue from $table $appendsql");
		sql.vars().set("key", config.getItemKeyColumn());
		sql.vars().set("value", config.getItemNameColumn());
		sql.vars().set("table", config.getTableName());
		sql.vars().set("appendsql", config.getAppendSql());

		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao.getEntity(Dic.class));
		dao.execute(sql);
		List<Dic> list = sql.getList(Dic.class);

		Map<String, String> dicMap = new LinkedHashMap<String, String>();
		if (list != null)
			for (Iterator<Dic> it = list.iterator(); it.hasNext();) {
				Dic dic = it.next();
				dicMap.put(dic.getAkey(), dic.getAvalue());
			}
		return dicMap;
	}
	@At
	@Ok("json")
	public Object getBusinessDic(){
		Map<String, String> result = new HashMap<String, String>();
		List<DicConfigBo> list = dao.query(DicConfigBo.class, Cnd.where("key_data_type","=",1));
		for(DicConfigBo bo:list){
			result.put(bo.getDicId()+"", bo.getDicName());
		}
		return result;
	}
}
