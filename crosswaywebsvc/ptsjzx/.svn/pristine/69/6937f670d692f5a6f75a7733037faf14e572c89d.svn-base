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
     <td class='label_1' align='center' width='20%'>行政处罚决定书文号</td>
     <td class='label_2' align='center' width='30%'>${obj.cfwsh }<td>
     <td class='label_1' align='center' width='20%'>处罚名称</td>
     <td class='label_2' align='center' width='30%'>${obj.cfcfmc }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>处罚类别</td>
     <td class='label_2' align='center' width='30%'>${obj.cfcflb1 }<td>
     <td class='label_1' align='center' width='20%'>详细类别</td>
     <td class='label_2' align='center' width='30%'>${obj.cfcflb2 }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>处罚事由</td>
     <td class='label_2' align='center' width='30%'>${obj.cfsy }<td>
     <td class='label_1' align='center' width='20%'>处罚依据</td>
     <td class='label_2' align='center' width='30%'>${obj.cfyj }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>行政相对人名称</td>
     <td class='label_2' align='center' width='30%'>${obj.cfxdrmc }<td>
     <td class='label_1' align='center' width='20%'>居民身份证号</td>
     <td class='label_2' align='center' width='30%'>${obj.cfxdrsfz }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>处罚结果</td>
     <td class='label_2' align='center' width='30%'>${obj.cfjg }<td>
     <td class='label_1' align='center' width='20%'>处罚决定日期</td>
     <td class='label_2' align='center' width='30%'><fmt:formatDate value="${obj.cfjdrq }" pattern="yyyy年MM月dd日"/><td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>处罚机关</td>
     <td class='label_2' align='center' width='30%'>${obj.cfxzjg }<td>
     <td class='label_1' align='center' width='20%'>当前状态</td>
     <td class='label_2' align='center' width='30%'>${obj.cfzt }<td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>地方编码</td>
     <td class='label_2' align='center' width='30%'>${obj.dfbm }<td>
     <td class='label_1' align='center' width='20%'>数据更新时间</td>
     <td class='label_2' align='center' width='30%'><fmt:formatDate value="${obj.sjc }" pattern="yyyy年MM月dd日"/><td>
   </tr>
   <tr>
     <td class='label_1' align='center' width='20%'>备注</td>
     <td class='label_2' align='center' width='30%'>${obj.bz }<td>
   </tr>
</tbody>
</table>
<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
	<input type="button" class="button" value="关  闭" onclick="window.close()"/>
</div>
</body>
</html>