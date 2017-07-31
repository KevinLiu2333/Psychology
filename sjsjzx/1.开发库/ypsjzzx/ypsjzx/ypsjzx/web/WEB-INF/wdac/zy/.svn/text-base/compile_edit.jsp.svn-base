<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head> 
<script type="text/javascript">

//信息校验
function checkInfo(type){
	if($('#resourceName').val()==''){
		alert("请输入资源目录名称！");
		return; 
	}
	if($('#infoSysName').val()==''){
		alert("请输入信息系统名称！");
		return;
	}
	if($('#sysAbstract').val()==''){
		alert("请输入信息系统简介！");
		return;
	}
	if($('#provideDepartment').val()==''){
		alert("请输入提供科室！");
		return;
	}
	if($('#keyWord').val()==''){
		alert("请输入检索关键字！");
		return;
	}
	if($('#busLinkman').val()==''){
		alert("请输入业务联系人姓名！");
		return;
	}
	if($('#busLinkmanPhone').val()==''){
		alert("请输入业务联系人电话！");
		return;
	}
	if($('#jointLinkman').val()==''){
		alert("请输入对接联系人姓名！");
		return;
	}
	if($('#jointLinkmanPhone').val()==''){
		alert("请输入对接联系人电话！");
		return;
	}
	if($('#jointType').val()==''){
		alert("请输入对接方式！");
		return;
	}
	/*
	if($('#shareProperty').val()==''){
		alert("请选择共享属性！");
		return;
	}
	*/
	if($('#r_openProperty').val()==''){
		alert("请选择公开属性！");
		return;
	}
	if($('#updateRate').val()==''){
		alert("请输入更新频度！");
		return;
	}

	//校验资源项信息
	$('#resourceList').find("input").each(function(){
		var thisId = $(this).attr("id");
		//alert(thisId.val());
		if(thisId.val() == ""){
			alert("资源项属性不能为空！");
			return;
		}
	});
	if(confirm("确定要保存吗？")){
			if(type == '1'){  
				$('#mainForm').attr('action','${ctx }/zy/saveBm?opType=1');
				$("#mainForm").submit();
			}
			if(type == '2'){  //提交审核
				$("#mainForm").submit();
			}
	}
}
//暂存
function tempStorage(){
	checkInfo('1');
}

//提交审核
function submitAudit(){
	checkInfo('2');
}
</script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;新增编制</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/zy/saveBm" method="post" >
   		 <input type="hidden" name="PZyInfo.zyInfoId" value="${obj.PZyInfo.zyInfoId }">
   		 <input type="hidden" id="tagLists1" name="tagLists1" value="${obj.tagLists }">
   		 
    <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>目录基础信息</b></h4>
    <div class="row">
    		<div class="col-md-6 form-group">
		    数据提供单位：<input type="text" id="zyUnit" style="width: 38%" name="PZyInfo.zyUnit" value="${obj.PZyInfo.zyUnit }" datatype="*1-200" nullmsg="请输入提供数据部门！" errormsg="请输入少于200个汉字！"/>
		    </div>
		    <div class="col-md-6 form-group">
		    数据来源：<select id="dataOrgin"  style="width: 38%" name="PZyInfo.dataOrgin"></select>
		    </div>
		    <div class="col-md-12 form-group">
		         资源目录名称：<input type="text" id="zyName" style="width: 67%" name="PZyInfo.zyName" value="${obj.PZyInfo.zyName}" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>
		    </div>
		    <div class="col-md-12 form-group">
		    更新说明：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="updateDesc" style="width: 67%" name="PZyInfo.updateDesc" value="${obj.PZyInfo.updateDesc }" datatype="*1-200" nullmsg="请输入提供数据部门！" errormsg="请输入少于200个汉字！"/>
		    </div>
		    <div class="col-md-12 form-group">
		        检索关键字：&nbsp;&nbsp;&nbsp;
        		<select multiple="" id="tagList" name="PZyInfo.tagLists" class="select2" data-placeholder="请选择标签...">
           			 <option value="">&nbsp;</option>
        		</select>
		    </div>
		    <div class="col-md-12 form-group">
		        资源目录摘要：<br/><textarea id="zyAbstract" style="width:77%;height:60px;" name="PZyInfo.zyAbstract" datatype="*1-200" nullmsg="请输入url！" errormsg="请输入少于200个汉字！"/>${obj.PZyInfo.zyAbstract}</textarea>
		    </div>
		    
	</div>
	<h4  class="page-header"><i class="ace-icon fa fa-expand green"></i>&nbsp;<b>与数据源关联</b></h4>
	<div class="row">
		    <div class="col-md-4 form-group" >
				数据源选择：<select class="form-control"  id ="dataSource" name="PZyInfo.dateSourceId" style="width:45%;display:inline;" onchange="showTable()"></select>
				
				<!--<input type="text"  id="zyTable" name="PZyInfo.zyTable" onchange="inputTable()" value="${obj.PZyInfo.zyTable}" datatype="*1-50" nullmsg="请输入返回参数！" errormsg="请输入少于50个汉字！"/>
		    --></div>
		    <div class="col-md-4 form-group" style="display:none" id="tableSel">
		    	关联数据库表：<select class="form-control" id ="viewName" name="PZyInfo.zyTable" style="width:45%;display:inline;" onchange="initItemList()"></select>
		    </div>
	</div>
