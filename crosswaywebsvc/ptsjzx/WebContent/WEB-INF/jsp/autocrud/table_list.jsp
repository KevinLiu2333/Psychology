<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a title="单表增删改查" target="navTab" rel="autocrudTableList" href="autocrud/reloadCache" class="edit"><span>重启缓存</span></a></li>
		</ul>
	</div>

	<table class="table" cellspacing="1" cellpadding="0" width="95%" layoutH="138">
		<thead>
			<tr>
				<th width="30%">业务分类</th>
				<th width="70%">业务表单名称</th>
			</tr>
		</thead>
		<!--列表页面-->
		<tbody>
			<c:forEach var="table" items="${obj.tableList}">
				<c:if test="${table.isLeaf == 0}">
				<!-- 只显示父表 -->
				<tr>
					<td>${table.themeType}</td>
					<td>
						<a href="autocrud/toTableDataList?tableId=${table.tableId}" target="navTab" rel="autocrudTableDataList">${table.name}</a>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</div>