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
     <td class='label_1' align='center' width='20%'>姓名</td>
     <td class='label_2' align='center' width='30%'>${obj.fullname }<td>
     <td class='label_1' align='center' width='20%'>证件号码</td>
     <td class='label_2' align='center' width='30%'>${obj.idno }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>姓名</td>
     <td class='label_2' align='center' width='30%'>${obj.fullname }<td>
     <td class='label_1' align='center' width='20%'>证件号码</td>
     <td class='label_2' align='center' width='30%'>${obj.idno }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>受理编号</td>
     <td class='label_2' align='center' width='30%'>${obj.applyno }<td>
     <td class='label_1' align='center' width='20%'>事项代码</td>
     <td class='label_2' align='center' width='30%'>${obj.affairscode }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>事项名称</td>
     <td class='label_2' align='center' width='30%'>${obj.affairsname }<td>
     <td class='label_1' align='center' width='20%'>办理时间</td>
     <td class='label_2' align='center' width='30%'>${obj.accepttime }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>受理机构名称</td>
     <td class='label_2' align='center' width='30%'>${obj.acceptorganname }<td>
     <td class='label_1' align='center' width='20%'>办结时间</td>
     <td class='label_2' align='center' width='30%'>${obj.endtime }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>事项状态名称</td>
     <td class='label_2' align='center' width='30%'>${obj.stagestatusdesc }<td>
     <td class='label_1' align='center' width='20%'>事项状态说明</td>
     <td class='label_2' align='center' width='30%'>${obj.stagestatusname }<td>
   </tr>
</tbody>
</table>
<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
	<input type="button" class="button" value="关  闭" onclick="window.close()"/>
</div>
</body>
</html>