<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!--self-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/skins/css/wddc.css" />
<!--font-awesome-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
<!-- Loading jquery -->
<script type="text/javascript"
	src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css"
	rel="stylesheet" />
<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
</head>
<body>
	<input type="hidden" id="js_ctx" value="${ctx}">
	<jsp:include page="/cj/header.jsp" />
	<br>
	<div class="container">
		<div class="col-md-12">
			<h2 class="page-header" style="margin-top: 5px">
				接口实现类 <span style="float: right; padding-right: 10px">
					<button type="button" class="btn btn-warning" style="width: 100px"
						onclick="add()">新增&nbsp;</button> &nbsp;
				</span>
			</h2>
			<div  class="col-md-12 form-group ">
				<div class="alert alert-success col-md-12" style="width:100%">
			       <strong>
			       <h4>提示：</h4>
			       	<ul>
			       	<li>后台接口测试调用地址：http://[ip]:[端口]/wddc/ic/services?classId=编号</li>
			       	</ul>
			       </strong>
			    </div>
			</div>
			<div class="content">
				<div class="panel-body">
					<div class="adv-table">
						<table class="display table table-bordered table-striped"
							id="dynamic-table">
							<thead>
								<tr>
									<th>编号</th>
									<th>接口id</th>
									<th>实现类路径</th>
									<th>修改时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${obj.info}" var="bo" varStatus="bos">
									<tr>
										<td>${bo.id}</td>
										<td>${bo.jkid}</td>
										<td>${bo.classPath}</td>
										<td><fmt:formatDate value="${bo.updateTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><a href="#" onclick="edit('${bo.id}')">编辑&nbsp&nbsp&nbsp</a>
											<a href="#" onclick="del('${bo.id}')">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/cj/foot.jsp" />
	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
	<script src="${ctx}/wddc/tiles/js/dic.js"></script>
	<script type="text/javascript">
		function add() {
			window.location.href = "${ctx}/sjic/jkrw/edit";
		}

		function del(id) {
			if (window.confirm("确定删除?")) {
				$.post("${ctx}/sjic/jkrw/del", {
					id : id
				}, function(data) {
					alert(data);
					window.location.reload();
				});
			}
		}
		function edit(id) {
			window.location.href = "${ctx}/sjic/jkrw/edit?id=" + id;
		}
		function save(id) {
			var classPath = $("#" + id).val();
			$.post("${ctx}/sjic/jkgl/save", {
				id : id,
				classPath : classPath
			}, function(data) {
				alert(data);
			});
		}
	</script>
</body>
</html>