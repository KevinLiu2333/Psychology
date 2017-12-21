<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<!-- 表单验证组件 --> 
 <script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
<title>资源申请</title>
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
function nextStep(){
	$('#MainForm').submit();
}
</script>
</head>
<body>
<form id="MainForm" action="${ctx}/zymlgx/toApiItemSelect" method="post">
<input type="hidden" id="resourceId" name="resourceId" value="${obj.resourceId}">
<p style="text-align:left;font-size:20px;padding-bottom: 15px;padding-top: 10px;margin-left: 60px;"><b>资源申请</b></p><br>
	<table width="60%" class="table_multilist" align="center" style="color: navy;">
		<tr>
			<td style="font-size: 15px">
			
			资源数据申请操作说明：<br>
			1、用户根据需求选择需要申请的资源类型。<br>
			1）“资源目录检索”页面内左侧基础数据资源仅提供接口方式；<br>
			2）专题类型数据依据资源目录内的“对接方式”项确定资源提供方式，包括“系统接口方式”和“离线文件方式”；<br>
			3）离线文件方式仅提供上月数据内容，并由区科委统一邮件提供。<br>
			2、勾选资源项名称，其中“普遍共享”和“按需共享”的资源项为当前可申请的资源，“不共享”的资源项为当前不可申请的资源。<br>
			3、选择需要的资源类型和资源项后，点击“下一步”。<br>
			4、填写申请主题信息（申请标题）、申请人联系电话和邮箱（必填），具体申请原因（简要描述资源获取后的用途），以及上传申请相关附件材料（如有）；点击“下一步”。<br>
			5、阅读申请资源协议，无异议并勾选复选框，点击“提交审核”。<br>
			6、区科委联系人：李逸健 52564588-7159，如有问题前及时联系。

			<br><br><br><br>
			</td>
		</tr>
	</table>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="下一步" onclick="nextStep();" /> 
	</div>
</form>
</body>
</html>