<!-- 资源项 -->
	<h4  class="page-header"><i class="ace-icon fa fa-crosshairs green"></i>&nbsp;<b>资源项</b></h4>
		<div class="row">
		
		<table style="width: 85%" class="table table-hover" align="left" id="zyMlTable">
		<tr id="dec">
			<td class="label_1" width="15%" style="text-align: center;height: 60px;">资源项名称</td>
			<td class="label_1" width="12%" style="text-align: center;height: 60px;">数据库字段名</td>
			<td class="label_1" width="10%" style="text-align: center;height: 60px;">数据类型</td>
			<td class="label_1" width="10%" style="text-align: center;height: 60px;">公开属性</td>
			<td class="label_1" width="15%" style="text-align: center;height: 60px;">描述</td>
			<td class="label_1" width="8%" align="center"><input class=" btn btn-warning" type="button" value="新增" onclick="addRow()"/></td>
		</tr>
		
		<!-- 数据回显 -->
		<c:forEach items="${obj.zyItemList}" var="zyItem" varStatus="row">
		<tr>
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" class="" style="width:90%" id="dataItemName_0" name="zyItemList[${row.index }].cnName" value="${zyItem.cnName }"/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<input type="text" class="" style="width:90%" id="fieldCode_0" name="zyItemList[${row.index }].enName" value="${zyItem.enName }"/>
			</td>
			<td class="label_2" width="10%" style="text-align: center;">
				<select name="zyItemList[${row.index }].itemType" value="${zyItem.itemType }">
					<option value="${zyItem.itemType }">${zyItem.itemType }</option>
					<option value="">--请选择--</option>
					<option value="字符型">字符型</option> 
					<option value="日期型">日期型</option> 
					<option value="数字型">数字型</option>
				</select>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<select name="zyItemList[${row.index }].openType" value="${zyItem.openType }">
					<option value="${zyItem.openType }">${zyItem.openType }</option>
					<option value="">--请选择--</option>
					<option value="主动公开">主动公开</option> 
					<option value="已申请公开">已申请公开</option>
				</select>
			</td>
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" class="" style="width:90%" name="zyItemList[${row.index }].itemDesc" value="${zyItem.itemDesc}"/>
			</td>
			<td class="label_2" width="5%" style="text-align: center;"><input class=" btn btn-warning" type="button" value="删除" onclick="deleteRow(this)"/></td>
		</tr>
		</c:forEach>
	
		
</table>
<c:if test="${obj.PZyInfo.auditResult eq '审核不通过'}">
<div class="row">
	<div class="col-md-12 form-group">
	<tr>
		<td>经办结果</td>
		<td style="text-align: center;">
				<input readonly="readonly" name="PZyInfo.auditResult" value="${obj.PZyInfo.auditResult }">
		</td>
	</tr>
	</div>
	<div class="col-md-12 form-group">
	<tr>
			<td class="label_1">审核不通过原因</td>
			<td class="label_2" colspan="3">
				<textarea readonly="readonly" name="PZyInfo.auditReason" class="dftextarea" style="width:98%;height:60px;">${obj.PZyInfo.auditReason}</textarea>
			</td>
	</tr>
	</div>
