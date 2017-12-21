<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    
    <!-- 表单验证组件 -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/Validform_v5.3.2.js"></script>
</head>
<script type="text/javascript">
function add_last(){
	$.ajax({
		type:"post",
		url:"${ctx}/config/form/addDataByLast",
		data:{
			'dreamformId':'${obj.querySave.formId}',
			'taskNo':'${obj.taskNo}'
			},
		success:function(result){
			data = eval("("+result+")");
			if (data == '1'){
				alert('保存成功！');
				$('#queryForm').submit();
			} else {
				alert('保存失败！');
			}
		}
		});
}
function add_tag(){
	$.ajax({
		type:"post",
		url:"${ctx}/config/form/addDataByTag",
		data:{
			'dreamformId':'${obj.querySave.formId}',
			'taskNo':'${obj.taskNo}'
			},
		success:function(result){
			data = eval("("+result+")");
			if (data == '1'){
				alert('保存成功！');
				$('#queryForm').submit();
			} else {
				alert('保存失败！');
			}
		}
		});
}
//根据类型查找
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$("#exportFlag").val('');
	$('#queryForm').submit();
}

function queryExport(){
	$("#exportFlag").val('1');
	$("#queryForm").submit();
	
}

function add_z(type){
	//href = "${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&taskNo=${obj.taskNo}&type="+type;
	//window.open(href)
	//var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes;status:no;");
	//window.location.reload();
	//if (returnValue==1){
	//	 query();
	// }	
	openWindowInCenter("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&taskNo=${obj.taskNo}&type="+type, 600, 800);
}
 				
function edit(busId,type,name,idCard){
	//href = "${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&busId="+busId+"&taskNo=${obj.taskNo}";
	//window.open(href)
	//var re = window.showModalDialog("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&busId="+busId+"&taskNo=${obj.taskNo}&type="+type+"&name="+encodeURI(encodeURI(name))+"&idCard="+idCard,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
	openWindowInCenter("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&busId="+busId+"&taskNo=${obj.taskNo}&type="+type+"&name="+encodeURI(encodeURI(name))+"&idCard="+idCard, 600, 800);
	//if(re){
	//	query();
	//}
}

function view(busId){
	//var re = window.showModalDialog("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&op=view&busId="+busId+"&taskNo=${obj.taskNo}",self,"dialogWidth=910px;dialogHeight=600px;status:no;");
	//if(re){
	//	query();
	//}
	openWindowInCenter("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&op=view&busId="+busId+"&taskNo=${obj.taskNo}", 600, 800);
}

//删除人员
function del(busId){
	if(confirm("确定删除吗？删除后数据不可恢复!")){
		$.post("${ctx}/config/form/delData?dreamformId=${obj.querySave.formId}&busId="+busId, 
	            { Action: "post"},
	            function (data, textStatus){
	            	if(data == "1"){
	            		alert("操作成功！");
	            		query();
	            	}else{
	            		alert("操作失败！");
	            	}
	             }
	            , "json");  
	}
}

function history(){
	window.location.href="${ctx}/config/query/toHistory?configId=${obj.accountBookTask.configId}"+
			"&currentTaskNo=${obj.taskNo}";
}

function toBack(){
	url="${ctx}/suite/config/query/toQueryList";//通过链接跳转达到返回目的
	$("#queryForm").attr("action",url);	
	$("#queryForm").submit();
}

$(function(){
	var flag = '0';
	$("#checkAll").click(function(){
		
		//所有checkbox跟着全选的checkbox走。
		$('[name=items]:checkbox').attr("checked", this.checked );
		
		if($(this).attr('checked')){
			$('#delAll').attr('value','批量删除');
			flag = '1';
		} else {
			$('#delAll').attr('value','全部删除');
			flag = '0';
		}
 	});
	
	var $tmp = '';
	$('[name=items]:checkbox').click(function(){
		//定义一个临时变量，避免重复使用同一个选择器选择页面中的元素，提升程序效率。
		$tmp=$('[name=items]:checkbox');
		//用filter方法筛选出选中的复选框。并直接给CheckedAll赋值。
		$('#checkAll').attr('checked',$tmp.length==$tmp.filter(':checked').length);
		
		if($tmp.filter(':checked').length==0){
			$('#delAll').attr('value','全部删除');
			flag = '0';
		} else {
			$('#delAll').attr('value','批量删除');
			flag = '1';
		}
	});
	
	$("#delAll").click(function(){
		if(flag=='0'){
			if(confirm("此操作将删除全部数据,删除后数据不可恢复!是否确定删除")){
				$.post("${ctx}/config/form/delAllData?dreamformId=${obj.querySave.formId}", 
			            { Action: "post"},
			            function (data, textStatus){
			            	if(data == "1"){
			            		alert("操作成功！");
			            		query();
			            	}else{
			            		alert("操作失败！");
			            	}
			             }
			            , "json");  
			}
		} else {
			var str="";
			$('[name=items]:checkbox:checked').each(function(){
				str+=$(this).val()+",";
			});
			str=str.substring(0,str.lastIndexOf(","));
			if(confirm("确定删除吗？删除后数据不可恢复!")){
				$.post("${ctx}/config/form/delAllData?dreamformId=${obj.querySave.formId}&busId="+str, 
			            { Action: "post"},
			            function (data, textStatus){
			            	if(data == "1"){
			            		alert("操作成功！");
			            		query();
			            	}else{
			            		alert("操作失败！");
			            	}
			             }
			            , "json");  
			}
		}		
	});
});


