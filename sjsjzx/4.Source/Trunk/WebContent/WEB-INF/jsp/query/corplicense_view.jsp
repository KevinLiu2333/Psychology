<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/dateUtils.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<link href="${ctx}/tiles/Validform/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/skins/result/style.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.info_title_aa,.info_title_bb {
	text-align:center;
}
.info_title_aa {
	background:#E5F6FD;
	background-size:100%;
}
.info_title_bb {
	background:#FFF;
}
</style>
</head>
<body>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><ul class="tag">
        <li id="one1" onclick="setTab('one',1,3)" class="active"><a href="javascript:void(0)">法人资质信息</a></li>
    </ul></td>
  </tr><tr>
    	<td valign="top" id="info_list">
    		<div id="con_one_1">
    			<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    				<tr>
       			     	<td width="15%" class="info_title_aa">统一社会信用代码</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 		<c:if test="${obj.license.uniscid!='null'&&obj.license.uniscid!='NULL' }">
       				 			${obj.license.uniscid }
       				 	</c:if></td>
        				<td width="15%" class="info_title_aa">单位性质</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 		<c:if test="${obj.license.unitproperty!='null'&&obj.license.unitproperty!='NULL' }">
        			 	${obj.license.unitproperty }
        			 	</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">资质类型</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1060" dicCode="${obj.license.licensetype }"/></td>
       			     	<td width="15%" class="info_title_bb">组织机构代码</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.organcode!='null'&&obj.license.organcode!='NULL' }">
       				 	${obj.license.organcode }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">委办局</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1064" dicCode="${obj.license.bureaucode }"/></td>
       			     	<td width="15%" class="info_title_aa">许可证状态</td>
       				 	<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1043" dicCode="${obj.license.licensestatus }"/></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">许可证号</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><c:if test="${obj.license.licensecode!='null'&&obj.license.licensecode!='NULL' }">${obj.license.licensecode }</c:if></td>
       			     	<td width="15%" class="info_title_bb">发证日期</td>
       				 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.license.licensedata }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">有效日期(起)</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.license.startdate }" pattern="yyyy年MM月dd日"/></td>
       			     	<td width="15%" class="info_title_aa">有效日期(止)</td>
       				 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.license.enddate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">法定代表人</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 		<c:if test="${obj.license.personname!='null'&&obj.license.personname!='NULL' }">
        			 	${obj.license.personname }
        			 	</c:if></td>
       			     	<td width="15%" class="info_title_bb"> 经营范围/业务范围</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.businessscope!='null'&&obj.license.businessscope!='NULL' }">
       				 	${obj.license.businessscope }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">单位地址</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.unitaddress!='null'&&obj.license.unitaddress!='NULL' }">
        			 	${obj.license.unitaddress }</c:if></td>
       			     	<td width="15%" class="info_title_aa">经济性质</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.economicnature!='null'&&obj.license.economicnature!='NULL' }">
       				 	${obj.license.economicnature }
       				 	</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">有效期限</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.timelimit!='null'&&obj.license.timelimit!='NULL' }">
        			 	${obj.license.timelimit }</c:if></td>
       			     	<td width="15%" class="info_title_bb">企业类型</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.etpstype!='null'&&obj.license.etpstype!='NULL' }">
       				 	${obj.license.etpstype }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">经营地址</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.businessaddress!='null'&&obj.license.businessaddress!='NULL' }">
        			 	${obj.license.businessaddress }</c:if></td>
       			     	<td width="15%" class="info_title_aa">经营方式</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.businessmethod!='null'&&obj.license.businessmethod!='NULL' }">
       				 	${obj.license.businessmethod }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">注册地址</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.regaddress!='null'&&obj.license.regaddress!='NULL' }">
        			 	${obj.license.regaddress }</c:if></td>
       			     	<td width="15%" class="info_title_bb">负责人/联系人</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.unitperson!='null'&&obj.license.unitperson!='NULL' }">
       				 	${obj.license.unitperson }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">生产范围</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.workscope!='null'&&obj.license.workscope!='NULL' }">
        			 	${obj.license.workscope }</c:if></td>
       			     	<td width="15%" class="info_title_aa">生产地址</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.workaddress!='null'&&obj.license.workaddress!='NULL' }">
       				 	${obj.license.workaddress }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">生产方式</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.workmethod!='null'&&obj.license.workmethod!='NULL' }">
        			 	${obj.license.workmethod }</c:if></td>
       			     	<td width="15%" class="info_title_bb">许可范围</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.permissionscope!='null'&&obj.license.permissionscope!='NULL' }">
       				 	${obj.license.permissionscope }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">成立日期</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.license.establishdate }" pattern="yyyy年MM月dd日"/></td>
       			     	<td width="15%" class="info_title_aa">有效区域</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.validityarea!='null'&&obj.license.validityarea!='NULL' }">
       				 	${obj.license.validityarea }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">资质等级</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.qualilevel!='null'&&obj.license.qualilevel!='NULL' }">
        			 	${obj.license.qualilevel }</c:if></td>
       			     	<td width="15%" class="info_title_bb">业务种类</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.businesscategory!='null'&&obj.license.businesscategory!='NULL' }">
       				 	${obj.license.businesscategory }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">主管机关/机关</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.manageorgan!='null'&&obj.license.manageorgan!='NULL' }">
        			 	${obj.license.manageorgan }</c:if></td>
       			     	<td width="15%" class="info_title_aa">质量管理人</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.qualitymanager!='null'&&obj.license.qualitymanager!='NULL' }">
       				 	${obj.license.qualitymanager }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">隶属单位</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.belongunit!='null'&&obj.license.belongunit!='NULL' }">
        			 	${obj.license.belongunit }</c:if></td>
       			     	<td width="15%" class="info_title_bb">企业地址</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.etpsaddress!='null'&&obj.license.etpsaddress!='NULL' }">
       				 	${obj.license.etpsaddress }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">仓库地址</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.license.warehouseaddr!='null'&&obj.license.warehouseaddr!='NULL' }">
        			 	${obj.license.warehouseaddr }</c:if></td>
       			     	<td width="15%" class="info_title_aa">登记号</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.regno!='null'&&obj.license.regno!='NULL' }">
       				 	${obj.license.regno }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">登记日期</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.license.regdate }" pattern="yyyy年MM月dd日"/></td>
       			     	<td width="15%" class="info_title_bb">医疗机构类别</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.license.cdocategory!='null'&&obj.license.cdocategory!='NULL' }">
       				 	${obj.license.cdocategory }</c:if></td>
        			</tr>
    			</table>
    		</div>
    	</td>
    </tr>
</table>

</body>
<script type="text/javascript">
function setTab(name,cursel,n){
    for(var i=1;i<=n;i++){
     var menu=document.getElementById(name+i);
     var con=document.getElementById("con_"+name+"_"+i);
     menu.className=i==cursel?"active":"";
     con.style.display=i==cursel?"block":"none";
    }
   }
$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});
</script>
</html>