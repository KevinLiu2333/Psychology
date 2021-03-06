<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标准规范</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<link rel="stylesheet" href="${ctx}/tiles/bootstrap2.3.2/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap2.3.2/js/bootstrap.min.js"></script>
 <style type="text/css">
 .table th, .table td { 
text-align: center; 
height:38px;
valign:middle;
}

 </style>
</head>
<body>
<div align="center">
   
   <div style="width:800px">
   <div><div align="right"><input class="dfinput"  id="fileUpload1" name="fileUpload1" type="file" value=""/></div></div>
   <table width="100%" class="table table-striped table-bordered table-hover table-condensed"  align="center" >
   	<tr style="height:50px" >
   		<th style="BORDER-RIGHT: #000000 0px solid;"></th>
   		<th style="BORDER-left: #000000 0px solid;">文档名称</th>
   		<th>文件大小</th>
   		<th>上传时间</th>
   		<th>操作</th>
   	</tr>
   	<c:forEach var="f" items="${obj.doc }">
   		<tr style="height:50px" >
   			<td style="BORDER-RIGHT: #000000 0px solid;">
   			<c:choose>
   				<c:when test="${f.fileSuffix=='doc'||f.fileSuffix=='docx'}">
   					<img  src="${ctx}/skins/images/db/logo/word.jpg"  style="widows:40px;height:40px">
   				</c:when>
   				<c:when test="${f.fileSuffix=='ppt'||f.fileSuffix=='pptx'}">
   					<img  src="${ctx}/skins/images/db/logo/ppt.jpg"  style="widows:40px;height:40px">
   				</c:when>
   				<c:when test="${f.fileSuffix=='pdf'}">
   					<img  src="${ctx}/skins/images/db/logo/pdf.jpg"  style="widows:40px;height:40px">
   				</c:when>
   				<c:otherwise >
   					<img  src="${ctx}/skins/images/db/logo/txt.jpg"  style="widows:40px;height:40px" >
   				</c:otherwise>
   			</c:choose>
			</td>
   			<td style="BORDER-left: #000000 0px solid;">${f.filenamelocal }</td>
   			<td ><fmt:formatNumber value="${f.filelength/1024 }" pattern="#.0"></fmt:formatNumber>kb</td>
   			<td ><fmt:formatDate value="${f.postedtime }" type="both" pattern="yyyy年MM月d日  HH:mm:ss "/></td>
   			<td ><a href="${ctx}/db/file/fileDownById?fid=${f.fid}">下载</a></td>
   		</tr>
   	</c:forEach>
   </table>
   </div>
   
</div>
<script type="text/javascript">
$(document).ready(function(){
	//配置文件上传文件类型
	var exts = "*.doc;*.ppt;*.pdf;*.xls";
	//配置上传文件类型说明
	var desc = "请选择规范文件";
	//配置上传文件的大小
	var size = 10*1024;
	//bookFile文件类型
	//flash的url
	var upurl = getUpUrl();
	$('#fileUpload1').uploadify({  
		 swf: upurl, 
		 uploader : "${ctx}/db/file/uploadFile",
		 // 上传使用的 Flash        
		 width: 80,                          // 按钮的宽度         
		 height: 23,                         // 按钮的高度      
		 fileSizeLimit:size,  
		 fileObjName : 'tempFile', 
		 buttonText: "上传规范文件",                 // 按钮上的文字       
		 buttonCursor: 'hand',                // 按钮的鼠标图标         
		 fileTypeExts: exts,             // 扩展名      
		 fileTypeDesc: desc,     // 文件说明     
		 auto: true, 
		 // 选择之后，自动开始上传    
		 multi: false,   
		 overrideEvents : [ 'onDialogClose', 'onUploadError', 'onSelectError' ],
		 onSelectError : onSelectError, 
		 onUploadSuccess : UploadSuccess
	 });
	
	
});
//错误说明
function onSelectError(file, errorCode, errorMsg, errorString){	
var msgText = "上传失败\n";  
	switch (errorCode) {  
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
			msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
			break;  
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
			msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";  
			break;  
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:  
			msgText += "文件大小为0";  
			break;  
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:  
			msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;  
			break;  
		default:  
			msgText += "错误代码：" + errorCode + "\n" + errorMsg;  
	}  
}
//flash的url的获取
function getUpUrl(){	
	var url = window.location.protocol+"//"+window.location.host;
	return url+"${ctx}/tiles/uploadify/scripts/uploadify.swf";
}
//上传完成时的回调
function UploadSuccess(){
	window.location.href="${ctx}/db/toDoc";
}
</script>
</body>
</html>