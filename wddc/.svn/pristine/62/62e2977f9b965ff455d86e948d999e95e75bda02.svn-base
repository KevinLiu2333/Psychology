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
<form action="${ctx }/suite/auth/saveAuth" name="fwsqForm" id="fwsqForm">
<input type="hidden" name="auth.nodeId" value="${obj.auth.nodeId}"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">资源基本信息</h3>
   
   <div class="row">
    <div class="col-md-12 form-group">
       资源名称：<input type="text" name="auth.nodeName" value="${obj.auth.nodeName}" style="width:785px"/>
    </div>
      <div class="col-md-12 form-group">
        资源代码：${obj.auth.nodeCode}（系统自动生成）
    </div>
     <div class="col-md-12 form-group">
        资源类型：<select name="auth.nodeType" style="width:200px;height: 30px">
            <option value="">----</option>
            <option value="菜单" <c:if test="${obj.auth.nodeType == '菜单'}">selected</c:if>>菜单</option>
            <option value="菜单" <c:if test="${obj.auth.nodeType == '按钮'}">selected</c:if>>按钮</option>
        </select>
        
    </div>
     <div class="col-md-12 form-group">
        资源链接：<input type="text" name="auth.nodeURL" value="${obj.auth.nodeURL}" style="width:785px"/>
    </div>
     <div class="col-md-12 form-group">
        资源顺序：<input type="text" name="auth.order" value="${obj.auth.order}" style="width:785px"/>
    </div>

    <div class="col-md-12 form-group">
        资源描述： <textarea name="auth.nodeMemo"  class="form-control" rows="3" style="width:865px;">${obj.auth.nodeMemo}</textarea>
    </div>

</div>
  

	<p align="center">
	    <button type="button" class="btn btn-warning" onclick="fwsq()">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/suite/auth/toAuthList'">返回</button>
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
