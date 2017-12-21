<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/default.css" />
	<script type="text/javascript" src="${ctx}/skins/jk/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/skins/jk/js/monitor.js"></script>
	<script type="text/javascript" src="${ctx}/skins/jk/js/nav.js"></script>
</head>
<body>
<div class="main">
	<div class="moreAll">
		<i class="leftArrow"></i>
		<div class="moreContent">
			<div class="itemIn">
				<div class="itemTitle">交换信息量</div>
				<ul>
                            <li class="z1"><span>交换总量</span>123765</li>
                            <li class="r1 %>"><span>日交换总量</span>0</li>
                </ul>
			</div>
		</div>
	</div>
		<div class="mainLeft">
			<div class="leftTitle"><span>交换信息量</span></div>
			<div class="leftMore">更多>></div>
			<div class="leftContent">
				<div class="itemTitle">区政务数据中心</div>
				<ul>
					<li id="z01"><span>交换总量</span>78930</li>
					<li id="r01"><span>日交换总量</span>0</li>
				</ul>
				<div class="itemTitle">监控局</div>
				<ul>
					<li id="z01"><span>交换总量</span>33444</li>
					<li id="r01"><span>日交换总量</span>0</li>
				</ul>
				<div class="itemTitle">编办</div>
				<ul>
					<li id="z01"><span>交换总量</span>12500</li>
					<li id="r01"><span>日交换总量</span>0</li>
				</ul>
				<div class="itemTitle">税务局</div>
				<ul>
					<li id="z01"><span>交换总量</span>10001</li>
					<li id="r01"><span>日交换总量</span>0</li>
				</ul>
				<div class="itemTitle">工商局</div>
				<ul>
					<li id="z01"><span>交换总量</span>7899</li>
					<li id="r01"><span>日交换总量</span>0</li>
				</ul>
			</div>
		</div>
		<div class="mainRight">
			<div class="rightTitle"><span>交换节点监控</span></div>
			<div id="monitorAll">
				<div class="centerData">数据交换平台</div>
				<ul class="dataShow">
					<li class="zr330100" ><a href="${ctx}/jh/toDetail"  class="good">区政务中心</a></li>
					<li class="zr330100" ><a href="${ctx}/jh/toDetail"  class="good">税务局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">安监局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">房管局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">国土局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">公安局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">民政局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail" class="good">工商局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">编办</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">计生委</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">社保局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">质监局</a></li>
					<li class="zr330100"><a href="${ctx}/jh/toDetail"  class="good">教育局</a></li>
				</ul>
			</div>
		</div>
		<div style="clear:both;"></div>
</div>
</body>
<script type="text/javascript">
	$("document").ready(function(){
		var data = $("#monitorAll");
		$(".leftMore").mouseenter(function(){
			$(".moreAll").show();
		});
		$(".leftMore").mouseleave(function(){
			$(".moreAll").hide();
		});
		data.find(".dataShow li a.bad").mouseenter(function(){
			$(this).parent().find(".detailAll").show();
		});
		data.find(".dataShow li a.bad").mouseleave(function(){
			$(this).parent().find(".detailAll").hide();
		});
		
	});
</script>
</html>