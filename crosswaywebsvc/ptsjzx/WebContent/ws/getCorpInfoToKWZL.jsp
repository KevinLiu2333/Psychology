<%@ page import="org.apache.axis.client.Call" %>
<%@ page import="org.apache.axis.client.Service" %>
<%@ page import="org.apache.axis.encoding.XMLType" %>
<%@ page import="javax.xml.namespace.QName" %>
<%@ page import="javax.xml.rpc.ParameterMode" %>
<%@ page import="javax.xml.rpc.ServiceException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法人信息列表</title>
</head>
<body>
<%
    // 创建服务
    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    String url =  rootPath+ "/services/KwZlCorpService?wsdl";
    // 命名空间
    String soapaction = rootPath + "/services/KwZlCorpService";
    // key
    String key = "422ffa50e73b43138045ba19a01fa9e1";
    Service service = new Service();
    // 创建调用句柄
    try {
        Call call = (Call) service.createCall();
        // 设置请求地址
        call.setTargetEndpointAddress(url);
        call.addParameter("key", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        call.addParameter("acceptCode", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        call.addParameter("returntype", XMLType.XSD_STRING, ParameterMode.INOUT);//接收的参数
        // 设置调用的方法和方法的命名空间；
        call.setOperationName(new QName(soapaction, "getCorpInfoToKWZL"));
        // 调用方法并传递参数
        String result = (String) call.invoke(new Object[]{key, "施耐德", "Json"});
        out.print(result);
    } catch (ServiceException e) {
        e.printStackTrace();
        out.print("出现异常");
    }
%>
</body>
</html>
