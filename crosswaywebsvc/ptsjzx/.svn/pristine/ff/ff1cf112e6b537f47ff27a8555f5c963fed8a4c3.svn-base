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
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">人口统计情况</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">本区实有户籍数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.bqhj }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">来沪人员数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.lhry }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">境外人员数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.jwry }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">人户分离数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.rhfl }人</td>
    		</tr>
    </table>
   	<div style="height: 20px">&nbsp;</div>
   	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">法人统计情况</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">登记有效单位数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.yxfr }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">法人资质信息条数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.frzz }条</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">法人处罚信息条数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.frcf }条</td>
    		</tr>
    </table>
    
    <div style="height: 20px">&nbsp;</div>
   	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">资源目录情况</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">单位数量</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">17</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">资源分布</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">人口：${obj.rrk }；法人：${obj.rfr }；经济：${obj.rjj }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">资源数据量</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.rsjl }条</td>
    		</tr>
    </table>
    
</form>

</body>
</html>