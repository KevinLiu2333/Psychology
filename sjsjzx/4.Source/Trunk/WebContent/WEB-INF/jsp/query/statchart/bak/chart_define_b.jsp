<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="com.wonders.tiles.query.statchart.entity.ChartSave"%>
<%@ page import="com.wonders.tiles.query.statchart.entity.QueryColumn"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ page import="com.wonders.tiles.query.statchart.StatChartConstans"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<html>
<head>
<title>������������</title>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script>  
</head>

<script type="text/javascript">
//ɾ��select�������
function deleteOption(fromObject) {
	 for (var i=fromObject.options.length-1; i>-1; i--) {
        if (fromObject.options[i].selected) {
           fromObject.options[i] = null ;
        }
    }  
}
//����
function clickNext(){
    	$("#query_defineForm").attr("action","query/statChart/saveChart");
    	selectAll(document.query_defineForm.conSelect);
    	$("#query_defineForm").submit();
}
//���ص���ѯ����
function query_back(){
    $("#query_defineForm").attr("action","query/statChart/toTheme");
	$("#query_defineForm").submit();
}
//ɾ���Ѷ����ѯ
function query_del(saveId,themeId){
    if(confirm("ȷ��ɾ����")){
	    $("#query_defineForm").attr("action","query/statChart/delChart");
		$("#query_defineForm").submit();
    }
}
//��ȡ����ȡ�Ķ����б�
function query_ref(){
    $("#query_defineForm").attr("action","query/statChart/toChartRef");
	$("#query_defineForm").submit();
}
//ѡ��ȫ��
function selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true ;
	}
}
//ѡ��ȫ��
function selectCheck(obj){
	document.getElementById("chartCatalog").value=obj.value;
}

</script>
<%
	Map objMap = (Map)request.getAttribute("obj");
   //��ѡ�����������б�
	List columnList = (List) objMap.get("columnList");
   //��������
    String themeId = (String) objMap.get("themeId");
   //��ȡ��������
    String chartRefName = (String) objMap.get("chartRefName");
	//����
	String catalog = (String) objMap.get("catalog");
	//���ٹ�������
	String saveName = (String) objMap.get("saveName");
	//��������
	String filterType = (String) objMap.get("filterType");
   //�Ѷ���õĲ�ѯ������ʱΪ��
    ChartSave chartSave = new ChartSave();
    if(objMap.get("chartSave") !=null)
    	chartSave = (ChartSave) objMap.get("chartSave");
    //���Ѿ�����õĲ�ѯ��������Map��������������������������
    Map conMap = new HashMap();
	if(StringUtils.isNotBlank(chartSave.getConCols())){
		String[] refStrings = chartSave.getConCols().split(",");
		for(int i=0;i<refStrings.length;i++) conMap.put(refStrings[i],refStrings[i]);
	}
	//ͳ��������
	String xGroup = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,chartSave.getXgroup());
	Map sortMap = new HashMap();
	Map syShowMap = new HashMap();
	Map vyShowMap = new HashMap();
	String[] yAxisNames ={};
	String[] yStats ={};
	String[] sorts = {};
	String[] syshows = {};
	String[] vyshows = {};
	if(objMap.get("chartSave") !=null){
	    yAxisNames = chartSave.getYaxisNames().split(",");
		yStats = chartSave.getYstat().split(",");
		if(chartSave.getSortCols() !=null){
			sorts = chartSave.getSortCols().split(",");
		}
		if(chartSave.getSyGroups() !=null){
			syshows = chartSave.getSyGroups().split(",");
		}
		if(chartSave.getVyGroups() !=null){
			vyshows = chartSave.getVyGroups().split(",");
		}
		for(int i=0;i<yStats.length;i++){
			String ystat = DicDataUtils.getInstance().getDicItemName(StatChartConstans.DIC_QUERY_COL,yStats[i]);
			for(int j=0;j<sorts.length;j++){
				if(sorts[j].startsWith(ystat)) sortMap.put(yStats[i],sorts[j+1]);
				if(sorts[j].startsWith(xGroup)) sortMap.put(chartSave.getXgroup(),sorts[j+1]);
			}
			for(int j=0;j<syshows.length;j++){
				if(syshows[j].startsWith(ystat)) syShowMap.put(yStats[i],yStats[i]);
			}
			for(int j=0;j<vyshows.length;j++){
				if(vyshows[j].startsWith(ystat)) vyShowMap.put(yStats[i],yStats[i]);
			}
			i++;
		}
	}
