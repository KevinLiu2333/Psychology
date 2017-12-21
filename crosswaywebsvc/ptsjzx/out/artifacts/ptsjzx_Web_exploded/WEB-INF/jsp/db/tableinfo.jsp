<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link rel="stylesheet" href="${ctx}/tiles/bootstrap2.3.2/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap2.3.2/js/bootstrap.min.js"></script>
<title>表详情</title>
</head>
<body>
<body>
		<div style="width:1000px;margin:0 auto;">
		<div id="usual1" class="usual" style="margin-top:20px;"> 
 		<div class="itab">
	  		<ul style="margin-left:0px;">
	    		<li>
	    			<a href="#tab1" class="selected">表信息</a>
	    		</li> 
	    		<li><a href="#tab2">列信息</a></li> 
	    	</ul>
	    </div> 
			
		<div id="tab1" class="">
			<table width="1000" class="table table-striped table-bordered table-hover table-condensed" align="center" >
				<tr>
					<td style="width:50%" align="center"><b>表名</b></td>
					<td style="width:50%" align="center">${obj.table.name }</td>
				</tr>
				<tr>
					<td align="center"><b>中文名</b></td>
					<td align="center">${obj.table.chinesename }</td>
				</tr>
				<tr>
					<td align="center"><b>数据量</b></td>
					<td align="center">${obj.table.counts }</td>
				</tr>
			
				<tr>
					<td align="center">&nbsp;<b>来源</b></td>
					<td align="center">${obj.from }</td>
				</tr>
			
			</table>
			
			
			
		</div>
			<div id="tab2" class="">
				<table width="1000" class="table table-striped table-bordered table-hover table-condensed" align="center" >
					<tr>
						<td style="width:25%" align="center"><b>字段名</b></td>
						<td style="width:25%" align="center"><b>中文名</b></td>
						<td style="width:25%" align="center"><b>类型</b></td>
						<td style="width:25%" align="center"><b>长度</b></td>
					</tr>
					<c:forEach var="col" items="${obj.col }">
						<tr>
							<td align="center">${col.columnName }</td>
							<td align="center">${col.comments }</td>
							<td align="center">${col.dataType }</td>
							<td align="center">${col.dataLength }</td>
						</tr>
					</c:forEach>
				</table>

			</div>
			</div>
			<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
			</script>
	</body>

</body>
</html>