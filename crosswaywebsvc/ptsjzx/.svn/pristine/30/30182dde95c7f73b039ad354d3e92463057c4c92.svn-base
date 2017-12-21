<%@ page import="org.apache.axis.client.Service" %>
<%@ page import="org.apache.axis.client.Call" %>
<%@ page import="javax.xml.rpc.ServiceException" %>
<%@ page import="org.apache.axis.encoding.XMLType" %>
<%@ page import="javax.xml.rpc.ParameterMode" %>
<%@ page import="javax.xml.namespace.QName" %><%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2017/12/4
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增量人口列表</title>
</head>
<body>
<%
    // 创建服务
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    String url = rootPath + "/services/mzjTaizhangPeopleService?wsdl";
    // 命名空间
    String soapaction = rootPath + "/services/mzjTaizhangPeopleService";
    // key
    String key = "da442648c35048fa8bd0ff1a5fba4b6d";
    Service service = new Service();
    // 创建调用句柄
    try {
        Call call = (Call) service.createCall();
        // 设置请求地址
        call.setTargetEndpointAddress(url);
        call.addParameter("key", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        call.addParameter("nianyue", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        call.addParameter("returntype", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        // 设置调用的方法和方法的命名空间；
        call.setOperationName(new QName(soapaction, "getInfoByyuefenToMzjDztz"));
        // 调用方法并传递参数
        String result = (String) call.invoke(new Object[]{key, "201701", "Json"});
        out.print(result);
    } catch (ServiceException e) {
        e.printStackTrace();
        out.print("出现异常");
    }
%>
</body>
</html>
