<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body>
<div id="01Form" style="padding: 10px;display:none">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
		<tr>
			<td align="right" style="width:120px;">接口代码：</td>
			<td style="width:150px;">
				<input id="ifdefcode" name="ifdefcode" class="mini-textbox asLabel" maxlength="25"  required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">接口名称：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifdefname" name="ifdefname" class="mini-textbox asLabel" maxlength="50" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">访问地址：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifuri" name="ifuri" class="mini-textbox asLabel" maxlength="250" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right">接口类型：</td>
			<td colspan="3">
				<input id="ifsubtype" name="ifsubtype" class="mini-combobox asLabel" maxlength="250" required="true" style="width:100%;" data="DIC_IFSUBTYPE" readonly="readonly"/>
			</td>
		</tr>
	</table>
	<div id="01WSDL">
		<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
			<tr>
				<td align="right" style="width:120px;">空间名：</td>
				<td style="width:225px;">
					<input id="namespace" name="namespace" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">服务名：</td>
				<td style="width:225px;">
					<input id="service" name="service" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">参数：</td>
				<td style="width:225px;">
					<input id="params" name="params" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">参数类型：</td>
				<td style="width:225px;">
					<input id="paramstype" name="paramstype" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">返回类型：</td>
				<td style="width:225px;">
					<input id="returntype" name="returntype" class="mini-combobox asLabel" required="true" style="width:30%;" data="DIC_RETURNTYPE" readonly="readonly"/>					
					加密标志:<input id="flag" name="flag" class="mini-checkbox asLabel" required="true" style="width:30%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">证书路径：
				</td>
				<td style="width:225px;">
					<input id="path" name="path" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">用户名：</td>
				<td style="width:550px;" colspan="3">
					<input id="username" name="username" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="01REST" style="display:none">
		<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
			<tr>
				<td align="right" style="width:120px;">方法：</td>
				<td style="width:225px;">
					<input id="method" name="method" class="mini-combobox asLabel" required="true" style="width:70%;" data="DIC_METHOD" readonly="readonly"/>
				</td>			
				<td align="right" style="width:100px;">接收类型：</td>
				<td style="width:225px;">
					<input id="accepttype" name="accepttype" class="mini-combobox asLabel" required="true" style="width:70%;" data="DIC_ACCEPTYPE" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">加密标志:</td>
				<td style="width:225px;">
					<input id="flag" name="flag" class="mini-checkbox asLabel" required="true" style="width:30%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">证书路径
				</td>
				<td style="width:225px;">
					<input id="path" name="path" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">headerkey：</td>
				<td style="width:550px;" colspan="3">
					<input id="headerkey" name="headerkey" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">headervalue：</td>
				<td style="width:550px;" colspan="3">
					<input id="headervalue" name="headervalue" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">用户名：</td>
				<td style="width:550px;" colspan="3">
					<input id="username" name="username" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</div>
</div>
<div id="02Form" style="padding: 10px;display:none">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
		<tr>
			<td align="right" style="width:120px;">应用代码：</td>
			<td style="width:150px;">
				<input id="ifdefcode" name="ifdefcode" class="mini-textbox asLabel" maxlength="25"  required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">应用名称：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifdefname" name="ifdefname" class="mini-textbox asLabel" maxlength="50" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">访问地址：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifuri" name="ifuri" class="mini-textbox asLabel" maxlength="250" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
	</table>            
</div>
<div id="03Form" style="padding: 10px;display:none">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
         <tr>
             <td style="width:120px;" align="right">开放端口代码：</td>
             <td style="width:400px;">    
				<input id="ifdefcode" name="ifdefcode" class="mini-textbox asLabel" maxlength="25"  required="true" style="width:100%;" readonly="readonly"/>
             </td>
         </tr>
         <tr>
             <td align="right">开放端口名称：</td>
             <td>    
				<input id="ifdefname" name="ifdefname" class="mini-textbox asLabel" maxlength="50" required="true" style="width:100%;" readonly="readonly"/>
             </td>
         </tr>
         <tr>
             <td align="right">访问地址：</td>
             <td>    
				<input id="ifuri" name="ifuri" class="mini-textbox asLabel" maxlength="250" required="true" style="width:100%;" readonly="readonly"/>
             </td>
         </tr> 
     </table>            
