<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>普陀区政务数据中心</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<frameset cols="*" frameborder="no" border="0" framespacing="0">
  <frameset cols="280,*" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx}/mlgl/toSourceQueryTree" name="leftFrame" id="leftFrame" title="leftFrame" scrolling="yes" style="border:1px solid #F0F0F0;"/>
    <frame src="${ctx }/mlgl/sourceQueryList" name="rightFrame" id="rightFrame" title="rightFrame" scrolling="yes" style="border:1px solid #F0F0F0;"/>
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>