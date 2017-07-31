<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
    <nav class="main-navigation" style="height:80px">
        <div class="container" style="background-image: url('${ctx}/sjdc/open/imgs/header_bg_02.png');background-repeat:no-repeat;">
            <div class="row">
                <div class="col-sm-12">
                    <div class="navbar-header" style="padding-top: 10px;">
                       <img src="${ctx}/sjdc/open/imgs/logo.png" width="100%" height="100%" alt=""/>
                    </div>
                    
                    <div class="collapse navbar-collapse" id="main-menu" style="float: right;font-size: 19px">
                        <ul class="menu">
							<li  class="nav-current" role="presentation"><a href="${ctx}/open.jsp">首页</a></li>
							<c:if test="${not empty sessionScope.session_user}">
							<li class="nav-current" role="presentation"><a href="${ctx}/login.jsp">退出</a></li>
							</c:if>
							<c:if test="${empty sessionScope.session_user}">
								<li class="nav-current" role="presentation"><a href="${ctx}/login.jsp">平台登录</a></li>
							</c:if>
						</ul>   
                    </div>
                </div>
            </div>
        </div>
    </nav>