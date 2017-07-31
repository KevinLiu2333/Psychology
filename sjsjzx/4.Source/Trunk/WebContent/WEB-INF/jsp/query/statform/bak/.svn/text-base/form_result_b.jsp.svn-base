<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.tiles.query.statform.entity.FormSave"%>
<%@ page import="com.wonders.tiles.query.statform.entity.QueryColumn"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="java.util.HashMap" %>

<html>
<head>
<!-- tab 页必须引入开始-->
<link href="${ctx }/tiles/query/styles/tab.webfx.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="${ctx }/tiles/query/scripts/tabpane.js"></script>
<!-- tab 页必须引入结束-->
<link href="${ctx }/tiles/query/styles/groupreport.css" rel="stylesheet" rev="stylesheet" type="text/css" />
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>
<script type="text/javascript">
//查询
function searchResult(){
	 $("#statForm").attr("action","query/statForm/showForm");
	 $("#statForm").submit(); 
}


</script>
</head>
<%
Map objMap = (Map)request.getAttribute("obj");
//图标数据XML
String formString = (String)objMap.get("formString");

String chartXmlString = (String)objMap.get("chartXmlString");
//保存统计的定义
FormSave formSave = (FormSave)objMap.get("formSave");
//查询条件字段列表
List conditionColList =(List)objMap.get("conditionColList");
//查询条件字段列表
Map conMap =(Map)objMap.get("conMap");
//展示类型
String showType = (String) objMap.get("showType");
//数否可以显示展示图表
String chartFlag = (String) objMap.get("chartFlag");
//显示图标类型名字
String catalogName = (String) objMap.get("catalogName");
//查询引用保存列表
Map queryRenderer =(Map)objMap.get("queryRenderer");

Map multiMap = new HashMap();
if(StringUtils.isNotBlank(formSave.getMultiCols())){
	String[] refStrings = formSave.getMultiCols().split(",");
	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
}
%>
<body>
<form id="statForm" name="statForm" method="post" action="" onsubmit="return navTabSearch(this);">
<input type="hidden" name="drillType" value="<%=StringUtils.trimToEmpty((String)objMap.get("drillType")) %>" />
<input type="hidden" name="drillId" value="<%=StringUtils.trimToEmpty((String)objMap.get("drillId")) %>" />
<input type="hidden" name="saveId" value="<%=StringUtils.trimToEmpty(formSave.getSaveId()) %>" />
<input type="hidden" name="catalogName" value="<%=StringUtils.trimToEmpty(catalogName) %>" />
<input type="hidden" name="showType" value="<%=StringUtils.trimToEmpty(showType) %>" />
<% if(StringUtils.isNotBlank(formSave.getConCols())){%>
<table width="100%" align="center" cellpadding="0" cellspacing="1" class="search">

	 <tr align="left">
	<%for(int i=0;i<conditionColList.size();i++){
			QueryColumn queryColumn = (QueryColumn)conditionColList.get(i);	
			String edit_type = queryColumn.getEditType();
			
	 %>
  		<td align="right" ><%=queryColumn.getName()%></td>
  		<% if(edit_type.equals("1") || edit_type.equals("5")) {%>
  		<td align="left">  <input type="text" name="<%=queryColumn.getNameLetter()%>" value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter())) %>"></td> 
  		<%} else if(edit_type.equals("2") || edit_type.equals("4")){%>
  			<%if(queryRenderer !=null && queryRenderer.containsKey(queryColumn.getColId())){%>
  				<%if(multiMap.containsKey(queryColumn.getColId())){ %>
	  			<td align="left"><%=queryRenderer.get(queryColumn.getColId()+"start") %>
	  			至
	  			 <%=queryRenderer.get(queryColumn.getColId()+"end") %></td>
  				<%}else{%>
	  			<td align="left"><%=queryRenderer.get(queryColumn.getColId()) %></td>
  				<%}%>
	  		<%}else{ %>
			<td align="left"> <wd:select name="<%=queryColumn.getNameLetter() %>" dicCode="<%=Integer.parseInt(queryColumn.getDicId().toString()) %>" defaultValue="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter())) %>"/></td>
			<%} %>
			
    	<% }else if(edit_type.equals("3")){ %>
    	<td align="left">
    	   <input type="text" size="10" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" name='<%=queryColumn.getNameLetter()+"staDATE"%>'  value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter()+"staDATE")) %>" />
    		至
    		<input  type="text" size="10" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" name='<%=queryColumn.getNameLetter()+"endDATE"%>'  value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter()+"endDATE")) %>" />
    	</td> 
  <%}}%>
  <td><input type="button" class="search_button" name="query"  onClick="searchResult()"/></td>
  </tr>
  </table>
  <%} %>
 <!-------------------条件查询 end-------------------------------> 
</form>
<!-------------------图标展示 start------------------------------->
<div class="pageFormContent" layoutH="55">
<div class="tab-pane" id="tabPane">
	<!-- TAB页1开始 -->
	<div class="tab-page">
		<h2 class="tab">图形</h2>
		<div class="searchResultDiv" width:99%;>
<%if(StringUtils.isNotBlank(chartFlag) &&(StringUtils.isBlank(showType)|| "2".equals(showType))){ %>
<table width="93%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center">图表</div>
      <script type="text/javascript">
           var swfString = "${ctx }/tiles/query/charts/<%=catalogName%>.swf";
		    var myChart=new FusionCharts(swfString,"myChartId","750","330");
			myChart.setDataXML("<%=chartXmlString%>"); 	   
		    myChart.render("chartdiv");
		</script>
	</td>
  </tr>
</table>
</div>
<%} %>
</div>
<!-------------------图标展示  end------------------------------> 
<!-------------------报表展示 start-------------------------------> 
	<!-- TAB页2开始 -->
	<div class="tab-page">
		<h2 class="tab">报表</h2>
		<div class="searchResultDiv" width:99%;>
<%if(StringUtils.isBlank(showType) || "1".equals(showType)){ %>
<div class="searchResultDiv" style="width:93%;margin-left :10px;">
 <%out.print(formString);%>
</div>
 </div>
 </div>
<%} %>
 </div>
 </div>
<!-------------------报表展示 end-------------------------->


</body>

</html>
