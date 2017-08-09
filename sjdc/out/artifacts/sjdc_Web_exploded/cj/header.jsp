<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<div class="navbar navbar-default navbar-fixed-top" id="mainnav" role="navigation">
    <div class="container">
        <div class="navbar-header">
			<c:if test="${sessionScope.session_user.userType == '普通用户'}">
            	<a class="navbar-brand" href="${ctx}/open.jsp" ><span class="navbar-title">${sys_title}</span></a>
            </c:if>
            <c:if test="${sessionScope.session_user.userType == '管理员'}">
            	<a class="navbar-brand" href="${ctx}/home/toHome" ><span class="navbar-title">${sys_title}</span></a>
            </c:if>
            
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="height: 50px"><a href="${ctx}/open.jsp"><span class="navbar-menu ">网站首页</span></a></li>
                <li style="background-color: #788BA0;height: 50px">
                <a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info" style="color:white;">
									功能菜单
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>

							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="${ctx}/index.jsp">
										<i class="ace-icon fa 	fa-home"></i>
										首页
									</a>
								</li>
								<li class="divider"></li>
								<li litype="indexsetting" >
									<a href="#" onclick="indexsetting()">
										<i class="ace-icon fa fa-cog" ></i>
										系统功能入口
									</a>
								</li>
								<li class="divider"></li>
								<li litype="indexsetting" >
									<a href="${ctx}/logout" >
										<i class="ace-icon fa fa-cog" ></i>
										退出登录
									</a>
								</li>
								<li class="divider"></li>
								<li>
									<a onclick="window.history.back(-1);">
										<i class="fa fa-reply" aria-hidden="true"></i>

										返回
									</a>
								</li>
							</ul>
                
                </li>
            </ul>
            
        </div><!--/.nav-collapse -->
    </div>
</div>