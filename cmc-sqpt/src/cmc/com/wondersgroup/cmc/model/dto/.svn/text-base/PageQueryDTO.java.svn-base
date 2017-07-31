package com.wondersgroup.cmc.model.dto;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class PageQueryDTO implements Serializable{
	
	private static final long serialVersionUID = 1l;
	private static final int NULL_INT = -999999999;
	private static final long NULL_LONG = -999999999L;
	private static final double NULL_DOUBLE = -999999999.0;
	
	private int pageIndex;
	private int pageSize;
	private String sortField;
	private String sortOrder;
	
	
	private LinkedHashMap params;
	private HttpServletRequest request;
	
	public PageQueryDTO(HttpServletRequest request){
		this.request = request;
		params = new LinkedHashMap();
		copyFromRequest(request);
		
	}
	
	public PageQueryDTO(){
		pageIndex = 0;
		pageSize = 10;
		sortField = "";
		sortOrder = "";
		params = new LinkedHashMap();
	}
	
	public void setParam(String name, Object value) {
		if (value instanceof String) {
			params.put(name, (String) value);
		} else {
			params.put(name, value);
		}
	}

	public Object getParam(String name) {
		return params.get(name);
	}
	
	public String getParamAsString(String name) {
		Object obj = params.get(name);

		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof String[]) {
			return unite((String[]) obj);
		} else {
			return (obj != null) ? obj.toString() : "";
		}
	}

	public String[] getParamsAsArray(String name) {
		Object obj = params.get(name);
		if (obj instanceof String[]) {
			return (String[]) obj;
		} else {
			return new String[] { (String) obj };
		}
	}

	
	public String getParamesAsText(String name) {
		return getParamsAsText(name, ";");
	}

	public String getParamsAsText(String name, String split) {
		Object obj = params.get(name);

		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof String[]) {
			return unite((String[]) obj, split);
		} else {
			return (obj != null) ? obj.toString() : null;
		}
	}

	public double getParamAsDouble(String name){
		return getParamAsDouble(name, NULL_DOUBLE);
	}
	
	public double getParamAsDouble(String name, double defaultValue) {
		Object obj = params.get(name);
		if(obj == null){
			return defaultValue;
		}
		String value = obj.toString();
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}

	public int getParamAsInt(String name){
		return getParamAsInt(name, NULL_INT);
	}
	
	public int getParamAsInt(String name, int defaultValue){
		Object obj = params.get(name);
		if(obj == null) { 
			return defaultValue;
	    }
		
		String value = obj.toString();
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	public long getParamAsLong(String name){
		return getParamAsLong(name, NULL_LONG);
	}

	public long getParamAsLong(String name, long defaultValue){
		Object obj = params.get(name);
		if(obj == null){
			return defaultValue;
		}
		String value = obj.toString();
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
	
	public Iterator getParamNames() {
		return params.keySet().iterator();
	}

	public String getAttribute(String name){
		return getAttribute(request, name, true);
	}
	
	public static String getAttribute(HttpServletRequest request, String name,
            boolean emptyStringsOK)
    {
        String temp = (String)request.getAttribute(name);
        if (temp != null) {
            if (temp.equals("") && !emptyStringsOK) {
                return null;
            }
            else {
                return temp;
            }
        }
        else {
            return null;
        }
    }
	
	public void copyFromRequest(HttpServletRequest request) {
		setRequest(request);
		Enumeration en = request.getParameterNames();
		if (en != null) {
			while (en.hasMoreElements()) {
				String name = (String) en.nextElement();
				String[] list = request.getParameterValues(name);
				if (list != null && list.length > 1) {
					setParam(name, list);
				} else {
					setParam(name, request.getParameter(name));
				}
			}
		}

	}
	
	public void removeParam(String name) {
		params.remove(name);
	}

	public void putAll(Map map) {
		this.params.putAll(map);
	}

	public boolean equals(Object obj) {
		if (obj != null && obj instanceof PageQueryDTO) {
			PageQueryDTO dto = (PageQueryDTO) obj;
			return this.params.equals(dto.params);
		}
		return false;
	}
	
	public String toString(){
		String str = ReflectionToStringBuilder.toString(this);
		return str;
	}
	
	
	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public int getPageIndex() {
		return getParamAsInt("pageIndex")==NULL_INT?this.pageIndex:getParamAsInt("pageIndex");
	}

	public void setPageIndex(int pageIndex) {
		setParam("pageIndex", pageIndex);
	}

	public int getPageSize() {
		return getParamAsInt("pageSize")==NULL_INT?this.pageSize:getParamAsInt("pageSize");
	}


	public void setPageSize(int pageSize) {
		setParam("pageSize", pageSize);
	}


	public String getSortField() {
		return getParamAsString("sortField");
	}


	public void setSortField(String sortField) {
		setParam("sortField", sortField);
	}


	public String getSortOrder() {
		return getParamAsString("sortOrder");
	}


	public void setSortOrder(String sortOrder) {
		setParam("sortOrder", sortOrder);
	}


	//******************************//
	private static String unite(String[] arr, String sp) {
		if (arr == null)
			return null;

		if (arr.length == 0)
			return "";

		int i;

		StringBuffer buff = new StringBuffer();
		for (i = 0; i < arr.length; i++) {
			if (StringUtils.isBlank(arr[i])) {
				continue;
			}
			buff = buff.append(arr[i]).append(sp);
		}

		if (buff.lastIndexOf(sp) > 0) 
			buff = buff.deleteCharAt(buff.lastIndexOf(sp));

		return buff.toString();
	}
	

	private static String unite(String[] arr) {
		return unite(arr, ";");
	}
	//*******************************//
}
