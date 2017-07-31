<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.wondersgroup.wssip.application.SessionConstants"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script>
		<%if(session.getAttribute(SessionConstants.WSSIP_OPERATOR_ID)==null){%>
			window.close();
		<%}else{
			request.getRequestDispatcher("/workspace/index.jsp").forward(request,response);
		}%>
	</script>
</head>
</html>