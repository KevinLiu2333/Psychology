<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>sample</title>
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/css/gzz.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
function toUpdatePwd(){
	window.showModalDialog("${ctx}/toPwd",self,"dialogWidth=550px;dialogHeight=400px;status:no;");
}

</script>


</head>

<div id="Container">
    <div id="Header">
    	<div id="shitop_x">
			<div class="topright"> 
				<ul>
			    <li><a href="#" onclick="toUpdatePwd()">修改密码</a></li>
			    <li><a href="${ctx }/logout" title="退出" target="_parent">退出</a></li>
			    </ul>
			    <div class="user">
			    	<span>${sessionScope.user.logonName}</span>
			    </div>  
			</div>
		</div>
	</div>
    </div>