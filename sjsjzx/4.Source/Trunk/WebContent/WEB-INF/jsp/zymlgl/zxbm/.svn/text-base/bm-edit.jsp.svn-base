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
<title>松江区政务数据中心-在线编目</title>
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
 .red{
     color:red;
    }
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">
var loginName='${obj.loginName}';
var log='${obj.resources.tableChinese}';
$(document).ready(function(){
	if(loginName=="admin")
	{
		$("#sroce").show();
	}
	});
	
//共享属性全选
function changeShareValue(value){
    $("select[id^='shareProperty']").val(value);
}
//公开属性全选
function changeOpenValue(value){
	$("select[id^='openProperty']").val(value);
}

//信息校验
function checkInfo(type){
	if($('#resourceName').val()==''){
		alert("请输入资源目录名称！");
		return; 
	}
	if($('#infoSysName').val()==''){
		alert("请输入信息系统名称！");
		return;
	}
	if($('#sysUrl').val()==''){
		alert("请输入信息系统链接！");
		return;
	}
	if($('#provideDepartment').val()==''){
		alert("请输入提供科室！");
		return;
	}
	if($('#keyWord').val()==''){
		alert("请输入检索关键字！");
		return;
	}
	if($('#busLinkman').val()==''){
		alert("请输入业务联系人姓名！");
		return;
	}
	if($('#busLinkmanPhone').val()==''){
		alert("请输入业务联系人电话！");
		return;
	}
	if($('#jointLinkman').val()==''){
		alert("请输入对接联系人姓名！");
		return;
	}
	if($('#jointLinkmanPhone').val()==''){
		alert("请输入对接联系人电话！");
		return;
	}
	if($('#jointType').val()==''){
		alert("请输入对接方式！");
		return;
	}
	/*
	if($('#shareProperty').val()==''){
		alert("请选择共享属性！");
		return;
	}
	*/
	if($('#r_openProperty').val()==''){
		alert("请选择公开属性！");
		return;
	}
	if($('#updateRate').val()==''){
		alert("请输入更新频度！");
		return;
	}
	if($('#tableChinese').val()=='')
	{
		alert("请输入中文库表名");
		return;
	}
	if($('#tableName').val()=='')
	{
		alert("请输入英文表名");
		return;
	}
	var pattern = /[a-z]|[A-Z]|[0-9]$/;
	if (!pattern.test($('#tableName').val()))
	{
		alert("请输入正确的英文表名");
		return;
	}
	//校验资源项信息
	$('#resourceList').find("input").each(function(){
		var thisId = $(this).attr("id");
		alert(thisId.val());
		if(thisId.val() == ""){
			alert("资源项属性不能为空！");
			return;
		}
	});
	if(confirm("确定要保存吗？")){
			if(type == '1'){  
				var a='${obj.resources.resourceId}';
				$('#mainForm').attr('action','${ctx }/zymlgl/saveBm?opType=1');
				$("#mainForm").submit();
			}
			if(type == '2'){  //提交审核
				$("#mainForm").submit();
			}
	}
}
//暂存
function tempStorage(){
	checkInfo('1');
}

