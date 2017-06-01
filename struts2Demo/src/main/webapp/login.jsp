<%--
  Created by IntelliJ IDEA.
  User: liukun
  Date: 2017/5/27
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script>
        function regist() {
            //获取表单元素
            var targetForm = document.forms[0];
//            action的属性为actionName!methodName的形式
            //动态修改目标表单的属性
//            targetForm.action = "Login!resgist.action";
            targetForm.action = "registAction.action";
            //提交表单
            targetForm.submit();

        }
    </script>
</head>
<body>
<form action="loginAction.action" method="post">
    <table align="center">
        <caption>
            <h3>用户登录</h3>
        </caption>
        <tr>
            <%--用户名的表单域--%>
            <td>用户名: <input name="username"></td>
        </tr>
        <tr>
            <%--密码的表单域--%>
            <td>密&nbsp;&nbsp;码: <input name="password"></td>
        </tr>
        <tr align="center">
            <td colspan="2">
                <input type="submit" value="登录">
                <input type="reset" value="重填">
                <input type="button" value="注册" onclick="regist();">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
