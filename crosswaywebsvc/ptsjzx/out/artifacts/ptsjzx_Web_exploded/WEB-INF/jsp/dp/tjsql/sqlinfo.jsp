<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普陀政务数据中心</title>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/json.css">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script> 
</head>
<script type="text/javascript">
     //查询数据
	function query(type){
		if(type == '1'){
			$('#pageNo').val('1');
		}
		$('#queryForm').submit();
	}
    //新增
	function addSqlInfo(){
			var href = "${ctx }/sqlinfo/tosqlinfo_edit";
			window.location.href=href;
	}
	function editSqlInfo(sqlid){
			var href = "${ctx }/sqlinfo/tosqlinfo_edit?sqlid="+sqlid;
			window.location.href=href;
	}

	function delSqlInfo(sqlid){
		if(confirm("确定删除吗？")){
		$.post("${ctx }/sqlinfo/delsqlinfo?sqlid="+sqlid, 
	            { Action: "post"},
	            function (data, textStatus){
	            	query();
	             }
	            , "json");
		}
	}

	function createjson(){
		var href = "${ctx }/sqlinfo/saveJson";
			window.location.href=href;
	}
</script>
  
  <body>
  <form id="queryForm" name="queryForm" action="${ctx}/sqlinfo/tosqlinfo" method="post">
   <table width="96%" border="0" height="90" align="center" cellpadding="0" cellspacing="0" class="query_search" style="margin-top: 20px;">
   <tr height="30" >
   	<td align="center" width="28%" style="line-height:25px;">&nbsp;&nbsp;SQL统计类型：<input class="dfinput" id="name" name="filter_str_diccode_like" type="text" value="${param.filter_str_diccode_like }"  style="width:120px;"/></td>
	<td align="center" width="28%">&nbsp;&nbsp;SQL描述：<input class="dfinput"  id="title" name="filter_str_title_like" type="text" value="${param.filter_str_title_like }"  style="width:120px;"/></td>
	<td align="center" width="44%">
		申请日期：
		<wd:datepicker defaultValue="${obj.applyDateStart}" name="applyDateStart"  id="applyDateStart" dateFormat="yyyy-MM-dd"  className="dfinput" style="width:100px;"/>
   		~
   		<wd:datepicker  defaultValue="${obj.applyDateEnd}" name="applyDateEnd"  id="applyDateEnd" dateFormat="yyyy-MM-dd"  className="dfinput"  style="width:100px;"/>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   	</td>
	<td align="left">
		
	</td>
   </tr>
   <tr height="30" >
   	<td align="center" colspan="3">
   		<input type="button" class="minButton" value="查  询" onclick="query(1)" />
		<input type="button" id="button" class="minButton" value="增 加" onclick="addSqlInfo()"/>
		<input type="button" id="button" class="minButton" value="query" onclick="query()"/>
		<input type="button" id="button" class="minButton" value="生成json" onclick="createjson()"/>
	</td>
   </tr>
   
   </table>
   <table width="96%" class="table_list" align="center">
   		<tr>
   			<th width="12%">序号</th>
   			<th width="22%">描述</th>
			<th width="22%">SQLCODE</th>
			<th width="22%">修改时间</th>
			<th width="22%">操作</th>
		</tr>
		<c:forEach items="${obj.rows}" var="sqlinfo" varStatus="row" >
				<tr>
					<td align="center">${(obj.pager.pageNumber-1)*obj.pager.pageSize + row.index+1 }</td>	        		
	        		<td align="center">${sqlinfo.title}</td>
	        		<td align="center">${sqlinfo.sqlcode }</td>
	        		<td align="center"><fmt:formatDate pattern="yyyy-MM-dd" value="${sqlinfo.updatedate}"/></td>
	        		<td align="center">
	        			<a href="#" onclick="editSqlInfo('${sqlinfo.sqlid}')">修改</a>
	        			<a href="#" onclick="delSqlInfo('${sqlinfo.sqlid}')">删除</a>
	        		</td>
	        	</tr>
			</c:forEach>
   </table>
   <table width="96%" class="tables">
			<tr>
				<td>
					<!-- （3）分页 -->
					<jsp:include page="/common/pager.jsp"></jsp:include>
				</td>
			</tr>
		</table>
  </form>
  </body>
</html>
