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
	<form id="uploadform" method="post" action="${ctx}/sjtb/upload" enctype="multipart/form-data">
		<input name="tbtype" type="hidden" value="${obj.type}" />
		<table border="0" cellspacing="0" cellpadding="0" width="100%"
			align="center">
			<tr>
				<td height="10">&nbsp;</td>
				<td height="10">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" class="minButton" value="模板下载" onclick="downloadmonban()" />
					
				</td>
				<td align="center">
					<input type="file" id="file" name="tempFile" accept=".xls" onchange="fileChange(this);" style="float:left"  />
				</td>
			
				<td>
					&nbsp;&nbsp;<input type="button" class="minButton" style="width:100px" value="上传上报文件" onclick="UpladFile()" />
				</td>
			</tr>
		</table>
	</form>
	<div style="height:10px;"></div>
	<form id="queryForm" name="queryForm" action="${ctx}/sjtb/toTbym?type=${obj.type}"
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
					<a href='#' onclick="sjyl('${tb.id}')" >预览</a>
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
	<div id="loading" class="loading" ><img src="${ctx }/skins/images/loading.gif" />&nbsp;<span id="state">正在上传中...</span></div>
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
	window.location.href="${ctx}/sjtb/downloadMoban?name=${obj.type}";
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
	window.setInterval(function(){
		$.get("${ctx}/sjtb/gettbstate",function(data){
			$('#state').html(data.replace(/\"/g, ""));
		});
	}, 500);
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
	href = "${ctx}/sjtb/toFileError?type=${obj.type}&fileid="+fileid;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	 if (returnValue==1){
		 query();
	 } 
}
window.onload = function() {
	$('#sjssyf').attr("autocomplete","off");
	if("${obj.state}"=="1"){
		sjyl('${obj.tbid}');
	}
	if("${obj.state}"=="0"){
		error('${obj.tbid}');
	}
	
}
function fileChange(target) {
    var fileSize = 0;         
    if (!target.files) {     
      	var filePath = target.value;     
      	var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
      	var file = fileSystem.GetFile (filePath);     
      	fileSize = file.Size;    
     } else {    
        fileSize = target.files[0].size;     
     }   
     var size = fileSize / 1024;   
     if(size>3000){
      	alert("文件过大，请拆分上传！");
      	$("#file").val("");
      	return
     }
} 

</script>
</html>