%>
<body>
<div class="pageFormContent" layoutH="55">
<h3 class="bz_title"> ��ѯ����</h3>

<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="<%=StringUtils.trimToEmpty(catalog) %>"/>
<input type="hidden" name="filterType" id="filterType" value="<%=StringUtils.trimToEmpty(filterType) %>"/>
<input type="hidden" name="saveName" id="saveName" value="<%=StringUtils.trimToEmpty(saveName) %>"/>
<input type="hidden" name="chartCatalog" id="chartCatalog" value="<%=StringUtils.trimToEmpty(chartSave.getCatalogName()) %>"/>
<input type="hidden" name="themeId" id="themeId" value="<%=themeId %>"/>
<input type="hidden" name="saveId" id="saveId" value="<%=StringUtils.trimToEmpty(chartSave.getSaveId()) %>"/>
<input type="hidden" name="selectRefId" id="selectRefId" value="<%=StringUtils.trimToEmpty(chartSave.getDrillSaveId())  %>"/>
<input type="hidden" name="refType" id="refType" value="<%=StringUtils.trimToEmpty(chartSave.getDrillType())  %>"/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center"  colspan="2"><strong>������Ϣ</strong></th>
  </tr>
  <tr>
    <td width="50%" valign="middle">��ʾ����:<input type="text"  name="name" id="name" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getName()) %>"/></td> 
 </tr>
   <tr>
     <td >��ϸ����:<input type="text"  name="saveDesc" id="saveDesc" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getSaveDesc()) %>"/></td>
   </tr>
    <tr>
     <td colspan="2">������Ⱦ��renderer:<input type="text"  name="renderer" id="renderer" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getRenderer()) %>"/></td>
   </tr>
     <tr>
     <td colspan="2" height="30" style="text-align:left">����ȡ�����б�: <a class="btnLook" href="query/statChart/toChartRef" lookupGroup="" >����ȡ�����б�: </a>
    <input name="selectRefNames" type="text" readonly="readonly" style="width:88%" value="<%=StringUtils.trimToEmpty(chartRefName)%>"/>
	</td>
     <tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center" ><strong>�����ֶ�ѡ��</strong></th>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="style_sousuo">
      <tr>
        <td height="10"></td>
      </tr>
      <tr>
        <td align="center"><table align="center" cellspacing="1" cellpadding="0">
            <tr>
              <td align="center">��ѡ����<br> 
          <select name="fromForm" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" >
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if((queryColumn.getColumnType().equals("1") || queryColumn.getColumnType().equals("2"))&& !conMap.containsKey(queryColumn.getColId()))  {
        				
			 %>
            <option value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select>
      </td>
      
        <td width="70px" align="center"><input name="button3" type="button"
			class="style_jiantou"
			onClick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" value=">>>">
                <br>
                    <br>
                    <input name="button" type="button" class="style_jiantou"
			onClick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" value="&lt;&lt;&lt;" >
			              </td><td align="center">��ѡ����<br>
                  <select name="conSelect" multiple="true"
			style="width:260px;height:160px;"
			ondblclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" >
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(conMap.containsKey(queryColumn.getColId())){
			 %>
            <option value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
                  </select>
              </td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td height="10"></td>
      </tr>
    </table></td>
  </tr>
</table>

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center" ><strong>ͼ����������</strong></th>
  </tr>
 
  <tr>
   <td ><span style="color:red">�����ֶΣ�</span>
    <select name="xGroup" >
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
			 %>
            <option <%if(queryColumn.getColId().equals(chartSave.getXgroup())){%> selected <%}%>  value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select>
       ����
     <select name="xSort" >
      <option value="">---</option>
      <option <%if("ASC".equals(sortMap.get(chartSave.getXgroup()))){%> selected <%}%> value="ASC">����</option>
	  <option <%if("DESC".equals(sortMap.get(chartSave.getXgroup()))){%> selected <%}%> value="DESC">����</option>
      </select>
      <span style="color:red">X�����ƣ�</span>
      <input type="text"  name="xAxisName" id="xAxisName" style="width:40%" value="<%=StringUtils.trimToEmpty(chartSave.getXaxisName()) %>"/></td>
    </tr>
    
  <tr>
    <td >
	    <table  width="95%" border="1">
	     <tr >
	      <td rowspan="5" width="20%">��ͼ��ͳ��һ�У�</td>
	     </tr>
	      <tr >
	      <td >��ͼ</td>
	      <td >
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Pie2D".equals(chartSave.getCatalogName())){%> checked <%}%>  value="Pie2D" />Pie2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Pie3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Pie3D" />Pie3D
	     </td>
	     </tr>
	       <tr >
	      <td >��״ͼ</td>
	      <td > 
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Column2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Column2D" />Column2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Column3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Column3D" />Column3D
	     </td>
	     </tr>
	       <tr >
	      <td >����ͼ</td>
	      <td > <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Line".equals(chartSave.getCatalogName())){%> checked <%}%> value="Line" />Line
	      </td>
	     </tr>
	       <tr >
	      <td >����</td>
	      <td > <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Doughnut2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Doughnut2D" />Doughnut2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Doughnut3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Doughnut3D" />Doughnut3D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Bar2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Bar2D" />Bar2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Area2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Area2D" />Area2D
           </td>
	     </tr>
	     
	       <tr >
	      <td rowspan="5" width="20%">����ͼ��ͳ�ƶ��У�</td>
	     </tr>
	      <tr >
	      <td >������״ͼ</td>
	      <td >
	    
         <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%>  value="MSColumn2D" />MSColumn2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="MSColumn3D" />MSColumn3D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("StackedColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="StackedColumn2D" />StackedColumn2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("StackedColumn3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="StackedColumn3D" />StackedColumn3D
	     </td>
	     </tr>
	       <tr >
	      <td >��������ͼ</td>
	      <td > 
	   <input type="radio" onclick="selectCheck(this)"  name="chartCatalogs" <%if("ScrollLine2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollLine2D" />ScrollLine2D
	     </td>
	     </tr>
	       <tr >
	      <td >˫�Ḵ��ͼ</td>
	      <td > 
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn3DLineDY".equals(chartSave.getCatalogName())){%> checked <%}%> value="MSColumn3DLineDY" />MSColumn3DLineDY
	      </td>
	     </tr>
	       <tr >
	      <td >����</td>
	     <td >
	       <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("ScrollColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollColumn2D" />ScrollColumn2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("ScrollStackedColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollStackedColumn2D" />ScrollStackedColumn2D
	     </td>
	     </tr>
	    </table>
    </td>
  </tr>
 
  <tr><th style="color:red" >ͳ���ж��壨ע�⣺����˳��Ϊ��������ᣨ���ڸ�������£���</th></tr>
  <tr>
   <td >
	    <table  width="50%" border="1">
	     <tr>
	      <td style="color:red">ͳ����</td>
	      <td style="color:red">ͳ������</td>
	      <td style="color:red">��ʾ����</td>
	      <td >����</td>
	      <td style="color:red">ͼ����ʾ</td>
	     </tr>
	     <%
	     int yStatsCount=0;
	     for(int j=0;j<yStats.length;j++){ 
	     %>
	       <tr>
	      <td  width="20%"><select name="yGroups" >
	      
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option <%if(queryColumn.getColId().equals(yStats[j])){%> selected <%}%> value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="yStats" >
		      <option value="">---</option>
		      <option <%if("COUNT".equals(yStats[j+1])){%> selected <%}%> value="COUNT">COUNT</option>
		      <option <%if("SUM".equals(yStats[j+1])){%> selected <%}%> value="SUM">SUM</option>
		      <option <%if("MAX".equals(yStats[j+1])){%> selected <%}%> value="MAX">MAX</option>
		      <option <%if("MIN".equals(yStats[j+1])){%> selected <%}%> value="MIN">MIN</option>
		      <option <%if("AVG".equals(yStats[j+1])){%> selected <%}%> value="AVG">AVG</option>
		    
		      </select>
	      </td>
	      <td><input type="text"  name="yAxisNames" id="yAxisNames" style="width:100%" value="<%=yAxisNames[yStatsCount] %>"/></td>
	      <td  width="20%"> 
		      <select name="ySorts" >
		      <option value="">---</option>
		      <option <%if("ASC".equals(sortMap.get(yStats[j]))){%> selected <%}%> value="ASC">����</option>
		      <option <%if("DESC".equals(sortMap.get(yStats[j]))){%> selected <%}%> value="DESC">����</option>
		      </select>
          </td>
	      <td width="20%">
	      <select name="vyShows" >
		      <option value="0">����ʾ</option>
		      <option value="1" <%if(yStats[j].equals(syShowMap.get(yStats[j]))){%> selected <%}%> >����</option>
		      <option value="2" <%if(yStats[j].equals(vyShowMap.get(yStats[j]))){%> selected <%}%>  >����</option>
		      </select>
	      </td>
	     </tr>
	       
	     <%j++;
	     yStatsCount++;} %>
	     
	     <%for(int k=0;k<5;k++){ %>
	     <tr>
	      <td  width="20%"><select name="yGroups" >
	      
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if((queryColumn.getColumnType().equals("1") || queryColumn.getColumnType().equals("2"))&& !conMap.containsKey(queryColumn.getColId()))  {
        				
			 %>
            <option  value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="yStats" >
		      <option value="">---</option>
		      <option value="COUNT">COUNT</option>
		      <option value="SUM">SUM</option>
		      <option value="MAX">MAX</option>
		      <option value="MIN">MIN</option>
		      <option value="AVG">AVG</option>
		      </select>
	      </td>
	      <td><input type="text"  name="yAxisNames" id="yAxisNames" style="width:100%" value=""/></td>
	      <td  width="20%"> 
		      <select name="ySorts" >
		      <option value="">---</option>
		      <option value="ASC">����</option>
		      <option value="DESC">����</option>
		      </select>
          </td>
	      <td width="20%">
	      <select name="vyShows" >
		      <option value="0">����ʾ</option>
		      <option value="1">����</option>
		      <option value="2">����</option>
		      </select>
	      </td>
	     </tr>
	     <%} %>
	     </table>
   </td>
  </tr>
</table>
</form>
  <table width="95%" border="0" align="left" cellpadding="0" cellspacing="1">
    <tr>
      <td align="center">
      <%if(objMap.get("chartSave") !=null){ %>
	  <input type="button" name="Submitbutton" class="shanchu_button" onclick="query_del(<%=chartSave.getSaveId() %>,<%=chartSave.getThemeId() %>)" />  
      <%} %>
	  <input type="button" name="Submitbutton" class="baocun_button" onclick="clickNext()" />  
      <input type="button" name="buttonReturn" class="quxiao_button" onClick="query_back('<%=catalog%>')" /></td>
    </tr>
  </table>
  </div>
</body>
</html>  
  
      
      
      


 