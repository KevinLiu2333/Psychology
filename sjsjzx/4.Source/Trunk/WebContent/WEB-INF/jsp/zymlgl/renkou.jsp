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
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.itab{height:36px; border-bottom:solid 1px #d0dee5; position:relative; border-left:solid 1px #d3dbde;}
.itab ul li{float:left;height:37px; line-height:37px; background:url(${ctx}/skins/images/itabbg.png) repeat-x; border-right:solid 1px #d3dbde;}
.itab ul li a{font-size:14px; color:#000; padding-left:25px; padding-right:25px;}
.itab ul li a.selected{ height:37px; display:block; background:url(${ctx}/skins/images/itabbg1.png) repeat-x; font-weight:bold;}
 }
</style>


</head>
<body style="background-image:url(${ctx}/skins/images/data_example.jpg);">
<div style="text-align: center;margin-top:10px;font-size:20px;">资源预览</div>
<div class="pageHeader" style="width:96%; text-align:center; margin-top:20px;">
	 <table width="96%"  id="starlist" class="table_multilist" style="margin-left:25px;">
	    	    <tr style="background-color: silver;">
	           		<th class="label_1"  style="text-align: center;">姓名</th>
	           		<th class="label_1"  style="text-align: center;">性别</th>
	           		<th class="label_1"  style="text-align: center;">民族</th>
	           		<th class="label_1"  style="text-align: center;">出生日期</th>
	           		<th class="label_1"  style="text-align: center;">身份证号</th>
	           		<th class="label_1"  style="text-align: center;">户籍地址</th>
	           		<th class="label_1"  style="text-align: center;">居住地址</th>
	           	</tr>
	           
	         	<tr id="test">
	           			<td align="center"  class="fontClass">张三</td>
	           			<td align="center"  class="fontClass">男</td>
	           			<td align="center"  class="fontClass">汉族</td>
	           			<td align="center"  class="fontClass">1987年12月10日</td>
	           			<td align="center"  class="fontClass">415226198712108563</td>
	           			<td align="center"  class="fontClass">上海市龙源路</td>
	           			<td align="center"  class="fontClass">上海市松江区某某路XX号</td>
	           	</tr>
	           	<tr id="test">
	           			<td align="center"  class="fontClass">李斯</td>
	           			<td align="center"  class="fontClass">女</td>
	           			<td align="center"  class="fontClass">汉族</td>
	           			<td align="center"  class="fontClass">1997年12月10日</td>
	           			<td align="center"  class="fontClass">425698199712105564</td>
	           			<td align="center"  class="fontClass">上海市江北路路</td>
	           			<td align="center"  class="fontClass">上海市松江区某某路XX号</td>
	           	</tr>
	           	<tr id="test">
	           			<td align="center"  class="fontClass">李雯</td>
	           			<td align="center"  class="fontClass">女</td>
	           			<td align="center"  class="fontClass">汉族</td>
	           			<td align="center"  class="fontClass">1993年12月10日</td>
	           			<td align="center"  class="fontClass">4206541993101014517</td>
	           			<td align="center"  class="fontClass">上海市江北路路</td>
	           			<td align="center"  class="fontClass">上海市松江区某某路XX号</td>
	           	</tr>
	           	<tr id="test">
	           			<td align="center"  class="fontClass">赵武</td>
	           			<td align="center"  class="fontClass">男</td>
	           			<td align="center"  class="fontClass">汉族</td>
	           			<td align="center"  class="fontClass">1988年12月10日</td>
	           			<td align="center"  class="fontClass">42319519881210</td>
	           			<td align="center"  class="fontClass">上海市江北路路</td>
	           			<td align="center"  class="fontClass">上海市松江区某某路XX号</td>
	           	</tr>
	           	<tr id="test">
	           			<td align="center"  class="fontClass">尼克买提</td>
	           			<td align="center"  class="fontClass">男</td>
	           			<td align="center"  class="fontClass">回族</td>
	           			<td align="center"  class="fontClass">1942年12月10日</td>
	           			<td align="center"  class="fontClass">482325194212108612</td>
	           			<td align="center"  class="fontClass">上海市江北路路</td>
	           			<td align="center"  class="fontClass">上海市松江区某某路XX号</td>
	           	</tr>
	  </table>
	  </div>
	<div style="padding-left:41.5%;padding-top:20px;">
		<button type="button" class="midButton close" onclick="window.close()">关  闭</button>
	</div>
</body>