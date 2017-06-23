package com.klsw.wk.hades.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class PagerPropertyUtils {
	
	/**
	 * 将JSON对象映射为Pager对象
	 * @param object 原JSON对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Pager copy(JSONObject object,Class cls) throws Exception{
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("parameters", Map.class);
		classMap.put("fastQueryParameters", Map.class);
		classMap.put("advanceQueryConditions", Condition.class);
		classMap.put("advanceQuerySorts", Sort.class);
		classMap.put("exhibitDatas", cls);
		classMap.put("exportColumns", Column.class);
		classMap.put("exportDatas", Map.class);
		Pager pager = (Pager)JSONObject.toBean(object, Pager.class, classMap);
		pager.getParameters().put("offset", pager.getStartRecord());
		pager.getParameters().put("pageSize", pager.getPageSize());
		return pager;
	}
}