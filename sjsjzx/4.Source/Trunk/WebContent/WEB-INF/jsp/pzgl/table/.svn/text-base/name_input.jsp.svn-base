<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script>
var index;
var listName;
function addTr(){
	var motherTable = $("#motherTable");
	var cloneTr = $("#branchTr").clone();
	$(cloneTr).attr("id",$("#branchTr").attr("id")+"_"+index);
	$(cloneTr).find(":text").each(function(){
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
		$(this).val("");
	});
	$(cloneTr).find(":checkbox").each(function(){
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
	});
	$(cloneTr).find(":radio").each(function(){
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
	});
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
function changeCheck(obj){
	$("input[name$='.isPk']").removeAttr("checked");
	$(obj).attr("checked",true);
}
</script>
<div class="pageContent">
	<form method="post" action="${ctx }/config/table/createDataByTableName" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="tableConfig.themeId" value="${obj.tableConfig.themeId}"/>
		<input type="hidden" name="accountBookId" value="${obj.accountBookId }"/>
		<input type="hidden" name="tableConfig.ifAutoCreate" value="1"/>
		<!-- 项目基本信息 -->
		<div class="pageFormContent nowrap" layoutH="57">
			<table class="tables" width="90%" cellpadding="0" cellspacing="1" align="center"> 
				<tr>
					<th class="label_row">表名</th>
					<th class="label_row">表描述</th>
				</tr>
				<tr>
					<td align="center"><input type="text" name="viewName" value="" size="50%"/></td>
					<td align="center"><input type="text" name="tableName" value="" size="50%"/></td>
				</tr>
			</table>
			<br/><br/>
		</div>
		<!-- 操作工具栏  -->
		<div class="formBar">
			<ul>
				<li><div class="buttonActive" id="saveBtn"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
