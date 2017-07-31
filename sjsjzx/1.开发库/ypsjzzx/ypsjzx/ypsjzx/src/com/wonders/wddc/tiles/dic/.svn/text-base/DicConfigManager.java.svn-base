package com.wonders.wddc.tiles.dic;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.service.EntityService;

import com.wonders.wddc.tiles.dic.entity.Dic;
import com.wonders.wddc.tiles.dic.entity.DicConfigBo;


/**
 * 字典加载类
 * 
 * @author Gordon
 *
 */
@IocBean(fields = "dao")
public class DicConfigManager extends EntityService<Object> {

	private Sql sql = Sqls
			.create("select $key as key, $value as value from $table $appendsql");

	private Log logs = Logs.get();

	/**
	 * 取得全部的字典项
	 */
	public void loadAllDic() {
		List<DicConfigBo> configList = dao().query(DicConfigBo.class, null);
		if (configList != null) {
			for (DicConfigBo dicConfig : configList) {
				loadDic(dicConfig);
			}
		}
	}
	
	public void reload() {
		DicDataUtils.getInstance().init();
		loadAllDic();
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
		sql.vars().set("key", config.getItemKeyColumn());
		sql.vars().set("value", config.getItemNameColumn());
		sql.vars().set("table", config.getTableName());
		sql.vars().set("appendsql", config.getAppendSql());

		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(dao().getEntity(Dic.class));
		dao().execute(sql);
		List<Dic> list = sql.getList(Dic.class);

		Map<String, String> dicMap = new LinkedHashMap<String, String>();
		if (list != null)
			for (Iterator<Dic> it = list.iterator(); it.hasNext();) {
				Dic dic = it.next();
				dicMap.put(dic.getKey(), dic.getValue());
			}
		return dicMap;
	}
}
