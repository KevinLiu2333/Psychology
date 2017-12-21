<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="com.wonders.tiles.query.statchart.entity.ChartSave"%>
<%@ page import="com.wonders.tiles.query.statchart.entity.QueryTheme"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<html>
<head>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />

<title>�������</title>
</head>
<%
Map<String,Object> objMap = (Map)request.getAttribute("obj");
//�����б�
List queryListsave = (List) objMap.get("chartSaveList");
//�����б�
List queryThemeList = (List) objMap.get("queryThemeList");
//�����б�
List catalogList = (List) objMap.get("catalogList");
//����
String catalog = (String) objMap.get("catalog");
//����
String filterType = (String) objMap.get("filterType");
//����
String saveName = (String) objMap.get("saveName");
%>
<script type="text/javascript">
//Ԥ������Ч��
function showQuery(saveId){
    $("#queryForm").attr("action","query/statChart/showChart?saveId="+saveId);
	$("#queryForm").submit();
}
//�����������
function toCatalogTheme(filter){
    $("#filterType").val(filter);
	$("#queryForm").attr("action","query/statChart/toTheme");
	$("#queryForm").submit();
}
//�����������
//�޸Ķ���
function editDefine(saveId,catalog){
    $("#queryForm").attr("action","query/statChart/toDefine?saveId="+saveId);
	$("#queryForm").submit();
}
//��������
function addDefine(themeId,catalog){
    $("#queryForm").attr("action","query/statChart/toDefine?themeId="+themeId);
	$("#queryForm").submit();
}
</script>

<body>
<form id="queryForm" name="queryForm" action=""  method="post" onsubmit="return navTabSearch(this);">
<input type="hidden"  name="filterType" id="filterType"  value="<%=StringUtils.trimToEmpty(filterType)%>"/>
<table width="95%" align="center" cellpadding="0" cellspacing="1"   class="table_list1">
<tr align="right" height="30">
 <th align="left"  width="30%">
 ���ࣺ<select name="catalog" onchange="toCatalogTheme('noFilter');">
 <option value=""  >--��ѡ��--</option>
  <% if(catalogList!=null&&catalogList.size()>0){ 
		for(int i=0;i<catalogList.size();i++){
			QueryTheme catalogTheme = (QueryTheme)catalogList.get(i);
			if(catalogTheme.getCatalog().equals(catalog)){
 %>
	<option value="<%=catalogTheme.getCatalog() %>" selected ><%=catalogTheme.getCatalog() %></option>			
 <%}else{ %>
	<option value="<%=catalogTheme.getCatalog() %>"  ><%=catalogTheme.getCatalog() %></option>		
 <%}}} %></select>
 </th>
  <th align="left"  width="70%">ͼ�����ƿ���������<input type="text"  name="saveName" id="saveName" size="40" value="<%=StringUtils.trimToEmpty(saveName) %>"/>
 <input type="button" class="search_button" name="query"  onClick="toCatalogTheme('filter');" />
 </th>
</tr>
</table>
<br/>
<div class="pageFormContent" layoutH="55">
<%if(StringUtils.isNotBlank(catalog) || StringUtils.isNotBlank(saveName)){ %>

 <table  width="95%" border="0" align="center" cellpadding="0" cellspacing="1"  class="table_list1">
	
 <%
 	   if(queryThemeList!=null&&queryThemeList.size()>0){ 
 	   for(int k=0;k<queryThemeList.size();k++){
 		  QueryTheme queryTheme = (QueryTheme)queryThemeList.get(k);
 %>

<tr height="25">
<th align="left"  width="100%"><%=queryTheme.getName() %> <span title="������">(<%=queryTheme.getThemeId() %>)</span><span title="�����Ӧ������">(<%=queryTheme.getViewName() %>)</span>
<a href="#" onclick="addDefine('<%=queryTheme.getThemeId() %>','<%=catalog %>');" style="font-size:12px;">��������</a>
</th></tr>
<tr height="25"><td align="left"  width="100%">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
	<%
	   int index=0;
	    if(queryListsave!=null&&queryListsave.size()>0){ 
	    	 for(int i=0;i<queryListsave.size();i++){
	    		 ChartSave querySave = (ChartSave)queryListsave.get(i);
	    		if(queryTheme.getThemeId().equals(querySave.getThemeId())){
	    	if(index%3==0){
	%>
  <tr height="25">
    <%} %>
    <td >
   
     <%if(StringUtils.isNotBlank(saveName) && StringUtils.isNotBlank(querySave.getName()) && querySave.getName().indexOf(saveName)>-1){ 
     %>
      <a href="#" title="�޸Ķ���" onclick="editDefine('<%=querySave.getSaveId()%>','<%=catalog%>');"><span style="background-color:#ffff80;color:#ee6600"><%=querySave.getName()%></span></a>
     <%}else{%>
      <a href="#" title="�޸Ķ���" onclick="editDefine('<%=querySave.getSaveId()%>','<%=catalog%>');"><span ><%=querySave.getName()%></span></a>
      <%}%>
     
     <a href="#" onclick="showQuery('<%=querySave.getSaveId() %>');">(Ԥ��)</a></td>
    <%if( (index+1)%3==0 ){ %>
  </tr>
  <% }
    index++;
    }}}%>
    <%if((index)%3!=0 ) {%>
    </tr>
    <%} %>
</table>
</td></tr>
<%}} %>
<%} %>
</table>
</div>
</form>
<br>
</body>
</html>