//提交审核
function submitAudit(){
	checkInfo('2');
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px;"><b>在线编目</b></p>
<form id="mainForm" name="mainForm" action="${ctx }/zymlgl/saveBm" method="post" >
<input type="hidden" id="" name="resources.resourceId" value="${obj.resources.resourceId}">
<input type="hidden" id="" name="tableName" value="${obj.resources.tableName}">
<input type="hidden" id="" name="tableChinese" value="${obj.resources.tableChinese}">
<input type="hidden" id="" name="resources.provideDepId" value="${obj.dept}">
<table width="90%" class="table_multilist" align="center">
	<tr>
		<td width="15%" class="label_1">资源目录名称<span class="red"> *</span></td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="resourceName" name="resources.resourceName" value="${obj.resources.resourceName}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">摘要</td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="abstractWord" name="resources.abstractWord" value="${obj.resources.abstractWord}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">信息系统名称<span class="red"> *</span></td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="infoSysName" name="resources.infoSysName" value="${obj.resources.infoSysName}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">系统简介</td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="sysAbstract" name="resources.sysAbstract" value="${obj.resources.sysAbstract}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">信息系统链接<span class="red"> *</span></td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="sysUrl" name="resources.sysUrl" value="${obj.resources.sysUrl}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">检索关键字<span class="red"> *</span></td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="keyWord" name="resources.keyWord" value="${obj.resources.keyWord}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td >
	</tr>
	<tr>
		<td width="15%" class="label_1">提供科室<span class="red"> *</span></td>
		<td width="85%" colspan="6" class="label_2">
		&nbsp;<input type="text" class="dfinput" style="width:98%" id="keyWord" name="resources.provideDepartment" value="${obj.resources.provideDepartment}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">业务联系人<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="2">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="busLinkman" name="resources.busLinkman" value="${obj.resources.busLinkman}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		</td>
		<td width="15%" class="label_1">联系电话<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="3">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="busLinkmanPhone" name="resources.busLinkmanPhone" value="${obj.resources.busLinkmanPhone}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		</td>		
	</tr>
	<tr>
		<td class="label_1">对接联系人<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="2">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="jointLinkman" name="resources.jointLinkman" value="${obj.resources.jointLinkman}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		</td>
		<td width="15%" class="label_1">联系电话<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="3">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="jointLinkmanPhone" name="resources.jointLinkmanPhone" value="${obj.resources.jointLinkmanPhone}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">对接方式<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="2">
		&nbsp;<wd:select name="resources.jointType" id="jointType" dicCode="1064" initValue="---请选择---" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.resources.jointType}"/>
		</td>
		<td width="15%" class="label_1">资源更新频度<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="3">
		&nbsp;<wd:select name="resources.updateRate" id="updateRate" dicCode="1063" initValue="---请选择---" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.resources.updateRate}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">所属资源类型<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="2">
		<c:if test="${obj.loginName == 'admin'}">
			&nbsp;<wd:select name="resources.resourceType" id="resourceType" dicCode="1054" initValue="---请选择---" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.resources.resourceType}"/>
		</c:if>
		<c:if test="${obj.loginName != 'admin'}">
			&nbsp;<input type="hidden" id="resourceType" name="resources.resourceType" value="r_ztl">专题类	 		
		</c:if>
		</td>
		<td width="15%" class="label_1">资源公开属性<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="3">
		&nbsp;<wd:select name="resources.openProperty" id="r_openProperty" dicCode="1057" initValue="---请选择---" className="selectInput" style="width:175px;height:25px;" defaultValue="${obj.resources.openProperty}"/>
		</td>
	</tr>
	<tr>
		<td class="label_1">数据库中文表名<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="2">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="tableChinese" name="resources.tableChinese" value="${obj.resources.tableChinese}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		</td>
		<td width="15%" class="label_1">数据库表名<span class="red"> *</span></td>
		<td width="35%" class="label_2" colspan="3">
		&nbsp;<input type="text" class="dfinput" style="width:95%" id="tableName" name="resources.tableName" value="${obj.resources.tableName}"  placeholder="请输入英文名" />
		</td>
	</tr>
