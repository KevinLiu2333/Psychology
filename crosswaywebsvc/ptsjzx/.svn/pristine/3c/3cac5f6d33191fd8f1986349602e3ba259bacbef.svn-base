<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>双公式数据填报</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
</head>
<body>
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
		<ul>
			<c:forEach items="${obj.contents }" var="contents" varStatus="row">
				<c:if test="${ row.index==0 }">
						<li><a href="#tab${row.index+1}" class="selected">${ contents.datatype}</a></li>
				</c:if>
				<c:if test="${ row.index!=0 }">
						<li><a href="#tab${row.index+1}" >${ contents.datatype}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
 		<c:forEach items="${obj.contents }" var="contents" varStatus="row">
 			<div id="tab${row.index+1 }" class="tabson">
	    		<iframe src="${ctx}/sjtb/sgs/toTbym?id=${contents.id}" height="650px" width="100%" frameborder="0"></iframe>
	    	</div>
 		</c:forEach>
	</div> 
</body>

<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
</script>
</html>