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
<form id="mainform" action="${ctx}/zypz/toSave" method="post">
	<div id="usual1" class="usual" style="margin-top:20px;">
		<div class="itab">
		<ul>数据项配置
		</ul><input type="hidden" name="resourceid" value="${obj.resourceid }">
		
		</div>
			<div id="tab1" class="tabson">
				<table width="80%" class="table_multilist" align="center">
				
							<th>字段名</th>						
							<th>数据库字段名</th>						
							<th>字段顺序</th>						
							<th>数据类型</th>						
										
						
						<c:forEach items="${obj.list }" var="cols" varStatus="col">
						<c:if test="${index!=null }">
 								<c:set var="index" value="${index+1 }"></c:set>
 							</c:if>
							<c:if test="${index==null }">
 								<c:set var="index" value="0"></c:set>
 							</c:if>
 							
						<tr>
							<td><input type="text" name="column[${ index }].dataitemname" value="${cols.dataitemname }" /></td>
							<td><input type="text" name="column[${ index }].fieldcode" value="${cols.fieldcode }" /></td>
							<td><input type="text" name="column[${ index }].ordernum" value="${cols.ordernum }" /></td>
						
							<td>
								<select name="column[${ index }].dataitemtype">
									<option value="1">字符串</option>
									<option value="2">日期</option>
									<option value="3">数字</option>
								</select>
							</td>
							
						</tr>
						</c:forEach>
				</table>
			</div>
	</div>
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
	<input type="button" id="button"  class="button" value="返回" onclick="back();" /> 
		&nbsp;<input type="button" id="button"  class="button" value="保存修改" onclick="save();" /> 
	</div>
</form>
</body>
<script type="text/javascript"> 
      function save(){
    	  $('#mainform').submit();
      }
      function back(){
  		window.history.back(-1); 
  	}
</script>
</html>