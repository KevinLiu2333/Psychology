<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<title>操作成功跳转页面</title>
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript">
$(document).ready(function()  {
	if (window.opener != undefined) {
            window.opener.returnValue = "1";
     } else {
            window.returnValue = "1";
      }

	window.close();
});
</script>
</head>
<body>
操作成功跳转页面
</body>
</html>