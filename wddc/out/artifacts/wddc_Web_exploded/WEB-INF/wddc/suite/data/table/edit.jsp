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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
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
<body>
<jsp:include page="/cj/header.jsp"/>

    <script type="text/javascript">   
      window.onload = function(){   
       var inputs = document.getElementsByTagName("input");   
        for(var e in inputs){   
            if(inputs[e].type == "radio"){   
                inputs[e].onclick = function(){   
                    for(var e in inputs){   
                          if(inputs[e].type == "radio" && this.group === inputs[e].group)   
                          inputs[e].checked = false;   
                    }   
                    this.checked = true;   
                };   
      
            }   
        }   
      }   
    </script>  


<script>
var index;
var listName;
//验证表名是否存在
function checkTable() {
	//js中通过id获取标签值
	var viewName = $("#viewName").val();
	if($("#viewName").val() != ""){
		$.post("${ctx }/suite/config/table/checkTable",  { Action: "post","viewName":viewName },
			function (data, textStatus)
			{
	        	 //初始值
	        	if(data.result == "1")
	        	{
	            	$("#showMsg").html("表名已存在，请重新输入");
	            	$("#showMsg").css("color","red");
	            	$("#viewName").val("");
	        	}
	        	else
	        	{
	            	$("#showMsg").html("表名可以使用");
	            	$("#showMsg").css("color","green");
	        	}	            
	         }
	        , "json");
	}
}
//增加一行字段编辑表格
function addTr(){
	var motherTable = $("#motherTable");//原始表格
	var cloneTr = $("#branchTr").clone();//克隆该jQuery对象
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);//给当前对象的id属性赋值

	var tagType = ["text","checkbox","radio"];
	//遍历
	for(var type in tagType ){
		$(cloneTr).find(":"+tagType[type]).each(function(){//对当前对象的：text进行遍历
		var thisName = $(this).attr("name");//去该对象name属性的值赋值给变量thisName
		var thisId = $(this).attr("id");//取该对象id属性的值赋值给变量thisId
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"columnList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
		$(this).val("");
	});
	}
	
	$(cloneTr).find("select").each(function(){
		var thisName = $(this).attr("name");
		var thisId = $(this).attr("id");
		if(thisName){
			if(thisName.indexOf(listName) != -1){
				$(this).attr("name",thisName.replace(listName,"columnList["+ index + "]"));
			}
		}
		if(thisId){
			$(this).attr("id",thisId.replace("_0","_"+index));
		}
		$(this).find("option:first").prop("selected",'selected');
		$(this).show();
	});
	$(cloneTr).find("span").each(function(){
		$(this).html("");
	});
	$(cloneTr).find("a[id='delBtn2']").eq(0).show();
	$(cloneTr).find("input[name$='.orderNum']").eq(0).val($("#motherTable tr").length);
	$(cloneTr).appendTo($("#motherTable"));
	$(cloneTr).find("input[name$='.isPk']").attr("checked",false);
	$(cloneTr).find("select[name$='.editType']:last").change();
	index++;
}
function deleteTr(obj){
	if(confirm("确定删除吗？")){
		$(obj).parent().parent().remove();
		 var i=1;
         $("input[name$='.orderNum']").each(function(){
             this.value = i;
             i++;                     
         });
	}
}
function changeInput(obj){
	if ($(obj).val() == '2'){
		$(obj).parent().next().next().find("select[name$='.dicId']").show();
		$(obj).parent().next().next().find("input[name$='.isMultiple']").show();
	} else {
		$(obj).parent().next().next().find("select[name$='.dicId']").hide();
		$(obj).parent().next().next().find("select[name$='.dicId']").val("");
		$(obj).parent().next().next().find("input[name$='.isMultiple']").hide();
	}
	if ($(obj).val() == '3' || $(obj).val() == '5'){
		$(obj).parent().next().find("input[name$='.colLength']").hide();
		$(obj).parent().next().find("input[name$='.colLength']").removeClass("required");
	} else {
		$(obj).parent().next().find("input[name$='.colLength']").show();
		$(obj).parent().next().find("input[name$='.colLength']").addClass("required");
	}
}

function toUpper(obj){
	$(obj).val($(obj).val().toUpperCase());
}
$(document).ready(function(){
	index = '${fn:length(obj.columnList)}';
	if(index == 0) index=1;
	listName = "columnList[0]";
	$("[name$='editType']").each(function (){
		$(this).change();
	});
});

