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
    	<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
    	<script src="${ctx }/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
    	
    	
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">字典配置</h3>
<form id="form1" action="${ctx }/kernel/dic/save" method="post">
	<input type="hidden" name="dic.dicId" value="${obj.dic.dicId }">
	<div class="row">
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 字典名称：</div>
		     	<input id="name" style="width: 250px;"  name="dic.dicName" value="${obj.dic.dicName }">
		</div>
		<div class="col-md-6 form-group">&nbsp;
			<c:if test="${obj.dic!=null}">
				<div  style="float: left;width: 150px;">字典编号：</div>${obj.dic.dicId }
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 字典属性：</div>
			<select id="keyDataType" style="width: 250px;"  class="selectpicker" data-live-search="true" title="请选择" name="dic.keyDataType">
				<option value="1" <c:if test="${obj.dic.keyDataType==1 }">selected</c:if>>业务字典</option>
				<option value="2" <c:if test="${obj.dic.keyDataType==2 }">selected</c:if>>系统字典</option>
			</select>
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 字典类型：</div>
			<select id="dictype" style="width: 250px;"  class="selectpicker" data-live-search="true" title="请选择" name="dic.dicType" onchange="dictypechange()">
				<option value="1" <c:if test="${obj.dic.dicType==1 }">selected</c:if>>数据型字典</option>
				<option value="2" <c:if test="${obj.dic.dicType==2 }">selected</c:if>>描述型字典</option>
				<option value="3" <c:if test="${obj.dic.dicType==3 }">selected</c:if>>自定义字典</option>
			</select>
		</div>
	</div>
	<h4 id="disable-responsive2" class="page-header">字典详细配置 </h4>
	<div class="row" id="type1" style="display: none;" >
		<div class="col-md-12 form-group">
			<table id="dicTable" class="tables" width="100%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="40%">键</th>
					<th class="label_row" align="center" width="40%">值</th>
					<th class="label_row" align="center" width="20%"><input type="button" value="+" onclick="adddic()"/></th>
				</tr>
				<c:if test="${obj.dic.resources==null||fn:length(obj.dic.resources)==0 }">
					<tr id="dicbranch">
						<td>
							<input style="width: 200px;"  name="dic.resources[0].dickey" type="text" value="${obj.dic.resources[0].dickey }">
						</td>
						<td>
							<input style="width: 200px;"  name="dic.resources[0].dicvalue" type="text" value="${obj.dic.resources[0].dicvalue }">
						</td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
				</c:if>
				<c:if test="${obj.dic.resources!=null&&fn:length(obj.dic.resources)>0 }">
					<c:forEach items="${obj.dic.resources}" var="resources" varStatus="row">
							<c:if test="${row.index == 0}">
							<tr id="dicbranch">
						</c:if>
						<c:if test="${row.index != 0}">
							<tr>
						</c:if>
						<td>
							<input style="width: 200px;"  name="dic.resources[${row.index}].dickey" type="text" value="${obj.dic.resources[row.index].dickey }">
						</td>
						<td>
							<input style="width: 200px;"  name="dic.resources[${row.index}].dicvalue" type="text" value="${obj.dic.resources[row.index].dicvalue }">
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
	<div class="row" id="type2" style="display: none;" >
		<div class="col-md-12 form-group">
			<div  style="float: left;width: 150px;"> 字典值：</div>
			<input value="${obj.dic.valueList }" style="width: 500px" name="dic.valueList" >
			(格式，字典间用'|'分割：key=value|key=value)
		</div>
	</div>
	<div class="row" id="type3" style="display: none;" >
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 字典表：</div>
			<input id="tablename" name="dic.tableName"  style="width: 250px" value="${obj.dic.tableName }"/>
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 键字段：</div>
			<input id="keycolumn" name="dic.itemKeyColumn"  style="width: 250px" value="${obj.dic.itemKeyColumn }"/>
		</div>
		<div class="col-md-6 form-group">
			<div  style="float: left;width: 150px;"> 值字段：</div>
			<input id="valuecolumn" name="dic.itemNameColumn"  style="width: 250px" value="${obj.dic.itemNameColumn }"/>
		</div>
		<div class="col-md-12 form-group">
			<div  style="float: left;width: 150px;"> 附加sql：</div>
			<input id="appendsql" value="${obj.dic.appendSql }" style="width: 500px" name="dic.appendSql" >
			（where开始）
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
var dicindex=0;
var diclistName;
$(document).ready(function() { 
	dicindex = '${fn:length(obj.dic.resources)}';
	if(dicindex == 0) dicindex=1;
	diclistName = "dic.resources[0]";
	var type = "${obj.dic.dicType}";
	if(type=='1'){
		$('#type1').show();
		$('#tablename').val('');
		$('#keycolumn').val('');
		$('#valuecolumn').val('');
		$('#appendsql').val('');
	}
	if(type=='2'){
		$('#type2').show();
	}
	if(type=='3'){
		$('#type3').show();
	}
});
function dictypechange(){
	var type = $('#dictype').val();
	if(type=='1'){
		$('#type1').show();
		$('#type2').hide();
		$('#type3').hide();
	}
	if(type=='2'){
		$('#type2').show();
		$('#type1').hide();
		$('#type3').hide();
	}
	if(type=='3'){
		$('#type3').show();
		$('#type2').hide();
		$('#type1').hide();
	}
}

function deletetr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
	}
}
function adddic(){
	var statTable = $("#dicTable");
	var cloneTr = $("#dicbranch").clone();
	$(cloneTr).attr("id",$("#dicbranch").attr("id")+"_"+dicindex);
	$(cloneTr).find(":text").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(diclistName) != -1){
				$(this).attr("name",thisName.replace(diclistName,"dic.resources["+ dicindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+dicindex));
		}
		$(this).val("");
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	dicindex++;
	$(cloneTr).appendTo($("#dicTable"));
}
function save(){
	if($('#name').val()==''){
		alert('请输入字典名称！');
		return;
	}
	if($('#keyDataType').val()==''){
		alert('请选择字典属性！');
		return;
	}
	if($('#dictype').val()==''){
		alert('请选择字典类型！');
		return;
	}
	$('#form1').submit();
}
</script>
</html>