<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/meta.jsp" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<title>松江区政务数据中心-法人登记信息查询</title>
<style type="text/css">
.loading {
	top: 250px;
	left: 350px;
	z-index: 10000;
	position: absolute;
	font-size: 30px;
	color: rgba(200, 0, 0, 0.5);
	filter: alpha(opacity = 80);
	opacity: 0.8;
	display: none;
}

</style>
</head>
<body>
	<form id="queryForm" name="queryForm"
		action="${ctx}/query/toCorpInfoList" method="post" onsubmit="beforesubmit()">
		<input id="newquery" type="hidden" name="newquery" value="0" />
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
  	 	 <td>&nbsp;</td>
 		 </tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top" id="list_search" style="height: 120px;">
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
        			<td>&nbsp;</td>
     			 </tr>
			</table>
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					
					<td width="8%" height="30" align="right">代码类型：</td>
					<td width="13%" align="left"><wd:select id="dmlx" name="dmlx" dicCode="1076" className="list_search_select" style="width:94%;" defaultValue="${obj.dmlx }" initValue="请选择代码类型" onchange="changedmlx()"/></td>
					<td width="13%" colspan="2" align="left"><input id="dmh" type="text" class="list_search_input" name="dmh" value="${obj.dmh }"  style="width:90%;" /><!-- <span id="dmts">(请选择代码类型)</span> --></td>
					
						
						
						<td width="11%" height="30" align="right">法人名称：</td>
					<td width="13%"><input id="dwmc" type="text" class="list_search_input" name="dwmc" value="${obj.dwmc }" style="width:90%;" /></td>
					
					<td width="6%" height="30" align="right">法人状态：</td>
					<td width="13%"><wd:select id="frzt" name="frzt" dicCode="1041" className="list_search_select" style="width:94%;" defaultValue="${obj.frzt }" initValue="-------请选择-------"/></td>	
					<td width="8%" style="text-align:center;"><a href="#" onclick="clearcondition()"><span style="font-size:12px;">重置查询条件</span></a></td>
				</tr>
				<tr>
				     <td width="8%" height="30" align="right">产业类别：</td>
					<td width="13%">
						<wd:select id="cylb1" name="cylb1" dicCode="1074" className="list_search_select" style="width:93%;" onchange="changecylb(1)" defaultValue="${obj.cylb1 }" initValue="-------请选择-------"/>
					</td>
					<td width="19" colspan="2" align="left"><select id="cylb2" name="cylb2" class="list_search_select" style="width:93%;" disabled="disabled">
							<option value="">------请选择------</option>
						</select></td>
				     
					
					
						<td height="30" align="right">注册资金(万元)：</td>
					<td><input id="zczj1" class="list_search_input" style="width:90%;" name="zczj1" value="${obj.zczj1 }"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
					<td height="30" align="right">至：</td>
					<td><input id="zczj2" class="list_search_input" style="width:90%;" name="zczj2" value="${obj.zczj2 }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
					<td width="8%" rowspan="2" valign="middle" align="center"><input type="button" name="button" id="list_search_button" value=" " onclick="query(1)"/></td>
				</tr>
				<tr>
				    <td width="5%" height="30" align="right">法人类型：</td>
					<td width="13%"><wd:select id="frlx" name="frlx" dicCode="1075" className="list_search_select" style="width:94%;" onchange="changefrlx(1)" defaultValue="${obj.frlx }" initValue="-------请选择-------"/></td>
					<td colspan="2">
						<select id="frlx2" name="frlx2" class="list_search_select" style="width:93%;" disabled="disabled">
							<option value="">------请选择------</option>
						</select>					
					</td> 
					
					<td height="30" align="right">成立日期：</td>
					<td><wd:datepicker id="checkedDate1" name="zcrq1"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.zcrq1}"
						className="list_search_input" minDate="1900-01-01" style="width:90%;"
						 /></td>
					<td height="30" align="right">至：</td>
					<td><wd:datepicker id="checkedDate2" name="zcrq2"
						dateFormat="yyyy-MM-dd" defaultValue="${obj.zcrq2}"
						className="list_search_input" minDate="1900-01-01" style="width:90%;"
						 /></td>
				</tr>
			</table>
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      			<tr>
        		<td>&nbsp;</td>
    			  </tr>
   				 </table>
		</td>
	</tr>
	</table>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
 		 <tr>
   			 <td>&nbsp;</td>
  		</tr>
	</table>
		<div align="center">
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
				<tr>
					<th width="5%">序号</th>
					<th width="35%">法人名称</th>
					<th width="15%">组织机构代码</th>
					<th width="15%">法定代表人</th>
					<th width="10%">法人状态</th>
					<th width="15%">成立日期</th>
					<th width="5%">操作</th>
				</tr>
				<c:forEach items="${obj.list}" var="corp" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">
							<c:if test="${fn:length(corp.corpname)<20 }">${corp.corpname }</c:if>
							<c:if test="${fn:length(corp.corpname)>=20 }">${fn:substring(corp.corpname,0,19) }...</c:if>
						</td>
						<td align="center">
							<c:if test="${corp.organcode!=null&&corp.organcode!='NULL' }">${corp.organcode }</c:if>
						</td>
						<td align="center">
							<c:if test="${corp.personname!=null&&corp.personname!='NULL' }">${corp.personname }</c:if>
						</td>
						<td align="center"><wd:dicvalue dicId="1041" dicCode="${corp.corpstatus }"/></td>
						<td align="center">
							<c:if test="${corp.establishdate!=null }">
								<fmt:formatDate value="${corp.establishdate }" pattern="yyyy年MM月dd日"/>
							</c:if>
						</td>
						<td align="center"><a href="#" onclick="view('${corp.corpinfoid}')">查看</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<table width="96%" class="tables">
				<tr>
					<td>
					<c:if test="${obj.list!=null}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" class="minButton" value="列表导出（1000条）" style="width:180px" onclick="exportExecl()" /></td>
					</c:if>
					<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<form id="export" action="${ctx}/query/exportCorpInfo" method="post">
		<input type="hidden"  name="dwmc" value="${obj.dwmc }" />
		<input type="hidden"  name="frzt" value="${obj.frzt }" />
		<input type="hidden"  name="frlx" value="${obj.frlx }" />
		<input type="hidden"  name="frlx2" value="${obj.frlx2 }" />
		<input type="hidden"  name="cylb1" value="${obj.cylb1 }" />
		<input type="hidden"  name="cylb2" value="${obj.cylb2 }" />
		<input type="hidden"  name="dmh" value="${obj.dmh }" />
		<input type="hidden"  name="dmlx" value="${obj.dmlx }" />
		<input type="hidden"  name="zczj1" value="${obj.zczj1 }" />
		<input type="hidden"  name="zczj2" value="${obj.zczj2 }" />
		<wd:datepicker  name="zcrq1" dateFormat="yyyy-MM-dd" defaultValue="${obj.zcrq1}" style="display: none;" />
		<wd:datepicker  name="zcrq2" dateFormat="yyyy-MM-dd" defaultValue="${obj.zcrq2}" style="display: none;" />
	</form>
	<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
	</div>
	
