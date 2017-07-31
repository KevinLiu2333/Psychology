<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx}" />
<div class="container">

	<h3 class="page-header" funtype="funh3" index = "5">数据查询</h3>
	<div class="row">
		<a href="${ctx }/jk/jdgl" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-code-fork bigger-230"></i>
		人口数据查询
		</a>
		<a href="${ctx }/jh/jhpz" class="btn btn-app btn-pink">
		<i class="ace-icon fa fa-cogs bigger-230"></i>
		法人数据查询
		</a>
		<a href="${ctx }/jh/jhjg" class="btn btn-app btn-info">
		<i class="ace-icon fa fa-retweet bigger-230"></i>
		专题数据查询
		</a>	
		<a href="${ctx }/jh/jhjg" class="btn btn-app btn-info">
		<i class="ace-icon fa fa-retweet bigger-230"></i>
		全文检索
		</a>
	
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "5">主题分析</h3>
	<div class="row">
		<a href="${ctx }/jk/jdgl" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-code-fork bigger-230"></i>
		人口主题
		</a>
		<a href="${ctx }/jh/jhpz" class="btn btn-app btn-pink">
		<i class="ace-icon fa fa-cogs bigger-230"></i>
		法人主题
		</a>
		
	
	</div>
	
	
	<h3 class="page-header" funtype="funh3" index = "2">图表配置管理</h3>
	<div class="row">
	
		<a href="${ctx}/suite/data/db/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-0"> 
		<i class="ace-icon fa fa-database bigger-230"></i>
		数据资源配置
		</a>
	
		
		<a href="${ctx}/suite/data/unit/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-1">
		<i class="ace-icon fa fa-cubes bigger-230"></i>
		图表数据元配置
		</a>
		
		<a href="${ctx}/suite/data/term/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-2">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		图表数据项配置
		</a>
		
	
		
		<a href="${ctx}/suite/data/df/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-3">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		图表数据组装
		</a>
		
		
		<a href="${ctx}/suite/chart/maintainList" class="btn btn-app btn-success" funtype="funa" index = "2-4">
		<i class="ace-icon fa fa-line-chart bigger-230"></i>
		数据图表配置
		</a>
		
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "3">报表配置管理</h3>
	<div class="row">
		<a href="${ctx}/suite/data/db/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "3-0"> 
		<i class="ace-icon fa fa-database bigger-230"></i>
		数据资源配置
		</a>
		<a href="${ctx}/suite/data/mult/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "3-1">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		报表数据项配置
		</a>
		<a href="${ctx}/suite/csrq/report/toIndex" class="btn btn-app btn-success" funtype="funa" index = "3-2">
		<i class="ace-icon fa fa-table bigger-230"></i>
		数据报表配置
		</a>
		
	</div>
	

</div>


<jsp:include page="/cj/foot.jsp"/>

</body>
</html>
