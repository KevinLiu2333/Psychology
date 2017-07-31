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
		<input type="hidden" id="js_ctx" value="${ctx }" />
		<br/>
		<div class='container'>
		<h2 class="page-header" style="margin-top: 5px">数据资源配置</h2>
		<form id="form1" action="${ctx }/suite/data/db/save"  method="post">
			<input type="hidden" name="dbinfo.id" value="${obj.dbinfo.id }"/>
			<input type="hidden" name="dbinfo.state" value="${obj.dbinfo.state }"/>
			<input type="hidden" name="dbinfo.configType" value="${obj.dbinfo.configType }"/>
			<div class="row">
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;"> 数据库名称(英文)：</div><input id="dbEnName" style="width: 500px;"  name="dbinfo.dbEnName" value="${obj.dbinfo.dbEnName }">
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;"> 数据库名称(中文)：</div><input id="dbname" style="width: 500px;"  name="dbinfo.dbname" value="${obj.dbinfo.dbname }">
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;">URL：</div><input id="url" style="width: 500px" name="dbinfo.url" value="${obj.dbinfo.url }">
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;">数据库类型：</div>
		     		 <select id="type" name="dbinfo.type" style="width:500px;height:30px"></select>
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;">数据库地址：</div><input id="address" style="width: 500px" name="dbinfo.address" value="${obj.dbinfo.address }">
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;">用户名：</div><input id="username" style="width: 500px" name="dbinfo.username" value="${obj.dbinfo.username }">
				</div>
				<div class="col-md-12 form-group">
		     		 <div  style="float: left;width: 150px;">密码：</div><input id="password" style="width: 500px" name="dbinfo.password" value="${obj.dbinfo.password }">
				</div>
			</div>
			<p align="center">
				<!-- <button type="button" class="btn btn-warning" onclick="test()" style="width: 100px">测试连接</button>&nbsp;&nbsp;&nbsp;&nbsp; -->
				<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       			<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
			</p>
		</form>
		</div>
		<jsp:include page="/cj/foot.jsp"/>
	</body>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#type').jsondic({
			dicid:'1001',
			initvalue:'请选择',
			defaultvalue:'${obj.dbinfo.type }'
			});
		});
		//保存
		function save(){
			var type = $('#type').val();
			if(type==''||type==null){
				alert("请选择数据库类型!");
				return;
			}
			var username = $('#username').val();
			if(username==''||username==null){
				alert("请输入用户名!");
				return;
			}
			var url = $('#url').val();
			if(url==''||url==null){
				alert("请输入URL!");
				return;
			}
			var dbname = $('#dbname').val();
			if(dbname==''||dbname==null){
				alert("请输入数据库名!");
				return;
			}
			var address = $('#url').val();
			if(address==''||address==null){
				alert("请输入数据库地址!");
				return;
			}
			$('#form1').submit();
		}
	</script>
</html>