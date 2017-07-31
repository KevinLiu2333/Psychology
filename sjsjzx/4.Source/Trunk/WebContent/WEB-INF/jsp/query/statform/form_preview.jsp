<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<link href="${ctx }/tiles/query/styles/groupreport.css" rel="stylesheet" rev="stylesheet" type="text/css" />
<link href="${ctx }/skins/css/query.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>
</head>
<!-- 1、查询条件 -->

<!-- 1、查询条件 -->
<c:if test="${obj.conditionColList!= null && fn:length(obj.conditionColList) != 0}">
	<form id="statForm" name="statForm" method="post" action="query/statForm/showForm" >
		<input type="hidden" name="drillType" value="${obj.drillType}" />
		<input type="hidden" name="drillId" value="${obj.drillId}" />
		<input type="hidden" name="saveId" value="${obj.formSave.saveId}" />
		<input type="hidden" name="catalogName" value="${obj.catalogName}" />
		<input type="hidden" name="showType" value="${obj.showType}" />
		<center>
		<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
				<tr height="40px">
				<td>
					<c:forEach var="queryColumn" items="${obj.conditionColList}">
						<c:set var="editType" value="${queryColumn.editType}"></c:set>
						<c:set var="staDate" value="${queryColumn.nameLetter}staDATE"></c:set>
						<c:set var="endDate" value="${queryColumn.nameLetter}endDATE"></c:set>
						${queryColumn.name}:<c:if test="${editType == 1 || editType == 5}">
								<input type="text" class="dfinput"  name="${queryColumn.nameLetter}" value="${obj.conMap[queryColumn.nameLetter]}">
							</c:if>
							<c:if test="${editType == 2 || editType == 4}">
								<wd:select name="${queryColumn.nameLetter}" dicCode="${queryColumn.dicId}" defaultValue="${obj.conMap[queryColumn.nameLetter]}" initValue="------"/>
							</c:if>	
							<c:if test="${editType == 3}">
								<input type="text" class="textinput" size="10" class="date" name="${staDate}" value="${obj.conMap[staDate]}" />
								至 
								<input type="text" class="textinput" size="10" class="date" name="${endDate}" value="${obj.conMap[endDate]}" />
							</c:if>			
						
					</c:forEach>
					</td>
				</tr>
				<tr height="40px">
					<td align="right"><button type="submit"  class="smallButton" >查询</button>&nbsp;&nbsp;</td>
				</tr>
			</table>
			</center>
	</form>
</c:if>
<br></br>


<center>
					${obj.formString}
			</center>
<!-------------------数据展示 end-------------------------->
</html>