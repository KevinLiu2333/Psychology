<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />

<!-- 2、查询结果 -->
<div class="pageContent" layoutH="55">
	<!-- （2）显示列表 -->
	<table class="table">
		<thead>
		<tr>
			<th width="10%"></th>
			<th width="10%">序号</th>
			<th width="30%">名称</th>
			<th width="30%">被引用名称</th>
			<th width="20%">使用热度</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="querySave" items="${obj.resultList}" varStatus="vs">
			<tr>
				<td>
					<input type="hidden" name="${querySave.saveId}" id="${querySave.saveId}" value="${querySave.name}"/> 
					<input type="checkbox" name="itemCheckBox" id="itemCheckBox" value="${querySave.saveId}" <c:if test="${not empty obj.refMap[querySave.saveId]}">checked</c:if> />
				</td>
				<td>${vs.index+1}</td>
		  		<td>${querySave.name}</td>
				<td>${querySave.refName}</td>
				<td>${querySave.queryCount}</td>
			</tr>
			</c:forEach>		
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
function query_save() {
	var selectValue = "";
	var selectName = "";
	var objsArray = document.getElementsByName("itemCheckBox");
	for (var i = 0; i < objsArray.length; i++) {
		if (objsArray[i].checked) {
			if (selectValue == "") {
				selectValue = objsArray[i].value;
				selectName = document.getElementById(objsArray[i].value).value;
			} else {
				selectValue += "," + objsArray[i].value;
				selectName += "," + document.getElementById(objsArray[i].value).value;
			}
		}
	}
	$.bringBack({
		selectRefIds: selectValue,
		selectRefNames: selectName
	});
}

</script>