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
<link rel="stylesheet" href="${ctx}/tiles/bootstrap3/css/bootstrap.min.css" type="text/css" />

<script type="text/javascript"
	src="${ctx }/tiles/bootstrap3/js/jquery1.10.2.js"></script>
<script type="text/javascript"
	src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/tiles/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${ctx }/tiles/bootstrap3/js/bootstrap.min.js" type="text/javascript"></script>

<title>人口库查询</title>
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
						className="list_search_input" minDate="1900-01-01" onchange="changedate1()" style="width:92%;"
						 /></td>
      		<td align="right">至&nbsp;&nbsp;&nbsp;</td>
         	<td>
         		<wd:datepicker id="csny2" name="csny2"
						dateFormat="yyyy-MM" defaultValue="${obj.csny2}"
						className="list_search_input" minDate="1900-01-01" onchange="changedate2()" style="width:90%;"
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
          				<wd:select id="xb" name="xb" dicCode="1056"
						className="list_search_select" initValue="-------请选择-------" style="width:94%;"
						defaultValue="${obj.xb }" />
          </td>
          <td width="8%" rowspan="2" align="center">
          	<c:if test="${fn:contains(sessionScope.user.roleId, '6')||fn:contains(sessionScope.user.roleId, '7')}">
          		<input  type="button" name="button" id="list_search_button" value=" " class="list_button" onclick="query(1)" />
          	</c:if>	
          	<c:if test="${!fn:contains(sessionScope.user.roleId, '6')&&!fn:contains(sessionScope.user.roleId, '7')}">
          		<input  type="button" name="button" id="list_count_button" value=" " class="list_button" onclick="countpeople()" />
          		<input type="hidden" name="countflag" value="1">
          	</c:if>
          	</td>
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
          	<wd:select id="syrklb" name="syrklb" dicCode="1058"
						className="list_search_select" initValue="----------请选择----------"
						defaultValue="${obj.syrklb }" style="width:94%;" /> 
          </td>
          <td align="right">
           		户籍街道：
          </td>
          <td>
            <wd:select id="hjjd" name="hjjd" dicCode="1059"
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
          			<td><wd:checkbox  name="mz" dicCode="1055"  defaultValuesStr="${obj.mz }" style="margin:0 0 0 5px;" /></td>
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
	<c:if test="${fn:contains(sessionScope.user.roleId, '6')||fn:contains(sessionScope.user.roleId, '7')}">
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
							onclick="view('${person.rid }')">${person.xm }</a>
							</c:if>
							<c:if test="${person.xm==null||person.xm=='' }">
								<a href="#"
							onclick="view('${person.rid }')">***&nbsp;${person.ywx }</a>
							</c:if>
						</td>
						<td align="center">${person.csrq }</td>
						<td align="center"><wd:dicvalue dicId="1056"
								dicCode="${person.xbdm }" /></td>
						<td align="center">${person.hjdjdhz }</td>
					</tr>
				</c:forEach>
				<c:if test="${obj.warn != null}">
				<tr>
				    <td colspan="6" style="color:red;text-align:center">${obj.warn }!</td>
				</tr>
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
		</c:if>
		<c:if test="${!fn:contains(sessionScope.user.roleId, '6')&&!fn:contains(sessionScope.user.roleId, '7')}">
			<div align="center" style="font-size: 25px;">按上述查询条件统计,共&nbsp;<a style=" font-size: 30px;font-weight: 5;color: red;">
				<c:if test="${obj.countresult ==null }">0</c:if>
				<c:if test="${obj.countresult!=null }">${obj.countresult }</c:if>
			</a>&nbsp;人</div>
		</c:if>
	</form>
	<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
	</div>
	<p id="Object" style="display:none">
		<OBJECT  ID="SafeEngineCtl" CLASSID="CLSID:B48B9648-E9F0-48A3-90A5-8C588CE0898F" width="400" height="50" border=0 ></OBJECT>
	</p>
	
	
		<!-- Modal ie8 -->
	    <div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="margin-left:35%;margin-top:10%">
	      <div class="modal-dialog modal-sm" role="document">
	        <div class="modal-content" style="width:300px; height:280px;">
	          <div class="modal-header btn-danger">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            <h5 class="modal-title" id="myModalLabel">查询超出次数限制,请输入验证码！</h5>
	          </div>
	          <div class="modal-body" style="margin-top:30px;margin-bottom:30px">
	          	<div class="from-group" style="width:60%;float:left">
	          		<input type="text" class="form-control" name="validatorCode" id="validatorCode" placeholder="请输入验证码">
	          	</div>
	          	<div class="from-group" style="width:40%;float:left">
	   				<!-- <span id="code" onclick="changeCode()" val="${obj.code }"></span> -->
	   				<img id="imgCode" alt="点击获取验证码" src="" onclick="toChangeVerify()" style="width:90px;height:32px;margin-left:10px">
	            </div>
	            <div style="width:100%;float:left">
	            	<p hidden id="errorCode" style="color:red;maggin-left:20px">验证码错误,点击图片更换验证码！</p>
	            </div>
	            <br>
	            <br>
	          </div>
	          <div class="modal-footer" style="margin-top:60px">
	            <button type="button" class="btn-sm btn-default" data-dismiss="modal">取消</button>
	            <button type="button" class="btn-sm btn-primary" onclick="toCheck()">确定</button>
	          </div>
	        </div>
	      </div>
	    </div>
