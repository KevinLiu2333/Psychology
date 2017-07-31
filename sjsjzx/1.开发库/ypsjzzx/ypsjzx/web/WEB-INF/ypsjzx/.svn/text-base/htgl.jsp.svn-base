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
	
	<h3 class="page-header" funtype="funh3" index = "2">数据项套件</h3>
	<div class="row">
	
		<a href="${ctx}/suite/data/db/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-0"> 
		<i class="ace-icon fa fa-database bigger-230"></i>
		数据资源配置
		</a>
		
		<!--<a href="${ctx}/merit/toFwrzList" class="btn btn-app btn-yellow" funtype="funa" index = "2-2">
		<i class="ace-icon fa fa-building-o  bigger-230"></i>
		库表配置管理
		</a>
		-->
		
		<a href="${ctx}/suite/config/table/toTableList" class="btn btn-app btn-yellow" funtype="funa" index = "2-1">
		<i class="ace-icon fa fa-building-o  bigger-230"></i>
		库表配置管理
		</a>
		
		<a href="${ctx}/suite/data/unit/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-2">
		<i class="ace-icon fa fa-cubes bigger-230"></i>
		图表数据元配置
		</a>
		
		<a href="${ctx}/suite/data/term/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-3">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		图表数据项配置
		</a>
		
		<a href="${ctx}/suite/data/mult/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-4">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		报表数据项配置
		</a>
		<a href="${ctx}/suite/data/df/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-5">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		文件缓存配置
		</a>
		
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "3">数据展现套件</h3>
	<div class="row">
		
		<a href="${ctx}/suite/chart/maintainList" class="btn btn-app btn-success" funtype="funa" index = "3-3">
		<i class="ace-icon fa fa-line-chart bigger-230"></i>
		数据图表配置
		</a>
		<a href="${ctx}/suite/csrq/report/toIndex" class="btn btn-app btn-success" funtype="funa" index = "3-4">
		<i class="ace-icon fa fa-table bigger-230"></i>
		数据报表配置
		</a>
		
	</div>
	<h3 class="page-header">资源目录</h3>
	<div class="row">
		<a href="${ctx }/zy/sourceList" class="btn btn-app btn-yellow">
		<i class="ace-icon fa  fa-asterisk bigger-230"></i>
		资源目录配置
		</a>
		<a href="${ctx }/zy/queryZyList" class="btn btn-app btn-yellow">
		<i class="ace-icon fa  fa-pencil bigger-230"></i>
		资源目录审批
		</a>
		<a href="${ctx }/zy/queryFbList" class="btn btn-app btn-yellow">
		<i class="ace-icon fa  fa-bullhorn bigger-230"></i>
		资源目录发布
		</a>
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "5">服务套件</h3>
	<div class="row">
		<a href="${ctx}/fw/toPublish" class="btn btn-app btn-primary" funtype="funa" index = "5-1">
		<i class="ace-icon fa fa-volume-up bigger-230"></i>
		服务发布
		</a>
		<a href="${ctx}/fw/toFwfbList" class="btn btn-app btn-primary" funtype="funa" index = "5-2">
		<i class="ace-icon fa fa-volume-up bigger-230"></i>
		 服务管理
		</a>
		
	</div>
	<h3 class="page-header" funtype="funh3" index = "4">日志套件</h3>
	<div class="row">
		<a href="${ctx}/suite/log/viewCode" class="btn btn-app btn-pink" funtype="funa" index = "4-1">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		日志代码表
		</a>
		<a href="${ctx}/suite/log/configList" class="btn btn-app btn-pink" funtype="funa" index = "4-2">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		操作日志配置
		</a>
	</div>
	<h3 class="page-header" funtype="funh3" index = "1">用户管理套件</h3>
	<div class="row">
	
		<a href="${ctx}/suite/unit/toUnitList" class="btn btn-app btn-info" funtype="funa" index = "1-0">
		<i class="ace-icon fa fa-sitemap bigger-230"></i>
		单位管理
		</a>
		
		<a href="${ctx}/suite/auth/toAuthList" class="btn btn-app btn-info" funtype="funa" index = "1-1">
		<i class="ace-icon fa fa-tasks bigger-230"></i>
		资源管理
		</a>
		
		<a href="${ctx}/suite/user/toUserList" class="btn btn-app btn-info" funtype="funa" index = "1-2">
		<i class="ace-icon fa  fa-user-plus bigger-230"></i>
		用户管理
		</a>
		
	</div>
</div>
<script type="text/javascript">
	
</script>
<jsp:include page="/cj/foot.jsp"/>
</body>
</html>