//改变选中状态
function changeCheck1(obj){
	//还需要改。。。
		//$("input[name$='.isPk']").attr("checked",false);
		//$("input[name$='.isPk']").removeAttr("checked");
		//$(obj).attr("checked",true);
		//alert($(obj).attr("checked"));
		
	
}
</script>
<div class="container">
	
	
	<h2 id="disable-responsive2" class="page-header" > 新增元数据    
	</h2>
	
	<form id="longForm" method="post" action="${ctx }/suite/config/table/toSaveTable" class="pageForm required-validate form" >
		<input type="hidden" name="tableConfig.themeId" value="${obj.tableConfig.themeId}"/>
		<input type="hidden" name="accountBookId" value="${obj.accountBookId }"/>
		<input type="hidden" name="tableConfig.ifAutoCreate" value="1"/>
		<!-- 项目基本信息 -->
		<div class="row" >
			<div class="col-md-5 form-group">
		     	 数据源  ：
		     	<select id="databaseid" name="databaseid" class="form-control" style="display:inline;width:280px;height:34px;"></select>
			</div>
			
			<div class="col-md-4 form-group">
				表名称 ：
				<input class="form-control" type="text" id="viewName" name="tableConfig.viewName" value="${obj.tableConfig.viewName }" style="display:inline;width:280px" onblur="checkTable()">
			</div>
			<div class="col-md-3 form-group">
				<span id="showMsg" style="width:191px">
  				</span>
			</div>
			
			
		</div>
		<div class="row form-group">
			<div class="col-md-5 form-group">
				表类型 ：
				<select class="form-control" style="display:inline;width:280px;height:34px;" name="tableConfig.catalog">				
					<option value = "1">台账表</option>
					<option value = "2">基础数据表</option>
					<option value = "3">日常工作表</option>
					<option value = "4">系统表</option>
				</select>
				<!--<wd:select name="tableConfig.catalog" dicCode="1038" defaultValue="${obj.tableConfig.catalog}"/>-->
			</div>
			<div class="col-md-4 form-group ">
				表描述 ：
				<input class="form-control" type="text"  name="tableConfig.name" value="${obj.tableConfig.name }" style="display:inline;width:280px" />
				<!--<textarea class="form-control"  name="tableConfig.name" placeholder="请输入表描述信息。。。" style="display:inline;width:284px">${obj.tableConfig.name }</textarea>
			--></div>
		</div>
			
		
		<h3 id="disable-responsive2" class="page-header">编辑</h3>
		<div class="row">
			<div class="col-md-6 form-group">
				<table id="motherTable" class="tables" width="200%" cellpadding="0" cellspacing="1" align="center" >
				<tr >
					<th class="label_row" width="16%">字段英文名</th>
					<th class="label_row" width="16%">字段描述</th>
					<th class="label_row" width="10%">编辑类型</th>
					<th class="label_row" width="10%">字段长度</th>
					<th class="label_row" width="13%">字典/是否多选</th>
					<th class="label_row" width="5%">主键</th>
					<th class="label_row" width="10%">有效性</th>
					<th class="label_row" width="10%" >排序</th>
					<th class="label_row" >
						<button  type="button"  onclick="addTr()" class="btn btn-warning">+</button>
					</th>
				</tr>
				<c:if test="${obj.tableConfig.themeId == null}">
					<tr id="branchTr">
						<td>
							<input  type="hidden" name="columnList[0].colId" value="${obj.columnList[0].colId }"/>
							<input class="form-control required" type="text" style="width:80%;" name="columnList[0].nameLetter" value="${obj.columnList[0].nameLetter }" />
						</td>
						<td>
							<input class="form-control required" style="width:80%;" type="text" name="columnList[0].name" value="${obj.columnList[0].name }"  />
						</td>										  
						<td>	
							<select class="form-control" style="width:80%;" name="columnList[0].editType" onchange="changeInput(this);">	
								<option value="1">字符</option>
								<option value="2">字典</option>
								<option value="3">时间</option>
								<option value="4">多文本</option>
								<option value="5">数值</option>
							</select>					
						</td>
						<td>
							<input class="form-control" type="text"  style="width:80%;" name="columnList[0].colLength" value="${obj.columnList[0].colLength }" size="5" class="required"/>
						</td>
						
						<td>
							<div class="row">
								<div class="col-md-8"> 
									<select class="form-control"  name="columnList[0].dicId" >
										<option value="1">hello</option>
										<option value="2">world</option>
										<option value="3">monkey</option>
										<option value="4">blue</option>
									</select>
								</div>	
								<div class="col-md-3"> 
										<input class="form-control"  type="checkbox" name="columnList[0].isMultiple" value="1" title="字典项是否多选"/>
								</div>	
							</div>
							
						</td>	
						
					  	<td>
							 <input class="form-control " type="radio" name="columnList[0].isPk" value="1" checked="checked"  onclick="changeCheck(this)"/> 
						</td>
						
						<td>
							 <label> <input class="form-control" type="checkbox" style="" name="columnList[0].validity" value="1" checked="checked"/> </label>
						</td>
						
						<td>
							<input class="form-control" type="text"  style="width:40%" name="columnList[0].orderNum" value="1" size="5" class="required"/>
						</td>
						<td>
							<a class="form-control" href="#" onclick="deleteTr(this);" style="display:none;" id="delBtn2">删除</a>
						</td>
					</tr>
				</c:if>
				
				<!-- 修改编辑表单 -->
				<c:if test="${obj.tableConfig.themeId != null && obj.tableConfig.themeId != ''}">
				<c:forEach items="${obj.columnList}" var="queryColumn" varStatus="row">
					<c:if test="${row.index == 0}">
						<tr id="branchTr">
					</c:if>
					<c:if test="${row.index != 0}">
						<tr>
					</c:if>
					<td>
						<input  type="hidden" name="columnList[${row.index }].colId" value="${obj.columnList[row.index].colId }"/>
						<input class="form-control" style="width:80%" type="text"  name="columnList[${row.index}].nameLetter" value="${obj.columnList[row.index].nameLetter }" style="height:25px" size="15" class="required"/>
					</td>
					
					<td>
						<input class="form-control" type="text"  style="width:80%" name="columnList[${row.index}].name" value="${obj.columnList[row.index].name }" size="15" class="required"/></td>
					<td>
						<select class="form-control" style="width:85%" name="columnList[${row.index }].editType" onchange="changeInput(this);" >							
							<option value="1">字符</option>
							<option value="2">字典</option>
							<option value="3">时间</option>
							<option value="4">多文本</option>
							<option value="5">数值</option>
						</select>					
					</td>
				
				<!--  	
					<td><wd:select name="columnList[${row.index}].editType" onchange="changeInput(this);" dicCode="1022" defaultValue="${obj.columnList[row.index].editType}"/></td>
				-->
					
					
					<td>
						<input class="form-control" type="text"  style="width:80%" name="columnList[${row.index}].colLength" value="${obj.columnList[row.index].colLength }" size="5" class="required"/>
					</td>
					
					<td>
						<!--  
						<select name="columnList[${row.index }].dicId" style="display:none;">
							<c:forEach items="${obj.dicMap}" var="dic" varStatus="row2">
								<c:if test="${dic.value == obj.columnList[row.index].dicId}">
									<option value="${dic.value}" selected="selected">${dic.key}</option>
								</c:if>
								<c:if test="${dic.value != obj.columnList[row.index].dicId}">
									<option value="${dic.value}">${dic.key}</option>
								</c:if>
							</c:forEach>
						</select>
						<input style="display:none;" type="checkbox" value="1" name="columnList[${row.index}].isMultiple"  title="字典项是否多选" <c:if test="${obj.columnList[row.index].isMultiple == '1'}">checked="checked"</c:if>/>
						-->
						
						<select class="form-control" style="width:80%" name="columnList[${row.index }].dicId" style="width:100px;display:none;">
							<option value="1">hello</option>
							<option value="2">world</option>
							<option value="3">monkey</option>
							<option value="4">blue</option>
						</select>
						<input class="form-control" style="width:80%;display:none;" type="checkbox" name="columnList[0].isMultiple" value="1" title="字典项是否多选"/>	
					</td>
					
					<td>	
						 <input class="form-control" style="width:80%;" type="radio" value="1" name="columnList[${row.index }].isPk" <c:if test="${obj.columnList[row.index].isPk == '1'}">checked="checked"</c:if> onclick="changeCheck(this)"/>
					</td>
					
					<td>
						 <input class="form-control"style="width:80%;" type="checkbox" style="height:26px" value="1" name="columnList[${row.index }].validity" <c:if test="${obj.columnList[row.index].validity == '1'}">checked="checked"</c:if>/>
					</td>
					
					<td>
						<input class="form-control" type="text" style="width:80%" name="columnList[${row.index}].orderNum" value="${obj.columnList[row.index].orderNum }" size="5" class="required"/>
					</td>
					
					<td>
						<a class="form-control" href="#" onclick="deleteTr(this);" style="display:none;" id="delBtn2">删除</a>						
					</td>			
				</c:forEach>
				</c:if>
				</table>
			</div>
		</div>
		<br/>
	<div class="formBar">
		<p align="center">
			<button type="submit" class="btn btn-warning"  >保存</button>				
			<a onclick="window.history.back(-1)" class="btn btn-warning">返回</a>
		</p>		  			
	</div>	
</form>
</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$('#databaseid').dicajaxselect({
	url:"${ctx}/suite/data/db/getAllDbJson",
	initvalue:"请选择",
	defaultvalue:"${obj.info.databaseid }"
});
</script>
</html>


