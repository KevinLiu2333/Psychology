<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/21 0021
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>
<%--无论返回什么，都会保存在obj中--%>
<h1><%=request.getAttribute("obj")%>
</h1>
</body>
</html>