</div>
</c:if>



</div>
</form>
<table style="width: 85%" class="table table-hover" align="left" id="maintable1">
	<tr id="branchTr" style="display:none;height: 50px;" colindex="1">
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" mark="1" class="" style="width:90%" id="cnName_0" name="zyItemList[0].cnName" value=""/>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<input type="text" mark="2" class="" style="width:90%" id="enName_0" name="zyItemList[0].enName" value=""/>
			</td>
			<td class="label_2" width="10%" style="text-align: center;">
				<select id="itemType_0" mark="3" name="zyItemList[0].itemType" value="">
					<option value="">--请选择--</option>
					<option value="字符型">字符型</option> 
					<option value="日期型" >日期型</option> 
					<option value="数字型">数字型</option>
				</select>
			</td>
			<td class="label_2" width="12%" style="text-align: center;">
				<select id="openType_0" mark="4" name="zyItemList[0].openType" value="">
				<option value="">--请选择--</option>
					<option value="主动公开" selected="selected">主动公开</option> 
					<option value="依申请公开">依申请公开</option>
				</select>
			</td>
			<td class="label_2" width="15%" style="text-align: center;">
				<input type="text" mark="5" id="itemDesc_0" class="" style="width:90%" name="zyItemList[0].itemDesc" value=""/>
			</td>
			<td class="label_2" width="5%" style="text-align: center;"><input class=" btn btn-warning" type="button" value="删除" onclick="deleteRow(this)"/></td>
		</tr>
</table>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="暂　存" onclick="tempStorage();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="提   交" onclick="submitAudit();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/zy/sourceList'" />
</div>
</div>

<script>
$('#dataOrgin').jsondic({dicid:'2001',initvalue:"请选择",defaultvalue:"${obj.PZyInfo.dataOrgin}"});
var index = "${fn:length(obj.zyItemList)}";

function addRow(obj) {
	index++;
	var listName = "zyItemList[0]";
	var cloneTr = $("#branchTr").clone();
	$(cloneTr).show();
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);//重新给克隆的对象的id属性赋值
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"zyItemList["+ index + "]"));
			}
		};
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		};
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"zyItemList["+ index + "]"));
			}
		};
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		};
	});
	$(cloneTr).appendTo($("#zyMlTable"));	
	
	}

