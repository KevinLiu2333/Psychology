<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<div class="easyui-accordion" fit="true" border="false">
<c:forEach items="${menu}" var="menu" >
<c:if test="${menu.nodeLevel == '1' }">
	<div title="${menu.nodeDesc}" style="overflow:auto;">
		<ul>
			<c:forEach items="${submenu}" var="submenu" >
				<c:if test="${submenu.nodePid == menu.nodeId }">
				<li><a href="javascript:void(0);" onclick="tab2Menu('${submenu.nodeDesc}','#')">${submenu.nodeDesc}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</c:if>
</c:forEach>
<div title="系统设置" style="overflow:auto;">  
	<ul>
   		<li><a href="javascript:void(0);" onclick="tab2Menu('centent_tabs','用户管理','user/page/list')">用户管理</a></li>
   		<li><a href="javascript:void(0);" onclick="tab2Menu('centent_tabs','角色管理','role/page/list')">角色管理</a></li>
   		<li><a href="javascript:void(0);" onclick="tab2Menu('centent_tabs','权限管理','authority/page/list')">权限管理</a></li>
	</ul>
</div>
</div>
