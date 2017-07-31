<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/dateUtils.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css">
<script type="text/javascript"
	src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx }/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<link href="${ctx}/tiles/Validform/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/skins/result/style.css" rel="stylesheet"
	type="text/css" />
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
        <li id="one1" onclick="setTab('one',1,3)" class="active"><a href="javascript:void(0)">法人处罚信息</a></li>
    </ul></td>
    </tr><tr>
    	<td valign="top" id="info_list">
    		<div id="con_one_1">
    			<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    				<tr>
         			 	<td width="15%" class="info_title_aa">法人名称</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.corpname!='null'&&obj.punishNoteEnty.corpname!='NULL' }">
        			 	${obj.punishNoteEnty.corpname }</c:if></td>
       			     	<td width="15%" class="info_title_aa">法人地址</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.corpaddress!='null'&&obj.punishNoteEnty.corpaddress!='NULL' }">
       				 	${obj.punishNoteEnty.corpaddress }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">组织机构代码</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.organcode!='null'&&obj.punishNoteEnty.organcode!='NULL' }">
        			 	${obj.punishNoteEnty.organcode }</c:if></td>
       			     	<td width="15%" class="info_title_bb">证据</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.illegalevidence!='null'&&obj.punishNoteEnty.illegalevidence!='NULL' }">
       				 	${obj.punishNoteEnty.illegalevidence }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">违反规定</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.illegalrule!='null'&&obj.punishNoteEnty.illegalrule!='NULL' }">
        			 	${obj.punishNoteEnty.illegalrule }</c:if></td>
       			     	<td width="15%" class="info_title_aa">有效性</td>
       				 	<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1065" dicCode="${obj.punishNoteEnty.validity }"/></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">违法事实</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.illegalcontext!='null'&&obj.punishNoteEnty.illegalcontext!='NULL' }">
        			 	${obj.punishNoteEnty.illegalcontext }</c:if></td>
       			     	<td width="15%" class="info_title_bb">违法地点</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.illegaladdress!='null'&&obj.punishNoteEnty.illegaladdress!='NULL' }">
       				 	${obj.punishNoteEnty.illegaladdress }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">违法时间</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.punishNoteEnty.illegaldate }" pattern="yyyy年MM月dd日"/></td>
       			     	<td width="15%" class="info_title_aa">联系电话</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.telephone!='null'&&obj.punishNoteEnty.telephone!='NULL' }">
       				 	${obj.punishNoteEnty.telephone }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">邮编</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.zip!='null'&&obj.punishNoteEnty.zip!='NULL' }">
        			 	${obj.punishNoteEnty.zip }</c:if></td>
       			     	<td width="15%" class="info_title_bb">法定代表人</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.personname!='null'&&obj.punishNoteEnty.personname!='NULL' }">
       				 	${obj.punishNoteEnty.personname }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">职务</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.persontitle!='null'&&obj.punishNoteEnty.persontitle!='NULL' }">
        			 	${obj.punishNoteEnty.persontitle }</c:if></td>
       			     	<td width="15%" class="info_title_aa">登记号（中文）</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.regno!='null'&&obj.punishNoteEnty.regno!='NULL' }">
       				 	${obj.punishNoteEnty.regno }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">标识</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.entityid!='null'&&obj.punishNoteEnty.entityid!='NULL' }">
        			 	${obj.punishNoteEnty.entityid }</c:if></td>
       			     	<td width="15%" class="info_title_bb">税务代码</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.taxcode!='null'&&obj.punishNoteEnty.taxcode!='NULL' }">
       				 	${obj.punishNoteEnty.taxcode }</c:if></td>
        			</tr>
        			<tr>
       			     	<td width="15%" class="info_title_aa">受处罚的单位和集体</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.teamname!='null'&&obj.punishNoteEnty.teamname!='NULL' }">
       				 	${obj.punishNoteEnty.teamname }</c:if></td>
       				 	<td width="15%" class="info_title_aa">履罚期限</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.punishlimit!='null'&&obj.punishNoteEnty.punishlimit!='NULL' }">
       				 	${obj.punishNoteEnty.punishlimit }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">单位性质</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.unitproperty!='null'&&obj.punishNoteEnty.unitproperty!='NULL' }">
        			 	${obj.punishNoteEnty.unitproperty }</c:if></td>
       			     	<td width="15%" class="info_title_bb">处罚依据</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.punishbasis!='null'&&obj.punishNoteEnty.punishbasis!='NULL' }">
       				 	${obj.punishNoteEnty.punishbasis }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">处罚机关</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.punishunit!='null'&&obj.punishNoteEnty.punishunit!='NULL' }">
        			 	${obj.punishNoteEnty.punishunit }</c:if></td>
       			     	<td width="15%" class="info_title_aa">违法地点区划</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.areacode!='null'&&obj.punishNoteEnty.areacode!='NULL' }">
       				 	${obj.punishNoteEnty.areacode }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">处罚通知书编号</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.punishcode!='null'&&obj.punishNoteEnty.punishcode!='NULL' }">
        			 	${obj.punishNoteEnty.punishcode }</c:if></td>
       			     	<td width="15%" class="info_title_bb">处罚内容</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.punishcontent!='null'&&obj.punishNoteEnty.punishcontent!='NULL' }">
       				 	${obj.punishNoteEnty.punishcontent }</c:if></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_aa">处罚部门</td>
        			 	<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1064" dicCode="${obj.punishNoteEnty.fromdeptid }"/></td>
       			     	<td width="15%" class="info_title_aa">处罚决定时间</td>
       				 	<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.punishNoteEnty.punishdate }" pattern="yyyy年MM月dd日"/></td>
        			</tr>
        			<tr>
         			 	<td width="15%" class="info_title_bb">通告原因</td>
        			 	<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.punishNoteEnty.announcereason!='null'&&obj.punishNoteEnty.announcereason!='NULL' }">
        			 	${obj.punishNoteEnty.announcereason }</c:if></td>
       			     	<td width="15%" class="info_title_bb">处罚措施</td>
       				 	<td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.punishNoteEnty.punishmeasures!='null'&&obj.punishNoteEnty.punishmeasures!='NULL' }">
       				 	${obj.punishNoteEnty.punishmeasures }</c:if></td>
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