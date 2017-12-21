<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>中心统一监控平台</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="header" id="header">
	<h1 style="width:200px">中心统一监控平台</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a href="#"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">库表关系</dt>
		<dd>
			<a href="${ctx}/db/toTable" target="main_frame" onClick="changeposition('库表关系')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">库表关系</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">统一预警</dt>
		<dd>
			<a href="${ctx}/jk/toGnyj" target="main_frame" onClick="changeposition('功能预警')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">功能预警</a>
			<a href="${ctx}/jk/toYyyj" target="main_frame" onClick="changeposition('应用预警')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">应用预警</a>
			<a href="${ctx}/jk/toFwyj" target="main_frame" onClick="changeposition('服务预警')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">服务预警</a>
		</dd>
	</dl>
	
	<dl>
		<dt class="menu_tCon">安全审计</dt>
		<dd>
			<a href="${ctx}/jk/toSafeAudit" target="main_frame" onClick="changeposition('安全审计')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">安全审计</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">统一监控</dt>
		<dd>
			<a href="${ctx}/jk/toHardWare" target="main_frame" onClick="changeposition('硬件设备')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">硬件设备</a>
			<a href="${ctx}/jk/toAppSys" target="main_frame" onClick="changeposition('应用系统')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">应用系统</a>
			<a href="${ctx}/jk/toAppSys" target="main_frame" onClick="changeposition('数据库')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">数据库</a>
			<!--  
			<a href="${ctx}/jk/toHardWare" target="main_frame" onClick="changeposition('操作系统')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">操作系统</a>
			<a href="${ctx}/jk/toHardWare" target="main_frame" onClick="changeposition('应用服务器')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">应用服务器</a>
			<a href="${ctx}/jk/toHardWare" target="main_frame" onClick="changeposition('数据访问监控')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">数据访问监控</a>
			-->
		</dd>
	</dl>
	
</div>
<div class="position" id="position">当前位置：首页</div>
<div class="content" id="content">
	<iframe src="" id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%"></iframe>
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
	function changeposition(position)
	{
		var p=document.getElementById("position");
		p.innerHTML="当前位置:"+position;
	}
</script>

</body>
</html>
