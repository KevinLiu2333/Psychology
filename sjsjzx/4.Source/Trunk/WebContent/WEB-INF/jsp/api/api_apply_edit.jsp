<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/> 
<title>松江区政务数据中心-API申请</title>
<style type="text/css">
.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">
//上一步
function backStep(){
	window.history.back(-1); 
}
//下一步
function nextStep(){
	var sqztVar = $('#sqzt').val();
	var sqyyVar = $('#sqyy').val();
	if(sqztVar == null || sqztVar == ""){
		alert("请填写申请主题!");
		return;
	}
	if(sqyyVar == null || sqyyVar == ""){
		alert("请填写申请原因!");
		return;
	}
	$('#MainForm').submit();
}
//附件上传
$(function(){
	//配置文件上传文件类型
	var exts = "*.doc";
	//配置上传文件类型说明
	var desc = "请选择 doc 文件";
	//配置上传文件的大小
	var size = 200;
	//flash的url
	var upurl = getUpUrl();
	//busId
	$('#jmsqUpload').uploadify({  
		 swf: upurl, 
		 //uploader : "${ctx}/file/uploadSingle?catalog=01&busId="+personId,
		 uploader : "${ctx}/db/file/uploadApiFile",		 
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

function downAppFile(fileId){	
	location.href='${ctx}/file/fileDownById?fileId='+fileId;
}


//上传完成时的回调
function imgUploadSuccess(file, data, response){
	var obj = eval("["+data+"]");
	$('#fileIds').val(obj[0].file.fid);
	//$('#jmsqfile').html("<a style='font-size: 14px' href='javaScript:downAppFile(\""+obj[0].file.fid+"\")'>"+file.name+"</a>&nbsp;&nbsp;");
}

</script>
</head>
<body>
<form id="MainForm" action="${ctx}/api/toApplyAgreement" method="post">
<input type="hidden" name="api.department" value="${obj.apiApply.department }"> 
<input type="hidden" name="api.applyInfo" value="${obj.apiApply.applyInfo}">
<input type="hidden" id="fileIds" name="api.fileIds" value="">
<p style="text-align:left;font-size:20px;padding-bottom: 15px;padding-top: 10px;margin-left: 60px;"><b>API资源申请</b></p>
	</br>
	<table width="60%" class="table_multilist" align="center" style="color: navy;">
		<tr>
			<td width="10%" class="label_1"  style="height:30px">申请人：</td>
			<td width="35%" class="label_2">${obj.userName }</td>
		</tr>
		<tr>
			<td width="10%" class="label_1"  style="height:30px">申请单位：</td>
			<td width="35%" class="label_2">${obj.apiApply.department }</td>
		</tr>
		<tr>
			<td width="10%" class="label_1"  style="height:30px">申请时间：</td>
			<td width="35%" class="label_2">${obj.date}</td>
		</tr>
		<tr>
			<td width="10%" class="label_1"  style="height:30px">申请主题：</td>
			<td width="35%" class="label_2">
				&nbsp;<input type="text" id="sqzt" name="api.applyTopic" class="dfinput" width="80%">
			</td>
		</tr>
		<tr>
			<td width="10%" class="label_1"  style="height:30px">申请原因：</td>
			<td width="35%" class="label_2">
				<textarea class="dftextarea" id="sqyy" rows="6" cols="90" name="api.applyReason" ></textarea>
			</td>
		</tr>
		<tr>
			<td width="10%" class="label_1"  style="height:30px">相关附件：</td>
			<td width="35%" class="label_2">
				<input id="jmsqUpload" type="button"  class="minButton" value="上传" onclick="uploadFile();"/> 
				<span id="jmsqfile"></span>
			</td>
		</tr>
	</table>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="上一步" onclick="backStep();"/> 
		<input type="button" id="button"  class="button" value="下一步" onclick="nextStep();"/> 
	</div>
</form>
</body>
</html>