<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="com.wonders.tiles.query.statform.entity.FormSave"%>
<%@ page import="com.wonders.tiles.query.statform.entity.QueryColumn"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script>  


<%
	Map objMap = (Map)request.getAttribute("obj");
   //可选择列配置项列表
	List columnList = (List) objMap.get("columnList");
   //主题主键
    String themeId = (String) objMap.get("themeId");
	//分类
	String catalog = (String) objMap.get("catalog");
	//快速过滤名称
	String saveName = (String) objMap.get("saveName");
	//查找类型
	String filterType = (String) objMap.get("filterType");
   //已定义好的查询，新增时为空
    String[] colGroup = {};
	String[] rowGroup = {};
	String[] statColsArray = {};
	String[] statDescArray = {};
	String[] statMethodArray = {};
	String[] groupSort= {};
    FormSave formSave = new FormSave();
    if(objMap.get("formSave") !=null){
    	formSave = (FormSave) objMap.get("formSave");
	   	 statColsArray = formSave.getStatCols().split(",");
	   	 statDescArray = formSave.getStatDesc().split(",");
	   	 statMethodArray = formSave.getStatMethod().split(",");
	   	 rowGroup = formSave.getRowGroups().split(",");
	   	if(StringUtils.isNotBlank(formSave.getColGroups())){
	   		colGroup = formSave.getColGroups().split(",");
	   	}
	   	 groupSort = formSave.getGroupsSort().split(",");
   	
    }
    //将已经定义好的查询条件放入Map（配置项主键，配置项主键）
    Map conMap = new HashMap();
	if(StringUtils.isNotBlank(formSave.getConCols())){
		String[] refStrings = formSave.getConCols().split(",");
		for(int i=0;i<refStrings.length;i++) conMap.put(refStrings[i],refStrings[i]);
	}
