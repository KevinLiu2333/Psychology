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
				<td align="right" style="width:120px;">用户名<font color="red">*</font>：</td>
				<td style="width:550px;" colspan="3">
					<input id="loginname" name="loginname" class="mini-textbox" maxlength="50"  onblur="checkLoginName()" required="true" style="width:100%;"/>
					<input id="userid" name="userid" class="mini-hidden" />
				</td>
			</tr>
			<tr id="loginNameTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">该用户名已使用！</font></div></td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">新密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-password" maxlength="50" onblur="checkPassword()" style="width:100%;"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">确认密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password2" name="password2" class="mini-password" maxlength="100" onblur="checkPassword()" style="width:100%;"/>
				</td>
			</tr>
			<tr id="changeTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">如不更改密码，请保持密码框为空。</font></div></td>
			</tr>
			<tr id="newTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">默认初始密码为111111。</font></div></td>
			</tr>
			<tr id="alertTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">两次输入密码不一致！</font></div></td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">显示名<font color="red">*</font>：</td>
				<td style="width:550px;" colspan="3">
					<input id="displayname" name="displayname" class="mini-textbox" maxlength="50" required="true" style="width:100%;"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">角色<font color="red">*</font>：</td>
				<td style="width:550px;" colspan="3">
					<div id="roleDiv" class="mini-checkboxlist" repeatItems="4" repeatLayout="table"
   						 textField="text" valueField="id" url="${pageContext.request.contextPath}/m38/m380101/m380101Action!loadRole.action" >
					</div>
				</td>
			</tr>
			
		</table>
	</div>
	<div>
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr align="center">
	        	<td align="center">
					<a class="mini-button" iconCls="icon-save" id="saveBtn"
					onclick="doSave()">保存</a> &nbsp;&nbsp;&nbsp; <a class="mini-button"
					iconCls="icon-close" onclick="closeWin()">关闭</a>
	        	</td>
	    	</tr>
	 	</table>
	</div>
	<script type="text/javascript">
		mini.parse();
		form = new mini.Form("#addForm");
		var action = "";
		var pswFlag = true;
		var nameFlag = true;
		function SetData(data){
			try{
				//跨页面传递的数据对象，克隆后才可以安全使用
				data = mini.clone(data);
				action = data.action;
				if(action=="edit"){
					$('#changeTr').show();
					mini.get("loginname").disable();
				}else{
					mini.get("password").setValue("111111");
					mini.get("password2").setValue("111111");
					mini.get("password").setRequired(true);
					mini.get("password2").setRequired(true);
					$('#newTr').show();
					return;
				}
				MiniUtils.maskwin("导入中...");
				MiniUtils.request({
					url: "${pageContext.request.contextPath}/m38/m380101/m380101Action!load.action",
					data: { userid: data.userid},
					success: function (text) {
						form.setData(text.user);
						mini.get("password").setValue('');
						mini.get("roleDiv").setValue(text.roleids);
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
				var roleids = mini.get("roleDiv").getValue();
			    var url = "";
				if(action=="new"){
						url = "${pageContext.request.contextPath}/m38/m380101/m380101Action!add.action";
					}else{
						url = "${pageContext.request.contextPath}/m38/m380101/m380101Action!edit.action";
					}
				MiniUtils.request({
					url : url,
					data: { jsonData:data,roleids:roleids},
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
		
		function checkPassword(){
			var p1 = mini.get("password").getValue();
			var p2 = mini.get("password2").getValue();
			if (p1 != p2){
				$('#alertTr').show();
				pswFlag = false;
			} else {
				pswFlag = true;
				$('#alertTr').hide();
				
			}
			if (pswFlag && nameFlag){
				mini.get("saveBtn").enable();
			} else {
				mini.get("saveBtn").disable();
			}
		}
		
		function checkLoginName(){
			MiniUtils.maskwin("查询中...");
			var loginname = mini.get("loginname").getValue();
			loginname = loginname.trim();
			if(loginname==""){
				MiniUtils.unmaskwin();
				return false;
			}
			MiniUtils.request({
					url : "${pageContext.request.contextPath}/m38/m380101/m380101Action!checkLoginName.action",
					data: {loginname:loginname},
					success: function (text) {
						MiniUtils.unmaskwin();
						if (text.flag == '0'){
							$('#loginNameTr').show();
							nameFlag = false;
						} else {
							nameFlag = true;
							$('#loginNameTr').hide();
						}
						if (nameFlag && pswFlag){
							mini.get("saveBtn").enable();
						} else {
							mini.get("saveBtn").disable();
						}
					},
					error: function (text) {
						MiniUtils.unmaskwin();
						mini.get("saveBtn").disable();
						mini.alert("用户名校验失败，返回结果:" + text);
					}
				});
		}
	</script>
</body>
</html>