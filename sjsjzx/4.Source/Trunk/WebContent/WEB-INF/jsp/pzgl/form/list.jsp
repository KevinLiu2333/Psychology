<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js" ></script>
<form id="pagerForm" method="post" action="config/form/toFormList">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="filter_str_userId_eq" value="${filter.filter_str_userId_eq}" />
	<input type="hidden" name="filter_str_userId_eq" value="${filter.filter_str_userId_eq}" />
</form>
<script type="text/javascript">
</script>
<!-- 1、查询条件  -->
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx}/config/form/toFormList" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>快速查询：<input type="text" name="formName" value="${obj.formName}"/></td>
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
			<li><a class="add" href="${ctx }/config/form/toEditForm" target="navTab" rel="navTab_form_add"><span>新增表单</span></a></li>
			<li class="line">line</li>
			<!-- 2015-07-28 by weedlu
			<li><a class="add" href="config/form/toDesign" target="_blank" ><span>直接新增表单</span></a></li>	
			<li class="line">line</li>	
			 -->
			<li><a class="edit" href="${ctx }/config/form/toEditForm?id={sid_formConfig}" target="navTab" rel="navTab_form_add"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="${ctx }/config/form/toDelForm?id={sid_formConfig}" target="ajaxTodo" title="确定要删除吗？"><span>删除</span></a></li>	
			<li class="line">line</li>
			<li><a class="icon" href="${ctx }/config/form/toDesignEdit?dreamformId={sid_formConfig}" target="navTab" rel="navTab_form_add"><span>布局设计</span></a></li>					
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
				<th width="5%"></th>
				<th width="40%">表单名称</th>
				<th width="20%">创建人</th>
				<th width="20%">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${obj.list}" var="formConfig" varStatus="row">
				<tr target="sid_formConfig" rel="${formConfig.dreamformId}">
					<td align="right">${row.index + 1}</td>
					<td>${formConfig.dreamformName}</td>
					<td>${formConfig.createUser}</td>
					<td><fmt:formatDate value="${formConfig.createTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/pager.jsp"/>
</div>
