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

<title>松江区政务数据中心-人口库查询</title>
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
		action="${ctx}/query/toPersonList" method="post" onsubmit="beforesubmit()">
		<input id="newquery" type="hidden" name="newquery" value="0" />
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		
  <tr>
    <td valign="top" id="list_search"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      	<tr>
      		<td width="9%" align="right">姓名：</td>
      		<td><input id="xm" class="list_search_input" name="xm" value="${obj.xm }" style="width:90%;"/></td>
      		<td width="9%" align="right">出生年月：</td>
      		<td colspan="3">
      			<wd:datepicker id="csny1" name="csny1"
						dateFormat="yyyy-MM" defaultValue="${obj.csny1}"
						className="list_search_input" minDate="1900-01-01" style="width:92%;"
						 /></td>
      		<td align="right">至&nbsp;&nbsp;&nbsp;</td>
         	<td>
         		<wd:datepicker id="csny2" name="csny2"
						dateFormat="yyyy-MM" defaultValue="${obj.csny2}"
						className="list_search_input" minDate="1900-01-01" style="width:90%;"
						 />
			</td>
			<td align="center"><a href="#" onclick="clearcondition()">重置查询条件</a></td>
      	</tr>
        <tr>
          <td width="9%" height="30" align="right">证件号码：</td>
          <td width="15%"><input id="zjhm" class="list_search_input" name="zjhm"
					value="${obj.zjhm }" style="width:90%;"/></td>
          <td width="10%" align="right">年龄：</td>
          <td width="6%"><input id="nl" class="list_search_input" name="nl" value="${obj.nl }"
					 onkeyup='this.value=this.value.replace(/\D/gi,"")' maxlength="3" style="width:80%;"/>
			</td>
		  <td width="2%">&nbsp;至&nbsp;</td>
			<td width="6%" align="left"><input id="nl2" class="list_search_input" name="nl2" value="${obj.nl2 }"
					 onkeyup='this.value=this.value.replace(/\D/gi,"")' maxlength="3" style="width:80%;"/>
					 </td>
          <td width="8%" align="right">性别：</td>
          <td width="13%">
          				<wd:select id="xb" name="xb" dicCode="1059"
						className="list_search_select" initValue="-------请选择-------" style="width:94%;"
						defaultValue="${obj.xb }" />
          </td>
          <td width="8%" rowspan="2" align="center">
          		<input  type="button" name="button" id="list_search_button" value=" " class="list_button" onclick="query(1)" />
          		<!--<input type="hidden" name="countflag" value="1">
          	--></td>
        </tr>
        <tr>
          <td height="30" align="right">民族：</td>
          <td>
          		 <input id="mzbtn" type="button" class="minButton" value="点击可选择多个民族" style="width:93%" onclick="mzselect()" /> 
          		<%-- <wd:select id="mz" name="mz" dicCode="1055" 
						className="list_search_select" style="width:94%;"
						initValue="----------请选择----------" defaultValue="${obj.mz }" />  --%>
          </td>
          <td align="right" >人口类型：</td>
          <td colspan="3">
          	<wd:select id="syrklb" name="syrklb" dicCode="1061"
						className="list_search_select" initValue="----------请选择----------"
						defaultValue="${obj.syrklb }" style="width:94%;" /> 
          </td>
          <td align="right">
           		户籍街道：
          </td>
          <td>
            <wd:select id="hjjd" name="hjjd" dicCode="1077"
						className="list_search_select" initValue="-------请选择-------"
						defaultValue="${obj.hjjd }" style="width:94%;" /> 
          </td>
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
					<th width="20%">证件号码</th>
					<th width="15%">姓名</th>
					<th width="15%">出生年月</th>
					<th width="15%">性别</th>
					<th width="15%">户籍所属街道</th>
				</tr>
				<c:forEach items="${obj.list}" var="person" varStatus="row">
					<tr>
						<td align="center">${row.index+1 }</td>
						<td align="center">${person.zjhm }</td>
						<td align="center">
							<c:if test="${person.xm!=null&&person.xm!='' }">
						<a href="#"
							onclick="vo('${person.rid }')">${person.xm }</a>
							</c:if>
							<c:if test="${person.xm==null||person.xm=='' }">
								<a href="#"
							onclick="vo('${person.rid }')">***&nbsp;${person.ywx }</a>
							</c:if>
						</td>
						<td align="center">${person.csrq }</td>
						<td align="center"><wd:dicvalue dicId="1059"
								dicCode="${person.xbdm }" /></td>
						<td align="center">${person.hjdjdhz }</td>
					</tr>
				</c:forEach>
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
	 <!--弹窗 开始--> 
  <div class="divSCA1" id="cancelApply">
    <div class="divSCA1in">
      <dl class="dllist1 clearfloat">
        <dd>
        	<dt>请输入密码：</dt><input id="password" style="border: 1px solid;" type="password" value="" style="width:90%;" />
        </dd>
      </dl>
      <p class="p2"> <a href="javascript:cancel();" class="admita1">确定</a><a href="javascript:vo1();" class="admita2">取消</a> </p>
    </div>
  </div>
  <div class="backgroundPopup"></div>
  
  <!--弹窗 结束-->
