<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="com.wonders.tiles.dic.DicDataUtils"%>
<form id="pagerForm" method="post" action="query/detailQuery/queryResult">
	<input type="hidden" name="pageNum" value="${obj.pager.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${obj.pager.pageSize}" />
	<input type="hidden" id="saveId" name="saveId" value="${obj.querySave.saveId }"/>
	<!-- 回传查询条件 -->
	<c:forEach var="queryColumn" items="${obj.conditionColList}">
		<c:set var="editType" value="${queryColumn.editType}"></c:set>
		<c:if test="${editType != 3}">
			<input type="hidden" name="${queryColumn.nameLetter}" value="${obj.conMap[queryColumn.nameLetter]}">
		</c:if>
		<c:if test="${editType == 3}">
			<c:set var="staDate" value="${queryColumn.nameLetter}staDATE"></c:set>
			<c:set var="endDate" value="${queryColumn.nameLetter}endDATE"></c:set>
			<input type="hidden" name="${staDate}" value="${obj.conMap[staDate]}" />
			<input type="hidden" name="${endDate}" value="${obj.conMap[endDate]}" />
		</c:if>			
	</c:forEach>	
</form>
<!-- 1、查询条件 -->
<div class="pageHeader">
	<form id="detailQueryForm" name="detailQueryForm" method="post" action="query/detailQuery/queryResult" onsubmit="return navTabSearch(this);">
		<input type="hidden" id="saveId" name="saveId" value="${obj.querySave.saveId }"/>
		<input type="hidden" id="exportFlag" name="exportFlag" id="exportFlag" value="">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<c:forEach var="queryColumn" items="${obj.conditionColList}">
						<c:set var="editType" value="${queryColumn.editType}"></c:set>
						<c:set var="staDate" value="${queryColumn.nameLetter}staDATE"></c:set>
						<c:set var="endDate" value="${queryColumn.nameLetter}endDATE"></c:set>
						<td>${queryColumn.name}</td>
						<td>
							<c:if test="${editType == 1 || editType == 5}">
								<input type="text" name="${queryColumn.nameLetter}" value="${obj.conMap[queryColumn.nameLetter]}">
							</c:if>
							<c:if test="${editType == 2 || editType == 4}">
								<wd:select name="${queryColumn.nameLetter}" dicCode="${queryColumn.dicId}" defaultValue="${obj.conMap[queryColumn.nameLetter]}" initValue="------"/>
							</c:if>	
							<c:if test="${editType == 3}">
								<input type="text" size="10" class="date" name="${staDate}" value="${obj.conMap[staDate]}" />
								至 
								<input type="text" size="10" class="date" name="${endDate}" value="${obj.conMap[endDate]}" />
							</c:if>			
						</td>
					</c:forEach>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>

