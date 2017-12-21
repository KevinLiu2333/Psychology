<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sample</title>
<jsp:include page="/common/meta.jsp"/>
</head>
<frameset rows="102,*,48" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${ctx}/sample/toTop" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx}/sample/toLeft" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="#" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
  <frame src="${ctx}/sample/toBottom" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
</frameset>
<noframes><body>
</body></noframes>
</html>
