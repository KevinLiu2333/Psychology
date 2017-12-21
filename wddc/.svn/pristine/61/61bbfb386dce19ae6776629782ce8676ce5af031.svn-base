<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   		<link href="${ctx}/wddc/tiles/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
	</head>
<body>
    
	<div class='container'>
	<h3 id="disable-responsive2" class="page-header" style="margin-top:0px;">统计结果
	<span  style="float: right;">
		
	</span>
	</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			统计名称：
			${obj.info.name }
		</div>
	  
		<c:if test="${obj.data!=null }">
			<div class="col-md-12 form-group">
				统计结果：<br>
				${obj.data }
			</div>
		</c:if>
	</div>
	</div>
	<p align="center">
       	<button type="button" class="btn btn-warning" onclick="window.close()" style="width: 100px">关闭</button>
	</p>
	
	
	
</body>
<script type="text/javascript">
</script>
</html>