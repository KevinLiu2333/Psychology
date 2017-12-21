<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新增模板</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
</head>
<body>
<form id="mainform" action="${ctx}/sjtb/config/save" method="post">
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
		<ul>
			<c:forEach items="${obj.sheet }" var="sheet" varStatus="row">
				<c:if test="${ row.index==0 }">
						<li><a href="#tab${row.index+1}" class="selected">${ sheet}</a></li>
				</c:if>
				<c:if test="${ row.index!=0 }">
						<li><a href="#tab${row.index+1}" >${ sheet}</a></li>
				</c:if>
			</c:forEach>
		</ul>
		</div>
		<c:forEach items="${obj.column }" var="column" varStatus="row">
			<div id="tab${row.index+1 }" class="tabson">
				<table width="80%" class="table_multilist" align="center">
						<th>
							<td>字段名</td>						
							<td>数据库字段名</td>						
							<td>数据类型</td>						
							<td>是否唯一标示</td>						
							<td>是否预览</td>						
							<td>校验类型</td>						
							<td>数据标识</td>						
							<td>信用平台标识</td>						
						</th>
						<c:forEach items="${column }" var="cols" varStatus="col">
							<c:if test="${index!=null }">
 								<c:set var="index" value="${index+1 }"></c:set>
 							</c:if>
							<c:if test="${index==null }">
 								<c:set var="index" value="0"></c:set>
 							</c:if>
 							
						<tr>
						<td>
							<input type="hidden" name="column[${ index }].id" value="${cols.id }" />
							<input type="hidden" name="column[${ index }].tableBm" value="${cols.tableBm }" />
							<input type="hidden" name="column[${ index }].tableName" value="${cols.tableName }" />
							<input type="hidden" name="column[${ index }].historyTableName" value="${cols.historyTableName }" />
							<input type="hidden" name="column[${ index }].orderNo" value="${cols.orderNo }" />
							<input type="hidden" name="column[${ index }].sheetId" value="${cols.sheetId }" />
							<input type="hidden" name="column[${ index }].sheetName" value="${cols.sheetName }" />
						</td>
							<td><input  name="column[${ index }].columnComment" value="${cols.columnComment }" /></td>
							<td><input  name="column[${ index }].columnName" value="s${row.index+1}_data${col.index+1}" /></td>
							<td>
								<select name="column[${ index }].columnType">
									<option value="VARCHAR">字符串</option>
									<option value="DATE">日期</option>
									<option value="NUMBER">数字</option>
								</select>
							</td>
							<td>
								<select name="column[${ index }].isWybs">
								<option value="">否</option>
									<option value="1">是</option>
									
								</select>
							</td>
							<td>
								<select name="column[${ index }].isYulan">
								<option value="">否</option>
									<option value="1">是</option>
									
								</select>
							</td>
							<td>
								<select name="column[${ index }].checkType">
									<option value="">无</option>
									<option value="1">身份证</option>
								</select>
							</td>
							<td>
								<select name="column[${ index }].extractflag">
									<option value="">无</option>
									<option value="1">身份证号</option>
									<option value="1">统一社会信用代码</option>
									<option value="2">组织机构代码</option>
									<option value="3">营业执照注册号</option>
									<option value="4">纳税人识别号</option>
									<option value="5">法人名称</option>
								</select>
							</td>
							<td>
								<select name="column[${ index }].creditFlag">
									<option value="">否</option>
									<option value="1">是</option>
								</select>
							</td>
							
						</tr>
						</c:forEach>
				</table>
			</div>
		</c:forEach>
	</div>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		&nbsp;<input type="button" id="button"  class="button" value="保存修改" onclick="save();" /> 
	</div>
</form>
</body>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      function save(){
    	  $('#mainform').submit();
      }
</script>
</html>