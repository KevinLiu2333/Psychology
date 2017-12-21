<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style type="text/css">
.itab{height:36px; border-bottom:solid 1px #d0dee5; position:relative; border-left:solid 1px #d3dbde;}
.itab ul li{float:left;height:37px; line-height:37px; background:url(${ctx}/skins/images/itabbg.png) repeat-x; border-right:solid 1px #d3dbde;}
.itab ul li a{font-size:14px; color:#000; padding-left:25px; padding-right:25px;}
.itab ul li a.selected{ height:37px; display:block; background:url(${ctx}/skins/images/itabbg1.png) repeat-x; font-weight:bold;}
 }
</style>
<script language="javascript" >
    $(function(){// dom元素加载完毕
       $('#starlist  tr td:even').css("backgroundColor","#eff5ed");
       //获取id为tb的元素,然后寻找他下面的tbody标签，再寻找tbody下索引值是偶数的tr元素,改变它的背景色.
       //tr:odd为奇数行，索引从0开始，0算偶数。
    });
    var a='${fn:length(obj.resourceDetailsList)}';
</script>

</head>
<body>
<div style="text-align: center;margin-top:10px;font-size:20px;">资源预览</div>
<div class="pageHeader" style="width:96%; text-align:center; margin-top:20px;">
	 <table width="96%"  id="starlist" class="table_multilist" style="margin-left:25px;">
	    	    <tr style="background-color: silver;">
	    	    <c:forEach items="${obj.resourceDetailsList}" var="resourceDetailsList" varStatus="row">
	           		<th class="label_1"  style="text-align: center;">${resourceDetailsList.dataItemName}</th>
	           	</c:forEach>
	           	</tr>
	           
	         	<tr id="test">
	           		<c:forEach items="${obj.resourceDetais}" var="details" varStatus="row">
	           			<td align="center"  class="fontClass">${details}</td>
		           		<c:if test="${(row.index+1) % fn:length(obj.resourceDetailsList) == 0}">
		           		  <tr></tr>
		           		</c:if>
	           		</c:forEach>
	           	</tr>
	  </table>
	  </div>
	<div style="padding-left:41.5%;padding-top:20px;">
		<button type="button" class="midButton close" onclick="window.close()">关  闭</button>
	</div>
</body>