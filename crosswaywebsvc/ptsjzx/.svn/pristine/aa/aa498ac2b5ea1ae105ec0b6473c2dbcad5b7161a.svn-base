<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx }/tiles/query/scripts/selectAndCheck.js"></script> 

<div class="pageContent" layoutH="55">
<form id="query_defineForm" name="query_defineForm" action="" method="post" onsubmit="return navTabSearch(this);">
<input type="hidden" name="catalog" id="catalog" value="${obj.catalog}"/>
<input type="hidden" name="filterType" id="filterType" value="${obj.filterType}"/>
<input type="hidden" name="saveName" id="saveName" value="${obj.saveName}"/>
<input type="hidden" name="chartCatalog" id="chartCatalog" value="${obj.chartSave.catalogName}"/>
<input type="hidden" name="themeId" id="themeId" value="${obj.themeId}"/>
<input type="hidden" name="saveId" id="saveId" value="${obj.chartSave.saveId}"/>
<input type="hidden" name="selectRefId" id="selectRefId" value="${obj.chartSave.drillSaveId}" />
<input type="hidden" name="refType" id="refType" value="${obj.chartSave.drillType}"/>
<div class="panelBar" >
	<ul class="toolBar">
		<li><span style="font-weight: bolder">基本信息</span></li>
	</ul>
</div>
<table width="95%"  class="table">
	<tr>
		<td width="20%" align="right" >显示名称：</td>
		<td colspan="2" width="80%"><input type="text" name="name" id="name" style="width:90%" value="${obj.chartSave.name}"/></td> 
	</tr>
	<tr>
		<td align="right" >详细描述：</td>
		<td colspan="2"><input type="text" name="saveDesc" id="saveDesc" style="width:90%" value="${obj.chartSave.saveDesc}"/></td>
	</tr>
	<tr>
		<td align="right">条件渲染器renderer：</td>
		<td colspan="2"><input type="text" name="renderer" id="renderer" style="width:90%" value="${obj.chartSave.renderer}"/></td>
	</tr>
	<tr>
		<td width="20%" align="right">可钻取定义列表：</td>
		<td width="70%">
			<input name="selectRefName" type="text" readonly="readonly" style="width:88%" value="${obj.selectRefName}"/>
		</td>
		<td width="10%"><a class="btnLook" href="query/statChart/toChartRef" lookupGroup="" >可钻取定义列表： </a></td>
	</tr>
</table>

<div class="panelBar" style="margin-top: 5px;">
	<ul class="toolBar">
		<li><span style="font-weight: bolder">条件字段选择</span></li>
	</ul>
</div>
<table width="70%" align="center">
	<tr>
		<td  height="20"></td>
	</tr>
	<tr>
		<td align="center">可选条件<br> 
			<select name="fromForm" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" >
				<c:forEach var="queryColumn" items="${obj.unselectedCons}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
			</select>
		</td>
		<td width="70px" align="center">
			<input name="button3" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.fromForm,query_defineForm.conSelect)" value="&gt;&gt;&gt;">
			<br>
			<br>
			<input name="button" type="button" class="style_jiantou" onclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" value="&lt;&lt;&lt;" >
		</td>
		<td align="center">已选条件<br>
			<select name="conSelect" multiple="true" style="width:260px;height:160px;" ondblclick="pub_moveSelected(query_defineForm.conSelect,query_defineForm.fromForm)" >
				<c:forEach var="queryColumn" items="${obj.selectedCons}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td  height="20"></td>
	</tr>	
</table>
<div class="panelBar" >
	<ul class="toolBar">
		<li><span style="font-weight: bolder">图表属性设置</span></li>
	</ul>
