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
<form action="${ctx }/suite/unit/saveUnit" name="fwsqForm" id="fwsqForm">
<input type="hidden" name="userUnit.unitId" value="${obj.userUnit.unitId}"/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">单位基本信息</h3>
   
   
   <div class="row">
    <div class="col-md-12 form-group">
       单位名称：<input type="text" name="userUnit.unitName" value="${obj.userUnit.unitName}" style="width:785px"/>
    </div>
   
     <div class="col-md-12 form-group">
        用户等级：<select name="userUnit.unitLevel" style="width:200px;height: 30px">
            <option value="">----</option>
            <option value="普通用户" <c:if test="${obj.userUnit.unitLevel == '普通用户'}">selected</c:if>>普通用户</option>
            <option value="高级用户" <c:if test="${obj.userUnit.unitLevel == '高级用户'}">selected</c:if>>高级用户</option>
        </select>
        
    </div>
     <div class="col-md-12 form-group">
        用户秘钥：${obj.userUnit.unitKeyDisplay}（系统自动生成）
    </div>

    <div class="col-md-12 form-group">
        单位描述： <textarea name="userUnit.unitMemo"  class="form-control" rows="3" style="width:865px;">${obj.userUnit.unitMemo}</textarea>
    </div>

</div>
  

	<p align="center">
	    <button type="button" class="btn btn-warning" onclick="fwsq()">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
	    <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/suite/unit/toUnitList'">返回</button>
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
