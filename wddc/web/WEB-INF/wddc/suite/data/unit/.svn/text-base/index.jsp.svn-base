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
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<input type="hidden" id="js_ctx" value="${ctx }">
		<jsp:include page="/cj/header.jsp"/>
		<br/>
		<div class="container">
       		<div class="col-md-12">
       			<form action="">
       			<h2 class="page-header" style="margin-top: 5px">图表统计单元配置
       			<span  style="float: right">
       				<button type="button" class="btn btn-warning" onclick="add()" style="width: 100px">新增&nbsp;</button> &nbsp;
       				<button type="button" class="btn btn-warning" onclick="countAll()" style="width: 100px">一键统计</button> 
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
											<td>${info.title }</td>
											<td>
											<c:if test="${info.needsave=='0'}">含有参数，无法更新</c:if>
											<c:if test="${info.needsave=='1'}">
												<c:if test="${info.updatetime==null }">未统计</c:if>
												<fmt:formatDate value="${info.updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											</c:if>
											<td>
												<a href="#" onclick="edit('${info.infoId }')">编辑</a>
												<a href="#" onclick="del('${info.infoId }')">删除</a>
												<c:if test="${info.needsave=='1'}">
												<a href="#" onclick="count('${info.infoId }')">更新数据</a>
												<a href="#" onclick="view('${info.infoId }')">查看数据</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>       							
       							</tbody>
       						</table>
       					</div>
       				</div>
       			</div>
       			</form>
       		</div>
		</div>
		<jsp:include page="/cj/foot.jsp"/>
	</body>
	<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
	<script type="text/javascript">
		function add(){
			window.location.href = "${ctx}/suite/data/unit/toEdit";
		}
		function edit(id){
			window.location.href = "${ctx}/suite/data/unit/toEdit?id="+id;
		}
		function del(id){
			$.post("${ctx}/suite/data/unit/isUsing",{"id":id},function(data){
				if(data=='0'){
					if(window.confirm('你确定要删除该数据库信息吗？')){
						$.post("${ctx}/suite/data/unit/delete?id="+id,{},function(){
							location.reload();
						});
					}
				}else{
					alert("该统计元正被使用，无法删除！");
				}
			});
		}
		function count(id){
			$.post("${ctx}/suite/data/unit/countById",{
				"id":id
			},function(){
				alert('统计完成');
				location.reload();
			});
		}
		function countAll(){
			$.post("${ctx}/suite/data/unit/countAll",{
			},function(){
				alert('统计完成');
				location.reload();
			});
		}
		function view(id){
			window.showModalDialog( "${ctx}/suite/data/unit/view?id="+id,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
		}
	</script>
</html>