<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据填报</title>
<style type="text/css">
.loading{
top:250px;
left:350px;
z-index:10000;
position:absolute; 
font-size: 30px;
color: rgba(200,0,0,0.5);
filter:alpha(opacity=80);
opacity:0.8;
display: none;
}
</style>
</head>
<body>

<div style="height:20px;">&nbsp;</div>
<form id="uploadform" method="post" action="${ctx}/sjtb/uploadbackgroud" enctype="multipart/form-data">
	<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>
				所属部门:&nbsp;
				<wd:select id="dept" name="dept" dicCode="1069" className="dfinput" initValue="---请选择---" defaultValue="${obj.dept }" onchange="initsjlx('1');"/>
			</td>
			<td>
				数据类型：&nbsp;
				<wd:select id="sjlx" name="tbtype" dicCode="" className="dfinput" initValue="---请选择---" onchange="getsheet('1')"/>
			</td>
			<td>数据所属月份：&nbsp;<wd:datepicker  id="sjssyf" name="sjssyf" dateFormat="yyyy-MM"  defaultValue="${obj.sjssyf}" className="dfinput" minDate="2014-01-01" style="width:150px;height:25px;" />
			</td>
			<td>
				<input type="file" id="file" name="tempFile" accept=".xls" style="float:left"  />
			</td>
			<td>
				<input type="button" class="minButton" style="width:120px" onclick="upload()" value="上传"/>
			</td>
		</tr>
	</table>
</form>
<div style="height:10px;"></div>
	<form id="queryForm" name="queryForm" action="${ctx}/sjtb/toTbymBackgroud"
		method="post">
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="20%">序号</th>
						<th width="30%">上报时间</th>
						<th width="30%">数据所属月份</th>
						<th width="20%">操作</th>
			</tr>
			<c:forEach items="${obj.tblist}" var="tb" varStatus="row">
			<tr>
				<td align="center">${row.index+1 }</td>
				<td align="center"><fmt:formatDate value="${tb.tbdate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
				<td align="center">
				<c:if test="${fn:length(tb.dataYear)==6 }">
					${fn:substring(tb.dataYear, 0, 4) }年${fn:substring(tb.dataYear, 4, 6) }月
				</c:if>
				<c:if test="${fn:length(tb.dataYear)==4 }">${tb.dataYear}年</c:if>
				<c:if test="${fn:length(tb.dataMon)==2 }">${tb.dataMon}月</c:if>
				</td>
				<td align="center">
					<a href='#' onclick="sjyl2('${tb.id}','${tb.tbtype }')" >预览</a>
				</td>
			</tr>
			</c:forEach>
		</table>
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
<div id="loading" class="loading" ><img src="${ctx }/skins/images/loading.gif" />&nbsp;正在上传中...</div>
</body>
<script type="text/javascript">
function upload(){
	if($('sjlx').val()==''){
		alert('前选择数据类型');
	}
	if($('sjssyf').val()==''){
		alert('前选择数据所属月份');
	}
	var fileObj = document.getElementById("file");
	if(fileObj.value==''){
		alert("请选择上传文件！");
		return;
	}
	$('#loading').show();
	$('#uploadform').submit();
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
window.onload = function() {
	$('#sjssyf').attr("autocomplete","off");
	if("${obj.state}"=="1"){
		sjyl('${obj.tbid}');
	}
	if("${obj.state}"=="0"){
		error('${obj.tbid}');
	}
	
};
function error(fileid){
	href = "${ctx}/sjtb/toFileError?type=${obj.type}&fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
function sjyl(fileid){
	href = "${ctx}/sjtb/toFileYl?type=${obj.type}&fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
function sjyl2(fileid,tbtype){
	href = "${ctx}/sjtb/toFileYl?type="+tbtype+"&fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
</script>
</html>