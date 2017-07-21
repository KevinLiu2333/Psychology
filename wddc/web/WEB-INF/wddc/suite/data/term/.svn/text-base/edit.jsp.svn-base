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
<h3 id="disable-responsive2" class="page-header">图表统计项配置</h3>
<form id="form1" action="${ctx }/suite/data/term/save" method="post">
	<input type="hidden" name="term.id" value="${obj.term.id }"/>
	<div class="row">
		<div class="col-md-6 form-group">
		     <div  style="float: left;width: 150px;"> 统计名称：</div>
		     <input id="name" style="width: 250px;"  name="term.name" value="${obj.term.name }">
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 坐标轴字典：</div>
			<select id="dic" name="term.axisdic" style="width:250px;height:30px"></select>
		</div>
	</div>
	<h4 id="disable-responsive2" class="page-header">统计元选择</h4>
	<div class="row">
		<div class="col-md-6 form-group">
			<table id="statTable" class="tables" width="200%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="30%">标题</th>
					<th class="label_row" align="center" width="50%">统计元</th>
					<th class="label_row" align="center" width="20%"><input type="button" value="+" onclick="addstat()"/></th>
				</tr>
				<c:if test="${obj.term.statinfolist==null||fn:length(obj.term.statinfolist)==0 }">
					<tr id="statbranch">
						<td>
							<input style="width: 200px;"  name="term.statinfolist[0].key" type="text" value="${obj.term.statinfolist[0].key }">
						</td>
						<td>
							<wddc:jsonselect name="term.statinfolist[0].value" jsonstr="${obj.unit}" style="width: 400px;height:30px" initValue="请选择"/>
						</td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
				</c:if>
					<c:if test="${obj.term.statinfolist!=null&&fn:length(obj.term.statinfolist)>0 }">
						<c:forEach items="${obj.term.statinfolist}" var="term" varStatus="row">
							<c:if test="${row.index == 0}">
							<tr id="statbranch">
						</c:if>
						<c:if test="${row.index != 0}">
							<tr>
						</c:if>
						<td>
							<input style="width: 200px;"  name="term.statinfolist[${row.index}].key" type="text" value="${obj.term.statinfolist[row.index].key }">
						</td>
						<td>
							<wddc:jsonselect name="term.statinfolist[${row.index}].value" jsonstr="${obj.unit}" style="width: 400px;height:30px" initValue="请选择" defaultValue="${obj.term.statinfolist[row.index].value }"/>
						</td>
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
var statindex=0;
var statlistName;
$(document).ready(function(){
	statindex = '${fn:length(obj.term.statinfolist)}';
	if(statindex == 0) statindex=1;
	statlistName = "term.statinfolist[0]";
	$("#dic").dicajaxselect({
		url:"${ctx}/kernel/dic/getBusinessDic",
		defaultvalue:"${obj.term.axisdic }",
		initvalue:"不使用轴坐标字典",
		initoptvalue:"0"
	});
});
function addstat(){
	var statTable = $("#statTable");
	var cloneTr = $("#statbranch").clone();
	$(cloneTr).attr("id",$("#statbranch").attr("id")+"_"+statindex);
	$(cloneTr).find(":text").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(statlistName) != -1){
				$(this).attr("name",thisName.replace(statlistName,"term.statinfolist["+ statindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+statindex));
		}
		$(this).val("");
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(statlistName) != -1){
				$(this).attr("name",thisName.replace(statlistName,"term.statinfolist["+ statindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+statindex));
		}
		$(this).find("option:first").prop("selected",'selected');
		$(this).show();
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	statindex++;
	$(cloneTr).appendTo($("#statTable"));
}
function deletetr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
	}
}
function save(){
	if($('#name').val()==''){
		alert('请填写统计名称！');
		return ;
	}
	$('#form1').submit();
}
</script>
</html>