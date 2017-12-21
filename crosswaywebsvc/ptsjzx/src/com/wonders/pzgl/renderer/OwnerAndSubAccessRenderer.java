package com.wonders.pzgl.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class OwnerAndSubAccessRenderer implements BaseRenderer{

	//此处是用于数据查询时显示的人员信息
	public Map genWhereClause(HttpServletRequest request) {
		String whereSql = "and 1=1";
		
		Map<String,String> hashMap= new HashMap<String,String>();
		hashMap.put(WHERE_SQL, whereSql);
		
		return hashMap;
	}

}
