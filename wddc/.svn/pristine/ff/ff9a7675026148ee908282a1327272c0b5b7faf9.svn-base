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
		<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
		<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
</head>
<body>
	<jsp:include page="/cj/header.jsp"/>
	<br>
	<br>
<form id="form" action="${ctx }/suite/csrq/report/saveConfig" method="post">
	<div class="container">
		<h3 class="page-header" style="margin-top: 5px">高级配置</h3>
		<h4 id="disable-responsive2" class="page-header" style="margin-top: 5px">缓存设置</h4>
		<div class="col-md-12">
			<br>
				<input type="hidden" name="id" value="${obj.bo.id }"/>
				<div class="col-md-6 form-group">
		     		<div  style="float: left;width: 100px;">是否缓存：</div>
		     		<select id="iscache" name="iscache" class="input-show-control" style="width: 100px">
					</select>
				</div>
				<div class="col-md-6 form-group">
		     		<div  style="float: left;width: 100px;">执行时间：</div>
		     		<input id="time" class="input-show-control" style="width:400px;" name="fixedTime" value="${obj.bo.fixedTime }" >
				</div>
			</div>
			<h4 id="disable-responsive2" class="page-header" style="margin-top: 5px">样式设置</h4>
			<div class="col-md-12">
				<div class="col-md-6 form-group">
		     		<div  style="float: left;width: 100px;">删除横表头：</div>
		     		<select id="row" name="hashead" class="input-show-control" style="width: 100px">
					</select>
				</div>
				<div class="col-md-6 form-group">
		     		<div  style="float: left;width: 100px;">删除竖表头：</div>
		     		<select id="header" name="hasrow" class="input-show-control" style="width: 100px">
					</select>
				</div>
				<div class="col-md-6 form-group">
		     		<div  style="float: left;width: 100px;">模板文件：</div>
		     		<select id="templet" class="selectpicker" data-live-search="true" title="请选择" name="templetfile" style="width: 250px;">
						<c:forEach items="${obj.filelist }" var="file">
							<c:if test="${file==obj.bo.templetfile }">
								<option value="${file }" selected="selected">${file }</option>
							</c:if>
							<c:if test="${file!=obj.bo.templetfile }">
								<option value="${file }" >${file }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
		</div>
		<br>
		<h4 id="disable-responsive2" class="page-header" style="margin-top: 5px">超链接设置</h4>
		<c:forEach items="${obj.headers}" var="head"  varStatus="row">
			<div class="col-md-12 form-group" >
			<div  style="float: left;width: 150px;"> ${ head.dictitle} ：</div>
				<input type="hidden" name="headers[${row.index}].id" value="${head.id }">
				<input type="hidden" name="headers[${row.index}].reportinfoid" value="${head.reportinfoid }">
				<input type="hidden" name="headers[${row.index}].dicid" value="${head.dicid }">
				<input type="hidden" name="headers[${row.index}].columnname" value="${head.columnname }">
				<input type="hidden" name="headers[${row.index}].issum" value="${head.issum }">
				<input type="hidden" name="headers[${row.index}].diczhcn" value="${head.diczhcn }">
				<input id="url" class="input-show-control" style="width:800px;" name="headers[${row.index}].url" value="${head.url }">
			</div>
		</c:forEach>
</div>
</form>
<div align="center" style="margin-top:50px;">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
function save(){
	//验证corn表达式
	if($('#iscache').val()=='1'){
		var time =$("#time").val();
		if(time==''){
			alert("表达式有误，请重新填写！");
			return ;
		}else{
			$.post("${ctx}/suite/csrq/report/checkTime",{time:time},function(data){
				if(data == '0'){
					alert("表达式有误，请重新填写！");
					return;
				}else{
					$('#form').submit();
				}
		    });
		}
	}else{
		$('#form').submit();
	}
}
$(document).ready(function(){
	var isnotdic="{'0':'否','1':'是'}";
	$('#iscache').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.bo.iscache }'
	});
	$('#row').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.bo.hasrow }'
	});
	$('#header').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.bo.hashead }'
	});
});

</script>
<script type="text/javascript" language="javascript" src="${ctx}/wddc/tiles/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/data-tables/DT_bootstrap.js"></script>
<!--dynamic table initialization -->
<script src="${ctx}/wddc/tiles/data-tables/dynamic_table_init.js"></script>
</html>