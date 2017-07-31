<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>松江区政务数据中心-数据填报</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
</head>
<body>
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
			<ul>
				<li><a href="#tab1" class="selected">登录日志</a></li>
				<li><a href="#tab2" >数据查询日志</a></li>
				<li><a href="#tab3" >接口调用日志</a></li>
				<li><a href="#tab4" >用户操作日志</a></li>
			</ul>
		</div>
		<div id="tab1" class="tabson">
	    		<iframe src="${ctx}/log/toLoginLog" height="650px" width="100%" frameborder="0"></iframe>
	    </div>
		<div id="tab2" class="tabson">
	    		<iframe src="${ctx}/log/toDwLog" height="650px" width="100%" frameborder="0"></iframe>
	    </div>
		<div id="tab3" class="tabson">
	    		<iframe src="${ctx}/log/toAPILog" height="650px" width="100%" frameborder="0"></iframe>
	    </div>
		<div id="tab4" class="tabson">
	    		<iframe src="${ctx}/log/toOperateLog" height="650px" width="100%" frameborder="0"></iframe>
	    </div>
	</div>
</body>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
</script>
</html>