<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心-数据中心</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
#con{
	position:absolute;
	right:0;
	top:97px;
	bottom:0;
	left:0;
}
#con iframe{
	display:block;
	width:100%;
	height:100%;
}
#pos{
	position:absolute;
	left:0px;
	top:60px;
	right:0;
}
.pos{
	background:#f0f0f0  no-repeat 23px center;
	font-size:14px;
	color:#333333;
	line-height:36px;
	border-bottom:1px solid #dddddd;
	padding-left:46px;
}
</style>
</head>

<body>
<div class="header"   id="header">
	<h1>数据中心</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>
    <a href="#"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="pos" id="pos" >
	<a href="${ctx}/db/toTable" target="main_frame" align="absmiddle" >库表关系</a>&nbsp;
    <a href="${ctx}/db/toDoc" target="main_frame"  align="absmiddle" >标准规范</a>
</div>
<div class="" id="con">
	<iframe src="${ctx}/db/toTable" id="main_frame" name="main_frame" frameborder="0" width="100%" height="100%"></iframe>
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