%>
<div class="pageFormContent" layoutH="55">
<h3 class="bz_title"> 查询定义</h3>
<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="<%=StringUtils.trimToEmpty(catalog) %>"/>
<input type="hidden" name="filterType" id="filterType" value="<%=StringUtils.trimToEmpty(filterType) %>"/>
<input type="hidden" name="saveName" id="saveName" value="<%=StringUtils.trimToEmpty(saveName) %>"/>
<input type="hidden" name="themeId" id="themeId" value="<%=themeId %>"/>
<input type="hidden" name="saveId" id="saveId" value="<%=StringUtils.trimToEmpty(formSave.getSaveId()) %>"/>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center" colspan="2"><strong>基本信息</strong></th>
  </tr>
  <tr>
    <td width="50%" valign="middle">显示名称:<input type="text"  name="name" id="name" style="width:90%" value="<%=StringUtils.trimToEmpty(formSave.getName()) %>"/></td> 
 </tr>
   <tr>
     <td >详细描述:<input type="text"  name="saveDesc" id="saveDesc" style="width:90%" value="<%=StringUtils.trimToEmpty(formSave.getSaveDesc()) %>"/></td>
   </tr>
    <tr>
     <td colspan="2">条件渲染器renderer:<input type="text"  name="renderer" id="renderer" style="width:90%" value="<%=StringUtils.trimToEmpty(formSave.getRenderer()) %>"/></td>
   </tr>
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
    <th height="30" align="center" colspan="2" ><strong>表单属性设置</strong></th>
  </tr>
    <tr>
     <td colspan="2" style="text-align:left">统计字段单位（右上角展示）<input type="text"  name="statColsUnit" id="statColsUnit" style="width:70%" value="<%=StringUtils.trimToEmpty(formSave.getStatColsUnit()) %>"/></td>
   </tr>
     <tr>
     <td colspan="2" style="text-align:left">合计信息显示
      <select name="totalFlag" >
		      <option  <%if("0".equals(formSave.getTotalFlag())){%> selected <%}%>  value="0">不显示行列合计</option>
		      <option  <%if("1".equals(formSave.getTotalFlag())){%> selected <%}%>  value="1">只显示列合计</option>
		      <option  <%if("2".equals(formSave.getTotalFlag())){%> selected <%}%>  value="2">只显示行合计</option>
		      <option  <%if("3".equals(formSave.getTotalFlag())){%> selected <%}%>  value="3">显示行列合计</option>
	      </select>
     
     </td>
   </tr>
  <tr>
   <!-------------------统计列设置 start-------------------------------> 
   <td>
   <table  width="80%" border="1" valign="top" class="table_list1">
	     <tr>
	      <td >统计列</td>
	      <td >统计类型</td>
	      <td >排序</td>
	     </tr>
	      <%for(int j=0;j<rowGroup.length;j++){%>
	      <tr>
	      <td  width="20%"><select name="groups" >
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option <%if(queryColumn.getColId().equals(rowGroup[j])){%> selected <%}%> value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="groupsTypes" >
		      <option  selected value="1">行分组</option>
		      <option  value="2">列分组</option>
		    </select>
	      </td>
	      <td  width="20%"> 
		      <select name="groupSorts" >
		      <option  <%if("ASC".equals(groupSort[j])){%> selected <%}%>  value="ASC">升序</option>
		      <option  <%if("DESC".equals(groupSort[j])){%> selected <%}%>  value="DESC">降序</option>
		      </select>
          </td>
	     </tr>
	     <%} %>
	       <%for(int j=0;j<colGroup.length;j++){%>
	      <tr>
	      <td  width="20%"><select name="groups" >
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option <%if(queryColumn.getColId().equals(colGroup[j])){%> selected <%}%> value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="groupsTypes" >
		      <option   value="1">行分组</option>
		      <option  selected value="2">列分组</option>
		      </select>
	      </td>
	      <td  width="20%"> 
		      <select name="groupSorts" >
		      <option  <%if("ASC".equals(groupSort[j+rowGroup.length])){%> selected <%}%>  value="ASC">升序</option>
		      <option  <%if("DESC".equals(groupSort[j+rowGroup.length])){%> selected <%}%>  value="DESC">降序</option>
		      </select>
          </td>
	     </tr>
	     <%} %>
	       <%for(int j=0;j<5;j++){%>
	      <tr>
	      <td  width="20%"><select name="groups" >
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option  value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="groupsTypes" >
		      <option  value="1">行分组</option>
		      <option  value="2">列分组</option>
		      </select>
	      </td>
	      <td  width="20%"> 
		      <select name="groupSorts" >
		      <option  value="ASC">升序</option>
		      <option  value="DESC">降序</option>
		      </select>
          </td>
	     </tr>
	     <%} %>
    </table>
   </td>  
   <!-------------------统计列设置 end-------------------------------> 
   <!-------------------统计字段 begin-------------------------------> 
   <td valign="top">
	    <table  width="80%" border="1" class="table_list1">
	     <tr >
	      <td >统计字段</td>
	      <td >统计类型</td>
	      <td >名称描述</td>
	     </tr>
	     <%for(int j=0;j<statColsArray.length;j++){ 
	     %>
	       <tr>
	      <td  width="20%"><select name="statCols" >
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option <%if(queryColumn.getColId().equals(statColsArray[j])){%> selected <%}%> value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="statMethods" >
		      <option value="">---</option>
		      <option <%if("COUNT".equals(statMethodArray[j])){%> selected <%}%> value="COUNT">COUNT</option>
		      <option <%if("SUM".equals(statMethodArray[j])){%> selected <%}%> value="SUM">SUM</option>
		      <option <%if("MAX".equals(statMethodArray[j])){%> selected <%}%> value="MAX">MAX</option>
		      <option <%if("MIN".equals(statMethodArray[j])){%> selected <%}%> value="MIN">MIN</option>
		      <option <%if("AVG".equals(statMethodArray[j])){%> selected <%}%> value="AVG">AVG</option>
		      </select>
	      </td>
	       <td  width="20%">
	       <input type="" name="statDescs" value="<%=statDescArray[j] %>" /> 
	      </td>
	     </tr>
	     <%} %>
	     
	     <%for(int k=0;k<5;k++){ %>
	    <tr>
	      <td  width="20%"><select name="statCols" >
		      <option value="">---</option>
			<% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);         
        			if(true)  {
        				
			 %>
            <option  value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
            <%		}
            	}
             }%>
          </select></td>
	      <td  width="20%">
	        <select name="statMethods" >
		      <option  value="">---</option>
		      <option  value="COUNT">COUNT</option>
		      <option  value="SUM">SUM</option>
		      <option  value="MAX">MAX</option>
		      <option  value="MIN">MIN</option>
		      <option  value="AVG">AVG</option>
		      </select>
	      </td>
	       <td  width="20%">
	       <input type="" name="statDescs" /> 
	      </td>
	     </tr>
	     <%} %>
	     </table>
   </td>
   <!-------------------统计字段 begin-------------------------------> 
  </tr>
</table>
</form>
  <table width="95%" border="0" align="left" cellpadding="0" cellspacing="1">
    <tr>
      <td align="center">
      <%if(objMap.get("formSave") !=null){ %>
	  <input type="button" name="Submitbutton" class="shanchu_button" onclick="query_del(<%=formSave.getSaveId() %>,<%=formSave.getThemeId() %>)" />  
      <%} %>
	  <input type="button" name="Submitbutton" class="baocun_button" onclick="clickNext()" />  
      <input type="button" name="buttonReturn" class="quxiao_button" onClick="query_back()" /></td>
    </tr>
  </table>
</div>

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
    	$("#query_defineForm").attr("action","query/statForm/saveForm");
    	selectAll(document.query_defineForm.conSelect);
    	$("#query_defineForm").submit();
}
//返回到查询界面
function query_back(){
    $("#query_defineForm").attr("action","query/statForm/toTheme");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
    if(confirm("确定删除吗？")){
	    $("#query_defineForm").attr("action","query/statForm/delForm");
		$("#query_defineForm").submit();
    }
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
      
      
      


 