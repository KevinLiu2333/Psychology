<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>数据填报统计</title>
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
.table1 {
border-right: 1px solid #CBCBCB;
border-bottom: 1px solid #CBCBCB;
border-collapse:collapse;
}
.table1 td {
height:40px;
border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
text-align:center;
}
.table1 th {

border-left: 1px solid #CBCBCB;
border-top: 1px solid #CBCBCB;
}
.new td{
background: #FFF2CC;
font-family: '宋体';
font-size: 15px;
}
</style>
</head>
<body>
	<div style="height: 20px;">&nbsp;</div>
	<form id="queryForm" name="queryForm"
		action="${ctx}/sjtb/toTbCount" method="post">
	<table width="80%" border="0" height="40" align="center"
			cellpadding="0" cellspacing="0" class="query_search">
			<tr>
				<td align="right">数据上传月份：</td>
				<td align="left" width="50px">
					<wd:datepicker id="checkedDate" name="datamonth"
						dateFormat="yyyy-MM" defaultValue="${obj.datamonth}"
						className="dfinput" minDate="2016-01-01" onchange="changedate()"
						 />
				</td>
				<td>至</td>
				<td align="left">
					<wd:datepicker id="checkedDate1" name="datamonth1"
						dateFormat="yyyy-MM" defaultValue="${obj.datamonth1}"
						className="dfinput" minDate="2016-01-01" onchange="changedate1()"
						 />
				</td>
				<td align="right">
				<input type="button" class="minButton" style="width: 80px" onclick="query(1)" value="统    计" />&nbsp;&nbsp;&nbsp;
				
					<input type="button" class="minButton" style="width: 120px" onclick="toupload()" value="数据上传" />&nbsp;&nbsp;&nbsp;
			
				</td> 
			</tr>
	</table>
	</form>
	<div style="height: 20px;">&nbsp;</div>
	<div align="center" style="height: 860px;">
	<table class="table1" width="80%"border="1" cellpadding="2"cellspacing="1" >
		<tr >
			<th width="5%">序号</th>
			<th width="35%">部门</th>
			<th width="15%">人口类</th>
			<th width="15%">法人类</th>
			<th width="15%">产业经济类</th>
			<th width="15%">部门数据总量</th>
		</tr>
		<c:forEach items="${obj.list}" var="l1" varStatus="row">
		<tr>
			<td align="center">${row.index+1 }</td>
			<td align="center"> <a  href="javascript:;" onclick="view('${l1.deptid}',this,'${row.index+1 }')" style="text-decoration:underline;color:blue">${l1.dept }</a></td>
			<c:forEach items="${l1.list }" var="l2">
				<td align="center">
					<c:if test="${l2.tbbm==null }"><a >${l2.sheets }</a></c:if>
					<c:if test="${l2.tbbm!=null&&l2.sheets=='1' }"><a style="color: #C81801">未上传</a></c:if>
					<c:if test="${l2.tbbm!=null&&l2.sheets=='2' }"><a style="color: green" >已上传(${l2.scs })</a></c:if>
				</td>
			</c:forEach>
			<td align="center"> ${l1.count4}</td>
		</tr>
		</c:forEach>
		<tr >
			<td width="5%" height="40px">20</td>
			<th width="35%">部门类别数据量</th>
			<td width="15%">${obj.count1}</td>
			<td width="15%">${obj.count2}</td>
			<td width="15%">${obj.count3}</td>
			<th width="15%">${obj.count5}</th>
		</tr>
	</table>
	</div>
</body>
<script type="text/javascript">
function count(type,col){
	if($('#checkedDate').val()==''||$('#checkedDate1').val()==null){
		alert('请选择数据所属月份');
		return ;
	}
	$('.table1').find("tr[class='new']").each(function(){
		$(this).remove();
	});
	$.post("${ctx}/sjtb/getcountdata",
			{type:type,datamon:'${obj.ssyf}',datamon1:'${obj.ssyf1}'},
			function(data, textStatus){
				data = eval('('+data+')');
				var html="<tr class='new'><td></td><td align='center'>数据类型</td><td align='center'>数据条数</td><td></td><td></td></tr>";
				for(var i=0;i<data.length ;i++ ){
					html=html+"<tr class='new'><td></td><td align='center'>"+data[i].sheet+
						"</td><td align='center'>"+data[i].value+"</td><td></td><td></td></tr>";
				}
				var currentcol=$(col).parent().parent();
				currentcol.after(html);
	});
	
}
function query(type) {
	if($('#checkedDate').val()==''||$('#checkedDate1')==null){
		alert("请选择所要查询月份");
		return;
	}
	$('#queryForm').submit();
}

function sendSms(){
	$.post("${ctx}/sms/batchSendSms",
		{ Action: "post"},
		function(data, textStatus){
			data = eval('('+data+')');
			if(data == '1'){
				alert("短信发送成功！");
			}else{
				alert("短信发送失败！");
			}
	});
}

function view(dept,col,rowid){
	if($(col).attr("ifselect")=='1'){
		$(col).attr("ifselect","0");
		$('.table1').find("tr[rowid='"+rowid+"']").each(function(){
			$(this).remove();
		});
	}else{
		$(col).attr("ifselect","1");
		$.post("${ctx}/sjtb/getcountdataByDept",
				{deptid:dept,datamon:'${obj.ssyf}',datamon1:'${obj.ssyf1}'},
				function(data, textStatus){
					data = eval('('+data+')');
					var html="<tr class='new' rowid='"+rowid+"'><td align='center' colspan='2'>数据内容</td><td align='center' colspan='4'>数据条数</td></tr>";
					for(var i=0;i<data.length;i++){
						if(i==0&&data[i].length>0){
							html=html+"<tr class='new' rowid='"+rowid+"'><td colspan='6'  align='center' >人口类</td></tr>";
						}
						if(i==1&&data[i].length>0){
							html=html+"<tr class='new' rowid='"+rowid+"'><td colspan='6'  align='center' >法人类</td></tr>";
						}
						if(i==2&&data[i].length>0){
							html=html+"<tr class='new' rowid='"+rowid+"'><td colspan='6' align='center' >产业经济类</td></tr>";
						}
						for(var j=0;j<data[i].length;j++){
							if(i==0){
								html=html+"<tr class='new' rowid='"+rowid+"'><td align='center' colspan='2'>"+data[i][j].sheet+
								"</td><td align='center' colspan='2'>"+data[i][j].value+"</td><td></td><td></td></tr>";
							}
							if(i==1){
								html=html+"<tr class='new' rowid='"+rowid+"'><td align='center' colspan='2'>"+data[i][j].sheet+
								"</td><td></td><td align='center' colspan='2'>"+data[i][j].value+"</td><td></td></tr>";
							}
							if(i==2){
								html=html+"<tr class='new' rowid='"+rowid+"'><td align='center' colspan='2'>"+data[i][j].sheet+
								"</td><td></td><td></td><td align='center' colspan='2'>"+data[i][j].value+"</td></tr>";
							}
						}
					}
					var currentcol=$(col).parent().parent();
					currentcol.after(html);
				});
	}
}
function changedate(){
	var date=$('#checkedDate').val();
	$('#checkedDate1').val(date);
}
function changedate1(){
	var date=$("#checkedDate").val();
	var date1=$("#checkedDate1").val();
	if(date.replace("-","")>date1.replace("-","")){
		alert("请选择大于等于"+date+"的月份！");
		$('#checkedDate1').val(date);
	}
}

function toupload(){
	href = "${ctx}/sjtb/toTbymBackgroud";
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=1200px;center=yes");
}
</script>
</html>