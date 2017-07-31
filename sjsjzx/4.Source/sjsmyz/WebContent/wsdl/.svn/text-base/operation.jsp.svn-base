<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>

<%@ page import="wsclient.util.ComponentBuilder" %>

<%@ page import="wsclient.domain.ServiceInfo" %>
<%@ page import="wsclient.domain.OperationInfo" %>
<%@ page import="wsclient.domain.ParameterInfo" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无标题文档</title>
</head>

<body>
<table width="200" border="0">
<%
try{
//String wsdlLocation = "http://jcjg.mlr.gov.cn/Module/DataInteractive/GisqStandard.asmx?WSDL";
//String wsdlLocation = "http://localhost:8080/nutz_dwz_hr/services/EchoService?wsdl";

String wsdlLocation = request.getParameter("wsdl_location");
System.out.println("wsdlLocation:" + wsdlLocation);

if(wsdlLocation!=null){
ComponentBuilder builder = new ComponentBuilder();
ServiceInfo serviceInfo = new ServiceInfo();

serviceInfo.setWsdllocation(wsdlLocation);
serviceInfo = builder.buildserviceinformation(serviceInfo);

//将web服务信息放到session中，以便后继页面调用
session.setAttribute("serviceInfo",serviceInfo);

Iterator iter = serviceInfo.getOperations();
while (iter.hasNext()) {
	OperationInfo oper = (OperationInfo) iter.next();	
%>
	<tr><td><a href="parameter.jsp?operation_name=<%=oper.getTargetMethodName() %>" target="upFrame"><%=oper.getTargetMethodName() %></a></td></tr>
<%
	List inps = oper.getInparameters();	
	//log.info("方法名:" + oper.getTargetMethodName());
	Iterator iter1 = inps.iterator();
	while(iter1.hasNext()) {
		ParameterInfo element = (ParameterInfo) iter1.next();
		//log.info("参数名为:" + element.getName());
		//log.info("参数类型为:" + element.getKind());
%>
	<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<%=element.getName()+"::"+element.getKind() %></td></tr>
<%
	}
	
	List outps = oper.getOutparameters();
	Iterator iter2 = outps.iterator();
	if(iter2.hasNext()) {
		ParameterInfo element = (ParameterInfo) iter2.next();
		//log.info("参数名为:" + element.getName());
		//log.info("参数类型为:" + element.getKind());
%>
	<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;return&nbsp;&nbsp;<%=element.getName()+"::"+element.getKind() %></td></tr>
<%
	}
}
}else{
%>
	<tr><td>WSDL-URL is null.</td></tr>
<%	
}
}catch(Exception e){
%>
  返回结果：<br/>
  <tr><td><%=e.getMessage() %></td></tr>
<%	
}
%>
</table>

</body>
</html>
