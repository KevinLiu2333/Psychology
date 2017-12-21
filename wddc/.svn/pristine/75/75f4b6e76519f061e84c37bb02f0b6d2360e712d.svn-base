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
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<form action="${ctx }/fw/fwsqChange" name="fwsqForm" id="fwsqForm">
<input type="hidden" name="fwApplyId" value="${obj.fwApply.fwApplyId }"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">服务授权</h3>
   
    <h4  class="page-header">填写审批意见</h4>
     <div class="row">
 		<div class="col-md-6 form-group">
           <span>  <select name="auditStatus" style="width:200px;height: 30px">
            <option value="">----</option>
            <option value="2">同意授权</option>
            <option value="3">不同意授权</option>
        </select></span>
        </div>
        <div class="col-md-12 form-group">
            <span><textarea class="form-control" rows="3" name="auditMemo"></textarea></span>
        </div>
      </div>
    <h4  class="page-header">申请人信息</h4>
    <div class="row">
      <div class="col-md-6 form-group">
           <span> 申请单位：${obj.fwApply.unitName }</span>
        </div>
        <div class="col-md-6 form-group">
           <span>申请人：${obj.fwApply.applyUserName }</span>
        </div>
        <div class="col-md-12 form-group">
            <span>申请原因：${obj.fwApply.applyReason }</span>
        </div>
      
    </div>
        <h4  class="page-header">申请的数据项</h4>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>资源项名称</th>
            <th>公开类型</th>
            <th>资源项名称</th>
            <th>公开类型</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${obj.itemList}" var="zyItem" varStatus="status">
        <c:if test="${status.count%2!=0}">  
	        <td>${zyItem.cnName }</td>  
	        <td>${zyItem.openType }</td>  
   		</c:if>  
    	<c:if test="${status.count%2==0}">  
        	<td>${zyItem.cnName }</td>  
        	<td>${zyItem.openType }</td>  
        </tr><tr>  
    	</c:if>  
        </c:forEach>
        </tbody>
    </table>
<p align="center">
    <button type="button" class="btn btn-warning" onclick="fwsq()">服务授权</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toFwsqList'">返回</button>
</p>
</div>
</form>
</body>
<script type="text/javascript">
function fwsq() {
	$("#fwsqForm").submit();
}
</script>
</html>
