<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx}" />
<div class="container">

	<h3 class="page-header text-info" funtype="funh3" index = "0">基础组件</h3>
	<div class="row">
		
		<a href="${ctx}/kernel/dic/toIndex" class="btn btn-app btn-purple" funtype="funa" index = "0-0">
		<i class="ace-icon fa fa-book bigger-230"></i>
		系统字典管理
		</a>
		
		<a href="${ctx}/kernel/file/toFileList" class="btn btn-app btn-purple" funtype="funa" index = "0-1">
		<i class="ace-icon fa fa-cloud-upload bigger-230"></i>
		上传文件管理
		</a>
		
		<a href="${ctx}/kernel/quartz/toIndex" class="btn btn-app btn-purple" funtype="funa" index = "0-2">
		<i class="ace-icon fa fa-hourglass-half  bigger-230"></i>
		定时器管理
		</a>
		
		<a href="${ctx}/kernel/tag/mgr" class="btn btn-app btn-purple" funtype="funa" index = "0-3">
		<i class="ace-icon fa  fa-credit-card  bigger-230"></i>
		数据标签管理
		</a>
		<a href="${ctx}/kernel/calendar/toIndex" class="btn btn-app btn-purple" funtype="funa" index = "0-4">
		<i class="ace-icon fa  fa-calendar  bigger-230"></i>
		个人日程管理
		</a>
		<a href="${ctx}/toIndex" class="btn btn-app btn-purple" funtype="funa" index = "0-5">
		<i class="ace-icon fa  fa-list-ol  bigger-230"></i>
		取号管理00000
		</a>
			
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "1">用户管理套件</h3>
	<div class="row">
	
		<a href="${ctx}/suite/unit/toUnitList" class="btn btn-app btn-info" funtype="funa" index = "1-0">
		<i class="ace-icon fa fa-sitemap bigger-230"></i>
		单位管理
		</a>
		
		<a href="${ctx}/suite/auth/toAuthList" class="btn btn-app btn-info" funtype="funa" index = "1-1">
		<i class="ace-icon fa fa-tasks bigger-230"></i>
		资源管理
		</a>
		
		<a href="${ctx}/suite/user/toUserList" class="btn btn-app btn-info" funtype="funa" index = "1-2">
		<i class="ace-icon fa  fa-user-plus bigger-230"></i>
		用户管理
		</a>
		
		<a href="${ctx}/suite/userStat/userOnline" class="btn btn-app btn-info" funtype="funa" index = "1-3">
		<i class="ace-icon fa  fa-users bigger-230"></i>
		在线用户
		</a>
		
		<a href="${ctx}/suite/userStat/toLoginLogList" class="btn btn-app btn-info" funtype="funa" index = "1-4">
		<i class="ace-icon fa  fa-sticky-note bigger-230"></i>
		登录日志
		</a>
		
		<a href="${ctx}/login.jsp" class="btn btn-app btn-info" funtype="funa" index = "1-5">
		<i class="ace-icon fa  fa-refresh bigger-230"></i>
		登录页面
		</a>
		
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "2">数据项套件</h3>
	<div class="row">
	
		<a href="${ctx}/suite/data/db/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-0"> 
		<i class="ace-icon fa fa-database bigger-230"></i>
		数据资源配置
		</a>
		
		<!--<a href="${ctx}/merit/toFwrzList" class="btn btn-app btn-yellow" funtype="funa" index = "2-2">
		<i class="ace-icon fa fa-building-o  bigger-230"></i>
		库表配置管理
		</a>
		-->
		
		<a href="${ctx}/suite/config/table/toTableList" class="btn btn-app btn-yellow" funtype="funa" index = "2-1">
		<i class="ace-icon fa fa-building-o  bigger-230"></i>
		库表配置管理
		</a>
		
		<a href="${ctx}/suite/data/unit/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-2">
		<i class="ace-icon fa fa-cubes bigger-230"></i>
		图表数据元配置
		</a>
		
		<a href="${ctx}/suite/data/term/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-3">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		图表数据项配置
		</a>
		
		<a href="${ctx}/suite/data/mult/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-4">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		报表数据项配置
		</a>
		
		<a href="${ctx}/suite/data/df/toDataIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-5">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		文件数据项配置
		</a>
		
		<a href="${ctx}/suite/data/df/toIndex" class="btn btn-app btn-yellow" funtype="funa" index = "2-6">
		<i class="ace-icon fa  fa-newspaper-o bigger-230"></i>
		文件缓存配置
		</a>
		
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "3">数据展现套件</h3>
	<div class="row">
		<a href="${ctx}/suite/config/query/toQueryList" class="btn btn-app btn-success" funtype="funa" index = "3-1">
		<i class="ace-icon fa fa-search-plus bigger-230"></i>
		数据查询配置
		</a>
		
		<a href="${ctx}/suite/config/form/toFormList" class="btn btn-app btn-success" funtype="funa" index = "3-2">
		<i class="ace-icon fa fa-file-text bigger-230"></i>
		数据表单配置
		</a>
		
		<a href="${ctx}/suite/chart/maintainList" class="btn btn-app btn-success" funtype="funa" index = "3-3">
		<i class="ace-icon fa fa-line-chart bigger-230"></i>
		数据图表配置
		</a>
		<a href="${ctx}/suite/csrq/report/toIndex" class="btn btn-app btn-success" funtype="funa" index = "3-4">
		<i class="ace-icon fa fa-table bigger-230"></i>
		数据报表配置
		</a>
		
	</div>
	
	<h3 class="page-header" funtype="funh3" index = "4">日志套件</h3>
	<div class="row">
		<a href="${ctx}/suite/log/viewCode" class="btn btn-app btn-pink" funtype="funa" index = "4-1">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		日志代码表
		</a>
		
		<a href="${ctx}/suite/log/configList" class="btn btn-app btn-pink" funtype="funa" index = "4-2">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		操作日志配置
		</a>
		
		<a href="${ctx}/tag/tagMaintain/11" class="btn btn-app btn-pink" funtype="funa" index = "4-3">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		字段修改日志
		</a>
		
		<a href="${ctx}/tag/tagMaintain/11" class="btn btn-app btn-pink" funtype="funa" index = "4-4">
		<i class="ace-icon fa fa-pencil-square-o bigger-230"></i>
		业务定做日志
		</a>
		
	</div>
	<h3 class="page-header" funtype="funh3" index = "5">服务套件</h3>
	<div class="row">
		<a href="${ctx}/fw/toPublish" class="btn btn-app btn-primary" funtype="funa" index = "5-1">
		<i class="ace-icon fa fa-volume-up bigger-230"></i>
		服务发布
		</a>
		<a href="${ctx}/fw/toFwfbList" class="btn btn-app btn-primary" funtype="funa" index = "5-2">
		<i class="ace-icon fa fa-volume-up bigger-230"></i>
		 服务管理
		</a>
		
	</div>
	<h3 class="page-header" funtype="funh3" index = "6">消息套件</h3>
	<div class="row">
		<a href="${ctx}/zy/toZyFbList" class="btn btn-app btn-primary" funtype="funa" index = "6-1">
		<i class="ace-icon fa fa-volume-up bigger-230"></i>
		通知公告
		</a>
		
		<a href="${ctx}/tag/tagMaintain/11" class="btn btn-app btn-primary" funtype="funa" index = "6-2">
		<i class="ace-icon fa fa-comment bigger-230"></i>
		短信
		</a>
		
		<a href="${ctx}/suite/mail/useMail" class="btn btn-app btn-primary" funtype="funa" index = "6-3">
		<i class="ace-icon fa fa-envelope bigger-230"></i>
		邮件
		</a>
		
	</div>
	
	

