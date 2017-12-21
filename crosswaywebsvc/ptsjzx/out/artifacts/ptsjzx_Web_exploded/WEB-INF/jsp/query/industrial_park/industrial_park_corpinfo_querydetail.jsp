<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<title>房屋详细户籍信息</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
	<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
	<jsp:include page="/common/meta.jsp"/>
	<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
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
<body style="overflow:scroll;overflow-x:hidden;">
	<table style="width:96% border:0; align:center; cellpadding:0; cellspacing:0;">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>
	
	<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="list_search" >
			
			<c:forEach items="${obj.list1}" var="park" varStatus="row">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list">
					<tr>
						<td width="5%"  style="font-weight:bold;border-bottom: dotted 0px #123456;">&nbsp;</td>
						<td width="30%" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">园区名称</font></td>
						<td width="30%" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">园区地址</font></td>
						<td width="25%" style="font-weight:bold;border-bottom: dotted 0px #123456;"><font size="4" color="white">企业数量</font></td>
					</tr>
					<tr>
						<td style="font-weight:bold;border-bottom: dotted 0px #123456;">&nbsp;</td>
						<td ><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${park.name }</font></td>
      					<td ><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${park.address }</font></td>
      					<td ><font size="4" color="white" style="border-bottom: dotted 0px #123456;">${obj.corpCount }</font></td>
					</tr>
			</table>
			</c:forEach>
			
		</td>
	</tr>
	</table>
	
	<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td>
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div id="tab1" class="tabson">
			<div id="main1">
				<c:forEach items="${obj.list2}" var="park" varStatus="row">
				<div style="height: 20px">&nbsp;</div>
				<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
			    		<tr>
			    			<td width="15%" class="info_title_a">法人名称</td>
			          		<td width="40%" bgcolor="#FFFFFF" align="left">&nbsp;&nbsp;&nbsp;${park.corpname }</td>
			          		<td width="15%" class="info_title_a">行业类别</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="left">&nbsp;&nbsp;&nbsp;${park.statname }</td>
			    		</tr>
			    		<tr>
			    			<td width="15%" class="info_title_a">详细地址</td>
			          		<td width="30%" bgcolor="#FFFFFF" align="left" colspan="3">&nbsp;&nbsp;&nbsp;${park.corpaddress }</td>
			    		</tr>
			    		
			    	</table>
			    <div style="height: 20px">&nbsp;</div>
			    </c:forEach>
			    </div>
		    </div>
		   	<div style="height: 20px">&nbsp;</div>
	    </div>
	</td></tr>
</table>
	
	
</body>
<script type="text/javascript"> 

	  $("#usual1 ul").idTabs(); 
	  $(function(){
			var $node = $('#watermark');  
			//if是判断是否ie,IE8及一下版本只能用滤镜实现透明,ie9、else火狐等用opacity属性  
			if(!document.all){  
				$node.addClass('no_ie_style');
			}  
		});

      
</script>
</html>