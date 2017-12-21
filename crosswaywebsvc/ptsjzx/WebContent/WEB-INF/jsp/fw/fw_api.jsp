<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀数据中心</title>
<link href="${ctx }/skins/index/css/style.css" rel="stylesheet">
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/json.css">
</head>
<script type="text/javascript">

	$(document).ready(function(){
		$('#corpLicenseSelect').hide();
		$('#corpInfoSelect').hide();
		});
	function changeType(){
		if($('#apiType').val() == "frdjInfo"){
			$('#corpInfoSelect').show();
			$('#corpLicenseSelect').hide();
		}else if($('#apiType').val() == "frzzInfo"){
			$('#corpInfoSelect').hide();
			$('#corpLicenseSelect').show();
		}else if($('#apiType').val() == "frjgInfo"){
			$('#corpLicenseSelect').hide();
			$('#corpInfoSelect').hide();
			alert("暂无法人监管信息服务!");
		}else{
			$('#corpLicenseSelect').hide();
			$('#corpInfoSelect').hide();
		}
	}
	//返回 
	function fanhui(){
	  	var openerUrl = '${obj.openerUrl}';
	  	if(openerUrl == ''){
	  		history.go(-1);
	  	}else{
	  		$('#fanhuiForm').submit();
	  	}
  	}
	//下一步
	function next(openId){
		if($('#apiType').val() == ""){
			alert("请选择服务类型!");
			return;
		}
		var box = document.getElementsByName("corpInfo");
		var objArray = box.length;
		var chestr="";
		
		for(var i=0;i<objArray;i++){
			if(box[i].checked == true){
				chestr += box[i].value+",";
			} 
		}
		if(chestr == "" || chestr.length == 0){
			alert("请勾选需要的服务项!");
			return;
		}
		//var hApiType = $('#apiType').val();
		chestr = chestr.substring(0, chestr.length-1);
		href = "${ctx }/apifw/open?openId="+openId+"&chestr="+chestr+"&apiType="+$('#apiType').val();
		window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
	}
