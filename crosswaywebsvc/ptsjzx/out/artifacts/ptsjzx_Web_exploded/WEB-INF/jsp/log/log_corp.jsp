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

<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>	
	
	<div style="height: 20px">&nbsp;</div>
	<table width="96%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td width="30%" class="info_title_a">表名</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">数据量</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">CORP_INFO</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.corpinfo }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">CORP_LICENSE</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.license }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">PUNISH_NOTE_CORP_ENTY</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.punishcorpenty }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">PUNISH_NOTE_ENTY</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.punishnoteenty }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">PUNISH_NOTE_INFO_ENTY</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.punishinfoenty }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">CORP_RENEW_NORMAL</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.normal }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">CORP_REPEAL</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.repeal }</td>
    		</tr>
    		<tr>
    			<td width="30%" class="info_title_b">总计</td>
          		<td width="70%" bgcolor="#FFFFFF" align="center">${obj.repeal+obj.normal+obj.punishinfoenty+obj.punishnoteenty+obj.punishcorpenty+obj.corpinfo+obj.license }</td>
    		</tr>
    	</table>
   	<div style="height: 20px">&nbsp;</div>
	

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