</body>
<script type="text/javascript">
	window.onload=function(){
		$('.tablelist tr:odd').addClass('odd');
		changefrlx(2);
		changecylb(2);
		
	};
	function changefrlx(type){
		var frlx1val='';
		if(type==1){
			frlx1val=$('#frlx').val();
		}
		if(type==2){
			frlx1val='${obj.frlx}';
		}
		if(frlx1val==''){
			$('#frlx2').attr('disabled','disabled');
			$('#frlx2').html('<option value="">------请选择------</option>');
		}else{
			$('#frlx2').removeAttr('disabled');
			$.post('${ctx}/query/getfrlx',
					{frlx:frlx1val},
					function(data, textStatus){
						data=eval('('+data+")");
						var html="<option value=''>------请选择------</option>";
						for(var i=0;i<data.length;i++){
					 		if('${obj.frlx2}'==data[i].id){
					 			html=html+"<option value='"+data[i].id+"' selected>"+data[i].value+"</option>";
					 		}else{
					 			html=html+"<option value='"+data[i].id+"' >"+data[i].value+"</option>";
					 		}
					 	}
						$('#frlx2').html(html);
					});
		}
	}
	function changecylb(type){
		var cylb1val='';
		if(type==1){
			cylb1val=$('#cylb1').val();
		}
		if(type==2){
			cylb1val='${obj.cylb1}';
		}
		if( cylb1val=='' ){
			$('#cylb2').attr('disabled','disabled');
			$('#cylb2').html('<option value="">------请选择------</option>');
		}else{
			$('#cylb2').removeAttr('disabled');
			$.post('${ctx}/query/getcylb',
					{cylb:cylb1val},
					function(data, textStatus){
				 	data=eval('('+data+")");
				 	var html="<option value=''>------请选择------</option>";
				 	for(var i=0;i<data.length;i++){
				 		if('${obj.cylb2}'==data[i].id){
				 			html=html+"<option value='"+data[i].id+"' selected>"+data[i].value+"</option>";
				 		}else{
				 			html=html+"<option value='"+data[i].id+"' >"+data[i].value+"</option>";
				 		}
				 	}
				 	$('#cylb2').html(html);
			});
		}
	}
	function clearcondition(){
		$('#dwmc').val("");
		$('#frzt').val("");
		$('#frlx').val("");
		$('#zczj1').val("");
		$('#zczj2').val("");
		$('#checkedDate1').val("");
		$('#checkedDate2').val("");
		$('#cylb1').val("");
		$('#dmlx').val("");
		$('#dmlx').val("");
		$('#dmh').val("");
		changefrlx(1);
		changecylb(1);
		changedmlx();
	}
	function query(type) {
		if($('#dwmc').val()==''&&$('#frzt').val()==''&&$('#frlx').val()==''
			&&$('#zczj1').val()==''&&$('#zczj2').val()==''&&$('#checkedDate1').val()==''&&$('#checkedDate2').val()==''&&$('#cylb1').val()==''
			&&($('#dmlx').val()==''||$('#dmh').val()=='')){
			alert("请输入查询条件！");
			return;
		}
		jumpPage(1);
		$('#newquery').val('1');
		$('#queryForm').submit();
	}
	function view(id){
		var re = window.showModalDialog("${ctx}/query/viewCorpInfo?corpinfoid="+id,self,"dialogWidth=910px;dialogHeight=600px;status:no;");
		if(re){
			query();
		}
	}
	


	function beforesubmit(){
		$('#loading').show();
	}
	function exportExecl(){
		$('#export').submit();
	}
	
	$(document).keyup(function(event){
		  if(event.keyCode ==13){
		    $("#list_search_button").trigger("click");
		  }
	});
	function changedmlx(){
		$('#dmts').html("("+$("#dmlx").find("option:selected").text()+")");
	}
	
	
</script>

</html>