</script>
<body style="height: 800px;background: url(${ctx}/skins/images/white_bg.gif);">
<form id="mainForm" name="mainForm" action="${ctx}/fw/savefw" method="post">
	<table width="80%" height=100% class="table_multilist" border="1" cellspacing="0" align="center">
		<tr>
			<td class="input_title" align="center" colspan="2">api申请</td>
		</tr>
		<tr>
   			<td width="25%" align="right">统计类型：</td>
   			<td align="left" colspan="3">&nbsp;
   				<select id="apiType" name="apiType" onchange="changeType()">
   					<option value="">----------请选择申请类型----------</option>
   					<option value="frdjInfo">法人登记信息</option>
					<option value="frzzInfo">法人资质信息</option>
					<option value="frjgInfo">法人监管信息</option>
   				</select>
   			</td>
   		</tr>
	</table>
	
	<!-- 法人登记信息 -->
	<table id="corpInfoSelect" width="80%" class="table_multilist" border="1" cellspacing="0" align="center">
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="organCode" name="corpInfo" id="organCode"
				<c:if test="">checked="checked"</c:if> />组织机构代码</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="entityId" name="corpInfo" id="entityId"
				<c:if test="">checked="checked"</c:if> />标识</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="corpName" name="corpInfo" id="corpName"
				<c:if test="">checked="checked"</c:if> />法人名称</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="corpType" name="corpInfo" id="corpType"
				<c:if test="">checked="checked"</c:if> />法人类型</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personName" name="corpInfo" id="personName"
				<c:if test="">checked="checked"</c:if> />法定代表人</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="address" name="corpInfo" id="address"
				<c:if test="">checked="checked"</c:if> />经营场所</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="areaCode" name="corpInfo" id="areaCode"
				<c:if test="">checked="checked"</c:if> />区划</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="zip" name="corpInfo" id="zip"
				<c:if test="">checked="checked"</c:if> />邮编编码</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="telephone" name="corpInfo" id="telephone"
				<c:if test="">checked="checked"</c:if> />联系电话</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="establelishDate" name="corpInfo" id="establelishDate"
				<c:if test="">checked="checked"</c:if> />成立日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regCapital3" name="corpInfo" id="regCapital3"
				<c:if test="">checked="checked"</c:if> />开办资金</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="currency" name="corpInfo" id="currency"
				<c:if test="">checked="checked"</c:if> />币种</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessScope" name="corpInfo" id="businessScope"
				<c:if test="">checked="checked"</c:if> />业务范围</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personCertType" name="corpInfo" id="personCertType"
				<c:if test="">checked="checked"</c:if> />法定代表人证件类型</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personCertCode" name="corpInfo" id="personCertCode"
				<c:if test="">checked="checked"</c:if> />法定代表人证件号</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="industryCode" name="corpInfo" id="industryCode"
				<c:if test="">checked="checked"</c:if> />行业类别</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="organizers" name="corpInfo" id="organizers"
				<c:if test="">checked="checked"</c:if> />业务主管单位</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundingSrc" name="corpInfo" id="fundingSrc"
				<c:if test="">checked="checked"</c:if> />经费来源</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regNo" name="corpInfo" id="regNo"
				<c:if test="">checked="checked"</c:if> />注册号</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessScope" name="corpInfo" id="businessScope"
				<c:if test="">checked="checked"</c:if> />业务范围</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="receivingOrgan" name="corpInfo" id="receivingOrgan"
				<c:if test="">checked="checked"</c:if> />受理机关代码</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="repealReason" name="corpInfo" id="repealReason"
				<c:if test="">checked="checked"</c:if> />法人注销原因</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="repealDate" name="corpInfo" id="repealDate"
				<c:if test="">checked="checked"</c:if> />法人注销日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="changeDate" name="corpInfo" id="changeDate"
				<c:if test="">checked="checked"</c:if> />法人变更日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="changeItem" name="corpInfo" id="changeItem"
				<c:if test="">checked="checked"</c:if> />法人变更登记事项</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="repealOrgan" name="corpInfo" id="repealOrgan"
				<c:if test="">checked="checked"</c:if> />法人注销机关</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="branchNum" name="corpInfo" id="branchNum"
				<c:if test="">checked="checked"</c:if> />分支机构数（社会组织）</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="representNum" name="corpInfo" id="representNum"
				<c:if test="">checked="checked"</c:if> />代表机构数（社会组织）</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regUpdDate" name="corpInfo" id="regUpdDate"
				<c:if test="">checked="checked"</c:if> />登记类业务发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxpayersCode" name="corpInfo" id="taxpayersCode"
				<c:if test="">checked="checked"</c:if> />纳税人识别号</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxCode" name="corpInfo" id="taxCode"
				<c:if test="">checked="checked"</c:if> />组合位置编码</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxRegDate" name="corpInfo" id="taxRegDate"
				<c:if test="">checked="checked"</c:if> />税务登记日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxChgeContent" name="corpInfo" id="taxChgeContent"
				<c:if test="">checked="checked"</c:if> />税务变更内容</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxChgeDate" name="corpInfo" id="taxChgeDate"
				<c:if test="">checked="checked"</c:if> />税务变更日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxRepealReason" name="corpInfo" id="taxRepealReason"
				<c:if test="">checked="checked"</c:if> />税务注销原因</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxRepealDate" name="corpInfo" id="taxRepealDate"
				<c:if test="">checked="checked"</c:if> />税务注销日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxRepealOrgan" name="corpInfo" id="taxRepealOrgan"
				<c:if test="">checked="checked"</c:if> />税务注销机关</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessAddress" name="corpInfo" id="businessAddress"
				<c:if test="">checked="checked"</c:if> />实际经营地址</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="taxUpdDate" name="corpInfo" id="taxUpdDate"
				<c:if test="">checked="checked"</c:if> />税务类业务发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="organcodeDate" name="corpInfo" id="organcodeDate"
				<c:if test="">checked="checked"</c:if> />组织机构代码赋码日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="orgcodeChgdate" name="corpInfo" id="orgcodeChgdate"
				<c:if test="">checked="checked"</c:if> />组织机构代码变更日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="orgcodeRepealdate" name="corpInfo" id="orgcodeRepealdate"
				<c:if test="">checked="checked"</c:if> />组织机构代码注销日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="qsUpdDate" name="corpInfo" id="qsUpdDate"
				<c:if test="">checked="checked"</c:if> />质监类业务发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="bdResult" name="corpInfo" id="bdResult"
				<c:if test="">checked="checked"</c:if> />比对结果</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="qykId" name="corpInfo" id="qykId"
				<c:if test="">checked="checked"</c:if> />qykId</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="updTime" name="corpInfo" id="updTime"
				<c:if test="">checked="checked"</c:if> />更新日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="trimCorpName" name="corpInfo" id="trimCorpName"
				<c:if test="">checked="checked"</c:if> />trimCorpName</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsCode" name="corpInfo" id="fundsCode"
				<c:if test="">checked="checked"</c:if> />公积金账号</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsOpenDate" name="corpInfo" id="fundsOpenDate"
				<c:if test="">checked="checked"</c:if> />公积金开户日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsRepealDate" name="corpInfo" id="fundsRepealDate"
				<c:if test="">checked="checked"</c:if> />公积金注销日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsUpdDate" name="corpInfo" id="fundsUpdDate"
				<c:if test="">checked="checked"</c:if> />公积金信息发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsaddCode" name="corpInfo" id="fundsaddCode"
				<c:if test="">checked="checked"</c:if> />补充公积金账号</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsaddOpenDate" name="corpInfo" id="fundsaddOpenDate"
				<c:if test="">checked="checked"</c:if> />补充公积金开户日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsaddRepealDate" name="corpInfo" id="fundsaddRepealDate"
				<c:if test="">checked="checked"</c:if> />补充公积金注销日期</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="fundsaddUpdDate" name="corpInfo" id="fundsaddUpdDate"
				<c:if test="">checked="checked"</c:if> />补充公积金信息发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="socialSecurityCode" name="corpInfo" id="socialSecurityCode"
				<c:if test="">checked="checked"</c:if> />社会保险账号</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="socialSecurityOpenDate" name="corpInfo" id="socialSecurityOpenDate"
				<c:if test="">checked="checked"</c:if> />社会保险帐号注销日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="socialSecurityRepealDate" name="corpInfo" id="socialSecurityRepealDate"
				<c:if test="">checked="checked"</c:if> />社保信息发布时间</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="socialSecurityUpdDate" name="corpInfo" id="socialSecurityUpdDate"
				<c:if test="">checked="checked"</c:if> />社会保险帐号开设日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="lkStatus" name="corpInfo" id="lkStatus"
				<c:if test="">checked="checked"</c:if> />离开状态</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="isZmq" name="corpInfo" id="isZmq"
				<c:if test="">checked="checked"</c:if> />是否自贸区</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="insertTime" name="corpInfo" id="insertTime"
				<c:if test="">checked="checked"</c:if> />插入时间</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="corpStatus" name="corpInfo" id="corpStatus"
				<c:if test="">checked="checked"</c:if> />法人状态</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="isGsl" name="corpInfo" id="isGsl"
				<c:if test="">checked="checked"</c:if> />是否工商联</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="isRecentlyZmq" name="corpInfo" id="isRecentlyZmq"
				<c:if test="">checked="checked"</c:if> />isRecentlyZmq</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="isWebSend" name="corpInfo" id="isWebSend"
				<c:if test="">checked="checked"</c:if> />isWebSend</td>
		</tr>
		
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="uniScId" name="corpInfo" id="uniScId"
				<c:if test="">checked="checked"</c:if> />统一社会信用代码</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regCapital" name="corpInfo" id="regCapital"
				<c:if test="">checked="checked"</c:if> />regCapital</td>
		</tr>
	</table><br/>
	
	<!-- 法人资质 -->
	<table id="corpLicenseSelect" width="80%" class="table_multilist" border="1" cellspacing="0" align="center">
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseId" name="corpInfo" id="licenseId"
				<c:if test="">checked="checked"</c:if> />数据中心许可证唯一序号</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="corpInfoId" name="corpInfo" id="corpInfoId"
				<c:if test="">checked="checked"</c:if> />数据中心法人实体序号</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="organCode" name="corpInfo" id="organCode"
				<c:if test="">checked="checked"</c:if> />组织结构代码</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseType" name="corpInfo" id="licenseType"
				<c:if test="">checked="checked"</c:if> />资质类型</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="uniqueCode" name="corpInfo" id="uniqueCode"
				<c:if test="">checked="checked"</c:if> />资质信息委办居唯一标识</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="bureauCode" name="corpInfo" id="bureauCode"
				<c:if test="">checked="checked"</c:if> />委办局编号</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseStatus" name="corpInfo" id="licenseStatus"
				<c:if test="">checked="checked"</c:if> />许可证号状态</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseCode" name="corpInfo" id="licenseCode"
				<c:if test="">checked="checked"</c:if> />许可证号(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseStatus" name="corpInfo" id="licenseStatus"
				<c:if test="">checked="checked"</c:if> />许可证号状态</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseCode" name="corpInfo" id="licenseCode"
				<c:if test="">checked="checked"</c:if> />许可证号(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseDate" name="corpInfo" id="licenseDate"
				<c:if test="">checked="checked"</c:if> />发证日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitName" name="corpInfo" id="unitName"
				<c:if test="">checked="checked"</c:if> />单位名称(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="statDate" name="corpInfo" id="statDate"
				<c:if test="">checked="checked"</c:if> />有效日期(起)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="endDate" name="corpInfo" id="endDate"
				<c:if test="">checked="checked"</c:if> />有效日期(止)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personName" name="corpInfo" id="personName"
				<c:if test="">checked="checked"</c:if> />法定代表人(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessScope" name="corpInfo" id="businessScope"
				<c:if test="">checked="checked"</c:if> />经营范围/业务范围(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitAddress" name="corpInfo" id="unitAddress"
				<c:if test="">checked="checked"</c:if> />单位地址(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="economicNature" name="corpInfo" id="economicNature"
				<c:if test="">checked="checked"</c:if> />经济性质(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="timeLimit" name="corpInfo" id="timeLimit"
				<c:if test="">checked="checked"</c:if> />有效期限(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="etpsType" name="corpInfo" id="etpsType"
				<c:if test="">checked="checked"</c:if> />企业类型(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessAddress" name="corpInfo" id="businessAddress"
				<c:if test="">checked="checked"</c:if> />经营地址(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessMethod" name="corpInfo" id="businessMethod"
				<c:if test="">checked="checked"</c:if> />经营方式(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regAddress" name="corpInfo" id="regAddress"
				<c:if test="">checked="checked"</c:if> />注册地址(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitPerson" name="corpInfo" id="unitPerson"
				<c:if test="">checked="checked"</c:if> />负责人/联系人</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="workScope" name="corpInfo" id="workScope"
				<c:if test="">checked="checked"</c:if> />生产范围(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="workAddress" name="corpInfo" id="workAddress"
				<c:if test="">checked="checked"</c:if> />生成地址(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="workMethod" name="corpInfo" id="workMethod"
				<c:if test="">checked="checked"</c:if> />生产方式(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="permissionScope" name="corpInfo" id="permissionScope"
				<c:if test="">checked="checked"</c:if> />许可范围(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="establishDate" name="corpInfo" id="establishDate"
				<c:if test="">checked="checked"</c:if> />成立日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="validityArea" name="corpInfo" id="validityArea"
				<c:if test="">checked="checked"</c:if> />有效区域(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="qualiLevel" name="corpInfo" id="qualiLevel"
				<c:if test="">checked="checked"</c:if> />资质等级(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessCategory" name="corpInfo" id="businessCategory"
				<c:if test="">checked="checked"</c:if> />业务种类(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="qualiLevel" name="corpInfo" id="qualiLevel"
				<c:if test="">checked="checked"</c:if> />资质等级(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="businessCategory" name="corpInfo" id="businessCategory"
				<c:if test="">checked="checked"</c:if> />业务种类(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="manageOrgan" name="corpInfo" id="manageOrgan"
				<c:if test="">checked="checked"</c:if> />主管机关/机关(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="qualityManager" name="corpInfo" id="qualityManager"
				<c:if test="">checked="checked"</c:if> />质量管理人(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="belongUnit" name="corpInfo" id="belongUnit"
				<c:if test="">checked="checked"</c:if> />隶属单位(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="etpsAddress" name="corpInfo" id="etpsAddress"
				<c:if test="">checked="checked"</c:if> />企业地址(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="warehouseAddr" name="corpInfo" id="warehouseAddr"
				<c:if test="">checked="checked"</c:if> />创库地址(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regNo" name="corpInfo" id="regNo"
				<c:if test="">checked="checked"</c:if> />登记号(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="regDate" name="corpInfo" id="regDate"
				<c:if test="">checked="checked"</c:if> />登记日期</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="cdoCategory" name="corpInfo" id="cdoCategory"
				<c:if test="">checked="checked"</c:if> />医疗机构类别(中文)</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="busiUpdTime" name="corpInfo" id="busiUpdTime"
				<c:if test="">checked="checked"</c:if> />业务发布时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="updTime" name="corpInfo" id="updTime"
				<c:if test="">checked="checked"</c:if> />更新日期</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitProperty" name="corpInfo" id="unitProperty"
				<c:if test="">checked="checked"</c:if> />单位性质</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="issueOrgan" name="corpInfo" id="issueOrgan"
				<c:if test="">checked="checked"</c:if> />发证机关</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="permissionContext" name="corpInfo" id="permissionContext"
				<c:if test="">checked="checked"</c:if> />许可内容</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personTitle" name="corpInfo" id="personTitle"
				<c:if test="">checked="checked"</c:if> />法定代表人职务/职称</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="personTel" name="corpInfo" id="personTel"
				<c:if test="">checked="checked"</c:if> />法定代表人电话</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitPersonTitle" name="corpInfo" id="unitPersonTitle"
				<c:if test="">checked="checked"</c:if> />负责人职务/职称</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="unitPersonTel" name="corpInfo" id="unitPersonTel"
				<c:if test="">checked="checked"</c:if> />联系电话</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="zip" name="corpInfo" id="zip"
				<c:if test="">checked="checked"</c:if> />邮政编码</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="otherUnitName" name="corpInfo" id="otherUnitName"
				<c:if test="">checked="checked"</c:if> />涉及单位名称</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="otherOrganCode" name="corpInfo" id="otherOrganCode"
				<c:if test="">checked="checked"</c:if> />涉及单位组织机构代码</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="areaCode" name="corpInfo" id="areaCode"
				<c:if test="">checked="checked"</c:if> />区划(中文)</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="bdResult" name="corpInfo" id="bdResult"
				<c:if test="">checked="checked"</c:if> />对比结果</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="matchResult" name="corpInfo" id="matchResult"
				<c:if test="">checked="checked"</c:if> />匹配结果</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseBdId" name="corpInfo" id="licenseBdId"
				<c:if test="">checked="checked"</c:if> />信息对比序号</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="dataUploadType" name="corpInfo" id="dataUploadType"
				<c:if test="">checked="checked"</c:if> />日期类型上传</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="lkStatus" name="corpInfo" id="lkStatus"
				<c:if test="">checked="checked"</c:if> />离开状态</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="insertTime" name="corpInfo" id="insertTime"
				<c:if test="">checked="checked"</c:if> />插入时间</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="permissionDecisionContext" name="corpInfo" id="permissionDecisionContext"
				<c:if test="">checked="checked"</c:if> />行政许可决定书内容</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="licenseName" name="corpInfo" id="licenseName"
				<c:if test="">checked="checked"</c:if> />证件名称</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="productName" name="corpInfo" id="productName"
				<c:if test="">checked="checked"</c:if> />产品名称</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="productModelNo" name="corpInfo" id="productModelNo"
				<c:if test="">checked="checked"</c:if> />型号</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="productStandard" name="corpInfo" id=productStandard
				<c:if test="">checked="checked"</c:if> />规格</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="productAccuracy" name="corpInfo" id="productAccuracy"
				<c:if test="">checked="checked"</c:if> />准确度</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="validity" name="corpInfo" id=validity
				<c:if test="">checked="checked"</c:if> />有效性</td>
		</tr>
		<tr>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="isWebSend" name="corpInfo" id="isWebSend"
				<c:if test="">checked="checked"</c:if> />isWebSend</td>
			<td align="left" valign="top" width="20%">&nbsp;&nbsp;<input
				type="checkbox" value="uniScId" name="corpInfo" id=uniScId
				<c:if test="">checked="checked"</c:if> />统一社会信用代码</td>
		</tr>
	</table><br/>
	
	<p align="center">
		<input type="button" class="midButton" value="下一步"
			onclick="next('ju')" />
		&nbsp;&nbsp; <input type="button" class="midButton" value="关 闭"
			onclick="window.close()" />
	</p><br/>
	
</form>
</body>
</html>