</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myModalLabel" align="center">
					功能设置
				</h3>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3 form-group">
						报表组件：<input inputtype="fun" index="0" type="checkbox" funa="2-0,2-1,2-4,3-4">
					</div>
					<div class="col-md-3 form-group">
						定时器组件：<input inputtype="fun" index="1" type="checkbox"  funa="0-2">
					</div>
					<div class="col-md-3 form-group">
						字典组件：<input inputtype="fun" index="2" type="checkbox"  funa="0-0">
					</div>
					<div class="col-md-3 form-group">
						取号组件：<input inputtype="fun" index="3" type="checkbox" funa="0-5,4-1,4-2" >
					</div>
					<div class="col-md-3 form-group">
						图表组件：<input inputtype="fun" index="4" type="checkbox"  funa="2-0,2-2,2-3">
					</div>
					<div class="col-md-3 form-group">
						公开服务：<input inputtype="fun" index="5" type="checkbox"  funa="5-1,5-2"> 
					</div>
					<div class="col-md-3 form-group">
						文件缓存：<input inputtype="fun" index="6" type="checkbox"  funa="2-0,2-5,2-6"> 
					</div>
					
				</div>				
			</div>
			<div class="modal-footer">
				<p align="center">
					<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;
		       		<button type="button" class="btn btn-warning" onclick="can()" style="width: 100px">取消</button>
				</p>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">
	$('li[litype="indexsetting"]').show();
	changestate();
	function indexsetting(){
		$('#myModal').modal({
			backdrop: 'static', keyboard: false
	    });
	}
	function changestate(){//根据cookie改变状态
		var funcookie = getCookie("indexfunction");
		if(funcookie == null || funcookie == undefined){
			funcookie = new Array();
			funcookie[0] = 1;
			addCookie("indexfunction",arraytostring(funcookie),365*24);
		}else{
			funcookie = funcookie.split('|'); 
		}
		$('input[inputtype="fun"]').each(function(){
			var index = parseInt($(this).attr('index'));
			if(index+1>funcookie.length){
				$(this).removeAttr("checked");
			}else{
				if(funcookie[index] == 1){
					$(this).attr("checked",'true');
				}else{
					$(this).removeAttr("checked");
				}
			}
		});
		$('h3[funtype="funh3"]').hide();
		$('a[funtype="funa"]').hide(); 
		//显示相应的组件
		for(var i=0;i<funcookie.length;i++){
			if(funcookie[i]==1){
				var funa = $('input[inputtype="fun"][index='+i+']').attr('funa').split(',');
				for(var j=0;j<funa.length;j++){
					$('a[index="'+funa[j]+'"]').show(); 
					$('h3[index="'+funa[j].split('-')[0]+'"]').show();
				}
			}
		}
	}
	function can(){
		changestate();
		$('#myModal').modal('hide');	
	}
	function save(){
		var fun = $('input[inputtype="fun"]');
		var funcookie = new Array(fun.size());
		for(var i = 0; i < fun.size() ; i++){
			if(fun[i].checked){ 
				funcookie[fun[i].getAttribute("index")]=1;
			}else{
				funcookie[fun[i].getAttribute("index")]=0;
			}
		}
		delCookie("indexfunction");
		addCookie("indexfunction",arraytostring(funcookie),365*24);
		changestate();		
		$('#myModal').modal('hide');	 
	}
	function arraytostring(args){
		var result = "";
		for(var i = 0 ; i < args.length ; i++){
			result = result + args[i];
			if(i < args.length-1){
				result = result + '|';
			}
		}
		return result;
	}
</script>
</body>
</html>
