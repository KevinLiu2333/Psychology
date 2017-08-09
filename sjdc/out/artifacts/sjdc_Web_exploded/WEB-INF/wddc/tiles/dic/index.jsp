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
<div class="container">
<div class="col-md-12">
	<h2 class="page-header" style="margin-top: 5px">字典管理
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
							<th align="center">序号</th>
							<th align="center">字典名称</th>
							<th align="center">字典属性</th>
							<th align="center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${obj.list}" var="dic">
							<tr>
								<td align="center">${dic.dicId } </td>
								<td align="center">${dic.dicName }</td>
								<td align="center">
									<c:if test="${dic.keyDataType =='1'}">业务字典</c:if>
									<c:if test="${dic.keyDataType =='2'}">系统字典</c:if>
								</td>
								<td align="center"><a href="#" onclick="edit('${dic.dicId }')">修改 </a> <a href="#" onclick="del('${dic.dicId }')">删除</a></td>
							</tr>						
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
<input type="hidden" id="js_ctx" value="${ctx }">
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
<script type="text/javascript">
	function add(){
		window.location.href="${ctx}/kernel/dic/edit";
	}
	function edit(id){
		window.location.href="${ctx}/kernel/dic/edit?dicid="+id;
	}

	function del(id){
		if(window.confirm('你确定要删除该字典吗？')){
			$.post("${ctx}/kernel/dic/delele",{id:id},function(){
				location.reload();
			});
		}
	}
</script>
</html>