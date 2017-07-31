<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>我的桌面</title>
</head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frame/1/index.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frame/1/style.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frame/1/portal_index.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frame/1/portal_dialog.css"/>"/>
<script type="text/javascript" src="<c:url value="/resources/js/pack/fashion/puck.jquery-1.5.1.common.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pack/fashion/puck.jquery-1.5.1.ui-plugin.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/portal/personal.js?v=1"/>"></script>
<script type="text/javascript">
var monInterval = 3;
var moduleIdStr = '';
var funcIdStr = "${sessionScope['com.wondersgroup.cmc.appids']}";
if(funcIdStr==""){
	funcIdStr = "430201";
}
var ostheme = '1';
var static_server = "${pageContext.request.contextPath}";

//-- 可用菜单 --
var funcarray = new Array();
//-- 一级菜单 --
var fmenu = new Array();
<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.list']}" var="menu">
	var menuObject = new Object();
	menuObject.menuId = "${menu.id}";
	menuObject.parentId = "${menu.pid}";
	menuObject.menuText = "${menu.name}";
	menuObject.url = static_server+"${menu.url}";
	menuObject.iconfont = "${menu.iconCls}";
	fmenu.push(menuObject);
	funcarray["${menu.id}"]=menuObject;
</c:forEach>
//-- 二级菜单 --
var smenu = new Array();
<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.second.list']}" var="menu">
	var menuObject = new Object();
	menuObject.menuId = "${menu.id}";
	menuObject.parentId = "${menu.pid}";
	menuObject.menuText = "${menu.name}";
	menuObject.url = static_server+"${menu.url}";
	menuObject.iconfont = "${menu.iconCls}";
	if(smenu["m${menu.pid}"]){
		smenu["m${menu.pid}"].push("${menu.id}");
	} else {
		smenu["m${menu.pid}"] = new Array();
		smenu["m${menu.pid}"].push("${menu.id}");
	}
	funcarray["${menu.id}"]=menuObject;
</c:forEach>
//-- 三级菜单 --
var tmenu = new Array();
<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.third.list']}" var="menu">
	var menuObject = new Object();
	menuObject.menuId = "${menu.id}";
	menuObject.parentId = "${menu.pid}";
	menuObject.menuText = "${menu.name}";
	menuObject.url = static_server+"${menu.url}";
	menuObject.iconfont = "${menu.iconCls}";
	if(tmenu["f${menu.pid}"]){
		tmenu["f${menu.pid}"].push("${menu.id}");
	} else {
		tmenu["f${menu.pid}"] = new Array();
		tmenu["f${menu.pid}"].push("${menu.id}");
	}
	funcarray["${menu.id}"]=menuObject;
</c:forEach>

//定义进入模块文字
var entertip = "查看更多";
</script>
<style>
.dialog .body .center .msg-content{
	 padding: 5px;
	 min-height: 260px;
}
.dialogContainer{
	 left: 50%;
	 margin-left: -365px;
}
.msg-content .TableList{
	 margin: 0px auto;
}
a.enter-icon{
	 display: inline-block;
	 height: 24px;
	 float: right;
	 margin-right: 20px;
	 line-height: 24px;
	 color: #fff;
}
</style>
<body>
	<input type="hidden" id="appid" value="" />
	<input type="hidden" id="appname" value="" />
	<input type="hidden" id="appurl" value="" />
	<input type="hidden" id="apptitle" value="" />
	<div id="control"> 
		<table align="center">
			<tr>
				<td class="control-l"></td>
				<td class="control-c"></td>
				<td class="control-r"><a id="openAppBox" title="打开功能盒子" href="javascript: void(0)" class="cfg"></a> </td>
			</tr>
		</table>
	</div>
	<div class="slidebox">
	 	<div id="trash"></div>
	  	<div id="container"></div>
	</div>
	<div id="overlay"></div>
	<div class="background"></div>
</body>
</html>