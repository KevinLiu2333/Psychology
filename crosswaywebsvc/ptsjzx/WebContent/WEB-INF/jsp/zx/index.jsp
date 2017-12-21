<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息综合利用平台</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="header" id="header">
	<h1 style="width:200px">信息综合利用平台</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a href="#"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">数据查询</dt>
		<dd>
			<a class="menu_cCon" href="${ctx}/config/query/toResult?saveId=1460623452768" target="main_frame" onClick="changeposition('人口基础数据查询')"><img src="${ctx}/skins/style/images/menu_icon_03w.png" pic-id="menu_icon_03">人口数据基础查询</a>
			<a href="${ctx}/config/query/toResult?saveId=1461291529301" target="main_frame" onClick="changeposition('房屋基础数据查询')"><img src="${ctx}/skins/style/images/menu_icon_06.png" pic-id="menu_icon_06">房屋数据基础查询</a>
			<a href="${ctx}/config/query/toResult?saveId=1456475836432" target="main_frame" onClick="changeposition('法人基本数据查询')"><img src="${ctx}/skins/style/images/menu_icon_08.png" pic-id="menu_icon_08">法人数据基础查询</a>
			<a href="${ctx}/cx/toOneKeySearch" target="main_frame" onClick="changeposition('一键查询')"><img src="${ctx}/skins/style/images/menu_icon_10.png" pic-id="menu_icon_10">一键查询</a>
			<a href="${ctx}/cx/toFulltextRetrieval" target="main_frame" onClick="changeposition('全文检索')"><img src="${ctx}/skins/style/images/menu_icon_12.png" pic-id="menu_icon_12">全文检索</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">数据访问</dt>
		<dd>
		<!--  
			<a href="#" onClick="changeposition('接口服务管理');setiframe('${ctx}/jkfw/toJkfwList');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">接口服务管理</a>
		 -->
			<a href="${ctx}/jkfw/toJkfwList" target="main_frame" onClick="changeposition('接口服务管理')"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">接口服务管理</a>
			<a href="${ctx}/jkfw/toJkfwApplyList" target="main_frame" onClick="changeposition('接口服务申请')"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">接口服务申请</a>
			<a href="${ctx}/jkfw/toJkfwCheckList" target="main_frame" onClick="changeposition('接口服务审核')"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">接口服务审核</a>
			<a href="${ctx}/apifw/toOpenFwList" target="main_frame" onClick="changeposition('接口服务开通')"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">接口服务开通</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">主题分析</dt>
		<dd>
			<a href="${ctx}/zx/toZtPeople" target="main_frame" onClick="changeposition('人口基本情况')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">人口基本情况</a>
			<a href="${ctx}/zx/toZtBuilding" target="main_frame" onClick="changeposition('房屋基本情况')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">房屋基本情况</a>
			<a href="${ctx}/zx/toFrjbqk" target="main_frame" onClick="changeposition('法人基本情况')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">法人基本情况</a>
			<a href="${ctx}/zx/toZtPeopledifferent" target="main_frame" onClick="changeposition('人口差异化情况')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">人口差异化情况</a>
			<a href="${ctx}/zx/toFrcyhqk" target="main_frame" onClick="changeposition('法人差异化情况')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">法人差异化情况</a>
		</dd>
	</dl> 
	<dl>
		<dt class="menu_tCon">配置管理</dt>
		<dd>
			<a href="${ctx}/config/table/toIndex" target="main_frame" onClick="changeposition('配置管理')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">配置管理</a>
		</dd>
	</dl>
</div>
<div class="position" id="position">当前位置：信息综合利用平台</div>
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
