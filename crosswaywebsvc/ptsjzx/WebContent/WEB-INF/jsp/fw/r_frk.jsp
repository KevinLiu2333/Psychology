<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link rel="stylesheet" href="${ctx}/tiles/bootstrap2.3.2/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap2.3.2/js/bootstrap.min.js"></script>
<title>表详情</title>
</head>
<body>
	<div style="width:800px;margin:0 auto;">
		<div id="usual1" class="usual" style="margin-top:20px;"> 
 		<div class="itab">
	  		<ul style="margin-left:0px;">
	    		<li>
	    			<a href="#tab1" class="selected">法人基本信息</a>
	    		</li> 
	    		<li><a href="#tab2">法人资质信息</a></li> 
	    		<li><a href="#tab3">法人处罚信息</a></li> 
	    	</ul>
	    </div> 
			
		<div id="tab1" class="">
			<iframe width="100%" height="500px"  src="${ctx}/db/getTableinfo1?tablename=T_FWK"></iframe>
		</div>
		<div id="tab2" class="">
			<iframe width="100%" height="500px"  src="${ctx}/db/getTableinfo1?tablename=T_FWJBXX"></iframe>
		</div>
		
		<div id="tab3" class="">
			<iframe width="100%" height="500px"  src="${ctx}/db/getTableinfo1?tablename=T_FWGLXX"></iframe>
		</div>
	</div>
			<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
			</script>


</body>
</html>

