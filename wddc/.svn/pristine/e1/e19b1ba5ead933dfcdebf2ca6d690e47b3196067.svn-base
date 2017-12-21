<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<input type="hidden" id="js_ctx" value="${ctx}" />
<div class="container">
	<h3 id="disable-responsive2" class="page-header">服务调用详细日志</h3>

	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
			<div class="profile-info-name"> 服务名称 </div>

			<div class="profile-info-value">
				<span class="editable" id="username">${obj.fwInfo.fwName }</span>
			</div>
		</div>

		<div class="profile-info-row">
			<div class="profile-info-name"> UNIT_KEY </div>

			<div class="profile-info-value">
				<span class="editable" id="city">${obj.fwLog.unitKeyDisplay }</span>
			</div>
		</div>

		<div class="profile-info-row">
			<div class="profile-info-name"> METHOD_KEY </div>

			<div class="profile-info-value">
				<span class="editable" id="age">${obj.fwLog.methodKeyDisplay}</span>
			</div>
		</div>

		<div class="profile-info-row">
			<div class="profile-info-name"> 调用单位 </div>

			<div class="profile-info-value">
				<span class="editable" id="signup">${obj.fwLog.unitName}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 调用状态 </div>

			<div class="profile-info-value">
				<span class="editable" >
					<c:if test="${obj.fwLog.opStatus =='1'}">调用成功</c:if>
					<c:if test="${obj.fwLog.opStatus !='1'}">调用失败</c:if>
				</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 调用参数 </div>

			<div class="profile-info-value">
				<span class="editable" id="login">${obj.fwLog.opParam}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 返回结果数量 </div>

			<div class="profile-info-value">
				<span class="editable" id="about">${obj.fwLog.resultCount}</span>
			</div>
		</div>


		<div class="profile-info-row">
			<div class="profile-info-name"> 返回结果 </div>

			<div class="profile-info-value">
				<span class="editable" >${obj.fwLog.opResult}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 调用开始时间 </div>

			<div class="profile-info-value">
				<span class="editable" ><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.fwLog.startTime}"/></span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 调用结束时间 </div>

			<div class="profile-info-value">
				<span class="editable" ><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.fwLog.endTime}"/></span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 调用用时 </div>

			<div class="profile-info-value">
				<span class="editable" >${obj.fwLog.usedTime}（毫秒）</span>
			</div>
		</div>
	</div>
</div>


<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">

</script>
</body>
</html>
