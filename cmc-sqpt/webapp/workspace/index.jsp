<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<title>社区平台</title>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
</head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/bootstrap/css/bootstrap.min.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/bootstrap/tag/bootstrap.tag.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/intro/show_guide.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/animate/animate.min.css?v=1"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/frame/1/index.css?v=1"/>"/>
<script type="text/javascript" src="<c:url value="/resources/js/pack/common/puck.jquery_bootstrap.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pack/frame/puck.frame.other.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugin.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/seajs/2.1.1/sea.js?v=1"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/base64/base64.min.js?v=1"/>"></script>
<script type="text/javascript">
	self.moveTo(0,0);
	self.resizeTo(screen.availWidth,screen.availHeight);
	self.focus();
	
	var static_server = "${pageContext.request.contextPath}";
	var bTabStyle = true;
	//--TODO 目前为本机时间
	var MACHINE_TIME = new Date();
	//-- 一级菜单 --
	var first_array = new Array();
	<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.list']}" var="menu">
		var menuObject = new Object();
		menuObject.menuId = "${menu.id}";
		menuObject.parentId = "${menu.pid}";
		menuObject.menuText = "${menu.name}";
		menuObject.url = static_server+"${menu.url}";
		menuObject.iconfont = "${menu.iconCls}";
		first_array.push(menuObject);
	</c:forEach>
	
	//-- 一级菜单对应的字体图标 --
	var module2iconfont	= {
		"2": "&#xe63b;",
		"3": "&#xe639;",
		"5": "&#xe650;",
		"6": "&#xe66d;",
		"7": "&#xe615;",
		"8": "&#xe603;",
		"default": "&#xe67a;"
	};
	var second_array = new Array();
	<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.second.list']}" var="menu">
		var menuObject = new Object();
		menuObject.menuId = "${menu.id}";
		menuObject.parentId = "${menu.pid}";
		menuObject.menuText = "${menu.name}";
		menuObject.url = static_server+"${menu.url}";
		menuObject.iconfont = "${menu.iconCls}";
		second_array.push(menuObject);
	</c:forEach>
	//-- 三级菜单 --
	var third_array = new Array();
	<c:forEach items="${sessionScope['com.wondersgroup.cmc.menu.third.list']}" var="menu">
		var menuObject = new Object();
		menuObject.menuId = "${menu.id}";
		menuObject.parentId = "${menu.pid}";
		menuObject.menuText = "${menu.name}";
		menuObject.url = static_server+"${menu.url}";
		menuObject.iconfont = "${menu.iconCls}";
		third_array.push(menuObject);
	</c:forEach>
	var loginUser = {uid:"${sessionScope['com.wondersgroup.wssip.operator.id']}", user_id:"${sessionScope['security.login.name']}", user_name:"${sessionScope['com.wondersgroup.wssip.operator.name']}"};
	//var portalLoadArray = [{id:"", url:"${pageContext.request.contextPath}/workspace/portal.jsp", title:"我的桌面", closable:true}];
	var portalLoadArray = new Array();
	var portalLoadArrayObject = new Object();
	portalLoadArrayObject.id = "";
	portalLoadArrayObject.url = static_server+"/workspace/portal.jsp";
	portalLoadArrayObject.title = "我的桌面";
	portalLoadArrayObject.closable = true;
	portalLoadArray.push(portalLoadArrayObject);
	
	<c:forEach items="${sessionScope['com.wondersgroup.cmc.protal.load.array']}" var="portalLoad">
		var menuObject = new Object();
		menuObject.id = "${portalLoad['id']}";
		menuObject.url = static_server+"${portalLoad['url']}";
		menuObject.title = "${portalLoad['title']}";
		menuObject.closable = true;
		portalLoadArray.push(menuObject);
	</c:forEach>

