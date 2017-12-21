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
	    <p style="text-align:center;padding-bottom: 15px;padding-top: 10px;font-size:24px">${obj.resource.resourcename}</p>
	    <div style="margin-left: 30px;">
	    		<table width="80%" align="center" class="table_multilist" >
			          
	           <tr><td width="15%" class="label_1">提供单位</td>
					<td width="35%" class="label_2" ><wd:dicvalue dicId="201701" dicCode="${obj.resource.providedept}"/></td>
					<td width="15%" class="label_1">资源更新频度</td>
					<td width="35%" class="label_2" ><wd:dicvalue dicId="1083" dicCode="${obj.resource.updaterate}"/></td>
	           </tr>
	           <tr>
	                <td width="15%" class="label_1">数据领域</td>
			        <td width="35%"  class="label_2">
				          ${obj.resource.sjly}
			        </td>
				    <td width="15%" class="label_1">访问</td>
			        <td width="35%"  class="label_2">
				          ${obj.resource.browsecount}&nbsp;次
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
				           			<td align="center" >${fieldDetails.dataitemname}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1085" dicCode="${fieldDetails.dataitemtype}"/>
					           		</td>
					           		<td align="center" >${fieldDetails.memo}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1086" dicCode="${fieldDetails.openproperty}"/>
					           		</td>
					           
					           		<td align="center" >${fieldDetails.noopenreason}</td>
				           		</tr>
			           		</c:if>
			           		<c:if test="${row.index%2 != 0}">
				           		<tr class="bg_grey">
				           			<td align="center" >${fieldDetails.dataitemname}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1085" dicCode="${fieldDetails.dataitemtype}"/>
					           		</td>
					           		<td align="center" >${fieldDetails.memo}</td>
					           		<td align="center" >
					           			<wd:dicvalue dicId="1086" dicCode="${fieldDetails.openproperty}"/>
					           		</td>
					           		
					           		<td align="center" >${fieldDetails.noopenreason}</td>
				           		</tr>
			           		</c:if>
			           </c:forEach>
			    </table>
	    </div>
	    <br>
	    <div style="text-align: center;margin-top: 20px;padding-bottom: 10px;margin-left: 25px;">
		
			
		 
		    	<input type="button" id="button"  class="button" value="返回" onclick="back();"/> 
		 
		</div>
	</form>
	
</body>
<script type="text/javascript"> 
      //$("#usual1 ul").idTabs(); 
      
      function back(){
    		window.history.back(-1); 
    	}
    
</script>
</html>
