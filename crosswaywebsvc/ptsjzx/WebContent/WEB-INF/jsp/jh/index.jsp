<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源共享交换平台</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="header" id="header">
	<h1 style="width:200px">资源共享交换平台</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a href="#"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">资源目录管理</dt>
		<dd>
			<a href="${ctx}/mlgl/list" target="main_frame" onClick="changeposition('在线编目'); "><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">在线编目</a>
			<a href="${ctx}/mlgl/checkList" target="main_frame" onClick="changeposition('资源审核发布');"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">资源审核发布</a>
			<a href="${ctx}/mlgl/treeList" target="main_frame" onClick="changeposition('资源目录检索');"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">资源目录检索</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">资源目录共享</dt>
		<dd>
			<a href="${ctx}/mlgx/toSourceApply" target="main_frame" onClick="changeposition('资源申请');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">资源申请</a>
			<a href="${ctx}/mlgx/checkSourceApply" target="main_frame" onClick="changeposition('申请审核');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">申请审核</a>
			<a href="${ctx}/mlgx/toGetSource" target="main_frame" onClick="changeposition('资源获取');"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">资源获取</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">交换节点监控</dt>
		<dd>
			<a href="${ctx}/jh/toMain" target="main_frame" onClick="changeposition('交换节点监控')"><img src="${ctx}/skins/style/images/menu_icon_06.png" pic-id="menu_icon_06">交换节点监控</a>
			<a href="${ctx}/jh/toHouse" target="main_frame" onClick="changeposition('房屋房产数据库')"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">房屋房产数据库</a>
			<a href="${ctx}/jh/toPeople" target="main_frame" onClick="changeposition('人口数据库')"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">人口数据库</a>
			<a href="${ctx}/jh/toCorporation" target="main_frame" onClick="changeposition('法人数据库')"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">法人数据库</a>
			<a href="${ctx}/jh/toMonitoring" target="main_frame" onClick="changeposition('监控统计')"><img src="${ctx}/skins/style/images/menu_icon_18.png" pic-id="menu_icon_18">监控统计</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">标准规范</dt>
		<dd>
			<a href="${ctx}/db/toDoc" target="main_frame" onClick="changeposition('标准规范')"><img src="${ctx}/skins/style/images/menu_icon_06.png" pic-id="menu_icon_06">标准规范</a>
		</dd>
	</dl>
</div>
<div class="position" id="position">当前位置：资源共享交换平台</div>
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
