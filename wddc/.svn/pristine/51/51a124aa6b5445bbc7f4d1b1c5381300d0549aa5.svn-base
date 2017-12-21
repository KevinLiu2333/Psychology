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
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
	<br>
	<div class='container'>
	<h3 id="disable-responsive2" class="page-header">更改字典项</h3>
	<div class="row">
		<div class="col-md-3 form-group">
			<select class="form-control disabled" style="width:80%; " name="columnList[${row.index }].dicId" style="width:100px;display:none;" onchange="isDic('${queryColumn.dicId}')">
				<c:if test="${queryColumn.dicId == ''} "> <option value="1" selected="selected">--非字典项--</option> </c:if>
				<option value="" selected="selected">--非字典项--</option>
				<option value="1">hello</option>
				<option value="2">world</option>
				<option value="3">monkey</option>
				<option value="4">blue</option>
			</select>
		</div>
		
	</div>
	</div>
	<p align="center">
       	<button type="button" class="btn btn-warning" onclick="window.close()" style="width: 100px">更改</button>
	</p>
</body>
</html>