<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QueryColumn"%>
<%@ page import="com.wonders.tiles.query.detailquery.entity.QuerySave"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<link href="${ctx }/tiles/query/styles/css.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script> 
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
	if(document.query_defineForm.resultSelect.options.length == 0){
		alert("请选择结果字段");
		return ;
	}else if(document.query_defineForm.conSelect.options.length == 0){
		alert("请选择条件字段");
		return ;
	}else{
    	$("#query_defineForm").attr("action","query/detailQuery/saveQuery");
    	selectAll(document.query_defineForm.conSelect);
		selectAll(document.query_defineForm.resultSelect);
		selectAll(document.query_defineForm.sortSelect);
    	$("#query_defineForm").submit();
    }
	
}
//返回到查询界面
function query_back(){
	$("#query_defineForm").attr("action","query/detailQuery/queryResult");
	$("#query_defineForm").submit();
}
//删除已定义查询
function query_del(saveId,themeId){
    if(confirm("确定删除吗？")){
	    $("#query_defineForm").attr("action","query/detailQuery/delQuery");
		$("#query_defineForm").submit();
    }
}
//获取可钻取的定义列表
function query_ref(){
	$("#query_defineForm").attr("action","query/detailQuery/toQueryRef");
	$("#query_defineForm").submit();
    
}
//选择全部
function selectAll(objName){
	for(var i=0;i<objName.options.length;i++){
		objName.options[i].selected = true ;
	}
}
//设置ASC排序
function write2sortStrASC(){	
	var count = 0;
   for (var i=0, len=query_defineForm.resultSelect.options.length; i<len; i++){
       if (query_defineForm.resultSelect.options[i].selected){
            count++;
           if (count==1) query_defineForm.sortSelect.selectedIndex = -1;
				var defaultSelected = false;
			    var selected = true;
			    var optionName = new Option(query_defineForm.resultSelect.options[i].text+'(↑)', query_defineForm.resultSelect.options[i].value+',asc', defaultSelected, selected);
			     var flag = true ;
			    for(var j =0 ;j<query_defineForm.sortSelect.length;j++){			    
					if(query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+',asc'||query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+'desc')				
			    		flag = false ;			    	
			    }
			    if(flag){ 	
			    	query_defineForm.sortSelect.options[query_defineForm.sortSelect.length] = optionName;
			    }    		
         }
    }
   if (count==1) query_defineForm.sortSelect.selectedIndex = query_defineForm.sortSelect.options.length -1;
}
//设置DeSC排序
function write2sortStrDESC(){
	var count = 0;
   for (var i=0, len=query_defineForm.resultSelect.options.length; i<len; i++){
       if (query_defineForm.resultSelect.options[i].selected){
            count++;
           if (count==1) query_defineForm.sortSelect.selectedIndex = -1;
				var defaultSelected = false;
			    var selected = true;
			    var value = query_defineForm.resultSelect.options[i].value+',desc' ;
			    var text = query_defineForm.resultSelect.options[i].text+'(↓)';
			    var optionName = new Option(text, value , defaultSelected, selected);
			    var flag = true ;
			    for(var j =0 ;j<query_defineForm.sortSelect.length;j++){			    
					if(query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+',asc'||query_defineForm.sortSelect.options[j].value==query_defineForm.resultSelect.options[i].value+'desc')
			    		flag = false ;			    	
			    }
			    if(flag){					
			    	query_defineForm.sortSelect.options[query_defineForm.sortSelect.length] = optionName;
			    }    		
         }
    }
   if (count==1) query_defineForm.sortSelect.selectedIndex = query_defineForm.sortSelect.options.length -1;	
}
//是否为详细信息
function selectDetail(obj){
	document.getElementById("showDetail").value=obj.value;
}
</script>
<%
Map objMap = (Map)request.getAttribute("obj");
//分类
String catalog = (String) objMap.get("catalog");
//快速过滤名称
String saveName = (String) objMap.get("saveName");
//查找类型
String filterType = (String) objMap.get("filterType");
   //可选择列配置项列表
	List columnList = (List) objMap.get("columnList");
   //主题主键
    String themeId = (String) objMap.get("themeId");
   //钻取已定义列表，新增时为空
    List saveList =new ArrayList();
   //已定义好的查询，新增时为空
    QuerySave querySave = new QuerySave();
    if(objMap.get("querySave") !=null)
    	 querySave = (QuerySave) objMap.get("querySave");
    if(objMap.get("saveList") !=null)
    	 saveList = (List) objMap.get("saveList");
    //将已经定义好的查询条件放入Map（配置项主键，配置项主键）
    Map conMap = new HashMap();
	if(StringUtils.isNotBlank(querySave.getConCols())){
		String[] refStrings = querySave.getConCols().split(",");
		for(int i=0;i<refStrings.length;i++) conMap.put(refStrings[i],refStrings[i]);
	}
	//将已经定义好的结果条件放入Map（配置项主键，配置项名称），便于排序设置
	Map rsMap = new HashMap();
	if(StringUtils.isNotBlank(querySave.getRsCols())){
		String[] rsStrings = querySave.getRsCols().split(",");
		for(int j=0;j<columnList.size();j++) {
			QueryColumn queryColumn = (QueryColumn)columnList.get(j);    
		     for(int i=0;i<rsStrings.length;i++) {
				if(rsStrings[i].equals(queryColumn.getColId()))
					rsMap.put(rsStrings[i],queryColumn.getName());
			}
		}	
	}
	