</script>
<script type="text/javascript">
seajs.config({
	base: '${pageContext.request.contextPath}/resources/js/',
	alias: {
		'underscore': 'backbone/underscore.js',
		'backbone': 'backbone/backbone.min.js',
		'menu-aim': 'jquery-1.10.2/jquery.menu-aim.js',
		'miniNotification': 'miniNotification.js',
		'wdDesk':'wdDesk/puck.wdDesk.js'
	}
});
</script>
<script>
jQuery(function(){
	seajs.use(['wdDesk', 'backbone'], function(wdDesk){
		var wdDesk = new wdDesk.WdDesk;
		window.wdDesk = wdDesk;
	});
});
</script>
</head>
<body class="frame">
	<div id="north" class="navwrapper">
		<div id="infobar" class="navbar pull-right">
			<ul class="infonav">
				<li id="info_avater" class="navin" data-placement="left" data-toggle="tooltip" title="" data-original-title="用户信息">
					<a href="javascript:;" id="avatar" class="nav-item iconfont">&#xe63e;</a>										
					<div class="info-wrap">
						<div class="person-info-header clearfix">
							<div class="person-info-avator">
								<img src="${pageContext.request.contextPath}/resources/images/avatar/0.gif" width="60" height="60" id="loginavatar">
							</div>
							<div class="person-info-content">				
								<h6 class="person-info-name">
									<span>${sessionScope['com.wondersgroup.wssip.operator.name']}</span>
								</h6>		 
								<p class="person-info-detail">
									<span class="person-info-department">${sessionScope['com.wondersgroup.wssip.operator.department.name']}</span>
								</p>
							</div>
						</div>
						<div class="person-info-body">
							<a href="javascript:;" id="person_info" hidefocus="hidefocus">控制面板</a>
							<a href="javascript:;" id="uaLogout" hidefocus="hidefocus">注销</a>
						</div>
					</div> 
				</li>
				<li class="navin">
					<a href="javascript:;" id="searchbar" class="nav-item searchbar iconfont" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="搜索">&#xe603;</a>
				</li>
				<li class="navin">
					<a href="javascript:;" id="totaskbar" class="nav-item iconfont" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="任务中心">&#xe641;</a>
				</li>
				<li class="navin">
					<a href="javascript:;" id="eastbar" class="nav-item iconfont" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="通知中心">&#xe60b;</a>
				</li>
			</ul>
		</div>
		<div id="logo" class="pull-left">
			<a href="javascript:;" onClick="javascript:createTab('portal_','我的桌面','${pageContext.request.contextPath}/workspace/portal.jsp','0');">
				<img src="${pageContext.request.contextPath}/resources/css/frame/1/images/logo_simple.png" style="margin:8px 0 0 0;"/>
			</a>
		</div>
		<div id="taskbar" class="pull-left">
			<div id="tabs_left_scroll" class="tabs-scroll scroll-left"></div>
			<div id="tabs_container" class="tabs-container"></div>
			<div id="tabs_right_scroll" class="tabs-scroll scroll-right"></div>
		</div>
	</div>
	<div id="funcbar" class="funcbar">
		<div id='funcmenu_switcher'>
			<span>导航菜单</span>
			 	<i class="funcmenu_switcher"></i>
		</div>
		<div id="funcbar_left"></div>
		<div id="funcbar_right">
			<i class="iconfont js-inNocbox" data-placement="bottom" data-toggle="tooltip" title="" data-original-title="事务提醒">&#xe681;</i>
		</div>
	</div>
	<div id="west" class="west">
		<div id ="west-body-wrapper">
			<div class="west-body">
				<div class="menu-scroll scroll-up"></div>
				<ul id="first_menu" class="first-menu"></ul>
				<div class="menu-scroll scroll-down"></div>
			</div>
		</div>
		<div class="west-footer">
			<ul class="ft-links clearfix" >
				<li class="ft-link"><a href="javascript:;" style="height: 22px !important;font-size: 18px;" class="west-handle"><i class="iconfont left_arrow" data-placement="top" data-toggle="tooltip" title="" data-original-title="图标模式">&#xe636;</i><i class="iconfont right_arrow" data-placement="top" data-toggle="tooltip" title="" data-original-title="图标+文字模式">&#xe637;</i></a></li>
			</ul>
		</div>
	</div>
	<div id="center" class="center">
		<div class="framebg" id="framebg"><div><a href="javascript:;" onClick="createTab('portal_','我的桌面','${pageContext.request.contextPath}/workspace/portal.jsp','0');" >我的桌面</a></div></div>
	</div>
				
	<ul class="msg-list">
		<div id="msg-ignore"><i class="iconfont"></i><span>忽略全部</span></div>
	</ul>
	<div id="east" class="east">
		<ul class="nav nav-tabs">
			<li class="nav-pill active" panelType="today"><a href="javascript:;" class="pill-bg">今日</a></li>
			<li class="nav-pill" panelType="msg"><a href="javascript:;" class="pill-bg">消息</a></li>
			<div class="tab-content" id="east-tab">
				<div class="tab-pane pane-today active">
					<div class="dateArea" id="datetime"><div id="date" class="weather-date" title=""><span></span></div><div id="mdate" title=""></div></div>
					<div class="mod">
						<div class="mod-hd"><span class="mod-hd-title">日程</span></div>
						<div class="mod-bd">
							<ul id="cal_list" class="calendar-list"></ul>
							<div class="notip" id="caltip">今日暂无日程</div>
						</div>
					</div>
					<div class="mod">
						<div class="mod-hd"><span class="mod-hd-title">提醒事项</span></div>
						<div class="mod-bd">
							<ul id="remind_list" class="remind_list"></ul>
							<div class="notip" id="remindtip">今日暂无提醒</div>
						</div>
					</div>
				</div>
				<div class="tab-pane pane-msg">
					<div class="btn-group msg-tool" id="msg-tool">
						<button type="button" msg-panel="nocbox" class="btn btn-mini btn-primary"><span>事务提醒</span></button>
						<button type="button" msg-panel="noticebox" class="btn btn-mini"><span>通知公告</span></button>
					</div>
					<div id="nocbox" class="msg-panel active">
						<div class="noc" id="new_noc_panel">
							<div id="nocbox_tips" class="nocbox_tips"><div class="loading"></div></div>
							<div id="nodata_tips" class="nodata_tips"><div class="">没有未读的事务提醒<a href="javascript:;" hidefocus="hidefocus" id="tohistory" class="tohistory">查看历史消息</a></div></div>
							<div class="noc-info" id="new_noc_title">共<span id="noc_item_num" class="noc_item_num">0</span>条消息记录</div>
							<div id="new_noc_list_wrapper" >
								<div id="new_noc_list" class="new_noc_list">
								</div>
							</div>
							<div class="noc-nav-bar">
								<a href="javascript:;" hidefocus="hidefocus" id="ViewAllNoc" class="viewbtn"><i class="iconfont">&#xe60c;</i>全部已阅</a>
								<a href="javascript:;" hidefocus="hidefocus" id="ViewDetail" class="viewbtn"><i class="iconfont">&#xe609;</i>查看全部</a>
								<a href="javascript:;" hidefocus="hidefocus" class="noc-right" id="check_remind_histroy"><i class="iconfont">&#xe606;</i>历史消息</a>
							</div>
						</div>
					</div>
					<div id="noticebox" class="msg-panel">
						
					</div>
				</div>
			</div>
		<a href="javascript:;" class="east-handle"><i class="iconfont left_arrow">&#xe637;</i></a>
	</div>

	<div class="search-container">
		<!-- 关闭按钮 -->
		<div class="iconfont search-close-btn">&#xe621;</div>
		<!-- 搜索框 -->
		<div class="search-box">
			<span class="search-input"><input id="search-input" type="text" autocomplete="off" placeholder="搜索用户、菜单、工作流、通讯簿、日程、帮助..."></span>
			<span class="search-btn"><input id="search-btn" type="submit" value="搜索" search-type="user"></span>
		</div>

		<!-- 搜索结果返回区 -->
		<div class="search-results-wrapper">
			<!-- 搜索结果切换tab -->
			<ul class="search-results-tabs">
				<li class="user-tab active" search-type="user" searched=""><span>用户</span><span class="search-counter search-counter-user">(0)</span></li>
				<li class="menu-tab" search-type="menu" searched=""><span>菜单</span><span class="search-counter search-counter-menu">(0)</span></li>
				<li class="workflow-tab" search-type="workflow" searched=""><span>工作流</span><span class="search-counter search-counter-workflow">(0)</span></li>
				<li class="contacts-tab" search-type="contacts" searched=""><span>通讯簿</span><span class="search-counter search-counter-contacts">(0)</span></li>
				<li class="calendar-tab" search-type="calendar" searched=""><span>日程安排</span><span class="search-counter search-counter-calendar">(0)</span></li>
			</ul>
			<!-- 陈列所有结果 -->
			<div class="search-results-container" id="search-results-container">
				<div class="search-results-iscroll">
					<ul class="search-results-user" >
						<!-- 翻页插件容器 -->
						<div id="pagination-user" class="pagination"></div>
					</ul>
					<ul class="search-results-menu">
						<!-- 翻页插件容器 -->
						<div id="pagination-menu" class="pagination"></div>
					</ul>
					<ul class="search-results-workflow">
						<!-- 翻页插件容器 -->
						<div id="pagination-workflow" class="pagination"></div>
					</ul>
					<ul class="search-results-contacts">
						<!-- 翻页插件容器 -->
						<div id="pagination-contacts" class="pagination"></div>
					</ul>
					<ul class="search-results-calendar">
						<!-- 翻页插件容器 -->
						<div id="pagination-calendar" class="pagination"></div>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 激光加载条(pulse bar) -->
	<div id="progressBar" class="done">
		<span id="flashBar"></span>
	</div>

	<!-- 消息(Message) -->
	<div id="mini-notification">
		<p>The notification has been successfully displayed</p>
	</div>

	<div id="overlay_theme"></div>
	 
