<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.tiles.query.statchart.entity.ChartSave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.wonders.tiles.query.statchart.entity.QueryColumn"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="com.wonders.tiles.query.statchart.StatChartConstans"%>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
<!-- tab ҳ�������뿪ʼ-->
<link href="${ctx }/tiles/query/styles/tab.webfx.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="${ctx }/tiles/query/scripts/tabpane.js"></script>
<!-- tab ҳ�����������-->
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${ctx }/tiles/query/scripts/fusionCharts.js"></script>
<script type="text/javascript">

//��ѯ
function searchResult(){
	$("#detailQueryForm").attr("action","query/statChart/showChart");
	$("#detailQueryForm").submit();
}

function selectCatalog(objs){
	document.getElementById("catalogName").value=objs.value;
    $("#detailQueryForm").attr("action","query/statChart/showChart");
	$("#detailQueryForm").submit(); 
}

</script>
</head>
<%
Map objMap = (Map)request.getAttribute("obj");
//ͼ������XML
String xmlString = (String)objMap.get("xmlString");
//����ͳ�ƵĶ���
ChartSave chartSave = (ChartSave)objMap.get("chartSave");
//��ѯ�����ֶ��б�
List conditionColList =(List)objMap.get("conditionColList");
//��ѯ����ֶ��б�
List resultColList =(List)objMap.get("resultColList");
//��ѯ����ֶ��б�
List chartDataList =(List)objMap.get("chartDataList");
//��ѯ���ñ����б�
Map queryRenderer =(Map)objMap.get("queryRenderer");
//��ѯ����ֶ��б�
QueryColumn groupColumn =(QueryColumn)objMap.get("groupColumn");
//����ֵMap
Map conMap = (Map) objMap.get("conMap");
//ͳ���ֶ���������
String[] yGroupsArray = chartSave.getYgroups().split(",");
////ͳ���ֶ���ʾ��������
String[] yGroupsNameArray = chartSave.getYaxisNames().split(",");
//ͳ���ֶ�Id����
String[] yStatArray = chartSave.getYstat().split(",");
//չʾ����
String showType = (String) objMap.get("showType");
//չʾ����
String showTotal = (String) objMap.get("showTotal");
Map multiMap = new HashMap();
if(StringUtils.isNotBlank(chartSave.getMultiCols())){
	String[] refStrings = chartSave.getMultiCols().split(",");
	for(int i=0;i<refStrings.length;i++) multiMap.put(refStrings[i],refStrings[i]);
}
DecimalFormat format = new DecimalFormat("#0.00");
%>
<body>
<form id="detailQueryForm" name="detailQueryForm" method="post" action="" onsubmit="return navTabSearch(this);">
<input type="hidden" name="saveId" value="<%=chartSave.getSaveId() %>" />
<input type="hidden" name="catalogName" name="catalogName" value="<%=chartSave.getCatalogName() %>" />
<input type="hidden" name="showTotal" value="<%=StringUtils.trimToEmpty(showTotal) %>" />
<input type="hidden" name="showType" value="<%=StringUtils.trimToEmpty(showType) %>" />
<!-------------------������ѯ start------------------------------->
<% if(StringUtils.isNotBlank(chartSave.getConCols())){%>
<table width="100%" align="center" cellpadding="0" cellspacing="1"  class="search">
	 <tr align="right">
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
	  			��
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
    		��
    		<input  type="text" size="10" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" name='<%=queryColumn.getNameLetter()+"endDATE"%>'  value="<%=StringUtils.trimToEmpty((String)conMap.get(queryColumn.getNameLetter()+"endDATE")) %>" />
    	</td> 
  <%}}%>
  <td><input type="button" class="search_button" name="query"  onClick="searchResult()" /></td>
  </tr>
  </table>
  <%} %>
 <!-------------------������ѯ end-------------------------------> 
  </form>
<!-------------------ͼ��չʾ start-------------------------------> 
<div class="pageFormContent" layoutH="55">
<div class="tab-pane" id="tabPane">
	<!-- TABҳ1��ʼ -->
	<div class="tab-page">
		<h2 class="tab">ͼ��</h2>
		<div class="searchResultDiv" width:99%;>
