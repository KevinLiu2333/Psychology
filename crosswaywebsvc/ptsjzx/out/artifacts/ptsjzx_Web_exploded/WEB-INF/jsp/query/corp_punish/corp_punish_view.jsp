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
<meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
<style type="text/css">
#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;background-color:rgb(244,242,239);}
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
        <li id="one1" onclick="setTab('one',1,4)" class="active"><a href="javascript:void(0)">法人登记信息</a></li>
        <li id="one2" onclick="setTab('one',2,4)"><a href="javascript:void(0)">法人资质信息</a></li>
        <li id="one3" onclick="setTab('one',3,4)"><a href="javascript:void(0)">法人处罚信息</a></li>
        <li id="one4" onclick="setTab('one',4,4)"><a href="javascript:void(0)">GIS</a></li>
    </ul></td>
  </tr>
   <tr>
    <td valign="top" id="info_list">
    	<div id="con_one_1">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
				<tr>
         			 <td width="15%" class="info_title_aa">法人名称</td>
        			 <td width="35%" bgcolor="#FFFFFF">${obj.corp.corpname }</td>
       			     <td width="15%" class="info_title_aa">组织机构代码</td>
       				 <td width="35%" bgcolor="#FFFFFF">${obj.corp.organcode }</td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">法人类型</td>
        			 <td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1052" dicCode="${obj.corp.corptype }"/></td>
       			     <td width="15%" class="info_title_bb">法定代表人</td>
       				 <td width="35%" bgcolor="#FFFFFF">${obj.corp.personname }</td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_aa">经营场所</td>
        			 <td width="35%" bgcolor="#FFFFFF">${obj.corp.address }</td>
        			 <td width="15%" class="info_title_aa">法人状态</td>
        			<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1041" dicCode="${obj.corp.corpstatus }"/></td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">邮编</td>
        			 <td width="35%" bgcolor="#FFFFFF">${obj.corp.zip }</td>
       			     <td width="15%" class="info_title_bb">联系电话</td>
       				 <td width="35%" bgcolor="#FFFFFF">${obj.corp.telephone }</td>
        		</tr>
        		<tr>
        			 <td width="15%" class="info_title_aa">统一社会信用代码</td>
       			     <td width="35%" bgcolor="#FFFFFF">${obj.corp.uniscid }</td>
         			 <td width="15%" class="info_title_aa">成立日期</td>
        			 <td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.establishdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">币种</td>
        			 <td width="35%" bgcolor="#FFFFFF">${obj.corp.currency }</td>
       			     <td width="15%" class="info_title_bb">开办资金(万)</td>
       				 <td width="35%" bgcolor="#FFFFFF">
       				 	<fmt:formatNumber type="number" value="${obj.corp.regcapital }" maxFractionDigits="0"/>
       				 </td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">业务范围</td>
       				 <td width="35%" bgcolor="#FFFFFF" colspan="3">${obj.corp.businessscope }</td>
        			
        		</tr>
        		
        		
        		<tr>
        			<td width="15%" class="info_title_bb">行业类别</td>
        			<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1049" dicCode="${obj.corp.industrycode}"/></td>
        			<td width="15%" class="info_title_bb">业务主管单位</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.organizers }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">经费来源</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.fundingsrc }</td>
        			<td width="15%" class="info_title_aa">营业执照注册号</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.regno }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">法人注销原因</td>
        			<td width="35%" bgcolor="#FFFFFF"> ${obj.corp.repealreason }</td>
        			<td width="15%" class="info_title_bb">法人变更登记事项</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.changeitem }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">法人注销日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.repealdate }" pattern="yyyy年MM月dd日"/></td>
        			<td width="15%" class="info_title_aa">法人变更日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.changedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">分支机构数（社会组织）</td>
        			<td width="35%" bgcolor="#FFFFFF">
        			<fmt:formatNumber type="number" value="${obj.corp.branchnum }" maxFractionDigits="0"/>
        			</td>
        			<td width="15%" class="info_title_bb">代表机构数（社会组织）</td>
        			
        			<td width="35%" bgcolor="#FFFFFF">
        			<fmt:formatNumber type="number" value="${obj.corp.representnum }" maxFractionDigits="0"/>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">登记类业务发布时间</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.regupddate }" pattern="yyyy年MM月dd日"/></td>
        			<td width="15%" class="info_title_aa">纳税人识别号</td>
        			<td width="35%" bgcolor="#FFFFFF"> ${obj.corp.taxpayerscode }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">组合位置编码</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.taxcode }</td>
        			<td width="15%" class="info_title_bb">税务登记日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxregdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">税务变更内容</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.taxchgecontent }</td>
        			<td width="15%" class="info_title_aa">税务变更日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxchgedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务注销原因</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.taxrepealreason }</td>
        			<td width="15%" class="info_title_bb">税务注销日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxrepealdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">实际经营地址</td>
        			<td width="35%" bgcolor="#FFFFFF" colspan="3">${obj.corp.businessaddress }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">税务注销机关</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.taxrepealorgan }</td>
        			<td width="15%" class="info_title_bb">是否工商联</td>
        			<td width="35%" bgcolor="#FFFFFF">${obj.corp.isgsl }</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">税务类业务发布时间</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.taxupddate }" pattern="yyyy年MM月dd日"/></td>
        			<td width="15%" class="info_title_aa">组织机构代码赋码日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.organcodedate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">组织机构代码变更日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.orgcodechgdate }" pattern="yyyy年MM月dd日"/></td>
        			<td width="15%" class="info_title_bb">组织机构代码注销日期</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.orgcoderepealdate }" pattern="yyyy年MM月dd日"/></td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">质监类业务发布时间</td>
        			<td width="35%" bgcolor="#FFFFFF"><fmt:formatDate value="${obj.corp.qsupddate }" pattern="yyyy年MM月dd日"/></td>
        			<td width="15%" class="info_title_aa">是否自贸区</td>
        			<td width="35%" bgcolor="#FFFFFF"><wd:dicvalue dicId="1040" dicCode="${obj.corp.iszmq }"/></td>
        		</tr>
			</table>    	
			<div>&nbsp;</div>
			<div align="center"><input type="button" class="minButton" value="导出" style="width:100px" onclick="exportexcel()" /></div>
			<div>&nbsp;</div>
    	</div>
    	<div id="con_one_2" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/query/toCorpLicenseList?corpinfoid=${obj.corp.corpinfoid}"></iframe></div>
   		<div id="con_one_3" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/query/toPunishNoteEntyList?corpinfoid=${obj.corp.corpinfoid}"></iframe></div>
   		<div id="con_one_4" style="display:none;">
   			<iframe width="100%" height="600px" src="${ctx}/query/gis/getCropgis?corpinfoid=${obj.corp.corpinfoid}"></iframe>
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
      $("#usual1 ul").idTabs(); 
function exportexcel(){
	window.location.href='${ctx}/query/exportcorpinfo?corpinfoid=${obj.corp.corpinfoid}';
}
$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});
</script>
</html>