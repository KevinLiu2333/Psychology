<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀区数据资源管理系统</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<div class="header" id="header">
		<h1 style="width: 250px">
			<a href="${ctx}/"><img style="margin-top:12px" src="${ctx}/skins/images/index/logo2.png"></a>
		</h1>
		<span> <a href="#" title="${sessionScope.user.displayName }" ><img
				src="${ctx}/skins/style/images/iconfont-user.png" align="absmiddle"
				title="${obj.displayName}"></a>丨 <a href="javascript:window.opener=null;window.open('','_self');window.close();"><img
				src="${ctx}/skins/style/images/out_03.png" align="absmiddle"
				title="退出"></a>
		</span>
	</div>

	<div class="menu" id="menu">
		${obj.menu}
		<div class="gg">
			<a href="http://31.1.2.141/" target="_blank">地理信息平台</a>
		</div>
	</div>
	<div class="position" id="position">当前位置：数据资源管理系统</div>
	<div class="content" id="content">
		<iframe id="main_frame" name="main_frame" frameborder="0" width="100%"
			height="100%"></iframe>
	</div>

	<script>
	//菜单栏的动画
	$(".menu dl dt").toggle(function() {//下降
		$(this).next().slideDown();
		$(this).removeClass("menu_tCon");
		var id = $(this).next().find("a").eq(0).attr("id");
		var btn = document.getElementById(id);
		btn.click();
	},function() {//上升
		$(this).next().slideUp();
		$(this).addClass("menu_tCon");
	});
		var loacation = "${ctx}/skins/style/images/";
		$(".menu dl dd a").click(
				function() {
					var srcBefore = loacation
							+ $(".menu_cCon img").attr("pic-id") + ".png";
					$(".menu_cCon img").attr("src", srcBefore);
					$(".menu dl dd a").removeClass("menu_cCon").eq(
							$(".menu dl dd a").index(this)).addClass(
							"menu_cCon");
					var src = loacation
							+ $(this).children("img").attr("pic-id") + "w.png";
					$(this).children("img").attr("src", src);
				});
		function changeposition(position) {
			var p = document.getElementById("position");
			p.innerHTML = "当前位置：" + position;
		}
		function changeposition2(position,message) {
			var p = document.getElementById("position");
			p.innerHTML = "当前位置：" + position+" &nbsp;<a style='color:red;font-size:12px;'>"+message+"</a>";
		}
		function setiframe(url) {
			$('#main_frame').attr('src', '${ctx}/frame?url=' + url);
		}
		window.onload = function(){
			if( ('${obj.id}'=='1') && ('${fn:contains(sessionScope.user.roleId,"4")}'=='false') ){
				$('#loc1001').parent().prev().click();
			}else{
				$('#loc${obj.id}').parent().prev().click();
			}
			
			if('${fn:contains(sessionScope.user.roleId,"2")}'=='false'){
				$("a[wyyh='wyyh']").each(function(){
					$(this).remove();
				});
			}
			
			if('${fn:contains(sessionScope.user.roleId,"AD")}'=='false'&&'${fn:contains(sessionScope.user.roleId,"2")}'=='false'){
				$("a[fwcy='fwcy']").each(function(){
					$(this).remove();
				});
			}
			if('${fn:contains(sessionScope.user.roleId,"AE")}'=='false'&&'${fn:contains(sessionScope.user.roleId,"2")}'=='false'){
				$("a[rksjbd='rksjbd']").each(function(){
					$(this).remove();
				});
			}
			if('${fn:contains(sessionScope.user.roleId,"AF")}'=='false'&&'${fn:contains(sessionScope.user.roleId,"2")}'=='false'){
				$("a[frsjbd='frsjbd']").each(function(){
					$(this).remove();
				});
			}
		};
	</script>
</body>
</html>