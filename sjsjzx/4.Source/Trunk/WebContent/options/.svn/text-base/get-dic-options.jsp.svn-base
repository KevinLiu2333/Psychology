<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map,java.util.HashMap" %>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@page import="org.nutz.lang.Strings" %>
<%@page import="java.lang.Integer" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="org.nutz.mvc.Mvcs" %>
<%
//参数区县
String dicId =  request.getParameter("dicId");
String headerKey = request.getParameter("headerKey");
String headerValue = request.getParameter("headerValue");

StringBuffer options = new StringBuffer();
if (!Strings.isEmpty(headerKey)){
	options=options.append("<option value=\"").append(headerValue).append("\">").append(headerKey).append("</option>\n");
}
if(StringUtils.isNotEmpty(dicId)){
	Map<String,String> streetsMap = DicDataUtils.getInstance().getDic(Integer.valueOf(dicId));
	if (!Strings.isEmpty(dicId) && streetsMap != null){
		for(Iterator<String> itor=streetsMap.keySet().iterator();itor.hasNext();){
			String streetCode=itor.next();
			options.append("<option value=\"").append(streetCode).append("\">").append(streetsMap.get(streetCode)).append("</option>");
		}
	}
	
}
out.print(options.toString());
%>