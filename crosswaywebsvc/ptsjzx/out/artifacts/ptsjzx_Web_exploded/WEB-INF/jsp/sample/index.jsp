<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户行为分析</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="header" id="header">
	<h1>用户行为分析</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a href="#"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">用户分析</dt>
		<dd>
			<a class="menu_cCon" href="#"><img src="${ctx}/skins/style/images/menu_icon_03w.png" pic-id="menu_icon_03">部门统计</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_06.png" pic-id="menu_icon_06">职级统计</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_08.png" pic-id="menu_icon_08">年龄统计</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_10.png" pic-id="menu_icon_10">特定用户分析</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_12.png" pic-id="menu_icon_12">首次登录时间</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_13.png" pic-id="menu_icon_13">设备分析</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">频度分析</dt>
		<dd>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">操作频度分析</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">密码错误情况</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">应用对象分析</dt>
		<dd>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">应用对象分析</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">异常行为分析</dt>
		<dd>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">非工作日分析</a>
			<a href="#"><img src="${ctx}/skins/style/images/menu_icon_23.png" pic-id="menu_icon_23">夜间行为分析</a>
		</dd>
	</dl>
</div>
<div class="position" id="position">当前位置：特定用户分析</div>
<div class="content" id="content">
	<iframe src="${ctx}/skins/style/page/main_03nianling.html" id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%"></iframe>
</div>

<script>
	$(".menu dl dt").toggle(function(){
		$(this).next().slideUp();
		$(this).removeClass("menu_tCon");
	},function(){
		$(this).next().slideDown();
		$(this).addClass("menu_tCon");
	});
	var loacation="${ctx}/skins/style/images/";
	$(".menu dl dd a").click(function(){
		var srcBefore=loacation+$(".menu_cCon img").attr("pic-id")+".png";
		$(".menu_cCon img").attr("src",srcBefore);
		$(".menu dl dd a").removeClass("menu_cCon").eq($(".menu dl dd a").index(this)).addClass("menu_cCon");
		var src=loacation+$(this).children("img").attr("pic-id")+"w.png";
		$(this).children("img").attr("src",src);
	});
</script>

</body>
</html>
