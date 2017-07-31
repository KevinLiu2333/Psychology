<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QueryColumn"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.math.BigDecimal "%>
<%@ page import="com.wonders.tiles.query.detailquery.DetailQueryConstants"%>
<%@ page import="org.nutz.dao.pager.Pager"%>

<html>
<head>
<title>查询结果</title>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/My97DataPicker/WdatePicker.js"></script>
</head>
<script type="text/javascript">
//查询
function searchResult(){
document.getElementById("exportFlag").value="";
    $("#detailQueryForm").attr("action","query/detailQuery/queryResult");
	$("#detailQueryForm").submit();
}
function queryExport(){
   document.getElementById("exportFlag").value="1";
   document.detailQueryForm.submit();
}
</script>

<%
Map<String,Object> objMap = (Map)request.getAttribute("obj");
//当前自定义保存信息
QuerySave querySave = (QuerySave) objMap.get("querySave");
//查询条件字段列表
List conditionColList =(List)objMap.get("conditionColList");
//条件值Map
Map conMap = (Map)objMap.get("conMap");
//查询结果字段列表
List resultColList =(List)objMap.get("resultColList");
//查询引用保存列表
List refColList =(List)objMap.get("refColList");
//查询标识  不为空：只查询条件  null：查询条件和结果
String queryFlag = (String) objMap.get("queryFlag");
//导航标识
String guideFlag = (String) objMap.get("guideFlag");
//查询引用保存列表
Map queryRenderer =(Map)objMap.get("queryRenderer");
//结果数据列表
Map multiMap = new HashMap();
if(StringUtils.isNotBlank(querySave.getMultiCols())){
	String[] refStrings = querySave.getMultiCols().split(",");
	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
}
//分页列表
List rsList =(List)objMap.get("rsList");
Pager pager = (Pager)objMap.get("pager");
int rsListCount = (Integer)objMap.get("rsListCount");
%>
<body>
<form id="detailQueryForm" name="detailQueryForm" method="post" action="query/detailQuery/queryResult" onsubmit="return navTabSearch(this);">
<input type="hidden" id="saveId" name="saveId" value="<%=querySave.getSaveId() %>" />
<input type="hidden" id="currentPage" name="currentPage" value="1">
<input type="hidden" id="exportFlag" name="exportFlag" id="exportFlag" value="">

<!-------------------导航条 start------------------------------->
<%if(StringUtils.isNotBlank(guideFlag)){ %>
<div>
<br>
  <table width="95%" class="currentPositionTable" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td>
        <span class="title" id="guideLine11"><%=StringUtils.trimToEmpty(querySave.getName())%></span>
      </td>
    </tr>
  </table>
</div>
<%} %>
<!-------------------导航条 end------------------------------->
<!-------------------条件查询 begin------------------------------->
<table width="100%" align="center" cellpadding="0" cellspacing="1"  class="search">
 
	 <tr align="right" height="30">
	<%for(int i=0;i<conditionColList.size();i++){
			QueryColumn queryColumn = (QueryColumn)conditionColList.get(i);	
			String edit_type = queryColumn.getEditType();
			
	 %>
  		<td align="right" ><%=queryColumn.getName()%></td>
  		<% if(edit_type.equals("1") || edit_type.equals("5")) {%>
  		<td align="left">  <input type="text" name="<%=queryColumn.getNameLetter()%>" value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter())) %>"></td> 
  		<%} else if(edit_type.equals("2") || edit_type.equals("4")){ %>
  			<%if(queryRenderer !=null && queryRenderer.containsKey(queryColumn.getColId())){ %>
	  			<%if(multiMap.containsKey(queryColumn.getColId())){ %>
	  			<td align="left"><%=queryRenderer.get(queryColumn.getColId()+"start") %>
	  			至
	  			 <%=queryRenderer.get(queryColumn.getColId()+"end") %></td>
  				<%}else{%>
	  			<td align="left"><%=queryRenderer.get(queryColumn.getColId()) %></td>
  				<%}%>
	  		<%}else{ %>
			<td align="left"> <wd:select name="<%=queryColumn.getNameLetter() %>" dicCode="<%=Integer.parseInt(queryColumn.getDicId().toString()) %>" defaultValue="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter())) %>"/>
			</td>
			<%} %>
    	<% }else if(edit_type.equals("3")){ %>
    	<td align="left">
    	   <input type="text" size="10" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" name='<%=queryColumn.getNameLetter()+"staDATE"%>'  value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter()+"staDATE")) %>" />
    		至
    		<input  type="text" size="10" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" name='<%=queryColumn.getNameLetter()+"endDATE"%>'  value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter()+"endDATE")) %>" onClick="WdatePicker()"/>
    	</td>  
  <%}}%>
  <td><input type="button" class="search_button" name="query"  onClick="searchResult()" />
  <%
if(rsList!=null&&rsList.size()>0){%>
  <input type="button" class="daochu_button" name="query1"  onClick="queryExport()" />
<%} %>
  </td>
  		</tr>
  </table>
<!-------------------条件查询 end------------------------------->

