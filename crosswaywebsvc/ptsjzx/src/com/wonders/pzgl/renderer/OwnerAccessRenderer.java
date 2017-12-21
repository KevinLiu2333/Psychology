package com.wonders.pzgl.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class OwnerAccessRenderer implements BaseRenderer {

	public Map<String,String> genWhereClause(HttpServletRequest request) {
		
		//此处是台帐填报时显示的人员信息
		String whereSql = "and 1=1";
		Map<String,String> hashMap= new HashMap<String,String>();
		hashMap.put(WHERE_SQL, whereSql);
		
		return hashMap;
	}

}
