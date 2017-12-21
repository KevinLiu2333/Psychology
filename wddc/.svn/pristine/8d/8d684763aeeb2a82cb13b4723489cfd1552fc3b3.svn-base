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
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">图表统计单元配置</h3>
<form id="form1" action="${ctx }/suite/data/unit/save" method="post">
	<input type="hidden" name="info.infoId" value="${obj.info.infoId }"/>
	<div class="row">
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 统计名称：</div>
		     	<input id="title" style="width: 250px;"  name="info.title" value="${obj.info.title }">
		</div>
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 数据源：</div>
		     	<select id="databaseid" name="info.databaseid" style="width:250px;height:30px"></select>
		</div>
	</div>
	<h4 id="disable-responsive2" class="page-header">SQL</h4>
	<div class="row">
		<div class="col-md-12 form-group">
		sql填写示例：select "男" key,count(1) value from t_people where xb=@P_xb，其中 key和value必须指明！</br>
		其中@P_bx可以使具体的值，也可为当前值，由调用者传入，且必须@P_开头
		</div>
		<div class="col-md-6 form-group">
			<table id="SqlTable" class="tables" width="200%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="80%">sql</th>
					<th class="label_row" align="center" width="20%"><input type="button" value="+" onclick="addsql()"/></th>
				</tr>
				<c:if test="${obj.info.sqllist==null||fn:length(obj.info.sqllist)==0 }">
					<tr id="sqlbranch">
						<td><input style="width: 800px;"  name="info.sqllist[0]" type="text" value="${obj.info.sqllist[0] }"></td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
				</c:if>
				<c:if test="${obj.info.sqllist!=null&&fn:length(obj.info.sqllist)>0 }">
					<c:forEach items="${obj.info.sqllist}" var="sql" varStatus="row">
						<c:if test="${row.index == 0}">
							<tr id="sqlbranch">
						</c:if>
						<c:if test="${row.index != 0}">
							<tr>
						</c:if>
							<td><input style="width: 800px;"  name="info.sqllist[${row.index}]" type="text" value="${obj.info.sqllist[row.index] }"></td>
							<td>
							<c:if test="${row.index == 0}">
								<a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a>
							</c:if>
							<c:if test="${row.index != 0}">
								<a href="#" onclick="deletetr(this);"  id="delBtn2">删除</a>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	
</form>
</div>
	<br>
	<p align="center">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
	</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
var sqlindex=0;
var sqllistName;
$(document).ready(function(){
	sqlindex = '${fn:length(obj.info.sqllist)}';
	if(sqlindex == 0) sqlindex=1;
	sqllistName = "info.sqllist[0]";
	$('#databaseid').dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"请选择",
		defaultvalue:"${obj.info.databaseid }"
	});
});
function addsql(){
	var sqlTable = $("#sqlTable");
	var cloneTr = $("#sqlbranch").clone();
	$(cloneTr).attr("id",$("#sqlbranch").attr("id")+"_"+sqlindex);
	$(cloneTr).find(":text").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(sqllistName) != -1){
				$(this).attr("name",thisName.replace(sqllistName,"info.sqllist["+ sqlindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+sqlindex));
		}
		$(this).val("");
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	sqlindex++;
	$(cloneTr).appendTo($("#SqlTable"));
}
function deletetr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
	}
}
function save(){
	if($('#title').val()==null||$('#title').val()==''){
		alert("请输入统计名称！");return;
	}
	if($('#databaseid').val()==null||$('#databaseid').val()==''){
		alert("请选择数据源！");
		return;
	}
	$('#form1').submit();
}
</script>
</html>