</div>
<div id="04Form" style="padding: 10px;display:none">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
		<tr>
			<td align="right" style="width:120px;">接口代码：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifdefcode" name="ifdefcode" class="mini-textbox asLabel" maxlength="25"  required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">接口名称：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifdefname" name="ifdefname" class="mini-textbox asLabel" maxlength="50" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">访问地址：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifuri" name="ifuri" class="mini-textbox asLabel" maxlength="250" required="true" style="width:100%;" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td align="right">接口类型：</td>
			<td colspan="3">
				<input id="ifsubtype" name="ifsubtype" class="mini-combobox asLabel" maxlength="250" required="true" style="width:100%;" data="DIC_IFSUBTYPE" readonly="readonly"/>
			</td>
		</tr>
	</table>
	<div id="04WSDL">
		<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
			<tr>
				<td align="right" style="width:120px;">空间名：</td>
				<td style="width:225px;">
					<input id="namespace" name="namespace" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">服务名：</td>
				<td style="width:225px;">
					<input id="service" name="service" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">参数：</td>
				<td style="width:225px;">
					<input id="params" name="params" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
				<td align="right" style="width:100px;">参数类型：</td>
				<td style="width:225px;">
					<input id="paramstype" name="paramstype" class="mini-textbox asLabel" required="true" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">返回类型：</td>
				<td style="width:225px;">
					<input id="returntype" name="returntype" class="mini-combobox asLabel" required="true" style="width:65%;" data="DIC_RETURNTYPE" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">用户名：</td>
				<td style="width:550px;" colspan="3">
					<input id="username" name="username" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="04REST" style="display:none">
		<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
			<tr>
				<td align="right" style="width:120px;">方法：</td>
				<td style="width:225px;">
					<input id="method" name="method" class="mini-combobox asLabel" required="true" style="width:70%;" data="DIC_METHOD" readonly="readonly"/>
				</td>			
				<td align="right" style="width:100px;">接收类型：</td>
				<td style="width:225px;">
					<input id="accepttype" name="accepttype" class="mini-combobox asLabel" required="true" style="width:70%;" data="DIC_ACCEPTYPE" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">headerkey：</td>
				<td style="width:550px;" colspan="3">
					<input id="headerkey" name="headerkey" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">headervalue：</td>
				<td style="width:550px;" colspan="3">
					<input id="headervalue" name="headervalue" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">用户名：</td>
				<td style="width:550px;" colspan="3">
					<input id="username" name="username" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-textbox asLabel" style="width:100%;" readonly="readonly"/>
				</td>
			</tr>
		</table>
	</div>
</div>

<div>
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr align="center">
	        	<td align="center">
					<a class="mini-button" iconCls="icon-close" onclick="closeWin()">关闭</a>	        	
					</td>
	    	</tr>
	 	</table>
	</div>
<script type="text/javascript">
mini.parse();
function SetData(data) {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		data = mini.clone(data);
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!load.action",
			data: { ifdefineid: data.ifdefineid,
					iftype:data.iftype },
			success: function (text) {			
				var formid=text.iftype+"Form";
				form = new mini.Form("#"+formid);
				form.setData(text);
				if(text.ifsubtype){
					
				}
			    $("#"+formid).show();
			    if(text.ifsubtype=="02"){
					wsdl = new mini.Form("#"+text.iftype+"WSDL");
					rest = new mini.Form("#"+text.iftype+"REST");			    
			    	$("#"+text.iftype+"WSDL").hide();
			    	$("#"+text.iftype+"REST").show();
			    	var dataext=mini.decode(text.ifext);
			    	rest.setData(dataext);
			    }
			    if(text.ifsubtype=="01"){
					wsdl = new mini.Form("#"+text.iftype+"WSDL");
					rest = new mini.Form("#"+text.iftype+"REST");			    
			    	$("#"+text.iftype+"REST").hide();
			    	$("#"+text.iftype+"WSDL").show();
			    	var dataext=mini.decode(text.ifext);
			    	wsdl.setData(dataext);
			    }
				MiniUtils.unmaskwin();	
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
}

function onActionRender(e){
	var recoder = e.recoder;
	recoder = toLocalDic(recoder,DIC_IFSUBTYPE);
	return recoder;
}


function closeWin(){
	try{
		if (window.CloseOwnerWindow){
			return window.CloseOwnerWindow();
		}else{
			window.close();
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}
</script>
</body>
</html>