</table><br>
<!-- 资源项 -->
<table width="90%" class="table_multilist" align="center" id="maintable">
	<tr>
		<td class="label_1" width="15%" style="text-align: center;height: 50px;">资源项名称<span class="red"> *</span></td>
		<td class="label_1" width="12%" style="text-align: center;height: 50px;">数据库字段名(英文)<span class="red"> *</span></td>
		<td class="label_1" width="10%" style="text-align: center;">数据类型<span class="red"> *</span></td>
		<td class="label_1" width="15%" style="text-align: center;height: 50px;">备注</td>
		<td class="label_1" width="10%" style="text-align: center;height: 50px;">
			共享属性<span class="red"> *</span>
			<label style="margin-left:10px;"><wd:select name="" id="shareProperty_public" dicCode="1056" initValue="--请选择--" className="selectInput" defaultValue="${resource.shareProperty}" onchange="changeShareValue(this.value)"/></label>
		</td>
		<td class="label_1" width="10%" style="text-align: center;height: 50px;">
			公开属性<span class="red"> *</span>
			<label style="margin-left:10px;"><wd:select name="" id="openProperty_public" dicCode="1057" initValue="--请选择--" className="selectInput" defaultValue="${resource.openProperty}" onchange="changeOpenValue(this.value)"/></label>
		</td>
		<td class="label_1" width="18%" style="text-align: center;height: 50px;">不予共享/公开理由</td>
		<td class="label_1" width="5%" align="left"><input type="button" value="+" onclick="addRow()"/></td>
	</tr>
	<!-- 克隆模板 -->
	<tr id="branchTr" style="display:none;">
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="dataItemName_0" name="resourceDetailList[0].dataItemName" value="${resource.dataItemName}"/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="fieldCode_0" name="resourceDetailList[0].fieldCode" value="${resource.fieldCode}"/>
			</td>
			<td class="label_2" width="10%" style="text-align: center;">
				<wd:select name="resourceDetailList[0].dataItemType" id="dataItemType_0" dicCode="1055" initValue="--请选择--" className="selectInput" style="width:98%" defaultValue="${resource.dataItemType}"/>
			</td>
			<td class="label_2" width="15%" style="text-align: center;"><input type="text" class="dfinput" style="width:90%" id="memo" name="resourceDetailList[0].memo" value="${resource.memo}"/></td>
			<td class="label_2" width="12%" style="text-align: center;">
				<wd:select name="resourceDetailList[0].shareProperty" id="shareProperty_0" dicCode="1056" initValue="--请选择--" className="selectInput" style="width:98%" defaultValue="${resource.shareProperty}"/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<wd:select name="resourceDetailList[0].openProperty" id="openProperty_0" dicCode="1057" initValue="--请选择--" className="selectInput" style="width:98%" defaultValue="${resource.openProperty}"/>
			</td>
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="noOpenReason_0" name="resourceDetailList[0].noOpenReason" value="${resource.noOpenReason}"/>
			</td>
			<td class="label_2" width="5%" style="text-align: center;"><input type="button" value="删除" onclick="deleteRow(this)"/></td>
		</tr>
	<c:forEach items="${obj.resourceDetailList}" var="resource" varStatus="row">
		<tr>
			<td class="label_2" width="12%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="dataItemName_0" name="resourceDetailList[${row.index+1}].dataItemName" value="${resource.dataItemName}"/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="fieldCode_0" name="resourceDetailList[${row.index+1}].fieldCode" value="${resource.fieldCode}"/>
			</td>
			<td class="label_2" width="10%" style="text-align: center;">
				<wd:select name="resourceDetailList[${row.index+1}].dataItemType" id="dataItemType_0" dicCode="1055" initValue="---请选择---" className="selectInput" style="width:98%" defaultValue="${resource.dataItemType}"/>
			</td>
			<td class="label_2" width="15%" style="text-align: center;"><input type="text" class="dfinput" style="width:90%" id="memo_0" name="resourceDetailList[${row.index+1}].memo" value="${resource.memo}"/></td>
			<td class="label_2" width="12%" style="text-align: center;">
				<wd:select name="resourceDetailList[${row.index+1}].shareProperty" id="shareProperty_0" dicCode="1056" initValue="---请选择---" className="selectInput" style="width:98%" defaultValue="${resource.shareProperty}"/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<wd:select name="resourceDetailList[${row.index+1}].openProperty" id="openProperty_0" dicCode="1057" initValue="---请选择---" className="selectInput" style="width:98%" defaultValue="${resource.openProperty}"/>
			</td>
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" class="dfinput" style="width:90%" id="noOpenReason_0" name="resourceDetailList[${row.index+1}].noOpenReason" value="${resource.noOpenReason}"/>
			</td>
			<td class="label_2" width="5%" style="text-align: center;">
				<input type="hidden" name="resourceDetailList[${row.index+1}].detailsId" value="${resource.detailsId}"/>
				<input type="button" value="删除" onclick="deleteRow(this)"/>
			</td>
		</tr>
	</c:forEach>
</table>


<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<!--<c:if test="${obj.resources.status eq '10713' || resource.status eq '10711'}">
		<input type="button" id="button"  class="button" value="暂　存" onclick="tempStorage();" /> 
		</c:if>
		--><input type="button" id="button"  class="button" value="暂　存" onclick="tempStorage();" /> 
		<input type="button" id="button"  class="button" value="提   交" onclick="submitAudit();" /> 
</div>
</form>

<script>
var index = "${fn:length(obj.resourceDetailList)}";
function addRow(obj) {
	index++;
	var listName = "resourceDetailList[0]";
	var cloneTr = $("#branchTr").clone();
	$(cloneTr).show();
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"resourceDetailList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"resourceDetailList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
	});
	$(cloneTr).appendTo($("#maintable"));	
	
	}
    function deleteRow(obj) {
		if(confirm("确定删除吗？")){
			$(obj).parent().parent().remove();
		}
	}
	
</script>
</body>
</html>