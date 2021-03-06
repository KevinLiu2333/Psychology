<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h3 id="disable-responsive2" class="page-header">文件缓存配置</h3>
<form id="form1" action="${ctx }/suite/data/df/toSavaInfo" method="post">
	<input type="hidden" name="info.id" value="${obj.info.id }"/>
	<input type="hidden" name="info.filepath" value="${obj.info.filepath }"/>
	<input type="hidden" name="info.cacheType" value="${obj.info.cacheType }"/>
	<input type="hidden" name="info.cornTime" value="${obj.info.cornTime }"/>
	<div class="row col-md-offset-1">
		<div class="col-md-6 form-group">
		     	<div  style="float: left;width: 150px;"> 名称：</div>
		     	<input id="name" style="width: 250px;"  name="info.name" value="${obj.info.name }">
		</div>
		<div class="col-md-6 form-group">
		<div  style="float: left;width: 150px;"> 文件格式：</div>
		<select id="filetype" name="info.filetype" style="width:250px;height:30px" ></select>
	    </div>
	</div>
	<h4 id="disable-responsive2" class="page-header">数据项</h4>
	<div class="row">
		<div class="col-md-6 form-group col-md-offset-1">
			<table id="headerTable" class="tables" width="200%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="35%">数据项</th>
					<th class="label_row" align="center" width="20%">查询类别</th>
					<th class="label_row" align="center" width="10%">数据项key</th>
					<th class="label_row" align="center" width="3%"><input class=" btn btn-warning" type="button" value="添加" onclick="addheader()"/></th>
					<th class="label_row" align="center" width="5%"><input class=" btn btn-warning" type="button" value="自定义" onclick="addCustom()"/></th>
				</tr>
				<c:if test="${obj.list==null||fn:length(obj.list)==0 }">
					<tr id="headerbranch">
						<%-- <td><input style="width: 300px;"  name="info.datas[0].name" type="text" value="${obj.info.datas[0].name }"></td> --%>
						<td><select id="datafileid" onchange="changetrem(this)" style="width:350px;height:30px" name="info.fileList[0].datafileid" value="${rows.dataid }" type="data"></select></td>
						<td>
							<select type="type" name="info.fileList[0].type" style="width:200px;height:30px" defalutvalue="${rows.type }"></select>
						</td>
						<td><input id="keyName" name="info.fileList[0].name" style="width:200px;height:30px" defalutvalue="${rows.name }"></td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
					<c:if test="${obj.customList==null||fn:length(obj.customList)==0 }">
						<table id="customTable" width="190%" style="display:none;">
							<tr>
								<th class="label_row" align="center" width="10%">自定义key</th>
								<th class="label_row" align="center" width="28%">自定义value</th>
								<th class="label_row" align="center" width="2%"><input class=" btn btn-warning" type="button" value="添加" onclick="addCustomTb()"/></th>
							</tr>
							
							<tr id="customTd" style="height:42px">
								<td ><input id="customKey" name="info.customList[0].customkey" style="width:90%;height:30px" defalutvalue="${rows.customKey }"></td>
								<td ><input id="customValue" name="info.customList[0].customvalue" style="width:90%;height:30px" defalutvalue="${rows.customValue }"></td>
								<td ><a href="#" onclick="deleteCustomTr(this);" style="display:none;" id="delBtn3">删除</a></td>
							</tr>
						</table>
					</c:if>
				</c:if>
					
				<c:if test="${obj.list!=null||fn:length(obj.list)>0 }">
					<c:forEach items="${obj.list}" var="row" varStatus="rows">
					<c:if test="${rows.index == 0}">
							<tr id="headerbranch">
					</c:if>
						<%-- <td><input style="width: 300px;"  name="info.datas[0].name" type="text" value="${obj.info.datas[0].name }"></td> --%>
						<td><select id="datafileid" onchange="changetrem(this)" style="width:300px;height:30px" name="info.fileList[${rows.index}].datafileid" value="${row.dataid }" type="data"></select></td>
						<td>
							<select type="type" name="info.fileList[${rows.index}].type" style="width:150px;height:30px" defalutvalue="${row.type }"></select>
						</td>
						<td><input id="keyName" name="info.fileList[${rows.index}].name" style="width:200px;height:30px" value="${row.name }"></td>
						<td><a href="#" onclick="deletetr(this);" style="display:block;" id="delBtn2">删除</a></td>
					</tr>
					</c:forEach>
				</c:if>
				<c:if test="${obj.customList!=null||fn:length(obj.customList)>0 }">
					<table id="customTable" width="190%" style="display:block;">
					<tr>
						<th class="label_row" align="center" >自定义key</th>
						<th class="label_row" align="center" >自定义value</th>
						<th class="label_row" align="center" ><input class=" btn btn-warning" type="button" value="添加" onclick="addCustomTb()"/></th>
					</tr>
					<c:if test="${fn:length(obj.customList)==0}">
						<tr id="customTd" style="height:42px">
							<td width="225px"><input id="customKey" name="info.customList[0].customkey" width="225px"  value='${row.customkey }'></td>
							<td width="650px"><input id="customValue" name="info.customList[0].customvalue" style="width:650px"  value='${row.customvalue }'></td>
							<td width="2%"><a href="#" onclick="deleteCustomTr(this);" style="display:block;" id="delBtn3">删除</a></td>
						</tr>
					</c:if>
					<c:forEach items="${obj.customList}" var="row" varStatus="rows">
						<tr id="customTd" style="height:42px">
							<td width="225px"><input id="customKey" name="info.customList[${rows.index}].customkey" width="225px"  value='${row.customkey }'></td>
							<td width="650px"><input id="customValue" name="info.customList[${rows.index}].customvalue" style="width:650px"  value='${row.customvalue }'></td>
							<td width="2%"><a href="#" onclick="deleteCustomTr(this);" style="display:block;" id="delBtn3">删除</a></td>
						</tr>
					</c:forEach>
					</table>
				</c:if>
			</table>
		</div>
	</div>
	
