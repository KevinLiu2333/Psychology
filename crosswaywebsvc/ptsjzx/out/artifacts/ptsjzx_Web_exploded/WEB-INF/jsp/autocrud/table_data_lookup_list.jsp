<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<form id="pagerForm" method="post" action="autocrud/toTableDataLookUpList?tableId=${obj.table.tableId}&fkName=${obj.fkName}">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
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
	<form name="pageSearchForm" onsubmit="return dwzSearch(this, 'dialog');" action="autocrud/toTableDataLookUpList?tableId=${obj.table.tableId}&fkName=${obj.fkName}" method="post">
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
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="138">
	<thead>
		<tr>
			<th width="26"></th>
			<c:forEach var="resultColumn" items="${obj.resultList}">
			<c:if test="${resultColumn.nameLetter != obj.pkCol.nameLetter}">
			<!-- 取出所有不为主键的列 -->
				<th>
					${resultColumn.name}
					<c:if test="${not empty resultColumn.dataUnit}">
						(${resultColumn.dataUnit})
					</c:if>
				</th>
			</c:if>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="row" items="${obj.formList}">
		<tr target="pk_id" rel="${row[obj.pkCol.nameLetter]}">
			<td>
				<a class="btnSelect" href="javascript:$.bringBack({${obj.fkName}:'${row[obj.pkCol.nameLetter]}'})" title="查找带回">选择</a>
			</td>
			<c:forEach var="resultColumn" items="${obj.resultList}">
				<c:if test="${resultColumn.nameLetter != obj.pkCol.nameLetter}">
				<!-- 取出所有不为主键的列 -->
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
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- （3）分页 -->
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> 
			<select class="combox" onchange="dwzPageBreak({targetType:'dialog', data:{numPerPage:this.value}})">
				<c:forEach begin="10" end="40" step="10" varStatus="s">
					<option value="${s.index}" ${obj.pager.pageSize eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
				</c:forEach>
			</select>
			<span>条，共${obj.pager.recordCount}条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${obj.pager.recordCount}" numPerPage="${obj.pager.pageSize}" pageNumShown="10" currentPage="${obj.pager.pageNumber}"></div>
	</div>
</div>
