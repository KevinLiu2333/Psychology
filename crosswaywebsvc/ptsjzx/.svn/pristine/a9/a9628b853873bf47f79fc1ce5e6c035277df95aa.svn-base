<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀区数据资源管理系统</title>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<script src="${ctx}/tiles/echarts/echarts.js"></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
<script type="text/javascript"
	src="${ctx}/tiles/data/zx/people/peopledata.js"></script>
<style type="text/css">
.h500 {
	position: relative;
	height: 450px;
}

.tablebox01 {
	border: 1px solid #d5d5d5;
	margin: 0 auto;
}

.tablebox01 tr td {
	font-size: 16px;
	clear: #333;
	line-height: 30px;
	text-align: center;
}

.tablebox01 tr td {
	font-size: 15px;
	clear: #333;
	line-height: 25px;
	text-align: center;
}

.mima_tit dt span {
	background: url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat
		center;
}

.mima_tit dd span {
	background: url(${ctx}/skins/style/images/bianhua.png) no-repeat center;
}
</style>
</head>
<body>
	
		
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
				<c:forEach items="${obj.list}" var="industrial" varStatus="row">
				<tr>
						<td width="10%"  align="center">${row.index+1 }</td>
						<td width="90%" align="center">${industrial.yqmc }</td>
					</tr>
				</c:forEach>
			</table>
	

	<script type="text/javascript">
		
			
			window.onload=function(){
				$('.tablelist tr:odd').addClass('odd');
			};	
	
	</script>
</body>
</html>