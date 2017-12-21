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
<title>用户管理</title>
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
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>人员的用户信息</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/yhgl/gxyh" method="post" >
	<input type="hidden" name="user.userId" value="${obj.user.userId }" />
		<table width="80%" class="table_multilist" align="center">
			<tr id="result">
				<td width="20%" style="text-align:right; height:30px">登录名称：</td>
				<td align="center"> ${obj.user.logonName}</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px">用户名称：</td>
				<td align="center">${obj.user.displayName}</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px">部门：</td>
				<td align="center">
					<wd:dicvalue dicId="1003" dicCode="${obj.user.dept}"/>
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px">e-mail：</td>
				<td align="center">
					${obj.user.eMail }
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px" >用户权限：</td>
				<td align="center">
					<wd:checkbox id="role" name="user.roleId"  dicCode="1066" defaultValuesStr="${obj.user.roleId }" />
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px">资源审批权限：</td>
				<td align="center">
					<wd:select id="type" name="user.type" dicCode="2014" className="dfinput" defaultValue="${obj.user.type }"/>
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align:right; height:30px">用户状态：</td>
				<td align="center">
					<wd:select id="state" name="user.state" dicCode="1067" className="dfinput" defaultValue="${obj.user.state }"/>
				</td>
			</tr>
			
		</table>
		<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="保存修改"  onclick="xiugai()"/> 
		</div>
</form>
</body>
<script type="text/javascript">

$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});

function xiugai(){
	var flag=0;
	var state=$('#state').val();
	for(var i= 1;i <20; i++) {
		 if($('#'+i).attr("checked")=="checked"){
			flag=1;
		 }
	}
	if(flag!=1&&state=='1'){
		alert("请选择权限或关闭用户！");
		return;
	}
	var userid ='${obj.user.userId}';
	var thisid ='${obj.thisuser.userId}';
	if(userid!=thisid){
		$('#mainForm').submit();
	}else{
		alert("用户无法对自己进行修改!");
	}
}
</script>
</html>