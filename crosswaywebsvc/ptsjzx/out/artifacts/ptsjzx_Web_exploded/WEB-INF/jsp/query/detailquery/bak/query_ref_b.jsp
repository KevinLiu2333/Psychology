<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />


<%
	Map<String,Object> objMap = (Map)request.getAttribute("obj");
	String selectRefIds = (String)objMap.get("selectRefIds");
	List saveList = (List)objMap.get("resultList");
	Map refMap = new HashMap();
	if(StringUtils.isNotBlank(selectRefIds)){
		String[] refStrings = selectRefIds.split(",");
		for(int i=0;i<refStrings.length;i++) refMap.put(refStrings[i],refStrings[i]);
	}
%>
<body>
<br>
<h3 class="bz_title"> 可引用定义列表</h3>
	<div  style="width:100%; height:26px; z-index:1; " align="center">
		<table class="table_list1" cellspacing="1" cellpadding="0" width="100%">
		<tr>
		  <th width="10%"></th>
		  <th width="10%"> 序号</th>
		  <th width="30%">名称</th>
		  <th width="30%">被引用名称</th>
		  <th width="20%">使用热度</th>
		</tr>
		</table>
	</div>
	<div  style="width:100%; height:280px; z-index:1; overflow-x:hidden;overflow-y:scroll;" align="center" id="topDiv" >
			<table class="table_list1" cellspacing="1" cellpadding="0" width="100%">
				 <% if(saveList!=null&&saveList.size()>0){ 
			           for(int k=0;k<saveList.size();k++){
				          QuerySave querySave = (QuerySave)saveList.get(k);
		         %>
				<tr class='resultTableRowLight' onmouseover="this.className='resultTableRowDeep'" onmouseout="this.className='resultTableRowLight'" >
					<td width="10%">
					<input type="hidden" name="<%=querySave.getSaveId() %>" id="<%=querySave.getSaveId() %>" value="<%= StringUtils.trimToEmpty(querySave.getName())%>"/> 
					<%if(refMap.containsKey(querySave.getSaveId())){ %>
					<input type="checkbox" checked name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(querySave.getSaveId())%>"/>
					<%}else{%>
					<input type="checkbox" name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(querySave.getSaveId())%>"/>
					<%}%>
					 </td>
					<td width="10%"> <%=k+1 %></td>
			  		<td width="30%"><%= StringUtils.trimToEmpty(querySave.getName())%>&nbsp;</td>
					<td width="30%"><%= StringUtils.trimToEmpty(querySave.getRefName())%>&nbsp;</td>
					<td width="20%"><%=querySave.getQueryCount() %>&nbsp;</td>
				</tr>
				<%}} %>
				
			</table>
	</div>
     <p align="center" >
     <input type="button" name="baocun" onclick="query_save()" class="baocun_button" >
     </p> 
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