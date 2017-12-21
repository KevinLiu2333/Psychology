<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/dateUtils.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<link href="${ctx}/tiles/Validform/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div style="margin-top: 10px"></div>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td class="label_1" width="20%" >操作人:</td>	
			<td class="label_2" width="30%">${obj.log.operateUser}</td>
			<td class="label_1" width="20%" >中文名</td>	
			<td class="label_2" width="30%">${obj.user.displayName}</td>
		</tr>
		<tr>
			<td class="label_1" >操作时间:</td>	
			<td class="label_2" ><fmt:formatDate value="${obj.log.operateDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
			<td class="label_1" >查询表:</td>	
			<td class="label_2" >${obj.log.queryTable}</td>
		</tr>
		<tr>
			<td class="label_1" >查询条件:</td>	
			<td class="label_2" >${obj.log.queryCondition}</td>
			<td class="label_1" >查询结果数:</td>	
			<td class="label_2" >${obj.log.queryCount}</td>
		</tr>
		<tr>
			<td class="label_1" width="20%" >操作类型:</td>	
			<td class="label_2" width="30%">${obj.log.operateType}</td>
		</tr>
	</table>
<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
	<input type="button" class="button" value="关  闭" onclick="window.close()"/>
</div>
</body>
</html>