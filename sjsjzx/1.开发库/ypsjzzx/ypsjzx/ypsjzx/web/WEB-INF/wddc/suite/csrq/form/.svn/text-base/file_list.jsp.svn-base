<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/tiles/dreamui/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/skins/blue/css/sjdcbs.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/dreamui/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${ctx}/tiles/dreamui/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	//配置文件上传文件类型
	var exts = "*.*";
	//配置上传文件类型说明
	var desc = "请选择文件";
	//配置上传文件的大小
	var size = 2000;
	//flash的url
	var upurl = getUpUrl();
	$('#imgUpload').uploadify({  
		 swf: upurl, 
		 uploader : "${ctx}/file/uploadFile?busId=${obj.busId}&busType=04&taskNo=${obj.taskNo}",
		 // 上传使用的 Flash        
		 width: 130,                          // 按钮的宽度         
		 height: 30,                         // 按钮的高度      
		 fileSizeLimit:size,  
		 fileObjName : 'tempFile',       
		 buttonText: "上传文件",                 // 按钮上的文字       
		 buttonCursor: 'hand',                // 按钮的鼠标图标         
		 fileTypeExts: exts,             // 扩展名      
		 fileTypeDesc: desc,     // 文件说明     
		 auto: true, 
		 // 选择之后，自动开始上传    
		 multi: false,   
		 overrideEvents : [ 'onDialogClose', 'onUploadError', 'onSelectError' ],
		 onSelectError : onSelectError, 
		 onUploadSuccess : imgUploadSuccess  
		 }); 
});
//flash的url的获取
function getUpUrl(){	
	var url = window.location.protocol+"//"+window.location.host;
	return url+"${ctx}/tiles/dreamui/uploadify/scripts/uploadify.swf";
}

function downAppFile(fileId){	
	location.href='${ctx}/file/fileDownById?fileId='+fileId;
}


//上传完成时的回调
function imgUploadSuccess(file, data, response){
	$('#queryForm').submit();
}

//错误说明
function onSelectError(file, errorCode, errorMsg, errorString){	
	var msgText = "上传失败\n";  
	switch (errorCode) {  
	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
		msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
		break;  
	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
		msgText += "大小超过限制( " + this.settings.fileSizeLimit + " K)";  
		break;  
	case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:  
		msgText += "大小为0";  
		break;  
	case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:  
		msgText += "格式不正确，仅限 " + this.settings.fileTypeExts;  
		break;  
	default:  
		msgText += "错误代码：" + errorCode + "\n" + errorMsg;  
	}  
	alert(msgText);
}

function delFile(fileId){
	$.post("${ctx}/file/delFile?fileId="+fileId, 
            { Action: "post"},
            function (data, textStatus){
            	alert("删除成功！");
            	$('#queryForm').submit();
             }
            , "json"); 
}
</script>
</head>

<body>
<c:if test="${obj.op != 'view'}">
<table>
	<tr>
		<td><input id="imgUpload" name="imgUpload" type="file" width="130" height="30"/></td>
	</tr>
</table>
</c:if>
<c:if test="${obj.op == 'view'}">
<table>
	<tr>
		<td>上传文件列表</td>
	</tr>
</table>
</c:if>
<form name="queryForm" id="queryForm" method="post" action="${ctx }/file/getUploadList">
<input type="hidden" name="busId" value="${obj.busId }" id="busId"/>
<input type="hidden" name="taskNo" value="${obj.taskNo }" id="taskNo"/>
<table width="96%" class="table_list" align="center">
<c:forEach var="file" items="${obj.fileList}">
	<tr>
		<td>
			<a href="#" onclick="downAppFile('${file.fileId}')">${file.fileName}</a>
		</td>
		<td>
			<c:if test="${obj.op != 'view'}">
			<a href="#" onclick="delFile('${file.fileId}')">删除</a>
			</c:if>
		</td>
	</tr>
</c:forEach>
</table>
</form>
</body>
</html>