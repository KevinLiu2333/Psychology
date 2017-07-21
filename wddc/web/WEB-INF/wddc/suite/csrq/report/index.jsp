<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${ctx}/wddc/tiles/easydialog/easydialog.css"
	rel="stylesheet">
<!--self-->
<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css" />
<!--font-awesome-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
<!-- Loading jquery -->
<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css"
	rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css" />
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }">
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
<div class="col-md-12">
	<h2 class="page-header" style="margin-top: 5px">数据报表配置
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
							<th  width="10%">编号</th>
							<th  width="20%">名称</th>
							<th  width="25%">更新时间</th>
							<th  width="45%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${obj.list }" var="info"  varStatus="row">
							<tr>
								<td align="center">${info.id }</td>
								<td align="center">
									<c:if test="${info.state=='1'}">
										<a href="#" onclick="view('${info.id }','${info.updatetime}')">${info.name }</a>
									</c:if>
									<c:if test="${info.state!='1'}">
										<a href="#" onclick="toDynamicView('${info.id }','${info.state}');">${info.name }</a>
									</c:if>
								</td>
								<td align="center">
									<c:if test="${info.updatetime==null }">
										无
									</c:if>
									<c:if test="${info.updatetime!=null }">
										<fmt:formatDate value="${info.updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</c:if>
								</td>
								<td align="center">
									<a href="#" onclick="edit('${info.id }')">编辑</a>&nbsp;
									<a href="#" onclick="config('${info.id }')">高级配置</a>&nbsp;
									<a href="#" onclick="designhtml('${info.id }')">页面设计</a>&nbsp;
									<a href="#" onclick="del('${info.id }')">删除</a>&nbsp;
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
<!-- <div  class=" hide_box" id="testBox" > 
	<h4 style="color:green">提示 :&nbsp;&nbsp;<span style="color:green;">正在下载最新数据，请稍后...</span> </h4> 
	
	<br>
	<div class="progress progress-striped active"  align="center">
	    <div class="progress-bar progress-bar-success" role="progressbar"
	         aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
	         style="width: 98%;">
	        <span class="sr-only">98% 完成（成功）</span>
	    </div>
	</div>
	<br>
</div> 
	 -->
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/easydialog/easydialog.js"></script>
<script type="text/javascript">
$(function(){
	$("#testBox").hide();
});
function add(){
	window.location.href = "${ctx}/suite/csrq/report/toEdit";
}
function edit(id){
	window.location.href = "${ctx}/suite/csrq/report/toEdit?id="+id;
}
function config(id){
	window.location.href = "${ctx}/suite/csrq/report/toConfig?id="+id;
}
function designhtml(id){
	window.location.href = "${ctx}/suite/csrq/report/EditHtml?id="+id;
}

//function update(id){
//	$.post("${ctx}/suite/csrq/report/update",{id:id},function(){
//		alert('更新完成!');
//		location.reload();
//	});
//}
function refreshdata(id){
	//$("#testBox").show();
	easyDialog.open({
		container : 'testBox',
		isOverlay : false
	});
	$.post("${ctx}/suite/csrq/report/refreshdata",{id:id},function(){
		easyDialog.close();
		location.reload();
		window.open( "${ctx}/suite/csrq/report/viewResult?id="+id);
	});
}
function view(id,date){
	window.open( "${ctx}/suite/csrq/report/viewResult?id="+id);
	//if(date == null||date ==""){
		//$("#testBox").show();
		//easyDialog.open({
			//container : 'testBox',
			//isOverlay : false
		//});
		//refreshdata(id);
		
	//}else{
		
	//}
	
	
	//window.location.href= "${ctx}/suite/csrq/report/viewResult?id="+id;
	//window.showModalDialog( "${ctx}/suite/csrq/report/viewResult?id="+id,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	//window.showModalDialog( "${ctx}/suite/csrq/report/viewResult?id="+id,'window',"dialogWidth=1000px;center=yes");
}
function del(id){
	if(window.confirm('你确定要删除此报表吗？')){
		$.post("${ctx}/suite/csrq/report/delete",{id:id},function(){
			location.reload();
		});
	}
}

function dicTest(){
	alert(1);
	window.open( "${ctx}/suite/csrq/report/dicTest?id=R00004");
}

function paramTest(){
	window.open( "${ctx}/suite/csrq/report/paramTest?id=R00005");
}
function toDynamicView(id,state){
	window.location.href="${ctx}/suite/csrq/report/toDynamicView?id="+id+"&state="+state;
}
</script>

