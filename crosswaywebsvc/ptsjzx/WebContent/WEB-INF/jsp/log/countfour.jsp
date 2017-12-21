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
    			<td colspan="1" width="30%" class="info_title_a">统计类型</td>
          		<td colspan="3" width="70%" bgcolor="#FFFFFF" align="center">对外接口情况</td>
    		</tr>
    		<tr>
    			<td width="25%" height="39px" bgcolor="#FFFFFF" align="center">接口名称</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">调用次数</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">调用返回结果数</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">最后调用时间</td>
          		
    		</tr>
    		<c:forEach items="${obj.list}" var="service" varStatus="row">
				<tr>
					<td width="25%"  height="39px" bgcolor="#FFFFFF" align="center">${service.serviceName }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${service.calltime }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${service.callresult }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${service.summary }</td>
				</tr>
			</c:forEach>
			<tr>
					<td width="25%"  height="39px" bgcolor="#FFFFFF" align="center">统计</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${obj.times }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${obj.getresult }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">/</td>
			</tr>
    		
    </table>
   	<div style="height: 20px">&nbsp;</div>
   	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td colspan="1" width="30%" class="info_title_a">统计类型</td>
          		<td colspan="3" width="70%" bgcolor="#FFFFFF" align="center">对接接口情况</td>
    		</tr>
    		<tr>
    			<td width="25%" height="39px" bgcolor="#FFFFFF" align="center">接口名称</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">调用次数</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">调用返回结果数</td>
    			<td width="25%"  bgcolor="#FFFFFF" align="center">最后调用时间</td>
          		
    		</tr>
    		<c:forEach items="${obj.apilist}" var="api" varStatus="row">
				<tr>
					<td width="25%"  height="39px" bgcolor="#FFFFFF" align="center">${api.sername }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${api.intime }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${api.input }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${api.lasttime }</td>
				</tr>
			</c:forEach>
			<tr>
					<td width="25%"  height="39px" bgcolor="#FFFFFF" align="center">统计</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${obj.alltimes }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">${obj.allresult }</td>
					<td width="25%"  bgcolor="#FFFFFF" align="center">/</td>
			</tr>
    		
    </table>
</form>

</body>
</html>