</body>
<script type="text/javascript">
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
		if ( $('#zjhm').val() == ''&&$('#xm').val()==''&&$('#nl2').val()==''
				&& $('#nl').val() == '' && $('#xb').val() == ''&& $("input[type='checkbox'][name='mz']:checked").length==0
				&& $('#csny1').val() ==''&& $('#csny2').val() ==''
				&& $('#syrklb').val() == ''&&$('#hjjd').val() =='') {
			alert('请输入查询条件！');
			return;
		}if (type == '1') {
				$('#pageNo').val('1');
				$('#newquery').val('1');
				$('#queryForm').submit();
		}else{
			$('#newquery').val('1');
			$('#queryForm').submit();
		}
	}
	function view(id) {
		$('#loading').show();
		href = "${ctx}/query/personView?type=1&rid=" + id;
		var returnValue = window.showModalDialog(href, 'window',
				"dialogHeight=600px;dialogWidth=900px;center=yes");
		$('#loading').hide();
		if (returnValue == 1) {
			query();
		}
	}
	var a;
	function vo(id)
	{
		a=id;
		$(".divSCA1").show();
		$(".backgroundPopup").show();
	}
	function vo1()
	{
		$(".divSCA1").hide();
		$(".backgroundPopup").hide();
		$("input#password").val("");
	}
	function cancel() {
		var password=$("input#password").val();
		if(password=='888888')
		{
			$(".divSCA1").hide();
			$(".backgroundPopup").hide();
			view(a);
			$("input#password").val("");
		}
		else{
			alert("输入密码错误，请重新输入！");
			$("input#password").val("");
		}
	}
	function beforesubmit(){
		$('#loading').show();
	}
	window.onload=function(){
		$('.tablelist tr:odd').addClass('odd');
	};
	function mzselect(){
		if($('#mztable').attr('show')=='1'){
			$("#mztable").attr('show','0');
			$("#mztable").css('display','none');
			$('#mzbtn').val('点击可选择多个民族');
		}else{
			$("#mztable").attr('show','1');
			$("#mztable").css('display','block');
			$('#mzbtn').val('点击收起多民族');
		}
	}
	
	function selectallmz(){
		$("input[name='mz']").attr("checked",true);
	}
	function selectinvertmz(){
		 $("input[name='mz']").each(function () {  
			  
	            this.checked = !this.checked;  
	  
	      });
	}
	$(document).keyup(function(event){
		  if(event.keyCode ==13){
		    $(".list_button").trigger("click");
		  }
	});
	function countpeople(){
		if ( $('#zjhm').val() == ''&&$('#xm').val()==''&&$('#nl2').val()==''
			&& $('#nl').val() == '' && $('#xb').val() == ''&& $("input[type='checkbox'][name='mz']:checked").length==0
			&& $('#csny1').val() ==''&& $('#csny2').val() ==''
			&& $('#syrklb').val() == ''&&$('#hjjd').val() =='') {
		alert('请输入查询条件！');
		return;
		}
		$('#queryForm').submit();
	}
	function clearcondition(){
		$('#zjhm').val("");
		$('#xm').val("");
		$('#nl2').val("");
		$('#nl').val("");
		$('#xb').val("");
		$('#csny1').val("");
		$('#csny2').val("");
		$('#syrklb').val("");
		$('#hjjd').val("");
		$("input[name='mz']").attr("checked",false);
	}
</script>
</html>