function initAddRow(obj) {
	index++;
	var listName = "zyItemList[0]";
	var cloneTr = $("#branchTr").clone();
	$(cloneTr).show();
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);//重新给克隆的对象的id属性赋值
	$(cloneTr).find("input").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		var thisMark = $(this).attr("mark");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"zyItemList["+ index + "]"));
			}
		};
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		};
		if(thisMark == '1'){
			$(this).val(obj.colComment) ;
		}else if(thisMark == '2'){
			$(this).val(obj.colName) ;
		}
	});
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		var thisMark = $(this).attr("mark");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"zyItemList["+ index + "]"));
			}
		};
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
			//alert($(this).attr("id"));
		};

		if(thisMark == '3'){
			if("1" == obj.editType){
				$(this).find('option[value="字符型"]').each(function(){
					$(this).attr("selected","selected");
				});
			}else if("2" == obj.editType){
				$(this).find('option[value="数值型"]').each(function(){
					$(this).attr("selected","selected");
				});
			}else if("3" == obj.editType){
				$(this).find('option[value="日期型"]').each(function(){
					$(this).attr("selected","selected");
				});
			}
		}
		
	});
	$(cloneTr).appendTo($("#zyMlTable"));	
	
	}
	
		
    function deleteRow(obj) {
		if(confirm("确定删除吗？")){
			$(obj).parent().parent().remove();
		}
	}

    function inputTable(){
    	var tableName = $('#zyTable').val();
    	if(tableName == '') return;
    	$.ajax({
    			type:"post",
    			url:"${ctx}/zy/getTableName",
    			async:false,
    			data:{"tableName":tableName
    				},
    			success:function(data){
    	    			data =data.zyItemList;
    	    			$("#zyMlTable").find("tr[colindex=1]").each(function(){
        	    			$(this).remove();//删除这个标记是 为了保证克隆模板的唯一性
        	    			});
    	    			for(var i=0;i<data.length;i++){
        	    		var da =data[i];	
        	    		var cloneTr = $("#branchTr").clone();//克隆模板
        	    		$(cloneTr).show();//将克隆的模板显示处理
        	    		$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);//给克隆的模板id重新赋值
    					$(cloneTr).find("input").each(function(){//遍历克隆模板的每一个input标签
    						var thisName = $(this).attr("name");
    						if(thisName){
    							if(thisName.indexOf('zyItemList[0]') != -1){
        						$(this).attr('name',thisName.replace('zyItemList[0]','zyItemList['+index+']'));
    							}
    						}
        						});
    					$(cloneTr).find("select").each(function(){
    						var thisName = $(this).attr("name");
    						if(thisName){
    							if(thisName.indexOf('zyItemList[0]') != -1){
        						$(this).attr('name',thisName.replace('zyItemList[0]','zyItemList['+index+']'));
    							}
    						}
        						});
    					for (key in da ){
    						$(cloneTr).find("input[id="+key+"]").each(function(){
								$(this).val(da[key]);
        					});
    						$(cloneTr).find("select[id="+key+"]").each(function(){
								$(this).find("option[value="+da[key]+"]").each(function(){
										$(this).attr("selected","selected");
									});
        					});
    					}
    					index++;
    					$(cloneTr).appendTo($("#zyMlTable"));
    	    			}
    			}
    				
    	});
    }

    jQuery(function($){
        $('.select2').css('width','760px').select2({allowClear:true});
        $('.select2').addClass('tag-input-style');
        //tag信息
        $.ajax({
            type:"post",
            async:false,
            url:"${ctx}/kernel/tag/tagAllData?catalog=11",
            success:function(data){
                for(var i=0;i<data.result.length;i++){
                    $("#tagList").append("<option value='"+data.result[i].showName+"'>"+data.result[i].showName+"</option>");
                }
                var b = eval('(' + $("#tagLists1").val() + ')');
                $("#tagList").val(b).trigger("change");
            }
        });

    });
</script>

<script type="text/javascript">

//为数据源下拉框提供数据
$(document).ready(function(){
	$('#dataSource').dicajaxselect({
		url:"${ctx}/suite/data/db/getAllDbJson",
		initvalue:"--请选择--",
		defaultvalue:"${obj.PZyInfo.dateSourceId}",
		callback:function(){
			showTable();
		}
	});
});

//数据源发生变化时，显示 可供选择的数据库表下拉框
function showTable(){
	$("#zyMlTable").find("tr").each(function(){
		if($(this).attr("id") != "dec")
		$(this).remove();
	});
	var selectValue = $("#dataSource").val();
	if(selectValue != ""&& selectValue != "--请选择--"){
		var url = "${ctx}/suite/config/table/getCfgTabNameListJson?dataSourceId="+selectValue;
		//var url = "http://localhost:8080/wddc/suite/config/table/getCfgTabNameListJson?dataSourceId="+selectValue;
		$("#tableSel").show();
		$('#viewName').dicajaxselect({
			url:url,
			initvalue:"--请选择--",
			defaultvalue:"${obj.PZyInfo.zyTable}"
			
		});
	}else{
		$("#tableSel").hide();
	}
	
}

//读表配置自动化设置初始资源项
function initItemList(){
	$("#zyMlTable").find("tr").each(function(){
		if($(this).attr("id") != "dec")
		$(this).remove();
	});
	var selectValue = $("#dataSource").val();
	var viewName = $("#viewName").val();
	//alert(viewName);
	var url = "${ctx}/suite/config/table/getCfgColsJson?dataSourceId="+selectValue+"&"+"tableName="+viewName;
	if(selectValue != ""&& viewName !=""){
		$.get(url,function(data){
	    	var dic = data;
	        for(key in dic){
		        initAddRow(dic[key]);
	        }
	    });
		
	}else{
		$("#tableName").hide();
		
	}
}


</script>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>