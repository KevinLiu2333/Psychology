<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/meta.jsp"/>
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>  
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据查询日志</title>
<style type="text/css">
.info_title_aa,.info_title_bb {
	text-align:center;
}
.info_title_aa {
	background:url(${ctx}/skins/query/images/info_title_bg.jpg) repeat-x;
	background-size:100%;
}
.info_title_bb {
	background:#FFF;
}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" action="${ctx}/log/toLogCount" method="post">
<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>	
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="list_search" >
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
        			<td>&nbsp;</td>
     			 </tr>
			</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" style="height: 50px">
		<tr>
			<td >&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>登录名：</td><td><input class="dfinput" name="user" value="${obj.user }"/></td>
			<td>操作时间：</td><td><wd:datepicker id="checkedDate" name="czsj1"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj1}"
						className="dfinput" minDate="2016-01-01" onchange="changedate1()" style="width:150px;"
						 /></td>
			<td>
				&nbsp;&nbsp;至&nbsp;</td><td><wd:datepicker id="checkedDate1" name="czsj2"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.czsj2}"
						className="dfinput" minDate="2016-01-01" onchange="changedate2()" style="width:150px;"
						 /></td>
			<td width="6%" rowspan="1" valign="middle" align="center"><input
					type="button" class="minButton" value="统计" style="" onclick="dosubmit()" /></td>
			<td width="5%">&nbsp;</td>
			<td width="6%"><input type="button" class="minButton" value="导出"  onclick="exportExecl()" /></td>
		</tr>
		
	</table>
	</td>
	</tr>
	</table>
	<div style="height: 20px">&nbsp;</div>
	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">管理日志</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">统计条件</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.login_condition }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">登录用户数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.login_countuser }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">登录次数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.login_countsucess }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">开启用户数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.kq }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">关闭用户数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.gb }人</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">修改权限数</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.xg }人</td>
    		</tr>
    	</table>
   	<div style="height: 20px">&nbsp;</div>
	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">统计类型</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">查询日志</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">统计条件</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query_condition }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">查询人口列表</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query1 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">查询人口详情</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query2 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">比对人口信息</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query9 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">统计人口信息</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query7 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">人口房屋查询</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query8 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">查询法人列表</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query3 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">查询法人详情</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query4 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">导出法人列表</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query5 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">导出法人详情</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query6 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">比对法人信息</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query10 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">一数一源上报</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query11 }次</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">双公示上报</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.query12 }次</td>
    		</tr>
    	</table>
</form>
<form id="export" action="${ctx}/log/exportLogCount" method="post">
		<input type="hidden"  name="login_condition" value="${obj.login_condition }" />
		<input type="hidden"  name="login_countuser" value="${obj.login_countuser }" />
		<input type="hidden"  name="login_countsucess" value="${obj.login_countsucess }" />
		<input type="hidden"  name="kq" value="${obj.kq }" />
		<input type="hidden"  name="gb" value="${obj.gb }" />
		<input type="hidden"  name="xg" value="${obj.xg }" />
		<input type="hidden"  name="query_condition" value="${obj.query_condition }" />
		<input type="hidden"  name="query1" value="${obj.query1 }" />
		<input type="hidden"  name="query2" value="${obj.query2 }" />
		<input type="hidden"  name="query3" value="${obj.query3 }" />
		<input type="hidden"  name="query4" value="${obj.query4 }" />
		<input type="hidden"  name="query5" value="${obj.query5 }" />
		<input type="hidden"  name="query6" value="${obj.query6 }" />
		<input type="hidden"  name="query7" value="${obj.query7 }" />
		<input type="hidden"  name="query8" value="${obj.query8 }" />
		<input type="hidden"  name="query9" value="${obj.query9 }" />
		<input type="hidden"  name="query10" value="${obj.query10}" />
		<input type="hidden"  name="query11" value="${obj.query11}" />
		<input type="hidden"  name="query12" value="${obj.query12}" />
	</form>
</body>
<script type="text/javascript">
function setTab(name,cursel,n){
	 for(var i=1;i<=n;i++){
	  var menu=document.getElementById(name+i);
	  var con=document.getElementById("con_"+name+"_"+i);
	  menu.className=i==cursel?"active":"";
	  con.style.display=i==cursel?"block":"none";
	 }
	}
function exportExecl(){
	$('#export').submit();
}
function dosubmit(){
	$('#queryForm').submit();
}
function changedate1(){
	var date=$('#checkedDate').val();
	$('#checkedDate1').val(date);
}
function changedate2(){
	var date=$("#checkedDate").val();
	var date1=$("#checkedDate1").val();
	if(date.replace("-","")>date1.replace("-","")){
		alert("请选择大于等于"+date+"的日期！");
		$('#checkedDate1').val(date);
	}
}
</script>
</html>