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
	<h2 class="page-header" style="margin-top: 5px">开关配置
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
							<th>开关状态</th>
							<th>开关名称</th>
							<th>开关代码</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${obj.list }" var="info">
							<tr>
								<td align="center">
									<input id="${info.id }" type="checkbox"<c:if test="${info.value=='1' }"> value="1" checked="checked"</c:if>/>
								</td>
								<td align="center">${info.name }</td>
								<td align="center">${info.code }</td>
								<td align="center">
									<a href="#" onclick="edit('${info.id}',${info.value})">编辑</a>
									<a href="#" onclick="del('${info.id}','${info.value}')">删除</a>
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
		onText:"开启",  
		offText:"关闭", 
		onColor:"success",
		offColor:"info",
		size:"small"
	});
	$('[type="checkbox"]').on('switchChange.bootstrapSwitch', function (e, state) {
		var switchid=$(this).attr('id');
		if(state){
			$(this).val("1");
			$.post("${ctx}/kernel/switch/switchTask",{id:switchid,state:'1'},function(data){
				if(data=='1'){
					alert('开关已开启');
				}else{
					$('#'+switchid).bootstrapSwitch('setState', false);
				}
			});
		}else{
			$(this).val("0");
			$.post("${ctx}/kernel/switch/switchTask",{id:switchid,state:'0'},function(data){
				if(data=='1'){
					alert('开关已关闭');
				}else{
					$('#'+switchid).bootstrapSwitch('setState', true);
				}
			});
		}
	});
});
function add(){
	window.location.href = "${ctx}/kernel/switch/addOrEdit";
}
function edit(id,state){
	if(state=='1'){
		alert("开关在开启状态，请先关闭!");
	}else{
		window.location.href = "${ctx}/kernel/switch/addOrEdit?id="+id;
	}
}
function del(id,state){
	if(state=='1'){
		alert("开关在开启状态，请先关闭!");
	}else{
		if(window.confirm('你确定要删除开关吗？')){
			$.post("${ctx}/kernel/switch/delSwitch",{id:id},function(){
				location.reload();
			});
		}
	}
}
</script>
</html>