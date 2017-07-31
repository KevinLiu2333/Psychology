<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<div class="panelBar" style="margin-bottom: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">${obj.querySave.name}</span></li>
	</ul>
</div>
<!-------------------数据显示 begin------------------------------->
<br>
<div class="pageFormContent" layoutH="95" width="95%">
<table width="80%" class="table">
	<c:forEach var="resultMap" items="${obj.resultList}">
		<c:forEach var="queryColumn" items="${obj.resultColList}">
			<tr height="25">
				<td align="center" width="20%">${queryColumn.name}</td>
				<c:if test="${not empty resultMap[queryColumn.nameLetter]}">
					<c:if test="${queryColumn.editType == 2 || queryColumn.editType == 4}">
						<!-- 字典型 -->
						<td style="text-align:left;"><wd:dicvalue dicId="${queryColumn.dicId}" dicCode="${resultMap[queryColumn.nameLetter]}"/></td>
					</c:if>
					<c:if test="${queryColumn.editType != 2 && queryColumn.editType != 4}">
						<!-- 其它类型 -->
						<td style="text-align:left;">${resultMap[queryColumn.nameLetter]}</td>
					</c:if>							
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td height="20" colspan="2"></td>
		</tr>		
	</c:forEach>
</table>
</div>
<!-------------------数据显示 end------------------------------->
<!-- 操作工具栏  -->
<div class="formBar">
	<ul>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
		</li>
	</ul>
</div>