</div>
<!--X轴设置开始-->
<table  width="95%" class="table">
	<thead>
		<tr>
			<th>&gt;&gt;&nbsp;X轴设置</th>
		</tr>
	</thead>
	<tbody>
	  <tr>
	   <td align="right">分组字段：</td>
	   <td width="75%">
	   	 <select name="xGroup"  style="width:50%" >
			<c:forEach var="xGroupColumn" items="${obj.columnList}">
				<option value="${xGroupColumn.colId}" <c:if test="${xGroupColumn.colId == obj.chartSave.xgroup}">selected</c:if> >${xGroupColumn.name}</option>
			</c:forEach>
		 </select>
		</td>
	  </tr>
	  <tr>
	   <td align="right">排序：</td>
	   <td >
	     <select name="xSort" style="width:50%">
	      <option value="">---</option>
	      <option <c:if test="${'ASC' == obj.sortMap[obj.chartSave.xgroup] }">selected</c:if> value="ASC">升序</option>
		  <option  <c:if test="${'DESC' ==obj.sortMap[obj.chartSave.xgroup] }">selected</c:if> value="DESC">降序</option>
	      </select>
	    </td>
	  </tr>  
	  <tr>
	   <td align="right">X轴名称：</td>
	   <td >
	      <input type="text"  name="xAxisName" id="xAxisName" style="width:90%" value="${obj.chartSave.xaxisName}"/>
	   </td>
	  </tr>
	</tbody>
 </table>
 <!--X轴设置结束-->
 <!--展示图种类设置开始-->
 <table  width="95%" class="table">
 	<thead>
		<tr>
			<th>&gt;&gt;&nbsp;展示图种类设置</th>
		</tr>
	</thead>
	<tbody>
		     <tr >
		      <td rowspan="5" width="20%" align="right">简单图表（统计一列）：</td>
		     </tr>
		      <tr >
		      <td width="10%">饼图</td>
		      <td width="70%">
		     	 <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Pie2D' == obj.chartSave.catalogName }"> checked </c:if> value="Pie2D" />Pie2D
		    	 <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Pie3D' == obj.chartSave.catalogName }"> checked </c:if> value="Pie3D" />Pie3D
		     </td>
		     </tr>
		       <tr >
		      <td >柱状图</td>
		      <td > 
		      	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Column2D' == obj.chartSave.catalogName }"> checked </c:if> value="Column2D" />Column2D
		      	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Column3D' == obj.chartSave.catalogName }"> checked </c:if> value="Column3D" />Column3D
		     </td>
		     </tr>
		       <tr >
		      <td >折线图</td>
		      <td > <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Line' == obj.chartSave.catalogName }"> checked </c:if> value="Line" />Line
		      </td>
		     </tr>
		       <tr >
		      <td >其他</td>
		      <td > 
		      	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'Doughnut2D' == obj.chartSave.catalogName }"> checked </c:if> value="Doughnut2D" />Doughnut2D
		     	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Doughnut3D' == obj.chartSave.catalogName }"> checked </c:if> value="Doughnut3D" />Doughnut3D
		     	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Bar2D' == obj.chartSave.catalogName }"> checked </c:if> value="Bar2D" />Bar2D
		     	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs"  <c:if test="${'Area2D' == obj.chartSave.catalogName }"> checked </c:if> value="Area2D" />Area2D
	           </td>
		     </tr>
		     
		       <tr >
		      <td rowspan="5" width="20%" align="right">复杂图表（统计多列）：</td>
		     </tr>
		      <tr >
		      <td >单轴柱状图</td>
		      <td >
	        	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'MSColumn2D' == obj.chartSave.catalogName }"> checked </c:if>  value="MSColumn2D" />MSColumn2D
		      	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'MSColumn3D' == obj.chartSave.catalogName }"> checked </c:if> value="MSColumn3D" />MSColumn3D
		     	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'StackedColumn2D' == obj.chartSave.catalogName }"> checked </c:if> value="StackedColumn2D" />StackedColumn2D
		     	<input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'StackedColumn3D' == obj.chartSave.catalogName }"> checked </c:if> value="StackedColumn3D" />StackedColumn3D
		     </td>
		     </tr>
		       <tr >
		      <td >单轴折线图</td>
		      <td > 
		   		<input type="radio" onclick="selectCheck(this)"  name="chartCatalogs" <c:if test="${'ScrollLine2D' == obj.chartSave.catalogName }"> checked </c:if> value="ScrollLine2D" />ScrollLine2D
		     </td>
		     </tr>
		       <tr >
		      <td >双轴复合图</td>
		      <td > 
		    	 <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'MSColumn3DLineDY' == obj.chartSave.catalogName }"> checked </c:if> value="MSColumn3DLineDY" />MSColumn3DLineDY
		      </td>
		     </tr>
		       <tr >
		      <td >其他</td>
		     <td >
		      	 <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'ScrollColumn2D' == obj.chartSave.catalogName }"> checked </c:if> value="ScrollColumn2D" />ScrollColumn2D
		     	 <input type="radio"  onclick="selectCheck(this)" name="chartCatalogs" <c:if test="${'ScrollStackedColumn2D' == obj.chartSave.catalogName }"> checked </c:if> value="ScrollStackedColumn2D" />ScrollStackedColumn2D
		     </td>
		     </tr>
	</tbody>
