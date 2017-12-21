<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据填报</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
</head>
<body>
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
		<ul>
			<li><a href="#tab4" class="selected">基本情况统计</a></li>
			<li><a href="#tab2" >一数一源统计</a></li>
			<li><a href="#tab1" >接口统计</a></li>
			<li><a href="#tab3" >人口法人资源统计</a></li>
			<li><a href="#tab5" >街镇人口分类统计</a></li>
			<li><a href="#tab6" >人口数据落地详情</a></li>
			
			
				
		</ul>
		</div>
		     <div id="tab1" class="tabson" style="height:380px;">
	    		<iframe src="${ctx}/logcount/toCountFour" height="100%" width="100%" frameborder="0"></iframe>
	    	</div>
	    	<div id="tab2" class="tabson">
	    		<iframe src="${ctx}/logcount/toCountThree" height="650px" width="100%" frameborder="0"></iframe>
	    	</div>
	    	<div id="tab3" class="tabson">
	    		<iframe src="${ctx}/logcount/toCountTwo" height="650px" width="100%" frameborder="0"></iframe>
	    	</div>
 			<div id="tab4" class="tabson">
	    		<iframe src="${ctx}/logcount/toCountOne" height="650px" width="100%" frameborder="0"></iframe>
	    	</div>
	    	<div id="tab5" class="tabson">
	    		<iframe src="${ctx}/logcount/toPersonCount" height="650px" width="100%" frameborder="0"></iframe>
	    	</div>
	    	<div id="tab6" class="tabson">
	    		<iframe src="${ctx}/logcount/toPersonDetails" height="700px" width="100%" frameborder="0"></iframe>
	    	</div>
	    	
	    	
	    	
	    	
	</div> 
</body>

<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
</script>
</html>