<!-- 2、查询结果 -->
<div class="pageContent" layoutH="55">
	<!-- （1）工具条 -->
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="icon" onclick="queryExport()"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<!-- （2）显示列表 -->
	<table class="table" width="100%" layoutH="138">
	<thead>
	<tr>
		<th align="center">序号</th>
		<c:forEach var="queryColumn" items="${obj.resultColList}">
			<th align="center">${queryColumn.name}</th>
		</c:forEach>
		<c:if test="${not empty obj.refColList}">
			<th align="center" >钻取</th>
		</c:if>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="resultMap" items="${obj.rsList}" varStatus="vs">
		<tr>
			<td>${vs.index+1}</td>
			<c:forEach var="resultCol" items="${obj.resultColList}">
				<td>
				<c:if test="${not empty resultCol.drillLink}">
				<!-------------------特殊处理字段 begin------------------------------->
					<c:set var="drillKey" value="#${resultMap.key}#" />
					<c:if test="${fn:contains(resultCol.drillLink, drillKey)}">
						<c:set var="linkUrl" value="${fn:replace(resultCol.drillLink, drillKey, resultMap.key)}" />
						<a href="${linkUrl}" target="navTab" rel="linkURL">
							<c:if test="${resultCol.editType == 2 || resultCol.editType == 4}">
								<!-- 字典型 -->
								<wd:dicvalue dicId="${resultCol.dicId}" dicCode="${resultMap[resultCol.nameLetter]}"/>
							</c:if>
							<c:if test="${resultCol.editType != 2 && resultCol.editType != 4}">
								<!-- 非字典型 -->
								${resultMap[resultCol.nameLetter]}
							</c:if>
						</a>
					</c:if>
				<!-------------------特殊处理字段 end------------------------------->
				</c:if>
				<c:if test="${empty resultCol.drillLink}">
					<c:if test="${resultCol.editType == 2 || resultCol.editType == 4}">
						<!-- 字典型 -->
						<wd:dicvalue dicId="${resultCol.dicId}" dicCode="${resultMap[resultCol.nameLetter]}"/>
					</c:if>
					<c:if test="${resultCol.editType != 2 && resultCol.editType != 4}">
						<!-- 非字典型 -->
						${resultMap[resultCol.nameLetter]}
					</c:if>				
				</c:if>
				</td>
			</c:forEach>
			
			<!-------------------钻取 begin------------------------------->
			<c:forEach var="refCol" items="${obj.refColList}">
				<td>
					<c:set var="actionString" value="query/detailQuery/queryResult?guideFlag=1&saveId=${refCol.saveId}"></c:set>
					<!-- 取出所有钻取字段 -->
					<c:set var="conStrings" value="${fn:split(refCol.conCols, ',')}"></c:set>
					<c:set var="queryDic" value="79001" scope="request"></c:set>
					<c:forEach var="conString" items="${conStrings}">
						<c:set var="currentCon" value="${conString}" scope="request"></c:set>
						<!-- TODO 这里没有解决C标签嵌套自定义标签的问题,所以暂时用java代码实现功能 -->
						<c:set var="nameLetter" scope="request" value='<%=DicDataUtils.getInstance().getDicData(Integer.valueOf((String)request.getAttribute("queryDic")),(String)request.getAttribute("currentCon")) %>'></c:set>
						<c:set var="staDate" value="${nameLetter}staDATE"></c:set>
						<c:set var="endDate" value="${nameLetter}endDATE"></c:set>
						<!-- 从resultMap和conMap中取值,拼接到钻取链接中 -->
						<c:if test="${not empty resultMap}">
							<c:set var="actionString" value="${actionString}&${nameLetter}=${resultMap[nameLetter]}"></c:set>
						</c:if>
						<c:if test="${not empty obj.conMap[nameLetter]}">
							<c:set var="actionString" value="${actionString}&${nameLetter}=${obj.conMap[nameLetter]}"></c:set>
							nameLetter:${nameLetter}
						</c:if>	
						<c:if test="${not empty obj.conMap[staDate]}">
							<c:set var="actionString" value="${actionString}&${nameLetter}=${obj.conMap[staDate]}"></c:set>
						</c:if>
						<c:if test="${not empty obj.conMap[endDATE]}">
							<c:set var="actionString" value="${actionString}&${nameLetter}=${obj.conMap[endDATE]}"></c:set>
						</c:if>																				
					</c:forEach>
					<a href="${actionString}" target="navTab" rel="showQuery">${refCol.refName}</a>
				</td>
			</c:forEach>
			<!-------------------钻取 end------------------------------->
		</tr>
		</c:forEach>
	</tbody>
	</table>
	<!-- （3）分页 -->
	<jsp:include page="/common/dwz-pager.jsp" flush="true"/>
</div>

<script type="text/javascript">
//查询
function drillURL(winURL){
	//var allscreenwin =window.open(winURL,"","height=600,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
	//allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}
//调用连接
function linkURL(winURL){
	//var allscreenwin =window.open(winURL,"","height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
	//allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}
//查询
function searchResult(){
	$("#exportFlag").val('');
	$("#detailQueryForm").attr("action","query/detailQuery/queryResult");
	$("#detailQueryForm").submit();
}
function queryExport(){
	$("#exportFlag").val('1');
	document.detailQueryForm.submit();
}

</script>
