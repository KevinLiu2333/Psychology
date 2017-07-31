<%@page import="com.wondersgroup.cmc.ca.utils.CaServiceProxy"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
	CaServiceProxy caServiceProxy=(CaServiceProxy)WebApplicationContextUtils.getRequiredWebApplicationContext(application).getBean("caServiceProxy");
	String version=caServiceProxy.getVersion();
%>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/seccom.js?<%=version%>"></script>