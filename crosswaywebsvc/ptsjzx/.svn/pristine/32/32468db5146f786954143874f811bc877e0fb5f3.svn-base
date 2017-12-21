<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style type="text/css">
<style type="text/css">
.new td{
background: #FFF2CC;
font-family: '宋体';
font-size: 15px;
}
</style>
</head>
<body>
<div style="width:800px;">
<div style="margin-top: 10px;" align="center"><p style=" font-size: 18px;font-weight: 5;">${obj.name }成功上传${obj.count}条!</p>
  
			<input type="button" id="button"  class="button" value="关  闭" onclick="wind_close();"/> 
		</div>
	<table class="table_list" id="table1" style="width:100%;height:98%;align:center;">
		<!-- 台头 -->
		<tr>
		   <th width="10%">序号</th>
		<c:forEach items="${obj.namelist }" var="item">
			<th width="18%">${item }</th>
		</c:forEach>
		</tr>
			<c:forEach  items="${obj.list}" var="data" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<c:forEach items="${data }" var="col" varStatus="nm">
					     <td align="center" width="18%">${col.value }</td>			   
					</c:forEach>
				</tr>
			</c:forEach>
	</table>
</div>
</body>
	<script type="text/javascript">
		$(document).keydown(function (event) {
		    if (event.keyCode == 27) {
		    	window.close();
		    }
		});
		function winclose(){
			window.close();
		}
		 function wind_close(){
				window.opener=null;
				window.open('','_self');
				window.close();
			}
	</script>
</html>