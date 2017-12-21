<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据查看</title>
</head>
<body>
<div style="height: 20px;">&nbsp;</div>
<form id="queryForm" name="queryForm" action="${ctx}/sjtb/toSeeData" method="post">
<input type="hidden" name="contents" value="${obk.contents }" />
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<c:if test="${obj.admin }">
				<td>
				所属部门:&nbsp;
				<wd:select id="dept" name="dept" dicCode="1069" className="dfinput" initValue="---请选择---" defaultValue="${obj.dept }" onchange="initsjlx('1');"/>
			</td>
			</c:if>
			<td>
				数据类型：&nbsp;
				<wd:select id="sjlx" name="sjlx" dicCode="" className="dfinput" initValue="---请选择---" onchange="getsheet('1')"/>
				<wd:select id="sheet" name="sheet" dicCode="" className="dfinput" initValue="---请选择---" style="width:130px"/>
			</td>
			<td>数据所属月份：&nbsp;<wd:datepicker  id="sjssyf" name="sjssyf" dateFormat="yyyy-MM"  defaultValue="${obj.sjssyf}" className="dfinput" minDate="2014-01-01" style="width:150px;height:25px;" />
			</td>
			<td>
				<input type="button" class="minButton" style="width:120px" onclick="query(1)" value="查  询"/>
			</td>
			<td>
				<input id="daochu" type="button" class="minButton" style="width:120px" onclick="query(2)" value="导出数据"/>
			</td>
		</tr>
	</table>
	<div style="height:10px;"></div>
	<div align="center">
			${obj.html }
	</div>
	<div>
	<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
function query(type){
	if($('#sjlx').val()==null||$('#sjlx').val()==''||$('#sheet').val()==null||$('#sheet').val()==''){
		alert("请选择要查询的数据！");
		return;
	}
	if(type == '1'){
		$('#pageNo').val('1');
		$("#queryForm").attr("action","${ctx}/sjtb/toSeeData");
		$('#queryForm').submit();
	}
	if(type=='2'){
		$("#queryForm").attr("action","${ctx}/sjtb/exportData");
		$('#queryForm').submit();
	}
}
function initsjlx(type){
	var obj;
	if(type=='1')
	{
		obj=$("#dept").val();
	}
	if(type=='2'){
		obj="${sessionScope.user.dept}";
	}
	if (obj == null || obj == undefined) return;
	$.ajax({
		type:"post",
		url:"${ctx}/sjtb/getSjlx",
		data:{
			dept:obj
		},dataType : "json",
		success:function(result){
			$('#sjlx').empty();
			$('#sjlx').append("<option value=''>---请选择---</option>");
			$.each(result.contents,function(n,value){
				if(value.name+""=="${obj.sjlx}"){
					$('#sjlx').append("<option value='"+value.name+"' selected='selected' >"+value.zwm+"</option>");
				}
				else{
					$('#sjlx').append("<option value='"+value.name+"' >"+value.zwm+"</option>");
				}
			});
		}
	});
}
window.onload=function(){
	$('#sjssyf').attr("autocomplete","off");
	if('${obj.admin }')
	{
		if($("#dept").val()!=null&&$("#dept").val()!=""){
			initsjlx('1');
		}
	}else{
		initsjlx('2');
		$("#daochu").hide();
	}
	getsheet('2');
	$("#year").find("option[value='${obj.year}']").attr("selected",true);
	$("#month").find("option[value='${obj.month}']").attr("selected",true);
};
function view(id,sjlx,sheet){
	href = "${ctx}/sjtb/view?id="+id+"&sjlx="+sjlx+"&sheet_id="+sheet;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	if (returnValue==1){
		 query();
	}
}
function getsheet(type){
	var obj;
	$('#sheet').attr("disabled","disabled");
	if(type=='1'){
		obj=$("#sjlx").val();
	}
	if(type=='2'){
		obj="${obj.sjlx}";
	}
	if (obj == null || obj == undefined) {
		$('#sheet').empty();
		$('#sheet').append("<option value=''>---请选择---</option>");
		return;
	}
	$.ajax({
		type:"post",
		url:"${ctx}/sjtb/getSheet",
		data:{
			sjlx:obj
		},dataType : "json",
		success:function(result){
			$('#sheet').empty();
			$('#sheet').append("<option value=''>---请选择---</option>");
			$.each(result.sheets,function(n,value){
				$('#sheet').removeAttr("disabled"); 
				if(value.orderNo+""=="${obj.sheet}"&&type=='2'){
					$('#sheet').append("<option value='"+value.orderNo+"' selected='selected' >"+value.sheetName+"</option>");
				}
				else{
					$('#sheet').append("<option value='"+value.orderNo+"' >"+value.sheetName+"</option>");
				}
			});
		}
	});
}
</script>
</html>