<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QueryColumn"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.math.BigDecimal "%>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<%
Map<String,Object> objMap = (Map)request.getAttribute("obj");
//��ǰ�Զ��屣����Ϣ
QuerySave querySave = (QuerySave) objMap.get("querySave");
//��ѯ����ֶ��б�
List resultColList =(List)objMap.get("resultColList");
//��������б�
List rsList =(List)objMap.get("resultList");
%>
 <table width="95%" class="currentPositionTable" border="0" cellpadding="0" cellspacing="0">
 <tr>
 <td>
 <span class="title" id="guideLine"><%=StringUtils.trimToEmpty(querySave.getName())%></span>
 </td>
 </tr>
 </table>
<!-------------------������ʾ begin------------------------------->
<br>
<div class="pageFormContent" layoutH="55">
<table border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1" width="99%">
	<%
	int index=0;
 if(rsList!=null&&rsList.size()>0){
 for(int i=0;i<rsList.size();i++){
 		HashMap map = ((HashMap)rsList.get(i));		
 		%>
	<%for( int j=0;j<resultColList.size();j++){
		QueryColumn queryColumns = (QueryColumn)resultColList.get(j);
	%>
	<%if(index%2==0){%>
 <tr height="25">
 <%} %>
 <td > 
	<th align="center" width="20%" style='background-color:#D6E7F7'><%=queryColumns.getName()%></th>
	<%if("2".equals(queryColumns.getEditType()) && map.get(queryColumns.getNameLetter())!=null ){ %>
	<td style="text-align:left;"><%= DicDataUtils.getInstance().getDicItemName(queryColumns.getDicId().intValue(), new Integer(((BigDecimal)map.get(queryColumns.getNameLetter())).intValue()))%></td>
	<% }else if("4".equals(queryColumns.getEditType()) && map.get(queryColumns.getNameLetter())!=null){%>
	<td style="text-align:left;"><%= DicDataUtils.getInstance().getDicItemName(queryColumns.getDicId().intValue(), map.get(queryColumns.getNameLetter()))%></td>
	<% }else{
	%>
	<td style="text-align:left;"><%if(map.get(queryColumns.getNameLetter()) !=null ) out.print(map.get(queryColumns.getNameLetter())); %> &nbsp;</td>
	<% }%>
 <%if( (index+1)%2==0 ){ %>
 </tr>
 <% } index++;}} %>
 <%if((index)%2!=0 ) {%>
 </tr>
 <%} %>
 
<%}%>
</table>
</div>
<!-------------------������ʾ end------------------------------->

<script type="text/javascript">
//��ѯ
function drillURL(winURL){
	window.open(winURL,"","height=768,width=600,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
}
</script>
