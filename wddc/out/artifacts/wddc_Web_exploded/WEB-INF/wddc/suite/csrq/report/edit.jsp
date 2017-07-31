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
    	<link rel="stylesheet" href="${ctx }/wddc/tiles/bootstrap-select/css/bootstrap-select.css">
    	<script src="${ctx }/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script src="${ctx }/wddc/tiles/bootstrap-select/js/bootstrap-select.js"></script>
    	
    	<!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
	</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
<h2 id="disable-responsive2" class="page-header" style="margin-top: 5px">报表配置
	<span  style="float: right;">
		<button type="button" class="btn btn-warning" onclick="adddic()" style="width:90px">添加字典&nbsp;</button> &nbsp;
		<button type="button" class="btn btn-warning" onclick="addterm()" style="width:90px">添加统计项</button>&nbsp; 
		<button type="button" class="btn btn-warning" onclick="update()" style="width:90px">数据更新</button>
	</span>
</h2>
<form id="form1" action="${ctx }/suite/csrq/report/save" method="post">
	<input type="hidden" name="info.id" value="${obj.info.id }"/>
	<input type="hidden" name="info.iscache" value="${obj.info.iscache }"/>
	<input type="hidden" name="info.hashead" value="${obj.info.hashead }"/>
	<input type="hidden" name="info.hasrow" value="${obj.info.hasrow }"/>
	<input type="hidden" name="info.fixedTime" value="${obj.info.fixedTime }"/>
	<div class="row">
	<div class="col-md-6 form-group">
	<div class="row">
		<div class="col-md-12 form-group">
		     	<div  style="float: left;width: 150px;"> 报表名称：</div>
		     	<input id="name" style="width: 500px;"  name="info.name" value="${obj.info.name }">
		</div>
		<div class="col-md-12 form-group">
				<div  style="float: left;width: 150px;"> 报表标签：</div>
				<select   multiple="" id="tagList" name="info.tag" class="select2" data-placeholder="请选择标签...">
				</select>
		</div>
		<div class="col-md-6 form-group">
				<div  style="float: left;width: 150px;"> 是否需要行求和：</div>
				<select id="rowsum" name="info.rowsum" style="width:200px;height:30px"></select>
		</div>
		<div class="col-md-6 form-group">
				<div  style="float: left;width: 150px;"> 是否需要列求和：</div>
				<select id="colsum" name="info.colsum" style="width:200px;height:30px"></select>
		</div>
	</div>
	</div>
	<div class="col-md-6 form-group"> 
		<div >图例：</div> 
		<div>
			<img alt="" src="${ctx}/wddc/skins/images/report_example.jpg" height="200px" width="330px"> 
		</div>
	</div>
	</div>
	<h3 id="disable-responsive2" class="page-header">数据来源</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			<table id="dataTable" class="tables" width="100%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<thead>
					<tr>
						<th class="label_row" align="center" width="30%">数据项</th>
						<th class="label_row" align="center" width="50%">字段名</th>
						<th class="label_row" align="center" width="20%"><input class="btn btn-warning" type="button" value="+" onclick="adddata()"/></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${obj.info.datas==null||fn:length(obj.info.datas)==0 }">
					<tr id="databranch">
						<td>
							<input type="hidden" value="${obj.info.datas[0].id }" name="info.datas[0].id">
							<input id="reportinfoid" type="hidden" value="${obj.info.id }" name="info.datas[0].reportinfoid">
							<!--<wd:select initValue="请选择" onchange="changetrem(this)" style="width:300px;height:30px" name="info.datas[0].mtdmid" dicCode="10008" defaultValue="${obj.info.datas[0].mtdmid }"/>
							--><select onchange="changetrem(this)" style="width:300px;height:30px" name="info.datas[0].mtdmid" value="${obj.info.datas[0].mtdmid }" type="data"></select>
						</td>
						<td id="sqlfield"></td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
					</c:if>
					<c:if test="${obj.info.datas!=null&&fn:length(obj.info.datas)>0 }">
						<c:forEach items="${obj.info.datas}" var="data" varStatus="row">
							<c:if test="${row.index == 0}">
								<tr id="databranch">
							</c:if>
							<c:if test="${row.index != 0}">
								<tr>
							</c:if>
								<td>
									<input type="hidden" value="${obj.info.datas[row.index].id }" name="info.datas[${row.index}].id">
									<input id="reportinfoid" type="hidden" value="${obj.info.datas[row.index].reportinfoid}" name="info.datas[${row.index}].reportinfoid">
									<!--<wd:select initValue="请选择" onchange="changetrem(this)" style="width:300px;height:30px" name="info.datas[${row.index}].mtdmid" dicCode="10008" defaultValue="${obj.info.datas[row.index].mtdmid }"/>
									--><select onchange="changetrem(this)" style="width:300px;height:30px" name="info.datas[${row.index}].mtdmid" value="${obj.info.datas[row.index].mtdmid }" type="data"></select>
								</td>
								<td id="sqlfield"></td>
								<td>
									<c:if test="${row.index == 0}">
										<a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a>
									</c:if>
									<c:if test="${row.index != 0}">
										<a href="#" onclick="deletetr(this);"  id="delBtn2">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<h3 id="disable-responsive2" class="page-header">横表头</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			<table id="headerTable" class="tables" width="100%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="30%">表头字典</th>
					<th class="label_row" align="center" width="30%">字段名</th>
					<th class="label_row" align="center" width="15%">中文key</th>
					<th class="label_row" align="center" width="15%">是否需要求和</th>
					<th class="label_row" align="center" width="10%"><input class="btn btn-warning" type="button" value="+" onclick="addheader()"/></th>
				</tr>
				<c:if test="${obj.info.headers==null||fn:length(obj.info.headers)==0 }">
					<tr id="headerbranch">
						<td>
							<input id="reportinfoid" type="hidden" name="info.headers[0].reportinfoid" value="${obj.info.id }" />
							<input type="hidden" name="info.headers[0].id" value="${obj.info.headers[0].id }" />
							<input type="hidden" name="info.headers[0].url" value="${obj.info.headers[0].url }" />
							<select name="info.headers[0].dicid" style="width: 300px;height:30px" value="${obj.info.headers[0].dicid }" type="dic"></select>
							<!--<wd:select  name="info.headers[0].dicid" dicCode="10005" style="width: 300px;height:30px" initValue="请选择" defaultValue="${obj.info.headers[0].dicid }"/>
						--></td>
						<td><input type="text" name="info.headers[0].columnname" style="width: 200px;" value="${obj.info.headers[0].columnname }"></td>
						<td><select type="dic_zh_cn" name="info.headers[0].diczhcn" style="width:100px;height:30px" defalutvalue="${obj.info.headers[0].diczhcn }"></select></td>
						<td>
						<wddc:jsonselect name="info.headers[0].issum" jsonstr="${obj.isnotdic}" style="width:100px;height:30px" initValue="请选择" defaultValue="${obj.info.headers[0].issum }"/>
						</td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
				</c:if>
				<c:if test="${obj.info.headers!=null&&fn:length(obj.info.headers)>0 }">
					<c:forEach items="${obj.info.headers}" var="header" varStatus="row">
						<c:if test="${row.index == 0}">
							<tr id="headerbranch">
						</c:if>
						<c:if test="${row.index != 0}">
							<tr>
						</c:if>
							<td>
								<input id="reportinfoid" type="hidden" name="info.headers[${row.index}].reportinfoid" value="${obj.info.headers[row.index].reportinfoid }" />
								<input type="hidden" name="info.headers[${row.index}].id" value="${obj.info.headers[row.index].id }" />
								<input type="hidden" id="headerurl" name="info.headers[${row.index}].url" value="${obj.info.headers[row.index].url }" />
								<!--<wd:select  name="info.headers[${row.index}].dicid" dicCode="10005" style="width: 300px;height:30px" initValue="请选择" defaultValue="${obj.info.headers[row.index].dicid }"/>
								--><select name="info.headers[${row.index}].dicid" style="width: 300px;height:30px" value="${obj.info.headers[row.index].dicid }" type="dic"></select>
							</td>
							<td><input  type="text" name="info.headers[${row.index}].columnname" style="width: 200px;" value="${obj.info.headers[row.index].columnname }"></td>
							<td><select type="dic_zh_cn" name="info.headers[${row.index}].diczhcn" style="width:100px;height:30px" defalutvalue="${obj.info.headers[row.index].diczhcn }"></select></td>
							<td>
							<wddc:jsonselect name="info.headers[${row.index}].issum" jsonstr="${obj.isnotdic}" style="width:100px;height:30px" initValue="请选择" defaultValue="${obj.info.headers[row.index].issum }"/>
							</td>
							<td>
								<c:if test="${row.index == 0}">
									<a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a>
								</c:if>
								<c:if test="${row.index != 0}">
									<a href="#" onclick="deletetr(this);"  id="delBtn2">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	
	<h3 id="disable-responsive2" class="page-header">竖表头</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			<table id="rowTable" class="tables" width="100%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<th class="label_row" align="center" width="30%">数据行字典</th>
					<th class="label_row" align="center" width="30%">字段名</th>
					<th class="label_row" align="center" width="15%">中文key</th>
					<th class="label_row" align="center" width="15%">是否需要求和</th>
					<th class="label_row" align="center" width="10%"><input class="btn btn-warning" type="button" value="+" onclick="addrows()"/></th>
				</tr>
				<c:if test="${obj.info.rows==null||fn:length(obj.info.rows)==0 }">
					<tr id="rowbranch">
						<td>
							<input id="reportinfoid" type="hidden" name="info.rows[0].reportinfoid" value="${obj.info.id }" />
							<input type="hidden" name="info.rows[0].id" value="${obj.info.rows[0].id }" />
							<!--<wd:select name="info.rows[0].dicid" dicCode="10005" style="width: 300px;height:30px" initValue="请选择" defaultValue="${obj.info.rows[0].dicid }"/>
							-->
							<select name="info.rows[0].dicid" style="width: 300px;height:30px" value="${obj.info.rows[0].dicid }" type='dic' upright="upright"></select>
						</td>
						<td><input type="text" name="info.rows[0].columnname" style="width: 200px;" value="${obj.info.rows[0].columnname }"></td>
						<td><select type="dic_zh_cn" name="info.rows[0].diczhcn" style="width:100px;height:30px" defalutvalue="${obj.info.rows[0].diczhcn }"></select></td>
						<td>
						<wddc:jsonselect name="info.rows[0].issum"  jsonstr="${obj.isnotdic}" style="width:100px;height:30px" initValue="请选择" defaultValue="${obj.info.rows[0].issum }"/>
						</td>
						<td><a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a></td>
					</tr>
				</c:if>
				<c:if test="${obj.info.rows!=null&&fn:length(obj.info.rows)>0 }">
					<c:forEach items="${obj.info.rows}" var="rows" varStatus="row">
						<c:if test="${row.index == 0}">
							<tr id="rowbranch">
						</c:if>
						<c:if test="${row.index != 0}">
							<tr>
						</c:if>
							<td>
								<input id="reportinfoid" type="hidden" name="info.rows[${row.index}].reportinfoid" value="${obj.info.rows[row.index].reportinfoid }" />
								<input type="hidden" name="info.rows[${row.index}].id" value="${obj.info.rows[row.index].id }" />
								<!--<wd:select name="info.rows[${row.index}].dicid" dicCode="10005" style="width: 300px;height:30px" initValue="请选择" defaultValue="${obj.info.rows[row.index].dicid }"/>
								--><select name="info.rows[${row.index}].dicid" style="width: 300px;height:30px" value="${obj.info.rows[row.index].dicid }" type='dic' upright="upright"></select>
							</td>
							<td><input type="text" name="info.rows[${row.index}].columnname" style="width: 200px;" value="${obj.info.rows[row.index].columnname }"></td>
							<td><select type="dic_zh_cn" name="info.rows[${row.index}].diczhcn" style="width:100px;height:30px" defalutvalue="${obj.info.rows[row.index].diczhcn }"></select></td>
							<td>
							<wddc:jsonselect name="info.rows[${row.index}].issum"  jsonstr="${obj.isnotdic}" style="width:100px;height:30px" initValue="请选择" defaultValue="${obj.info.rows[row.index].issum }"/>
							<td>
								<c:if test="${row.index == 0}">
									<a href="#" onclick="deletetr(this);" style="display:none;" id="delBtn2">删除</a>
								</c:if>
								<c:if test="${row.index != 0}">
									<a href="#" onclick="deletetr(this);"  id="delBtn2">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	
