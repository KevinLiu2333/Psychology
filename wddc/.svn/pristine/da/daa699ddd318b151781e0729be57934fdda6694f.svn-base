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
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css"/>
		<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<input type="hidden" id="js_ctx" value="${ctx }">
<div class="container">
<div class="col-md-12">
	<h2 class="page-header" style="margin-top: 5px">定时器配置
		<span style="float: right;padding-right: 10px">
		<button type="button" class="btn btn-warning" onclick="add()" style="width: 100px">新增&nbsp;</button>
		</span>
	</h2>
	<div class="content">
		<div class="panel-body">
			<div class="adv-table">
				<table  class="display table table-bordered table-striped" id="dynamic-table">
					<thead>
						<tr>
							<th>状态</th>
							<th>定时器名称</th>
							<th>定时时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${obj.list }" var="task">
							<tr>
								<td align="center">
									<input id="${task.id }" type="checkbox"<c:if test="${task.currentstate=='1' }"> value="1" checked="checked"</c:if>/>
								</td>
								<td align="center">${task.name }</td>
								<td align="center">${task.excutetime }</td>
								<td align="center">
									<a href="#" onclick="edit('${task.id}')">编辑</a>
									<a href="#" onclick="del('${task.id}')">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/swich/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('[type="checkbox"]').bootstrapSwitch({
		onText:"启动",  
		offText:"停止", 
		onColor:"success",
		offColor:"info",
		size:"small"
	});
	$('[type="checkbox"]').on('switchChange.bootstrapSwitch', function (e, state) {
		var switchid=$(this).attr('id');
		if(state){
			$(this).val("1");
			$.post("${ctx}/kernel/quartz/switchTask",{id:switchid,state:'1'},function(data){
				if(data=='1'){
					alert('定时器已启动');
				}else{
					alert(data);
					$('#'+switchid).bootstrapSwitch('setState', false);
				}
			});
		}else{
			$(this).val("0");
			$.post("${ctx}/kernel/quartz/switchTask",{id:switchid,state:'0'},function(data){
				if(data=='1'){
					alert('定时器已停止');
				}else{
					alert(data);
					$('#'+switchid).bootstrapSwitch('setState', true);
				}
			});
		}
	});
});
function add(){
	window.location.href = "${ctx}/kernel/quartz/toEdit";
}
function edit(id){
	var state=$('#'+id).val();
	if(state=='1'){
		alert("定时器正在运行中，请先停止定时器!");
	}else{
		window.location.href = "${ctx}/kernel/quartz/toEdit?id="+id;
	}
}
function del(id){
	var state=$('#'+id).val();
	if(state=='1'){
		alert("定时器正在运行中，请先停止定时器!");
	}else{
		if(window.confirm('你确定要删除定时器吗？')){
			$.post("${ctx}/kernel/quartz/deleteTask",{id:id},function(){
				location.reload();
			});
		}
	}
}
</script>
</html>