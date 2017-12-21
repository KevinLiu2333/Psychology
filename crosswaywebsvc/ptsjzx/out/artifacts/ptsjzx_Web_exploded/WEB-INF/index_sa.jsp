<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀区数据资源管理系统</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="header" id="header">
	<h1 style="width:200px">超级管理员</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a href="${ctx }/logout"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">用户管理</dt>
		<dd>
			<a href="${ctx}/yhgl/toUserList?level=1" target="main_frame" onClick="changeposition('超级管理员用户');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">超级管理员用户</a>
			<a href="${ctx}/yhgl/toUserList?level=2" target="main_frame" onClick="changeposition('普通管理员用户');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">普通管理员用户</a>
			<a href="${ctx}/yhgl/toUserList?level=3" target="main_frame" onClick="changeposition('普通用户');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">普通用户</a>
			<a href="${ctx}/sqlinfo/tosqlinfo" target="main_frame" onClick="changeposition('大屏数据统计管理');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">大屏数据统计管理</a>
		</dd>
	</dl>
</div>

<div class="position" id="position">当前位置：超级管理员</div>
<div class="content" id="content">
	<iframe src="" id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%"></iframe>
</div>
<input type="hidden" name="iframeUrl" id="iframeUrl" value=""/>
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
		p.innerHTML="当前位置："+position;
	}
	function setiframe(url){
		//$('#iframeUrl').val(url);
		$('#main_frame').attr('src','${ctx}/frame?url='+url);
	}
</script>

</body>
</html>