</body>
<!--一级菜单-->
<script id="menuTmpl" type="text/x-jquery-tmpl">
<li data-submenu-id="second-menu-{{= menuId}}">
	<div id="m{{= menuId}}" hidefocus="hidefocus" class="first-menu-item"><i class="iconfont">{{= iconfont}}</i><span class="first-menu-title">{{= menuText}}</span></div>
	<div id="second-menu-{{= menuId}}" class="second-panel">
		<h4>{{= menuText}}</h4>
		<ul id="second-menulist-{{= menuId}}" class="second-menu clearfix">	
		</ul>
	</div>
</li> 
</script>
<!--二级菜单-->
<script id="secondMenuTmpl" type="text/x-jquery-tmpl">
<li class="{{if expand !=false }}expand{{/if}}"><a id="{{= menuId}}" class="second-menu-item" title="{{= menuText}}" href="javascript:;" onclick="{{= actionType}}" hidefocus="hidefocus">{{= menuText}}</a>{{if expand !=false }}<ul id="third-menulist-{{= menuId}}" class="third-menu"></ul>{{/if}}</li>
</script>
<!--三级菜单-->
<script id="thirdMenuTmpl" type="text/x-jquery-tmpl">
<li><a id="{{= menuId}}" class="third-menu-item" href="javascript:;" title="{{= menuText}}" onclick="{{= actionType}}" hidefocus="hidefocus">{{= menuText}}</a></li>
</script>
<!--系统消息提示条-->
<script type="text/template" id="item-template">
<div class="msg-wrapper	msg-id-{{= mid}}">
	<div class="msg-icon iconfont">{{if msgTipIconfont[$data.mid]}}	{{= msgTipIconfont[$data.mid]}}	{{else}} {{= msgTipIconfont[24]}} {{/if}} </div>
	<p class="msg-title">{{= title}}</p>
	<p class="msg-content">{{= msg}}</p>
	<div class="msg-count">{{= num}}</div>
	<div class="msg-close"></div>
	<div class="msg-op">
		<i>
			<svg version="1.1" id="图形" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1024px" height="1024px" viewBox="0 0 1024 1024" enable-background="new 0 0 1024 1024" xml:space="preserve">
				<path fill="#272636" d="M746.662019 300c0-51.835575 42.044582-93.865831 93.865831-93.865831 51.851948 0 93.865831 42.029232 93.865831 93.865831 0 51.836599-42.013883 93.865831-93.865831 93.865831C788.706601 393.864808 746.662019 351.836599 746.662019 300zM89.604272 300c0-51.835575 42.043558-93.865831 93.864808-93.865831 51.822272 0 93.865831 42.029232 93.865831 93.865831 0 51.836599-42.043558 93.865831-93.865831 93.865831C131.648854 393.864808 89.604272 351.836599 89.604272 300zM418.132634 300c0-51.835575 42.013883-93.865831 93.866854-93.865831 51.821249 0 93.864808 42.029232 93.864808 93.865831 0 51.836599-42.043558 93.865831-93.864808 93.865831C460.146517 393.864808 418.132634 351.836599 418.132634 300z" transform="translate(0, 812) scale(1, -1)"/>
			</svg>
		</i>
		<div class="msg-op-btns">
			<div class="ignore-this">忽略此条</div>
			<div class="ignore-all">忽略全部</div>
			{{if $data.mid != "message" }}
				<div class="mark-read">已阅此条</div>
				<div class="mark-all">已阅全部</div>
			{{/if}}
		</div>
