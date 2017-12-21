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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
    <!-- datetimepicker js -->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/datetimepicker/css/datetimepicker-custom.css" />

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx}" />
<div class="container">
<c:set scope="request"  var="pageForm" value="queryForm" />
<form id="queryForm" name="queryForm" action="toViewComp" method="post">
		<p style="padding-top: 15px">
		   任务名称： <input id="taskName" name="filter_str_taskName_like" style="width:200px;height: 30px"></input>
		  记录时间：<input type="text" id="form_date" class="form_datetime input-show-control" name="filter_dt_taskDate_start" style="width:100px" value="${param.filter_dt_taskDate_start}" />-
		   <input type="text" id="form_date" class="form_datetime input-show-control" name="filter_dt_taskDate_end" style="width:100px" value="${param.filter_dt_taskDate_end}"/>
		   <button type="button" class="btn btn-warning" onclick="query()">查询</button>
		</p>
		<table width="96%" class="table table-bordered" align="center">
			<tr>
				<th>序号</th>
				<th>任务名称</th>
				<th>任务时间</th>
				<th>应完成频率</th>
				<th>实际完成频率</th>
				<th>是否预警</th>
			</tr>
			<c:forEach items="${obj.rows}" var="task" varStatus="row" >
				<tr>
					<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>
	        		<td align="center">${task.taskName }</td>
	        		<td align="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${task.taskDate }"/></td>
	        		<td align="center">${task.taskSetDay }天${task.taskSetTime }次</td>
	        		<td align="center">${task.taskSetDay }天${task.taskActualTime }次</td>
	        		<td align="center">
	        			<c:if test="${task.isWarning == '1' }">是</c:if>
	        			<c:if test="${task.isWarning == '0' }">否</c:if>
	        		</td>
	        	</tr>
			</c:forEach>
			
		</table>
		<table width="96%" class="tables">
			<tr>
				<td>
					<!-- （3）分页 -->
					<jsp:include page="/cj/pager.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</form>
	
	

</div>


<jsp:include page="/cj/foot.jsp"/>

<script type="text/javascript" src="${ctx}/wddc/tiles/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

function query() {
	$("#${pageForm}").submit();
}
$(document).ready(function() {
	$(".form_datetime").each(function(){
		$(this).datetimepicker({
		    format: "yyyy-mm-dd ",
		    autoclose: true,
		    todayBtn: true,
		    //todayHighlight: true,
		    language: 'cn',
		    pickerPosition: "bottom-left",
		    startView: 2,
		    minView: "month",
		    endDate : new Date()
		}).on('changeDate',function(e){ 
		});  
	  });
	//日期选择器
	
});  
</script>
</body>
</html>
