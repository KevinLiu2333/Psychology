<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
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
<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" />
<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css" />    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
   		
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	
    	<!--  提示框js -->
<style type="text/css">
 td,th{
    text-align:center;
 }	
</style>	
</head>
<body>
	<jsp:include page="/cj/header.jsp"/>
	<input type="hidden" id="js_ctx" value="${ctx }">
	<br/>
	<div class="container">
       	<div class="col-md-12">
       		<h2 class="page-header" style="margin-top: 5px">报表数据项配置
       		<span  style="float: right;">
       			<button type="button" class="btn btn-warning" onclick="add()" style="width: 100px">新增&nbsp;</button> &nbsp;
       			<!-- <a href="#" id="message_tip" class="btn btn-warning"  onclick="countAll()" style="width: 100px">全部更新</a> --> 
       		</span>
       		</h2>
       		<div class="content">
       			<div class="panel-body">
       				<div class="adv-table">
       					<table  class="display table table-bordered table-striped" id="dynamic-table">
       						<thead>
   								<tr>
       								<th>统计名称</th>
       								<th>数据更新时间</th>
       								<th>操作</th>
       							</tr>
       						</thead>
       						<tbody>
       							<c:forEach items="${obj.list }" var="info">
										<tr>
											<td>${info.name }</td>
											<td>
											<c:if test="${fn:contains(info.sql, '@')&&info.updatetime==null}">无</c:if>
											<c:if test="${info.updatetime==null && fn:contains(info.sql, '@')==false }">未统计</c:if>
											<fmt:formatDate value="${info.updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td> 
												<a href="#" onclick="edit('${info.id }')">编辑</a>
												<a href="#" onclick="del('${info.id }')">删除</a>
												<c:if test="${ fn:contains(info.sql, '@')==false }">
												<a href="#" onclick="view('${info.id }')">查看数据</a>
												</c:if>
												<c:if test="${fn:contains(info.sql, '@')}">
													<a href="#" onclick="testParam('${info.id}')">测试参数</a>
												</c:if>
												
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


<script>
$(function (){ 
	$("#message_tip").popover({placement:'bottom'});
});
</script>

<script type="text/javascript">
//新增
function add(){
	window.location.href="${ctx}/suite/data/mult/toEdit";
}
//编辑
function edit(id){
	window.location.href="${ctx}/suite/data/mult/toEdit?id="+id;
}
//查看
function view(id){
	window.open( "${ctx}/suite/data/mult/view?id="+id);
	location.reload();
}
//删除
function del(id){
	$.post("${ctx}/suite/data/mult/isUsing",{'id':id},function(data){
		if(data == '0'){
			if(window.confirm('你确定要删除该统计信息吗？')){
				$.post("${ctx}/suite/data/mult/delete?id="+id,{},function(){
					location.reload();
				});
			}
		}else{
			alert("该数据项正与报表关联，无法删除！");
		}
	});
}
//测试动态参数
function testParam(id){
	window.location.href="${ctx}/suite/data/mult/toTestParam?id="+id;
}
</script>
</html>