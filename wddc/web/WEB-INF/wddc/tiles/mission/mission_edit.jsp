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
	
	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head> 
<script type="text/javascript">

//信息校验
function submitTask(){
	if($('#taskName').val()==''){
		alert("请输入任务名称！");
		return; 
	}
	if($('#taskFreqDay').val()==''){
		alert("请输入任务频率！");
		return;
	}
	if($('#taskFreqTime').val()==''){
		alert("请输入任务频率！");
		return;
	}
	if(confirm("确定要保存吗？")){
		$("#mainForm").submit();
	}
}
</script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;新增任务</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/kernel/mission/saveConfig" method="post" >
   		 <input type="hidden" name="TaskConfig.taskId" value="${obj.TaskConfig.taskId }">
   		 
    <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>任务信息</b></h4>
    <div class="row">
   		<div class="col-md-6 form-group">
	    	任务名称：<input type="text" id="taskName" style="width: 38%" name="TaskConfig.taskName" value="${obj.TaskConfig.taskName}"/>
	    </div>
	    <div class="col-md-6 form-group">
	  		 任务完成频率：每<input type="text" id="taskFreqDay" style="width: 10%" name="TaskConfig.taskFreqDay" value="${obj.TaskConfig.taskFreqDay}"/>天
	  		 <input type="text" id="taskFreqTime" style="width: 10%" name="TaskConfig.taskFreqTime" value="${obj.TaskConfig.taskFreqTime}"/>次
	    </div>
	    <div class="col-md-6 form-group">
	    	日志类型：<input type="text" id="logType" style="width: 38%" name="TaskConfig.logType" value="${obj.TaskConfig.logType}"/>
	    </div>
	    <div class="col-md-6 form-group">
	    	是否可以忽略预警：<input type="text" id="isIgnoreWarning" style="width: 38%" name="TaskConfig.isIgnoreWarning" value="${obj.TaskConfig.isIgnoreWarning}"/>
	    </div>
	    <div class="col-md-12 form-group">
	        	 任务简介：<textarea type="text" id="taskDesc" style="width: 67%" name="TaskConfig.taskDesc">${obj.TaskConfig.taskDesc}</textarea>
	    </div>
	</div>
</form>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="保   存" onclick="submitTask();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/kernel/mission/toIndex'" />
</div>
</div>

<script>
</script>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>