<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源目录</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script> 
<style type="text/css">
.itab{height:36px; border-bottom:solid 1px #d0dee5; position:relative; border-left:solid 1px #d3dbde;}
.itab ul li{float:left;height:37px; line-height:37px; background:url(${ctx}/skins/images/itabbg.png) repeat-x; border-right:solid 1px #d3dbde;}
.itab ul li a{font-size:14px; color:#000; padding-left:25px; padding-right:25px;}
.itab ul li a.selected{ height:37px; display:block; background:url(${ctx}/skins/images/itabbg1.png) repeat-x; font-weight:bold;}

 }
</style>
<script type="text/javascript">
	function show_div(){ 
		var obj_div = document.getElementById("starlist"); 
			obj_div.style.display = (obj_div.style.display=='none')?'block':'none'; 
		} 
</script>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
	<form id="MainForm" action="" method="post">
	    <p style="text-align:center;padding-bottom: 15px;padding-top: 10px;font-size:24px">${obj.resource.resourceName}</p>
	    <div style="margin-left: 30px;">
	    		<table width="80%" align="center" class="table_multilist" >
			           <%-- <tr>
			           		<td width="15%" class="label_1">访问</td>
			           		<td width="85%" colspan="6" class="label_2">
			           			<c:if test="${obj.resource.browseCount eq null}">
				           			0&nbsp; 次
				           		</c:if>
				           		<c:if test="${obj.resource.browseCount != null}">
				           			${obj.resource.browseCount}&nbsp;次
				           		</c:if>
			           		</td>
			           </tr> --%>
			          <%--  <tr>
			           		<td width="15%" class="label_1">摘要</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.abstractWord}</td>
			           </tr>
			           <tr>
			           		<td width="15%" class="label_1">关键字</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.keyWord}</td>
			           </tr>
			           <tr>
			           		<td width="15%" class="label_1">信息系统名</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.infoSysName}</td>
			           </tr>
			           <tr>
			           		<td width="15%" class="label_1">信息系统链接</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.sysUrl}</td>
			           </tr>
			           <tr>
			           		<td width="15%" class="label_1">系统简介</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.sysAbstract}</td>
			           </tr>
			           <tr>
			           		<td width="15%" class="label_1">提供科室</td>
			           		<td width="85%" colspan="6" class="label_2">${obj.resource.provideDepartment}</td>
			           </tr> --%>
			           <tr>
	           		<td class="label_1">业务联系人</td>
					<td width="35%" class="label_2" colspan="2">${obj.resource.busLinkman}</td>
					<td width="15%" class="label_1">联系电话</td>
					<td width="35%" class="label_2" colspan="3">${obj.resource.busLinkmanPhone}</td>
	           </tr>
	           <%-- <tr>
	           		<td class="label_1">对接联系人</td>
					<td width="35%" class="label_2" colspan="2">${obj.resource.jointLinkman}</td>
					<td width="15%" class="label_1">联系电话</td>
					<td width="35%" class="label_2" colspan="3">${obj.resource.jointLinkmanPhone}</td>
	           </tr> --%>
	           <tr>
	           		<td class="label_1">对接方式</td>
					<td width="35%" class="label_2" colspan="2"><wd:dicvalue dicId="1082" dicCode="${obj.resource.jointType}"/></td>
					<td width="15%" class="label_1">资源更新频度</td>
					<td width="35%" class="label_2" colspan="3"><wd:dicvalue dicId="1083" dicCode="${obj.resource.updateRate}"/></td>
	           </tr>
	           <tr>
					<%-- <td class="label_1">资源共享属性</td>
					<td width="35%" class="label_2" colspan="2"><wd:dicvalue dicId="1086" dicCode="${obj.resource.shareProperty}"/></td> --%>
					<%-- <td width="15%" class="label_1">资源公开属性</td>
					<td width="35%" class="label_2" colspan="3"><wd:dicvalue dicId="1057" dicCode="${obj.resource.openProperty}"/></td> --%>
				<!-- </tr>
				<tr> -->
					<td class="label_1">所属资源类型</td>
					<td width="35%" class="label_2" colspan="2"><wd:dicvalue dicId="1054" dicCode="${obj.resource.resourceType}"/></td>
					<%-- <td width="15%" class="label_1">数据库表名</td>	
					<td width="35%" class="label_2" colspan="3">${obj.resource.tableName}</td>	 --%>			
				    <td width="15%" class="label_1">访问</td>
			           		<td width="35%" colspan="6" class="label_2">
			           			<c:if test="${obj.resource.browseCount eq null}">
				           			0&nbsp; 次
				           		</c:if>
				           		<c:if test="${obj.resource.browseCount != null}">
				           			${obj.resource.browseCount}&nbsp;次
				           		</c:if>
			           		</td>
				</tr>
			    </table>
			    <div style="margin-top: 10px;margin-bottom: 10px;text-align: center;">
				   <!--  <a href="javascript:show_div()" style="font-size: 15px;">收起</a> -->
			    </div>
			    <table width="80%"  id="starlist" class="table_multilist" align="center">
			    	   <tr style="background-color: silver;">
			           		<td class="label_1" width="20%" style="text-align: center;">资源项名</td>
			           		<td class="label_1" width="20%" style="text-align: center;">数据类型</td>
			           		<td class="label_1" width="20%" style="text-align: center;">备注</td>
			           		<td class="label_1" width="20%" style="text-align: center;">共享属性</td>
			           		<!-- <td class="label_1" width="15%" style="text-align: center;">公开属性</td> -->
			           		<td class="label_1" width="20%" style="text-align: center;">不予共享/公开理由</td>
			           </tr>
			           <c:forEach items="${obj.fieldDetailsList}" var="fieldDetails" varStatus="row">
			           		<c:if test="${row.index%2 == 0}">
			           			<tr class="bg_white">
				           			<td align="center" >${fieldDetails.dataItemName}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1085" dicCode="${fieldDetails.dataItemType}"/>
					           		</td>
					           		<td align="center" >${fieldDetails.memo}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1086" dicCode="${fieldDetails.shareProperty}"/>
					           		</td>
					           	<%-- 	<td align="center" class="fontClass">
					           			<wd:dicvalue dicId="1057" dicCode="${fieldDetails.openProperty}"/>
					           		</td> --%>
					           		<td align="center" >${fieldDetails.noOpenReason}</td>
				           		</tr>
			           		</c:if>
			           		<c:if test="${row.index%2 != 0}">
				           		<tr class="bg_grey">
				           			<td align="center" >${fieldDetails.dataItemName}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1085" dicCode="${fieldDetails.dataItemType}"/>
					           		</td>
					           		<td align="center" >${fieldDetails.memo}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1086" dicCode="${fieldDetails.shareProperty}"/>
					           		</td>
					           		<%-- <td align="center" class="fontClass">
					           			<wd:dicvalue dicId="1057" dicCode="${fieldDetails.openProperty}"/>
					           		</td> --%>
					           		<td align="center" >${fieldDetails.noOpenReason}</td>
				           		</tr>
			           		</c:if>
			           </c:forEach>
			    </table>
	    </div>
	    <br>
	    <div style="text-align: center;margin-top: 20px;padding-bottom: 10px;margin-left: 25px;">
		 <%--  <c:if test="${obj.resource.resourceId  !='e2f92fd91fc94a82b2703ee022caa5d3'}">
		   <input type="button" id="button1"  class="button" value="数据预览" onclick="querycreateTable('${obj.resource.tableName}','${obj.resource.resourceId}');"/>&nbsp;&nbsp;
		    </c:if> --%>
		   <%--  <c:if test="${obj.applyFlag eq '1'}"> --%>
				<input type="button" id="button"  class="button" value="资源申请" onclick="applyResource('${obj.resource.resourceId}');"/>&nbsp;&nbsp;
		   <%--  </c:if> --%>
		    <c:if test="${obj.isSy != '1' || obj.isSy eq ''}">
				<input type="button" id="button"  class="button" value="返  回" onclick="back();"/> 
		    </c:if>
		    <c:if test="${obj.isSy eq '1'}">
		    	<input type="button" id="button"  class="button" value="关  闭" onclick="window.close();"/> 
		    </c:if>
		</div>
	</form>
	
</body>
<script type="text/javascript"> 
      //$("#usual1 ul").idTabs(); 
      
      function back(){
    		window.history.back(-1); 
    	}
      
      /* $(document).ready(function(){
    	   $("#starlist").hide();
    	}); */
//数据预览
      function querycreateTable(table,resourceId){
  		window.showModalDialog("${ctx}/zymlgl/queryTablie?table="+table+"&resourceId="+resourceId,self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:yes;");
  	}
      //资源申请
      function applyResource(resourceId){
    	  $('#MainForm').attr('action','${ctx}/zymlgx/toApplyView?resourceId='+resourceId);
    	  $('#MainForm').submit();
      }
</script>
</html>
