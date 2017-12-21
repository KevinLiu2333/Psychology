<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="autocrud/toTableDataList?tableId=${obj.table.tableId}">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" name="fkId" value="${obj.fkId}">
	<input type="hidden" name="pkKeyValue" value="">
	<!-- 回传查询条件 -->
	<c:forEach var="queryColumn" items="${obj.queryList}">
		<c:set var="Qname" value="Q_${queryColumn.colId}"></c:set>
		<c:if test="${queryColumn.dataType != 3}">
			<!-- 非时间型 -->
			<input type="hidden" name="Q_${queryColumn.colId}" value="${obj.parameterMap[Qname]}"/>
		</c:if>
		<c:if test="${queryColumn.dataType == 3}">
			<c:set var="QnameS" value="Q_${queryColumn.colId}_S"></c:set>
			<c:set var="QnameE" value="Q_${queryColumn.colId}_E"></c:set>
			<!-- 时间型 -->
			<input type="hidden" name="${QnameS}" value="${obj.parameterMap[QnameS]}"></input>
			<input type="hidden" name="${QnameE}" value="${obj.parameterMap[QnameE]}"></input>  
		</c:if>
	</c:forEach>
</form>
<!-- 1、查询条件  -->
<div class="pageHeader">
	<form name="pageSearchForm" onsubmit="return navTabSearch(this);" action="autocrud/toTableDataList?tableId=${obj.table.tableId}" method="post">
		<input type="hidden" name="fkId" value="${obj.fkId}">
		<input type="hidden" name="pkKeyValue" value="">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				<c:forEach var="queryColumn" items="${obj.queryList}">
					<c:set var="Qname" value="Q_${queryColumn.colId}"></c:set>
					<td>${queryColumn.name}
					<c:if test="${queryColumn.dataType == 1}">
						<!-- 字符型 -->
						<input id="Q_${queryColumn.colId}" type="text" name="Q_${queryColumn.colId}" maxlength="${queryColumn.dataLength}" value="${obj.parameterMap[Qname]}"/>
					</c:if>
					<c:if test="${queryColumn.dataType == 2}">
						<!-- 数值型 -->
						<input id="Q_${queryColumn.colId}" type="text" name="Q_${queryColumn.colId}" maxlength="${queryColumn.dataLength}" class="number" value="${obj.parameterMap[Qname]}"/>
					</c:if>
					<c:if test="${queryColumn.dataType == 3}">
						<c:set var="QnameS" value="Q_${queryColumn.colId}_S"></c:set>
						<c:set var="QnameE" value="Q_${queryColumn.colId}_E"></c:set>
						<!-- 时间型 -->
						<input id="Q_${queryColumn.colId}_S" type="text" class="date" name="Q_${queryColumn.colId}_S" value="${obj.parameterMap[QnameS]}"></input>
						<input id="Q_${queryColumn.colId}_E" type="text" class="date" name="Q_${queryColumn.colId}_E" value="${obj.parameterMap[QnameE]}"></input>  
					</c:if>
					<c:if test="${queryColumn.dataType == 4}">
						<!-- 字典型 -->
						<wd:select id="Q_${queryColumn.colId}" name="Q_${queryColumn.colId}" dicCode="${queryColumn.dicId}" defaultValue="${obj.parameterMap[Qname]}" initValue="------"/>
					</c:if>
					</td>						
				</c:forEach>
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
			<li><a class="add" href="autocrud/toAddRow?tableId=${obj.table.tableId}&fkId=${obj.fkId}" target="navTab" rel="autocrudTableDataInfo" title="新增数据"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="autocrud/toUpdateRow?tableId=${obj.table.tableId}&${obj.pkCol.nameLetter}={pk_id}" target="navTab" rel="autocrudTableDataInfo" title="修改数据"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="${obj.pkCol.nameLetter}" href="autocrud/toDeleteRow?tableId=${obj.table.tableId}" class="delete"><span>删除</span></a></li>
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="138">
	<thead>
		<tr>
			<th width="15"></th>
<!--			<th width="26" group="${pkCol.nameLetter}"></th>-->
			<th width="26"><input type="checkbox" group="${obj.pkCol.nameLetter}" class="checkboxCtrl"></th>
				<c:forEach var="resultColumn" items="${obj.resultList}">
				<c:if test="${resultColumn.isShow == 1}">
				<!-- 取出所有要展示的列 -->
					<th>
						${resultColumn.name}
						<c:if test="${not empty resultColumn.dataUnit}">
							(${resultColumn.dataUnit})
						</c:if>
					</th>
				</c:if>
				</c:forEach>
			<th>
				关联表
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${obj.formList}" varStatus="vs">
		<tr target="pk_id" rel="${row[obj.pkCol.nameLetter]}">
			<td>${vs.index+1}</td>
			<td><input name="${obj.pkCol.nameLetter}" value="${row[obj.pkCol.nameLetter]}" type="checkbox"></td>
			<c:forEach var="resultColumn" items="${obj.resultList}">
				<c:if test="${resultColumn.isShow == 1}">
				<!-- 取出所有要展示的列 -->
				<td>
					<c:if test="${resultColumn.dataType == 1}">
						${row[resultColumn.nameLetter]}
					</c:if>
					<c:if test="${resultColumn.dataType == 2}">
						${row[resultColumn.nameLetter]}
					</c:if>
					<c:if test="${resultColumn.dataType == 3}">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${row[resultColumn.nameLetter]}" />
					</c:if>
					<c:if test="${resultColumn.dataType == 4}">
						<wd:dicvalue dicId="${resultColumn.dicId}" dicCode="${row[resultColumn.nameLetter]}"/>
					</c:if>	
				</td>
				</c:if>									
			</c:forEach>
			<td>
				<c:forEach var="subTable" items="${obj.subTableList}">
					<a target="navTab" rel="autocrudTableDataList" href="autocrud/toTableDataList?tableId=${subTable.tableId}&fkId=${row[obj.pkCol.nameLetter]}">${subTable.name}</a>
				</c:forEach>
			</td>			
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/dwz-pager.jsp" flush="true"/>
</div>