%>
<div class="pageFormContent" layoutH="55">
<h3 class="bz_title"> 查询定义</h3>
<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="<%=StringUtils.trimToEmpty(catalog) %>"/>
<input type="hidden" name="filterType" id="filterType" value="<%=StringUtils.trimToEmpty(filterType) %>"/>
<input type="hidden" name="saveName" id="saveName" value="<%=StringUtils.trimToEmpty(saveName) %>"/>
<input type="hidden" name="themeId" id="themeId" value="<%=themeId %>"/>
<input type="hidden" name="saveId" id="saveId" value="<%=StringUtils.trimToEmpty(querySave.getSaveId()) %>"/>
<input type="hidden" name="selectRefIds" id="selectRefIds" value="<%=StringUtils.trimToEmpty(querySave.getDrillSaveIds()) %>" />
<input type="hidden" name="showDetail" id="showDetail" value="<%=StringUtils.trimToEmpty(querySave.getShowDetail()) %>" />
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list1">
  <tr>
    <th height="30" align="center"   colspan="2"><strong>基本信息</strong></th>
  </tr>
  <tr>
    <td width="50%" valign="middle">显示名称:<input type="text"  name="name" id="name" style="width:80%" value="<%=StringUtils.trimToEmpty(querySave.getName()) %>"/></td> 
    <td>被钻取名称:<input type="text"  name="refName" id="refName" style="width:80%" value="<%=StringUtils.trimToEmpty(querySave.getRefName()) %>"/></td>
   </tr>
   <tr>
     <td colspan="2">详细描述:<input type="text"  name="saveDesc" id="saveDesc" style="width:90%" value="<%=StringUtils.trimToEmpty(querySave.getSaveDesc()) %>"/></td>
   </tr>
      <tr>
     <td colspan="2">条件渲染器renderer:<input type="text"  name="renderer" id="renderer" style="width:90%" value="<%=StringUtils.trimToEmpty(querySave.getRenderer()) %>"/></td>
   </tr>
     <tr>
     <td colspan="2" height="30" style="text-align:left">可钻取定义列表: <a class="btnLook" href="query/detailQuery/toQueryRef" lookupGroup="" >可钻取定义列表: </a>
     <%
     String selectRefName = "";
     for(int i=0;i<saveList.size();i++){
    	 QuerySave tempQuerySave = (QuerySave)saveList.get(i);
    	 if("".equals(selectRefName)) selectRefName = tempQuerySave.getName();
    	 else selectRefName+=","+tempQuerySave.getName();
     }
     %>
     <input name="selectRefNames" type="text" readonly="readonly" style="width:88%" value="<%=StringUtils.trimToEmpty(selectRefName) %>"/>
	</td>
     <tr>
     <td colspan="2" style="text-align:left">是否是详细信息:
     <%if("1".equals(querySave.getShowDetail())){ %>
     <input type="radio"  name="showDetails" id="showDetails"  value="1" checked onclick="selectDetail(this);" /> 是 <input type="radio"  name="showDetails" id="showDetails"  value="0" onclick="selectDetail(this);" /> 否
      <%}else{ %>
      <input type="radio"  name="showDetails" id="showDetails"  value="1" onclick="selectDetail(this);" /> 是 <input type="radio"  name="showDetails" id="showDetails"  value="0" onclick="selectDetail(this);" checked /> 否
       <%} %>
     </td>
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
    <th height="30" align="center" ><strong>结果字段选择及排序</strong></th>
  </tr>
  <tr>
    <td align="center" valign="top"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="style_sousuo">
      <tr>
        <td height="10"></td>
      </tr>
      <tr>
        <td align="center"><table align="center" cellspacing="1" cellpadding="0">
          <tr>
            <td align="center">可选结果：<br>
                <select name="result" multiple="true"
			style="width:150px;height:160px"
			ondblclick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" >
            <% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);
           			if((queryColumn.getColumnType().equals("1")||queryColumn.getColumnType().equals("3")) && !rsMap.containsKey(queryColumn.getColId())){
			 %>
            <option value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
          <%}}} %>
          </select>
      </td>
      
            <td width="70px" align="center"><input name="button2" type="button"
			class="style_jiantou"
			onClick="pub_moveSelected(query_defineForm.result,query_defineForm.resultSelect)" value=">>>">
                <br>
                <br>
                <input name="button2" type="button" class="style_jiantou" onClick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" value="&lt;&lt;&lt;" />
            </td>
            <td align="center">已选结果：<br>
                <select name="resultSelect" multiple="true"
			style="width:150px;height:160px"
			ondblclick="pub_moveSelected(query_defineForm.resultSelect,query_defineForm.result)" >
			  <% 
			 if(columnList!=null&&columnList.size()>0){ 
       			 for(int i=0;i<columnList.size();i++){
          
        			QueryColumn queryColumn = (QueryColumn)columnList.get(i);
           			if(rsMap.containsKey(queryColumn.getColId())){
			 %>
            <option value="<%=queryColumn.getColId() %>"><%=queryColumn.getName() %></option>
          <%}}} %>
            </select>
            </td>
            <td width="70px" align="center"><input name="button2" type="button"
			class="style_jiantou"
			onClick="write2sortStrASC()"  value="升序">
                <br>
                <br>
                <input name="button2" type="button" class="style_jiantou"
			onClick="write2sortStrDESC(2)" value="降序">
            </td>
            <td align="center">排序结果：<br>
                <select name="sortSelect" multiple="true"
			style="width:150px;height:160px"
			ondblclick="pub_moveSelected(query_defineForm.sortSelect,query_defineForm.resultSelect)" >
			<%
			if(StringUtils.isNotBlank(querySave.getSortCols())){
				String[] sortStrings = querySave.getSortCols().split(",");
				for(int i=0;i<sortStrings.length;i++){
					if("desc".equals(sortStrings[i+1])){
					%>
					 <option value="<%=sortStrings[i]+","+sortStrings[i+1]%>"><%=rsMap.get(sortStrings[i])+"(↓)" %></option>
					<%}else{%>
					
					 <option value="<%=sortStrings[i]+","+sortStrings[i+1]%>"><%=rsMap.get(sortStrings[i])+"(↑)" %></option>
					<%}
					i++;}
			}
			%>
                </select>
            </td>
            <td width="70px" align="center"><input name="button2" type="button"
			class="style_jiantou"
			onClick="pub_moveSelectedUpOrDown(sortSelect,-1)"  value="上移">
                <br>
                <br>
                <input name="button2" type="button"
			class="style_jiantou"
			onClick="pub_moveSelectedUpOrDown(sortSelect,1)"  value="下移">
                <br>
                <br>
                <input name="button2" type="button" class="style_jiantou"
			onClick="deleteOption(sortSelect)" value="删除">
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

</form>
  <table width="95%" border="0" align="left" cellpadding="0" cellspacing="1">
    <tr>
      <td align="center">
      <%if(objMap.get("querySave") !=null){ %>
	  <input type="button" name="Submitbutton" class="shanchu_button" onclick="query_del(<%=querySave.getSaveId() %>,<%=querySave.getThemeId() %>)" />  
      <%} %>
	  <input type="button" name="Submitbutton" class="baocun_button" onclick="clickNext()" />  
      <input type="button" name="buttonReturn" class="quxiao_button" onClick="query_back()" /></td>
    </tr>
  </table>
</div>
  
      
      
      


 