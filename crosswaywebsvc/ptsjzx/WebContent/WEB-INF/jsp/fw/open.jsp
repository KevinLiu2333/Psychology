<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据申请公开协议</title>
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
</head>
<script type="text/javascript">
	function next(openId){
		if($("input[type='checkbox']").is(':checked')){
			alert("你已同意该协议!");
			$('#mainForm').submit();
		}else{ 
			alert("是否阅读并同意该协议!");
		}
	}
</script>

<form id="mainForm" name="mainForm" action="${ctx}/apifw/toApiApply" method="post"> 
<input type="hidden" name="userService.applyInfo" value="${obj.chestr}"/>
<input type="hidden" name="userService.type" value="${obj.apiType}"/>
<body>
<h2 align="center">上海市普陀区数据信息共享协议</h2>
<iframe width="850px" height="400px"  src="${ctx}/apifw/xieyi"></iframe>
<div style="height:20px;padding-top: 5px;text-align: center">
	<input type="checkbox" id="xieyibox"/>已阅读协议并同意协议条款
	
	
</div>

<div style="text-align: center;margin-top: 10px;margin-bottom:15px;">
	<input type="button" onclick="next('person')" value="确定">
</div>
	
</body>
</form>
</html>

