<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/sjdc/open/screen.css">

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx}" />
   <jsp:include page="${ctx}/open/header.jsp"></jsp:include>
<div class="container">
    <article id="98" class="post" style="padding: 10px;">
    <h4  class="page-header"><b>服务基本信息</b></h4>
  <div class="row">
  
   <div class="col-md-6 form-group">
        <b>申请编号：</b>${obj.zyApply.appApplyNum}
    </div>
    <div class="col-md-6 form-group">
        <b>申请日期：</b><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.zyApply.applyDate}"/>
    </div>
    
    <div class="col-md-6 form-group">
        <b>申请人：</b>${obj.zyApply.userName}
    </div>
    
    <div class="col-md-6 form-group">
        <b>申请人联系电话：</b>${obj.zyApply.linkmanPhone}
    </div>
    
     <div class="col-md-6 form-group">
        <b>申请主题：</b>${obj.zyApply.applyTopic}
    </div>
      <div class="col-md-6 form-group">
      <b>资源提供单位： </b>${obj.zyApply.resourceProvider}
    </div>
    <div class="col-md-6 form-group">
        <b>资源名称：</b> ${obj.zyApply.resourceName}
    </div>
      <div class="col-md-6 form-group">
       <b> 资源类型：</b> ${obj.zyApply.resourceType}
    </div>
  
    
      <div class="col-md-12 form-group">
      <b>申请原因：</b>${obj.zyApply.applyReason}
    </div>
      <div class="col-md-12 form-group">
      <b>申请资源项：</b>
      <br/> 
       [普遍共享]：
       <c:forEach items="${obj.zyApplyDetailsList}" var="item" varStatus="row" >
       <c:if test="${item.openType == '普遍共享'}">
       	${item.cnName}&nbsp;&nbsp;&nbsp;
       </c:if>
       </c:forEach>
       <br/>
      [按需共享]：
         <c:forEach items="${obj.zyApplyDetailsList}" var="item" varStatus="row" >
       <c:if test="${item.openType == '按需共享'}">
       	${item.cnName}&nbsp;&nbsp;&nbsp;
       </c:if>
       </c:forEach>
    </div>
  
</div>
  
    

		</article>
</div>
<script src="${ctx}/sjfw/js/wdsp-link.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		fwUsedCount("fwCount","${fn:length(obj.applyMap)}");

	});

</script>
</body>
</html>
