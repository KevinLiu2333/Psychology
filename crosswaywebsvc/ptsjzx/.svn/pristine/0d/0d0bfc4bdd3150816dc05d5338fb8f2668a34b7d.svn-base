<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源目录</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style type="text/css">
.itab{height:36px; border-bottom:solid 1px #d0dee5; position:relative; border-left:solid 1px #d3dbde;}
.itab ul li{float:left;height:37px; line-height:37px; background:url(${ctx}/skins/images/itabbg.png) repeat-x; border-right:solid 1px #d3dbde;}
.itab ul li a{font-size:14px; color:#000; padding-left:25px; padding-right:25px;}
.itab ul li a.selected{ height:37px; display:block; background:url(${ctx}/skins/images/itabbg1.png) repeat-x; font-weight:bold;}
.fontClass{
	text-align:center; 
	height:22px;
	font-size: 12px;
}

 }
</style>
<script type="text/javascript">
	function show_div(){ 
		var obj_div = document.getElementById("starlist"); 
			obj_div.style.display = (obj_div.style.display=='none')?'block':'none'; 
	} 
	
	$("#usual1 ul").idTabs(); 
    
    function back(){
  		window.history.back(-1); 
  	}
    
    /* $(document).ready(function(){
  	   $("#starlist").hide();
    }); */
    
    function wind_close(){
		window.opener=null;
		window.open('','_self');
		window.close();
	}
</script>
</head>
<body style="background: url(${ctx}/skins/images/white_bg.gif);">
	<form id="MainForm" action="" method="post">
		<jsp:include page="../view-bas-info.jsp"></jsp:include>
	    <br>
	    <c:if test="${obj.resource.status != '2'}">
		    <div style="margin-left: 30px;">
		    	<h1 style="margin-left: 455px; font-size: 18px;">资源审核意见</h1><br>
		    	<table width="80%" align="center" class="table_multilist" >
					<tr align="center">
						<td width="20%" class="label_1" style="text-align:center">操作人</td>
						<td width="20%" class="label_1" style="text-align:center">操作时间</td>
						<td width="20%" class="label_1" style="text-align:center">操作类型</td>
						<td width="40%" class="label_1" style="text-align:center">操作意见</td>
					</tr>
					<c:forEach items="${obj.auditResultList}" var="opnn" varStatus="row">
						<tr align="center">
							<td>${opnn.opnnPerson}</td>
							<td><fmt:formatDate value="${opnn.opnnTime}" pattern="yyyy-MM-dd HH:ss:mm"/></td>
							<td><wd:dicvalue dicId="1013" dicCode="${opnn.opnnType}"/></td>
							<td>${opnn.opnnMemo}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	    <div style="text-align: center;margin-top: 20px;padding-bottom: 10px;margin-left: 25px;">
			<input type="button" id="button"  class="button" value="关  闭" onclick="wind_close();"/> 
		</div>
	</form>
	
</body>
</html>
