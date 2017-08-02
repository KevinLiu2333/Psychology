<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp"%>
		<!-- Loading Bootstrap -->
		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<!--self-->
		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css" />
		<!--font-awesome-->
		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
		<!-- Loading jquery -->
		<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
		<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/swich/css/bootstrap3/bootstrap-switch.min.css" />
		<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/cj/header.jsp"/>
	<br>
	<br>
<form id="form" action="${ctx }/suite/csrq/report/fetDynamicView" method="post">
	<div class="container">
		<h3 class="page-header" style="margin-top: 5px">测试参数</h3>
		<div class="col-md-12">
			<br>
				<input type="hidden" name="id" value="${obj.id }"/>
				<input type="hidden" name="state" value="${obj.state }"/>
				<c:if test="${obj.state=='2'||obj.state=='4'}">
				<div class="col-md-12 form-group">
		     		<div  style="float: left;width: 100px;">动态字典：</div>
		     		<div  style="float: left;width: 650px;">
		     		<input id="dic" class="input-show-control" style="width:650px;" name="dic" value="${obj.dic }">
		     		</div>
		     		<div style="float: left;width: 300px;padding-left: 30px;">示例：{XBDM:{'1':'男性','2':'女性'}}</div>
				</div>
				</c:if>
				
				<c:if test="${obj.state=='3'||obj.state=='4'}">
				<div class="col-md-12 form-group">
		     		<div  style="float: left;width: 100px;">动态参数：</div>
		     		<div  style="float: left;width: 650px;">
		     		<input id="param" class="input-show-control" style="width:650px;" name="param" value="${obj.param }">
		     		</div>
		     		<div style="float: left;width: 300px;padding-left: 30px;">示例：{S_sex:'1'}</div>
				</div>
				</c:if>
		</div>
		<div class="col-md-12 form-group">报表名称：&nbsp;${obj.info.name }
		</div>
		<div class="col-md-12">
			${obj.result.result }
		</div>
		<br>
		
</div>
</form>
<div align="center" style="margin-top:50px;">
		<button type="button" class="btn btn-warning" onclick="test()" style="width: 100px">测试</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1);" style="width: 100px">返回</button>
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
<script type="text/javascript">
function test(){
	$('#myModal').modal({
		backdrop: 'static', keyboard: false
    });
	$('#form').submit();
}
</script>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
</html>