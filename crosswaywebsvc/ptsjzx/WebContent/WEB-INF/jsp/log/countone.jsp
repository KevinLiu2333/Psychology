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
          		<td width="70%" bgcolor="#FFFFFF" align="center">用户情况</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">开通用户数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.usercount }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">拥有数字证书数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.key }人</td>
    		</tr>
    </table>
   	<div style="height: 20px">&nbsp;</div>
   	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td colspan="2" width="70%" bgcolor="#FFFFFF" align="center">数据落地情况</td>
    		</tr>
    		<tr>
    			<td width="30%" height="39px" bgcolor="#FFFFFF" align="center">数据类型</td>
          		<td width="30%" bgcolor="#FFFFFF" align="center">数据量</td>
          		<td  width="40%" bgcolor="#FFFFFF" align="center">最后一次落地时间</td>
    		</tr>
    		<c:forEach items="${obj.rksj}" var="sj" varStatus="row">
				<tr>
				 <td width="30%" class="info_title_b">${sj.sjlx }</td>
          		 <td width="30%" bgcolor="#FFFFFF" align="center">${sj.rksl }条</td>
          		 <td width="40%" bgcolor="#FFFFFF" align="center">${sj.rktime }</td>
				</tr>
			</c:forEach>
			<tr>
				 <td width="30%" class="info_title_b">法人落地数据</td>
          		 <td width="30%" bgcolor="#FFFFFF" align="center">${obj.frsj }条</td>
          		 <td width="40%" bgcolor="#FFFFFF" align="center">${obj.frti }</td>
				</tr>
    </table>
    <div style="height: 20px">&nbsp;</div>
    <table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td colspan="2" width="70%" bgcolor="#FFFFFF" align="center">数据定制服务</td>
    		</tr>
    		<tr>
    			<td width="30%" height="39px" bgcolor="#FFFFFF" align="center">数据类型</td>
          		<td width="30%" bgcolor="#FFFFFF" align="center">查询次数</td>
          		<td  width="40%" bgcolor="#FFFFFF" align="center">最后一次查询时间</td>
    		</tr>
				<tr>
				 <td width="30%" class="info_title_b">房屋查询</td>
          		 <td width="30%" bgcolor="#FFFFFF" align="center">${obj.fwdb }次</td>
          		 <td width="40%" bgcolor="#FFFFFF" align="center"><fmt:formatDate value="${obj.fwdbtime }" pattern="yyyyMMdd" /></td>
				</tr>
			<tr>
				 <td width="30%" class="info_title_b">自然人对比查询</td>
          		  <td width="30%" bgcolor="#FFFFFF" align="center">${obj.rkdb }次</td>
          		 <td width="40%" bgcolor="#FFFFFF" align="center"><fmt:formatDate value="${obj.rkdbtime }" pattern="yyyyMMdd" /></td>
			</tr>
			<tr>
				 <td width="30%" class="info_title_b">法人对比查询</td>
          		 <td width="30%" bgcolor="#FFFFFF" align="center">${obj.frdb }次</td>
          		 <td width="40%" bgcolor="#FFFFFF" align="center"><fmt:formatDate value="${obj.frdbtime }" pattern="yyyyMMdd" /></td>
			</tr>
    </table>
	
</form>

</body>
</html>