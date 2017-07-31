<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.wonders.tiles.dic.DicDataUtils"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<title>松江数据中心</title>
<jsp:include page="/common/meta.jsp"/>
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
	href = "${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&taskNo=${obj.taskNo}&type="+type;
	//window.open(href)
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes;status:no;");
	window.location.reload();
	if (returnValue==1){
		 query();
	 }
	
}
 				
function edit(busId,type,name,idCard){
	href = "${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&busId="+busId+"&taskNo=${obj.taskNo}";
	//window.open(href)
	var re = window.showModalDialog("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&busId="+busId+"&taskNo=${obj.taskNo}&type="+type+"&name="+encodeURI(encodeURI(name))+"&idCard="+idCard,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
	if(re){
		query();
	}
}

function view(busId){
	var formid=${obj.querySave.formId}+"";
	if(formid=="1456469863365"){
		//var re = window.showModalDialog("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&op=view&busId="+busId,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
		var re = window.showModalDialog("${ctx}/cx/toFarenResult?dreamformId=${obj.querySave.formId}&op=view&busId="+busId,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
		if(re){
			query();
		}
	}
	else{
		var re = window.showModalDialog("${ctx}/config/form/result?dreamformId=${obj.querySave.formId}&op=view&busId="+busId,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
		if(re){
			query();
		}
	}
	
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
</script>
<body>
<p style="height:10px;"></p>
<form id="queryForm" name="queryForm" action="${ctx}/config/query/toResult" method="post">
	<input type="hidden" id="saveId" name="saveId" value="${obj.querySave.saveId}">
	<input type="hidden" id="taskNo" name="taskNo" value="${obj.taskNo}">
	<input type="hidden" id="viewFlag" name="viewFlag" value="${obj.viewFlag}">
	<input type="hidden" id="exportFlag" name="exportFlag" value="">
	<c:if test="${obj.onekey==null }">
		<table width="96%" border="0" height="60" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
				
				<tr>
					<td align="center" rowspan="2" style="background-color: #6495ED;color: white;font-size: 18px;margin-bottom: 5px">
					<td  height="50px" align="right">
					<c:forEach var="queryColumn" items="${obj.conditionColList}">
						<c:set var="editType" value="${queryColumn.editType}"></c:set>
						<c:set var="staDate" value="${queryColumn.nameLetter}staDATE"></c:set>
						<c:set var="endDate" value="${queryColumn.nameLetter}endDATE"></c:set>
						&nbsp;&nbsp;${queryColumn.name}:
						    <c:if test="${editType == 1 || editType == 5}">
								<input type="text" class="dfinput"   name="${queryColumn.nameLetter}" value="${obj.conMap[queryColumn.nameLetter]}">
							</c:if>
							<c:if test="${editType == 2}">
								<wd:select className="selectInput"  name="${queryColumn.nameLetter}" dicCode="${queryColumn.dicId}" defaultValue="${obj.conMap[queryColumn.nameLetter]}" initValue="------"/>
							</c:if>	
							<c:if test="${editType == 3}">
								<input type="text" size="10" class="dfinput"   name="${staDate}" value="${obj.conMap[staDate]}" />
								至 
								<input type="text" size="10" class="dfinput"   name="${endDate}" value="${obj.conMap[endDate]}" />
							</c:if>	
					</c:forEach>		
					&nbsp;&nbsp;<input type="button" class="minButton" value="查  询" onclick="query(1)" />
					&nbsp;
					</td>
				</tr>
				
		</table>
		</c:if>
		<table width="96%" class="table_list" align="center">
		<tr>
			<th align="left">序号</th>
			 <c:if test="${sessionScope.sessionUser.regionLevel == '1'}">
					 	<th align="center">街镇</th>
					 	<th align="center">居村</th>
					 </c:if>
					  <c:if test="${sessionScope.sessionUser.regionLevel == '2' || sessionScope.sessionUser.regionLevel == '5'}">
					 	<th align="center">居村</th>
					 </c:if>
			<c:forEach var="queryColumn" items="${obj.resultColList}">
				<th align="center">${queryColumn.name}</th>
			</c:forEach>
				<th align="center" >操作</th>
		</tr>
		<c:forEach var="resultMap" items="${obj.rsList}" varStatus="vs">
		<tr >
			<td align="center">${vs.index+1}</td>
			 <c:if test="${sessionScope.sessionUser.regionLevel == '1'}">
				 	<td align="center">&nbsp;<%-- <wd:orgCode orgCode="${resultMap['BELONG_TOWN']}" /> --%></td>
				 	<td align="center">&nbsp;<%-- <wd:orgCode orgCode="${resultMap['BELONG_COMMUNITY']}" /> --%></td>
				 </c:if>
				  <c:if test="${sessionScope.sessionUser.regionLevel == '2' ||sessionScope.sessionUser.regionLevel == '5'}">
				 	<td align="center">&nbsp;<%-- <wd:orgCode orgCode="${resultMap['BELONG_COMMUNITY']}" /> --%></td>
				 </c:if>
			<c:forEach var="resultCol" items="${obj.resultColList}">
				<td align="left">
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
			<td  align="center">
				<a href="#" onclick="view('${resultMap[obj.pkList.nameLetter]}'),1">查看</a>&nbsp;
			</td>
		</tr>
		</c:forEach>
	</table>
			
		<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>