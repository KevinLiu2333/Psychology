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
<title>定义条件界面</title>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script>  
</head>

<script type="text/javascript">
//删除select框操作项
function deleteOption(fromObject) {
	 for (var i=fromObject.options.length-1; i>-1; i--) {
        if (fromObject.options[i].selected) {
           fromObject.options[i] = null ;
        }
    }  
}
//保存
function clickNext(){
    	$("#query_defineForm").attr("action","query/statChart/saveChart");
    	selectAll(document.query_defineForm.conSelect);
    	$("#query_defineForm").submit();
}
//返回到查询界面
function query_back(){
    $("#query_defineForm").attr("action","query/statChart/toTheme");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
    if(confirm("确定删除吗？")){
	    $("#query_defineForm").attr("action","query/statChart/delChart");
		$("#query_defineForm").submit();
    }
}
//获取可钻取的定义列表
function query_ref(){
    $("#query_defineForm").attr("action","query/statChart/toChartRef");
	$("#query_defineForm").submit();
}
//选择全部
function selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true ;
	}
}
//选择全部
function selectCheck(obj){
	document.getElementById("chartCatalog").value=obj.value;
}

</script>
<%
	Map objMap = (Map)request.getAttribute("obj");
   //可选择列配置项列表
	List columnList = (List) objMap.get("columnList");
   //主题主键
    String themeId = (String) objMap.get("themeId");
   //钻取定义名称
    String chartRefName = (String) objMap.get("chartRefName");
	//分类
	String catalog = (String) objMap.get("catalog");
	//快速过滤名称
	String saveName = (String) objMap.get("saveName");
	//查找类型
	String filterType = (String) objMap.get("filterType");
   //已定义好的查询，新增时为空
    ChartSave chartSave = new ChartSave();
    if(objMap.get("chartSave") !=null)
    	chartSave = (ChartSave) objMap.get("chartSave");
    //将已经定义好的查询条件放入Map（配置项主键，配置项主键）
    Map conMap = new HashMap();
	if(StringUtils.isNotBlank(chartSave.getConCols())){
		String[] refStrings = chartSave.getConCols().split(",");
		for(int i=0;i<refStrings.length;i++) conMap.put(refStrings[i],refStrings[i]);
	}
	//统计列设置
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
<h3 class="bz_title"> 查询定义</h3>

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
    <th height="30" align="center"  colspan="2"><strong>基本信息</strong></th>
  </tr>
  <tr>
    <td width="50%" valign="middle">显示名称:<input type="text"  name="name" id="name" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getName()) %>"/></td> 
 </tr>
   <tr>
     <td >详细描述:<input type="text"  name="saveDesc" id="saveDesc" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getSaveDesc()) %>"/></td>
   </tr>
    <tr>
     <td colspan="2">条件渲染器renderer:<input type="text"  name="renderer" id="renderer" style="width:90%" value="<%=StringUtils.trimToEmpty(chartSave.getRenderer()) %>"/></td>
   </tr>
     <tr>
     <td colspan="2" height="30" style="text-align:left">可钻取定义列表: <a class="btnLook" href="query/statChart/toChartRef" lookupGroup="" >可钻取定义列表: </a>
    <input name="selectRefNames" type="text" readonly="readonly" style="width:88%" value="<%=StringUtils.trimToEmpty(chartRefName)%>"/>
	</td>
     <tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center" ><strong>条件字段选择</strong></th>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="style_sousuo">
      <tr>
        <td height="10"></td>
      </tr>
      <tr>
        <td align="center"><table align="center" cellspacing="1" cellpadding="0">
            <tr>
              <td align="center">可选条件<br> 
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
			              </td><td align="center">已选条件<br>
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
    <th height="30" align="center" ><strong>图表属性设置</strong></th>
  </tr>
 
  <tr>
   <td ><span style="color:red">分组字段：</span>
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
       排序：
     <select name="xSort" >
      <option value="">---</option>
      <option <%if("ASC".equals(sortMap.get(chartSave.getXgroup()))){%> selected <%}%> value="ASC">升序</option>
	  <option <%if("DESC".equals(sortMap.get(chartSave.getXgroup()))){%> selected <%}%> value="DESC">降序</option>
      </select>
      <span style="color:red">X轴名称：</span>
      <input type="text"  name="xAxisName" id="xAxisName" style="width:40%" value="<%=StringUtils.trimToEmpty(chartSave.getXaxisName()) %>"/></td>
    </tr>
    
  <tr>
    <td >
	    <table  width="95%" border="1">
	     <tr >
	      <td rowspan="5" width="20%">简单图表（统计一列）</td>
	     </tr>
	      <tr >
	      <td >饼图</td>
	      <td >
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Pie2D".equals(chartSave.getCatalogName())){%> checked <%}%>  value="Pie2D" />Pie2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Pie3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Pie3D" />Pie3D
	     </td>
	     </tr>
	       <tr >
	      <td >柱状图</td>
	      <td > 
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Column2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Column2D" />Column2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Column3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Column3D" />Column3D
	     </td>
	     </tr>
	       <tr >
	      <td >折线图</td>
	      <td > <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Line".equals(chartSave.getCatalogName())){%> checked <%}%> value="Line" />Line
	      </td>
	     </tr>
	       <tr >
	      <td >其他</td>
	      <td > <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Doughnut2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Doughnut2D" />Doughnut2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Doughnut3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Doughnut3D" />Doughnut3D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Bar2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Bar2D" />Bar2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <%if("Area2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="Area2D" />Area2D
           </td>
	     </tr>
	     
	       <tr >
	      <td rowspan="5" width="20%">复杂图表（统计多列）</td>
	     </tr>
	      <tr >
	      <td >单轴柱状图</td>
	      <td >
	    
         <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%>  value="MSColumn2D" />MSColumn2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="MSColumn3D" />MSColumn3D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("StackedColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="StackedColumn2D" />StackedColumn2D
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("StackedColumn3D".equals(chartSave.getCatalogName())){%> checked <%}%> value="StackedColumn3D" />StackedColumn3D
	     </td>
	     </tr>
	       <tr >
	      <td >单轴折线图</td>
	      <td > 
	   <input type="radio" onclick="selectCheck(this)"  name="chartCatalogs" <%if("ScrollLine2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollLine2D" />ScrollLine2D
	     </td>
	     </tr>
	       <tr >
	      <td >双轴复合图</td>
	      <td > 
	     <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("MSColumn3DLineDY".equals(chartSave.getCatalogName())){%> checked <%}%> value="MSColumn3DLineDY" />MSColumn3DLineDY
	      </td>
	     </tr>
	       <tr >
	      <td >其他</td>
	     <td >
	       <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("ScrollColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollColumn2D" />ScrollColumn2D
	      <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <%if("ScrollStackedColumn2D".equals(chartSave.getCatalogName())){%> checked <%}%> value="ScrollStackedColumn2D" />ScrollStackedColumn2D
	     </td>
	     </tr>
	    </table>
    </td>
  </tr>
 
  <tr><th style="color:red" >统计列定义（注意：定义顺序为先主轴后副轴（存在副轴情况下））</th></tr>
  <tr>
   <td >
	    <table  width="50%" border="1">
	     <tr>
	      <td style="color:red">统计列</td>
	      <td style="color:red">统计类型</td>
	      <td style="color:red">显示名称</td>
	      <td >排序</td>
	      <td style="color:red">图表显示</td>
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
		      <option <%if("ASC".equals(sortMap.get(yStats[j]))){%> selected <%}%> value="ASC">升序</option>
		      <option <%if("DESC".equals(sortMap.get(yStats[j]))){%> selected <%}%> value="DESC">降序</option>
		      </select>
          </td>
	      <td width="20%">
	      <select name="vyShows" >
		      <option value="0">不显示</option>
		      <option value="1" <%if(yStats[j].equals(syShowMap.get(yStats[j]))){%> selected <%}%> >主轴</option>
		      <option value="2" <%if(yStats[j].equals(vyShowMap.get(yStats[j]))){%> selected <%}%>  >副轴</option>
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
		      <option value="ASC">升序</option>
		      <option value="DESC">降序</option>
		      </select>
          </td>
	      <td width="20%">
	      <select name="vyShows" >
		      <option value="0">不显示</option>
		      <option value="1">主轴</option>
		      <option value="2">副轴</option>
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
  
      
      
      


 