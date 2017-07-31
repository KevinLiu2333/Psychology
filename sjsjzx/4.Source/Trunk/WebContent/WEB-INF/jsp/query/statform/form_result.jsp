<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<link href="${ctx }/tiles/query/styles/groupreport.css" rel="stylesheet" rev="stylesheet" type="text/css" />
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>
<!-- 1、查询条件 -->
<div class="pageHeader">
	<form id="statForm" name="statForm" method="post" action="query/statForm/showForm" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="drillType" value="${obj.drillType}" />
		<input type="hidden" name="drillId" value="${obj.drillId}" />
		<input type="hidden" name="saveId" value="${obj.formSave.saveId}" />
		<input type="hidden" name="catalogName" value="${obj.catalogName}" />
		<input type="hidden" name="showType" value="${obj.showType}" />
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

<!-------------------数据展示 start------------------------------->
<div class="pageFormContent" layoutH="55">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>报表</span></a></li>
					<li><a href="javascript:;"><span>图形</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:85%">
			<div>
					${obj.formString}
			</div>
			<div>
				<table width="93%" border="0" cellspacing="0" cellpadding="3" align="center">
					<tr> 
						<td valign="top" class="text" align="center">
							<!-- TODO DWZ框架中打开navTab页面中,如果有dom id相同的元素,会有问题 -->
							<div id="chartdiv2" align="center"></div>
							<script type="text/javascript">
								var catalogName = '${obj.catalogName}';
								var chartXmlString = "${obj.chartXmlString}";
								var swfString = "${ctx }/tiles/query/charts/" + catalogName + ".swf";
								var myChart=new FusionCharts(swfString,"myChartId","750","330");
								myChart.setDataXML(chartXmlString); 	   
								myChart.render("chartdiv2");
							</script>
						</td>
					</tr>
				</table>			
			</div>			
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
<!-------------------数据展示 end-------------------------->
