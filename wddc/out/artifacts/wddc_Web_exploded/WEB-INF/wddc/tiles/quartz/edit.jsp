<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
    	<script src="${ctx }/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
    	
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">定时器配置</h3>
<form id="form1" action="${ctx }/kernel/quartz/saveTask" method="post">
	<input type="hidden" name="task.projectname" value="${obj.task.projectname }">
	<input type="hidden" name="task.id" value="${obj.task.id }"/>
	<input type="hidden" name="task.currentstate" value="${obj.task.currentstate }"/>
	<div class="row">
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 定时器名称：</div>
		     	<input id="name" style="width: 250px;"  name="task.name" value="${obj.task.name }">
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 定时器任务类：</div>
			<select id="cls" class="selectpicker" data-live-search="true" title="请选择" name="task.jobclass" style="width: 250px;">
				<c:forEach items="${obj.dic }" var="cls">
					<c:if test="${cls==obj.task.jobclass }">
						<option value="${cls }" selected="selected">${cls }</option>
					</c:if>
					<c:if test="${cls!=obj.task.jobclass }">
						<option value="${cls }" >${cls }</option>
					</c:if>
				</c:forEach>
			</select>
			<%-- <wd:jsonselect id="cls" name="task.jobclass" jsonstr="${obj.dic }" style="width: 250px;"
				initValue="请选择" defaultValue="${obj.task.jobclass }"/> --%>
		</div>
		<div class="col-md-12 form-group">
			<div  style="float: left;width: 150px;"> 定时器执行时间：</div>
			<input id="excutetime" style="width: 250px;"  name="task.excutetime" value="${obj.task.excutetime }">
		</div>
	</div>
	<h4 id="disable-responsive2" class="page-header">定时器执行时间说明：</h4>
	<div class="row">
		<div class="col-md-12 form-group">
			格式: [秒] [分] [小时] [日] [月] [周] [年] (其中除年外参数全为必填项，允许填写准确值以及通配符) 
		</div>
		<div class="col-md-12 form-group">
			通配符说明:1、*表示所有值；2、? ：表示不指定值；3、 - ：表示区间；4、, ：表示指定多个值；5、/ ：用于递增触发。6、L ：表示最后的意思；
		</div>
		<div class="col-md-12 form-group">
			&nbsp;7、 W ：表示离指定日期的最近那个工作日(周一至周五)；8、# ：序号(表示每月的第几周星期几)；
		</div>
		<div class="col-md-12 form-group">
			示例： 1、每日18:00:00启动 : 0 0 18 * * ?  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、每天下午的 2点到2点05分每分触发：0 0-5 14 * * ?
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、每月的第三周的星期五开始触发：0 15 10 ? * 6#3
		</div>
	</div>
</form>
</div>
	<br>
	<p align="center">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
	</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
function save(){
	if($('#name').val()==''){
		alert("请输入定时器名称");
		return;
	}
	if($('#excutetime').val()==''){
		alert("请选择定时器任务类");
		return;
	}
	if($('#cls').val()==''){
		alert("请输入定时器执行时间");
		return;
	}
	$('#form1').submit();
}
</script>
</html>