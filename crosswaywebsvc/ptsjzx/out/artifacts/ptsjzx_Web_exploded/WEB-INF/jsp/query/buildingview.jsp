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
.tag li {
    width:81px;
}
#infolist {
	border:0px solid #9cd0fc;
	}
#infolist td {
	font-size:14px;
	line-height:39px;
	padding:0 10px;
	}

</style>
</head>
<body style="overflow: auto;"> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
   <td valign="top" id="infolist">
    <div id="con_one_1">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
				<tr>
         			 <td width="15%" class="info_title_aa">楼宇名称</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.buildingname!='null'&&obj.build.buildingname!='NULL' }">
        			 		${obj.build.buildingname }
        			 	</c:if>
        			 </td>
       			     <td width="15%" class="info_title_aa">地址</td>
       				 <td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.build.addresscn!='null'&&obj.build.addresscn!='NULL' }">
       				 		${obj.build.addresscn }
       				 	</c:if>
       				 </td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">竣工日期</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 <c:if test="${obj.build.completiontime!='null'&&obj.build.completiontime!='NULL' }">
       				 		${obj.build.completiontime }
       				 	</c:if>
        			 </td>
       			     <td width="15%" class="info_title_bb">邮编</td>
       				 <td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.build.postalcode!='null'&&obj.build.postalcode!='NULL' }">
       				 		${obj.build.postalcode }
       				 	</c:if>
       				 </td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_aa">建筑面积 (㎡)</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.totalacreage!='null'&&obj.build.totalacreage!='NULL' }">
        			 		${obj.build.totalacreage }
        			 	</c:if>
        			 </td>
        			 <td width="15%" class="info_title_aa">楼宇高度（m）</td>
        			<td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.buildingheight!='null'&&obj.build.buildingheight!='NULL' }">
        			 		${obj.build.buildingheight }
        			 	</c:if>
        			 </td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">单层面积 (㎡)</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.businessacreage!='null'&&obj.build.businessacreage!='NULL' }">
        			 		${obj.build.businessacreage }
        			 	</c:if>
        			 </td>
       			     <td width="15%" class="info_title_bb">空置面积（㎡)</td>
       				 <td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.build.occupiedacreage!='null'&&obj.build.occupiedacreage!='NULL' }">
       				 		${obj.build.occupiedacreage }
       				 	</c:if>
       				 </td>
        		</tr>
        		<tr>
        			 <td width="15%" class="info_title_aa">地上层数 (层)</td>
       			     <td width="35%" bgcolor="#FFFFFF">
       			     	<c:if test="${obj.build.groundlayer!='null'&&obj.build.groundlayer!='NULL' }">
       			     		${obj.build.groundlayer }
       			     	</c:if>
       			     </td>
         			 <td width="15%" class="info_title_aa">地下层数 (层)</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.undergroundlayer!='null'&&obj.build.undergroundlayer!='NULL' }">
        			 		${obj.build.undergroundlayer }
        			 	</c:if>	
        			 </td>
        		</tr>
        		<tr>
         			 <td width="15%" class="info_title_bb">地下停车位 (个)</td>
        			 <td width="35%" bgcolor="#FFFFFF">
        			 	<c:if test="${obj.build.undergroundparking!='null'&&obj.build.undergroundparking!='NULL' }">
        			 		${obj.build.undergroundparking }
        			 	</c:if>	
        			 </td>
       			     <td width="15%" class="info_title_bb">地上停车位 (个)</td>
       				 <td width="35%" bgcolor="#FFFFFF">
       				 	<c:if test="${obj.build.pavementparking!='null'&&obj.build.pavementparking!='NULL' }">
        			 		${obj.build.pavementparking }
        			 	</c:if>	
       				 </td>
        		</tr>     		
        		<tr>
        			<td width="15%" class="info_title_aa">小时停车费 (元/小时)</td>
        			<td width="35%" bgcolor="#FFFFFF">
        			<c:if test="${obj.build.hourparkingfeemin!='null'&&obj.build.hourparkingfeemin!='NULL' }">
        				${obj.build.hourparkingfeemin }
        			</c:if>	
        			</td>
        			<td width="15%" class="info_title_aa">月停车费 (元/月)</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.monthparkingfeemin!='null'&&obj.build.monthparkingfeemin!='NULL' }">
        			${obj.build.monthparkingfeemin }
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">公交情况</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.traffic!='null'&&obj.build.traffic!='NULL' }">
        				 ${obj.build.traffic }
        				 </c:if>
        				 </td>
        			<td width="15%" class="info_title_bb">轨道交通</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.roadway!='null'&&obj.build.roadway!='NULL' }">
        				${obj.build.roadway }
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">物业联系人</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.wylxr!='null'&&obj.build.wylxr!='NULL' }">
        				${obj.build.wylxr }
        				</c:if>
        			</td>
        			<td width="15%" class="info_title_aa">联系电话</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.wylxrdh!='null'&&obj.build.wylxrdh!='NULL' }">
        				${obj.build.wylxrdh }
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_bb">商业面积 (㎡)</td>
        			<td width="35%" bgcolor="#FFFFFF">
        			<c:if test="${obj.build.symj!='null'&&obj.build.symj!='NULL' }">
        				${obj.build.symj }
        				</c:if>
        			</td>
        			<td width="15%" class="info_title_bb">平均租金 (元/㎡.天)</td>
        			
        			<td width="35%" bgcolor="#FFFFFF">
        			<c:if test="${obj.build.bgpjzj!='null'&&obj.build.bgpjzj!='NULL' }">
        				${obj.build.bgpjzj }
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td width="15%" class="info_title_aa">物业管理费 (元/㎡.月)</td>
        			<td width="35%" bgcolor="#FFFFFF">
        			<c:if test="${obj.build.sybgpjzj!='null'&&obj.build.sybgpjzj!='NULL' }">
        				${obj.build.sybgpjzj }
        				</c:if>
        			</td>
        			<td width="15%" class="info_title_aa">物业公司</td>
        			<td width="35%" bgcolor="#FFFFFF">
        				<c:if test="${obj.build.wygs!='null'&&obj.build.wygs!='NULL' }">
        			 ${obj.build.wygs }
        			 	</c:if>
        			 </td>
        		</tr>
        		
			</table>    	
    	</div>
    	</td></tr></table>
</body>
</html>