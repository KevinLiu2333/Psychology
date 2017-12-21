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
				<h2 class="page-header" style="margin-top: 5px">对外公开服务
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
       									<th width="20%">编号</th>
       									<th width="20%">名称</th>
       									<th width="30%">服务地址</th>
       									<th width="20%">操作</th>
       								</tr>
       							</thead>
       							<tbody>
									<c:forEach items="${obj.list}" var="bo">
										<tr>
											<td>${bo.id }</td>
											<td><a href="${ctx}/suite/service/foreign/service?id=${bo.id}">${bo.name }</a></td>
											<td>${bo.url }</td>
											<td>
												<a href="#" onclick="edit('${bo.id  }')">编辑</a>
												<a href="#" onclick="del('${bo.id  }')">删除</a>
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
	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
	<script type="text/javascript">
		function add(){//新增
			window.location.href="${ctx}/suite/service/foreign/edit";
		}
		function edit(id){//编辑
			window.location.href="${ctx}/suite/service/foreign/edit?id="+id;
		}
		function del(id){//删除
			if(window.confirm('你确定要删除吗？')){
				$.post("${ctx}/suite/service/foreign/delete?id="+id,{},function(){
					location.reload();
				});
			}
		}
	</script>
</html>