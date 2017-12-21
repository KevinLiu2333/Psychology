<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!-- tab 页必须引入开始-->

<%@page import="com.wonders.tiles.dic.DicDataUtils"%><link href="${ctx }/tiles/query/styles/tab.webfx.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="${ctx }/tiles/query/scripts/tabpane.js"></script>
<!-- tab 页必须引入结束-->
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>

<!-- 1、查询条件 -->
<div class="pageHeader">
	<form id="detailQueryForm" name="detailQueryForm" method="post" action="query/statChart/showChart" onsubmit="return navTabSearch(this);">
		<input type="hidden" name="saveId" value="${obj.chartSave.saveId}" />
		<input type="hidden" name="catalogName" name="catalogName" value="${obj.chartSave.catalogName}" />
		<input type="hidden" name="showTotal" value="${obj.showTotal}" />
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
<div class="pageFormContent" layoutH="55" >
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>图形</span></a></li>
					<li><a href="javascript:;"><span>报表</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:85%">
			<div>
				<table width="93%" border="0" cellspacing="0" cellpadding="3" align="center">
					<tr> 
						<td valign="top" class="text" align="center">
							<div id="chartdiv" align="center" style="z-index: -1"></div>
							<script type="text/javascript">
								var catalogName = '${obj.chartSave.catalogName}';
								var chartXmlString = "${obj.xmlString}";
								var swfString = "${ctx }/tiles/query/charts/" + catalogName + ".swf";
								var myChart=new FusionCharts(swfString,"myChartId","750","330");
								myChart.setTransparent(true);
								myChart.setDataXML(chartXmlString); 	
								myChart.render("chartdiv");
							</script>
						</td>
					</tr>
				</table>			
			</div>
			<div>
				<table border="0" align="center" cellpadding="0" cellspacing="1" id="table_list" class="table_list2" width="93%">
					<tr >
						<td style="color:#0982e1;font-weight:bold">${obj.chartSave.xaxisName}</td>
						<c:forEach var="yGroupName" items="${obj.yGroupNames}" varStatus="vs">
							<th align="center">${obj.yGroupNames[vs.index]}</th>
						</c:forEach>
					</tr>
					<c:set var="queryDic" value="79001" scope="request"></c:set>
					
					
					<c:forEach var="chartDataMap" items="${obj.chartDataList}">
					
					<tr>
						<c:set var="xGroupCode" value="${obj.chartSave.xgroup}" scope="request"></c:set>
						<!-- TODO 这里没有解决C标签嵌套自定义标签的问题,所以暂时用java代码实现功能 -->
						<c:set var="editType" value="${obj.groupColumn.editType}"></c:set>
						<c:set var="xGroup" value='<%=DicDataUtils.getInstance().getDicItemName(Integer.valueOf((String)request.getAttribute("queryDic")),(String)request.getAttribute("xGroupCode")) %>' />
						<th align="center" class="resultTableTitle" style="color:#024e88;font-weight:bold">
						<c:if test="${editType == 2 || editType == 4}">
							<!-- 字典型 -->
							${chartDataMap[xGroup]}
						</c:if>
						<c:if test="${editType == 5}">
							<!-- 数值型 -->
							<fmt:formatNumber value="${chartDataMap[xGroup]}" pattern="#.##"/>
						</c:if>
						<c:if test="${editType == 1}">
							<!-- 字符型 -->
							${chartDataMap[xGroup]}
						</c:if>
						</th>
						<c:forEach var="yGroup" items="${obj.yGroups}">
							<td align="center" style="background: #ffffff;color:#024e88;font-weight:bold" >
								<c:if test="${not empty chartDataMap[yGroup]}">
									<fmt:formatNumber value="${chartDataMap[yGroup]}" pattern="#.##"/>
								</c:if>
								<c:if test="${empty chartDataMap[yGroup]}">
									0.00
								</c:if>
							</td>
						</c:forEach>
					</tr>						
					</c:forEach>
					<tr>
						<th align="center">合计</th>
						<c:forEach var="yGroupName" items="${obj.yGroupNames}">
							<td align="center" style="background: #ffffff;color:#024e88;font-weight:bold" >&nbsp;</td>
						</c:forEach>
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

<script type="text/javascript">

function selectCatalog(objs){
	document.getElementById("catalogName").value=objs.value;
	$("#detailQueryForm").attr("action","query/statChart/showChart");
	$("#detailQueryForm").submit(); 
}

//查询
function drillURL(winURL){
	//$.pdialog.open(winURL, 'showChart_drill', '数据钻取',{width:800,height:460});
	navTab.openTab('showQuery', winURL, { title:'数据钻取'});


}
computeTotal();
//计算合计信息
function computeTotal(){
	var data_table = document.getElementById("table_list");
	var row_length = data_table.rows.length;
	var col_length = data_table.rows[0].cells.length;
	for(var i=1;i<col_length;i++){
		var total = 0;
		for(var j=1;j<row_length-1;j++){
			total = accAdd(total,data_table.rows[j].cells[i].innerHTML,2);
		}
		data_table.rows[row_length-1].cells[i].innerHTML = total;
	}
}
//加法函数，用来得到精确的加法结果 
//返回值：arg1加上arg2的精确结果
//fix 表示返回小数精度，默认是2位
function accAdd(arg1,arg2,fix){
	var fixNum = 2;
	if(accAdd.arguments.length > 2){fixNum = fix}
	if(!isNaN(arg1) && !isNaN(arg2)){
		var r1,r2,m;
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
		m=Math.pow(10,Math.max(r1,r2))
		return ((arg1*m+arg2*m)/m) .toFixed(fixNum)
	}else return 0
}
</script>
