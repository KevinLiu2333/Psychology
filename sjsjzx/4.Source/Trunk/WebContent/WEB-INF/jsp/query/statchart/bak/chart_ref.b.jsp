<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.tiles.query.statchart.entity.ChartSave"%>
<html>
<head>
<title>保存条件</title>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
</head>
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
    	refType = document.getElementById("vyShows").value;
    	}
    $.bringBack({selectRefId:selectValue,selectRefName:selectName,refType:refType});
}
//获取可钻取的定义列表
function query_ref(){
	document.getElementById("refType").value=document.getElementById("vyShows").value;
	document.saveForm.action="statChart.do?method=toChartRef";
    document.saveForm.submit();
}
</script>

<%
	Map<String,Object> objMap = (Map)request.getAttribute("obj");
	String selectRefId = (String)objMap.get("selectRefId");
	String refType = (String)objMap.get("refType");
	List saveList = (List)objMap.get("resultList");
	
%>
<body>
<br>
<form name="saveForm" method="post" action="" onsubmit="return navTabSearch(this);">
<input type="hidden" name="selectRefId" id="selectRefId" value="<%=StringUtils.trimToEmpty(selectRefId)  %>"/>
<input type="hidden" name="refType" id="refType" value="<%=StringUtils.trimToEmpty(refType)  %>"/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1">
  <tr>
    <td height="30" align="center"><span class="bz_title">可引用定义列表</span></td>
  </tr>
    <tr>
    <th height="30" align="center"> 
              <select name="vyShows" onchange="query_ref()">
		      <option value="2"<%if("2".equals(refType)){%> selected <%}%>  >图表钻取</option>
		      <option value="1" <%if("1".equals(refType)){%> selected <%}%> >查询钻取</option>
		      </select>
		      </th>
  </tr>
</table>
<div id="scrolling2" style="width:100%; height:26px; z-index:1; overflow:auto;" align="center">
	<table class="table_list1" cellspacing="1" cellpadding="0" width="100%">
	<tr >
	  		<th width="10%"></th>
	  		<th width="10%"> 序号</th>
	  		<th width="30%">名称</th>
			<th width="30%">被引用名称</th>
			<th width="20%">使用热度</th>
	</tr>
	</table>
	</div>
	<div  style="width:100%; height:290px; z-index:1; overflow-x:hidden;overflow-y:scroll;" align="center" id="topDiv" >
	<%if("1".equals(refType)){ %>
	<table class="table_list1" cellspacing="1" cellpadding="0" width="100%">
		 <% if(saveList!=null&&saveList.size()>0){ 
	           for(int k=0;k<saveList.size();k++){
		          QuerySave querySave = (QuerySave)saveList.get(k);
         %>
		<tr class='resultTableRowLight' onmouseover="this.className='resultTableRowDeep'" onmouseout="this.className='resultTableRowLight'" >
			<td width="10%">
			<input type="hidden" name="<%=querySave.getSaveId() %>" id="<%=querySave.getSaveId() %>" value="<%= StringUtils.trimToEmpty(querySave.getName())%>"/> 
			<%if(querySave.getSaveId().equals(selectRefId)){ %>
			<input type="radio" checked name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(querySave.getSaveId())%>"/>
			<%}else{%>
			<input type="radio" name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(querySave.getSaveId())%>"/>
			<%}%>
			 </td>
			<td width="10%"> <%=k+1 %></td>
	  		<td width="30%"><%= StringUtils.trimToEmpty(querySave.getName())%>&nbsp;</td>
			<td width="30%"><%= StringUtils.trimToEmpty(querySave.getRefName())%>&nbsp;</td>
			<td width="20%"><%=querySave.getQueryCount() %>&nbsp;</td>
		</tr>
		<%}} %>
	</table>
	<%}else{ %>
	 <table class="table_list1" cellspacing="1" cellpadding="0" width="100%">
		 <% if(saveList!=null&&saveList.size()>0){ 
	           for(int k=0;k<saveList.size();k++){
	        	   ChartSave chartSave = (ChartSave)saveList.get(k);
         %>
		<tr class='resultTableRowLight' onmouseover="this.className='resultTableRowDeep'" onmouseout="this.className='resultTableRowLight'" >
			<td width="10%">
			<input type="hidden" name="<%=chartSave.getSaveId() %>" id="<%=chartSave.getSaveId() %>" value="<%= StringUtils.trimToEmpty(chartSave.getName())%>"/> 
			<%if(chartSave.getSaveId().equals(selectRefId)){ %>
			<input type="radio" checked name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(chartSave.getSaveId())%>"/>
			<%}else{%>
			<input type="radio" name="itemCheckBox" id="itemCheckBox" value="<%= StringUtils.trimToEmpty(chartSave.getSaveId())%>"/>
			<%}%>
			 </td>
			<td width="10%"> <%=k+1 %></td>
	  		<td width="30%"><%= StringUtils.trimToEmpty(chartSave.getName())%>&nbsp;</td>
			<td width="30%"><%= StringUtils.trimToEmpty(chartSave.getCatalogName())%>&nbsp;</td>
			<td width="20%"><%=chartSave.getQueryCount() %></td>
		</tr>
		<%}} %>
	   </table>
	<%} %>
	</div>
     <p align="center" >
     <input type="button" name="Submit" onclick="query_save()" class="baocun_button" >
     </p> 

</form>

</body>
</html>