<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js" ></script>
<form id="pagerForm" method="post" action="config/table/toTableList">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="filter_str_userId_eq" value="${filter.filter_str_userId_eq}" />
	<input type="hidden" name="filter_str_userId_eq" value="${filter.filter_str_userId_eq}" />
</form>
<script type="text/javascript">
</script>
<!-- 1、查询条件  -->
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/config/table/toTableList" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>快速查询：<input type="text" name="tableName" value="${obj.tableName}"/></td>
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
<div class="pageContent">
<!-- （1）工具条 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx }/config/table/toEditTable" target="navTab" rel="navTab_tablemanage_add"><span>添加</span></a></li>
			<li><a class="add" href="${ctx }/config/table/toNameInput" target="navTab" rel="navTab_name_input"><span>反向生成</span></a></li>	
			<li><a class="edit" href="${ctx }/config/table/toEditTable?id={sid_queryTheme}" target="navTab" rel="navTab_tablemanage_add"><span>修改</span></a></li>
			<li><a class="delete" href="${ctx }/config/table/toDelTable?id={sid_queryTheme}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>				
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="5%"></th>
				<th width="30%">表名</th>
				<th width="50%">表描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${obj.list}" var="tableConfig" varStatus="row">
				<tr target="sid_queryTheme" rel="${tableConfig.themeId }">
					<td align="right">${row.index + 1 }</td>
					<td>${tableConfig.viewName}</td>
					<td>${tableConfig.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pzgl/pager.jsp"/>
</div>
