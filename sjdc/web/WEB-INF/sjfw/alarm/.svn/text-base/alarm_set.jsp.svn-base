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
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/iCheck/skins/flat/blue.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/iCheck/jquery.icheck.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/iCheck/icheck-init.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<form id="fwyjForm" name="fwyjForm" action="${ctx }/fw/saveLevel" method="post" target="">
<input type="hidden" name="fwInfoId" value="${obj.fwList.fwInfoId }">
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">服务预警</h3>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">一级预警</h3>
        </div>
        <div class="panel-body">
        	<div class="icheck">
        		<div class="flat-blue single-row radio">
        			<input type="radio" name="alertLevel" id="yj0" value="一级"<c:if test="${obj.fwList.alertLevel == '一级'}">checked="checked"</c:if>/>
            		半小时内调用超过100次
                 </div>
            </div>
        </div>
    </div>
    <div class="panel panel-info">
        <div class="panel-heading ">
        	<h3 class="panel-title">二级预警</h3>
        </div>     
        <div class="panel-body">
        	<div class="icheck">
        		<div class="flat-blue single-row radio">
        			<input tabindex="3"  type="radio"  name="alertLevel" id="yj1" value="二级"<c:if test="${obj.fwList.alertLevel == '二级'}">checked="checked"</c:if>/>
            		10分钟内调用超过100次
            	</div>
            </div>
        </div>
    </div>
    <div class="panel panel-warning">
        <div class="panel-heading">
            <h3 class="panel-title">三级预警</h3>
        </div>
        <div class="panel-body">
        	<div class="icheck">
        		<div class="flat-blue single-row radio">
        			<input type="radio" name="alertLevel" id="yj2" value="三级"<c:if test="${obj.fwList.alertLevel == '三级'}">checked="checked"</c:if>/>
            			1分钟内调用超过50次
            	</div>
            </div>
        </div>
    </div>
    <p align="center">
        <button type="submit" class="btn btn-warning" >确定</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/fw/toFwyjList'">返回</button>
    </p>

</div>
</form>
</body>

</html>
