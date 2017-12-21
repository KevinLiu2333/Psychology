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
<div class="pageContent"  >
	<form method="post" action="${ctx }/config/table/toSaveTable" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="tableConfig.themeId" value="${obj.tableConfig.themeId}"/>
		<input type="hidden" name="accountBookId" value="${obj.accountBookId }"/>
		<input type="hidden" name="tableConfig.ifAutoCreate" value="1"/>
		<!-- 项目基本信息 -->
		<div class="pageFormContent nowrap" layoutH="57">
			<table class="tables" width="90%" cellpadding="0" cellspacing="1" align="center"> 
				<tr>
					<th class="label_row" width="30%">表名</th>
					<th class="label_row" width="30%">表描述</th>
					<th class="label_row" width="12%">表类型</th>
				</tr>
				<tr>
					<td align="center"><input type="text" name="tableConfig.viewName" value="${obj.tableConfig.viewName }" size="60"/></td>
					<td align="center"><input type="text" name="tableConfig.name" value="${obj.tableConfig.name }" size="60"/></td>
					<td align="center"><wd:select name="tableConfig.catalog" dicCode="1038" defaultValue="${obj.tableConfig.catalog}"/></td>
				</tr>
			</table>
			<br/><br/>
			<table id="motherTable" class="tables" width="90%" cellpadding="0" cellspacing="1" align="center" >
				<tr >
					<th class="label_row">字段英文名</th>
					<th class="label_row">字段描述</th>
					<th class="label_row" width="8%">编辑类型</th>
					<th class="label_row" width="10%">字段长度</th>
					<th class="label_row" width="25%">字典/是否多选</th>
					<th class="label_row" width="7%">主键</th>
					<th class="label_row" width="7%">有效性</th>
					<th class="label_row" width="3%">排序</th>
					<th class="label_row" width="7%"><input type="button" value="+" onclick="addTr()"/></th>
				</tr>
				<c:if test="${obj.tableConfig.themeId == null}">
				<tr id="branchTr">
					<td><input type="hidden" name="columnList[0].colId" value="${obj.columnList[0].colId }"/><input type="text" name="columnList[0].nameLetter" value="${obj.columnList[0].nameLetter }" size="15" class="required"/></td>
					<td><input type="text" name="columnList[0].name" value="${obj.columnList[0].name }"  size="15" class="required"/></td>
					<td><wd:select name="columnList[0].editType" onchange="changeInput(this);" dicCode="1023" defaultValue="${obj.columnList[0].editType}"/></td>
					<td><input type="text" name="columnList[0].colLength" value="${obj.columnList[0].colLength }" size="5" class="required"/></td>
					<td>
						<select name="columnList[0].dicId" style="display:none;">
							<c:forEach items="${obj.dicMap}" var="dic" varStatus="row">
								<option value="${dic.value}">${dic.key}</option>
							</c:forEach>
						</select>
						<input style="display:none;" type="checkbox" name="columnList[0].isMultiple" value="1" title="字典项是否多选"/>
					</td>
				  	    <td>
							<input type="radio" name="columnList[0].isPk" value="1" checked="checked"  onclick="changeCheck(this)"/>
						</td>
					<td>
						 <input type="checkbox" name="columnList[0].validity" value="1" checked="checked"/>
					</td>
					<td><input type="text" name="columnList[0].orderNum" value="1" size="5" class="required"/></td>
					<td><a href="#" onclick="deleteTr(this);" style="display:none;" id="delBtn2">删除</a></td>
				</tr>
				</c:if>
				<c:if test="${obj.tableConfig.themeId != null && obj.tableConfig.themeId != ''}">
				<c:forEach items="${obj.columnList}" var="queryColumn" varStatus="row">
					<c:if test="${row.index == 0}">
						<tr id="branchTr">
					</c:if>
					<c:if test="${row.index != 0}">
						<tr>
					</c:if>
					<td><input type="hidden" name="columnList[${row.index }].colId" value="${obj.columnList[row.index].colId }"/><input type="text" name="columnList[${row.index}].nameLetter" value="${obj.columnList[row.index].nameLetter }"  size="15" class="required"/></td>
					<td><input type="text" name="columnList[${row.index}].name" value="${obj.columnList[row.index].name }" size="15" class="required"/></td>
					<td><wd:select name="columnList[${row.index}].editType" onchange="changeInput(this);" dicCode="1023" defaultValue="${obj.columnList[row.index].editType}"/></td>
					<td><input type="text" name="columnList[${row.index}].colLength" value="${obj.columnList[row.index].colLength }" size="5" class="required"/></td>
					<td>
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
					</td>
					<td>	
						 <input type="radio" value="1" name="columnList[${row.index }].isPk" <c:if test="${obj.columnList[row.index].isPk == '1'}">checked="checked"</c:if> onclick="changeCheck(this)"/>
					</td>
					<td>
						 <input type="checkbox" value="1" name="columnList[${row.index }].validity" <c:if test="${obj.columnList[row.index].validity == '1'}">checked="checked"</c:if>/>
					</td>
					<td>
						<input type="text" name="columnList[${row.index}].orderNum" value="${obj.columnList[row.index].orderNum }" size="5" class="required"/>
					</td>
					<td>
						<a href="#" onclick="deleteTr(this);" style="display:none;" id="delBtn2">删除</a>
					</td>			
				</c:forEach>
				</c:if>
			</table>
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
