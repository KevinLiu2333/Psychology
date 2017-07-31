<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新增接口</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
	</head>
	<body>
		<div id="addForm" style="padding: 10px">
			<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
				<tr>
					<td align="right" style="width:120px;">接口代码<font color="red">*</font>：</td>
					<td style="width:550px;" colspan="3">
						<input id="ifdefcode" name="ifdefcode" class="mini-textbox" maxlength="25"  required="true" style="width:100%;"/>
						<input id="iftype" name="iftype" class="mini-hidden" value="04"/>
						<input id="ifdefineid" name="ifdefineid" class="mini-hidden" />
					</td>
				</tr>
				<tr>
					<td align="right" style="width:120px;">接口名称<font color="red">*</font>：</td>
					<td style="width:550px;" colspan="3">
						<input id="ifdefname" name="ifdefname" class="mini-textbox" maxlength="50" required="true" style="width:100%;"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width:120px;">访问地址<font color="red">*</font>：</td>
					<td style="width:550px;" colspan="3">
						<input id="ifuri" name="ifuri" class="mini-textbox" maxlength="250" required="true" style="width:100%;"/>
					</td>
				</tr>
				<tr>
					<td align="right">接口类型<font color="red">*</font>：</td>
					<td colspan="3">
						<div id="ifsubtype" class="mini-radiobuttonlist" repeatItems="2" repeatLayout="table"
		 				   textField="text" valueField="id" value="01" data="DIC_IFSUBTYPE">
						</div> 
					</td>
				</tr>
			</table>
			<div id="WSDL">
				<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
					<tr>
						<td align="right" style="width:120px;">空间名<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="namespace" name="namespace" class="mini-textbox" required="true" style="width:100%;"/>
						</td>
						<td align="right" style="width:100px;">服务名<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="service" name="service" class="mini-textbox" required="true" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">参数<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="params" name="params" class="mini-textbox" required="true" style="width:100%;"/>
						</td>
						<td align="right" style="width:100px;">参数类型<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="paramstype" name="paramstype" class="mini-textbox" required="true" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">返回类型<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="returntype" name="returntype" class="mini-combobox" required="true" style="width:65%;" data="DIC_RETURNTYPE"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">用户名：</td>
						<td style="width:550px;" colspan="3">
							<input id="username" name="username" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">密码：</td>
						<td style="width:550px;" colspan="3">
							<input id="password" name="password" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
				</table>
			</div>
			<div id="REST" style="display:none">
				<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
					<tr>
						<td align="right" style="width:120px;">方法<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="method" name="method" class="mini-combobox" required="true" style="width:70%;" data="DIC_METHOD"/>
						</td>			
						<td align="right" style="width:100px;">接收类型<font color="red">*</font>：</td>
						<td style="width:225px;">
							<input id="accepttype" name="accepttype" class="mini-combobox" required="true" style="width:70%;" data="DIC_ACCEPTYPE"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">headerkey：</td>
						<td style="width:550px;" colspan="3">
							<input id="headerkey" name="headerkey" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">headervalue：</td>
						<td style="width:550px;" colspan="3">
							<input id="headervalue" name="headervalue" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">用户名：</td>
						<td style="width:550px;" colspan="3">
							<input id="username" name="username" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:120px;">密码：</td>
						<td style="width:550px;" colspan="3">
							<input id="password" name="password" class="mini-textbox" style="width:100%;"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr align="center">
		        	<td align="center">
        				<a id="test" class="mini-button" onclick="onTest" style="width:60px;margin-right:20px;">测试</a>         
						<a class="mini-button" iconCls="icon-save" onclick="doSave()">保存</a> &nbsp;&nbsp;&nbsp;
						<a class="mini-button" iconCls="icon-reload" onclick="doReset">重置</a> &nbsp;&nbsp;&nbsp;
						<a class="mini-button" iconCls="icon-close" onclick="closeWin()">关闭</a>
		        	</td>
		    	</tr>
		 	</table>
		</div>
		<script type="text/javascript">
			//var Types = [{ id: 'string', text: 'string' }, { id: 'byte[]', text: 'byte[]'}];
			
			mini.parse();
			form = new mini.Form("#addForm");
			wsdl = new mini.Form("#WSDL");
			rest = new mini.Form("#REST");
			var action = "";
			function SetData(data){
				try{
					//跨页面传递的数据对象，克隆后才可以安全使用
					data = mini.clone(data);
					action = data.action;
					if(action=="edit"){
						mini.get("ifdefcode").setEnabled(false);
					}else{
						return;
					}
					MiniUtils.maskwin("导入中...");
					MiniUtils.request({
						url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!load.action",
						data: { ifdefineid: data.ifdefineid },
						success: function (text) {
							form.setData(text);
							mini.get("ifsubtype").setValue(text.ifsubtype);
						    if(text.ifsubtype=="02"){
						    	$("#WSDL").hide();
						    	$("#REST").show();
						    	var dataext=mini.decode(text.ifext);
						    	rest.setData(dataext);
						    }
						    if(text.ifsubtype=="01"){
						    	$("#REST").hide();
						    	$("#WSDL").show();
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
			
			function doSave(){
			    form.validate();
				if (form.isValid() == false) {
					mini.alert('请检查必填项！');
					return false;
				}		
					
				try{
					MiniUtils.maskwin("提交中...");
					var data = form.getData();//获取表单多个控件的数据
					data = mini.encode(data);//序列化成JSON
					var dataext;
					var ifsubtype = mini.get("ifsubtype").getValue();
					if(ifsubtype=="01"){
			    		dataext = wsdl.getData();//获取表单多个控件的数据
						dataext = mini.encode(dataext);//序列化成JSON
			 		}
				    if(ifsubtype=="02"){
						dataext = rest.getData();//获取表单多个控件的数据
						dataext = mini.encode(dataext);//序列化成JSON]
				    }
				    var url = "";
					if(action=="new"){
							url = "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!add.action";
						}else{
							url = "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!edit.action";
						}
					MiniUtils.request({
						url : url,
						data: { jsonData: data,
								dataext:dataext,
								ifsubtype:ifsubtype },
						success: function (text) {
							MiniUtils.unmaskwin();	
							MiniUtils.info("保存成功!",closeWin);
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
			
			var ifsubtype = mini.get("ifsubtype");
			ifsubtype.on("valuechanged", function (e) {
			    if(this.getValue()=="02"){
			    	$("#WSDL").hide();
			    	$("#REST").show();
			    }
			    if(this.getValue()=="01"){
			    	$("#REST").hide();
			    	$("#WSDL").show();
			    }
			});
			
		function onTest(e){
        	try{
        		var data = form.getData();//获取表单多个控件的数据
				if(data.ifuri){	
					MiniUtils.maskwin("测试中...");		
					MiniUtils.request({
						url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!test.action",
						data: {ifuri:data.ifuri,ifsubtype:mini.get("ifsubtype").getValue()},
						success: function (text) {
							MiniUtils.unmaskwin();
							if(text.detailmsg){
								mini.alert("测试结果："+text.detailmsg);
							}
						},
						error: function (text) {
							MiniUtils.unmaskwin();
							mini.alert("提交失败，返回结果:" + text);
						}
					});	
				}else{
					mini.alert("请输入访问地址");
				}								
			}catch(e){
				MiniUtils.unmaskwin();
				mini.alert("未知异常："+e.responseText);
			}
        }
        
function doReset() {
if (action == "edit") {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!load.action",
			data: { ifdefineid: mini.get("ifdefineid").getValue() },
			success: function (text) {
				form.setData(text);
				mini.get("ifsubtype").setValue(text.ifsubtype);
			    if(text.ifsubtype=="02"){
			    	$("#WSDL").hide();
			    	$("#REST").show();
			    	var dataext=mini.decode(text.ifext);
			    	rest.setData(dataext);
			    }
			    if(text.ifsubtype=="01"){
			    	$("#REST").hide();
			    	$("#WSDL").show();
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
	}else{
		form.reset();	
	}
}
		</script>
	</body>
</html>