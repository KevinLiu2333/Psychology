<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2017/5/28
  Time: 7:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%--����Struts2�ı�ǩ��--%>
<html>
<head>
    <%--ʹ��s:text������ʻ���Ϣ--%>
    <title><s:text name="bookPageTitle"/></title>
    <%--���ָ�ʽҲ����--%>
    <%--<title><s:property value="%{getText('bookPageTitle')}"/></title>--%>
    <%--<title>������յ�ͼ��</title>--%>
</head>
<body>
<table border="1" width="360">
    <%--ʹ��s:text������ʻ���Ϣ--%>
    <caption><s:text name="bookPageTitle"/></caption>
    <%--<caption>������յ�ͼ��</caption>--%>
    <%--<%--%>
    <%--//        ��ȡ��װ�����Ϣ��ValueStack����--%>
    <%--ValueStack vs = (ValueStack) request.getAttribute("struts.valueStack");--%>
    <%--//����ValueStack��fineValue������ȡAction�е�books����ֵ--%>
    <%--String[] books = (String[]) vs.findValue("books");--%>
    <%--//�������ȫ����Ϣ--%>
    <%--for (String book:books) {--%>
    <%--%>--%>
    <%--<tr>--%>
    <%--<td>����:</td>--%>
    <%--<td><%=book%></td>--%>
    <%--</tr>--%>
    <%--<%}%>--%>
    <%--�������ValueStack�е�books����,����status�ǵ��������--%>
    <s:iterator value="books" status="index">
        <%--�ж�����Ƿ�Ϊ����--%>
        <s:if test="#index.odd == true">
            <tr style="background-color: aqua">
        </s:if>
        <s:else>
            <tr>
        </s:else>
        <td>����:</td>
        <td><s:property/></td>
        </tr>
    </s:iterator>
</table>
</body>
</html>
