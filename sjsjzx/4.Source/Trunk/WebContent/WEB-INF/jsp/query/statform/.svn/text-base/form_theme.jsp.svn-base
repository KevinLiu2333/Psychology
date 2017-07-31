<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!-- 1、查询条件  -->
<div class="pageHeader">
	<form id="queryForm" onsubmit="return navTabSearch(this);" action="query/statForm/toTheme" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>分类：
						 <select name="catalog" >
							 <option value=""  >--请选择--</option>
							 <c:forEach items="${obj.catalogList}" var="cataTheme" varStatus="row">
							 	<c:if test="${cataTheme.catalog == obj.catalog}">
							 		<option value="${cataTheme.catalog}" selected >${cataTheme.catalog}</option>	
							 	</c:if>
							 	<c:if test="${cataTheme.catalog != obj.catalog}">
							 		<option value="${cataTheme.catalog}">${cataTheme.catalog}</option>	
							 	</c:if>
							 </c:forEach>
						 </select>
					</td>
					<td>名称：<input type="text" name="saveName" value="${obj.saveName}"/></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>

<!-- 2、查询结果 -->
<div class="pageFormContent" layoutH="55">
	<c:forEach items="${obj.queryThemeList}" var="queryTheme" varStatus="row">
	<!-- （1）工具条 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li><span title="主题对应表名称">分类：${queryTheme.catalog} &nbsp;表名：${queryTheme.viewName}</span></li>
			<li class="line">line</li>
			<li><a class="add" href="query/statForm/toDefine?themeId=${queryTheme.themeId}" target="navTab" rel="queryDefine" title="添加表格统计"><span>添加</span></a></li>
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table  width="100%">
		<tr><td>
		<c:forEach items="${obj.saveList}" var="save" varStatus="saveRow">
			<c:if test="${save.themeId == queryTheme.themeId}">
				<div style="float: left;margin: 5px;width: 30%">
						<c:if test="${not empty obj.saveName && fn:contains(save.name,obj.saveName)}">
							<a href="query/statForm/toDefine?saveId=${save.saveId}" title="修改表格统计" target="navTab" rel="queryDefine"><span style="background-color:#ffff80;color:#ee6600">${save.name }${saveName}</span></a>
						</c:if>
						<c:if test="${ empty obj.saveName || !fn:contains(save.name,obj.saveName)}">
							<a href="query/statForm/toDefine?saveId=${save.saveId}" title="修改表格统计" target="navTab" rel="queryDefine"><span >${save.name }</span></a>
						</c:if>
						<a href="query/statForm/showForm?saveId=${save.saveId}"  target="navTab" rel="showQuery" title="预览表格统计">(预览)</a>
				</div>
			</c:if>
		</c:forEach>
		</td></tr>
	</table>
	</c:forEach>
</div>
