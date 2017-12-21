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
	</head>
	<body>
	<input type="hidden" id="js_ctx" value="${ctx }">
		<jsp:include page="/cj/header.jsp"/>
		<br/>
		<div class="container">
       		<div class="col-md-12">
       			<form action="">
       			<h2 class="page-header" style="margin-top: 5px">数据资源配置
       				<span style="float: right;padding-right: 10px">
    	   				<button type="button" class="btn btn-warning" style="width: 100px" onclick="add()">新增&nbsp;</button> &nbsp;
	       			</span>
       			</h2>
       			
       			<div class="content">
       				<div class="panel-body">
       					<div class="adv-table">
       						<table  class="display table table-bordered table-striped" id="dynamic-table">
       							<thead>
       								<tr>
       									<th width="10%">编号</th>
       									<th width="30%">数据库名称</th>
       									<th width="20%">数据库类型</th>
       									<th width="20%">数据库地址</th>
       									<th width="20%">操作</th>
       								</tr>
       							</thead>
       							<tbody>
									<c:forEach items="${obj.dbinfos }" var="dbinfo">
										<tr>
											<td>${dbinfo.id }</td>
											<td>${dbinfo.dbname }</td>
											<td type="dbType" val="${dbinfo.type}"></td>
											<td>${dbinfo.address }</td>
											<td>
												<a href="#" onclick="test('${dbinfo.id }')">测试连接</a>
												<a href="#" onclick="edit('${dbinfo.id }')">编辑</a>
												<a href="#" onclick="del('${dbinfo.id }')">删除</a>
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
	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
	<script src="${ctx}/wddc/tiles/js/dic.js"></script>
	<script type="text/javascript">
		//新增
		function add(){
			window.location.href="${ctx}/suite/data/db/toEdit";
		}
		//修改	
		function edit(id){
			window.location.href="${ctx}/suite/data/db/toEdit?id="+id;
		}
		//删除
		function del(id){
			$.post("${ctx}/suite/data/db/isUsing",{'id':id},function(data){
				if(data=='0'){
					if(window.confirm('你确定要删除该数据库信息吗？')){
						$.post("${ctx}/suite/data/db/delete?id="+id,{},function(){
							location.reload();
						});
					}
				}else{
					alert('在数据项配置中已被使用，不能被删除！');
				}
			});
			
		}
		//测试数据库连接
		function test(id){
			$.post("${ctx}/suite/data/db/DBTestById",{
				id:id
			},function(data){
				alert(data);
			});
		}
			
	$("td[type='dbType']").each(function(){
		$(this).dicvalue({
			dic:"{'1':'MySQL','2':'Oracle','3':'Sql Server','4':'postgresql'}",
			value:$(this).attr('val')
			});
	});
	</script>
</html>