<%if(StringUtils.isBlank(showType) || "2".equals(showType)){ %>
<table width="93%" border="0" cellspacing="0" cellpadding="3" align="center">
  <tr> 
    <td valign="top" class="text" align="center"> <div id="chartdiv" align="center">ͼ��</div>
      <script type="text/javascript">
           var swfString = "${ctx }/tiles/query/charts/<%=chartSave.getCatalogName()%>.swf";
		    var myChart=new FusionCharts(swfString,"myChartId","750","330");
			myChart.setDataXML("<%=xmlString%>"); 	   
		    myChart.render("chartdiv");
		</script>
	</td>
  </tr> 
</table>
 </div>
<%}%>
</div>
<!-------------------ͼ��չʾ  end-------------------------------> 

<!-------------------���չʾ  start-------------------------------> 
	<!-- TABҳ2��ʼ -->
	<div class="tab-page">
		<h2 class="tab">����</h2>
		<div class="searchResultDiv" width:99%;>
 <%if(StringUtils.isBlank(showType) || "1".equals(showType)){ %>
<table  border="0" align="center" cellpadding="0" cellspacing="1"  id="table_list" class="table_list2" width="93%">
<tr >
<td style="color:#0982e1;font-weight:bold"><%=StringUtils.trimToEmpty(chartSave.getXaxisName())%></td>
<%for(int k=0;k<yGroupsNameArray.length;k++){%>
<th align="center"><%=StringUtils.trimToEmpty(yGroupsNameArray[k])%></th>
<% }%>
</tr>
<%for(int i=0;i<chartDataList.size();i++){
    Map dataMap = (Map)chartDataList.get(i);
    String xGroup = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
    if("2".equals(groupColumn.getEditType()) || "4".equals(groupColumn.getEditType())){	
   	 String chartLable = DicDataUtils.getInstance().getDicItemName(groupColumn.getDicId().intValue(),(String)dataMap.get(xGroup));
%>
 <tr >
<th align="center" class="resultTableTitle" ><%=chartLable%></th>
<% }else if("5".equals(groupColumn.getEditType())){%>
<th align="center" class="resultTableTitle" style="color:#024e88;font-weight:bold"><%=format.format(dataMap.get(xGroup))%></th>
<% }else{%>
<th align="center" class="resultTableTitle" style="color:#024e88;font-weight:bold"><%=dataMap.get(xGroup)%></th>
<%}%>
<%for(int k=0;k<yGroupsArray.length;k++){%>
<td align="center" style="background: #ffffff;color:#024e88;font-weight:bold" ><%if(dataMap.get(yGroupsArray[k])!=null){out.print(format.format(dataMap.get(yGroupsArray[k])));}else{out.print("0.00");}%></td>
<%}%>
</tr>
<%}%>
<%if(StringUtils.isNotBlank(showTotal)){ %>
<tr>
<th >�ϼ�</th>
<%for(int k=0;k<yGroupsNameArray.length;k++){%>
<td align="center" style="background: #ffffff;color:#024e88;font-weight:bold" >&nbsp;</td>
<% }%>
</tr>
<% }%>
</table>
 </div>
 </div>
<%} %>
</div>
</div>
<!-------------------���չʾ  end-------------------------------> 
<br>
<br>
</body>

<script type="text/javascript">
//��ѯ
function drillURL(winURL){
 var allscreenwin =window.open(winURL,"","height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
 allscreenwin.resizeTo(screen.availWidth, screen.availHeight);
	
}
<%if(StringUtils.isNotBlank(showTotal)){ %>
computeTotal();
<%}%>
//����ϼ���Ϣ
function computeTotal(){
	var data_table = document.getElementById("table_list");
	var row_length = data_table.rows.length;
	var col_length = data_table.rows[0].cells.length;
	for(var i=1;i<col_length;i++){
	  var total = 0;
	  for(var j=1;j<row_length-1;j++){
	   total = accAdd(total,data_table.rows[j].cells[i].innerHTML,2);
	  }
	  data_table.rows[row_length-1].cells[i].innerHTML = total;
	}
}
//�ӷ������������õ���ȷ�ļӷ���� 
//����ֵ��arg1����arg2�ľ�ȷ���
//fix ��ʾ����С�����ȣ�Ĭ����2λ
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


