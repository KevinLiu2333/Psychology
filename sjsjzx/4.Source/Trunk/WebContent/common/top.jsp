<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="header">
	<img class="logo" src="${ctx}/skins/home/images/logo_01.png">
	
    <ul>
        
    	<li><iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=1" width="330" height="35" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe></li>
    	 <li >&nbsp;</li>
    	<li><span id="userName" ></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:toLogin()" onFocus="this.blur();"><img src="${ctx}/skins/home/images/nav_m03.png" style="vertical-align:middle;" >重新登录</a></li>
    </ul>
</div>
<div class="nav">
	<div class="nav_topline"></div>
  
</div>
<script type="text/javascript">
function toLogin(){
	window.location.href="${ctx}/logout";
}
$(document).ready(function(){
	$("#userName").html("欢迎您，"+getCookie("dreamhome_display_name"));
});
</script>