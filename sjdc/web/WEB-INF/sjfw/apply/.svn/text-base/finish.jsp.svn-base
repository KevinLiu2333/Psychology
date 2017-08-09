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
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">授权完成</h3>

    <div class="alert alert-success" role="alert">
        <strong>服务已开通!</strong> 服务接口访问KEY：${obj.fwApply.methodKey}
        <button type="button" class="btn btn-sm btn-warning" onclick="toExample('${obj.fwInfo.fwInfoId}')">调用示例查看</button>
    </div>


    <h4  class="page-header">服务基本信息</h4>
    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name"> 服务名称 </div>

            <div class="profile-info-value">
                <span class="editable" id="username">${obj.fwInfo.fwName}</span>
            </div>
        </div>

        <div class="profile-info-row">
        <div class="profile-info-name"> 公开类型 </div>

        <div class="profile-info-value">
            <span class="editable" id="city">${obj.fwInfo.openType}</span>
        </div>
	    </div>
	     <div class="profile-info-row">
	        <div class="profile-info-name"> 申请类型 </div>
	
	        <div class="profile-info-value">
	            <span class="editable" id="city">${obj.fwInfo.applyType}</span>
	        </div>
	    </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> 服务状态 </div>

            <div class="profile-info-value">
                <span class="editable" id="age">${obj.fwInfo.status}</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> 预警级别 </div>

            <div class="profile-info-value">
                <span class="editable" id="signup">${obj.fwInfo.alertLevel}</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> 服务标签 </div>

            <div class="profile-info-value">
                <span class="editable" id="login">${obj.fwInfo.tagList}</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> 服务描述 </div>

            <div class="profile-info-value">
                <span class="editable" id="about">${obj.fwInfo.fwAbstract}</span>
            </div>
        </div>
    </div>


    <h4  class="page-header">服务数据信息</h4>
    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name">服务条件项</div>

            <div class="profile-info-value">
                <c:forEach  var="config"  items="${obj.fwConfigList}" >
                    <c:if test="${config.configType == '1'}">
                        <span class="editable" id="country">${config.cnName}</span>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name">服务数据项</div>

            <div class="profile-info-value">
                <c:forEach  var="config"  items="${obj.fwConfigList}" >
                    <c:if test="${config.configType == '2'}">
                    	<c:if test="${config.opType == 'COUNT'}">
                        	数量
                        </c:if>
                        <c:if test="${config.opType != 'COUNT'}">
                        	<span class="editable" id="country">${config.cnName}</span>
                        </c:if>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    //服务申请
    function toExample(fwInfoId){
        window.open("${ctx}/fw/example?fwInfoId="+fwInfoId);
    }
</script>
</html>
