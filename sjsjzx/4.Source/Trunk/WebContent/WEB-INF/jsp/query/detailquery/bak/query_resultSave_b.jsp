<%@ page contentType="application/vnd.ms-excel;charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QueryColumn"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.tiles.query.detailquery.DetailQueryConstants"%>
<%response.setHeader("Content-Disposition","attachment; filename="+DateUtils.date2String(new Date(),"yyyyMMdd")+".xls");%>

<%
//当前自定义保存信息
QuerySave querySave = (QuerySave) request.getAttribute("querySave");
//查询结果字段列表
List resultColList =(List)request.getAttribute("resultColList");
//结果数据列表
List rsList =(List)request.getAttribute("resultList");
	
%>


<%@page import="com.wonders.util.DateUtils"%><table width="95%" border="0" align="center" cellpadding="0" cellspacing="1"  class="searchResultTable">
<tr align="middle" class="resultTableTitle">
<td align="center" width="10%">序号</td>
<%
int colIndex=1;
if(resultColList !=null && resultColList.size()>0){
for(int i=0;i<resultColList.size();i++){
	colIndex++;
	QueryColumn queryColumn = (QueryColumn)resultColList.get(i);	
%>
<td align="center"><%=queryColumn.getName()%></td>
<%}}%>

</tr>

<%
if(rsList!=null&&rsList.size()>0){
for(int i=0;i<rsList.size();i++){
	HashMap map = ((HashMap)rsList.get(i));		
%>
<tr class="<%=(i%2)==1?"resultTableRowLight":"resultTableRowDeep" %>" >
<td class="menulabel99"><%=i+1 %></td>
<%for( int j=0;j<resultColList.size();j++){
QueryColumn queryColumns = (QueryColumn)resultColList.get(j);
%>
<td class="menulabel99"><%=map.get(queryColumns.getNameLetter()) %></td>

<%}%>

<%}} else{%>
<tr> <td  align="middle" colspan="<%=colIndex %>"><span class="style_biaoti">没有满足条件的查询结果</span></td></tr>
<%} %>