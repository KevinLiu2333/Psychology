package com.wonders.tiles.query.statrenderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ZzTaxRenderer  implements BaseRenderer{

	public Map genWhereClause(HttpServletRequest request) {
		
		String zzTAX = request.getParameter("ZZ_TAX_PERCENT");
		String syTAX = request.getParameter("SY_TAX_PERCENT");
		String bgTAX = request.getParameter("BG_TAX_PERCENT");
		String qtTAX = request.getParameter("QT_TAX_PERCENT");
//		System.out.println(zzTAX);
//		System.out.println(syTAX);
//		System.out.println(bgTAX);
//		System.out.println(qtTAX);
		String whereSql = " and ZZ_TAX_PERCENT=10 and SY_TAX_PERCENT=10 and BG_TAX_PERCENT=10 and QT_TAX_PERCENT=10 ";
		Map hashMap= new HashMap();
		String zzString = "<select name='ZZ_TAX_PERCENT'>";
		String syString = "<select name='SY_TAX_PERCENT'>";
		String bgString = "<select name='BG_TAX_PERCENT'>";
		String qtString = "<select name='QT_TAX_PERCENT'>";
		// 初次加载时
		if(zzTAX == null || "".equals(zzTAX)) {
			String commonString="";
			for(int i=1;i<=10;i++){
				if(i==10){
					commonString+="<option value='"+i+"' selected>"+i*10+"%</option>";
				}else{
					commonString+="<option value='"+i+"'>"+i*10+"%</option>";
				}
			}
			commonString+="</select>";

			hashMap.put("5703", zzString+commonString);
			hashMap.put("5704", syString+commonString);
			hashMap.put("5705", bgString+commonString);
			hashMap.put("5706", qtString+commonString);
		} else {
			whereSql = " and ZZ_TAX_PERCENT=" + zzTAX +" and SY_TAX_PERCENT=" + syTAX
				+ " and BG_TAX_PERCENT=" + bgTAX + " and QT_TAX_PERCENT=" + qtTAX + " ";
			
			for(int i=1; i<=10; i++){
				if(i == Integer.parseInt(zzTAX)){
					zzString += "<option value='" + i + "' selected>" + i*10 + "%</option>";
				}else{
					zzString += "<option value='" + i + "'>" + i*10 + "%</option>";
				}
			}
			
			for(int i=1; i<=10; i++){
				if(i == Integer.parseInt(syTAX)){
					syString += "<option value='" + i + "' selected>" + i*10 + "%</option>";
				}else{
					syString += "<option value='" + i + "'>" + i*10 + "%</option>";
				}
			}
			
			for(int i=1; i<=10; i++){
				if(i == Integer.parseInt(bgTAX)){
					bgString += "<option value='" + i + "' selected>" + i*10 + "%</option>";
				}else{
					bgString += "<option value='" + i + "'>" + i*10 + "%</option>";
				}
			}
			
			for(int i=1; i<=10; i++){
				if(i == Integer.parseInt(qtTAX)){
					qtString += "<option value='" + i + "' selected>" + i*10 + "%</option>";
				}else{
					qtString += "<option value='" + i + "'>" + i*10 + "%</option>";
				}
			}

			hashMap.put("5703", zzString + "</select>");
			hashMap.put("5704", syString + "</select>");
			hashMap.put("5705", bgString + "</select>");
			hashMap.put("5706", qtString + "</select>");
		}
		
		hashMap.put(WHERE_SQL, whereSql);
		
		
		return hashMap;
	}

}
