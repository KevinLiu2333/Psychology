<%--
  Created by IntelliJ IDEA.
  User: liukun
  Date: 2017/6/1
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功页面</title>
</head>
<body>
<%--通过表达式访问ServletContext对象的属性--%>
本站访问次数为:${applicationScope.counter}<br>
<%--通过表达式访问HttpSession对象的属性--%>
${sessionScope.user},您已经登录!<br>
<%--通过表达式访问HttpServletRequest对象的属性--%>
${requestScope.tip}
从系统中读取的Cookie值:${cookie.user.value}
</body>
</html>