</form>
</div>
	<div  class="col-md-10 col-md-offset-1 form-group ">
		<div class="alert alert-info col-md-12" style="width:100%">
	       <strong>
		       <h4>提示：</h4>
		       <ul>
			       <li>SR(单一结果):表示仅输出第一行第一列的一个数据</li>
			       <li>KV(单条记录):表示输出第一行的数据</li>
			       <li>LM(list_map):表示使用key-value输出全部数据</li>
			       <li>MA(map数组):表示使用List的方式输出，列如：{"key":[111,222,333,444]}</li>
		       </ul>
	       </strong>
	    </div>
	</div>
	<div class="col-md-12" style="margin-bottom:60px">
		<p align="center">
			<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
	       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
		</p>
	</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
var isnotdic="{'SR':'SR(单一结果)','KV':'KV(单条记录)','LM':'LM(list_map)','MA':'MA(map数组)'}";
$("select[type='type']").each(function(){
	var defaultval=$(this).attr("defalutvalue");
	$(this).dicselect({
		dic:isnotdic,
		defaultvalue:defaultval
	});
});
var sqllistName;
var sqlindex=0;
var headerindex=0;
var headerName;
var customIndex = 0;

$(document).ready(function(){
	headerindex = '${fn:length(obj.list)}';
	if(headerindex == 0) headerindex=1;
	headerName = "info.fileList[0]";
	
	var isnotdic="{'0':'JSON','1':'XML'}";
	$('#filetype').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.info.filetype }'
	});
	sqllistName='${obj.info.datas[0].id }';
	sqlindex='${fn:length(obj.info.datas)}';
	$.post("${ctx}/suite/data/mult/getAllTerm",function(data){
		$("select[type='data']").each(function(){
			$(this).dicselect({
				json:data,
				defaultvalue:$(this).attr('value'),
				initvalue:'请选择'
			});
			changetrem(this);
		});
	});
});

function addCustom(){
	if($("#customTable").attr("style") == "display:none;"){
		$("#customTable").attr("style","display:block;");
	}else{
		$("#customTable").attr("style","display:none;");
		$("#customTd > td > input").val(null);
	}
}

var customLength = $("#customTable > tbody").children("tr").length-1;

function addCustomTb(){
	if(customIndex==0){
		customIndex=1;
	}
	var cloneTr = $("#customTd").clone();
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		$(this).attr("name",thisName.replace("info.customList[0]","info.customList["+ customLength + "]"));
		if($(this).attr('id')!='customKey'){
			$(this).val("");
		}
		if($(this).attr('id')!='customValue'){
			$(this).val("");
		}
	})
	$(cloneTr).find("a[id='delBtn3']").eq(0).show();
	$(cloneTr).appendTo($("#customTable"));
	customIndex++;
	customLength++;
}