</body>
<script type="text/javascript">
	/* window.onload = function() { 
		$("input[type=checkbox]").click(function(){
			alert("test");
		});
	}; */
	
	$('#myModel').on('shown.bs.modal', function (e) {  
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#youModel .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });  
    });  
	
	$(document.body).ready(function () {
	    //首次获取验证码
	    $.post("${ctx}/query/getVerifyImg",function(result){
	    		$("#imgCode").attr("src","${ctx}/query/getVerifyImg");
		   });
	});
	
	// 点击改变验证码
	function toChangeVerify(){
		var id = Math.random();
		 $.post("${ctx}/query/getVerifyImg",function(result){
			 	$("#errorCode").hide();
			 	$("#imgCode").attr("src","${ctx}/query/getVerifyImg?"+id);
		    });
	}
	
	function toCheck(){
		$('#myModal').modal();
		var validatorCode = $("#validatorCode").val();
		$.post("${ctx}/query/checkVerifyCode",{validatorCode:validatorCode},function(result){
			if(result == "FALSE"){
				if(validatorCode == null||validatorCode == ""){
					$("#errorCode").hide()
				}else{
					$("#errorCode").show()
				}
			}
			
			if(result == "TRUE"){
				console.log(result);
				$("#myModal").hide();
				$("#errorCode").hide();
				//caquery();
				// 验证成功,进行必要请求
				$('#pageNo').val('1');
				$('#newquery').val('1');
				$('#queryForm').submit();
			}
		})
	}
	
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
		}
		
		/* var passwd = prompt("请插入KEY并输入密码:");
		SafeEngineCtl.SEH_InitialSession(0xa, "com1", passwd, 0, 0xa, "com1",
				"");
		if (SafeEngineCtl.ErrorCode != 0) {
			alert("请确认密码及KEY是否插入电脑.");
			return;
		}
		strCert = SafeEngineCtl.SEH_GetSelfCertificate(0xa, "com1", "");
		if (SafeEngineCtl.ErrorCode != 0) {
			alert("KEY状态异常.");
			return;
		}
		strCert = SafeEngineCtl.SEH_GetSelfCertificate(0xa, "com1",
				"");
		if (SafeEngineCtl.ErrorCode != 0) {
			alert("KEY状态异常.");
			return;
		}
		strItemVal = SafeEngineCtl.SEH_GetCertDetail(strCert, 17);
		if (SafeEngineCtl.ErrorCode != 0) {
			alert("KEY状态异常.");
			SafeEngineCtl.SEH_ClearSession();
			return;
		}  */
		/* SafeEngineCtl.SEH_ClearSession(); */
		if (type == '1') {
			var roleId = "${sessionScope.user.roleId}";
			if(roleId.indexOf("7") >= 0){
				if(${obj.queryCount} >= ${obj.xzCount}){
					toCheck();
				}else{
					$('#pageNo').val('1');
					$('#newquery').val('1');
					$('#queryForm').submit();
				}
			}else{
				caquery();
			}
			
			//$('#pageNo').val('1');
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
	
	function caquery(){
		// 验证成功,进行必要请求
		//	$('#pageNo').val('1');
		//	$('#newquery').val('1');
		//	$('#queryForm').submit();
		//	return;
	  	if(document.getElementById("Object").innerHTML.length < 140){
	  		alert("在使用key前需要安装证书管理器.");
	  		return;
	  	}
	  	var name= "${sessionScope.user.displayName}";
	  	var sessionpsd = '${sessionScope.keypsd}';
	  	strpripath="com1"; 
		strcertpath="com1";
		strcertchainpath="com1";
		ConfigurationNum=0;
		DevNumber=0xa;
	  	if(sessionpsd != ''){
	  		SafeEngineCtl.SEH_InitialSession(DevNumber,strpripath, sessionpsd, 0, DevNumber, strcertchainpath, "");
	  		strCert = SafeEngineCtl.SEH_GetSelfCertificate(DevNumber, strcertpath, "");
	  		strItemVal = SafeEngineCtl.SEH_GetCertDetail(strCert, 17);
  			if(SafeEngineCtl.ErrorCode==0&&name==strItemVal)
  			{
  				if(${obj.queryCount} >= ${obj.xzCount}){
  					toCheck();
  					return;
  				}else{
	  				$('#pageNo').val('1');
	  				$('#newquery').val('1');
	  				$('#queryForm').submit();
	  				return;
  				}

  			}else if(SafeEngineCtl.ErrorCode!=0){
  				alert('key已被拔出，请关闭浏览器后重新登录');
  				return;	
  			}else{
  				alert("key用户与当前用户不一致！");
  				return;
  				}
  			}
	  	$.ligerDialog.prompt('请输入KEY的密码!', function (yes,value) { 
	  		if(yes) {
	  			strpassword = value;
	  		  	if(strpassword == ""){
	  		  		alert("密码不能为空.");
	  		  		return;
	  		  	}
	  			SafeEngineCtl.SEH_InitialSession(DevNumber,strpripath, strpassword, 0, DevNumber, strcertchainpath, "");
	  			if(SafeEngineCtl.ErrorCode!=0)
	  			{
	  				alert("请确认证书插入、密码是否输入正确！");
	  				return;
	  			}
	  			$.ajax({
	  				tyep : "POST",
	  				url : "${ctx}/ca/getId",
	  				data:{password:value},
	  				dataType : "Json",
	  				sync : false,
	  				success : function(str){
	  					if(str != null){
	  						strCert = SafeEngineCtl.SEH_GetSelfCertificate(DevNumber, strcertpath, "");	
	  						if(SafeEngineCtl.ErrorCode!=0)
	  						{
	  							alert("证书异常,请检查key是否正常.");
	  							return;
	  						}
	  						strSigned = SafeEngineCtl.SEH_SignData(str, 3);	
	  						if(SafeEngineCtl.ErrorCode!=0)
	  						{
	  							alert("证书异常,请检查key是否正常.");
	  							SafeEngineCtl.SEH_ClearSession();
	  							return;
	  						}
	  						strItemVal = SafeEngineCtl.SEH_GetCertDetail(strCert, 17);
	  						if(name!=strItemVal){
	  							alert("key用户与当前用户不一致！");
	  							SafeEngineCtl.SEH_ClearSession();
	  							return;
	  						}
	  						if(SafeEngineCtl.ErrorCode!=0)
	  						{
	  							alert("证书异常,请检查key是否正常.");
	  							SafeEngineCtl.SEH_ClearSession();
	  							return;
	  						}
	  						strVal= SafeEngineCtl.SEH_SignData(strItemVal, 3);	
	  						if(SafeEngineCtl.ErrorCode!=0)
	  						{
	  							alert("证书异常,请检查key是否正常.");
	  							SafeEngineCtl.SEH_ClearSession();
	  							return;
	  						}
	  						$.ajax({
	  							tyep : "POST",
	  							url : "${ctx}/ca/checker",
	  							data : {"cert" : strCert,"signed" : strSigned,"itemVal" : strVal},
	  							dataType : "Json",
	  							sync : false,
	  							success : function (s){
	  								if(s != null){
	  									//判断查询是否超过阈值，超过则给出验证码校验，通过才查询
	  									if(${obj.queryCount} >= ${obj.xzCount}){
	  										toCheck();
	  									}else{
	  										$('#pageNo').val('1');
	  										$('#newquery').val('1');
	  										$('#queryForm').submit();
	  									}
	  								}else{
	  									alert("验证失败.");
	  								}
	  							},
	  							error : function (){
	  								alert("服务器繁忙,请稍后再试.");
	  							}
	  						});
	  					}else{
	  						alert("随机数非法.");
	  					}
	  				},
	  				error : function(){
	  					alert("服务器繁忙,请稍后再试.");
	  				}
	  			});
	  		}
	  	});
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
	function changedate1(){
		var date=$('#csny1').val();
		$('#csny2').val(date);
	}
	function changedate2(){
		var date=$("#csny1").val();
		var date1=$("#csny2").val();
		if(date.replace("-","")>date1.replace("-","")){
			alert("请选择大于等于"+date+"的日期！");
			$('#csny2').val(date);
		}
	}
</script>
</html>