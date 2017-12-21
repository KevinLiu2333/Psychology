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
<title>Api编辑</title>
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
function dosubmit(){
	if($('#serviceName').val()==''){
		alert("请输入api名称！");
		return;
	}
	if($('#summary').val()==''){
		alert("请输入简介！");
		return;
	}
	if($('#url').val()==''){
		alert("请输入url！");
		return;
	}
	if($('#function').val()==''){
		alert("请输入api方法！");
		return;
	}
	if($('#param').val()==''){
		alert("请输入传入参数！");
		return;
	}
	if($('#returnParam').val()==''){
		alert("请输入返回内容！");
		return;
	}
	if($('#apiType').val()==''){
		alert("请选择服务接口类型！");
		return;
	}
	if($('#instruction').val()==''){
		alert("请输入说明！");
		return;
	}
	if(confirm("确定要保存吗？")){
			$("#mainForm").submit();
	}
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>Api编辑</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/api/saveApi" method="post" >
	<input type="hidden" id="id" name="service.id" value="${obj.service.id }"/>
	<input type="hidden" id="id" name="service.valid" value="${obj.service.valid }"/>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td width="15%" class="label_1"  style="text-align:right; height:30px">Api名称：</td>
			<td width="35%" class="label_2">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="serviceName" name="service.serviceName" value="${obj.service.serviceName}" datatype="*1-50"nullmsg="请输入Api名称！"maxlength="50" errormsg="请输入少于50个汉字！"/>
			</td>
			<td class="label_1" width="15%" style="text-align:right; height:30px">简介:</td>
			<td class="label_2" width="35%">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="summary" name="service.summary" value="${obj.service.summary}" datatype="*1-200" nullmsg="请输入简介！" maxlength ="200" errormsg="请输入少于200个汉字！"/>
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">url：</td>
			<td class="label_2">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="url" name="service.url" value="${obj.service.url}" datatype="*1-200" nullmsg="请输入url！" maxlength="200" errormsg="请输入少于200个汉字！"/>
			</td >
			<td style="text-align:right; height:30px" class="label_1">api方法:</td>
			<td class="label_2">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="function" name="service.function" value="${obj.service.function}" datatype="*1-50" nullmsg="请输入api方法！" maxlength="50" errormsg="请输入少于50个汉字！"/>
			</td>
		</tr>
		<tr>
			<td style="text-align:right; height:30px" class="label_1">传入参数：</td>
			<td class="label_2">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="param" name="service.param" value="${obj.service.param}" datatype="*1-100" nullmsg="请输入调用参数！" maxlength="100" errormsg="请输入少于100个汉字！"/>
			</td>
			<td style="text-align:right; height:30px" class="label_1">返回内容:</td>
			<td class="label_2">
				&nbsp;<input type="text" class="dfinput" style="width:95%" id="returnParam" name="service.returnParam" value="${obj.service.returnParam}" datatype="*1-50" nullmsg="请输入返回参数！" maxlength="50" errormsg="请输入少于50个汉字！"/>
			</td>
		</tr>
		<tr>
			<td class="label_1">服务接口类型：</td>
			<td class="label_2" colspan="3">&nbsp;<wd:select id="apiType" name="service.apiType" className="dfinput" dicCode="1048" defaultValue="${obj.service.apiType }" initValue="---请选择---"/></td>
		</tr>
		<tr>
			<td class="label_1">服务状态：</td>
			<td class="label_2" colspan="3">&nbsp;<wd:select id="apiStatus" name="service.status" className="dfinput" dicCode="2015" defaultValue="${obj.service.status }" initValue="---请选择---"/></td>
		</tr>
		<tr height="100px">
			<td style="text-align:right; " class="label_1">说明:</td>
			
			<td colspan="3" class="label_2">
				 &nbsp;<textarea rows="5" cols="90" id="instruction" class="dfinput" style="width:95%;height: 80%;"  name="service.instruction">${obj.service.instruction}</textarea>
			</td>
		</tr>
	</table>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="保存修改" onclick="dosubmit();" /> 
	</div>
</form>
</body>
</html>