function addheader(){
	var cloneTr = $("#headerbranch").clone();
	$(cloneTr).attr("id",$("#headerbranch").attr("id")+"_"+headerindex);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(headerName) != -1){
				$(this).attr("name",thisName.replace(headerName,"info.fileList["+ headerindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+headerindex));
		}
		if($(this).attr('id')!='reportinfoid'){
			$(this).val("");
		}
		if($(this).attr('id')!='headerurl'){
			$(this).val("");
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(headerName) != -1){
				$(this).attr("name",thisName.replace(headerName,"info.fileList["+ headerindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+headerindex));
		}
		$(this).find("option:first").prop("selected",'selected');
		$(this).show();
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	headerindex++; 
	$(cloneTr).appendTo($("#headerTable"));
}


function deletetr(obj){
	if(confirm("确定删除吗？")){
		if($("#headerTable > tbody").children("tr").length > 2){
			$(obj).parent().parent().remove();
		}else{
			alert("最后一条数据不可删除，只可清空！");
		}
	}
}
function deleteCustomTr(obj){
	if(confirm("确定删除吗？")){
		if($("#customTable > tbody").children("tr").length > 2){
			$(obj).parent().parent().remove();
		}else{
			alert("最后一条数据不可删除，只可清空！");
		}
	}
}
function save(){
	if($('#name').val()==null||$('#name').val()==''){
		alert("请输入缓存名称！");return;
	}
	for(var i = 0; i< $("[id='keyName']").length;i++){
		if($("[id='keyName']")[i].value == null||$("[id='keyName']")[i].value == ""){
			alert("数据项key不能为空！！");
			return;
		}
		if($("[id='datafileid']")[i].value==null||$("[id='datafileid']")[i].value ==''){
			alert("数据项不能为空！！");
			return;
		}
		for(var j = i+1;j< $("[id='keyName']").length;j++){
			if($("[id='keyName']")[i].value == $("[id='keyName']")[j].value){
				alert("数据项key不能相同！！"+$("[id='keyName']")[i].value+'-----'+$("[id='keyName']")[j].value);
				
			}	
		}
	}
	for(var i = 0; i< $("[id='customKey']").length;i++){
		if($("#customValue").val() != "" && $("#customKey").val() == "" ){
			alert("自定义value:"+$("#customValue").val()+"对应的key不能为空");
			return;
		}
		for(var j = i+1;j< $("[id='customKey']").length;j++){
			if($("[id='customKey']")[i].value == $("[id='customKey']")[j].value){
				alert("自定义key不能相同！！"+$("[id='customKey']")[i].value+'-----'+$("[id='customKey']")[j].value);
				return;
			}
		}
		if($("#customTable > tbody").children("tr").length > 2){
			var customTableLength = $("#customTable > tbody").children("tr").length;
			for(var i = 2; i < customTableLength+1 ; i++){
				if($("#customTable > tbody > tr:nth-child("+i+")> td:nth-child(1) > #customKey").val() == "" || $("#customTable > tbody > tr:nth-child("+i+") > td:nth-child(2) > #customValue").val() == ""){
					alert("自定义key或者自定义value不能为空！");
					return;
				}
			}
			
		}
	}
	$('#form1').submit();
}

function changetrem(select){
	if($(select).val()!='')
	{
		$.post("${ctx}/suite/data/df/getsqlfield",
			{id:$(select).val()},
			function(data){
				$(select).parent().parent().find('td[id="sqlfield"]').html(data);
		});
	}else{
		$(select).parent().parent().find('td[id="sqlfield"]').html('');
	}			
}

function browseFolder(path) {
	try {
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
        var Shell = new ActiveXObject("Shell.Application");
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
        //var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
        if (Folder != null) {
            Folder = Folder.items(); // 返回 FolderItems 对象
            Folder = Folder.item(); // 返回 Folderitem 对象
            Folder = Folder.Path; // 返回路径
            if (Folder.charAt(Folder.length - 1) != "\\") {
                Folder = Folder + "\\";
            }
            document.getElementById(path).value = Folder;
            return Folder;
        }
    }
    catch (e) {
        alert(e.message);
    }
}

</script>
</html>