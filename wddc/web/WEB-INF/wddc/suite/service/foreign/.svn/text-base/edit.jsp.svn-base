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
    	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<jsp:include page="/cj/header.jsp"/>
		<jsp:include page="/cj/foot.jsp"/>
		<div class='container'>
		<form id="form1" action="${ctx}/suite/service/foreign/save"  method="post">
		<div class="row">
		<div class="col-md-12">
			<h3 id="page-header"  class="page-header">对外公开服务配置</h3>
			<input type="hidden" name="bo.id" value="${obj.bo.id }"/>
		</div>
		<br>
		<br>
		<br>
		<div class="col-md-12 form-group">
		     <div  style="float: left;width: 150px;">名称：</div><input id="name" style="width: 500px;"  name="bo.name" value="${obj.bo.name }">
		</div>
		<div class="col-md-12 form-group">
		 <div  style="float: left;width: 150px;">服务地址：</div><input id="url" style="width: 500px;"  name="bo.url" value="${obj.bo.url }">
		</div>
		<div class="col-md-12 form-group">
		 <div  style="float: left;width: 150px;">页面编码：</div><input id="charset" style="width: 500px;"  name="bo.charset" value="${obj.bo.charset }">
		</div>
		</div>
			<p align="center" style="margin-top: 100px">
				<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       			<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
			</p>
		</form>
		</div>
	</body>
	<script type="text/javascript">
	function save(){//保存
		var url = $('#name').val();
		if(url==''||url==null){
			alert("请输入名称!");
			return;
		}
		var url = $('#url').val();
		if(url==''||url==null){
			alert("请输入服务地址!");
			return;
		}
		var charset = $('#charset').val();
		if(charset==''||charset==null){
			alert("请输入页面编码!");
			return;
		}
		$('#form1').submit();
	}
	</script>
</html>
	