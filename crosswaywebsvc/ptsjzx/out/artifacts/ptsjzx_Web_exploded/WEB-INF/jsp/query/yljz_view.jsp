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
<div><p style='text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px'><b>详细信息</b></p></div>
<table class='table_multilist' width='96%' style='margin:auto'>
<tbody>
   <tr>
     <td class='label_1' align='center' width='20%'>姓名:</td>
     <td class='label_2' align='center' width='30%'>${obj.xm }<td>
     <td class='label_1' align='center' width='20%'>救助金额:</td>
     <td class='label_2' align='center' width='30%'>${obj.jzje }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>门诊救助:</td>
     <td class='label_2' align='center' width='30%'>${obj.mzjz }<td>
     <td class='label_1' align='center' width='20%'>住院救助:</td>
     <td class='label_2' align='center' width='30%'>${obj.zyjz }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>末次门诊时间:</td>
     <td class='label_2' align='center' width='30%'>${obj.zjmzsj }<td>
     <td class='label_1' align='center' width='20%'>末次住院时间:</td>
     <td class='label_2' align='center' width='30%'>${obj.zjzysj }<td>
   </tr>

 
</tbody>
</table>
<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
	<input type="button" class="button" value="关  闭" onclick="window.close()"/>
</div>
</body>
</html>