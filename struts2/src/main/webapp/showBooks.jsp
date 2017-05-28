<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2017/5/28
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--导入Struts2的标签库--%>
<html>
<head>
    <title>作者李刚的图书</title>
</head>
<body>
<table border="1" width="360">
    <caption>作者李刚的图书</caption>
    <%--<%--%>
    <%--//        获取封装输出信息的ValueStack对象--%>
    <%--ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");--%>
    <%--//调用ValueStack的fineValue方法获取Action中的books属性值--%>
    <%--String[] books = (String[]) vs.findValue("books");--%>
    <%--//迭代输出全部信息--%>
    <%--for (String book:books) {--%>
    <%--%>--%>
    <%--<tr>--%>
    <%--<td>书名:</td>--%>
    <%--<td><%=book%></td>--%>
    <%--</tr>--%>
    <%--<%}%>--%>
    <%--迭代输出ValueStack中的books对象,其中status是迭代的序号--%>
    <s:iterator value="books" status="index">
        <%--判断序号是否为奇数--%>
        <s:if test="#index.odd == true">
            <tr style="background-color: aqua">
        </s:if>
        <s:else>
            <tr>
        </s:else>
        <td>书名:</td>
        <td><s:property/></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
