<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<form id="pagerForm" method="post" action="config/query/toQueryList">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="filter_str_roleDesc_like" value="${filter.filter_str_roleDesc_like}" />
</form>
<!-- 1、查询条件  -->
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }config/query/toQueryList" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>配置名称：<input type="text" name="filter_str_name_like" value="${filter.filter_str_name_like}"/></td>
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
			<li><a class="add" href="${ctx }/config/query/toEditQuery" target="navTab" rel="query" title="新增配置"><span>新增配置</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx }/config/query/toEditQuery?saveId={sid_query}" target="navTab" rel="query" title="修改配置"><span>修改配置</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些配置吗?" target="ajaxTodo" rel="query" href="${ctx }/config/query/toDelQuery?saveId={sid_query}" class="delete"><span>删除配置</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx }/config/query/toBindForm?saveId={sid_query}" target="navTab" rel="query" title="绑定表单"><span>绑定表单</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要取消表单绑定?" target="ajaxTodo" rel="query" href="${ctx }/config/query/cancelBindForm?saveId={sid_query}" class="delete"><span>取消绑定表单</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="${ctx }/config/query/toResult?saveId={sid_query}" target="navTab" rel="{sid_query}" title="结果预览"><span>结果预览</span></a></li>
			
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="3%">序号</th>
				<th>配置名称</th>
				<th>配置描述</th>
				<th>saveId</th>
				<th width="10%">配置时间</th>
				<th width="10%">配置人</th>
				<th width="10%">是否绑定表单</th>
				<th width="15%">配置库表名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${obj.list}" var="query" varStatus="row">
				<tr target="sid_query" rel="${query.saveId}">
					<td width="30">${row.index + 1 }</td>
					<td>${query.name}</td>
					<td>${query.saveDesc}</td>
					<td>${query.saveId}</td>
					<td><fmt:formatDate value="${query.saveDate }"/></td>
					<td>${query.userName}</td>
					<c:if test="${not empty query.formId}">
					<td>已绑定</td>
					</c:if>
					<c:if test="${ empty query.formId}">
					<td>未绑定</td>
					</c:if>
					<td><wd:dicvalue dicId="79002" dicCode="${query.themeId}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pager.jsp" flush="true"/>
</div>
