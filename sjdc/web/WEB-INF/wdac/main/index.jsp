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
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class="container">
	<h3 class="page-header">数据采集</h3>
	<div class="row">
		<a href="#" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-bug bigger-230"></i>
		爬虫采集
		</a>
		<a href="${ctx}/suite/config/form/toFormList" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-table bigger-230"></i>
		表单配置
		</a>
		<a href="${ctx }/cj/wjsc" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-folder-o bigger-230"></i>
		文件上传
		</a>
	</div>
	<h3 class="page-header">数据交换监控</h3>
	<div class="row">
		<a href="${ctx }/jk/jdgl" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-code-fork bigger-230"></i>
		交换节点管理
		</a>
		<a href="${ctx }/jk/jdck" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-random bigger-230"></i>
		交换状态监控
		</a>
		<a href="${ctx }/jk/jhjk" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-desktop bigger-230"></i>
		交换量监控
		</a>
		<a href="${ctx }/jk/bmjk" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-briefcase bigger-230"></i>
		部门交换统计
		</a>
		<a href="${ctx }/jk/jdjk" class="btn btn-app btn-yellow">
		<i class="ace-icon fa fa-sitemap bigger-230"></i>
		节点交换统计
		</a>
	</div>
	<h3 class="page-header">数据交换管理</h3>
	<div class="row">
		<a href="${ctx }/jh/jhpz" class="btn btn-app btn-pink">
		<i class="ace-icon fa fa-cogs bigger-230"></i>
		交换配置管理
		</a>
		<a href="${ctx }/jh/jhjg" class="btn btn-app btn-info">
		<i class="ace-icon fa fa-retweet bigger-230"></i>
		数据交换结果
		</a>
		
		<a href="${ctx }/jh/sjjy" class="btn btn-app btn-success">
		<i class="ace-icon fa fa-search bigger-230"></i>
		质量检查结果
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
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
</html>