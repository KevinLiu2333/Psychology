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
				<h2 class="page-header" style="margin-top: 5px">文件缓存配置
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
       									<th width="30%">名称</th> 
       									<th width="40%">缓存路径</th>
       									<th width="20%">操作</th>
       								</tr>
       							</thead>
       							<tbody>
       							<c:forEach items="${obj.list}" var="bo">
       								<tr>
       									<td>${bo.id }</td>
       									<td>${bo.name }</td>
       									<td>${bo.filepath }</td>
       									<td>
       										<a href="#" onclick="view('${bo.id }')">预览</a>
       										<a href="#" onclick="exportdata('${bo.id }')">缓存</a>
											<a href="#" onclick="edit('${bo.id }')">编辑</a>
											<a href="#" onclick="deleteinfo('${bo.id }')">删除</a>
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
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">
							数据更新中。。。
						</h4>
					</div>
					<div class="modal-body">
						<div class="progress progress-striped active">
							<div class="progress-bar progress-bar-success" role="progressbar"
								 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
								 style="width: 80%;">
							</div>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
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
		function add(){
			window.location.href = "${ctx}/suite/data/df/toInfoEdit";
		}
		
		function edit(id){
			window.location.href = "${ctx}/suite/data/df/toInfoEdit?id="+id;
		}
		
		function deleteinfo(id){
			if (confirm("确定删除吗？")) {  
		        $.post("${ctx}/suite/data/df/deleteInfo",{id:id},function(data){
		        	location.reload();
		        });  
		    } 
		}
		
		function view(id){
			window.open( "${ctx}/suite/data/df/viewInfo?id="+id);
			location.reload();
		}
		
		function exportdata(id){
			$('#myModal').modal({
				backdrop: 'static', keyboard: false
		    });
			$.post("${ctx}/suite/data/df/cacheData",{id:id},function(data){
	        	if(data){
	        		alert("缓存成功！");
	        		window.location.reload();
	        	}else{
	        		alert("缓存失败！");
	        		window.location.reload();
	        	}
	        }); 
		}
	</script>
</html>