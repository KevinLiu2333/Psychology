<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据查询日志</title>
<style type="text/css">
.info_title_aa,.info_title_bb {
	text-align:center;
}
.info_title_aa {
	background:url(${ctx}/skins/query/images/info_title_bg.jpg) repeat-x;
	background-size:100%;
}
.info_title_bb {
	background:#FFF;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" action="${ctx}/log/toLogCount" method="post">
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>	
	
	<div style="height: 20px">&nbsp;</div>
	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="20%" class="info_title_a">统计类型</td>
          		<td colspan="4" width="70%" bgcolor="#FFFFFF" align="center">一数一源</td>
    		</tr>
    		<tr>
    			<td width="20%" class="info_title_a">资源类型</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">人口</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">法人</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">经济</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">合计</td>
    		</tr>
    		<tr>
    			<td width="20%" class="info_title_a">资源目录</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.rkzy }个</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.frzy }个</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjzy }个</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjzy+obj.frzy+obj.rkzy }个</td>
    		</tr>
    		<tr>
    			<td width="20%" class="info_title_a">资源数据项</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.rkzyx }项</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.frzyx }项</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjzyx }项</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjzyx+obj.frzyx+obj.rkzyx }项</td>
    		</tr>
    		
    		<tr>
    			<td width="20%" class="info_title_a">历史数据量</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">340308条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">5788条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">245条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">346341条</td>
    		</tr>
    		<tr>
    			<td width="20%" class="info_title_a">2016年7月至今数据量</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.rksj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.frsj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjsj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.jjsj+obj.frsj+obj.rksj }条</td>
    		</tr>
    		<tr>
    			<td width="20%" class="info_title_a">当月归集数据</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.mrksj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.mfrsj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.mjjsj }条</td>
          		<td width="20%" bgcolor="#FFFFFF" align="center">${obj.mjjsj+obj.mfrsj+obj.mrksj }条</td>
    		</tr>
    		
   
    </table>
   	<div style="height: 20px">&nbsp;</div>
</form>

</body>
</html>