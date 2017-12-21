<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />

<!-- 1、查询条件 -->
<div class="pageHeader">
	<form name="saveForm" method="post" action="query/statChart/toChartRef" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="selectRefId" id="selectRefId" value="${obj.selectRefId}"/>
		<input type="hidden" name="refType" id="refType" value="${obj.refType}"/>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<th height="30" align="center"> 
					<select id="vyShows" name="vyShows" onchange="query_ref()">
						<option value="2" <c:if test="${obj.refType == 2}"> selected </c:if> >图表钻取</option>
						<option value="1" <c:if test="${obj.refType == 1}"> selected </c:if> >查询钻取</option>
					</select>
				</tr>
			</table>
		</div>
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
	</form>
</div>

<!-- 2、查询结果 -->
<div class="pageContent" layoutH="120">
	<!-- （2）显示列表 -->
	<table class="table">
		<thead>
			<tr >
		  		<th width="10%"></th>
		  		<th width="10%">序号</th>
		  		<th width="30%">名称</th>
				<th width="30%">被引用名称</th>
				<th width="20%">使用热度</th>			
			</tr>
		</thead>
		<tbody>
			<c:if test="${obj.refType == 1}">
				<!-- 查询钻取 -->
				<c:forEach var="querySave" items="${obj.resultList}" varStatus="vs">
				<tr>
					<td>
						<input type="hidden" name="${querySave.saveId}" id="${querySave.saveId}" value="${querySave.name}"/> 
						<input type="radio" name="itemCheckBox" id="itemCheckBox" value="${querySave.saveId}" <c:if test="${querySave.saveId == obj.selectRefId}">checked</c:if> />
					</td>
					<td>${vs.index+1}</td>
			  		<td>${querySave.name}</td>
					<td>${querySave.refName}</td>
					<td>${querySave.queryCount}</td>
				</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${obj.refType != 1}">
				<!-- 图表钻取 -->
				<c:forEach var="chartSave" items="${obj.resultList}" varStatus="vs">
				<tr>
					<td>
						<input type="hidden" name="${chartSave.saveId}" id="${chartSave.saveId}" value="${chartSave.name}"/> 
						<input type="radio" name="itemCheckBox" id="itemCheckBox" value="${chartSave.saveId}" <c:if test="${chartSave.saveId == obj.selectRefId}">checked</c:if> />
					</td>
					<td>${vs.index+1}</td>
			  		<td>${chartSave.name}</td>
					<td>${chartSave.catalogName}</td>
					<td>${chartSave.queryCount}</td>
				</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>

<div class="formBar">
	<ul>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="button" onclick="query_save()">保存</button></div></div>
		</li>
	</ul>
</div>

<script type="text/javascript">
function query_save(){
	var selectValue = "";
	var selectName = "";
	var objsArray = document.getElementsByName("itemCheckBox");
	for(var i=0;i<objsArray.length;i++){
		if(objsArray[i].checked){
			if(selectValue == ""){
				selectValue = objsArray[i].value;
				selectName = document.getElementById(objsArray[i].value).value;
			} else{
				selectValue += ","+objsArray[i].value;
				selectName += ","+document.getElementById(objsArray[i].value).value;
			}
		}
	}
	var refType = "";
	if(selectValue ==""){
		refType ='0';
	}else{
		refType = $("#vyShows").val();
	}
	$.bringBack({selectRefId:selectValue,selectRefName:selectName,refType:refType});
}
//获取可钻取的定义列表
function query_ref(){
	$("#saveForm").attr("action","query/statChart/toChartRef");
	$("#saveForm").submit(); 
}
</script>