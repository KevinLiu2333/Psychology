<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//静态文件版本号
String version =request.getParameter("v");
if(version ==null){
	version ="1";
}
String oldVer =(String)request.getAttribute("__INCLUDED__");
if(oldVer !=null){
	out.write("<!-- INCLUDED -->");
	if(!oldVer.equals(version)){
		throw new JspException("引入了两个版本不一致的include.jsp");
	}
}else{

request.setAttribute("STATIC_FILE_VERSION", "1");
request.setAttribute("version", version);
%>
<%
	//服务器系统时间
	String sysDate=new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
	request.setAttribute("_sysDate_",sysDate);
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<c:url value="/resources/miniui/themes/default/miniui.css?v=${STATIC_FILE_VERSION}"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/miniui/themes/blue2010/skin.css?v=${STATIC_FILE_VERSION}"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/miniui/themes/icons.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/style.css?v=${STATIC_FILE_VERSION}"/>" rel="stylesheet" type="text/css" />
<!--page=<%=request.getRequestURL().toString()%>-->
<script type="text/javascript" src="<c:url value="/resources/scripts/core.js?v=${STATIC_FILE_VERSION}"/>"></script>
<%
if("2".equals(version)){%>
<script>var __WSSIP_DIC_URL__='<c:url value="/commons/dict/query.action"/>';</script>
<script type="text/javascript" src="<c:url value="/resources/miniui/jquery-1.11.0.min.js?v=${version}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/miniui/miniui.cmc.js?v=${version}"/>"></script>
<%}else{%>
<script>var __DICRELATE_URL__='<c:url value="/workspace/getRelatedDic.action"/>'; </script>
<script type="text/javascript" src="<c:url value="/resources/miniui/jquery-1.7.2.min.js?v=${version}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/miniui/miniui-3.3.js?v=${version}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/miniui/mini-codeSelect.js?v=${version}"/>"></script>
<%}%>
<script type="text/javascript" src="<c:url value="/resources/miniui/mini-utils.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/miniui/miniui-cmc.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/json2.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/dateUtils.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/StringEx.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/utf8.js?v=${STATIC_FILE_VERSION}"/>"></script>
<link href="<c:url value="/resources/jquery/ajaxfileupload/ajaxfileupload.css?v=${STATIC_FILE_VERSION}"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/jquery/ajaxfileupload/ajaxfileupload.js?v=${STATIC_FILE_VERSION}"/>"></script>
<link href="<c:url value="/resources/miniui-extend/css/wssip-mini-css.css?v=${STATIC_FILE_VERSION}"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/miniui-extend/mini-extend.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/miniui-extend/mini-autocomplete-wssip.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/wssipUtils.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/fileUtil.js?v=${STATIC_FILE_VERSION}"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/scripts/data.js?v=${STATIC_FILE_VERSION}"/>"></script>
<%
request.setAttribute("__INCLUDED__", version);
}
%>
<script>var __CONTEXT_PATH__="${pageContext.request.contextPath}"; </script>