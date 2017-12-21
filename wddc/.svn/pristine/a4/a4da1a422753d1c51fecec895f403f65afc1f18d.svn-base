<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>

<h4  class="page-header">填写申请原因</h4>
<div class="profile-user-info profile-user-info-striped">
<textarea id="applyReason" name="applyReason" class="form-control" rows="3"></textarea>
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
            <i class="fa fa-map-marker light-orange bigger-110"></i>
            <c:forEach  var="config"  items="${obj.fwConfigList}" >
                <c:if test="${config.configType == '2'}">
                    <span class="editable" id="country">${config.cnName}</span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>