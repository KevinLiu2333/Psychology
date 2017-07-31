<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<link href="${ctx }/tiles/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />  
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript"
	src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/tiles/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>

<title>松江区政务数据中心-专题查询</title>
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
/*弹窗 开始*/

.backgroundPopup { display: none; position: fixed; height: 100%; width: 100%; top: 0; left: 0; z-index: 1000; background: rgba(0,0,0,0.5); }
.divSCA1 { display: none; position: absolute; top: 50%; left: 68%; width: 52.875%; height: 14.375em; margin-left: -45.9375%; z-index: 1003; overflow: hidden; background: #FFF; border-radius: 5px; }
.divSCA1 .divSCA1in .dllist1 { overflow: hidden; padding-top: 3em;margin-left: 11em;}
.divSCA1 .divSCA1in .dllist1 dt { display: block; width: 6.8125em; height: 3.8125em; overflow: hidden; float: left; margin-left: 5.6%; }
.divSCA1 .divSCA1in .dllist1 dd { display: block; width: 63.26%; overflow: hidden; float: left; margin-left: 5.95%; }
.divSCA1 .divSCA1in .dllist1 dd h3 { height: 1.84em; line-height: 1.84em; overflow: hidden; font-size: 1.1875em; color: #ff8901; }
.divSCA1 .divSCA1in .dllist1 dd p { height: 2.32em; line-height: 2.32em; overflow: hidden; font-size: 0.78125em; color: #666; }
.divSCA1 .divSCA1in .p2 { margin-top: 4.5625em; text-align: center; }
.divSCA1 .divSCA1in .p2 a { display: inline-block; overflow: hidden; width: 15.76%; height: 2.733em; line-height: 2.733em; text-align: center; font-size: 0.9375em; color: #FFF; border-radius: 5px; }
.divSCA1 .divSCA1in .p2 a.admita1 { background: #ff4200; margin-right: 3.06%; }
.divSCA1 .divSCA1in .p2 a.admita2 { background: #ff4200; }
</style>
</head>
<body>
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
   	 		<td>&nbsp;</td>
  		</tr>
	</table>
	<form id="queryForm" name="queryForm"
		action="${ctx}/query/toSpecialList" method="post" onsubmit="beforesubmit()">
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		
  <tr>
    <td valign="top" id="list_search"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      	
        <tr>
          <td width="5%" height="30" align="right">&nbsp;&nbsp;&nbsp;法人名称：</td>
          <td width="15%"><input id="Name" class="list_search_input" name="Name"
					value="${obj.Name}" /></td>
          <td width="10%" align="right">专题类型：</td>
          <td width="10%">
         <wd:select id="table" name="table" dicCode="1078" className="list_search_select" style="width:94%;" onchange="changefrlx(1)" defaultValue="${obj.table }" initValue="-------请选择-------"/></td> 
			</td>
          <td width="8%" rowspan="2" align="center">
          		<input  type="button" name="button" id="list_search_button" value=" " class="list_button" onclick="query(1)" />
          		<!--<input type="hidden" name="countflag" value="1">
          	--></td>
        </tr>
        
    </table>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
	<div style="height: 10px">&nbsp;</div>
		 <table id="mztable" width="96%" border="0" align="center" cellpadding="0" cellspacing="0" style="display: none;">
 	 	<tr>
    		<td valign="top" id="list_search" style="">
    		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        		<tr>
          			<td>&nbsp;</td>
        		</tr>
    		</table>
    		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
        		<tr>
          			<td><wd:checkbox  name="mz" dicCode="1060"  defaultValuesStr="${obj.mz }" style="margin:0 0 0 5px;" /></td>
        		</tr>
        		<tr><td align="center"><input onclick="selectallmz()" class="minButton" type="button" value="全选" />&nbsp;
        		<input class="minButton" type="button" value="反选" onclick="selectinvertmz()" /></td></tr>
    		</table>
    		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
       			 <tr>
          			<td>&nbsp;</td>
        		</tr>
    		</table>
   		 </td>
   		</tr>
    </table>  
		<div style="height: 10px;"></div>
		<div align="center">
			<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0"  class="tablelist">
				<tr>
					<th width="5%">序号</th>
					<th width="20%">法人名称</th>
					<th width="15%">法定代表人</th>
					<th width="15%">地址</th>
				</tr>
				<c:if test="${obj.table!=null&&obj.table=='xjr' }">
				<c:forEach items="${obj.xjr}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center"><a href="#" onclick="view('${person.organCode }')"  >${person.businessName }</a></td>
						<td align="center">${person.linkman }</td>
						<td align="center">${person.address }</td>
					</tr>
				</c:forEach>
				</c:if>
				
			<c:if test="${obj.table!=null&&obj.table=='lhrh' }">
				<c:forEach items="${obj.lhrh}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center" ><a href="#" onclick="view('${person.organCode }')">${person.applicationUnits }</a></td>
						<td align="center" >${person.principal }</td>
						<td align="center">${person.businessAddress }</td>
					</tr>
				</c:forEach>
				</c:if>
				
				<c:if test="${obj.table!=null&&obj.table=='gq' }">
				<c:forEach items="${obj.gq}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center" ><a href="#" onclick="view('${person.organCode }')">${person.companyName }</a></td>
						<td align="center" >${person.corporaTion }</td>
						<td align="center">${person.address }</td>
					</tr>
				</c:forEach>
				</c:if>
			</table>
		</div>
		<div>
			<table width="96%" class="tables">
				<tr>
					<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
					</td>
				</tr>
			</table>
		</div>
			
	</form>
	<div id="loading" class="loading" >
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
	</div>
	<p id="Object" style="display:none">
		<OBJECT  ID="SafeEngineCtl" CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="400" height="50" border=0 ></OBJECT>
	</p>
</body>
<script type="text/javascript"><!--
	/* window.onload = function() { 
		$("input[type=checkbox]").click(function(){
			alert("test");
		});
	}; */
	
	function mzselect(){
		var len=$("input[name='mz'][type='checkbox'][checked]").length;
		if(len>0){
			return false;
		}
		return true;
	}
	function query(type) {
		if ($('#table').val()==''&& $('#Name').val()=='' || $('#Name').val()!='' && $('#table').val()=='') {
			alert('请输入查询条件！');
			return;
		}
		if (type == '1') {
			$('#pageNo').val('1');
			$('#pageSize').val('10');
			//var i=$('#ty option:selected').val();
				//$('#queryForm').attr('action','${ctx}/query/toSpecialList?Name='+$('#linkman').val()+"&table="+i);
				$('#queryForm').submit();
		}
	}
	function view(id) {
		$('#loading').show();
		var table="${obj.table}";
		href = "${ctx}/query/specialView?table="+table+"&address="+id;
		var returnValue = window.showModalDialog(href, 'window',
				"dialogHeight=600px;dialogWidth=900px;center=yes");
		$('#loading').hide();
		if (returnValue == 1) {
			query();
		}
	}

	function beforesubmit(){
		$('#loading').show();
	}
	window.onload=function(){
		$('.tablelist tr:odd').addClass('odd');
	};
	
</script>
</html>