<!-------------------数据显示 begin------------------------------->
<br>
<%if(StringUtils.isBlank(queryFlag)){ %>
<table align="center" class="draggable sortable table_list1" style="width:98%">

<tr>
<th align="center">序号</th>
<%
int colIndex=1;
if(resultColList !=null && resultColList.size()>0){
for(int i=0;i<resultColList.size();i++){
	colIndex++;
	QueryColumn queryColumn = (QueryColumn)resultColList.get(i);	
%>
<th align="center"><%=queryColumn.getName()%></th>
<%}}%>
<%if(refColList !=null && refColList.size()>0 ) {
	colIndex++;
%>
<th align="center" >钻取</th>
<%} %>
</tr>

<%
if(rsList!=null&&rsList.size()>0){
for(int i=0;i<rsList.size();i++){
	HashMap map = ((HashMap)rsList.get(i));		
%>
 <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''">
<td class="menulabel99"><%=(pager.getPageNumber()-1)*pager.getPageSize()+i+1 %></td>
<%for( int j=0;j<resultColList.size();j++){
QueryColumn queryColumns = (QueryColumn)resultColList.get(j);
%>
<!-------------------特殊处理字段 begin------------------------------->
<%if(StringUtils.isNotBlank(queryColumns.getDrillLink())){
//url解析并设值,将url参数补充值后组成调用url
String linkURL = queryColumns.getDrillLink();
Iterator iterator = map.keySet().iterator();
while (iterator.hasNext()) {
	Object key = iterator.next();
	if(linkURL.indexOf("#"+key+"#") !=-1){
		linkURL = linkURL.replaceAll("#"+key+"#",""+map.get(key));
	}
}
%>
<td class="menulabel99">
<a  href="<%=linkURL%>" target="navTab" rel="linkURL">
<%if("2".equals(queryColumns.getEditType()) || "4".equals(queryColumns.getEditType())){ 
 DicDataUtils.getInstance().getDicItemName(queryColumns.getDicId().intValue(), map.get(queryColumns.getNameLetter()));
}else{
 if(map.get(queryColumns.getNameLetter()) !=null) out.print(map.get(queryColumns.getNameLetter())); 
}%>
</a>
</td>
<!-------------------特殊处理字段 end------------------------------->
<%}else if("2".equals(queryColumns.getEditType()) && map.get(queryColumns.getNameLetter())!=null ){ %>
<td class="menulabel99"><%= DicDataUtils.getInstance().getDicItemName(queryColumns.getDicId().intValue(), new Integer(((BigDecimal)map.get(queryColumns.getNameLetter())).intValue()))%></td>
<% }else if("4".equals(queryColumns.getEditType()) && map.get(queryColumns.getNameLetter())!=null){%>
<td style="text-align:left;"><%= DicDataUtils.getInstance().getDicItemName(queryColumns.getDicId().intValue(), map.get(queryColumns.getNameLetter()))%></td>
<% }else{%>
<td class="menulabel99"><%if(map.get(queryColumns.getNameLetter()) !=null) out.print(map.get(queryColumns.getNameLetter())); %>  &nbsp;</td>
<% }%>

<%}%>
<!-------------------钻取 begin------------------------------->
<%if(refColList !=null && refColList.size()>0 ) {%>
<td align="center">
<%for( int k=0;k<refColList.size();k++){
	QuerySave refSave = (QuerySave)refColList.get(k);
	String actionString = "query/detailQuery/queryResult?guideFlag=1&saveId="+refSave.getSaveId();
	String[] conStrings = refSave.getConCols().split(",");
	for(int m=0;m<conStrings.length;m++){
		String nameLetter = DicDataUtils.getInstance().getDicItemName(DetailQueryConstants.DIC_QUERY_COL, conStrings[m]);
		if(map.get(nameLetter) !=null){
		actionString += "&"+nameLetter+"="+map.get(nameLetter);
		}else if(conMap.get(nameLetter) != null){
			actionString += "&"+nameLetter+"="+conMap.get(nameLetter);
		}else if(conMap.get(nameLetter+"staDATE") != null){
			actionString += "&"+nameLetter+"="+conMap.get(nameLetter);
		}else if(conMap.get(nameLetter+"endDATE") != null){
			actionString += "&"+nameLetter+"="+conMap.get(nameLetter);
		}
	}
%>
<a  href="<%=actionString %>" target="navTab" rel="actionString"><%=refSave.getRefName() %></a>
<%}%>
</td>
<%} %>
<!-------------------钻取 end------------------------------->
<%}} else{%>
 <td  align="middle" colspan="<%=colIndex %>"><span class="style_biaoti">没有满足条件的查询结果</span></td>
<%} %>

<%} %>
</tr>
</table>
</form>
<!-------------------数据显示 begin------------------------------->

<!-------------------分页页面 begin------------------------------->
<%
if (rsList != null && rsList.size()>0){
    request.setAttribute("pager",pager);
    request.setAttribute("rsListCount",rsListCount);
    request.setAttribute("queryForm", "detailQueryForm");   
    
%>
<!--<jsp:include page="pageInfo.jsp" flush="true"/>-->
<%}%>
</body>
<!-------------------分页页面 end------------------------------->
<script type="text/javascript">
//查询
function drillURL(winURL){
 //var allscreenwin =window.open(winURL,"","height=600,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
 //allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
	 
}
//调用连接
function linkURL(winURL){
 //var allscreenwin =window.open(winURL,"","height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
 //allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
}
</script>
</html>