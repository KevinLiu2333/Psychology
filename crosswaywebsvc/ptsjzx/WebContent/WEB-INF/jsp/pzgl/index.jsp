<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>配置管理</title>

<link href="${ctx}/tiles/dreamui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/tiles/dreamui/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/tiles/dreamui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${ctx}/tiles/dreamui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/skins/css/form.css" rel="stylesheet" type="text/css" media="screen"/>

<link href="${ctx}/skins/css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${ctx}/tiles/dreamui/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>

<!--[if lte IE 9]>
<script src="${ctx}/tiles/dreamui/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="${ctx}/tiles/dreamui/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>

<script src="${ctx}/tiles/dreamui/js/dream.ui.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.regional.zh.js" type="text/javascript"></script>

<script src="${ctx}/tiles/dreamui/xheditor/xheditor-1.1.14-en.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.ui.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.init.js" type="text/javascript"></script>


<script type="text/javascript">
//台帐定义中下一步操作
function tzdyNext(url,dlgId,title){
	$.pdialog.open(url,dlgId,title,"{width:1000px,height500px,mask:true}");
}
</script>
</head>

<body style=" overflow:scroll;">
	<div id="layout" >
		<div id="header" style="height:0px">
			<!-- navMenu -->
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>配置管理</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					
					<div class="accordionContent">
						<ul class="tree">
							<!-- <li ><a href="user/list" target="navTab" rel="syrkList1" style="line-height: 20px;" ><img src="${ctx}/tiles/zTree/css/img/script_wiz.gif" width="16" height="16"  style="vertical-align:bottom"/>&nbsp;快速配置向导</a></li> -->
							<li><a href="${ctx}/config/table/toTableList" target="navTab" rel="tableManageList"><img src="${ctx}/tiles/zTree/css/img/all_history_mode.gif" width="16" height="16"  style="vertical-align:bottom"/>&nbsp;库表配置</a></li>
							<li><a href="${ctx}/config/form/toFormList" target="navTab" rel="formList"><img src="${ctx}/tiles/zTree/css/img/history_list.gif" width="16" height="16"  style="vertical-align:bottom"/>&nbsp;表单配置</a></li>
							<li><a href="${ctx}/config/query/toQueryList" target="navTab" rel="queryconfig"><img src="${ctx}/tiles/zTree/css/img/table_obj.gif" width="16" height="16"  style="vertical-align:bottom"/>&nbsp;列表配置</a></li>
							
						</ul>
					</div>
					
		
				</div>
			</div>
		</div>
		<div id="container" >
			<div id="navTab" class="tabsPage" >
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>