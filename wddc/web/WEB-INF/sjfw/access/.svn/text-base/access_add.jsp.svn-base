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
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
	<input type="hidden" id="js_ctx" value="${ctx }" />
<div class='container'>
    <form method="post" id="publishForm" action="${ctx}/fw/saveFwAccessList">
        <input type="hidden" name="fwAccess.fwAccessId" value="${obj.fwAccess.fwAccessId}">
        <input type="hidden" name="fwInfoId" value="${obj.fwInfoId}">
    <h4  class="page-header"><b>授权信息</b></h4>
  <div class="row">
   <div class="col-md-6 form-group">
        unitKey：<input type="text" name="fwAccess.unitKey" style="width:200px" value="${obj.fwAccess.unitKey}"/>
    </div>
    <div class="col-md-6 form-group">
        methodKey：<input type="text" name="fwAccess.methodKey" style="width:200px" value="${obj.fwAccess.methodKey}"/>
    </div>
    <div class="col-md-6 form-group">
        授权类型：<select id="accessType" name="fwAccess.accessType" style="width:200px;height: 30px"></select>
    </div>
    
      <div class="col-md-6 form-group">
        授权状态：<select id="usedStatus" name="fwAccess.usedStatus" style="width:200px;height: 30px"></select>
    </div>
    
    <div class="col-md-12 form-group">
      临时授权截止时间：<input type="text" name="fwAccess.accessEndDate" style="width:200px" value="${obj.fwAccess.accessEndDate}"/>
    </div>
  
</div>
  
</form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save()">保  存</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toFwAccessList?fwInfoId=${obj.fwInfoId}'">返回</button>
    </p>

</div>
</body>
<script type="text/javascript">
    function save() {
        $("#publishForm").submit();
    }
   
    jQuery(function($){

		$('#usedStatus').jsondic({dicid:'2002',initvalue:"--请选择--",defaultvalue:"${obj.fwAccess.usedStatus}"});
		$('#accessType').jsondic({dicid:'2003',initvalue:"--请选择--",defaultvalue:"${obj.fwAccess.accessType}"});
		
       

    });
   
 </script>
</html>
