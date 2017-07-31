<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心-资源共享交换平台</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<script type="text/javascript">

$(document).ready(function(){

    var a="${(obj.byid)}";
    if(a=='1')
    {
    $("#zysq").trigger("click");
    
    document.getElementById("zysq").click();
    }else{
    	 $("#byjh").trigger("click");
    	    
    	    document.getElementById("byjh").click();
        }
    	   //window.location.href="${ctx}/zymlgx/toResourceApplyList";
});
</script>
<div class="header" id="header">
	<h1 style="width:200px">资源共享交换平台</h1>
    <span>
    <a href="#"><img src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle" title="管理员"></a>丨
    <a  href="${ctx}/logout"><img src="${ctx}/skins/style/images/out_03.png" align="absmiddle" title="退出"></a>
    </span>
</div>
<div class="menu" id="menu">
	<dl>
		<dt class="menu_tCon">资源目录管理</dt>
		<dd>
			<a href="${ctx}/zymlgl/toZxbmList" target="main_frame" id="zxbm"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">在线编目</a>
			<a href="${ctx}/zymlgl/checkList" target="main_frame" id="zysh"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">目录审核</a>
			<!-- 
			<a href="${ctx}/zymlgl/toContentIndex" target="main_frame" id="zyml"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">资源目录检索</a>
			 -->
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">资源数据管理</dt>
		<dd>
			<a href="${ctx}/zymlgx/toResourceApplyList" target="main_frame" id="zysq"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">资源申请</a>
			<a href="${ctx}/zymlgx/checkResourceApply" target="main_frame" id="sqsh"><img src="${ctx}/skins/style/images/menu_icon_19.png" pic-id="menu_icon_19">资源审核</a>
		</dd>
	</dl>
	<dl>
		<dt class="menu_tCon">数据资源统计</dt>
		<dd>
			<a href="${ctx}/jh/toHouse" id="byjh" target="main_frame"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">数据资源统计</a>
			<%-- <a href="${ctx}/jh/toPeople" target="main_frame" id="rksj"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">资源数据</a> --%>
		</dd>
	</dl>
	<!-- 
	<dl>
		<dt class="menu_tCon">交换节点监控</dt>
		<dd>
			<a href="${ctx}/jh/toHouse" target="main_frame" onClick="changeposition('房屋房产数据库')"><img src="${ctx}/skins/style/images/menu_icon_14.png" pic-id="menu_icon_14">房屋房产数据库</a>
			<a href="${ctx}/jh/toPeople" target="main_frame" onClick="changeposition('人口数据库')" id="rksj"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">人口数据库</a>
			<a href="${ctx}/jh/toCorporation" target="main_frame" onClick="changeposition('法人数据库')"><img src="${ctx}/skins/style/images/menu_icon_16.png" pic-id="menu_icon_16">法人数据库</a>
		</dd>
	</dl>
	 -->
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
	//首页-数据共享平台-资源目录管理、资源目录共享、数据交换管理  链接用
	var lianjie = document.getElementById("${obj.id}");
	if(!( lianjie == '' || lianjie == null)){
		lianjie.click();
	}
</script>

</body>
</html>