</div>
</div>
</script>
<!--日程--> 
<script id="calendar-template" type="text/template">
<li><a href="javascript:;" cal_id="{{= id}}" cal_type="{{= type}}" class="common-font"><span class="cal_content">{{html formatCalendarTitle(title) }}</span><span class="pull-right">{{= shortstart}}</span></a></li>
</script>
<!--提醒事项--> 
<script id="reminder-template" type="text/template">
<li><a href="javascript:;" data_id="{{= id}}" class="common-font"><span class="cal_content">{{= title}}</span><span class="pull-right">{{= appoint_time}}</span></a></li>
</script>
<!--事务提醒模块--> 
<script id="noc-template" type="text/template">
<div class="noc_item noc_item_{{= type_id}}">
	<div class="noc_item_title">
		<a href="javascript:;" class="noc_item_read pull-right" type_id="{{= type_id}}" title='查看全部'><i class="iconfont">&#xe609;</i></a>
		<a href="javascript:;" class="noc_item_cancel pull-right" type_id="{{= type_id}}" title='全部已阅'><i class="iconfont">&#xe640;</i></a>
		<span>{{= type_name}}</span>
	</div>
	<ul class="noc_item_data">
	</ul>
</div>
</script>
<!--事务提醒条目--> 
<script id="nocitem-template" type="text/template">
<li id="noc_li_{{= sms_id}}" sms_id="{{= sms_id}}" url="{{= url}}" type_id="{{= type_id}}" class="">
	<a href="javascript:;" class="noc-subitem">
		<p class="noc_item_info">
			<span class="noc_item_time pull-right ">{{= send_time}}</span>
			<span class="name">{{= from_name}}</span>
		</p>
		<p class="noc_item_content">{{html content }}</p>
	</a>
