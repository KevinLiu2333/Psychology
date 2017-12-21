<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<script type="text/javascript">
//附件上传
$(function(){
	//配置文件上传文件类型
	var exts = "*.xls";
	//配置上传文件类型说明
	var desc = "请选择 doc 文件";
	//配置上传文件的大小
	var size = 200;
	//flash的url
	var upurl = getUpUrl();
	//busId
	$('#jmsqUpload').uploadify({  
		 swf: upurl, 
		 uploader : "${ctx}/zy/templet/uploadResourceCatalogTemplet",		 
		 // 上传使用的 Flash        
		 width: 80,                          // 按钮的宽度         
		 height: 30,                         // 按钮的高度      
		 fileSizeLimit:size,  
		 fileObjName : 'tempFile',       
		 buttonText: "文件上传",                 // 按钮上的文字       
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
//错误说明
function onSelectError(file, errorCode, errorMsg, errorString){	
	var msgText = "上传失败\n";  
	switch (errorCode) {  
	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
		msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
		break;  
	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
		msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " K)";  
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
	alert(msgText);
}
//flash的url的获取
function getUpUrl(){	
	var url = window.location.protocol+"//"+window.location.host;
	return url+"${ctx}/tiles/uploadify/scripts/uploadify.swf";
}
//上传完成时的回调
function imgUploadSuccess(file, data, response){
	var obj = eval("["+data+"]");
	$('#fileIds').val(obj[0].file.fid);
}

//模板下载
function downloadTemplet(isCatalog){
	window.location.href="${ctx}/zy/templet/downloadTemplet?isCatalog="+isCatalog;
}

function wind_close(){
	window.opener=null;
	window.open('','_self');
	window.close();
}

</script>
</head> 
<body style="width:96%">
<form action="" method="post" enctype="multipart/form-data">
	<div align="center">
		<h1 style="margin-top: 20px;font-size: 20px;">资源目录-模板下载|上传</h1>
		<table width="96%" style="margin-top: 50px;">
			<tr>
				<td><img src="${ctx}/skins/images/db/logo/word.jpg" style="widows:40px;height:40px"></td>
				<td style="margin-left: 80px;"><input id="jmsqUpload" value="上传"/></td>
			</tr>
			<tr>
				<td><a href="#" onclick="downloadTemplet('R001')">模板下载</a></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div style="margin-left: 230px;margin-top: 100px;">
		<p style="font-size: 15px;color: gray;">
		操作步骤：<br>1、先点击下载左边的资源目录模板。<br>2、按照下载的模板填写好资源目录信息，包括数据资源项。<br>3、上传填写好的资源目录xls文件。
		</p>
	</div><br>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px;margin-left: 25px;">
		<input type="button" id="button"  class="button" value="关  闭" onclick="wind_close();"/> 
	</div>
</form>	
</body>
</html>