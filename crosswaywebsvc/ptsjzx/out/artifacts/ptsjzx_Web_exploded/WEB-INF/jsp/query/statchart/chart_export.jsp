<%@ page contentType="application/vnd.ms-excel;charset=UTF-8" %>
 <%response.setHeader("Content-Disposition","attachment; filename=ImportWork.xls");%>
    <%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.wonders.tiles.dic.DicDataUtils"%>
<!-- tab 页必须引入结束-->
<link href="${ctx }/skins/css/query.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
</head>
<!-- 1、查询条件 -->
<c:if test="${obj.conditionColList!= null && fn:length(obj.conditionColList) != 0}">
	<form id="detailQueryForm" name="detailQueryForm" method="post" action="query/statChart/showChart" >
		<input type="hidden" name="saveId" value="${obj.chartSave.saveId}" />
		<input type="hidden" name="catalogName" name="catalogName" value="${obj.chartSave.catalogName}" />
		<input type="hidden" name="showTotal" value="${obj.showTotal}" />
		<input type="hidden" name="showType" value="${obj.showType}" />
		<center>
		<table width="750px" border="0" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
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
<!-------------------数据展示 start------------------------------->
	
			
			<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>图表数据信息</b></p>
			<center>
				<table align="center" class="table_multilist"  id="table_list" width="750px" style="align:center">
					<tr >
						<th align="center">${obj.chartSave.xaxisName}</th>
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
						<th align="center" class="resultTableTitle" style=";font-weight:bold">
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
				</center>
<!-------------------数据展示 end-------------------------->
<br></br>
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
			total = accAdd(total,data_table.rows[j].cells[i].innerHTML,0);
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
</html>