</table>
<!--展示图种类设置结束-->
<!--统计列定义开始-->
<table  width="95%" class="table">
	<thead>
		<tr>
			<th>&gt;&gt;&nbsp;Y轴设置<span style="color:red">（注意：定义顺序为先主轴后副轴（存在副轴情况下））</span></th>
		</tr>
	</thead>
	<tbody>
	     <tr>
	      <td >统计列</td>
	      <td >统计类型</td>
	      <td >显示名称</td>
	      <td >排序</td>
	      <td >图表显示</td>
	     </tr>
	     <c:forEach var="yStat" items="${obj.yStats}" varStatus="vs" step="2">
	     <tr>
	      	<td  width="20%">
	      		<select name="yGroups" >
		      		<option value="">---</option>
		      		<c:forEach var="queryColumn" items="${obj.columnList}" >
            			<option <c:if test="${queryColumn.colId == obj.yStats[vs.index] }"> selected </c:if>  value="${queryColumn.colId }">${queryColumn.name }</option>
         		 	</c:forEach>
         		 </select>
         	</td>
	      <td  width="20%">
	        <select name="yStats" >
		      <option value="">---</option>
		      <option <c:if test="${'COUNT' == obj.yStats[vs.index+1] }"> selected </c:if> value="COUNT">COUNT</option>
		      <option <c:if test="${'SUM' == obj.yStats[vs.index+1] }"> selected </c:if> value="SUM">SUM</option>
		      <option <c:if test="${'MAX' == obj.yStats[vs.index+1] }"> selected </c:if> value="MAX">MAX</option>
		      <option <c:if test="${'MIN' == obj.yStats[vs.index+1] }"> selected </c:if> value="MIN">MIN</option>
		      <option <c:if test="${'AVG' == obj.yStats[vs.index+1] }"> selected </c:if> value="AVG">AVG</option>
		     </select>
	      </td>
	      <td><input type="text"  name="yAxisNames" id="yAxisNames" style="width:100%" value="${obj.yAxisNames[vs.index/2] }"/></td>
	      <td  width="20%"> 
		      <select name="ySorts" >
		      <option value="">---</option>
		      <option <c:if test="${'ASC' == obj.sortMap[obj.yStats[vs.index]] }">selected</c:if> value="ASC">升序</option>
		      <option <c:if test="${'DESC' == obj.sortMap[obj.yStats[vs.index]] }">selected</c:if> value="DESC">降序</option>
		      </select>
          </td>
	      <td width="20%">
	      <select name="vyShows" >
		      <option value="0">不显示</option>
		      <option value="1" <c:if test="${ obj.syShowMap[obj.yStats[vs.index]] == obj.yStats[vs.index] }">selected</c:if> >主轴</option>
		      <option value="2" <c:if test="${ obj.vyShowMap[obj.yStats[vs.index]] == obj.yStats[vs.index] }">selected</c:if>  >副轴</option>
		  </select>
	      </td>
	     </tr>
	     </c:forEach>
	     <%for(int k=0;k<5;k++){ %>
	     <tr>
	      <td  width="20%">
		      <select name="yGroups" >
		      	<option value="">---</option>
				<c:forEach var="queryColumn" items="${obj.unselectedCons}">
					<option value="${queryColumn.colId}">${queryColumn.name}</option>
				</c:forEach>
	          </select>
          </td>
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
	</tbody>
</table>
<!--统计列定义结束-->
</form>
</div>

<!-- 操作工具栏 -->
<div class="formBar">
	<ul>
		<li>
		<c:if test="${not empty obj.chartSave}">
			<div class="button"><div class="buttonContent"><button type="button" onclick="chart_query_del('${obj.querySave.saveId}','${obj.querySave.themeId}')">删除</button></div></div>
		</c:if>
		</li>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="chart_clickNext()">保存</button></div></div>
		</li>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="chart_query_back()">取消</button></div></div>
		</li>		
	</ul>
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
function chart_clickNext(){
    	$("#query_defineForm").attr("action","query/statChart/saveChart");
    	selectAll(document.query_defineForm.conSelect);
    	$("#query_defineForm").submit();
}
//返回到查询界面
function chart_query_back(){
    $("#query_defineForm").attr("action","query/statChart/toTheme");
	$("#query_defineForm").submit();
}
//删除已定义查询
function chart_query_del(saveId,themeId){
    if(confirm("确定删除吗？")){
	    $("#query_defineForm").attr("action","query/statChart/delChart");
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