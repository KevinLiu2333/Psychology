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
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
	<form id="uploadform" method="post" action="${ctx}/sjtb/sgs/upload1" enctype="multipart/form-data">
		
		<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search">
			<tr>
				<td height="10">&nbsp;</td>
				<td height="10">&nbsp;</td>
			</tr>
			<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
				所属部门:&nbsp;
				<wd:select id="dept" name="dept" dicCode="1069" className="dfinput" initValue="---请选择---" defaultValue="${obj.dept }" onchange="initsjlx('1')" />
			</td>
			<td>数据类型：&nbsp;</td>
				<td width="13%"><input id="id" type="text" class="list_search_input" name="id"  style="width:90%;" /></td>	
			
			<td>数据所属月份：&nbsp;<wd:datepicker  id="sjssyf" name="sjssyf" dateFormat="yyyy-MM"  defaultValue="${obj.sjssyf}" className="dfinput" minDate="2014-01-01" style="width:150px;height:25px;" />
			</td>
				<td align="center">
					<input type="file" id="file" name="tempFile" accept=".xls" style="float:left"  />
				</td>
			
				<td>
					&nbsp;&nbsp;<input type="button" class="minButton" style="width:100px" value="上传上报文件" onclick="UpladFile()" />
				</td>
			</tr>
		</table>
	</form>
	<div style="height:10px;"></div>
	<form id="queryForm" name="queryForm" action="${ctx}/sjtb/sgs/toTbym?id=${obj.id}"
		method="post">
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
						<th width="20%">序号</th>
						<th width="80%">上报时间</th>
			</tr>
			<c:forEach items="${obj.tbfilelist}" var="tb" varStatus="row">
			<tr>
				<td align="center">${row.index+1 }</td>
				<td align="center"><fmt:formatDate value="${tb.tbdate }" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
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
function sjyl(fileid){
	href = "${ctx}/sjtb/toFileYl?type=${obj.type}&fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
function downloadmonban(){
	window.location.href="${ctx}/sjtb/sgs/downloadMoban?id=${obj.id}";
}

function UpladFile(){
	//var fileObj = document.getElementById("file");
	//var fileObj = document.getElementById("file").file;
	//var sjssyf=$('#sjssyf').val();
	/* if(fileObj==null){
		alert("请选择上传文件！");
		return;
	} */
	var fileObj = document.getElementById("file");
	if(fileObj.value==''){
		alert("请选择上传文件！");
		return;
	}
	$('#loading').show();
	$('#uploadform').submit();
}
/* function UpladFile() {
	
	var fileObj = document.getElementById("file").files[0]; // 获取文件对象
	var FileController = "${ctx}/sjtb/upload"; // 接收上传文件的后台地址 
	var datayear=$('#datayear').val();
	var datamon=$('#datamon').val();
	if(fileObj==null){
		alert("请选择上传文件！");
		return;
	}
	if(datayear==null||datayear==''){
		alert("请选择文件数据所属年份！");
		return ;
	}
	if(datamon==null||datamon==''){
		alert("请选择文件数据所属月份！");
		return ;
	}
	// FormData 对象
	var form = new FormData();
	form.append("tempFile", fileObj); // 文件对象
	form.append("tbtype", "${obj.type}"); 
	form.append('datayear',datayear);
	form.append('datamon',datamon);
	// XMLHttpRequest 对象
	var xhr = new XMLHttpRequest();
	xhr.open("post", FileController, true);
	xhr.onreadystatechange = callback;
	xhr.send(form);
	function callback() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				// 转换Json数据为javascript对象  
				eval("var objResults =" + xhr.responseText);
				if(objResults.state==1)
				{
					sjyl(objResults.tbid);
					window.location.href="${ctx}/sjtb/toTbym?type=${obj.type}";
				}
				if(objResults.state==0){
					error(objResults.tbid);
					window.location.href="${ctx}/sjtb/toTbym?type=${obj.type}";
				}
			}
		}
	}
} */

function error(fileid){
	href = "${ctx}/sjtb/sgs/toFileError?fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
window.onload = function() {
	$('#sjssyf').attr("autocomplete","off");
	if("${obj.state}"=="1"){
		success();
	}
	if("${obj.state}"=="0"){
		error('${obj.fileid}');
	}
	
};
function success(){
	href = "${ctx}/sjtb/sgs/success";
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
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
</script>
</html>