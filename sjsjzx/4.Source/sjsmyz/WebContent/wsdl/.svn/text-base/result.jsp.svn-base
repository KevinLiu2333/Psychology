<%@ page contentType="text/html; charset=UTF-8"%>

<%@ page import="javax.xml.namespace.QName" %>
<%@ page import="javax.xml.rpc.ParameterMode" %>
<%@ page import="javax.xml.rpc.ServiceException" %>

<%@ page import="org.apache.axis.client.Call" %>
<%@ page import="org.apache.axis.client.Service" %>
<%@ page import="org.apache.axis.encoding.XMLType" %>

<%@ page import="java.net.MalformedURLException" %>
<%@ page import="java.rmi.RemoteException" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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

<%
try{
//TODO  请在此处扩展相关类型
//定义返回值列表
Map typeMap = new HashMap();
typeMap.put("string",XMLType.XSD_STRING);
typeMap.put("integer",XMLType.XSD_INTEGER);
typeMap.put("boolean",XMLType.XSD_BOOLEAN);
typeMap.put("base64Binary",XMLType.XSD_BASE64);

//从session中取出serviceInfo
ServiceInfo serviceInfo = (ServiceInfo)session.getAttribute("serviceInfo");

String operationName = request.getParameter("operation_name");
System.out.println("operationName:" + operationName);

if(serviceInfo!=null&&operationName!=null){
	List valueList = new ArrayList();
	
	//1、设置临时参数
	String url = serviceInfo.getWsdllocation();
	String namespace = serviceInfo.getTargetnamespace();
	String methodName = operationName;
	String soapActionURI = serviceInfo.getTargetnamespace() + operationName;
	
	System.out.println("url:" + url);
	System.out.println("namespace:" + namespace);
	System.out.println("methodName:" + methodName);
	System.out.println("soapActionURI:" + soapActionURI);
	
	//2、设置axis调用参数
	Service service = new Service();
	Call call = (Call) service.createCall();
	call.setTargetEndpointAddress(new java.net.URL(url));
	call.setUseSOAPAction(true);
	call.setSOAPActionURI(soapActionURI);
	call.setOperationName(new QName(namespace, methodName));
	
	//3、设置参数
	Iterator iter = serviceInfo.getOperations();
	while (iter.hasNext()) {
		OperationInfo oper = (OperationInfo) iter.next();
		if(operationName.equals(oper.getTargetMethodName())){
			//方法参数
			List inps = oper.getInparameters();			
			Iterator iter1 = inps.iterator();
			while(iter1.hasNext()) {
				ParameterInfo element = (ParameterInfo) iter1.next();
				
				//设置调用参数
				call.addParameter(new QName(namespace, element.getName()), (QName)typeMap.get(element.getType()), ParameterMode.IN);
				
				//接受从parameter.jsp页面的数据
				valueList.add(new String(request.getParameter(element.getName()).getBytes("ISO-8859-1"), "utf-8"));	
				System.out.println(element.getName() + ":" + new String(request.getParameter(element.getName()).getBytes("ISO-8859-1"), "utf-8"));
			}
			
			//返回参数
			List outps = oper.getOutparameters();
			Iterator iter2 = outps.iterator();
			if(iter2.hasNext()) {
				ParameterInfo element = (ParameterInfo) iter2.next();
				
				//设置返回参数
				call.setReturnType((QName)typeMap.get(element.getType()));
			}
		}
	}	
	
	//4、调用
	String ret = (String) call.invoke(valueList.toArray());
%>
  返回结果：<br/>
  <textarea name="textarea" cols="120" rows="12"><%=ret %></textarea>
<%	
}else{
%>
	INVOKE RESULT is null.
<%	
}
}catch(Exception e){
%>
  返回结果：<br/>
  <textarea name="textarea" cols="120" rows="12"><%=e.getMessage() %></textarea>
<%	
}
%>

</body>
</html>