function refreshSelf(){
	setTimeOut(query(),2000);
}
</script>
<body>
<jsp:include page="/cj/header.jsp"/>
<div class="container">
	<h3 class="page-header"> 结果预览</h3>
	<p style="text-align:center;font-size:20px;"><b>${obj.querySave.name}</b></p>
	<div class="content">
	<form id="queryForm" name="queryForm" action="${ctx}/suite/config/query/toResult" method="post" class="form">
		<input type="hidden" id="saveId" name="saveId" value="${obj.querySave.saveId}">
		<input type="hidden" id="viewFlag" name="viewFlag" value="${obj.viewFlag}">
		<input type="hidden" id="exportFlag" name="exportFlag" value="">
		<div class="form-group" style="width:95%;" align="center">
			<table width="100%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
				<tr>
					<c:if test="${obj.shbz == '1'}">
						<td align="center" rowspan="2" style="background-color: #6495ED;color: white;font-size: 18px;margin-bottom: 5px">
						<c:if test="${obj.viewFlag == '1'}">
						<b>数据截止日期</b>
						</c:if>
						<c:if test="${obj.viewFlag != '1'}">
						<b>上报截止日期</b>
						</c:if>
						<br></br><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.accountBookTask.taskLimitTime }"/>
						</td>
					</c:if>
				
					<td  height="50px" align="right" >
					<c:forEach var="queryColumn" items="${obj.conditionColList}">
						<c:set var="editType" value="${queryColumn.editType}"></c:set>
						<c:set var="staDate" value="${queryColumn.nameLetter}staDATE"></c:set>
						<c:set var="endDate" value="${queryColumn.nameLetter}endDATE"></c:set>
						<label>${queryColumn.name}:</label>
						   
						    <c:if test="${editType == 1 || editType == 5}">
								<input class="form-control"  style="display:inline; width:15%" type="text" class="dfinput"   name="${queryColumn.nameLetter}" value="${obj.conMap[queryColumn.nameLetter]}">
							</c:if>
							
							<c:if test="${editType == 2}">
								<wd:select className="selectInput"  name="${queryColumn.nameLetter}" dicCode="${queryColumn.dicId}" defaultValue="${obj.conMap[queryColumn.nameLetter]}" initValue="------"/>
							</c:if>	
							<c:if test="${editType == 3}">
								<input class="form-control" style="display:inline" type="text" size="10" class="dfinput"   name="${staDate}" value="${obj.conMap[staDate]}" />
								至 
								<input class="form-control" style="display:inline" type="text" size="10" class="dfinput"   name="${endDate}" value="${obj.conMap[endDate]}" />
							</c:if>	
					</c:forEach>		
					&nbsp;&nbsp;
					<input class="form-control" style="display:inline;width:8%" type="button" class="minButton" value="查  询" onclick="query(1)" />
					</td>
				</tr>
				<tr >
					<td align="right" height="50px" >
						<c:if test="${obj.viewFlag != '1'}">
							<c:if test="${obj.shbz == '1'}">
								<input type="button" class="minButton" style="width:180px" value="根据上一次填报信息生成" onclick="add_last()" />
							</c:if>
							<c:if test="${obj.ifNeedCz == '1'}">
								<c:if test="${obj.tagFlag == '1'}">
									<input type="button" class="minButton" style="width:150px" value="根据人员标签生成" onclick="add_tag()" />
								</c:if>
								<input type="button" class="minButton" value="新增" onclick="add_z(1)" />
							</c:if>
						</c:if>
			
						<c:if test="${obj.viewFlag == '1'}">
						<c:if test="${obj.shbz == '1'}">
							<input type="button" class="minButton" value="历史版本查看" style="width:100px" onclick="history()" />
						</c:if>
						</c:if>
					
						<input class="form-control" style="display:inline;width:8%" type="button"  value="导出Excel" onclick="queryExport()" />
						<c:if test="${obj.viewFlag != '1'}">
						<c:if test="${obj.ifNeedCz == '1'}">
						<input type="button" id="delAll" class="minButton" value="全部删除" />
						</c:if>
						</c:if>
					</td>
				</tr>	
			</table>
		</div>
		<!--<h3 class="page-header">表单数据</h3>-->
		<div  style="width:95%;" align="center" >
			<table width="40%" class="table  table-hover table-bordered" align="center" border="0"> 
			
			<tr >
				<th align="center">序号</th>
			<!--	 <c:if test="${sessionScope.sessionUser.regionLevel == '1'}">
				 	<th align="center">街镇</th>
				 	<th align="center">居村</th>
				 </c:if>
				  <c:if test="${sessionScope.sessionUser.regionLevel == '2' || sessionScope.sessionUser.regionLevel == '5'}">
				 	<th align="center">居村</th>
				 </c:if>
			-->	 
				<c:forEach var="queryColumn" items="${obj.resultColList}">
					<th align="center">${queryColumn.name}</th>
				</c:forEach>
				<c:if test="${obj.ifNeedCz == '1'}">
					<th align="center" >操作</th>
				</c:if>
					<c:if test="${obj.viewFlag != '1'}">
					<c:if test="${obj.ifNeedCz == '1'}">
					<th align="center">
						<input type="checkbox" name="checkAll" id="checkAll"/>全选
					</th>
					</c:if>
					</c:if>
			</tr>
			
			<c:forEach var="resultMap" items="${obj.rsList}" varStatus="vs">
				<tr >
					<td >${vs.index+1}</td>
					<c:forEach var="resultCol" items="${obj.resultColList}">
						<td >
						<c:if test="${not empty resultCol.drillLink}">
						<!-------------------特殊处理字段 begin------------------------------->
							<c:set var="drillKey" value="#${resultMap.key}#" />
							<c:if test="${fn:contains(resultCol.drillLink, drillKey)}">
								<c:set var="linkUrl" value="${fn:replace(resultCol.drillLink, drillKey, resultMap.key)}" />
								<a href="${linkUrl}" target="navTab" rel="linkURL">
									<c:if test="${resultCol.editType == 2}">
										<!-- 字典型 -->
										<wd:dicvalue dicId="${resultCol.dicId}" dicCode="${resultMap[resultCol.nameLetter]}"/>
									</c:if>
									<c:if test="${resultCol.editType != 2 }">
										<!-- 非字典型 -->
										${resultMap[resultCol.nameLetter]}
									</c:if>
								</a>
							</c:if>
						<!-------------------特殊处理字段 end------------------------------->
						</c:if>
						<c:if test="${empty resultCol.drillLink}">
							<c:if test="${resultCol.editType == 2}">
								<!-- 字典型 -->
								<wd:dicvalue dicId="${resultCol.dicId}" dicCode="${resultMap[resultCol.nameLetter]}"/>
							</c:if>
							<c:if test="${resultCol.editType != 2 }">
								<!-- 非字典型 -->
								${resultMap[resultCol.nameLetter]}
							</c:if>				
						</c:if>
						</td>
					</c:forEach>
					<c:if test="${obj.ifNeedCz == '1'}">
					<td  align="center">
						<a href="#" onclick="view('${resultMap[obj.pkList.nameLetter]}',1)">查看</a>&nbsp;
						<c:if test="${obj.viewFlag != '1'}">
						<a href="#" onclick="edit('${resultMap[obj.pkList.nameLetter]}',2,'${resultMap['NAME']}', '${resultMap['ID_CARD']}')">修改</a>&nbsp;
					    <a href="#" onclick="del('${resultMap[obj.pkList.nameLetter]}')">删除</a>
					    </c:if>
					</td>
					</c:if>
					<c:if test="${obj.viewFlag != '1'}">
					<c:if test="${obj.ifNeedCz == '1'}">
					<td align="center">
						<input type="checkbox" name="items" value="${resultMap[obj.pkList.nameLetter]}"/>
					</td>
					</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
		<table width="96%" class="tables" align="right">
			<tr>
				<td>
					<jsp:include page="/wddc/tiles/pager/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	<br><br>	
		<div class="formBar">
		<p align="center">
			<c:if test="${obj.viewFlag != '1'}"><!-- 如果是查看，则不显示返回按钮 -->
				<button onclick="toBack()" class="btn btn-warning">返回</button>
			</c:if>	
		</p>		  			
	</div>		
	</form>
	
</div>
</div>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>