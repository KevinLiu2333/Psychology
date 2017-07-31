<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id="addForm" style="padding: 10px">
		<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
			<tr>
				<td align="right" style="width:120px;">旧密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="oldPassword" name="oldPassword" class="mini-password" maxlength="50" required="true" style="width:100%;"/>
				</td>
			</tr>
			<tr id="oldPasswordTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">请输入正确的旧密码！</font></div></td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">新密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password" name="password" class="mini-password" maxlength="50" onblur="checkPassword()" required="true" style="width:100%;"/>
				</td>
			</tr>
			<tr>
				<td align="right" style="width:120px;">确认密码：</td>
				<td style="width:550px;" colspan="3">
					<input id="password2" class="mini-password" maxlength="100" onblur="checkPassword()" style="width:100%;" required="true"/>
				</td>
			</tr>
			<tr id="alertTr" style="display:none;">
				<td align="left" colspan="4"><div style="padding-left:120px"><font color="red">两次输入密码不一致！</font></div></td>
			</tr>
		</table>
	</div>
	<div>
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr align="center">
	        	<td align="center">
					<a class="mini-button" iconCls="icon-save" id="saveBtn"
					onclick="doSave()">保存</a> 
	        	</td>
	    	</tr>
	 	</table>
	</div>
