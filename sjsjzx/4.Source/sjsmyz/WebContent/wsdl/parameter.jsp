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
<form id="form1" name="form1" method="post" action="result.jsp" target="downFrame">
<table width="600" border="0">
<%
//从session中取出serviceInfo
ServiceInfo serviceInfo = (ServiceInfo)session.getAttribute("serviceInfo");

String operationName = request.getParameter("operation_name");
System.out.println("operationName:" + operationName);

if(serviceInfo!=null&&operationName!=null){
Iterator iter = serviceInfo.getOperations();
while (iter.hasNext()) {
	OperationInfo oper = (OperationInfo) iter.next();
	//oper.getTargetMethodName()
	if(operationName.equals(oper.getTargetMethodName())){
%>
<input type="hidden" name="operation_name" value="<%=operationName %>"/>
<%
	List inps = oper.getInparameters();
	
	Iterator iter1 = inps.iterator();
	while(iter1.hasNext()) {
		ParameterInfo element = (ParameterInfo) iter1.next();
		//log.info("参数名为:" + element.getName());
		//log.info("参数类型为:" + element.getKind());
%>
	<tr><td><%=element.getName() %>：<input type="text" name="<%=element.getName() %>" />(<%=element.getKind() %>)</td></tr>
<%
	}
%>
	<tr><td><input type="submit" name="Submit" value="调用" />&nbsp;&nbsp;<input type="reset" name="Reset" value="清空" /></td></tr>
<%	
	}
}
}else{
%>
	<tr><td>WEBSERVICE INFORMATION is null.</td></tr>
<%	
}
%>
</table>
</form>
</body>
</html>