</form>
</div>
<p align="center">
		<button type="button" class="btn btn-warning" onclick="save()" style="width: 100px">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
       	<button type="button" class="btn btn-warning" onclick="history.go(-1)" style="width: 100px">返回</button>
	</p>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
var headerindex=0;
var headerName;
var rowindex=0;
var rowName;
var dataindex=0;
var dataName;
$(document).ready(function(){
	headerindex = '${fn:length(obj.info.headers)}';
	if(headerindex == 0) headerindex=1;
	headerName = "info.headers[0]";
	rowindex = '${fn:length(obj.info.rows)}';
	if(rowindex == 0) rowindex=1;
	rowName = "info.rows[0]";
	dataindex = '${fn:length(obj.info.datas)}';
	if(dataindex == 0) dataindex=1;
	dataName = "info.datas[0]";
	
	//初始化数据项选择select
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
	//
	$.post("${ctx}/kernel/dic/getBusinessDic",function(data){
		$("select[type='dic']").each(function(){
			$(this).dicselect({
				json:data,
				defaultvalue:$(this).attr('value'),
				initvalue:'请选择'
			});
		});
		$("select[upright='upright']").each(function(){
			if($(this).attr('value')=='-1'){
				$(this).append("<option value='-1' selected>动态字典</option>");
			}else{ 
				$(this).append("<option value='-1'>动态字典</option>");
			}
		});
	});
	var isnotdic="{'0':'否','1':'是'}";
	$("select[type='dic_zh_cn']").each(function(){
		var defaultval=$(this).attr("defalutvalue");
		$(this).dicselect({
			dic:isnotdic,
			defaultvalue:defaultval
		});
	});
	$('#rowsum').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.info.rowsum }'
	});
	$('#colsum').dicselect({
		dic:isnotdic,
		defaultvalue:'${obj.info.colsum }'
	});

	$('.select2').css('width','500px').select2({allowClear:true});
    $('.select2').addClass('tag-input-style');
  	//获取所有数据资源tag信息
    $.ajax({
        type:"post",
        async:false,
        url:"${ctx}/kernel/tag/tagList?catalog=14",
        success:function(data){
       	 for(var i=0;i<data.tagList.length;i++){
                $("#tagList").append("<option value='"+data.tagList[i].tagId+"'>"+data.tagList[i].showName+"</option>");
            }
        }
    });
    var tag = new Array();
    <c:forEach items="${obj.info.tag}" var="tag">
    tag.push("${tag }");
    </c:forEach>
    $('.select2').val(tag).trigger("change");
});
function addheader(){
	var cloneTr = $("#headerbranch").clone();
	$(cloneTr).attr("id",$("#headerbranch").attr("id")+"_"+headerindex);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(headerName) != -1){
				$(this).attr("name",thisName.replace(headerName,"info.headers["+ headerindex + "]"));
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
				$(this).attr("name",thisName.replace(headerName,"info.headers["+ headerindex + "]"));
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
function addrows(){
	var cloneTr = $("#rowbranch").clone();
	$(cloneTr).attr("id",$("#rowbranch").attr("id")+"_"+rowindex);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(rowName) != -1){
				$(this).attr("name",thisName.replace(rowName,"info.rows["+ rowindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+rowindex));
		}
		if($(this).attr('id')!='reportinfoid'){
			$(this).val("");
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(rowName) != -1){
				$(this).attr("name",thisName.replace(rowName,"info.rows["+ rowindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+rowindex));
		}
		$(this).find("option:first").prop("selected",'selected');
		$(this).show();
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	rowindex++; 
	$(cloneTr).appendTo($("#rowTable"));
}
function adddata(){
	var cloneTr = $("#databranch").clone();
	$(cloneTr).attr("id",$("#databranch").attr("id")+"_"+dataindex);
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(dataName) != -1){
				$(this).attr("name",thisName.replace(dataName,"info.datas["+ dataindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+dataindex));
		}
		if($(this).attr('id')!='reportinfoid'){
			$(this).val("");
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(dataName) != -1){
				$(this).attr("name",thisName.replace(dataName,"info.datas["+ dataindex + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+dataindex));
		}
		$(this).find("option:first").prop("selected",'selected');
		$(this).show();
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("td[id='sqlfield']").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	dataindex++; 
	$(cloneTr).appendTo($("#dataTable"));
}
function deletetr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
	}
}
function save(){
	if($('#name').val()==''){
		alert('请填写报表名称!');
		return;
	}
	if($('#colsum').val()==''){
		alert('请选择是否需要列求和!');
		return;
	}
	if($('#rowsum').val()==''){
		alert('请选择是否需要行求和!');
		return;
	}
	var datas = $('select[type="data"]');
	for(var i=0 ; i<datas.length ; i++){
		if(datas[i].value==null||datas[i].value==""){
			alert("请选择数据!");
			return;
		}
	}
	$('#form1').submit();
}

function adddic(){
	window.open("${ctx}/kernel/dic/edit");
}
function addterm(){
	window.open("${ctx}/suite/data/mult/toEdit");
}
function changetrem(select){
	if($(select).val()!='')
	{
		$.post("${ctx}/suite/data/mult/getsqlfield",
			{id:$(select).val()},
			function(data){
				$(select).parent().parent().find('td[id="sqlfield"]').html(data);
		});
	}else{
		$(select).parent().parent().find('td[id="sqlfield"]').html('');
	}			
}

//数据刷新
function update(){
	 window.location.reload();//刷新当前页面.
}
</script>
</html>