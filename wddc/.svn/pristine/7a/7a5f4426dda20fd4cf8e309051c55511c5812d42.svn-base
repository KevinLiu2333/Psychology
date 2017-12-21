<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
    	<script src="${ctx }/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
    	
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">开关配置</h3>
<form id="form1" action="${ctx }/kernel/switch/saveSwitch" method="post">
	<input type="hidden" name="info.id" value="${obj.id }">
	<input type="hidden" name="info.code" value="${obj.code }"/>
	<div class="row">
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 开关名称：</div>
		     	<input id="name" style="width: 250px;"  name="info.name" value="${obj.name }">
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 开关状态：</div>
			<select id="state"  name="info.value" class="input-show-control" style="width: 250px;">
			</select>
		</div>
		
	</div>
	<div class="row" style="height: 200px">
		
	</div>
</form>
</div>
	<br>
	<p align="center">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
	</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var isnotdic="{'0':'关闭','1':'开启'}";
	$('#state').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.value }'
	});
});
function save(){
	if($('#name').val()==''){
		alert("请输入开关名称");
		return;
	}
	
	$('#form1').submit();
}
</script>
</html>