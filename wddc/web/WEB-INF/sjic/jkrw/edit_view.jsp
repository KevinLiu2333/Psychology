<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!--self-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/skins/css/wddc.css" />
<!--font-awesome-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
<!-- Loading jquery -->
<script type="text/javascript"
	src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css"
	rel="stylesheet" />
<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
<script type="text/javascript"
	src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function save() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "${ctx}/sjic/jkrw/save",
			data : $('#form1').serialize(),// 你的formid
			async : false,
			error : function() {
				alert("未知错误");
			},
			success : function(data) {
				alert(data);
			}
		});
	}
</script>
</head>
<body>
	<jsp:include page="/cj/header.jsp" />
	<div class='container'>
		<h3 id="disable-responsive2" class="page-header">接口实现类配置</h3>
		<form id="form1">
			<input required="required" type="hidden" id="id" name="id"
				value="${obj.info.id}">
			<div class="col-md-12 form-group">
				<div class="col-md-6">
					<div style="float: left; width: 80px; margin-top: 5px">接口id：</div>
					<input id="jkid" name="jkid" class="form-control"
						value="${obj.info.jkid }">
				</div>
				<div class="col-md-6">
					<div style="float: left; width: 100px; margin-top: 5px">
						实现类路径：</div>
					<input id="classPath" class="form-control" name="classPath"
						value="${obj.info.classPath}">
				</div>
			</div>
			<button type="button" onclick="save()"
				class=" form-control center-block btn btn-warning"
				style="width: 100px">保存</button>
		</form>
	</div>
	<br>
	<jsp:include page="/cj/foot.jsp" />
</body>
</html>