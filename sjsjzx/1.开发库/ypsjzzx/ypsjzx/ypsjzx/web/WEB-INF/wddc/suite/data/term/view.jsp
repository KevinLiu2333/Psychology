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
	</head>
<body>
<br>
	<div class='container'>
	<h3 id="disable-responsive2" class="page-header">统计结果</h3>
	<div class="row">
		<div class="col-md-3 form-group">
			统计名称：
			${obj.term.name }
		</div>
		<c:if test="${obj.result==null }">
			<div class="col-md-12 form-group">
				当前没有进行统计！
			</div>
		</c:if>
		<c:if test="${obj.result!=null }">
			<div class="col-md-12 form-group">
				更新时间：
				<fmt:formatDate value="${obj.result.updatetime }" pattern="yyyy年MM月dd日  HH:mm:ss"/>
			</div>
			<div class="col-md-12 form-group">
				统计json1：
				${obj.result.result1 }
			</div>
			<div class="col-md-12 form-group">
				统计json2：
				${obj.result.result2 }
			</div>
		</c:if>
	</div>
	</div>
	<p align="center">
       	<button type="button" class="btn btn-warning" onclick="window.close()" style="width: 100px">关闭</button>
	</p>
</body>
</html>