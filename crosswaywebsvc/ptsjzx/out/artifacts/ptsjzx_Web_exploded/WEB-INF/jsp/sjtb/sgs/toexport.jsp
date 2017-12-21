<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据导出</title>
<style type="text/css">
.loading{
top:250px;
left:350px;
z-index:10000;
position:absolute; 
font-size: 30px;
color: rgba(200,0,0,0.5);
filter:alpha(opacity=80);
opacity:0.8;
display: none;
}
</style>
</head>
<body>

<div style="height:20px;">&nbsp;</div>
<form id="expform" name="expform"  action="${ctx}/sjtb/sgs/exportdata" method="post">
	<table width="96%" border="0" height="80" align="center" cellpadding="0" cellspacing="0" class="query_search">
		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;
				所属部门:&nbsp;</td>
			<td>
				<wd:select id="dept" name="dept" dicCode="2009" style="height:25px" className="dfinput" initValue="---请选择---" defaultValue="${obj.dept }" />
			</td>
			<td>
				数据类型：&nbsp;
				<wd:select id="sjlx" name="sjlx" dicCode="2008" style="height:25px" className="dfinput" initValue="---请选择---" defaultValue="${obj.sjlx }"/>
			</td>
			<td width="270px">数据所属月份：&nbsp;<wd:datepicker  id="sjssyf" name="sjssyf" dateFormat="yyyy-MM"  defaultValue="${obj.sjssyf}" className="dfinput" minDate="2014-01-01" onchange="changedate()" style="width:150px;height:25px;" />
			</td>
			<td>至&nbsp;<wd:datepicker  id="sjssyf1" name="sjssyf1" dateFormat="yyyy-MM"  defaultValue="${obj.sjssyf1}" className="dfinput" minDate="2014-01-01" onchange="changedate1()" style="width:150px;height:25px;" />
			</td>
			<td>
				<input type="button" class="minButton" style="width:120px" onclick="export1()" value="导出数据"/>
			</td>
		</tr>
		<tr>
		   <td>&nbsp;&nbsp;&nbsp;&nbsp;
		                 决定日期：&nbsp;
		    </td>
		    <td><wd:datepicker  name="jdrq1"
					dateFormat="yyyy-MM-dd" defaultValue="${obj.jdrq1}"
					className="dfinput" minDate="1900-01-01"  style="height:25px"
					 /></td><td>
					 至
				
				<wd:datepicker  name="jdrq2"
					dateFormat="yyyy-MM-dd" defaultValue="${obj.jdrq2}"
					className="dfinput" minDate="1900-01-01"   style="height:25px"
					 /></td>
			
					   
		</tr>
	</table>
	<div id="loading" class="loading" ><img src="${ctx }/skins/images/loading.gif" />&nbsp;正在导出...</div>
</form>
</body>
<script type="text/javascript">
function export1(){
	var dept = $('#dept').val();
	var sjlx = $('#sjlx').val();
	var sjssyf =$('#sjssyf').val();
	var sjssyf1 =$('#sjssyf1').val();
    if(sjlx==''){
		alert('请选择数据类型');
	}else{
		if(dept==''){
		  if(confirm("您正在导出所有单位数据,确定导出吗？")){
		  /* $('#expform').attr('action','${ctx}/sjtb/sgs/exportdata?dept='+dept+"&sjlx="+sjlx+"&sjssyf="+sjssyf+"&sjssyf1="+sjssyf1); */
		  $('#expform').submit();
		  $('#loading').show();
		  var t=setTimeout(hide,6000)
		  }
		}else{
			$('#expform').submit();
			$('#loading').show();
			var t=setTimeout(hide,6000)
		}
	}
}
function hide(){
	$('#loading').hide();
}

function changedate(){
	var date=$('#sjssyf').val();
	$('#sjssyf1').val(date);
}
function changedate1(){
	var date=$("#sjssyf").val();
	var date1=$("#sjssyf1").val();
	if(date.replace("-","")>date1.replace("-","")){
		alert("请选择大于等于"+date+"的月份！");
		$('#sjssyf1').val(date);
	}
}
</script>
</html>