</li>
</script>
<!-- 搜索模块的js模板 -->
<!-- 菜单模板 -->
<script type="text/template" id="search-template-menu">
	<li menu_id="{{= menuId}}" onclick="createTab('{{= menuId}}','{{= menuTitle}}','{{= menuUrl}}','');">{{= menuTitle}}</li>
</script>
<!-- 工作流模板 -->
<script type="text/template" id="search-template-workflow">
	<li class="workflow-search-results" url="{{= url}}" run_id="{{= runId}}">
		<a flow_id="{{= flowId}}">{{= runName}}</a>
		<div>工作流ID：<span>{{= runId}}</span> &nbsp;<br />当前办理步骤：第 <span>{{= prcsId}}</span> 步</div>
	</li>
</script>
<!-- 通讯簿模板 -->
<script type="text/template" id="search-template-contacts">
	<li u_id="{{= uid}}">
		<div class="contacts-avatar"><img src="{{= portraitUrl}}"></div>
		<div class="contacts-body">
			<span class="contacts-name">{{= uName}}</span>
			<div class="contacts-company">{{= company}} {{= department}}</div>
			<div class="contacts-contact">{{if phoneNum}}电话：{{= phoneNum}}{{/if}} &nbsp;&nbsp;&nbsp; {{if email}}邮箱：{{= email}}{{/if}} &nbsp;&nbsp;&nbsp; {{if qq}}QQ：{{= qq}}{{/if}}</div>
			<div class="contacts-address">{{if address}}地址：{{= address}}{{/if}}</div>
		</div>
		<div class="op-labels">
		{{if groupName != '1'}}
			{{if groupName == '0'}}
				<a href="email/new/?TO_ID={{= userid}}&TO_NAME={{= uName}}" target="_blank"><button class="send-email">邮件</button></a>
			{{else}}
				{{if email}}
					<a href="email/new/?TO_WEBMAIL={{= email}}" target="_blank"><button class="send-email">邮件</button></a>
				{{/if}}
			{{/if}}
		{{/if}}
		</div>
	</li>
</script>
</html>