<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>Dreamwork</title>
<!-- dwz.core bug 214行，type:'post' : 查询之后，新增，提交，刷新列表 中文乱码 -->
<link href="${ctx}/tiles/dreamui/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/tiles/dreamui/themes/css/frame.core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/tiles/dreamui/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<!--[if IE]>
<link href="${ctx}/tiles/dreamui/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx}/tiles/dreamui/js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="${ctx}/tiles/dreamui/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/jquery.bgiframe.js" type="text/javascript"></script>

<!-- 编辑器 -->
<%-- <script src="${ctx}/tiles/dreamui/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script> --%>
<!-- 上传附件 -->
<%-- <script src="${ctx}/tiles/dreamui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script> --%>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<%-- <script src="${ctx}/tiles/dreamui/chart/raphael.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/tiles/dreamui/chart/g.raphael.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/tiles/dreamui/chart/g.bar.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/tiles/dreamui/chart/g.line.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/tiles/dreamui/chart/g.pie.js" type="text/javascript"></script> --%>
<%-- <script src="${ctx}/tiles/dreamui/chart/g.dot.js" type="text/javascript"></script> --%>

<script src="${ctx}/tiles/dreamui/js/dream.ui.core.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.util.date.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.regiondream.ui.h.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.drag.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.tree.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.accordion.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.frame.ui.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.theme.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.navTab.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.tab.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.resize.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.dialog.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.stable.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.ajax.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.pagination.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.database.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.effects.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.panel.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.history.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.combox.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.print.js" type="text/javascript"></script>
<!-- ztree Start -->
<script src="${ctx}/tiles/zTree/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<link href="${ctx}/tiles/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- ztree End -->
<%-- <script src="${ctx}/tiles/dreamui/js/dream.ui.min.js" type="text/javascript"></script> --%>
<script src="${ctx}/tiles/dreamui/js/dream.ui.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/js/dream.ui.frame.init.js" type="text/javascript"></script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main" url="${obj}"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
				</div>
				<ul class="tabsMoreList">
				
					<li><a href="javascript:;">main</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="pageFormContent" layoutH="80">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>