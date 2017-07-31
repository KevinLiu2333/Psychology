<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<div class="navbar navbar-default navbar-fixed-top" id="mainnav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${ctx}/main/toMain" ><span class="navbar-title">万达信息技术平台</span></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="navbar-menu ">欢迎使用，${sessionScope.session_user.displayName}</span></a></li>
                <li ><a href="${ctx}/logout" class="navbar-menu"><span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
            
        </div><!--/.nav-collapse -->
    </div>
</div>