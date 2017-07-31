<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<style type="text/css">
	.navbar-default .navbar-nav>.open>a,.navbar-default .navbar-nav>.open>a:focus,.navbar-default .navbar-nav>.open>a:hover {
			color:#555;
			background-color:rgba(44, 54, 68, 0.44);
		}
	.nav.nav-tabs.padding-12 {
	    padding-left: 12px;
	}
	.nav-tabs.background-blue {
	    background-color: #eff3f8;
	    border: 1px solid #c5d0dc;
	    padding-top: 6px;
	}
	.nav-tabs {
	    margin-bottom: 0;
	    margin-left: 0;
	    position: relative;
	    top: 1px;
	}
	.nav {
	    list-style: outside none none;
	}
</style>
<script type="text/javascript">
	function zyIndex(){
		$.post("${ctx}/suite/log/logOp",{catalog:"cz",logType:"cz002"},function(){
			window.location.href="${ctx}/zy/toZyIndex";
		});
	}
</script>
<div class="navbar navbar-default navbar-fixed-top" id="mainnav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${ctx}/into?jspPath=home" ><span class="navbar-title">数据综合管理平台</span></a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav" style="padding-left:100px">
                <c:if test="${sessionScope.session_user.userType =='管理员'}">
                	<li style="width:160px;text-align:center" onclick="zyIndex()"><a href="#"><span class="navbar-menu" style="font-size:16px">资源目录</span></a></li>
	                <li style="width:160px;text-align:center">
	                	<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="navbar-menu" style="font-size:16px">展现分析</span></a>
	                	<ul class="dropdown-menu" style="width:150px;">
	                		<li style="text-align:center"><a href="${ctx}/into?jspPath=zxfx.tbfx.rkxxfx">图表分析</a></li>
	          				<li role="separator" class="divider"></li>
					        <li style="text-align:center"><a href="${ctx}/into?jspPath=zxfx.bbfx.rkbbfx">报表分析</a></li>
					    </ul>
	                </li><!-- End 展现分析 -->
	                <li class="dropdown" style="width:160px;text-align:center">
	                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="navbar-menu" style="font-size:16px">综合监管</span></a>
	                	<ul class="dropdown-menu" style="width:150px;">
	          				<li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.dljk">登录监控</a></li>
	          				<li role="separator" class="divider"></li>
					        <li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.czjk">操作监控</a></li>
					        <li role="separator" class="divider"></li>
					        <li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.jhjgck">交换结果查看</a></li>
					    </ul>
	                </li>
	                <li style="width:150px;text-align:center"><a href="${ctx}/into?jspPath=htgl"><span class="navbar-menu" style="font-size:16px">后台管理</span></a></li>
                	<c:if test="${sessionScope.session_user.userType != null}">
                		<li><a href="${ctx }/logout"><span class="navbar-menu ">欢迎使用，${sessionScope.session_user.displayName}</span></a></li>
                	</c:if>
                </c:if>
                <c:if test="${sessionScope.session_user.userType != '管理员'}">
                	<li style="width:180px;text-align:center"  onclick="zyIndex()"><a href="#"><span class="navbar-menu" style="font-size:16px">资源目录</span></a></li>
	                <li class="dropdown" style="width:180px;text-align:center">
	                	<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="navbar-menu" style="font-size:16px">展现分析</span></a>
	          				<ul class="dropdown-menu" style="width:180px;">
		          				<li style="width:180px;text-align:center"><a href="${ctx}/into?jspPath=zxfx.tbfx.rkxxfx">图表分析</a></li>
		          				<li role="separator" class="divider"></li>
						        <li style="width:180px;text-align:center"><a href="${ctx}/into?jspPath=zxfx.bbfx.rkbbfx">报表分析</a></li>
						    </ul>
	                </li><!-- End 展现分析 -->
	                <li class="dropdown" style="width:180px;text-align:center">
	                	<a href="#" class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="navbar-menu" style="font-size:16px">综合监管</span></a>
	                	<ul class="dropdown-menu" style="width:180px;">
	          				<li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.dljk">登录监控</a></li>
	          				<li role="separator" class="divider"></li>
					        <li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.czjk">操作监控</a></li>
					        <li role="separator" class="divider"></li>
					        <li style="text-align:center"><a href="${ctx}/into?jspPath=zhjg.jhjgck">交换结果查看</a></li>
					    </ul>
	                </li>
	                <c:if test="${sessionScope.session_user.userType != null}">
                		<li><a href="${ctx }/logout"><span class="navbar-menu ">欢迎使用，${sessionScope.session_user.displayName}</span></a></li>
                	</c:if>
                </c:if>
                <c:if test="${sessionScope.session_user.userType == null}">
                	<li style="width:180px;text-align:center"><a href="${ctx}/into?jspPath=index"><span class="navbar-menu" style="font-size:16px">登录</span></a></li>
                </c:if>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>